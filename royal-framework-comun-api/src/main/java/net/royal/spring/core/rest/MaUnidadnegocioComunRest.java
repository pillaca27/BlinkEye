package net.royal.spring.core.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dao.impl.MaUnidadnegocioDaoImpl;
//import net.royal.spring.core.dominio.MaUnidadnegocio;
//import net.royal.spring.core.dominio.MaUnidadnegocioPk;
import net.royal.spring.core.dominio.dto.DtoComunMaUnidadnegocio;
import net.royal.spring.core.dominio.dto.DtoComunPais;
import net.royal.spring.core.dominio.filtro.FiltroComunCompaniamast;
import net.royal.spring.core.dominio.filtro.FiltroComunMaUnidadnegocio;
import net.royal.spring.core.dominio.filtro.FiltroComunPais;
//import net.royal.spring.core.dominio.lista.DtlMaUnidadnegocio;
import net.royal.spring.core.servicio.impl.MaUnidadnegocioServicioImpl;
import net.royal.spring.core.servicio.validar.MaUnidadnegocioServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/maunidadnegociocomun")
@CrossOrigin(origins = "*")
public class MaUnidadnegocioComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(MaUnidadnegocioComunRest.class);

	@Autowired
	private MaUnidadnegocioServicioImpl servicio;

	@Autowired
	private MaUnidadnegocioServicioValidar validar;

	@Autowired
	private MaUnidadnegocioDaoImpl maUnidadnegocioDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public MaUnidadnegocioComunRest() {
		super("maunidadnegocio");
	}

	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaUnidadnegocio> validar(@Validated @PathVariable String accion, @RequestBody DtoComunMaUnidadnegocio dto) throws Exception {
		logger.debug("MaUnidadnegocioRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunMaUnidadnegocio>(dto, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * OBTIENE OBJETO MAUNIDADNEGOCIO
	 * */
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaUnidadnegocio> obtenerDto(@RequestBody DtoComunMaUnidadnegocio pk) throws Exception {
		logger.debug("MaUnidadnegocioRest.obtenerDto");
		DtoComunMaUnidadnegocio dto = maUnidadnegocioDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunMaUnidadnegocio>(dto,HttpStatus.OK);
	}

	/*
	 * REGISTRA
	 * ACTUALIZA MAUNIDADNEGOCIO
	 * */
	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaUnidadnegocio> registrar(@RequestBody DtoComunMaUnidadnegocio dto) throws Exception {
		logger.debug("MaUnidadnegocioRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunMaUnidadnegocio>(dto, HttpStatus.CREATED);
	}

	/*
	 * LEONARDO
	 * ACTUALIZA MAUNIDADNEGOCIO
	 * */
	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaUnidadnegocio> actualizar(@RequestBody DtoComunMaUnidadnegocio dto) throws Exception {
		logger.debug("MaUnidadnegocioRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunMaUnidadnegocio>(dto, HttpStatus.OK);
	}

	/*@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaUnidadnegocio> anular(@RequestBody DtoComunMaUnidadnegocio dto) throws Exception {
		logger.debug("MaUnidadnegocioRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunMaUnidadnegocio>(dto, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * ELMINA MAUNIDADNEGOCIO
	 * */
	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunMaUnidadnegocio> eliminar(@RequestBody DtoComunMaUnidadnegocio dto) throws Exception {
		logger.debug("MaUnidadnegocioRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunMaUnidadnegocio>(dto,HttpStatus.OK);
	}

	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("MaUnidadnegocioRest.listar");
		// TODO MaUnidadnegocioRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "maunidadnegocio.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("MaUnidadnegocioRest.listaractivos");
		// TODO MaUnidadnegocioRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "maunidadnegocio.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunMaUnidadnegocio>> listarDtoFiltros(@RequestBody DtoComunMaUnidadnegocio filtro) throws Exception {
		logger.debug("MaUnidadnegocioRest.listardtofiltros");
        List datos = maUnidadnegocioDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunMaUnidadnegocio>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunMaUnidadnegocio>> listarDtoActivos(@RequestBody DtoComunMaUnidadnegocio filtro) throws Exception {
		logger.debug("MaUnidadnegocioRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = maUnidadnegocioDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunMaUnidadnegocio>>(datos, HttpStatus.OK);
	}*/

	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunMaUnidadnegocio filtro) throws Exception {
		logger.debug("MaUnidadnegocioRest.listarPaginado");
		DominioPaginacion paginacion = maUnidadnegocioDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping(value = "/exportarUnidadNegocio", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarUnidadNegocio(HttpServletResponse response, @RequestBody FiltroComunMaUnidadnegocio filtro) throws Exception
	 {
		
		DominioPaginacion paginacion = maUnidadnegocioDao.exportarUnidadNegocio(filtro);
		String[] arrCabecera = new String[] {"Und. Negocio","Descripci\u00f3n","Estado"};
		String[] arrColumnas = new String[] {"unidadnegocio","descripcionlocal","estadodescripcion"}; 
		

		DtoWhExportar dtoExportar=new DtoWhExportar();
		dtoExportar.setTitulo("Listado de Unidad de Negocio");
		dtoExportar.setTipoExpotar(filtro.getTipoexportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setPaginacion(paginacion);
			
		servicio.exportarInformacionWh(response,dtoExportar);
	}

	
	/***
	 * LEONARDO COPI COMUN
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado Activos Tipo trabajador de la tabla MA_UnidadNegocio y estado activo | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "99-UNINEG-CLIACT", tags = {"CORE", "UNIDAD NEGOCIO"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("listar Activos Tipo Trabajador");
		List datos = this.listarPorQuery(DtoTabla.class, "maunidadnegocio.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/***
	 * LEONARDO COPI COMUN
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@Transactional
	@PostMapping(value = "/listarunidadnegocioporfiltro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DominioPaginacion> listarunidadnegocioporfiltro(@RequestBody FiltroComunMaUnidadnegocio filtro)
    {
        logger.debug("MaUnidadnegocioComunRest.listarunidadnegocioporfiltro");        
        Integer cantidadEncontrados = 0;
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        
        if(UString.estaVacio(filtro.getUnidadnegocio()))
        	filtro.setUnidadnegocio(null);
        
        if(UString.estaVacio(filtro.getDescripcionlocal()))
        	filtro.setDescripcionlocal(null);

        parametros.add(new DominioParametroPersistencia("p_unidadnegocio", String.class, filtro.getUnidadnegocio()));
        parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
        parametros.add(new DominioParametroPersistencia("p_usuario", String.class, this.getUsuarioActual().getUsuario()));

        cantidadEncontrados = this.contar("maunidadnegocio.contarunidadnegocioporfiltro", parametros);

        List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros, 
        		"maunidadnegocio.listarunidadnegocioporfiltro", DtoComunMaUnidadnegocio.class);

        filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);
		
        
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
}
