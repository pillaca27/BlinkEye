package net.royal.spring.contabilidad.dominio;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.AC_CostCenterMst
*/
@Entity
@Table(name = "AC_COSTCENTERMST")
public class BeanAcCostcentermst extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanAcCostcentermstPk pk;

	@Size(min = 0, max = 50)
	@Column(name = "LOCALNAME", length = 50, nullable = true)
	private String localname;

	@Size(min = 0, max = 50)
	@Column(name = "ENGLISHNAME", length = 50, nullable = true)
	private String englishname;

	@Size(min = 0, max = 10)
	@Column(name = "COSTCENTERCLASIFICATION", length = 10, nullable = true)
	private String costcenterclasification;

	@Size(min = 0, max = 10)
	@Column(name = "COSTCENTERGROUP", length = 10, nullable = true)
	private String costcentergroup;

	@Size(min = 0, max = 10)
	@Column(name = "COSTCENTERSUBGROUP", length = 10, nullable = true)
	private String costcentersubgroup;

	@Size(min = 0, max = 10)
	@Column(name = "COSTCENTERRELATION", length = 10, nullable = true)
	private String costcenterrelation;

	@Size(min = 0, max = 4)
	@Column(name = "RESPONSIBLE", length = 4, nullable = true)
	private String responsible;

	@Size(min = 0, max = 1)
	@Column(name = "EXPENSEFINANCEFLAG", length = 1, nullable = true)
	private String expensefinanceflag;

	@Size(min = 0, max = 1)
	@Column(name = "EXPENSEADMINISTRATIVEFLAG", length = 1, nullable = true)
	private String expenseadministrativeflag;

	@Size(min = 0, max = 1)
	@Column(name = "EXPENSESALESFLAG", length = 1, nullable = true)
	private String expensesalesflag;

	@Size(min = 0, max = 1)
	@Column(name = "EXPENSEPRODUCTIONFLAG", length = 1, nullable = true)
	private String expenseproductionflag;

	@Size(min = 0, max = 10)
	@Column(name = "COSTCENTERNEXT", length = 10, nullable = true)
	private String costcenternext;

	@Column(name = "VENDOR", nullable = true)
	private Integer vendor;

	@Column(name = "AMOUNTINVOICES", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal amountinvoices;

	@Column(name = "AMOUNTREQUISITIONS", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal amountrequisitions;

	@Column(name = "AMOUNTADVANCES", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal amountadvances;

	@Column(name = "AMOUNTOTHERS01", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal amountothers01;

	@Column(name = "AMOUNTOTHERS02", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal amountothers02;

	@Column(name = "AMOUNTOTHERS03", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal amountothers03;

	@Size(min = 0, max = 20)
	@Column(name = "VENDORSIGNFILE", length = 20, nullable = true)
	private String vendorsignfile;

	@Size(min = 0, max = 20)
	@Column(name = "ACCOUNT", length = 20, nullable = true)
	private String account;

	@Size(min = 0, max = 1)
	@Column(name = "STATUS", length = 1, nullable = true)
	private String status;

	@Size(min = 0, max = 10)
	@Column(name = "LASTUSER", length = 10, nullable = true)
	private String lastuser;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "LASTDATE", nullable = true)
	private java.util.Date lastdate;

	@Size(min = 0, max = 1)
	@Column(name = "COSTPRODUCTIONLEVEL", length = 1, nullable = true)
	private String costproductionlevel;

	@Size(min = 0, max = 20)
	@Column(name = "ACCOUNTDESTINATION", length = 20, nullable = true)
	private String accountdestination;

	@Size(min = 0, max = 2)
	@Column(name = "COSTCENTERTYPE", length = 2, nullable = true)
	private String costcentertype;

	@Size(min = 0, max = 1)
	@Column(name = "INCOMEFLAG", length = 1, nullable = true)
	private String incomeflag;

	@Size(min = 0, max = 20)
	@Column(name = "ACCOUNTINFLATION", length = 20, nullable = true)
	private String accountinflation;

	@Size(min = 0, max = 1)
	@Column(name = "EXPENSEDIRECTFLAG", length = 1, nullable = true)
	private String expensedirectflag;

	@Size(min = 0, max = 1)
	@Column(name = "EXPENSEFIXEDFLAG", length = 1, nullable = true)
	private String expensefixedflag;

	@Size(min = 0, max = 4)
	@Column(name = "SUCURSAL", length = 4, nullable = true)
	private String sucursal;

	@Size(min = 0, max = 20)
	@Column(name = "INTERNALNUMBER", length = 20, nullable = true)
	private String internalnumber;

	@Size(min = 0, max = 4)
	@Column(name = "RACIONPERFIL", length = 4, nullable = true)
	private String racionperfil;

	@Size(min = 0, max = 4)
	@Column(name = "RACIONPERFILMANTENIMIENTO", length = 4, nullable = true)
	private String racionperfilmantenimiento;

	@Size(min = 0, max = 60)
	@Column(name = "ADDRESS", length = 60, nullable = true)
	private String address;

	@Size(min = 0, max = 1)
	@Column(name = "MULTICOMPANYFLAG", length = 1, nullable = true)
	private String multicompanyflag;

	@Size(min = 0, max = 1)
	@Column(name = "CPAUTOMATICINVOFLAG", length = 1, nullable = true)
	private String cpautomaticinvoflag;

	@Size(min = 0, max = 1)
	@Column(name = "CPAUTOMATICREQUFLAG", length = 1, nullable = true)
	private String cpautomaticrequflag;

	@Size(min = 0, max = 36)
	@Column(name = "UUID", length = 36, nullable = true)
	private String uuid;	
	
	@Transient
	private String auxParametro1 = "N";
	
	@Transient
	private String auxParametro2 = "N";
	
	
	
	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getAuxParametro1() {
		return auxParametro1;
	}


	public void setAuxParametro1(String auxParametro1) {
		this.auxParametro1 = auxParametro1;
	}


	public String getAuxParametro2() {
		return auxParametro2;
	}


	public void setAuxParametro2(String auxParametro2) {
		this.auxParametro2 = auxParametro2;
	}


	public BeanAcCostcentermst() {
		pk = new BeanAcCostcentermstPk();
	}


	public BeanAcCostcentermst(BeanAcCostcentermstPk pk) {
		this.pk = pk;
	}

	public BeanAcCostcentermstPk getPk() {
		return pk;
	}

	public void setPk(BeanAcCostcentermstPk pk) {
		this.pk = pk;
	}
	/**
	 * 
	 * 
	 * @campo LocalName
	*/
	public String getLocalname() {
		return localname;
	}

	/**
	 * 
	 * 
	 * @campo LocalName
	*/
	public void setLocalname(String localname) {
		this.localname = localname;
	}
	/**
	 * 
	 * 
	 * @campo EnglishName
	*/
	public String getEnglishname() {
		return englishname;
	}

	/**
	 * 
	 * 
	 * @campo EnglishName
	*/
	public void setEnglishname(String englishname) {
		this.englishname = englishname;
	}
	/**
	 * 
	 * 
	 * @campo CostCenterClasification
	*/
	public String getCostcenterclasification() {
		return costcenterclasification;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterClasification
	*/
	public void setCostcenterclasification(String costcenterclasification) {
		this.costcenterclasification = costcenterclasification;
	}
	/**
	 * 
	 * 
	 * @campo CostCenterGroup
	*/
	public String getCostcentergroup() {
		return costcentergroup;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterGroup
	*/
	public void setCostcentergroup(String costcentergroup) {
		this.costcentergroup = costcentergroup;
	}
	/**
	 * 
	 * 
	 * @campo CostCenterSubGroup
	*/
	public String getCostcentersubgroup() {
		return costcentersubgroup;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterSubGroup
	*/
	public void setCostcentersubgroup(String costcentersubgroup) {
		this.costcentersubgroup = costcentersubgroup;
	}
	/**
	 * 
	 * 
	 * @campo CostCenterRelation
	*/
	public String getCostcenterrelation() {
		return costcenterrelation;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterRelation
	*/
	public void setCostcenterrelation(String costcenterrelation) {
		this.costcenterrelation = costcenterrelation;
	}
	/**
	 * 
	 * 
	 * @campo Responsible
	*/
	public String getResponsible() {
		return responsible;
	}

	/**
	 * 
	 * 
	 * @campo Responsible
	*/
	public void setResponsible(String responsible) {
		this.responsible = responsible;
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
	 * @campo CostCenterNext
	*/
	public String getCostcenternext() {
		return costcenternext;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterNext
	*/
	public void setCostcenternext(String costcenternext) {
		this.costcenternext = costcenternext;
	}
	/**
	 * 
	 * 
	 * @campo Vendor
	*/
	public Integer getVendor() {
		return vendor;
	}

	/**
	 * 
	 * 
	 * @campo Vendor
	*/
	public void setVendor(Integer vendor) {
		this.vendor = vendor;
	}
	/**
	 * 
	 * 
	 * @campo AmountInvoices
	*/
	public java.math.BigDecimal getAmountinvoices() {
		return amountinvoices;
	}

	/**
	 * 
	 * 
	 * @campo AmountInvoices
	*/
	public void setAmountinvoices(java.math.BigDecimal amountinvoices) {
		this.amountinvoices = amountinvoices;
	}
	/**
	 * 
	 * 
	 * @campo AmountRequisitions
	*/
	public java.math.BigDecimal getAmountrequisitions() {
		return amountrequisitions;
	}

	/**
	 * 
	 * 
	 * @campo AmountRequisitions
	*/
	public void setAmountrequisitions(java.math.BigDecimal amountrequisitions) {
		this.amountrequisitions = amountrequisitions;
	}
	/**
	 * 
	 * 
	 * @campo AmountAdvances
	*/
	public java.math.BigDecimal getAmountadvances() {
		return amountadvances;
	}

	/**
	 * 
	 * 
	 * @campo AmountAdvances
	*/
	public void setAmountadvances(java.math.BigDecimal amountadvances) {
		this.amountadvances = amountadvances;
	}
	/**
	 * 
	 * 
	 * @campo AmountOthers01
	*/
	public java.math.BigDecimal getAmountothers01() {
		return amountothers01;
	}

	/**
	 * 
	 * 
	 * @campo AmountOthers01
	*/
	public void setAmountothers01(java.math.BigDecimal amountothers01) {
		this.amountothers01 = amountothers01;
	}
	/**
	 * 
	 * 
	 * @campo AmountOthers02
	*/
	public java.math.BigDecimal getAmountothers02() {
		return amountothers02;
	}

	/**
	 * 
	 * 
	 * @campo AmountOthers02
	*/
	public void setAmountothers02(java.math.BigDecimal amountothers02) {
		this.amountothers02 = amountothers02;
	}
	/**
	 * 
	 * 
	 * @campo AmountOthers03
	*/
	public java.math.BigDecimal getAmountothers03() {
		return amountothers03;
	}

	/**
	 * 
	 * 
	 * @campo AmountOthers03
	*/
	public void setAmountothers03(java.math.BigDecimal amountothers03) {
		this.amountothers03 = amountothers03;
	}
	/**
	 * 
	 * 
	 * @campo VendorSignFile
	*/
	public String getVendorsignfile() {
		return vendorsignfile;
	}

	/**
	 * 
	 * 
	 * @campo VendorSignFile
	*/
	public void setVendorsignfile(String vendorsignfile) {
		this.vendorsignfile = vendorsignfile;
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
	 * @campo Status
	*/
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * 
	 * @campo Status
	*/
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 
	 * 
	 * @campo LastUser
	*/
	public String getLastuser() {
		return lastuser;
	}

	/**
	 * 
	 * 
	 * @campo LastUser
	*/
	public void setLastuser(String lastuser) {
		this.lastuser = lastuser;
	}
	/**
	 * 
	 * 
	 * @campo Lastdate
	*/
	public java.util.Date getLastdate() {
		return lastdate;
	}

	/**
	 * 
	 * 
	 * @campo Lastdate
	*/
	public void setLastdate(java.util.Date lastdate) {
		this.lastdate = lastdate;
	}
	/**
	 * 
	 * 
	 * @campo CostProductionLevel
	*/
	public String getCostproductionlevel() {
		return costproductionlevel;
	}

	/**
	 * 
	 * 
	 * @campo CostProductionLevel
	*/
	public void setCostproductionlevel(String costproductionlevel) {
		this.costproductionlevel = costproductionlevel;
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
	 * @campo CostCenterType
	*/
	public String getCostcentertype() {
		return costcentertype;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterType
	*/
	public void setCostcentertype(String costcentertype) {
		this.costcentertype = costcentertype;
	}
	/**
	 * 
	 * 
	 * @campo IncomeFlag
	*/
	public String getIncomeflag() {
		return incomeflag;
	}

	/**
	 * 
	 * 
	 * @campo IncomeFlag
	*/
	public void setIncomeflag(String incomeflag) {
		this.incomeflag = incomeflag;
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
	 * @campo InternalNumber
	*/
	public String getInternalnumber() {
		return internalnumber;
	}

	/**
	 * 
	 * 
	 * @campo InternalNumber
	*/
	public void setInternalnumber(String internalnumber) {
		this.internalnumber = internalnumber;
	}
	/**
	 * 
	 * 
	 * @campo RacionPerfil
	*/
	public String getRacionperfil() {
		return racionperfil;
	}

	/**
	 * 
	 * 
	 * @campo RacionPerfil
	*/
	public void setRacionperfil(String racionperfil) {
		this.racionperfil = racionperfil;
	}
	/**
	 * 
	 * 
	 * @campo RacionPerfilMantenimiento
	*/
	public String getRacionperfilmantenimiento() {
		return racionperfilmantenimiento;
	}

	/**
	 * 
	 * 
	 * @campo RacionPerfilMantenimiento
	*/
	public void setRacionperfilmantenimiento(String racionperfilmantenimiento) {
		this.racionperfilmantenimiento = racionperfilmantenimiento;
	}
	/**
	 * 
	 * 
	 * @campo Address
	*/
	public String getAddress() {
		return address;
	}

	/**
	 * 
	 * 
	 * @campo Address
	*/
	public void setAddress(String address) {
		this.address = address;
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
	 * @campo CPAutomaticInvoFlag
	*/
	public String getCpautomaticinvoflag() {
		return cpautomaticinvoflag;
	}

	/**
	 * 
	 * 
	 * @campo CPAutomaticInvoFlag
	*/
	public void setCpautomaticinvoflag(String cpautomaticinvoflag) {
		this.cpautomaticinvoflag = cpautomaticinvoflag;
	}
	/**
	 * 
	 * 
	 * @campo CPAutomaticRequFlag
	*/
	public String getCpautomaticrequflag() {
		return cpautomaticrequflag;
	}

	/**
	 * 
	 * 
	 * @campo CPAutomaticRequFlag
	*/
	public void setCpautomaticrequflag(String cpautomaticrequflag) {
		this.cpautomaticrequflag = cpautomaticrequflag;
	}

}
