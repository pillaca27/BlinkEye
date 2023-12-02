package net.royal.spring.asistencia.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunAsTipohorario extends DominioTransaccion {
	// pk
	private BigDecimal tipohorario;
	
	// columnas
	private String descripcionlocal;
	private String descripcionextranjera;
	private BigDecimal tipoturno;
	private BigDecimal lunes;
	private BigDecimal martes;
	private BigDecimal miercoles;
	private BigDecimal jueves;
	private BigDecimal viernes;
	private BigDecimal sabado;
	private BigDecimal domingo;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	public DtoComunAsTipohorario() {}
	public DtoComunAsTipohorario(BigDecimal tipohorario) {
		this.tipohorario=tipohorario;
	}
	
	public BigDecimal getTipohorario() {
		return tipohorario;
	}
	public void setTipohorario(BigDecimal tipohorario) {
		this.tipohorario = tipohorario;
	}
	public String getDescripcionlocal() {
		return descripcionlocal;
	}
	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}
	public String getDescripcionextranjera() {
		return descripcionextranjera;
	}
	public void setDescripcionextranjera(String descripcionextranjera) {
		this.descripcionextranjera = descripcionextranjera;
	}
	public BigDecimal getTipoturno() {
		return tipoturno;
	}
	public void setTipoturno(BigDecimal tipoturno) {
		this.tipoturno = tipoturno;
	}
	public BigDecimal getLunes() {
		return lunes;
	}
	public void setLunes(BigDecimal lunes) {
		this.lunes = lunes;
	}
	public BigDecimal getMartes() {
		return martes;
	}
	public void setMartes(BigDecimal martes) {
		this.martes = martes;
	}
	public BigDecimal getMiercoles() {
		return miercoles;
	}
	public void setMiercoles(BigDecimal miercoles) {
		this.miercoles = miercoles;
	}
	public BigDecimal getJueves() {
		return jueves;
	}
	public void setJueves(BigDecimal jueves) {
		this.jueves = jueves;
	}
	public BigDecimal getViernes() {
		return viernes;
	}
	public void setViernes(BigDecimal viernes) {
		this.viernes = viernes;
	}
	public BigDecimal getSabado() {
		return sabado;
	}
	public void setSabado(BigDecimal sabado) {
		this.sabado = sabado;
	}
	public BigDecimal getDomingo() {
		return domingo;
	}
	public void setDomingo(BigDecimal domingo) {
		this.domingo = domingo;
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
