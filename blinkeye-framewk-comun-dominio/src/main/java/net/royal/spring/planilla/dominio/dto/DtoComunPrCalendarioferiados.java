package net.royal.spring.planilla.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunPrCalendarioferiados extends DominioTransaccion{
	private String fechamesdia;
	private String feriadocompletoflag;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String descripcion;
	private String allsucursal;
	private String alltipoplanilla;
	private java.util.Date fechafincompensable;
	private java.util.Date fechainiciocompensable;
	private String flagcompensable;
	private BigDecimal iniciocompensable;
	private java.util.Date iniciocompensablefecha;
	private String periodoadescontar;
	private String sucursal;
	private String tipoplanilla;
	private java.math.BigDecimal topediascompensar;
	private String feriado;
	private Integer cantidadFeriado;
	
	public DtoComunPrCalendarioferiados() {}
	public DtoComunPrCalendarioferiados(String fechamesdia) {
		this.fechamesdia=fechamesdia;
	}
	
	public String getFechamesdia() {
		return fechamesdia;
	}
	public void setFechamesdia(String fechamesdia) {
		this.fechamesdia = fechamesdia;
	}
	public String getFeriadocompletoflag() {
		return feriadocompletoflag;
	}
	public void setFeriadocompletoflag(String feriadocompletoflag) {
		this.feriadocompletoflag = feriadocompletoflag;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getAllsucursal() {
		return allsucursal;
	}
	public void setAllsucursal(String allsucursal) {
		this.allsucursal = allsucursal;
	}
	public String getAlltipoplanilla() {
		return alltipoplanilla;
	}
	public void setAlltipoplanilla(String alltipoplanilla) {
		this.alltipoplanilla = alltipoplanilla;
	}
	public java.util.Date getFechafincompensable() {
		return fechafincompensable;
	}
	public void setFechafincompensable(java.util.Date fechafincompensable) {
		this.fechafincompensable = fechafincompensable;
	}
	public java.util.Date getFechainiciocompensable() {
		return fechainiciocompensable;
	}
	public void setFechainiciocompensable(java.util.Date fechainiciocompensable) {
		this.fechainiciocompensable = fechainiciocompensable;
	}
	public String getFlagcompensable() {
		return flagcompensable;
	}
	public void setFlagcompensable(String flagcompensable) {
		this.flagcompensable = flagcompensable;
	}
	public BigDecimal getIniciocompensable() {
		return iniciocompensable;
	}
	public void setIniciocompensable(BigDecimal iniciocompensable) {
		this.iniciocompensable = iniciocompensable;
	}
	public java.util.Date getIniciocompensablefecha() {
		return iniciocompensablefecha;
	}
	public void setIniciocompensablefecha(java.util.Date iniciocompensablefecha) {
		this.iniciocompensablefecha = iniciocompensablefecha;
	}
	public String getPeriodoadescontar() {
		return periodoadescontar;
	}
	public void setPeriodoadescontar(String periodoadescontar) {
		this.periodoadescontar = periodoadescontar;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getTipoplanilla() {
		return tipoplanilla;
	}
	public void setTipoplanilla(String tipoplanilla) {
		this.tipoplanilla = tipoplanilla;
	}
	public java.math.BigDecimal getTopediascompensar() {
		return topediascompensar;
	}
	public void setTopediascompensar(java.math.BigDecimal topediascompensar) {
		this.topediascompensar = topediascompensar;
	}
	public String getFeriado() {
		return feriado;
	}
	public void setFeriado(String feriado) {
		this.feriado = feriado;
	}
	public Integer getCantidadFeriado() {
		return cantidadFeriado;
	}
	public void setCantidadFeriado(Integer cantidadFeriado) {
		this.cantidadFeriado = cantidadFeriado;
	}
	
	
}
