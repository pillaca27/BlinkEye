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
import net.royal.spring.asistencia.dominio.dto.DtoComunAsArea;
import net.royal.spring.asistencia.dominio.dto.DtoComunAsConceptoacceso;
import net.royal.spring.asistencia.dominio.dto.DtoComunAsTipohorario;
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/asistencia/asconceptoaccesocomun")
@CrossOrigin(origins = "*")
public class AsConceptoaccesoComunRest extends GenericoHibernateRest {
 
	private static Logger logger = LogManager.getLogger(AsConceptoaccesoComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AsConceptoaccesoComunRest() {
		super("asconceptoacceso");
	}
		
	
	/**
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla As_Tipohorario "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "AS-CONCEACC-CLISTAR", tags = {"ASISTENCIA", "CONCEPTO ACCESO"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("AsConceptoaccesoComunRest.listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "asconceptoacceso.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla As_Tipohorario "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "AS-CONCEACC-CLISACT", tags = {"ASISTENCIA", "CONCEPTO ACCESO"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("AsConceptoaccesoComunRest.listaractivos");
		List datos = this.listarPorQuery(DtoTabla.class, "asconceptoacceso.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Obtener Por id as_area | Parametros de entrada: FiltroComunHrCursodescripcion | Parametros de salida: DtoTabla(id, nombre) ", 
			value = "AS-CONCEACC-COBTTAB", tags = {"ASISTENCIA", "AREA"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		logger.debug("AsConceptoaccesoComunRest.obtenertabla");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_conceptoacceso", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "asconceptoacceso.obtenertabla", parametros);
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
	@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla As_Tipohorario "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "AS-CONCEACC-COBTDTO", tags = {"ASISTENCIA", "CONCEPTO ACCESO"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAsConceptoacceso> obtenerDto(@RequestBody DtoComunAsConceptoacceso pk) throws Exception {
		logger.debug("AsConceptoaccesoComunRest.obtenerdto");
		DtoComunAsConceptoacceso dto = obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunAsConceptoacceso>(dto,HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener campo Expresado en de la tabla as_conceptoacceso "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "AS-EXPRESADOEN", tags = {"ASISTENCIA", "CONCEPTO ACCESO"})
	@Transactional
	@PutMapping(value="/obtenerexpresadoen", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerExpresadoen(@RequestBody DtoComunAsConceptoacceso pk) throws Exception {
		logger.debug("obtenerexpresadoen");
		DtoComunAsConceptoacceso dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getExpresadoen(),HttpStatus.OK);
	}
	
	public DtoComunAsConceptoacceso obtenerDtoCore(DtoComunAsConceptoacceso pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_conceptoacceso", String.class, pk.getConceptoacceso()));
		List datos = this.listarPorQuery(DtoComunAsConceptoacceso.class, "asconceptoacceso.obtenerdto",parametros);
		DtoComunAsConceptoacceso dto;
		if (datos.size()==1) {
			dto = (DtoComunAsConceptoacceso)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunAsConceptoacceso();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla As_Tipohorario "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "AS-CONCEACC-CLISFIL", tags = {"ASISTENCIA", "CONCEPTO ACCESO"})
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAsConceptoacceso>> listarDtoFiltros(@RequestBody DtoComunAsConceptoacceso filtro) {
		logger.debug("AsConceptoaccesoComunRest.listarfiltros");
		if (UString.esNuloVacio(filtro.getConceptoacceso()))
			filtro.setConceptoacceso(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		else
			filtro.setDescripcionlocal(filtro.getDescripcionlocal().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_conceptoacceso", String.class, filtro.getConceptoacceso()));
        parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunAsConceptoacceso.class, "asconceptoacceso.listardtofiltros", parametros);
		return new ResponseEntity<List<DtoComunAsConceptoacceso>>(datos, HttpStatus.OK);
	}
}