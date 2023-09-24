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
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioLogin;
import net.royal.spring.framework.util.UBoolean;
import net.royal.spring.framework.util.UPropiedades;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.constante.ConstanteFiltro;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.seguridad.dao.impl.UsuarioDaoImpl;
import net.royal.spring.seguridad.dominio.dto.RecaptchaV3Response;
import net.royal.spring.seguridad.servicio.impl.UsuarioServicioImpl;

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
	private UsuarioServicioImpl servicio;

	@Autowired
	private UsuarioDaoImpl usuarioDao;

	public static Map<String, Object> sessiones = new HashMap<String, Object>();
	public static BigDecimal minutosSessionGlobal = new BigDecimal(200);

	/**
	 * Get validartoken
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/validartoken", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SeguridadUsuarioActual> validarToken() throws Exception {

		SeguridadUsuarioActual usuarioActual = null;

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String token = httpRequest.getHeader(ConstanteFiltro.TOKEN);

		if (UString.esNuloVacio(token)) {

			logger.debug("TOKEN VACIO");

			usuarioActual = new SeguridadUsuarioActual();
			usuarioActual.setTransaccionEstado(DominioTransaccion.ERROR);
			usuarioActual.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "token vacio"));
			return new ResponseEntity<SeguridadUsuarioActual>(usuarioActual, HttpStatus.OK);
		}

		Object map = this.sessiones.get(token.replace("Bearer ", ""));

		if (map == null) {

			logger.debug("TOKEN NO EN SESSION");

			usuarioActual = new SeguridadUsuarioActual();
			usuarioActual.setTransaccionEstado(DominioTransaccion.ERROR);
			usuarioActual.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "session no encontrada"));
			return new ResponseEntity<SeguridadUsuarioActual>(usuarioActual, HttpStatus.OK);
		}

		Map<String, Object> mapUsuario = (Map) map;
		Date fechaFin = (Date) mapUsuario.get("hasta");
		usuarioActual = (SeguridadUsuarioActual) mapUsuario.get("usuario");

		if (fechaFin == null) {

			logger.debug("TOKEN NO EN SESSION 2");

			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			throw new UException(this.getMessage("mensaje.uexception.session.no.encontrada"), tipo_mensaje.ERROR);
		}
		Date hasta = (Date) fechaFin;

		Date actual = new Date();

		if (actual.after(hasta)) {
			logger.debug("Session vencida");
			throw new UExceptionSessionFinished(this.getMessage("mensaje.uexception.session.terminada"));
		}

		usuarioActual.setTransaccionEstado(DominioTransaccion.OK);
		return new ResponseEntity<SeguridadUsuarioActual>(usuarioActual, HttpStatus.OK);
	}

	/**
	 * Get estavivo
	 * 
	 * @return
	 */
	@GetMapping(value = "/estavivo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> estavivo() {
		logger.debug("LoginRest.estavivo");
		String ss = "";
		Date now = new Date();
		try {
			ss = UFile.nombreUnico();
			logger.debug(ss);
			SeguridadUsuarioActual usu = this.getUsuarioActual();
			logger.debug(usu);
		} catch (Exception e) {
			logger.debug("usuario actual no encontrado");
			e.printStackTrace();
		}

		return new ResponseEntity<String>("SI - " + ss.toString(), HttpStatus.OK);
	}

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

	@Transactional
	@GetMapping(value = "/listarcompaniaslogin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarcompaniaslogin() throws Exception {
		logger.debug("LoginRest.listarcompaniaslogin");
		List<DtoTabla> lst = null;
		try {
			lst = servicio.listarcompaniaslogin();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<DtoTabla>>(lst, HttpStatus.OK);
	}

	/**
	 * Post ingresar
	 * 
	 * @return
	 */
	@Transactional
	@PutMapping(value = "/ingresar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SeguridadUsuarioLogin> ingresar(@RequestBody SeguridadUsuarioLogin bean) throws Exception {
		logger.debug("LoginRest.ingresar");

		// Validacion Captcha
		RestTemplate restTemplate = null;
		if (UString.esNuloVacio(proxyUsar))
			this.leerPropiedades();
		if (this.proxyUsar.equals("S")) {
			Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress(proxyNombre, proxyPuerto));
			SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
			requestFactory.setProxy(proxy);
			restTemplate = new RestTemplate(requestFactory);
		} else {
			restTemplate = new RestTemplate();
		}

		// configuracion
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
			} else {
				logger.debug("CAPCHA = Validado");
			}
		} else {
			logger.debug("CAPCHA = NO SE USA");
		}

		// Validacion Captcha

		String tmp = null;
		boolean flgTipoUsuarioEncontrado = false;

		if (bean == null) {
			bean = new SeguridadUsuarioLogin();
			bean.setTransaccionEstado(DominioTransaccion.VALIDACION);
			bean.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Usuario y/o clave incorrecto"));
			return new ResponseEntity<SeguridadUsuarioLogin>(bean, HttpStatus.OK);
		}

		tmp = UString.obtenerValorCadenaSinNulo(bean.getUsuario()).trim().toUpperCase();
		bean.setUsuario(tmp);

		tmp = UString.obtenerValorCadenaSinNulo(bean.getClave()).trim();
		bean.setClave(tmp);

		SeguridadUsuarioActual usuarioValidado = null;

		if (UString.esNuloVacio(bean.getTipoUsuarioId()))
			bean.setTipoUsuarioId(SeguridadUsuarioLogin.TIPOUSUARIO_EMPLEADO);

		if (bean.getTipoUsuarioId().equals(SeguridadUsuarioLogin.TIPOUSUARIO_EMPLEADO)) {

			if (bean.getUsuario() == null) {
				bean = new SeguridadUsuarioLogin();
				bean.setTransaccionEstado(DominioTransaccion.VALIDACION);
				bean.getTransaccionListaMensajes()
						.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Usuario y/o clave incorrecto"));
				return new ResponseEntity<SeguridadUsuarioLogin>(bean, HttpStatus.OK);
			}

			usuarioValidado = servicio.login(bean.getAplicacionCodigo(), bean.getUsuario(), bean.getClave(),
					bean.getCompaniaCodigo());

			if (usuarioValidado.getTransaccionEstado().equals(DominioTransaccion.ERROR)) {
				bean = new SeguridadUsuarioLogin();
				bean.setTransaccionEstado(DominioTransaccion.ERROR);
				bean.setTransaccionListaMensajes(usuarioValidado.getTransaccionListaMensajes());
				return new ResponseEntity<SeguridadUsuarioLogin>(bean, HttpStatus.OK);
			}
			if (servicio.expiroUsuario(usuarioValidado.getUsuario())) {
				bean = new SeguridadUsuarioLogin();
				bean.setTransaccionEstado(DominioTransaccion.ERROR);
				bean.getTransaccionListaMensajes()
						.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Usuario y/o clave incorrecto"));
				return new ResponseEntity<SeguridadUsuarioLogin>(bean, HttpStatus.OK);
			}

			flgTipoUsuarioEncontrado = true;

			tmp = usuarioDao.usuarioEsAdministradorDeAplicacion("WH", bean.getUsuario());
			usuarioValidado.setEsAdministradorWh(tmp);

			tmp = usuarioDao.usuarioEsAdministradorDeAplicacion("SG", bean.getUsuario());
			usuarioValidado.setEsAdministradorSg(tmp);

			tmp = usuarioDao.usuarioEsAdministradorDeAplicacion(bean.getAplicacionCodigo(), bean.getUsuario());
			usuarioValidado.setEsAdministradorAplicacion(tmp);
		}
		if (bean.getTipoUsuarioId().equals(SeguridadUsuarioLogin.TIPOUSUARIO_CLIENTE)) {
			flgTipoUsuarioEncontrado = true;
		}
		if (bean.getTipoUsuarioId().equals(SeguridadUsuarioLogin.TIPOUSUARIO_PROVEEDOR)) {

			if (bean.getUsuario() == null) {
				throw new UException("Usuario y/o clave incorrecto", tipo_mensaje.ERROR);
			}
			usuarioValidado = servicio.loginProveedores(bean.getClave(), bean.getUsuario());

			if (usuarioValidado.getTransaccionEstado().equals(DominioTransaccion.ERROR)) {
				bean.setTransaccionEstado(DominioTransaccion.VALIDACION);

				for (DominioMensajeUsuario men : usuarioValidado.getTransaccionListaMensajes()) {
					bean.getTransaccionListaMensajes().add(men);
				}

				return new ResponseEntity<SeguridadUsuarioLogin>(bean, HttpStatus.OK);
			}

			flgTipoUsuarioEncontrado = true;
		}
		if (bean.getTipoUsuarioId().equals(SeguridadUsuarioLogin.TIPOUSUARIO_POSTULANTE)) {
			flgTipoUsuarioEncontrado = true;
		}

		if (!flgTipoUsuarioEncontrado) {
			bean.setTransaccionEstado(DominioTransaccion.VALIDACION);
			bean.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "tipo de usuario no encontrado"));
			return new ResponseEntity<SeguridadUsuarioLogin>(bean, HttpStatus.OK);
		}

		ObjectMapper mapper = new ObjectMapper();
		SeguridadUsuarioActual usuarioActual = usuarioValidado;

		usuarioActual.setTipoUsuarioId(bean.getTipoUsuarioId());
		usuarioActual.setAplicacionCodigo(bean.getAplicacionCodigo());
		usuarioActual.setCompaniaCodigo(bean.getCompaniaCodigo());
		usuarioActual.setPersonaFotoUrl(
				servicio.fotoObtenerRuta(usuarioValidado.getPersonaId(), usuarioValidado.getPersonaNroDocumento()));

		Integer minutos = Integer.parseInt((String) recursoPropiedad.get("token.minutos")) * 100000;

		Calendar date = Calendar.getInstance();
		long t = date.getTimeInMillis();
		Date desde = new Date();
		Date hasta = new Date(t + (minutos));
		Date hastaLargo = new Date(t + (minutos));

		String jwtToken = Jwts.builder().setSubject(mapper.writeValueAsString(usuarioActual.getUsuario()))
				.claim("roles", "user").setIssuedAt(desde)
				.signWith(SignatureAlgorithm.HS256, (String) recursoPropiedad.get("token.key"))
				.setExpiration(hastaLargo).compact();

		bean.setTransaccionEstado(DominioTransaccion.OK);
		bean.setToken(jwtToken);

		Map<String, Object> mapUsuario = new HashMap<String, Object>();
		mapUsuario.put("hasta", hasta);
		mapUsuario.put("usuario", usuarioActual);

		sessiones.put(jwtToken, mapUsuario);

		return new ResponseEntity<SeguridadUsuarioLogin>(bean, HttpStatus.OK);
	}

	/**
	 * Post ingresar
	 * 
	 * @return
	 */
	@Transactional
	@PostMapping(value = "/ingresarExterno", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SeguridadUsuarioLogin> ingresarExterno(@RequestBody SeguridadUsuarioLogin bean)
			throws Exception {
		logger.debug("LoginRest.ingresar");
		boolean flgTipoUsuarioEncontrado = false;

		if (bean == null) {
			bean = new SeguridadUsuarioLogin();
			bean.setTransaccionEstado(DominioTransaccion.VALIDACION);
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "usuario no enviado"));
			return new ResponseEntity<SeguridadUsuarioLogin>(bean, HttpStatus.OK);
		}

		SeguridadUsuarioActual usuarioValidado = null;

		bean.setUsuario(UString.obtenerSinNulo(bean.getUsuario()));
		bean.setClave(UString.obtenerSinNulo(bean.getClave()));

		String usuario = servicio.parametroObtenerExplicacion("SY", "USRWFWEB", "999999");
		String clave = servicio.parametroObtenerExplicacion("SY", "PWDWFWEB", "999999");

		if (UString.estaVacio(usuario)) {
			usuario = "royal";
		}

		if (UString.estaVacio(clave)) {
			clave = "123";
		}

		if (bean.getUsuario().equals(usuario) && bean.getClave().equals(clave)) {

		} else {
			bean = new SeguridadUsuarioLogin();
			bean.setTransaccionEstado(DominioTransaccion.ERROR);
			bean.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA, "Los datos no son correctos"));
			return new ResponseEntity<SeguridadUsuarioLogin>(bean, HttpStatus.OK);
		}

		ObjectMapper mapper = new ObjectMapper();

		SeguridadUsuarioActual usuarioActual = new SeguridadUsuarioActual();
		usuarioActual.setTipoUsuarioId(bean.getTipoUsuarioId());
		usuarioActual.setUsuario("EXTERNO");
		usuarioActual.setPersonaId(0);

		Integer minutos = 900 * 100000;

		Calendar date = Calendar.getInstance();
		long t = date.getTimeInMillis();
		Date desde = new Date();
		Date hasta = new Date(t + (minutos));
		Date hastaLargo = new Date(t + (minutos));

		logger.debug(hasta);

		String jwtToken = Jwts.builder().setSubject(mapper.writeValueAsString(usuarioActual)).claim("roles", "user")
				.setIssuedAt(desde).signWith(SignatureAlgorithm.HS256, ConstanteBoot.TOKEN_JWTKEY).setExpiration(hastaLargo).compact();

		bean.setTransaccionEstado(DominioTransaccion.OK);
		bean.setToken(jwtToken);

		Map<String, Object> mapUsuario = new HashMap<String, Object>();
		mapUsuario.put("hasta", hasta);
		mapUsuario.put("usuario", usuarioActual);
		sessiones.put(jwtToken, mapUsuario);
		logger.debug(jwtToken);

		bean.setUsuario(null);
		bean.setClave(null);

		return new ResponseEntity<SeguridadUsuarioLogin>(bean, HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/recuperarclave", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> recuperarclave(@RequestBody DtoTabla usuarioLogear)
			throws Exception {
		logger.debug("UsuarioRest.recuperarclave");

		List<DominioMensajeUsuario> listaErrores = servicio.recuperarclave(usuarioLogear);

		logger.debug("UsuarioRest.recuperarclave:fin");
		return new ResponseEntity<List<DominioMensajeUsuario>>(listaErrores, HttpStatus.OK);
	}

	@GetMapping(value = "/estavivoproxy", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> estavivoproxy() {
		logger.debug("LoginRest.estavivoproxy");
		String estavivo = "SI ";
		try {
			RestTemplate restTemplate = null;
			if (UString.esNuloVacio(proxyUsar))
				this.leerPropiedades();
			if (this.proxyUsar.equals("S")) {
				Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress(proxyNombre, proxyPuerto));
				SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
				requestFactory.setProxy(proxy);
				restTemplate = new RestTemplate(requestFactory);
			} else {
				restTemplate = new RestTemplate();
			}

			ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://catfact.ninja/fact",
					String.class);
			estavivo = estavivo + responseEntity.getBody();
		} catch (Exception e) {
			logger.debug("usuario actual no encontrado");
			e.printStackTrace();
		}
		return new ResponseEntity<String>(estavivo, HttpStatus.OK);
	}

}
