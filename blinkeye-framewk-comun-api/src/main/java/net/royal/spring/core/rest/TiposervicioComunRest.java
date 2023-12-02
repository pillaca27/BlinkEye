package net.royal.spring.core.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dao.impl.TiposervicioDaoImpl;
import net.royal.spring.core.dominio.dto.DtoComunTipopago;
import net.royal.spring.core.dominio.dto.DtoComunTiposervicio;
import net.royal.spring.core.dominio.filtro.FiltroComunTiposervicio;
import net.royal.spring.core.servicio.impl.TiposervicioServicioImpl;
import net.royal.spring.core.servicio.validar.TiposervicioServicioValidar;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/tiposerviciocomun")
@CrossOrigin(origins = "*")
public class TiposervicioComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(TiposervicioComunRest.class);

	@Autowired
	private TiposervicioServicioImpl servicio;

	@Autowired
	private TiposervicioServicioValidar validar;

	@Autowired
	private TiposervicioDaoImpl tiposervicioDao;
	
	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public TiposervicioComunRest() {
		super("tiposervicio");
	}
	
	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar");
		List datos = this.listarPorQuery(DtoTabla.class, "tiposervicio.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "tiposervicio.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener tabla. Entrada: DtoTabla: codigo. Retorno: DtoTabla",					
			nickname="TIPOSERVICIO_COBT", value = "Obtener tabla", tags = {"TIPOSERVICIO", "OBTENER"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "tiposervicio.obtenerTabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	@Transactional
	@PutMapping(value = "/listarfiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarFiltros(@RequestBody DtoTabla filtro) {
		logger.debug("listar Filtros");
		if (UString.esNuloVacio(filtro.getCodigo()))
			filtro.setCodigo(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);
		else
			filtro.setNombre(filtro.getNombre().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstadoId()))
			filtro.setEstadoId(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();		
		parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "tiposervicio.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener dto. Entrada: DtoComunTiposervicio: tiposervicio. Retorno: DtoComunTiposervicio",					
			nickname="TIPOSERVICIO_COBTDTO", value = "Obtener dto", tags = {"TIPOSERVICIO", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTiposervicio> obtenerDto(@RequestBody DtoComunTiposervicio pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, pk.getTiposervicio()));
		List datos = this.listarPorQuery(DtoComunTiposervicio.class, "tiposervicio.obtenerDto",parametros);
		DtoComunTiposervicio dto;
		if (datos.size()==1) {
			dto = (DtoComunTiposervicio)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunTiposervicio();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunTiposervicio>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunTiposervicio>> listarDtoFiltros(@RequestBody DtoComunTiposervicio filtro) {
		logger.debug("listardtofiltros");
        List datos = listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunTiposervicio>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@GetMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunTiposervicio>> listarDtoActivos() {
		logger.debug("listardtoactivos");
		DtoComunTiposervicio filtro=new DtoComunTiposervicio();
		filtro.setEstado("A");
        List datos = listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunTiposervicio>>(datos, HttpStatus.OK);
	}
	
	public List<DtoComunTiposervicio> listarDtoCore(DtoComunTiposervicio filtro) {
		if (UString.esNuloVacio(filtro.getTiposervicio()))
			filtro.setTiposervicio(null);		
		if (UString.esNuloVacio(filtro.getDescripcion()))
			filtro.setDescripcion(null);
		else
			filtro.setDescripcion(filtro.getDescripcion().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, filtro.getTiposervicio()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunTiposervicio.class, "tiposervicio.listardtofiltros", parametros);
        logger.debug(datos.size());
		return datos;
	}
	
	@Transactional(readOnly = true)
	@PutMapping(value = "/listardtoactivosporregimenfiscal", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunTiposervicio>> listarDtoActivosPorRegimenfiscal(@RequestBody DtoComunTiposervicio filtro) {
		logger.debug("listardtofiltros");
        List datos = listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunTiposervicio>>(datos, HttpStatus.OK);
	}
	public List<DtoComunTiposervicio> listarDtoPorRegimenfiscal(DtoComunTiposervicio filtro) {
		if (UString.esNuloVacio(filtro.getRegimenfiscal()))
			filtro.setRegimenfiscal(null);		
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_regimenfiscal", String.class, filtro.getRegimenfiscal()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunTiposervicio.class, "tiposervicio.listarDtoPorRegimenfiscal", parametros);
        logger.debug(datos.size());
		return datos;
	}*/
	
/*MANTENIMIENTO LEONARDO*/
	
	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTiposervicio> validar(@Validated @PathVariable String accion, @RequestBody DtoComunTiposervicio dto) throws Exception {
		logger.debug("TiposervicioRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunTiposervicio>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTiposervicio> obtenerDto(@RequestBody DtoComunTiposervicio pk) throws Exception {
		logger.debug("TiposervicioRest.obtenerDto");
		DtoComunTiposervicio dto = tiposervicioDao.obtenerDtoPorUuid(pk.getUuid());
		return new ResponseEntity<DtoComunTiposervicio>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTiposervicio> registrar(@RequestBody DtoComunTiposervicio dto) throws Exception {
		logger.debug("TiposervicioRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunTiposervicio>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTiposervicio> actualizar(@RequestBody DtoComunTiposervicio dto) throws Exception {
		logger.debug("TiposervicioRest.actualizar");
		dto = servicio.coreActualizarPorUuid(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunTiposervicio>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTiposervicio> anular(@RequestBody DtoComunTiposervicio dto) throws Exception {
		logger.debug("TiposervicioRest.anular");
		dto = servicio.coreAnularPorUuid(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunTiposervicio>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTiposervicio> eliminar(@RequestBody DtoComunTiposervicio dto) throws Exception {
		logger.debug("TiposervicioRest.eliminar");
		dto = servicio.coreEliminarPorUuid(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunTiposervicio>(dto,HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("TiposervicioRest.listar");
		// TODO TiposervicioRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "tiposervicio.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("TiposervicioRest.listaractivos");
		// TODO TiposervicioRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "tiposervicio.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunTiposervicio>> listarDtoFiltros(@RequestBody DtoComunTiposervicio filtro) throws Exception {
		logger.debug("TiposervicioRest.listardtofiltros");
        List datos = tiposervicioDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunTiposervicio>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunTiposervicio>> listarDtoActivos(@RequestBody DtoComunTiposervicio filtro) throws Exception {
		logger.debug("TiposervicioRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = tiposervicioDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunTiposervicio>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunTiposervicio filtro) throws Exception {
		logger.debug("TiposervicioRest.listarPaginado");
		DominioPaginacion paginacion = tiposervicioDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/exportarTipoServicio", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarTipoServicio(HttpServletResponse response, @RequestBody FiltroComunTiposervicio filtro) throws Exception
	 {
		
		DominioPaginacion paginacion = tiposervicioDao.listarPaginado(this.getUsuarioActual(), filtro);  // tiposervicioDao.exportarTipoServicio(filtro);
		String[] arrCabecera = new String[] {"Tipo de Servicio","Descripci\u00f3n","Estado"};
		String[] arrColumnas = new String[] {"tiposervicio","descripcion","estadodescripcion"}; 
		

		DtoWhExportar dtoExportar=new DtoWhExportar();
		dtoExportar.setTitulo("Listado de Tipos de Servicio");
		dtoExportar.setTipoExpotar(filtro.getTipoexportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setPaginacion(paginacion);
			
		servicio.exportarInformacionWh(response,dtoExportar);
	}
}
