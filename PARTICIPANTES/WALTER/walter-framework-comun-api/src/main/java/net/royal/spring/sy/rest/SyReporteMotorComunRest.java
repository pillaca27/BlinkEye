package net.royal.spring.sy.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.framework.modelo.ReporteTransaccion;
import net.royal.spring.framework.modelo.correo.EmailTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.sy.servicio.impl.SyReporteMotorServicioImpl;

@RestController
@RequestMapping("/spring/sistema/syreportemotor")
@CrossOrigin(origins = "*")
public class SyReporteMotorComunRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(SyReporteMotorComunRest.class);

	@Autowired
	private SyReporteMotorServicioImpl servicio;

	/**
	 * ARMAS MIGRADO solo se implementa en NETCORE-BACKEND
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@PutMapping(value = "/ejecutar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReporteTransaccion> ejecutar(@RequestBody ReporteTransaccion bean) throws Exception {
		logger.debug("ReporteMotorRest.ejecutar");
		ReporteTransaccion res = new ReporteTransaccion();
		try {
			bean = servicio.obtenerConfiguracionReporte(bean);
			if (!bean.getTransaccionEstado().equals(DominioTransaccion.OK)) {
				res.setTransaccionEstado(EmailTransaccion.ERROR);
				res.setTransaccionListaMensajes(bean.getTransaccionListaMensajes());
				return new ResponseEntity<ReporteTransaccion>(res, HttpStatus.OK);		
			}			
			byte[] newboby = servicio.ejecutarReporte(bean, bean.getParametros());
			res.setResultadoCuerpoBinario(newboby);
			res.setResultadoAsunto(bean.getResultadoAsunto());
			res.setTransaccionEstado(EmailTransaccion.OK);
		} catch (Exception e) {
			res.setTransaccionEstado(EmailTransaccion.ERROR);
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, e.getMessage()));
			e.printStackTrace();
		}
		return new ResponseEntity<ReporteTransaccion>(res, HttpStatus.OK);
	}
	
	
	@Transactional
	@PutMapping(value="/ejecutarcadena",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReporteTransaccion> ejecutarCadena(@RequestBody ReporteTransaccion bean) throws Exception {
		logger.debug("ReporteMotorRest.ejecutar");
		ReporteTransaccion resu = new ReporteTransaccion();		
		try {
			// no se debe usar nada de usuario actual, por si lo llama un privado
			//UsuarioActual usu = this.getUsuarioActual();
			ReporteTransaccion config = servicio.obtenerConfiguracionReporte(bean);
			if (!bean.getTransaccionEstado().equals(DominioTransaccion.OK)) {
				resu.setTransaccionEstado(EmailTransaccion.ERROR);
				resu.setTransaccionListaMensajes(bean.getTransaccionListaMensajes());
				return new ResponseEntity<ReporteTransaccion>(resu, HttpStatus.OK);		
			}
			byte[] newboby = servicio.ejecutarReporte(config, bean.getParametros());			
			resu.setTransaccionEstado(ReporteTransaccion.OK);
			resu.setResultadoCuerpoBinario(null);
			resu.setResultadoCuerpoCadena(new String(newboby));
			resu.setResultadoAsunto(config.getResultadoAsunto());
		} catch (Exception e) {
			resu.setTransaccionEstado(ReporteTransaccion.ERROR);
			resu.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, e.getMessage()));
			e.printStackTrace();
		}		
		logger.debug("devolviendo");
		return new ResponseEntity<ReporteTransaccion>(resu,HttpStatus.OK);
	}
	

}
