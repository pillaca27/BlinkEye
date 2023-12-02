package net.royal.autorizacion.seguridad.rest;

import java.io.File;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.core.UExceptionSessionFinished;
import net.royal.spring.framework.core.UFile;
import net.royal.spring.framework.core.UWebServer;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadMenu;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioLogin;
import net.royal.spring.framework.util.SeguridadHelper;
import net.royal.spring.framework.util.UBoolean;
import net.royal.spring.framework.util.UPropiedades;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.constante.ConstanteFiltro;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.seguridad.dao.impl.UsuarioSeguridadDaoImpl;
import net.royal.spring.seguridad.dominio.dto.RecaptchaV3Response;
import net.royal.spring.seguridad.servicio.impl.Menu01ServicioImpl;
import net.royal.spring.seguridad.servicio.impl.Menu02ServicioImpl;
import net.royal.spring.seguridad.servicio.impl.UsuarioSeguridadServicioImpl;

@RestController
@RequestMapping("/autorizacion/seguridad/login")
@CrossOrigin(origins = "*")
public class LoginRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(LoginRest.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse httpResponse;

	@Autowired
	private UsuarioSeguridadServicioImpl servicio;

	@Autowired
	private UsuarioSeguridadDaoImpl usuarioDao;
	
	@Autowired
	private Menu01ServicioImpl menu01;

	@Autowired
	private Menu02ServicioImpl menu02;

	public static Map<String, Object> sessiones = new HashMap<String, Object>();
	public static BigDecimal minutosSessionGlobal = new BigDecimal(200);

	
	/*INICIO: CRYSTAL*/
	
	//BUSQUEDA PARA LA COMPANIAS DEL USUARIO
	/**
	 * Post listarCompaniasPorUsuario()
	 * 
	 * @return
	 */
	@Transactional
	@PutMapping(value = "/listarcompaniasporusuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarCompaniasPorUsuario(@RequestBody SeguridadUsuarioLogin bean)
			throws Exception {
		logger.debug("LoginRest.listarCompaniasPorUsuario 111");
		List<DtoTabla> lst = null;
		try {
			lst = servicio.listarCompanias(bean.getUsuario());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<DtoTabla>>(lst, HttpStatus.OK);
	}
	
	//LOGEO PARA EL USUARIO
	/**
	 * Post ingresar
	 * 
	 * @return
	 */
	@Transactional
	@PutMapping(value = "/ingresar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SeguridadUsuarioLogin> ingresar(@RequestBody SeguridadUsuarioLogin bean) throws Exception {
		logger.debug("LoginRest.ingresar");
		logger.debug("bean.getAplicacionCodigo():"+bean.getAplicacionCodigo());
		
		// Validacion Captcha
		RestTemplate restTemplate = null;
		if (UString.esNuloVacio(proxyUsar))
			this.leerPropiedades();
		if (this.proxyUsar.equals("S")) {
			Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress(proxyNombre, proxyPuerto));
			SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
			requestFactory.setProxy(proxy);
			restTemplate = new RestTemplate(requestFactory);
		}else {
			restTemplate = new RestTemplate();
		}
		
		
		String recursoRuta = UWebServer.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;
		Properties recursoPropiedad = UPropiedades.abrir(recursoRuta, ConstanteBoot.PROPERTIES_GLOBAL);
		String validarCaptcha = (String) recursoPropiedad.get("captcha.validar");
		if (UBoolean.validarFlag(validarCaptcha)) {
			logger.debug("CAPCHA = ingreso a validar");
			String RECAPTCHA_SECRET = (String) recursoPropiedad.get("captcha.key");
			RecaptchaV3Response r = new RecaptchaV3Response();
			r.setSuccess(false);
			URI verifyingUri = URI
					.create(String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s",
							RECAPTCHA_SECRET, bean.getToken()));
			r = restTemplate.getForObject(verifyingUri, RecaptchaV3Response.class);
			if (!r.getSuccess()) {
				logger.debug("CAPCHA = Error de autenticacion");
				bean = new SeguridadUsuarioLogin();
				bean.setTransaccionEstado(DominioTransaccion.VALIDACION);
				bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Error"));
				return new ResponseEntity<SeguridadUsuarioLogin>(bean, HttpStatus.OK);
			}else {
				logger.debug("CAPCHA = Validado");
			}
		}else {
			logger.debug("CAPCHA = NO SE USA");
		}
		
		String tmp = null;

		if (bean == null) {
			bean = new SeguridadUsuarioLogin();
			bean.setTransaccionEstado(DominioTransaccion.VALIDACION);
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Usuario y/o clave incorrecto"));
			return new ResponseEntity<SeguridadUsuarioLogin>(bean, HttpStatus.OK);
		}

		String tipo = "";
		tipo = servicio.verificarTipoUsuario(bean.getUsuario(), UString.obtenerSinNulo(SeguridadHelper.springEncriptar(UString.obtenerValorCadenaSinNulo(bean.getClave()))), bean.getCompaniaCodigo());
		
		bean.setTipoUsuarioId(tipo);
		
		tmp = UString.obtenerValorCadenaSinNulo(bean.getUsuario()).trim().toUpperCase();
		bean.setUsuario(tmp);

		tmp = UString.obtenerValorCadenaSinNulo(bean.getClave()).trim();
		bean.setClave(tmp);

		SeguridadUsuarioActual usuarioValidado = null;

		if (UString.esNuloVacio(bean.getTipoUsuarioId()))
		{
			bean.setTransaccionEstado(DominioTransaccion.VALIDACION);
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Tipo de usuario no encontrado"));
			return new ResponseEntity<SeguridadUsuarioLogin>(bean, HttpStatus.OK);
		}
		else
		{
			if (bean.getTipoUsuarioId().equals(SeguridadUsuarioLogin.TIPOUSUARIO_DOCENTE) || bean.getTipoUsuarioId().equals(SeguridadUsuarioLogin.TIPOUSUARIO_AUXILIAR) || 
				bean.getTipoUsuarioId().equals(SeguridadUsuarioLogin.TIPOUSUARIO_DIRECTOR) || bean.getTipoUsuarioId().equals(SeguridadUsuarioLogin.TIPOUSUARIO_SUBDIRECTOR)) {

				if (bean.getUsuario() == null) {
					bean = new SeguridadUsuarioLogin();
					bean.setTransaccionEstado(DominioTransaccion.VALIDACION);
					bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Usuario y/o clave incorrecto"));
					return new ResponseEntity<SeguridadUsuarioLogin>(bean, HttpStatus.OK);
				}

				usuarioValidado = servicio.login(bean.getAplicacionCodigo(), bean.getUsuario(), bean.getClave(),bean.getCompaniaCodigo());

				if (usuarioValidado.getTransaccionEstado().equals(DominioTransaccion.ERROR)) {
					bean = new SeguridadUsuarioLogin();
					bean.setTransaccionEstado(DominioTransaccion.ERROR);
					bean.setTransaccionListaMensajes(usuarioValidado.getTransaccionListaMensajes());
					return new ResponseEntity<SeguridadUsuarioLogin>(bean, HttpStatus.OK);
				}

//				if (servicio.expiroUsuario(usuarioValidado.getUsuario())) {
//					bean = new SeguridadUsuarioLogin();
//					bean.setTransaccionEstado(DominioTransaccion.ERROR);
//					bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Usuario y/o clave incorrecto"));
//					return new ResponseEntity<SeguridadUsuarioLogin>(bean, HttpStatus.OK);
//				}
				
				logger.debug("bean.getAplicacionCodigo():" + bean.getAplicacionCodigo());
				tmp = menu01.usuarioEsAdministradorDeAplicacion(bean.getAplicacionCodigo(), bean.getUsuario());
				
				usuarioValidado.setEsAdministradorAplicacion(tmp);
			}
		}
		
		if(usuarioValidado == null)
		{
			bean = new SeguridadUsuarioLogin();
			bean.setTransaccionEstado(DominioTransaccion.VALIDACION);
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Usuario y/o clave incorrecto"));
			return new ResponseEntity<SeguridadUsuarioLogin>(bean, HttpStatus.OK);
		}
		
		//LOGEO PARA ALUMNOS
//		if (bean.getTipoUsuarioId().equals(SeguridadUsuarioLogin.TIPOUSUARIO_ALUMNO)) {
//			if (UString.estaVacio(bean.getCompaniaCodigo()) || UString.estaVacio(bean.getUsuario())) {
//				bean = new SeguridadUsuarioLogin();
//				bean.setTransaccionEstado(DominioTransaccion.ERROR);
//				bean.getTransaccionListaMensajes()
//						.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ingrese los datos"));
//				return new ResponseEntity<SeguridadUsuarioLogin>(bean, HttpStatus.OK);
//			}
//			usuarioValidado = servicio.loginAlumno(bean.getCompaniaCodigo(), bean.getUsuario(), bean.getClave());
//			if (usuarioValidado == null) {
//				bean = new SeguridadUsuarioLogin();
//				bean.setTransaccionEstado(DominioTransaccion.ERROR);
//				bean.getTransaccionListaMensajes()
//						.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Usuario y/o clave incorrecto"));
//				return new ResponseEntity<SeguridadUsuarioLogin>(bean, HttpStatus.OK);
//			}
//			flgTipoUsuarioEncontrado = true;
//			usuarioValidado.setTipoUsuarioId(bean.getTipoUsuarioId());
//			usuarioValidado.setUsuario(bean.getUsuario());
//		}
		
		ObjectMapper mapper = new ObjectMapper();
		SeguridadUsuarioActual usuarioActual = usuarioValidado;

		usuarioActual.setTipoUsuarioId(bean.getTipoUsuarioId());
		usuarioActual.setAplicacionCodigo(bean.getAplicacionCodigo());
		usuarioActual.setCompaniaCodigo(bean.getCompaniaCodigo());
		
		//Para la foto del usuario ingresado
		usuarioActual.setPersonaFotoUrl(servicio.fotoObtenerRuta(usuarioValidado.getPersonaId(), usuarioValidado.getPersonaNroDocumento()));

		String tokenMinutosString = (String)recursoPropiedad.get("token.minutos");
		Integer tokenMinutos = Integer.parseInt(tokenMinutosString); 
		Integer minutos = tokenMinutos * 100000;

		Calendar date = Calendar.getInstance();
		long t = date.getTimeInMillis();
		Date desde = new Date();
		Date hasta = new Date(t + (minutos));
		Date hastaLargo = new Date(t + (minutos));

		String jwtToken = Jwts.builder().setSubject(mapper.writeValueAsString(usuarioActual.getUsuario()))
				.claim("roles", "user").setIssuedAt(desde).signWith(SignatureAlgorithm.HS256, ConstanteBoot.TOKEN_JWTKEY)
				.setExpiration(hastaLargo).compact();

		bean.setTransaccionEstado(DominioTransaccion.OK);
		bean.setToken(jwtToken);

		Map<String, Object> mapUsuario = new HashMap<String, Object>();
		mapUsuario.put("hasta", hasta);
		mapUsuario.put("usuario", usuarioActual);
		sessiones.put(jwtToken, mapUsuario);

		return new ResponseEntity<SeguridadUsuarioLogin>(bean, HttpStatus.OK);
	}
	
	/*FIN: CRYSTAL*/
	
}
