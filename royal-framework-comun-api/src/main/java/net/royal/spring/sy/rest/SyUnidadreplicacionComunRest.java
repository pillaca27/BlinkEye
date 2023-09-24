package net.royal.spring.sy.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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

import net.royal.spring.sy.dao.impl.SyUnidadreplicacionDaoImpl;
import net.royal.spring.sy.dominio.BeanSyUnidadreplicacion;
import net.royal.spring.sy.dominio.BeanSyUnidadreplicacionPk;
import net.royal.spring.sy.dominio.dto.DtoComunSyUnidadreplicacion;
import net.royal.spring.sy.dominio.filtro.FiltroComunSyUnidadreplicacion;
import net.royal.spring.sy.dominio.lista.DtlComunSyUnidadreplicacion;
import net.royal.spring.sy.servicio.impl.SyUnidadreplicacionServicioImpl;
import net.royal.spring.sy.servicio.validar.SyUnidadreplicacionServicioValidar;
import net.royal.spring.core.dominio.filtro.FiltroComunMaUnidadnegocio;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/sistema/syunidadreplicacioncomun")
@CrossOrigin(origins = "*")
public class SyUnidadreplicacionComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(SyUnidadreplicacionComunRest.class);

	@Autowired
	private SyUnidadreplicacionServicioImpl servicio;

	@Autowired
	private SyUnidadreplicacionServicioValidar validar;

	@Autowired
	private SyUnidadreplicacionDaoImpl syUnidadreplicacionDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public SyUnidadreplicacionComunRest() {
		super("syunidadreplicacion");  
	}

	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoSyUnidadreplicacion> validar(@Validated @PathVariable String accion, @RequestBody DtoSyUnidadreplicacion dto) throws Exception {
		logger.debug("SyUnidadreplicacionRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoSyUnidadreplicacion>(dto, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * OBTIENE OBJETO SYUNIDADREPLICACION
	 * */
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyUnidadreplicacion> obtenerDto(@RequestBody DtoComunSyUnidadreplicacion pk) throws Exception {
		logger.debug("SyUnidadreplicacionRest.obtenerDto");
		DtoComunSyUnidadreplicacion dto = syUnidadreplicacionDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunSyUnidadreplicacion>(dto,HttpStatus.OK);
	}

	/*
	 * LEONARDO
	 * REGISTRO SYUNIDADREPLICACION
	 * */
	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyUnidadreplicacion> registrar(@RequestBody DtoComunSyUnidadreplicacion dto) throws Exception {
		logger.debug("SyUnidadreplicacionRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyUnidadreplicacion>(dto, HttpStatus.CREATED);
	}

	/*
	 * LEONARDO
	 * ACTUALIZA SYUNIDADREPLICACION
	 * */
	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyUnidadreplicacion> actualizar(@RequestBody DtoComunSyUnidadreplicacion dto) throws Exception {
		logger.debug("SyUnidadreplicacionRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyUnidadreplicacion>(dto, HttpStatus.OK);
	}

	/*@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoSyUnidadreplicacion> anular(@RequestBody DtoSyUnidadreplicacion dto) throws Exception {
		logger.debug("SyUnidadreplicacionRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoSyUnidadreplicacion>(dto, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * ELIMINA SYUNIDADREPLICACION
	 * */
	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyUnidadreplicacion> eliminar(@RequestBody DtoComunSyUnidadreplicacion dto) throws Exception {
		logger.debug("SyUnidadreplicacionRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunSyUnidadreplicacion>(dto,HttpStatus.OK);
	}

	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("SyUnidadreplicacionRest.listar");
		// TODO SyUnidadreplicacionRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "syunidadreplicacion.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("SyUnidadreplicacionRest.listaractivos");
		// TODO SyUnidadreplicacionRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "syunidadreplicacion.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoSyUnidadreplicacion>> listarDtoFiltros(@RequestBody DtoSyUnidadreplicacion filtro) throws Exception {
		logger.debug("SyUnidadreplicacionRest.listardtofiltros");
        List datos = syUnidadreplicacionDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoSyUnidadreplicacion>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoSyUnidadreplicacion>> listarDtoActivos(@RequestBody DtoSyUnidadreplicacion filtro) throws Exception {
		logger.debug("SyUnidadreplicacionRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = syUnidadreplicacionDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoSyUnidadreplicacion>>(datos, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * LISTADO PAGINADO GENERAL
	 * */
	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunSyUnidadreplicacion filtro) throws Exception {
		logger.debug("SyUnidadreplicacionRest.listarPaginado");
		DominioPaginacion paginacion = syUnidadreplicacionDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	/*
	 * LEONARDO
	 * EXPORTAR LISTADO UNIDAD REPLICACION
	 * */
	@Transactional
	@PostMapping(value = "/exportarUnidadReplicacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarUnidadReplicacion(HttpServletResponse response, @RequestBody FiltroComunSyUnidadreplicacion filtro) throws Exception
	 {
		
		DominioPaginacion paginacion = syUnidadreplicacionDao.exportarUnidadReplicacion(filtro);
		String[] arrCabecera = new String[] {"Und. Replicaci\u00f3n","Descripci\u00f3n","Estado"};
		String[] arrColumnas = new String[] {"unidadreplicacion","descripcionlocal","estadodescripcion"}; 
		

		DtoWhExportar dtoExportar=new DtoWhExportar();
		dtoExportar.setTitulo("Listado de Unidad de Replicaci\u00f3n");
		dtoExportar.setTipoExpotar(filtro.getTipoexportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setPaginacion(paginacion);
			
		servicio.exportarInformacionWh(response,dtoExportar);
	}
}
