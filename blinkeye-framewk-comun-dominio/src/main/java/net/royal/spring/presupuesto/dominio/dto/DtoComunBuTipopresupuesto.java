package net.royal.spring.presupuesto.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunBuTipopresupuesto extends DominioTransaccion {
	private String tipopresupuesto;
	private String descripcionlocal;
	private String descripcioningles;
	private String montounidadflag;
	private String cuentacontableflag;
	private String centrocostoflag;
	private String elementogastoflag;
	private String proyectoflag;
	private String sucursalflag;
	private String referencia01flag;
	private String referencia02flag;
	private String cuentacontablebudgetflag;
	private String centrocostobudgetflag;
	private String elementogastobudgetflag;
	private String proyectobudgetflag;
	private String sucursalbudgetflag;
	private String referencia01budgetflag;
	private String referencia02budgetflag;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String clienteareaflag;
	private String clienteflag;
	private String clienteareabudgetflag;
	private String clientebudgetflag;
	private BigDecimal cuentacontabledigits;
	public String getTipopresupuesto() {
		return tipopresupuesto;
	}
	public void setTipopresupuesto(String tipopresupuesto) {
		this.tipopresupuesto = tipopresupuesto;
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
	public String getMontounidadflag() {
		return montounidadflag;
	}
	public void setMontounidadflag(String montounidadflag) {
		this.montounidadflag = montounidadflag;
	}
	public String getCuentacontableflag() {
		return cuentacontableflag;
	}
	public void setCuentacontableflag(String cuentacontableflag) {
		this.cuentacontableflag = cuentacontableflag;
	}
	public String getCentrocostoflag() {
		return centrocostoflag;
	}
	public void setCentrocostoflag(String centrocostoflag) {
		this.centrocostoflag = centrocostoflag;
	}
	public String getElementogastoflag() {
		return elementogastoflag;
	}
	public void setElementogastoflag(String elementogastoflag) {
		this.elementogastoflag = elementogastoflag;
	}
	public String getProyectoflag() {
		return proyectoflag;
	}
	public void setProyectoflag(String proyectoflag) {
		this.proyectoflag = proyectoflag;
	}
	public String getSucursalflag() {
		return sucursalflag;
	}
	public void setSucursalflag(String sucursalflag) {
		this.sucursalflag = sucursalflag;
	}
	public String getReferencia01flag() {
		return referencia01flag;
	}
	public void setReferencia01flag(String referencia01flag) {
		this.referencia01flag = referencia01flag;
	}
	public String getReferencia02flag() {
		return referencia02flag;
	}
	public void setReferencia02flag(String referencia02flag) {
		this.referencia02flag = referencia02flag;
	}
	public String getCuentacontablebudgetflag() {
		return cuentacontablebudgetflag;
	}
	public void setCuentacontablebudgetflag(String cuentacontablebudgetflag) {
		this.cuentacontablebudgetflag = cuentacontablebudgetflag;
	}
	public String getCentrocostobudgetflag() {
		return centrocostobudgetflag;
	}
	public void setCentrocostobudgetflag(String centrocostobudgetflag) {
		this.centrocostobudgetflag = centrocostobudgetflag;
	}
	public String getElementogastobudgetflag() {
		return elementogastobudgetflag;
	}
	public void setElementogastobudgetflag(String elementogastobudgetflag) {
		this.elementogastobudgetflag = elementogastobudgetflag;
	}
	public String getProyectobudgetflag() {
		return proyectobudgetflag;
	}
	public void setProyectobudgetflag(String proyectobudgetflag) {
		this.proyectobudgetflag = proyectobudgetflag;
	}
	public String getSucursalbudgetflag() {
		return sucursalbudgetflag;
	}
	public void setSucursalbudgetflag(String sucursalbudgetflag) {
		this.sucursalbudgetflag = sucursalbudgetflag;
	}
	public String getReferencia01budgetflag() {
		return referencia01budgetflag;
	}
	public void setReferencia01budgetflag(String referencia01budgetflag) {
		this.referencia01budgetflag = referencia01budgetflag;
	}
	public String getReferencia02budgetflag() {
		return referencia02budgetflag;
	}
	public void setReferencia02budgetflag(String referencia02budgetflag) {
		this.referencia02budgetflag = referencia02budgetflag;
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
	public String getClienteareaflag() {
		return clienteareaflag;
	}
	public void setClienteareaflag(String clienteareaflag) {
		this.clienteareaflag = clienteareaflag;
	}
	public String getClienteflag() {
		return clienteflag;
	}
	public void setClienteflag(String clienteflag) {
		this.clienteflag = clienteflag;
	}
	public String getClienteareabudgetflag() {
		return clienteareabudgetflag;
	}
	public void setClienteareabudgetflag(String clienteareabudgetflag) {
		this.clienteareabudgetflag = clienteareabudgetflag;
	}
	public String getClientebudgetflag() {
		return clientebudgetflag;
	}
	public void setClientebudgetflag(String clientebudgetflag) {
		this.clientebudgetflag = clientebudgetflag;
	}
	public BigDecimal getCuentacontabledigits() {
		return cuentacontabledigits;
	}
	public void setCuentacontabledigits(BigDecimal cuentacontabledigits) {
		this.cuentacontabledigits = cuentacontabledigits;
	}
	
	
}
