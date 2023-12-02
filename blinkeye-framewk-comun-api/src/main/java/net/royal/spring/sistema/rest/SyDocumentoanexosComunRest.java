package net.royal.spring.sistema.rest;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
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

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.sistema.dominio.dto.DtoComunSyDocumentoanexos;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSyDocumentoanexos;
import net.royal.spring.sistema.dao.impl.SyDocumentoanexosDaoImpl;
import net.royal.spring.sistema.servicio.impl.SyDocumentoanexosServicioImpl;
import net.royal.spring.sistema.servicio.validar.SyDocumentoanexosServicioValidar;

@RestController
@RequestMapping("/spring/logistica/sydocumentoanexos")
@CrossOrigin(origins = "*")
public class SyDocumentoanexosComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(SyDocumentoanexosComunRest.class);

	@Autowired
	private SyDocumentoanexosServicioImpl servicio;

	@Autowired
	private SyDocumentoanexosServicioValidar validar;

	@Autowired
	private SyDocumentoanexosDaoImpl syDocumentoanexosDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public SyDocumentoanexosComunRest() {
		super("sydocumentoanexos");
	}

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoanexos> validar(@Validated @PathVariable String accion, @RequestBody DtoComunSyDocumentoanexos dto) throws Exception {
		logger.debug("SyDocumentoanexosRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunSyDocumentoanexos>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoanexos> obtenerDto(@RequestBody DtoComunSyDocumentoanexos pk) throws Exception {
		logger.debug("SyDocumentoanexosRest.obtenerDto");
		DtoComunSyDocumentoanexos dto = syDocumentoanexosDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunSyDocumentoanexos>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoanexos> registrar(@RequestBody DtoComunSyDocumentoanexos dto) throws Exception {
		logger.debug("SyDocumentoanexosRest.registrar");
		dto.setSecuencia(syDocumentoanexosDao.generarSecuencia(dto));
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyDocumentoanexos>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoanexos> actualizar(@RequestBody DtoComunSyDocumentoanexos dto) throws Exception {
		logger.debug("SyDocumentoanexosRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyDocumentoanexos>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoanexos> anular(@RequestBody DtoComunSyDocumentoanexos dto) throws Exception {
		logger.debug("SyDocumentoanexosRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunSyDocumentoanexos>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoanexos> eliminar(@RequestBody DtoComunSyDocumentoanexos dto) throws Exception {
		logger.debug("SyDocumentoanexosRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyDocumentoanexos>(dto,HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("SyDocumentoanexosRest.listar");
		// TODO SyDocumentoanexosRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "sydocumentoanexos.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("SyDocumentoanexosRest.listaractivos");
		// TODO SyDocumentoanexosRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "sydocumentoanexos.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSyDocumentoanexos>> listarDtoFiltros(@RequestBody DtoComunSyDocumentoanexos filtro) throws Exception {
		logger.debug("SyDocumentoanexosRest.listardtofiltros");
        List datos = syDocumentoanexosDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunSyDocumentoanexos>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSyDocumentoanexos>> listarDtoActivos(@RequestBody DtoComunSyDocumentoanexos filtro) throws Exception {
		logger.debug("SyDocumentoanexosRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = syDocumentoanexosDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunSyDocumentoanexos>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunSyDocumentoanexos filtro) throws Exception {
		logger.debug("SyDocumentoanexosRest.listarPaginado");
		DominioPaginacion paginacion = syDocumentoanexosDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listacortaactivospornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaActivosPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("SyDocumentoanexosRest.listaCortaActivosPorNombre");
		filtro.setEstadoId("A");
		List datos = syDocumentoanexosDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listacortapornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("SyDocumentoanexosRest.listaCortaPorNombre");
		List datos = syDocumentoanexosDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional(readOnly = true)
	@PostMapping(value = "/verAdjunto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyDocumentoanexos> verAdjunto(@RequestBody DtoComunSyDocumentoanexos bean) throws Exception {
		logger.debug("SyDocumentoanexosRest.verAdjunto");
		return new ResponseEntity<DtoComunSyDocumentoanexos>(servicio.verAdjunto(bean), HttpStatus.OK);
	}

}
