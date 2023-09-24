package net.royal.spring.framework.web.filter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.royal.spring.framework.core.UExceptionSessionFinished;
import net.royal.spring.framework.core.UWebServer;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UPropiedades;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.constante.ConstanteFiltro;
import net.royal.spring.framework.web.rest.HeaderMapRequestWrapper;

public class GenericoSeguridadFiltroPrivado implements Filter {
	private static Logger logger = LogManager.getLogger(GenericoSeguridadFiltroPrivado.class);
	private String securityServer;
	private String usuarioSeguroKey;
	private String encriptarCanal;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//logger.debug("GenericoSeguridadFiltroPrivado.doFilter:");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		if (httpRequest.getMethod().equals("OPTIONS")) {
			chain.doFilter(request, response);
			return;
		}

		HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(httpRequest);

		String usuarioSeguro = httpRequest.getHeader(ConstanteFiltro.USUARIO_SEGURO);
		String sid = httpRequest.getHeader(ConstanteFiltro.SID);
		String token = httpRequest.getHeader(ConstanteFiltro.TOKEN);
		token = UString.obtenerSinNulo(token).replace("Bearer ", "");

		if (UString.obtenerSinNulo(usuarioSeguro).equals(usuarioSeguroKey)) {
			SeguridadUsuarioActual uaaSeguro = new SeguridadUsuarioActual();
			uaaSeguro.setUsuarioSeguro();
			ObjectMapper mapper = new ObjectMapper();
			requestWrapper.addHeader(ConstanteFiltro.USUARIO_SEGURO, usuarioSeguro);			
			chain.doFilter(requestWrapper, response);
			return;
		}

		SeguridadUsuarioActual usuarioActual = null;
		try {

			List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
			acceptableMediaTypes.add(MediaType.APPLICATION_XML);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set(ConstanteFiltro.TOKEN, token);
			headers.set(ConstanteFiltro.SID, sid);
			headers.set(ConstanteFiltro.USUARIO_SEGURO, usuarioSeguro);

			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<SeguridadUsuarioActual> respEntity = restTemplate.exchange(securityServer, HttpMethod.GET,
					entity, SeguridadUsuarioActual.class);
			usuarioActual = respEntity.getBody();

		} catch (HttpClientErrorException ex) {
			logger.debug("HttpClientErrorException.getStatusCode()" + ex.getStatusCode());
			if (ex.getStatusCode() == HttpStatus.PROXY_AUTHENTICATION_REQUIRED) {
				logger.debug("407 SESSION TERMINADA");
				httpResponse.sendError(HttpServletResponse.SC_PROXY_AUTHENTICATION_REQUIRED, "Session terminada");
				return;
			}

		} catch (ResourceAccessException ex) {
			logger.debug("504 SERVIDOR SEGUIRDAD APAGADO");
			httpResponse.sendError(HttpServletResponse.SC_GATEWAY_TIMEOUT, "Servidor de Seguridad apagado");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		// si no es nulo, avanzar y poner en el header el usuario actual
		if (usuarioActual == null) {
			// logger.debug("usuario nulo:"+uuids);
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		usuarioActual.setToken(token);
		ObjectMapper mapper = new ObjectMapper();
		requestWrapper.addHeader(ConstanteFiltro.USUARIO_ACTUAL, mapper.writeValueAsString(usuarioActual));
		requestWrapper.addHeader(ConstanteFiltro.USUARIO_SEGURO, usuarioSeguro);
		
		if (encriptarCanal.equals("N")) {
			chain.doFilter(requestWrapper, response);	
		}else {
			RequestWrapper wrapper = new RequestWrapper((HttpServletRequest) requestWrapper);
			chain.doFilter(wrapper, response);	
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String recursoRuta = UWebServer.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;
		Properties recursoPropiedad;
		try {
			recursoPropiedad = recursoPropiedad = UPropiedades.getInstance().abrir(recursoRuta,
					ConstanteBoot.PROPERTIES_GLOBAL);
			securityServer = recursoPropiedad.getProperty("token.servicio.validar");
			usuarioSeguroKey = recursoPropiedad.getProperty("seguridad.usuarioseguro");
			encriptarCanal = recursoPropiedad.getProperty("seguridad.canalcomunicacion");
			if (UString.esNuloVacio(encriptarCanal))
				encriptarCanal="S";
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
