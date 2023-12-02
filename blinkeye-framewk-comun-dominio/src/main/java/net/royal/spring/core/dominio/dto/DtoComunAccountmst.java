package net.royal.spring.core.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunAccountmst  extends DominioTransaccion{
	// pk
	private String account;
	
	// campos
	private String prime;
	private String element;
	private String localname;
	private String englishname;
	private String afevalidation;
	private String commitmentvalidation;
	private String commitmentexpenseflag;
	private String exchangepdvalidation;
	private String intercompanyvalidation;
	private String vendorvalidation;
	private String invoicevalidation;
	private String checkvalidation;
	private String datevalidation;
	private String costcentervalidation;
	private String costcenterdestvalidation;
	private String subledgervalidation;
	private String cashflowvalidation;
	private String code01validation;
	private String code02validation;
	private String code03validation;
	private String code04validation;
	private String code05validation;
	private String code06validation;
	private String code07validation;
	private String code08validation;
	private String code09validation;
	private String code10validation;
	private String revaluationindicator;
	private String period13indicator;
	private String period14indicator;
	private String period16indicator;
	private String accountcurrency;
	private String accountdestination;
	private String accountdestinationmaterials;
	private String accountoffset;
	private String accountoffsetmaterials;
	private String elementsubindicator;
	private String vendortypeflag;
	private String multicompanyflag;
	private String inflationadjustmentflag;
	private String statisticsgenerationflag;
	private String cashflowcodedefault;
	private String budgetcategorydefault;
	private String responsibledefault;
	private String expensefinanceflag;
	private String expenseadministrativeflag;
	private String expensesalesflag;
	private String expenseproductionflag;
	private String accountinflation;
	private String accountinflation02;
	private String accountrelation;
	private String accountgroup;
	private String invoiceclasification;
	private String accounttype;
	private String costproductionclasification;
	private String billingprocessflag;
	private String billingaccountlocal;
	private String billingaccountdollar;
	private String overheadprocessflag;
	private String status;
	private String lastuser;
	private java.util.Date lastdate;
	private String statutoryprime;
	private String vendorusertype;
	private String profitlossflag;
	private String expensefixedflag;
	private String expensedirectflag;
	private String sucursalvalidation;
	private String afedefault;
	private String fasb52flag;
	private String fiscalreferencevalidation;
	private String inflationadjustmentafeflag;
	private String cpautomaticrequflag;
	private String cpautomaticinvoflag;
	private String budgetexcluirflag;
	private String accountPcge;
	private String accountOld;
	private String budgetcategorydefault2;
	private String sunatcostclasification;
	private String cashflowdirectfiscal;
	private String plancontable;
	@JsonIgnore
	private BigDecimal ROWNUM_;
	
	public DtoComunAccountmst() {}
	public DtoComunAccountmst(String account) {
		this.account=account;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPrime() {
		return prime;
	}
	public void setPrime(String prime) {
		this.prime = prime;
	}
	public String getElement() {
		return element;
	}
	public void setElement(String element) {
		this.element = element;
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
	public String getAfevalidation() {
		return afevalidation;
	}
	public void setAfevalidation(String afevalidation) {
		this.afevalidation = afevalidation;
	}
	public String getCommitmentvalidation() {
		return commitmentvalidation;
	}
	public void setCommitmentvalidation(String commitmentvalidation) {
		this.commitmentvalidation = commitmentvalidation;
	}
	public String getCommitmentexpenseflag() {
		return commitmentexpenseflag;
	}
	public void setCommitmentexpenseflag(String commitmentexpenseflag) {
		this.commitmentexpenseflag = commitmentexpenseflag;
	}
	public String getExchangepdvalidation() {
		return exchangepdvalidation;
	}
	public void setExchangepdvalidation(String exchangepdvalidation) {
		this.exchangepdvalidation = exchangepdvalidation;
	}
	public String getIntercompanyvalidation() {
		return intercompanyvalidation;
	}
	public void setIntercompanyvalidation(String intercompanyvalidation) {
		this.intercompanyvalidation = intercompanyvalidation;
	}
	public String getVendorvalidation() {
		return vendorvalidation;
	}
	public void setVendorvalidation(String vendorvalidation) {
		this.vendorvalidation = vendorvalidation;
	}
	public String getInvoicevalidation() {
		return invoicevalidation;
	}
	public void setInvoicevalidation(String invoicevalidation) {
		this.invoicevalidation = invoicevalidation;
	}
	public String getCheckvalidation() {
		return checkvalidation;
	}
	public void setCheckvalidation(String checkvalidation) {
		this.checkvalidation = checkvalidation;
	}
	public String getDatevalidation() {
		return datevalidation;
	}
	public void setDatevalidation(String datevalidation) {
		this.datevalidation = datevalidation;
	}
	public String getCostcentervalidation() {
		return costcentervalidation;
	}
	public void setCostcentervalidation(String costcentervalidation) {
		this.costcentervalidation = costcentervalidation;
	}
	public String getCostcenterdestvalidation() {
		return costcenterdestvalidation;
	}
	public void setCostcenterdestvalidation(String costcenterdestvalidation) {
		this.costcenterdestvalidation = costcenterdestvalidation;
	}
	public String getSubledgervalidation() {
		return subledgervalidation;
	}
	public void setSubledgervalidation(String subledgervalidation) {
		this.subledgervalidation = subledgervalidation;
	}
	public String getCashflowvalidation() {
		return cashflowvalidation;
	}
	public void setCashflowvalidation(String cashflowvalidation) {
		this.cashflowvalidation = cashflowvalidation;
	}
	public String getCode01validation() {
		return code01validation;
	}
	public void setCode01validation(String code01validation) {
		this.code01validation = code01validation;
	}
	public String getCode02validation() {
		return code02validation;
	}
	public void setCode02validation(String code02validation) {
		this.code02validation = code02validation;
	}
	public String getCode03validation() {
		return code03validation;
	}
	public void setCode03validation(String code03validation) {
		this.code03validation = code03validation;
	}
	public String getCode04validation() {
		return code04validation;
	}
	public void setCode04validation(String code04validation) {
		this.code04validation = code04validation;
	}
	public String getCode05validation() {
		return code05validation;
	}
	public void setCode05validation(String code05validation) {
		this.code05validation = code05validation;
	}
	public String getCode06validation() {
		return code06validation;
	}
	public void setCode06validation(String code06validation) {
		this.code06validation = code06validation;
	}
	public String getCode07validation() {
		return code07validation;
	}
	public void setCode07validation(String code07validation) {
		this.code07validation = code07validation;
	}
	public String getCode08validation() {
		return code08validation;
	}
	public void setCode08validation(String code08validation) {
		this.code08validation = code08validation;
	}
	public String getCode09validation() {
		return code09validation;
	}
	public void setCode09validation(String code09validation) {
		this.code09validation = code09validation;
	}
	public String getCode10validation() {
		return code10validation;
	}
	public void setCode10validation(String code10validation) {
		this.code10validation = code10validation;
	}
	public String getRevaluationindicator() {
		return revaluationindicator;
	}
	public void setRevaluationindicator(String revaluationindicator) {
		this.revaluationindicator = revaluationindicator;
	}
	public String getPeriod13indicator() {
		return period13indicator;
	}
	public void setPeriod13indicator(String period13indicator) {
		this.period13indicator = period13indicator;
	}
	public String getPeriod14indicator() {
		return period14indicator;
	}
	public void setPeriod14indicator(String period14indicator) {
		this.period14indicator = period14indicator;
	}
	public String getPeriod16indicator() {
		return period16indicator;
	}
	public void setPeriod16indicator(String period16indicator) {
		this.period16indicator = period16indicator;
	}
	public String getAccountcurrency() {
		return accountcurrency;
	}
	public void setAccountcurrency(String accountcurrency) {
		this.accountcurrency = accountcurrency;
	}
	public String getAccountdestination() {
		return accountdestination;
	}
	public void setAccountdestination(String accountdestination) {
		this.accountdestination = accountdestination;
	}
	public String getAccountdestinationmaterials() {
		return accountdestinationmaterials;
	}
	public void setAccountdestinationmaterials(String accountdestinationmaterials) {
		this.accountdestinationmaterials = accountdestinationmaterials;
	}
	public String getAccountoffset() {
		return accountoffset;
	}
	public void setAccountoffset(String accountoffset) {
		this.accountoffset = accountoffset;
	}
	public String getAccountoffsetmaterials() {
		return accountoffsetmaterials;
	}
	public void setAccountoffsetmaterials(String accountoffsetmaterials) {
		this.accountoffsetmaterials = accountoffsetmaterials;
	}
	public String getElementsubindicator() {
		return elementsubindicator;
	}
	public void setElementsubindicator(String elementsubindicator) {
		this.elementsubindicator = elementsubindicator;
	}
	public String getVendortypeflag() {
		return vendortypeflag;
	}
	public void setVendortypeflag(String vendortypeflag) {
		this.vendortypeflag = vendortypeflag;
	}
	public String getMulticompanyflag() {
		return multicompanyflag;
	}
	public void setMulticompanyflag(String multicompanyflag) {
		this.multicompanyflag = multicompanyflag;
	}
	public String getInflationadjustmentflag() {
		return inflationadjustmentflag;
	}
	public void setInflationadjustmentflag(String inflationadjustmentflag) {
		this.inflationadjustmentflag = inflationadjustmentflag;
	}
	public String getStatisticsgenerationflag() {
		return statisticsgenerationflag;
	}
	public void setStatisticsgenerationflag(String statisticsgenerationflag) {
		this.statisticsgenerationflag = statisticsgenerationflag;
	}
	public String getCashflowcodedefault() {
		return cashflowcodedefault;
	}
	public void setCashflowcodedefault(String cashflowcodedefault) {
		this.cashflowcodedefault = cashflowcodedefault;
	}
	public String getBudgetcategorydefault() {
		return budgetcategorydefault;
	}
	public void setBudgetcategorydefault(String budgetcategorydefault) {
		this.budgetcategorydefault = budgetcategorydefault;
	}
	public String getResponsibledefault() {
		return responsibledefault;
	}
	public void setResponsibledefault(String responsibledefault) {
		this.responsibledefault = responsibledefault;
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
	public String getAccountinflation() {
		return accountinflation;
	}
	public void setAccountinflation(String accountinflation) {
		this.accountinflation = accountinflation;
	}
	public String getAccountinflation02() {
		return accountinflation02;
	}
	public void setAccountinflation02(String accountinflation02) {
		this.accountinflation02 = accountinflation02;
	}
	public String getAccountrelation() {
		return accountrelation;
	}
	public void setAccountrelation(String accountrelation) {
		this.accountrelation = accountrelation;
	}
	public String getAccountgroup() {
		return accountgroup;
	}
	public void setAccountgroup(String accountgroup) {
		this.accountgroup = accountgroup;
	}
	public String getInvoiceclasification() {
		return invoiceclasification;
	}
	public void setInvoiceclasification(String invoiceclasification) {
		this.invoiceclasification = invoiceclasification;
	}
	public String getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}
	public String getCostproductionclasification() {
		return costproductionclasification;
	}
	public void setCostproductionclasification(String costproductionclasification) {
		this.costproductionclasification = costproductionclasification;
	}
	public String getBillingprocessflag() {
		return billingprocessflag;
	}
	public void setBillingprocessflag(String billingprocessflag) {
		this.billingprocessflag = billingprocessflag;
	}
	public String getBillingaccountlocal() {
		return billingaccountlocal;
	}
	public void setBillingaccountlocal(String billingaccountlocal) {
		this.billingaccountlocal = billingaccountlocal;
	}
	public String getBillingaccountdollar() {
		return billingaccountdollar;
	}
	public void setBillingaccountdollar(String billingaccountdollar) {
		this.billingaccountdollar = billingaccountdollar;
	}
	public String getOverheadprocessflag() {
		return overheadprocessflag;
	}
	public void setOverheadprocessflag(String overheadprocessflag) {
		this.overheadprocessflag = overheadprocessflag;
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
	public String getStatutoryprime() {
		return statutoryprime;
	}
	public void setStatutoryprime(String statutoryprime) {
		this.statutoryprime = statutoryprime;
	}
	public String getVendorusertype() {
		return vendorusertype;
	}
	public void setVendorusertype(String vendorusertype) {
		this.vendorusertype = vendorusertype;
	}
	public String getProfitlossflag() {
		return profitlossflag;
	}
	public void setProfitlossflag(String profitlossflag) {
		this.profitlossflag = profitlossflag;
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
	public String getSucursalvalidation() {
		return sucursalvalidation;
	}
	public void setSucursalvalidation(String sucursalvalidation) {
		this.sucursalvalidation = sucursalvalidation;
	}
	public String getAfedefault() {
		return afedefault;
	}
	public void setAfedefault(String afedefault) {
		this.afedefault = afedefault;
	}
	public String getFasb52flag() {
		return fasb52flag;
	}
	public void setFasb52flag(String fasb52flag) {
		this.fasb52flag = fasb52flag;
	}
	public String getFiscalreferencevalidation() {
		return fiscalreferencevalidation;
	}
	public void setFiscalreferencevalidation(String fiscalreferencevalidation) {
		this.fiscalreferencevalidation = fiscalreferencevalidation;
	}
	public String getInflationadjustmentafeflag() {
		return inflationadjustmentafeflag;
	}
	public void setInflationadjustmentafeflag(String inflationadjustmentafeflag) {
		this.inflationadjustmentafeflag = inflationadjustmentafeflag;
	}
	public String getCpautomaticrequflag() {
		return cpautomaticrequflag;
	}
	public void setCpautomaticrequflag(String cpautomaticrequflag) {
		this.cpautomaticrequflag = cpautomaticrequflag;
	}
	public String getCpautomaticinvoflag() {
		return cpautomaticinvoflag;
	}
	public void setCpautomaticinvoflag(String cpautomaticinvoflag) {
		this.cpautomaticinvoflag = cpautomaticinvoflag;
	}
	public String getBudgetexcluirflag() {
		return budgetexcluirflag;
	}
	public void setBudgetexcluirflag(String budgetexcluirflag) {
		this.budgetexcluirflag = budgetexcluirflag;
	}
	public String getAccountPcge() {
		return accountPcge;
	}
	public void setAccountPcge(String accountPcge) {
		this.accountPcge = accountPcge;
	}
	public String getAccountOld() {
		return accountOld;
	}
	public void setAccountOld(String accountOld) {
		this.accountOld = accountOld;
	}
	public String getBudgetcategorydefault2() {
		return budgetcategorydefault2;
	}
	public void setBudgetcategorydefault2(String budgetcategorydefault2) {
		this.budgetcategorydefault2 = budgetcategorydefault2;
	}
	public String getSunatcostclasification() {
		return sunatcostclasification;
	}
	public void setSunatcostclasification(String sunatcostclasification) {
		this.sunatcostclasification = sunatcostclasification;
	}
	public String getCashflowdirectfiscal() {
		return cashflowdirectfiscal;
	}
	public void setCashflowdirectfiscal(String cashflowdirectfiscal) {
		this.cashflowdirectfiscal = cashflowdirectfiscal;
	}
	public String getPlancontable() {
		return plancontable;
	}
	public void setPlancontable(String plancontable) {
		this.plancontable = plancontable;
	}
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	
	
}
