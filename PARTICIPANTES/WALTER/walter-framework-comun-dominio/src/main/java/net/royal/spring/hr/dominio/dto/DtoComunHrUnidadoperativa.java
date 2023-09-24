package net.royal.spring.hr.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunHrUnidadoperativa extends DominioTransaccion{
	
	private String unidadoperativa;
	private String descripcion;
	private Integer responsable;
	private String unidadoperativaSuperior;
	private BigDecimal responsablecontratos;
	private String responsablecompania;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	private String responsablenombre;
	private String responsablecompanianombre;
	
	
	public String getResponsablecompanianombre() {
		return responsablecompanianombre;
	}
	public void setResponsablecompanianombre(String responsablecompanianombre) {
		this.responsablecompanianombre = responsablecompanianombre;
	}
	public String getResponsablenombre() {
		return responsablenombre;
	}
	public void setResponsablenombre(String responsablenombre) {
		this.responsablenombre = responsablenombre;
	}
	public String getUnidadoperativa() {
		return unidadoperativa;
	}
	public void setUnidadoperativa(String unidadoperativa) {
		this.unidadoperativa = unidadoperativa;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getResponsable() {
		return responsable;
	}
	public void setResponsable(Integer responsable) {
		this.responsable = responsable;
	}
	public String getUnidadoperativaSuperior() {
		return unidadoperativaSuperior;
	}
	public void setUnidadoperativaSuperior(String unidadoperativaSuperior) {
		this.unidadoperativaSuperior = unidadoperativaSuperior;
	}
	public BigDecimal getResponsablecontratos() {
		return responsablecontratos;
	}
	public void setResponsablecontratos(BigDecimal responsablecontratos) {
		this.responsablecontratos = responsablecontratos;
	}
	public String getResponsablecompania() {
		return responsablecompania;
	}
	public void setResponsablecompania(String responsablecompania) {
		this.responsablecompania = responsablecompania;
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
