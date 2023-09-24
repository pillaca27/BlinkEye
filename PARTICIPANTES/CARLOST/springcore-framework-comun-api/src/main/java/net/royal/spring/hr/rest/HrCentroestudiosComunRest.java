package net.royal.spring.hr.rest;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.hr.dominio.filtro.FiltroComunHrCentroestudios;

@RestController
@RequestMapping("/spring/rrhh/hrcentroestudioscomun")
@CrossOrigin(origins = "*")
public class HrCentroestudiosComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(HrCentroestudiosComunRest.class);


	@Autowired
	private CorrelativosmastServicioValidar validar;

	@Autowired
	private CorrelativosmastDaoImpl dao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public HrCentroestudiosComunRest() {
		super("hrcentroestudios");
	}
	
	/**
	 * ARMAS MIGRADO
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Listado de todos los Centros de estudio de la tabla HR_CentroEstudios | Sin parametros de entrada | Parametros de salida: id, nombre", 
			value = "RH-CENESTU-LISTAR", tags = {"RRHH", "CENTRO DE ESTUDIO"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar()
	{
		logger.debug("listar centro de estudios");		
		 List datos = this.listarPorQuery(DtoTabla.class, "hrcentroestudios.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/
	
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Obtener Por id Hr_Tipotrabajador | Parametros de entrada: FiltroComunHrCursodescripcion | Parametros de salida: DtoTabla(id, nombre) ", 
			value = "HR-CENESTU-OBTTAB", tags = {"RRHH", "AFP"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_centro", BigDecimal.class, filtro.getId()));
        List datos = this.listarPorQuery(DtoTabla.class, "hrcentroestudios.obtenertabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}*/
	
	/**
	 * ARMAS MIGRADO
	 * @param filtro
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Listado paginado de Centros de estudio de la tabla HR_CentroEstudios | Filtro FiltroPaginacionCentroEstudios | Parametros de salida: id, nombre", 
			value = "RH-CENESTU-LISFIL", tags = {"RRHH", "CENTRO DE ESTUDIO"})
	@Transactional
	@PutMapping(value = "/listarfiltros",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarFiltros(
			@RequestBody DtoTabla filtro)
    {
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        if (UString.estaVacio(filtro.getDescripcion()))
            filtro.setDescripcion(null);
        if (UString.estaVacio(filtro.getNombre()))
            filtro.setNombre(null);
        else
        	filtro.setNombre(filtro.getNombre().toUpperCase());
        parametros.add(new DominioParametroPersistencia("p_centro", BigDecimal.class, filtro.getId()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getNombre()));
        List lstRegistros = this.listarPorQuery(DtoTabla.class, "hrcentroestudios.listarfiltros", parametros);
        return new ResponseEntity<List<DtoTabla>>(lstRegistros, HttpStatus.OK);
    }*/
	
	/**
	 * ARMAS MIGRADO
	 * LEONARDO LISTADO SELECTOR HRCENTROESTUDIO
	 * @param filtro
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado paginado de Centros de estudio de la tabla HR_CentroEstudios | Filtro FiltroPaginacionCentroEstudios | Parametros de salida: id, nombre", 
			value = "RH-CENESTU-CPAGIN", tags = {"RRHH", "CENTRO DE ESTUDIO"})
	@Transactional
	@PutMapping(value = "/listarpaginacion",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarCentroEstudiosPaginacion(
			@RequestBody FiltroComunHrCentroestudios filtro)
    {
		Integer cantidadEncontrado = 0;
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        if (UInteger.esCeroOrNulo(filtro.getCentro()))
            filtro.setCentro(null);
        if (UString.estaVacio(filtro.getDescripcion()))
            filtro.setDescripcion(null);
        if (UString.estaVacio(filtro.getEstado()))
            filtro.setEstado(null);
        parametros.add(new DominioParametroPersistencia("p_id_centro_estudios", Integer.class, filtro.getCentro()));
        parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getDescripcion()));
        parametros.add(new DominioParametroPersistencia("p_id_estado", String.class, filtro.getEstado()));        
        cantidadEncontrado = this.contar("hrcentroestudios.listarpaginacionContar", parametros);
        List lstRegistros = this.listarConPaginacion(filtro.getPaginacion(), parametros, "hrcentroestudios.listarpaginacionSql", 
        		DtoTabla.class);
        filtro.getPaginacion().setPaginacionListaResultado(lstRegistros);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrado);		
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }

}
