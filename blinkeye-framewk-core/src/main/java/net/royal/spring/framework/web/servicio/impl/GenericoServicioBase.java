package net.royal.spring.framework.web.servicio.impl;

import java.io.File;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import net.royal.spring.framework.core.UWebServer;
import net.royal.spring.framework.modelo.ParametroTransaccion;
import net.royal.spring.framework.modelo.TipoCambioTransaccion;
import net.royal.spring.framework.modelo.WorkFlowResultado;
import net.royal.spring.framework.modelo.WorkFlowTransaccion;
import net.royal.spring.framework.modelo.correo.EmailTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioLogin;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UPropiedades;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.constante.ConstanteFiltro;

public class GenericoServicioBase {
	private static Logger logger = LogManager.getLogger(GenericoServicioBase.class);

	@Autowired
	public ApplicationContext appContext;

	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	private MessageSource messageSource;

	public String getMessage(String msgCode) {
		Locale locale = LocaleContextHolder.getLocale();
		return getMessageBase(msgCode, null, null, locale);
	}

	public String getMessage(String msgCode, String defaultMsg) {
		Locale locale = LocaleContextHolder.getLocale();
		return getMessageBase(msgCode, null, defaultMsg, locale);
	}

	private String getMessageBase(String msgCode, Object[] params, String defaultMsg, Locale locale) {
		try {
			if (locale == null)
				locale = LocaleContextHolder.getLocale();
			//messageSource.
			defaultMsg = UString.esNuloVacio(defaultMsg) ? msgCode : defaultMsg;
			String msg = messageSource.getMessage(msgCode, params, defaultMsg, locale);
			return UString.esNuloVacio(msg) ? msgCode : msg;
		} catch (Exception e) {
			logger.error(msgCode);
		}
		return msgCode;
	}

	public DominioMensajeUsuario getMsjUsuarioError(String msgCode) {
		String msg = getMessage(msgCode);
		DominioMensajeUsuario msj = new DominioMensajeUsuario(tipo_mensaje.ERROR, msg);
		return msj;
	}
	
	public DominioMensajeUsuario getMsjUsuarioCampoRequerido(String msgCode) {
		String s1 = getMessage("campo.requerido");
		String s2 = getMessage(msgCode);
		DominioMensajeUsuario msj = new DominioMensajeUsuario(tipo_mensaje.ERROR, String.format(s1,s2));
		return msj;
	}

	public DominioMensajeUsuario getMsjUsuarioCampoValido(String msgCode) {
		String s1 = getMessage("campo.valido");
		String s2 = getMessage(msgCode);
		DominioMensajeUsuario msj = new DominioMensajeUsuario(tipo_mensaje.ERROR, String.format(s1,s2));
		return msj;
	}

	public DominioMensajeUsuario getMsjUsuarioError(ConstraintViolation cons) {
		String msg = cons.getRootBeanClass().getSimpleName();
		msg = msg + "." + cons.getPropertyPath().toString();
		msg = msg + cons.getMessageTemplate().replace("{javax.validation", "").replace(".message}", "");
		msg = getMessage(msg.toLowerCase());
		DominioMensajeUsuario msj = new DominioMensajeUsuario(tipo_mensaje.ERROR, msg);
		return msj;
	}
	
	public List<DominioMensajeUsuario> setMessageError(List<DominioMensajeUsuario> lst,String msg) {
		if (lst==null)
			lst=new ArrayList<>();
		lst.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, msg));
		return lst;
	}
	
	/** CORREO : INICIO **/
	public EmailTransaccion correoRegistrar(EmailTransaccion bean) throws Exception {
		String apiAlertasCorreo = null;
		try {			
			logger.debug(apiAplicacionAlertas);
			
			if (apiAplicacionAlertas == null)
				leerPropiedades();
			
			logger.debug(apiAplicacionAlertas);
			
			apiAlertasCorreo = apiAplicacionAlertas + "spring/alertas/correo/registrarexterno";
			
			logger.debug(apiAlertasCorreo);
			
			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiAlertasCorreo);
			logger.debug(uri);

			HttpEntity<EmailTransaccion> request = new HttpEntity<EmailTransaccion>(bean, this.getHeadersSeguro());
			ResponseEntity<EmailTransaccion> result = restTemplate.exchange(uri, HttpMethod.POST, request,
					EmailTransaccion.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("apiAlertasCorreo:" + UString.obtenerSinNulo(apiAlertasCorreo));
			logger.error("API ENVIO CORREO ERROR :" + e.getMessage());
			logger.error("API ENVIO CORREO ERROR :" + bean.getAsunto());
			bean.setTransaccionEstado(DominioTransaccion.ERROR);
			bean.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return bean;
	} 
	/** CORREO : FIN **/

	/** TIPO DE CAMBIO: INICIO **/
	public TipoCambioTransaccion tipoCambioObtener(TipoCambioTransaccion bean) throws Exception {
		TipoCambioTransaccion res = new TipoCambioTransaccion();
		try {
			if (apiTipoCambioObtener == null)
				leerPropiedades();

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
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return res;
	}

	/** TIPO DE CAMBIO: FIN **/

	/** WORKFLOW : INICIO **/
	public WorkFlowResultado workflowRegistrar(WorkFlowTransaccion bean) throws Exception {
		WorkFlowResultado res = new WorkFlowResultado();
		try {
			if (apiWorkFlowTransaccionIniciar == null)
				leerPropiedades();

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiWorkFlowTransaccionIniciar);
			logger.debug(uri);

			HttpEntity<WorkFlowTransaccion> request = new HttpEntity<WorkFlowTransaccion>(bean, this.getHeaders());
			ResponseEntity<WorkFlowResultado> result = restTemplate.exchange(uri, HttpMethod.POST, request,
					WorkFlowResultado.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("apiWorkFlowExterno:" + UString.obtenerSinNulo(apiWorkFlowTransaccionIniciar));
			res.setEstado(DominioTransaccion.ERROR);
			res.setMensaje("Ocurrio un error.");
			e.printStackTrace();
		}
		return res;
	}
	/** WORKFLOW : FIN **/
	
	/** PARAMETROS : INICIO **/
	public BigDecimal parametroObtenerNumero(ParametroTransaccion bean) throws Exception {
		bean = parametroObtener(bean);
		return bean.getNumero();
	}

	public Date parametroObtenerFecha(ParametroTransaccion bean) throws Exception {
		bean = parametroObtener(bean);
		return bean.getFecha();
	}

	public String parametroObtenerTexto(ParametroTransaccion bean) throws Exception {
		bean = parametroObtener(bean);
		return bean.getTexto();
	}

	public String parametroObtenerExplicacion(ParametroTransaccion bean) throws Exception {
		bean = parametroObtener(bean);
		return bean.getExplicacion();
	}
	
	public String parametroObtenerDescripcion(ParametroTransaccion bean) throws Exception {
		bean = parametroObtener(bean);
		return bean.getDescripcionparametro();
	}

	public ParametroTransaccion parametroObtener(ParametroTransaccion bean) throws Exception {
		ParametroTransaccion res = new ParametroTransaccion();
		try {
			if (apiParametroObtener == null)
				leerPropiedades();

			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(apiParametroObtener);
			logger.debug(uri);

			HttpEntity<ParametroTransaccion> request = new HttpEntity<ParametroTransaccion>(bean, this.getHeaders());
			ResponseEntity<ParametroTransaccion> result = restTemplate.exchange(uri, HttpMethod.PUT, request,
					ParametroTransaccion.class);
			return result.getBody();
		} catch (Exception e) {
			logger.error("apiParametroObtener:" + UString.obtenerSinNulo(apiParametroObtener));
			res.setTransaccionEstado(TipoCambioTransaccion.ERROR);
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error."));
			e.printStackTrace();
		}
		return res;
	}
	
	public String parametroObtenerExplicacion(String aplicacioncodigo, String parametroclave) throws Exception {
		ParametroTransaccion bean = new ParametroTransaccion(null, aplicacioncodigo, parametroclave);
		bean = parametroObtener(bean);
		return bean.getExplicacion();
	}
	public BigDecimal parametroObtenerNumero(String aplicacioncodigo, String parametroclave) throws Exception {
		ParametroTransaccion bean = new ParametroTransaccion(null, aplicacioncodigo, parametroclave);
		bean = parametroObtener(bean);
		return bean.getNumero();		
	}
	public Integer parametroObtenerNumeroEntero(String aplicacioncodigo, String parametroclave) throws Exception {
		ParametroTransaccion bean = new ParametroTransaccion(null, aplicacioncodigo, parametroclave);
		bean = parametroObtener(bean);
		return UBigDecimal.obtenerValorSinNulo(bean.getNumero()).intValue();		
	}
	public String parametroObtenerTexto(String aplicacioncodigo, String parametroclave) throws Exception {
		ParametroTransaccion bean = new ParametroTransaccion(null, aplicacioncodigo, parametroclave);
		bean = parametroObtener(bean);
		return bean.getTexto();
	}
	/**/

	public String parametroObtenerExplicacion(String aplicacioncodigo, String parametroclave, String companiacodigo)
			throws Exception {
		ParametroTransaccion bean = new ParametroTransaccion(companiacodigo, aplicacioncodigo, parametroclave);
		bean = parametroObtener(bean);
		return bean.getExplicacion();
	}
	public BigDecimal parametroObtenerNumero(String aplicacioncodigo, String parametroclave, String companiacodigo)
			throws Exception {
		ParametroTransaccion bean = new ParametroTransaccion(companiacodigo, aplicacioncodigo, parametroclave);
		bean = parametroObtener(bean);
		return bean.getNumero();
	}
	public Integer parametroObtenerNumeroEntero(String aplicacioncodigo, String parametroclave, String companiacodigo)
			throws Exception {
		ParametroTransaccion bean = new ParametroTransaccion(companiacodigo, aplicacioncodigo, parametroclave);
		bean = parametroObtener(bean);
		return UBigDecimal.obtenerValorSinNulo(bean.getNumero()).intValue();
	}
	public String parametroObtenerTexto(String aplicacioncodigo, String parametroclave,String companiacodigo) throws Exception {
		ParametroTransaccion bean = new ParametroTransaccion(companiacodigo, aplicacioncodigo, parametroclave);
		bean = parametroObtener(bean);
		
		if(!UString.esNuloVacio(bean.getTexto())) {
			bean.setTexto(bean.getTexto().trim());
		}
		
		return bean.getTexto();
	}
	/** PARAMETROS: FIN **/

	public HttpHeaders getHeaders() {
		return getHeaders(null);
	}

	public HttpHeaders getHeadersSeguro() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.add(ConstanteFiltro.USUARIO_SEGURO, usuarioSeguroKey);
		return headers;
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
	
	protected void leerPropiedades() throws Exception {
		String rutaPropiedades = UWebServer.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;
		propiedades = UPropiedades.getInstance().abrir(rutaPropiedades, ConstanteBoot.PROPERTIES_GLOBAL);
		if (propiedades != null) {
			usuarioSeguroKey = UString.obtenerSinNulo(propiedades.getProperty("seguridad.usuarioseguro"));
			serverNombre = UString.obtenerSinNulo(propiedades.getProperty("server.nombre"));
			
			apiTipoCambioObtener = UString.obtenerSinNulo(propiedades.getProperty("tipocambio.obtener"));
			
			apiWorkFlow = UString.obtenerSinNulo(propiedades.getProperty("proxy.framework.workflow"));
			apiAplicacionAlertas = UString.obtenerSinNulo(propiedades.getProperty("proxy.erp.alertas"));

			apiWorkFlowTransaccionIniciar = UString.obtenerSinNulo(propiedades.getProperty("workflow.transaccion.iniciar"));
			apiWorkFlowTransaccionSeguimiento= UString.obtenerSinNulo(propiedades.getProperty("workflow.transaccion.seguimiento"));
			apiWorkFlowTransaccionObtener= UString.obtenerSinNulo(propiedades.getProperty("workflow.transaccion.obtener"));
			comunProcesarAdjuntos=UString.obtenerSinNulo(propiedades.getProperty("comun.procesarAdjuntos"));
			
			// apiComunParametro =
			// UString.obtenerSinNulo(propiedades.getProperty("comun.service.parametros"));
			apiCorreoEnviar = UString.obtenerSinNulo(propiedades.getProperty("correo.enviar"));
			apiReporteEjecutar = UString.obtenerSinNulo(propiedades.getProperty("reporte.ejecutar"));
			apiReporteEjecutarCadena = UString.obtenerSinNulo(propiedades.getProperty("reporte.ejecutarcadena"));
			rutaTemporalFisica = UString.obtenerSinNulo(propiedades.getProperty("ruta.fisica.temporal"));
			imagenReportes = UString.obtenerSinNulo(propiedades.getProperty("ruta.imagen.logoexportar"));
			apiParametroObtener = UString.obtenerSinNulo(propiedades.getProperty("parametro.obtener"));
			apiPersonaObtenerDatos = UString.obtenerSinNulo(propiedades.getProperty("persona.obtenerdatos"));
			apiEmpleadoObtenerDatos = UString.obtenerSinNulo(propiedades.getProperty("empleado.obtenerdatos"));
			apiCompanyownerObtenerRecurso = UString.obtenerSinNulo(propiedades.getProperty("companyowner.obtenerecurso"));

			apiCompaniaObtenerNombre = UString.obtenerSinNulo(propiedades.getProperty("compania.obtenertabla"));
			apiMiscelaneoObtenerDescripcion = UString.obtenerSinNulo(propiedades.getProperty("miscelaneo.obtenerdescripcion"));

			apiObtenerTransaccion = UString.obtenerSinNulo(propiedades.getProperty("workflow.obtenertransaccion"));
			apiAnularTransaccionDesdeSolicitud = UString.obtenerSinNulo(propiedades.getProperty("workflow.anulartransacciondesdesolicitud"));
			apiMiscelaneoListar = UString.obtenerSinNulo(propiedades.getProperty("miscelaneo.listar"));
			apiHrCentroestudiosobtener = UString.obtenerSinNulo(propiedades.getProperty("centroestudios.obtener"));
			apiHrCursodescripcionobtener = UString.obtenerSinNulo(propiedades.getProperty("cursodescripcion.obtener"));
			apiErrorRegistrar = UString.obtenerSinNulo(propiedades.getProperty("error.registrar"));
			apiSubastaCorreoGestor = UString.obtenerSinNulo(propiedades.getProperty("subastas.enviarcorreogestor"));
			comunActualizarTemporal = UString.obtenerSinNulo(propiedades.getProperty("comun.actualizarTemporal"));
		}
	}
	
	protected static Properties propiedades;
	protected static String apiWorkFlow;
	protected static String apiAplicacionAlertas;

	protected static String serverNombre;
	protected static String apiParametroObtener;
	protected static String apiTipoCambioObtener;
	protected static String apiWorkFlowTransaccionIniciar;
	protected static String apiWorkFlowTransaccionSeguimiento;
	protected static String apiWorkFlowTransaccionObtener;
	protected static String comunProcesarAdjuntos;
	
	protected static String apiCorreoEnviar;
	protected static String apiReporteEjecutar;
	protected static String apiReporteEjecutarCadena;
	protected static String apiPersonaObtenerDatos;
	protected static String apiCompanyownerObtenerRecurso;
	protected static String apiEmpleadoObtenerDatos;
	protected static String apiCompaniaObtenerNombre;
	protected static String apiMiscelaneoObtenerDescripcion;
	protected static String apiObtenerTransaccion;
	protected static String apiAnularTransaccionDesdeSolicitud;
	protected static String apiSubastaCorreoGestor;
	protected static String comunActualizarTemporal;
	protected static String rutaTemporalFisica;
	protected static String imagenReportes;
	protected static String apiMiscelaneoListar;
	protected static String apiHrCentroestudiosobtener;
	protected static String apiHrCursodescripcionobtener;
	protected static String apiErrorRegistrar;
	protected static String usuarioSeguroKey;
}
