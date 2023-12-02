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
import net.royal.spring.comercial.dominio.dto.DtoComunCoTipopago;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcSucursal;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/comercial/cotipopagocomun")
@CrossOrigin(origins = "*")
public class CoTipopagoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(CoTipopagoComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public CoTipopagoComunRest() {
		super("cotipopago");
	}
	
	@ApiOperation(notes = "Listado. Retorno: DtoTabla",					
			nickname="CO_TIPOPAGO_CLISTAR", value = "Listar dto", tags = {"CO_TIPOPAGO", "LISTAR"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar");
		List datos = this.listarPorQuery(DtoTabla.class, "cotipopago.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@ApiOperation(notes = "Listado de registros activos. Retorno: DtoTabla",					
			nickname="CO_TIPOPAGO_CLISTARACT", value = "Listaro de registros activos", tags = {"CO_TIPOPAGO", "LISTAR", "ACTIVOS"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "cotipopago.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener tabla CO_TIPOPAGO. Retorno: DtoTabla",					
			nickname="CO_TIPOPAGO_COBT", value = "Obtener tabla", tags = {"CO_TIPOPAGO", "OBTENER"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_tipopago", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "cotipopago.obtenerTabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	
	@ApiOperation(notes = "Listar por Filtros: codigo, nombre, estadoId. Retorno: DtoTabla",					
			nickname="CO_TIPOPAGO_CLISTARFIL", value = "Listar por filtros.", tags = {"CO_TIPOPAGO", "LISTAR", "FILTROS"})
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
		parametros.add(new DominioParametroPersistencia("p_tipopago", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "cotipopago.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener dto tabla CO_TIPOPAGO. Retorno: DtoComunCoTipopago",					
			nickname="CO_TIPOPAGO_COBTDTO", value = "Obtener dto tabla", tags = {"CO_TIPOPAGO", "OBTENER", "DTO"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCoTipopago> obtenerDto(@RequestBody DtoComunCoTipopago pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipopago", String.class, pk.getTipopago()));
		List datos = this.listarPorQuery(DtoComunCoTipopago.class, "cotipopago.obtenerDto",parametros);
		DtoComunCoTipopago dto;
		if (datos.size()==1) {
			dto = (DtoComunCoTipopago)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunCoTipopago();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunCoTipopago>(dto,HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Listar dto por filtros: tipopago, descripcionlocal, estado. Retorno: DtoComunCoTipopago",					
			nickname="CO_TIPOPAGO_CLISTARDTO", value = "Listar dto por filtros.", tags = {"CO_TIPOPAGO", "LISTAR", "DTO"})
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunCoTipopago>> listarDtoFiltros(@RequestBody DtoComunCoTipopago filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getTipopago()))
			filtro.setTipopago(null);		
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		else
			filtro.setDescripcionlocal(filtro.getDescripcionlocal().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_tipopago", String.class, filtro.getTipopago()));
        parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunCoTipopago.class, "cotipopago.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunCoTipopago>>(datos, HttpStatus.OK);
	}
	
}
