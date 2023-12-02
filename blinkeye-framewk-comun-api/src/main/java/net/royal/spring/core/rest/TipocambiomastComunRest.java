package net.royal.spring.core.rest;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dao.impl.ParametrosmastDaoImpl;
import net.royal.spring.core.dao.impl.TipocambiomastDaoImpl;
import net.royal.spring.core.dominio.BeanTipocambiomast;
import net.royal.spring.core.dominio.BeanTipocambiomastPk;
import net.royal.spring.core.dominio.dto.DtoComunTipocambiomast;
import net.royal.spring.core.dominio.filtro.FiltroComunTipocambiomast;
import net.royal.spring.core.servicio.impl.TipocambiomastServicioImpl;
import net.royal.spring.core.servicio.validar.TipocambiomastServicioValidar;
import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.TipoCambioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UFechaHora;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/tipocambiomastcomun")
@CrossOrigin(origins = "*")
public class TipocambiomastComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(TipocambiomastComunRest.class);

	@Autowired
	private TipocambiomastServicioImpl servicio;

	@Autowired
	private TipocambiomastServicioValidar validar;

	@Autowired
	private TipocambiomastDaoImpl tipocambiomastDao;

	@Autowired
	private ParametrosmastDaoImpl parametrosmastDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public TipocambiomastComunRest() {
		super("tipocambiomast");
	}

	/*
	 * @Transactional
	 * 
	 * @PutMapping(value = "/validar/{accion}", produces =
	 * MediaType.APPLICATION_JSON_VALUE) public
	 * ResponseEntity<DtoComunTipocambiomast> validar(@Validated @PathVariable
	 * String accion, @RequestBody DtoComunTipocambiomast dto) throws Exception {
	 * logger.debug("TipocambiomastRest.validar"); logger.debug(accion);
	 * logger.debug(dto); dto = validar.core(this.getUsuarioActual(), accion, dto);
	 * return new ResponseEntity<DtoComunTipocambiomast>(dto, HttpStatus.OK); }
	 */

	/**
	 * QQUECHOD VALIDADO ESTE SERVICIO NO ES OBLIGATORIO EN ANGULAR / SE USA ENTRE
	 * TRANSACCIONES
	 * 
	 * @param tipo
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "Descripcion: Obtener tipo de cambio | Parametros de entrada: TipoCambioTransaccion | Parametros de salida: TipoCambioTransaccion", value = "99-TICAMB-COBTAC", tags = {
			"CORE", "TIPO CAMBIO" })
	@Transactional
	@PutMapping(value = "/obtenertipocambio", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoCambioTransaccion> obtenertipocambio(@RequestBody TipoCambioTransaccion tipo)
			throws Exception {
		logger.debug("TipocambiomastRest.obtenerDtoPorId");

		DtoComunTipocambiomast pk = new DtoComunTipocambiomast();
		pk.setFechacambio(tipo.getFecha());
		logger.debug(tipo.getFecha());
		pk.setMonedacodigo(tipo.getTipo());
		pk.setMonedacambiocodigo(tipo.getTipoCambio());

		// DtoComunTipocambiomast dto = tipocambiomastDao.obtenerDtoPorId(pk);

		String fechaDesdeString = UFechaHora.convertirFechaCadena(tipo.getFecha(), "yyyyMMdd");

		pk.setFechacambiostring(fechaDesdeString);

		DtoComunTipocambiomast dto = obtenerDtoCadena(pk);

		TipoCambioTransaccion transaccion = new TipoCambioTransaccion();

		if (dto == null) {
			// dto = new DtoTipocambiomast();
			transaccion.setTransaccionEstado(DominioTransaccion.ERROR);
			transaccion.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
			return new ResponseEntity<TipoCambioTransaccion>(transaccion, HttpStatus.OK);
		}

		transaccion.setFactor(dto.getFactor());
		transaccion.setFactorcompra(dto.getFactorcompra());
		transaccion.setFactorventa(dto.getFactorventa());
		transaccion.setFactorpromedio(dto.getFactorpromedio());
		transaccion.setFactorcompraafp(dto.getFactorcompraafp());
		transaccion.setFactorventaafp(dto.getFactorventaafp());
		transaccion.setFactorcomprasbs(dto.getFactorcomprasbs());
		transaccion.setFactorventasbs(dto.getFactorventasbs());
		transaccion.setValorcuota(dto.getValorcuota());
		transaccion.setEstado(dto.getEstado());
		transaccion.setTasatamex(dto.getTasatamex());
		transaccion.setTasatamn(dto.getTasatamn());
		transaccion.setTasaanualtamex(dto.getTasaanualtamex());
		transaccion.setTasaanualtamn(dto.getTasaanualtamn());
		transaccion.setFactorcobranzacompra(dto.getFactorcobranzacompra());
		transaccion.setFactorcobranzaventa(dto.getFactorcobranzaventa());

		transaccion.setTransaccionEstado(DominioTransaccion.OK);
		return new ResponseEntity<TipoCambioTransaccion>(transaccion, HttpStatus.OK);
	}

	/**
	 * QQUECHOD VALIDADO
	 * 
	 * @param filtro
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "Descripcion: Listar paginado | Parametros de entrada: TipoCambioTransaccion | Parametros de salida: TipoCambioTransaccion", value = "99-TICAMB-CLISPAG", tags = {
			"CORE", "TIPO CAMBIO" })
	@Transactional
	@PutMapping(value = "/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunTipocambiomast filtro)
			throws Exception {
		logger.debug("TipocambiomastRest.listarPaginado");
		SeguridadUsuarioActual usu = this.getUsuarioActual();
		DominioPaginacion paginacion = tipocambiomastDao.listarPaginado(usu, filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion, HttpStatus.OK);
	}

	/**
	 * QQUECHOD VALIDADO
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "Descripcion: Registrar tipo de cambio | Parametros de entrada: TipoCambioTransaccion | Parametros de salida: TipoCambioTransaccion", value = "99-TICAMB-CREGIS", tags = {
			"CORE", "TIPO CAMBIO" })
	@Transactional
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTipocambiomast> registrar(@RequestBody DtoComunTipocambiomast dto) throws Exception {
		logger.debug("TipocambiomastRest.registrar");
		dto = servicio.coreInsertar(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunTipocambiomast>(dto, HttpStatus.CREATED);
	}

	/**
	 * QQUECHOD VALIDADO
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "Descripcion: Actualizar tipo de cambio | Parametros de entrada: TipoCambioTransaccion | Parametros de salida: TipoCambioTransaccion", value = "99-TICAMB-CACTUA", tags = {
			"CORE", "TIPO CAMBIO" })
	@Transactional
	@PutMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTipocambiomast> actualizar(@RequestBody DtoComunTipocambiomast dto) throws Exception {
		logger.debug("TipocambiomastRest.actualizar");
		dto = servicio.coreActualizarPorUuid(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunTipocambiomast>(dto, HttpStatus.OK);
	}

	/**
	 * QQUECHOD VALIDADO
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "Descripcion: Anular tipo de cambio | Parametros de entrada: TipoCambioTransaccion | Parametros de salida: TipoCambioTransaccion", value = "99-TICAMB-CANULA", tags = {
			"CORE", "TIPO CAMBIO" })
	@Transactional
	@PutMapping(value = "/anular", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTipocambiomast> anular(@RequestBody DtoComunTipocambiomast dto) throws Exception {
		logger.debug("TipocambiomastRest.anular");
		dto = servicio.coreAnularPorUuid(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunTipocambiomast>(dto, HttpStatus.OK);
	}

	/**
	 * QQUECHOD VALIDADO
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "Descripcion: Elminar tipo de cambio | Parametros de entrada: TipoCambioTransaccion | Parametros de salida: TipoCambioTransaccion", value = "99-TICAMB-CELIMIN", tags = {
			"CORE", "TIPO CAMBIO" })
	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTipocambiomast> eliminar(@RequestBody DtoComunTipocambiomast dto) throws Exception {
		logger.debug("TipocambiomastRest.eliminar");
		dto = servicio.coreEliminarPorUuid(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunTipocambiomast>(dto, HttpStatus.OK);
	}

//	/**
//	 * QQUECHOD VALIDADO
//	 * 
//	 * @param pk
//	 * @return
//	 * @throws Exception
//	 */
//	@ApiOperation(notes = "Descripcion: Obtener tipo de cambio | Parametros de entrada: TipoCambioTransaccion | Parametros de salida: TipoCambioTransaccion", value = "99-TICAMB-COBDTO", tags = {
//			"CORE", "TIPO CAMBIO" })
//	@Transactional
//	@PutMapping(value = "/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<DtoComunTipocambiomast> obtenerDto(@RequestBody DtoComunTipocambiomast pk) throws Exception {
//		logger.debug("obtenerDto");
//		DtoComunTipocambiomast dto = null;
//		if (pk == null) {
//			dto = new DtoComunTipocambiomast();
//			dto.setTransaccionEstado(DominioTransaccion.ERROR);
//			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no enviado"));
//			return new ResponseEntity<DtoComunTipocambiomast>(dto, HttpStatus.OK);
//		}
//
//		if (UString.esNuloVacio(pk.getFechacambiostring())) {
//			logger.debug("obtenerDtoPorId");
//			dto = tipocambiomastDao.obtenerDtoPorUuid(pk.getUuid());
//		} else {
//			dto = obtenerDtoCadena(pk);
//		}
//
//		if (dto == null) {
//			dto = new DtoComunTipocambiomast();
//			dto.setTransaccionEstado(DominioTransaccion.ERROR);
//			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
//			return new ResponseEntity<DtoComunTipocambiomast>(dto, HttpStatus.OK);
//		}
//		dto.setTransaccionEstado(DominioTransaccion.OK);
//		return new ResponseEntity<DtoComunTipocambiomast>(dto, HttpStatus.OK);
//	}

	public DtoComunTipocambiomast obtenerDtoCadena(DtoComunTipocambiomast pk) throws Exception {
		logger.debug("obtenerDtoCadena");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_monedacodigo", String.class, pk.getMonedacodigo()));
		parametros.add(
				new DominioParametroPersistencia("p_monedacambiocodigo", String.class, pk.getMonedacambiocodigo()));
		parametros
				.add(new DominioParametroPersistencia("p_fechacambiostring", String.class, pk.getFechacambiostring()));
		List datos = this.listarPorQuery(DtoComunTipocambiomast.class, "tipocambiomast.obtenerDtoCadena", parametros);
		DtoComunTipocambiomast dto;
		if (datos.size() == 1) {
			dto = (DtoComunTipocambiomast) datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		} else {
			dto = new DtoComunTipocambiomast();
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}

	/**
	 * f_sql_get_rate
	 * 
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@PutMapping(value = "/obtenerfactor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BigDecimal> obtenerFactor(@RequestBody DtoComunTipocambiomast pk) throws Exception {
		BigDecimal rate = tipocambiomastDao.obtenerFactor(pk);
		return new ResponseEntity<BigDecimal>(rate, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/obtenerTipoCambioCreacionDefault", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTipocambiomast> obtenerTipoCambioCreacionDefault() throws Exception {
		logger.debug("obtenerTipoCambioCreacionDefault");
		DtoComunTipocambiomast dto = new DtoComunTipocambiomast();
		Date hoy = UFechaHora.removerHora(new Date());
		BeanTipocambiomast tipocambiomast = tipocambiomastDao.obtenerPorId(new BeanTipocambiomastPk("EX", "LO", hoy));
		if (tipocambiomast == null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.TEXT_HTML);
			headers.add("Cookie",
					"visid_incap_2355492=msIJgwjzRWeEOfNiudGhO7DiN2AAAAAAQUIPAAAAAAC/o9psxacaT8jHiQQs4pbE; incap_ses_994_2355492=UghNZIuK5jTmik77CmbLDbDiN2AAAAAAbS/cNFaqFSyskv+UyFQpKg==; ___utmvmcMuIwpVZ=dIzdFnwazKT; ___utmvbcMuIwpVZ=pZZ XqsOValf: mty");
			headers.add("User-Agent", "PostmanRuntime/7.26.8");
			RestTemplate restTemplate = new RestTemplate();
			
			try {
				ResponseEntity<String> response = restTemplate.exchange(
						"https://www.sbs.gob.pe/app/pp/sistip_portal/paginas/publicacion/tipocambiopromedio.aspx",
						HttpMethod.GET, new HttpEntity(headers), String.class);
				if (response.getStatusCode() == HttpStatus.OK) {
					SeguridadUsuarioActual usuarioActual = new SeguridadUsuarioActual();
					double compra = 0.0;
					double venta = 0.0;
					String body = response.getBody();
					//logger.debug("BODY SBS :: " + body);
					int indexOfCompraInicio = body.indexOf("<td class=\"APLI_fila2\"");
					logger.debug("indexOfCompraInicio SBS :: " + indexOfCompraInicio);
					String nuevoBodyCompra = body.substring(indexOfCompraInicio + 35, body.length());
					logger.debug("nuevoBodyCompra SBS :: " + nuevoBodyCompra);
					int indexOfCompraFin = nuevoBodyCompra.indexOf("</td>");
					logger.debug("indexOfCompraFin SBS :: " + indexOfCompraFin);
					compra = Double.parseDouble(nuevoBodyCompra.substring(0, indexOfCompraFin));
					String nuevoBody = nuevoBodyCompra.substring(indexOfCompraFin, nuevoBodyCompra.length());
					int indexOfVentaInicio = nuevoBody.indexOf("<td class=\"APLI_fila2\"");
					String nuevoBodyVenta = nuevoBody.substring(indexOfVentaInicio + 35, nuevoBody.length());
					int indexOfVentaFin = nuevoBodyVenta.indexOf("</td>");
					venta = Double.parseDouble(nuevoBodyVenta.substring(0, indexOfVentaFin));
					// registrar nuevo tipo cambio
					logger.debug("COMPRA SBS :: " + compra);
					logger.debug("VENTA SBS :: " + venta);
					dto = new DtoComunTipocambiomast();
					dto.setMonedacodigo("EX");
					dto.setMonedacambiocodigo("LO");
					dto.setFechacambio(hoy);
					dto.setFechacambiostring(new SimpleDateFormat("yyyy/MM/dd").format(hoy).replace("/", ""));
					dto.setFactor(new BigDecimal(0));
					dto.setFactorventa(new BigDecimal(venta));
					dto.setFactorcompra(new BigDecimal(compra));
					dto.setFactorventasbs(new BigDecimal(venta));
					dto.setFactorcomprasbs(new BigDecimal(compra));
					dto.setEstado("A");
					dto.setUltimafechamodif(new Date());
					dto.setUltimousuario(usuarioActual.getUsuario());
					servicio.coreInsertar(usuarioActual, dto);
					logger.debug("REGISTRADO tipo cambio de sbs");
				} else {
					logger.debug("ERROR al traer el tipo cambio de sbs");
					dto.setTransaccionEstado(DominioTransaccion.VALIDACION);
					dto.getTransaccionListaMensajes().add(
							new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA, "El tipo de cambio no esta configurado"));
					return new ResponseEntity<DtoComunTipocambiomast>(dto, HttpStatus.OK);
				}
			}catch (Exception e) {
				logger.debug("ERROR al traer el tipo cambio de sbs");
				logger.debug("ERROR :: ", e.getMessage());
				dto.setTransaccionEstado(DominioTransaccion.VALIDACION);
				dto.getTransaccionListaMensajes().add(
						new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA, "El tipo de cambio no esta configurado"));
				return new ResponseEntity<DtoComunTipocambiomast>(dto, HttpStatus.OK);
			}
			
			
		}
		else {
			logger.debug("TIPO CAMBIO YA REGISTRADO");
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return new ResponseEntity<DtoComunTipocambiomast>(dto, HttpStatus.OK);
	}

	/*
	 *LEONARDO
	 *OBTIENE OBJETO TIPOCAMBIOMAST
	 * */
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTipocambiomast> obtenerDto(@RequestBody DtoComunTipocambiomast pk) throws Exception {
		logger.debug("TipocambiomastRest.obtenerDto");
		DtoComunTipocambiomast dto = tipocambiomastDao.obtenerDtoPorUuid(pk.getUuid());
		return new ResponseEntity<DtoComunTipocambiomast>(dto,HttpStatus.OK);
	}
 
	
	/*
	 * LEONARDO
	 * EXPORTAR LISTADO TIPOCAMBIOMAST
	 * */
	@Transactional
	@PostMapping(value = "/exportarTipoCambio", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarTipoCambio(HttpServletResponse response, @RequestBody FiltroComunTipocambiomast filtro) throws Exception
	 {
		
		DominioPaginacion paginacion = tipocambiomastDao.exportarTipoCambio(filtro);
		String[] arrCabecera = new String[] {"Fecha","Oficial Compra","Oficial Venta","Otros","Estado"};
		String[] arrColumnas = new String[] {"fechacambiodescripcion","factorcompra2","factorventa2","factorpromedio2","estadodescripcion"}; 
		

		DtoWhExportar dtoExportar=new DtoWhExportar();
		dtoExportar.setTitulo("Listado de Tipo de Cambio Diario");
		dtoExportar.setTipoExpotar(filtro.getTipoexportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setPaginacion(paginacion);
			
		servicio.exportarInformacionWh(response,dtoExportar);
	}
}
