package net.royal.spring.planilla.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunPrTipoprestamo extends DominioTransaccion{
	private String tipoprestamo;
	private String concepto;
	private String descripcion;
	private BigDecimal minimoano;
	private BigDecimal minimosueldo;
	private String monedasueldo;
	private java.math.BigDecimal tasainteres;
	private String flagvacacional;
	private String estado;
	private BigDecimal orden;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String flagrequiereaprobacion;
	private String cuentadebe;
	private String cuentahaber;
	private String flagtipoproceso;
	private String tipoproceso;
	private String conceptointeres;
	private String conceptoamortizacion;
	private String flagcxp;
	private String flagdesembolsable;
	private String cuentaobligacion;
	private BigDecimal proveedor;
	
	public DtoComunPrTipoprestamo() {}
	public DtoComunPrTipoprestamo(String tipoprestamo) {
		this.tipoprestamo=tipoprestamo;
	}
	
	public String getTipoprestamo() {
		return tipoprestamo;
	}
	public void setTipoprestamo(String tipoprestamo) {
		this.tipoprestamo = tipoprestamo;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getMinimoano() {
		return minimoano;
	}
	public void setMinimoano(BigDecimal minimoano) {
		this.minimoano = minimoano;
	}
	public BigDecimal getMinimosueldo() {
		return minimosueldo;
	}
	public void setMinimosueldo(BigDecimal minimosueldo) {
		this.minimosueldo = minimosueldo;
	}
	public String getMonedasueldo() {
		return monedasueldo;
	}
	public void setMonedasueldo(String monedasueldo) {
		this.monedasueldo = monedasueldo;
	}
	public java.math.BigDecimal getTasainteres() {
		return tasainteres;
	}
	public void setTasainteres(java.math.BigDecimal tasainteres) {
		this.tasainteres = tasainteres;
	}
	public String getFlagvacacional() {
		return flagvacacional;
	}
	public void setFlagvacacional(String flagvacacional) {
		this.flagvacacional = flagvacacional;
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
	public String getFlagrequiereaprobacion() {
		return flagrequiereaprobacion;
	}
	public void setFlagrequiereaprobacion(String flagrequiereaprobacion) {
		this.flagrequiereaprobacion = flagrequiereaprobacion;
	}
	public String getCuentadebe() {
		return cuentadebe;
	}
	public void setCuentadebe(String cuentadebe) {
		this.cuentadebe = cuentadebe;
	}
	public String getCuentahaber() {
		return cuentahaber;
	}
	public void setCuentahaber(String cuentahaber) {
		this.cuentahaber = cuentahaber;
	}
	public String getFlagtipoproceso() {
		return flagtipoproceso;
	}
	public void setFlagtipoproceso(String flagtipoproceso) {
		this.flagtipoproceso = flagtipoproceso;
	}
	public String getTipoproceso() {
		return tipoproceso;
	}
	public void setTipoproceso(String tipoproceso) {
		this.tipoproceso = tipoproceso;
	}
	public String getConceptointeres() {
		return conceptointeres;
	}
	public void setConceptointeres(String conceptointeres) {
		this.conceptointeres = conceptointeres;
	}
	public String getConceptoamortizacion() {
		return conceptoamortizacion;
	}
	public void setConceptoamortizacion(String conceptoamortizacion) {
		this.conceptoamortizacion = conceptoamortizacion;
	}
	public String getFlagcxp() {
		return flagcxp;
	}
	public void setFlagcxp(String flagcxp) {
		this.flagcxp = flagcxp;
	}
	public String getFlagdesembolsable() {
		return flagdesembolsable;
	}
	public void setFlagdesembolsable(String flagdesembolsable) {
		this.flagdesembolsable = flagdesembolsable;
	}
	public String getCuentaobligacion() {
		return cuentaobligacion;
	}
	public void setCuentaobligacion(String cuentaobligacion) {
		this.cuentaobligacion = cuentaobligacion;
	}
	public BigDecimal getProveedor() {
		return proveedor;
	}
	public void setProveedor(BigDecimal proveedor) {
		this.proveedor = proveedor;
	}	
}
