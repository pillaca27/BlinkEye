package net.royal.spring.pr.dominio;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunPrTipoplanilla extends DominioTransaccion{
	private String tipoplanilla;
	private BigDecimal funcion;
	private String descripcion;
	private String titulodeboleta;
	private String descripcionextranjera;
	private java.math.BigDecimal tipodecambio;
	private String clasepago;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String mensaje;
	private BigDecimal planillaperfil;
	private String periodicidad;
	private String categoriatrabajador;
	private String tipotrabajador;
	private String cuentacontable;
	private String flujocaja;
	private String afectoutilidad;
	private BigDecimal diasvacaciones;
	
	public String getTipoplanilla() {
		return tipoplanilla;
	}
	public void setTipoplanilla(String tipoplanilla) {
		this.tipoplanilla = tipoplanilla;
	}
	public BigDecimal getFuncion() {
		return funcion;
	}
	public void setFuncion(BigDecimal funcion) {
		this.funcion = funcion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTitulodeboleta() {
		return titulodeboleta;
	}
	public void setTitulodeboleta(String titulodeboleta) {
		this.titulodeboleta = titulodeboleta;
	}
	public String getDescripcionextranjera() {
		return descripcionextranjera;
	}
	public void setDescripcionextranjera(String descripcionextranjera) {
		this.descripcionextranjera = descripcionextranjera;
	}
	public java.math.BigDecimal getTipodecambio() {
		return tipodecambio;
	}
	public void setTipodecambio(java.math.BigDecimal tipodecambio) {
		this.tipodecambio = tipodecambio;
	}
	public String getClasepago() {
		return clasepago;
	}
	public void setClasepago(String clasepago) {
		this.clasepago = clasepago;
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
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public BigDecimal getPlanillaperfil() {
		return planillaperfil;
	}
	public void setPlanillaperfil(BigDecimal planillaperfil) {
		this.planillaperfil = planillaperfil;
	}
	public String getPeriodicidad() {
		return periodicidad;
	}
	public void setPeriodicidad(String periodicidad) {
		this.periodicidad = periodicidad;
	}
	public String getCategoriatrabajador() {
		return categoriatrabajador;
	}
	public void setCategoriatrabajador(String categoriatrabajador) {
		this.categoriatrabajador = categoriatrabajador;
	}
	public String getTipotrabajador() {
		return tipotrabajador;
	}
	public void setTipotrabajador(String tipotrabajador) {
		this.tipotrabajador = tipotrabajador;
	}
	public String getCuentacontable() {
		return cuentacontable;
	}
	public void setCuentacontable(String cuentacontable) {
		this.cuentacontable = cuentacontable;
	}
	public String getFlujocaja() {
		return flujocaja;
	}
	public void setFlujocaja(String flujocaja) {
		this.flujocaja = flujocaja;
	}
	public String getAfectoutilidad() {
		return afectoutilidad;
	}
	public void setAfectoutilidad(String afectoutilidad) {
		this.afectoutilidad = afectoutilidad;
	}
	public BigDecimal getDiasvacaciones() {
		return diasvacaciones;
	}
	public void setDiasvacaciones(BigDecimal diasvacaciones) {
		this.diasvacaciones = diasvacaciones;
	}
	
}
