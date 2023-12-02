package net.royal.spring.rrhh.rest;

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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrPosicionempresa;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrTipocontrato;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunHrPosicionEmpresa;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunHrTipocontrato;
import net.royal.spring.tesoreria.dominio.dto.DtoComunApGastoclasificacion;

@RestController
@RequestMapping("/spring/rrhh/hrposicionempresacomun")
public class HrPosicionempresaComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(HrPosicionempresaComunRest.class);

	@Autowired
	private CorrelativosmastServicioValidar validar;

	@Autowired
	private CorrelativosmastDaoImpl dao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public HrPosicionempresaComunRest() {
		super("hrposicionempresa");
	}
	
	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Cargos de la tabla hr_posicionempresa | Sin parametros de entrada | Parametros de salida: codigo, nombre",  
			value = "HR-POSEMP-CLISTA", tags = {"RRHH","POSICION EMPRESA"})
	@Transactional
	@GetMapping(value = "/listardto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunHrPosicionempresa>> listarDto() {
		logger.debug("listar Activos CargoMast");
		List datos = this.listarPorQuery(DtoComunHrPosicionempresa.class, "hrposicionempresa.listardto");
		return new ResponseEntity<List<DtoComunHrPosicionempresa>>(datos, HttpStatus.OK);
	}
	
	/**
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Cargos activos de la tabla hr_posicionempresa | Sin parametros de entrada | Parametros de salida: codigo, nombre",  
			value = "HR-POSEMP-CLIACT", tags = {"RRHH","POSICION EMPRESA"})
	@Transactional
	@GetMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunHrPosicionempresa>> listardtoactivos() {
		logger.debug("listar Activos CargoMast");
		List datos = this.listarPorQuery(DtoComunHrPosicionempresa.class, "hrposicionempresa.listardtoactivos");
		return new ResponseEntity<List<DtoComunHrPosicionempresa>>(datos, HttpStatus.OK);
	}

	/***
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: obtener dto de la tabla hr_posicionempresa | parametros de entrada:DtoComunHrPosicionempresa(companyowner,codigoposicion) | Parametros de salida DtoComunHrPosicionempresa()",  
			value = "HR-POSEMP-COBTE", tags = {"RRHH","POSICION EMPRESA"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunHrPosicionempresa> obtenerdto(@RequestBody DtoComunHrPosicionempresa pk) throws Exception {
		logger.debug("HrPosicionempresaRest.obtenerDtoPorId");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, pk.getCompanyowner()));
		parametros.add(new DominioParametroPersistencia("p_codigoposicion", Integer.class, pk.getCodigoposicion()));
		List query = this.listarPorQuery(DtoComunHrPosicionempresa.class, "hrposicionempresa.obtenerdto", parametros);
		DtoComunHrPosicionempresa dto = null;
		if (query.size()==1)
			dto = (DtoComunHrPosicionempresa)query.get(0);
		return new ResponseEntity<DtoComunHrPosicionempresa>(dto,HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunHrPosicionempresa>> listardtofiltros(@RequestBody DtoComunHrPosicionempresa filtro) throws Exception {
		logger.debug("HrPosicionempresaRest.obtenerDtoPorId");
		if (UString.esNuloVacio(filtro.getCompanyowner()))
			filtro.setCompanyowner(null);
		if (UInteger.esCeroOrNulo(filtro.getCodigoposicion()))
			filtro.setCodigoposicion(null);
		if (UInteger.esCeroOrNulo(filtro.getCodigotipo()))
			filtro.setCodigotipo(null);
		if (UString.esNuloVacio(filtro.getDescripcion()))
			filtro.setDescripcion(null);
		else
			filtro.setDescripcion(filtro.getDescripcion().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, filtro.getCompanyowner()));
		parametros.add(new DominioParametroPersistencia("p_codigoposicion", Integer.class, filtro.getCodigoposicion()));
		parametros.add(new DominioParametroPersistencia("p_codigotipo", Integer.class, filtro.getCodigotipo()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		List query = this.listarPorQuery(DtoComunHrPosicionempresa.class, "hrposicionempresa.listardtofiltros", parametros);
		return new ResponseEntity<List<DtoComunHrPosicionempresa>>(query,HttpStatus.OK);
	}
	
	/**
	 * MALCA MIGRADO
	 * HR-POSICIONEMPRESA COMUN SELECTOR
	 */
	@Transactional
	@PostMapping(value = "/listarPaginado", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunHrPosicionEmpresa filtro)
    {
        logger.debug("HrPosicionempresaComunRest.listarPaginado");        
        Integer cantidadEncontrados = 0;
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

        if(UInteger.esCeroOrNulo(filtro.getCodigoposicion()))
        	filtro.setCodigoposicion(null);
        if(UString.estaVacio(filtro.getDescripcion()))
        	filtro.setDescripcion(null);

        parametros.add(new DominioParametroPersistencia("p_unidadorg", Integer.class, filtro.getCodigoposicion()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
        parametros.add(new DominioParametroPersistencia("p_usuario", String.class, getUsuarioActual().getUsuario()));

        cantidadEncontrados = this.contar("hrposicionempresa.listarPaginadoContar", parametros);

        List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros, "hrposicionempresa.listarPaginadoSentencia", 
        		DtoComunHrPosicionempresa.class);

        filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);
		
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }

}
