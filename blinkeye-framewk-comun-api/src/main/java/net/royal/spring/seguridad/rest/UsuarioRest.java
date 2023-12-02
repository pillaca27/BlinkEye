package net.royal.spring.seguridad.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.core.dominio.BeanPersonamast;
import net.royal.spring.core.dominio.dto.DtoComunPersonaMast;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.ReporteTransaccion;
import net.royal.spring.framework.modelo.correo.EmailDestino;
import net.royal.spring.framework.modelo.correo.EmailTransaccion;
import net.royal.spring.framework.modelo.generico.DominioAdjunto;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.PasswordGenerator;
import net.royal.spring.framework.util.SeguridadHelper;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;
 
import net.royal.spring.seguridad.dao.impl.UsuarioComunDaoImpl;
import net.royal.spring.seguridad.dominio.BeanUsuario;
import net.royal.spring.seguridad.dominio.BeanUsuarioPk;
import net.royal.spring.seguridad.dominio.dto.DtoComunSegAutorizacion;
import net.royal.spring.seguridad.dominio.dto.DtoTablaBigDecimal;
import net.royal.spring.seguridad.dominio.dto.DtoComunUsuario02;
import net.royal.spring.seguridad.dominio.dto.DtoComunUsuarioBean;
import net.royal.spring.seguridad.dominio.filtro.FiltroComunPaginacionUsuario;
import net.royal.spring.seguridad.servicio.impl.UsuarioServicioImpl;

@RestController
@RequestMapping("/spring/seguridad/usuario")
@CrossOrigin(origins = "*")
public class UsuarioRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(UsuarioRest.class);

	@Autowired
	private UsuarioComunDaoImpl usuarioDao;

	@Autowired
	private UsuarioServicioImpl servicio;
	
	public static Map<String, Object> sessiones = new HashMap<String, Object>();

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse httpResponse;

	// LEONARDO SEGURIDAD MANTENIMIENTO - DE AGRO

	@Transactional
	@PostMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listar(@RequestBody FiltroComunPaginacionUsuario filtro) throws Exception {
		return servicio.listar(filtro);
	}

	@Transactional
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanUsuario> registrar(@RequestBody BeanUsuario bean) throws Exception {
		logger.debug("UsuarioRest.registrar");
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		bean.getPk().setUsuario(bean.getPk().getUsuario().toUpperCase());

		BeanUsuario usu = usuarioDao.obtenerPorId(bean.getPk().getUsuario());

		if (usu != null) {

			List<DominioMensajeUsuario> lsterr = new ArrayList<DominioMensajeUsuario>();
			lsterr.add(this.getMsjUsuarioError("usuariorest.usuario.existe"));
			usu.setTransaccionEstado(DominioTransaccion.ERROR);
			usu.setTransaccionListaMensajes(lsterr);
			return new ResponseEntity<BeanUsuario>(usu, HttpStatus.CREATED);
		} else {
			bean.setUltimafechamodif(new Date());
			bean.setUltimousuario(this.getUsuarioActual().getUsuario());
			bean.setNombre(bean.getNombre().toUpperCase());

			// bean.setFechapassword(date);

			String clave = bean.getClave();
			String nuevaClaveCrifrada = SeguridadHelper.springEncriptar(clave);

			bean.setClave(nuevaClaveCrifrada);

			bean = servicio.coreInsertar(this.getUsuarioActual(), bean);

		}
		return new ResponseEntity<BeanUsuario>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@GetMapping(value = "/obtenerParametro", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanUsuario> obtenerParametro(@Validated @PathVariable String usuario) throws Exception {
		logger.debug("UsuarioRest.obtenerParametro");
		BeanUsuario bean = usuarioDao.obtenerPorId(usuario);
		if (bean == null)
			return new ResponseEntity<BeanUsuario>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<BeanUsuario>(bean, HttpStatus.OK);
	}

	@SuppressWarnings("unused")
	@Transactional
	@GetMapping(value = "/obtenerporid/{usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunUsuarioBean> obtenerPorId(@Validated @PathVariable String usuario) throws Exception {
		logger.debug("UsuarioRest.obtenerPorId");
		BeanUsuarioPk pk = new BeanUsuarioPk();
		pk.setUsuario(usuario);
		BeanUsuario bean = servicio.obtenerPorId(pk);
		
		//Personamast bean = servicio.obtenerPorId(pk);
		DtoComunUsuarioBean userresponse = new DtoComunUsuarioBean();
		if ("US".equals(bean.getUsuarioperfil())) {
			String nuevaClaveCrifrada = SeguridadHelper.springDesencriptar(bean.getClave().trim());
			userresponse.setClave(nuevaClaveCrifrada);
		}
		
		userresponse.setUsuario(bean);
		if(bean.getUsuarioperfil() != null) {
			if(bean.getUsuarioperfil().equals("PR")) {
				List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
				parametros.add(new DominioParametroPersistencia("p_documentofiscal", String.class, bean.getPk().getUsuario()));
				List lst = usuarioDao.listarPorQuery(DtoComunPersonaMast.class, "usuario.obtenerpersona", parametros);
				if (lst.size() == 1) {
					DtoComunPersonaMast test = (DtoComunPersonaMast)lst.get(0);
					userresponse.setPersonagroid(test.getPersona());
				}
			}
		}
	
		if (userresponse == null)
			return new ResponseEntity<DtoComunUsuarioBean>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<DtoComunUsuarioBean>(userresponse, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanUsuario> actualizar(@RequestBody BeanUsuario bean) throws Exception {
		logger.debug("UsuarioRest.actualizar");

		if ("US".equals(bean.getUsuarioperfil())) {
			String clave = bean.getClave();
			String nuevaClaveCrifrada = SeguridadHelper.springEncriptar(clave);
			bean.setClave(nuevaClaveCrifrada);
		}
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(this.getUsuarioActual().getUsuario());
		bean = servicio.coreActualizar(this.getUsuarioActual(), bean);
		return new ResponseEntity<BeanUsuario>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/actualizarestado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanUsuario> actualizarestado(@RequestBody BeanUsuario bean) throws Exception {
		logger.debug("UsuarioRest.actualizar");

		String usuario = bean.getPk().getUsuario();

		logger.debug("UsuarioRest.anular");
		bean = usuarioDao.obtenerPorId(usuario);
		if (bean == null)
			return new ResponseEntity<BeanUsuario>(HttpStatus.NOT_FOUND);
		try {
			bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		} catch (Exception e) {
			System.out.println("err_ : " + e);
		}

		return new ResponseEntity<BeanUsuario>(bean, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@PutMapping(value = "/generarnuevaclave", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunUsuarioBean> generarNuevaClave(@RequestBody BeanUsuario beanNew) throws Exception {
		logger.debug("UsuarioRest.generarnuevaclave");

		DtoComunUsuarioBean userresponse = new DtoComunUsuarioBean();

		String usuario = beanNew.getPk().getUsuario();
		beanNew = usuarioDao.obtenerPorId(usuario);

		userresponse.setUsuario(beanNew);

//         servicio.generarNuevaClave(usuario);
//         

		// se obtiene la informacion del usuario para verificar que exista bean.usuario

// 		if (beanNew==null) {
// 			throw new UException((List<MensajeUsuario>) this.getMsjUsuarioError("usuario.nuevaclave.usuariovacio"));
// 		}
// 		if (!beanNew.getUsuarioperfil().equals("US") ) {
// 			throw new UException((List<MensajeUsuario>) this.getMsjUsuarioError("usuario.nuevaclave.usuarioperfil"));
// 		}

		// se verifica que tenga una persona Agro Id relacionada

		// se verifica que tenga un correo relacionado desde ge_personaagro
		DtoTablaBigDecimal dto = usuarioDao.obtenerCorreo(beanNew.getPk().getUsuario());
		logger.debug("correoElectronico getCodigo:" + dto.getCodigo());
		logger.debug("correoElectronico getNombre:" + dto.getNombre());
		String correoElectronico = dto.getNombre();

		if (UString.esNuloVacio(correoElectronico)) {
			// throw new UException((List<MensajeUsuario>)
			// this.getMsjUsuarioError("usuario.nuevaclave.correoenblanco"));

//			userresponse.setStatus(0);
//			userresponse.setMensajeuser(String.format(this.getMsjUsuarioError("usuariorest.aplicacion.notienecorreo").getMensaje()));
			List<DominioMensajeUsuario> lsterr = new ArrayList<DominioMensajeUsuario>();
			lsterr.add(this.getMsjUsuarioError("usuariorest.aplicacion.notienecorreo"));
			userresponse.setTransaccionEstado(DominioTransaccion.ERROR);
			userresponse.setTransaccionListaMensajes(lsterr);
			return new ResponseEntity<DtoComunUsuarioBean>(userresponse, HttpStatus.OK);
		}

		// generar una nueva clave
		String nuevaClave = PasswordGenerator.getPassword();
		logger.debug("UsuarioRest.new key:" + nuevaClave);
		String nuevaClaveCrifrada = SeguridadHelper.springEncriptar(nuevaClave);
		logger.debug("UsuarioRest.new key encriptada:" + nuevaClaveCrifrada);

		// generar el formato html nuevo
		ReporteTransaccion parametro = new ReporteTransaccion();
		parametro.setAplicacionCodigo("SG");
		parametro.setReporteCodigo("001");
		parametro.getParametros().put("p_usuario", beanNew.getPk().getUsuario());
		parametro.getParametros().put("p_nombre", beanNew.getNombre());
		parametro.getParametros().put("p_clave", nuevaClave);
		ReporteTransaccion repRes = servicio.reporteEjecutar(parametro);
		logger.debug("reporte estado:" + repRes.getTransaccionEstado());
		logger.debug("reporte mensaje:" + repRes.getTransaccionMensajesCadena());
		// if (repRes.getEstado().equals(ReporteResultado.ERROR)) {
		if (repRes.getTransaccionEstado().equals(ReporteTransaccion.ERROR)) {
			// throw new UException((List<MensajeUsuario>)
			// this.getMsjUsuarioError("usuario.nuevaclave.errorreporte"));
//			userresponse.setStatus(1);
//			userresponse.setMensajeuser(String.format(this.getMsjUsuarioError("usuariorest.aplicacion.nogeneraclave").getMensaje()));
			List<DominioMensajeUsuario> lsterr = new ArrayList<DominioMensajeUsuario>();
			lsterr.add(this.getMsjUsuarioError("usuariorest.aplicacion.nogeneraclave"));
			userresponse.setTransaccionEstado(DominioTransaccion.ERROR);
			userresponse.setTransaccionListaMensajes(lsterr);
			return new ResponseEntity<DtoComunUsuarioBean>(userresponse, HttpStatus.OK);
		}

		// enviar por correo
		EmailTransaccion correo = new EmailTransaccion();
		correo.setAsunto(repRes.getResultadoAsunto());
		correo.setCuerpoCorreoBytes(repRes.getResultadoCuerpoBinario());
		correo.getListaCorreoDestino().add(new EmailDestino(correoElectronico));

		DominioAdjunto emailAdjunto = new DominioAdjunto();

		// emailAdjunto.setArchivoAdjunto(archivoAdjunto);
		//emailAdjunto.setNombreArchivo("");
		//correo.getListaCorreoAdjunto().add(emailAdjunto);

		EmailTransaccion resEmail = servicio.correoEnviar(correo);
		logger.debug("email estado:" + resEmail.getTransaccionEstado());
		logger.debug("email mensaje:" + resEmail.getTransaccionMensajesCadena());
		if (resEmail.getTransaccionEstado().equals(EmailTransaccion.ERROR)) {
			// throw new UException((List<MensajeUsuario>)
			// this.getMsjUsuarioError("usuario.nuevaclave.errorcorreo"));

//			userresponse.setStatus(2);
//			userresponse.setMensajeuser(String.format(this.getMsjUsuarioError("usuariorest.aplicacion.noenviaclave.porcorreo").getMensaje()));
			List<DominioMensajeUsuario> lsterr = new ArrayList<DominioMensajeUsuario>();
			lsterr.add(this.getMsjUsuarioError("usuariorest.aplicacion.noenviaclave.porcorreo"));
			userresponse.setTransaccionEstado(DominioTransaccion.ERROR);
			userresponse.setTransaccionListaMensajes(lsterr);
			return new ResponseEntity<DtoComunUsuarioBean>(userresponse, HttpStatus.OK);
		}

		// actualizar el usuario
		beanNew.setClave(nuevaClaveCrifrada);
		beanNew.setUltimologin(null);
		beanNew.setFechapassword(new Date());
		beanNew.setUltimafechamodif(new Date());
		beanNew.setNumerologinsusados(null);
		beanNew.setSituacion("V");
		usuarioDao.coreActualizar(beanNew);

//		userresponse.setStatus(200);
//		userresponse.setMensajeuser(String.format(this.getMsjUsuarioError("usuariorest.aplicacion.clave.enviadacorreo").getMensaje()));
		List<DominioMensajeUsuario> lsterr = new ArrayList<DominioMensajeUsuario>();
		lsterr.add(this.getMsjUsuarioError("usuariorest.aplicacion.clave.enviadacorreo"));
		userresponse.setTransaccionEstado(DominioTransaccion.ERROR);
		userresponse.setTransaccionListaMensajes(lsterr);

		return new ResponseEntity<DtoComunUsuarioBean>(userresponse, HttpStatus.OK);
	}

	@SuppressWarnings("unused")
	@Transactional
	@PutMapping(value = "/actualizarestadodesbloquear", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanUsuario> actualizarestadodesbloquear(@RequestBody BeanUsuario bean) throws Exception {
		logger.debug("UsuarioRest.actualizar");
		Date date = new Date();
		String usuario = bean.getPk().getUsuario();

		logger.debug("UsuarioRest.desbloquar");
		bean = usuarioDao.obtenerPorId(usuario);

		bean.setNumerologinsusados(null);
		bean.setSituacion("V");
		bean.setUltimologin(date);
		bean.setFechapassword(date);

		if (bean == null)
			return new ResponseEntity<BeanUsuario>(HttpStatus.NOT_FOUND);
		bean = servicio.coreActualizar(this.getUsuarioActual(), bean);
		return new ResponseEntity<BeanUsuario>(bean, HttpStatus.OK);
	}
	// FIN LEONARDO
	
	
	@Transactional
	@GetMapping(value = "/aplicaciones", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSegAutorizacion>> aplicaciones() throws Exception {
		logger.debug("aplicaciones");
		SeguridadUsuarioActual usu = this.getUsuarioActual();
		List<DtoComunSegAutorizacion> lst = usuarioDao.listarDtoAutorizacionesAplicacionesUsuarioPerfilAdministrador(usu.getUsuario());		
		logger.debug(lst.size());
		return new ResponseEntity<List<DtoComunSegAutorizacion>>(lst, HttpStatus.OK);
	}
	
}
