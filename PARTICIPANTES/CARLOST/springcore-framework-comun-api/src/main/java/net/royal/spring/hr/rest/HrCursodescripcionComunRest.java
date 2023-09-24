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
import net.royal.spring.hr.dominio.dto.DtoComunHrCursodescripcion;
import net.royal.spring.hr.dominio.filtro.FiltroComunHrCursodescripcion;

@RestController
@RequestMapping("/spring/rrhh/hrcursodescripcioncomun")
@CrossOrigin(origins = "*")
public class HrCursodescripcionComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(HrCursodescripcionComunRest.class);

	@Autowired
	private CorrelativosmastServicioValidar validar;

	@Autowired
	private CorrelativosmastDaoImpl dao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public HrCursodescripcionComunRest() {
		super("hrcursodescripcion");
	}
	
	/*
	 * LEONARDO
	 * LISTADO CURSO SELECTOR
	 * 
	 * */
	@ApiOperation(notes = "-Descripcion: Listado selector de CURSO de la tabla HR_CursoDescripcion | Sin parametros de entrada | Parametros de salida: id, nombre", 
			value = "HR-CURSO-SELECTOR", tags = {"RRHH", "CURSO DESCRIPCION"})
	@Transactional
	@PostMapping(value = "/listarcursoporfiltro",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarCursosPaginacion(@RequestBody FiltroComunHrCursodescripcion filtro)
    {
		
		logger.debug("HrCursodescripcionComunRest.listarcursoporfiltro");
		Integer cantidadEncontrado = 0;
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

        if (UInteger.esCeroOrNulo(filtro.getCurso()))
            filtro.setCurso(null);
        if (UString.estaVacio(filtro.getDescripcion()))
            filtro.setDescripcion(null);
        if (UString.estaVacio(filtro.getTipomaestro()))
            filtro.setTipomaestro(null);
        
        parametros.add(new DominioParametroPersistencia("p_curso", Integer.class, filtro.getCurso()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
        parametros.add(new DominioParametroPersistencia("p_tipomaestro", String.class, filtro.getTipomaestro())); 
 

        cantidadEncontrado = this.contar("hrcursodescripcion.contarcursoporfiltro", parametros);

        List lstRegistros = this.listarConPaginacion(filtro.getPaginacion(), parametros, "hrcursodescripcion.listarcursoporfiltro", 
       		 DtoComunHrCursodescripcion.class);

        filtro.getPaginacion().setPaginacionListaResultado(lstRegistros);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrado);
		
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
	/**
	 * ARMAS MIGRADO
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Listado de Idiomas de la tabla HR_CursoDescripcion | Sin parametros de entrada | Parametros de salida: id, nombre", 
			value = "HR-CURSO-LISTAR", tags = {"RRHH", "CURSO DESCRIPCION"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar()
	{
		logger.debug("listar cursos");
		List datos = this.listarPorQuery(DtoTabla.class, "hrcursodescripcion.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/
	
	/**
	 * ARMAS MIGRADO
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Listado de Idiomas de la tabla HR_CursoDescripcion estado Activo | Sin parametros de entrada | Parametros de salida: id, nombre", 
			value = "HR-CURSO-LISACT", tags = {"RRHH", "CURSO DESCRIPCION"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos()
	{
		logger.debug("listar cursos activos");
		List datos = this.listarPorQuery(DtoTabla.class, "hrcursodescripcion.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/	
	
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Obtener Por id HR_CursoDescripcion | Parametros de entrada: FiltroComunHrCursodescripcion | Parametros de salida: DtoTabla(id, nombre) ", 
			value = "HR-CURSO-OBTTAB", tags = {"RRHH", "CURSO DESCRIPCION"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenertabla(@RequestBody DtoTabla filtro) { 
		logger.debug("obtenertabla");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_curso", BigDecimal.class, filtro.getId()));
        List datos = this.listarPorQuery(DtoTabla.class, "hrcursodescripcion.obtenertabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}*/
	
	/**
	 * QQUECHOD VALIDADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	/*@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla As_Tipohorario "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "HR-CURSO-OBTDTO", tags = {"RRHH", "CURSO DESCRIPCION"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunHrCursodescripcion> obtenerdto(@RequestBody DtoComunHrCursodescripcion pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_curso", BigDecimal.class, pk.getCurso()));
		List datos = this.listarPorQuery(DtoComunHrCursodescripcion.class, "hrcursodescripcion.obtenerdto",parametros);
		DtoComunHrCursodescripcion dto;
		if (datos.size()==1) {
			dto = (DtoComunHrCursodescripcion)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunHrCursodescripcion();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunHrCursodescripcion>(dto,HttpStatus.OK);
	}*/
	
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Listado tabla HR_CursoDescripcion | Parametros de entrada: FiltroComunHrCursodescripcion | Parametros de salida: DtoTabla(id, nombre) ", 
			value = "HR-CURSO-LISDTOFIL", tags = {"RRHH", "CURSO DESCRIPCION"})
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunHrCursodescripcion>> listardtofiltros(@RequestBody DtoComunHrCursodescripcion filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UBigDecimal.esCeroOrNulo(filtro.getCurso()))
            filtro.setCurso(null);
        if (UString.estaVacio(filtro.getDescripcion()))
            filtro.setDescripcion(null);
        else
        	filtro.setDescripcion(filtro.getDescripcion().toUpperCase());
        if (UString.estaVacio(filtro.getEstado()))
            filtro.setEstado(null);
        if (UString.estaVacio(filtro.getTipo()))
            filtro.setTipo(null);
        if (UString.estaVacio(filtro.getArea()))
            filtro.setArea(null);
        parametros.add(new DominioParametroPersistencia("p_curso", BigDecimal.class, filtro.getCurso()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        parametros.add(new DominioParametroPersistencia("p_tipo", String.class, filtro.getTipo()));
        parametros.add(new DominioParametroPersistencia("p_area", String.class, filtro.getArea()));
        List datos = this.listarPorQuery(DtoComunHrCursodescripcion.class, "hrcursodescripcion.listarfiltros", parametros);
		return new ResponseEntity<List<DtoComunHrCursodescripcion>>(datos, HttpStatus.OK); 
	}*/
	
	/**
	 * ARMAS MIGRADO
	 * @param filtro
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Listado Paginado de la tabla HR_CursoDescripcion estado Activo | Parametros de entrada: FiltroPaginacionCurso | Parametros de salida: idCurso, nombre, tipoId, areaId, areaNombre, estadoId", 
			value = "HR-CURSO-CLSPAG", tags = {"RRHH", "CURSO DESCRIPCION"})
	@Transactional
	@PutMapping(value = "/listarcursospaginacion",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarCursosPaginacion(@RequestBody FiltroComunHrCursodescripcion filtro)
    {
		
		Integer cantidadEncontrado = 0;
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

        if (UInteger.esCeroOrNulo(filtro.getCurso()))
            filtro.setCurso(null);
        if (UString.estaVacio(filtro.getDescripcion()))
            filtro.setDescripcion(null);
        if (UString.estaVacio(filtro.getEstado()))
            filtro.setEstado(null);
        if (UString.estaVacio(filtro.getTipo()))
            filtro.setTipo(null);
        if (UString.estaVacio(filtro.getArea()))
            filtro.setArea(null);

        parametros.add(new DominioParametroPersistencia("p_id_curso", Integer.class, filtro.getCurso()));
        parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getDescripcion()));
        parametros.add(new DominioParametroPersistencia("p_id_estado", String.class, filtro.getEstado()));
        parametros.add(new DominioParametroPersistencia("p_id_tipo", String.class, filtro.getTipo()));
        parametros.add(new DominioParametroPersistencia("p_id_area", String.class, filtro.getArea()));

        cantidadEncontrado = this.contar("hrcursodescripcion.listarCursosContar", parametros);

        List lstRegistros = this.listarConPaginacion(filtro.getPaginacion(), parametros, "hrcursodescripcion.listarCursosPaginacion", 
       		 DtoComunHrCursodescripcion.class);

        filtro.getPaginacion().setPaginacionListaResultado(lstRegistros);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrado);
		
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }*/
		
	/**
	 * ARMAS MIGRADO
	 * @param filtro
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Listado tabla HR_CursoDescripcion | Parametros de entrada: FiltroComunHrCursodescripcion | Parametros de salida: DtoTabla(id, nombre) ", 
			value = "HR-CURSO-C0001", tags = {"RRHH", "CURSO DESCRIPCION"})
	@Transactional
	@PutMapping(value = "/listarpordescripciontipo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarfiltrosDtoTabla(@RequestBody FiltroComunHrCursodescripcion filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UInteger.esCeroOrNulo(filtro.getCurso()))
            filtro.setCurso(null);
        if (UString.estaVacio(filtro.getDescripcion()))
            filtro.setDescripcion(null);
        if (UString.estaVacio(filtro.getEstado()))
            filtro.setEstado(null);
        if (UString.estaVacio(filtro.getTipo()))
            filtro.setTipo(null);
        if (UString.estaVacio(filtro.getArea()))
            filtro.setArea(null);

        parametros.add(new DominioParametroPersistencia("p_id_curso", Integer.class, filtro.getCurso()));
        parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getDescripcion()));
        parametros.add(new DominioParametroPersistencia("p_id_estado", String.class, filtro.getEstado()));
        parametros.add(new DominioParametroPersistencia("p_id_tipo", String.class, filtro.getTipo()));
        parametros.add(new DominioParametroPersistencia("p_id_area", String.class, filtro.getArea()));
        List datos = this.listarPorQuery(DtoTabla.class, "hrcursodescripcion.listarpordescripciontipo", parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK); 
	}*/
}
