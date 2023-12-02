package net.royal.spring.tesoreria.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunApConceptogasto extends DominioTransaccion {
	private String conceptogasto;
	private String conceptogastogrupo;
	private String descripcionlocal;
	private String descripcioningles;
	private String descripcionrequeridaflag;
	private String elementogasto;
	private String cuentacontable;
	private String frecuencia;
	private String importacionflag;
	private BigDecimal porcentaje;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String empleadoautorizadoflag;
	private String viajeflag;
	private String monedacodigo;
	private BigDecimal monto;
	private String viajealimentosflag;
	private String gastoflag;
	private String referenciafiscal02;
	private String tipocentrocosto;
	private String xcentrocosto;
	private String cuentacontableextranjera;
	private String itemrequeridoflag;
	private String conceptogastoprincipal;
	private String commodity;
	/* solo sirve para la paginacion */
	@JsonIgnore
	private BigDecimal ROWNUM_;
	
	public DtoComunApConceptogasto() {}
	public DtoComunApConceptogasto(String conceptogasto) {
		this.conceptogasto=conceptogasto;
	}
	public DtoComunApConceptogasto(String conceptogasto,String monedacodigo) {
		this.conceptogasto=conceptogasto;
		this.monedacodigo=monedacodigo;
	}
	
	public String getConceptogasto() {
		return conceptogasto;
	}
	public void setConceptogasto(String conceptogasto) {
		this.conceptogasto = conceptogasto;
	}
	public String getConceptogastogrupo() {
		return conceptogastogrupo;
	}
	public void setConceptogastogrupo(String conceptogastogrupo) {
		this.conceptogastogrupo = conceptogastogrupo;
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
	public String getDescripcionrequeridaflag() {
		return descripcionrequeridaflag;
	}
	public void setDescripcionrequeridaflag(String descripcionrequeridaflag) {
		this.descripcionrequeridaflag = descripcionrequeridaflag;
	}
	public String getElementogasto() {
		return elementogasto;
	}
	public void setElementogasto(String elementogasto) {
		this.elementogasto = elementogasto;
	}
	public String getCuentacontable() {
		return cuentacontable;
	}
	public void setCuentacontable(String cuentacontable) {
		this.cuentacontable = cuentacontable;
	}
	public String getFrecuencia() {
		return frecuencia;
	}
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}
	public String getImportacionflag() {
		return importacionflag;
	}
	public void setImportacionflag(String importacionflag) {
		this.importacionflag = importacionflag;
	}
	public BigDecimal getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
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
	public String getEmpleadoautorizadoflag() {
		return empleadoautorizadoflag;
	}
	public void setEmpleadoautorizadoflag(String empleadoautorizadoflag) {
		this.empleadoautorizadoflag = empleadoautorizadoflag;
	}
	public String getViajeflag() {
		return viajeflag;
	}
	public void setViajeflag(String viajeflag) {
		this.viajeflag = viajeflag;
	}
	public String getMonedacodigo() {
		return monedacodigo;
	}
	public void setMonedacodigo(String monedacodigo) {
		this.monedacodigo = monedacodigo;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public String getViajealimentosflag() {
		return viajealimentosflag;
	}
	public void setViajealimentosflag(String viajealimentosflag) {
		this.viajealimentosflag = viajealimentosflag;
	}
	public String getGastoflag() {
		return gastoflag;
	}
	public void setGastoflag(String gastoflag) {
		this.gastoflag = gastoflag;
	}
	public String getReferenciafiscal02() {
		return referenciafiscal02;
	}
	public void setReferenciafiscal02(String referenciafiscal02) {
		this.referenciafiscal02 = referenciafiscal02;
	}
	public String getTipocentrocosto() {
		return tipocentrocosto;
	}
	public void setTipocentrocosto(String tipocentrocosto) {
		this.tipocentrocosto = tipocentrocosto;
	}
	public String getXcentrocosto() {
		return xcentrocosto;
	}
	public void setXcentrocosto(String xcentrocosto) {
		this.xcentrocosto = xcentrocosto;
	}
	public String getCuentacontableextranjera() {
		return cuentacontableextranjera;
	}
	public void setCuentacontableextranjera(String cuentacontableextranjera) {
		this.cuentacontableextranjera = cuentacontableextranjera;
	}
	public String getItemrequeridoflag() {
		return itemrequeridoflag;
	}
	public void setItemrequeridoflag(String itemrequeridoflag) {
		this.itemrequeridoflag = itemrequeridoflag;
	}
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public String getConceptogastoprincipal() {
		return conceptogastoprincipal;
	}
	public void setConceptogastoprincipal(String conceptogastoprincipal) {
		this.conceptogastoprincipal = conceptogastoprincipal;
	}
	public String getCommodity() {
		return commodity;
	}
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}
	
	
}
