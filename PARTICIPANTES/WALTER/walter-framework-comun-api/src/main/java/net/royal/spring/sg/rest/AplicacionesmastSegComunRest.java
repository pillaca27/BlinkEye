package net.royal.spring.sg.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.core.dao.impl.AplicacionesmastDaoImpl;
import net.royal.spring.core.dominio.BeanAplicacionesmast;
import net.royal.spring.core.servicio.validar.AplicacionesmastServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.sg.dominio.filtro.FiltroComunPaginacionApliacionesMast;
import net.royal.spring.sg.servicio.impl.AplicacionesmastServicioImpl;

@RestController
@RequestMapping("/spring/seguridad/aplicacionesmast")
@CrossOrigin(origins = "*")
public class AplicacionesmastSegComunRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(AplicacionesmastSegComunRest.class);

	@Autowired
	private AplicacionesmastServicioImpl servicio;

	@Autowired
	private AplicacionesmastServicioValidar validar;

	@Autowired
	private AplicacionesmastDaoImpl consulta;
	
	@Autowired
	private AplicacionesmastDaoImpl aplicacionesmastDao;

	@Transactional
	@PutMapping(value = "/validar/{accion}/{aplicacioncodigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion,
			@Validated @PathVariable String aplicacioncodigo, @RequestBody BeanAplicacionesmast bean) throws Exception {
		logger.debug("AplicacionesmastRest.validar");
		List<DominioMensajeUsuario> lst = validar.core(this.getUsuarioActual(), accion, bean, aplicacioncodigo);
		if (lst.isEmpty())
			return new ResponseEntity<List<DominioMensajeUsuario>>(HttpStatus.OK);
		return new ResponseEntity<List<DominioMensajeUsuario>>(lst, HttpStatus.BAD_REQUEST);
	}


	/* NO SE USA EN ANGULAR
	@Transactional
	@XxtMapping(value = "/obtenerporid/{aplicacioncodigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Aplicacionesmast> obtenerPorId(@Validated @PathVariable String aplicacioncodigo)
			throws Exception {
		logger.debug("AplicacionesmastRest.obtenerPorId");
		Aplicacionesmast bean = consulta.obtenerPorId(aplicacioncodigo);
		if (bean == null)
			return new ResponseEntity<Aplicacionesmast>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Aplicacionesmast>(bean, HttpStatus.OK);
	}
	*/

	@Transactional
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanAplicacionesmast> registrar(@RequestBody BeanAplicacionesmast bean) throws Exception {
		logger.debug("AplicacionesmastRest.registrar");
		bean = servicio.coreInsertar(this.getUsuarioActual(), bean);
		return new ResponseEntity<BeanAplicacionesmast>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanAplicacionesmast> actualizar(@RequestBody BeanAplicacionesmast bean) throws Exception {
		logger.debug("AplicacionesmastRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(), bean);
		return new ResponseEntity<BeanAplicacionesmast>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular/{aplicacioncodigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanAplicacionesmast> anular(@Validated @PathVariable String aplicacioncodigo) throws Exception {
		logger.debug("AplicacionesmastRest.anular");
		BeanAplicacionesmast bean = consulta.obtenerPorId(aplicacioncodigo);
		if (bean == null)
			return new ResponseEntity<BeanAplicacionesmast>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<BeanAplicacionesmast>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar/{aplicacioncodigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@Validated @PathVariable String aplicacioncodigo) throws Exception {
		logger.debug("AplicacionesmastRest.eliminar");
		BeanAplicacionesmast bean = consulta.obtenerPorId(aplicacioncodigo);
		if (bean == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			servicio.coreEliminar(this.getUsuarioActual(), bean);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@Transactional
	@PostMapping(value = "/listarcombotemp", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listar(@RequestBody FiltroComunPaginacionApliacionesMast filtro) throws Exception {
		return servicio.listar(filtro);
	}

	@Transactional
	@PostMapping(value = "/listarcombotipodet", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarcombotipodet(@RequestBody FiltroComunPaginacionApliacionesMast filtro)
			throws Exception {
		return servicio.listarTipoDet(filtro);
	}

	
}
