package net.royal.spring.core.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunImpuestos extends DominioTransaccion{
	// pk
	private String impuesto;
	
	// campos
	private String descripcion;
	private String tipoimpuesto;
	private BigDecimal factorporcentaje;
	private String cuentacontablelocal;
	private String cuentacontabledolares;
	private String signo;
	private String exportableflag;
	private String exportabletipodecambio;
	private String certificadorentaflag;
	private String estado;
	private java.util.Date ultimafechamodif;
	private String ultimousuario;
	private String montonoafectoflag;
	private String cuentacontablerevlocal;
	private String cuentacontablerevdolares;
	private String voucherprovisionflag;
	private String montoimponibleflag;
	private BigDecimal montoexonerado;
	
	public DtoComunImpuestos() {}
	public DtoComunImpuestos(String impuesto) {
		this.impuesto=impuesto;
	}
	
	public String getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipoimpuesto() {
		return tipoimpuesto;
	}
	public void setTipoimpuesto(String tipoimpuesto) {
		this.tipoimpuesto = tipoimpuesto;
	}
	public BigDecimal getFactorporcentaje() {
		return factorporcentaje;
	}
	public void setFactorporcentaje(BigDecimal factorporcentaje) {
		this.factorporcentaje = factorporcentaje;
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
	public String getSigno() {
		return signo;
	}
	public void setSigno(String signo) {
		this.signo = signo;
	}
	public String getExportableflag() {
		return exportableflag;
	}
	public void setExportableflag(String exportableflag) {
		this.exportableflag = exportableflag;
	}
	public String getExportabletipodecambio() {
		return exportabletipodecambio;
	}
	public void setExportabletipodecambio(String exportabletipodecambio) {
		this.exportabletipodecambio = exportabletipodecambio;
	}
	public String getCertificadorentaflag() {
		return certificadorentaflag;
	}
	public void setCertificadorentaflag(String certificadorentaflag) {
		this.certificadorentaflag = certificadorentaflag;
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
	public String getMontonoafectoflag() {
		return montonoafectoflag;
	}
	public void setMontonoafectoflag(String montonoafectoflag) {
		this.montonoafectoflag = montonoafectoflag;
	}
	public String getCuentacontablerevlocal() {
		return cuentacontablerevlocal;
	}
	public void setCuentacontablerevlocal(String cuentacontablerevlocal) {
		this.cuentacontablerevlocal = cuentacontablerevlocal;
	}
	public String getCuentacontablerevdolares() {
		return cuentacontablerevdolares;
	}
	public void setCuentacontablerevdolares(String cuentacontablerevdolares) {
		this.cuentacontablerevdolares = cuentacontablerevdolares;
	}
	public String getVoucherprovisionflag() {
		return voucherprovisionflag;
	}
	public void setVoucherprovisionflag(String voucherprovisionflag) {
		this.voucherprovisionflag = voucherprovisionflag;
	}
	public String getMontoimponibleflag() {
		return montoimponibleflag;
	}
	public void setMontoimponibleflag(String montoimponibleflag) {
		this.montoimponibleflag = montoimponibleflag;
	}
	public BigDecimal getMontoexonerado() {
		return montoexonerado;
	}
	public void setMontoexonerado(BigDecimal montoexonerado) {
		this.montoexonerado = montoexonerado;
	}
}
