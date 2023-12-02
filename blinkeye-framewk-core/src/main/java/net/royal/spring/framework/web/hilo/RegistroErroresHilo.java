package net.royal.spring.framework.web.hilo;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import net.royal.spring.framework.modelo.ErrorTransaccion;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

public class RegistroErroresHilo extends Thread {

	private static Logger logger = LogManager.getLogger(RegistroErroresHilo.class);

	private String apiErrorRegistrar;
	private ErrorTransaccion bean;
	private HttpHeaders httpHeaders;

	public RegistroErroresHilo(String apiErrorRegistrar, ErrorTransaccion bean1, HttpHeaders httpHeaders) {
		this.apiErrorRegistrar = apiErrorRegistrar;
		this.bean = bean1;
		this.httpHeaders = httpHeaders; 
	}

	@Override
	public void run() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			URI uri;
			uri = new URI(apiErrorRegistrar);
			
			HttpEntity<ErrorTransaccion> request = new HttpEntity<ErrorTransaccion>(bean, httpHeaders);
			ResponseEntity<ErrorTransaccion> result = restTemplate.exchange(uri, HttpMethod.POST, request,ErrorTransaccion.class);

		} catch (URISyntaxException e) {
			logger.debug("RegistroErroresHilo : error de Registro de error");
			e.printStackTrace();
		}
	}
}
