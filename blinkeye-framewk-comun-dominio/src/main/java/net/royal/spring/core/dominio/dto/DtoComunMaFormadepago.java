package net.royal.spring.core.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunMaFormadepago  extends DominioTransaccion{
	private String formadepago;
	private String descripcion;
	private String descripcionextranjera;
	private String creditoflag;
	private String importacionflag;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String cuotacreditoflag;
	
	public DtoComunMaFormadepago() {}
	public DtoComunMaFormadepago(String formadepago) {
		this.formadepago=formadepago;
	}
	
	public String getFormadepago() {
		return formadepago;
	}
	public void setFormadepago(String formadepago) {
		this.formadepago = formadepago;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescripcionextranjera() {
		return descripcionextranjera;
	}
	public void setDescripcionextranjera(String descripcionextranjera) {
		this.descripcionextranjera = descripcionextranjera;
	}
	public String getCreditoflag() {
		return creditoflag;
	}
	public void setCreditoflag(String creditoflag) {
		this.creditoflag = creditoflag;
	}
	public String getImportacionflag() {
		return importacionflag;
	}
	public void setImportacionflag(String importacionflag) {
		this.importacionflag = importacionflag;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUltimousuario() {
		return ultimousuario;
	}
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	public String getCuotacreditoflag() {
		return cuotacreditoflag;
	}
	public void setCuotacreditoflag(String cuotacreditoflag) {
		this.cuotacreditoflag = cuotacreditoflag;
	}
	
	
}
