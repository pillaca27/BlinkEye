package net.royal.spring.logistica.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
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
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.logistica.dominio.dto.DtoComunWhItemmast;
import net.royal.spring.logistica.dominio.filtro.FiltroComunWhItemmast;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrCursodescripcion;

@RestController
@RequestMapping("/spring/logistica/whitemmastcomun")
@CrossOrigin(origins = "*")
public class WhItemmastComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(WhItemmastComunRest.class);
	
	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}
	
	public WhItemmastComunRest() {
		super("whitemmast");
	}
	
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhItemmast> obtenerdto(@RequestBody DtoComunWhItemmast pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunWhItemmast dto = obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunWhItemmast>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/obteneritemtipo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerItemtipo(@RequestBody DtoComunWhItemmast pk) throws Exception {
		logger.debug("obtenerItemtipo");
		DtoComunWhItemmast dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getItemtipo(),HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/obtenerunidadcodigo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerUnidadcodigo(@RequestBody DtoComunWhItemmast pk) throws Exception {
		logger.debug("obtenerunidadcodigo");
		DtoComunWhItemmast dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getUnidadcodigo(),HttpStatus.OK);
	}
	
	public DtoComunWhItemmast obtenerDtoCore(DtoComunWhItemmast pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_item", String.class, pk.getItem()));
		List datos = this.listarPorQuery(DtoComunWhItemmast.class, "whitemmast.obtenerdto",parametros);
		DtoComunWhItemmast dto;
		if (datos.size()==1) {
			dto = (DtoComunWhItemmast)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunWhItemmast();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhItemmast>> listardtofiltros(@RequestBody DtoComunWhItemmast filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.estaVacio(filtro.getItem()))
            filtro.setItem(null);
        if (UString.estaVacio(filtro.getDescripcionlocal()))
            filtro.setDescripcionlocal(null);
        else
        	filtro.setDescripcionlocal(filtro.getDescripcionlocal().toUpperCase());
        if (UString.estaVacio(filtro.getEstado()))
            filtro.setEstado(null);
        parametros.add(new DominioParametroPersistencia("p_item", BigDecimal.class, filtro.getItem()));
        parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunWhItemmast.class, "whitemmast.listarfiltros", parametros);
		return new ResponseEntity<List<DtoComunWhItemmast>>(datos, HttpStatus.OK); 
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de items por filtro | Parametro de entrada DtoTabla(codigo, descripcion) | Parametros de salida: Listado DtoTabla(codigo, descripcion)", 
			value = "WH-ITEM-CLISTA", tags = {"LOGISTICA", "ITEM"})
	@Transactional
	@PutMapping(value = "/listarporcodigonombre",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhItemmast>> listarPorCodigoNombre(@RequestBody DtoComunWhItemmast filtro)
    {
		logger.debug("listar filtro");		
		if (UString.esNuloVacio(filtro.getItem()));
			filtro.setItem(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()));
			filtro.setDescripcionlocal(null);		
		List<DominioParametroPersistencia> parametros=new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getItem()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getDescripcionlocal()));		
		List lst = this.listarPorQuery(DtoComunWhItemmast.class, "whitemmast.listarPorCodigoNombre",parametros);
		logger.debug(lst.size());
		return new ResponseEntity<List<DtoComunWhItemmast>>(lst, HttpStatus.OK);
    }
	
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de items por filtro | Parametro de entrada DtoTabla(codigo, descripcion) | Parametros de salida: Listado DtoTabla(codigo, descripcion)", 
			value = "WH-ITEM-CLIACT", tags = {"LOGISTICA", "ITEM"})
	@Transactional
	@PutMapping(value = "/listarporcodigonombreactivo",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhItemmast>> listarPorCodigoNombreActivo(@RequestBody DtoComunWhItemmast filtro)
    {
		logger.debug("listar filtro");		
		if (UString.esNuloVacio(filtro.getItem()));
			filtro.setItem(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()));
			filtro.setDescripcionlocal(null);		
		List<DominioParametroPersistencia> parametros=new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getItem()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getDescripcionlocal()));		
		List lst = this.listarPorQuery(DtoComunWhItemmast.class, "whitemmast.listarPorCodigoNombreActivo",parametros);
		logger.debug(lst.size());
		return new ResponseEntity<List<DtoComunWhItemmast>>(lst, HttpStatus.OK);
    }
	
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado filtrado de items | Parametro de entrada DtoWhItemmastFiltroPaginado(item, descripcionlocal) | Parametros de salida: Listado DtoTabla(item, descripcionlocal)", 
			value = "WH-ITEM-CLIPAG", tags = {"LOGISTICA", "ITEM"})
	@Transactional
	@PutMapping(value = "/listarporcodigonombrepaginado",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPorCodigoNombrePaginado(@RequestBody FiltroComunWhItemmast filtro)
    {
		logger.debug("listar filtro paginado");		
		if (UString.esNuloVacio(filtro.getItem()))
			filtro.setItem(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros=new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getItem()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getDescripcionlocal()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));		
		Integer contador = this.contar("whitemmast.paginadoContar", parametros);
        List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "whitemmast.paginadolistar", DtoComunWhItemmast.class);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
        filtro.getPaginacion().setPaginacionListaResultado(lista);
        logger.debug(contador);
        logger.debug(lista.size());        
		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
	@ApiOperation(notes = "-Descripcion: Listado filtrado de items | Parametro de entrada DtoWhItemmastFiltroPaginado(item, descripcionlocal, tipo, linea, familia) | Parametros de salida: Listado DtoTabla(item, descripcionlocal)", 
			value = "WH-ITEM-CLIPAG", tags = {"LOGISTICA", "ITEM"})
	@Transactional
	@PutMapping(value = "/listarporcodigonombrepaginadotipo",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarporcodigonombrepaginadotipo(@RequestBody FiltroComunWhItemmast filtro)
    {
		logger.debug("listar filtro paginado");		
		if (UString.esNuloVacio(filtro.getItem()))
			filtro.setItem(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		if (UString.esNuloVacio(filtro.getTipo()))
			filtro.setTipo(null);
		if (UString.esNuloVacio(filtro.getFamilia()))
			filtro.setFamilia(null);
		if (UString.esNuloVacio(filtro.getLinea()))
			filtro.setLinea(null);
		if (UString.esNuloVacio(filtro.getSubfamilia()))
			filtro.setSubfamilia(null);
		if (UString.esNuloVacio(filtro.getCodigointerno()))
			filtro.setCodigointerno(null);
		
		List<DominioParametroPersistencia> parametros=new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getItem()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getDescripcionlocal()));
		parametros.add(new DominioParametroPersistencia("p_tipo", String.class, filtro.getTipo()));		
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, filtro.getLinea()));	
		parametros.add(new DominioParametroPersistencia("p_familia", String.class, filtro.getFamilia()));	
		parametros.add(new DominioParametroPersistencia("p_subfamilia", String.class, filtro.getSubfamilia()));	
		parametros.add(new DominioParametroPersistencia("p_codigointerno", String.class, filtro.getCodigointerno()));	
		
		Integer contador = this.contar("whitemmast.itemspaginadoContar", parametros);
        List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "whitemmast.itemspaginadolistar", DtoComunWhItemmast.class);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
        filtro.getPaginacion().setPaginacionListaResultado(lista);
        logger.debug(contador);
        logger.debug(lista.size());        
		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
}
