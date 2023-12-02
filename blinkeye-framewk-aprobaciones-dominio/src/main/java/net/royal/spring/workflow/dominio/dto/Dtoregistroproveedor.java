package net.royal.spring.workflow.dominio.dto;

import java.util.Date;

public class Dtoregistroproveedor {
	
	private Date regfechasolicitud;
	private Date regfechavencimiento;
	private String regnombreempresa;
	private String regdocumentofiscal;
	public Date getRegfechasolicitud() {
		return regfechasolicitud;
	}
	public void setRegfechasolicitud(Date regfechasolicitud) {
		this.regfechasolicitud = regfechasolicitud;
	}
	public Date getRegfechavencimiento() {
		return regfechavencimiento;
	}
	public void setRegfechavencimiento(Date regfechavencimiento) {
		this.regfechavencimiento = regfechavencimiento;
	}
	public String getRegnombreempresa() {
		return regnombreempresa;
	}
	public void setRegnombreempresa(String regnombreempresa) {
		this.regnombreempresa = regnombreempresa;
	}
	public String getRegdocumentofiscal() {
		return regdocumentofiscal;
	}
	public void setRegdocumentofiscal(String regdocumentofiscal) {
		this.regdocumentofiscal = regdocumentofiscal;
	}
	

}
