package net.royal.spring.rrhh.rest;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.framework.core.UFile;
import net.royal.spring.framework.modelo.EmpleadomastTransaccion;
import net.royal.spring.framework.modelo.generico.DominioArchivo;
import net.royal.spring.framework.modelo.generico.DominioExportar;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.rrhh.dao.impl.HrCapacitacionDaoImpl;
import net.royal.spring.rrhh.dominio.dto.DtoHrCapacitacion;
import net.royal.spring.rrhh.dominio.dto.DtoHrEmpleadoCapacitacion;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunPersonamastclis001;
import net.royal.spring.rrhh.dominio.filtro.FiltroHrCapacitacion;
import net.royal.spring.rrhh.servicio.impl.HrCapacitacionServicioImpl;

@RestController
@RequestMapping("/spring/rrhh/hrcapacitacion")
@CrossOrigin(origins = "*")
public class HrCapacitacionRest extends GenericoRest {

	@Autowired
	private HrCapacitacionServicioImpl hrCapacitacionServicioImpl;
	
	@Autowired
	private HrCapacitacionDaoImpl hrCapacitacionDaoImpl;

	// listarPaginacion
	@Transactional(readOnly = true)
	@PutMapping(value = "/listarPaginacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarPaginacion(@RequestBody FiltroHrCapacitacion filtro) {
		SeguridadUsuarioActual usuario= new SeguridadUsuarioActual();
		usuario.setUsuario("MISESF");
		usuario.setPersonaId(-1);
		
		return hrCapacitacionServicioImpl.listarPaginacion(filtro, usuario);
	}

	// registrar
	@Transactional
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoHrCapacitacion registrar(@RequestBody DtoHrCapacitacion dto) {
		SeguridadUsuarioActual usuario= new SeguridadUsuarioActual();
		usuario.setUsuario("MISESF");
		usuario.setPersonaId(-1);
		
		return hrCapacitacionServicioImpl.registrar(dto, usuario);
	}

	// obtenerDto
	@Transactional(readOnly = true)
	@PutMapping(value = "/obtenerDto", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoHrCapacitacion obtenerDto(@RequestBody DtoHrCapacitacion dto) {
		return hrCapacitacionServicioImpl.obtenerDto(dto);
	}

	// actualizar
	@Transactional
	@PutMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoHrCapacitacion actualizar(@RequestBody DtoHrCapacitacion dto) {
		SeguridadUsuarioActual usuario= new SeguridadUsuarioActual();
		usuario.setUsuario("MISESF");
		usuario.setPersonaId(-1);
		return hrCapacitacionServicioImpl.actualizar(dto, usuario);
	}

	// aprobar = cambio estado + actualizacion
	@Transactional
	@PutMapping(value = "/aprobar", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoHrCapacitacion aprobar(@RequestBody DtoHrCapacitacion dto) {
		SeguridadUsuarioActual usuario= new SeguridadUsuarioActual();
		usuario.setUsuario("MISESF");
		usuario.setPersonaId(-1);
		return hrCapacitacionServicioImpl.aprobar(dto, usuario);
	}

	// anular = cambio estado + actualizacion
	@Transactional
	@PutMapping(value = "/anular", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoHrCapacitacion anular(@RequestBody DtoHrCapacitacion dto) {
		SeguridadUsuarioActual usuario= new SeguridadUsuarioActual();
		usuario.setUsuario("MISESF");
		usuario.setPersonaId(-1);
		return hrCapacitacionServicioImpl.anular(dto, usuario);
	}

	// eliminar
	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoHrCapacitacion eliminar(@RequestBody DtoHrCapacitacion dto) {
		SeguridadUsuarioActual usuario= new SeguridadUsuarioActual();
		usuario.setUsuario("MISESF");
		usuario.setPersonaId(-1);
		return hrCapacitacionServicioImpl.eliminar(dto, usuario);
	}

	// exportarExcel
	@Transactional(readOnly = true)
	@PutMapping(value = "/exportarListadoExcel", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarListadoExcel(HttpServletResponse response, @RequestBody FiltroHrCapacitacion filtro)
			throws Exception {
		
		//Obtener la Lista de Datos
		filtro.getDominioPaginacion().setPaginacionRegistrosPorPagina(0);
		List lista = listarPaginacion(filtro).getPaginacionListaResultado();
		
		DominioExportar dtoExportar = new DominioExportar();
		dtoExportar.setFormato("XLS");
		dtoExportar.setLstDatos(lista);
		dtoExportar.setTitulo("Reporte de capacitaciones");

		dtoExportar.setArrColumnas(new String[] { "companyOwner", "capacitacion", "auxCursoNombre", "descripcion",
				"auxSolicitanteNombre", "auxEstadoNombre" });
		dtoExportar.setArrCabeceras(new String[] { "Compania", "Id", "Curso", "Descripcion", "Solicitante", "Estado" });

		DominioArchivo obj = hrCapacitacionServicioImpl.exportarInformacion(dtoExportar);
		response.setContentType(obj.getMimeContentType());
		response.setHeader("Content-Disposition",
				String.format("attachment; filename=\"" + obj.getNombreArchivo()) + "\"");
		response.setContentLength((int) obj.getArchivoAdjuntoBytes().length);
		InputStream inputStream = new BufferedInputStream(new FileInputStream(obj.getRutaCompletaArchivo()));
		FileCopyUtils.copy(inputStream, response.getOutputStream());

	}

	// exportarPDF
	@Transactional(readOnly = true)
	@PutMapping(value = "/exportarPDF", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarPDF(HttpServletResponse response, @RequestBody DtoHrCapacitacion dto) throws Exception {

		Map<String, Object> parametrosReport = new HashMap<>();
		parametrosReport.put("p_compania", dto.getCompanyOwner());
		parametrosReport.put("p_capacitacion", dto.getCapacitacion());

		String rutaReporte = UFile.rutaFisicaWebApp() + File.separator + "recursos" + File.separator + "rrhh"
				+ File.separator + "hrcapacitaciones" + File.separator + "R001.jasper";

		byte[] content = hrCapacitacionServicioImpl.ejecutarReporteComoPDF(rutaReporte, parametrosReport);

		enviarReportePdf(content, response);
	}
	
	@Transactional(readOnly = true)
	@PutMapping(value = "/exportar", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportar(HttpServletResponse response, @RequestBody DtoHrCapacitacion dto) throws Exception {

		Map<String, Object> parametrosReport = new HashMap<>();
		//parametrosReport.put("p_compania", dto.getCompanyOwner());
		parametrosReport.put("capacitacion", dto.getCapacitacion());

		String rutaReporte = UFile.rutaFisicaWebApp() + File.separator + "recursos" + File.separator + "rrhh"
				+ File.separator + "hrcapacitaciones" + File.separator + "reportCapacitacion.jasper";

		byte[] content = hrCapacitacionServicioImpl.ejecutarReporteComoPDF(rutaReporte, parametrosReport);

		//enviarReportePdf(content, response);
	}

	// listarParticipantes (Reporte Participantes) - SP
	@Transactional(readOnly = true)
	@PutMapping(value = "/listarParticipantes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoHrEmpleadoCapacitacion> listarParticipantes(@RequestBody DtoHrCapacitacion dto) {
		return hrCapacitacionServicioImpl.listarParticipantes(dto);
	}
	
	public void enviarReportePdf(byte[] contenido, HttpServletResponse response) throws IOException {
		InputStream inputStream;

		String CONTENT_TYPE_APPLICATION_PDF_VALUE = "application/pdf";
		String HTTP_HEADER_CONTENT_DISPOSITION = "Content-Disposition";
		String CONTENT_DISPOSITION_INLINE_FORMAT = "inline; filename=\"%s.pdf\"";

		response.setContentType(CONTENT_TYPE_APPLICATION_PDF_VALUE);
		response.setHeader(HTTP_HEADER_CONTENT_DISPOSITION,
				String.format(CONTENT_DISPOSITION_INLINE_FORMAT, UUID.randomUUID()));
		response.setContentLength(contenido.length);

		inputStream = new BufferedInputStream(new ByteArrayInputStream(contenido));
		StreamUtils.copy(inputStream, response.getOutputStream());
		response.flushBuffer();
	}
	
	//ADICIONALES
	
	@Transactional
	@GetMapping(value = "/listarcompaniasporusuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarCompaniasPorUsuario()
			throws Exception {
		List<DtoTabla> lst = null;
		try {
			lst = hrCapacitacionServicioImpl.listarCompanias("MISESF");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<DtoTabla>>(lst, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listarEmpleados", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarEmpleados(@RequestBody FiltroComunPersonamastclis001 filtro) {

		if (UBigDecimal.esCeroOrNulo(filtro.getPersona()))
			filtro.setPersona(null);
		if (UString.esNuloVacio(filtro.getBusqueda()))
			filtro.setBusqueda(null);

		if (UString.esNuloVacio(filtro.getDocumento()))
			filtro.setDocumento(null);
		if (UString.esNuloVacio(filtro.getDocumentofiscal()))
			filtro.setDocumentofiscal(null);
		if (UString.esNuloVacio(filtro.getDocumentoidentidad()))
			filtro.setDocumentoidentidad(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigo", BigDecimal.class, filtro.getPersona()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getBusqueda()));

		parametros.add(new DominioParametroPersistencia("p_documento", String.class, filtro.getDocumento()));
		parametros.add(new DominioParametroPersistencia("p_docfiscal", String.class, filtro.getDocumentofiscal()));
		parametros
				.add(new DominioParametroPersistencia("p_docidentidad", String.class, filtro.getDocumentoidentidad()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer contador = hrCapacitacionDaoImpl.contar("hrcapacitacion.paginadoEmpleadoOtroContar", parametros);
		List lista = hrCapacitacionDaoImpl.listarConPaginacion(filtro.getPaginacion(), parametros,
				"hrcapacitacion.paginadoEmpleadoOtroListar", EmpleadomastTransaccion.class);

		filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
		filtro.getPaginacion().setPaginacionListaResultado(lista);

		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
	}

	
	@Transactional
	@PutMapping(value = "/listarCursos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarCursos(@RequestBody FiltroComunPersonamastclis001 filtro) {

		if (UString.esNuloVacio(filtro.getBusqueda()))
			filtro.setBusqueda(null);

		if (UString.esNuloVacio(filtro.getDocumento()))
			filtro.setDocumento(null);

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getBusqueda()));
		parametros.add(new DominioParametroPersistencia("p_curso", String.class, filtro.getDocumento()));

		Integer contador = hrCapacitacionDaoImpl.contar("hrcapacitacion.paginadoCursoContar", parametros);
		List lista = hrCapacitacionDaoImpl.listarConPaginacion(filtro.getPaginacion(), parametros,
				"hrcapacitacion.paginadoCursoListar", DtoTabla.class);

		filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
		filtro.getPaginacion().setPaginacionListaResultado(lista);

		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
	}
}
