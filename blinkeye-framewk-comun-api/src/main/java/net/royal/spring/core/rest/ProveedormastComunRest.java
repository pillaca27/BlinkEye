package net.royal.spring.core.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
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
import net.royal.spring.core.dao.impl.ProveedormastDaoImpl;
import net.royal.spring.core.dominio.dto.DtoComunProveedormast;
import net.royal.spring.core.servicio.impl.ProveedormastServicioImpl;
import net.royal.spring.core.servicio.validar.ProveedormastServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/proveedormastcomun")
@CrossOrigin(origins = "*")
public class ProveedormastComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(ProveedormastComunRest.class);

	@Autowired
	private ProveedormastServicioImpl servicio;

	@Autowired
	private ProveedormastServicioValidar validar;

	@Autowired
	private ProveedormastDaoImpl proveedormastDao;
	
	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public ProveedormastComunRest() {
		super("proveedormast");
	}
	
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar");
		List datos = this.listarPorQuery(DtoTabla.class, "proveedormast.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "proveedormast.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener tabla. Entrada: DtoTabla: id. Retorno: DtoTabla",					
			nickname="PROVEEDORMAST_COBT", value = "Obtener tabla", tags = {"PROVEEDORMAST", "OBTENER"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		logger.debug("obtenertabla");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_proveedor", BigDecimal.class, filtro.getId()));
        List datos = this.listarPorQuery(DtoTabla.class, "proveedormast.obtenerTabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	@Transactional
	@PutMapping(value = "/listarfiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarFiltros(@RequestBody DtoTabla filtro) {
		logger.debug("listarfiltros");
		if (UString.esNuloVacio(filtro.getCodigo()))
			filtro.setCodigo(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);
		else
			filtro.setNombre(filtro.getNombre().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstadoId()))
			filtro.setEstadoId(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();		
		parametros.add(new DominioParametroPersistencia("p_proveedor", BigDecimal.class, filtro.getId()));
		parametros.add(new DominioParametroPersistencia("p_busqueda", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "proveedormast.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
//	@Transactional
//	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<DtoComunProveedormast> obtenerDto(@RequestBody DtoComunProveedormast pk) throws Exception {
//		logger.debug("obtenerdto");
//		DtoComunProveedormast dto = obtenerDtoCore(pk);
//		return new ResponseEntity<DtoComunProveedormast>(dto,HttpStatus.OK);
//	}
	
	@Transactional
	@PutMapping(value="/obtenertiposervicio", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerTiposervicio(@RequestBody DtoComunProveedormast pk) throws Exception {
		logger.debug("obtenertiposervicio");
		DtoComunProveedormast dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getTiposervicio(),HttpStatus.OK);
	}
	
	public DtoComunProveedormast obtenerDtoCore(DtoComunProveedormast pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proveedor", Integer.class, pk.getProveedor()));
		List datos = this.listarPorQuery(DtoComunProveedormast.class, "proveedormast.obtenerDto",parametros);
		DtoComunProveedormast dto;
		if (datos.size()==1) {
			dto = (DtoComunProveedormast)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunProveedormast();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunProveedormast>> listarDtoFiltros(@RequestBody DtoComunProveedormast filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getProveedor()))
			filtro.setProveedor(null);		
		if (UString.esNuloVacio(filtro.getBusqueda()))
			filtro.setBusqueda(null);
		else
			filtro.setBusqueda(filtro.getBusqueda().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_proveedor", BigDecimal.class, filtro.getProveedor()));
        parametros.add(new DominioParametroPersistencia("p_busqueda", String.class, filtro.getBusqueda()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunProveedormast.class, "proveedormast.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunProveedormast>>(datos, HttpStatus.OK);
	}
	
	
	
	/*********************** mantenimiento *********************/
	
	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoProveedormast> validar(@Validated @PathVariable String accion, @RequestBody DtoProveedormast dto) throws Exception {
		logger.debug("ProveedormastRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoProveedormast>(dto, HttpStatus.OK);
	}*/

	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunProveedormast> obtenerDto(@RequestBody DtoComunProveedormast pk) throws Exception {
		logger.debug("ProveedormastRest.obtenerDto");
		DtoComunProveedormast dto = proveedormastDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunProveedormast>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunProveedormast> registrar(@RequestBody DtoComunProveedormast dto) throws Exception {
		logger.debug("ProveedormastRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunProveedormast>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunProveedormast> actualizar(@RequestBody DtoComunProveedormast dto) throws Exception {
		logger.debug("ProveedormastRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunProveedormast>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunProveedormast> eliminar(@RequestBody DtoComunProveedormast dto) throws Exception {
		logger.debug("ProveedormastRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunProveedormast>(dto,HttpStatus.OK);
	}

	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("ProveedormastRest.listar");
		// TODO ProveedormastRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "proveedormast.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("ProveedormastRest.listaractivos");
		// TODO ProveedormastRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "proveedormast.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunProveedormast>> listarDtoFiltros(@RequestBody DtoComunProveedormast filtro) throws Exception {
		logger.debug("ProveedormastRest.listardtofiltros");
        List datos = proveedormastDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunProveedormast>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunProveedormast>> listarDtoActivos(@RequestBody DtoComunProveedormast filtro) throws Exception {
		logger.debug("ProveedormastRest.listardtoactivos");
		List datos = proveedormastDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunProveedormast>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunProveedormast filtro) throws Exception {
		logger.debug("ProveedormastRest.listarPaginado");
		DominioPaginacion paginacion = proveedormastDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}*/
}
