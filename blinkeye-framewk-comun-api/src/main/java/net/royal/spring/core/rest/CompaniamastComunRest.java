package net.royal.spring.core.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dao.impl.CompaniamastDaoImpl;
import net.royal.spring.core.dominio.dto.DtoComunCompaniamast;
import net.royal.spring.core.dominio.filtro.FiltroComunCompaniamast;
import net.royal.spring.core.servicio.impl.CompaniamastServicioImpl;
import net.royal.spring.core.servicio.validar.CompaniamastServicioValidar;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/companiamastcomun")
@CrossOrigin(origins = "*")
public class CompaniamastComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(CompaniamastComunRest.class);

	@Autowired
	private CompaniamastServicioImpl servicio;

	@Autowired
	private CompaniamastServicioValidar validar;

	@Autowired
	private CompaniamastDaoImpl companiamastDao;
	
	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public CompaniamastComunRest() {
		super("companiamast");
	}
	
	
//	@ApiOperation(notes = "Listar dto. Retorno: List DtoTabla",					
//			nickname="COMPANIAMAST_CLIST", value = "Listar dto", tags = {"COMPANIAMAST", "LISTAR"})
//	@Transactional
//	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
//	public ResponseEntity<List<DtoTabla>> listar() {	        	            
//		logger.debug("listar");
//		List datos = this.listarPorQuery(DtoTabla.class, "companiamast.listar");
//		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
//	}

//	@ApiOperation(notes = "Listar dto activos. Retorno: List DtoTabla",					
//			nickname="COMPANIAMAST_CLISTACT", value = "Listar dto activos", tags = {"COMPANIAMAST", "LISTAR"})
//	@Transactional
//	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
//	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
//		logger.debug("listaractivos");
//		List datos = this.listarPorQuery(DtoTabla.class, "companiamast.listarActivos");
//		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
//	}
	
	@ApiOperation(notes = "Obtener tabla. Retorno: DtoTabla",					
			nickname="COMPANIAMAST_COBT", value = "Listar dto", tags = {"COMPANIAMAST", "OBTENER"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "companiamast.obtenerTabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	@ApiOperation(notes = "Listar por filtros. Entrada: DtoTabla Retorno: List DtoTabla",					
			nickname="COMPANIAMAST_CLISTFIL", value = "Listar por filtros", tags = {"COMPANIAMAST", "LISTAR"})
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
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcioncorta", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "companiamast.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
//	@ApiOperation(notes = "Obtener dto. Entrada: DtoComunCompaniamast. Retorno: DtoComunCompaniamast",					
//			nickname="COMPANIAMAST_COBTDTO", value = "Obtener dto", tags = {"COMPANIAMAST", "OBTENER"})
//	@Transactional
//	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<DtoComunCompaniamast> obtenerDto(@RequestBody DtoComunCompaniamast pk) throws Exception {
//		logger.debug("obtenerdto");
//		DtoComunCompaniamast dto = obtenerDtoCore(pk);
//		return new ResponseEntity<DtoComunCompaniamast>(dto,HttpStatus.OK);
//	}
	
	@ApiOperation(notes = "Obtener plan contable. Entrada: DtoComunCompaniamast. Retorno: DtoComunCompaniamast",					
			nickname="COMPANIAMAST_COBTPLCON", value = "Obtener plan contable", tags = {"COMPANIAMAST", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerplancontable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerPlancontable(@RequestBody DtoComunCompaniamast pk) throws Exception {
		logger.debug("obtenerplancontable");
		DtoComunCompaniamast dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getPlancontable(),HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/obtenerDtoCore", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoComunCompaniamast obtenerDtoCore(@RequestBody DtoComunCompaniamast pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, pk.getCompaniacodigo()));
		List datos = this.listarPorQuery(DtoComunCompaniamast.class, "companiamast.obtenerDto",parametros);
		DtoComunCompaniamast dto;
		if (datos.size()==1) {
			dto = (DtoComunCompaniamast)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunCompaniamast();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	@ApiOperation(notes = "Listar dto por filtros. Entrada: DtoComunCompaniamast. Retorno: List DtoComunCompaniamast",					
			nickname="COMPANIAMAST_COBTPLCON", value = "Listar dto por filtros", tags = {"COMPANIAMAST", "LISTAR"})
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunCompaniamast>> listarDtoFiltros(@RequestBody DtoComunCompaniamast filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getCompaniacodigo()))
			filtro.setCompaniacodigo(null);		
		if (UString.esNuloVacio(filtro.getDescripcioncorta()))
			filtro.setDescripcioncorta(null);
		else
			filtro.setDescripcioncorta(filtro.getDescripcioncorta().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, filtro.getCompaniacodigo()));
        parametros.add(new DominioParametroPersistencia("p_descripcioncorta", String.class, filtro.getDescripcioncorta()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunCompaniamast.class, "companiamast.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunCompaniamast>>(datos, HttpStatus.OK);
	}
	
	
	
	/*MANTENIMIENTO*/
	
	
	
	
	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCompaniamast> validar(@Validated @PathVariable String accion, @RequestBody DtoComunCompaniamast dto) throws Exception {
		logger.debug("CompaniamastRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunCompaniamast>(dto, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * OBTIENE OBJETO COMPANIAMAST
	 * */
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCompaniamast> obtenerDto(@RequestBody DtoComunCompaniamast pk) throws Exception {
		logger.debug("CompaniamastRest.obtenerDto");
		DtoComunCompaniamast dto = companiamastDao.obtenerDtoPorUuid(pk.getUuid());
		return new ResponseEntity<DtoComunCompaniamast>(dto,HttpStatus.OK);
	}


	/*
	 * LEONARDO
	 * REGISTRA COMPANIAMAST, COMPANYOWNER y REPORTINGCOMPANY
	 * */
	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCompaniamast> registrar(@RequestBody DtoComunCompaniamast dto) throws Exception {
		logger.debug("CompaniamastRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunCompaniamast>(dto, HttpStatus.CREATED);
	}

	/*
	 * LEONARDO
	 * ACTUALIZA COMPANIAMAST, COMPANYOWNER y REPORTINGCOMPANY
	 * */
	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCompaniamast> actualizar(@RequestBody DtoComunCompaniamast dto) throws Exception {
		logger.debug("CompaniamastRest.actualizar");
		dto = servicio.coreActualizarPorUuid(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunCompaniamast>(dto, HttpStatus.OK);
	}

	/*@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCompaniamast> anular(@RequestBody DtoComunCompaniamast dto) throws Exception {
		logger.debug("CompaniamastRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunCompaniamast>(dto, HttpStatus.OK);
	}*/

	
	/*
	 * LEONARDO
	 * ELIMINA COMPANIAMAST, COMPANYOWNER y REPORTINGCOMPANY
	 * */
	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCompaniamast> eliminar(@RequestBody DtoComunCompaniamast dto) throws Exception {
		logger.debug("CompaniamastRest.eliminar");
		dto = servicio.coreEliminarPorUuid(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunCompaniamast>(dto,HttpStatus.OK);
	}

	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("CompaniamastRest.listar");
		// TODO CompaniamastRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "companiamast.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("CompaniamastRest.listaractivos");
		// TODO CompaniamastRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "companiamast.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

 

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunCompaniamast>> listarDtoActivos(@RequestBody DtoComunCompaniamast filtro) throws Exception {
		logger.debug("CompaniamastRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = companiamastDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunCompaniamast>>(datos, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * LISTADO PAGINADO GENERAL
	 * 
	 * */
	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunCompaniamast filtro) throws Exception {
		logger.debug("CompaniamastRest.listarPaginado");
		DominioPaginacion paginacion = companiamastDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	/*
	 * LEONARDO
	 * SUBIDA DE LOGO(PNG)
	 * */
	@Transactional
	@PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> nuevoupload(@RequestParam("logo") MultipartFile logo, @RequestParam("logoanterior") String logoanterior) throws IOException{
		Map<String, Object> response = new HashMap<>();

			try {
				servicio.registrarImagen(logo,logoanterior);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir el logo");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause() != null ?  e.getCause().getMessage() : " Sin Causa"));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	/*
	 * LEONARDO
	 * BORRADO DE LOGO(PNG)
	 * */
	@Transactional
	@PostMapping(value = "/delete_upload", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete_upload( @RequestParam("logoanterior") String logoanterior) throws IOException{
		Map<String, Object> response = new HashMap<>();
			servicio.eliminarImagen(logoanterior);	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	/*
	 * LEONARDO
	 * VALIDADOR DE LOGO(PNG)
	 * */
	@Transactional //VALIDAR LOGO COMPANY
	@GetMapping(value = "/logo_compania/{logo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> logo_compania(@PathVariable String logo ) {
		logger.debug("CompaniamastRest.logo_compania");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_logo", String.class, (UString.estaVacio(logo) ? null : logo.trim())));
		List datos = this.listarPorQuery(DtoTabla.class, "companiamast.logo_compania",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/*
	 * LEONARDO
	 * EXPORTAR LISTA COMPANIAMAST
	 * */
	@Transactional
	@PostMapping(value = "/exportarCompanias", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarCompanias(HttpServletResponse response, @RequestBody FiltroComunCompaniamast filtro) throws Exception
	 {
		
		DominioPaginacion paginacion = companiamastDao.exportarCompanias(filtro);
		String[] arrCabecera = new String[] {"Compa\u00F1\u00EDa","Descripci\u00f3n","Estado"};
		String[] arrColumnas = new String[] {"companiacodigo","descripcioncorta","estadodescripcion"}; 
		

		DtoWhExportar dtoExportar=new DtoWhExportar();
		dtoExportar.setTitulo("Listado de Compa\u00F1\u00EDas");
		dtoExportar.setTipoExpotar(filtro.getTipoexportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setPaginacion(paginacion);
			
		servicio.exportarInformacionWh(response,dtoExportar);
	}
}
