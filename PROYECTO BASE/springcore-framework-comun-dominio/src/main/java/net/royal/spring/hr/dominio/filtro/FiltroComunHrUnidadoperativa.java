package net.royal.spring.hr.dominio.filtro;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunHrUnidadoperativa{
	
	private String unidadoperativa;
	private String descripcion;
	private BigDecimal responsable;
	private String unidadoperativaSuperior;
	private BigDecimal responsablecontratos;
	private String responsablecompania;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	private DominioPaginacion paginacion;
	
	
	
	public DominioPaginacion getPaginacion() {
		return paginacion;
	}
	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
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
	public BigDecimal getResponsable() {
		return responsable;
	}
	public void setResponsable(BigDecimal responsable) {
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
