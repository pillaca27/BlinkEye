package net.royal.spring.seguridad.rest;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.autorizacion.seguridad.rest.LoginRest;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadMenu;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UObject;
import net.royal.spring.framework.web.constante.ConstanteFiltro;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.seguridad.dao.impl.UsuarioDaoImpl;
import net.royal.spring.seguridad.dominio.dto.DtoSegAutorizacion;
import net.royal.spring.seguridad.dominio.dto.DtoUsuario;
import net.royal.spring.seguridad.servicio.impl.UsuarioServicioImpl;

@RestController
@RequestMapping("/spring/seguridad/usuario")
@CrossOrigin(origins = "*")
public class UsuarioRest extends GenericoRest {
	
	private static Logger logger = LogManager.getLogger(UsuarioRest.class);
	
	@Autowired
	private UsuarioDaoImpl usuarioDao;
	
	@Autowired
	private UsuarioServicioImpl servicio;
	
	public static Map<String, Object> sessiones = new HashMap<String, Object>();
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse httpResponse;
		
	
	/**
	 * Get obtenermenu
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@GetMapping(value = "/obtenermenuporaplicacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SeguridadMenu> obtenerMenuPorAplicacion() throws Exception {
		logger.debug("obtenerMenuPorAplicacion");
		SeguridadUsuarioActual usu = this.getUsuarioActual();
		SeguridadMenu menu = null;
		logger.debug("usu.getEsAdministradorAplicacion():"+usu.getEsAdministradorAplicacion());
		logger.debug("usu.getEsAdministradorWh():"+usu.getEsAdministradorWh());
		menu = servicio.obtenerMenuRecursivo(usu, usu.getEsAdministradorAplicacion(),usu.getEsAdministradorWh());
				
		if (UObject.estaVacio(menu))
			return new ResponseEntity<SeguridadMenu>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<SeguridadMenu>(menu, HttpStatus.OK);
	}
	
	/**
	 * Get obtenermenu
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@GetMapping(value = "/obtenermenu", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SeguridadMenu> obtenerMenu() throws Exception {
		logger.debug("obtenerMenu");
		SeguridadUsuarioActual usu = this.getUsuarioActual();
		SeguridadMenu menu = null;
		logger.debug("obtenerMenu aplicac:"+usu.getAplicacionCodigo());
		logger.debug("obtenerMenu usuario:"+usu.getUsuario());
		logger.debug("usu.getEsAdministradorWh():"+usu.getEsAdministradorWh());
		logger.debug("usu.getEsAdministradorSg():"+usu.getEsAdministradorSg());
		logger.debug("usu.getEsAdministradorAplicacion():"+usu.getEsAdministradorAplicacion());
		menu = servicio.obtenerMenuRecursivo(usu,usu.getEsAdministradorWh(),"N");
				
		if (UObject.estaVacio(menu))
			return new ResponseEntity<SeguridadMenu>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<SeguridadMenu>(menu, HttpStatus.OK);
	}
	
	/**
	 * Get estavivo
	 * @return
	 */
	@GetMapping(value="/estavivo",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> estavivo() {
		logger.debug("UsuarioRest (Privado).estavivo");
		Date now = new Date();		
		try {
			SeguridadUsuarioActual usu = this.getUsuarioActual();
			logger.debug(usu);	
		} catch (Exception e) {
			logger.debug("usuario actual no encontrado");
			e.printStackTrace();
		}		
		
		return new ResponseEntity<String>("SI - " + now.toString(), HttpStatus.OK);
	}
	
	/**
	 * Get obtenerusuarioactual
	 * @return
	 */
	@GetMapping(value = "/obtenerusuarioactual", produces = MediaType.APPLICATION_JSON_VALUE)
	public SeguridadUsuarioActual obtenerUsuarioActual() {
		return getUsuarioActual();
	}
	
	
	@Transactional
	@PutMapping(value = "/validarClave", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validarClave(@RequestBody DtoUsuario usuario) throws Exception {
		
		List<DominioMensajeUsuario> lst = servicio.validarClave(usuario);	
		
		return new ResponseEntity<List<DominioMensajeUsuario>>(lst, HttpStatus.OK);	
	}
	
	
	@Transactional
	@PutMapping(value = "/cambiarClaveUsuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoUsuario> cambiarClaveUsuario(@RequestBody DtoUsuario usuario) throws Exception {
		List<DominioMensajeUsuario> msj = new ArrayList<DominioMensajeUsuario>();
		SeguridadUsuarioActual usuarioActual = getUsuarioActual();
		
		if(!usuario.getUsuario().equals(usuarioActual.getUsuario())) {
			throw new UException(this.getMessage("mensaje.uexception.usuario.noesel.actual"), tipo_mensaje.ERROR);			
		}
		
		// por seguridad actualizamos con el usuario actual
		usuario.setUsuario(getUsuarioActual().getUsuario());

		// desencriptar con AES y token
		//String key = getUsuarioActual().getTokenCliente().substring(0, 16);

		usuario.setClaveOld(usuario.getClaveOld());
		usuario.setClaveNueva(usuario.getClaveNueva());
		usuario.setClaveRep(usuario.getClaveRep());

		try {
			servicio.cambiarClaveUsuario(usuario);	
		} catch (Exception e) {
			logger.debug(e);
			msj.add(this.getMsjUsuarioError(e.getMessage()));
		}
		if(!msj.isEmpty()) {
			usuario.setTransaccionEstado(DominioTransaccion.ERROR);
			usuario.setTransaccionListaMensajes(msj);
		}
		return new ResponseEntity<DtoUsuario>(usuario, HttpStatus.OK);
	}
	
	@GetMapping(value = "/logOut", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoTabla logOut() throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String token = httpRequest.getHeader(ConstanteFiltro.TOKEN);
		token = token.replace("Bearer ", "");
		LoginRest.sessiones.remove(token);
		return new DtoTabla();
	}
	
	@Transactional
	@GetMapping(value = "/menucore", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SeguridadMenu> menucore() throws Exception {
		logger.debug("menucore");
		SeguridadUsuarioActual usu = this.getUsuarioActual();
		SeguridadMenu menu = null;
		
		//menu = servicio.obtenerMenuRecursivo(usu,usu.getEsAdministradorWh(),"N");
		menu = servicio.menuPorAplicacionUsuario(usu.getAplicacionCodigo(),usu.getUsuario());
		
		if (UObject.estaVacio(menu))
			return new ResponseEntity<SeguridadMenu>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<SeguridadMenu>(menu, HttpStatus.OK);
	}
		
	@Transactional
	@GetMapping(value = "/aplicaciones", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoSegAutorizacion>> aplicaciones() throws Exception {
		logger.debug("aplicaciones");
		SeguridadUsuarioActual usu = this.getUsuarioActual();
		List<DtoSegAutorizacion> lst = usuarioDao.listarDtoAutorizacionesAplicacionesUsuarioPerfilAdministrador(usu.getUsuario());		
		logger.debug(lst.size());
		return new ResponseEntity<List<DtoSegAutorizacion>>(lst, HttpStatus.OK);
	}
	
}
