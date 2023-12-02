package net.royal.spring.core.rest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.royal.spring.core.dominio.dto.DtoComunProvincia;
import net.royal.spring.core.dominio.dto.DtoComunZonapostal;
import net.royal.spring.core.dominio.filtro.FiltroComunUbigeo;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/provinciacomun")
@CrossOrigin(origins = "*")
public class ProvinciaComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(ProvinciaComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public ProvinciaComunRest() {
		super("provincia");
	}
	
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunProvincia> obtenerdto(@RequestBody DtoComunProvincia pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_departamento", String.class, pk.getDepartamento()));
		parametros.add(new DominioParametroPersistencia("p_provincia", String.class, pk.getProvincia()));
		List datos = this.listarPorQuery(DtoComunProvincia.class, "provincia.obtenerdto",parametros);
		DtoComunProvincia dto;
		if (datos.size()==1) {
			dto = (DtoComunProvincia)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunProvincia();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunProvincia>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunProvincia>> listardtofiltros(@RequestBody DtoComunProvincia filtro) throws Exception {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getDepartamento()))
			filtro.setDepartamento(null);
		if (UString.esNuloVacio(filtro.getProvincia()))
			filtro.setProvincia(null);
		if (UString.esNuloVacio(filtro.getDescripcioncorta()))
			filtro.setDescripcioncorta(null);
		else
			filtro.setDescripcioncorta(filtro.getDescripcioncorta().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_departamento", String.class, filtro.getDepartamento()));
		parametros.add(new DominioParametroPersistencia("p_provincia", String.class, filtro.getProvincia()));
		parametros.add(new DominioParametroPersistencia("p_descripcioncorta", String.class, filtro.getDescripcioncorta()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		List datos = this.listarPorQuery(DtoComunProvincia.class, "provincia.listardtofiltros",parametros);
		return new ResponseEntity<List<DtoComunProvincia>>(datos,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/listardtopordepartamento", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunProvincia>> listardtopordepartamento(@RequestBody DtoComunProvincia filtro) throws Exception {
		logger.debug("listardtopordepartamento");
		if (UString.esNuloVacio(filtro.getDepartamento()))
			filtro.setDepartamento(null);
		if (UString.esNuloVacio(filtro.getDescripcioncorta()))
			filtro.setDescripcioncorta(null);
		else
			filtro.setDescripcioncorta(filtro.getDescripcioncorta().toUpperCase());
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_departamento", String.class, filtro.getDepartamento()));
		parametros.add(new DominioParametroPersistencia("p_descripcioncorta", String.class, filtro.getDescripcioncorta()));
		List datos = this.listarPorQuery(DtoComunProvincia.class, "provincia.listardtopordepartamento",parametros);
		return new ResponseEntity<List<DtoComunProvincia>>(datos,HttpStatus.OK);
	}
	
	/**
	 * ARMAS MIGRADO
	 * @param dto
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Provincias de la tabla Provincia | Parametros de entrada: id Pais, id Departamento | Parametros de salida: codigo, nombre", 
			value = "99-PROVINCIA-C0001", tags = {"CORE", "PROVINCIA"})
	@Transactional
	@PutMapping(value = "/listarpordepartamentopk", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarProvinciasPorDepartamento(
			@ApiParam(value = "Codigos de la tabla", required = true)
    		@RequestBody DtoComunProvincia dto )
	{
	    logger.debug("listar provincias");
	    List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_pais", String.class, dto.getPais()));
        parametros.add(new DominioParametroPersistencia("p_departamento", String.class, dto.getDepartamento()));
        List datos = this.listarPorQuery(DtoTabla.class, "provincia.listarPorDepartamentoPk", parametros);
	    return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/**
	 * ARMAS MIGRADO
	 * @param dto
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Obtener nombre provincia | Parametros de entrada: id Pais, id Departamento, id Provincia | Parametros de salida: codigo, nombre", 
			value = "99-PROVINCIA-C0002", tags = {"CORE", "PROVINCIA"})
	@Transactional
	@PutMapping(value = "/obtenernombreprovincia", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerNombreProvincia(
			@ApiParam(value = "Codigos de la tabla", required = true)
    		@RequestBody DtoComunProvincia dto )
	{
		logger.debug("listar distritos");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_pais", String.class, dto.getPais()));
        parametros.add(new DominioParametroPersistencia("p_departamento", String.class, dto.getDepartamento()));
        parametros.add(new DominioParametroPersistencia("p_provincia", String.class, dto.getProvincia()));        
        List datos = this.listarPorQuery( DtoTabla.class, "provincia.obtenerNombreProvincia", parametros);
        
        if(datos.size() > 0) {
        	return new ResponseEntity<DtoTabla>((DtoTabla) datos.get(0), HttpStatus.OK);	
        }
        
        return new ResponseEntity<DtoTabla>(new DtoTabla(), HttpStatus.OK);
	}
	
	
}
