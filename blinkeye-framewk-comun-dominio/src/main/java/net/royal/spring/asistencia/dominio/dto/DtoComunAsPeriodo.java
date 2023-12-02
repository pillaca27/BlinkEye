package net.royal.spring.asistencia.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunAsPeriodo extends DominioTransaccion {
	private BigDecimal secuencia;
	private java.util.Date fechadesde;
	private String tipo;
	private java.util.Date fechahasta;
	private String nombre;
	private String estado;
	private java.util.Date ultimafechamodif;
	private String ultimousuario;
	private String periodoplanilla;
	private String tipoplanilla;
	public BigDecimal getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(BigDecimal secuencia) {
		this.secuencia = secuencia;
	}
	public java.util.Date getFechadesde() {
		return fechadesde;
	}
	public void setFechadesde(java.util.Date fechadesde) {
		this.fechadesde = fechadesde;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public java.util.Date getFechahasta() {
		return fechahasta;
	}
	public void setFechahasta(java.util.Date fechahasta) {
		this.fechahasta = fechahasta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public String getPeriodoplanilla() {
		return periodoplanilla;
	}
	public void setPeriodoplanilla(String periodoplanilla) {
		this.periodoplanilla = periodoplanilla;
	}
	public String getTipoplanilla() {
		return tipoplanilla;
	}
	public void setTipoplanilla(String tipoplanilla) {
		this.tipoplanilla = tipoplanilla;
	}
		
}
