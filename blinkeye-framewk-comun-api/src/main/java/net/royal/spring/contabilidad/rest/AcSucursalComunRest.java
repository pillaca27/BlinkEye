package net.royal.spring.contabilidad.rest;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.contabilidad.dao.impl.AcSucursalDaoImpl;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcSucursal;
import net.royal.spring.contabilidad.dominio.filtro.FiltroComunAcSucursal;
import net.royal.spring.contabilidad.dominio.filtro.FiltroComunAcSucursal;
import net.royal.spring.contabilidad.servicio.impl.AcSucursalServicioImpl;
import net.royal.spring.contabilidad.servicio.validar.AcSucursalServicioValidar;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/contabilidad/acsucursalcomun")
@CrossOrigin(origins = "*")
public class AcSucursalComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(AcSucursalComunRest.class);
	
	@Autowired
	private AcSucursalServicioImpl servicio;

	@Autowired
	private AcSucursalServicioValidar validar;

	@Autowired
	private AcSucursalDaoImpl acSucursalDao;
	
	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AcSucursalComunRest() {
		super("acsucursal");
	}

	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Sucursales activas de la tabla AC_Sucursal | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "AC-SUCUR-LISTAR", tags = {"CONTABILIDAD", "SUCURSAL"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("listar Sucursal Planillas");
		List datos = this.listarPorQuery(DtoTabla.class, "acsucursal.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Sucursales activas de la tabla AC_Sucursal | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "AC-SUCUR-LISACT", tags = {"CONTABILIDAD", "SUCURSAL"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaractivos() {
		logger.debug("listar Sucursal Planillas");
		List datos = this.listarPorQuery(DtoTabla.class, "acsucursal.listaractivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla As_Tipohorario "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "AC-SUCUR-OBTTAB", tags = {"CONTABILIDAD", "SUCURSAL"})
	@Transactional
	@PutMapping(value="/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenertabla(@RequestBody DtoTabla pk) throws Exception {
		logger.debug("AsTipohorarioRest.obtenerDtoPorId");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_sucursal", String.class, pk.getCodigo()));
		List datos = this.listarPorQuery(DtoTabla.class, "acsucursal.obtenertabla",parametros);
		DtoTabla dto = null;
		if (datos.size()==1)
			dto = (DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto,HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	/*@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla As_Tipohorario "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "AC-SUCUR-OBTDTO", tags = {"CONTABILIDAD", "SUCURSAL"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcSucursal> obtenerDto(@RequestBody DtoComunAcSucursal pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunAcSucursal dto = obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunAcSucursal>(dto,HttpStatus.OK);
	}*/
	
	public DtoComunAcSucursal obtenerDtoCore(DtoComunAcSucursal pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_sucursal", String.class, pk.getSucursal()));
		List datos = this.listarPorQuery(DtoComunAcSucursal.class, "acsucursal.obtenerdto",parametros);
		DtoComunAcSucursal dto;
		if (datos.size()==1) {
			dto = (DtoComunAcSucursal)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunAcSucursal();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	@Transactional
	@PutMapping(value="/obtenerdescripcionlocal", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerDescripcionlocal(@RequestBody DtoComunAcSucursal pk) throws Exception {
		logger.debug("obtenerdescripcionlocal");
		DtoComunAcSucursal dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getDescripcionlocal(),HttpStatus.OK);
	}
	
	
	/**
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla As_Tipohorario "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "AC-SUCUR-LISDTOFIL", tags = {"CONTABILIDAD", "SUCURSAL"})
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAcSucursal>> listarDtoFiltros(@RequestBody DtoComunAcSucursal filtro) {
		logger.debug("listardtofiltros");
        List datos = this.listarDtoCore(filtro);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunAcSucursal>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@GetMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAcSucursal>> listarDtoActivos() {
		logger.debug("listardtoactivos");
		DtoComunAcSucursal filtro=new DtoComunAcSucursal();
		filtro.setEstado("A");
        List datos = this.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunAcSucursal>>(datos, HttpStatus.OK);
	}
	
	public List<DtoComunAcSucursal> listarDtoCore(@RequestBody DtoComunAcSucursal filtro) {
		if (UString.esNuloVacio(filtro.getSucursal()))
			filtro.setSucursal(null);		
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		else
			filtro.setDescripcionlocal(filtro.getDescripcionlocal().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_sucursal", String.class, filtro.getSucursal()));
        parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunAcSucursal.class, "acsucursal.listardtofiltros", parametros);
        logger.debug(datos.size());
		return datos;
	}
	
	@Transactional
	@PutMapping(value="/obtenersucursalporsucursalygrupo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerSucursalPorSucursalAndGrupo(@RequestBody DtoComunAcSucursal pk) throws Exception {
		logger.debug("obtenerDtoPorSucursalAndGrupo");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_sucursal", String.class, pk.getSucursal()));
		parametros.add(new DominioParametroPersistencia("p_gruposucursal", String.class, pk.getSucursalgrupo()));
		List datos = this.listarPorQuery(DtoComunAcSucursal.class, "acsucursal.obtenerSucursalPorSucursalAndGrupo",parametros);
		DtoComunAcSucursal dto;
		if (datos.size()==1) {
			dto = (DtoComunAcSucursal)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunAcSucursal();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<String>(dto.getSucursal(),HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/listarsucursalporfiltro", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarsucursalporfiltro(@RequestBody FiltroComunAcSucursal filtro) throws Exception {
		logger.debug("acsucursal.listarsucursalporfiltro");
	
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Tipocambiomast.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado("A");
		
		if(UString.esNuloVacio(filtro.getSucursal())) {
			filtro.setSucursal(null);
		}
		
		if(UString.esNuloVacio(filtro.getDescripcionlocal())) {
			filtro.setDescripcionlocal(null);
		}
		
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		parametros.add(new DominioParametroPersistencia("p_sucursal", String.class, filtro.getSucursal()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcionlocal()));

		Integer registros = contar("acsucursal.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "acsucursal.listarPaginadoSentencia",DtoComunAcSucursal.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
	 
		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(),HttpStatus.OK);
	}
	
	/*****************MANTENIMIENTO***************************/
	
	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcSucursal> validar(@Validated @PathVariable String accion, @RequestBody DtoComunAcSucursal dto) throws Exception {
		logger.debug("AcSucursalRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunAcSucursal>(dto, HttpStatus.OK);
	}*/

	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcSucursal> obtenerDto(@RequestBody DtoComunAcSucursal pk) throws Exception {
		logger.debug("AcSucursalRest.obtenerDto");
		DtoComunAcSucursal dto = acSucursalDao.obtenerDtoPorUuid(pk.getUuid());
		return new ResponseEntity<DtoComunAcSucursal>(dto,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcSucursal> registrar(@RequestBody DtoComunAcSucursal dto) throws Exception {
		logger.debug("AcSucursalRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunAcSucursal>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcSucursal> actualizar(@RequestBody DtoComunAcSucursal dto) throws Exception {
		logger.debug("AcSucursalRest.actualizar");
		dto = servicio.coreActualizarPorUuid(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunAcSucursal>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcSucursal> anular(@RequestBody DtoComunAcSucursal dto) throws Exception {
		logger.debug("AcSucursalRest.anular");
		dto = servicio.coreAnularPorUuid(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunAcSucursal>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcSucursal> eliminar(@RequestBody DtoComunAcSucursal dto) throws Exception {
		logger.debug("AcSucursalRest.eliminar");
		dto = servicio.coreEliminarPorUuid(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunAcSucursal>(dto,HttpStatus.OK);
	}

	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("AcSucursalRest.listar");
		// TODO AcSucursalRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "acsucursal.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("AcSucursalRest.listaractivos");
		// TODO AcSucursalRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "acsucursal.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAcSucursal>> listarDtoFiltros(@RequestBody DtoComunAcSucursal filtro) throws Exception {
		logger.debug("AcSucursalRest.listardtofiltros");
        List datos = acSucursalDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunAcSucursal>>(datos, HttpStatus.OK);
	}*/

	@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAcSucursal>> listarDtoActivos(@RequestBody DtoComunAcSucursal filtro) throws Exception {
		logger.debug("AcSucursalRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = acSucursalDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunAcSucursal>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunAcSucursal filtro) throws Exception {
		logger.debug("AcSucursalRest.listarPaginado");
		DominioPaginacion paginacion = acSucursalDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/exportarSucursales", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarSucursales(HttpServletResponse response, @RequestBody FiltroComunAcSucursal filtro) throws Exception
	 {
		
		DominioPaginacion paginacion = acSucursalDao.exportarSucursales(filtro);
		String[] arrCabecera = new String[] {"Sucursal","Descripci\u00f3n","Grupo","Estado","\u00daltimo Usuario ","\u00daltima Modif."};
		String[] arrColumnas = new String[] {"sucursal", "descripcionlocal","grupo","estadodescripcion","ultimousuario","fechadescripcion"}; 
		

		DtoWhExportar dtoExportar=new DtoWhExportar();
		dtoExportar.setTitulo("Listado de Sucursales");
		dtoExportar.setTipoExpotar(filtro.getTipoexportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setPaginacion(paginacion);
			
		servicio.exportarInformacionWh(response,dtoExportar);
	}
}
