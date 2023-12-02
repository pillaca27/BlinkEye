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

import net.royal.spring.sistema.dao.impl.SyProcesomstDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyProcesomst;
import net.royal.spring.sistema.dominio.BeanSyProcesomstPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyProcesomst;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSyProcesomst;
import net.royal.spring.sistema.servicio.impl.SyProcesomstServicioImpl;
import net.royal.spring.sistema.servicio.validar.SyProcesomstServicioValidar;
import net.royal.spring.core.dominio.filtro.FiltroComunAplicacionesmast;
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
@RequestMapping("/spring/sistema/syprocesomst")
@CrossOrigin(origins = "*")
public class SyProcesomstComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(SyProcesomstComunRest.class);

	@Autowired
	private SyProcesomstServicioImpl servicio;

	@Autowired
	private SyProcesomstServicioValidar validar;

	@Autowired
	private SyProcesomstDaoImpl syProcesomstDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public SyProcesomstComunRest() {
		super("syprocesomst");
	}

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyProcesomst> validar(@Validated @PathVariable String accion, @RequestBody DtoComunSyProcesomst dto) throws Exception {
		logger.debug("SyProcesomstRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunSyProcesomst>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyProcesomst> obtenerDto(@RequestBody DtoComunSyProcesomst pk) throws Exception {
		logger.debug("SyProcesomstRest.obtenerDto");
		DtoComunSyProcesomst dto = syProcesomstDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunSyProcesomst>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyProcesomst> registrar(@RequestBody DtoComunSyProcesomst dto) throws Exception {
		logger.debug("SyProcesomstRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyProcesomst>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyProcesomst> actualizar(@RequestBody DtoComunSyProcesomst dto) throws Exception {
		logger.debug("SyProcesomstRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyProcesomst>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyProcesomst> anular(@RequestBody DtoComunSyProcesomst dto) throws Exception {
		logger.debug("SyProcesomstRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunSyProcesomst>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyProcesomst> eliminar(@RequestBody DtoComunSyProcesomst dto) throws Exception {
		logger.debug("SyProcesomstRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyProcesomst>(dto,HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("SyProcesomstRest.listar");
		// TODO SyProcesomstRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "syprocesomst.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("SyProcesomstRest.listaractivos");
		// TODO SyProcesomstRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "syprocesomst.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSyProcesomst>> listarDtoFiltros(@RequestBody DtoComunSyProcesomst filtro) throws Exception {
		logger.debug("SyProcesomstRest.listardtofiltros");
        List datos = syProcesomstDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunSyProcesomst>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSyProcesomst>> listarDtoActivos(@RequestBody DtoComunSyProcesomst filtro) throws Exception {
		logger.debug("SyProcesomstRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = syProcesomstDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunSyProcesomst>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunSyProcesomst filtro) throws Exception {
		logger.debug("SyProcesomstRest.listarPaginado");
		DominioPaginacion paginacion = syProcesomstDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listacortaactivospornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaActivosPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("SyProcesomstRest.listaCortaActivosPorNombre");
		filtro.setEstadoId("A");
		List datos = syProcesomstDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listacortapornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("SyProcesomstRest.listaCortaPorNombre");
		List datos = syProcesomstDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/listaraplicacionesmastporfiltro", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listaraplicacionesmastporfiltro(@RequestBody FiltroComunAplicacionesmast filtro) throws Exception {
		logger.debug("SyProcesomstRest.listaraplicacionesmastporfiltro");
		DominioPaginacion paginacion = syProcesomstDao.listaraplicacionesmastporfiltro(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}
}
