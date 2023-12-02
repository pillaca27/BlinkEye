package net.royal.publico;

import java.util.Date;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.framework.modelo.correo.EmailConfiguracion;
import net.royal.spring.framework.modelo.correo.EmailTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.sistema.rest.SyCorreoComunRest;
import net.royal.spring.sistema.servicio.impl.SyCorreoServicioImpl;

@RestController
@RequestMapping("/publico/correomotor")
@CrossOrigin(origins = "*")
public class CorreoMotorRest extends GenericoRest{
	
	private static Logger logger = LogManager.getLogger(CorreoMotorRest.class);

	@Autowired
	private SyCorreoServicioImpl servicio;

	@Transactional
	@PutMapping(value = "/enviarcorreo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmailTransaccion> enviarcorreo(@RequestBody EmailTransaccion correo) throws Exception {
		EmailTransaccion res = new EmailTransaccion();
		try {
			EmailConfiguracion config = servicio.obtenerConfiguracionBd(correo);
			res = servicio.enviarCorreo(config, correo);			
			res.setTransaccionEstado(EmailTransaccion.OK);
		} catch (Exception e) {
			res.setTransaccionEstado(EmailTransaccion.ERROR);
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, e.getMessage()));
			e.printStackTrace();
		}
		return new ResponseEntity<EmailTransaccion>(correo, HttpStatus.OK );
	}
	
	/**
	 * Get estavivo
	 * @return
	 */
	@GetMapping(value="/estavivo",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> estavivo() {
		logger.debug("UsuarioRest (Privado).estavivo");
		Date now = new Date();		
		try {
			SeguridadUsuarioActual usu = this.getUsuarioActual();
			logger.debug(usu);	
		} catch (Exception e) {
			logger.debug("usuario actual no encontrado");
		}				
		return new ResponseEntity<String>("SI - " + now.toString(), HttpStatus.OK);
	}
	
	/*@GetMapping(value="/estavivo2",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> estavivo2() {
		logger.debug("UsuarioRest (Privado).estavivo 2");
		String re="";
		TimeZone tm = TimeZone.getDefault();
		try {
			//SeguridadUsuarioActual usu = this.getUsuarioActual();
			re = tm.toString();
			re = re + "|getID=" +tm.getID();
			logger.debug(tm.getID());	
		} catch (Exception e) {
			logger.debug("usuario actual no encontrado");
		}				
		return new ResponseEntity<String>("SI - " + re, HttpStatus.OK);
	}*/
}
