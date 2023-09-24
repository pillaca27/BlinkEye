package net.royal.spring.framework.web.filter;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.royal.spring.framework.core.UWebServer;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioLogin;
import net.royal.spring.framework.util.UPropiedades;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.constante.ConstanteFiltro;
import net.royal.spring.framework.web.rest.HeaderMapRequestWrapper;

public class GenericoSeguridadFiltroAutorizacion  implements Filter{
	private static Logger logger = LogManager.getLogger(GenericoSeguridadFiltroAutorizacion.class);
	private String securityServer;
	private String encriptarCanal;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//logger.debug("GenericoSeguridadFiltroAutorizacion.doFilter:");
		        
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		if (httpRequest.getMethod().equals("OPTIONS")) {
			chain.doFilter(request, response);
			return;
		}

		HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(httpRequest);
		
		String usuarioSeguro = httpRequest.getHeader(ConstanteFiltro.USUARIO_SEGURO);
		String token = httpRequest.getHeader(ConstanteFiltro.TOKEN); 
		String sid = httpRequest.getHeader(ConstanteFiltro.SID);
		token = UString.obtenerSinNulo(token).replace("Bearer ", "");

		ObjectMapper mapper = new ObjectMapper();
		requestWrapper.addHeader(ConstanteFiltro.TOKEN, token);
		requestWrapper.addHeader(ConstanteFiltro.SID, sid);
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
			encriptarCanal = recursoPropiedad.getProperty("seguridad.canalcomunicacion");
			if (UString.esNuloVacio(encriptarCanal))
				encriptarCanal="S";
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
