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
import net.royal.spring.core.dao.impl.BancoDaoImpl;
import net.royal.spring.core.dominio.dto.DtoComunBanco;
import net.royal.spring.core.dominio.filtro.FiltroComunBanco;
import net.royal.spring.core.servicio.impl.BancoServicioImpl;
import net.royal.spring.core.servicio.validar.BancoServicioValidar;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/bancocomun")
@CrossOrigin(origins = "*")
public class BancoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(BancoComunRest.class);
	
	@Autowired
	private BancoServicioImpl servicio;

	@Autowired
	private BancoServicioValidar validar;

	@Autowired
	private BancoDaoImpl bancoDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BancoComunRest() {
		super("banco");
	}
	
	/***
	 * QQUECHOD VALIDADO
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Listado de Paises activos de la tabla Pais | Sin parametros de entrada | Parametros de salida: codigo, nombre, descripcion (Nacionalidad)", 
			value = "99-BANCO-CLISTA", tags= {"CORE", "BANCO"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar paises");
		List datos = this.listarPorQuery(DtoTabla.class, "banco.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/***
	 * QQUECHOD VALIDADO
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Listado de Paises activos de la tabla Pais | Sin parametros de entrada | Parametros de salida: codigo, nombre, descripcion (Nacionalidad)", 
			value = "99-BANCO-CLIACT", tags= {"CORE", "BANCO"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listar paises");
		List datos = this.listarPorQuery(DtoTabla.class, "banco.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/
	
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 */
	/*@ApiOperation(notes = "-Descripcion: Obtener Por id Hr_Tipotrabajador | Parametros de entrada: FiltroComunHrCursodescripcion | Parametros de salida: DtoTabla(id, nombre) ", 
			value = "HR-BANCO-COBTDTO", tags = {"RRHH", "BANCO"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerDtoTablaPorId(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_banco", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "banco.obtenerDtoTablaPorId", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}*/
	
	/*@Transactional
	@PutMapping(value = "/listarfiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarFiltros(@RequestBody DtoTabla filtro) {
		logger.debug("listar Filtros");
		if (UString.esNuloVacio(filtro.getCodigo()))
			filtro.setCodigo(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);
		else
			filtro.setNombre(filtro.getNombre().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstadoId()))
			filtro.setEstadoId(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();		
		parametros.add(new DominioParametroPersistencia("p_banco", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcioncorta", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "banco.listarfiltros",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/
	
	
	/*MANTENIMIENTO LEONARDO*/
	
	
	@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunBanco> validar(@Validated @PathVariable String accion, @RequestBody DtoComunBanco dto) throws Exception {
		logger.debug("BancoRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunBanco>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunBanco> obtenerDto(@RequestBody DtoComunBanco pk) throws Exception {
		logger.debug("BancoRest.obtenerDto");
		DtoComunBanco dto = bancoDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunBanco>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunBanco> registrar(@RequestBody DtoComunBanco dto) throws Exception {
		logger.debug("BancoRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunBanco>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunBanco> actualizar(@RequestBody DtoComunBanco dto) throws Exception {
		logger.debug("BancoRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunBanco>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunBanco> anular(@RequestBody DtoComunBanco dto) throws Exception {
		logger.debug("BancoRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunBanco>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunBanco> eliminar(@RequestBody DtoComunBanco dto) throws Exception {
		logger.debug("BancoRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunBanco>(dto,HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("BancoRest.listar");
		// TODO BancoRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "banco.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("BancoRest.listaractivos");
		// TODO BancoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "banco.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunBanco>> listarDtoFiltros(@RequestBody DtoComunBanco filtro) throws Exception {
		logger.debug("BancoRest.listardtofiltros");
        List datos = bancoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunBanco>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunBanco>> listarDtoActivos(@RequestBody DtoComunBanco filtro) throws Exception {
		logger.debug("BancoRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = bancoDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunBanco>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunBanco filtro) throws Exception {
		logger.debug("BancoRest.listarPaginado");
		DominioPaginacion paginacion = bancoDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/exportarBancos", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarBancos(HttpServletResponse response, @RequestBody FiltroComunBanco filtro) throws Exception
	 {
		
		DominioPaginacion paginacion = bancoDao.exportarBancos(filtro);
		String[] arrCabecera = new String[] {"Tipo Pago","Descripci\u00f3n","Estado"};
		String[] arrColumnas = new String[] {"banco","descripcioncorta","estadodescripcion"}; 
		

		DtoWhExportar dtoExportar=new DtoWhExportar();
		dtoExportar.setTitulo("Listado de Bancos");
		dtoExportar.setTipoExpotar(filtro.getTipoexportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setPaginacion(paginacion);
			
		servicio.exportarInformacionWh(response,dtoExportar);
	}
}
