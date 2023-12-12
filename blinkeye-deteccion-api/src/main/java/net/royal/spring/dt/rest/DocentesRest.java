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

import net.royal.spring.dt.dao.impl.DocentesDaoImpl;
import net.royal.spring.dt.dominio.Docentes;
import net.royal.spring.dt.dominio.DocentesPk;
import net.royal.spring.dt.dominio.dto.DtoDocentes;
import net.royal.spring.dt.dominio.filtro.FiltroDocentes;
import net.royal.spring.dt.servicio.impl.DocentesServicioImpl;
import net.royal.spring.dt.servicio.impl.DocentesServicioValidar;
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
@RequestMapping("/spring/dt/docentes")
@CrossOrigin(origins = "*")
public class DocentesRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(DocentesRest.class);

	@Autowired
	private DocentesServicioImpl servicio;

	@Autowired
	private DocentesServicioValidar validar;

	@Autowired
	private DocentesDaoImpl docentesDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public DocentesRest() {
		super("docentes");
	}

	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoDocentes> validar(@Validated @PathVariable String accion, @RequestBody DtoDocentes dto) throws Exception {
		logger.debug("DocentesRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoDocentes>(dto, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoDocentes> obtenerDto(@RequestBody DtoDocentes pk) throws Exception {
		logger.debug("DocentesRest.obtenerDto");
		DtoDocentes dto = docentesDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoDocentes>(dto,HttpStatus.OK);
	}*/

	/*@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoDocentes> registrar(@RequestBody DtoDocentes dto) throws Exception {
		logger.debug("DocentesRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoDocentes>(dto, HttpStatus.CREATED);
	}*/

	/*@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoDocentes> actualizar(@RequestBody DtoDocentes dto) throws Exception {
		logger.debug("DocentesRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoDocentes>(dto, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoDocentes> eliminar(@RequestBody DtoDocentes dto) throws Exception {
		logger.debug("DocentesRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoDocentes>(dto,HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("DocentesRest.listar");
		// TODO DocentesRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "docentes.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("DocentesRest.listaractivos");
		// TODO DocentesRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "docentes.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoDocentes>> listarDtoFiltros(@RequestBody DtoDocentes filtro) throws Exception {
		logger.debug("DocentesRest.listardtofiltros");
        List datos = docentesDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoDocentes>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoDocentes>> listarDtoActivos(@RequestBody DtoDocentes filtro) throws Exception {
		logger.debug("DocentesRest.listardtoactivos");
		List datos = docentesDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoDocentes>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroDocentes filtro) throws Exception {
		logger.debug("DocentesRest.listarPaginado");
		DominioPaginacion paginacion = docentesDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listacortaactivospornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaActivosPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("DocentesRest.listaCortaActivosPorNombre");
		filtro.setEstadoId("A");
		List datos = docentesDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listacortapornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("DocentesRest.listaCortaPorNombre");
		List datos = docentesDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

}
