package net.royal.spring.asistencia.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import net.royal.spring.asistencia.dominio.dto.DtoComunAsArea;
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/asistencia/asareacomun")
@CrossOrigin(origins = "*")
public class AsAreaComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(AsAreaComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AsAreaComunRest() {
		super("asarea");
	}
	
	/***
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "Listar todos los registros de la tabla as_area,"			
			+ "<br><b>Entrada:</b>sin parametros"
			+ "<br><b>Salida:</b>Lista(id,nombre,estadoId)"
			+ "<br><b>Reglas:</b>sin reglas", 
			nickname="AS_AREA-LISTAR", value = "LISTAR TODOS", tags = {"ASISTENCIA", "AS_AREA", "AREA", "LISTAR"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("listar");
		List datos = this.listarPorQuery(DtoTabla.class, "asarea.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/***
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "Listar los registros activos de la tabla as_area,"			
			+ "<br><b>Entrada:</b>sin parametros"
			+ "<br><b>Salida:</b>Lista(id,nombre,estadoId)"
			+ "<br><b>Reglas:</b>estado=A",  
			nickname="AS_AREA-LISTARACTIVOS", value = "LISTAR ACTIVOS", tags = {"ASISTENCIA", "AS_AREA", "AREA", "LISTAR","ACTIVOS"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "asarea.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	 
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 */
	@ApiOperation(notes = "Obtener por PK de la tabla as_area,"			
			+ "<br><b>Entrada:</b>id"
			+ "<br><b>Salida:</b>(id,nombre,estadoId)"
			+ "<br><b>Reglas:</b>sin reglas",  
			nickname="AS_AREA-OBTENERTABLA", value = "OBTENER", tags = {"ASISTENCIA", "AS_AREA", "AREA", "OBTENER"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		logger.debug("AsAreaComunRest.obtenertabla");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_area", BigDecimal.class, filtro.getId()));
        List datos = this.listarPorQuery(DtoTabla.class, "asarea.obtenertabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	@ApiOperation(notes = "Listar con filtros de la tabla as_area,"			
			+ "<br><b>Entrada:</b>id,nombre,estadoId"
			+ "<br><b>Salida:</b>Lista(id,nombre,estadoId)"
			+ "<br><b>Reglas:</b>sin reglas", 
			nickname="AS_AREA-LISTARFILTROS", value = "LISTAR CON FILTROS", tags = {"ASISTENCIA", "AS_AREA", "AREA", "LISTAR"})
	@Transactional
	@PutMapping(value = "/listarfiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarFiltros(@RequestBody DtoTabla filtro) {
		logger.debug("listarfiltros");
		if (UInteger.esCeroOrNulo(filtro.getId()))
			filtro.setId(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);
		else
			filtro.setNombre(filtro.getNombre().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstadoId()))
			filtro.setEstadoId(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();		
		parametros.add(new DominioParametroPersistencia("p_area", BigDecimal.class, filtro.getId()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "asarea.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 */
	@ApiOperation(notes = "Obtener por PK de la tabla as_area,"			
			+ "<br><b>Entrada:</b>area"
			+ "<br><b>Salida:</b>(area, nombre, areapadre, diarotacion, ultimarotacion, horario, empleadoresponsable, secretariaarea, estado, ultimousuario, ultimafechamodif)"
			+ "<br><b>Reglas:</b>sin reglas",  
			nickname="AS_AREA-OBTENERDTO", value = "OBTENER DTO", tags = {"ASISTENCIA","AS_AREA","AREA","OBTENER"})
	@Transactional
	@PutMapping(value = "/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAsArea> obtenerDto(@RequestBody DtoComunAsArea filtro) { 
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_area", BigDecimal.class, filtro.getArea()));
        List datos = this.listarPorQuery(DtoComunAsArea.class, "asarea.obtenerdto", parametros);
        DtoComunAsArea dto = null;
        if (datos.size()==1)
        	dto=(DtoComunAsArea)datos.get(0);
		return new ResponseEntity<DtoComunAsArea>(dto, HttpStatus.OK); 
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 */
	@ApiOperation(notes = "Listar con filtros de la tabla as_area,"			
			+ "<br><b>Entrada:</b>area,nombre,estado"
			+ "<br><b>Salida:</b>Lista(area, nombre, areapadre, diarotacion, ultimarotacion, horario, empleadoresponsable, secretariaarea, estado, ultimousuario, ultimafechamodif)"
			+ "<br><b>Reglas:</b>sin reglas",  
			nickname="AS_AREA-LISTARDTOFILTROS", value = "LISTAR DTO CON FILTROS", tags = {"ASISTENCIA","AS_AREA", "AREA", "LISTAR"})
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAsArea>> listarDtoFiltros(@RequestBody DtoComunAsArea filtro) { 
		logger.debug("AsAreaComunRest.listarfiltros");
		if (UBigDecimal.esCeroOrNulo(filtro.getArea()))
			filtro.setArea(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);
		else
			filtro.setNombre(filtro.getNombre().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_area", BigDecimal.class, filtro.getArea()));
        parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunAsArea.class, "asarea.listardtofiltros", parametros);
		return new ResponseEntity<List<DtoComunAsArea>>(datos, HttpStatus.OK); 
	}
}
