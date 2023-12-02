package net.royal.spring.core.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dominio.dto.DtoComunImpuestos;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/impuestoscomun")
@CrossOrigin(origins = "*")
public class ImpuestosComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(ImpuestosComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public ImpuestosComunRest() {
		super("impuestos");
	}
	
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar");
		List datos = this.listarPorQuery(DtoTabla.class, "impuestos.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "impuestos.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_impuesto", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "impuestos.obtenerTabla", parametros);
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
		parametros.add(new DominioParametroPersistencia("p_impuesto", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "impuestos.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener impuesto. Entrada: DtoComunImpuestos. Retorno: DtoComunImpuestos",					
			nickname="IMPUESTOS_COBT", value = "Obtener impuesto", tags = {"IMPUESTOS", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunImpuestos> obtenerDto(@RequestBody DtoComunImpuestos pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_impuesto", String.class, pk.getImpuesto()));
		List datos = this.listarPorQuery(DtoComunImpuestos.class, "impuestos.obtenerDto",parametros);
		DtoComunImpuestos dto;
		if (datos.size()==1) {
			dto = (DtoComunImpuestos)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunImpuestos();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunImpuestos>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunImpuestos>> listarDtoFiltros(@RequestBody DtoComunImpuestos filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getImpuesto()))
			filtro.setImpuesto(null);		
		if (UString.esNuloVacio(filtro.getDescripcion()))
			filtro.setDescripcion(null);
		else
			filtro.setDescripcion(filtro.getDescripcion().toUpperCase());
		if (UString.esNuloVacio(filtro.getTipoimpuesto()))
			filtro.setTipoimpuesto(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_impuesto", String.class, filtro.getImpuesto()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
        parametros.add(new DominioParametroPersistencia("p_tipoimpuesto", String.class, filtro.getTipoimpuesto()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunImpuestos.class, "impuestos.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunImpuestos>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/obtenerdtofactorporcentaje", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BigDecimal> obtenerDtoFactorporcentaje(@RequestBody DtoComunImpuestos pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_impuesto", String.class, pk.getImpuesto()));
		List datos = this.listarPorQuery(DtoComunImpuestos.class, "impuestos.obtenerDto",parametros);
		DtoComunImpuestos dto;
		if (datos.size()==1) {
			dto = (DtoComunImpuestos)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunImpuestos();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<BigDecimal>(dto.getFactorporcentaje(),HttpStatus.OK);
	}
}
