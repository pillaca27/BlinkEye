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

import net.royal.spring.sistema.dao.impl.SySeguridadgrupoDaoImpl;
import net.royal.spring.sistema.dominio.BeanSySeguridadgrupo;
import net.royal.spring.sistema.dominio.BeanSySeguridadgrupoPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSySeguridadgrupo;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSySeguridadgrupo;
import net.royal.spring.sistema.servicio.impl.SySeguridadgrupoServicioImpl;
import net.royal.spring.sistema.servicio.validar.SySeguridadgrupoServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/sistema/syseguridadgrupo")
@CrossOrigin(origins = "*")
public class SySeguridadgrupoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(SySeguridadgrupoComunRest.class);

	@Autowired
	private SySeguridadgrupoServicioImpl servicio;

	@Autowired
	private SySeguridadgrupoServicioValidar validar;

	@Autowired
	private SySeguridadgrupoDaoImpl sySeguridadgrupoDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public SySeguridadgrupoComunRest() {
		super("syseguridadgrupo");
	}

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSySeguridadgrupo> validar(@Validated @PathVariable String accion, @RequestBody DtoComunSySeguridadgrupo dto) throws Exception {
		logger.debug("SySeguridadgrupoRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunSySeguridadgrupo>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSySeguridadgrupo> obtenerDto(@RequestBody DtoComunSySeguridadgrupo pk) throws Exception {
		logger.debug("SySeguridadgrupoRest.obtenerDto");
		DtoComunSySeguridadgrupo dto = sySeguridadgrupoDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunSySeguridadgrupo>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSySeguridadgrupo> registrar(@RequestBody DtoComunSySeguridadgrupo dto) throws Exception {
		logger.debug("SySeguridadgrupoRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSySeguridadgrupo>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSySeguridadgrupo> actualizar(@RequestBody DtoComunSySeguridadgrupo dto) throws Exception {
		logger.debug("SySeguridadgrupoRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSySeguridadgrupo>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSySeguridadgrupo> anular(@RequestBody DtoComunSySeguridadgrupo dto) throws Exception {
		logger.debug("SySeguridadgrupoRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunSySeguridadgrupo>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSySeguridadgrupo> eliminar(@RequestBody DtoComunSySeguridadgrupo dto) throws Exception {
		logger.debug("SySeguridadgrupoRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSySeguridadgrupo>(dto,HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar(@RequestBody DtoComunSySeguridadgrupo filtro) {
		logger.debug("SySeguridadgrupoRest.listar");
		// TODO SySeguridadgrupoRest.listar : modificar query
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if(UString.estaVacio(filtro.getAplicacioncodigo())) {
			filtro.setAplicacioncodigo(null);
		}
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		List datos = this.listarPorQuery(DtoTabla.class, "syseguridadgrupo.listar",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listarseguridadgrupo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarseguridadgrupo(@RequestBody DtoComunSySeguridadgrupo filtro) {
		logger.debug("SySeguridadgrupoRest.listarseguridadgrupo");
		// TODO SySeguridadgrupoRest.listar : modificar query
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if(UString.estaVacio(filtro.getAplicacioncodigo())) {
			filtro.setAplicacioncodigo(null);
		}
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		List datos = this.listarPorQuery(DtoTabla.class, "syseguridadgrupo.listarseguridadgrupo",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("SySeguridadgrupoRest.listaractivos");
		// TODO SySeguridadgrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "syseguridadgrupo.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSySeguridadgrupo>> listarDtoFiltros(@RequestBody DtoComunSySeguridadgrupo filtro) throws Exception {
		logger.debug("SySeguridadgrupoRest.listardtofiltros");
        List datos = sySeguridadgrupoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunSySeguridadgrupo>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSySeguridadgrupo>> listarDtoActivos(@RequestBody DtoComunSySeguridadgrupo filtro) throws Exception {
		logger.debug("SySeguridadgrupoRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = sySeguridadgrupoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunSySeguridadgrupo>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunSySeguridadgrupo filtro) throws Exception {
		logger.debug("SySeguridadgrupoRest.listarPaginado");
		DominioPaginacion paginacion = sySeguridadgrupoDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listacortaactivospornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaActivosPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("SySeguridadgrupoRest.listaCortaActivosPorNombre");
		filtro.setEstadoId("A");
		List datos = sySeguridadgrupoDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listacortapornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("SySeguridadgrupoRest.listaCortaPorNombre");
		List datos = sySeguridadgrupoDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listarTipoDetalle", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarTipoDetalle() {
		logger.debug("SySeguridadgrupoRest.listarTipoDetalle");
		List datos = this.listarPorQuery(DtoTabla.class, "syseguridadgrupo.listarTipoDetalle");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
}
