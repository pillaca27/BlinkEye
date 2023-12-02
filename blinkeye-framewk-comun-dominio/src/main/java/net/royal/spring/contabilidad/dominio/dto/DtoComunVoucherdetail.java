package net.royal.spring.contabilidad.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

import net.royal.spring.contabilidad.dominio.BeanVoucherdetail;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

/**
 * 
 * 
 * @tabla dbo.voucherdetail
 */
public class DtoComunVoucherdetail extends DominioTransaccion implements java.io.Serializable {

	private String period;
	private String companyowner;
	private String voucherno;
	private Integer voucherline;
	private String account;
	private java.math.BigDecimal localamount;
	private java.math.BigDecimal dollaramount;
	private java.math.BigDecimal thirdamount;
	private Integer vendor;
	private String afe;
	private String destinationcompanyowner;
	private String destinationaccount;
	private String commitment;
	private String invoice;
	private String checknumber;
	private java.util.Date variabledate;
	private String referencefield;
	private String subledgercode;
	private String variablecode01;
	private String variablecode02;
	private String variablecode03;
	private String variablecode04;
	private String variablecode05;
	private String variablecode06;
	private String variablecode07;
	private String variablecode08;
	private String variablecode09;
	private String variablecode10;
	private java.math.BigDecimal postedamountlocal;
	private java.math.BigDecimal postedamountdollar;
	private String costcenter;
	private String costcenterdestination;
	private String budgetcategory;
	private String cashflowcode;
	private String origin;
	private String description;
	private String sucursal;
	private String splitdistributionflag;
	private String status;
	private String prime;
	private String currencydocument;
	private String referenciafiscal02;
	private String referenciafiscal01;
	private String sucursaloriginal;
	private String marcagerencialoriginal;
	private String referenciafiscal03;
	private String transferperiod;
	private String companiaoriginal;
	private String centrocostooriginal;
	private String cashflowfiscal;
	private String variabledate_aux;
	private String accountlocalname;
	private String indicadordetalle;
	private String variabledateaux;

	public String getVariabledateaux() {
		return variabledateaux;
	}

	public void setVariabledateaux(String variabledateaux) {
		this.variabledateaux = variabledateaux;
	}

	public String getIndicadordetalle() {
		return indicadordetalle;
	}

	public void setIndicadordetalle(String indicadordetalle) {
		this.indicadordetalle = indicadordetalle;
	}

	public String getAccountlocalname() {
		return accountlocalname;
	}

	public void setAccountlocalname(String accountlocalname) {
		this.accountlocalname = accountlocalname;
	}

	public String getVariabledate_aux() {
		return variabledate_aux;
	}

	public void setVariabledate_aux(String variabledate_aux) {
		this.variabledate_aux = variabledate_aux;
	}

	// HEADER
	private String ledger;
	private String vouchertitle;
	private Date voucherdate;
	private String busqueda;
	private String accountcurrency;

	private String accion;

	// COLORS
	private String AF;
	private String VE;
	private String CO;
	private String IN;
	private String CH;
	private String DT;
	private String SL;
	private String IC;
	private String CC;
	private String CD;
	private String CF;
	private String SU;
	private String RF;
	private String X1;
	private String X2;
	private String X3;
	private String X4;
	private String X5;
	private String X6;
	private String X7;
	private String X8;
	private String X9;
	private String X0;
	private String F1;
	private String F2;
	private String F3;

	public String getAF() {
		return AF;
	}

	public void setAF(String aF) {
		AF = aF;
	}

	public String getVE() {
		return VE;
	}

	public void setVE(String vE) {
		VE = vE;
	}

	public String getCO() {
		return CO;
	}

	public void setCO(String cO) {
		CO = cO;
	}

	public String getIN() {
		return IN;
	}

	public void setIN(String iN) {
		IN = iN;
	}

	public String getCH() {
		return CH;
	}

	public void setCH(String cH) {
		CH = cH;
	}

	public String getDT() {
		return DT;
	}

	public void setDT(String dT) {
		DT = dT;
	}

	public String getSL() {
		return SL;
	}

	public void setSL(String sL) {
		SL = sL;
	}

	public String getIC() {
		return IC;
	}

	public void setIC(String iC) {
		IC = iC;
	}

	public String getCC() {
		return CC;
	}

	public void setCC(String cC) {
		CC = cC;
	}

	public String getCD() {
		return CD;
	}

	public void setCD(String cD) {
		CD = cD;
	}

	public String getCF() {
		return CF;
	}

	public void setCF(String cF) {
		CF = cF;
	}

	public String getSU() {
		return SU;
	}

	public void setSU(String sU) {
		SU = sU;
	}

	public String getRF() {
		return RF;
	}

	public void setRF(String rF) {
		RF = rF;
	}

	public String getX1() {
		return X1;
	}

	public void setX1(String x1) {
		X1 = x1;
	}

	public String getX2() {
		return X2;
	}

	public void setX2(String x2) {
		X2 = x2;
	}

	public String getX3() {
		return X3;
	}

	public void setX3(String x3) {
		X3 = x3;
	}

	public String getX4() {
		return X4;
	}

	public void setX4(String x4) {
		X4 = x4;
	}

	public String getX5() {
		return X5;
	}

	public void setX5(String x5) {
		X5 = x5;
	}

	public String getX6() {
		return X6;
	}

	public void setX6(String x6) {
		X6 = x6;
	}

	public String getX7() {
		return X7;
	}

	public void setX7(String x7) {
		X7 = x7;
	}

	public String getX8() {
		return X8;
	}

	public void setX8(String x8) {
		X8 = x8;
	}

	public String getX9() {
		return X9;
	}

	public void setX9(String x9) {
		X9 = x9;
	}

	public String getX0() {
		return X0;
	}

	public void setX0(String x0) {
		X0 = x0;
	}

	public String getF1() {
		return F1;
	}

	public void setF1(String f1) {
		F1 = f1;
	}

	public String getF2() {
		return F2;
	}

	public void setF2(String f2) {
		F2 = f2;
	}

	public String getF3() {
		return F3;
	}

	public void setF3(String f3) {
		F3 = f3;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getAccountcurrency() {
		return accountcurrency;
	}

	public void setAccountcurrency(String accountcurrency) {
		this.accountcurrency = accountcurrency;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public String getLedger() {
		return ledger;
	}

	public void setLedger(String ledger) {
		this.ledger = ledger;
	}

	public String getVouchertitle() {
		return vouchertitle;
	}

	public void setVouchertitle(String vouchertitle) {
		this.vouchertitle = vouchertitle;
	}

	public Date getVoucherdate() {
		return voucherdate;
	}

	public void setVoucherdate(Date voucherdate) {
		this.voucherdate = voucherdate;
	}

	/**
	 * 
	 * 
	 * @campo period
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * 
	 * 
	 * @campo period
	 */
	public void setPeriod(String period) {
		this.period = period;
	}

	/**
	 * 
	 * 
	 * @campo companyowner
	 */
	public String getCompanyowner() {
		return companyowner;
	}

	/**
	 * 
	 * 
	 * @campo companyowner
	 */
	public void setCompanyowner(String companyowner) {
		this.companyowner = companyowner;
	}

	/**
	 * 
	 * 
	 * @campo voucherno
	 */
	public String getVoucherno() {
		return voucherno;
	}

	/**
	 * 
	 * 
	 * @campo voucherno
	 */
	public void setVoucherno(String voucherno) {
		this.voucherno = voucherno;
	}

	/**
	 * 
	 * 
	 * @campo voucherline
	 */
	public Integer getVoucherline() {
		return voucherline;
	}

	/**
	 * 
	 * 
	 * @campo voucherline
	 */
	public void setVoucherline(Integer voucherline) {
		this.voucherline = voucherline;
	}

	/**
	 * 
	 * 
	 * @campo Account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * 
	 * 
	 * @campo Account
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * 
	 * 
	 * @campo localamount
	 */
	public java.math.BigDecimal getLocalamount() {
		if (localamount == null)
			return new BigDecimal(0);
		return localamount;
	}

	/**
	 * 
	 * 
	 * @campo localamount
	 */
	public void setLocalamount(java.math.BigDecimal localamount) {
		this.localamount = localamount;
	}

	/**
	 * 
	 * 
	 * @campo dollaramount
	 */
	public java.math.BigDecimal getDollaramount() {
		if (dollaramount == null)
			return new BigDecimal(0);
		return dollaramount;
	}

	/**
	 * 
	 * 
	 * @campo dollaramount
	 */
	public void setDollaramount(java.math.BigDecimal dollaramount) {
		this.dollaramount = dollaramount;
	}

	/**
	 * 
	 * 
	 * @campo thirdamount
	 */
	public java.math.BigDecimal getThirdamount() {
		return thirdamount;
	}

	/**
	 * 
	 * 
	 * @campo thirdamount
	 */
	public void setThirdamount(java.math.BigDecimal thirdamount) {
		this.thirdamount = thirdamount;
	}

	/**
	 * 
	 * 
	 * @campo vendor
	 */
	public Integer getVendor() {
		return vendor;
	}

	/**
	 * 
	 * 
	 * @campo vendor
	 */
	public void setVendor(Integer vendor) {
		this.vendor = vendor;
	}

	/**
	 * 
	 * 
	 * @campo afe
	 */
	public String getAfe() {
		return afe;
	}

	/**
	 * 
	 * 
	 * @campo afe
	 */
	public void setAfe(String afe) {
		this.afe = afe;
	}

	/**
	 * 
	 * 
	 * @campo destinationcompanyowner
	 */
	public String getDestinationcompanyowner() {
		return destinationcompanyowner;
	}

	/**
	 * 
	 * 
	 * @campo destinationcompanyowner
	 */
	public void setDestinationcompanyowner(String destinationcompanyowner) {
		this.destinationcompanyowner = destinationcompanyowner;
	}

	/**
	 * 
	 * 
	 * @campo destinationaccount
	 */
	public String getDestinationaccount() {
		return destinationaccount;
	}

	/**
	 * 
	 * 
	 * @campo destinationaccount
	 */
	public void setDestinationaccount(String destinationaccount) {
		this.destinationaccount = destinationaccount;
	}

	/**
	 * 
	 * 
	 * @campo commitment
	 */
	public String getCommitment() {
		return commitment;
	}

	/**
	 * 
	 * 
	 * @campo commitment
	 */
	public void setCommitment(String commitment) {
		this.commitment = commitment;
	}

	/**
	 * 
	 * 
	 * @campo invoice
	 */
	public String getInvoice() {
		return invoice;
	}

	/**
	 * 
	 * 
	 * @campo invoice
	 */
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	/**
	 * 
	 * 
	 * @campo checknumber
	 */
	public String getChecknumber() {
		return checknumber;
	}

	/**
	 * 
	 * 
	 * @campo checknumber
	 */
	public void setChecknumber(String checknumber) {
		this.checknumber = checknumber;
	}

	/**
	 * 
	 * 
	 * @campo variabledate
	 */
	public java.util.Date getVariabledate() {
		return variabledate;
	}

	/**
	 * 
	 * 
	 * @campo variabledate
	 */
	public void setVariabledate(java.util.Date variabledate) {
		this.variabledate = variabledate;
	}

	/**
	 * 
	 * 
	 * @campo ReferenceField
	 */
	public String getReferencefield() {
		return referencefield;
	}

	/**
	 * 
	 * 
	 * @campo ReferenceField
	 */
	public void setReferencefield(String referencefield) {
		this.referencefield = referencefield;
	}

	/**
	 * 
	 * 
	 * @campo SubLedgerCode
	 */
	public String getSubledgercode() {
		return subledgercode;
	}

	/**
	 * 
	 * 
	 * @campo SubLedgerCode
	 */
	public void setSubledgercode(String subledgercode) {
		this.subledgercode = subledgercode;
	}

	/**
	 * 
	 * 
	 * @campo variablecode01
	 */
	public String getVariablecode01() {
		return variablecode01;
	}

	/**
	 * 
	 * 
	 * @campo variablecode01
	 */
	public void setVariablecode01(String variablecode01) {
		this.variablecode01 = variablecode01;
	}

	/**
	 * 
	 * 
	 * @campo variablecode02
	 */
	public String getVariablecode02() {
		return variablecode02;
	}

	/**
	 * 
	 * 
	 * @campo variablecode02
	 */
	public void setVariablecode02(String variablecode02) {
		this.variablecode02 = variablecode02;
	}

	/**
	 * 
	 * 
	 * @campo variablecode03
	 */
	public String getVariablecode03() {
		return variablecode03;
	}

	/**
	 * 
	 * 
	 * @campo variablecode03
	 */
	public void setVariablecode03(String variablecode03) {
		this.variablecode03 = variablecode03;
	}

	/**
	 * 
	 * 
	 * @campo variablecode04
	 */
	public String getVariablecode04() {
		return variablecode04;
	}

	/**
	 * 
	 * 
	 * @campo variablecode04
	 */
	public void setVariablecode04(String variablecode04) {
		this.variablecode04 = variablecode04;
	}

	/**
	 * 
	 * 
	 * @campo variablecode05
	 */
	public String getVariablecode05() {
		return variablecode05;
	}

	/**
	 * 
	 * 
	 * @campo variablecode05
	 */
	public void setVariablecode05(String variablecode05) {
		this.variablecode05 = variablecode05;
	}

	/**
	 * 
	 * 
	 * @campo variablecode06
	 */
	public String getVariablecode06() {
		return variablecode06;
	}

	/**
	 * 
	 * 
	 * @campo variablecode06
	 */
	public void setVariablecode06(String variablecode06) {
		this.variablecode06 = variablecode06;
	}

	/**
	 * 
	 * 
	 * @campo variablecode07
	 */
	public String getVariablecode07() {
		return variablecode07;
	}

	/**
	 * 
	 * 
	 * @campo variablecode07
	 */
	public void setVariablecode07(String variablecode07) {
		this.variablecode07 = variablecode07;
	}

	/**
	 * 
	 * 
	 * @campo variablecode08
	 */
	public String getVariablecode08() {
		return variablecode08;
	}

	/**
	 * 
	 * 
	 * @campo variablecode08
	 */
	public void setVariablecode08(String variablecode08) {
		this.variablecode08 = variablecode08;
	}

	/**
	 * 
	 * 
	 * @campo variablecode09
	 */
	public String getVariablecode09() {
		return variablecode09;
	}

	/**
	 * 
	 * 
	 * @campo variablecode09
	 */
	public void setVariablecode09(String variablecode09) {
		this.variablecode09 = variablecode09;
	}

	/**
	 * 
	 * 
	 * @campo variablecode10
	 */
	public String getVariablecode10() {
		return variablecode10;
	}

	/**
	 * 
	 * 
	 * @campo variablecode10
	 */
	public void setVariablecode10(String variablecode10) {
		this.variablecode10 = variablecode10;
	}

	/**
	 * 
	 * 
	 * @campo postedamountlocal
	 */
	public java.math.BigDecimal getPostedamountlocal() {
		return postedamountlocal;
	}

	/**
	 * 
	 * 
	 * @campo postedamountlocal
	 */
	public void setPostedamountlocal(java.math.BigDecimal postedamountlocal) {
		this.postedamountlocal = postedamountlocal;
	}

	/**
	 * 
	 * 
	 * @campo postedamountdollar
	 */
	public java.math.BigDecimal getPostedamountdollar() {
		return postedamountdollar;
	}

	/**
	 * 
	 * 
	 * @campo postedamountdollar
	 */
	public void setPostedamountdollar(java.math.BigDecimal postedamountdollar) {
		this.postedamountdollar = postedamountdollar;
	}

	/**
	 * 
	 * 
	 * @campo CostCenter
	 */
	public String getCostcenter() {
		return costcenter;
	}

	/**
	 * 
	 * 
	 * @campo CostCenter
	 */
	public void setCostcenter(String costcenter) {
		this.costcenter = costcenter;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterDestination
	 */
	public String getCostcenterdestination() {
		return costcenterdestination;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterDestination
	 */
	public void setCostcenterdestination(String costcenterdestination) {
		this.costcenterdestination = costcenterdestination;
	}

	/**
	 * 
	 * 
	 * @campo BudgetCategory
	 */
	public String getBudgetcategory() {
		return budgetcategory;
	}

	/**
	 * 
	 * 
	 * @campo BudgetCategory
	 */
	public void setBudgetcategory(String budgetcategory) {
		this.budgetcategory = budgetcategory;
	}

	/**
	 * 
	 * 
	 * @campo CashFlowCode
	 */
	public String getCashflowcode() {
		return cashflowcode;
	}

	/**
	 * 
	 * 
	 * @campo CashFlowCode
	 */
	public void setCashflowcode(String cashflowcode) {
		this.cashflowcode = cashflowcode;
	}

	/**
	 * 
	 * 
	 * @campo Origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * 
	 * 
	 * @campo Origin
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * 
	 * 
	 * @campo description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * 
	 * @campo description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * 
	 * @campo Sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * 
	 * 
	 * @campo Sucursal
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * 
	 * 
	 * @campo SplitDistributionFlag
	 */
	public String getSplitdistributionflag() {
		return splitdistributionflag;
	}

	/**
	 * 
	 * 
	 * @campo SplitDistributionFlag
	 */
	public void setSplitdistributionflag(String splitdistributionflag) {
		this.splitdistributionflag = splitdistributionflag;
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
	 * @campo Prime
	 */
	public String getPrime() {
		return prime;
	}

	/**
	 * 
	 * 
	 * @campo Prime
	 */
	public void setPrime(String prime) {
		this.prime = prime;
	}

	/**
	 * 
	 * 
	 * @campo CurrencyDocument
	 */
	public String getCurrencydocument() {
		return currencydocument;
	}

	/**
	 * 
	 * 
	 * @campo CurrencyDocument
	 */
	public void setCurrencydocument(String currencydocument) {
		this.currencydocument = currencydocument;
	}

	/**
	 * 
	 * 
	 * @campo ReferenciaFiscal02
	 */
	public String getReferenciafiscal02() {
		return referenciafiscal02;
	}

	/**
	 * 
	 * 
	 * @campo ReferenciaFiscal02
	 */
	public void setReferenciafiscal02(String referenciafiscal02) {
		this.referenciafiscal02 = referenciafiscal02;
	}

	/**
	 * 
	 * 
	 * @campo ReferenciaFiscal01
	 */
	public String getReferenciafiscal01() {
		return referenciafiscal01;
	}

	/**
	 * 
	 * 
	 * @campo ReferenciaFiscal01
	 */
	public void setReferenciafiscal01(String referenciafiscal01) {
		this.referenciafiscal01 = referenciafiscal01;
	}

	/**
	 * 
	 * 
	 * @campo SucursalOriginal
	 */
	public String getSucursaloriginal() {
		return sucursaloriginal;
	}

	/**
	 * 
	 * 
	 * @campo SucursalOriginal
	 */
	public void setSucursaloriginal(String sucursaloriginal) {
		this.sucursaloriginal = sucursaloriginal;
	}

	/**
	 * 
	 * 
	 * @campo MarcaGerencialOriginal
	 */
	public String getMarcagerencialoriginal() {
		return marcagerencialoriginal;
	}

	/**
	 * 
	 * 
	 * @campo MarcaGerencialOriginal
	 */
	public void setMarcagerencialoriginal(String marcagerencialoriginal) {
		this.marcagerencialoriginal = marcagerencialoriginal;
	}

	/**
	 * 
	 * 
	 * @campo ReferenciaFiscal03
	 */
	public String getReferenciafiscal03() {
		return referenciafiscal03;
	}

	/**
	 * 
	 * 
	 * @campo ReferenciaFiscal03
	 */
	public void setReferenciafiscal03(String referenciafiscal03) {
		this.referenciafiscal03 = referenciafiscal03;
	}

	/**
	 * 
	 * 
	 * @campo TransferPeriod
	 */
	public String getTransferperiod() {
		return transferperiod;
	}

	/**
	 * 
	 * 
	 * @campo TransferPeriod
	 */
	public void setTransferperiod(String transferperiod) {
		this.transferperiod = transferperiod;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaOriginal
	 */
	public String getCompaniaoriginal() {
		return companiaoriginal;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaOriginal
	 */
	public void setCompaniaoriginal(String companiaoriginal) {
		this.companiaoriginal = companiaoriginal;
	}

	/**
	 * 
	 * 
	 * @campo CentroCostoOriginal
	 */
	public String getCentrocostooriginal() {
		return centrocostooriginal;
	}

	/**
	 * 
	 * 
	 * @campo CentroCostoOriginal
	 */
	public void setCentrocostooriginal(String centrocostooriginal) {
		this.centrocostooriginal = centrocostooriginal;
	}

	/**
	 * 
	 * 
	 * @campo CashFlowFiscal
	 */
	public String getCashflowfiscal() {
		return cashflowfiscal;
	}

	/**
	 * 
	 * 
	 * @campo CashFlowFiscal
	 */
	public void setCashflowfiscal(String cashflowfiscal) {
		this.cashflowfiscal = cashflowfiscal;
	}

	public BeanVoucherdetail obtenerBean() {
		BeanVoucherdetail bean = new BeanVoucherdetail();
		return obtenerBean(bean);
	}

	public BeanVoucherdetail obtenerBean(BeanVoucherdetail bean) {
		if (bean == null)
			bean = new BeanVoucherdetail();

		bean.getPk().setPeriod(period);
		bean.getPk().setCompanyowner(companyowner);
		bean.getPk().setVoucherno(voucherno);
		bean.getPk().setVoucherline(voucherline);
		bean.setAccount(account);
		bean.setLocalamount(localamount);
		bean.setDollaramount(dollaramount);
		bean.setThirdamount(thirdamount);
		bean.setVendor(vendor);
		bean.setAfe(afe);
		bean.setDestinationcompanyowner(destinationcompanyowner);
		bean.setDestinationaccount(destinationaccount);
		bean.setCommitment(commitment);
		bean.setInvoice(invoice);
		bean.setChecknumber(checknumber);
		bean.setVariabledate(variabledate);
		bean.setReferencefield(referencefield);
		bean.setSubledgercode(subledgercode);
		bean.setVariablecode01(variablecode01);
		bean.setVariablecode02(variablecode02);
		bean.setVariablecode03(variablecode03);
		bean.setVariablecode04(variablecode04);
		bean.setVariablecode05(variablecode05);
		bean.setVariablecode06(variablecode06);
		bean.setVariablecode07(variablecode07);
		bean.setVariablecode08(variablecode08);
		bean.setVariablecode09(variablecode09);
		bean.setVariablecode10(variablecode10);
		bean.setPostedamountlocal(postedamountlocal);
		bean.setPostedamountdollar(postedamountdollar);
		bean.setCostcenter(costcenter);
		bean.setCostcenterdestination(costcenterdestination);
		bean.setBudgetcategory(budgetcategory);
		bean.setCashflowcode(cashflowcode);
		bean.setOrigin(origin);
		bean.setDescription(description);
		bean.setSucursal(sucursal);
		bean.setSplitdistributionflag(splitdistributionflag);
		bean.setStatus(status);
		bean.setPrime(prime);
		bean.setCurrencydocument(currencydocument);
		bean.setReferenciafiscal02(referenciafiscal02);
		bean.setReferenciafiscal01(referenciafiscal01);
		bean.setSucursaloriginal(sucursaloriginal);
		bean.setMarcagerencialoriginal(marcagerencialoriginal);
		bean.setReferenciafiscal03(referenciafiscal03);
		bean.setTransferperiod(transferperiod);
		bean.setCompaniaoriginal(companiaoriginal);
		bean.setCentrocostooriginal(centrocostooriginal);
		bean.setCashflowfiscal(cashflowfiscal);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
