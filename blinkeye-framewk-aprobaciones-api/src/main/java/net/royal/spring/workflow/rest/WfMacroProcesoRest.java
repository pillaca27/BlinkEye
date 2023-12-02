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

import net.royal.spring.workflow.dao.impl.WfMacroProcesoDaoImpl;
import net.royal.spring.workflow.dominio.WfMacroProceso;
import net.royal.spring.workflow.dominio.WfMacroProcesoPk;
import net.royal.spring.workflow.dominio.dto.DtoWfMacroProceso;
import net.royal.spring.workflow.dominio.filtro.FiltroWfMacroProceso;
import net.royal.spring.workflow.servicio.impl.WfMacroProcesoServicioImpl;
import net.royal.spring.workflow.servicio.validar.WfMacroProcesoServicioValidar;
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
@RequestMapping("/spring/workflow/wfmacroproceso")
@CrossOrigin(origins = "*")
public class WfMacroProcesoRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(WfMacroProcesoRest.class);

	@Autowired
	private WfMacroProcesoServicioImpl servicio;

	@Autowired
	private WfMacroProcesoServicioValidar validar;

	@Autowired
	private WfMacroProcesoDaoImpl wfMacroProcesoDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WfMacroProcesoRest() {
		super("wfmacroproceso");
	}

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoWfMacroProceso> validar(@Validated @PathVariable String accion, @RequestBody DtoWfMacroProceso dto) throws Exception {
		logger.debug("WfMacroProcesoRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoWfMacroProceso>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoWfMacroProceso> obtenerDto(@RequestBody DtoWfMacroProceso pk) throws Exception {
		logger.debug("WfMacroProcesoRest.obtenerDto");
		DtoWfMacroProceso dto = wfMacroProcesoDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoWfMacroProceso>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoWfMacroProceso> registrar(@RequestBody DtoWfMacroProceso dto) throws Exception {
		logger.debug("WfMacroProcesoRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoWfMacroProceso>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoWfMacroProceso> actualizar(@RequestBody DtoWfMacroProceso dto) throws Exception {
		logger.debug("WfMacroProcesoRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoWfMacroProceso>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoWfMacroProceso> anular(@RequestBody DtoWfMacroProceso dto) throws Exception {
		logger.debug("WfMacroProcesoRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoWfMacroProceso>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoWfMacroProceso> eliminar(@RequestBody DtoWfMacroProceso dto) throws Exception {
		logger.debug("WfMacroProcesoRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoWfMacroProceso>(dto,HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("WfMacroProcesoRest.listar");
		// TODO WfMacroProcesoRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "wfmacroproceso.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("WfMacroProcesoRest.listaractivos");
		// TODO WfMacroProcesoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "wfmacroproceso.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoWfMacroProceso>> listarDtoFiltros(@RequestBody DtoWfMacroProceso filtro) throws Exception {
		logger.debug("WfMacroProcesoRest.listardtofiltros");
        List datos = wfMacroProcesoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoWfMacroProceso>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoWfMacroProceso>> listarDtoActivos(@RequestBody DtoWfMacroProceso filtro) throws Exception {
		logger.debug("WfMacroProcesoRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = wfMacroProcesoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoWfMacroProceso>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroWfMacroProceso filtro) throws Exception {
		logger.debug("WfMacroProcesoRest.listarPaginado");
		DominioPaginacion paginacion = wfMacroProcesoDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listacortaactivospornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaActivosPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("WfMacroProcesoRest.listaCortaActivosPorNombre");
		filtro.setEstadoId("A");
		List datos = wfMacroProcesoDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listacortapornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("WfMacroProcesoRest.listaCortaPorNombre");
		List datos = wfMacroProcesoDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

}
