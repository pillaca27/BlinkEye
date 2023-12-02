package net.royal.spring.contabilidad.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunAcCashflowmajor extends DominioTransaccion{
	private String cashflowmajor;
	private String localname;
	private String englishname;
	private String tiporegistroflag;
	private String tipooperacionflag;
	private String status;
	private String lastuser;
	private java.util.Date lastdate;
	public String getCashflowmajor() {
		return cashflowmajor;
	}
	public void setCashflowmajor(String cashflowmajor) {
		this.cashflowmajor = cashflowmajor;
	}
	public String getLocalname() {
		return localname;
	}
	public void setLocalname(String localname) {
		this.localname = localname;
	}
	public String getEnglishname() {
		return englishname;
	}
	public void setEnglishname(String englishname) {
		this.englishname = englishname;
	}
	public String getTiporegistroflag() {
		return tiporegistroflag;
	}
	public void setTiporegistroflag(String tiporegistroflag) {
		this.tiporegistroflag = tiporegistroflag;
	}
	public String getTipooperacionflag() {
		return tipooperacionflag;
	}
	public void setTipooperacionflag(String tipooperacionflag) {
		this.tipooperacionflag = tipooperacionflag;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLastuser() {
		return lastuser;
	}
	public void setLastuser(String lastuser) {
		this.lastuser = lastuser;
	}
	public java.util.Date getLastdate() {
		return lastdate;
	}
	public void setLastdate(java.util.Date lastdate) {
		this.lastdate = lastdate;
	}
	
}
