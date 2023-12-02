package net.royal.spring.core.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.royal.spring.core.dao.impl.DepartamentoDaoImpl;
import net.royal.spring.core.dominio.dto.DtoComunDepartamento;
import net.royal.spring.core.dominio.dto.DtoComunProvincia;
import net.royal.spring.core.dominio.dto.DtoComunUbigeo;
import net.royal.spring.core.dominio.dto.DtoComunZonapostal;
import net.royal.spring.core.dominio.filtro.FiltroComunDepartamento;
import net.royal.spring.core.dominio.filtro.FiltroComunUbigeo;
import net.royal.spring.core.servicio.impl.DepartamentoServicioImpl;
import net.royal.spring.core.servicio.validar.DepartamentoServicioValidar;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.MiscelaneosHeaderTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
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

//	@Transactional
//	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<DtoComunDepartamento> obtenerdto(@RequestBody DtoComunDepartamento pk) throws Exception {
//		logger.debug("obtenerdto");
//		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
//		parametros.add(new DominioParametroPersistencia("p_departamento", String.class, pk.getDepartamento()));
//		List datos = this.listarPorQuery(DtoComunDepartamento.class, "departamento.obtenerdto",parametros);
//		DtoComunDepartamento dto;
//		if (datos.size()==1) {
//			dto = (DtoComunDepartamento)datos.get(0);
//			dto.setTransaccionEstado(DominioTransaccion.OK);
//		}else {
//			dto = new DtoComunDepartamento();
//		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
//		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
//		}
//		return new ResponseEntity<DtoComunDepartamento>(dto,HttpStatus.OK);
//	}
//	
	@Transactional
	@PutMapping(value="/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunDepartamento>> listardtofiltros(@RequestBody DtoComunDepartamento filtro) throws Exception {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getDepartamento()))
			filtro.setDepartamento(null);
		if (UString.esNuloVacio(filtro.getDescripcioncorta()))
			filtro.setDescripcioncorta(null);
		else
			filtro.setDescripcioncorta(filtro.getDescripcioncorta().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_departamento", String.class, filtro.getDepartamento()));
		parametros.add(new DominioParametroPersistencia("p_descripcioncorta", String.class, filtro.getDescripcioncorta()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		List datos = this.listarPorQuery(DtoComunDepartamento.class, "departamento.listardtofiltros",parametros);
		return new ResponseEntity<List<DtoComunDepartamento>>(datos,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/listardtoporpais", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunDepartamento>> listardtoporpais(@RequestBody DtoComunDepartamento filtro) throws Exception {
		logger.debug("listardtoporpais");
		if (UString.esNuloVacio(filtro.getPais()))
			filtro.setPais(null);
		if (UString.esNuloVacio(filtro.getDescripcioncorta()))
			filtro.setDescripcioncorta(null);
		else
			filtro.setDescripcioncorta(filtro.getDescripcioncorta().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_pais", String.class, filtro.getPais()));
		parametros.add(new DominioParametroPersistencia("p_descripcioncorta", String.class, filtro.getDescripcioncorta()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		List datos = this.listarPorQuery(DtoComunDepartamento.class, "departamento.listardtoporpais",parametros);
		return new ResponseEntity<List<DtoComunDepartamento>>(datos,HttpStatus.OK);
	}
	
	/**
	 * ARMAS MIGRADO
	 * @param dto
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Departamentos nde la tabla Departamento | Parametros de entrada: id del pais | Parametros de salida: codigo, nombre", 
			value = "99-DEPARTAMENTO-C0001", tags = {"CORE", "DEPARTAMENTO"})
	@Transactional
	@PutMapping(value = "/listarporpais", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DtoTabla>> listarPorPais(    		
    		@ApiParam(value = "Codigos de la tabla", required = true)
    		@RequestBody DtoComunDepartamento dto )
	{
	    logger.debug("listar departamentos");	    
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_pais", String.class, dto.getPais()));       
        List datos = this.listarPorQuery(DtoTabla.class, "departamento.listarPorPais", parametros);
	    return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
//	/**
//	 * ARMAS MIGRADO
//	 * @param filtro
//	 * @return
//	 */
//	@ApiOperation(notes = "-Descripcion: Listado de Ubigeo por filtro | Parametros de entrada: id del pais | Parametros de salida: codigo, nombre", 
//			value = "99-DEPARTAMENTO-C0002", tags = {"CORE", "UBIGEO"})
//	@Transactional
//	@PostMapping(value = "/listarubigeoporfiltro", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<DominioPaginacion> listarUbigeoPorFiltro(@RequestBody FiltroComunUbigeo filtro)
//    {
//        logger.debug("EmpleadomastRest.listarUbigeoPorFiltro");        
//        Integer cantidadEncontrados = 0;
//        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
//        
//
//        parametros.add(new DominioParametroPersistencia("p_pais", String.class, "PER"));
//        parametros.add(new DominioParametroPersistencia("p_departamento", String.class, filtro.getDepartamento()));
//        parametros.add(new DominioParametroPersistencia("p_provincia", String.class, filtro.getProvincia()));
//        parametros.add(new DominioParametroPersistencia("p_distrito", String.class, filtro.getZonapostal()));
//
//        cantidadEncontrados = this.contar("departamento.contarubicaciongeografica", parametros);
//
//        List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros, "departamento.listarubicaciongeografica", DtoComunUbigeo.class);
//
//        filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
//        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);
//		
//        
//        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
//    }	
	
	/**
	 * ARMAS MIGRADO
	 * @param dto
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Obtener nombre departamento | Parametros de entrada: id Pais, id Departamento | Parametros de salida: codigo, nombre", 
			value = "99-DEPARTAMENTO-C0003", tags = {"CORE", "DISTRITO"})
	@Transactional
	@PutMapping(value = "/obtenernombredepartamento", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerNombreDepartamento(
			@ApiParam(value = "Codigos de la tabla", required = true)
    		@RequestBody FiltroComunUbigeo dto )
	{
		logger.debug("listar distritos");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_pais", String.class, dto.getPais()));
        parametros.add(new DominioParametroPersistencia("p_departamento", String.class, dto.getDepartamento()));
        List datos = this.listarPorQuery( DtoTabla.class, "departamento.obtenerNombreDepartamento", parametros);
        
        if(datos.size() > 0) {
        	return new ResponseEntity<DtoTabla>((DtoTabla) datos.get(0), HttpStatus.OK);	
        }
        
        return new ResponseEntity<DtoTabla>(new DtoTabla(), HttpStatus.OK);
	}
	
	
	
/****************/
	
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
		DtoComunDepartamento dto = departamentoDao.obtenerDtoPorUuid(pk);
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
		dto = servicio.coreActualizarPorUuid(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunDepartamento>(dto, HttpStatus.OK);
	}

	/*@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunDepartamento> anular(@RequestBody DtoComunDepartamento dto) throws Exception {
		logger.debug("DepartamentoRest.anular");
		dto = servicio.coreAnularPorUuid(this.getUsuarioActual(), dto);
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
		dto = servicio.coreEliminarPorUuid(this.getUsuarioActual(),dto);
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
        
        if (UString.estaVacio(filtro.getPais()))
        	filtro.setPais("PER");

        parametros.add(new DominioParametroPersistencia("p_pais", String.class, filtro.getPais()));
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
