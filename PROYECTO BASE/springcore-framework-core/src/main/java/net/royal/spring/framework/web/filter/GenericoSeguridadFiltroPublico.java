package net.royal.spring.framework.web.filter;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.royal.spring.framework.core.UWebServer;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioLogin;
import net.royal.spring.framework.util.UPropiedades;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.constante.ConstanteFiltro;
import net.royal.spring.framework.web.rest.HeaderMapRequestWrapper;

public class GenericoSeguridadFiltroPublico implements Filter {
	private static Logger logger = LogManager.getLogger(GenericoSeguridadFiltroPublico.class);
	private String securityServer;
	private String encriptarCanal;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//logger.debug("GenericoSeguridadFiltroPublico.doFilter");
		
		SeguridadUsuarioActual usuarioActual = null;
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (httpRequest.getMethod().equals("OPTIONS")) {
			chain.doFilter(request, response);
			return;
		}

		HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(httpRequest);

		// obtener valores del header
		String usuarioSeguro = httpRequest.getHeader(ConstanteFiltro.USUARIO_SEGURO);
		String token = httpRequest.getHeader(ConstanteFiltro.TOKEN);
		String sid = httpRequest.getHeader(ConstanteFiltro.SID);
		token = UString.obtenerSinNulo(token).replace("Bearer ", "");
		
		if (!UString.estaVacio(token)) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set(ConstanteFiltro.TOKEN, token);
			headers.set(ConstanteFiltro.SID, sid);
			headers.set(ConstanteFiltro.USUARIO_SEGURO, usuarioSeguro);
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			try {
				ResponseEntity<SeguridadUsuarioActual> respEntity = restTemplate.exchange(securityServer, HttpMethod.GET,
						entity, SeguridadUsuarioActual.class);
				usuarioActual = respEntity.getBody();
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}

		if (usuarioActual != null) {
			ObjectMapper mapper = new ObjectMapper();
			requestWrapper.addHeader(ConstanteFiltro.USUARIO_ACTUAL, mapper.writeValueAsString(usuarioActual));
		}
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
			encriptarCanal = recursoPropiedad.getProperty("seguridad.canalcomunicacion");
			if (UString.esNuloVacio(encriptarCanal))
				encriptarCanal="S";
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
}
