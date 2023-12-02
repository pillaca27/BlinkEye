package net.royal.spring.logistica.rest;

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
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.logistica.dominio.dto.DtoComunWhClasificacionrequorden;

@RestController
@RequestMapping("/spring/logistica/whclasificacionrequordencomun")
@CrossOrigin(origins = "*")
public class WhClasificacionrequordenComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(WhClasificacionrequordenComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WhClasificacionrequordenComunRest() {
		super("whclasificacionrequorden");
	}
	
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar");
		List datos = this.listarPorQuery(DtoTabla.class, "whclasificacionrequorden.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "whclasificacionrequorden.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener tabla. Entrada: DtoTabla: codigo. Retorno: DtoTabla",					
			nickname="WH_CLASIFICACIONREQUORDEN-COBT", value = "Obtener tabla", tags = {"WH_CLASIFICACIONREQUORDEN", "OBTENER"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_clasificacion", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "whclasificacionrequorden.obtenerTabla", parametros);
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
		parametros.add(new DominioParametroPersistencia("p_clasificacion", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "whclasificacionrequorden.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener dto. Entrada: DtoComunWhClasificacionrequorden: Clasificacion. Retorno: DtoComunWhClasificacionrequorden",					
			nickname="WH_CLASIFICACIONREQUORDEN-COBT", value = "Obtener dto", tags = {"WH_CLASIFICACIONREQUORDEN", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhClasificacionrequorden> obtenerDto(@RequestBody DtoComunWhClasificacionrequorden pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_clasificacion", String.class, pk.getClasificacion()));
		List datos = this.listarPorQuery(DtoComunWhClasificacionrequorden.class, "whclasificacionrequorden.obtenerDto",parametros);
		DtoComunWhClasificacionrequorden dto;
		if (datos.size()==1) {
			dto = (DtoComunWhClasificacionrequorden)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunWhClasificacionrequorden();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunWhClasificacionrequorden>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhClasificacionrequorden>> listarDtoFiltros(@RequestBody DtoComunWhClasificacionrequorden filtro) {
		logger.debug("listardtofiltros");
        List datos = listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunWhClasificacionrequorden>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtoporclasificacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhClasificacionrequorden>> listarDtoPorClasificacion(@RequestBody DtoComunWhClasificacionrequorden filtro) {
		logger.debug("listardtoporclasificacion");
        List datos = listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunWhClasificacionrequorden>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@GetMapping(value = "/listarClasificacionesdisponibles", produces = MediaType.APPLICATION_JSON_VALUE)	    
	public ResponseEntity<List<DtoComunWhClasificacionrequorden>> listarClasificacionesdisponibles() {
		logger.debug("listarClasificacionesdisponibles");
        List datos = listarPorQuery(DtoComunWhClasificacionrequorden.class, "whclasificacionrequorden.listarClasificacionesdisponibles");
		return new ResponseEntity<List<DtoComunWhClasificacionrequorden>>(datos, HttpStatus.OK);
	}
	
	public List<DtoComunWhClasificacionrequorden> listarDtoCore(DtoComunWhClasificacionrequorden filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getClasificacion()))
			filtro.setClasificacion(null);		
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		else
			filtro.setDescripcionlocal(filtro.getDescripcionlocal().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_clasificacion", String.class, filtro.getClasificacion()));
        parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunWhClasificacionrequorden.class, "whclasificacionrequorden.listardtofiltros", parametros);
        logger.debug(datos.size());
		return datos;
	}
	
	@Transactional
	@GetMapping(value = "/listardtolicitacionrequesicion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhClasificacionrequorden>> listardtolicitacionrequesicion() {
		logger.debug("listardtolicitacionrequesicion");
        List datos = this.listarPorQuery(DtoComunWhClasificacionrequorden.class, "whclasificacionrequorden.listardtolicitacionrequesicion");
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunWhClasificacionrequorden>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@GetMapping(value = "/listardtorequesicion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhClasificacionrequorden>> listardtorequesicion() {
		logger.debug("listardtolicitacionrequesicion");
        List datos = this.listarPorQuery(DtoComunWhClasificacionrequorden.class, "whclasificacionrequorden.listardtorequesicion");
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunWhClasificacionrequorden>>(datos, HttpStatus.OK);
	}
	
}
