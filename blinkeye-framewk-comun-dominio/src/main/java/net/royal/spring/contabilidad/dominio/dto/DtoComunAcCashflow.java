package net.royal.spring.contabilidad.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunAcCashflow extends DominioTransaccion{
	
	private String tiporegistroflag;   
	private String cashflowmajor;   
	private String localname;
	private String cashflowgroup;
	private String localname1;
	private String cashflowcode;
	private String localname2;
	private String tipooperacionflag;
	private BigDecimal ROWNUM_;  
	
	
	public DtoComunAcCashflow() {}
	public DtoComunAcCashflow(String cashflowcode) {
		this.cashflowcode=cashflowcode;
	}

	public String getCashflowcode() {
		return cashflowcode;
	}

	public void setCashflowcode(String cashflowcode) {
		this.cashflowcode = cashflowcode;
	}
	public String getLocalname() {
		return localname;
	}

	public void setLocalname(String localname) {
		this.localname = localname;
	}
	
	public String getCashflowgroup() {
		return cashflowgroup;
	}

	public void setCashflowgroup(String cashflowgroup) {
		this.cashflowgroup = cashflowgroup;
	}

	public String getTiporegistroflag() {
		return tiporegistroflag;
	}
	public void setTiporegistroflag(String tiporegistroflag) {
		this.tiporegistroflag = tiporegistroflag;
	}
	public String getCashflowmajor() {
		return cashflowmajor;
	}
	public void setCashflowmajor(String cashflowmajor) {
		this.cashflowmajor = cashflowmajor;
	}
	public String getLocalname1() {
		return localname1;
	}
	public void setLocalname1(String localname1) {
		this.localname1 = localname1;
	}
	public String getLocalname2() {
		return localname2;
	}
	public void setLocalname2(String localname2) {
		this.localname2 = localname2;
	}
	public String getTipooperacionflag() {
		return tipooperacionflag;
	}
	public void setTipooperacionflag(String tipooperacionflag) {
		this.tipooperacionflag = tipooperacionflag;
	}
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	
}
