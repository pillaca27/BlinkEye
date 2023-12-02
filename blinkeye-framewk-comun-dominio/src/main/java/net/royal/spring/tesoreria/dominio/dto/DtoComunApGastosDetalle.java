package net.royal.spring.tesoreria.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunApGastosDetalle implements java.io.Serializable {

	private String companiasocio;
	private String tipogasto;
	private String numeroliquidacion;
	private BigDecimal secuencia;
	private BigDecimal montomonedapago;
	  private String tipoimpuestoflag;
	  private String documentofiscal;
	  private BigDecimal proveedor;
	  private String proveedornombre;
	  private String tipodocumento;
	  private String numerodocumento;
	  private String monedadocumento;
	  private Date fechadocumento;
	  private BigDecimal tipocambio;
	  private BigDecimal montoimpuesto;
	  private String centrocosto;
	  private String retencionflag;
	  private BigDecimal retencionmonto;
	  private String pagoflag;
	  private String retencionpagoflag;
	
	private BigDecimal ROWNUM_;

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

	public String getNumeroliquidacion() {
		return numeroliquidacion;
	}

	public void setNumeroliquidacion(String numeroliquidacion) {
		this.numeroliquidacion = numeroliquidacion;
	}

	public BigDecimal getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(BigDecimal secuencia) {
		this.secuencia = secuencia;
	}

	public BigDecimal getMontomonedapago() {
		return montomonedapago;
	}

	public void setMontomonedapago(BigDecimal montomonedapago) {
		this.montomonedapago = montomonedapago;
	}

	public String getTipoimpuestoflag() {
		return tipoimpuestoflag;
	}

	public void setTipoimpuestoflag(String tipoimpuestoflag) {
		this.tipoimpuestoflag = tipoimpuestoflag;
	}

	public String getDocumentofiscal() {
		return documentofiscal;
	}

	public void setDocumentofiscal(String documentofiscal) {
		this.documentofiscal = documentofiscal;
	}

	public BigDecimal getProveedor() {
		return proveedor;
	}

	public void setProveedor(BigDecimal proveedor) {
		this.proveedor = proveedor;
	}

	public String getProveedornombre() {
		return proveedornombre;
	}

	public void setProveedornombre(String proveedornombre) {
		this.proveedornombre = proveedornombre;
	}

	public String getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public String getNumerodocumento() {
		return numerodocumento;
	}

	public void setNumerodocumento(String numerodocumento) {
		this.numerodocumento = numerodocumento;
	}

	public String getMonedadocumento() {
		return monedadocumento;
	}

	public void setMonedadocumento(String monedadocumento) {
		this.monedadocumento = monedadocumento;
	}

	public Date getFechadocumento() {
		return fechadocumento;
	}

	public void setFechadocumento(Date fechadocumento) {
		this.fechadocumento = fechadocumento;
	}

	public BigDecimal getTipocambio() {
		return tipocambio;
	}

	public void setTipocambio(BigDecimal tipocambio) {
		this.tipocambio = tipocambio;
	}

	public BigDecimal getMontoimpuesto() {
		return montoimpuesto;
	}

	public void setMontoimpuesto(BigDecimal montoimpuesto) {
		this.montoimpuesto = montoimpuesto;
	}

	public String getCentrocosto() {
		return centrocosto;
	}

	public void setCentrocosto(String centrocosto) {
		this.centrocosto = centrocosto;
	}

	public String getRetencionflag() {
		return retencionflag;
	}

	public void setRetencionflag(String retencionflag) {
		this.retencionflag = retencionflag;
	}

	public BigDecimal getRetencionmonto() {
		return retencionmonto;
	}

	public void setRetencionmonto(BigDecimal retencionmonto) {
		this.retencionmonto = retencionmonto;
	}

	public String getPagoflag() {
		return pagoflag;
	}

	public void setPagoflag(String pagoflag) {
		this.pagoflag = pagoflag;
	}

	public String getRetencionpagoflag() {
		return retencionpagoflag;
	}

	public void setRetencionpagoflag(String retencionpagoflag) {
		this.retencionpagoflag = retencionpagoflag;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	
}
