package net.royal.spring.framework.web.servicio.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.royal.spring.framework.constante.ConstanteDatos;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.core.UExportar;
import net.royal.spring.framework.core.UFile;
import net.royal.spring.framework.core.UMap;
import net.royal.spring.framework.modelo.CompanyownerrecursoTransaccion;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.EmpleadomastTransaccion;
import net.royal.spring.framework.modelo.ErrorTransaccion;
import net.royal.spring.framework.modelo.MiscelaneosHeaderTransaccion;
import net.royal.spring.framework.modelo.PersonamastTransaccion;
import net.royal.spring.framework.modelo.ReporteTransaccion;
import net.royal.spring.framework.modelo.TipoCambioTransaccion;
import net.royal.spring.framework.modelo.WorkFlowAdjunto;
import net.royal.spring.framework.modelo.WorkFlowEnvioCorreo;
import net.royal.spring.framework.modelo.WorkFlowProceso;
import net.royal.spring.framework.modelo.WorkFlowResultado;
import net.royal.spring.framework.modelo.WorkFlowSeguimiento;
import net.royal.spring.framework.modelo.WorkFlowTransaccion;
import net.royal.spring.framework.modelo.correo.EmailTransaccion;
import net.royal.spring.framework.modelo.generico.DominioArchivo;
import net.royal.spring.framework.modelo.generico.DominioExportar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioLogin;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.constante.ConstanteExportar;
import net.royal.spring.framework.web.constante.ConstanteFiltro;
import net.royal.spring.framework.web.hilo.RegistroErroresHilo;


public class GenericoServicioImpl extends GenericoServicioBase {

	private static Logger logger = LogManager.getLogger(GenericoServicioImpl.class);

	@Autowired
	public HttpServletRequest request;

	/** TIPO DE CAMBIO: INICIO **/
	public TipoCambioTransaccion tipoCambioObtener(TipoCambioTransaccion bean) throws Exception {
		TipoCambioTransaccion res = new TipoCambioTransaccion();
		try {
			if (apiTipoCambioObtener == null)
				this.leerPropiedades();

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiTipoCambioObtener);
			logger.debug(uri);

			HttpEntity<TipoCambioTransaccion> request = new HttpEntity<TipoCambioTransaccion>(bean, this.getHeaders());
			ResponseEntity<TipoCambioTransaccion> result = restTemplate.exchange(uri, HttpMethod.PUT, request,
					TipoCambioTransaccion.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("apiObtenerTipoCambio:" + UString.obtenerSinNulo(apiTipoCambioObtener));
			res.setTransaccionEstado(TipoCambioTransaccion.ERROR);
			//res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, e.getMessage()));
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return res;
	}

	/** TIPO DE CAMBIO: FIN **/

	/** WORKFLOW : INICIO **/
	public WorkFlowEnvioCorreo workflowEnviarCorreo(WorkFlowEnvioCorreo bean) throws Exception {
		try {
			if (apiWorkFlow == null)
				this.leerPropiedades();

			String apiUrl = apiWorkFlow + "spring/workflow/wftransaccion/enviarCorreoExterno";

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiUrl);
			logger.debug(apiUrl);

			HttpEntity<WorkFlowEnvioCorreo> request = new HttpEntity<WorkFlowEnvioCorreo>(bean, this.getHeaders());
			ResponseEntity<WorkFlowEnvioCorreo> result = restTemplate.exchange(uri, HttpMethod.PUT, request,
					WorkFlowEnvioCorreo.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("workflowEnviarCorreo:" + UString.obtenerSinNulo(apiWorkFlow));
			bean.setTransaccionEstado(DominioTransaccion.ERROR);
			//bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return bean;
	}
		
	public WorkFlowSeguimiento workflowSeguimiento(WorkFlowSeguimiento bean) throws Exception {
		return workflowSeguimiento(bean, false);
	}
	public WorkFlowSeguimiento workflowSeguimiento(WorkFlowSeguimiento bean, boolean headersInseguros) throws Exception {
		try {
			if (apiWorkFlowTransaccionSeguimiento == null)
				this.leerPropiedades();

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiWorkFlowTransaccionSeguimiento);
			logger.debug(uri);

			HttpEntity<WorkFlowSeguimiento> request = new HttpEntity<WorkFlowSeguimiento>(bean, headersInseguros ? 
					this.getHeadersSeguro() : this.getHeaders());
			ResponseEntity<WorkFlowSeguimiento> result = restTemplate.exchange(uri, HttpMethod.PUT, request,
					WorkFlowSeguimiento.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error(
					"apiWorkFlowTransaccionSeguimiento:" + UString.obtenerSinNulo(apiWorkFlowTransaccionSeguimiento));
			bean.setTransaccionEstado(DominioTransaccion.ERROR);
			//bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return bean;
	}

	public WorkFlowTransaccion workflowObtener(WorkFlowTransaccion bean) throws Exception {
		try {
			if (apiWorkFlowTransaccionObtener == null)
				this.leerPropiedades();

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiWorkFlowTransaccionObtener);
			logger.debug(uri);

			HttpEntity<WorkFlowTransaccion> request = new HttpEntity<WorkFlowTransaccion>(bean, this.getHeaders());
			ResponseEntity<WorkFlowTransaccion> result = restTemplate.exchange(uri, HttpMethod.PUT, request,
					WorkFlowTransaccion.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("apiWorkFlowTransaccionObtener:" + UString.obtenerSinNulo(apiWorkFlowTransaccionObtener));
			bean.setTransaccionEstado(DominioTransaccion.ERROR);
			//bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return bean;
	}

	public WorkFlowTransaccion workflowEstadoSiguiente(WorkFlowTransaccion bean) throws Exception {
		try {
			if (apiWorkFlow == null)
				this.leerPropiedades();

			String apiUrl = apiWorkFlow + "spring/workflow/wftransaccion/obtenerEstadoSiguienteExterno";

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiUrl);
			logger.debug(apiUrl);

			HttpEntity<WorkFlowTransaccion> request = new HttpEntity<WorkFlowTransaccion>(bean, this.getHeaders());
			ResponseEntity<WorkFlowTransaccion> result = restTemplate.exchange(uri, HttpMethod.PUT, request,
					WorkFlowTransaccion.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("workflowSiguienteEstado:" + UString.obtenerSinNulo(apiWorkFlow));
			bean.setTransaccionEstado(DominioTransaccion.ERROR);
			//bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return bean;
	}
	
	public String workflowEstadoSiguiente(Integer transaccionId) throws Exception {
		WorkFlowTransaccion res = workflowEstadoSiguiente(new WorkFlowTransaccion(transaccionId));
		return res.getNivelSiguienteEstadoId();
	}

	public WorkFlowTransaccion workflowEstadoAnterior(WorkFlowTransaccion bean) throws Exception {
		try {
			if (apiWorkFlow == null)
				this.leerPropiedades();

			String apiUrl = apiWorkFlow + "spring/workflow/wftransaccion/obtenerEstadoAnteriorExterno";

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiUrl);
			logger.debug(apiUrl);

			HttpEntity<WorkFlowTransaccion> request = new HttpEntity<WorkFlowTransaccion>(bean, this.getHeaders());
			ResponseEntity<WorkFlowTransaccion> result = restTemplate.exchange(uri, HttpMethod.PUT, request,
					WorkFlowTransaccion.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("workflowAnteriorEstado:" + UString.obtenerSinNulo(apiWorkFlow));
			bean.setTransaccionEstado(DominioTransaccion.ERROR);
			//bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return bean;
	}

	public String workflowEstadoAnterior(Integer transaccionId) throws Exception {
		WorkFlowTransaccion res = workflowEstadoAnterior(new WorkFlowTransaccion(transaccionId));
		return res.getNivelAnteriorEstadoId();
	}

	public WorkFlowTransaccion workflowEstadoRechazado(WorkFlowTransaccion bean) throws Exception {
		try {
			if (apiWorkFlow == null)
				this.leerPropiedades();

			String apiUrl = apiWorkFlow + "spring/workflow/wftransaccion/obtenerEstadoRechazadoExterno";

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiUrl);
			logger.debug(apiUrl);

			HttpEntity<WorkFlowTransaccion> request = new HttpEntity<WorkFlowTransaccion>(bean, this.getHeaders());
			ResponseEntity<WorkFlowTransaccion> result = restTemplate.exchange(uri, HttpMethod.PUT, request,
					WorkFlowTransaccion.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("workflowEstadoRechazado:" + UString.obtenerSinNulo(apiWorkFlow));
			bean.setTransaccionEstado(DominioTransaccion.ERROR);
			//bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return bean;
	}

	public String workflowEstadoRechazado(Integer transaccionId) throws Exception {
		WorkFlowTransaccion res = workflowEstadoRechazado(new WorkFlowTransaccion(transaccionId));
		return res.getNivelRechazadoEstadoId();
	}
	
	public WorkFlowTransaccion workflowSaltarNivel(WorkFlowTransaccion bean) throws Exception {
		try {
			if (apiWorkFlow == null)
				this.leerPropiedades();

			String apiUrl = apiWorkFlow + "spring/workflow/wftransaccion/saltarNivelExterno";

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiUrl);
			logger.debug(apiUrl);

			HttpEntity<WorkFlowTransaccion> request = new HttpEntity<WorkFlowTransaccion>(bean, this.getHeaders());
			ResponseEntity<WorkFlowTransaccion> result = restTemplate.exchange(uri, HttpMethod.PUT, request,
					WorkFlowTransaccion.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("saltarNivelExterno:" + UString.obtenerSinNulo(apiWorkFlow));
			bean.setTransaccionEstado(DominioTransaccion.ERROR);
			//bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return bean;
	}
	/**
	 * Como no mandan la version, se retorna los datos de la ultima version
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public WorkFlowProceso procesoObtener(WorkFlowProceso bean) throws Exception {
		try {
			if (apiWorkFlow == null)
				this.leerPropiedades();

			String apiUrl = apiWorkFlow + "spring/workflow/wftransaccion/obtenerProceso";

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiUrl);
			logger.debug(apiUrl);

			HttpEntity<WorkFlowProceso> request = new HttpEntity<WorkFlowProceso>(bean, this.getHeadersSeguro());
			ResponseEntity<WorkFlowProceso> result = restTemplate.exchange(uri, HttpMethod.PUT, request,
					WorkFlowProceso.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("procesoObtener:" + UString.obtenerSinNulo(apiWorkFlow));
			bean.setTransaccionEstado(DominioTransaccion.ERROR);
			//bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return bean;
	}
		
	public WorkFlowAdjunto workflowVerAdjunto(WorkFlowAdjunto bean) throws Exception {
		try {
			if (apiWorkFlow == null)
				this.leerPropiedades();

			String apiUrl = apiWorkFlow + "spring/workflow/wftransaccion/verAdjuntoWf";

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiUrl);
			logger.debug(apiUrl);

			HttpEntity<WorkFlowAdjunto> request = new HttpEntity<WorkFlowAdjunto>(bean, this.getHeaders());
			ResponseEntity<WorkFlowAdjunto> result = restTemplate.exchange(uri, HttpMethod.POST, request,
					WorkFlowAdjunto.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("workflowEnviarCorreo:" + UString.obtenerSinNulo(apiWorkFlow));
			bean.setTransaccionEstado(DominioTransaccion.ERROR);
			//bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * Como no mandan la version, se retorna los datos de la ultima version
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public String workflowEstadoInicial(String procesoId) throws Exception {
		WorkFlowProceso res = procesoObtener(new WorkFlowProceso(procesoId));
		return res.getNivelEstadoIdInicial();
	}

	/** WORKFLOW : FIN **/

	/** EXPORTAR : INICIO **/
	public DominioArchivo exportarInformacion(String formato, List lstDatos) throws Exception {
		return exportarInformacion(formato, lstDatos, null);
	}

	public DominioArchivo exportarInformacion(String formato, List lstDatos, String[] arrColumnas) throws Exception {
		DominioArchivo dto = null;
		if (lstDatos == null) {
			return null;
		}
		if (propiedades == null)
			this.leerPropiedades();
		logger.debug(formato);
		logger.debug(lstDatos.size());
		logger.debug("rutaTemporalFisica:" + UString.obtenerSinNulo(rutaTemporalFisica));
		UExportar.rutaTemporal = rutaTemporalFisica;

		if (formato.equals("XLS")) {
			// dto = UList.listToXls(lstDatos, arrColumnas);
			dto = exportarInformacion(new DominioExportar(formato, lstDatos, arrColumnas));
		}
		if (formato.equals("PDF")) {
			// dto = UList.listToPdf(lstDatos, arrColumnas);
			dto = exportarInformacion(new DominioExportar(formato, lstDatos, arrColumnas));
		}
		if (formato.equals("XML"))
			dto = UExportar.listToXml(lstDatos);
		if (formato.equals("CSV"))
			dto = UExportar.listToCsv(lstDatos, arrColumnas);
		if (formato.equals("TXT"))
			dto = UExportar.listToTxt(lstDatos, arrColumnas);
		return dto;
	}

	public DominioArchivo exportarInformacion(DominioExportar exp) throws Exception {
		logger.debug("exportarInformacion(DominioExportar dtoExportar) ");
		SeguridadUsuarioActual usu = this.getUsuarioActual();
		String usua = usu.getUsuario();
		logger.debug(usua);
		// usua = "DARIO QQUECHO QUISPE";

		DominioArchivo dto = null;
		if (exp.getLstDatos() == null) {
			return null;
		}
		if (propiedades == null)
			this.leerPropiedades();
		logger.debug(exp.getFormato());
		logger.debug(exp.getLstDatos().size());
		logger.debug("rutaTemporalFisica:" + UString.obtenerSinNulo(rutaTemporalFisica));
		UExportar.rutaTemporal = rutaTemporalFisica;

		List<String> atributos = null;
		List<String> cabeceras = null;
		if (exp.getArrColumnas() != null) {
			atributos = new ArrayList<>();
			String[] arrColumnas = exp.getArrColumnas();
			for (int i = 0; i < arrColumnas.length; i++) {
				atributos.add(arrColumnas[i]);
			}
		}
		if (exp.getArrCabeceras() != null) {
			cabeceras = new ArrayList<>();
			String[] arrCabeceras = exp.getArrCabeceras();
			for (int i = 0; i < arrCabeceras.length; i++) {
				cabeceras.add(arrCabeceras[i]);
			}
		} else {
			cabeceras = atributos;
		}
		if (exp.getFormato().equals("XLS")) {
			logger.debug("exportarInformacion(DominioExportar dtoExportar):XLS");
			// dto = UList.listToXls(dtoExportar.getLstDatos(),
			// dtoExportar.getArrColumnas());
			dto = exportarInformacionExcel(UString.obtenerValorCadenaSinNulo(exp.getTitulo()), usua, cabeceras,
					atributos, exp.getLstDatos());
		}
		if (exp.getFormato().equals("XML")) {
			dto = exportarInformacionXML(UString.obtenerValorCadenaSinNulo(exp.getTitulo()), usua, cabeceras, atributos,
					exp.getLstDatos());
		}
		if (exp.getFormato().equals("PDF")) {
			logger.debug("exportarInformacion(DominioExportar dtoExportar):PDF");
			// dto = UList.listToPdf(dtoExportar.getLstDatos(),
			// dtoExportar.getArrColumnas());
			dto = exportarInformacionPdf(UString.obtenerValorCadenaSinNulo(exp.getTitulo()), usua, cabeceras, atributos,
					exp.getLstDatos(), exp.isVertical());
		}
		if (exp.getFormato().equals("JSON")) {
			logger.debug("exportarInformacion(DominioExportar dtoExportar):JSON");
//			dto = exportarInformacionJson(UString.obtenerValorCadenaSinNulo(exp.getTitulo()), usua, cabeceras, atributos,
//					exp.getLstDatos(), exp.isVertical());
			dto =  UExportar.imprimirJson(exp.getLstDatos(),exp.getArrColumnas());
//			DtoTabla json = new DtoTabla();
//			ObjectMapper mapper = new ObjectMapper();
//			mapper.writeValueAsString(exp.getLstDatos());
//			json.setNombre( exp.getTitulo() +" " + new SimpleDateFormat("ddMMyyyy_hhmss").format(new Date()) + ".json");
//			json.setDescripcion(mapper.writeValueAsString(exp.getLstDatos()));
			
			
		}
		if (exp.getFormato().equals("CSV"))
			dto = UExportar.listToCsv(exp.getLstDatos(), exp.getArrColumnas());
		if (exp.getFormato().equals("TXT"))
			dto = UExportar.listToTxt(exp.getLstDatos(), exp.getArrColumnas());
		return dto;
	}

	private DominioArchivo exportarInformacionXML(String titulo, String usuario, List<String> cabeceras,
			List<String> atributos, List objetos) throws Exception {
		logger.debug("exportarInformacionXML");
		if (propiedades == null)
			this.leerPropiedades();
		UExportar.rutaTemporal = rutaTemporalFisica;

		if (UString.esNuloVacio(titulo))
			titulo = "Reporte";

		String rutaCompletaXML = UExportar.generarNombreAleatorio("xml");
		DominioArchivo dto = UExportar.imprimirXML(titulo, usuario, cabeceras, atributos, objetos, rutaCompletaXML);
		return dto;
	}

	public DominioArchivo exportarInformacionPdf(String titulo, String usuario, List<String> atributos, List objetos,
			boolean vertical) throws Exception {
		return exportarInformacionPdf(titulo, usuario, atributos, atributos, objetos, vertical);
	}

	public DominioArchivo exportarInformacionPdf(String titulo, String usuario, List<String> cabeceras,
			List<String> atributos, List objetos) throws Exception {
		return exportarInformacionPdf(titulo, usuario, cabeceras, atributos, objetos, true);
	}

	public DominioArchivo exportarInformacionPdf(String titulo, String usuario, List<String> cabeceras,
			List<String> atributos, List objetos, boolean vertical) throws Exception {
		logger.debug("exportarInformacionPdf");
		DominioArchivo dto = new DominioArchivo();
		if (propiedades == null)
			this.leerPropiedades();
		UExportar.rutaTemporal = rutaTemporalFisica;
		String rutaCompletaPdf = UExportar.generarNombreAleatorio("pdf");

		UExportar.imprimirPdf(titulo, usuario, cabeceras, atributos, objetos, vertical, rutaCompletaPdf,
				imagenReportes);

		File file = new File(rutaCompletaPdf);

		dto.setRutaCompletaArchivo(rutaCompletaPdf);
		dto.setArchivoAdjuntoBytes(UFile.obtenerArregloByte(rutaCompletaPdf));
		dto.setMimeContentType(UString.obtenerMimeType(file.getName()));
		dto.setNombreArchivo(file.getName());

		return dto;
	}
	
	public DominioArchivo exportarInformacionJson(String titulo, String usuario, List<String> cabeceras,
			List<String> atributos, List objetos, boolean vertical) throws Exception {
		logger.debug("exportarInformacionJson");
		DominioArchivo dto = new DominioArchivo();
		if (propiedades == null)
			this.leerPropiedades();
		UExportar.rutaTemporal = rutaTemporalFisica;
		String rutaCompletaJson = UExportar.generarNombreAleatorio("json");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValueAsString(objetos);
		

		File file = new File(rutaCompletaJson);

		dto.setRutaCompletaArchivo(rutaCompletaJson);
		dto.setArchivoAdjuntoBytes(UFile.obtenerArregloByte(mapper.toString()));
		dto.setMimeContentType(UString.obtenerMimeType(file.getName()));
		dto.setNombreArchivo(file.getName());

		return dto;
	}

	public DominioArchivo exportarInformacionExcel(String titulo, String usuario, List<String> cabeceras,
			List<String> atributos, List objetos) throws Exception {
		logger.debug("exportarInformacionExcel");
		if (propiedades == null)
			this.leerPropiedades();
		UExportar.rutaTemporal = rutaTemporalFisica;

		if (UString.esNuloVacio(titulo))
			titulo = "Hoja";

		String rutaCompletaPdf = UExportar.generarNombreAleatorio("xls");

		DominioArchivo dto = UExportar.imprimirExcel(titulo, usuario, cabeceras, atributos, objetos, rutaCompletaPdf,
				imagenReportes);

		return dto;
	}

	/** EXPORTAR : FIN **/

	/** CORREO : INICIO **/
	
	public EmailTransaccion correoEnviar(EmailTransaccion bean) {
		return correoEnviar(bean, null);
	}

	public EmailTransaccion correoEnviar(EmailTransaccion bean, SeguridadUsuarioLogin token) {

		EmailTransaccion res = new EmailTransaccion();
		try {
			if (propiedades == null)
				this.leerPropiedades();
			URI uri = new URI(apiCorreoEnviar);
			HttpEntity<EmailTransaccion> request = new HttpEntity<EmailTransaccion>(bean, this.getHeaders(token));

			RestTemplate restTemplate = new RestTemplate();
			restTemplate.exchange(uri, HttpMethod.PUT, request, EmailTransaccion.class);

			EmailTransaccion emailTransaccion = new EmailTransaccion();
			emailTransaccion.setTransaccionEstado(DominioTransaccion.OK);
			return emailTransaccion;

		} catch (Exception e) {
			logger.error("API ENVIO CORREO ERROR :" + e.getMessage());
			logger.error("API ENVIO CORREO ERROR :" + bean.getAsunto());
			res.setTransaccionEstado(EmailTransaccion.ERROR);
			//res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, e.getMessage()));
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return res;
	}

	class EnviarCorreoHilo extends Thread {
		private URI uri;
		private HttpEntity<EmailTransaccion> request;

		public EnviarCorreoHilo(URI uri, HttpEntity<EmailTransaccion> request) {
			this.uri = uri;
			this.request = request;
		}

		@Override
		public void run() {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<EmailTransaccion> result = restTemplate.exchange(uri, HttpMethod.POST, request,
					EmailTransaccion.class);
		}
	}

	public void correoEnviarAsincrono(EmailTransaccion bean) throws Exception {
		correoEnviarAsincrono(bean, null);
	}

	public void correoEnviarAsincrono(EmailTransaccion bean, SeguridadUsuarioLogin token) throws Exception {
		EmailTransaccion res = new EmailTransaccion();
		try {
			if (propiedades == null)
				this.leerPropiedades();
			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiCorreoEnviar);

			HttpEntity<EmailTransaccion> request = new HttpEntity<EmailTransaccion>(bean, this.getHeaders(token));
			ExecutorService executor = Executors.newSingleThreadExecutor();
			executor.submit(() -> {
				ResponseEntity<EmailTransaccion> result = restTemplate.exchange(uri, HttpMethod.POST, request,
						EmailTransaccion.class);
			});
		} catch (Exception e) {
			logger.error("API ENVIO CORREO ERROR :" + e.getMessage());
			logger.error("API ENVIO CORREO ERROR :" + bean.getAsunto());
			res.setTransaccionEstado(EmailTransaccion.ERROR);
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
	}

	public EmailTransaccion correoEnviarInseguro(EmailTransaccion bean) throws Exception {
		EmailTransaccion res = new EmailTransaccion();
		try {
			if (propiedades == null)
				this.leerPropiedades();

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiCorreoEnviar);

			HttpEntity<EmailTransaccion> request = new HttpEntity<EmailTransaccion>(bean, this.getHeadersSeguro());
			ResponseEntity<EmailTransaccion> result = restTemplate.exchange(uri, HttpMethod.PUT, request,
					EmailTransaccion.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("API ENVIO CORREO ERROR :" + e.getMessage());
			logger.error("API ENVIO CORREO ERROR :" + bean.getAsunto());
			res.setTransaccionEstado(EmailTransaccion.ERROR);
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return res;
	}

	public void correoEnviarAsincronoInseguro(EmailTransaccion bean) throws Exception {
		EmailTransaccion res = new EmailTransaccion();
		try {
			if (propiedades == null)
				this.leerPropiedades();
			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiCorreoEnviar);

			HttpEntity<EmailTransaccion> request = new HttpEntity<EmailTransaccion>(bean, this.getHeadersSeguro());
			ExecutorService executor = Executors.newSingleThreadExecutor();
			executor.submit(() -> {
				ResponseEntity<EmailTransaccion> result = restTemplate.exchange(uri, HttpMethod.POST, request,
						EmailTransaccion.class);
			});
		} catch (Exception e) {
			logger.error("API ENVIO CORREO ERROR :" + e.getMessage());
			logger.error("API ENVIO CORREO ERROR :" + bean.getAsunto());
			res.setTransaccionEstado(EmailTransaccion.ERROR);
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
	}
	
	/** CORREO : FIN **/

	/** REPORTES : INICIO **/
	public ReporteTransaccion reporteEjecutarWorkFlow(ReporteTransaccion bean) throws Exception {
		bean.setOrigen(ReporteTransaccion.ORIGEN_WORKFLOW);
		return reporteEjecutar(bean,false);
	}
	public ReporteTransaccion reporteEjecutar(ReporteTransaccion bean) throws Exception {
		bean.setOrigen(ReporteTransaccion.ORIGEN_GENERICO);
		return reporteEjecutar(bean,false);
	}
	public ReporteTransaccion reporteEjecutar(ReporteTransaccion bean,Boolean flgGenerarPorDefecto) throws Exception {
		ReporteTransaccion res = new ReporteTransaccion();
		if (bean==null) {
			res.setTransaccionEstado(DominioTransaccion.ERROR);
			res.setTransaccionMensajesCadena("======> ReporteTransaccion no enviado!!");
			logger.error(res.getTransaccionMensajesCadena());
			return res;
		}
		
		if (UString.esNuloVacio(bean.getAplicacionCodigo()) ||  UString.esNuloVacio(bean.getReporteCodigo())) {
			res.setTransaccionEstado(DominioTransaccion.ERROR);
			res.setTransaccionMensajesCadena("======> Codigo de apliacion o reporte no enviado!!");
			logger.error(res.getTransaccionMensajesCadena());
			return res;
		}
		
		
		String cc="<!DOCTYPE html><html><head><title>[p_transaccionProcesoNombre]</title><style></style></head><body><h1>[p_transaccionProcesoNombre]</h1><h2>[p_transaccionAccionDescripcion]</h2><br/><table><tbody><tr><td>Transacci&oacute;n</td><td>:</td><td>[p_transaccionId]</td></tr><tr><td>Solicitante</td><td>:</td><td>[p_transaccionSolicitanteNombre]</td></tr><tr><td>Referencia</td><td>:</td><td>[p_transaccionReferencia]</td></tr><tr><td>Fecha Registro</td><td>:</td><td>[p_transaccionFechaRegistro_mdformatofechahora]</td></tr></tbody></table><br/><p>Nota: Este e-mail es generado de manera autom&aacute;tica, por favor no responda a este mensaje.</p></body></html>";
		try {
			if (propiedades == null)
				this.leerPropiedades();

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiReporteEjecutar);
			HttpEntity<ReporteTransaccion> requestp = new HttpEntity<ReporteTransaccion>(bean, this.getHeaders());
			ResponseEntity<ReporteTransaccion> result = restTemplate.exchange(uri, HttpMethod.PUT, requestp,
					ReporteTransaccion.class);
			bean = result.getBody();
			if (!bean.getTransaccionEstado().equals(DominioTransaccion.OK)) {
				// Si el generar plantilla de correo falla evaluar si se asigna una plantilla por defecto
				if (flgGenerarPorDefecto) {				
					bean.setResultadoAsunto("Sin Formato "+bean.getNombreCompletoReporte());
					bean.setResultadoCuerpoBinario(UMap.obtenerBinarioParseado(cc.getBytes(),bean.getParametros()));
					bean.setTransaccionEstado(DominioTransaccion.OK);
				}	
			}			
			return bean;
		} catch (Exception e) {
			logger.error("apiReporteEjecutar:" + UString.obtenerSinNulo(apiReporteEjecutar));
			res.setTransaccionEstado(DominioTransaccion.ERROR);
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();			
			if (flgGenerarPorDefecto) {				
				bean.setResultadoAsunto("Sin Formato "+bean.getNombreCompletoReporte());
				bean.setResultadoCuerpoBinario(UMap.obtenerBinarioParseado(cc.getBytes(),bean.getParametros()));
				bean.setTransaccionEstado(DominioTransaccion.OK);
			}			
		}
		return res;
	}

	public ReporteTransaccion reporteEjecutarCadena(ReporteTransaccion bean) throws Exception {
		ReporteTransaccion res = new ReporteTransaccion();
		try {
			if (propiedades == null)
				this.leerPropiedades();

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiReporteEjecutarCadena);
			HttpEntity<ReporteTransaccion> requestp = new HttpEntity<ReporteTransaccion>(bean, this.getHeaders());
			ResponseEntity<ReporteTransaccion> result = restTemplate.exchange(uri, HttpMethod.PUT, requestp,
					ReporteTransaccion.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("apiReporteEjecutarCadena:" + UString.obtenerSinNulo(apiReporteEjecutarCadena));
			res.setTransaccionEstado(DominioTransaccion.ERROR);
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return res;
	}

	public ReporteTransaccion reporteEjecutarInseguro(ReporteTransaccion bean) throws Exception {
		ReporteTransaccion res = new ReporteTransaccion();
		try {
			if (propiedades == null)
				this.leerPropiedades();

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiReporteEjecutar);
			HttpHeaders headerInseguro = this.getHeadersSeguro();
			HttpEntity<ReporteTransaccion> requestp = new HttpEntity<ReporteTransaccion>(bean, headerInseguro);
			ResponseEntity<ReporteTransaccion> result = restTemplate.exchange(uri, HttpMethod.PUT, requestp,
					ReporteTransaccion.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("apiReporte:" + UString.obtenerSinNulo(apiReporteEjecutar));
			res.setTransaccionEstado(DominioTransaccion.ERROR);
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return res;
	}

	public ReporteTransaccion reporteEjecutarCadenaInseguro(ReporteTransaccion bean) throws Exception {
		ReporteTransaccion res = new ReporteTransaccion();
		try {
			if (propiedades == null)
				this.leerPropiedades();

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiReporteEjecutarCadena);
			HttpHeaders headerInseguro = this.getHeadersSeguro();
			HttpEntity<ReporteTransaccion> requestp = new HttpEntity<ReporteTransaccion>(bean, headerInseguro);
			ResponseEntity<ReporteTransaccion> result = restTemplate.exchange(uri, HttpMethod.PUT, requestp,
					ReporteTransaccion.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("apiReporte:" + UString.obtenerSinNulo(apiReporteEjecutarCadena));
			res.setTransaccionEstado(DominioTransaccion.ERROR);
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * REPORTES : FIN
	 * 
	 * @throws Exception
	 **/

	public DtoTabla companiaObtenerNombre(String companiacodigo) throws Exception {

		DtoTabla res = new DtoTabla();
		DtoTabla pTran = new DtoTabla();
		pTran.setCodigo(companiacodigo);

		try {
			if (apiCompaniaObtenerNombre == null)
				this.leerPropiedades();

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiCompaniaObtenerNombre);
			logger.debug(uri);

			HttpEntity<DtoTabla> request = new HttpEntity<DtoTabla>(pTran, this.getHeaders());
			ResponseEntity<DtoTabla> result = restTemplate.exchange(uri, HttpMethod.PUT, request, DtoTabla.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("apiCompaniaObtenerNombre:" + UString.obtenerSinNulo(apiCompaniaObtenerNombre));
			res.setCodigo(DominioTransaccion.ERROR);
			//res.setDescripcion(e.getMessage());
			res.setDescripcion("Ha ocurrido un error.");
			e.printStackTrace();
		}
		return null;
	}

	public String imagenObtener(String compania, ConstanteDatos.TIPO_IMAGEN tipoImagen, String periodo,
			String tipoReporte) throws Exception {

		CompanyownerrecursoTransaccion res = new CompanyownerrecursoTransaccion();
		CompanyownerrecursoTransaccion pTran = new CompanyownerrecursoTransaccion();
		String urlImagen = "";
		pTran.setCompania(compania);
		pTran.setTipoImagen(tipoImagen);
		pTran.setPeriodo(periodo);
		pTran.setTipoReporte(tipoReporte);

		try {
			if (apiCompanyownerObtenerRecurso == null)
				this.leerPropiedades();

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiCompanyownerObtenerRecurso);
			logger.debug(uri);

			HttpEntity<CompanyownerrecursoTransaccion> request = new HttpEntity<CompanyownerrecursoTransaccion>(pTran,
					this.getHeaders());
			ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.PUT, request, String.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("apiCompanyownerObtenerRecurso:" + UString.obtenerSinNulo(apiCompanyownerObtenerRecurso));
			res.setTransaccionEstado(DominioTransaccion.ERROR);
			//res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, e.getMessage()));
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return null;
	}

	public EmpleadomastTransaccion empleadoObtenerDatos(String companiacodigo, Integer empleado) throws Exception {

		EmpleadomastTransaccion res = new EmpleadomastTransaccion();
		EmpleadomastTransaccion pTran = new EmpleadomastTransaccion();
		pTran.setPersona(empleado);
		pTran.setEmpleado(empleado);
		pTran.setCompaniasocio(companiacodigo);

		try {
			if (apiEmpleadoObtenerDatos == null)
				this.leerPropiedades();

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiEmpleadoObtenerDatos);
			logger.debug(uri);

			HttpEntity<EmpleadomastTransaccion> request = new HttpEntity<EmpleadomastTransaccion>(pTran,
					this.getHeaders());
			ResponseEntity<EmpleadomastTransaccion> result = restTemplate.exchange(uri, HttpMethod.PUT, request,
					EmpleadomastTransaccion.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("apiEmpleadoObtenerDatos:" + UString.obtenerSinNulo(apiEmpleadoObtenerDatos));
			res.setTransaccionEstado(DominioTransaccion.ERROR);
			//res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, e.getMessage()));
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return null;
	}

	public PersonamastTransaccion personaObtenerDatos(Integer empleado) throws Exception {

		PersonamastTransaccion res = new PersonamastTransaccion();
		PersonamastTransaccion pTran = new PersonamastTransaccion();
		pTran.setPersona(empleado);

		try {
			if (apiPersonaObtenerDatos == null)
				this.leerPropiedades();

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiPersonaObtenerDatos);
			logger.debug(uri);

			HttpEntity<PersonamastTransaccion> request = new HttpEntity<PersonamastTransaccion>(pTran,
					this.getHeaders());
			ResponseEntity<PersonamastTransaccion> result = restTemplate.exchange(uri, HttpMethod.PUT, request,
					PersonamastTransaccion.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("apiPersonaObtenerDatos:" + UString.obtenerSinNulo(apiPersonaObtenerDatos));
			res.setTransaccionEstado(DominioTransaccion.ERROR);
			//res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, e.getMessage()));
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return null;
	}

	public DtoTabla miscelaneoObtenerDescripcion(String aplicacion, String codigotabla, String compania,
			String codigoelemento) throws Exception {

		DtoTabla res = new DtoTabla();
		MiscelaneosHeaderTransaccion pTran = new MiscelaneosHeaderTransaccion();
		pTran.setAplicacioncodigo(aplicacion);
		pTran.setCompania(compania);
		pTran.setCodigotabla(codigotabla);
		pTran.setTransaccionEstado(codigoelemento);

		try {
			if (apiMiscelaneoObtenerDescripcion == null)
				this.leerPropiedades();

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiMiscelaneoObtenerDescripcion);
			logger.debug(uri);

			HttpEntity<MiscelaneosHeaderTransaccion> request = new HttpEntity<MiscelaneosHeaderTransaccion>(pTran,
					this.getHeaders());
			ResponseEntity<DtoTabla> result = restTemplate.exchange(uri, HttpMethod.PUT, request, DtoTabla.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("apiMiscelaneoObtenerDescripcion:" + UString.obtenerSinNulo(apiMiscelaneoObtenerDescripcion));
			res.setCodigo(DominioTransaccion.ERROR);
			//res.setDescripcion(e.getMessage());
			res.setDescripcion("Ha ocurrido un error.");
			e.printStackTrace();
		}
		return null;
	}

	public List<DtoTabla> miscelaneoListar(String aplicacion, String codigotabla, String compania) throws Exception {

		DtoTabla res = new DtoTabla();
		MiscelaneosHeaderTransaccion pTran = new MiscelaneosHeaderTransaccion();
		pTran.setAplicacioncodigo(aplicacion);
		pTran.setCompania(compania);
		pTran.setCodigotabla(codigotabla);

		try {
			if (apiMiscelaneoListar == null)
				this.leerPropiedades();

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiMiscelaneoListar);
			logger.debug(uri);

			HttpEntity<MiscelaneosHeaderTransaccion> request = new HttpEntity<MiscelaneosHeaderTransaccion>(pTran,
					this.getHeaders());

			ResponseEntity<List<DtoTabla>> result = restTemplate.exchange(uri, HttpMethod.PUT, request,
					new ParameterizedTypeReference<List<DtoTabla>>() {
					});
			return result.getBody();
		} catch (Exception e) {
			logger.error("apiMiscelaneoListar:" + UString.obtenerSinNulo(apiMiscelaneoListar));
			res.setCodigo(DominioTransaccion.ERROR);
			//res.setDescripcion(e.getMessage());
			res.setDescripcion("Ha ocurrido un error.");
			e.printStackTrace();
		}
		return null;
	}

	public DtoTabla obtenerCentroEstudioPorId(Integer centro) throws Exception {

		DtoTabla res = new DtoTabla();
		DtoTabla pTran = new DtoTabla();
		if (centro != null) {
			pTran.setId(centro);
		}

		try {
			if (apiHrCentroestudiosobtener == null)
				this.leerPropiedades();

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiHrCentroestudiosobtener);
			logger.debug(uri);

			HttpEntity<DtoTabla> request = new HttpEntity<DtoTabla>(pTran, this.getHeaders());
			ResponseEntity<DtoTabla> result = restTemplate.exchange(uri, HttpMethod.PUT, request, DtoTabla.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("apiHrCentroestudiosobtener:" + UString.obtenerSinNulo(apiHrCentroestudiosobtener));
			res.setCodigo(DominioTransaccion.ERROR);
			//res.setDescripcion(e.getMessage());
			res.setDescripcion("Ha ocurrido un error.");
			e.printStackTrace();
		}
		return null;
	}

	public DtoTabla obtenerCursodescripcion(Integer curso) throws Exception {

		DtoTabla res = new DtoTabla();
		DtoTabla pTran = new DtoTabla();
		if (curso != null) {
			pTran.setId(curso);
		}

		try {
			if (apiHrCursodescripcionobtener == null)
				this.leerPropiedades();

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiHrCursodescripcionobtener);
			logger.debug(uri);

			HttpEntity<DtoTabla> request = new HttpEntity<DtoTabla>(pTran, this.getHeaders());
			ResponseEntity<DtoTabla> result = restTemplate.exchange(uri, HttpMethod.PUT, request, DtoTabla.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("apiHrCursodescripcionobtener:" + UString.obtenerSinNulo(apiHrCursodescripcionobtener));
			res.setCodigo(DominioTransaccion.ERROR);
			//res.setDescripcion(e.getMessage());
			res.setDescripcion("Ha ocurrido un error.");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ALEJANDRO
	 * 
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public WorkFlowResultado obtenerTransaccion(WorkFlowTransaccion body) throws Exception {
		try {
			if (apiObtenerTransaccion == null)
				this.leerPropiedades();
			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiObtenerTransaccion);
			logger.debug(uri);
			HttpEntity<WorkFlowTransaccion> request = new HttpEntity<WorkFlowTransaccion>(body, this.getHeaders());
			ResponseEntity<WorkFlowResultado> result = restTemplate.exchange(uri, HttpMethod.POST, request,
					WorkFlowResultado.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("apiObtenerTransaccion:" + UString.obtenerSinNulo(apiObtenerTransaccion));
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ALEJANDRO
	 * 
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public WorkFlowResultado anularTransaccionDesdeSolicitud(WorkFlowTransaccion body) throws Exception {
		try {
			if (apiAnularTransaccionDesdeSolicitud == null)
				this.leerPropiedades();
			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiAnularTransaccionDesdeSolicitud);
			logger.debug(uri);
			HttpEntity<WorkFlowTransaccion> request = new HttpEntity<WorkFlowTransaccion>(body, this.getHeaders());
			ResponseEntity<WorkFlowResultado> result = restTemplate.exchange(uri, HttpMethod.POST, request,
					WorkFlowResultado.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error(
					"apiAnularTransaccionDesdeSolicitud:" + UString.obtenerSinNulo(apiAnularTransaccionDesdeSolicitud));
			e.printStackTrace();
		}
		return null;
	}


	public HttpHeaders getHeadersSeguro() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.add(ConstanteFiltro.USUARIO_SEGURO, usuarioSeguroKey);
		return headers;
	}

	public HttpHeaders getHeaders() {
		return getHeaders(null);
	}

	public HttpHeaders getHeaders(SeguridadUsuarioLogin token) {
		HttpHeaders headers = new HttpHeaders();
		String idtoken = request.getHeader(ConstanteFiltro.TOKEN);
		String sid = request.getHeader(ConstanteFiltro.SID);
		if (token != null && UString.esNuloVacio(idtoken)) {
			if (!UString.esNuloVacio(token.getToken())) {
				idtoken = token.getToken();
			}
		}
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.add(ConstanteFiltro.TOKEN, idtoken);
		headers.add(ConstanteFiltro.SID, sid);
		return headers;
	}

	public SeguridadUsuarioActual getUsuarioActual() {
		ObjectMapper mapper = new ObjectMapper();
		String ua = request.getHeader(ConstanteFiltro.USUARIO_ACTUAL);
		try {
			if (UString.esNuloVacio(ua)) {
				System.err.println("HEADER_USUARIO_ACTUAL es nulo, se devuelve null");
				return null;
			}
			SeguridadUsuarioActual usu = mapper.readValue(ua, SeguridadUsuarioActual.class);
			return usu;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void ErrorRegistrar(String proceso, String descripcionError) throws Exception {
		ErrorTransaccion bean = new ErrorTransaccion();
		bean.setProceso(proceso);
		bean.setDescripcionError(descripcionError);
		this.ErrorRegistrar(bean);
	}

	public void ErrorRegistrar(ErrorTransaccion bean) throws Exception {
		try {
			logger.debug("GenericoServicioImpl.ErrorRegistrar:001");
			logger.debug(apiErrorRegistrar);
			if (apiErrorRegistrar == null) {
				this.leerPropiedades();
				logger.debug("GenericoServicioImpl.ErrorRegistrar:002");
			} 			
			RegistroErroresHilo hilo = new RegistroErroresHilo(apiErrorRegistrar, bean, this.getHeadersSeguro());
			logger.debug("GenericoServicioImpl.ErrorRegistrar:003");
			hilo.start();
			logger.debug("GenericoServicioImpl.ErrorRegistrar:004");	
		} catch (Exception e) {
			logger.debug("GenericoServicioImpl.ErrorRegistrar:005");
			logger.debug(e.getMessage());
			logger.debug(UException.getStackTrace(e));
			e.printStackTrace();
		}		
	}
	
	/* EXPORTAR GENERICO WH*/
	public void exportarInformacionWh(HttpServletResponse response,DtoWhExportar dto) throws Exception {
		
		DominioExportar dtoExportar = new DominioExportar();
		dtoExportar.setFormato(dto.getTipoExpotar());
		
		
		if(dto.getPaginacion() != null) {
			if(dto.getPaginacion().getPaginacionListaResultado() != null) {
				dtoExportar.setLstDatos(dto.getPaginacion().getPaginacionListaResultado());	
			}				
		}else {
			dtoExportar.setLstDatos(dto.getLista());
		}
		
		
		dtoExportar.setArrColumnas(dto.getArrColumnas());
		dtoExportar.setTitulo(dto.getTitulo());
		dtoExportar.setArrCabeceras(dto.getArrCabecera());

		DominioArchivo obj = exportarInformacion(dtoExportar);

		InputStream inputStream;
		inputStream = new BufferedInputStream(new ByteArrayInputStream(obj.getArchivoAdjuntoBytes()));

		String CONTENT_TYPE_APPLICATION_PDF_VALUE = "";
		String HTTP_HEADER_CONTENT_DISPOSITION = "";
		String CONTENT_DISPOSITION_INLINE_FORMAT = "";

		if (dto.getTipoExpotar().equals("PDF")) {
			CONTENT_TYPE_APPLICATION_PDF_VALUE = ConstanteExportar.CONTENT_TYPE_APPLICATION_PDF_VALUE;
			HTTP_HEADER_CONTENT_DISPOSITION = ConstanteExportar.HTTP_HEADER_CONTENT_DISPOSITION;
			CONTENT_DISPOSITION_INLINE_FORMAT = ConstanteExportar.CONTENT_DISPOSITION_INLINE_FORMAT_PDF;
		} else if (dto.getTipoExpotar().equals("XLS")) {
			CONTENT_TYPE_APPLICATION_PDF_VALUE = ConstanteExportar.CONTENT_TYPE_APPLICATION_XLS_VALUE;
			HTTP_HEADER_CONTENT_DISPOSITION = ConstanteExportar.HTTP_HEADER_CONTENT_DISPOSITION;
			CONTENT_DISPOSITION_INLINE_FORMAT = ConstanteExportar.CONTENT_DISPOSITION_INLINE_FORMAT_XLS;
		} else if (dto.getTipoExpotar().equals("XML")) {
			CONTENT_TYPE_APPLICATION_PDF_VALUE = ConstanteExportar.CONTENT_TYPE_APPLICATION_XML_VALUE;
			HTTP_HEADER_CONTENT_DISPOSITION = ConstanteExportar.HTTP_HEADER_CONTENT_DISPOSITION;
			CONTENT_DISPOSITION_INLINE_FORMAT = ConstanteExportar.CONTENT_DISPOSITION_INLINE_FORMAT_XML;
		} else if (dto.getTipoExpotar().equals("JSON")) {
			CONTENT_TYPE_APPLICATION_PDF_VALUE = ConstanteExportar.CONTENT_TYPE_APPLICATION_JSON_VALUE;
			HTTP_HEADER_CONTENT_DISPOSITION = ConstanteExportar.HTTP_HEADER_CONTENT_DISPOSITION;
			CONTENT_DISPOSITION_INLINE_FORMAT = ConstanteExportar.CONTENT_DISPOSITION_INLINE_FORMAT_JSON;
		}

		response.setContentType(CONTENT_TYPE_APPLICATION_PDF_VALUE);
		response.setHeader(HTTP_HEADER_CONTENT_DISPOSITION,String.format(CONTENT_DISPOSITION_INLINE_FORMAT, UUID.randomUUID()));
		response.setContentLength(obj.getArchivoAdjuntoBytes().length);

		inputStream = new BufferedInputStream(new ByteArrayInputStream(obj.getArchivoAdjuntoBytes()));
		StreamUtils.copy(inputStream, response.getOutputStream());
		
		Path archivo = getPath(obj.getRutaCompletaArchivo());
		File archivoEliminar = archivo.toFile();
		if(archivoEliminar.exists() && archivoEliminar.canRead()) {
			archivoEliminar.delete();
		}
		
		response.flushBuffer();
	}
	
	public Path getPath(String url) {
		return Paths.get(url).toAbsolutePath();
	}
	
	/** TIPO DE CAMBIO: INICIO **/
	public void terminarSubasta(String actualizadas) {
		if (UString.estaVacio(actualizadas)) {
			return;
		}
		if (actualizadas.length() < 5) {
			return;
		}
		if (apiSubastaCorreoGestor == null) {
			try {
				this.leerPropiedades();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (apiSubastaCorreoGestor == null) {
			return;
		}
		HttpHeaders httpHeaders = this.getHeadersSeguro();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					DtoTabla res = new DtoTabla();
					res.setDescripcion(actualizadas);
					RestTemplate restTemplate = new RestTemplate();
					URI uri = new URI(apiSubastaCorreoGestor);
					HttpEntity<DtoTabla> request = new HttpEntity<DtoTabla>(res, httpHeaders);
					restTemplate.exchange(uri, HttpMethod.POST, request, DtoTabla.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}
