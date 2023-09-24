package net.royal.spring.sy.rest;

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

import net.royal.spring.sy.dao.impl.SyAplicacionreportetopicoDaoImpl;
import net.royal.spring.sy.dominio.BeanSyAplicacionreportetopico;
import net.royal.spring.sy.dominio.BeanSyAplicacionreportetopicoPk;
import net.royal.spring.sy.dominio.dto.DtoComunSyAplicacionreportetopico;
import net.royal.spring.sy.dominio.filtro.FiltroComunSyAplicacionreportetopico;
import net.royal.spring.sy.servicio.impl.SyAplicacionreportetopicoServicioImpl;
import net.royal.spring.sy.servicio.validar.SyAplicacionreportetopicoServicioValidar;
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
@RequestMapping("/spring/sy/syaplicacionreportetopico")
@CrossOrigin(origins = "*")
public class SyAplicacionreportetopicoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(SyAplicacionreportetopicoComunRest.class);

	@Autowired
	private SyAplicacionreportetopicoServicioImpl servicio;

	@Autowired
	private SyAplicacionreportetopicoServicioValidar validar;

	@Autowired
	private SyAplicacionreportetopicoDaoImpl syAplicacionreportetopicoDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public SyAplicacionreportetopicoComunRest() {
		super("syaplicacionreportetopico");
	}

	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoSyAplicacionreportetopico> validar(@Validated @PathVariable String accion, @RequestBody DtoSyAplicacionreportetopico dto) throws Exception {
		logger.debug("SyAplicacionreportetopicoRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoSyAplicacionreportetopico>(dto, HttpStatus.OK);
	}*/

	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyAplicacionreportetopico> obtenerDto(@RequestBody DtoComunSyAplicacionreportetopico pk) throws Exception {
		logger.debug("SyAplicacionreportetopicoRest.obtenerDtoPorUuid");
		DtoComunSyAplicacionreportetopico dto = syAplicacionreportetopicoDao.obtenerDtoPorUuid(pk.getUuid());
		return new ResponseEntity<DtoComunSyAplicacionreportetopico>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyAplicacionreportetopico> registrar(@RequestBody DtoComunSyAplicacionreportetopico dto) throws Exception {
		logger.debug("SyAplicacionreportetopicoRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyAplicacionreportetopico>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyAplicacionreportetopico> actualizar(@RequestBody DtoComunSyAplicacionreportetopico dto) throws Exception {
		logger.debug("SyAplicacionreportetopicoRest.actualizar");
		dto = servicio.coreActualizarPorUuid(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyAplicacionreportetopico>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyAplicacionreportetopico> anular(@RequestBody DtoComunSyAplicacionreportetopico dto) throws Exception {
		logger.debug("SyAplicacionreportetopicoRest.anularPorUuid");
		dto = servicio.coreAnularPorUuid(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunSyAplicacionreportetopico>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/activar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyAplicacionreportetopico> activar(@RequestBody DtoComunSyAplicacionreportetopico dto) throws Exception {
		logger.debug("SyAplicacionreportetopicoRest.activarPorUuid");
		dto = servicio.coreActivarPorUuid(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunSyAplicacionreportetopico>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyAplicacionreportetopico> eliminar(@RequestBody DtoComunSyAplicacionreportetopico dto) throws Exception {
		logger.debug("SyAplicacionreportetopicoRest.eliminarPorUuid");
		dto = servicio.coreEliminarPorUuid(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyAplicacionreportetopico>(dto,HttpStatus.OK);
	}

	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("SyAplicacionreportetopicoRest.listar");
		// TODO SyAplicacionreportetopicoRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "syaplicacionreportetopico.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("SyAplicacionreportetopicoRest.listaractivos");
		// TODO SyAplicacionreportetopicoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "syaplicacionreportetopico.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoSyAplicacionreportetopico>> listarDtoFiltros(@RequestBody DtoSyAplicacionreportetopico filtro) throws Exception {
		logger.debug("SyAplicacionreportetopicoRest.listardtofiltros");
        List datos = syAplicacionreportetopicoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoSyAplicacionreportetopico>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoSyAplicacionreportetopico>> listarDtoActivos(@RequestBody DtoSyAplicacionreportetopico filtro) throws Exception {
		logger.debug("SyAplicacionreportetopicoRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = syAplicacionreportetopicoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoSyAplicacionreportetopico>>(datos, HttpStatus.OK);
	}*/

	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunSyAplicacionreportetopico filtro) throws Exception {
		logger.debug("SyAplicacionreportetopicoRest.listarPaginado");
		DominioPaginacion paginacion = syAplicacionreportetopicoDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	/*@Transactional
	@PutMapping(value = "/listacortaactivospornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaActivosPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("SyAplicacionreportetopicoRest.listaCortaActivosPorNombre");
		filtro.setEstadoId("A");
		List datos = syAplicacionreportetopicoDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listacortapornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("SyAplicacionreportetopicoRest.listaCortaPorNombre");
		List datos = syAplicacionreportetopicoDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

}
