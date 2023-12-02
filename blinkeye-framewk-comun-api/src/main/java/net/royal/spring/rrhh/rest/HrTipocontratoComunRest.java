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
import net.royal.spring.rrhh.dominio.dto.DtoComunHrEvalplantilla;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrTipocontrato;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrUnidadoperativa;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunHrEvalplantilla;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunHrTipocontrato;

@RestController
@RequestMapping("/spring/rrhh/hrtipocontratocomun")
@CrossOrigin(origins = "*")
public class HrTipocontratoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(HrTipocontratoComunRest.class);

	@Autowired
	private CorrelativosmastServicioValidar validar;

	@Autowired
	private CorrelativosmastDaoImpl dao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public HrTipocontratoComunRest() {
		super("hrtipocontrato");
	}
	
	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Tipos de contratos activos de la tabla HR_TipoContrato | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "RH-TIPCONT-CLISTA", tags = {"RRHH", "TIPO CONTRATO"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar()
	{
		logger.debug("listar tipo contratos");
		List datos = this.listarPorQuery(DtoTabla.class, "hrtipocontrato.listar");    
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);		
	}
	
	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Tipos de contratos activos de la tabla HR_TipoContrato | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "RH-TIPCONT-CLIACT", tags = {"RRHH", "TIPO CONTRATO"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos()
	{
		logger.debug("listar tipo contratos");
		List datos = this.listarPorQuery(DtoTabla.class, "hrtipocontrato.listarActivos");    
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);		
	}
	
	/***
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado Activos Tipo trabajador de la tabla Hr_Tipotrabajador y estado activo | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "HR-AFP-LISFIL", tags = {"RRHH", "AFP"})
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
		parametros.add(new DominioParametroPersistencia("p_tipocontrato", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "hrtipocontrato.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	 
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Obtener Por id Hr_Tipotrabajador | Parametros de entrada: FiltroComunHrCursodescripcion | Parametros de salida: DtoTabla(id, nombre) ", 
			value = "HR-AFP-OBTDTO", tags = {"RRHH", "AFP"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_tipocontrato", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "hrtipocontrato.obtenertabla", parametros);
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
			value = "HR-UNIOPER-COBTENER", tags = {"RRHH", "UNIDAD OPERATIVA"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunHrTipocontrato> obtenerdto(@RequestBody DtoComunHrTipocontrato pk) throws Exception {
		logger.debug("hrgradoinstruccion.obtenerDtoPorId");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipocontrato", String.class, pk.getTipocontrato()));
		List datos = this.listarPorQuery(DtoComunHrTipocontrato.class, "hrtipocontrato.obtenerdto",parametros);
		DtoComunHrTipocontrato dto;
		if (datos.size()==1) {
			dto = (DtoComunHrTipocontrato)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunHrTipocontrato();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunHrTipocontrato>(dto,HttpStatus.OK);
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
	public ResponseEntity<List<DtoComunHrTipocontrato>> listarfiltros(@RequestBody DtoComunHrTipocontrato filtro) throws Exception {
		logger.debug("hrgradoinstruccion.listarfiltros");
		if (UString.esNuloVacio(filtro.getTipocontrato()))
			filtro.setTipocontrato(null);
		if (UString.esNuloVacio(filtro.getDescripcion()))
			filtro.setDescripcion(null);
		else
			filtro.setDescripcion(filtro.getDescripcion().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipocontrato", String.class, filtro.getTipocontrato()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		List datos = this.listarPorQuery(DtoComunHrTipocontrato.class, "hrtipocontrato.listardtofiltros",parametros);
		return new ResponseEntity<List<DtoComunHrTipocontrato>>(datos,HttpStatus.OK);
	}
	
	/**
	 * MALCA MIGRADO
	 * HR-TIPOCONTRATO COMUN SELECTOR
	 */
	@Transactional
	@PostMapping(value = "/listarPaginado", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunHrTipocontrato filtro)
    {
        logger.debug("HrTipocontratoComunRest.listarPaginado");        
        Integer cantidadEncontrados = 0;
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

        if(UString.estaVacio(filtro.getTipocontrato()))
        	filtro.setTipocontrato(null);
        if(UString.estaVacio(filtro.getDescripcion()))
        	filtro.setDescripcion(null);

        parametros.add(new DominioParametroPersistencia("p_tipocontr", String.class, filtro.getTipocontrato()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));

        cantidadEncontrados = this.contar("hrtipocontrato.listarPaginadoContar", parametros);

        List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros, "hrtipocontrato.listarPaginadoSentencia", 
        		DtoComunHrTipocontrato.class);

        filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);
		
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
}
