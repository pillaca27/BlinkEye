package net.royal.spring.gth.rest;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.framework.constante.ConstanteCliente;
import net.royal.spring.framework.core.UFile;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.gth.dto.DtoDocumento;
import net.royal.spring.gth.dto.DtoHrEvaluacionPeriodoListado;
import net.royal.spring.gth.dto.DtoHrRequerimientoListado;
import net.royal.spring.gth.dto.DtoHrRequerimientoPk;
import net.royal.spring.gth.dto.DtoPersonaListado;
import net.royal.spring.gth.dto.DtoUsuarioExportar;
import net.royal.spring.gth.filtro.FiltroHrEvaluacionPeriodoListado;
import net.royal.spring.gth.filtro.FiltroHrRequerimientoListado;
import net.royal.spring.gth.filtro.FiltroPersonaListado;
import net.royal.spring.gth.filtro.FiltroReporteUsuarios;
import net.royal.spring.gth.servicio.impl.PruebasServicioImpl;
import net.royal.spring.rrhh.servicio.impl.HrCapacitacionServicioImpl;

@RestController
@RequestMapping("/spring/gth/pruebas")
@CrossOrigin(origins = "*")
public class PruebasRest extends GenericoHibernateRest {

	@Autowired
	private PruebasServicioImpl pruebasServicioImpl;

	@Autowired
	private HrCapacitacionServicioImpl hrCapacitacionServicioImpl;

	public PruebasRest() {
		super("pruebas");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	/**
	 * Metodo que consume un query embebido en el app para retornar una lista
	 * 
	 * @param filtro
	 * @return
	 */
	@Transactional(readOnly = true)
	@PostMapping(value = "/listarRequerimientos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoHrRequerimientoListado> listarRequerimientos(@RequestBody FiltroHrRequerimientoListado filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_requerimiento", String.class, filtro.getRequerimiento()));
		List l = listarPorQuery(DtoHrRequerimientoListado.class, "pruebas.listarRequerimientos", parametros);
		return l;
	}

	/**
	 * Metodo que consume un stored procedure para retornar una lista
	 * 
	 * @param filtro
	 * @return
	 */
	@Transactional(readOnly = true)
	@PostMapping(value = "/listarEvaluaciones", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoHrEvaluacionPeriodoListado> listarEvaluaciones(
			@RequestBody FiltroHrEvaluacionPeriodoListado filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, filtro.getCompanyowner()));
		parametros.add(new DominioParametroPersistencia("p_secuencia", Integer.class, filtro.getSecuencia()));
		List l = listarPorQuery(DtoHrEvaluacionPeriodoListado.class, "pruebas.listarEvaluaciones", parametros);
		return l;
	}

	/**
	 * Metodo que consume un stored procedure para retornar una un codigo y mensaje
	 * 
	 * @param filtro
	 * @return
	 */
	@Transactional(readOnly = true)
	@PostMapping(value = "/pruebaDatoRetorno", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoRespuestaBD pruebaDatoRetorno(@RequestBody FiltroHrEvaluacionPeriodoListado filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, filtro.getCompanyowner()));
		parametros.add(new DominioParametroPersistencia("p_secuencia", Integer.class, filtro.getSecuencia()));
		List l = listarPorQuery(DtoHrEvaluacionPeriodoListado.class, "pruebas.listarEvaluaciones", parametros);

		DtoRespuestaBD respuesta = (DtoRespuestaBD) l.get(0);

		if (respuesta.getCodigo().equals("001")) {
			// adicion
		} else {

		}

		return respuesta;
	}

	@Transactional
	@PostMapping(value = "/anularRequerimiento", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoRespuestaBD anularRequerimiento(@RequestBody DtoHrRequerimientoPk pk) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, pk.getCompanyowner()));
		parametros.add(new DominioParametroPersistencia("p_requerimiento", String.class, pk.getRequerimiento()));
		ejecutarPorQuery("pruebas.anularRequerimiento", parametros);
		DtoRespuestaBD respuesta = new DtoRespuestaBD();
		respuesta.setCodigo("OK");
		return respuesta;
	}

	/**
	 * Metodo que retorna una lista de personas paginadas
	 * 
	 * @param filtro
	 * @return
	 */
	@Transactional(readOnly = true)
	@PostMapping(value = "/listarPersonasPaginacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarRequerimientos(@RequestBody FiltroPersonaListado filtro) {

		// TODO limpieza de parametros
		if (UString.estaVacio(filtro.getNombre())) {
			filtro.setNombre(null);
		}
		if (!UString.estaVacio(filtro.getNombre())) {
			filtro.setNombre(UString.trimConNulo(filtro.getNombre()));
		}

		Date anioBase = null;

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(
				new DominioParametroPersistencia("p_usuarioActual", String.class, getUsuarioActual().getUsuario()));
		parametros.add(new DominioParametroPersistencia("p_aniobase", Date.class, anioBase));

		Integer nroRegistrosEncontrados = contar("pruebas.contarPersonasPaginacion", parametros);
		List registrosEncontrados = listarConPaginacion(filtro.getPaginacion(), parametros,
				"pruebas.listarPersonasPaginacion", DtoPersonaListado.class);

		filtro.getPaginacion().setPaginacionRegistrosEncontrados(nroRegistrosEncontrados);
		filtro.getPaginacion().setPaginacionListaResultado(registrosEncontrados);

		for (DtoPersonaListado row : (List<DtoPersonaListado>) registrosEncontrados) {
			row.setDatoAdicional("x");
		}

		return filtro.getPaginacion();
	}

//	public byte[] ejecutarReporteComoPDF(String rutaReporte, Map<String, Object> mapaReporte) throws Exception {
//		return compromisoDao.ejecutarReporteComoPDF(rutaReporte, mapaReporte);
//	}

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

	/**
	 * Reporte PDF
	 * 
	 * @param filtro
	 * @return
	 */
//	@Transactional(readOnly = true)
//	@PostMapping(value = "/generarReporteUsuarios", produces = MediaType.APPLICATION_JSON_VALUE)
//	public DtoDocumento generarReporteUsuarios(@RequestBody FiltroReporteUsuarios filtro) {
//
//		DtoDocumento res = new DtoDocumento();
//
//		String rutaPlantillaJasper = UFile.rutaFisicaWebApp() + File.separator + ConstanteCliente.RECURSOS_GLOBAL
//				+ File.separator + "gth" + File.separator + "usuarios" + File.separator + "ReporteUsuarios.jasper";
//
//		Map<String, Object> parametros = new HashMap<String, Object>();
//		parametros.put("p_centrocostonombre", filtro.getCentrocostoNombre());
//		parametros.put("p_centrocosto", filtro.getCentrocosto());
//		parametros.put("p_usuario", getUsuarioActual().getUsuario());
//
//		try {
//			byte contenido[] = ejecutarReporteComoPDF(rutaPlantillaJasper, parametros);
//			res.setDocumentoNombre("Reporte Usuarios.pdf");
//			res.setDocumentoContenidoBase64(org.apache.commons.codec.binary.Base64.encodeBase64String(contenido));
//			res.setTransaccionEstado(DominioTransaccion.OK);
//			return res;
//		} catch (Exception e) {
//			e.printStackTrace();
//			res.setTransaccionEstado(DominioTransaccion.ERROR);
//			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Hubo un error"));
//			return res;
//		}
//	}

	@Transactional
	@PostMapping(value = "/generarReporteUsuarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public void imprimir(HttpServletResponse response, @RequestBody FiltroReporteUsuarios filtro) throws Exception {
		byte[] content;

		String rutaPlantillaJasper = UFile.rutaFisicaWebApp() + File.separator + ConstanteCliente.RECURSOS_GLOBAL
				+ File.separator + "gth" + File.separator + "usuarios" + File.separator + "ReporteUsuarios.jasper";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("p_centrocostonombre", filtro.getCentrocostoNombre());
		parametros.put("p_centrocosto", filtro.getCentrocosto());
		parametros.put("p_usuario", getUsuarioActual().getUsuario());
		
		System.out.println("RUTA==>" + rutaPlantillaJasper);

		content = hrCapacitacionServicioImpl.ejecutarReporteComoPDF(rutaPlantillaJasper, parametros);

		enviarReportePdf(content, response);
	}

	/**
	 * Reporte texto plano
	 * 
	 * @param filtro
	 * @return
	 */
	/*
	@Transactional(readOnly = true)
	@PostMapping(value = "/generarTramaUsuarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoDocumento generarTramaUsuarios(@RequestBody FiltroReporteUsuarios filtro) {
		DtoDocumento res = new DtoDocumento();

		String separador = "\t";// |, ;, &

		// Obtener datos
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_centrocosto", String.class, filtro.getCentrocosto()));
		List l = listarPorQuery(DtoUsuarioExportar.class, "pruebas.listarusuarioexportar", parametros);

		List<DtoUsuarioExportar> datos = l;

		// Armar Trama
		StringBuilder sb = new StringBuilder("");

		for (DtoUsuarioExportar row : datos) {
			sb.append(row.getUsuario()).append(separador).append(row.getNombre()).append(separador)
					.append(row.getTipoPlanillaNombre()).append(separador).append(row.getCompania()).append(separador)
					.append(row.getCompaniaNombre()).append("\n");
		}

		// Convertimos a bytes
		byte[] contenido = sb.toString().getBytes();
		res.setDocumentoNombre("Reporte Usuarios.txt");
		// Convertimos a base64
		res.setDocumentoContenidoBase64(org.apache.commons.codec.binary.Base64.encodeBase64String(contenido));
		res.setTransaccionEstado(DominioTransaccion.OK);
		return res;
	}
	*/

	@Transactional(readOnly = true)
	@PostMapping(value = "/generarTramaUsuarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public void generarTramaUsuarios(@RequestBody FiltroReporteUsuarios filtro,HttpServletResponse response) throws Exception {

		String separador = "\t";// |, ;, &

		// Obtener datos
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_centrocosto", String.class, filtro.getCentrocosto()));
		List l = listarPorQuery(DtoUsuarioExportar.class, "pruebas.listarusuarioexportar", parametros);

		List<DtoUsuarioExportar> datos = l;

		// Armar Trama
		StringBuilder sb = new StringBuilder("");

		for (DtoUsuarioExportar row : datos) {
			sb.append(row.getUsuario()).append(separador).append(row.getNombre()).append(separador)
					.append(row.getTipoPlanillaNombre()).append(separador).append(row.getCompania()).append(separador)
					.append(row.getCompaniaNombre()).append("\n");
		}

		// Convertimos a bytes
		byte[] contenido = sb.toString().getBytes();
		
		File file = new File("D:\\cursojava\\CAPACITACION\\temporal\\usuario.txt");
		
		FileUtils.writeByteArrayToFile( file, contenido );

		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if (mimeType == null) {
			System.out.println("mimetype is not detectable, will take default");
			mimeType = "application/vnd.ms-excel;charset=UTF-8";
		}

		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
		response.setContentLength((int) file.length());
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}
	
	/**
	 * Reporte texto html
	 * 
	 * @param filtro
	 * @return
	 */

	/**
	 * Reporte texto plano
	 * 
	 * @param filtro
	 * @return
	 * @throws Exception
	 */
	
	/*
	@Transactional(readOnly = true)
	@PostMapping(value = "/generarHtmlUsuarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoDocumento generarHtmlUsuarios(@RequestBody FiltroReporteUsuarios filtro) throws Exception {
		DtoDocumento res = new DtoDocumento();

		// Obtener Planilla
		String rutaPlantilla = UFile.rutaFisicaWebApp() + File.separator + ConstanteCliente.RECURSOS_GLOBAL
				+ File.separator + "gth" + File.separator + "usuarios" + File.separator + "ReporteUsuarios.html";

		// Cargar Plantilla
		String plantilla = UFile.obtenerContenidoFile(rutaPlantilla);

		// Obtener datos
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_centrocosto", String.class, filtro.getCentrocosto()));
		List l = listarPorQuery(DtoUsuarioExportar.class, "pruebas.listarusuarioexportar", parametros);

		List<DtoUsuarioExportar> datos = l;

		// Armar HTML
		StringBuilder detalleHtml = new StringBuilder("");
		for (DtoUsuarioExportar row : datos) {
			detalleHtml.append("<tr>");
			detalleHtml.append("<td>");
			detalleHtml.append(row.getUsuario());
			detalleHtml.append("</td>");
			detalleHtml.append("<td>");
			detalleHtml.append(row.getNombre());
			detalleHtml.append("</td>");
			detalleHtml.append("</tr>");
		}

		plantilla = plantilla.replace("[p_cc]", filtro.getCentrocostoNombre());
		plantilla = plantilla.replace("[p_usuario]", getUsuarioActual().getUsuario());
		plantilla = plantilla.replace("[p_detalle]", detalleHtml.toString());

		// Convertimos a bytes
		byte[] contenido = plantilla.getBytes();
		res.setDocumentoNombre("Reporte Usuarios.html");
		// Convertimos a base64
		res.setDocumentoContenidoBase64(org.apache.commons.codec.binary.Base64.encodeBase64String(contenido));
		res.setTransaccionEstado(DominioTransaccion.OK);
		return res;
	}
	*/

	@Transactional(readOnly = true)
	@PostMapping(value = "/generarHtmlUsuarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public void generarHtmlUsuarios(@RequestBody FiltroReporteUsuarios filtro,HttpServletResponse response) throws Exception {

		// Obtener Planilla
		String rutaPlantilla = UFile.rutaFisicaWebApp() + File.separator + ConstanteCliente.RECURSOS_GLOBAL
				+ File.separator + "gth" + File.separator + "usuarios" + File.separator + "ReporteUsuarios.html";

		// Cargar Plantilla
		String plantilla = UFile.obtenerContenidoFile(rutaPlantilla);

		// Obtener datos
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_centrocosto", String.class, filtro.getCentrocosto()));
		List l = listarPorQuery(DtoUsuarioExportar.class, "pruebas.listarusuarioexportar", parametros);

		List<DtoUsuarioExportar> datos = l;

		// Armar HTML
		StringBuilder detalleHtml = new StringBuilder("");
		for (DtoUsuarioExportar row : datos) {
			detalleHtml.append("<tr>");
			detalleHtml.append("<td>");
			detalleHtml.append(row.getUsuario());
			detalleHtml.append("</td>");
			detalleHtml.append("<td>");
			detalleHtml.append(row.getNombre());
			detalleHtml.append("</td>");
			detalleHtml.append("</tr>");
		}

		plantilla = plantilla.replace("[p_cc]", filtro.getCentrocostoNombre());
		plantilla = plantilla.replace("[p_usuario]", getUsuarioActual().getUsuario());
		plantilla = plantilla.replace("[p_detalle]", detalleHtml.toString());

		// Convertimos a bytes
		byte[] contenido = plantilla.getBytes();

		File file = new File("D:\\cursojava\\CAPACITACION\\temporal\\usuarios.html");
		
		FileUtils.writeByteArrayToFile( file, contenido );

		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if (mimeType == null) {
			System.out.println("mimetype is not detectable, will take default");
			mimeType = "application/vnd.ms-excel;charset=UTF-8";
		}

		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
		response.setContentLength((int) file.length());
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}
	
	/**
	 * Prueba exportar excel generico
	 * 
	 * @param response
	 * @param filtro
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	@PostMapping(value = "/generarUsuariosExcel", produces = MediaType.APPLICATION_JSON_VALUE)
	public void generarUsuariosExcel(HttpServletResponse response, @RequestBody FiltroReporteUsuarios filtro)
			throws Exception {

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_centrocosto", String.class, filtro.getCentrocosto()));
		List l = listarPorQuery(DtoUsuarioExportar.class, "pruebas.listarusuarioexportar", parametros);

		String[] arrCabecera = new String[] { "Compa\u00f1\u00EDa", "Usuario", "Nombre del Usuario",
				"Tipo de Planilla" };

		String[] arrColumnas = new String[] { "companiaNombre", "usuario", "nombre", "tipoPlanillaNombre" };

		DtoWhExportar dtoExportar = new DtoWhExportar();
		dtoExportar.setTitulo("Listado de Usuarios");
		dtoExportar.setTipoExpotar("XLS");
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setLista(l);

		pruebasServicioImpl.exportarInformacionWh(response, dtoExportar);
	}

}

class DtoRespuestaBD {
	private String codigo;
	private String descripcion;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
