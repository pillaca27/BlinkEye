package net.royal.spring.dt.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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

import net.royal.spring.dt.dao.impl.MicrosuenosDaoImpl;
import net.royal.spring.dt.dominio.Microsuenos;
import net.royal.spring.dt.dominio.MicrosuenosPk;
import net.royal.spring.dt.dominio.dto.DtoMicrosuenos;
import net.royal.spring.dt.dominio.filtro.FiltroMicrosuenos;
import net.royal.spring.dt.servicio.impl.MicrosuenosServicioImpl;
import net.royal.spring.dt.servicio.impl.MicrosuenosServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.core.UFile;
import net.royal.spring.framework.core.UWebServer;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UPropiedades;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/dt/microsuenos")
@CrossOrigin(origins = "*")
public class MicrosuenosRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(MicrosuenosRest.class);

	@Autowired
	private MicrosuenosServicioImpl servicio;

	@Autowired
	private MicrosuenosServicioValidar validar;

	@Autowired
	private MicrosuenosDaoImpl microsuenosDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public MicrosuenosRest() {
		super("microsuenos");
	}
	
	@RequestMapping(path = "/obtenerDeteccion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody byte[] imprimirCertificado(@RequestBody FiltroMicrosuenos filtro,
			HttpServletResponse response) throws Exception {
		byte[] content = {};
		String pathPrincipal = UFile.rutaFisicaWebApp();
		String rutaPropiedades = UWebServer.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;
		Properties propiedades = UPropiedades.getInstance().abrir(rutaPropiedades, ConstanteBoot.PROPERTIES_GLOBAL);
	
		Map<String, Object> parametrosReport = new HashMap<String, Object>();
//		parametrosReport.put("p_anio", filtro.getAnio());
		
		pathPrincipal = pathPrincipal + File.separator + ConstanteBoot.RECURSOS_GLOBAL + File.separator 
				+ "autoservicios" + File.separator + "dt" + File.separator + "reporte_sesionterminada.jasper";

		return microsuenosDao.ejecutarReporteComoPDF(pathPrincipal, parametrosReport);
	}
	
	@Transactional
	@PutMapping(value="/listarPorClasepaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroMicrosuenos filtro) throws Exception {
		logger.debug("MicrosuenosRest.listarPorClasepaginado");
		DominioPaginacion paginacion = microsuenosDao.listarPorClasepaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}

	/*@Transactional
	@PutMapping(value = "/validar/{accion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoMicrosuenos> validar(@Validated @PathVariable String accion, @RequestBody DtoMicrosuenos dto) throws Exception {
		logger.debug("MicrosuenosRest.validar");
		logger.debug(accion);
		logger.debug(dto);
		dto = validar.core(this.getUsuarioActual(), accion, dto);
		return new ResponseEntity<DtoMicrosuenos>(dto, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoMicrosuenos> obtenerDto(@RequestBody DtoMicrosuenos pk) throws Exception {
		logger.debug("MicrosuenosRest.obtenerDto");
		DtoMicrosuenos dto = microsuenosDao.obtenerDtoCore(pk);
		return new ResponseEntity<DtoMicrosuenos>(dto,HttpStatus.OK);
	}*/

	/*@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoMicrosuenos> registrar(@RequestBody DtoMicrosuenos dto) throws Exception {
		logger.debug("MicrosuenosRest.registrar");
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoMicrosuenos>(dto, HttpStatus.CREATED);
	}*/

	/*@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoMicrosuenos> actualizar(@RequestBody DtoMicrosuenos dto) throws Exception {
		logger.debug("MicrosuenosRest.actualizar");
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoMicrosuenos>(dto, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoMicrosuenos> eliminar(@RequestBody DtoMicrosuenos dto) throws Exception {
		logger.debug("MicrosuenosRest.eliminar");
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoMicrosuenos>(dto,HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("MicrosuenosRest.listar");
		// TODO MicrosuenosRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "microsuenos.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivos() {
		logger.debug("MicrosuenosRest.listaractivos");
		// TODO MicrosuenosRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "microsuenos.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoMicrosuenos>> listarDtoFiltros(@RequestBody DtoMicrosuenos filtro) throws Exception {
		logger.debug("MicrosuenosRest.listardtofiltros");
        List datos = microsuenosDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoMicrosuenos>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listardtoactivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoMicrosuenos>> listarDtoActivos(@RequestBody DtoMicrosuenos filtro) throws Exception {
		logger.debug("MicrosuenosRest.listardtoactivos");
		List datos = microsuenosDao.listarDtoCore(filtro);
		return new ResponseEntity<List<DtoMicrosuenos>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value="/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroMicrosuenos filtro) throws Exception {
		logger.debug("MicrosuenosRest.listarPaginado");
		DominioPaginacion paginacion = microsuenosDao.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listacortaactivospornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaActivosPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("MicrosuenosRest.listaCortaActivosPorNombre");
		filtro.setEstadoId("A");
		List datos = microsuenosDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

	/*@Transactional
	@PutMapping(value = "/listacortapornombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaCortaPorNombre(@RequestBody DtoTabla filtro) throws Exception {
		logger.debug("MicrosuenosRest.listaCortaPorNombre");
		List datos = microsuenosDao.listaCortaPorNombre(filtro);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}*/

}
