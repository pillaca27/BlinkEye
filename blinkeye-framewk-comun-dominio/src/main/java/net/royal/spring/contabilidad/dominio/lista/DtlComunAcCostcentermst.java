package net.royal.spring.contabilidad.dominio.lista;

import net.royal.spring.contabilidad.dominio.BeanAcCostcentermst;

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
public class DtlComunAcCostcentermst extends DominioTransaccion implements java.io.Serializable{


	private String costcenter;
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
	private Integer vendor;
	private java.math.BigDecimal amountinvoices;
	private java.math.BigDecimal amountrequisitions;
	private java.math.BigDecimal amountadvances;
	private java.math.BigDecimal amountothers01;
	private java.math.BigDecimal amountothers02;
	private java.math.BigDecimal amountothers03;
	private String vendorsignfile;
	private String account;
	private String status;
	private String lastuser;
	private java.util.Date lastdate;
	private String costproductionlevel;
	private String accountdestination;
	private String costcentertype;
	private String incomeflag;
	private String accountinflation;
	private String expensedirectflag;
	private String expensefixedflag;
	private String sucursal;
	private String internalnumber;
	private String racionperfil;
	private String racionperfilmantenimiento;
	private String address;
	private String multicompanyflag;
	private String cpautomaticinvoflag;
	private String cpautomaticrequflag;
	private String estadodescripcion;
	private String admin;
	private String ventas;
	private String prod;
	private String finanzas;
	private String uuid;
	

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getEstadodescripcion() {
		return estadodescripcion;
	}

	public void setEstadodescripcion(String estadodescripcion) {
		this.estadodescripcion = estadodescripcion;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getVentas() {
		return ventas;
	}

	public void setVentas(String ventas) {
		this.ventas = ventas;
	}

	public String getProd() {
		return prod;
	}

	public void setProd(String prod) {
		this.prod = prod;
	}

	public String getFinanzas() {
		return finanzas;
	}

	public void setFinanzas(String finanzas) {
		this.finanzas = finanzas;
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
