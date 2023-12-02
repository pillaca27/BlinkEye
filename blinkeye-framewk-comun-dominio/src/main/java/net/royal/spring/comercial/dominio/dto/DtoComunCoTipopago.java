package net.royal.spring.comercial.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunCoTipopago extends DominioTransaccion{
	private String tipopago;
	private String descripcionlocal;
	private String descripcioningles;
	private String utilizartarjetacreditoflag;
	private String utilizardocumentoflag;
	private String utilizarbancoflag;
	private String conciliableflag;
	private String cuentacontablelocal;
	private String cuentacontabledolares;
	private String retencionflag;
	private BigDecimal retencionporcentaje;
	private String retencioncuentabancaria;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
		
	public String getTipopago() {
		return tipopago;
	}
	public void setTipopago(String tipopago) {
		this.tipopago = tipopago;
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
	public String getUtilizartarjetacreditoflag() {
		return utilizartarjetacreditoflag;
	}
	public void setUtilizartarjetacreditoflag(String utilizartarjetacreditoflag) {
		this.utilizartarjetacreditoflag = utilizartarjetacreditoflag;
	}
	public String getUtilizardocumentoflag() {
		return utilizardocumentoflag;
	}
	public void setUtilizardocumentoflag(String utilizardocumentoflag) {
		this.utilizardocumentoflag = utilizardocumentoflag;
	}
	public String getUtilizarbancoflag() {
		return utilizarbancoflag;
	}
	public void setUtilizarbancoflag(String utilizarbancoflag) {
		this.utilizarbancoflag = utilizarbancoflag;
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
	public String getConciliableflag() {
		return conciliableflag;
	}
	public void setConciliableflag(String conciliableflag) {
		this.conciliableflag = conciliableflag;
	}
	public String getCuentacontablelocal() {
		return cuentacontablelocal;
	}
	public void setCuentacontablelocal(String cuentacontablelocal) {
		this.cuentacontablelocal = cuentacontablelocal;
	}
	public String getCuentacontabledolares() {
		return cuentacontabledolares;
	}
	public void setCuentacontabledolares(String cuentacontabledolares) {
		this.cuentacontabledolares = cuentacontabledolares;
	}
	public String getRetencionflag() {
		return retencionflag;
	}
	public void setRetencionflag(String retencionflag) {
		this.retencionflag = retencionflag;
	}
	public BigDecimal getRetencionporcentaje() {
		return retencionporcentaje;
	}
	public void setRetencionporcentaje(BigDecimal retencionporcentaje) {
		this.retencionporcentaje = retencionporcentaje;
	}
	public String getRetencioncuentabancaria() {
		return retencioncuentabancaria;
	}
	public void setRetencioncuentabancaria(String retencioncuentabancaria) {
		this.retencioncuentabancaria = retencioncuentabancaria;
	}
	
	
}
