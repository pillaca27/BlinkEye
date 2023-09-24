package net.royal.spring.core.rest;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dao.impl.PersonamastWhDaoImpl;
import net.royal.spring.core.dominio.dto.DtoComunPersonaMast;
import net.royal.spring.core.dominio.filtro.FiltroComunCorrelativosmast;
import net.royal.spring.core.dominio.filtro.FiltroComunPersonamast;
import net.royal.spring.core.dominio.filtro.FiltroComunPersonamastclis001;
import net.royal.spring.core.servicio.impl.PersonamastServicioImpl;
import net.royal.spring.core.servicio.validar.PersonamastServicioValidar;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.EmpleadomastTransaccion;
import net.royal.spring.framework.modelo.PersonamastTransaccion;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/personamastcomun")
@CrossOrigin(origins = "*")
public class PersonamastComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(PersonamastComunRest.class);

	@Autowired
	private PersonamastServicioImpl servicio;

	@Autowired
	private PersonamastServicioValidar validar;

	@Autowired
	private PersonamastWhDaoImpl personamastDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public PersonamastComunRest() {
		super("personamast");
	}

	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunPersonaMastWh> validar(@Validated @PathVariable String accion, @RequestBody DtoComunPersonaMastWh dto) throws Exception {
		logger.debug("PersonamastRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunPersonaMastWh>(dto, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * OBTIENE OBJETO PERSONAMAST
	 * 
	 * */
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunPersonaMast> obtenerDto(@RequestBody DtoComunPersonaMast pk) throws Exception {
		logger.debug("PersonamastRest.obtenerDto");
		DtoComunPersonaMast dto = personamastDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunPersonaMast>(dto,HttpStatus.OK);
	}

	/*
	 * LEONARDO
	 * REGISTRO PERSONAMAST
	 * 
	 * */
	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunPersonaMast> registrar(@RequestBody DtoComunPersonaMast dto) throws Exception {
		logger.debug("PersonamastRest.registrar");
		dto =  servicio.coreInsertar(dto.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunPersonaMast>(dto, HttpStatus.CREATED);
	}

	
	/*
	 * LEONARDO
	 * ACTUALIZAR PERSONAMAST
	 * 
	 * */
	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunPersonaMast> actualizar(@RequestBody DtoComunPersonaMast dto) throws Exception {
		logger.debug("PersonamastRest.actualizar");
		dto = servicio.coreActualizar(dto.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunPersonaMast>(dto, HttpStatus.OK);
	}

	/*
	 * LEONARDO
	 * ACTUALIZAR MASIVO PERSONAMAST
	 * 
	 * */
	@Transactional
	@PutMapping(value = "/actualizarMasivo",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunPersonaMast> actualizarMasivo(@RequestBody List<DtoComunPersonaMast> dto) throws Exception {
		logger.debug("PersonamastRest.actualizarMasivo");
		servicio.coreActualizarMasivo(getUsuarioActual(),dto);
		DtoComunPersonaMast dw = new DtoComunPersonaMast();
		return new ResponseEntity<DtoComunPersonaMast>(dw, HttpStatus.OK);
	}
	
	/*@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunPersonaMastWh> anular(@RequestBody DtoComunPersonaMastWh dto) throws Exception {
		logger.debug("PersonamastRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunPersonaMastWh>(dto, HttpStatus.OK);
	}*/

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunPersonaMast> eliminar(@RequestBody DtoComunPersonaMast dto) throws Exception {
		logger.debug("PersonamastRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunPersonaMast>(dto,HttpStatus.OK);
	}

	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("PersonamastRest.listar");
		// TODO PersonamastRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "personamastWh.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("PersonamastRest.listaractivos");
		// TODO PersonamastRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "personamastWh.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunPersonaMastWh>> listarDtoFiltros(@RequestBody DtoComunPersonaMastWh filtro) throws Exception {
		logger.debug("PersonamastRest.listardtofiltros");
        List datos = personamastDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunPersonaMastWh>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunPersonaMastWh>> listarDtoActivos(@RequestBody DtoComunPersonaMastWh filtro) throws Exception {
		logger.debug("PersonamastRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = personamastDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunPersonaMastWh>>(datos, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * LISTADO PAGINADO GENERAL
	 * 
	 * */
	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunPersonamast filtro) throws Exception {
		logger.debug("PersonamastRest.listarPaginado");
		DominioPaginacion paginacion = personamastDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}
	
	/*
	 * LEONARDO
	 * LISTA DE ZONAS 
	 *
	 * */
	@Transactional
	@PostMapping(value = "/listarZonas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarZonas(@RequestBody DtoComunPersonaMast dto) {
		logger.debug("PersonamastRest.listarZonas");
		// TODO PersonamastRest.listaractivos : modificar query
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		
		parametros.add(new DominioParametroPersistencia("p_departamento", String.class, dto.getDepartamento()));
		parametros.add(new DominioParametroPersistencia("p_codigopostal", String.class, dto.getCodigopostal()));
		parametros.add(new DominioParametroPersistencia("p_provincia", String.class, dto.getProvincia()));
		
		List datos = this.listarPorQuery(DtoTabla.class, "personamast.listarZonas",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/*
	 * LEONARDO
	 * EXPORTAR LISTADO GENERAL
	 * 
	 * */
	@Transactional
	@PostMapping(value = "/exportarPersonas", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarPersonas(HttpServletResponse response, @RequestBody FiltroComunPersonamast filtro) throws Exception
	 {
		
		DominioPaginacion paginacion = personamastDao.exportarPersonas(filtro);
		String[] arrCabecera = new String[] {"Origen","Persona","B\u00FAsqueda","Cli","Prov","Emp","Documento","Doc Fiscal","Doc Identidad","Estado"};
		String[] arrColumnas = new String[] { "origen", "persona", "busqueda", "cli","prov","emp","documento","documentofiscal","documentoidentidad","estadodescripcion"}; 
		
		DtoWhExportar dtoExportar=new DtoWhExportar();
		dtoExportar.setTitulo("Listado de Personas");
		dtoExportar.setTipoExpotar(filtro.getTipoexportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setPaginacion(paginacion);
			
		servicio.exportarInformacionWh( response,dtoExportar);
	}
	

	/**
	 * TIPO(PRCLOT) : Proveedor / Cliente / Otro
	 * 
	 * @param filtro
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Personamast (Proveedores) | Parametros de entrada: PersonamastFiltroPaginado ( persona, busqueda,"
			+ "documento, documentofiscal, documentoidentidad, estado  )  | Parametros de salida: persona, busqueda, escliente, esproveedor, esempleado, esotro, documento, documentofiscal, documentoidentidad, estado, centroCosto, descripcioncentroCosto, unidad, descripcionunidad, departamento, descripciondepartamento, sucursal", value = "99-PERSON-PROVEED", tags = {
					"CORE", "PROVEEDOR" })
	@Transactional
	@PutMapping(value = "/listarclienteproveedorotro", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarClienteProveedorOtro(
			@RequestBody FiltroComunPersonamastclis001 filtro) {
		logger.debug("TIPO(PRCLOT) : Proveedor / Cliente / Otro");

		if (UBigDecimal.esCeroOrNulo(filtro.getPersona()))
			filtro.setPersona(null);
		if (UString.esNuloVacio(filtro.getBusqueda()))
			filtro.setBusqueda(null);

		if (UString.esNuloVacio(filtro.getDocumento()))
			filtro.setDocumento(null);
		if (UString.esNuloVacio(filtro.getDocumentofiscal()))
			filtro.setDocumentofiscal(null);
		if (UString.esNuloVacio(filtro.getDocumentoidentidad()))
			filtro.setDocumentoidentidad(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigo", BigDecimal.class, filtro.getPersona()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getBusqueda()));

		parametros.add(new DominioParametroPersistencia("p_documento", String.class, filtro.getDocumento()));
		parametros.add(new DominioParametroPersistencia("p_docfiscal", String.class, filtro.getDocumentofiscal()));
		parametros
				.add(new DominioParametroPersistencia("p_docidentidad", String.class, filtro.getDocumentoidentidad()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer contador = this.contar("personamast.paginadoClienteProveedorOtroContar", parametros);
		List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros,
				"personamast.paginadoClienteProveedorOtroListar", PersonamastTransaccion.class);

		filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
		filtro.getPaginacion().setPaginacionListaResultado(lista);
		logger.debug(contador);
		logger.debug(lista.size());

		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
	}
	
	
	/**
	 * ARMAS MIGRADO TIPO(EMOT) : Empleado / Otro
	 * 
	 * @param filtro
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Personamast | Parametros de entrada: PersonamastFiltroPaginado ( persona, busqueda,"
			+ "documento, documentofiscal, documentoidentidad, estado  )  | Parametros de salida: persona, busqueda, escliente, esproveedor, esempleado, esotro, documento, documentofiscal, documentoidentidad, estado, centroCosto, descripcioncentroCosto, unidad, descripcionunidad, departamento, descripciondepartamento, sucursal", value = "99-PERSON-CLIS001", tags = {
					"CORE", "PERSONA" })
	@Transactional
	@PutMapping(value = "/clis001", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> clis001(@RequestBody FiltroComunPersonamastclis001 filtro) {
		logger.debug("TIPO(EMOT) : Empleado / Otro");

		if (UBigDecimal.esCeroOrNulo(filtro.getPersona()))
			filtro.setPersona(null);
		if (UString.esNuloVacio(filtro.getBusqueda()))
			filtro.setBusqueda(null);

		if (UString.esNuloVacio(filtro.getDocumento()))
			filtro.setDocumento(null);
		if (UString.esNuloVacio(filtro.getDocumentofiscal()))
			filtro.setDocumentofiscal(null);
		if (UString.esNuloVacio(filtro.getDocumentoidentidad()))
			filtro.setDocumentoidentidad(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		if (UString.esNuloVacio(filtro.getCentrocosto()))
			filtro.setCentrocosto(null);

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigo", BigDecimal.class, filtro.getPersona()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getBusqueda()));

		parametros.add(new DominioParametroPersistencia("p_documento", String.class, filtro.getDocumento()));
		parametros.add(new DominioParametroPersistencia("p_docfiscal", String.class, filtro.getDocumentofiscal()));
		parametros
				.add(new DominioParametroPersistencia("p_docidentidad", String.class, filtro.getDocumentoidentidad()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		parametros.add(new DominioParametroPersistencia("p_centrocosto", String.class, filtro.getCentrocosto()));

		Integer contador = this.contar("personamast.paginadoEmpleadoOtroContar", parametros);
		List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros,
				"personamast.paginadoEmpleadoOtroListar", EmpleadomastTransaccion.class);

		filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
		filtro.getPaginacion().setPaginacionListaResultado(lista);
		logger.debug(contador);
		logger.debug(lista.size());

		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
	}
	
	
	/**
	 * TIPO(PR) : Proveedor
	 * 
	 * @param filtro
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Personamast (Proveedores) | Parametros de entrada: PersonamastFiltroPaginado ( persona, busqueda,"
			+ "documento, documentofiscal, documentoidentidad, estado  )  | Parametros de salida: persona, busqueda, escliente, esproveedor, esempleado, esotro, documento, documentofiscal, documentoidentidad, estado, centroCosto, descripcioncentroCosto, unidad, descripcionunidad, departamento, descripciondepartamento, sucursal", value = "99-PERSON-PROVEED", tags = {
					"CORE", "PROVEEDOR" })
	@Transactional
	@PutMapping(value = "/listarproveedor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarProveedor(@RequestBody FiltroComunPersonamastclis001 filtro) {
		logger.debug("TIPO(PR) : Proveedor");

		if (UBigDecimal.esCeroOrNulo(filtro.getPersona()))
			filtro.setPersona(null);
		if (UString.esNuloVacio(filtro.getBusqueda()))
			filtro.setBusqueda(null);

		if (UString.esNuloVacio(filtro.getDocumento()))
			filtro.setDocumento(null);
		if (UString.esNuloVacio(filtro.getDocumentofiscal()))
			filtro.setDocumentofiscal(null);
		if (UString.esNuloVacio(filtro.getDocumentoidentidad()))
			filtro.setDocumentoidentidad(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigo", BigDecimal.class, filtro.getPersona()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getBusqueda()));

		parametros.add(new DominioParametroPersistencia("p_documento", String.class, filtro.getDocumento()));
		parametros.add(new DominioParametroPersistencia("p_docfiscal", String.class, filtro.getDocumentofiscal()));
		parametros
				.add(new DominioParametroPersistencia("p_docidentidad", String.class, filtro.getDocumentoidentidad()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer contador = this.contar("personamast.paginadoProveedorContar", parametros);
		List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "personamast.paginadoProveedorListar",
				PersonamastTransaccion.class);

		filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
		filtro.getPaginacion().setPaginacionListaResultado(lista);
		logger.debug(contador);
		logger.debug(lista.size());

		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
	}
}
