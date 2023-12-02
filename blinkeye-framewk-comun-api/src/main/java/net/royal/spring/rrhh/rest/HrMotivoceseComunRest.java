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
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunHrMotivocese;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunHrTipotrabajador;

@RestController
@RequestMapping("/spring/rrhh/hrmotivocesecomun")
@CrossOrigin(origins = "*")
public class HrMotivoceseComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(HrMotivoceseComunRest.class);

	@Autowired
	private CorrelativosmastServicioValidar validar;

	@Autowired
	private CorrelativosmastDaoImpl dao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public HrMotivoceseComunRest() {
		super("hrmotivocese");
	}
	
	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado Activos Tipo trabajador de la tabla Hr_Tipotrabajador y estado activo | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "HR-MOTCESE-CLISTA", tags = {"RRHH", "MOTIVO CESE"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("listar Activos Tipo Trabajador");
		List datos = this.listarPorQuery(DtoTabla.class, "hrmotivocese.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado Activos Tipo trabajador de la tabla Hr_Tipotrabajador y estado activo | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "HR-MOTCESE-CLIACT", tags = {"RRHH", "MOTIVO CESE"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("listar Activos Tipo Trabajador");
		List datos = this.listarPorQuery(DtoTabla.class, "hrmotivocese.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	 
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Obtener Por id Hr_Tipotrabajador | Parametros de entrada: FiltroComunHrCursodescripcion | Parametros de salida: DtoTabla(id, nombre) ", 
			value = "HR-MOTCESE-COBTDTO", tags = {"RRHH", "MOTIVO CESE"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerDtoTablaPorId(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_motivo", BigDecimal.class, filtro.getId()));
        List datos = this.listarPorQuery(DtoTabla.class, "hrmotivocese.obtenertabla", parametros);
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
			value = "HR-MOTCESE-LISFIL", tags = {"RRHH", "MOTIVO CESE"})
	@Transactional
	@PutMapping(value = "/listarfiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarFiltros(@RequestBody DtoTabla filtro) {
		logger.debug("listar Filtros");
		if (UInteger.esCeroOrNulo(filtro.getId()))
			filtro.setId(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);
		else
			filtro.setNombre(filtro.getNombre().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstadoId()))
			filtro.setEstadoId(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();		
		parametros.add(new DominioParametroPersistencia("p_motivo", Integer.class, filtro.getId()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "hrmotivocese.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/**
	 * MALCAN VALIDADO
	 * MOTIVO CESE COMUN SELECTOR
	 */
	@Transactional
	@PostMapping(value = "/listarmotivoceseporfiltro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DominioPaginacion> listarMotivoCesePorFiltro(@RequestBody FiltroComunHrMotivocese filtro)
    {
        logger.debug("HrMotivoceseComunRest.listarmotivoceseporfiltro");        
        Integer cantidadEncontrados = 0;
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        
        if(UInteger.esCeroOrNulo(filtro.getMotivo()))
        	filtro.setMotivo(null);
        if(UString.estaVacio(filtro.getDescripcion()))
        	filtro.setDescripcion(null);

        parametros.add(new DominioParametroPersistencia("p_motivo", Integer.class, filtro.getMotivo()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, "A"));

        cantidadEncontrados = this.contar("hrmotivocese.contarmotivoceseporfiltro", parametros);

        List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros, "hrmotivocese.listarmotivoceseporfiltro", 
        		DtoTabla.class);

        filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);
		
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
}
