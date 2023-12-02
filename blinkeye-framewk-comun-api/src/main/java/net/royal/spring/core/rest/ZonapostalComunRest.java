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
import io.swagger.annotations.ApiParam;
import net.royal.spring.core.dominio.dto.DtoComunMaCts;
import net.royal.spring.core.dominio.dto.DtoComunZonapostal;
import net.royal.spring.core.dominio.filtro.FiltroComunUbigeo;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/zonapostalcomun")
@CrossOrigin(origins = "*")
public class ZonapostalComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(ZonapostalComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public ZonapostalComunRest() {
		super("zonapostal");
	}
	
	@ApiOperation(notes = "Obtener dto. Entrada: DtoComunZonapostal. Retorno: DtoComunZonapostal",					
			nickname="ZONAPOSTAL_COBT", value = "Obtener dto.", tags = {"ZONAPOSTAL", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunZonapostal> obtenerdto(@RequestBody DtoComunZonapostal pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_departamento", String.class, pk.getDepartamento()));
		parametros.add(new DominioParametroPersistencia("p_provincia", String.class, pk.getProvincia()));
		parametros.add(new DominioParametroPersistencia("p_codigopostal", String.class, pk.getCodigopostal()));
		List datos = this.listarPorQuery(DtoComunZonapostal.class, "zonapostal.obtenerdto",parametros);
		
		List lsttablaNombre = this.listarPorQuery(DtoTabla.class, "zonapostal.obtenerNombreZona",parametros);
		String nombreZona = "";
		if(lsttablaNombre.size() > 0) {
			DtoTabla tabla = (DtoTabla) lsttablaNombre.get(0);
			nombreZona = tabla.getNombre();
		}
		
		DtoComunZonapostal dto;
		if (datos.size()==1) {
			dto = (DtoComunZonapostal)datos.get(0);
			dto.setDescripcionlarga(nombreZona);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunZonapostal();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunZonapostal>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunZonapostal>> listardtofiltros(@RequestBody DtoComunZonapostal filtro) throws Exception {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getDepartamento()))
			filtro.setDepartamento(null);
		if (UString.esNuloVacio(filtro.getProvincia()))
			filtro.setProvincia(null);
		if (UString.esNuloVacio(filtro.getCodigopostal()))
			filtro.setCodigopostal(null);
		if (UString.esNuloVacio(filtro.getDescripcioncorta()))
			filtro.setDescripcioncorta(null);
		else
			filtro.setDescripcioncorta(filtro.getDescripcioncorta().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_departamento", String.class, filtro.getDepartamento()));
		parametros.add(new DominioParametroPersistencia("p_provincia", String.class, filtro.getProvincia()));
		parametros.add(new DominioParametroPersistencia("p_codigopostal", String.class, filtro.getCodigopostal()));		
		parametros.add(new DominioParametroPersistencia("p_descripcioncorta", String.class, filtro.getDescripcioncorta()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		List datos = this.listarPorQuery(DtoComunZonapostal.class, "zonapostal.listardtofiltros",parametros);
		return new ResponseEntity<List<DtoComunZonapostal>>(datos,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/listardtoporprovincia", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunZonapostal>> listardtoporprovincia(@RequestBody DtoComunZonapostal filtro) throws Exception {
		logger.debug("listardtoporprovincia");
		if (UString.esNuloVacio(filtro.getDepartamento()))
			filtro.setDepartamento(null);
		if (UString.esNuloVacio(filtro.getProvincia()))
			filtro.setProvincia(null);
		if (UString.esNuloVacio(filtro.getDescripcioncorta()))
			filtro.setDescripcioncorta(null);
		else
			filtro.setDescripcioncorta(filtro.getDescripcioncorta().toUpperCase());
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_departamento", String.class, filtro.getDepartamento()));
		parametros.add(new DominioParametroPersistencia("p_provincia", String.class, filtro.getProvincia()));
		parametros.add(new DominioParametroPersistencia("p_descripcioncorta", String.class, filtro.getDescripcioncorta()));
		List datos = this.listarPorQuery(DtoComunZonapostal.class, "zonapostal.listardtoporprovincia",parametros);
		return new ResponseEntity<List<DtoComunZonapostal>>(datos,HttpStatus.OK);
	}

	/**
	 * ARMAS MIGRADO
	 * @param dto
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Distritos de la tabla Provincia | Parametros de entrada: id Pais, id Departamento, id Provincia | Parametros de salida: codigo, nombre", 
			value = "99-ZONPOS-C0001", tags = {"CORE", "DISTRITO"})
	@Transactional
	@PutMapping(value = "/listarporprovinciapk", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarDistritosPorProvincia(
			@ApiParam(value = "Codigos de la tabla", required = true)
    		@RequestBody DtoComunZonapostal dto )
	{
		logger.debug("listar distritos");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_pais", String.class, dto.getPais()));
        parametros.add(new DominioParametroPersistencia("p_departamento", String.class, dto.getDepartamento()));
        parametros.add(new DominioParametroPersistencia("p_provincia", String.class, dto.getProvincia()));
        List datos = this.listarPorQuery( DtoTabla.class, "zonapostal.listarPorProvinciaPk", parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/**
	 * ARMAS MIGRADO
	 * @param dto
	 * @return
	 */
	
	@ApiOperation(notes = "-Descripcion: Obtener nombre distrito | Parametros de entrada: id Pais, id Departamento, id Provincia, id Zona Postal | Parametros de salida: codigo, nombre", 
			value = "99-ZONPOS-C0002", tags = {"CORE", "DISTRITO"})
	@Transactional
	@PutMapping(value = "/obtenernombrezonapostal", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenernombrezonapostal(
			@ApiParam(value = "Codigos de la tabla", required = true)
    		@RequestBody DtoComunZonapostal dto )
	{
		logger.debug("listar distritos");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_pais", String.class, dto.getPais()));
        parametros.add(new DominioParametroPersistencia("p_departamento", String.class, dto.getDepartamento()));
        parametros.add(new DominioParametroPersistencia("p_provincia", String.class, dto.getProvincia()));
        parametros.add(new DominioParametroPersistencia("p_distrito", String.class, dto.getCodigopostal()));
        List datos = this.listarPorQuery( DtoTabla.class, "zonapostal.obtenerNombreZonaPostal", parametros);
        
        if(datos.size() > 0) {
        	return new ResponseEntity<DtoTabla>((DtoTabla) datos.get(0), HttpStatus.OK);	
        }
        
        return new ResponseEntity<DtoTabla>(new DtoTabla(), HttpStatus.OK);
		
	}
	
}
