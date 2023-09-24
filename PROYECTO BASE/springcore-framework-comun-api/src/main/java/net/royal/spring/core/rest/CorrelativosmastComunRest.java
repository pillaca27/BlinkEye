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
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.dominio.BeanCorrelativosmast;
import net.royal.spring.core.dominio.BeanCorrelativosmastPk;
import net.royal.spring.core.dominio.dto.DtoComunCorrelativosmast;
import net.royal.spring.core.dominio.filtro.FiltroComunParametrosmast;
import net.royal.spring.core.dominio.filtro.FiltroComunCorrelativosmast;
import net.royal.spring.core.servicio.impl.CorrelativosmastServicioImpl;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.CorrelativoTransaccion;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/correlativosmastcomun")
@CrossOrigin(origins = "*")
public class CorrelativosmastComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(CorrelativosmastComunRest.class);

	@Autowired
	private CorrelativosmastServicioImpl servicio;

	@Autowired
	private CorrelativosmastServicioValidar validar;

	@Autowired
	private CorrelativosmastDaoImpl correlativosmastDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public CorrelativosmastComunRest() {
		super("correlativosmast");
	}
	
	@ApiOperation(notes = "Generar correlativo. Entrada: DtoComunCorrelativosmast. Retorno: BigDecimal",					
			nickname="CORRELATIVOS_MAST_CGEN", value = "Generar correlativo", tags = {"CORRELATIVOS_MAST", "GENERAR"})
	@Transactional
	@PutMapping(value = "/generarcorrelativonumero",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BigDecimal> generarCorrelativoNumero(@RequestBody DtoComunCorrelativosmast bean) throws Exception {
		logger.debug("generarcorrelativonumero");
		CorrelativoTransaccion dto = new CorrelativoTransaccion(bean.getCompaniacodigo(),bean.getTipocomprobante(),bean.getSerie());
		dto.setCerosIzquierda(UBigDecimal.obtenerValorSinNulo(new BigDecimal(bean.getAuxCerosIzquierda())).intValue());
		dto = obtenercorrelativoCore(CorrelativoTransaccion.TIPO_CORRELATIVO_SPRING,dto);
		if (!dto.getTransaccionEstado().equals(DominioTransaccion.OK)) {
			logger.error(dto.getTransaccionMensajesCadena());
			dto.setNumeroGenerado(null);
		}
		return new ResponseEntity<BigDecimal>(dto.getNumeroGenerado(), HttpStatus.OK);
	}
	
	
	@ApiOperation(notes = "Generar correlativo cadena. Entrada: DtoComunCorrelativosmast. Retorno: String",					
			nickname="CORRELATIVOS_MAST_CGENST", value = "Generar correlativo cadena", tags = {"CORRELATIVOS_MAST", "GENERAR"})
	@Transactional
	@PutMapping(value = "/generarcorrelativocadena",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> generarCorrelativoCadena(@RequestBody DtoComunCorrelativosmast bean) throws Exception {
		logger.debug("generarcorrelativocadena");
		CorrelativoTransaccion dto = new CorrelativoTransaccion(bean.getCompaniacodigo(),bean.getTipocomprobante(),bean.getSerie());
		dto.setCerosIzquierda(UBigDecimal.obtenerValorSinNulo(new BigDecimal(bean.getAuxCerosIzquierda())).intValue());
		dto = obtenercorrelativoCore(CorrelativoTransaccion.TIPO_CORRELATIVO_SPRING,dto);
		if (!dto.getTransaccionEstado().equals(DominioTransaccion.OK)) {
			logger.error(dto.getTransaccionMensajesCadena());
			dto.setNumeroGenerado(null);
		}
		return new ResponseEntity<String>(dto.getNumeroGeneradoCadena(), HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener correlativo cadena. Entrada: CorrelativoTransaccion. Retorno: CorrelativoTransaccion",					
			nickname="CORRELATIVOS_MAST_COBT", value = "Generar correlativo cadena", tags = {"CORRELATIVOS_MAST", "OBTENER"})
	@Transactional
	@PutMapping(value = "/obtenercorrelativo",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CorrelativoTransaccion> obtenercorrelativo(@RequestBody CorrelativoTransaccion dto) throws Exception {
		logger.debug("obtenercorrelativo");
		dto = obtenercorrelativoCore(CorrelativoTransaccion.TIPO_CORRELATIVO_SPRING, dto);
		return new ResponseEntity<CorrelativoTransaccion>(dto, HttpStatus.OK);
	}
	
	public CorrelativoTransaccion obtenercorrelativoCore(String tipo,CorrelativoTransaccion dto) throws Exception {
		if (dto==null) {
			dto=new  CorrelativoTransaccion();
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR,"no enviado"));
			return dto;
		}
		try {			
			SeguridadUsuarioActual usu = this.getUsuarioActual();
			
			if (UString.esNuloVacio(dto.getSerie())) {
				dto.setTransaccionEstado(DominioTransaccion.ERROR);
				dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR,"serie no enviada"));
				return dto;
			}
			if (UString.esNuloVacio(dto.getCompaniacodigo())) {
				dto.setTransaccionEstado(DominioTransaccion.ERROR);
				dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR,"compania no enviada"));
				return dto;
			}
			if (UString.esNuloVacio(dto.getTipocomprobante())) {
				dto.setTransaccionEstado(DominioTransaccion.ERROR);
				dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR,"tipo comprobante no enviada"));
				return dto;
			}
			if (tipo.equals(CorrelativoTransaccion.TIPO_CORRELATIVO_ANIO)) {
				if (UString.esNuloVacio(dto.getAnio())) {
					dto.setTransaccionEstado(DominioTransaccion.ERROR);
					dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR,"anio no enviado"));
					return dto;
				}
			}
			
			if(UString.estaVacio(dto.getAnio())) {
				dto = correlativosmastDao.obtenerCorrelativo(usu,dto);
			}else {
				dto = correlativosmastDao.obtenerCorrelativoAnio(usu,dto);	
			}			
				
		} catch (Exception e) {
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
			e.printStackTrace();
		}		
		return dto;
	}
	
	/*
	 * LEONARDO
	 * LISTADO PAGINADO GENERAL
	 * 
	 * */
	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunCorrelativosmast filtro) throws Exception {
		logger.debug("CorrelativosmastRest.listarPaginado");
		DominioPaginacion paginacion = correlativosmastDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}
	
	/*
	 * LEONARDO
	 * OBTIENE OBJETO CORRELATIVOMAST
	 * 
	 * */
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCorrelativosmast> obtenerDto(@RequestBody DtoComunCorrelativosmast pk) throws Exception {
		logger.debug("CorrelativosmastRest.obtenerDto");
		DtoComunCorrelativosmast dto = correlativosmastDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunCorrelativosmast>(dto,HttpStatus.OK);
	}
	
	/*
	 * LEONARDO
	 * REGISTRA CORRELATIVOMAST
	 * 
	 * */
	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCorrelativosmast> registrar(@RequestBody DtoComunCorrelativosmast dto) throws Exception {
		logger.debug("CorrelativosmastRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunCorrelativosmast>(dto, HttpStatus.CREATED);
	}
	
	/*
	 * LEONARDO
	 * ACTUALIZA CORRELATIVOMAST
	 * 
	 * */
	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCorrelativosmast> actualizar(@RequestBody DtoComunCorrelativosmast dto) throws Exception {
		logger.debug("CorrelativosmastRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunCorrelativosmast>(dto, HttpStatus.OK);
	}
	
	/*
	 * LEONARDO
	 * ELIMINA CORRELATIVOMAST
	 * 
	 * */
	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCorrelativosmast> eliminar(@RequestBody DtoComunCorrelativosmast dto) throws Exception {
		logger.debug("CorrelativosmastRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunCorrelativosmast>(dto,HttpStatus.OK);
	}
	
	/*
	 * LEONARDO
	 * LISTA TIPOS (COMPROBANTEMAST)
	 * 
	 * */
	@Transactional
	@GetMapping(value = "/listarTipos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarTipos() {	        	            
		logger.debug("listar TIPOS");
		List datos = this.listarPorQuery(DtoTabla.class, "correlativosmast.listarTipos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/*
	 * LEONARDO
	 * EXPORTAR LISTA CORRELATIVOMAST
	 * 
	 * */
	@Transactional
	@PostMapping(value = "/exportarCorrelativos", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarCorrelativos(HttpServletResponse response, @RequestBody FiltroComunCorrelativosmast filtro) throws Exception
	 {
		
		DominioPaginacion paginacion = correlativosmastDao.exportarParametros(filtro);
		String[] arrColumnas = new String[] { "tipocomprobante", "serie", "descripcion", "companiacodigo","correlativonumero","estadodescripcion"}; 
		String[] arrCabecera = new String[] {"Tipo","Serie","Descripci\u00f3n","Compa\u00F1\u00EDa","\u00DAltimo Nro. Generado","Estado"};
		
		DtoWhExportar dtoExportar=new DtoWhExportar();
		dtoExportar.setTitulo("Listado de N\u00FAmeros Correlativos");
		dtoExportar.setTipoExpotar(filtro.getTipoexportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setPaginacion(paginacion);
			
		servicio.exportarInformacionWh( response,dtoExportar);
	}
	
	/*
	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCorrelativosmast> validar(@Validated @PathVariable String accion, @RequestBody DtoComunCorrelativosmast dto) throws Exception {
		logger.debug("CorrelativosmastRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunCorrelativosmast>(dto, HttpStatus.OK);
	}
	@Transactional
	@PutMapping(value="/obtenerdtoporid", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCorrelativosmast> obtenerDtoPorId(@RequestBody DtoComunCorrelativosmast pk) throws Exception {
		logger.debug("CorrelativosmastRest.obtenerDtoPorId");
		DtoComunCorrelativosmast dto = correlativosmastDao.obtenerDtoPorId(pk);
		if (dto==null) {
		    dto = new DtoComunCorrelativosmast();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return new ResponseEntity<DtoComunCorrelativosmast>(dto,HttpStatus.OK);
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return new ResponseEntity<DtoComunCorrelativosmast>(dto,HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody DtoComunCorrelativosmast filtro) throws Exception {
		logger.debug("CorrelativosmastRest.listarPaginado");
		DominioPaginacion paginacion = correlativosmastDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCorrelativosmast> anular(@RequestBody DtoComunCorrelativosmast dto) throws Exception {
		logger.debug("CorrelativosmastRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunCorrelativosmast>(dto, HttpStatus.OK);
	}
	*/
	
	
	@ApiOperation(notes = "Generar correlativo cadena. Entrada: DtoComunCorrelativosmast. Retorno: String",					
			nickname="CORRELATIVOS_MAST_CGENST", value = "Generar correlativo cadena", tags = {"CORRELATIVOS_MAST", "GENERAR"})
	@Transactional
	@PutMapping(value = "/generarcorrelativoconaniocadena",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> generarCorrelativoConAnioCadena(@RequestBody DtoComunCorrelativosmast bean) throws Exception {
		logger.debug("generarcorrelativoconaniocadena");
		CorrelativoTransaccion dto = new CorrelativoTransaccion(bean.getCompaniacodigo(),bean.getTipocomprobante(),bean.getSerie());
		dto.setAnio(bean.getAnio());
		
		dto.setCerosIzquierda(UBigDecimal.obtenerValorSinNulo(new BigDecimal(bean.getAuxCerosIzquierda())).intValue());
		dto = obtenercorrelativoCore(CorrelativoTransaccion.TIPO_CORRELATIVO_ANIO,dto);
		if (!dto.getTransaccionEstado().equals(DominioTransaccion.OK)) {
			logger.error(dto.getTransaccionMensajesCadena());
			dto.setNumeroGenerado(null);
		}
		return new ResponseEntity<String>(dto.getNumeroGeneradoCadena(), HttpStatus.OK);
	}
}
