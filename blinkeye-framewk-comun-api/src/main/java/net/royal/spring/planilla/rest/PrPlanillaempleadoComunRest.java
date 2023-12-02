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
import net.royal.spring.planilla.dominio.dto.DtoComunPrPlanillaEmpleado;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/planilla/prplanillaempleadocomun")
@CrossOrigin(origins = "*")
public class PrPlanillaempleadoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(PrPlanillaempleadoComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public PrPlanillaempleadoComunRest() {
		super("prplanillaempleado");
	}
	
	/**
	 * ARMAS MIGRADO
	 * @param dto
	 * @return
	 */
	@ApiOperation(notes = "Listado periodos en base a Tipo Proceso, compania,tipo planialla,sucursal "
			+ "<br><b>DtoComunPrPlanillaEmpleado : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "PR-PEM-C0001", tags = {"PLANILLA", "PLANILLA EMPLEADO"})
	@Transactional
	@PutMapping(value = "/listarperiodoportipoprocesoyfiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarperiodoportipoprocesoyfiltros(@RequestBody DtoComunPrPlanillaEmpleado filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.esNuloVacio(filtro.getCompaniasocio()))
			filtro.setCompaniasocio(null);
		if (UString.esNuloVacio(filtro.getTipoplanilla()))
			filtro.setTipoplanilla(null);
		if (UString.esNuloVacio(filtro.getSucursal()))
			filtro.setSucursal(null);
		parametros.add(new DominioParametroPersistencia("p_tipoprocesoid", String.class, filtro.getTipoproceso()));
		parametros.add(new DominioParametroPersistencia("p_companiasocioid", String.class, filtro.getCompaniasocio()));
		parametros.add(new DominioParametroPersistencia("p_tipoplanillaid", String.class, filtro.getTipoplanilla()));
		parametros.add(new DominioParametroPersistencia("p_sucursalid", String.class, filtro.getSucursal()));		
		List datos = listarPorQuery(DtoTabla.class, "prplanillaempleado.listarperiodoportipoprocesoyfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos,HttpStatus.OK);
	}
}
