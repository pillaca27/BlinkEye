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

import net.royal.spring.sistema.dao.impl.SyPreferencesDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyPreferences;
import net.royal.spring.sistema.dominio.BeanSyPreferencesPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyPreferences;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSyPreferences;
import net.royal.spring.sistema.servicio.impl.SyPreferencesServicioImpl;
import net.royal.spring.sistema.servicio.validar.SyPreferencesServicioValidar;
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
@RequestMapping("/spring/sistema/sypreferences")
@CrossOrigin(origins = "*")
public class SyPreferencesRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(SyPreferencesRest.class);

	@Autowired
	private SyPreferencesServicioImpl servicio;

	@Autowired
	private SyPreferencesServicioValidar validar;

	@Autowired
	private SyPreferencesDaoImpl syPreferencesDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public SyPreferencesRest() {
		super("sypreferences");
	}

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyPreferences> validar(@Validated @PathVariable String accion, @RequestBody DtoComunSyPreferences dto) throws Exception {
		logger.debug("SyPreferencesRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunSyPreferences>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyPreferences> obtenerDto(@RequestBody DtoComunSyPreferences pk) throws Exception {
		logger.debug("SyPreferencesRest.obtenerDto");
		DtoComunSyPreferences dto = syPreferencesDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunSyPreferences>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyPreferences> registrar(@RequestBody DtoComunSyPreferences dto) throws Exception {
		logger.debug("SyPreferencesRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyPreferences>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyPreferences> actualizar(@RequestBody DtoComunSyPreferences dto) throws Exception {
		logger.debug("SyPreferencesRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyPreferences>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyPreferences> eliminar(@RequestBody DtoComunSyPreferences dto) throws Exception {
		logger.debug("SyPreferencesRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyPreferences>(dto,HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("SyPreferencesRest.listar");
		// TODO SyPreferencesRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "sypreferences.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("SyPreferencesRest.listaractivos");
		// TODO SyPreferencesRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "sypreferences.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSyPreferences>> listarDtoFiltros(@RequestBody DtoComunSyPreferences filtro) throws Exception {
		logger.debug("SyPreferencesRest.listardtofiltros");
        List datos = syPreferencesDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunSyPreferences>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSyPreferences>> listarDtoActivos(@RequestBody DtoComunSyPreferences filtro) throws Exception {
		logger.debug("SyPreferencesRest.listardtoactivos");
		List datos = syPreferencesDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunSyPreferences>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunSyPreferences filtro) throws Exception {
		logger.debug("SyPreferencesRest.listarPaginado");
		DominioPaginacion paginacion = syPreferencesDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listacortaactivospornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaActivosPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("SyPreferencesRest.listaCortaActivosPorNombre");
		filtro.setEstadoId("A");
		List datos = syPreferencesDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listacortapornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("SyPreferencesRest.listaCortaPorNombre");
		List datos = syPreferencesDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/registrarpreference", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSyPreferences>> listarProcesoResultado(@RequestBody DtoComunSyPreferences filtro) throws Exception {
		logger.debug("AsAsistenciadiariaRest.listarProcesoResultado");
		List datos = (List) syPreferencesDao.registrarPreferencias(null, filtro);
		return new ResponseEntity<List<DtoComunSyPreferences>>(datos, HttpStatus.OK);
		
	}
}
