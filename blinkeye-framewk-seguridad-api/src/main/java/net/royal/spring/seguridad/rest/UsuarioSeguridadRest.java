package net.royal.spring.seguridad.rest;

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
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadMenu;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UObject;
import net.royal.spring.framework.web.constante.ConstanteFiltro;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.seguridad.dao.impl.UsuarioSeguridadDaoImpl;
import net.royal.spring.seguridad.dominio.dto.DtoSegAutorizacion;
import net.royal.spring.seguridad.dominio.dto.DtoUsuario;
import net.royal.spring.seguridad.servicio.impl.Menu01ServicioImpl;
import net.royal.spring.seguridad.servicio.impl.Menu02ServicioImpl;
import net.royal.spring.seguridad.servicio.impl.UsuarioSeguridadServicioImpl;

@RestController
@RequestMapping("/spring/seguridad/usuario")
@CrossOrigin(origins = "*")
public class UsuarioSeguridadRest extends GenericoRest {
	
	private static Logger logger = LogManager.getLogger(UsuarioSeguridadRest.class);
	
	@Autowired
	private UsuarioSeguridadDaoImpl usuarioDao;
	
	@Autowired
	private UsuarioSeguridadServicioImpl servicio;
	
	public static Map<String, Object> sessiones = new HashMap<String, Object>();
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse httpResponse;
	
	@Autowired
	private Menu01ServicioImpl menu01;
	
	@Autowired
	private Menu02ServicioImpl menuServicio02;
	
	@Transactional
	@GetMapping(value = "/menucore", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SeguridadMenu> menucore() throws Exception {
		logger.debug("menucore");
		SeguridadUsuarioActual usu = this.getUsuarioActual();
		SeguridadMenu menu = null;		
		menu = menu01.menuPorAplicacionUsuario(usu.getAplicacionCodigo(),usu.getUsuario());		
		if (UObject.estaVacio(menu))
			return new ResponseEntity<SeguridadMenu>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<SeguridadMenu>(menu, HttpStatus.OK);
	}
	
	/**
	 * Get obtenerusuarioactual
	 * @return
	 */
	@GetMapping(value = "/obtenerusuarioactual", produces = MediaType.APPLICATION_JSON_VALUE)
	public SeguridadUsuarioActual obtenerUsuarioActual() {
		return getUsuarioActual();
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
	@PutMapping(value = "/cambiarClaveUsuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoUsuario> cambiarClaveUsuario(@RequestBody DtoUsuario usuario) throws Exception {

		DtoTabla dto = new DtoTabla();
		
		usuario.setUsuario(usuario.getUsuario());
		usuario.setClaveOld(usuario.getClaveOld());
		usuario.setClaveNueva(usuario.getClaveNueva());
		usuario.setClaveRep(usuario.getClaveRep());

		servicio.cambiarClaveUsuario(usuario);
		
		return new ResponseEntity<DtoUsuario>(usuario, HttpStatus.OK);
	}
	
}
