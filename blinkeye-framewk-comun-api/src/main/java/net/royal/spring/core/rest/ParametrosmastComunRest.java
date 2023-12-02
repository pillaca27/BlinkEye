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

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dao.impl.ParametrosmastDaoImpl;
import net.royal.spring.core.dominio.BeanParametrosmast;
import net.royal.spring.core.dominio.BeanParametrosmastPk;
import net.royal.spring.core.dominio.dto.DtoComunParametrosmast;
import net.royal.spring.core.dominio.filtro.FiltroComunAplicacionesmast;
import net.royal.spring.core.dominio.filtro.FiltroComunParametrosmast;
import net.royal.spring.core.servicio.impl.ParametrosmastServicioImpl;
import net.royal.spring.core.servicio.validar.ParametrosmastServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.EmpleadomastTransaccion;
import net.royal.spring.framework.modelo.ParametroTransaccion;
import net.royal.spring.framework.modelo.PersonamastTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/parametrosmastcomun")
@CrossOrigin(origins = "*")
public class ParametrosmastComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(ParametrosmastComunRest.class);

	@Autowired
	private ParametrosmastServicioImpl servicio;

	@Autowired
	private ParametrosmastServicioValidar validar;

	@Autowired
	private ParametrosmastDaoImpl parametrosmastDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public ParametrosmastComunRest() {
		super("parametrosmast");
	}

	/**
	 * QQUECHOD VALIDADO OBTENER PARAMETROS NO VA A ANGULAR
	 * 
	 * @param pTran
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Anular un parametro | Entrada: DtoComunParametrosmast | Parametros de salida: DtoComunParametrosmast", value = "99-PARAM-COBTPAR", tags = {
			"CORE", "PARAMETRO" })
	@Transactional(readOnly = true)
	@PutMapping(value = "/parametroobtener", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ParametroTransaccion> parametroObtener(@RequestBody ParametroTransaccion pTran)
			throws Exception {
		logger.debug("ParametrosmastRest.parametroobtener");

		if (pTran == null)
			pTran = new ParametroTransaccion();
		if (UString.esNuloVacio(pTran.getAplicacioncodigo()))
			pTran.setAplicacioncodigo("99");
		if (UString.esNuloVacio(pTran.getCompaniacodigo()))
			pTran.setCompaniacodigo("999999");

		try {
			BeanParametrosmast pp = parametrosmastDao.obtenerPorId(pTran.getCompaniacodigo(), pTran.getAplicacioncodigo(),
					pTran.getParametroclave());
			if (pp == null) {
				pTran.setTransaccionEstado(DominioTransaccion.ERROR);
				pTran.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
			} else {
				pTran.setExplicacion(pp.getExplicacion());
				pTran.setTexto(pp.getTexto());
				pTran.setFecha(pp.getFecha());
				pTran.setNumero(pp.getNumero() == null ? null : new BigDecimal(pp.getNumero()));
				pTran.setDescripcionparametro(pp.getDescripcionparametro());
				pTran.setTransaccionEstado(DominioTransaccion.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			pTran.setTransaccionEstado(DominioTransaccion.ERROR);
			pTran.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, e.getMessage()));
		}
		return new ResponseEntity<ParametroTransaccion>(pTran, HttpStatus.OK);
	}

	/*
	 * @Transactional
	 * 
	 * @PutMapping(value = "/validar/{accion}", produces =
	 * MediaType.APPLICATION_JSON_VALUE) public
	 * ResponseEntity<DtoComunParametrosmast> validar(@Validated @PathVariable
	 * String accion, @RequestBody DtoComunParametrosmast dto) throws Exception {
	 * logger.debug("ParametrosmastRest.validar"); logger.debug(accion);
	 * logger.debug(dto); dto = validar.core(this.getUsuarioActual(), accion, dto);
	 * return new ResponseEntity<DtoComunParametrosmast>(dto, HttpStatus.OK); }
	 */

	/**
	 * QQUECHOD VALIDADO
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Obtener un parametro | Entrada: DtoComunParametrosmast | Parametros de salida: DtoComunParametrosmast", value = "99-PARAM-COBTD", tags = {
			"CORE", "PARAMETRO" })
	@Transactional
	@PutMapping(value = "/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunParametrosmast> obtenerdto(@RequestBody DtoComunParametrosmast pk) throws Exception {
		logger.debug("ParametrosmastRest.obtenerDtoPorId");
		DtoComunParametrosmast dto = parametrosmastDao.obtenerDtoPorUuid(pk.getUuid());
		if (dto == null) {
			dto = new DtoComunParametrosmast();
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
			return new ResponseEntity<DtoComunParametrosmast>(dto, HttpStatus.OK);
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return new ResponseEntity<DtoComunParametrosmast>(dto, HttpStatus.OK);
	}

	/**
	 * QQUECHOD VALIDADO
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Registrar un parametro | Entrada: DtoComunParametrosmast | Parametros de salida: DtoComunParametrosmast", value = "99-PARAM-CREGIS", tags = {
			"CORE", "PARAMETRO" })
	@Transactional
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunParametrosmast> registrar(@RequestBody DtoComunParametrosmast dto) throws Exception {
		logger.debug("ParametrosmastRest.registrar");
		dto = servicio.coreInsertar(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunParametrosmast>(dto, HttpStatus.CREATED);
	}

	/**
	 * QQUECHOD VALIDADO
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Actualizar un parametro | Entrada: DtoComunParametrosmast | Parametros de salida: DtoComunParametrosmast", value = "99-PARAM-CACTU", tags = {
			"CORE", "PARAMETRO" })
	@Transactional
	@PutMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunParametrosmast> actualizar(@RequestBody DtoComunParametrosmast dto) throws Exception {
		logger.debug("ParametrosmastRest.actualizar");
		dto = servicio.coreActualizarPorUuid(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunParametrosmast>(dto, HttpStatus.OK);
	}

	/**
	 * QQUECHOD VALIDADO
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Anular un parametro | Entrada: DtoComunParametrosmast | Parametros de salida: DtoComunParametrosmast", value = "99-PARAM-CANUL", tags = {
			"CORE", "PARAMETRO" })
	@Transactional
	@PutMapping(value = "/anular", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunParametrosmast> anular(@RequestBody DtoComunParametrosmast dto) throws Exception {
		logger.debug("ParametrosmastRest.anular");
		dto = servicio.coreAnularPorUuid(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunParametrosmast>(dto, HttpStatus.OK);
	}

	/**
	 * QQUECHOD VALIDADO
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Anular un parametro | Entrada: DtoComunParametrosmast | Parametros de salida: DtoComunParametrosmast", value = "99-PARAM-CELIMI", tags = {
			"CORE", "PARAMETRO" })
	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunParametrosmast> eliminar(@RequestBody DtoComunParametrosmast dto) throws Exception {
		logger.debug("ParametrosmastRest.eliminar");
		dto = servicio.coreEliminarPorUuid(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunParametrosmast>(dto, HttpStatus.OK);
	}

	/**
	 * QQUECHOD VALIDADO
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Anular un parametro | Entrada: DtoComunParametrosmast | Parametros de salida: DtoComunParametrosmast", value = "99-PARAM-CPAGIN", tags = {
			"CORE", "PARAMETRO" })
	@Transactional
	@PutMapping(value = "/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunParametrosmast filtro)
			throws Exception {
		logger.debug("ParametrosmastRest.listarPaginado");

		/*
		 * PersonamastTransaccion per = servicio.personaObtenerDatos(374);
		 * logger.debug("per:"+per.getTransaccionEstado()); EmpleadomastTransaccion emp
		 * = servicio.empleadoObtenerDatos("00000000",374);
		 * logger.debug("emp:"+emp.getTransaccionEstado());
		 */

		DominioPaginacion paginacion = parametrosmastDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion, HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping(value = "/exportarParametros", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarParametros(HttpServletResponse response, @RequestBody FiltroComunParametrosmast filtro) throws Exception
	 {
		
		DominioPaginacion paginacion = parametrosmastDao.exportarParametros(filtro);
		String[] arrColumnas = new String[] { "parametroclave", "descripcionparametro", "companiacodigo", "tipovalordescripcion","valordescripcion","estadodescripcion"}; 
		String[] arrCabecera = new String[] {"Par\u00E1metro","Descripci\u00f3n","Compa\u00F1\u00EDa","Tipo valor","Valor","Estado"};
		
		DtoWhExportar dtoExportar=new DtoWhExportar();
		dtoExportar.setTitulo("Listado de P\u00E1rametros");
		dtoExportar.setTipoExpotar(filtro.getTipoexportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setPaginacion(paginacion);
			
		servicio.exportarInformacionWh( response,dtoExportar);
	}
	
	/**
	 * MALCAN VALIDADO
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Obtener un parametro | Entrada: DtoComunParametrosmast | Parametros de salida: DtoComunParametrosmast", value = "99-PARAM-COBTD", tags = {
			"CORE", "PARAMETRO" })
	@Transactional
	@PutMapping(value = "/obtenerdtopk", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunParametrosmast> obtenerdtopk(@RequestBody DtoComunParametrosmast pk) throws Exception {
		logger.debug("ParametrosmastRest.obtenerDtoPorId");
		DtoComunParametrosmast dto = parametrosmastDao.obtenerDtoPorId(pk);
		if (dto == null) {
			dto = new DtoComunParametrosmast();
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
			return new ResponseEntity<DtoComunParametrosmast>(dto, HttpStatus.OK);
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return new ResponseEntity<DtoComunParametrosmast>(dto, HttpStatus.OK);
	}
	
}
