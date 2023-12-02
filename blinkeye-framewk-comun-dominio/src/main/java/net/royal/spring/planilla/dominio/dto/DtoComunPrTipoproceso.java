package net.royal.spring.planilla.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunPrTipoproceso extends DominioTransaccion{
	private String tipoproceso;
	private String descripcion;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String descripcionextranjera;
	private String flagadelanto;
	private java.util.Date fechapago;
	private java.util.Date fechaproceso;
	private String piedeboleta;
	private String subtitulodeboleta;
	private String titulodeboleta;
	private String flagrequierecierre;
	private String tipofuncion;
	private String flagsimulacion;
	private String flagactualizavacaciones;
	private String seleccionado;
		
	@JsonIgnore
	private Integer ROWNUM_;
	
	
	public String getTipoproceso() {
		return tipoproceso;
	}
	public void setTipoproceso(String tipoproceso) {
		this.tipoproceso = tipoproceso;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public String getDescripcionextranjera() {
		return descripcionextranjera;
	}
	public void setDescripcionextranjera(String descripcionextranjera) {
		this.descripcionextranjera = descripcionextranjera;
	}
	public String getFlagadelanto() {
		return flagadelanto;
	}
	public void setFlagadelanto(String flagadelanto) {
		this.flagadelanto = flagadelanto;
	}
	public java.util.Date getFechapago() {
		return fechapago;
	}
	public void setFechapago(java.util.Date fechapago) {
		this.fechapago = fechapago;
	}
	public java.util.Date getFechaproceso() {
		return fechaproceso;
	}
	public void setFechaproceso(java.util.Date fechaproceso) {
		this.fechaproceso = fechaproceso;
	}
	public String getPiedeboleta() {
		return piedeboleta;
	}
	public void setPiedeboleta(String piedeboleta) {
		this.piedeboleta = piedeboleta;
	}
	public String getSubtitulodeboleta() {
		return subtitulodeboleta;
	}
	public void setSubtitulodeboleta(String subtitulodeboleta) {
		this.subtitulodeboleta = subtitulodeboleta;
	}
	public String getTitulodeboleta() {
		return titulodeboleta;
	}
	public void setTitulodeboleta(String titulodeboleta) {
		this.titulodeboleta = titulodeboleta;
	}
	public String getFlagrequierecierre() {
		return flagrequierecierre;
	}
	public void setFlagrequierecierre(String flagrequierecierre) {
		this.flagrequierecierre = flagrequierecierre;
	}
	public String getTipofuncion() {
		return tipofuncion;
	}
	public void setTipofuncion(String tipofuncion) {
		this.tipofuncion = tipofuncion;
	}
	public String getFlagsimulacion() {
		return flagsimulacion;
	}
	public void setFlagsimulacion(String flagsimulacion) {
		this.flagsimulacion = flagsimulacion;
	}
	public String getFlagactualizavacaciones() {
		return flagactualizavacaciones;
	}
	public void setFlagactualizavacaciones(String flagactualizavacaciones) {
		this.flagactualizavacaciones = flagactualizavacaciones;
	}
	public String getSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(String seleccionado) {
		this.seleccionado = seleccionado;
	}
	public Integer getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(Integer rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	
	
}
