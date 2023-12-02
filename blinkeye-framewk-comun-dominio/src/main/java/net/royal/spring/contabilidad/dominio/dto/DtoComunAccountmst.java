package net.royal.spring.contabilidad.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

/**
 * 
 * 
 * @tabla dbo.accountmst
 */
public class DtoComunAccountmst extends DominioTransaccion implements java.io.Serializable {

	private String account;
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
	private String expensefixedflag;
	private String expensedirectflag;
	private String sucursalvalidation;
	private String inflationadjustmentafeflag;
	private String fasb52flag;
	private String fiscalreferencevalidation;
	private String afedefault;
	private String itfflag;
	private String sunatcostclasification;

	private Integer digit;

	public Integer getDigit() {
		return digit;
	}

	public void setDigit(Integer digit) {
		this.digit = digit;
	}

	/**
	 * 
	 * 
	 * @campo account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * 
	 * 
	 * @campo account
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * 
	 * 
	 * @campo prime
	 */
	public String getPrime() {
		return prime;
	}

	/**
	 * 
	 * 
	 * @campo prime
	 */
	public void setPrime(String prime) {
		this.prime = prime;
	}

	/**
	 * 
	 * 
	 * @campo element
	 */
	public String getElement() {
		return element;
	}

	/**
	 * 
	 * 
	 * @campo element
	 */
	public void setElement(String element) {
		this.element = element;
	}

	/**
	 * 
	 * 
	 * @campo localname
	 */
	public String getLocalname() {
		return localname;
	}

	/**
	 * 
	 * 
	 * @campo localname
	 */
	public void setLocalname(String localname) {
		this.localname = localname;
	}

	/**
	 * 
	 * 
	 * @campo englishname
	 */
	public String getEnglishname() {
		return englishname;
	}

	/**
	 * 
	 * 
	 * @campo englishname
	 */
	public void setEnglishname(String englishname) {
		this.englishname = englishname;
	}

	/**
	 * 
	 * 
	 * @campo afevalidation
	 */
	public String getAfevalidation() {
		return afevalidation;
	}

	/**
	 * 
	 * 
	 * @campo afevalidation
	 */
	public void setAfevalidation(String afevalidation) {
		this.afevalidation = afevalidation;
	}

	/**
	 * 
	 * 
	 * @campo commitmentvalidation
	 */
	public String getCommitmentvalidation() {
		return commitmentvalidation;
	}

	/**
	 * 
	 * 
	 * @campo commitmentvalidation
	 */
	public void setCommitmentvalidation(String commitmentvalidation) {
		this.commitmentvalidation = commitmentvalidation;
	}

	/**
	 * 
	 * 
	 * @campo CommitmentExpenseFlag
	 */
	public String getCommitmentexpenseflag() {
		return commitmentexpenseflag;
	}

	/**
	 * 
	 * 
	 * @campo CommitmentExpenseFlag
	 */
	public void setCommitmentexpenseflag(String commitmentexpenseflag) {
		this.commitmentexpenseflag = commitmentexpenseflag;
	}

	/**
	 * 
	 * 
	 * @campo exchangepdvalidation
	 */
	public String getExchangepdvalidation() {
		return exchangepdvalidation;
	}

	/**
	 * 
	 * 
	 * @campo exchangepdvalidation
	 */
	public void setExchangepdvalidation(String exchangepdvalidation) {
		this.exchangepdvalidation = exchangepdvalidation;
	}

	/**
	 * 
	 * 
	 * @campo intercompanyvalidation
	 */
	public String getIntercompanyvalidation() {
		return intercompanyvalidation;
	}

	/**
	 * 
	 * 
	 * @campo intercompanyvalidation
	 */
	public void setIntercompanyvalidation(String intercompanyvalidation) {
		this.intercompanyvalidation = intercompanyvalidation;
	}

	/**
	 * 
	 * 
	 * @campo vendorvalidation
	 */
	public String getVendorvalidation() {
		return vendorvalidation;
	}

	/**
	 * 
	 * 
	 * @campo vendorvalidation
	 */
	public void setVendorvalidation(String vendorvalidation) {
		this.vendorvalidation = vendorvalidation;
	}

	/**
	 * 
	 * 
	 * @campo invoicevalidation
	 */
	public String getInvoicevalidation() {
		return invoicevalidation;
	}

	/**
	 * 
	 * 
	 * @campo invoicevalidation
	 */
	public void setInvoicevalidation(String invoicevalidation) {
		this.invoicevalidation = invoicevalidation;
	}

	/**
	 * 
	 * 
	 * @campo checkvalidation
	 */
	public String getCheckvalidation() {
		return checkvalidation;
	}

	/**
	 * 
	 * 
	 * @campo checkvalidation
	 */
	public void setCheckvalidation(String checkvalidation) {
		this.checkvalidation = checkvalidation;
	}

	/**
	 * 
	 * 
	 * @campo datevalidation
	 */
	public String getDatevalidation() {
		return datevalidation;
	}

	/**
	 * 
	 * 
	 * @campo datevalidation
	 */
	public void setDatevalidation(String datevalidation) {
		this.datevalidation = datevalidation;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterValidation
	 */
	public String getCostcentervalidation() {
		return costcentervalidation;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterValidation
	 */
	public void setCostcentervalidation(String costcentervalidation) {
		this.costcentervalidation = costcentervalidation;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterDestValidation
	 */
	public String getCostcenterdestvalidation() {
		return costcenterdestvalidation;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterDestValidation
	 */
	public void setCostcenterdestvalidation(String costcenterdestvalidation) {
		this.costcenterdestvalidation = costcenterdestvalidation;
	}

	/**
	 * 
	 * 
	 * @campo SubLedgerValidation
	 */
	public String getSubledgervalidation() {
		return subledgervalidation;
	}

	/**
	 * 
	 * 
	 * @campo SubLedgerValidation
	 */
	public void setSubledgervalidation(String subledgervalidation) {
		this.subledgervalidation = subledgervalidation;
	}

	/**
	 * 
	 * 
	 * @campo CashFlowValidation
	 */
	public String getCashflowvalidation() {
		return cashflowvalidation;
	}

	/**
	 * 
	 * 
	 * @campo CashFlowValidation
	 */
	public void setCashflowvalidation(String cashflowvalidation) {
		this.cashflowvalidation = cashflowvalidation;
	}

	/**
	 * 
	 * 
	 * @campo Code01Validation
	 */
	public String getCode01validation() {
		return code01validation;
	}

	/**
	 * 
	 * 
	 * @campo Code01Validation
	 */
	public void setCode01validation(String code01validation) {
		this.code01validation = code01validation;
	}

	/**
	 * 
	 * 
	 * @campo Code02Validation
	 */
	public String getCode02validation() {
		return code02validation;
	}

	/**
	 * 
	 * 
	 * @campo Code02Validation
	 */
	public void setCode02validation(String code02validation) {
		this.code02validation = code02validation;
	}

	/**
	 * 
	 * 
	 * @campo Code03Validation
	 */
	public String getCode03validation() {
		return code03validation;
	}

	/**
	 * 
	 * 
	 * @campo Code03Validation
	 */
	public void setCode03validation(String code03validation) {
		this.code03validation = code03validation;
	}

	/**
	 * 
	 * 
	 * @campo Code04Validation
	 */
	public String getCode04validation() {
		return code04validation;
	}

	/**
	 * 
	 * 
	 * @campo Code04Validation
	 */
	public void setCode04validation(String code04validation) {
		this.code04validation = code04validation;
	}

	/**
	 * 
	 * 
	 * @campo Code05Validation
	 */
	public String getCode05validation() {
		return code05validation;
	}

	/**
	 * 
	 * 
	 * @campo Code05Validation
	 */
	public void setCode05validation(String code05validation) {
		this.code05validation = code05validation;
	}

	/**
	 * 
	 * 
	 * @campo Code06Validation
	 */
	public String getCode06validation() {
		return code06validation;
	}

	/**
	 * 
	 * 
	 * @campo Code06Validation
	 */
	public void setCode06validation(String code06validation) {
		this.code06validation = code06validation;
	}

	/**
	 * 
	 * 
	 * @campo Code07Validation
	 */
	public String getCode07validation() {
		return code07validation;
	}

	/**
	 * 
	 * 
	 * @campo Code07Validation
	 */
	public void setCode07validation(String code07validation) {
		this.code07validation = code07validation;
	}

	/**
	 * 
	 * 
	 * @campo Code08Validation
	 */
	public String getCode08validation() {
		return code08validation;
	}

	/**
	 * 
	 * 
	 * @campo Code08Validation
	 */
	public void setCode08validation(String code08validation) {
		this.code08validation = code08validation;
	}

	/**
	 * 
	 * 
	 * @campo Code09Validation
	 */
	public String getCode09validation() {
		return code09validation;
	}

	/**
	 * 
	 * 
	 * @campo Code09Validation
	 */
	public void setCode09validation(String code09validation) {
		this.code09validation = code09validation;
	}

	/**
	 * 
	 * 
	 * @campo Code10Validation
	 */
	public String getCode10validation() {
		return code10validation;
	}

	/**
	 * 
	 * 
	 * @campo Code10Validation
	 */
	public void setCode10validation(String code10validation) {
		this.code10validation = code10validation;
	}

	/**
	 * 
	 * 
	 * @campo RevaluationIndicator
	 */
	public String getRevaluationindicator() {
		return revaluationindicator;
	}

	/**
	 * 
	 * 
	 * @campo RevaluationIndicator
	 */
	public void setRevaluationindicator(String revaluationindicator) {
		this.revaluationindicator = revaluationindicator;
	}

	/**
	 * 
	 * 
	 * @campo Period13Indicator
	 */
	public String getPeriod13indicator() {
		return period13indicator;
	}

	/**
	 * 
	 * 
	 * @campo Period13Indicator
	 */
	public void setPeriod13indicator(String period13indicator) {
		this.period13indicator = period13indicator;
	}

	/**
	 * 
	 * 
	 * @campo Period14Indicator
	 */
	public String getPeriod14indicator() {
		return period14indicator;
	}

	/**
	 * 
	 * 
	 * @campo Period14Indicator
	 */
	public void setPeriod14indicator(String period14indicator) {
		this.period14indicator = period14indicator;
	}

	/**
	 * 
	 * 
	 * @campo Period16Indicator
	 */
	public String getPeriod16indicator() {
		return period16indicator;
	}

	/**
	 * 
	 * 
	 * @campo Period16Indicator
	 */
	public void setPeriod16indicator(String period16indicator) {
		this.period16indicator = period16indicator;
	}

	/**
	 * 
	 * 
	 * @campo AccountCurrency
	 */
	public String getAccountcurrency() {
		return accountcurrency;
	}

	/**
	 * 
	 * 
	 * @campo AccountCurrency
	 */
	public void setAccountcurrency(String accountcurrency) {
		this.accountcurrency = accountcurrency;
	}

	/**
	 * 
	 * 
	 * @campo AccountDestination
	 */
	public String getAccountdestination() {
		return accountdestination;
	}

	/**
	 * 
	 * 
	 * @campo AccountDestination
	 */
	public void setAccountdestination(String accountdestination) {
		this.accountdestination = accountdestination;
	}

	/**
	 * 
	 * 
	 * @campo AccountDestinationMaterials
	 */
	public String getAccountdestinationmaterials() {
		return accountdestinationmaterials;
	}

	/**
	 * 
	 * 
	 * @campo AccountDestinationMaterials
	 */
	public void setAccountdestinationmaterials(String accountdestinationmaterials) {
		this.accountdestinationmaterials = accountdestinationmaterials;
	}

	/**
	 * 
	 * 
	 * @campo AccountOffset
	 */
	public String getAccountoffset() {
		return accountoffset;
	}

	/**
	 * 
	 * 
	 * @campo AccountOffset
	 */
	public void setAccountoffset(String accountoffset) {
		this.accountoffset = accountoffset;
	}

	/**
	 * 
	 * 
	 * @campo AccountOffsetMaterials
	 */
	public String getAccountoffsetmaterials() {
		return accountoffsetmaterials;
	}

	/**
	 * 
	 * 
	 * @campo AccountOffsetMaterials
	 */
	public void setAccountoffsetmaterials(String accountoffsetmaterials) {
		this.accountoffsetmaterials = accountoffsetmaterials;
	}

	/**
	 * 
	 * 
	 * @campo elementsubindicator
	 */
	public String getElementsubindicator() {
		return elementsubindicator;
	}

	/**
	 * 
	 * 
	 * @campo elementsubindicator
	 */
	public void setElementsubindicator(String elementsubindicator) {
		this.elementsubindicator = elementsubindicator;
	}

	/**
	 * 
	 * 
	 * @campo VendorTypeflag
	 */
	public String getVendortypeflag() {
		return vendortypeflag;
	}

	/**
	 * 
	 * 
	 * @campo VendorTypeflag
	 */
	public void setVendortypeflag(String vendortypeflag) {
		this.vendortypeflag = vendortypeflag;
	}

	/**
	 * 
	 * 
	 * @campo MultiCompanyFlag
	 */
	public String getMulticompanyflag() {
		return multicompanyflag;
	}

	/**
	 * 
	 * 
	 * @campo MultiCompanyFlag
	 */
	public void setMulticompanyflag(String multicompanyflag) {
		this.multicompanyflag = multicompanyflag;
	}

	/**
	 * 
	 * 
	 * @campo InflationAdjustmentFlag
	 */
	public String getInflationadjustmentflag() {
		return inflationadjustmentflag;
	}

	/**
	 * 
	 * 
	 * @campo InflationAdjustmentFlag
	 */
	public void setInflationadjustmentflag(String inflationadjustmentflag) {
		this.inflationadjustmentflag = inflationadjustmentflag;
	}

	/**
	 * 
	 * 
	 * @campo StatisticsGenerationFlag
	 */
	public String getStatisticsgenerationflag() {
		return statisticsgenerationflag;
	}

	/**
	 * 
	 * 
	 * @campo StatisticsGenerationFlag
	 */
	public void setStatisticsgenerationflag(String statisticsgenerationflag) {
		this.statisticsgenerationflag = statisticsgenerationflag;
	}

	/**
	 * 
	 * 
	 * @campo CashFlowCodeDefault
	 */
	public String getCashflowcodedefault() {
		return cashflowcodedefault;
	}

	/**
	 * 
	 * 
	 * @campo CashFlowCodeDefault
	 */
	public void setCashflowcodedefault(String cashflowcodedefault) {
		this.cashflowcodedefault = cashflowcodedefault;
	}

	/**
	 * 
	 * 
	 * @campo BudgetCategoryDefault
	 */
	public String getBudgetcategorydefault() {
		return budgetcategorydefault;
	}

	/**
	 * 
	 * 
	 * @campo BudgetCategoryDefault
	 */
	public void setBudgetcategorydefault(String budgetcategorydefault) {
		this.budgetcategorydefault = budgetcategorydefault;
	}

	/**
	 * 
	 * 
	 * @campo ResponsibleDefault
	 */
	public String getResponsibledefault() {
		return responsibledefault;
	}

	/**
	 * 
	 * 
	 * @campo ResponsibleDefault
	 */
	public void setResponsibledefault(String responsibledefault) {
		this.responsibledefault = responsibledefault;
	}

	/**
	 * 
	 * 
	 * @campo ExpenseFinanceFlag
	 */
	public String getExpensefinanceflag() {
		return expensefinanceflag;
	}

	/**
	 * 
	 * 
	 * @campo ExpenseFinanceFlag
	 */
	public void setExpensefinanceflag(String expensefinanceflag) {
		this.expensefinanceflag = expensefinanceflag;
	}

	/**
	 * 
	 * 
	 * @campo ExpenseAdministrativeFlag
	 */
	public String getExpenseadministrativeflag() {
		return expenseadministrativeflag;
	}

	/**
	 * 
	 * 
	 * @campo ExpenseAdministrativeFlag
	 */
	public void setExpenseadministrativeflag(String expenseadministrativeflag) {
		this.expenseadministrativeflag = expenseadministrativeflag;
	}

	/**
	 * 
	 * 
	 * @campo ExpenseSalesFlag
	 */
	public String getExpensesalesflag() {
		return expensesalesflag;
	}

	/**
	 * 
	 * 
	 * @campo ExpenseSalesFlag
	 */
	public void setExpensesalesflag(String expensesalesflag) {
		this.expensesalesflag = expensesalesflag;
	}

	/**
	 * 
	 * 
	 * @campo ExpenseProductionFlag
	 */
	public String getExpenseproductionflag() {
		return expenseproductionflag;
	}

	/**
	 * 
	 * 
	 * @campo ExpenseProductionFlag
	 */
	public void setExpenseproductionflag(String expenseproductionflag) {
		this.expenseproductionflag = expenseproductionflag;
	}

	/**
	 * 
	 * 
	 * @campo AccountInflation
	 */
	public String getAccountinflation() {
		return accountinflation;
	}

	/**
	 * 
	 * 
	 * @campo AccountInflation
	 */
	public void setAccountinflation(String accountinflation) {
		this.accountinflation = accountinflation;
	}

	/**
	 * 
	 * 
	 * @campo AccountInflation02
	 */
	public String getAccountinflation02() {
		return accountinflation02;
	}

	/**
	 * 
	 * 
	 * @campo AccountInflation02
	 */
	public void setAccountinflation02(String accountinflation02) {
		this.accountinflation02 = accountinflation02;
	}

	/**
	 * 
	 * 
	 * @campo AccountRelation
	 */
	public String getAccountrelation() {
		return accountrelation;
	}

	/**
	 * 
	 * 
	 * @campo AccountRelation
	 */
	public void setAccountrelation(String accountrelation) {
		this.accountrelation = accountrelation;
	}

	/**
	 * 
	 * 
	 * @campo AccountGroup
	 */
	public String getAccountgroup() {
		return accountgroup;
	}

	/**
	 * 
	 * 
	 * @campo AccountGroup
	 */
	public void setAccountgroup(String accountgroup) {
		this.accountgroup = accountgroup;
	}

	/**
	 * 
	 * 
	 * @campo InvoiceClasification
	 */
	public String getInvoiceclasification() {
		return invoiceclasification;
	}

	/**
	 * 
	 * 
	 * @campo InvoiceClasification
	 */
	public void setInvoiceclasification(String invoiceclasification) {
		this.invoiceclasification = invoiceclasification;
	}

	/**
	 * 
	 * 
	 * @campo AccountType
	 */
	public String getAccounttype() {
		return accounttype;
	}

	/**
	 * 
	 * 
	 * @campo AccountType
	 */
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	/**
	 * 
	 * 
	 * @campo CostProductionClasification
	 */
	public String getCostproductionclasification() {
		return costproductionclasification;
	}

	/**
	 * 
	 * 
	 * @campo CostProductionClasification
	 */
	public void setCostproductionclasification(String costproductionclasification) {
		this.costproductionclasification = costproductionclasification;
	}

	/**
	 * 
	 * 
	 * @campo BillingProcessFlag
	 */
	public String getBillingprocessflag() {
		return billingprocessflag;
	}

	/**
	 * 
	 * 
	 * @campo BillingProcessFlag
	 */
	public void setBillingprocessflag(String billingprocessflag) {
		this.billingprocessflag = billingprocessflag;
	}

	/**
	 * 
	 * 
	 * @campo BillingAccountLocal
	 */
	public String getBillingaccountlocal() {
		return billingaccountlocal;
	}

	/**
	 * 
	 * 
	 * @campo BillingAccountLocal
	 */
	public void setBillingaccountlocal(String billingaccountlocal) {
		this.billingaccountlocal = billingaccountlocal;
	}

	/**
	 * 
	 * 
	 * @campo BillingAccountDollar
	 */
	public String getBillingaccountdollar() {
		return billingaccountdollar;
	}

	/**
	 * 
	 * 
	 * @campo BillingAccountDollar
	 */
	public void setBillingaccountdollar(String billingaccountdollar) {
		this.billingaccountdollar = billingaccountdollar;
	}

	/**
	 * 
	 * 
	 * @campo OverHeadProcessFlag
	 */
	public String getOverheadprocessflag() {
		return overheadprocessflag;
	}

	/**
	 * 
	 * 
	 * @campo OverHeadProcessFlag
	 */
	public void setOverheadprocessflag(String overheadprocessflag) {
		this.overheadprocessflag = overheadprocessflag;
	}

	/**
	 * 
	 * 
	 * @campo status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * 
	 * @campo status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * 
	 * @campo lastuser
	 */
	public String getLastuser() {
		return lastuser;
	}

	/**
	 * 
	 * 
	 * @campo lastuser
	 */
	public void setLastuser(String lastuser) {
		this.lastuser = lastuser;
	}

	/**
	 * 
	 * 
	 * @campo lastdate
	 */
	public java.util.Date getLastdate() {
		return lastdate;
	}

	/**
	 * 
	 * 
	 * @campo lastdate
	 */
	public void setLastdate(java.util.Date lastdate) {
		this.lastdate = lastdate;
	}

	/**
	 * 
	 * 
	 * @campo ExpenseFixedFlag
	 */
	public String getExpensefixedflag() {
		return expensefixedflag;
	}

	/**
	 * 
	 * 
	 * @campo ExpenseFixedFlag
	 */
	public void setExpensefixedflag(String expensefixedflag) {
		this.expensefixedflag = expensefixedflag;
	}

	/**
	 * 
	 * 
	 * @campo ExpenseDirectFlag
	 */
	public String getExpensedirectflag() {
		return expensedirectflag;
	}

	/**
	 * 
	 * 
	 * @campo ExpenseDirectFlag
	 */
	public void setExpensedirectflag(String expensedirectflag) {
		this.expensedirectflag = expensedirectflag;
	}

	/**
	 * 
	 * 
	 * @campo SucursalValidation
	 */
	public String getSucursalvalidation() {
		return sucursalvalidation;
	}

	/**
	 * 
	 * 
	 * @campo SucursalValidation
	 */
	public void setSucursalvalidation(String sucursalvalidation) {
		this.sucursalvalidation = sucursalvalidation;
	}

	/**
	 * 
	 * 
	 * @campo InflationAdjustmentAfeFlag
	 */
	public String getInflationadjustmentafeflag() {
		return inflationadjustmentafeflag;
	}

	/**
	 * 
	 * 
	 * @campo InflationAdjustmentAfeFlag
	 */
	public void setInflationadjustmentafeflag(String inflationadjustmentafeflag) {
		this.inflationadjustmentafeflag = inflationadjustmentafeflag;
	}

	/**
	 * 
	 * 
	 * @campo FasB52Flag
	 */
	public String getFasb52flag() {
		return fasb52flag;
	}

	/**
	 * 
	 * 
	 * @campo FasB52Flag
	 */
	public void setFasb52flag(String fasb52flag) {
		this.fasb52flag = fasb52flag;
	}

	/**
	 * 
	 * 
	 * @campo FiscalReferenceValidation
	 */
	public String getFiscalreferencevalidation() {
		return fiscalreferencevalidation;
	}

	/**
	 * 
	 * 
	 * @campo FiscalReferenceValidation
	 */
	public void setFiscalreferencevalidation(String fiscalreferencevalidation) {
		this.fiscalreferencevalidation = fiscalreferencevalidation;
	}

	/**
	 * 
	 * 
	 * @campo afedefault
	 */
	public String getAfedefault() {
		return afedefault;
	}

	/**
	 * 
	 * 
	 * @campo afedefault
	 */
	public void setAfedefault(String afedefault) {
		this.afedefault = afedefault;
	}

	/**
	 * 
	 * 
	 * @campo ITFFlag
	 */
	public String getItfflag() {
		return itfflag;
	}

	/**
	 * 
	 * 
	 * @campo ITFFlag
	 */
	public void setItfflag(String itfflag) {
		this.itfflag = itfflag;
	}

	/**
	 * 
	 * 
	 * @campo SUNATCostClasification
	 */
	public String getSunatcostclasification() {
		return sunatcostclasification;
	}

	/**
	 * 
	 * 
	 * @campo SUNATCostClasification
	 */
	public void setSunatcostclasification(String sunatcostclasification) {
		this.sunatcostclasification = sunatcostclasification;
	}

}
