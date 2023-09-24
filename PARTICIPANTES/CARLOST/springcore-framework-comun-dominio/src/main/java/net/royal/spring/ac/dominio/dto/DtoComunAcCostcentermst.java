package net.royal.spring.ac.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunAcCostcentermst extends DominioTransaccion{
	// pk
	private String costcenter;
	
	// columnas
	private String localname;
	private String englishname;
	private String costcenterclasification;
	private String costcentergroup;
	private String costcentersubgroup;
	private String costcenterrelation;
	private String responsible;
	private String expensefinanceflag;
	private String expenseadministrativeflag;
	private String expensesalesflag;
	private String expenseproductionflag;
	private String costcenternext;
	private BigDecimal vendor;
	private BigDecimal amountinvoices;
	private BigDecimal amountrequisitions;
	private BigDecimal amountadvances;
	private BigDecimal amountothers01;
	private BigDecimal amountothers02;
	private BigDecimal amountothers03;
	private String vendorsignfile;
	private String account;
	private String status;
	private String lastuser;
	private java.util.Date lastdate;	
	
	private String incomeflag;
	private String accountinflation;
	private String expensefixedflag;
	private String expensedirectflag;
	private String sucursal;
	private String internalnumber;
	
	@JsonIgnore
	private BigDecimal ROWNUM_;

	public DtoComunAcCostcentermst() {}
	public DtoComunAcCostcentermst(String costcenter) {
		this.costcenter=costcenter;
	}
	
	public String getCostcenter() {
		return costcenter;
	}

	public void setCostcenter(String costcenter) {
		this.costcenter = costcenter;
	}

	public String getLocalname() {
		return localname;
	}

	public void setLocalname(String localname) {
		this.localname = localname;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

	public String getExpensefinanceflag() {
		return expensefinanceflag;
	}

	public void setExpensefinanceflag(String expensefinanceflag) {
		this.expensefinanceflag = expensefinanceflag;
	}

	public String getExpenseadministrativeflag() {
		return expenseadministrativeflag;
	}

	public void setExpenseadministrativeflag(String expenseadministrativeflag) {
		this.expenseadministrativeflag = expenseadministrativeflag;
	}

	public String getExpensesalesflag() {
		return expensesalesflag;
	}

	public void setExpensesalesflag(String expensesalesflag) {
		this.expensesalesflag = expensesalesflag;
	}

	public String getExpenseproductionflag() {
		return expenseproductionflag;
	}

	public void setExpenseproductionflag(String expenseproductionflag) {
		this.expenseproductionflag = expenseproductionflag;
	}

	public String getEnglishname() {
		return englishname;
	}

	public void setEnglishname(String englishname) {
		this.englishname = englishname;
	}

	public String getCostcenterclasification() {
		return costcenterclasification;
	}

	public void setCostcenterclasification(String costcenterclasification) {
		this.costcenterclasification = costcenterclasification;
	}

	public String getCostcentergroup() {
		return costcentergroup;
	}

	public void setCostcentergroup(String costcentergroup) {
		this.costcentergroup = costcentergroup;
	}

	public String getCostcentersubgroup() {
		return costcentersubgroup;
	}

	public void setCostcentersubgroup(String costcentersubgroup) {
		this.costcentersubgroup = costcentersubgroup;
	}

	public String getCostcenterrelation() {
		return costcenterrelation;
	}

	public void setCostcenterrelation(String costcenterrelation) {
		this.costcenterrelation = costcenterrelation;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public String getCostcenternext() {
		return costcenternext;
	}

	public void setCostcenternext(String costcenternext) {
		this.costcenternext = costcenternext;
	}

	public BigDecimal getVendor() {
		return vendor;
	}

	public void setVendor(BigDecimal vendor) {
		this.vendor = vendor;
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

	public String getVendorsignfile() {
		return vendorsignfile;
	}

	public void setVendorsignfile(String vendorsignfile) {
		this.vendorsignfile = vendorsignfile;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public String getIncomeflag() {
		return incomeflag;
	}

	public void setIncomeflag(String incomeflag) {
		this.incomeflag = incomeflag;
	}

	public String getAccountinflation() {
		return accountinflation;
	}

	public void setAccountinflation(String accountinflation) {
		this.accountinflation = accountinflation;
	}

	public String getExpensefixedflag() {
		return expensefixedflag;
	}

	public void setExpensefixedflag(String expensefixedflag) {
		this.expensefixedflag = expensefixedflag;
	}

	public String getExpensedirectflag() {
		return expensedirectflag;
	}

	public void setExpensedirectflag(String expensedirectflag) {
		this.expensedirectflag = expensedirectflag;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getInternalnumber() {
		return internalnumber;
	}

	public void setInternalnumber(String internalnumber) {
		this.internalnumber = internalnumber;
	}
	
}
