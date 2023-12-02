package net.royal.spring.rrhh.rest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrProfesion;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrPuestoempresa;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunHrProfesion;

@RestController
@RequestMapping("/spring/rrhh/hrprofesioncomun")
@CrossOrigin(origins = "*")
public class HrProfesionComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(HrProfesionComunRest.class);

	@Autowired
	private CorrelativosmastServicioValidar validar;

	@Autowired
	private CorrelativosmastDaoImpl dao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public HrProfesionComunRest() {
		super("hrprofesion");
	}
	
	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Profesiones de la tabla HR_Profesion | Parametros de entrada: ninguno | Parametros de salida: codigo, nombre", 
			value = "HR-PROFE-CLISTA", tags = {"RRHH", "PROFESION"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar()
	{
		logger.debug("listar");
        List datos = this.listarPorQuery(DtoTabla.class, "hrprofesion.listar");  
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);		
	}
	
	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Profesiones de la tabla HR_Profesion Activos | Parametros de entrada: ninguno | Parametros de salida: codigo, nombre", 
			value = "HR-PROFE-CLIACT", tags = {"RRHH", "PROFESION"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaractivos()
	{
		logger.debug("listar activos");
        List datos = this.listarPorQuery(DtoTabla.class, "hrprofesion.listaractivos");  
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);		
	}
	
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		logger.debug("obtenertabla");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_profesion", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "hrprofesion.obtenertabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunHrProfesion> obtenerdto(@RequestBody DtoComunHrProfesion pk) throws Exception {
		logger.debug("HrPosicionempresaRest.obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_profesion", String.class, pk.getProfesion()));
		List query = this.listarPorQuery(DtoComunHrProfesion.class, "hrprofesion.obtenerdto", parametros);
		DtoComunHrProfesion dto = null;
		if (query.size()==1)
			dto = (DtoComunHrProfesion)query.get(0);
		return new ResponseEntity<DtoComunHrProfesion>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunHrProfesion>> listardtofiltros(DtoComunHrProfesion filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getProfesion()))
			filtro.setProfesion(null);
		if (UString.esNuloVacio(filtro.getDescripcion()))
			filtro.setDescripcion(null);
		else
			filtro.setDescripcion(filtro.getDescripcion().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_profesion", String.class, filtro.getProfesion()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		List datos = this.listarPorQuery(DtoComunHrProfesion.class, "hrprofesion.listardtofiltros", parametros);
		return new ResponseEntity<List<DtoComunHrProfesion>>(datos, HttpStatus.OK);
	}
	
	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Profesiones de la tabla HR_Profesion por id de Area | Parametros de entrada: id Area | Parametros de salida: codigo, nombre", 
			value = "HR-PROFE-C0001", tags = {"RRHH", "PROFESION"})
	@Transactional
	@PutMapping(value = "/listartablaporarea", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listartablaporarea(
			@ApiParam(value = "Id de Area", required = true)
			@RequestBody DtoComunHrProfesion dto)
	{
		logger.debug("listar profesiones por area");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_area", String.class, dto.getArea()));
        List datos = this.listarPorQuery(DtoTabla.class, "hrprofesion.listartablaporarea", parametros);  
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);		
	}
	
	@Transactional
	@PutMapping(value = "/listarpaginado",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunHrProfesion filtro)
    {
		logger.debug("listarPaginacion");
		Integer cantidadEncontrado = 0;
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

        if (UString.estaVacio(filtro.getProfesion()))
            filtro.setProfesion(null);
        if (UString.estaVacio(filtro.getDescripcion()))
            filtro.setDescripcion(null);
        
        parametros.add(new DominioParametroPersistencia("p_profesion", String.class, filtro.getProfesion()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
 
        cantidadEncontrado = this.contar("hrprofesion.listarPaginadoContar", parametros);

        List lstRegistros = this.listarConPaginacion(filtro.getPaginacion(), parametros, "hrprofesion.listarPaginadoSentencia", 
       		 DtoComunHrProfesion.class);

        filtro.getPaginacion().setPaginacionListaResultado(lstRegistros);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrado);
		
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
}
