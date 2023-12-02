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
import net.royal.spring.core.dominio.dto.DtoComunTipodocumentocxp;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/tipodocumentocxpcomun")
@CrossOrigin(origins = "*")
public class TipodocumentocxpComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(TipodocumentocxpComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public TipodocumentocxpComunRest() {
		super("tipodocumentocxp");
	}
	
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar");
		List datos = this.listarPorQuery(DtoTabla.class, "tipodocumentocxp.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "tipodocumentocxp.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtenr Tabla por tipo documento cxp. Entrada: DtoTabla: codigo. Retorno: DtoTabla",					
			nickname="TIPODOCUMENTOCXP_COBT", value = "Obtenr tipo documento cxp", tags = {"TIPODOCUMENTOCXP", "OBTENER"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		logger.debug("obtenertabla");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_tipodocumento", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "tipodocumentocxp.obtenerTabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	@Transactional
	@PutMapping(value = "/listarfiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarFiltros(@RequestBody DtoTabla filtro) {
		logger.debug("listarfiltros");
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
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "tipodocumentocxp.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener formacio edicion. Entrada: DtoComunTipodocumentocxp: codigo. Retorno: String",					
			nickname="TIPODOCUMENTOCXP_COBTFOR", value = "Obtener formacio edicion", tags = {"TIPODOCUMENTOCXP", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerformatoedicion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerFormatoedicion(@RequestBody DtoComunTipodocumentocxp pk) throws Exception {
		logger.debug("obtenerformatoedicion");
		DtoComunTipodocumentocxp dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getFormatoedicion(),HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtenr dto. Entrada: DtoComunTipodocumentocxp: codigo. Retorno: DtoComunTipodocumentocxp",					
			nickname="TIPODOCUMENTOCXP_COBDTO", value = "Obtenr dto", tags = {"TIPODOCUMENTOCXP", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunTipodocumentocxp> obtenerDto(@RequestBody DtoComunTipodocumentocxp pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunTipodocumentocxp dto = obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunTipodocumentocxp>(dto,HttpStatus.OK);
	}
	
	public DtoComunTipodocumentocxp obtenerDtoCore(DtoComunTipodocumentocxp pk) throws Exception {
		logger.debug("obtenerDtoCore");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipodocumento", String.class, pk.getTipodocumento()));
		List datos = this.listarPorQuery(DtoComunTipodocumentocxp.class, "tipodocumentocxp.obtenerDto",parametros);
		DtoComunTipodocumentocxp dto;
		if (datos.size()==1) {
			dto = (DtoComunTipodocumentocxp)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunTipodocumentocxp();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunTipodocumentocxp>> listarDtoFiltros(@RequestBody DtoComunTipodocumentocxp filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getTipodocumento()))
			filtro.setTipodocumento(null);		
		if (UString.esNuloVacio(filtro.getDescripcion()))
			filtro.setDescripcion(null);
		else
			filtro.setDescripcion(filtro.getDescripcion().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_tipodocumento", String.class, filtro.getTipodocumento()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunTipodocumentocxp.class, "tipodocumentocxp.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunTipodocumentocxp>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@GetMapping(value = "/listardtoactivosclasificacionfeob", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunTipodocumentocxp>> listarDtoActivosClasificacionFeOb() {
		logger.debug("listardtoactivosclasificacionfeob");
        List datos = this.listarPorQuery(DtoComunTipodocumentocxp.class, "tipodocumentocxp.listarDtoActivosClasificacionFeOb");
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunTipodocumentocxp>>(datos, HttpStatus.OK);
	}
}
