package net.royal.spring.logistica.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunWhItemprocedencia extends DominioTransaccion{
	private String itemprocedencia;
	private String descripcionlocal;
	private String descripcioningles;
	private String estado;
	private java.util.Date ultimafechamodif;
	private String ultimousuario;
	
	public DtoComunWhItemprocedencia() {}
	public DtoComunWhItemprocedencia(String itemprocedencia) {
		this.itemprocedencia=itemprocedencia;
	}
	public String getItemprocedencia() {
		return itemprocedencia;
	}
	public void setItemprocedencia(String itemprocedencia) {
		this.itemprocedencia = itemprocedencia;
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	public String getUltimousuario() {
		return ultimousuario;
	}
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	
	
}
