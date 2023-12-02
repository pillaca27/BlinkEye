package net.royal.spring.sistema.rest;

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

import net.royal.spring.sistema.dao.impl.SySeguridadconceptoDaoImpl;
import net.royal.spring.sistema.dominio.BeanSySeguridadconcepto;
import net.royal.spring.sistema.dominio.BeanSySeguridadconceptoPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSySeguridadconcepto;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSySeguridadconcepto;
import net.royal.spring.sistema.servicio.impl.SySeguridadconceptoServicioImpl;
import net.royal.spring.sistema.servicio.validar.SySeguridadconceptoServicioValidar;
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
@RequestMapping("/spring/sistema/syseguridadconcepto")
@CrossOrigin(origins = "*")
public class SySeguridadconceptoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(SySeguridadconceptoComunRest.class);

	@Autowired
	private SySeguridadconceptoServicioImpl servicio;

	@Autowired
	private SySeguridadconceptoServicioValidar validar;

	@Autowired
	private SySeguridadconceptoDaoImpl sySeguridadconceptoDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public SySeguridadconceptoComunRest() {
		super("syseguridadconcepto");
	}

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSySeguridadconcepto> validar(@Validated @PathVariable String accion, @RequestBody DtoComunSySeguridadconcepto dto) throws Exception {
		logger.debug("SySeguridadconceptoRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunSySeguridadconcepto>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSySeguridadconcepto> obtenerDto(@RequestBody DtoComunSySeguridadconcepto pk) throws Exception {
		logger.debug("SySeguridadconceptoRest.obtenerDto");
		DtoComunSySeguridadconcepto dto = sySeguridadconceptoDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunSySeguridadconcepto>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSySeguridadconcepto> registrar(@RequestBody DtoComunSySeguridadconcepto dto) throws Exception {
		logger.debug("SySeguridadconceptoRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSySeguridadconcepto>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSySeguridadconcepto> actualizar(@RequestBody DtoComunSySeguridadconcepto dto) throws Exception {
		logger.debug("SySeguridadconceptoRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSySeguridadconcepto>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSySeguridadconcepto> anular(@RequestBody DtoComunSySeguridadconcepto dto) throws Exception {
		logger.debug("SySeguridadconceptoRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunSySeguridadconcepto>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSySeguridadconcepto> eliminar(@RequestBody DtoComunSySeguridadconcepto dto) throws Exception {
		logger.debug("SySeguridadconceptoRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSySeguridadconcepto>(dto,HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("SySeguridadconceptoRest.listar");
		// TODO SySeguridadconceptoRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "syseguridadconcepto.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("SySeguridadconceptoRest.listaractivos");
		// TODO SySeguridadconceptoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "syseguridadconcepto.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSySeguridadconcepto>> listarDtoFiltros(@RequestBody DtoComunSySeguridadconcepto filtro) throws Exception {
		logger.debug("SySeguridadconceptoRest.listardtofiltros");
        List datos = sySeguridadconceptoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunSySeguridadconcepto>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSySeguridadconcepto>> listarDtoActivos(@RequestBody DtoComunSySeguridadconcepto filtro) throws Exception {
		logger.debug("SySeguridadconceptoRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = sySeguridadconceptoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunSySeguridadconcepto>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunSySeguridadconcepto filtro) throws Exception {
		logger.debug("SySeguridadconceptoRest.listarPaginado");
		DominioPaginacion paginacion = sySeguridadconceptoDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listacortaactivospornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaActivosPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("SySeguridadconceptoRest.listaCortaActivosPorNombre");
		filtro.setEstadoId("A");
		List datos = sySeguridadconceptoDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listacortapornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("SySeguridadconceptoRest.listaCortaPorNombre");
		List datos = sySeguridadconceptoDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

}
