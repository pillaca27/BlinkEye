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
import net.royal.spring.core.dominio.dto.DtoComunMaCts;
import net.royal.spring.core.dominio.dto.DtoComunMonedamast;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/monedamastcomun")
@CrossOrigin(origins = "*")
public class MonedamastComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(MonedamastComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public MonedamastComunRest() {
		super("monedamast");
	}
	
	/**
	 * ARMAS MIGRADO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Monedas Activas | Sin parametros de entrada | Parametros de salida: codigo, nombre (nombre de la moneda), descripcion (sigla de la moneda)", 
			value = "99-MONED-CLISTA", tags= {"CORE", "MONEDA"})	
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() throws Exception {
				
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		List datos = this.listarPorQuery(DtoTabla.class, "monedamast.listar", parametros);		
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	/**
	 * ARMAS MIGRADO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Monedas Activas | Sin parametros de entrada | Parametros de salida: codigo, nombre (nombre de la moneda), descripcion (sigla de la moneda)", 
			value = "99-MONED-CLIACT", tags= {"CORE", "MONEDA"})	
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaractivos() throws Exception {				
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		List datos = this.listarPorQuery(DtoTabla.class, "monedamast.listaractivos", parametros);		
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerDtoTablaPorId(@RequestBody DtoTabla filtro) { 
		logger.debug("obtenertabla");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_monedacodigo", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "monedamast.obtenertabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	@Transactional
	@PutMapping(value="/obtenerdescripcioncorta", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerDescripcioncorta(@RequestBody DtoComunMonedamast pk) throws Exception {
		logger.debug("obtenerdescripcioncorta");
		DtoComunMonedamast dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getDescripcioncorta(),HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMonedamast> obtenerdto(@RequestBody DtoComunMonedamast pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunMonedamast dto = obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunMonedamast>(dto,HttpStatus.OK);
	}
	
	public DtoComunMonedamast obtenerDtoCore(DtoComunMonedamast pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_monedacodigo", String.class, pk.getMonedacodigo()));
		List datos = this.listarPorQuery(DtoComunMonedamast.class, "monedamast.obtenerdto",parametros);
		DtoComunMonedamast dto;
		if (datos.size()==1) {
			dto = (DtoComunMonedamast)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunMonedamast();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	@Transactional
	@GetMapping(value="/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunMonedamast>> listarDtoActivos() throws Exception {
		logger.debug("listardtoactivos");
		DtoComunMonedamast filtro=new DtoComunMonedamast();
		filtro.setEstado("A");
		List datos = listardtofiltrosCore(filtro);
		return new ResponseEntity<List<DtoComunMonedamast>>(datos,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunMonedamast>> listardtofiltros(@RequestBody DtoComunMonedamast filtro) throws Exception {
		logger.debug("listardtofiltros");
		List datos = listardtofiltrosCore(filtro);
		return new ResponseEntity<List<DtoComunMonedamast>>(datos,HttpStatus.OK);
	}
	
	public List<DtoComunMonedamast> listardtofiltrosCore(DtoComunMonedamast filtro) throws Exception {
		if (UString.esNuloVacio(filtro.getMonedacodigo()))
			filtro.setMonedacodigo(null);
		if (UString.esNuloVacio(filtro.getDescripcioncorta()))
			filtro.setDescripcioncorta(null);
		else
			filtro.setDescripcioncorta(filtro.getDescripcioncorta().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_monedacodigo", String.class, filtro.getMonedacodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcioncorta", String.class, filtro.getDescripcioncorta()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		List datos = this.listarPorQuery(DtoComunMonedamast.class, "monedamast.listardtofiltros",parametros);
		return datos;
	}
	
}
