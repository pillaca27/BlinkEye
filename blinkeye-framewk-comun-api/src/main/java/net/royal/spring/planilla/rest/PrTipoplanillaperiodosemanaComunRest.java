package net.royal.spring.planilla.rest;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.dominio.BeanCorrelativosmast;
import net.royal.spring.core.dominio.BeanCorrelativosmastPk;
import net.royal.spring.core.dominio.dto.DtoComunCorrelativosmast;
import net.royal.spring.core.dominio.dto.DtoComunTipopago;
import net.royal.spring.core.servicio.impl.CorrelativosmastServicioImpl;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.planilla.dominio.dto.DtoComunPrTipoplanillaperiodosemana;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/planilla/prtipoplanillaperiodosemanacomun")
@CrossOrigin(origins = "*")
public class PrTipoplanillaperiodosemanaComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(PrTipoplanillaperiodosemanaComunRest.class);


	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public PrTipoplanillaperiodosemanaComunRest() {
		super("prtipoplanillaperiodosemana");
	}
	
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunPrTipoplanillaperiodosemana> obtenerDto(@RequestBody DtoComunPrTipoplanillaperiodosemana pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipoplanilla", String.class, pk.getTipoplanilla()));
		parametros.add(new DominioParametroPersistencia("p_periodosemanal", String.class, pk.getPeriodosemanal()));
		parametros.add(new DominioParametroPersistencia("p_xperiodo", String.class, pk.getPeriodo()));
		parametros.add(new DominioParametroPersistencia("p_tipoproceso", String.class, pk.getTipoproceso()));
		parametros.add(new DominioParametroPersistencia("p_secuencia", BigDecimal.class, pk.getSecuencia()));
		List datos = this.listarPorQuery(DtoComunPrTipoplanillaperiodosemana.class, "prtipoplanillaperiodosemana.obtenerDto",parametros);
		DtoComunPrTipoplanillaperiodosemana dto;
		if (datos.size()==1) {
			dto = (DtoComunPrTipoplanillaperiodosemana)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunPrTipoplanillaperiodosemana();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunPrTipoplanillaperiodosemana>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/obtenerperiodosemanacorta", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerPeriodosemanal(@RequestBody DtoComunPrTipoplanillaperiodosemana pk) throws Exception {
		logger.debug("obtenerperiodosemanal");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipoplanilla", String.class, pk.getTipoplanilla()));
		parametros.add(new DominioParametroPersistencia("p_xperiodo", String.class, pk.getPeriodo()));
		parametros.add(new DominioParametroPersistencia("p_tipoproceso", String.class, pk.getTipoproceso()));
		List datos = this.listarPorQuery(DtoComunPrTipoplanillaperiodosemana.class, "prtipoplanillaperiodosemana.obtenerperiodosemanal",parametros);
		DtoComunPrTipoplanillaperiodosemana dto;
		if (datos.size()==1) {
			dto = (DtoComunPrTipoplanillaperiodosemana)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunPrTipoplanillaperiodosemana();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<String>(dto.getAuxPeriodosemanalCorto(),HttpStatus.OK);
	}
	
}
