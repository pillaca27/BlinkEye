package net.royal.spring.logistica.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunWhMarcas  extends DominioTransaccion{
	private String marcacodigo;
	private String descripcionlocal;
	private String descripcioningles;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private BigDecimal persona;
	
	public DtoComunWhMarcas() {}
	public DtoComunWhMarcas(String marcacodigo) {
		this.marcacodigo=marcacodigo;
	}
	
	public String getMarcacodigo() {
		return marcacodigo;
	}
	public void setMarcacodigo(String marcacodigo) {
		this.marcacodigo = marcacodigo;
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
	public BigDecimal getPersona() {
		return persona;
	}
	public void setPersona(BigDecimal persona) {
		this.persona = persona;
	}
	
	
}
