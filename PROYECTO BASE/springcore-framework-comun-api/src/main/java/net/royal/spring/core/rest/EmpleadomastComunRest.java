package net.royal.spring.core.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.dao.impl.EmpleadomastDaoImpl;
import net.royal.spring.core.dominio.dto.DtoComunEmpleado;
import net.royal.spring.core.dominio.dto.DtoComunEmpleadomast;
import net.royal.spring.core.dominio.filtro.FiltroComunEmpleado;
import net.royal.spring.core.dominio.filtro.FiltroComunEmpleadomast;
import net.royal.spring.core.dominio.filtro.FiltroComunEmpleadomastListaRapida;
import net.royal.spring.core.servicio.impl.EmpleadomastServicioImpl;
import net.royal.spring.core.servicio.validar.EmpleadomastServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.EmpleadomastTransaccion;
import net.royal.spring.framework.modelo.generico.DominioArchivo;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/empleadomastcomun")
@CrossOrigin(origins = "*")
public class EmpleadomastComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(EmpleadomastComunRest.class);

	@Autowired
	private EmpleadomastServicioImpl servicio;

	@Autowired
	private EmpleadomastServicioValidar validar;
	
	@Autowired
	private EmpleadomastDaoImpl empleadomastDao;
	
	@Autowired
	private CorrelativosmastDaoImpl dao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public EmpleadomastComunRest() {
		super("empleadomast");
	}

	
	
	
	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunEmpleadomast> validar(@Validated @PathVariable String accion, @RequestBody DtoComunEmpleadomast dto) throws Exception {
		logger.debug("EmpleadomastRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunEmpleadomast>(dto, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * OBTIENE OBJETO EMPLEADOMAST
	 * 
	 * */
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunEmpleadomast> obtenerDto(@RequestBody DtoComunEmpleadomast pk) throws Exception {
		logger.debug("EmpleadomastRest.obtenerDto");
		DtoComunEmpleadomast dto = empleadomastDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunEmpleadomast>(dto,HttpStatus.OK);
	}

	/*
	 * LEONARDO 
	 * REGISTRA UN EMPLEADOMAST
	 * 
	 * */
	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunEmpleadomast> registrar(@RequestBody DtoComunEmpleadomast dto) throws Exception {
		logger.debug("EmpleadomastRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunEmpleadomast>(dto, HttpStatus.CREATED);
	}

	/*
	 * LEONARDO 
	 * ACTUALIZA UN EMPLEADOMAST
	 * 
	 * */
	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunEmpleadomast> actualizar(@RequestBody DtoComunEmpleadomast dto) throws Exception {
		logger.debug("EmpleadomastRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunEmpleadomast>(dto, HttpStatus.OK);
	}

	/*@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunEmpleadomast> anular(@RequestBody DtoComunEmpleadomast dto) throws Exception {
		logger.debug("EmpleadomastRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunEmpleadomast>(dto, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO 
	 * ELIMINA UN EMPLEADOMAST
	 * 
	 * */
	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunEmpleadomast> eliminar(@RequestBody DtoComunEmpleadomast dto) throws Exception {
		logger.debug("EmpleadomastRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunEmpleadomast>(dto,HttpStatus.OK);
	}

	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("EmpleadomastRest.listar");
		// TODO EmpleadomastRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "empleadomast.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("EmpleadomastRest.listaractivos");
		// TODO EmpleadomastRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "empleadomast.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunEmpleadomast>> listarDtoFiltros(@RequestBody DtoComunEmpleadomast filtro) throws Exception {
		logger.debug("EmpleadomastRest.listardtofiltros");
        List datos = empleadomastDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunEmpleadomast>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunEmpleadomast>> listarDtoActivos(@RequestBody DtoComunEmpleadomast filtro) throws Exception {
		logger.debug("EmpleadomastRest.listardtoactivos");
		filtro.setEstadonivelacion("A");
		List datos = empleadomastDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunEmpleadomast>>(datos, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * LISTADO PAGINADO GENERAL
	 * */
	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunEmpleadomast filtro) throws Exception {
		logger.debug("EmpleadomastRest.listarPaginado");
		DominioPaginacion paginacion = empleadomastDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*COPIADO DEL COMUN EMPLEADOMAST*/
	
	@Transactional
	@GetMapping(value = "/obtenerfotoactual", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioArchivo> obtenerFotoActual() {
		logger.debug("empleadomastcomun.obtenerfotoactual");
		DominioArchivo dto = new DominioArchivo();
		return new ResponseEntity<DominioArchivo>(dto, HttpStatus.OK);
	}

	/**
	 * QQUECHOD VALIDADO
	 * 
	 * @param pTran
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Personamast | Parametros de entrada: PersonamastFiltroPaginado ( persona, busqueda,"
			+ "documento, documentofiscal, documentoidentidad, estado  )  | Parametros de salida: persona, busqueda, escliente, esproveedor, esempleado, esotro, documento, documentofiscal, documentoidentidad, estado, centroCosto, descripcioncentroCosto, unidad, descripcionunidad, departamento, descripciondepartamento, sucursal", value = "99-EMPLEA-COBTAPI", tags = {
					"CORE", "PERSONA" })
	@Transactional
	@PutMapping(value = "/obtenerempleadopordtoapi", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmpleadomastTransaccion> obtenerEmpleadoPorDtoApi(@RequestBody EmpleadomastTransaccion pTran) {
		EmpleadomastTransaccion bean = obtenerTransaccionPorEmpleadoCompaniaCore(pTran);
		return new ResponseEntity<EmpleadomastTransaccion>(bean, HttpStatus.OK);
	}
	@Transactional
	@PutMapping(value = "/obtenertransaccionporempleadocompania", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmpleadomastTransaccion> obtenerTransaccionPorEmpleadoCompania(@RequestBody EmpleadomastTransaccion pTran) {
		EmpleadomastTransaccion bean = obtenerTransaccionPorEmpleadoCompaniaCore(pTran);
		return new ResponseEntity<EmpleadomastTransaccion>(bean, HttpStatus.OK);
	}
	public EmpleadomastTransaccion obtenerTransaccionPorEmpleadoCompaniaCore(EmpleadomastTransaccion pTran) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_id_persona", Integer.class, pTran.getEmpleado()));
		parametros.add(new DominioParametroPersistencia("p_id_compania", String.class, pTran.getCompaniasocio()));
		try {
			List datos = this.listarPorQuery(EmpleadomastTransaccion.class, "empleadomast.obtenerempleadopordtoapi",
					parametros);
			if (datos == null) {
				pTran.setTransaccionEstado(DominioTransaccion.ERROR);
				pTran.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
			} else {
				pTran = (EmpleadomastTransaccion) datos.get(0);
				pTran.setTransaccionEstado(DominioTransaccion.OK);
			}
		} catch (Exception e) {
			pTran.setTransaccionEstado(DominioTransaccion.ERROR);
			pTran.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, e.getMessage(),UException.getStackTrace(e)));
			e.printStackTrace();
		}
		return pTran;
	}

	/**
	 * QQUECHOD VALIDADO
	 * 
	 * @param pTran
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Personamast | Parametros de entrada: PersonamastFiltroPaginado ( persona, busqueda,"
			+ "documento, documentofiscal, documentoidentidad, estado  )  | Parametros de salida: persona, busqueda, escliente, esproveedor, esempleado, esotro, documento, documentofiscal, documentoidentidad, estado, centroCosto, descripcioncentroCosto, unidad, descripcionunidad, departamento, descripciondepartamento, sucursal", value = "99-EMPLEA-COBAPPE", tags = {
					"CORE", "PERSONA" })
	@Transactional
	@PutMapping(value = "/obtenerempleadopordtoapipersona", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmpleadomastTransaccion> obtenerEmpleadoPorDtoApiPersona(@RequestBody EmpleadomastTransaccion pTran) {
		EmpleadomastTransaccion bean = obtenerTransaccionPorEmpleadoCore(pTran);
		return new ResponseEntity<EmpleadomastTransaccion>(bean, HttpStatus.OK);
	}
	@Transactional
	@PutMapping(value = "/obtenertransaccionporempleado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmpleadomastTransaccion> obtenerTransaccionPorEmpleado(@RequestBody EmpleadomastTransaccion pTran) {
		EmpleadomastTransaccion bean = obtenerTransaccionPorEmpleadoCore(pTran);
		return new ResponseEntity<EmpleadomastTransaccion>(bean, HttpStatus.OK);
	}
	public EmpleadomastTransaccion obtenerTransaccionPorEmpleadoCore(EmpleadomastTransaccion pTran) {
		EmpleadomastTransaccion bean = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_id_persona", BigDecimal.class, pTran.getEmpleado()));
		try {
			List datos = this.listarPorQuery(EmpleadomastTransaccion.class,
					"empleadomast.obtenerEmpleadoPorDtoApiPersona", parametros);
			if (datos.size() == 0) {
				pTran.setTransaccionEstado(DominioTransaccion.ERROR);
				pTran.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
			} else {
				pTran = (EmpleadomastTransaccion) datos.get(0);
				pTran.setTransaccionEstado(DominioTransaccion.OK);
			}
		} catch (Exception e) {
			pTran.setTransaccionEstado(DominioTransaccion.ERROR);
			pTran.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, e.getMessage()));
			e.printStackTrace();
		}
		return pTran;
	}

	/**
	 * QQUECHOD VALIDADO
	 * 
	 * @param pTran
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de EmpleadoMast los primeros 30 | Parametros de entrada: FiltroComunEmpleadomastListaRapida "
			+ " | Parametros de salida: PersonaEmpleadoTransaccion", value = "99-EMPLEA-CLISRAP", tags = { "CORE",
					"EMPLEADO" })
	@Transactional
	@PutMapping(value = "/listarapida", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmpleadomastTransaccion>> listaRapida(
			@RequestBody FiltroComunEmpleadomastListaRapida pTran) {
		List<EmpleadomastTransaccion> lst = listaRapidaCore(pTran);
		return new ResponseEntity<List<EmpleadomastTransaccion>>(lst, HttpStatus.OK);
	}

	/**
	 * QQUECHOD VALIDADO
	 * 
	 * @param pTran
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de EmpleadoMast los primeros 30 | Parametros de entrada: FiltroComunEmpleadomastListaRapida "
			+ " | Parametros de salida: PersonaEmpleadoTransaccion", value = "99-EMPLEA-CLIRAAC", tags = { "CORE",
					"EMPLEADO" })
	@Transactional
	@PutMapping(value = "/listarapidaactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmpleadomastTransaccion>> listaRapidaActivos(
			@RequestBody FiltroComunEmpleadomastListaRapida pTran) {
		pTran.setEstadoempleado("0");
		List<EmpleadomastTransaccion> lst = listaRapidaCore(pTran);
		return new ResponseEntity<List<EmpleadomastTransaccion>>(lst, HttpStatus.OK);
	}

	public List<EmpleadomastTransaccion> listaRapidaCore(FiltroComunEmpleadomastListaRapida filtro) {
		if (UString.esNuloVacio(filtro.getBusqueda()))
			filtro.setBusqueda(null);
		if (UString.esNuloVacio(filtro.getEstadoempleado()))
			filtro.setEstadoempleado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompaniasocio()));
		parametros.add(new DominioParametroPersistencia("p_busqueda", String.class, filtro.getBusqueda()));
		parametros.add(new DominioParametroPersistencia("p_estadoempleado", String.class, filtro.getEstadoempleado()));
		List datos = this.listarPorQuery(EmpleadomastTransaccion.class, "empleadomast.listaRapidaCore", parametros);
		return datos;
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/listarcumpleanios", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarcumpleanios(@RequestBody FiltroComunEmpleado filtro) {
		if (UInteger.esCeroOrNulo(filtro.getEmpleado()))
			filtro.setEmpleado(null);
		if (UString.estaVacio(filtro.getCentrocosto())) 
			filtro.setCentrocosto(null);
		DominioPaginacion paginacion = filtro.getPaginacion();
		Integer cantidadEncontrados = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_empleado", Integer.class, filtro.getEmpleado()));
		parametros.add(new DominioParametroPersistencia("p_cc", String.class, filtro.getCentrocosto()));
		parametros.add(new DominioParametroPersistencia("p_desde", Date.class, filtro.getDesde()));
		parametros.add(new DominioParametroPersistencia("p_hasta", Date.class, filtro.getHasta()));
		cantidadEncontrados = contar("empleadomast.contarcumpleanios", parametros);
		List lstResultado = listarConPaginacion(paginacion, parametros, "empleadomast.listarcumpleanios",
				DtoComunEmpleado.class);
		paginacion.setPaginacionListaResultado(lstResultado);
		paginacion.setPaginacionRegistrosEncontrados(cantidadEncontrados);
		return new ResponseEntity<DominioPaginacion>(paginacion, HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/listaraniversarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listaraniversarios(@RequestBody FiltroComunEmpleado filtro) {
		if (UInteger.esCeroOrNulo(filtro.getEmpleado()))
			filtro.setEmpleado(null);
		if (UString.estaVacio(filtro.getCentrocosto())) 
			filtro.setCentrocosto(null);
		DominioPaginacion paginacion = filtro.getPaginacion();
		Integer cantidadEncontrados = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_empleado", Integer.class, filtro.getEmpleado()));
		parametros.add(new DominioParametroPersistencia("p_cc", String.class, filtro.getCentrocosto()));
		parametros.add(new DominioParametroPersistencia("p_desde", Date.class, filtro.getDesde()));
		parametros.add(new DominioParametroPersistencia("p_hasta", Date.class, filtro.getHasta()));
		cantidadEncontrados = contar("empleadomast.contaraniversarios", parametros);
		List lstResultado = listarConPaginacion(paginacion, parametros, "empleadomast.listaraniversarios",
				DtoComunEmpleado.class);
		paginacion.setPaginacionListaResultado(lstResultado);
		paginacion.setPaginacionRegistrosEncontrados(cantidadEncontrados);
		return new ResponseEntity<DominioPaginacion>(paginacion, HttpStatus.OK);
	}
	
	/*@Transactional
	@PutMapping(value = "/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunEmpleadomast> obtenerdto(@RequestBody DtoComunEmpleadomast pk) {				
		logger.debug("obtenerdto");
		pk = obtenerdtoCore(pk);
		return new ResponseEntity<DtoComunEmpleadomast>(pk,HttpStatus.OK);
	}*/
	
	public DtoComunEmpleadomast obtenerdtoCore(DtoComunEmpleadomast pTran) {				
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_empleado", Integer.class, pTran.getEmpleado()));
		try {
			List datos = this.listarPorQuery(DtoComunEmpleadomast.class,"empleadomast.obtenerdto", parametros);
			if (datos.size()!=1) {
				pTran.setTransaccionEstado(DominioTransaccion.ERROR);
				pTran.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
			}else {
				pTran = (DtoComunEmpleadomast) datos.get(0);
				pTran.setTransaccionEstado(DominioTransaccion.OK);
			}
		} catch (Exception e) {
			pTran.setTransaccionEstado(DominioTransaccion.ERROR);
			pTran.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, e.getMessage()));
			e.printStackTrace();
		}
		return pTran;
	}
	
	@Transactional
	@PutMapping(value = "/obtenertipoplanilla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerTipoplanilla(@RequestBody DtoComunEmpleadomast pk) {				
		logger.debug("obtenertipoplanilla");
		DtoComunEmpleadomast dto = obtenerdtoCore(pk);
		return new ResponseEntity<String>(dto.getTipoplanilla(),HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/obtenertipocontrato", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerTipocontrato(@RequestBody DtoComunEmpleadomast pk) {				
		logger.debug("obtenertipocontrato");
		DtoComunEmpleadomast dto = obtenerdtoCore(pk);
		return new ResponseEntity<String>(dto.getTipocontrato(),HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/obtenertipohorario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> obtenerTipohorario(@RequestBody DtoComunEmpleadomast pk) {				
		logger.debug("obtenertipohorario");
		DtoComunEmpleadomast dto = obtenerdtoCore(pk);
		return new ResponseEntity<Integer>(dto.getTipohorario(),HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/obtenercentrocostos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerCentrocostos(@RequestBody DtoComunEmpleadomast pk) {				
		logger.debug("obtenercentrocostos");
		DtoComunEmpleadomast dto = obtenerdtoCore(pk);
		return new ResponseEntity<String>(dto.getCentrocostos(),HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/obtenercentrocostosnombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerCentrocostosNombre(@RequestBody DtoComunEmpleadomast pk) {				
		logger.debug("obtenercentrocostosnombre");
		EmpleadomastTransaccion dto = obtenerTransaccionPorEmpleadoCore(new EmpleadomastTransaccion(pk.getEmpleado()));
		logger.debug(dto.getCentrocostos());
		return new ResponseEntity<String>(dto.getCentrocostosNombre(),HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/obtenercodigocargonombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerCodigocargoNombre(@RequestBody DtoComunEmpleadomast pk) {				
		logger.debug("obtenercodigocargonombre");
		EmpleadomastTransaccion dto = obtenerTransaccionPorEmpleadoCore(new EmpleadomastTransaccion(pk.getEmpleado()));
		logger.debug(dto.getCodigocargo());
		return new ResponseEntity<String>(dto.getCodigocargoNombre(),HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/obtenerdtoporcodigousuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunEmpleadomast> obtenerdtoPorCodigousuario(@RequestBody DtoComunEmpleadomast pTran) {				
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigousuario", String.class, pTran.getCodigousuario()));
		try {
			List datos = this.listarPorQuery(DtoComunEmpleadomast.class,"empleadomast.obtenerdtoPorCodigousuario", parametros);
			if (datos.size()!=1) {
				pTran.setTransaccionEstado(DominioTransaccion.ERROR);
				pTran.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
			}else {
				pTran = (DtoComunEmpleadomast) datos.get(0);
				pTran.setTransaccionEstado(DominioTransaccion.OK);
			}
		} catch (Exception e) {
			pTran.setTransaccionEstado(DominioTransaccion.ERROR);
			pTran.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, e.getMessage()));
			e.printStackTrace();
		}
		return new ResponseEntity<DtoComunEmpleadomast>(pTran,HttpStatus.OK);
	}
	
	
	@Transactional
	@PutMapping(value = "/obtenerMaxEmpleado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BigDecimal> obtenerMaxEmpleado(@RequestBody DtoComunEmpleadomast pk) throws Exception {
		BigDecimal rate = empleadomastDao.obtenerMaximoEmpleado(pk.getCodigousuario());
		return new ResponseEntity<BigDecimal>(rate, HttpStatus.OK);
	}
	
	
	
	
}
