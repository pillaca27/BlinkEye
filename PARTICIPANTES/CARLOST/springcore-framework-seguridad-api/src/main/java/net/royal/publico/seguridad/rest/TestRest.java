package net.royal.publico.seguridad.rest;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.rest.GenericoRest;

@RestController
@RequestMapping("/publico/seguridad/test")
@CrossOrigin(origins = "*")
public class TestRest extends GenericoRest {
	
	private static Logger logger = LogManager.getLogger(TestRest.class);
	
	@GetMapping(value="/estavivo",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> estavivo() {
		logger.debug("TestRest(Publico).estavivo");
		Date now = new Date();
		
		try {
			SeguridadUsuarioActual usu = this.getUsuarioActual();
			logger.debug(usu);	
		} catch (Exception e) {
			logger.debug("usuario actual no encontrado");
			e.printStackTrace();
		}		
		
		return new ResponseEntity<String>("SI - " + now.toString(), HttpStatus.OK);
	}
}
