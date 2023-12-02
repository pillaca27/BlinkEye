package net.royal.spring.logistica.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunWhTipodocumento extends DominioTransaccion{
	private String tipodocumento;
	private String descripcionlocal;
	private String descripcioningles;
	private String esfiscalflag;
	private String transacciondelsistemaflag;
	private String codigofiscal;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	public String getTipodocumento() {
		return tipodocumento;
	}
	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	public String getDescripcionlocal() {
		return descripcionlocal;
	}
	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}
	public String getDescripcioningles() {
		return descripcioningles;
	}
	public void setDescripcioningles(String descripcioningles) {
		this.descripcioningles = descripcioningles;
	}
	public String getEsfiscalflag() {
		return esfiscalflag;
	}
	public void setEsfiscalflag(String esfiscalflag) {
		this.esfiscalflag = esfiscalflag;
	}
	public String getTransacciondelsistemaflag() {
		return transacciondelsistemaflag;
	}
	public void setTransacciondelsistemaflag(String transacciondelsistemaflag) {
		this.transacciondelsistemaflag = transacciondelsistemaflag;
	}
	public String getCodigofiscal() {
		return codigofiscal;
	}
	public void setCodigofiscal(String codigofiscal) {
		this.codigofiscal = codigofiscal;
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
	
}
