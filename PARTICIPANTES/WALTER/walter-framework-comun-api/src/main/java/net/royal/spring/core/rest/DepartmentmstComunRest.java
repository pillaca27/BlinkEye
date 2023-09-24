package net.royal.spring.core.rest;

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

import net.royal.spring.core.dao.impl.DepartmentmstDaoImpl;
import net.royal.spring.core.dominio.BeanDepartmentmst;
import net.royal.spring.core.dominio.BeanDepartmentmstPk;
import net.royal.spring.core.dominio.dto.DtoComunDepartmentmst;
import net.royal.spring.core.dominio.filtro.FiltroComunDepartmentmst;
//import net.royal.spring.core.dominio.filtro.FiltroResponsiblemst;
//import net.royal.spring.core.dominio.lista.DtlDepartmentmst;
import net.royal.spring.core.servicio.impl.DepartmentmstServicioImpl;
import net.royal.spring.core.servicio.validar.DepartmentmstServicioValidar;
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
@RequestMapping("/spring/core/departmentmstcomun")
@CrossOrigin(origins = "*")
public class DepartmentmstComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(DepartmentmstComunRest.class);

	@Autowired
	private DepartmentmstServicioImpl servicio;

	@Autowired
	private DepartmentmstServicioValidar validar;

	@Autowired
	private DepartmentmstDaoImpl departmentmstDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public DepartmentmstComunRest() {
		super("departmentmst");
	}

	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunDepartmentmst> validar(@Validated @PathVariable String accion, @RequestBody DtoComunDepartmentmst dto) throws Exception {
		logger.debug("DepartmentmstRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunDepartmentmst>(dto, HttpStatus.OK);
	}*/

	/*
	 *LEONARDO 
	 * OBTIENE OBJETO DEPARTMENTMST
	 * 
	 */
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunDepartmentmst> obtenerDto(@RequestBody DtoComunDepartmentmst pk) throws Exception {
		logger.debug("DepartmentmstRest.obtenerDto");
		DtoComunDepartmentmst dto = departmentmstDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunDepartmentmst>(dto,HttpStatus.OK);
	}

	/*
	 *LEONARDO 
	 * REGISTRA DEPARTMENTMST
	 * 
	 */
	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunDepartmentmst> registrar(@RequestBody DtoComunDepartmentmst dto) throws Exception {
		logger.debug("DepartmentmstRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunDepartmentmst>(dto, HttpStatus.CREATED);
	}

	/*
	 *LEONARDO 
	 * ACTUALIZA DEPARTMENTMST
	 * 
	 */
	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunDepartmentmst> actualizar(@RequestBody DtoComunDepartmentmst dto) throws Exception {
		logger.debug("DepartmentmstRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunDepartmentmst>(dto, HttpStatus.OK);
	}
	
	/*
	 *LEONARDO 
	 * ELIMINA DEPARTMENTMST
	 * 
	 */
	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunDepartmentmst> eliminar(@RequestBody DtoComunDepartmentmst dto) throws Exception {
		logger.debug("DepartmentmstRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunDepartmentmst>(dto,HttpStatus.OK);
	}

	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("DepartmentmstRest.listar");
		// TODO DepartmentmstRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "departmentmstWh.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("DepartmentmstRest.listaractivos");
		// TODO DepartmentmstRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "departmentmstWh.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunDepartmentmst>> listarDtoFiltros(@RequestBody DtoComunDepartmentmst filtro) throws Exception {
		logger.debug("DepartmentmstRest.listardtofiltros");
        List datos = departmentmstDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunDepartmentmst>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunDepartmentmst>> listarDtoActivos(@RequestBody DtoComunDepartmentmst filtro) throws Exception {
		logger.debug("DepartmentmstRest.listardtoactivos");
		List datos = departmentmstDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunDepartmentmst>>(datos, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * LISTADO PAGINADO GENERAL
	 * 
	 */
	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunDepartmentmst filtro) throws Exception {
		logger.debug("DepartmentmstRest.listarPaginado");
		DominioPaginacion paginacion = departmentmstDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/exportarDepartamentosMast", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarDepartamentosMast(HttpServletResponse response, @RequestBody FiltroComunDepartmentmst filtro) throws Exception
	 {
		
		DominioPaginacion paginacion = departmentmstDao.exportarDepartamentosMast(filtro);
		String[] arrCabecera = new String[] {"Departamento","Descripci\u00f3n","Estado","Dispon. Contabilidad","Dispon. Log\u00EDstica"};
		String[] arrColumnas = new String[] { "department", "description", "estadodescripcion","discontabilidad","dislogistica"}; 
		

		DtoWhExportar dtoExportar=new DtoWhExportar();
		dtoExportar.setTitulo("Listado de Maestro de Departamentos");
		dtoExportar.setTipoExpotar(filtro.getTipoexportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setPaginacion(paginacion);
			
		servicio.exportarInformacionWh(response,dtoExportar);
	}
}
