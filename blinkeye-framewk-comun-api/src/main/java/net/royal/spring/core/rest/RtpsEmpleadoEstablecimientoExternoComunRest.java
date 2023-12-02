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

import net.royal.spring.core.dao.impl.RtpsEmpleadoEstablecimientoExternoDaoImpl;
import net.royal.spring.core.dao.impl.RtpsEstablecimientoexternoDaoImpl;
import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexterno;
import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexternoPk;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEmpleadoEstablecimientoExterno;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEstablecimientoexterno;
import net.royal.spring.core.dominio.filtro.FiltroComunRtpsEmpleadoEstablecimientoExterno;
import net.royal.spring.core.dominio.filtro.FiltroComunRtpsEstablecimientoexterno;
import net.royal.spring.core.servicio.impl.RtpsEmpleadoEstablecimientoExternoServicioImpl;
import net.royal.spring.core.servicio.impl.RtpsEstablecimientoexternoServicioImpl;
import net.royal.spring.core.servicio.validar.RtpsEmpleadoEstablecimientoExternoServicioValidar;
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
@RequestMapping("/spring/core/rtpsempleadoestablecimientoexterno")
@CrossOrigin(origins = "*")
public class RtpsEmpleadoEstablecimientoExternoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(RtpsEmpleadoEstablecimientoExternoComunRest.class);

	@Autowired
	private RtpsEmpleadoEstablecimientoExternoServicioImpl servicio;

	@Autowired
	private RtpsEmpleadoEstablecimientoExternoServicioValidar validar;

	@Autowired
	private RtpsEmpleadoEstablecimientoExternoDaoImpl rtpsEmpleadoEstablecimientoexternoDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public RtpsEmpleadoEstablecimientoExternoComunRest() {
		super("rtpsempleadoestablecimientoexterno");
	}

	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunRtpsEmpleadoEstablecimientoExterno> validar(@Validated @PathVariable String accion, @RequestBody DtoComunRtpsEmpleadoEstablecimientoExterno dto) throws Exception {
		logger.debug("RtpsEmpleadoEstablecimientoExternoRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunRtpsEmpleadoEstablecimientoExterno>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunRtpsEmpleadoEstablecimientoExterno> obtenerDto(@RequestBody DtoComunRtpsEmpleadoEstablecimientoExterno pk) throws Exception {
		logger.debug("RtpsEmpleadoEstablecimientoExternoRest.obtenerDto");
		DtoComunRtpsEmpleadoEstablecimientoExterno dto = rtpsEmpleadoEstablecimientoexternoDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunRtpsEmpleadoEstablecimientoExterno>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunRtpsEmpleadoEstablecimientoExterno> registrar(@RequestBody DtoComunRtpsEmpleadoEstablecimientoExterno dto) throws Exception {
		logger.debug("RtpsEmpleadoEstablecimientoExternoRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunRtpsEmpleadoEstablecimientoExterno>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunRtpsEmpleadoEstablecimientoExterno> actualizar(@RequestBody DtoComunRtpsEmpleadoEstablecimientoExterno dto) throws Exception {
		logger.debug("RtpsEmpleadoEstablecimientoExternoRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunRtpsEmpleadoEstablecimientoExterno>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/actualizarlista",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FiltroComunRtpsEmpleadoEstablecimientoExterno> actualizarLista(@RequestBody FiltroComunRtpsEmpleadoEstablecimientoExterno filtro) throws Exception {
		logger.debug("RtpsEmpleadoEstablecimientoExternoRest.actualizarlista");
		filtro = servicio.coreActualizarLista(this.getUsuarioActual(),filtro);
		return new ResponseEntity<FiltroComunRtpsEmpleadoEstablecimientoExterno>(filtro, HttpStatus.OK);
	}

	
	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunRtpsEmpleadoEstablecimientoExterno> anular(@RequestBody DtoComunRtpsEmpleadoEstablecimientoExterno dto) throws Exception {
		logger.debug("RtpsEmpleadoEstablecimientoExternoRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunRtpsEmpleadoEstablecimientoExterno>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunRtpsEmpleadoEstablecimientoExterno> eliminar(@RequestBody DtoComunRtpsEmpleadoEstablecimientoExterno dto) throws Exception {
		logger.debug("RtpsEmpleadoEstablecimientoExternoRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunRtpsEmpleadoEstablecimientoExterno>(dto,HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("RtpsEmpleadoEstablecimientoExternoRest.listar");
		// TODO RtpsEstablecimientoexternoRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "rtpsestablecimientoexterno.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("RtpsEmpleadoEstablecimientoExternoRest.listaractivos");
		// TODO RtpsEstablecimientoexternoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "rtpsestablecimientoexterno.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunRtpsEmpleadoEstablecimientoExterno>> listarDtoFiltros(@RequestBody DtoComunRtpsEmpleadoEstablecimientoExterno filtro) throws Exception {
		logger.debug("RtpsEmpleadoEstablecimientoExternoRest.listardtofiltros");
        List datos = rtpsEmpleadoEstablecimientoexternoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunRtpsEmpleadoEstablecimientoExterno>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunRtpsEmpleadoEstablecimientoExterno>> listarDtoActivos(@RequestBody DtoComunRtpsEmpleadoEstablecimientoExterno filtro) throws Exception {
		logger.debug("RtpsEmpleadoEstablecimientoExternoRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = rtpsEmpleadoEstablecimientoexternoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunRtpsEmpleadoEstablecimientoExterno>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunRtpsEmpleadoEstablecimientoExterno filtro) throws Exception {
		logger.debug("RtpsEmpleadoEstablecimientoExternoRest.listarPaginado");
		DominioPaginacion paginacion = rtpsEmpleadoEstablecimientoexternoDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listacortaactivospornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaActivosPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("RtpsEstablecimientoexternoRest.listaCortaActivosPorNombre");
		filtro.setEstadoId("A");
		List datos = rtpsEmpleadoEstablecimientoexternoDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listacortapornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("RtpsEmpleadoEstablecimientoExternoRest.listaCortaPorNombre");
		List datos = rtpsEmpleadoEstablecimientoexternoDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

}
