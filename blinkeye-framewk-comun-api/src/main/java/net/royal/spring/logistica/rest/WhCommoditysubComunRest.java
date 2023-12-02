package net.royal.spring.logistica.rest;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dominio.dto.DtoComunTipocambiomast;
import net.royal.spring.core.dominio.filtro.FiltroComunCommoditySub;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.TipoCambioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.logistica.dao.impl.WhCommodityDaoImpl;
import net.royal.spring.logistica.dominio.dto.DtoComunWhClasificacionrequorden;
import net.royal.spring.logistica.dominio.dto.DtoComunWhCommoditysub;
import net.royal.spring.logistica.dominio.dto.DtoComunWhItemmast;
import net.royal.spring.logistica.dominio.dto.DtoComunWhCommodity;
import net.royal.spring.logistica.dominio.filtro.FiltroComunWhItemmast;
import net.royal.spring.logistica.servicio.impl.WhCommoditysubServicioImpl;

@RestController
@RequestMapping("/spring/logistica/whcommoditysubcomun")
@CrossOrigin(origins = "*")
public class WhCommoditysubComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(WhCommoditysubComunRest.class);

	@Autowired
	private WhCommodityDaoImpl commodityDao;
	
	@Autowired
	private WhCommoditysubServicioImpl commodiyService;
	
	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WhCommoditysubComunRest() {
		super("whcommoditysub");
	}
	
	@ApiOperation(notes = "Obtener dto. Entrada: DtoComunWhCommoditysub: Commodity01, Commodity02. Retorno: DtoComunWhCommoditysub",					
			nickname="WH_COMMODITYSUB-COBT", value = "Obtener dto", tags = {"WH_COMMODITYSUB", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhCommoditysub> obtenerDto(@RequestBody DtoComunWhCommoditysub pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_commodity01", String.class, pk.getCommodity01()));
		parametros.add(new DominioParametroPersistencia("p_commodity02", String.class, pk.getCommodity02()));
		List datos = this.listarPorQuery(DtoComunWhCommoditysub.class, "whcommoditysub.obtenerDto",parametros);
		DtoComunWhCommoditysub dto;
		if (datos.size()==1) {
			dto = (DtoComunWhCommoditysub)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunWhCommoditysub();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunWhCommoditysub>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhCommoditysub>> listarDtoFiltros(@RequestBody DtoComunWhCommoditysub filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getCommodity01()))
			filtro.setCommodity01(null);	
		if (UString.esNuloVacio(filtro.getCommodity02()))
			filtro.setCommodity02(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		else
			filtro.setDescripcionlocal(filtro.getDescripcionlocal().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_commodity01", String.class, filtro.getCommodity01()));
        parametros.add(new DominioParametroPersistencia("p_commodity02", String.class, filtro.getCommodity02()));
        parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunWhCommoditysub.class, "whcommoditysub.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunWhCommoditysub>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtoporcommodity", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhCommoditysub>> listarDtoPorCommodity(@RequestBody DtoComunWhCommoditysub filtro) {
		logger.debug("listardtoporcommodity");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_commodity", String.class, filtro.getCommodity()));
        List datos = this.listarPorQuery(DtoComunWhCommoditysub.class, "whcommoditysub.listardtoporcommodity", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunWhCommoditysub>>(datos, HttpStatus.OK);
	}
	
	
	//EDGAR LUQUE - SELECTOR NUEVO DE COMMODITY
	@Transactional
	@PutMapping(value = "/listarCommoditySub", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarCommoditySub(@RequestBody FiltroComunCommoditySub filtro) {
		logger.debug("listarCommoditySub");

		if (UString.esNuloVacio(filtro.getClasificacion()))
			filtro.setClasificacion(null);

		if (UString.esNuloVacio(filtro.getDescripcion1()))
			filtro.setDescripcion1("");
		
	
		List<DominioParametroPersistencia> parametros=new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_clasificacion", String.class, filtro.getClasificacion()));
		parametros.add(new DominioParametroPersistencia("p_descri", String.class, filtro.getDescripcion1()));	
	
		if (!UString.esNuloVacio(filtro.getClasificacion())) {
			if(!filtro.getClasificacion().equals("SER")) {
				filtro.setClasificacion("SER");
				Integer contador = this.contar("whcommoditysub.contarCommoditySubSER", parametros);
		        List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "whcommoditysub.listarCommoditySubSER", DtoComunWhCommoditysub.class);
		        filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
		        filtro.getPaginacion().setPaginacionListaResultado(lista);
		        logger.debug(contador.intValue());
		        logger.debug(lista.size()); 
		        
		        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
			}else {
				System.out.println("VALID");
			}
		}
			Integer contador = this.contar("whcommoditysub.contarCommoditySub", parametros);
	        List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "whcommoditysub.listarCommoditySub", DtoComunWhCommoditysub.class);
	        filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
	        filtro.getPaginacion().setPaginacionListaResultado(lista);
	        logger.debug(contador.intValue());
	        logger.debug(lista.size());        
		
		
	        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
	}
	
	//EDGAR LUQUE -  COMMODITY
	@Transactional
	@PutMapping(value = "/listarCommodity", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarLineaFamilia(@RequestBody FiltroComunCommoditySub filtro) {
		logger.debug("listarCommodity");

		if (UString.esNuloVacio(filtro.getClasificacion()))
			filtro.setClasificacion(null);
		if (UString.esNuloVacio(filtro.getDescripcion1()))
			filtro.setDescripcion1("");
	
		List<DominioParametroPersistencia> parametros=new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getClasificacion()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion1()));
		
		Integer contador = this.contar("whcommoditysub.contarCommodity", parametros);
        List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "whcommoditysub.listarCommodity", DtoComunWhCommoditysub.class);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
        filtro.getPaginacion().setPaginacionListaResultado(lista);
        logger.debug(contador.intValue());
        logger.debug(lista.size());        
		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
	}
	
	//EDGAR LUQUE -  COMMODITY OBTENER POR ID
	@Transactional
	@PostMapping(value="/obtenerdtoCommodity", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhCommodity> obtenerDto(@RequestBody DtoComunWhCommodity pk) throws Exception {
		logger.debug("obtenerDto");
		DtoComunWhCommodity dto = null;
		if (pk==null) {
		    dto = new DtoComunWhCommodity();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no enviado"));
		    return new ResponseEntity<DtoComunWhCommodity>(dto,HttpStatus.OK);
		}
				
			dto = commodityDao.obtenerDto(pk);

		if (dto==null) {
		    dto = new DtoComunWhCommodity();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return new ResponseEntity<DtoComunWhCommodity>(dto,HttpStatus.OK);
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return new ResponseEntity<DtoComunWhCommodity>(dto,HttpStatus.OK);
	}
	
	//EDGAR LUQUE - COMMODITY/COMMODITYSUB REGISTER
	@Transactional
	@PostMapping(value = "/registrarCommodity",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhCommodity> registrarCommodity(@RequestBody DtoComunWhCommodity dto) throws Exception {
		logger.debug("registrarCommodity");
		dto =  commodiyService.coreInsertarCommodity(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunWhCommodity>(dto, HttpStatus.CREATED);
		
	}
	
	//EDGAR LUQUE - COMMODITY/COMMODITYSUB COPIAR
	@Transactional
	@PostMapping(value = "/copiarCommodity",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhCommodity> copiarCommodity(@RequestBody DtoComunWhCommodity dto) throws Exception {
		logger.debug("copiarCommodity");
		dto =  commodiyService.coreCopiarCommodity(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunWhCommodity>(dto, HttpStatus.CREATED);
		
	}
	
	//EDGAR LUQUE - COMMODITY/COMMODITYSUB UPDATE
	@Transactional
	@PutMapping(value = "/actualizarCommodity",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhCommodity> actualizarCommodity(@RequestBody DtoComunWhCommodity dto) throws Exception {
		logger.debug("actualizarCommodity");
		dto =  commodiyService.coreActualizarCommodity(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunWhCommodity>(dto, HttpStatus.OK);
		
	}
	
	//EDGAR LUQUE - COMMODITY/COMMODITYSUB DELETE
	@Transactional
	@PostMapping(value = "/eliminarCommodity", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhCommodity> eliminar(@RequestBody DtoComunWhCommodity dto) throws Exception {
		logger.debug("eliminarCommodity");
		dto =  commodiyService.coreEliminarCommodity(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunWhCommodity>(dto,HttpStatus.OK);
	}
	
	//EDGAR LUQUE -  CLASIFICACION DE ACTIVOS - COMMODITIES
	@Transactional
	@PutMapping(value = "/listarClasificacionCommodities", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarClasificacionCommodities(@RequestBody FiltroComunCommoditySub filtro) {
		logger.debug("listarCommodity");

		if (UString.esNuloVacio(filtro.getClasificacion()))
			filtro.setClasificacion("");

	
		List<DominioParametroPersistencia> parametros=new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getClasificacion()));
		
		Integer contador = this.contar("whcommoditysub.contarCommodityClasificacion", parametros);
        List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "whcommoditysub.listarCommodityClasificacion", DtoTabla.class);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
        filtro.getPaginacion().setPaginacionListaResultado(lista);
        logger.debug(contador.intValue());
        logger.debug(lista.size());        
		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping(value = "/exportarCommodities", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarCommodities(HttpServletResponse response, @RequestBody FiltroComunCommoditySub filtro) throws Exception
	 {
		
		DominioPaginacion paginacion = commodityDao.exportarCommodities(filtro);
		String[] arrCabecera = new String[] {"Commodity","Descripci\u00f3n Local","Estado"};
		String[] arrColumnas = new String[] {"commodity01","descripcionlocal","estadodescripcion"}; 
		

		DtoWhExportar dtoExportar=new DtoWhExportar();
		dtoExportar.setTitulo("Listado de Commodities");
		dtoExportar.setTipoExpotar(filtro.getTipoexportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setPaginacion(paginacion);
			
		commodiyService.exportarInformacionWh(response,dtoExportar);
	}
}
