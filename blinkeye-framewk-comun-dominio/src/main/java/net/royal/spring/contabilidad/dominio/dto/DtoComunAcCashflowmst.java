package net.royal.spring.contabilidad.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunAcCashflowmst extends DominioTransaccion{
	//pk
	private String cashflowcode;
	
	//campos
	private String localname;
	private String englishname;
	private String cashflowgroup;
	private String internalcode;
	private String status;
	private String lastuser;
	private java.util.Date lastdate;
	private String excluirflag;
	
	public DtoComunAcCashflowmst() {}
	public DtoComunAcCashflowmst(String cashflowcode) {
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
	public String getEnglishname() {
		return englishname;
	}

	public void setEnglishname(String englishname) {
		this.englishname = englishname;
	}
	public String getCashflowgroup() {
		return cashflowgroup;
	}

	public void setCashflowgroup(String cashflowgroup) {
		this.cashflowgroup = cashflowgroup;
	}
	public String getInternalcode() {
		return internalcode;
	}

	public void setInternalcode(String internalcode) {
		this.internalcode = internalcode;
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
	public String getExcluirflag() {
		return excluirflag;
	}

	public void setExcluirflag(String excluirflag) {
		this.excluirflag = excluirflag;
	}
	
}
