package net.royal.spring.workflow.rest;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.framework.core.UFile;
import net.royal.spring.framework.modelo.ReporteArchivoTransaccion;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UBoolean;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.workflow.dao.impl.WfProcesosDaoImpl;
import net.royal.spring.workflow.dominio.WfProcesos;
import net.royal.spring.workflow.dominio.dto.DtoSelectorDinamico;
import net.royal.spring.workflow.dominio.dto.DtoWfEstado;
import net.royal.spring.workflow.dominio.dto.DtoWfProceso;
import net.royal.spring.workflow.dominio.filtro.FiltroSyDocumentos;
import net.royal.spring.workflow.servicio.impl.WfTransaccionesServicioImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spring/workflow/wfproceso")
public class WfProcesosRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(WfProcesosRest.class);

	@Autowired
	private WfTransaccionesServicioImpl servicio;

	@Autowired
	private WfProcesosDaoImpl wfProcesoDaoImpl;

	@PutMapping(value = "/obtenerProcesoPorId", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoWfProceso obtenerProcesoPorId(@RequestBody DtoTabla dt) throws Exception {
		return servicio.obtenerProcesoPorId(dt.getCodigo());
	}

	@PutMapping(value = "/obtenerPlantillasRelacionadas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ReporteArchivoTransaccion> obtenerPlantillasRelacionadas(@RequestBody DtoTabla dt) throws Exception {
		return servicio.obtenerPlantillasRelacionadas(dt.getCodigo());
	}

	@PostMapping(value = "/listarProcesos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoWfProceso> listarProcesos(@RequestBody DtoTabla filtro) throws Exception {
		return servicio.listarProcesos(filtro);
	}

	@PostMapping(value = "/listarProcesosSinVersion", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoWfProceso> listarProcesosSinVersion(@RequestBody DtoTabla filtro) throws Exception {
		return servicio.listarProcesosSinVersion(filtro);
	}

	@PostMapping(value = "/registrarProceso", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoTabla registrarProceso(@RequestBody DtoWfProceso bean) throws Exception {
		return servicio.registrarProceso(bean, getUsuarioActual(), true, false);
	}

	@PostMapping(value = "/actualizaProceso", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoTabla actualizaProceso(@RequestBody DtoWfProceso bean) throws Exception {
		return servicio.actualizaProceso(bean, getUsuarioActual(), UBoolean.validarFlag(bean.getAuxEsNuevaVersion()));
	}

	@PutMapping(value = "/listarSelector", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoTabla> listarSelector(@RequestBody DtoTabla dt) throws Exception {
		return servicio.listarSelector(dt.getCodigo());
	}

	@Transactional
	@PutMapping(value = "/listarPorAplicacionWF", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<WfProcesos>> listarPorAplicacionWF(@RequestBody DtoTabla dto) throws Exception {
		return new ResponseEntity<List<WfProcesos>>(wfProcesoDaoImpl.listarPorAplicacionWF(dto.getCodigo()),
				HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listarWF", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<WfProcesos>> listarWF() throws Exception {
		return new ResponseEntity<List<WfProcesos>>(wfProcesoDaoImpl.listarWF(), HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listarEstadosPorProceso", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoWfEstado>> listarEstadosPorProceso(@RequestBody DtoTabla dt) throws Exception {
		return new ResponseEntity<List<DtoWfEstado>>(servicio.listarEstadosPorProceso(dt.getCodigo()), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/listarSelectorpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarSelectorpaginado(@RequestBody DtoSelectorDinamico filtro)
			throws Exception {
		return new ResponseEntity<DominioPaginacion>(servicio.listarSelectorpaginado(filtro), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PutMapping(value = "/exportar", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoTabla exportar(@RequestBody DtoTabla dto) throws Exception {
		return servicio.exportar(dto.getCodigo());
	}

	@Transactional
	@PostMapping(value = "/importar", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoTabla importar(@RequestBody DtoTabla json) throws Exception {
		return servicio.importar(json, getUsuarioActual());
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/listarSyTipoDocumentos", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarSyTipoDocumentos(@RequestBody FiltroSyDocumentos filtro) throws Exception {
		return servicio.listarSyTipoDocumentos(filtro);
	}

	@Transactional(readOnly = true)
	@GetMapping(value = "/syprocesomst", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoTabla> exportar() throws Exception {
		return servicio.syprocesomst();
	}

	@Transactional(readOnly = true)
	@GetMapping(value = "/sytipodocumento", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoTabla> sytipodocumento() throws Exception {
		return servicio.sytipodocumento();
	}

	@Transactional(readOnly = true)
	@RequestMapping(method = RequestMethod.POST, value = "/reporte")
	public @ResponseBody byte[] reporte(HttpServletResponse response, @RequestBody DtoTabla filtro) throws Exception {

		String rutaFolder = UFile.rutaFisicaWebApp();
		String rutaLogo = rutaFolder + File.separator + "springcore_recursos" + File.separator + "logo.png";
		String rutaReporte = rutaFolder + File.separator + "springcore_recursos" + File.separator + "workflow"
				+ File.separator + "wfproceso" + File.separator + "procesos.jasper";
		
		if (UString.estaVacio(filtro.getEstado())) {
			filtro.setEstado(null);
		}
		if (UString.estaVacio(filtro.getCodigo())) {
			filtro.setCodigo(null);
		}
		if (UString.estaVacio(filtro.getDescripcion())) {
			filtro.setDescripcion(null);
		}

		Map<String, Object> parametrosReport = new HashMap<String, Object>();
		parametrosReport.put("p_aplicacion", filtro.getEstado());
		parametrosReport.put("p_codigo", filtro.getCodigo());
		parametrosReport.put("p_descripcion", filtro.getDescripcion());
		parametrosReport.put("p_logo", rutaLogo);

		return wfProcesoDaoImpl.ejecutarReporte(filtro.getFlag1(), rutaReporte, parametrosReport);
	}
}
