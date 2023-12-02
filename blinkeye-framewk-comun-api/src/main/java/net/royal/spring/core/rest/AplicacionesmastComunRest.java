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
import net.royal.spring.core.dao.impl.AplicacionesmastDaoImpl;
import net.royal.spring.core.dominio.BeanAplicacionesmast;
import net.royal.spring.core.dominio.dto.DtoComunAplicacionesmast;
import net.royal.spring.core.dominio.filtro.FiltroComunAplicacionesmast;
import net.royal.spring.core.servicio.impl.AplicacionesmastServicioImpl;
import net.royal.spring.core.servicio.validar.AplicacionesmastServicioValidar;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/aplicacionesmastcomun")
@CrossOrigin(origins = "*")
public class AplicacionesmastComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(AplicacionesmastComunRest.class);

	@Autowired
	private AplicacionesmastServicioImpl servicio;

	@Autowired
	private AplicacionesmastServicioValidar validar;

	@Autowired
	private AplicacionesmastDaoImpl aplicacionesmastDao;
	

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AplicacionesmastComunRest() {
		super("aplicacionesmast");
	}
	
	/*@Transactional
	@PutMapping(value = "/comparar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoBdComparar> comparar(@RequestBody DtoBdComparar dto) throws Exception {
		logger.debug("AplicacionesmastRest.comparar");
		dto = aplicacionesmastDao.comparar(dto);
		
		return new ResponseEntity<DtoBdComparar>(dto, HttpStatus.OK); 
	}
	
	@Transactional
	@PutMapping(value = "/ejecutar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoBdEjecutar> ejecutar(@RequestBody DtoBdEjecutar dto) throws Exception {
		logger.debug("AplicacionesmastRest.ejecutar");
		dto = aplicacionesmastDao.ejecutar(dto);
		return new ResponseEntity<DtoBdEjecutar>(dto, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAplicacionesmast> validar(@Validated @PathVariable String accion, @RequestBody DtoComunAplicacionesmast dto) throws Exception {
		logger.debug("AplicacionesmastRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunAplicacionesmast>(dto, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * OBTIENE UN OBJETO APLICACIONESMAST POR APLICACIONCODIGO
	 * 
	 * */
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAplicacionesmast> obtenerDto(@RequestBody DtoComunAplicacionesmast pk) throws Exception {
		logger.debug("AplicacionesmastRest.obtenerDto");
		DtoComunAplicacionesmast dto = aplicacionesmastDao.obtenerDtoPorUuid(pk.getUuid());
		return new ResponseEntity<DtoComunAplicacionesmast>(dto,HttpStatus.OK);
	}

	/*
	 * LEONARDO
	 * REGISTRA UN OBJETO APLICACIONESMAST
	 * 
	 * */
	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAplicacionesmast> registrar(@RequestBody DtoComunAplicacionesmast dto) throws Exception {
		logger.debug("AplicacionesmastRest.registrar");
		System.out.println(""+dto.getCodigocontablevalid());
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunAplicacionesmast>(dto, HttpStatus.CREATED);
	}
	
	/*
	 * LEONARDO
	 * ACTUALIZA OBJETO APLICACIONESMAST
	 * 
	 * */
	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAplicacionesmast> actualizar(@RequestBody DtoComunAplicacionesmast dto) throws Exception {
		logger.debug("AplicacionesmastRest.actualizar");
		dto = servicio.coreActualizarPorUuid(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunAplicacionesmast>(dto, HttpStatus.OK);
	}

	/*
	 * LEONARDO
	 * ANULA UNA APLICACIONMAST - CAMBIA ESTADO INACTIVO (I)
	 * 
	 * */
	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAplicacionesmast> anular(@RequestBody DtoComunAplicacionesmast dto) throws Exception {
		logger.debug("AplicacionesmastRest.anular");
		dto = servicio.coreAnularPorUuid(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunAplicacionesmast>(dto, HttpStatus.OK);
	}

	/*
	 * LEONARDO
	 * ELIMINA UNA APLICACIONMAST
	 * 
	 * */
	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAplicacionesmast> eliminar(@RequestBody DtoComunAplicacionesmast dto) throws Exception {
		logger.debug("AplicacionesmastRest.eliminar");
		dto = servicio.coreEliminarPorUuid(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunAplicacionesmast>(dto,HttpStatus.OK);
	}


	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAplicacionesmast>> listarDtoFiltros(@RequestBody DtoComunAplicacionesmast filtro) throws Exception {
		logger.debug("AplicacionesmastRest.listardtofiltros");
        List datos = aplicacionesmastDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunAplicacionesmast>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAplicacionesmast>> listarDtoActivos(@RequestBody DtoComunAplicacionesmast filtro) throws Exception {
		logger.debug("AplicacionesmastRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = aplicacionesmastDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunAplicacionesmast>>(datos, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * LISTADO PAGINADO GENERAL
	 *
	 * */
	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunAplicacionesmast filtro) throws Exception {
		logger.debug("AplicacionesmastRest.listarPaginado");
		DominioPaginacion paginacion = aplicacionesmastDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}
	
	/*
	 * LEONARDO
	 * LISTADO DEPARTAMENTOS
	 * 
	 * */
	@Transactional
	@GetMapping(value = "/listardepartamentos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listardepartamentos() {	        	            
		logger.debug("listardepartamentos");
		List datos = this.listarPorQuery(DtoTabla.class, "aplicacionesmast.listardepartamentos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/*
	 * LEONARDO
	 * LISTADO SISTEMA FUENTE 
	 * 
	 * */
	@Transactional
	@GetMapping(value = "/listarsistemafuente", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarsistemafuente() {	        	            
		logger.debug("listarsistemafuente");
		List datos = this.listarPorQuery(DtoTabla.class, "aplicacionesmast.listarsistemafuente");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/*
	 * LEONARDO 
	 * EXPORTAR APLICACIONESMAST
	 * */
	@Transactional
	@PostMapping(value = "/exportarAplicaciones", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarAplicaciones(HttpServletResponse response, @RequestBody FiltroComunAplicacionesmast filtro) throws Exception
	 {
		
		DominioPaginacion paginacion = aplicacionesmastDao.exportarAplicaciones(filtro);
		String[] arrColumnas = new String[] { "aplicacioncodigo", "descripcioncorta", "ultimoperiodocontable", "aplicacionusuario","estadodescripcion"
		}; 
		
		String[] arrCabecera = new String[] {"Aplicaci\u00f3n","Descripci\u00f3n Local","Periodo","Prefijo Voucher","Estado"};
		

		DtoWhExportar dtoExportar=new DtoWhExportar();
		dtoExportar.setTitulo("Listado de Aplicaciones");
		dtoExportar.setTipoExpotar(filtro.getTipoexportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setPaginacion(paginacion);
			
		servicio.exportarInformacionWh( response,dtoExportar);
	}
	
	
	
	/*COPIADO DEL COMUN*/
	
	
	/**
	 * QQUECHOD VALIDADO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Aplicaciones | Sin parametros de entrada | Parametros de salida: codigo, nombre, descripcion (Nacionalidad)", 
			value = "SG-APLICA-CLISTA", tags= {"CORE", "APLICACIONES"})	
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() throws Exception {				
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		List datos = this.listarPorQuery(DtoTabla.class, "aplicacionesmast.listar", parametros);		
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Aplicaciones Activas | Sin parametros de entrada | Parametros de salida: codigo, nombre, descripcion (Nacionalidad)", 
			value = "SG-APLICA-CLIACT", tags= {"CORE", "APLICACIONES"})	
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaractivos() throws Exception {		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		List datos = this.listarPorQuery(DtoTabla.class, "aplicacionesmast.listaractivos", parametros);		
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "aplicacionesmast.obtenerTabla", parametros);
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
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcioncorta", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "aplicacionesmast.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/*@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAplicacionesmast> obtenerDto(@RequestBody DtoComunAplicacionesmast pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunAplicacionesmast dto = obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunAplicacionesmast>(dto,HttpStatus.OK);
	}*/
	
	@Transactional
	@PutMapping(value="/obtenerultimoperiodocontable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerUltimoperiodocontable(@RequestBody DtoComunAplicacionesmast pk) throws Exception {
		logger.debug("obtenerultimoperiodocontable");
		DtoComunAplicacionesmast dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getUltimoperiodocontable(),HttpStatus.OK);
	}
	
	public DtoComunAplicacionesmast obtenerDtoCore(DtoComunAplicacionesmast pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, pk.getAplicacioncodigo()));
		List datos = this.listarPorQuery(DtoComunAplicacionesmast.class, "aplicacionesmast.obtenerDtoCore",parametros);
		DtoComunAplicacionesmast dto;
		if (datos.size()==1) {
			dto = (DtoComunAplicacionesmast)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunAplicacionesmast();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAplicacionesmast>> listarDtoFiltros(@RequestBody DtoComunAplicacionesmast filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);		
		if (UString.esNuloVacio(filtro.getDescripcioncorta()))
			filtro.setDescripcioncorta(null);
		else
			filtro.setDescripcioncorta(filtro.getDescripcioncorta().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
        parametros.add(new DominioParametroPersistencia("p_descripcioncorta", String.class, filtro.getDescripcioncorta()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunAplicacionesmast.class, "aplicacionesmast.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunAplicacionesmast>>(datos, HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Aplicaciones por usuario | Sin parametros de entrada | Parametros de salida: codigo, nombre, descripcion (Nacionalidad)", 
			value = "SG-APLICA-C0001", tags= {"CORE", "APLICACIONES"})	
	@Transactional
	@GetMapping(value = "/listaraplicacionporusuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarAplicacionPorUsuario() throws Exception {		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		List datos = this.listarPorQuery(DtoTabla.class, "aplicacionesmast.listarAplicacionPorUsuario", parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional //  APLICACION
	@GetMapping(value = "/gv_period_voucher/{aplicacion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> gv_period_voucher (@PathVariable String aplicacion) {
		logger.debug("MaPersonagrupoRest.gv_period_voucher");
		BeanAplicacionesmast b =aplicacionesmastDao.obtenerPorId(aplicacion); 
		String w_periodo = b.getUltimoperiodocontable();
		return new ResponseEntity<String>(w_periodo, HttpStatus.OK);
	}
	
}
