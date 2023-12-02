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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.asistencia.dominio.dto.DtoComunAsConceptoacceso;
import net.royal.spring.asistencia.dominio.dto.DtoComunAsPeriodo;
import net.royal.spring.asistencia.dominio.dto.DtoComunAsTipohorario;
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/asistencia/asperiodocomun")
@CrossOrigin(origins = "*")
public class AsPeriodoComunRest extends GenericoHibernateRest {
 
	private static Logger logger = LogManager.getLogger(AsPeriodoComunRest.class);

	@Autowired
	private CorrelativosmastDaoImpl dao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AsPeriodoComunRest() {
		super("asperiodo");
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "Listado de todos los Horarios de la tabla As_Periodo "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "AS-PERIODO-CLISTA", tags = {"ASISTENCIA", "PERIODO"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> lista() {
		logger.debug("AsPeriodoComunRest.listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "asperiodo.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla As_Periodo "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "AS-PERIODO-CLIACT", tags = {"ASISTENCIA", "PERIODO"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaActivos() {
		logger.debug("AsPeriodoComunRest.listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "asperiodo.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla As_Periodo "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "AS-PERIODO-COBTTAB", tags = {"ASISTENCIA", "PERIODO"})
	@Transactional
	@PutMapping(value="/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla pk) throws Exception {
		logger.debug("AsPeriodoComunRest.obtenertabla");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_secuencia", BigDecimal.class, pk.getId()));
		List datos = this.listarPorQuery(DtoTabla.class, "asperiodo.obtenertabla",parametros);
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
			value = "AS-PERIODO-COBTDTO", tags = {"ASISTENCIA", "PERIODO"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAsPeriodo> obtenerDto(@RequestBody DtoComunAsPeriodo pk) throws Exception {
		logger.debug("AsConceptoaccesoComunRest.obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_secuencia", BigDecimal.class, pk.getSecuencia()));
		List datos = this.listarPorQuery(DtoComunAsPeriodo.class, "asperiodo.obtenerdto",parametros);
		DtoComunAsPeriodo dto;
		if (datos.size()==1) {
			dto = (DtoComunAsPeriodo)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunAsPeriodo();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunAsPeriodo>(dto,HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla As_Tipohorario "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "AS-PERIODO-CLISFIL", tags = {"ASISTENCIA", "PERIODO"})
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAsPeriodo>> listarDtoFiltros(@RequestBody DtoComunAsPeriodo filtro) {
		logger.debug("AsConceptoaccesoComunRest.listarfiltros");
		if (UString.esNuloVacio(filtro.getSecuencia()))
			filtro.setSecuencia(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);
		else
			filtro.setNombre(filtro.getNombre().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_secuencia", BigDecimal.class, filtro.getSecuencia()));
        parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunAsPeriodo.class, "asperiodo.listardtofiltros", parametros);
		return new ResponseEntity<List<DtoComunAsPeriodo>>(datos, HttpStatus.OK);
	}
	
	

	
	
	
}