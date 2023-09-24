package net.royal.spring.core.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.royal.spring.core.dao.impl.CompaniamastDaoImpl;
import net.royal.spring.core.dominio.BeanCompaniamast;
import net.royal.spring.core.dominio.BeanCompaniamastPk;
import net.royal.spring.core.dominio.dto.DtoComunCompaniamast;
//import net.royal.spring.core.dominio.filtro.FiltroBanco;
import net.royal.spring.core.dominio.filtro.FiltroComunCompaniamast;
import net.royal.spring.core.dominio.lista.DtlComunCompaniamast;
import net.royal.spring.core.servicio.impl.CompaniamastServicioImpl;
import net.royal.spring.core.servicio.validar.CompaniamastServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/companiamastcomun")
@CrossOrigin(origins = "*")
public class CompaniamastComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(CompaniamastComunRest.class);

	@Autowired
	private CompaniamastServicioImpl servicio;

	@Autowired
	private CompaniamastServicioValidar validar;

	@Autowired
	private CompaniamastDaoImpl companiamastDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public CompaniamastComunRest() {
		super("companiamast"); //companiamast
	}

	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCompaniamast> validar(@Validated @PathVariable String accion, @RequestBody DtoComunCompaniamast dto) throws Exception {
		logger.debug("CompaniamastRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoComunCompaniamast>(dto, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * OBTIENE OBJETO COMPANIAMAST
	 * */
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCompaniamast> obtenerDto(@RequestBody DtoComunCompaniamast pk) throws Exception {
		logger.debug("CompaniamastRest.obtenerDto");
		DtoComunCompaniamast dto = companiamastDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunCompaniamast>(dto,HttpStatus.OK);
	}


	/*
	 * LEONARDO
	 * REGISTRA COMPANIAMAST, COMPANYOWNER y REPORTINGCOMPANY
	 * */
	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCompaniamast> registrar(@RequestBody DtoComunCompaniamast dto) throws Exception {
		logger.debug("CompaniamastRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunCompaniamast>(dto, HttpStatus.CREATED);
	}

	/*
	 * LEONARDO
	 * ACTUALIZA COMPANIAMAST, COMPANYOWNER y REPORTINGCOMPANY
	 * */
	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCompaniamast> actualizar(@RequestBody DtoComunCompaniamast dto) throws Exception {
		logger.debug("CompaniamastRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunCompaniamast>(dto, HttpStatus.OK);
	}

	/*@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCompaniamast> anular(@RequestBody DtoComunCompaniamast dto) throws Exception {
		logger.debug("CompaniamastRest.anular");
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunCompaniamast>(dto, HttpStatus.OK);
	}*/

	
	/*
	 * LEONARDO
	 * ELIMINA COMPANIAMAST, COMPANYOWNER y REPORTINGCOMPANY
	 * */
	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCompaniamast> eliminar(@RequestBody DtoComunCompaniamast dto) throws Exception {
		logger.debug("CompaniamastRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoComunCompaniamast>(dto,HttpStatus.OK);
	}

	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("CompaniamastRest.listar");
		// TODO CompaniamastRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "companiamast.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("CompaniamastRest.listaractivos");
		// TODO CompaniamastRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "companiamast.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunCompaniamast>> listarDtoFiltros(@RequestBody DtoComunCompaniamast filtro) throws Exception {
		logger.debug("CompaniamastRest.listardtofiltros");
        List datos = companiamastDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunCompaniamast>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunCompaniamast>> listarDtoActivos(@RequestBody DtoComunCompaniamast filtro) throws Exception {
		logger.debug("CompaniamastRest.listardtoactivos");
		filtro.setEstado("A");
		List datos = companiamastDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoComunCompaniamast>>(datos, HttpStatus.OK);
	}*/

	/*
	 * LEONARDO
	 * LISTADO PAGINADO GENERAL
	 * 
	 * */
	@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunCompaniamast filtro) throws Exception {
		logger.debug("CompaniamastRest.listarPaginado");
		DominioPaginacion paginacion = companiamastDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	/*
	 * LEONARDO
	 * SUBIDA DE LOGO(PNG)
	 * */
	@Transactional
	@PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> nuevoupload(@RequestParam("logo") MultipartFile logo, @RequestParam("logoanterior") String logoanterior) throws IOException{
		Map<String, Object> response = new HashMap<>();

			try {
				servicio.registrarImagen(logo,logoanterior);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir el logo");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	/*
	 * LEONARDO
	 * BORRADO DE LOGO(PNG)
	 * */
	@Transactional
	@PostMapping(value = "/delete_upload", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete_upload( @RequestParam("logoanterior") String logoanterior) throws IOException{
		Map<String, Object> response = new HashMap<>();
			servicio.eliminarImagen(logoanterior);	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	/*
	 * LEONARDO
	 * VALIDADOR DE LOGO(PNG)
	 * */
	@Transactional //VALIDAR LOGO COMPANY
	@PutMapping(value = "/logo_compania", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> logo_compania(@RequestBody DtoTabla dt ) {
		logger.debug("CompaniamastRest.logo_compania");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_logo", String.class, dt.getCodigo().trim() ));
		List datos = this.listarPorQuery(DtoTabla.class, "companiamast.logo_compania",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	/*
	 * LEONARDO
	 * EXPORTAR LISTA COMPANIAMAST
	 * */
	@Transactional
	@PostMapping(value = "/exportarCompanias", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarCompanias(HttpServletResponse response, @RequestBody FiltroComunCompaniamast filtro) throws Exception
	 {
		
		DominioPaginacion paginacion = companiamastDao.exportarCompanias(filtro);
		String[] arrCabecera = new String[] {"Compa\u00F1\u00EDa","Descripci\u00f3n","Estado"};
		String[] arrColumnas = new String[] {"companiacodigo","descripcioncorta","estadodescripcion"}; 
		

		DtoWhExportar dtoExportar=new DtoWhExportar();
		dtoExportar.setTitulo("Listado de Compa\u00F1\u00EDas");
		dtoExportar.setTipoExpotar(filtro.getTipoexportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setPaginacion(paginacion);
			
		servicio.exportarInformacionWh(response,dtoExportar);
	}
}
