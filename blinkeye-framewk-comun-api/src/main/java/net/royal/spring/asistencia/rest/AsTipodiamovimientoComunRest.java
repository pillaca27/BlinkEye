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
import net.royal.spring.asistencia.dominio.dto.DtoComunAsPeriodo;
import net.royal.spring.asistencia.dominio.dto.DtoComunAsTipodiamovimiento;
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
@RequestMapping("/spring/asistencia/astipodiamovimientocomun")
@CrossOrigin(origins = "*")
public class AsTipodiamovimientoComunRest extends GenericoHibernateRest {
 
	private static Logger logger = LogManager.getLogger(AsTipodiamovimientoComunRest.class);

	@Autowired
	private CorrelativosmastDaoImpl dao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AsTipodiamovimientoComunRest() {
		super("astipodiamovimiento");
	}
	
	@ApiOperation(notes = "Obtener por PK de la tabla as_tipodiamovimiento"	,  
			nickname="AS_TIPODIA-OBTENERDTO", value = "OBTENER DTO", tags = {"ASISTENCIA","AS_AREA","AREA","OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAsTipodiamovimiento> obtenerDto(@RequestBody DtoComunAsTipodiamovimiento pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipodia", BigDecimal.class, pk.getTipodia()));
		parametros.add(new DominioParametroPersistencia("p_intervaloacceso", String.class, pk.getIntervaloacceso()));
		parametros.add(new DominioParametroPersistencia("p_secuencia", BigDecimal.class, pk.getSecuencia()));
		List datos = this.listarPorQuery(DtoComunAsTipodiamovimiento.class, "astipodiamovimiento.obtenerdto",parametros);
		DtoComunAsTipodiamovimiento dto;
		if (datos.size()==1) {
			dto = (DtoComunAsTipodiamovimiento)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunAsTipodiamovimiento();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunAsTipodiamovimiento>(dto,HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Listar con filtros de la tabla as_tipodiamovimiento",
			nickname="as_tipodiamovimiento-LISTARDTOFILTROS", value = "LISTAR DTO CON FILTROS", tags = {"ASISTENCIA","AS_AREA", "AREA", "LISTAR"})
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAsTipodiamovimiento>> listarDtoFiltros(@RequestBody DtoComunAsTipodiamovimiento filtro) {
		logger.debug("listardtofiltros");
		if (UBigDecimal.esCeroOrNulo(filtro.getTipodia()))
			filtro.setTipodia(null);
		if (UString.esNuloVacio(filtro.getIntervaloacceso()))
			filtro.setIntervaloacceso(null);
		if (UBigDecimal.esCeroOrNulo(filtro.getSecuencia()))
			filtro.setSecuencia(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipodia", BigDecimal.class, filtro.getTipodia()));
		parametros.add(new DominioParametroPersistencia("p_intervaloacceso", String.class, filtro.getIntervaloacceso()));
		parametros.add(new DominioParametroPersistencia("p_secuencia", BigDecimal.class, filtro.getSecuencia()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunAsTipodiamovimiento.class, "astipodiamovimiento.listardtofiltros", parametros);
		return new ResponseEntity<List<DtoComunAsTipodiamovimiento>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener Dto Interno acceso maximo"  								
			+ "<br><b>Entrada:</b>Tipo dia",			 
			nickname="AS-TIPODIA-INTERACCESOMAX", value = "OBTENER DTO INTERNO ACCESO MAXIMO", tags = {"TIPODIA","AS_TIPODIA", "INTERNO", "MAX"})
	@Transactional
	@PutMapping(value="/obtenerdtointeraccesomax", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAsTipodiamovimiento> obtenerDtoInterAccesoMax(@RequestBody DtoComunAsTipodiamovimiento pk) throws Exception {
		logger.debug("obtenerdtointeraccesomax");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipodia", BigDecimal.class, pk.getTipodia()));
		List datos = this.listarPorQuery(DtoComunAsTipodiamovimiento.class, "astipodiamovimiento.obtenerDtoInterAccesoMax",parametros);
		DtoComunAsTipodiamovimiento dto;
		if (datos.size()==1) {
			dto = (DtoComunAsTipodiamovimiento)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunAsTipodiamovimiento();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunAsTipodiamovimiento>(dto,HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener Dto Horario inicio fin max min"  								
			+ "<br><b>Entrada:</b>Tipo dia",			 
			nickname="AS-TIPODIA-MOVIMIENTO", value = "OBTENER DTO TIPO DIA MOVIMIENTO", tags = {"TIPODIA","AS_TIPODIAMOVIMIENTO", "INTERNO", "MAX"})
	@Transactional
	@PutMapping(value="/obtenerdtohorainiciofinmaxmin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAsTipodiamovimiento> obtenerDtoHoraInicioFinMaxMin(@RequestBody DtoComunAsTipodiamovimiento pk) throws Exception {
		logger.debug("obtenerDtoHoraInicioFinMaxMin");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipodia", BigDecimal.class, pk.getTipodia()));
		List datos = this.listarPorQuery(DtoComunAsTipodiamovimiento.class, "astipodiamovimiento.obtenerDtoHoraInicioFinMaxMin",parametros);
		DtoComunAsTipodiamovimiento dto;
		if (datos.size()==1) {
			dto = (DtoComunAsTipodiamovimiento)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunAsTipodiamovimiento();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunAsTipodiamovimiento>(dto,HttpStatus.OK);
	}
}