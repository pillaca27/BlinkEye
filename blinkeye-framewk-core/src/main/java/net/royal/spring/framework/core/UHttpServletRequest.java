package net.royal.spring.framework.core;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class UHttpServletRequest {
	public static String rutaWebServer(HttpServletRequest request) {		
		String baseUrl2 = ServletUriComponentsBuilder.fromRequestUri(request)
	            .replacePath(null)
	            .build()
	            .toUriString();
		return baseUrl2;
	}
}
