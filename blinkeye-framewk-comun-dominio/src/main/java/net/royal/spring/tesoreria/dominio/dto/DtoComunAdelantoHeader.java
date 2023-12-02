package net.royal.spring.tesoreria.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DtoComunAdelantoHeader implements java.io.Serializable {

	private String periodo;   
	private String moneda;
	private String companiasocio;
	private String tipogasto;
	private String numeroproceso;
    private Date fechaproceso; 
    private Date fechaentrega;
    private String centrocosto;
    private BigDecimal preparadopor;
    private BigDecimal montoadelanto;
    private BigDecimal montosustento;
    private String observaciones;
    private String referenciafiscal01;   
    private String busqueda;
    private String localname;
    private BigDecimal ROWNUM_;
    
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getCompaniasocio() {
		return companiasocio;
	}
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}
	public String getTipogasto() {
		return tipogasto;
	}
	public void setTipogasto(String tipogasto) {
		this.tipogasto = tipogasto;
	}
	public String getNumeroproceso() {
		return numeroproceso;
	}
	public void setNumeroproceso(String numeroproceso) {
		this.numeroproceso = numeroproceso;
	}
	public Date getFechaproceso() {
		return fechaproceso;
	}
	public void setFechaproceso(Date fechaproceso) {
		this.fechaproceso = fechaproceso;
	}
	public Date getFechaentrega() {
		return fechaentrega;
	}
	public void setFechaentrega(Date fechaentrega) {
		this.fechaentrega = fechaentrega;
	}
	public String getCentrocosto() {
		return centrocosto;
	}
	public void setCentrocosto(String centrocosto) {
		this.centrocosto = centrocosto;
	}
	public BigDecimal getPreparadopor() {
		return preparadopor;
	}
	public void setPreparadopor(BigDecimal preparadopor) {
		this.preparadopor = preparadopor;
	}
	public BigDecimal getMontoadelanto() {
		return montoadelanto;
	}
	public void setMontoadelanto(BigDecimal montoadelanto) {
		this.montoadelanto = montoadelanto;
	}
	public BigDecimal getMontosustento() {
		return montosustento;
	}
	public void setMontosustento(BigDecimal montosustento) {
		this.montosustento = montosustento;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getReferenciafiscal01() {
		return referenciafiscal01;
	}
	public void setReferenciafiscal01(String referenciafiscal01) {
		this.referenciafiscal01 = referenciafiscal01;
	}
	public String getBusqueda() {
		return busqueda;
	}
	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}
	public String getLocalname() {
		return localname;
	}
	public void setLocalname(String localname) {
		this.localname = localname;
	}
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
    
    
	
}
