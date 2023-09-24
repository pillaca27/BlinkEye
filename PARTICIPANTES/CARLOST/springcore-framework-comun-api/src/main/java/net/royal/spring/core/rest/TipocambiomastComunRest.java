package net.royal.spring.core.rest;

import java.math.BigDecimal;
 
import java.text.SimpleDateFormat;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import net.royal.spring.core.dao.impl.TipocambiomastDaoImpl;
import net.royal.spring.core.dominio.BeanTipocambiomast;
import net.royal.spring.core.dominio.BeanTipocambiomastPk;
import net.royal.spring.core.dominio.dto.DtoComunTipocambiomast;
import net.royal.spring.core.dominio.filtro.FiltroComunTipocambiomast;
import net.royal.spring.core.servicio.impl.TipocambiomastServicioImpl;
import net.royal.spring.core.servicio.validar.TipocambiomastServicioValidar;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UFechaHora;
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

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public TipocambiomastComunRest() {
		super("tipocambiomast");
	}

	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTipocambiomast> validar(@Validated @PathVariable String accion, @RequestBody DtoComunTipocambiomast dto) throws Exception {
		logger.debug("TipocambiomastRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunTipocambiomast>(dto, HttpStatus.OK);
	}*/

	/*
	 *LEONARDO
	 *OBTIENE OBJETO TIPOCAMBIOMAST
	 * */
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTipocambiomast> obtenerDto(@RequestBody DtoComunTipocambiomast pk) throws Exception {
		logger.debug("TipocambiomastRest.obtenerDto");
		DtoComunTipocambiomast dto = tipocambiomastDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunTipocambiomast>(dto,HttpStatus.OK);
	}

	/*
	 *LEONARDO
	 *REGISTRA TIPOCAMBIOMAST
	 * */
	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTipocambiomast> registrar(@RequestBody DtoComunTipocambiomast dto) throws Exception {
		logger.debug("TipocambiomastRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunTipocambiomast>(dto, HttpStatus.CREATED);
	}

	/*
	 *LEONARDO
	 *ACTUALIZA TIPOCAMBIOMAST
	 * */
	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTipocambiomast> actualizar(@RequestBody DtoComunTipocambiomast dto) throws Exception {
		logger.debug("TipocambiomastRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunTipocambiomast>(dto, HttpStatus.OK);
	}

	
	/*@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTipocambiomast> anular(@RequestBody DtoComunTipocambiomast dto) throws Exception {
		logger.debug("TipocambiomastRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunTipocambiomast>(dto, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * ELIMINAR TIPOCAMBIOMAST
	 * 
	 * */
	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTipocambiomast> eliminar(@RequestBody DtoComunTipocambiomast dto) throws Exception {
		logger.debug("TipocambiomastRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunTipocambiomast>(dto,HttpStatus.OK);
	}

	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("TipocambiomastRest.listar");
		// TODO TipocambiomastRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "tipocambiomast.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("TipocambiomastRest.listaractivos");
		// TODO TipocambiomastRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "tipocambiomast.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunTipocambiomast>> listarDtoFiltros(@RequestBody DtoComunTipocambiomast filtro) throws Exception {
		logger.debug("TipocambiomastRest.listardtofiltros");
        List datos = tipocambiomastDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunTipocambiomast>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunTipocambiomast>> listarDtoActivos(@RequestBody DtoComunTipocambiomast filtro) throws Exception {
		logger.debug("TipocambiomastRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = tipocambiomastDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunTipocambiomast>>(datos, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO 
	 * LISTADO PAGINADO GENERAL TIPOCAMBIOMAST
	 * */
	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunTipocambiomast filtro) throws Exception {
		logger.debug("TipocambiomastRest.listarPaginado");
		DominioPaginacion paginacion = tipocambiomastDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
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

	/*
	 * LEONARDO
	 * OBTIENE TIPO DE CAMBIO DE LA WEB SBS
	 * COPIADO DE SU COMUN
	 * */
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
					//logger.debug("nuevoBodyCompra SBS :: " + nuevoBodyCompra);
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
				dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA, "El tipo de cambio no esta configurado"));
				e.printStackTrace();
				return new ResponseEntity<DtoComunTipocambiomast>(dto, HttpStatus.OK);
			}
			
			
		}
		else {
			logger.debug("TIPO CAMBIO YA REGISTRADO");
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return new ResponseEntity<DtoComunTipocambiomast>(dto, HttpStatus.OK);
	}
	
}
