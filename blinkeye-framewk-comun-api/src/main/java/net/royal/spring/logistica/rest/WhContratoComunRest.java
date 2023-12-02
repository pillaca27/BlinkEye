package net.royal.spring.logistica.rest;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dao.impl.ParametrosmastDaoImpl;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.util.UFechaHora;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.logistica.dominio.dto.DtoComunWhContrato;
import net.royal.spring.logistica.dominio.dto.DtoComunWhContratoProveedor;
import net.royal.spring.logistica.dominio.dto.DtoComunWhOrdencompra;
import net.royal.spring.logistica.dominio.dto.DtoComunContratoDetalle;
import net.royal.spring.logistica.dominio.filtro.FiltroComunWhContrato;
import net.royal.spring.logistica.dominio.filtro.FiltroComunWhContratoProveedor;

@RestController
@RequestMapping("/spring/logistica/whcontrato")
@CrossOrigin(origins = "*")
public class WhContratoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(WhContratoComunRest.class);
	
	@Autowired
	private ParametrosmastDaoImpl parametrosmastDaoImpl;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WhContratoComunRest() {
		super("whcontrato");
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarpaginado(@RequestBody FiltroComunWhContrato filtro) {

		Integer contador = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, filtro.getCompania()));

		contador = this.contar("whcontrato.contar", parametros);

		List lstDatos = this.listarConPaginacion(filtro.getPaginacion(), parametros, "whcontrato.listarpaginado",
				DtoComunWhContrato.class);

		filtro.getPaginacion().setPaginacionListaResultado(lstDatos);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);

		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/listarpaginadoContratoProveedor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarpaginadoContratoProveedor(
			@RequestBody FiltroComunWhContratoProveedor filtro) {
		
		if(UString.esNuloVacio(filtro.getNumerocontrato())) {
			filtro.setNumerocontrato(null);
		}
		if(UString.esNuloVacio(filtro.getCompania())) {
			filtro.setCompania(null);
		}
		
		Integer contador = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proveedor", Integer.class, filtro.getIdEmpleado()));
		parametros.add(new DominioParametroPersistencia("p_numerocontrato", String.class, filtro.getNumerocontrato()));
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompania()));
		parametros.add(new DominioParametroPersistencia("p_usuariocomprador", String.class, filtro.getCodigocosto()));
		
		List lstDatos = this.listarConPaginacion(filtro.getPaginacion(), parametros,
				"whcontrato.listarpaginadoContratoProveedor", DtoComunWhContratoProveedor.class);
		
		//contador = this.contar("whcontrato.contarContratoProveedor", parametros);
		if(lstDatos !=null) {
			contador =lstDatos.size();
		}
		
		filtro.getPaginacion().setPaginacionListaResultado(lstDatos);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);

		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
	}
	
	@Transactional(readOnly = true)
	@PutMapping(value = "/listarpaginadoEvaluacionContratoProveedor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarpaginadoEvaluacionContratoProveedor(
			@RequestBody FiltroComunWhContratoProveedor filtro) {
		
		if(UString.esNuloVacio(filtro.getNumerocontrato())) {
			filtro.setNumerocontrato(null);
		}
		
		Integer contador = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proveedor", Integer.class, filtro.getIdEmpleado()));
		parametros.add(new DominioParametroPersistencia("p_numerocontrato", String.class, filtro.getNumerocontrato()));
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompania()));

		contador = this.contar("whcontrato.contarEvaluacionContratoProveedor", parametros);

		List lstDatos2 = this.listarConPaginacion(filtro.getPaginacion(), parametros,"whcontrato.listarpaginadoEvaluacionContratoProveedor", DtoComunWhContratoProveedor.class);

		filtro.getPaginacion().setPaginacionListaResultado(lstDatos2);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);

		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
	}
	
	@Transactional(readOnly = true)
	@PutMapping(value = "/obtenerlistadetalleevalcontratoprovee", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> obtenerlistadetalleevalcontratoprovee(
			@RequestBody FiltroComunWhContratoProveedor filtro) {
		
		if(UString.esNuloVacio(filtro.getNumerocontrato())) {
			filtro.setNumerocontrato(null);
		}
		
		Integer contador = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_numerocontrato", String.class, filtro.getNumerocontrato()));
		parametros.add(new DominioParametroPersistencia("p_evaluacionid", Integer.class, filtro.getEvaluacionid()));

		contador = this.contar("whcontrato.contarlstEvaluacionContratoProve", parametros);

		List lstDatos = this.listarConPaginacion(filtro.getPaginacion(), parametros,
				"whcontrato.listarpaginadolstEvaluacionContratoProve", DtoComunWhContratoProveedor.class);

		filtro.getPaginacion().setPaginacionListaResultado(lstDatos);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);

		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
	}

	@ApiOperation(notes = "Obtener dto contrato proveedor. Entrada: DtoComunWhContratoProveedor: idEmpleado, NumeroContrato, Compania. Retorno: v",					
			nickname="WH_CONTRATOPROVEEDOR-COBT", value = "Obtener dto contrato proveedor", tags = {"WH_CONTRATOPROVEEDOR", "OBTENER"})
	@Transactional(readOnly = true)
	@PutMapping(value = "/obtenerDtoContratoProveedor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhContratoProveedor> obtenerDtoContratoProveedor(
			@RequestBody FiltroComunWhContratoProveedor filtro) {
		Integer contador = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proveedor", Integer.class, filtro.getIdEmpleado()));
		parametros.add(new DominioParametroPersistencia("p_numerocontrato", String.class, filtro.getNumerocontrato()));
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompania()));

		contador = this.contar("whcontrato.contarobtenerDtoContratoProveedor", parametros);

		List lstDatos = this.listarPorQuery(DtoComunWhContratoProveedor.class,
				"whcontrato.obtenerDtoContratoProveedor", parametros);

		DtoComunWhContratoProveedor dto = new DtoComunWhContratoProveedor();

		if (lstDatos.size() > 0) {
			dto = (DtoComunWhContratoProveedor) lstDatos.get(0);
		}

		return new ResponseEntity<DtoComunWhContratoProveedor>(dto, HttpStatus.OK);
	}
	
	
	
	
	@Transactional(readOnly = true)
	@PutMapping(value = "/listarpaginadoOrdenCompraServiProveedor", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarpaginadoOrdenCompraServiProveedor(
			@RequestBody FiltroComunWhContratoProveedor filtro) {
		
		if(UString.esNuloVacio(filtro.getNumerocontrato())) {
			filtro.setNumerocontrato(null);
		}
		
		Integer contador = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proveedor", Integer.class, filtro.getIdEmpleado()));
		parametros.add(new DominioParametroPersistencia("p_numeroorden", String.class, filtro.getNumeroorden()));
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompania()));

		contador = this.contar("whcontrato.contarlistarpaginadoOrdenCompraServiProveedor", parametros);

		List lstDatos = this.listarConPaginacion(filtro.getPaginacion(), parametros,
				"whcontrato.listarpaginadolistarpaginadoOrdenCompraServiProveedor", DtoComunWhOrdencompra.class);
		logger.debug(lstDatos.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
		filtro.getPaginacion().setPaginacionListaResultado(lstDatos);
		
		

		return filtro.getPaginacion();
	}
	

	@Transactional(readOnly = true)
	@PutMapping(value = "/wmawhcontratoselect", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> wmawhcontratoselect(@RequestBody FiltroComunWhContratoProveedor filtro) {

		Integer contador = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompania()));
		parametros.add(new DominioParametroPersistencia("p_proveedor", Integer.class, filtro.getIdEmpleado()));
		parametros.add(new DominioParametroPersistencia("p_tipo01", String.class, filtro.getW_tipo01()));
		parametros.add(new DominioParametroPersistencia("p_tipo02", String.class, filtro.getW_tipo02()));
		parametros.add(new DominioParametroPersistencia("p_responsable", Integer.class, getUsuarioActual().getPersonaId()));
		parametros.add(new DominioParametroPersistencia("p_hasta", Date.class, UFechaHora.removerHora(filtro.getHasta())));

		contador = this.contar("whcontrato.wmawhcontratoselectContar", parametros);

		List lstDatos = this.listarConPaginacion(filtro.getPaginacion(), parametros,
				"whcontrato.wmawhcontratoselectListar", DtoComunWhContratoProveedor.class);

		filtro.getPaginacion().setPaginacionListaResultado(lstDatos);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);

		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
	}
	
	@Transactional(readOnly = true)
	@PutMapping(value = "/wmawhcontratoselectAdenda", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> wmawhcontratoselectAdenda(@RequestBody FiltroComunWhContratoProveedor filtro) throws Exception {
		
		
		if(!UValidador.esNulo(filtro.getFechadesde()) && !UValidador.esNulo(filtro.getFechahasta())) {
			if(filtro.getFechadesde().compareTo(filtro.getFechahasta())==1) {
				throw new UException("La Fecha desde debe ser menor que la fecha hasta");
			}
		}
		

		Integer contador = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompania()));
		parametros.add(new DominioParametroPersistencia("p_proveedor", Integer.class, filtro.getIdEmpleado()));
		parametros.add(new DominioParametroPersistencia("p_tipo01", String.class, filtro.getW_tipo01()));
		parametros.add(new DominioParametroPersistencia("p_tipo02", String.class, filtro.getW_tipo02()));
		parametros.add(new DominioParametroPersistencia("p_responsable", Integer.class, getUsuarioActual().getPersonaId()));
		
		
		parametros.add(new DominioParametroPersistencia("p_fechadesde", Date.class, UValidador.esNulo(filtro.getFechadesde())?null:UFechaHora.removerHora(filtro.getFechadesde())));
		parametros.add(new DominioParametroPersistencia("p_fechahasta", Date.class, UValidador.esNulo(filtro.getFechahasta())?null:UFechaHora.removerHora(filtro.getFechahasta())));
		parametros.add(new DominioParametroPersistencia("p_numeroContrato", String.class, filtro.getNumerocontrato()));
		
		Integer diasVencidos=parametrosmastDaoImpl.parametroObtenerNumeroEntero("WH", "DIASCONT", "999999");
		
		parametros.add(new DominioParametroPersistencia("p_diascontratovencido", Integer.class, diasVencidos));
		
		contador = this.contar("whcontrato.wmawhcontratoselectContarAdenda", parametros);

		List lstDatos = this.listarConPaginacion(filtro.getPaginacion(), parametros,
				"whcontrato.wmawhcontratoselectListarAdenda", DtoComunWhContratoProveedor.class);

		filtro.getPaginacion().setPaginacionListaResultado(lstDatos);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);

		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/wmawhcontratodetailselect", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunContratoDetalle>> wmawhcontratodetailselect(
			@RequestBody DtoComunWhContratoProveedor filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompania()));
		parametros.add(new DominioParametroPersistencia("p_contrato", String.class, filtro.getNumerocontrato()));
		List lstDatos = this.listarPorQuery(DtoComunContratoDetalle.class, "whcontrato.wmawhcontratodetailselect",
				parametros);
		return new ResponseEntity<List<DtoComunContratoDetalle>>(lstDatos, HttpStatus.OK);
	}

}
