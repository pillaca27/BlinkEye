package net.royal.spring.logistica.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.dominio.BeanCorrelativosmast;
import net.royal.spring.core.dominio.BeanCorrelativosmastPk;
import net.royal.spring.core.dominio.dto.DtoComunCorrelativosmast;
import net.royal.spring.core.servicio.impl.CorrelativosmastServicioImpl;

import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.logistica.dominio.dto.DtoComunWhAlmacenmast;
import net.royal.spring.logistica.dominio.dto.DtoComunWhClasificacionrequorden;
import net.royal.spring.presupuesto.dominio.dto.DtoComunBuNivelserviciomst;
import net.royal.spring.presupuesto.dominio.dto.DtoComunBuPeriodo;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/logistica/whalmacenmstcomun")
@CrossOrigin(origins = "*")
public class WhAlmacenmstComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(WhAlmacenmstComunRest.class);

	@Autowired
	private CorrelativosmastServicioValidar validar;

	@Autowired
	private CorrelativosmastDaoImpl dao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WhAlmacenmstComunRest() {
		super("whalmacenmst");
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Almacenes | Sin parametros de entrada | Parametros de salida: Listado DtoTabla(codigo, descripcion)", 
			value = "WH-ALMACEN-CLISTA", tags = {"LOGISTICA", "ALMACEN"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() throws Exception {
		logger.debug("whalmacenmst.listarAlmacen");				
		List registos = this.listarPorQuery(DtoTabla.class, "whalmacenmst.listar");				
		return new ResponseEntity<List<DtoTabla>>(registos, HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Almacenes | Sin parametros de entrada | Parametros de salida: Listado DtoTabla(codigo, descripcion)", 
			value = "WH-ALMACEN-CLIACT", tags = {"LOGISTICA", "ALMACEN"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaractivos() throws Exception {
		logger.debug("whalmacenmst.listarAlmacen");				
		List registos = this.listarPorQuery(DtoTabla.class, "whalmacenmst.listaractivos");				
		return new ResponseEntity<List<DtoTabla>>(registos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		logger.debug("obtenertabla");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_almacencodigo", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "whalmacenmst.obtenerTabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	@Transactional
	@PutMapping(value = "/listarfiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarFiltros(@RequestBody DtoTabla filtro) {
		logger.debug("listarFiltros");
		if (UString.esNuloVacio(filtro.getCodigo()))
			filtro.setCodigo(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);
		else
			filtro.setNombre(filtro.getNombre().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstadoId()))
			filtro.setEstadoId(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();		
		parametros.add(new DominioParametroPersistencia("p_almacencodigo", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "whalmacenmst.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhAlmacenmast> obtenerDto(@RequestBody DtoComunWhAlmacenmast pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_almacencodigo", String.class, pk.getAlmacencodigo()));
		List datos = this.listarPorQuery(DtoComunWhAlmacenmast.class, "whalmacenmst.obtenerDto",parametros);
		DtoComunWhAlmacenmast dto;
		if (datos.size()==1) {
			dto = (DtoComunWhAlmacenmast)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunWhAlmacenmast();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunWhAlmacenmast>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhAlmacenmast>> listarDtoFiltros(@RequestBody DtoComunWhAlmacenmast filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getAlmacencodigo()))
			filtro.setAlmacencodigo(null);		
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		else
			filtro.setDescripcionlocal(filtro.getDescripcionlocal().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_almacencodigo", String.class, filtro.getAlmacencodigo()));
        parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunWhAlmacenmast.class, "whalmacenmst.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunWhAlmacenmast>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtoporcompania", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhAlmacenmast>> listardtoporcompania(@RequestBody DtoComunWhAlmacenmast filtro) {
		logger.debug("listardtoporcompania");
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompaniasocio()));
        List datos = this.listarPorQuery(DtoComunWhAlmacenmast.class, "whalmacenmst.listardtoporcompania", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunWhAlmacenmast>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtoactivoscommodityflagnporcompania", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhAlmacenmast>> listarDtoActivosCommodityflagNPorCompania(@RequestBody DtoComunWhAlmacenmast filtro) {
		logger.debug("listardtoactivoscommodityflagnporcompania");
		if (UString.esNuloVacio(filtro.getCompaniasocio()))
			filtro.setCompaniasocio(null);	
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompaniasocio()));
        List datos = this.listarPorQuery(DtoComunWhAlmacenmast.class, "whalmacenmst.listardtoactivoscommodityflagnporcompania", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunWhAlmacenmast>>(datos, HttpStatus.OK);
	}
	

	/* EDGAR LUQUE - LISTAR ALMACENES POR USUARIO ACTUAL*/
	@Transactional
	@GetMapping(value = "/listaractivosPorUsuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaractivosPorUsuario() throws Exception {
		logger.debug("whalmacenmst.listaractivosPorUsuario");				
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_usuario", String.class, this.getUsuarioActual().getUsuario()));
        List datos = this.listarPorQuery(DtoTabla.class, "whalmacenmst.listaractivosporusuario", parametros);
        return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK); 
	}
	
}
