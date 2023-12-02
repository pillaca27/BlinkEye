package net.royal.spring.rrhh.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.rrhh.dominio.dto.DtoComunHrCompetencia;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrCompetenciacomportamiento;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrEvalevaluacion;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrEvalplantilla;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunHrCompetencia;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunHrEvalevaluacion;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunHrEvalplantilla;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/rrhh/hrevalevaluacioncomun")
@CrossOrigin(origins = "*")
public class HrEvalevaluacionComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(HrEvalevaluacionComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public HrEvalevaluacionComunRest() {
		super("hrevalevaluacion");
	}

	/**
	 * MALCA MIGRADO
	 * HR-EVALEVALUACION COMUN SELECTOR
	 */
	@Transactional
	@PostMapping(value = "/listarPaginado", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunHrEvalevaluacion filtro)
    {
        logger.debug("HrEvalevaluacionRest.listarPaginado");        
        Integer cantidadEncontrados = 0;
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        String pUsuario = getUsuarioActual().getUsuario();
        
        if(UInteger.esCeroOrNulo(filtro.getCodigo()))
        	filtro.setCodigo(null);
        if(UString.estaVacio(filtro.getDescripcion()))
        	filtro.setDescripcion(null);

        parametros.add(new DominioParametroPersistencia("p_codigo", Integer.class, filtro.getCodigo()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
        parametros.add(new DominioParametroPersistencia("p_compania", String.class, filtro.getCompanyowner()));
        parametros.add(new DominioParametroPersistencia("p_evaluacion", Integer.class, filtro.getEvaluacion()));
        parametros.add(new DominioParametroPersistencia("p_plantilla", String.class, filtro.getPlantcriterio()));
        parametros.add(new DominioParametroPersistencia("p_flagcomp", String.class, filtro.getFlagevalcomp()));
        parametros.add(new DominioParametroPersistencia("p_usuario", String.class, pUsuario));

        cantidadEncontrados = this.contar("hrevalevaluacion.listarPaginadoContar", parametros);

        List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros, "hrevalevaluacion.listarPaginadoSentencia", 
        		DtoComunHrEvalevaluacion.class);

        filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);
		
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
	/**
	 * MALCA MIGRADO
	 * HR-EVALPLANTILLA COMUN SELECTOR
	 */
	@Transactional
	@PostMapping(value = "/listarPlantPaginado", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DominioPaginacion> listarPlantPaginado(@RequestBody FiltroComunHrEvalplantilla filtro)
    {
        logger.debug("HrEvalevaluacionRest.listarPlantPaginado");        
        Integer cantidadEncontrados = 0;
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

        if(UString.estaVacio(filtro.getPlantilla()))
        	filtro.setPlantilla(null);
        if(UString.estaVacio(filtro.getDescripcion()))
        	filtro.setDescripcion(null);

        parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getPlantilla()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));

        cantidadEncontrados = this.contar("hrevalevaluacion.listarPlantPaginadoContar", parametros);

        List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros, "hrevalevaluacion.listarPlantPaginadoSentencia", 
        		DtoComunHrEvalplantilla.class);

        filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);
		
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
	/**
	 * MALCA MIGRADO
	 * HR_COMPETENCIA COMUN SELECTOR
	 */
	@Transactional
	@PostMapping(value = "/listarCompetencias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DtoComunHrCompetencia>> listarCompetencias(@RequestBody FiltroComunHrCompetencia filtro)
    {
        logger.debug("HrEvalevaluacionRest.listarCompetencias");        
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

        if(UInteger.esCeroOrNulo(filtro.getCompetencia()))
        	filtro.setCompetencia(null);
        if(UString.estaVacio(filtro.getDescripcion()))
        	filtro.setDescripcion(null);

        parametros.add(new DominioParametroPersistencia("p_codigo", Integer.class, filtro.getCompetencia()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
        parametros.add(new DominioParametroPersistencia("p_maestro", String.class, filtro.getMaestro()));
        parametros.add(new DominioParametroPersistencia("p_flageval", String.class, filtro.getFlagevaluacion()));

        List lstResultado = this.listarPorQuery(DtoComunHrCompetencia.class, "hrevalevaluacion.listarCompetencias", parametros);

        return new ResponseEntity<List<DtoComunHrCompetencia>>(lstResultado, HttpStatus.OK);
    }
	
	/**
	 * MALCA MIGRADO
	 * HR_COMPETENCIACOMPORTAMIENTO COMUN SELECTOR
	 */
	@Transactional
	@PostMapping(value = "/listarComportamientos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DtoComunHrCompetenciacomportamiento>> listarComportamientos(@RequestBody FiltroComunHrCompetencia filtro)
    {
        logger.debug("HrEvalevaluacionRest.listarComportamientos");        
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

        if(UInteger.esCeroOrNulo(filtro.getComportamiento()))
        	filtro.setComportamiento(null);
        if(UString.estaVacio(filtro.getComportadesc()))
        	filtro.setComportadesc(null);

        parametros.add(new DominioParametroPersistencia("p_codigo", Integer.class, filtro.getComportamiento()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getComportadesc()));
        parametros.add(new DominioParametroPersistencia("p_maestro", String.class, filtro.getMaestro()));
        parametros.add(new DominioParametroPersistencia("p_flageval", String.class, filtro.getFlagevaluacion()));
        parametros.add(new DominioParametroPersistencia("p_competen", Integer.class, filtro.getIdcompeten()));

        List lstResultado = this.listarPorQuery(DtoComunHrCompetenciacomportamiento.class, 
        		"hrevalevaluacion.listarComportamientos", parametros);
        
        return new ResponseEntity<List<DtoComunHrCompetenciacomportamiento>>(lstResultado, HttpStatus.OK);
    }

}
