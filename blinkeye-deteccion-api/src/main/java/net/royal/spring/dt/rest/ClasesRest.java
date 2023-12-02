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

import net.royal.spring.dt.dao.impl.ClasesDaoImpl;
import net.royal.spring.dt.dominio.Clases;
import net.royal.spring.dt.dominio.ClasesPk;
import net.royal.spring.dt.dominio.dto.DtoClases;
import net.royal.spring.dt.dominio.filtro.FiltroClases;
import net.royal.spring.dt.servicio.impl.ClasesServicioImpl;
import net.royal.spring.dt.servicio.impl.ClasesServicioValidar;
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
@RequestMapping("/publico/erp/core/clases")
@CrossOrigin(origins = "*")
public class ClasesRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(ClasesRest.class);

	@Autowired
	private ClasesServicioImpl servicio;

	@Autowired
	private ClasesServicioValidar validar;

	@Autowired
	private ClasesDaoImpl clasesDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public ClasesRest() {
		super("clases");
	}

	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoClases> validar(@Validated @PathVariable String accion, @RequestBody DtoClases dto) throws Exception {
		logger.debug("ClasesRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoClases>(dto, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoClases> obtenerDto(@RequestBody DtoClases pk) throws Exception {
		logger.debug("ClasesRest.obtenerDto");
		DtoClases dto = clasesDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoClases>(dto,HttpStatus.OK);
	}*/

	/*@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoClases> registrar(@RequestBody DtoClases dto) throws Exception {
		logger.debug("ClasesRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoClases>(dto, HttpStatus.CREATED);
	}*/

	/*@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoClases> actualizar(@RequestBody DtoClases dto) throws Exception {
		logger.debug("ClasesRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoClases>(dto, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoClases> eliminar(@RequestBody DtoClases dto) throws Exception {
		logger.debug("ClasesRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoClases>(dto,HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("ClasesRest.listar");
		// TODO ClasesRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "clases.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("ClasesRest.listaractivos");
		// TODO ClasesRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "clases.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoClases>> listarDtoFiltros(@RequestBody DtoClases filtro) throws Exception {
		logger.debug("ClasesRest.listardtofiltros");
        List datos = clasesDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoClases>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoClases>> listarDtoActivos(@RequestBody DtoClases filtro) throws Exception {
		logger.debug("ClasesRest.listardtoactivos");
		List datos = clasesDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoClases>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroClases filtro) throws Exception {
		logger.debug("ClasesRest.listarPaginado");
		DominioPaginacion paginacion = clasesDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listacortaactivospornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaActivosPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("ClasesRest.listaCortaActivosPorNombre");
		filtro.setEstadoId("A");
		List datos = clasesDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listacortapornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("ClasesRest.listaCortaPorNombre");
		List datos = clasesDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

}
