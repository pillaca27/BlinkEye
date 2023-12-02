package net.royal.spring.core.rest;

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
import net.royal.spring.core.dominio.dto.DtoComunUnidadesmast;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/unidadesmastcomun")
@CrossOrigin(origins = "*")
public class UnidadesmastComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(UnidadesmastComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public UnidadesmastComunRest() {
		super("unidadesmast");
	}
	
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar");
		List datos = this.listarPorQuery(DtoTabla.class, "unidadesmast.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "unidadesmast.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener tabla. Entrada: DtoTabla: codigo. Retorno: DtoTabla",					
			nickname="UNIDADESMAST_COBT", value = "Obtener tabla", tags = {"UNIDADESMAST", "OBTENER"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_unidadcodigo", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "unidadesmast.obtenerTabla", parametros);
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
		parametros.add(new DominioParametroPersistencia("p_unidadcodigo", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcioncorta", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "unidadesmast.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener dto. Entrada: DtoComunUnidadesmast. Retorno: DtoComunUnidadesmast",					
			nickname="UNIDADESMAST_COBTDTO", value = "Obtener tabla", tags = {"UNIDADESMAST", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunUnidadesmast> obtenerDto(@RequestBody DtoComunUnidadesmast pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunUnidadesmast dto = obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunUnidadesmast>(dto,HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener dto descripcion corta. Entrada: DtoComunUnidadesmast. Retorno: String",					
			nickname="UNIDADESMAST_COBTDTODES", value = "Obtener dto descripcion corta", tags = {"UNIDADESMAST", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdescripcioncorta", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerDescripcioncorta(@RequestBody DtoComunUnidadesmast pk) throws Exception {
		logger.debug("obtenerdescripcioncorta");
		DtoComunUnidadesmast dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getDescripcioncorta(),HttpStatus.OK);
	}
	
	public DtoComunUnidadesmast obtenerDtoCore(DtoComunUnidadesmast pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_unidadcodigo", String.class, pk.getUnidadcodigo()));
		List datos = this.listarPorQuery(DtoComunUnidadesmast.class, "unidadesmast.obtenerDto",parametros);
		DtoComunUnidadesmast dto;
		if (datos.size()==1) {
			dto = (DtoComunUnidadesmast)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunUnidadesmast();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunUnidadesmast>> listarDtoFiltros(@RequestBody DtoComunUnidadesmast filtro) {
		logger.debug("listardtofiltros");
        List datos = listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunUnidadesmast>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@GetMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunUnidadesmast>> listarDtoActivos() {
		logger.debug("listardtofiltros");
		DtoComunUnidadesmast filtro=new DtoComunUnidadesmast();
		filtro.setEstado("A");
        List datos = listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunUnidadesmast>>(datos, HttpStatus.OK);
	}
	
	public List<DtoComunUnidadesmast> listarDtoCore(DtoComunUnidadesmast filtro) {
		if (UString.esNuloVacio(filtro.getUnidadcodigo()))
			filtro.setUnidadcodigo(null);		
		if (UString.esNuloVacio(filtro.getDescripcioncorta()))
			filtro.setDescripcioncorta(null);
		else
			filtro.setDescripcioncorta(filtro.getDescripcioncorta().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_unidadcodigo", String.class, filtro.getUnidadcodigo()));
        parametros.add(new DominioParametroPersistencia("p_descripcioncorta", String.class, filtro.getDescripcioncorta()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunUnidadesmast.class, "unidadesmast.listardtofiltros", parametros);
        logger.debug(datos.size());
		return datos;
	}
	
}
