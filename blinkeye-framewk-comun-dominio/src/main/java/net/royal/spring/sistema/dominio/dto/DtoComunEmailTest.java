package net.royal.spring.sistema.dominio.dto;

import net.royal.spring.framework.modelo.correo.EmailConfiguracion;
import net.royal.spring.framework.modelo.correo.EmailTransaccion;

public class DtoComunEmailTest {
	public EmailConfiguracion config;
	public EmailTransaccion correo;
	
	public DtoComunEmailTest() {
		config=new EmailConfiguracion();
		correo=new EmailTransaccion();
	}
	
	public EmailConfiguracion getConfig() {
		return config;
	}
	public void setConfig(EmailConfiguracion config) {
		this.config = config;
	}
	public EmailTransaccion getCorreo() {
		return correo;
	}
	public void setCorreo(EmailTransaccion correo) {
		this.correo = correo;
	}	
}
