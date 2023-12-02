package net.royal.spring.core.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunResponsiblemst  extends DominioTransaccion{
	private String responsible;
	private String nextresponsible;
	private String localname;
	private String englishname;
	private BigDecimal vendor;
	private BigDecimal responsabilitylevel;
	private String status;
	private String lastuser;
	private java.util.Date lastdate;
	private BigDecimal authorizedamount;
	private BigDecimal amountinvoices;
	private BigDecimal amountrequisitions;
	private BigDecimal amountadvances;
	private BigDecimal amountothers01;
	private BigDecimal amountothers02;
	private BigDecimal amountothers03;
	public String getResponsible() {
		return responsible;
	}
	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}
	public String getNextresponsible() {
		return nextresponsible;
	}
	public void setNextresponsible(String nextresponsible) {
		this.nextresponsible = nextresponsible;
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
	public BigDecimal getVendor() {
		return vendor;
	}
	public void setVendor(BigDecimal vendor) {
		this.vendor = vendor;
	}
	public BigDecimal getResponsabilitylevel() {
		return responsabilitylevel;
	}
	public void setResponsabilitylevel(BigDecimal responsabilitylevel) {
		this.responsabilitylevel = responsabilitylevel;
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
	public BigDecimal getAuthorizedamount() {
		return authorizedamount;
	}
	public void setAuthorizedamount(BigDecimal authorizedamount) {
		this.authorizedamount = authorizedamount;
	}
	public BigDecimal getAmountinvoices() {
		return amountinvoices;
	}
	public void setAmountinvoices(BigDecimal amountinvoices) {
		this.amountinvoices = amountinvoices;
	}
	public BigDecimal getAmountrequisitions() {
		return amountrequisitions;
	}
	public void setAmountrequisitions(BigDecimal amountrequisitions) {
		this.amountrequisitions = amountrequisitions;
	}
	public BigDecimal getAmountadvances() {
		return amountadvances;
	}
	public void setAmountadvances(BigDecimal amountadvances) {
		this.amountadvances = amountadvances;
	}
	public BigDecimal getAmountothers01() {
		return amountothers01;
	}
	public void setAmountothers01(BigDecimal amountothers01) {
		this.amountothers01 = amountothers01;
	}
	public BigDecimal getAmountothers02() {
		return amountothers02;
	}
	public void setAmountothers02(BigDecimal amountothers02) {
		this.amountothers02 = amountothers02;
	}
	public BigDecimal getAmountothers03() {
		return amountothers03;
	}
	public void setAmountothers03(BigDecimal amountothers03) {
		this.amountothers03 = amountothers03;
	}
	
	
}
