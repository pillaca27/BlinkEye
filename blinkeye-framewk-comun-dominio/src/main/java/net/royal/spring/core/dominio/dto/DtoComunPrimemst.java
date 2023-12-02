package net.royal.spring.core.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunPrimemst extends DominioTransaccion {
	private String prime;
	private BigDecimal digitsnumber;
	private String localname;
	private String englishname;
	private String primetype;
	private String elementrevaluation;
	private String responsible;
	private String costcenterflag;
	private String budgetflag;
	private String primegroup;
	private String status;
	private String lastuser;
	private java.util.Date lastdate;
	private String prime2;
	private String costproductionclasification;
	private String budgetincomeflag;
	private String budgetcostflag;
	private String inventorydetailflag;
	private String codigofiscal;
	private BigDecimal ROWNUM_;
	
	public DtoComunPrimemst() {}
	public DtoComunPrimemst(String prime) {
		this.prime=prime;
	}
	
	public String getPrime() {
		return prime;
	}
	public void setPrime(String prime) {
		this.prime = prime;
	}
	public BigDecimal getDigitsnumber() {
		return digitsnumber;
	}
	public void setDigitsnumber(BigDecimal digitsnumber) {
		this.digitsnumber = digitsnumber;
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
	public String getPrimetype() {
		return primetype;
	}
	public void setPrimetype(String primetype) {
		this.primetype = primetype;
	}
	public String getElementrevaluation() {
		return elementrevaluation;
	}
	public void setElementrevaluation(String elementrevaluation) {
		this.elementrevaluation = elementrevaluation;
	}
	public String getResponsible() {
		return responsible;
	}
	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}
	public String getCostcenterflag() {
		return costcenterflag;
	}
	public void setCostcenterflag(String costcenterflag) {
		this.costcenterflag = costcenterflag;
	}
	public String getBudgetflag() {
		return budgetflag;
	}
	public void setBudgetflag(String budgetflag) {
		this.budgetflag = budgetflag;
	}
	public String getPrimegroup() {
		return primegroup;
	}
	public void setPrimegroup(String primegroup) {
		this.primegroup = primegroup;
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
	public String getPrime2() {
		return prime2;
	}
	public void setPrime2(String prime2) {
		this.prime2 = prime2;
	}
	public String getCostproductionclasification() {
		return costproductionclasification;
	}
	public void setCostproductionclasification(String costproductionclasification) {
		this.costproductionclasification = costproductionclasification;
	}
	public String getBudgetincomeflag() {
		return budgetincomeflag;
	}
	public void setBudgetincomeflag(String budgetincomeflag) {
		this.budgetincomeflag = budgetincomeflag;
	}
	public String getBudgetcostflag() {
		return budgetcostflag;
	}
	public void setBudgetcostflag(String budgetcostflag) {
		this.budgetcostflag = budgetcostflag;
	}
	public String getInventorydetailflag() {
		return inventorydetailflag;
	}
	public void setInventorydetailflag(String inventorydetailflag) {
		this.inventorydetailflag = inventorydetailflag;
	}
	public String getCodigofiscal() {
		return codigofiscal;
	}
	public void setCodigofiscal(String codigofiscal) {
		this.codigofiscal = codigofiscal;
	}
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	
}
