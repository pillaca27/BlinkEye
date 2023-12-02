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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrGradoinstruccion;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunHrGradoinstruccion;

@RestController
@RequestMapping("/spring/rrhh/hrgradoinstruccioncomun")
@CrossOrigin(origins = "*")
public class HrGradoinstruccionComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(HrGradoinstruccionComunRest.class);


	@Autowired
	private CorrelativosmastServicioValidar validar;

	@Autowired
	private CorrelativosmastDaoImpl dao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public HrGradoinstruccionComunRest() {
		super("hrgradoinstruccion");
	}
	
	/**
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Grados de Instruccion de la tabla HR_GradoInstruccion | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "RH-GRAINST-LISTAR", tags = {"RRHH", "GRADO INSTRUCCION"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar()
	{
		logger.debug("listar grados costos");
		List datos = this.listarPorQuery(DtoTabla.class, "hrgradoinstruccion.listar");   
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);		
	}
	
	/**
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Grados de Instruccion de la tabla HR_GradoInstruccion y estado activo | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "RH-GRAINST-LISACT", tags = {"RRHH", "GRADO INSTRUCCION"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos()
	{
		logger.debug("listar grados costos");
		List datos = this.listarPorQuery(DtoTabla.class, "hrgradoinstruccion.listarActivos");   
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);		
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Obtener Por id Hr_Tipotrabajador | Parametros de entrada: FiltroComunHrCursodescripcion | Parametros de salida: DtoTabla(id, nombre) ", 
			value = "HR-GRAINST-OBTTAB", tags = {"RRHH", "GRADO INSTRUCCION"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_gradoinstruccion", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "hrgradoinstruccion.obtenertabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla HR_GradoInstruccion "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoComunHrGradoinstruccion", 
			value = "RH-GRAINST-OBTDTO", tags = {"RRHH", "GRADO INSTRUCCION"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunHrGradoinstruccion> obtenerDtoPorId(@RequestBody DtoComunHrGradoinstruccion pk) throws Exception {
		logger.debug("hrgradoinstruccion.obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_gradoinstruccion", String.class, pk.getGradoinstruccion()));
		List datos = this.listarPorQuery(DtoComunHrGradoinstruccion.class, "hrgradoinstruccion.obtenerdto",parametros);
		DtoComunHrGradoinstruccion dto;
		if (datos.size()==1) {
			dto = (DtoComunHrGradoinstruccion)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunHrGradoinstruccion();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunHrGradoinstruccion>(dto,HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla HR_GradoInstruccion "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoComunHrGradoinstruccion", 
			value = "RH-GRAINST-LISDTOFIL", tags = {"RRHH", "GRADO INSTRUCCION"})
	@Transactional
	@PutMapping(value="/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunHrGradoinstruccion>> listarfiltros(@RequestBody DtoComunHrGradoinstruccion filtro) throws Exception {
		logger.debug("hrgradoinstruccion.listarfiltros");
		if (UString.esNuloVacio(filtro.getGradoinstruccion()))
			filtro.setGradoinstruccion(null);
		if (UString.esNuloVacio(filtro.getDescripcion()))
			filtro.setDescripcion(null);
		else
			filtro.setDescripcion(filtro.getDescripcion().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_gradoinstruccion", String.class, filtro.getGradoinstruccion()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		List datos = this.listarPorQuery(DtoComunHrGradoinstruccion.class, "hrgradoinstruccion.listardtofiltros",parametros);
		return new ResponseEntity<List<DtoComunHrGradoinstruccion>>(datos,HttpStatus.OK);
	}
	
	/*
	 * MALCAN
	 * LISTADO GRADO INSTRUCCION
	 * 
	 * */
	@Transactional
	@PostMapping(value = "/listarpaginado",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunHrGradoinstruccion filtro)
    {
		logger.debug("hrgradoinstruccion.listarpaginado");
		Integer cantidadEncontrado = 0;
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

        if (UString.estaVacio(filtro.getGradoinstruccion()))
            filtro.setGradoinstruccion(null);
        if (UString.estaVacio(filtro.getDescripcion()))
            filtro.setDescripcion(null);
        
        parametros.add(new DominioParametroPersistencia("p_gradoinstruccion", String.class, filtro.getGradoinstruccion()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
 
        cantidadEncontrado = this.contar("hrgradoinstruccion.listarPaginadoContar", parametros);

        List lstRegistros = this.listarConPaginacion(filtro.getPaginacion(), parametros, "hrgradoinstruccion.listarPaginadoSentencia", 
       		 DtoComunHrGradoinstruccion.class);

        filtro.getPaginacion().setPaginacionListaResultado(lstRegistros);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrado);
		
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
}
