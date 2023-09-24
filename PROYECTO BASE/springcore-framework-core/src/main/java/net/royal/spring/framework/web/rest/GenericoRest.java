package net.royal.spring.framework.web.rest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.core.UExceptionSessionFinished;
import net.royal.spring.framework.core.UWebServer;
import net.royal.spring.framework.modelo.ErrorTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UPropiedades;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.constante.ConstanteFiltro;
import net.royal.spring.framework.web.hilo.RegistroErroresHilo;

public class GenericoRest {
	private static Logger logger = LogManager.getLogger(GenericoRest.class);
	public static final String CONTENT_TYPE_APPLICATION_PDF_VALUE = "application/pdf";
	public static final String HTTP_HEADER_CONTENT_DISPOSITION = "Content-Disposition";
	public static final String CONTENT_DISPOSITION_INLINE_FORMAT = "inline; filename=\"%s.pdf\"";
	public static final String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private MessageSource messageSource;

	public SeguridadUsuarioActual getUsuarioActual() {
		ObjectMapper mapper = new ObjectMapper();
		String ua = request.getHeader(ConstanteFiltro.USUARIO_ACTUAL);
		if (UString.esNuloVacio(ua))
			return null;
		try {
			ObjectMapper mapper2 = new ObjectMapper();
			SeguridadUsuarioActual usu = mapper2.readValue(ua, SeguridadUsuarioActual.class);
			return usu;
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	@ExceptionHandler({ UException.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		List<DominioMensajeUsuario> apiError = new ArrayList<DominioMensajeUsuario>();
		try {
			logger.debug("1");
			logger.debug(ex.getMessage());
			ex.printStackTrace();
			apiError.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "ERROR", "ERROR"));
			apiError.get(0).setFuente(UException.getStackTrace(ex));
			registrarError(null, UException.getStackTrace(ex), UException.getClassName(ex));
			//Alejandro, ocultar el trace
			for (DominioMensajeUsuario dominioMensajeUsuario : apiError) {
				dominioMensajeUsuario.setFuente(null);
				dominioMensajeUsuario.setTitulo(null);			
				dominioMensajeUsuario.setMensaje(null);
			}	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAllExcepcions(Exception ex, WebRequest request) {
		List<DominioMensajeUsuario> apiError = new ArrayList<DominioMensajeUsuario>();
		try {
			//logger.debug("2");
			//logger.debug(ex.getMessage());
			ex.printStackTrace();
			apiError.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "ERROR", "ERROR"));
			apiError.get(0).setFuente(UException.getStackTrace(ex));
			registrarError(null, UException.getStackTrace(ex), UException.getClassName(ex));
			//Alejandro, ocultar el trace
			for (DominioMensajeUsuario dominioMensajeUsuario : apiError) {
				dominioMensajeUsuario.setFuente(null);
				dominioMensajeUsuario.setTitulo(null);			
				dominioMensajeUsuario.setMensaje(null);
			}	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({ UExceptionSessionFinished.class })
	public ResponseEntity<Object> handleSesionFinished(Exception ex, WebRequest request) {
		List<DominioMensajeUsuario> apiError = new ArrayList<DominioMensajeUsuario>();
		try {
			logger.debug("3");
			logger.debug(ex.getMessage());
			ex.printStackTrace();
			apiError.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "ERROR", "ERROR"));
			apiError.get(0).setFuente(UException.getStackTrace(ex));
			registrarError(null, UException.getStackTrace(ex), UException.getClassName(ex));
			//Alejandro, ocultar el trace
			for (DominioMensajeUsuario dominioMensajeUsuario : apiError) {
				dominioMensajeUsuario.setFuente(null);
				dominioMensajeUsuario.setTitulo(null);			
				dominioMensajeUsuario.setMensaje(null);
			}	
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.PROXY_AUTHENTICATION_REQUIRED);
	}
	
	public void registrarError(String proceso, String descripcionError) {
		ErrorTransaccion bean = new ErrorTransaccion();
		bean.setProceso(proceso);
		bean.setDescripcionError(descripcionError);
		this.registrarError(bean);
	}
	
	public void registrarError(String proceso, String descripcionError, String classname) {
		ErrorTransaccion bean = new ErrorTransaccion();
		bean.setProceso(proceso);
		bean.setDescripcionError(descripcionError);
		bean.setClassName(classname);
		//this.registrarError(bean); 
	}

	public void registrarError(ErrorTransaccion bean) {
		try {
			if (UString.esNuloVacio(usuarioSeguroKey))
				leerPropiedades();
			String rutaPropiedades = UWebServer.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;
			Properties propiedades;
			propiedades = UPropiedades.getInstance().abrir(rutaPropiedades, ConstanteBoot.PROPERTIES_GLOBAL);
			String apiErrorRegistrar = UString.obtenerSinNulo(propiedades.getProperty("servicio.spring.comun.error.registrar"));
			RegistroErroresHilo hilo = new RegistroErroresHilo(apiErrorRegistrar, bean, this.getHeadersSeguro());
			hilo.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		String idtoken = request.getHeader(ConstanteFiltro.TOKEN);
		String sid = request.getHeader(ConstanteFiltro.SID);
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.add(ConstanteFiltro.TOKEN, idtoken);
		headers.add(ConstanteFiltro.SID, sid);
		return headers;
	}
	public HttpHeaders getHeadersSeguro() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.add(ConstanteFiltro.USUARIO_SEGURO, usuarioSeguroKey);
		return headers;
	}
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
			// messageSource.
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

	public DominioMensajeUsuario getMsjUsuarioError(ConstraintViolation cons) {
		String msg = cons.getRootBeanClass().getSimpleName();
		msg = msg + "." + cons.getPropertyPath().toString();
		msg = msg + cons.getMessageTemplate().replace("{javax.validation", "").replace(".message}", "");
		msg = getMessage(msg.toLowerCase());
		DominioMensajeUsuario msj = new DominioMensajeUsuario(tipo_mensaje.ERROR, msg);
		return msj;
	}

	public List<DominioMensajeUsuario> setMessageError(List<DominioMensajeUsuario> lst, String msg) {
		if (lst == null)
			lst = new ArrayList<>();
		lst.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, msg));
		return lst;
	}
	
	public String proxyUsar;
	public String proxyNombre;
	public int proxyPuerto;
	public String usuarioSeguroKey;
	public void leerPropiedades() {
		String recursoRuta = UWebServer.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;
		Properties recursoPropiedad;
		try {
			recursoPropiedad = UPropiedades.getInstance().abrir(recursoRuta,	ConstanteBoot.PROPERTIES_GLOBAL);
			proxyUsar = recursoPropiedad.getProperty("proxy.usar");
			proxyNombre = recursoPropiedad.getProperty("proxy.nombre");
			String proPue = recursoPropiedad.getProperty("proxy.puerto");
			proxyPuerto = UInteger.obtenerValorCadena(proPue);
			usuarioSeguroKey = UString.obtenerSinNulo(recursoPropiedad.getProperty("seguridad.usuarioseguro"));
			if (UString.esNuloVacio(proxyUsar))
				proxyUsar="N";
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
