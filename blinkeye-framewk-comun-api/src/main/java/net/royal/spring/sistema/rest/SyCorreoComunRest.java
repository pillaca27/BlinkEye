package net.royal.spring.sistema.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.sistema.dao.impl.SyReporteDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyReporte;
import net.royal.spring.sistema.dominio.BeanSyReportePk;
import net.royal.spring.sistema.dominio.dto.DtoComunEmailTest;
import net.royal.spring.sistema.dominio.dto.DtoComunSyReporte;
import net.royal.spring.sistema.servicio.impl.SyCorreoServicioImpl;
import net.royal.spring.sistema.servicio.impl.SyReporteServicioImpl;
import net.royal.spring.sistema.servicio.validar.SyReporteServicioValidar;
import net.royal.spring.framework.constante.ConstanteDatos;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.ParametroTransaccion;
import net.royal.spring.framework.modelo.correo.EmailConfiguracion;
import net.royal.spring.framework.modelo.correo.EmailDestino;
import net.royal.spring.framework.modelo.correo.EmailTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UByte;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/sistema/sycorreocomun")
@CrossOrigin(origins = "*")
public class SyCorreoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(SyCorreoComunRest.class);

	@Autowired
	private SyCorreoServicioImpl servicio;


	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public SyCorreoComunRest() {
		super("syreporte");
	}

	/**
	 * ARMAS MIGRADO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Envio de correo | Sin parametros de entrada | Parametros de salida: EmailConfiguracion", 
			value = "SY-CORREO-CONFIG", tags = {"SISTEMA", "CORREO"})
	@Transactional
	@GetMapping(value = "/obtenerconfiguracion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmailConfiguracion> obtenerConfiguracion() throws Exception {
		logger.debug("SyReporteRest.obtenerConfiguracion");
		EmailConfiguracion config = new EmailConfiguracion();
		config = servicio.obtenerConfiguracionBd(null);
		try {
			return new ResponseEntity<EmailConfiguracion>(config, HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return new ResponseEntity<EmailConfiguracion>(config, HttpStatus.OK);	
		}		
	}
	
	/**
	 * ARMAS MIGRADO
	 * @param correo
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Envio de correo | Parametros de entrada: EmailTransaccion | Parametros de salida: EmailTransaccion", 
			value = "SY-CORREO-CENVIAR", tags = {"SISTEMA", "CORREO"})
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
	 * ARMAS MIGRADO
	 * @param emailEnviar
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Envio de correo de prueba | Parametros de entrada: EmailTransaccion | Parametros de salida: EmailTransaccion", 
			value = "SY-CORREO-CENVPRB", tags = {"SISTEMA", "CORREO"})
	@Transactional
	@PutMapping(value = "/enviarcorreoprueba", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmailTransaccion> enviarCorreoPrueba(@RequestBody DtoComunEmailTest emailEnviar) throws Exception {
		EmailConfiguracion configCorreo = emailEnviar.getConfig();
		//EmailConfiguracion cfg2 = servicio.obtenerConfiguracionBd(null);
		configCorreo.setSessionCorreo(servicio.prepararConfiguracion(configCorreo));
        return new ResponseEntity<EmailTransaccion>( servicio.enviarCorreoJava(configCorreo, emailEnviar.getCorreo()), HttpStatus.OK );
	}
	

}
