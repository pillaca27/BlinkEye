package net.royal.spring.comun.rest;

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
import net.royal.spring.comun.dominio.dto.WfDtoComunAplicacionesmast;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/comun/wfaplicacionesmastcomun")
@CrossOrigin(origins = "*")
public class WfAplicacionesmastComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(WfAplicacionesmastComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WfAplicacionesmastComunRest() {
		super("wfaplicacionesmast");
	}

	/**
	 * QQUECHOD VALIDADO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Aplicaciones | Sin parametros de entrada | Parametros de salida: codigo, nombre, descripcion (Nacionalidad)", 
			value = "SG-APLICA-CLISTA", tags= {"CORE", "APLICACIONES"})	
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() throws Exception {				
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		List datos = this.listarPorQuery(DtoTabla.class, "wfaplicacionesmast.listar", parametros);		
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Aplicaciones Activas | Sin parametros de entrada | Parametros de salida: codigo, nombre, descripcion (Nacionalidad)", 
			value = "SG-APLICA-CLIACT", tags= {"CORE", "APLICACIONES"})	
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaractivos() throws Exception {		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		List datos = this.listarPorQuery(DtoTabla.class, "wfaplicacionesmast.listaractivos", parametros);		
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "wfaplicacionesmast.obtenerTabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
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
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcioncorta", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "wfaplicacionesmast.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WfDtoComunAplicacionesmast> obtenerDto(@RequestBody WfDtoComunAplicacionesmast pk) throws Exception {
		logger.debug("obtenerdto");
		WfDtoComunAplicacionesmast dto = obtenerDtoCore(pk);
		return new ResponseEntity<WfDtoComunAplicacionesmast>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/obtenerultimoperiodocontable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerUltimoperiodocontable(@RequestBody WfDtoComunAplicacionesmast pk) throws Exception {
		logger.debug("obtenerultimoperiodocontable");
		WfDtoComunAplicacionesmast dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getUltimoperiodocontable(),HttpStatus.OK);
	}
	
	public WfDtoComunAplicacionesmast obtenerDtoCore(WfDtoComunAplicacionesmast pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, pk.getAplicacioncodigo()));
		List datos = this.listarPorQuery(WfDtoComunAplicacionesmast.class, "wfaplicacionesmast.obtenerDtoCore",parametros);
		WfDtoComunAplicacionesmast dto;
		if (datos.size()==1) {
			dto = (WfDtoComunAplicacionesmast)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new WfDtoComunAplicacionesmast();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<WfDtoComunAplicacionesmast>> listarDtoFiltros(@RequestBody WfDtoComunAplicacionesmast filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);		
		if (UString.esNuloVacio(filtro.getDescripcioncorta()))
			filtro.setDescripcioncorta(null);
		else
			filtro.setDescripcioncorta(filtro.getDescripcioncorta().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
        parametros.add(new DominioParametroPersistencia("p_descripcioncorta", String.class, filtro.getDescripcioncorta()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(WfDtoComunAplicacionesmast.class, "wfaplicacionesmast.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<WfDtoComunAplicacionesmast>>(datos, HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Aplicaciones por usuario | Sin parametros de entrada | Parametros de salida: codigo, nombre, descripcion (Nacionalidad)", 
			value = "SG-APLICA-C0001", tags= {"CORE", "APLICACIONES"})	
	@Transactional
	@GetMapping(value = "/listaraplicacionporusuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarAplicacionPorUsuario() throws Exception {		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		List datos = this.listarPorQuery(DtoTabla.class, "wfaplicacionesmast.listarAplicacionPorUsuario", parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
}
