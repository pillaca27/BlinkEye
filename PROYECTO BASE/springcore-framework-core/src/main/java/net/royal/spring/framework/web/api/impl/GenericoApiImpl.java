package net.royal.spring.framework.web.api.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.core.UExceptionSessionFinished;
import net.royal.spring.framework.core.UWebServer;
import net.royal.spring.framework.modelo.ErrorTransaccion;
import net.royal.spring.framework.modelo.MiscelaneosHeaderTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioLogin;
import net.royal.spring.framework.util.UPropiedades;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.constante.ConstanteFiltro;
import net.royal.spring.framework.web.hilo.RegistroErroresHilo;

public abstract class GenericoApiImpl<T> {
	private static Logger logger = LogManager.getLogger(GenericoApiImpl.class);
	
	@Autowired
	private HttpServletRequest reqclass;
	
	@Autowired
	private HttpServletRequest request;
	
	protected static String proxySeguridad = "proxy.framework.seguridad";
	protected static String proxyComun = "proxy.framework.comun";
	protected static String proxyWorkflow = "proxy.framework.workflow";
	
	protected static String usuarioSeguroKey;
	
	private static Properties propiedades;
	private String apiRaiz;
	private String apiComponente;
	protected String apiUrl;
	protected ParameterizedTypeReference<List<T>> listaTipo;
	
	/*public GenericoApiImpl(String api,String apiComponente) throws Exception {
		this.apiComponente=apiComponente;
		leerPropiedades(api);
	}*/
	public GenericoApiImpl(String api,String apiComponente,ParameterizedTypeReference<List<T>> listaTipo) throws Exception {
		this.listaTipo=listaTipo;
		this.apiComponente=apiComponente;
		leerPropiedades(api);
	}
	
	protected void leerPropiedades(String api) throws Exception {
		String rutaPropiedades = UWebServer.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;
		//logger.debug(rutaPropiedades);
		propiedades = UPropiedades.getInstance().abrir(rutaPropiedades, ConstanteBoot.PROPERTIES_GLOBAL);
		//logger.debug(propiedades);
		if (propiedades != null) {
			apiRaiz = UString.obtenerSinNulo(propiedades.getProperty(api));
			apiUrl = apiRaiz + apiComponente;
			usuarioSeguroKey = UString.obtenerSinNulo(propiedades.getProperty("seguridad.usuarioseguro"));
		}
	}
	protected void leerPropiedadesTodas() throws Exception {
		String rutaPropiedades = UWebServer.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;
		logger.debug(rutaPropiedades);
		propiedades = UPropiedades.getInstance().abrir(rutaPropiedades, ConstanteBoot.PROPERTIES_GLOBAL);
		logger.debug(propiedades);
		if (propiedades != null) {
			usuarioSeguroKey = UString.obtenerSinNulo(propiedades.getProperty("seguridad.usuarioseguro"));
		}
	}
	
	
	/*public T getObtenerPorId(String metodo,T entity) {
		entity = this.get(metodo, entity);
		if (!((DominioTransaccion)entity).getTransaccionEstado().equals(DominioTransaccion.OK)) {
			return null;
		}
		return entity;
	}*/
	
	public List<DtoTabla> getToListaDtoTabla(String metodo) {
		try {			
			URI uri = new URI(apiUrl + "/" + metodo);
			logger.debug("GenericoApiImpl.getToListaDtoTabla :" + uri);			
			RestTemplate restTemplate = new RestTemplate();			
			HttpEntity<T> reqlocal = new HttpEntity<T>(this.getHeaders());			
			logger.debug("GenericoApiImpl.getToListaDtoTabla : antes del llamado");
			ResponseEntity<List<DtoTabla>> result = restTemplate.exchange(uri, HttpMethod.GET, reqlocal,new ParameterizedTypeReference<List<DtoTabla>>() {});
			logger.debug("GenericoApiImpl.getToListaDtoTabla : despues del llamado");
			return result.getBody();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected T get(String metodo,T entity) throws URISyntaxException {
			Class claselocal = entity.getClass();						
			URI uri = new URI(apiUrl + "/" + metodo);
			RestTemplate restTemplate = new RestTemplate();			
			HttpEntity<T> reqlocal = new HttpEntity<T>(this.obtenerHeaders());			
			ResponseEntity<T> result = restTemplate.exchange(uri, HttpMethod.GET, reqlocal,claselocal);
			entity = result.getBody();			
		
		return entity;
	}
	protected List<T> getToLista(String metodo) {
		try {			
			URI uri = new URI(apiUrl + "/" + metodo);
			logger.debug("GenericoApiImpl.get :" + uri);			
			RestTemplate restTemplate = new RestTemplate();			
			HttpEntity<T> reqlocal = new HttpEntity<T>(this.obtenerHeaders());			
			logger.debug("GenericoApiImpl.get : antes del llamado");
			ResponseEntity<List<T>> result = restTemplate.exchange(uri, HttpMethod.GET, reqlocal,listaTipo);
			logger.debug("GenericoApiImpl.get : despues del llamado");
			return result.getBody();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	protected List<DtoTabla> getToListaTabla(String metodo) {
		try {			
			URI uri = new URI(apiUrl + "/" + metodo);
			//logger.debug("GenericoApiImpl.getToListaDtoTabla :" + uri);			
			RestTemplate restTemplate = new RestTemplate();			
			HttpEntity<T> reqlocal = new HttpEntity<T>(this.obtenerHeaders());			
			//logger.debug("GenericoApiImpl.getToListaDtoTabla : antes del llamado");
			ResponseEntity<List<DtoTabla>> result = restTemplate.exchange(uri, HttpMethod.GET, reqlocal,new ParameterizedTypeReference<List<DtoTabla>>() {});
			//logger.debug("GenericoApiImpl.getToListaDtoTabla : despues del llamado");
			return result.getBody();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected T putObtenerPorId(String metodo,T entity) {
		entity = this.put(metodo, entity);
		if (!((DominioTransaccion)entity).getTransaccionEstado().equals(DominioTransaccion.OK)) {
			return null;
		}
		return entity;
	}
	
	public List<T> get(String metodo) {
		try {			
			URI uri = new URI(apiUrl + "/" + metodo);
			logger.debug("GenericoApiImpl.get :" + uri);			
			RestTemplate restTemplate = new RestTemplate();			
			HttpEntity<T> reqlocal = new HttpEntity<T>(this.getHeaders());			
			logger.debug("GenericoApiImpl.get : antes del llamado");
			ResponseEntity<List<T>> result = restTemplate.exchange(uri, HttpMethod.GET, reqlocal,listaTipo);
			logger.debug("GenericoApiImpl.get : despues del llamado");
			return result.getBody();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	protected T put(String metodo,T entity) {
		try {
			Class claselocal = entity.getClass();						
			URI uri = new URI(apiUrl + "/" + metodo);
//			logger.debug("GenericoApiImpl.put :" + uri);			
			RestTemplate restTemplate = new RestTemplate();			
			HttpEntity<T> reqlocal = new HttpEntity<T>(entity,this.obtenerHeaders());			
//			logger.debug("GenericoApiImpl.put : antes del llamado");
			ResponseEntity<T> result = restTemplate.exchange(uri, HttpMethod.PUT, reqlocal,claselocal);
//			logger.debug("GenericoApiImpl.put : despues del llamado");
			entity = result.getBody();			
		} catch (Exception e) {
			((DominioTransaccion)entity).setTransaccionEstado(DominioTransaccion.ERROR);			
			//((DominioTransaccion)entity).getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
			((DominioTransaccion)entity).getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error"));
			e.printStackTrace();
		}
		return entity;
	}
	
	protected T putSeguro(String metodo,T entity) {
		try {
			Class claselocal = entity.getClass();						
			URI uri = new URI(apiUrl + "/" + metodo);
//			logger.debug("GenericoApiImpl.put :" + uri);			
			RestTemplate restTemplate = new RestTemplate();			
			HttpEntity<T> reqlocal = new HttpEntity<T>(entity,this.getHeadersSeguro());			
//			logger.debug("GenericoApiImpl.put : antes del llamado");
			ResponseEntity<T> result = restTemplate.exchange(uri, HttpMethod.PUT, reqlocal,claselocal);
//			logger.debug("GenericoApiImpl.put : despues del llamado");
			entity = result.getBody();			
		} catch (Exception e) {
			((DominioTransaccion)entity).setTransaccionEstado(DominioTransaccion.ERROR);			
			//((DominioTransaccion)entity).getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
			((DominioTransaccion)entity).getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error"));
			e.printStackTrace();
		}
		return entity;
	}
	
	protected Object putBeanToBean(String metodo,Object entity) {
		try {
			Class claselocal = entity.getClass();						
			URI uri = new URI(apiUrl + "/" + metodo);
			RestTemplate restTemplate = new RestTemplate();			
			HttpEntity<Object> reqlocal = new HttpEntity<Object>(entity,this.obtenerHeaders());			
			ResponseEntity<?> result = restTemplate.exchange(uri, HttpMethod.PUT, reqlocal,claselocal);
			entity = result.getBody();			
		} catch (Exception e) {
			e.printStackTrace();
			((DominioTransaccion)entity).setTransaccionEstado(DominioTransaccion.ERROR);			
			//((DominioTransaccion)entity).getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
			((DominioTransaccion)entity).getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error"));
		}
		return entity;
	}
	protected Object putBeanToBean(String metodo,Object entity,Class classSalida) {
		try {
			URI uri = new URI(apiUrl + "/" + metodo);
			RestTemplate restTemplate = new RestTemplate();			
			HttpEntity<Object> reqlocal = new HttpEntity<Object>(entity,this.obtenerHeaders());			
			ResponseEntity<?> result = restTemplate.exchange(uri, HttpMethod.PUT, reqlocal,classSalida);
			return result.getBody();			
		} catch (Exception e) {
			e.printStackTrace();
			((DominioTransaccion)entity).setTransaccionEstado(DominioTransaccion.ERROR);			
			//((DominioTransaccion)entity).getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
			((DominioTransaccion)entity).getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error"));
		}
		return null;
	}
	protected List<DtoTabla> putBeanToListaTabla(String metodo,T entity) {
		try {			
			URI uri = new URI(apiUrl + "/" + metodo);
			RestTemplate restTemplate = new RestTemplate();			
			HttpEntity<T> reqlocal = new HttpEntity<T>(entity,this.obtenerHeaders());			
			ResponseEntity<List<DtoTabla>> result = restTemplate.exchange(uri, HttpMethod.PUT, reqlocal,new ParameterizedTypeReference<List<DtoTabla>>() {});
			return result.getBody();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	protected String putToString(String metodo,T entity) {
		try {
					
			URI uri = new URI(apiUrl + "/" + metodo);
			RestTemplate restTemplate = new RestTemplate();			
			HttpEntity<T> reqlocal = new HttpEntity<T>(entity,this.obtenerHeaders());			
			ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.PUT, reqlocal,String.class);
			return result.getBody();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	protected BigDecimal putToBigDecimal(String metodo,T entity) {
		try {
					
			URI uri = new URI(apiUrl + "/" + metodo);
			logger.debug("GenericoApiImpl.putToString :" + uri);			
			RestTemplate restTemplate = new RestTemplate();			
			HttpEntity<T> reqlocal = new HttpEntity<T>(entity,this.obtenerHeaders());			
			logger.debug("GenericoApiImpl.putToString : antes del llamado");
			ResponseEntity<BigDecimal> result = restTemplate.exchange(uri, HttpMethod.PUT, reqlocal,BigDecimal.class);
			logger.debug("GenericoApiImpl.putToString : despues del llamado");
			return result.getBody();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected List<T> putToLista(String metodo,T entity) {
		logger.debug(metodo + " : inicio");
		try {			
			URI uri = new URI(apiUrl + "/" + metodo);
			logger.debug(metodo + " :" + uri);			
			RestTemplate restTemplate = new RestTemplate();			
			HttpEntity<T> reqlocal = new HttpEntity<T>(entity,this.obtenerHeaders());			
			logger.debug(metodo + " : antes del llamado");
			ResponseEntity<List<T>> result = restTemplate.exchange(uri, HttpMethod.PUT, reqlocal,listaTipo);
			logger.debug(metodo + " : despues del llamado");
			logger.debug(result.getBody().size());
			return result.getBody();
		} catch (Exception e) {
			logger.debug(metodo + " : (ERROR) = " + e.getMessage());
			logger.debug(e.getMessage());
			e.printStackTrace();
		}
		logger.debug(metodo + " : fin");
		return null;	
	}
	
	protected DtoTabla putTablaToTabla(String metodo,DtoTabla dto) {
		try {
					
			URI uri = new URI(apiUrl + "/" + metodo);
			logger.debug("GenericoApiImpl.putTablaToTabla :" + uri);			
			RestTemplate restTemplate = new RestTemplate();			
			HttpEntity<DtoTabla> reqlocal = new HttpEntity<DtoTabla>(dto,this.obtenerHeaders());			
			logger.debug("GenericoApiImpl.putTablaToTabla : antes del llamado");
			ResponseEntity<DtoTabla> result = restTemplate.exchange(uri, HttpMethod.PUT, reqlocal,DtoTabla.class);
			logger.debug("GenericoApiImpl.putTablaToTabla : despues del llamado");
			return result.getBody();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	protected List<DtoTabla> putTablaToListaTabla(String metodo,DtoTabla dto) {
		try {			
			URI uri = new URI(apiUrl + "/" + metodo);
			logger.debug("GenericoApiImpl.putTablaToListaTabla :" + uri);			
			RestTemplate restTemplate = new RestTemplate();			
			HttpEntity<DtoTabla> reqlocal = new HttpEntity<DtoTabla>(dto,this.obtenerHeaders());			
			logger.debug("GenericoApiImpl.putTablaToListaTabla : antes del llamado");
			ResponseEntity<List<DtoTabla>> result = restTemplate.exchange(uri, HttpMethod.PUT, reqlocal,new ParameterizedTypeReference<List<DtoTabla>>() {});
			logger.debug("GenericoApiImpl.putTablaToListaTabla : despues del llamado");
			return result.getBody();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
	protected Object postBeanToBean(String metodo,Object objEntrada,Class classSalida) {
		try {
			URI uri = new URI(apiUrl + "/" + metodo);
			RestTemplate restTemplate = new RestTemplate();			
			HttpEntity<Object> reqlocal = new HttpEntity<Object>(objEntrada,this.obtenerHeaders());			
			ResponseEntity<?> result = restTemplate.exchange(uri, HttpMethod.POST, reqlocal,classSalida);
			return result.getBody();			
		} catch (Exception e) {
			e.printStackTrace();
			((DominioTransaccion)objEntrada).setTransaccionEstado(DominioTransaccion.ERROR);			
			//((DominioTransaccion)objEntrada).getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
			((DominioTransaccion)objEntrada).getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error"));
			return null;
		}		
	}
	
	protected Object postBeanToBeanSeguro(String metodo,Object objEntrada,Class classSalida) {
		try {
			URI uri = new URI(apiUrl + "/" + metodo);
			RestTemplate restTemplate = new RestTemplate();			
			HttpEntity<Object> reqlocal = new HttpEntity<Object>(objEntrada,this.getHeadersSeguro());			
			ResponseEntity<?> result = restTemplate.exchange(uri, HttpMethod.POST, reqlocal,classSalida);
			return result.getBody();			
		} catch (Exception e) {
			e.printStackTrace();
			((DominioTransaccion)objEntrada).setTransaccionEstado(DominioTransaccion.ERROR);			
			//((DominioTransaccion)objEntrada).getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
			((DominioTransaccion)objEntrada).getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error"));
			return null;
		}		
	}
		
	private HttpHeaders getHeadersSeguro() throws Exception {
		if (UString.esNuloVacio(usuarioSeguroKey))
			this.leerPropiedadesTodas();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.add(ConstanteFiltro.TOKEN, "");
		headers.add(ConstanteFiltro.SID, "");
		headers.add(ConstanteFiltro.USUARIO_SEGURO, usuarioSeguroKey);
		return headers;
	}

	private HttpHeaders obtenerHeaders() {
		return obtenerHeaders(null);
	}
	
	private HttpHeaders obtenerHeaders(SeguridadUsuarioLogin token) {
		HttpHeaders headers = new HttpHeaders();
		String idtoken = reqclass.getHeader(ConstanteFiltro.TOKEN);
		String sid = reqclass.getHeader(ConstanteFiltro.SID);
		if (token != null && UString.esNuloVacio(idtoken)) {
			if (!UString.esNuloVacio(token.getToken())) {
				idtoken = token.getToken();
			}
		}
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.add(ConstanteFiltro.TOKEN, idtoken);
		headers.add(ConstanteFiltro.SID, sid);
		//headers.add(ConstanteFiltro.USUARIO_SEGURO, usuarioSeguroKey);
		return headers;
	}
	
		
	public SeguridadUsuarioActual getUsuarioActual(SeguridadUsuarioActual entity) {
		try {
			
			String ruta = leerruta(GenericoApiImpl.proxySeguridad);
			
			Class claselocal = entity.getClass();						
			URI uri = new URI(ruta + "/spring/seguridad/usuario" + "/" + "obtenerusuarioactual");
			RestTemplate restTemplate = new RestTemplate();			
			HttpEntity<T> reqlocal = new HttpEntity<T>(this.obtenerHeaders());			
			ResponseEntity<T> result = restTemplate.exchange(uri, HttpMethod.GET, reqlocal,claselocal);
			entity = (SeguridadUsuarioActual) result.getBody();			
		} catch (Exception e) {
			((DominioTransaccion)entity).setTransaccionEstado(DominioTransaccion.ERROR);			
			//((DominioTransaccion)entity).getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
			((DominioTransaccion)entity).getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Ha ocurrido un error"));
			e.printStackTrace();
		}
		return entity;
	}
	
	
	private String leerruta(String api) throws Exception {
		String rutaPropiedades = UWebServer.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;
		logger.debug(rutaPropiedades);
		propiedades = UPropiedades.getInstance().abrir(rutaPropiedades, ConstanteBoot.PROPERTIES_GLOBAL);
		logger.debug(propiedades);
		String apiRaiz2 = "";
		String apiUrl2 = "";
		if (propiedades != null) {
			apiRaiz2 = UString.obtenerSinNulo(propiedades.getProperty(api));
			apiUrl2 = apiRaiz2;
		}
		return apiUrl2;
	}
	
	/*****/
	@ExceptionHandler({ UException.class })
	protected ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		//List<DominioMensajeUsuario> apiError = ((UException) ex).getErrors();
		//return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.CONFLICT);
		
		List<DominioMensajeUsuario> apiError = new ArrayList<DominioMensajeUsuario>();
		try {
			logger.debug("4");
			String msg = ex.getMessage();
			logger.debug(msg);
			apiError.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "ERROR", "ERROR"));
			apiError.get(0).setFuente(UException.getStackTrace(ex));
			ex.printStackTrace();		
			registrarError(null, UException.getStackTrace(ex), UException.getClassName(ex));
			//Alejandro, ocultar el trace
			for (DominioMensajeUsuario dominioMensajeUsuario : apiError) {
				dominioMensajeUsuario.setFuente(null);
				dominioMensajeUsuario.setTitulo(null);			
				logger.debug(dominioMensajeUsuario.getMensaje());
				
				dominioMensajeUsuario.setMensaje(null);
			}	
		}catch (Exception e) {
			// TODO: handle exception
		}
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.CONFLICT);
		
	}

	@ExceptionHandler({ Exception.class })
	protected ResponseEntity<Object> handleAllExcepcions(Exception ex, WebRequest request) {
		List<DominioMensajeUsuario> apiError = new ArrayList<DominioMensajeUsuario>();		
		try {
			logger.debug("5");
			String msg = ex.getMessage();
			logger.debug(msg);
			apiError.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "ERROR", "ERROR"));
			apiError.get(0).setFuente(UException.getStackTrace(ex));
			ex.printStackTrace();		
			registrarError(null, UException.getStackTrace(ex), UException.getClassName(ex));
			//Alejandro, ocultar el trace
			for (DominioMensajeUsuario dominioMensajeUsuario : apiError) {
				dominioMensajeUsuario.setFuente(null);
				dominioMensajeUsuario.setTitulo(null);			
				logger.debug(dominioMensajeUsuario.getMensaje());
				
				dominioMensajeUsuario.setMensaje(null);
			}	
		}catch (Exception e) {
			// TODO: handle exception
		}
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({ UExceptionSessionFinished.class })
	protected ResponseEntity<Object> handleSesionFinished(Exception ex, WebRequest request) {
		//List<DominioMensajeUsuario> apiError = ((UExceptionSessionFinished) ex).getErrors();
		//System.out.println("handling UExceptionSessionFinished");		
		//return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.PROXY_AUTHENTICATION_REQUIRED);
		
		List<DominioMensajeUsuario> apiError = new ArrayList<DominioMensajeUsuario>();
		try {
			logger.debug("6");
			String msg = ex.getMessage();
			logger.debug(msg);
			apiError.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "ERROR", "ERROR"));
			apiError.get(0).setFuente(UException.getStackTrace(ex));
			ex.printStackTrace();		
			registrarError(null, UException.getStackTrace(ex), UException.getClassName(ex));
			//Alejandro, ocultar el trace
			for (DominioMensajeUsuario dominioMensajeUsuario : apiError) {
				dominioMensajeUsuario.setFuente(null);
				dominioMensajeUsuario.setTitulo(null);			
				logger.debug(dominioMensajeUsuario.getMensaje());
				
				dominioMensajeUsuario.setMensaje(null);
			}	
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.PROXY_AUTHENTICATION_REQUIRED);
		
	}
	
	protected void registrarError(String proceso, String descripcionError) throws Exception {
		ErrorTransaccion bean = new ErrorTransaccion();
		bean.setProceso(proceso);
		bean.setDescripcionError(descripcionError);
		this.registrarError(bean);
	}
	
	protected void registrarError(String proceso, String descripcionError, String classname) throws Exception {
		ErrorTransaccion bean = new ErrorTransaccion();
		bean.setProceso(proceso);
		bean.setDescripcionError(descripcionError);
		bean.setClassName(classname);
		this.registrarError(bean); 
	}

	protected void registrarError(ErrorTransaccion bean) throws Exception {
		try {
			String rutaPropiedades = UWebServer.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;
			Properties propiedades;
			propiedades = UPropiedades.getInstance().abrir(rutaPropiedades, ConstanteBoot.PROPERTIES_GLOBAL);
			String apiErrorRegistrar = UString.obtenerSinNulo(propiedades.getProperty("servicio.spring.comun.error.registrar"));
			RegistroErroresHilo hilo = new RegistroErroresHilo(apiErrorRegistrar, bean, this.getHeadersSeguro());
			hilo.start();
		} catch (IOException e) {
			//e.printStackTrace();
			logger.error("Error al llamar al API de registrar error:"+e.getMessage());
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
}
