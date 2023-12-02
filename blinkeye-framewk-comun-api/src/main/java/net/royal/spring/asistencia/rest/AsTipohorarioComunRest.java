package net.royal.spring.asistencia.rest;

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
import net.royal.spring.asistencia.dominio.dto.DtoComunAsPeriodo;
import net.royal.spring.asistencia.dominio.dto.DtoComunAsTipohorario;
import net.royal.spring.asistencia.dominio.filtro.FiltroComunAsTipohorario;
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunHrTipotrabajador;

@RestController
@RequestMapping("/spring/asistencia/astipohorariocomun")
@CrossOrigin(origins = "*")
public class AsTipohorarioComunRest extends GenericoHibernateRest {
 
	private static Logger logger = LogManager.getLogger(AsTipohorarioComunRest.class);

	@Autowired
	private CorrelativosmastDaoImpl dao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AsTipohorarioComunRest() {
		super("astipohorario");
	}
	
	/**
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla As_Tipohorario "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "AS-TIPHORA-CLISTA", tags = {"ASISTENCIA", "TIPO HORARIO"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("AsTipohorarioRest.listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "astipohorario.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/**
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla As_Tipohorario "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "AS-TIPHORA-CLISACT", tags = {"ASISTENCIA", "TIPO HORARIO"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("AsTipohorarioRest.listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "astipohorario.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla As_Tipohorario "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "AS-TIPHORA-COBTTAB", tags = {"ASISTENCIA", "TIPO HORARIO"})
	@Transactional
	@PutMapping(value="/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenertabla(@RequestBody DtoTabla pk) throws Exception {
		logger.debug("AsTipohorarioRest.obtenerDtoPorId");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipohorario", BigDecimal.class, pk.getId()));
		List datos = this.listarPorQuery(DtoTabla.class, "astipohorario.obtenertabla",parametros);
		DtoTabla dto = null;
		if (datos.size()==1)
			dto = (DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto,HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla As_Tipohorario "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "AS-TIPHORA-COBTDTO", tags = {"ASISTENCIA", "TIPO HORARIO"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAsTipohorario> obtenerDto(@RequestBody DtoComunAsTipohorario pk) throws Exception {
		logger.debug("AsTipohorarioRest.obtenerDtoPorId");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipohorario", BigDecimal.class, pk.getTipohorario()));
		List datos = this.listarPorQuery(DtoComunAsTipohorario.class, "astipohorario.obtenerdto",parametros);
		DtoComunAsTipohorario dto;
		if (datos.size()==1) {
			dto = (DtoComunAsTipohorario)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunAsTipohorario();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunAsTipohorario>(dto,HttpStatus.OK);
	}
	
	
	/**
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla As_Tipohorario "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "AS-TIPHORA-CLISFIL", tags = {"ASISTENCIA", "PERIODO"})
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAsTipohorario>> listarDtoFiltros(@RequestBody DtoComunAsTipohorario filtro) {
		logger.debug("AsTipohorarioRest.listarfiltros");
		if (UString.esNuloVacio(filtro.getTipohorario()))
			filtro.setTipohorario(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		else
			filtro.setDescripcionlocal(filtro.getDescripcionlocal().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_tipohorario", BigDecimal.class, filtro.getTipohorario()));
        parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunAsTipohorario.class, "astipohorario.listardtofiltros", parametros);
		return new ResponseEntity<List<DtoComunAsTipohorario>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Listar dto orden tipo horario",					
			nickname="AS-TIPOHORARIO", value = "AS-TIPHORA-COBTDTO", tags = {"ASISTENCIA", "TIPO HORARIO"})
	@Transactional
	@GetMapping(value = "/listardtoordentipohorario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAsTipohorario>> listarDtoOrdenTipohorario() {
		logger.debug("AsTipohorarioRest.listardtoordentipohorario");
        List datos = this.listarPorQuery(DtoComunAsTipohorario.class, "astipohorario.listardtoordentipohorario");
		return new ResponseEntity<List<DtoComunAsTipohorario>>(datos, HttpStatus.OK);
	}
	
	
	@Transactional
	@GetMapping(value = "/obtenercomboTipoHorario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> obtenercomboTipoHorario() {
		logger.debug("AsTipohorarioRest.obtenercomboTipoHorario");
		List<DominioParametroPersistencia> parametrosClave = new ArrayList<DominioParametroPersistencia>();
		List listado = this.listarPorQuery(DtoTabla.class, "astipohorario.obtenercomboTipoHorario",parametrosClave);
		
		return new ResponseEntity<List<DtoTabla>>(listado, HttpStatus.OK);
	}
	
	/**
	 * MALCAN VALIDADO
	 * TIPO HORARIO COMUN SELECTOR
	 */
	@Transactional
	@PostMapping(value = "/listartipohorarioporfiltro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DominioPaginacion> listarTipoHorarioPorFiltro(@RequestBody FiltroComunAsTipohorario filtro)
    {
        logger.debug("AsTipohorarioRest.listartipohorarioporfiltro");        
        Integer cantidadEncontrados = 0;
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        
        if(UInteger.esCeroOrNulo(filtro.getTipohorario()))
        	filtro.setTipohorario(null);
        if(UString.estaVacio(filtro.getDescripcion()))
        	filtro.setDescripcion(null);

        parametros.add(new DominioParametroPersistencia("p_tipo", Integer.class, filtro.getTipohorario()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, "A"));

        cantidadEncontrados = this.contar("astipohorario.contartipohorarioporfiltro", parametros);

        List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros, "astipohorario.listartipohorarioporfiltro", 
        		DtoTabla.class);

        filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);
		
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
}