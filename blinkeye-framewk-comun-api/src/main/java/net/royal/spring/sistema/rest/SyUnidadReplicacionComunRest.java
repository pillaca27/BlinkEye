package net.royal.spring.sistema.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.sistema.dao.impl.SyUnidadreplicacionDaoImpl;
import net.royal.spring.sistema.dominio.dto.DtoComunSyUnidadreplicacion;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSyUnidadreplicacion;
import net.royal.spring.sistema.servicio.impl.SyUnidadreplicacionServicioImpl;
import net.royal.spring.sistema.servicio.validar.SyUnidadreplicacionServicioValidar;

@RestController
@RequestMapping("/spring/sistema/syunidadreplicacioncomun")
@CrossOrigin(origins = "*")
public class SyUnidadReplicacionComunRest extends GenericoHibernateRest {

	 
	private static Logger logger = LogManager.getLogger(SyUnidadReplicacionComunRest.class);

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

	public SyUnidadReplicacionComunRest() {
		super("syunidadreplicacion");  
	}
	
	
	@ApiOperation(notes = "Listado de todos las Unidades de replicacion activas "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "99-UNIREPL-CLIACT", tags = {"CORE", "UNIDAD REPLICACION"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaractivos() {
		List datos = listarPorQuery(DtoTabla.class, "syunidadreplicacion.listaractivos");
		return new ResponseEntity<List<DtoTabla>>(datos,HttpStatus.OK);
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
		DtoComunSyUnidadreplicacion dto = syUnidadreplicacionDao.obtenerDtoPorUuid(pk.getUuid());
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
		dto = servicio.coreActualizarPorUuid(this.getUsuarioActual(),dto);
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
		dto = servicio.coreEliminarPorUuid(this.getUsuarioActual(),dto);
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
