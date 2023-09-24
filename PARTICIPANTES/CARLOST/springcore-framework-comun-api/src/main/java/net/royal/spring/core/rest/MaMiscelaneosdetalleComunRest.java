package net.royal.spring.core.rest;

import java.util.ArrayList;
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
import net.royal.spring.core.dao.impl.MaMiscelaneosdetalleDaoImpl;
import net.royal.spring.core.dominio.BeanMaMiscelaneosdetalle;
import net.royal.spring.core.dominio.BeanMaMiscelaneosdetallePk;
import net.royal.spring.core.dominio.dto.DtoComunMaMiscelaneosdetalle;
import net.royal.spring.core.dominio.dto.DtoComunMaMiscelaneosheader;
import net.royal.spring.core.dominio.dto.DtoComunParametrosmast;
import net.royal.spring.core.servicio.impl.MaMiscelaneosdetalleServicioImpl;
import net.royal.spring.core.servicio.validar.MaMiscelaneosdetalleServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.MiscelaneosHeaderTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/mamiscelaneosdetallecomun")
@CrossOrigin(origins = "*")
public class MaMiscelaneosdetalleComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(MaMiscelaneosdetalleComunRest.class);

	@Autowired
	private MaMiscelaneosdetalleServicioImpl servicio;

	@Autowired
	private MaMiscelaneosdetalleServicioValidar validar;

	@Autowired
	private MaMiscelaneosdetalleDaoImpl maMiscelaneosdetalleDao;
	

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public MaMiscelaneosdetalleComunRest() {
		super("mamiscelaneosdetalle");
	}

	@Transactional
	@PutMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaMiscelaneosdetalle> actualizar(@RequestBody DtoComunMaMiscelaneosdetalle dto) throws Exception {
		logger.debug("ParametrosmastRest.eliminar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunMaMiscelaneosdetalle>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaMiscelaneosdetalle> eliminar(@RequestBody DtoComunMaMiscelaneosdetalle dto) throws Exception {
		logger.debug("ParametrosmastRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunMaMiscelaneosdetalle>(dto,HttpStatus.OK);
	}
	
	/**
	 * ARMAS MIGRADO
	 * @param filtro
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Miscelaneos activos | Parametros de entrada: codigo de compania, aplicacion y tabla | Parametros de salida: codigo, nombre", 
			value = "99-MISDET-CLSDET", tags = {"CORE", "MISCELANEOS DETALLE"})
	@Transactional
	@PutMapping(value="/listardtoporheader", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunMaMiscelaneosdetalle>> listarDtoPorHeader(@RequestBody DtoComunMaMiscelaneosdetalle filtro) throws Exception {
		logger.debug("MaMiscelaneosdetalleRest.listarPaginado");
		List<DtoComunMaMiscelaneosdetalle> listado = maMiscelaneosdetalleDao.listarDtoPorHeader(filtro);
		return new ResponseEntity<List<DtoComunMaMiscelaneosdetalle>>(listado,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/listardtoporaplicacioncodigotabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunMaMiscelaneosdetalle>> listarDtoPorAplicacionCodigoTabla(@RequestBody DtoComunMaMiscelaneosdetalle filtro) throws Exception {
		logger.debug("listardtoporaplicacioncodigotabla");
		List<DtoComunMaMiscelaneosdetalle> listado = maMiscelaneosdetalleDao.listarDtoPorAplicacionCodigoTabla(filtro);
		return new ResponseEntity<List<DtoComunMaMiscelaneosdetalle>>(listado,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/listardtoactivosporaplicacioncodigotabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunMaMiscelaneosdetalle>> listarDtoActivosPorAplicacionCodigoTabla(@RequestBody DtoComunMaMiscelaneosdetalle filtro) throws Exception {
		logger.debug("listardtoactivosporaplicacioncodigotabla");
		filtro.setEstado("A");
		List<DtoComunMaMiscelaneosdetalle> listado = maMiscelaneosdetalleDao.listarDtoPorAplicacionCodigoTabla(filtro);
		return new ResponseEntity<List<DtoComunMaMiscelaneosdetalle>>(listado,HttpStatus.OK);
	}
	
	/**
	 * ARMAS MIGRADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Miscelaneos activos | Parametros de entrada: codigo de compania, aplicacion y tabla | Parametros de salida: codigo, nombre", 
			value = "99-MISDET-COBDTO", tags = {"CORE", "MISCELANEOS DETALLE"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaMiscelaneosdetalle> obtenerDtoPorId(@RequestBody DtoComunMaMiscelaneosdetalle pk) throws Exception {
		logger.debug("MaMiscelaneosdetalleRest.obtenerDtoPorId");
		DtoComunMaMiscelaneosdetalle dto = maMiscelaneosdetalleDao.obtenerDtoPorId(pk);
		if (dto==null) {
		    dto = new DtoComunMaMiscelaneosdetalle();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return new ResponseEntity<DtoComunMaMiscelaneosdetalle>(dto,HttpStatus.OK);
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return new ResponseEntity<DtoComunMaMiscelaneosdetalle>(dto,HttpStatus.OK);
	}
	
	/******************************************************************/
	/**
	 * ARMAS MIGRADO
	 * 	NO SE PONE EN ANGULAR
	 * @param header
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Miscelaneos activos | Parametros de entrada: codigo de compania, aplicacion y tabla | Parametros de salida: codigo, nombre", 
			value = "99-MISDET-COBTDES", tags = {"CORE", "MISCELANEOS DETALLE"})
	@Transactional
	@PutMapping(value="/obtenerdescripcion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerDtoPorId(@RequestBody MiscelaneosHeaderTransaccion header) throws Exception {
		logger.debug("MaMiscelaneosdetalleRest.obtenerDtoPorId");		
		DtoComunMaMiscelaneosdetalle pk = new DtoComunMaMiscelaneosdetalle();
		pk.setAplicacioncodigo(header.getAplicacioncodigo());
		pk.setCompania(header.getCompania());
		pk.setCodigotabla(header.getCodigotabla());
		pk.setCodigoelemento(header.getTransaccionEstado());		
		DtoComunMaMiscelaneosdetalle dto = maMiscelaneosdetalleDao.obtenerDtoPorId(pk);
		DtoTabla tabla = new DtoTabla();		
		if(dto != null) {			
			tabla.setCodigo(dto.getCodigoelemento());
			tabla.setDescripcion(dto.getDescripcionlocal());	
		}
		return new ResponseEntity<DtoTabla>(tabla,HttpStatus.OK);
	}
	
	/**
	 * ARMAS MIGRADO
	 * @param dto
	 * @return	listarporheaderactivos
	 */	
	@ApiOperation(notes = "-Descripcion: Listado de Miscelaneos activos | Parametros de entrada: codigo de compania, aplicacion y tabla | Parametros de salida: codigo, nombre", 
			value = "99-MISDET-CLHACT", tags = {"CORE", "MISCELANEOS DETALLE"})
	@Transactional
	@PutMapping(value = "/listartablaporheader", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listartablaporheader(
			@ApiParam(value = "Codigos de la tabla", required = true)
			@RequestBody DtoComunMaMiscelaneosdetalle dto) {
				
		if (UString.esNuloVacio(dto.getAplicacioncodigo()))
			dto.setAplicacioncodigo("99");
		if (UString.esNuloVacio(dto.getCompania()))
			dto.setCompania("999999");
		if (UString.esNuloVacio(dto.getEstado()))
			dto.setEstado(null);		
		
		 List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
	     parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, dto.getAplicacioncodigo()));
	     parametros.add(new DominioParametroPersistencia("p_codigotabla", String.class, dto.getCodigotabla()));
	     parametros.add(new DominioParametroPersistencia("p_compania", String.class, dto.getCompania()));
	     parametros.add(new DominioParametroPersistencia("p_estado", String.class, dto.getEstado()));
	     List datos = this.listarPorQuery(DtoTabla.class,"mamiscelaneosdetalle.listarActivos", parametros);
		return new ResponseEntity<List<DtoTabla>>(datos,HttpStatus.OK); 
	}	
	
	
	@Transactional
	@PutMapping(value="/obtenerdtoporaplicaciontablaelemento", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaMiscelaneosdetalle> obtenerDtoPorAplicacionTablaElemento(@RequestBody DtoComunMaMiscelaneosdetalle pk) throws Exception {
		logger.debug("obtenerDtoPorAplicacionTablaElemento");
		DtoComunMaMiscelaneosdetalle dto = maMiscelaneosdetalleDao.obtenerDtoPorAplicacionTablaElemento(pk);
		if (dto==null) {
		    dto = new DtoComunMaMiscelaneosdetalle();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return new ResponseEntity<DtoComunMaMiscelaneosdetalle>(dto,HttpStatus.OK);
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return new ResponseEntity<DtoComunMaMiscelaneosdetalle>(dto,HttpStatus.OK);
	}
}
