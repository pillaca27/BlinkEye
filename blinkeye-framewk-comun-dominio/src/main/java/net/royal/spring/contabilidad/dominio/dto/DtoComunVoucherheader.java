package net.royal.spring.contabilidad.dominio.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.royal.spring.contabilidad.dominio.BeanVoucherheader;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

/**
 * 
 * 
 * @tabla dbo.voucherheader
 */
public class DtoComunVoucherheader extends DominioTransaccion implements java.io.Serializable {

	private String period;
	private String companyowner;
	private String voucherno;
	private String businessunit;
	private String vouchermodel;
	private String currency;
	private String vouchertype;
	private String ledger;
	private String vouchersource;
	private String department;
	private Integer runnumber;
	private Integer postingnumber;
	private java.math.BigDecimal dollarcredits;
	private java.math.BigDecimal dollardebits;
	private java.math.BigDecimal localcredits;
	private java.math.BigDecimal localdebits;
	private Integer totallines;
	private Integer totalerrorlines;
	private Integer preparedby;
	private java.util.Date prepareddate;
	private Integer approvedby;
	private java.util.Date approveddate;
	private Integer reprintnumber;
	private String vouchertitle;
	private String vouchercomments;
	private java.util.Date voucherdate;
	private java.util.Date exchangerateperiod;
	private java.math.BigDecimal exchangerate;
	private String voucherclasification;
	private String internalnumber;
	private String status;
	private String lastuser;
	private java.util.Date lastdate;
	private String replicationunit;
	private String transferflag;
	private String fileattached;
	private java.util.Date valuedate;
	private String voucherreverse;
	private String cashflowfiscal;
	private String auxAccion;
	private String voucherno_first;
	private String voucherno_last;

	private String preparadopor;
	private String aprobadopor;
	private String year;
	private String mes;
	private String fecha;
	private String fecha_tc;
	private Boolean batch_mode;
	private List<DtoComunVoucherdetail> dw_detail = new ArrayList<DtoComunVoucherdetail>();
	private BigDecimal localacum;
	private BigDecimal dolaracum;
	private BigDecimal total_local_02;
	private BigDecimal total_local_04;
	private BigDecimal total_dolar_02;
	private BigDecimal total_dolar_04;
	private String indicadorvoucher;
	private String parametroVOUCHPOSTS;
	private String tipoExportar;

	public String getTipoExportar() {
		return tipoExportar;
	}

	public void setTipoExportar(String tipoExportar) {
		this.tipoExportar = tipoExportar;
	}

	public String getParametroVOUCHPOSTS() {
		if (parametroVOUCHPOSTS == null)
			return "N";
		return parametroVOUCHPOSTS;
	}

	public void setParametroVOUCHPOSTS(String parametroVOUCHPOSTS) {
		this.parametroVOUCHPOSTS = parametroVOUCHPOSTS;
	}

	public String getIndicadorvoucher() {
		if (indicadorvoucher == null)
			return "N";
		return indicadorvoucher;
	}

	public void setIndicadorvoucher(String indicadorvoucher) {
		this.indicadorvoucher = indicadorvoucher;
	}

	public BigDecimal getTotal_local_02() {
		return total_local_02;
	}

	public void setTotal_local_02(BigDecimal total_local_02) {
		this.total_local_02 = total_local_02;
	}

	public BigDecimal getTotal_local_04() {
		return total_local_04;
	}

	public void setTotal_local_04(BigDecimal total_local_04) {
		this.total_local_04 = total_local_04;
	}

	public BigDecimal getTotal_dolar_02() {
		return total_dolar_02;
	}

	public void setTotal_dolar_02(BigDecimal total_dolar_02) {
		this.total_dolar_02 = total_dolar_02;
	}

	public BigDecimal getTotal_dolar_04() {
		return total_dolar_04;
	}

	public void setTotal_dolar_04(BigDecimal total_dolar_04) {
		this.total_dolar_04 = total_dolar_04;
	}

	public BigDecimal getLocalacum() {
		if (localacum == null)
			return new BigDecimal(0);
		return localacum;
	}

	public void setLocalacum(BigDecimal localacum) {
		this.localacum = localacum;
	}

	public BigDecimal getDolaracum() {
		if (dolaracum == null)
			return new BigDecimal(0);
		return dolaracum;
	}

	public void setDolaracum(BigDecimal dolaracum) {
		this.dolaracum = dolaracum;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getFecha_tc() {
		return fecha_tc;
	}

	public void setFecha_tc(String fecha_tc) {
		this.fecha_tc = fecha_tc;
	}

	public Boolean getBatch_mode() {
		return batch_mode;
	}

	public void setBatch_mode(Boolean batch_mode) {
		this.batch_mode = batch_mode;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPreparadopor() {
		return preparadopor;
	}

	public void setPreparadopor(String preparadopor) {
		this.preparadopor = preparadopor;
	}

	public String getAprobadopor() {
		return aprobadopor;
	}

	public void setAprobadopor(String aprobadopor) {
		this.aprobadopor = aprobadopor;
	}

	public List<DtoComunVoucherdetail> getDw_detail() {
		return dw_detail;
	}

	public void setDw_detail(List<DtoComunVoucherdetail> dw_detail) {
		this.dw_detail = dw_detail;
	}

	public String getVoucherno_first() {
		return voucherno_first;
	}

	public void setVoucherno_first(String voucherno_first) {
		this.voucherno_first = voucherno_first;
	}

	public String getVoucherno_last() {
		return voucherno_last;
	}

	public void setVoucherno_last(String voucherno_last) {
		this.voucherno_last = voucherno_last;
	}

	public String getAuxAccion() {
		return auxAccion;
	}

	public void setAuxAccion(String auxAccion) {
		this.auxAccion = auxAccion;
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
	 * @campo BusinessUnit
	 */
	public String getBusinessunit() {
		return businessunit;
	}

	/**
	 * 
	 * 
	 * @campo BusinessUnit
	 */
	public void setBusinessunit(String businessunit) {
		this.businessunit = businessunit;
	}

	/**
	 * 
	 * 
	 * @campo vouchermodel
	 */
	public String getVouchermodel() {
		return vouchermodel;
	}

	/**
	 * 
	 * 
	 * @campo vouchermodel
	 */
	public void setVouchermodel(String vouchermodel) {
		this.vouchermodel = vouchermodel;
	}

	/**
	 * 
	 * 
	 * @campo currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * 
	 * 
	 * @campo currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * 
	 * 
	 * @campo vouchertype
	 */
	public String getVouchertype() {
		return vouchertype;
	}

	/**
	 * 
	 * 
	 * @campo vouchertype
	 */
	public void setVouchertype(String vouchertype) {
		this.vouchertype = vouchertype;
	}

	/**
	 * 
	 * 
	 * @campo ledger
	 */
	public String getLedger() {
		return ledger;
	}

	/**
	 * 
	 * 
	 * @campo ledger
	 */
	public void setLedger(String ledger) {
		this.ledger = ledger;
	}

	/**
	 * 
	 * 
	 * @campo vouchersource
	 */
	public String getVouchersource() {
		return vouchersource;
	}

	/**
	 * 
	 * 
	 * @campo vouchersource
	 */
	public void setVouchersource(String vouchersource) {
		this.vouchersource = vouchersource;
	}

	/**
	 * 
	 * 
	 * @campo department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * 
	 * 
	 * @campo department
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * 
	 * 
	 * @campo runnumber
	 */
	public Integer getRunnumber() {
		return runnumber;
	}

	/**
	 * 
	 * 
	 * @campo runnumber
	 */
	public void setRunnumber(Integer runnumber) {
		this.runnumber = runnumber;
	}

	/**
	 * 
	 * 
	 * @campo postingnumber
	 */
	public Integer getPostingnumber() {
		return postingnumber;
	}

	/**
	 * 
	 * 
	 * @campo postingnumber
	 */
	public void setPostingnumber(Integer postingnumber) {
		this.postingnumber = postingnumber;
	}

	/**
	 * 
	 * 
	 * @campo dollarcredits
	 */
	public java.math.BigDecimal getDollarcredits() {
		return dollarcredits;
	}

	/**
	 * 
	 * 
	 * @campo dollarcredits
	 */
	public void setDollarcredits(java.math.BigDecimal dollarcredits) {
		this.dollarcredits = dollarcredits;
	}

	/**
	 * 
	 * 
	 * @campo dollardebits
	 */
	public java.math.BigDecimal getDollardebits() {
		return dollardebits;
	}

	/**
	 * 
	 * 
	 * @campo dollardebits
	 */
	public void setDollardebits(java.math.BigDecimal dollardebits) {
		this.dollardebits = dollardebits;
	}

	/**
	 * 
	 * 
	 * @campo localcredits
	 */
	public java.math.BigDecimal getLocalcredits() {
		return localcredits;
	}

	/**
	 * 
	 * 
	 * @campo localcredits
	 */
	public void setLocalcredits(java.math.BigDecimal localcredits) {
		this.localcredits = localcredits;
	}

	/**
	 * 
	 * 
	 * @campo localdebits
	 */
	public java.math.BigDecimal getLocaldebits() {
		return localdebits;
	}

	/**
	 * 
	 * 
	 * @campo localdebits
	 */
	public void setLocaldebits(java.math.BigDecimal localdebits) {
		this.localdebits = localdebits;
	}

	/**
	 * 
	 * 
	 * @campo totallines
	 */
	public Integer getTotallines() {
		return totallines;
	}

	/**
	 * 
	 * 
	 * @campo totallines
	 */
	public void setTotallines(Integer totallines) {
		this.totallines = totallines;
	}

	/**
	 * 
	 * 
	 * @campo totalerrorlines
	 */
	public Integer getTotalerrorlines() {
		return totalerrorlines;
	}

	/**
	 * 
	 * 
	 * @campo totalerrorlines
	 */
	public void setTotalerrorlines(Integer totalerrorlines) {
		this.totalerrorlines = totalerrorlines;
	}

	/**
	 * 
	 * 
	 * @campo preparedby
	 */
	public Integer getPreparedby() {
		return preparedby;
	}

	/**
	 * 
	 * 
	 * @campo preparedby
	 */
	public void setPreparedby(Integer preparedby) {
		this.preparedby = preparedby;
	}

	/**
	 * 
	 * 
	 * @campo prepareddate
	 */
	public java.util.Date getPrepareddate() {
		return prepareddate;
	}

	/**
	 * 
	 * 
	 * @campo prepareddate
	 */
	public void setPrepareddate(java.util.Date prepareddate) {
		this.prepareddate = prepareddate;
	}

	/**
	 * 
	 * 
	 * @campo approvedby
	 */
	public Integer getApprovedby() {
		return approvedby;
	}

	/**
	 * 
	 * 
	 * @campo approvedby
	 */
	public void setApprovedby(Integer approvedby) {
		this.approvedby = approvedby;
	}

	/**
	 * 
	 * 
	 * @campo approveddate
	 */
	public java.util.Date getApproveddate() {
		return approveddate;
	}

	/**
	 * 
	 * 
	 * @campo approveddate
	 */
	public void setApproveddate(java.util.Date approveddate) {
		this.approveddate = approveddate;
	}

	/**
	 * 
	 * 
	 * @campo reprintnumber
	 */
	public Integer getReprintnumber() {
		return reprintnumber;
	}

	/**
	 * 
	 * 
	 * @campo reprintnumber
	 */
	public void setReprintnumber(Integer reprintnumber) {
		this.reprintnumber = reprintnumber;
	}

	/**
	 * 
	 * 
	 * @campo vouchertitle
	 */
	public String getVouchertitle() {
		return vouchertitle;
	}

	/**
	 * 
	 * 
	 * @campo vouchertitle
	 */
	public void setVouchertitle(String vouchertitle) {
		this.vouchertitle = vouchertitle;
	}

	/**
	 * 
	 * 
	 * @campo vouchercomments
	 */
	public String getVouchercomments() {
		return vouchercomments;
	}

	/**
	 * 
	 * 
	 * @campo vouchercomments
	 */
	public void setVouchercomments(String vouchercomments) {
		this.vouchercomments = vouchercomments;
	}

	/**
	 * 
	 * 
	 * @campo VoucherDate
	 */
	public java.util.Date getVoucherdate() {
		return voucherdate;
	}

	/**
	 * 
	 * 
	 * @campo VoucherDate
	 */
	public void setVoucherdate(java.util.Date voucherdate) {
		this.voucherdate = voucherdate;
	}

	/**
	 * 
	 * 
	 * @campo exchangerateperiod
	 */
	public java.util.Date getExchangerateperiod() {
		return exchangerateperiod;
	}

	/**
	 * 
	 * 
	 * @campo exchangerateperiod
	 */
	public void setExchangerateperiod(java.util.Date exchangerateperiod) {
		this.exchangerateperiod = exchangerateperiod;
	}

	/**
	 * 
	 * 
	 * @campo exchangerate
	 */
	public java.math.BigDecimal getExchangerate() {
		return exchangerate;
	}

	/**
	 * 
	 * 
	 * @campo exchangerate
	 */
	public void setExchangerate(java.math.BigDecimal exchangerate) {
		this.exchangerate = exchangerate;
	}

	/**
	 * 
	 * 
	 * @campo VoucherClasification
	 */
	public String getVoucherclasification() {
		return voucherclasification;
	}

	/**
	 * 
	 * 
	 * @campo VoucherClasification
	 */
	public void setVoucherclasification(String voucherclasification) {
		this.voucherclasification = voucherclasification;
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
	 * @campo ReplicationUnit
	 */
	public String getReplicationunit() {
		return replicationunit;
	}

	/**
	 * 
	 * 
	 * @campo ReplicationUnit
	 */
	public void setReplicationunit(String replicationunit) {
		this.replicationunit = replicationunit;
	}

	/**
	 * 
	 * 
	 * @campo TransferFlag
	 */
	public String getTransferflag() {
		return transferflag;
	}

	/**
	 * 
	 * 
	 * @campo TransferFlag
	 */
	public void setTransferflag(String transferflag) {
		this.transferflag = transferflag;
	}

	/**
	 * 
	 * 
	 * @campo FileAttached
	 */
	public String getFileattached() {
		return fileattached;
	}

	/**
	 * 
	 * 
	 * @campo FileAttached
	 */
	public void setFileattached(String fileattached) {
		this.fileattached = fileattached;
	}

	/**
	 * 
	 * 
	 * @campo ValueDate
	 */
	public java.util.Date getValuedate() {
		return valuedate;
	}

	/**
	 * 
	 * 
	 * @campo ValueDate
	 */
	public void setValuedate(java.util.Date valuedate) {
		this.valuedate = valuedate;
	}

	/**
	 * 
	 * 
	 * @campo VoucherReverse
	 */
	public String getVoucherreverse() {
		return voucherreverse;
	}

	/**
	 * 
	 * 
	 * @campo VoucherReverse
	 */
	public void setVoucherreverse(String voucherreverse) {
		this.voucherreverse = voucherreverse;
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

	public BeanVoucherheader obtenerBean() {
		BeanVoucherheader bean = new BeanVoucherheader();
		return obtenerBean(bean);
	}

	public BeanVoucherheader obtenerBean(BeanVoucherheader bean) {
		if (bean == null)
			bean = new BeanVoucherheader();

		bean.getPk().setPeriod(period);
		bean.getPk().setCompanyowner(companyowner);
		bean.getPk().setVoucherno(voucherno);
		bean.setBusinessunit(businessunit);
		bean.setVouchermodel(vouchermodel);
		bean.setCurrency(currency);
		bean.setVouchertype(vouchertype);
		bean.setLedger(ledger);
		bean.setVouchersource(vouchersource);
		bean.setDepartment(department);
		bean.setRunnumber(runnumber);
		bean.setPostingnumber(postingnumber);
		bean.setDollarcredits(dollarcredits);
		bean.setDollardebits(dollardebits);
		bean.setLocalcredits(localcredits);
		bean.setLocaldebits(localdebits);
		bean.setTotallines(totallines);
		bean.setTotalerrorlines(totalerrorlines);
		bean.setPreparedby(preparedby);
		bean.setPrepareddate(prepareddate);
		bean.setApprovedby(approvedby);
		bean.setApproveddate(approveddate);
		bean.setReprintnumber(reprintnumber);
		bean.setVouchertitle(vouchertitle);
		bean.setVouchercomments(vouchercomments);
		bean.setVoucherdate(voucherdate);
		bean.setExchangerateperiod(exchangerateperiod);
		bean.setExchangerate(exchangerate);
		bean.setVoucherclasification(voucherclasification);
		bean.setInternalnumber(internalnumber);
		bean.setStatus(status);
		bean.setLastuser(lastuser);
		bean.setLastdate(lastdate);
		bean.setReplicationunit(replicationunit);
		bean.setTransferflag(transferflag);
		bean.setFileattached(fileattached);
		bean.setValuedate(valuedate);
		bean.setVoucherreverse(voucherreverse);
		bean.setCashflowfiscal(cashflowfiscal);

		bean.setAuxAccion(auxAccion);
		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
