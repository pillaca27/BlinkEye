package net.royal.spring.core.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunTipodocumentocxp extends DominioTransaccion{
	// pk
	private String tipodocumento;
	
	// columnas
	private String descripcion;
	private String cuentacontablelocal;
	private String cuentacontabledolares;
	private String cuentacontableservlocal;
	private String cuentacontableservdolares;
	private String adelantocuentalocal;
	private String adelantocuentadolares;
	private String esadelanto;
	private String esfiscal;
	private String generarprovisionflag;
	private String adelantomultipleflag;
	private String codigofiscal;
	private String regimenfiscal;
	private String transacciondelsistemaflag;
	private String formatoedicion;
	private String vouchertipo;
	private String vouchertipomateriales;
	private String voucherclasificacion;
	private String controlpresupuestalflag;
	private String controlpresupaplicableflag;
	private String negativoflag;
	private String normalflag;
	private String estado;
	private java.util.Date ultimafechamodif;
	private String ultimousuario;
	private String interfasebancariaflag;
	private String clasificacion;
	private String retencionigvflag;
	private String codigosiaf;
	private String controlpresupuestalfiltroflag;
	private String descripcioningles;
	private String prepagorevisionflag;
	
	public DtoComunTipodocumentocxp() {}
	public DtoComunTipodocumentocxp(String tipodocumento) {
		this.tipodocumento=tipodocumento;
	}
	
	public String getTipodocumento() {
		return tipodocumento;
	}
	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public String getCuentacontableservlocal() {
		return cuentacontableservlocal;
	}
	public void setCuentacontableservlocal(String cuentacontableservlocal) {
		this.cuentacontableservlocal = cuentacontableservlocal;
	}
	public String getCuentacontableservdolares() {
		return cuentacontableservdolares;
	}
	public void setCuentacontableservdolares(String cuentacontableservdolares) {
		this.cuentacontableservdolares = cuentacontableservdolares;
	}
	public String getAdelantocuentalocal() {
		return adelantocuentalocal;
	}
	public void setAdelantocuentalocal(String adelantocuentalocal) {
		this.adelantocuentalocal = adelantocuentalocal;
	}
	public String getAdelantocuentadolares() {
		return adelantocuentadolares;
	}
	public void setAdelantocuentadolares(String adelantocuentadolares) {
		this.adelantocuentadolares = adelantocuentadolares;
	}
	public String getEsadelanto() {
		return esadelanto;
	}
	public void setEsadelanto(String esadelanto) {
		this.esadelanto = esadelanto;
	}
	public String getEsfiscal() {
		return esfiscal;
	}
	public void setEsfiscal(String esfiscal) {
		this.esfiscal = esfiscal;
	}
	public String getGenerarprovisionflag() {
		return generarprovisionflag;
	}
	public void setGenerarprovisionflag(String generarprovisionflag) {
		this.generarprovisionflag = generarprovisionflag;
	}
	public String getAdelantomultipleflag() {
		return adelantomultipleflag;
	}
	public void setAdelantomultipleflag(String adelantomultipleflag) {
		this.adelantomultipleflag = adelantomultipleflag;
	}
	public String getCodigofiscal() {
		return codigofiscal;
	}
	public void setCodigofiscal(String codigofiscal) {
		this.codigofiscal = codigofiscal;
	}
	public String getRegimenfiscal() {
		return regimenfiscal;
	}
	public void setRegimenfiscal(String regimenfiscal) {
		this.regimenfiscal = regimenfiscal;
	}
	public String getTransacciondelsistemaflag() {
		return transacciondelsistemaflag;
	}
	public void setTransacciondelsistemaflag(String transacciondelsistemaflag) {
		this.transacciondelsistemaflag = transacciondelsistemaflag;
	}
	public String getFormatoedicion() {
		return formatoedicion;
	}
	public void setFormatoedicion(String formatoedicion) {
		this.formatoedicion = formatoedicion;
	}
	public String getVouchertipo() {
		return vouchertipo;
	}
	public void setVouchertipo(String vouchertipo) {
		this.vouchertipo = vouchertipo;
	}
	public String getVouchertipomateriales() {
		return vouchertipomateriales;
	}
	public void setVouchertipomateriales(String vouchertipomateriales) {
		this.vouchertipomateriales = vouchertipomateriales;
	}
	public String getVoucherclasificacion() {
		return voucherclasificacion;
	}
	public void setVoucherclasificacion(String voucherclasificacion) {
		this.voucherclasificacion = voucherclasificacion;
	}
	public String getControlpresupuestalflag() {
		return controlpresupuestalflag;
	}
	public void setControlpresupuestalflag(String controlpresupuestalflag) {
		this.controlpresupuestalflag = controlpresupuestalflag;
	}
	public String getControlpresupaplicableflag() {
		return controlpresupaplicableflag;
	}
	public void setControlpresupaplicableflag(String controlpresupaplicableflag) {
		this.controlpresupaplicableflag = controlpresupaplicableflag;
	}
	public String getNegativoflag() {
		return negativoflag;
	}
	public void setNegativoflag(String negativoflag) {
		this.negativoflag = negativoflag;
	}
	public String getNormalflag() {
		return normalflag;
	}
	public void setNormalflag(String normalflag) {
		this.normalflag = normalflag;
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
	public String getInterfasebancariaflag() {
		return interfasebancariaflag;
	}
	public void setInterfasebancariaflag(String interfasebancariaflag) {
		this.interfasebancariaflag = interfasebancariaflag;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public String getRetencionigvflag() {
		return retencionigvflag;
	}
	public void setRetencionigvflag(String retencionigvflag) {
		this.retencionigvflag = retencionigvflag;
	}
	public String getCodigosiaf() {
		return codigosiaf;
	}
	public void setCodigosiaf(String codigosiaf) {
		this.codigosiaf = codigosiaf;
	}
	public String getControlpresupuestalfiltroflag() {
		return controlpresupuestalfiltroflag;
	}
	public void setControlpresupuestalfiltroflag(String controlpresupuestalfiltroflag) {
		this.controlpresupuestalfiltroflag = controlpresupuestalfiltroflag;
	}
	public String getDescripcioningles() {
		return descripcioningles;
	}
	public void setDescripcioningles(String descripcioningles) {
		this.descripcioningles = descripcioningles;
	}
	public String getPrepagorevisionflag() {
		return prepagorevisionflag;
	}
	public void setPrepagorevisionflag(String prepagorevisionflag) {
		this.prepagorevisionflag = prepagorevisionflag;
	}
	
	
}
