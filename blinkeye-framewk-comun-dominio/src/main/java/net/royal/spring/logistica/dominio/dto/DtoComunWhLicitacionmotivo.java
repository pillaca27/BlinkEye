package net.royal.spring.logistica.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunWhLicitacionmotivo extends DominioTransaccion{
	private String tipomotivo;
	private String motivo;
	private String descripcion;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String estado;
	private BigDecimal orden;
	private String codigoseace;
	
	public String getTipomotivo() {
		return tipomotivo;
	}
	public void setTipomotivo(String tipomotivo) {
		this.tipomotivo = tipomotivo;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public BigDecimal getOrden() {
		return orden;
	}
	public void setOrden(BigDecimal orden) {
		this.orden = orden;
	}
	public String getCodigoseace() {
		return codigoseace;
	}
	public void setCodigoseace(String codigoseace) {
		this.codigoseace = codigoseace;
	}
	
}
