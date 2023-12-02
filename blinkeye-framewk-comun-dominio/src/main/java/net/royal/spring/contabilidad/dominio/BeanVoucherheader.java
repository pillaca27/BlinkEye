package net.royal.spring.contabilidad.dominio;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.voucherheader
 */
@Entity
@Table(name = "VOUCHERHEADER")
public class BeanVoucherheader extends DominioTransaccion implements java.io.Serializable {

	@EmbeddedId
	private BeanVoucherheaderPk pk;

	@Size(min = 0, max = 4)
	@Column(name = "BUSINESSUNIT", length = 4, nullable = true)
	private String businessunit;

	@Size(min = 0, max = 4)
	@Column(name = "VOUCHERMODEL", length = 4, nullable = true)
	private String vouchermodel;

	@Size(min = 0, max = 2)
	@Column(name = "CURRENCY", length = 2, nullable = true)
	private String currency;

	@Size(min = 0, max = 1)
	@Column(name = "VOUCHERTYPE", length = 1, nullable = true)
	private String vouchertype;

	@Size(min = 0, max = 2)
	@Column(name = "LEDGER", length = 2, nullable = true)
	private String ledger;

	@Size(min = 0, max = 8)
	@Column(name = "VOUCHERSOURCE", length = 8, nullable = true)
	private String vouchersource;

	@Size(min = 0, max = 3)
	@Column(name = "DEPARTMENT", length = 3, nullable = true)
	private String department;

	@Column(name = "RUNNUMBER", nullable = true)
	private Integer runnumber;

	@Column(name = "POSTINGNUMBER", nullable = true)
	private Integer postingnumber;

	@Column(name = "DOLLARCREDITS", precision = 19, scale = 4, nullable = true)
	private java.math.BigDecimal dollarcredits;

	@Column(name = "DOLLARDEBITS", precision = 19, scale = 4, nullable = true)
	private java.math.BigDecimal dollardebits;

	@Column(name = "LOCALCREDITS", precision = 19, scale = 4, nullable = true)
	private java.math.BigDecimal localcredits;

	@Column(name = "LOCALDEBITS", precision = 19, scale = 4, nullable = true)
	private java.math.BigDecimal localdebits;

	@Column(name = "TOTALLINES", nullable = true)
	private Integer totallines;

	@Column(name = "TOTALERRORLINES", nullable = true)
	private Integer totalerrorlines;

	@Column(name = "PREPAREDBY", nullable = true)
	private Integer preparedby;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "PREPAREDDATE", nullable = true)
	private java.util.Date prepareddate;

	@Column(name = "APPROVEDBY", nullable = true)
	private Integer approvedby;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "APPROVEDDATE", nullable = true)
	private java.util.Date approveddate;

	@Column(name = "REPRINTNUMBER", nullable = true)
	private Integer reprintnumber;

	@Size(min = 0, max = 200)
	@Column(name = "VOUCHERTITLE", length = 200, nullable = true)
	private String vouchertitle;

	@Size(min = 0, max = 200)
	@Column(name = "VOUCHERCOMMENTS", length = 200, nullable = true)
	private String vouchercomments;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "VOUCHERDATE", nullable = true)
	private java.util.Date voucherdate;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "EXCHANGERATEPERIOD", nullable = true)
	private java.util.Date exchangerateperiod;

	@Column(name = "EXCHANGERATE", precision = 24, scale = 0, nullable = true)
	private java.math.BigDecimal exchangerate;

	@Size(min = 0, max = 4)
	@Column(name = "VOUCHERCLASIFICATION", length = 4, nullable = true)
	private String voucherclasification;

	@Size(min = 0, max = 10)
	@Column(name = "INTERNALNUMBER", length = 10, nullable = true)
	private String internalnumber;

	@Size(min = 0, max = 2)
	@Column(name = "STATUS", length = 2, nullable = true)
	private String status;

	@Size(min = 0, max = 20)
	@Column(name = "LASTUSER", length = 20, nullable = true)
	private String lastuser;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "LASTDATE", nullable = true)
	private java.util.Date lastdate;

	@Size(min = 0, max = 20)
	@Column(name = "REPLICATIONUNIT", length = 20, nullable = true)
	private String replicationunit;

	@Size(min = 0, max = 1)
	@Column(name = "TRANSFERFLAG", length = 1, nullable = true)
	private String transferflag;

	@Size(min = 0, max = 100)
	@Column(name = "FILEATTACHED", length = 100, nullable = true)
	private String fileattached;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "VALUEDATE", nullable = true)
	private java.util.Date valuedate;

	@Size(min = 0, max = 12)
	@Column(name = "VOUCHERREVERSE", length = 12, nullable = true)
	private String voucherreverse;

	@Size(min = 0, max = 6)
	@Column(name = "CASHFLOWFISCAL", length = 6, nullable = true)
	private String cashflowfiscal;

	@Transient
	private String parametro;

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public BeanVoucherheader() {
		pk = new BeanVoucherheaderPk();
	}

	public BeanVoucherheader(BeanVoucherheaderPk pk) {
		this.pk = pk;
	}

	public BeanVoucherheaderPk getPk() {
		return pk;
	}

	public void setPk(BeanVoucherheaderPk pk) {
		this.pk = pk;
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
		if (vouchersource == null)
			return "";
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

}
