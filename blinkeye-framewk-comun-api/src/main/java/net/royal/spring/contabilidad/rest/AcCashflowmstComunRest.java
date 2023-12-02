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
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCashflowmst;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/contabilidad/accashflowmstcomun")
@CrossOrigin(origins = "*")
public class AcCashflowmstComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(AcCashflowmstComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AcCashflowmstComunRest() {
		super("accashflowmst");
	}
	
	
	@ApiOperation(notes = "Listar dto. Retorno: List Dto Tabla",					
			nickname="AC_CASHFLOWMST_CLIST", value = "LISTAR DTO", tags = {"AC_CASHFLOWMST", "LISTAR"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar");
		List datos = this.listarPorQuery(DtoTabla.class, "accashflowmst.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@ApiOperation(notes = "Listar dto activos. Retorno: List Dto Tabla",					
			nickname="AC_CASHFLOWMST_CLISTACT", value = "LISTAR DTO ACTIVOS", tags = {"AC_CASHFLOWMST", "LISTAR", "ACTIVOS"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "accashflowmst.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener tabla por codigo. Retorno: Dto Tabla",					
			nickname="AC_CASHFLOWMST_COBT", value = "LISTAR DTO ACTIVOS", tags = {"AC_CASHFLOWMST", "OBTENER"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_cashflowcode", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "accashflowmst.obtenerTabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	@ApiOperation(notes = "Listar por filtros codigo, nombre, estadoId. Retorno: Dto Tabla",					
			nickname="AC_CASHFLOWMST_CLISTRFIL", value = "LISTAR POR FILTROS", tags = {"AC_CASHFLOWMST", "LISTAR"})
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
		parametros.add(new DominioParametroPersistencia("p_cashflowcode", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_localname", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_status", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "accashflowmst.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener dto. Retorno: DtoComunAcCashflowmst",					
			nickname="AC_CASHFLOWMST_COBTDTO", value = "OBTENER DTO", tags = {"AC_CASHFLOWMST", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcCashflowmst> obtenerDto(@RequestBody DtoComunAcCashflowmst pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunAcCashflowmst dto = obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunAcCashflowmst>(dto,HttpStatus.OK);
	}
	
	
	@ApiOperation(notes = "Obtener Staturs. Retorno: Dto Tabla",					
			nickname="AC_CASHFLOWMST_COBTDTO", value = "OBTENER DTO", tags = {"AC_CASHFLOWMST", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerstatus", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerStatus(@RequestBody DtoComunAcCashflowmst pk) throws Exception {
		logger.debug("obtenerstatus");
		DtoComunAcCashflowmst dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getStatus(),HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener localname por pk.",					
			nickname="AC_CASHFLOWMST_COBTNAME", value = "OBTENER LOCALNAME", tags = {"AC_CASHFLOWMST", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerlocalname", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerLocalname(@RequestBody DtoComunAcCashflowmst pk) throws Exception {
		logger.debug("obtenerlocalname");
		DtoComunAcCashflowmst dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getLocalname(),HttpStatus.OK);
	}
	
	public DtoComunAcCashflowmst obtenerDtoCore(DtoComunAcCashflowmst pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_cashflowcode", String.class, pk.getCashflowcode()));
		List datos = this.listarPorQuery(DtoComunAcCashflowmst.class, "accashflowmst.obtenerDto",parametros);
		DtoComunAcCashflowmst dto;
		if (datos.size()==1) {
			dto = (DtoComunAcCashflowmst)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunAcCashflowmst();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	@ApiOperation(notes = "Listar dto filtros, cashflowcode, localname, status. Retorno: List DtoComunAcCashflowmst ",					
			nickname="AC_CASHFLOWMST_CLISTDTOFIL", value = "LISTAR DTO FILTROS", tags = {"AC_CASHFLOWMST", "OBTENER"})
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAcCashflowmst>> listarDtoFiltros(@RequestBody DtoComunAcCashflowmst filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getCashflowcode()))
			filtro.setCashflowcode(null);		
		if (UString.esNuloVacio(filtro.getLocalname()))
			filtro.setLocalname(null);
		else
			filtro.setLocalname(filtro.getLocalname().toUpperCase());
		if (UString.esNuloVacio(filtro.getStatus()))
			filtro.setStatus(null);
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_cashflowcode", String.class, filtro.getCashflowcode()));
        parametros.add(new DominioParametroPersistencia("p_localname", String.class, filtro.getLocalname()));
        parametros.add(new DominioParametroPersistencia("p_status", String.class, filtro.getStatus()));
        List datos = this.listarPorQuery(DtoComunAcCashflowmst.class, "accashflowmst.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunAcCashflowmst>>(datos, HttpStatus.OK);
	}
	
}
