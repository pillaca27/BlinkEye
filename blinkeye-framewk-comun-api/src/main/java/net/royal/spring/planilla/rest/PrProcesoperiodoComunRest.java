package net.royal.spring.planilla.rest;

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
import net.royal.spring.planilla.dominio.dto.DtoComunPrProcesoperiodo;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/planilla/prprocesoperiodocomun")
@CrossOrigin(origins = "*")
public class PrProcesoperiodoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(PrProcesoperiodoComunRest.class);


	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public PrProcesoperiodoComunRest() {
		super("prprocesoperiodo");
	}
	
	
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunPrProcesoperiodo> obtenerDto(@RequestBody DtoComunPrProcesoperiodo pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, pk.getCompaniasocio()));
		parametros.add(new DominioParametroPersistencia("p_periodo", String.class, pk.getPeriodo()));
		parametros.add(new DominioParametroPersistencia("p_tipoplanilla", String.class, pk.getTipoplanilla()));
		parametros.add(new DominioParametroPersistencia("p_tipoproceso", String.class, pk.getTipoproceso()));		
		List datos = this.listarPorQuery(DtoComunPrProcesoperiodo.class, "prprocesoperiodo.obtenerDto",parametros);
		DtoComunPrProcesoperiodo dto;
		if (datos.size()==1) {
			dto = (DtoComunPrProcesoperiodo)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunPrProcesoperiodo();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunPrProcesoperiodo>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunPrProcesoperiodo>> listarDtoFiltros(@RequestBody DtoComunPrProcesoperiodo filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getCompaniasocio()))
			filtro.setCompaniasocio(null);
		if (UString.esNuloVacio(filtro.getPeriodo()))
			filtro.setPeriodo(null);
		if (UString.esNuloVacio(filtro.getTipoplanilla()))
			filtro.setTipoplanilla(null);		
		if (UString.esNuloVacio(filtro.getTipoproceso()))
			filtro.setTipoproceso(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompaniasocio()));
		parametros.add(new DominioParametroPersistencia("p_periodo", String.class, filtro.getPeriodo()));
		parametros.add(new DominioParametroPersistencia("p_tipoplanilla", String.class, filtro.getTipoplanilla()));
		parametros.add(new DominioParametroPersistencia("p_tipoproceso", String.class, filtro.getTipoproceso()));		
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));        
        List datos = this.listarPorQuery(DtoComunPrProcesoperiodo.class, "prprocesoperiodo.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunPrProcesoperiodo>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/obtenerperiodoactivo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerPeriodoActivo(@RequestBody DtoComunPrProcesoperiodo pk) throws Exception {
		logger.debug("obtenerperiodoactivo");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, pk.getCompaniasocio()));
		parametros.add(new DominioParametroPersistencia("p_periodo", String.class, pk.getPeriodo()));
		parametros.add(new DominioParametroPersistencia("p_tipoplanilla", String.class, pk.getTipoplanilla()));
		List datos = this.listarPorQuery(DtoComunPrProcesoperiodo.class, "prprocesoperiodo.obtenerDtoPorCompaniaPeriodoTipoplanillaFlagprocesadoN",parametros);
		DtoComunPrProcesoperiodo dto;
		if (datos.size()==1) {
			dto = (DtoComunPrProcesoperiodo)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunPrProcesoperiodo();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<String>(dto.getPeriodo(),HttpStatus.OK);
	}
}
