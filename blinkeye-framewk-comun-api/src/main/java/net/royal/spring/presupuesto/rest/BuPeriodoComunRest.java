package net.royal.spring.presupuesto.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import net.royal.spring.core.servicio.impl.CorrelativosmastServicioImpl;

import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.presupuesto.dominio.dto.DtoComunBuNivelserviciomst;
import net.royal.spring.presupuesto.dominio.dto.DtoComunBuPeriodo;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/presupuesto/buperiodocomun")
@CrossOrigin(origins = "*")
public class BuPeriodoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(BuPeriodoComunRest.class);

	@Autowired
	private CorrelativosmastServicioValidar validar;

	@Autowired
	private CorrelativosmastDaoImpl dao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BuPeriodoComunRest() {
		super("buperiodo");
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de PERIODOS | Sin parametros de entrada | Parametros de salida: Listado DtoTabla(id, nombre)", 
			value = "BU-PERIODO-C0001", tags = {"PRESUPUESTO", "PERIODOS"})
	@Transactional
	@GetMapping(value = "/listarperiodospoi", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarPeriodosPoi() { 
		logger.debug("BuPeriodoRest.listarPeriodospoi");		
		List datos = this.listarPorQuery(DtoTabla.class,"buperiodo.listarPeriodospoi");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK); 
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Obtener Periodo Poi Actual | Sin parametros de entrada | Parametros de salida: codigo, descripcion", 
			value = "BU-PERIODO-C0002", tags = {"PRESUPUESTO", "PERIODOS"})
	@Transactional
	@GetMapping(value = "/obtenerperiodopoiactual", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunBuPeriodo> obtenerPeriodoPoiActual() throws Exception {
		
		
		DtoComunBuPeriodo periodo = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();		

		Calendar cal= Calendar.getInstance();
		int year= cal.get(Calendar.YEAR);
		
		Integer periodoNum = year;
		parametros.add(new DominioParametroPersistencia("p_periodo", Integer.class, periodoNum));

		List datos = this.listarPorQuery(DtoComunBuPeriodo.class, "buperiodo.obtenerPeriodoPoiActual", parametros);

		if (datos.size() > 0) {
			periodo = (DtoComunBuPeriodo) datos.get(0);
		}
		
		return new ResponseEntity<DtoComunBuPeriodo>(periodo,
				HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listado de PERIODOS | Sin parametros de entrada | Parametros de salida: Listado DtoListaPeriodos", 
			value = "BU-PERIODO-C0003", tags = {"PRESUPUESTO", "PERIODOS"})
	@Transactional
	@GetMapping(value = "/obtenerlistaperiodos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunBuPeriodo>> obtenerListaPeriodos() throws Exception {
		logger.debug("ListaPeriodosDto");		
		List registos = this.listarPorQuery(DtoComunBuPeriodo.class, "buperiodo.obtenerListaPeriodos");
		
		if (registos == null)
			return new ResponseEntity<List<DtoComunBuPeriodo>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<DtoComunBuPeriodo>>(registos, HttpStatus.OK);
	}
}
