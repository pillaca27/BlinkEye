package net.royal.spring.contabilidad.dominio.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.contabilidad.dominio.BeanAcCostcentermst;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;

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
	private Integer vendor;
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
	
 
	private String costproductionlevel;
	private String accountdestination;
	private String costcentertype;
	private String racionperfil;
	private String racionperfilmantenimiento;
	private String address;
	private String multicompanyflag;
	private String cpautomaticinvoflag;
	private String cpautomaticrequflag;
	private String grupo;
	private String subgrupo;
	private String auxParametro1;
	private String auxParametro2;
	private String vendorDescri;
	private String costcenternextDescri;
	private List<DtoComunAcCostcenteraccount>lstDetalle1= new ArrayList<DtoComunAcCostcenteraccount>();
	private List<DtoComunAcCostcentervendor>lstDetalle2= new ArrayList<DtoComunAcCostcentervendor>();
	private List<DtoComunAcCostcenterdestvalid>lstDetalle3= new ArrayList<DtoComunAcCostcenterdestvalid>();
	private List<DtoComunAcCostcenterafe>lstDetalle4= new ArrayList<DtoComunAcCostcenterafe>();
	private String uuid;	
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
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

	public Integer getVendor() {
		return vendor;
	}

	public void setVendor(Integer vendor) {
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
	public String getCostproductionlevel() {
		return costproductionlevel;
	}
	public void setCostproductionlevel(String costproductionlevel) {
		this.costproductionlevel = costproductionlevel;
	}
	public String getAccountdestination() {
		return accountdestination;
	}
	public void setAccountdestination(String accountdestination) {
		this.accountdestination = accountdestination;
	}
	public String getCostcentertype() {
		return costcentertype;
	}
	public void setCostcentertype(String costcentertype) {
		this.costcentertype = costcentertype;
	}
	public String getRacionperfil() {
		return racionperfil;
	}
	public void setRacionperfil(String racionperfil) {
		this.racionperfil = racionperfil;
	}
	public String getRacionperfilmantenimiento() {
		return racionperfilmantenimiento;
	}
	public void setRacionperfilmantenimiento(String racionperfilmantenimiento) {
		this.racionperfilmantenimiento = racionperfilmantenimiento;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMulticompanyflag() {
		return multicompanyflag;
	}
	public void setMulticompanyflag(String multicompanyflag) {
		this.multicompanyflag = multicompanyflag;
	}
	public String getCpautomaticinvoflag() {
		return cpautomaticinvoflag;
	}
	public void setCpautomaticinvoflag(String cpautomaticinvoflag) {
		this.cpautomaticinvoflag = cpautomaticinvoflag;
	}
	public String getCpautomaticrequflag() {
		return cpautomaticrequflag;
	}
	public void setCpautomaticrequflag(String cpautomaticrequflag) {
		this.cpautomaticrequflag = cpautomaticrequflag;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getSubgrupo() {
		return subgrupo;
	}
	public void setSubgrupo(String subgrupo) {
		this.subgrupo = subgrupo;
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
	public String getVendorDescri() {
		return vendorDescri;
	}
	public void setVendorDescri(String vendorDescri) {
		this.vendorDescri = vendorDescri;
	}
	public String getCostcenternextDescri() {
		return costcenternextDescri;
	}
	public void setCostcenternextDescri(String costcenternextDescri) {
		this.costcenternextDescri = costcenternextDescri;
	}
	public List<DtoComunAcCostcenteraccount> getLstDetalle1() {
		return lstDetalle1;
	}
	public void setLstDetalle1(List<DtoComunAcCostcenteraccount> lstDetalle1) {
		this.lstDetalle1 = lstDetalle1;
	}
	public List<DtoComunAcCostcentervendor> getLstDetalle2() {
		return lstDetalle2;
	}
	public void setLstDetalle2(List<DtoComunAcCostcentervendor> lstDetalle2) {
		this.lstDetalle2 = lstDetalle2;
	}
	public List<DtoComunAcCostcenterdestvalid> getLstDetalle3() {
		return lstDetalle3;
	}
	public void setLstDetalle3(List<DtoComunAcCostcenterdestvalid> lstDetalle3) {
		this.lstDetalle3 = lstDetalle3;
	}
	public List<DtoComunAcCostcenterafe> getLstDetalle4() {
		return lstDetalle4;
	}
	public void setLstDetalle4(List<DtoComunAcCostcenterafe> lstDetalle4) {
		this.lstDetalle4 = lstDetalle4;
	}
 
	
	
	public BeanAcCostcentermst obtenerBean() {
		return obtenerBeanCore(new BeanAcCostcentermst(),ConstantePantallaAccion.NINGUNO);
	}

	public BeanAcCostcentermst obtenerBeanRegistrar() {
		return obtenerBeanCore(new BeanAcCostcentermst(),ConstantePantallaAccion.INSERTAR);
	}

	public BeanAcCostcentermst obtenerBeanActualizar(BeanAcCostcentermst bean) {
		return obtenerBeanCore(bean,ConstantePantallaAccion.ACTUALIZAR);
	}

	private BeanAcCostcentermst obtenerBeanCore(BeanAcCostcentermst bean,String tipo) {
		if (UString.esNuloVacio(tipo))
			tipo=ConstantePantallaAccion.NINGUNO;

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		switch (tipo) {
		case ConstantePantallaAccion.INSERTAR:
		case ConstantePantallaAccion.NINGUNO:
			bean.getPk().setCostcenter(costcenter);
			bean.setLocalname(localname);
			bean.setEnglishname(englishname);
			bean.setCostcenterclasification(costcenterclasification);
			bean.setCostcentergroup(costcentergroup);
			bean.setCostcentersubgroup(costcentersubgroup);
			bean.setCostcenterrelation(costcenterrelation);
			bean.setResponsible(responsible);
			bean.setExpensefinanceflag(expensefinanceflag);
			bean.setExpenseadministrativeflag(expenseadministrativeflag);
			bean.setExpensesalesflag(expensesalesflag);
			bean.setExpenseproductionflag(expenseproductionflag);
			bean.setCostcenternext(costcenternext);
			bean.setVendor(vendor);
			bean.setAmountinvoices(amountinvoices);
			bean.setAmountrequisitions(amountrequisitions);
			bean.setAmountadvances(amountadvances);
			bean.setAmountothers01(amountothers01);
			bean.setAmountothers02(amountothers02);
			bean.setAmountothers03(amountothers03);
			bean.setVendorsignfile(vendorsignfile);
			bean.setAccount(account);
			bean.setStatus(status);
			bean.setLastuser(lastuser);
			bean.setLastdate(lastdate);
			bean.setCostproductionlevel(costproductionlevel);
			bean.setAccountdestination(accountdestination);
			bean.setCostcentertype(costcentertype);
			bean.setIncomeflag(incomeflag);
			bean.setAccountinflation(accountinflation);
			bean.setExpensedirectflag(expensedirectflag);
			bean.setExpensefixedflag(expensefixedflag);
			bean.setSucursal(sucursal);
			bean.setInternalnumber(internalnumber);
			bean.setRacionperfil(racionperfil);
			bean.setRacionperfilmantenimiento(racionperfilmantenimiento);
			bean.setAddress(address);
			bean.setMulticompanyflag(multicompanyflag);
			bean.setCpautomaticinvoflag(cpautomaticinvoflag);
			bean.setCpautomaticrequflag(cpautomaticrequflag);			
			bean.setAuxParametro1(auxParametro1);
			bean.setAuxParametro2(auxParametro2);
 			bean.setUuid(uuid);
			break;
		case ConstantePantallaAccion.ACTUALIZAR:
			//bean.getPk().setCostcenter(costcenter);
			bean.setLocalname(localname);
			bean.setEnglishname(englishname);
			bean.setCostcenterclasification(costcenterclasification);
			bean.setCostcentergroup(costcentergroup);
			bean.setCostcentersubgroup(costcentersubgroup);
			bean.setCostcenterrelation(costcenterrelation);
			bean.setResponsible(responsible);
			bean.setExpensefinanceflag(expensefinanceflag);
			bean.setExpenseadministrativeflag(expenseadministrativeflag);
			bean.setExpensesalesflag(expensesalesflag);
			bean.setExpenseproductionflag(expenseproductionflag);
			bean.setCostcenternext(costcenternext);
			bean.setVendor(vendor);
			bean.setAmountinvoices(amountinvoices);
			bean.setAmountrequisitions(amountrequisitions);
			bean.setAmountadvances(amountadvances);
			bean.setAmountothers01(amountothers01);
			bean.setAmountothers02(amountothers02);
			bean.setAmountothers03(amountothers03);
			bean.setVendorsignfile(vendorsignfile);
			bean.setAccount(account);
			bean.setStatus(status);
			bean.setLastuser(lastuser);
			bean.setLastdate(lastdate);
			bean.setCostproductionlevel(costproductionlevel);
			bean.setAccountdestination(accountdestination);
			bean.setCostcentertype(costcentertype);
			bean.setIncomeflag(incomeflag);
			bean.setAccountinflation(accountinflation);
			bean.setExpensedirectflag(expensedirectflag);
			bean.setExpensefixedflag(expensefixedflag);
			bean.setSucursal(sucursal);
			bean.setInternalnumber(internalnumber);
			bean.setRacionperfil(racionperfil);
			bean.setRacionperfilmantenimiento(racionperfilmantenimiento);
			bean.setAddress(address);
			bean.setMulticompanyflag(multicompanyflag);
			bean.setCpautomaticinvoflag(cpautomaticinvoflag);
			bean.setCpautomaticrequflag(cpautomaticrequflag);			
			bean.setAuxParametro1(auxParametro1);
			bean.setAuxParametro2(auxParametro2);
 			//bean.setUuid(uuid);
			break;
		}

		return bean;
	}
}
