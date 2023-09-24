package net.royal.spring.core.dominio;

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
@Entity
@Table(name = "COMPANYOWNER")
public class BeanCompanyowner extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanCompanyownerPk pk;

	@Size(min = 0, max = 40)
	@NotNull
	@NotEmpty
	@Column(name = "DESCRIPTION", length = 40, nullable = false)
	private String description;

	@Size(min = 0, max = 40)
	@Column(name = "ENGLISHDESCRIPTION", length = 40, nullable = true)
	private String englishdescription;

	@Column(name = "PERCENTAGE", precision = 24,scale =0, nullable = true)
	private java.math.BigDecimal percentage;

	@Size(min = 0, max = 6)
	@Column(name = "COMPANY", length = 6, nullable = true)
	private String company;

	@Size(min = 0, max = 2)
	@Column(name = "OWNER", length = 2, nullable = true)
	private String owner;

	@Column(name = "CHANGERATE", precision = 24,scale =0, nullable = true)
	private java.math.BigDecimal changerate;

	@Size(min = 0, max = 10)
	@Column(name = "LASTUSER", length = 10, nullable = true)
	private String lastuser;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "LASTDATE", nullable = true)
	private java.util.Date lastdate;


	public BeanCompanyowner() {
		pk = new BeanCompanyownerPk();
	}


	public BeanCompanyowner(BeanCompanyownerPk pk) {
		this.pk = pk;
	}

	public BeanCompanyownerPk getPk() {
		return pk;
	}

	public void setPk(BeanCompanyownerPk pk) {
		this.pk = pk;
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
