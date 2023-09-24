package net.royal.spring.core.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dao.impl.DepartamentoDaoImpl;
import net.royal.spring.core.dominio.dto.DtoComunUbigeo;
import net.royal.spring.core.dominio.dto.DtoComunDepartamento;
import net.royal.spring.core.dominio.filtro.FiltroComunDepartamento;
import net.royal.spring.core.dominio.filtro.FiltroComunUbigeo;
import net.royal.spring.core.servicio.impl.DepartamentoServicioImpl;
import net.royal.spring.core.servicio.validar.DepartamentoServicioValidar;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/departamentocomun")
@CrossOrigin(origins = "*")
public class DepartamentoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(DepartamentoComunRest.class);

	@Autowired
	private DepartamentoServicioImpl servicio;

	@Autowired
	private DepartamentoServicioValidar validar;

	@Autowired
	private DepartamentoDaoImpl departamentoDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public DepartamentoComunRest() {
		super("departamento");
	}

	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunDepartamento> validar(@Validated @PathVariable String accion, @RequestBody DtoComunDepartamento dto) throws Exception {
		logger.debug("DepartamentoRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunDepartamento>(dto, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * OBTIENE OBJETO POR QUERY
	 * */
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunDepartamento> obtenerDto(@RequestBody DtoComunDepartamento pk) throws Exception {
		logger.debug("DepartamentoRest.obtenerDto");
		DtoComunDepartamento dto = departamentoDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunDepartamento>(dto,HttpStatus.OK);
	}

	/*
	 * LEONARDO
	 * REGISTRA DEPARTAMENTO, PROVINCIA Y DISTRITO
	 * */
	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunDepartamento> registrar(@RequestBody DtoComunDepartamento dto) throws Exception {
		logger.debug("DepartamentoRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunDepartamento>(dto, HttpStatus.CREATED);
	}

	/*
	 * LEONARDO
	 * ACTUALIZA DEPARTAMENTO, PROVINCIA Y DISTRITO
	 * */
	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunDepartamento> actualizar(@RequestBody DtoComunDepartamento dto) throws Exception {
		logger.debug("DepartamentoRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunDepartamento>(dto, HttpStatus.OK);
	}

	/*@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunDepartamento> anular(@RequestBody DtoComunDepartamento dto) throws Exception {
		logger.debug("DepartamentoRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunDepartamento>(dto, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * ELIMINA DEPARTAMENTO, PROVINCIA Y DISTRITO
	 * */
	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunDepartamento> eliminar(@RequestBody DtoComunDepartamento dto) throws Exception {
		logger.debug("DepartamentoRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunDepartamento>(dto,HttpStatus.OK);
	}

	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("DepartamentoRest.listar");
		// TODO DepartamentoRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "departamento.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("DepartamentoRest.listaractivos");
		// TODO DepartamentoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "departamento.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunDepartamento>> listarDtoFiltros(@RequestBody DtoComunDepartamento filtro) throws Exception {
		logger.debug("DepartamentoRest.listardtofiltros");
        List datos = departamentoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunDepartamento>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunDepartamento>> listarDtoActivos(@RequestBody DtoComunDepartamento filtro) throws Exception {
		logger.debug("DepartamentoRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = departamentoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunDepartamento>>(datos, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * LISTADO CON PAGINADO GENERAL
	 * */
	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunDepartamento filtro) throws Exception {
		logger.debug("DepartamentoRest.listarPaginado");
		DominioPaginacion paginacion = departamentoDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	/*
	 * LEONARDO
	 * EXPORTA LISTADO GENERAL
	 * */
	@Transactional
	@PostMapping(value = "/exportarDepartamentosGeograficos", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarDepartamentosGeograficos(HttpServletResponse response, @RequestBody FiltroComunDepartamento filtro) throws Exception
	 {
		
		DominioPaginacion paginacion = departamentoDao.exportarDepartamentosGeograficos(filtro);
		String[] arrCabecera = new String[] {"Departamento","Provincia","Descripci\u00f3n","Estado"};
		String[] arrColumnas = new String[] {"descripcioncorta","pais","descripcionlarga","estadodescripcion"}; 
		

		DtoWhExportar dtoExportar=new DtoWhExportar();
		dtoExportar.setTitulo("Listado de Departamentos Geogr\u00E1ficos");
		dtoExportar.setTipoExpotar(filtro.getTipoexportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setPaginacion(paginacion);
			
		servicio.exportarInformacionWh(response,dtoExportar);
	}
	
	/*
	 *LEONARDO
	 *DEPARTAMENTO COMUN COPIADO
	 * SELECTOR DPTO-PROV-ZONA
	 * 
	 * */
	/**
	 * ARMAS MIGRADO
	 * @param filtro
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Ubigeo por filtro | Parametros de entrada: id del pais | Parametros de salida: codigo, nombre", 
			value = "99-DEPARTAMENTO-C0002", tags = {"CORE", "UBIGEO"})
	@Transactional
	@PostMapping(value = "/listarubigeoporfiltro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DominioPaginacion> listarUbigeoPorFiltro(@RequestBody FiltroComunUbigeo filtro)
    {
        logger.debug("EmpleadomastRest.listarUbigeoPorFiltro");        
        Integer cantidadEncontrados = 0;
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        

        parametros.add(new DominioParametroPersistencia("p_pais", String.class, "PER"));
        parametros.add(new DominioParametroPersistencia("p_departamento", String.class, filtro.getDepartamento()));
        parametros.add(new DominioParametroPersistencia("p_provincia", String.class, filtro.getProvincia()));
        parametros.add(new DominioParametroPersistencia("p_distrito", String.class, filtro.getZonapostal()));

        cantidadEncontrados = this.contar("departamento.contarubicaciongeografica", parametros);

        List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros, "departamento.listarubicaciongeografica", DtoComunUbigeo.class);

        filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);
		
        
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
}
