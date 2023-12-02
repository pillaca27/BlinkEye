package net.royal.spring.core.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dominio.dto.DtoComunDwMaAccount;
import net.royal.spring.core.dominio.dto.DtoComunPais;
import net.royal.spring.core.dominio.dto.DtoComunPrimemst;
import net.royal.spring.core.dominio.filtro.FiltroComunAccountmst;
import net.royal.spring.core.dominio.filtro.FiltroComunPrime;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/primemstcomun")
@CrossOrigin(origins = "*")
public class PrimemstComunRest  extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(PrimemstComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public PrimemstComunRest() {
		super("primemst");
	}
	
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar");
		List datos = this.listarPorQuery(DtoTabla.class, "primemst.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "primemst.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listaractivospordigitsnumber", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivosPorDigitsnumber(@RequestBody DtoTabla filtro) {	        	            
		logger.debug("listaractivospordigitsnumber");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_digitsnumber", Integer.class, filtro.getId()));
		List datos = this.listarPorQuery(DtoTabla.class, "primemst.listarActivosPorDigitsnumber",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener tabla. Entrada: DtoTabla: codigo. Retorno: DtoTabla",					
			nickname="PRIMEMST_COBT", value = "Obtener tabla", tags = {"PRIMEMST", "OBTENER"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerDtoTablaPorId(@RequestBody DtoTabla filtro) { 
		logger.debug("obtenertabla");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_prime", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "primemst.obtenertabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	@Transactional
	@PutMapping(value="/obtenercostcenterflag", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerCostcenterflag(@RequestBody DtoComunPrimemst pk) throws Exception {
		logger.debug("obtenercostcenterflag");
		DtoComunPrimemst dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getCostcenterflag(),HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunPrimemst> obtenerdto(@RequestBody DtoComunPrimemst pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunPrimemst dto = obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunPrimemst>(dto,HttpStatus.OK);
	}
	
	public DtoComunPrimemst obtenerDtoCore(DtoComunPrimemst pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_prime", String.class, pk.getPrime()));
		List datos = this.listarPorQuery(DtoComunPrimemst.class, "primemst.obtenerdto",parametros);
		DtoComunPrimemst dto;
		if (datos.size()==1) {
			dto = (DtoComunPrimemst)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunPrimemst();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	@Transactional
	@PutMapping(value="/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunPrimemst>> listardtofiltros(@RequestBody DtoComunPrimemst filtro) throws Exception {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getPrime()))
			filtro.setPrime(null);
		if (UString.esNuloVacio(filtro.getLocalname()))
			filtro.setLocalname(null);
		else
			filtro.setLocalname(filtro.getLocalname().toUpperCase());
		if (UString.esNuloVacio(filtro.getStatus()))
			filtro.setStatus(null);
		if (UBigDecimal.esCeroOrNulo(filtro.getDigitsnumber()))
			filtro.setDigitsnumber(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_prime", String.class, filtro.getPrime()));
		parametros.add(new DominioParametroPersistencia("p_localname", String.class, filtro.getLocalname()));
		parametros.add(new DominioParametroPersistencia("p_digitsnumber", BigDecimal.class, filtro.getDigitsnumber()));
		parametros.add(new DominioParametroPersistencia("p_status", String.class, filtro.getStatus()));
		List datos = this.listarPorQuery(DtoComunPrimemst.class, "primemst.listardtofiltros",parametros);
		return new ResponseEntity<List<DtoComunPrimemst>>(datos,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listarxx",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarxx(@RequestBody FiltroComunPrime filtro)
    {	
		List<DominioParametroPersistencia> param = new ArrayList<DominioParametroPersistencia>();
		param.add(new DominioParametroPersistencia("p_digits", BigDecimal.class, filtro.getDigits()));		
		List datos = this.listarPorQuery(DtoTabla.class, "primemst.listarxx", param);   
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
    }
	

}
