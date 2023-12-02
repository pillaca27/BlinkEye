package net.royal.spring.core.rest;

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

import net.royal.spring.core.dao.impl.RtpsEstablecimientoexternoDaoImpl;
import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexterno;
import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexternoPk;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEstablecimientoexterno;
import net.royal.spring.core.dominio.filtro.FiltroComunRtpsEstablecimientoexterno;
import net.royal.spring.core.servicio.impl.RtpsEstablecimientoexternoServicioImpl;
import net.royal.spring.core.servicio.validar.RtpsEstablecimientoexternoServicioValidar;
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
@RequestMapping("/spring/core/rtpsestablecimientoexterno")
@CrossOrigin(origins = "*")
public class RtpsEstablecimientoexternoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(RtpsEstablecimientoexternoComunRest.class);

	@Autowired
	private RtpsEstablecimientoexternoServicioImpl servicio;

	@Autowired
	private RtpsEstablecimientoexternoServicioValidar validar;

	@Autowired
	private RtpsEstablecimientoexternoDaoImpl rtpsEstablecimientoexternoDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public RtpsEstablecimientoexternoComunRest() {
		super("rtpsestablecimientoexterno");
	}

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunRtpsEstablecimientoexterno> validar(@Validated @PathVariable String accion, @RequestBody DtoComunRtpsEstablecimientoexterno dto) throws Exception {
		logger.debug("RtpsEstablecimientoexternoRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunRtpsEstablecimientoexterno>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunRtpsEstablecimientoexterno> obtenerDto(@RequestBody DtoComunRtpsEstablecimientoexterno pk) throws Exception {
		logger.debug("RtpsEstablecimientoexternoRest.obtenerDto");
		DtoComunRtpsEstablecimientoexterno dto = rtpsEstablecimientoexternoDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunRtpsEstablecimientoexterno>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunRtpsEstablecimientoexterno> registrar(@RequestBody DtoComunRtpsEstablecimientoexterno dto) throws Exception {
		logger.debug("RtpsEstablecimientoexternoRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunRtpsEstablecimientoexterno>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunRtpsEstablecimientoexterno> actualizar(@RequestBody DtoComunRtpsEstablecimientoexterno dto) throws Exception {
		logger.debug("RtpsEstablecimientoexternoRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunRtpsEstablecimientoexterno>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunRtpsEstablecimientoexterno> anular(@RequestBody DtoComunRtpsEstablecimientoexterno dto) throws Exception {
		logger.debug("RtpsEstablecimientoexternoRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunRtpsEstablecimientoexterno>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunRtpsEstablecimientoexterno> eliminar(@RequestBody DtoComunRtpsEstablecimientoexterno dto) throws Exception {
		logger.debug("RtpsEstablecimientoexternoRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunRtpsEstablecimientoexterno>(dto,HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("RtpsEstablecimientoexternoRest.listar");
		// TODO RtpsEstablecimientoexternoRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "rtpsestablecimientoexterno.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("RtpsEstablecimientoexternoRest.listaractivos");
		// TODO RtpsEstablecimientoexternoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "rtpsestablecimientoexterno.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunRtpsEstablecimientoexterno>> listarDtoFiltros(@RequestBody DtoComunRtpsEstablecimientoexterno filtro) throws Exception {
		logger.debug("RtpsEstablecimientoexternoRest.listardtofiltros");
        List datos = rtpsEstablecimientoexternoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunRtpsEstablecimientoexterno>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunRtpsEstablecimientoexterno>> listarDtoActivos(@RequestBody DtoComunRtpsEstablecimientoexterno filtro) throws Exception {
		logger.debug("RtpsEstablecimientoexternoRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = rtpsEstablecimientoexternoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunRtpsEstablecimientoexterno>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunRtpsEstablecimientoexterno filtro) throws Exception {
		logger.debug("RtpsEstablecimientoexternoRest.listarPaginado");
		DominioPaginacion paginacion = rtpsEstablecimientoexternoDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listacortaactivospornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaActivosPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("RtpsEstablecimientoexternoRest.listaCortaActivosPorNombre");
		filtro.setEstadoId("A");
		List datos = rtpsEstablecimientoexternoDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listacortapornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("RtpsEstablecimientoexternoRest.listaCortaPorNombre");
		List datos = rtpsEstablecimientoexternoDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

}
