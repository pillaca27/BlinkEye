package net.royal.spring.sistema.servicio.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.core.dao.impl.ParametrosmastDaoImpl;
import net.royal.spring.framework.constante.ConstanteDatos;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.core.UFile;
import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.modelo.correo.EmailConfiguracion;
import net.royal.spring.framework.modelo.correo.EmailConstante;
import net.royal.spring.framework.modelo.correo.EmailDestino;
import net.royal.spring.framework.modelo.correo.EmailDestino.tipo_destino;
import net.royal.spring.framework.modelo.correo.EmailTransaccion;
import net.royal.spring.framework.modelo.generico.DominioAdjunto;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UBoolean;
import net.royal.spring.framework.util.ULista;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.sistema.dao.impl.SyReporteDaoImpl;

import net.royal.spring.sistema.dominio.BeanSyReporte;
import net.royal.spring.sistema.dominio.BeanSyReportePk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyReporte;
import net.royal.spring.sistema.servicio.validar.SyReporteServicioValidar;

@Service (value = "BeanServicioSyCorreoServicioImpl")
public class SyCorreoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioSyReporte";
	private static Logger logger = LogManager.getLogger(SyCorreoServicioImpl.class);

	@Autowired
	private ParametrosmastDaoImpl parametrosmastDaoImpl;
	
	public EmailConfiguracion obtenerConfiguracionBd(EmailTransaccion email) throws Exception {

		if (email == null)
			email = new EmailTransaccion();
		
		if (UString.esNuloVacio(this.serverNombre))
			this.leerPropiedades();

		EmailConfiguracion config = new EmailConfiguracion();
		List<DtoTabla> lst = parametrosmastDaoImpl.obtenerParametroCorreo();
		if (lst == null)
			lst = new ArrayList();
		//logger.info("obtenerConfiguracionBd:"+lst.size());
		for (DtoTabla dto : lst) {
			//logger.info("dto.getCodigo():"+dto.getCodigo());
			//logger.info("dto.getNombre():"+dto.getNombre());
			
			// no se usa el directorio raiz de archivos como temporal:DARIO
			/*if (dto.getCodigo().trim().equals("DIRFILE"))
				config.setRutaRaizTemporal(dto.getNombre());*/
			if (dto.getCodigo().trim().equals("MAILCUENTA"))
				config.setEmailCuenta(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAILCLAVE"))
				config.setEmailClave(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAILPUERTO"))
				config.setEmailPuerto(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAILHOST"))
				config.setEmailServidor(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAILFLGSSL"))
				config.setEmailFlgSsl(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAILFLGPRU"))
				config.setFlgCorreoPrueba(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAILPRUEBA"))
				config.setCorreoPrueba(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAILRUTATM"))
				config.setRutaRaizAdjuntos(dto.getNombre());
			if (dto.getCodigo().trim().equals("MAIFLGCRED"))
				config.setEmailFlgCredenciales(dto.getNombre());	
			
			if (dto.getCodigo().trim().equals("TRACEFLG"))
				config.setTraceFlg(dto.getNombre());
			config.setTraceServidor(this.serverNombre);
		}
		if (UString.esNuloVacio(email.getRemitenteCorreo()))
			config.setEmailRemitente(config.getEmailCuenta());
		else
			config.setEmailRemitente(email.getRemitenteCorreo());
		config.setTipoConfiguracion(EmailConstante.CORREO_TIPO_CONFIGURACION_CLASE);
		return config.prepararConfiguracion();
	}
		
	public EmailTransaccion enviarCorreoJava(EmailConfiguracion cfg, EmailTransaccion correo) {
		EmailTransaccion res = new EmailTransaccion();
		if (cfg == null) {
			res.setTransaccionEstado(EmailTransaccion.ERROR);
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR,this.getMessage("mensaje.error.emailresultado.null")));
			return res;
		}

		try {
			Message message = new MimeMessage(cfg.getSessionCorreo());

			if (message == null) {
				res.setTransaccionEstado(EmailTransaccion.ERROR);
				res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR,
						this.getMessage("mensaje.error.sessionmensaje.null")));
				return res;
			}

			if (!UString.esNuloVacio(correo.getCuerpoCorreoBase64())) {
				String sinSalto = correo.getCuerpoCorreoBase64();				
				byte[] addcuerpo = Base64.getDecoder().decode(sinSalto);	//ALEJANDRO, no cambiar			 
				correo.setCuerpoCorreoBytes(addcuerpo);
			} else {
			}

			if (UString.estaVacio(correo.getRemitenteCorreo())) {
				if (UString.estaVacio(cfg.getEmailRemitente())) {
					if (UString.esNuloVacio(correo.getRemitenteNombre()))
						message.setFrom(new InternetAddress(cfg.getEmailCuenta(), cfg.getEmailCuenta()));
					else
						message.setFrom(new InternetAddress(cfg.getEmailCuenta(), correo.getRemitenteNombre()));
				} else {
					if (UString.esNuloVacio(correo.getRemitenteNombre()))
						message.setFrom(new InternetAddress(cfg.getEmailRemitente(), cfg.getEmailRemitente()));
					else
						message.setFrom(new InternetAddress(cfg.getEmailRemitente(), correo.getRemitenteNombre()));
				}
			} else {
				if (UString.esNuloVacio(correo.getRemitenteNombre())) {
					message.setFrom(new InternetAddress(correo.getRemitenteCorreo(), correo.getRemitenteCorreo()));
				} else {
					message.setFrom(new InternetAddress(correo.getRemitenteCorreo(), correo.getRemitenteCorreo()));
				}

			}

			message.setSentDate(new Date());
			message.setHeader("X-SES-CONFIGURATION-SET", "ConfigSet");

			if (UBoolean.validarFlag(cfg.getFlgCorreoPrueba())) {
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(cfg.getCorreoPrueba()));
			} else {
				int contadorInternoTO = 0;
				int contadorInternoCC = 0;
				int contadorInternoBCC = 0;

				for (EmailDestino correoDestino : correo.getListaCorreoDestino()) {
					if (correoDestino.getDestino() == null)
						correoDestino.setDestino(tipo_destino.TO);

					if (correoDestino.getDestino().equals(tipo_destino.TO))
						contadorInternoTO++;
					if (correoDestino.getDestino().equals(tipo_destino.CC))
						contadorInternoCC++;
					if (correoDestino.getDestino().equals(tipo_destino.BCC))
						contadorInternoBCC++;
				}
				InternetAddress[] addressTo = new InternetAddress[contadorInternoTO];
				InternetAddress[] addressCC = new InternetAddress[contadorInternoCC];
				InternetAddress[] addressBCC = new InternetAddress[contadorInternoBCC];

				contadorInternoTO = 0;
				contadorInternoCC = 0;
				contadorInternoBCC = 0;
				for (EmailDestino correoDestino : correo.getListaCorreoDestino()) {
					if (correoDestino.getDestino() == null)
						correoDestino.setDestino(tipo_destino.TO);

					if (correoDestino.getDestino().equals(tipo_destino.TO)) {
						addressTo[contadorInternoTO] = new InternetAddress(correoDestino.getCorreoDestino());
						contadorInternoTO++;
					}
					if (correoDestino.getDestino().equals(tipo_destino.CC)) {
						addressCC[contadorInternoCC] = new InternetAddress(correoDestino.getCorreoDestino());
						contadorInternoCC++;
					}
					if (correoDestino.getDestino().equals(tipo_destino.BCC)) {
						addressBCC[contadorInternoBCC] = new InternetAddress(correoDestino.getCorreoDestino());
						contadorInternoBCC++;
					}
				}
				message.setRecipients(Message.RecipientType.TO, addressTo);
				message.setRecipients(Message.RecipientType.CC, addressCC);
				message.setRecipients(Message.RecipientType.BCC, addressBCC);
			}

			if (correo.getCuerpoCorreoBytes() != null) {
				String cuerpoCorreo = new String(correo.getCuerpoCorreoBytes());
				message.setContent(cuerpoCorreo, "text/html");
			}
			
			/*if (!UString.estaVacio(correo.getAsunto())) {
				message.setSubject(correo.getAsunto());
			}			
			if (cfg.getTraceFlg().equals("S")){
				String as = "|" + cfg.getTraceServidor() + "|" + UString.obtenerValorCadenaSinNulo(correo.getTraceReferencia());
				if (!UString.estaVacio(correo.getAsunto())) {
					message.setSubject(correo.getAsunto()+as);
				}else {
					message.setSubject(as);
				}
			}else {
				if (!UString.estaVacio(correo.getAsunto())) {
					message.setSubject(correo.getAsunto());
				}	
			}*/
			/* DARIO se comenta lo de arriba para poder tener lo de los acentos*/
			String asuntoTemp = correo.getAsunto();						
			if (cfg.getTraceFlg().equals("S")){
				String as = "|" + cfg.getTraceServidor() + "|" + UString.obtenerValorCadenaSinNulo(correo.getTraceReferencia());
				if (!UString.estaVacio(correo.getAsunto())) {
					asuntoTemp=asuntoTemp+as;
				}else {
					asuntoTemp=as;
				}
			}	
			message.setSubject(MimeUtility.encodeText(asuntoTemp, "utf-8", "B"));

			String nombreCarpetaSession = UFile.archivoUnico();
			if (!ULista.esListaVacia(correo.getListaCorreoAdjunto())) {
				message = adjuntarArchivosJava(cfg, correo, message, nombreCarpetaSession);
			}
			
			Transport transport = cfg.getSessionCorreo().getTransport("smtp");
			System.out.println(message.getContent());
			transport.connect(cfg.getEmailServidor(), cfg.getEmailCuenta(), cfg.getEmailClave());
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

			res.setTransaccionEstado(EmailTransaccion.OK);
			String strMsg = this.getMessage("correo.envioNoExito");
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, this.getMessage("correo.envioExito")));
			try {
				UFile.eliminarCarpeta(new File(cfg.getRutaRaizAdjuntos() + nombreCarpetaSession));
			} catch (Exception e) {
			}
			return res;
		} catch (Exception ex) {
			res.setTransaccionEstado(EmailTransaccion.ERROR);
			res.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, ex.getMessage()));
			ex.printStackTrace();
		}
		return res;
	}
	
	private static Message adjuntarArchivosJava(EmailConfiguracion config, EmailTransaccion correo, Message message,
			String nombreCarpetaSession) throws Exception {

		Integer contador = 0;
		MimeMultipart multipart = new MimeMultipart();
		Boolean flgCarpetaCreada = false;
		
		String rTemporal=UString.obtenerValorCadenaSinNulo(config.getRutaRaizAdjuntos());
		if (UString.esNuloVacio(rTemporal))
			rTemporal=UString.obtenerValorCadenaSinNulo(config.getRutaRaizTemporal());
		
		logger.debug("config.getRutaRaizAdjuntos():"+config.getRutaRaizAdjuntos());
		logger.debug("config.getRutaRaizTemporal():"+config.getRutaRaizTemporal());
		logger.debug("rTemporal:"+rTemporal);

		if (correo.getListaCorreoAdjunto() == null)
			correo.setListaCorreoAdjunto(new ArrayList<DominioAdjunto>());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message.getContent(), "text/html");
		multipart.addBodyPart(messageBodyPart);

		for (DominioAdjunto adjunto : correo.getListaCorreoAdjunto()) {
			String rutaArchivoTmp = null;
			String nombreArchivoTmp = null;

			if (!flgCarpetaCreada) {
				flgCarpetaCreada = true;
				File fadj = new File(rTemporal + File.separator
						+ nombreCarpetaSession);
				boolean bCreado = fadj.mkdir();
				if (!bCreado) {
					logger.debug("carpeta de session no creada");
				}
				logger.debug(fadj.getAbsolutePath());
			}

			if (!UString.estaVacio(adjunto.getRutaCompletaArchivo())) {
				logger.debug(">>RutaCompletaArchivo");
				rutaArchivoTmp = adjunto.getRutaCompletaArchivo();
				if (UString.estaVacio(adjunto.getNombreArchivo()))
					nombreArchivoTmp = UFile.archivoUnico();
				else
					nombreArchivoTmp = adjunto.getNombreArchivo();
				logger.debug(rutaArchivoTmp);
			} else if (adjunto.getArchivoAdjuntoBytes() != null) {
				logger.debug(">>Archivo Adjunto byte[]");
				rutaArchivoTmp = rTemporal + File.separator
						+ nombreCarpetaSession + File.separator;
				if (UString.estaVacio(adjunto.getNombreArchivo()))
					nombreArchivoTmp = UFile.archivoUnico();
				else
					nombreArchivoTmp = adjunto.getNombreArchivo();

				rutaArchivoTmp = rutaArchivoTmp + nombreArchivoTmp;

				FileOutputStream fos = new FileOutputStream(rutaArchivoTmp);
				fos.write(adjunto.getArchivoAdjuntoBytes());
				fos.close();
				logger.debug(rutaArchivoTmp);
			} else if (!UString.estaVacio(adjunto.getArchivoAdjuntoBase64())) {
				logger.debug(">>Archivo Adjunto Bae 64");
				byte[] addjjun = Base64.getDecoder().decode(adjunto.getArchivoAdjuntoBase64());

				rutaArchivoTmp = rTemporal + File.separator
						+ nombreCarpetaSession + File.separator;
				if (UString.estaVacio(adjunto.getNombreArchivo()))
					nombreArchivoTmp = UFile.archivoUnico();
				else
					nombreArchivoTmp = adjunto.getNombreArchivo();

				rutaArchivoTmp = rutaArchivoTmp + nombreArchivoTmp;

				FileOutputStream fos = new FileOutputStream(rutaArchivoTmp);
				fos.write(addjjun);
				fos.close();
				logger.debug(rutaArchivoTmp);
			} else {
				logger.debug(">> NO ENVIADO NADA DE ADJUNTOS");
			}

			File f = new File(rutaArchivoTmp);

			if (f.exists()) {
				contador++;

				MimeBodyPart messageBodyPart2 = new MimeBodyPart();
				DataSource source = new FileDataSource(rutaArchivoTmp);
				messageBodyPart2.setDataHandler(new DataHandler(source));
				messageBodyPart2.setFileName(nombreArchivoTmp);
				multipart.addBodyPart(messageBodyPart2);
			} else {
				logger.error("archivo no existe :{}", rutaArchivoTmp);
			}
		}

		if (contador > 0)
			message.setContent(multipart);
		return message;
	}
	
	public EmailTransaccion enviarCorreo(EmailConfiguracion config, EmailTransaccion correo) throws Exception {
		EmailTransaccion res = new EmailTransaccion();
		if (config.getTipoConfiguracion().equals(EmailConstante.CORREO_TIPO_CONFIGURACION_CLASE)) {
			res = enviarCorreoJava(config, correo);
		}
		if (config.getTipoConfiguracion().equals(EmailConstante.CORREO_TIPO_CONFIGURACION_BDFUNCION)) {

		}
		return res;
	}
	
	public Session prepararConfiguracion(EmailConfiguracion cfg) {
		Properties pro = new Properties();

		if (!UString.estaVacio(cfg.getEmailFlgSsl())) 
			pro.put("mail.smtp.starttls.enable", UBoolean.obtenerNombreValorIngles(cfg.getEmailFlgSsl()));

		if (!UString.estaVacio(cfg.getEmailTlsVersion()))
			pro.put("mail.smtp.ssl.protocols", "TLSv" + cfg.getEmailTlsVersion());				
		
		if (!UString.estaVacio(cfg.getEmailServidor()))
			pro.put("mail.smtp.host", cfg.getEmailServidor());

		if (!UString.estaVacio(cfg.getEmailPuerto()))
			pro.put("mail.smtp.port", cfg.getEmailPuerto());

		if (!UString.estaVacio(cfg.getEmailFlgCredenciales()))
			pro.put("mail.smtp.auth", UBoolean.obtenerNombreValorIngles(cfg.getEmailFlgCredenciales()));

		if (!UString.estaVacio(cfg.getEmailCuenta()))
			pro.put("mail.user", cfg.getEmailCuenta());

		if (!UString.estaVacio(cfg.getEmailClave()))
			pro.put("mail.password", cfg.getEmailClave());

		if (!UString.estaVacio(cfg.getEmailRemitente()))
			pro.put("mail.smtp.mail.sender", cfg.getEmailRemitente());
		
		logger.debug("SMTP Props: " + pro);

		Session session = Session.getDefaultInstance(pro, null);
		return session;
	}

}
