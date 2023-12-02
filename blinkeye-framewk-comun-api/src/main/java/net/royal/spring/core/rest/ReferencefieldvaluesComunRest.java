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
import net.royal.spring.core.dominio.dto.DtoComunReferencefieldmst;
import net.royal.spring.core.dominio.dto.DtoComunReferencefieldvalues;
import net.royal.spring.core.dominio.dto.DtoComunTipodocumentocxp;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/referencefieldvaluescomun")
@CrossOrigin(origins = "*")
public class ReferencefieldvaluesComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(ReferencefieldvaluesComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public ReferencefieldvaluesComunRest() {
		super("referencefieldvalues");
	}
		
	
	@ApiOperation(notes = "Obtener tabla. Entrada: DtoComunReferencefieldvalues. Retorno: DtoComunReferencefieldvalues",					
			nickname="REFERENCEFIELDVALUES_COBT", value = "Obtener tabla", tags = {"REFERENCEFIELDVALUES", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunReferencefieldvalues> obtenerDto(@RequestBody DtoComunReferencefieldvalues pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunReferencefieldvalues dto = obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunReferencefieldvalues>(dto,HttpStatus.OK);
	}
	
	public DtoComunReferencefieldvalues obtenerDtoCore(DtoComunReferencefieldvalues pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_referencefield", String.class, pk.getReferencefield()));
        parametros.add(new DominioParametroPersistencia("p_referencevalue", String.class, pk.getReferencevalue()));
		List datos = this.listarPorQuery(DtoComunReferencefieldvalues.class, "referencefieldvalues.obtenerDto",parametros);
		DtoComunReferencefieldvalues dto;
		if (datos.size()==1) {
			dto = (DtoComunReferencefieldvalues)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunReferencefieldvalues();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	@Transactional
	@PutMapping(value="/obtenerdescription", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerDescription(@RequestBody DtoComunReferencefieldvalues pk) throws Exception {
		logger.debug("obtenerdescription");
		DtoComunReferencefieldvalues dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getDescription(),HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunReferencefieldvalues>> listarDtoFiltros(@RequestBody DtoComunReferencefieldvalues filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getReferencefield()))
			filtro.setReferencefield(null);	
		if (UString.esNuloVacio(filtro.getReferencevalue()))
			filtro.setReferencevalue(null);	
		if (UString.esNuloVacio(filtro.getDescription()))
			filtro.setDescription(null);
		else
			filtro.setDescription(filtro.getDescription().toUpperCase());
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_referencefield", String.class, filtro.getReferencefield()));
        parametros.add(new DominioParametroPersistencia("p_referencevalue", String.class, filtro.getReferencevalue()));
        parametros.add(new DominioParametroPersistencia("p_description", String.class, filtro.getDescription()));
        List datos = this.listarPorQuery(DtoComunReferencefieldvalues.class, "referencefieldvalues.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunReferencefieldvalues>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtoporreferencefield", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunReferencefieldvalues>> listarDtoPorReferencefield(@RequestBody DtoComunReferencefieldvalues filtro) {
		logger.debug("listardtoporreferencefield");
		if (UString.esNuloVacio(filtro.getReferencefield()))
			filtro.setReferencefield(null);	
		if (UString.esNuloVacio(filtro.getReferencevalue()))
			filtro.setReferencevalue(null);	
		if (UString.esNuloVacio(filtro.getDescription()))
			filtro.setDescription(null);
		else
			filtro.setDescription(filtro.getDescription().toUpperCase());
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_referencefield", String.class, filtro.getReferencefield()));
        List datos = this.listarPorQuery(DtoComunReferencefieldvalues.class, "referencefieldvalues.listardtoporreferencefield", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunReferencefieldvalues>>(datos, HttpStatus.OK);
	}
	
	
	@Transactional
	@PutMapping(value = "/obtenerreferencefield", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunReferencefieldmst> obtenerreferencefield(@RequestBody DtoComunReferencefieldvalues filtro) {
		logger.debug("listardtoporreferencefield");
		if (UString.esNuloVacio(filtro.getReferencefield()))
			filtro.setReferencefield(null);	
		
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_referencefield", String.class, filtro.getReferencefield()));
        List datos = this.listarPorQuery(DtoComunReferencefieldmst.class, "referencefieldvalues.obtenerreferencefield", parametros);
        logger.debug(datos.size());
        
        if(datos.size() > 0) {
        	return new ResponseEntity<DtoComunReferencefieldmst>((DtoComunReferencefieldmst) datos.get(0), HttpStatus.OK);	
        }
        
        return new ResponseEntity<DtoComunReferencefieldmst>(new DtoComunReferencefieldmst(), HttpStatus.OK);
        
		
	}
	
	
}
