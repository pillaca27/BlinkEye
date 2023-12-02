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
import net.royal.spring.core.dominio.dto.DtoComunAfemst;
import net.royal.spring.core.dominio.filtro.FiltroComunAfemst;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrUnidadoperativa;

@RestController
@RequestMapping("/spring/core/afetypemstcomun")
@CrossOrigin(origins = "*")
public class AfetypemstComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(AfetypemstComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AfetypemstComunRest() {
		super("afetypemst");
	}
	
	@ApiOperation(notes = "Listar dto. Retorno: List DtoTabla",					
			nickname="AFE_TYPEMST_CLIST", value = "Listar dto", tags = {"AFE_TYPEMST", "LISTAR"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar");
		List datos = this.listarPorQuery(DtoTabla.class, "afetypemst.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Listar dto activos. Retorno: List DtoTabla",					
			nickname="AFE_TYPEMST_CLISTACT", value = "Listar dto activos", tags = {"AFE_TYPEMST", "LISTAR"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "afetypemst.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener tabla DtoTabla. Retorno: DtoTabla",					
			nickname="AFE_TYPEMST_COBT", value = "Obtener tabla DtoTabla", tags = {"AFE_TYPEMST", "OBTENER"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenertabla(@RequestBody DtoTabla filtro) { 
		logger.debug("obtenertabla");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_afetype", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "afetypemst.obtenerTabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	
	/***
	 * LUQUEE VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "Listar proyecto activos. Retorno: List DtoTabla",					
			nickname="AFE_TYPEMST_CLISTPRACT", value = "Obtener tabla DtoTabla", tags = {"AFE_TYPEMST", "LISTAR"})
	@Transactional
	@GetMapping(value = "/listaractivosProyecto", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivosProyecto() {	        	            
		logger.debug("listar listaractivosProyecto");
		List datos = this.listarPorQuery(DtoTabla.class, "afetypemst.listarActivosProyectos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
}
