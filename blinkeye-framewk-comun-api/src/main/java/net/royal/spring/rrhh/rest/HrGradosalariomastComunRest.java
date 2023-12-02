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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrCargosmast;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrGradoinstruccion;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrGradosalariomast;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunHrCargosmast;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunHrGradosalario;

@RestController
@RequestMapping("/spring/rrhh/hrgradosalariomastcomun")
@CrossOrigin(origins = "*")
public class HrGradosalariomastComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(HrGradosalariomastComunRest.class);

	@Autowired
	private CorrelativosmastServicioValidar validar;

	@Autowired
	private CorrelativosmastDaoImpl dao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public HrGradosalariomastComunRest() {
		super("hrgradosalariomast");
	}
	
	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Niveles Salariales de la tabla Hr_Gradosalariomast"
			+ " | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "HR-GRASALA-LISTAR", tags = {"RRHH", "GRADO SALARIO"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("listar Activos Nivel Salarial");
		List datos = this.listarPorQuery(DtoTabla.class, "hrgradosalariomast.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Niveles Salariales de la tabla Hr_Gradosalariomast y estado activo"
			+ " | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "HR-GRASALA-LISACT", tags = {"RRHH", "GRADO SALARIO"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("listar Activos Nivel Salarial");
		List datos = this.listarPorQuery(DtoTabla.class, "hrgradosalariomast.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Obtener Por id Hr_Tipotrabajador | Parametros de entrada: FiltroComunHrCursodescripcion | Parametros de salida: DtoTabla(id, nombre) ", 
			value = "HR-GRASALA-OBTTAB", tags = {"RRHH", "GRADO INSTRUCCION"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_gradosalario", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "hrgradosalariomast.obtenertabla", parametros);
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
			value = "HR-GRASALA-OBTDTO", tags = {"RRHH", "GRADO INSTRUCCION"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunHrGradosalariomast> obtenerDtoPorId(@RequestBody DtoComunHrGradosalariomast pk) throws Exception {
		logger.debug("hrgradoinstruccion.obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_gradosalario", String.class, pk.getGradosalario()));
		List datos = this.listarPorQuery(DtoComunHrGradosalariomast.class, "hrgradosalariomast.obtenerdto",parametros);
		DtoComunHrGradosalariomast dto;
		if (datos.size()==1) {
			dto = (DtoComunHrGradosalariomast)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunHrGradosalariomast();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunHrGradosalariomast>(dto,HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla HR_GradoInstruccion "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoComunHrGradoinstruccion", 
			value = "HR-GRASALA-LISDTOFIL", tags = {"RRHH", "GRADO INSTRUCCION"})
	@Transactional
	@PutMapping(value="/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunHrGradosalariomast>> listarfiltros(@RequestBody DtoComunHrGradosalariomast filtro) throws Exception {
		logger.debug("hrgradoinstruccion.listarfiltros");
		if (UString.esNuloVacio(filtro.getGradosalario()))
			filtro.setGradosalario(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		else
			filtro.setDescripcionlocal(filtro.getDescripcionlocal().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_gradosalario", String.class, filtro.getGradosalario()));
		parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		List datos = this.listarPorQuery(DtoComunHrGradosalariomast.class, "hrgradosalariomast.listardtofiltros",parametros);
		return new ResponseEntity<List<DtoComunHrGradosalariomast>>(datos,HttpStatus.OK);
	}
	
	/**
	 * MALCAN VALIDADO
	 * HR-GRADOSALARIO COMUN SELECTOR
	 */
	@Transactional
	@PostMapping(value = "/listargradosalarioporfiltro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DominioPaginacion> listarGradoSalarioPorFiltro(@RequestBody FiltroComunHrGradosalario filtro)
    {
        logger.debug("HrGradosalariomastComunRest.listargradosalarioporfiltro");        
        Integer cantidadEncontrados = 0;
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        
        if(UString.estaVacio(filtro.getGradosalario()))
        	filtro.setGradosalario(null);
        if(UString.estaVacio(filtro.getDescripcionlocal()))
        	filtro.setDescripcionlocal(null);
        if(UString.estaVacio(filtro.getCargo()))
        	filtro.setCargo(null);

        parametros.add(new DominioParametroPersistencia("p_gradosalario", String.class, filtro.getGradosalario()));
        parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, "A"));
        parametros.add(new DominioParametroPersistencia("p_cargo", String.class, filtro.getCargo()));

        cantidadEncontrados = this.contar("hrgradosalariomast.contargradosalarioporfiltro", parametros);

        List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros, "hrgradosalariomast.listargradosalarioporfiltro", 
        		DtoComunHrGradosalariomast.class);

        filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);
		
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
}
