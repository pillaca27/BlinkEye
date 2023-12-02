package net.royal.spring.dt.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.dt.dao.impl.AlumnosDaoImpl;
import net.royal.spring.dt.dominio.Alumnos;
import net.royal.spring.dt.dominio.AlumnosPk;
import net.royal.spring.dt.dominio.dto.DtoAlumnos;
import net.royal.spring.dt.dominio.filtro.FiltroAlumnos;
import net.royal.spring.dt.servicio.impl.AlumnosServicioImpl;
import net.royal.spring.dt.servicio.impl.AlumnosServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/publico/core/alumnos")
@CrossOrigin(origins = "*")
public class AlumnosRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(AlumnosRest.class);

	@Autowired
	private AlumnosServicioImpl servicio;

	@Autowired
	private AlumnosServicioValidar validar;

	@Autowired
	private AlumnosDaoImpl alumnosDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AlumnosRest() {
		super("alumnos");
	}

	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoAlumnos> validar(@Validated @PathVariable String accion, @RequestBody DtoAlumnos dto) throws Exception {
		logger.debug("AlumnosRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoAlumnos>(dto, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoAlumnos> obtenerDto(@RequestBody DtoAlumnos pk) throws Exception {
		logger.debug("AlumnosRest.obtenerDto");
		DtoAlumnos dto = alumnosDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoAlumnos>(dto,HttpStatus.OK);
	}*/

	/*@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoAlumnos> registrar(@RequestBody DtoAlumnos dto) throws Exception {
		logger.debug("AlumnosRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoAlumnos>(dto, HttpStatus.CREATED);
	}*/

	/*@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoAlumnos> actualizar(@RequestBody DtoAlumnos dto) throws Exception {
		logger.debug("AlumnosRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoAlumnos>(dto, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoAlumnos> eliminar(@RequestBody DtoAlumnos dto) throws Exception {
		logger.debug("AlumnosRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoAlumnos>(dto,HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("AlumnosRest.listar");
		// TODO AlumnosRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "alumnos.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("AlumnosRest.listaractivos");
		// TODO AlumnosRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "alumnos.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoAlumnos>> listarDtoFiltros(@RequestBody DtoAlumnos filtro) throws Exception {
		logger.debug("AlumnosRest.listardtofiltros");
        List datos = alumnosDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoAlumnos>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoAlumnos>> listarDtoActivos(@RequestBody DtoAlumnos filtro) throws Exception {
		logger.debug("AlumnosRest.listardtoactivos");
		List datos = alumnosDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoAlumnos>>(datos, HttpStatus.OK);
	}*/

	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroAlumnos filtro) throws Exception {
		logger.debug("AlumnosRest.listarPaginado");
		DominioPaginacion paginacion = alumnosDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	/*@Transactional
	@PutMapping(value = "/listacortaactivospornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaActivosPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("AlumnosRest.listaCortaActivosPorNombre");
		filtro.setEstadoId("A");
		List datos = alumnosDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listacortapornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("AlumnosRest.listaCortaPorNombre");
		List datos = alumnosDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

}
