package net.royal.spring.core.dominio.lista;

import net.royal.spring.core.dominio.BeanCompanyowner;

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
 * @tabla dbo.companyowner
*/
public class DtlComunCompanyowner extends DominioTransaccion implements java.io.Serializable{


	private String companyowner;
	private String description;
	private String englishdescription;
	private java.math.BigDecimal percentage;
	private String company;
	private String owner;
	private java.math.BigDecimal changerate;
	private String lastuser;
	private java.util.Date lastdate;

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
	 * @campo englishdescription
	*/
	public String getEnglishdescription() {
		return englishdescription;
	}

	/**
	 * 
	 * 
	 * @campo englishdescription
	*/
	public void setEnglishdescription(String englishdescription) {
		this.englishdescription = englishdescription;
	}
	/**
	 * 
	 * 
	 * @campo percentage
	*/
	public java.math.BigDecimal getPercentage() {
		return percentage;
	}

	/**
	 * 
	 * 
	 * @campo percentage
	*/
	public void setPercentage(java.math.BigDecimal percentage) {
		this.percentage = percentage;
	}
	/**
	 * 
	 * 
	 * @campo company
	*/
	public String getCompany() {
		return company;
	}

	/**
	 * 
	 * 
	 * @campo company
	*/
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * 
	 * 
	 * @campo owner
	*/
	public String getOwner() {
		return owner;
	}

	/**
	 * 
	 * 
	 * @campo owner
	*/
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * 
	 * 
	 * @campo ChangeRate
	*/
	public java.math.BigDecimal getChangerate() {
		return changerate;
	}

	/**
	 * 
	 * 
	 * @campo ChangeRate
	*/
	public void setChangerate(java.math.BigDecimal changerate) {
		this.changerate = changerate;
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

}
