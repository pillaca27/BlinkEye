package net.royal.spring.contabilidad.dominio;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.voucherdetail
 */
@Entity
@Table(name = "VOUCHERDETAIL")
public class BeanVoucherdetail extends DominioTransaccion implements java.io.Serializable {

	@EmbeddedId
	private BeanVoucherdetailPk pk;

	@Size(min = 0, max = 20)
	@Column(name = "ACCOUNT", length = 20, nullable = true)
	private String account;

	@Column(name = "LOCALAMOUNT", precision = 19, scale = 4, nullable = true)
	private java.math.BigDecimal localamount;

	@Column(name = "DOLLARAMOUNT", precision = 19, scale = 4, nullable = true)
	private java.math.BigDecimal dollaramount;

	@Column(name = "THIRDAMOUNT", precision = 19, scale = 4, nullable = true)
	private java.math.BigDecimal thirdamount;

	@Column(name = "VENDOR", nullable = true)
	private Integer vendor;

	@Size(min = 0, max = 15)
	@Column(name = "AFE", length = 15, nullable = true)
	private String afe;

	@Size(min = 0, max = 8)
	@Column(name = "DESTINATIONCOMPANYOWNER", length = 8, nullable = true)
	private String destinationcompanyowner;

	@Size(min = 0, max = 20)
	@Column(name = "DESTINATIONACCOUNT", length = 20, nullable = true)
	private String destinationaccount;

	@Size(min = 0, max = 15)
	@Column(name = "COMMITMENT", length = 15, nullable = true)
	private String commitment;

	@Size(min = 0, max = 50)
	@Column(name = "INVOICE", length = 50, nullable = true)
	private String invoice;

	@Size(min = 0, max = 10)
	@Column(name = "CHECKNUMBER", length = 10, nullable = true)
	private String checknumber;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "VARIABLEDATE", nullable = true)
	private java.util.Date variabledate;

	@Size(min = 0, max = 12)
	@Column(name = "REFERENCEFIELD", length = 12, nullable = true)
	private String referencefield;

	@Size(min = 0, max = 20)
	@Column(name = "SUBLEDGERCODE", length = 20, nullable = true)
	private String subledgercode;

	@Size(min = 0, max = 4)
	@Column(name = "VARIABLECODE01", length = 4, nullable = true)
	private String variablecode01;

	@Size(min = 0, max = 4)
	@Column(name = "VARIABLECODE02", length = 4, nullable = true)
	private String variablecode02;

	@Size(min = 0, max = 4)
	@Column(name = "VARIABLECODE03", length = 4, nullable = true)
	private String variablecode03;

	@Size(min = 0, max = 4)
	@Column(name = "VARIABLECODE04", length = 4, nullable = true)
	private String variablecode04;

	@Size(min = 0, max = 6)
	@Column(name = "VARIABLECODE05", length = 6, nullable = true)
	private String variablecode05;

	@Size(min = 0, max = 6)
	@Column(name = "VARIABLECODE06", length = 6, nullable = true)
	private String variablecode06;

	@Size(min = 0, max = 6)
	@Column(name = "VARIABLECODE07", length = 6, nullable = true)
	private String variablecode07;

	@Size(min = 0, max = 12)
	@Column(name = "VARIABLECODE08", length = 12, nullable = true)
	private String variablecode08;

	@Size(min = 0, max = 12)
	@Column(name = "VARIABLECODE09", length = 12, nullable = true)
	private String variablecode09;

	@Size(min = 0, max = 12)
	@Column(name = "VARIABLECODE10", length = 12, nullable = true)
	private String variablecode10;

	@Column(name = "POSTEDAMOUNTLOCAL", precision = 19, scale = 4, nullable = true)
	private java.math.BigDecimal postedamountlocal;

	@Column(name = "POSTEDAMOUNTDOLLAR", precision = 19, scale = 4, nullable = true)
	private java.math.BigDecimal postedamountdollar;

	@Size(min = 0, max = 10)
	@Column(name = "COSTCENTER", length = 10, nullable = true)
	private String costcenter;

	@Size(min = 0, max = 6)
	@Column(name = "COSTCENTERDESTINATION", length = 6, nullable = true)
	private String costcenterdestination;

	@Size(min = 0, max = 4)
	@Column(name = "BUDGETCATEGORY", length = 4, nullable = true)
	private String budgetcategory;

	@Size(min = 0, max = 4)
	@Column(name = "CASHFLOWCODE", length = 4, nullable = true)
	private String cashflowcode;

	@Size(min = 0, max = 1)
	@Column(name = "ORIGIN", length = 1, nullable = true)
	private String origin;

	@Size(min = 0, max = 200)
	@Column(name = "DESCRIPTION", length = 200, nullable = true)
	private String description;

	@Size(min = 0, max = 20)
	@Column(name = "SUCURSAL", length = 20, nullable = true)
	private String sucursal;

	@Size(min = 0, max = 1)
	@Column(name = "SPLITDISTRIBUTIONFLAG", length = 1, nullable = true)
	private String splitdistributionflag;

	@Size(min = 0, max = 1)
	@Column(name = "STATUS", length = 1, nullable = true)
	private String status;

	@Size(min = 0, max = 10)
	@Column(name = "PRIME", length = 10, nullable = true)
	private String prime;

	@Size(min = 0, max = 2)
	@Column(name = "CURRENCYDOCUMENT", length = 2, nullable = true)
	private String currencydocument;

	@Size(min = 0, max = 20)
	@Column(name = "REFERENCIAFISCAL02", length = 20, nullable = true)
	private String referenciafiscal02;

	@Size(min = 0, max = 10)
	@Column(name = "REFERENCIAFISCAL01", length = 10, nullable = true)
	private String referenciafiscal01;

	@Size(min = 0, max = 4)
	@Column(name = "SUCURSALORIGINAL", length = 4, nullable = true)
	private String sucursaloriginal;

	@Size(min = 0, max = 10)
	@Column(name = "MARCAGERENCIALORIGINAL", length = 10, nullable = true)
	private String marcagerencialoriginal;

	@Size(min = 0, max = 10)
	@Column(name = "REFERENCIAFISCAL03", length = 10, nullable = true)
	private String referenciafiscal03;

	@Size(min = 0, max = 6)
	@Column(name = "TRANSFERPERIOD", length = 6, nullable = true)
	private String transferperiod;

	@Size(min = 0, max = 8)
	@Column(name = "COMPANIAORIGINAL", length = 8, nullable = true)
	private String companiaoriginal;

	@Size(min = 0, max = 10)
	@Column(name = "CENTROCOSTOORIGINAL", length = 10, nullable = true)
	private String centrocostooriginal;

	@Size(min = 0, max = 6)
	@Column(name = "CASHFLOWFISCAL", length = 6, nullable = true)
	private String cashflowfiscal;

	public BeanVoucherdetail() {
		pk = new BeanVoucherdetailPk();
	}

	public BeanVoucherdetail(BeanVoucherdetailPk pk) {
		this.pk = pk;
	}

	public BeanVoucherdetailPk getPk() {
		return pk;
	}

	public void setPk(BeanVoucherdetailPk pk) {
		this.pk = pk;
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

}
