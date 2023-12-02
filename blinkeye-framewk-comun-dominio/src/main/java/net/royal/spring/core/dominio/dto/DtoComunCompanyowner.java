package net.royal.spring.core.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.core.dominio.BeanCompanyowner;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunCompanyowner extends DominioTransaccion{
	private String companyowner;
	private String description;
	private String englishdescription;
	private java.math.BigDecimal percentage;
	private String company;
	private String owner;
	private java.math.BigDecimal changerate;
	private String lastuser;
	private java.util.Date lastdate;
	private String estado;
	private String accion;
	@JsonIgnore
	private BigDecimal ROWNUM_;
 
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getCompanyowner() {
		return companyowner;
	}
	public void setCompanyowner(String companyowner) {
		this.companyowner = companyowner;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEnglishdescription() {
		return englishdescription;
	}
	public void setEnglishdescription(String englishdescription) {
		this.englishdescription = englishdescription;
	}
	public java.math.BigDecimal getPercentage() {
		return percentage;
	}
	public void setPercentage(java.math.BigDecimal percentage) {
		this.percentage = percentage;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public java.math.BigDecimal getChangerate() {
		return changerate;
	}
	public void setChangerate(java.math.BigDecimal changerate) {
		this.changerate = changerate;
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
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public BeanCompanyowner obtenerBean() {
		BeanCompanyowner bean = new BeanCompanyowner();
		return obtenerBean(bean);
	}

	public BeanCompanyowner obtenerBean(BeanCompanyowner bean) {
		if (bean == null)
			bean = new BeanCompanyowner();

		bean.getPk().setCompanyowner(companyowner);
		bean.setDescription(description);
		bean.setEnglishdescription(englishdescription);
		//bean.setPercentage(percentage != null ? new BigDecimal(percentage) : null);
		bean.setPercentage(percentage);
		bean.setCompany(company);
		bean.setOwner(owner);
		bean.setChangerate(changerate);
		bean.setLastuser(lastuser);
		bean.setLastdate(lastdate);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}
	
}
