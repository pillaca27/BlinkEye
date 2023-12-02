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
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCashflowmajor;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCashflowmst;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/contabilidad/accashflowmajorcomun")
@CrossOrigin(origins = "*")
public class AcCashflowmajorComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(AcCashflowmajorComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AcCashflowmajorComunRest() {
		super("accashflowmajor");
	}
	
	@ApiOperation(notes = "Listar Dto tabla. Retorno: List DtoTabla",					
			nickname="AC_CASHFLOWMAJOR_CLIST", value = "LISTAR DTO TABLA", tags = {"AC_CASHFLOWMAJOR", "LISTAR"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar");
		List datos = this.listarPorQuery(DtoTabla.class, "accashflowmajor.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Listar Dto tabla activos. Retorno: List DtoTabla",					
			nickname="AC_CASHFLOWMAJOR_CLISTACT", value = "LISTAR DTO TABLA ACTIVOS", tags = {"AC_CASHFLOWMAJOR", "LISTAR", "ACTIVOS"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "accashflowmajor.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener tabla por codigo. Retorno: List DtoTabla",					
			nickname="AC_CASHFLOWMAJOR_COBT", value = "OBTENER TABLA", tags = {"AC_CASHFLOWMAJOR", "OBTENER"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_cashflowmajor", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "accashflowmajor.obtenerTabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	@ApiOperation(notes = "LISTAR por filtros, codigo, nombre y estadoId. Retorno: List DtoTabla",					
			nickname="AC_CASHFLOWMAJOR_CLIST", value = "LISTAR POR FILTROS", tags = {"AC_CASHFLOWMAJOR", "LISTAR"})
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
		parametros.add(new DominioParametroPersistencia("p_cashflowmajor", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_localname", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_status", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "accashflowmajor.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener dto por cashflowmajor. Entrada: DtoComunAcCashFlowMajor. Retorno: DtoComunAcCashFlowMajor",					
			nickname="AC_CASHFLOWMAJOR_COBT", value = "OBTENER DTO", tags = {"AC_CASHFLOWMAJOR", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcCashflowmajor> obtenerDto(@RequestBody DtoComunAcCashflowmajor pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_cashflowmajor", String.class, pk.getCashflowmajor()));
		List datos = this.listarPorQuery(DtoComunAcCashflowmajor.class, "accashflowmajor.obtenerDto",parametros);
		DtoComunAcCashflowmajor dto;
		if (datos.size()==1) {
			dto = (DtoComunAcCashflowmajor)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunAcCashflowmajor();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunAcCashflowmajor>(dto,HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener dto por filtros cashflowmajor. Entrada: DtoComunAcCashFlowMajor. Retorno: DtoComunAcCashFlowMajor",					
			nickname="AC_CASHFLOWMAJOR_CLISTDTO", value = "OBTENER DTO POR FILTROS", tags = {"AC_CASHFLOWMAJOR", "OBTENER"})
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAcCashflowmajor>> listarDtoFiltros(@RequestBody DtoComunAcCashflowmajor filtro) {
		logger.debug("listardtofiltros");
        List datos = listarDtoFiltrosCore(filtro);
		return new ResponseEntity<List<DtoComunAcCashflowmajor>>(datos, HttpStatus.OK);
	}
	
	public List<DtoComunAcCashflowmajor> listarDtoFiltrosCore(DtoComunAcCashflowmajor filtro) {
		if (UString.esNuloVacio(filtro.getCashflowmajor()))
			filtro.setCashflowmajor(null);		
		if (UString.esNuloVacio(filtro.getLocalname()))
			filtro.setLocalname(null);
		else
			filtro.setLocalname(filtro.getLocalname().toUpperCase());
		if (UString.esNuloVacio(filtro.getStatus()))
			filtro.setStatus(null);
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_cashflowmajor", String.class, filtro.getCashflowmajor()));
        parametros.add(new DominioParametroPersistencia("p_localname", String.class, filtro.getLocalname()));
        parametros.add(new DominioParametroPersistencia("p_status", String.class, filtro.getStatus()));
        List datos = this.listarPorQuery(DtoComunAcCashflowmajor.class, "accashflowmajor.listardtofiltros", parametros);
        logger.debug(datos.size());
		return datos;
	}
	
	
	@ApiOperation(notes = "Listar dto activos. Entrada: DtoComunAcCashFlowMajor. Retorno: DtoComunAcCashFlowMajor",					
			nickname="AC_CASHFLOWMAJOR_CLISTDTO", value = "LISTAR DTO ACTIVOS", tags = {"AC_CASHFLOWMAJOR", "LISTAR", "ACTIVOS"})
	@Transactional
	@GetMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAcCashflowmajor>> listarDtoActivos() {
		logger.debug("listardtoactivos");
		DtoComunAcCashflowmajor filtro=new DtoComunAcCashflowmajor();
		filtro.setStatus("A");
        List datos = listarDtoFiltrosCore(filtro);
		return new ResponseEntity<List<DtoComunAcCashflowmajor>>(datos, HttpStatus.OK);
	}
	
}
