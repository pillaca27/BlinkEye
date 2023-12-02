package net.royal.spring.rrhh.rest;

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
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/rrhh/hridiomasmastcomun")
@CrossOrigin(origins = "*")
public class HrIdiomasmastComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(HrIdiomasmastComunRest.class);

	@Autowired
	private CorrelativosmastServicioValidar validar;

	@Autowired
	private CorrelativosmastDaoImpl dao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public HrIdiomasmastComunRest() {
		super("hridiomasmast");
	}
	
	/***
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado Activos Tipo trabajador de la tabla hr_idiomasmast y estado activo | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "HR-IDIOMA-LISTAR", tags = {"RRHH", "IDIOMA"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("listar Activos Tipo Trabajador");
		List datos = this.listarPorQuery(DtoTabla.class, "hridiomasmast.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/***
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado Activos Tipo trabajador de la tabla hr_idiomasmast y estado activo | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "HR-IDIOMA-LISACT", tags = {"RRHH", "IDIOMA"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("listar Activos Tipo Trabajador");
		List datos = this.listarPorQuery(DtoTabla.class, "hridiomasmast.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	 
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Obtener Por id hr_idiomasmast | Parametros de entrada: FiltroComunHrCursodescripcion | Parametros de salida: DtoTabla(id, nombre) ", 
			value = "HR-IDIOMA-COBTDTO", tags = {"RRHH", "IDIOMA"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerDtoTablaPorId(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_idioma", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "hridiomasmast.obtenertabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	/***
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado Activos Tipo trabajador de la tabla Hr_Tipotrabajador y estado activo | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "HR-IDIOMA-LISFIL", tags = {"RRHH", "IDIOMA"})
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
		parametros.add(new DominioParametroPersistencia("p_idioma", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcionLocal", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "hridiomasmast.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
}
