package net.royal.spring.workflow.rest;

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

import net.royal.spring.workflow.dao.impl.WfMacroProcesoDetalleDaoImpl;
import net.royal.spring.workflow.dominio.WfMacroProcesoDetalle;
import net.royal.spring.workflow.dominio.WfMacroProcesoDetallePk;
import net.royal.spring.workflow.dominio.dto.DtoWfMacroProcesoDetalle;
import net.royal.spring.workflow.dominio.filtro.FiltroWfMacroProcesoDetalle;
import net.royal.spring.workflow.servicio.impl.WfMacroProcesoDetalleServicioImpl;
import net.royal.spring.workflow.servicio.validar.WfMacroProcesoDetalleServicioValidar;
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
@RequestMapping("/spring/workflow/wfmacroprocesodetalle")
@CrossOrigin(origins = "*")
public class WfMacroProcesoDetalleRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(WfMacroProcesoDetalleRest.class);

	@Autowired
	private WfMacroProcesoDetalleServicioImpl servicio;

	@Autowired
	private WfMacroProcesoDetalleServicioValidar validar;

	@Autowired
	private WfMacroProcesoDetalleDaoImpl wfMacroProcesoDetalleDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WfMacroProcesoDetalleRest() {
		super("wfmacroprocesodetalle");
	}

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoWfMacroProcesoDetalle> validar(@Validated @PathVariable String accion, @RequestBody DtoWfMacroProcesoDetalle dto) throws Exception {
		logger.debug("WfMacroProcesoDetalleRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoWfMacroProcesoDetalle>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoWfMacroProcesoDetalle> obtenerDto(@RequestBody DtoWfMacroProcesoDetalle pk) throws Exception {
		logger.debug("WfMacroProcesoDetalleRest.obtenerDto");
		DtoWfMacroProcesoDetalle dto = wfMacroProcesoDetalleDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoWfMacroProcesoDetalle>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoWfMacroProcesoDetalle> registrar(@RequestBody DtoWfMacroProcesoDetalle dto) throws Exception {
		logger.debug("WfMacroProcesoDetalleRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoWfMacroProcesoDetalle>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoWfMacroProcesoDetalle> actualizar(@RequestBody DtoWfMacroProcesoDetalle dto) throws Exception {
		logger.debug("WfMacroProcesoDetalleRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoWfMacroProcesoDetalle>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoWfMacroProcesoDetalle> eliminar(@RequestBody DtoWfMacroProcesoDetalle dto) throws Exception {
		logger.debug("WfMacroProcesoDetalleRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoWfMacroProcesoDetalle>(dto,HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("WfMacroProcesoDetalleRest.listar");
		// TODO WfMacroProcesoDetalleRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "wfmacroprocesodetalle.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("WfMacroProcesoDetalleRest.listaractivos");
		// TODO WfMacroProcesoDetalleRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "wfmacroprocesodetalle.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoWfMacroProcesoDetalle>> listarDtoFiltros(@RequestBody DtoWfMacroProcesoDetalle filtro) throws Exception {
		logger.debug("WfMacroProcesoDetalleRest.listardtofiltros");
        List datos = wfMacroProcesoDetalleDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoWfMacroProcesoDetalle>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoWfMacroProcesoDetalle>> listarDtoActivos(@RequestBody DtoWfMacroProcesoDetalle filtro) throws Exception {
		logger.debug("WfMacroProcesoDetalleRest.listardtoactivos");
		List datos = wfMacroProcesoDetalleDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoWfMacroProcesoDetalle>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroWfMacroProcesoDetalle filtro) throws Exception {
		logger.debug("WfMacroProcesoDetalleRest.listarPaginado");
		DominioPaginacion paginacion = wfMacroProcesoDetalleDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listacortaactivospornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaActivosPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("WfMacroProcesoDetalleRest.listaCortaActivosPorNombre");
		filtro.setEstadoId("A");
		List datos = wfMacroProcesoDetalleDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listacortapornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("WfMacroProcesoDetalleRest.listaCortaPorNombre");
		List datos = wfMacroProcesoDetalleDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

}
