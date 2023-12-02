package net.royal.spring.seguridad.boot;

import java.io.IOException;
import java.util.Map;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import net.royal.autorizacion.seguridad.rest.LoginRest;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.constante.ConstanteFiltro;
import net.royal.spring.framework.web.filter.RequestWrapper;
import net.royal.spring.framework.web.rest.HeaderMapRequestWrapper;

public class SeguridadAutorizacionSecurityFilter implements Filter {
	private static Logger logger = LogManager.getLogger(SeguridadAutorizacionSecurityFilter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		UUID uuid = UUID.randomUUID();
		String uuids = uuid.toString();

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		if (httpRequest.getMethod().equals("OPTIONS")) {
			chain.doFilter(request, response);
			return;
		}

		HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(httpRequest);

		// obtener token
		String usuarioSeguro = httpRequest.getHeader(ConstanteFiltro.USUARIO_SEGURO);
		String token = httpRequest.getHeader(ConstanteFiltro.TOKEN);
		token = UString.obtenerSinNulo(token).replace("Bearer ", "");

		if (UString.estaVacio(token)) {
			if (UString.obtenerSinNulo(usuarioSeguro).equals(ConstanteFiltro.USUARIO_SEGURO)) {
				requestWrapper.addHeader(ConstanteFiltro.USUARIO_SEGURO, usuarioSeguro);
				chain.doFilter(requestWrapper, response);
				return;
			} else {
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
		}

		SeguridadUsuarioActual usuarioActual = null;
		try {
			Claims claims = null;
			try {
				claims = Jwts.parser().setSigningKey(ConstanteBoot.TOKEN_JWTKEY).parseClaimsJws(token).getBody();
				Object map =  LoginRest.sessiones.get(token.replace("Bearer ", ""));
				if (map == null) {
					httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
					return;
				}
				Map<String, Object> mapUsuario = (Map) map;
				usuarioActual = (SeguridadUsuarioActual) mapUsuario.get("usuario");
			}
			// ALEJANDRO dentro del api de seguridad asi se valida la session, para apis
			// externas es en validarToken
			catch (ExpiredJwtException e) {
				//e.printStackTrace();
				logger.error("Session espirada jwt");
				logger.error("Session espirada jwt:"+token);
				httpResponse.sendError(HttpServletResponse.SC_PROXY_AUTHENTICATION_REQUIRED, "Session expirada");
				return;
			} catch (Exception e) {
				//e.printStackTrace();
				logger.error("Session excepcion jwt:"+e.getMessage());
				logger.error("Session excepcion jwt:"+token);
				try {
					if ( UString.obtenerValorCadenaSinNulo(token).length()>=7 ) {
						claims = Jwts.parser().setSigningKey(ConstanteBoot.TOKEN_JWTKEY).parseClaimsJws(token.substring(7, token.length()))
								.getBody();
						Object map =  LoginRest.sessiones.get(token.replace("Bearer ", ""));
						if (map == null) {
							httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
							return;
						}
						Map<String, Object> mapUsuario = (Map) map;
						usuarioActual = (SeguridadUsuarioActual) mapUsuario.get("usuario");	
					}else {
						logger.error("Session lenght< 7");
						logger.error("Session lenght< 7:"+token);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					logger.error("Session excepcion jwt");
					logger.error("Session excepcion jwt:"+token);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// si no es nulo, avanzar y poner en el header el usuario actual
		if (usuarioActual == null) {
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		ObjectMapper mapper = new ObjectMapper();
		usuarioActual.setToken(token);
		requestWrapper.addHeader(ConstanteFiltro.USUARIO_SEGURO, usuarioSeguro);
		requestWrapper.addHeader(ConstanteFiltro.USUARIO_ACTUAL, mapper.writeValueAsString(usuarioActual));
		RequestWrapper wrapper = new RequestWrapper((HttpServletRequest) requestWrapper);
		chain.doFilter(wrapper, response);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}