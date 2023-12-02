package net.royal.spring.comercial.rest;

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
import net.royal.spring.comercial.dominio.dto.DtoComunCoTipodocumento;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/comercial/cotipodocumentocomun")
@CrossOrigin(origins = "*")
public class CoTipodocumentoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(CoTipodocumentoComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public CoTipodocumentoComunRest() {
		super("cotipodocumento");
	}
	
	@ApiOperation(notes = "Listar. Retorno: DtoTabla",					
			nickname="CO_TIPODOCUMENTO_CLISTAR", value = "Listar dto", tags = {"CO_TIPODOCUMENTO", "LISTAR"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar");
		List datos = this.listarPorQuery(DtoTabla.class, "cotipodocumento.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@ApiOperation(notes = "Listar activos. Retorno: DtoTabla",					
			nickname="CO_TIPODOCUMENTO_CLISTARACT", value = "Listar dto activos", tags = {"CO_TIPODOCUMENTO", "LISTAR", "ACTIVOS"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "cotipodocumento.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener Dto por codigo de tipo de documento . Retorno: DtoTabla",					
			nickname="CO_TIPODOCUMENTO_COBT", value = "Obtener tabla", tags = {"CO_TIPODOCUMENTO", "OBTENER"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_tipodocumento", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "cotipodocumento.obtenerTabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	@ApiOperation(notes = "Listar por codigo, nombre, estadoId. Retorno: DtoTabla",					
			nickname="CO_TIPODOCUMENTO_CLISTARFIL", value = "Listar por filtros", tags = {"CO_TIPODOCUMENTO", "LISTAR"})
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
		parametros.add(new DominioParametroPersistencia("p_tipodocumento", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "cotipodocumento.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener DTO DtoComunCoTipodocumento . Retorno: DtoTabla",					
			nickname="CO_TIPODOCUMENTO_COBTDTO", value = "Obtener dto", tags = {"CO_TIPODOCUMENTO", "LISTAR"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCoTipodocumento> obtenerDto(@RequestBody DtoComunCoTipodocumento pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipodocumento", String.class, pk.getTipodocumento()));
		List datos = this.listarPorQuery(DtoComunCoTipodocumento.class, "cotipodocumento.obtenerDto",parametros);
		DtoComunCoTipodocumento dto;
		if (datos.size()==1) {
			dto = (DtoComunCoTipodocumento)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunCoTipodocumento();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunCoTipodocumento>(dto,HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Listar dto filtros: tipodocumento, descripcionlocal, estado . Retorno: DtoComunCoTipodocumento",					
			nickname="CO_TIPODOCUMENTO_CLISTARDTO", value = "Listar dto filtros", tags = {"CO_TIPODOCUMENTO", "LISTAR"})
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunCoTipodocumento>> listarDtoFiltros(@RequestBody DtoComunCoTipodocumento filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getTipodocumento()))
			filtro.setTipodocumento(null);		
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		else
			filtro.setDescripcionlocal(filtro.getDescripcionlocal().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_tipodocumento", String.class, filtro.getTipodocumento()));
        parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunCoTipodocumento.class, "cotipodocumento.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunCoTipodocumento>>(datos, HttpStatus.OK);
	}
	
}
