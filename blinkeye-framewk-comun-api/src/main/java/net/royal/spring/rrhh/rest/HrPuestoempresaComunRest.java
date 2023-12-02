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
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrOrganigrama;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrOrganigramaPrevio;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrPosicionempresa;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrPuestoempresa;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunHrOrganigrama;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunHrPuestoempresa;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunOrganigramaPuestos;

@RestController
@RequestMapping("/spring/rrhh/hrpuestoempresacomun")
@CrossOrigin(origins = "*")
public class HrPuestoempresaComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(HrPuestoempresaComunRest.class);

	@Autowired
	private CorrelativosmastServicioValidar validar;

	@Autowired
	private CorrelativosmastDaoImpl dao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public HrPuestoempresaComunRest() {
		super("hrpuestoempresa");
	}
	
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar()
	{
		logger.debug("listar");
		List datos = this.listarPorQuery(DtoTabla.class, "hrpuestoempresa.listar");    
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);		
	}
	
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos()
	{
		logger.debug("hrpuestoempresa");
		List datos = this.listarPorQuery(DtoTabla.class, "hrpuestoempresa.listarActivos");    
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);		
	}
	
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerTabla(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_codigopuesto", BigDecimal.class, filtro.getId()));
        List datos = this.listarPorQuery(DtoTabla.class, "hrpuestoempresa.obtenertabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunHrPuestoempresa>> listardtofiltros(DtoComunHrPuestoempresa filtro) {
		logger.debug("listardtofiltros");
		if (UInteger.esCeroOrNulo(filtro.getCodigopuesto()))
			filtro.setCodigopuesto(null);
		if (UString.esNuloVacio(filtro.getDescripcion()))
			filtro.setDescripcion(null);
		else
			filtro.setDescripcion(filtro.getDescripcion().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigopuesto", Integer.class, filtro.getCodigopuesto()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		List datos = this.listarPorQuery(DtoComunHrPuestoempresa.class, "hrpuestoempresa.listardtofiltros", parametros);
		return new ResponseEntity<List<DtoComunHrPuestoempresa>>(datos, HttpStatus.OK);
	}
	
	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: obtener dto de la tabla HR_PUESTOEMPRESA | parametros de entrada:DtoComunHrPosicionempresa(companyowner,codigoposicion) | Parametros de salida DtoComunHrPosicionempresa()",  
			value = "HR-PUESTO-COBTE", tags = {"RRHH","PUESTO EMPRESA"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunHrPuestoempresa> obtenerdto(@RequestBody DtoComunHrPuestoempresa pk) throws Exception {
		logger.debug("HrPosicionempresaRest.obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigopuesto", BigDecimal.class, pk.getCodigopuesto()));
		List query = this.listarPorQuery(DtoComunHrPuestoempresa.class, "hrpuestoempresa.obtenerdto", parametros);
		DtoComunHrPuestoempresa dto = null;
		if (query.size()==1)
			dto = (DtoComunHrPuestoempresa)query.get(0);
		return new ResponseEntity<DtoComunHrPuestoempresa>(dto,HttpStatus.OK);
	}
	
	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Cargos de la tabla HR_PUESTOEMPRESA | Sin parametros de entrada | Parametros de salida: codigo, nombre",  
			value = "HR-PUESTO-CLISTA", tags = {"RRHH","PUESTO EMPRESA"})
	@Transactional
	@GetMapping(value = "/listardto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunHrPuestoempresa>> listardto() {
		logger.debug("listar Activos CargoMast");
		List datos = this.listarPorQuery(DtoComunHrPuestoempresa.class, "hrpuestoempresa.listardto");
		return new ResponseEntity<List<DtoComunHrPuestoempresa>>(datos, HttpStatus.OK);
	}
	
	/**
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Cargos activos de la tabla HR_PUESTOEMPRESA | Sin parametros de entrada | Parametros de salida: codigo, nombre",  
			value = "HR-PUESTO-CLIACT", tags = {"RRHH","PUESTO EMPRESA"})
	@Transactional
	@GetMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunHrPuestoempresa>> listardtoactivos() {
		logger.debug("listar Activos CargoMast");
		List datos = this.listarPorQuery(DtoComunHrPuestoempresa.class, "hrpuestoempresa.listardtoactivos");
		return new ResponseEntity<List<DtoComunHrPuestoempresa>>(datos, HttpStatus.OK);
	}
	
	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: dto de la tabla HR_PUESTOEMPRESA | parametros de entrada:DtoComunHrPosicionempresa(companyowner,codigoposicion) | Parametros de salida DtoComunHrPosicionempresa()",  
			value = "HR-PUESTO-CPAGIN", tags = {"RRHH","PUESTO EMPRESA"})
	@Transactional(readOnly = true)
	@PutMapping(value = "/listarpuestospaginacion",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarpuestospaginacion(@RequestBody FiltroComunHrPuestoempresa filtro)
    {
        Integer contador = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		
		if (UInteger.esCeroOrNulo(filtro.getCodigopuesto())) 
			filtro.setCodigopuesto(null);
		if (UString.estaVacio(filtro.getDescripcion())) 
			filtro.setDescripcion(null);
		if (UString.estaVacio(filtro.getFlginactivo()))
			filtro.setFlginactivo("N");
		if (UString.estaVacio(filtro.getPuestolinea()))
			filtro.setPuestolinea(null);
		
		parametros.add(new DominioParametroPersistencia("p_codigo", Integer.class, filtro.getCodigopuesto()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
		parametros.add(new DominioParametroPersistencia("p_flginactivo", String.class, filtro.getFlginactivo()));
		parametros.add(new DominioParametroPersistencia("p_puestolinea", String.class, filtro.getPuestolinea()));
		parametros.add(new DominioParametroPersistencia("p_puestodef", Integer.class, filtro.getPuestodef()));
		
		contador = this.contar("hrpuestoempresa.solicitudContarHrPuestoEmpresa", parametros);

		List lstDatos = this.listarConPaginacion(filtro.getPaginacion(), parametros, "hrpuestoempresa.solicitudListarHrPuestoEmpresa",
				DtoComunHrPuestoempresa.class);

		filtro.getPaginacion().setPaginacionListaResultado(lstDatos);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
		        
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
	@Transactional
	@GetMapping(value = "/obtenercomboPuesto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarcomboPuesto() {
		logger.debug("hrpuestoempresa.obtenercomboPuesto");
		List<DominioParametroPersistencia> parametrosClave = new ArrayList<DominioParametroPersistencia>();
		List listado = this.listarPorQuery(DtoTabla.class, "hrpuestoempresa.obtenercomboPuesto",parametrosClave);
		
		return new ResponseEntity<List<DtoTabla>>(listado, HttpStatus.OK);
	}
	
	/***
	 * LEONARDO CPP SELECTOR PUESTO, HR_PUESTOEMPRESA
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@PostMapping(value = "/listarPuestosOrganigrama", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> FiltroComunOrganigramaPuestos(
			@RequestBody FiltroComunOrganigramaPuestos filtro) {

		Integer contador = 0;
		List lstDatos = new ArrayList();
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UInteger.esCeroOrNulo(filtro.getCodigo()))
			filtro.setCodigo(null);
		if (UString.estaVacio(filtro.getNombre()))
			filtro.setNombre(null);

		parametros.add(new DominioParametroPersistencia("p_codigopuesto", Integer.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, filtro.getCompaniaSocio()));
		parametros.add(new DominioParametroPersistencia("p_unidadnegocio", String.class, filtro.getUnidadNegocio()));
		parametros.add(new DominioParametroPersistencia("p_anio", Integer.class, filtro.getAnio()));
		parametros.add(new DominioParametroPersistencia("p_secuencia", Integer.class, filtro.getSecuencia()));
		parametros.add(new DominioParametroPersistencia("p_evento", String.class, filtro.getEvento()));

		if ("Update".equals(filtro.getEvento())) {
			contador = this.contar("hrpuestoempresa.contarpuestoprevioporfiltro", parametros);

			lstDatos = this.listarConPaginacion(filtro.getPaginacion(), parametros,
					"hrpuestoempresa.listarpuestoprevioporfiltro", DtoComunHrPuestoempresa.class);
		} else {
			contador = this.contar("hrpuestoempresa.contarpuestoporfiltro", parametros);

			lstDatos = this.listarConPaginacion(filtro.getPaginacion(), parametros,
					"hrpuestoempresa.listarpuestoporfiltro", DtoComunHrPuestoempresa.class);
		}

		filtro.getPaginacion().setPaginacionListaResultado(lstDatos);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);

		// LOGICA
		Integer total = 0;
		Integer existepuesto = 0;
		Integer existeunidad = 0;
		List<DtoComunHrOrganigramaPrevio> lstorganigramaprevio = new ArrayList<DtoComunHrOrganigramaPrevio>(); // DtoHrOrganigramaPrevio
		Integer unidadorganigrama = 0;
		String unidadorganigramanombre = "";
		String orden2 = "";
		String centrocosto = null;
		String centrocostonombre = null;

		for (DtoComunHrPuestoempresa obj : (List<DtoComunHrPuestoempresa>) filtro.getPaginacion()
				.getPaginacionListaResultado()) {
			Integer codpuesto = obj.getCodigopuesto();
			parametros.add(new DominioParametroPersistencia("p_puestotemporal", Integer.class, codpuesto));

			if ("Browse".equals(filtro.getEvento()) || "UpdateBrowse".equals(filtro.getEvento())) {
				total = this.contar("hrpuestoempresa.contarHr_organigramaPorFiltros", parametros);

			} else if ("Update".equals(filtro.getEvento())) {
				total = this.contar("hrpuestoempresa.contarHr_organigrama_previoPorFiltros", parametros);
			}

			if (total == 1) {
				List lsttemp = this.listarPorQuery(DtoComunHrOrganigramaPrevio.class,
						"hrpuestoempresa.listarorganigramaprevio", parametros);
				lstorganigramaprevio = (List<DtoComunHrOrganigramaPrevio>) lsttemp;
				existepuesto = lstorganigramaprevio.size();

				DtoComunHrOrganigramaPrevio filtradoXX = lstorganigramaprevio.stream()
						.filter(org -> org.getCodigo().equals(obj.getCodigopuesto()) && org.getTipo().equals("2"))
						.findAny().orElse(null);
				if (existepuesto > 0) {

					String orden = filtradoXX.getOrden().substring(0, (filtradoXX.getOrden().length()) - 5);

					DtoComunHrOrganigramaPrevio filtradoYY = lstorganigramaprevio.stream()
							.filter(org -> orden.equals(org.getOrden())).findAny().orElse(null);

					if (filtradoYY != null) {
						unidadorganigrama = filtradoYY != null ? filtradoYY.getCodigo() : null;
						unidadorganigramanombre = filtradoYY != null ? filtradoYY.getCodigonombre() : "";
						centrocosto = filtradoYY != null ? filtradoYY.getCentrocosto() : "";
						centrocostonombre= filtradoYY != null ? filtradoYY.getCentrocostonombre() : "";
						orden2 = orden;
					}
				}
				DtoTabla unidad = new DtoTabla();
				unidad.setId(unidadorganigrama);
				unidad.setDescripcion(unidadorganigramanombre);
				unidad.setCodigo(orden2);
				unidad.setEstadoId(centrocosto);
				unidad.setNombre(centrocostonombre);
				
				//c.CentroCostos as "estadoId",
				//d.LocalName as "nombre"
				
				obj.getUnidades().add(unidad);
				obj.setUnidadorganigramanombre(unidadorganigramanombre);
				obj.setUnidadorganigrama(unidadorganigrama);
			} else if (total > 1) {
				unidadorganigrama = null;
				unidadorganigramanombre = "(DEBE DEFINIR LA UNIDAD DE ORGANIGRAMA CUANDO SELECCIONE)";
				List<DominioParametroPersistencia> prms = new ArrayList<DominioParametroPersistencia>();
				prms.add(new DominioParametroPersistencia("p_companyowner", String.class, filtro.getCompaniaSocio()));
				prms.add(new DominioParametroPersistencia("p_unidadnegocio", String.class, filtro.getUnidadNegocio()));
				prms.add(new DominioParametroPersistencia("p_anio", Integer.class, filtro.getAnio()));
				prms.add(new DominioParametroPersistencia("p_secuencia", Integer.class, filtro.getSecuencia()));
				prms.add(new DominioParametroPersistencia("p_puestotemporal", Integer.class, codpuesto));
				List t = this.listarPorQuery(DtoTabla.class, "hrpuestoempresa.listarUnidades", prms);
				obj.setUnidades(t);
				obj.setUnidadorganigramanombre(unidadorganigramanombre);
				obj.setUnidadorganigrama(unidadorganigrama);
			}
			
		}

		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
	}
	
}
