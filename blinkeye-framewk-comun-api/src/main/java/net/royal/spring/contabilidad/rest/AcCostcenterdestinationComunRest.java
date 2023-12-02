package net.royal.spring.contabilidad.rest;

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
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcAfecompany;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcenterdestination;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/contabilidad/accostcenterdestinationcomun")
@CrossOrigin(origins = "*")
public class AcCostcenterdestinationComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(AcCostcenterdestinationComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AcCostcenterdestinationComunRest() {
		super("accostcenterdestination");
	}
	
	@ApiOperation(notes = "Listar dto . Retorno: List DtoTabla ",					
			nickname="AC_CASHFLOWMST_CLIST", value = "Listar dto", tags = {"AC_COSTCENTERDESTINATION", "LISTAR"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar");
		List datos = this.listarPorQuery(DtoTabla.class, "accostcenterdestination.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@ApiOperation(notes = "Listar activos. Retorno: List DtoTabla ",					
			nickname="AC_COSTCENTERDESTINATION_CLISTACT", value = "Listar dto", tags = {"AC_COSTCENTERDESTINATION", "LISTAR"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "accostcenterdestination.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener tabla: Codigo. Retorno: DtoTabla ",					
			nickname="AC_COSTCENTERDESTINATION_COBT", value = "Obtener tabla por codigo", tags = {"AC_COSTCENTERDESTINATION", "OBTENER"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		logger.debug("obtenertabla");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_costcenterdestination", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "accostcenterdestination.obtenerTabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	@ApiOperation(notes = "Listar por filtros: codigo, nombre, estadoId. Retorno: List DtoTabla ",					
			nickname="AC_COSTCENTERDESTINATION_CLISTFIL", value = "Listar por filtros", tags = {"AC_COSTCENTERDESTINATION", "LISTAR"})
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
		parametros.add(new DominioParametroPersistencia("p_costcenterdestination", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_localname", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_status", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "accostcenterdestination.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
		
	@ApiOperation(notes = "Obtener dto: costcenterdestination. Retorno: DtoComunAcCostcenterdestination",					
			nickname="AC_COSTCENTERDESTINATION_COBTDTO", value = "Listar por filtros", tags = {"AC_COSTCENTERDESTINATION", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcCostcenterdestination> obtenerDto(@RequestBody DtoComunAcCostcenterdestination pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_costcenterdestination", String.class, pk.getCostcenterdestination()));
		List datos = this.listarPorQuery(DtoComunAcCostcenterdestination.class, "accostcenterdestination.obtenerDto",parametros);
		DtoComunAcCostcenterdestination dto;
		if (datos.size()==1) {
			dto = (DtoComunAcCostcenterdestination)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunAcCostcenterdestination();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunAcCostcenterdestination>(dto,HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Listar dto filtros. Retorno:List DtoComunAcCostcenterdestination",					
			nickname="AC_COSTCENTERDESTINATION_CLISTFIL", value = "Listar por filtros", tags = {"AC_COSTCENTERDESTINATION", "LISTAR"})
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAcCostcenterdestination>> listarDtoFiltros(@RequestBody DtoComunAcCostcenterdestination filtro) {
		logger.debug("listardtofiltros");
        List datos = listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunAcCostcenterdestination>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Listar dto activos. Retorno:List DtoComunAcCostcenterdestination",					
			nickname="AC_COSTCENTERDESTINATION_CLISTACT", value = "Listar por filtros", tags = {"AC_COSTCENTERDESTINATION", "LISTAR"})
	@Transactional
	@GetMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAcCostcenterdestination>> listarDtoActivos() {
		logger.debug("listardtoactivos");
		DtoComunAcCostcenterdestination filtro=new DtoComunAcCostcenterdestination();
		filtro.setStatus("A");
        List datos = listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunAcCostcenterdestination>>(datos, HttpStatus.OK);
	}
	
	public List<DtoComunAcCostcenterdestination> listarDtoCore(DtoComunAcCostcenterdestination filtro) {
		logger.debug("listarDtoCore");
		if (UString.esNuloVacio(filtro.getCostcenterdestination()))
			filtro.setCostcenterdestination(null);
		if (UString.esNuloVacio(filtro.getLocalname()))
			filtro.setLocalname(null);
		else
			filtro.setLocalname(filtro.getLocalname().toUpperCase());
		if (UString.esNuloVacio(filtro.getStatus()))
			filtro.setStatus(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_costcenterdestination", String.class, filtro.getCostcenterdestination()));
		parametros.add(new DominioParametroPersistencia("p_localname", String.class, filtro.getLocalname()));
        parametros.add(new DominioParametroPersistencia("p_status", String.class, filtro.getStatus()));
        List datos = this.listarPorQuery(DtoComunAcCostcenterdestination.class, "accostcenterdestination.listardtofiltros", parametros);
        logger.debug(datos.size());
		return datos;
	}
	
}
