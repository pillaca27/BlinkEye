package net.royal.spring.hr.rest;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
//import net.royal.spring.hr.dominio.dto.DtoComunHrGradoinstruccion;
import net.royal.spring.hr.dominio.dto.DtoComunHrUnidadoperativa;
import net.royal.spring.hr.dominio.filtro.FiltroComunHrUnidadoperativa;

@RestController
@RequestMapping("/spring/rrhh/hrunidadoperativacomun")
@CrossOrigin(origins = "*")
public class HrUnidadoperativaComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(HrUnidadoperativaComunRest.class);

	@Autowired
	private CorrelativosmastServicioValidar validar;

	@Autowired
	private CorrelativosmastDaoImpl dao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public HrUnidadoperativaComunRest() {
		super("hrunidadoperativa");
	}
	
	/**
	 * LEONARDO
	 * UNIDAD OPERATIVA COMUN SELECTOR
	 * -Descripcion: Listado de Unidad Operativa por filtro | Parametros de entrada: id unidadoperativa, nombre del unidadoperativa | Parametros de salida: codigo, nombre
	 */
	@Transactional
	@PostMapping(value = "/listarunidadoperativaporfiltro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DominioPaginacion> listarPaisPorFiltro(@RequestBody FiltroComunHrUnidadoperativa filtro)
    {
        logger.debug("HrUnidadoperativaComunRest.listarunidadoperativaporfiltro");        
        Integer cantidadEncontrados = 0;
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        
        if(UString.estaVacio(filtro.getUnidadoperativa()))
        	filtro.setUnidadoperativa(null);
        
        if(UString.estaVacio(filtro.getDescripcion()))
        	filtro.setDescripcion(null);

        parametros.add(new DominioParametroPersistencia("p_unidadoperativa", String.class, filtro.getUnidadoperativa()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
        parametros.add(new DominioParametroPersistencia("p_usuario", String.class, this.getUsuarioActual().getUsuario()));
        //parametros.add(new DominioParametroPersistencia("p_estado", String.class, "A"));

        cantidadEncontrados = this.contar("hrunidadoperativa.contarunidadoperativaporfiltro", parametros);

        List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros, "hrunidadoperativa.listarunidadoperativaporfiltro", 
        		DtoComunHrUnidadoperativa.class);

        filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);
		
        
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
	/***
	 * QQUECHOD VALIDADO
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Listado Activos Tipo trabajador de la tabla hr_unidadoperativa y estado activo | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "HR-UNIOPER-CLISTA", tags = {"RRHH", "UNIDAD OPERATIVA"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("listar Activos Tipo Trabajador");
		List datos = this.listarPorQuery(DtoTabla.class, "hrunidadoperativa.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/
	
	/***
	 * QQUECHOD VALIDADO
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Listado Activos Tipo trabajador de la tabla hr_unidadoperativa y estado activo | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "HR-UNIOPER-CLIACT", tags = {"RRHH", "UNIDAD OPERATIVA"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("listar Activos Tipo Trabajador");
		List datos = this.listarPorQuery(DtoTabla.class, "hrunidadoperativa.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/
	 
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Obtener Por id hr_unidadoperativa | Parametros de entrada: FiltroComunHrCursodescripcion | Parametros de salida: DtoTabla(id, nombre) ", 
			value = "HR-UNIOPER-COBTDTO", tags = {"RRHH", "UNIDAD OPERATIVA"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenertabla(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_unidadoperativa", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "hrunidadoperativa.obtenertabla", parametros);
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
	/*@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla HR_GradoInstruccion "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoComunHrGradoinstruccion", 
			value = "HR-UNIOPER-COBTENER", tags = {"RRHH", "UNIDAD OPERATIVA"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunHrUnidadoperativa> obtenerdto(@RequestBody DtoComunHrUnidadoperativa pk) throws Exception {
		logger.debug("hrgradoinstruccion.obtenerDtoPorId");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_unidadoperativa", String.class, pk.getUnidadoperativa()));
		List datos = this.listarPorQuery(DtoComunHrUnidadoperativa.class, "hrunidadoperativa.obtenerdto",parametros);
		DtoComunHrUnidadoperativa dto;
		if (datos.size()==1) {
			dto = (DtoComunHrUnidadoperativa)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunHrUnidadoperativa();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunHrUnidadoperativa>(dto,HttpStatus.OK);
	}*/
	
	/**
	 * QQUECHOD VALIDADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	/*@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla HR_GradoInstruccion "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoComunHrGradoinstruccion", 
			value = "RH-GRAINST-LISDTOFIL", tags = {"RRHH", "GRADO INSTRUCCION"})
	@Transactional
	@PutMapping(value="/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunHrUnidadoperativa>> listarfiltros(@RequestBody DtoComunHrUnidadoperativa filtro) throws Exception {
		logger.debug("hrgradoinstruccion.listarfiltros");
		if (UString.esNuloVacio(filtro.getUnidadoperativa()))
			filtro.setUnidadoperativa(null);
		if (UString.esNuloVacio(filtro.getDescripcion()))
			filtro.setDescripcion(null);
		else
			filtro.setDescripcion(filtro.getDescripcion().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_unidadoperativa", String.class, filtro.getUnidadoperativa()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		List datos = this.listarPorQuery(DtoComunHrUnidadoperativa.class, "hrunidadoperativa.listardtofiltros",parametros);
		return new ResponseEntity<List<DtoComunHrUnidadoperativa>>(datos,HttpStatus.OK);
	}*/
}
