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
 * @tabla dbo.AC_CostCenterGroup
*/
@Entity
@Table(name = "AC_COSTCENTERGROUP")
public class BeanAcCostcentergroup extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanAcCostcentergroupPk pk;

	@Size(min = 0, max = 50)
	@Column(name = "LOCALNAME", length = 50, nullable = true)
	private String localname;

	@Size(min = 0, max = 50)
	@Column(name = "ENGLISHNAME", length = 50, nullable = true)
	private String englishname;

	@Size(min = 0, max = 15)
	@Column(name = "COSTCENTERMAJORGROUP", length = 15, nullable = true)
	private String costcentermajorgroup;

	@Size(min = 0, max = 1)
	@Column(name = "STATUS", length = 1, nullable = true)
	private String status;

	@Size(min = 0, max = 20)
	@Column(name = "LASTUSER", length = 20, nullable = true)
	private String lastuser;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "LASTDATE", nullable = true)
	private java.util.Date lastdate;


	public BeanAcCostcentergroup() {
		pk = new BeanAcCostcentergroupPk();
	}


	public BeanAcCostcentergroup(BeanAcCostcentergroupPk pk) {
		this.pk = pk;
	}

	public BeanAcCostcentergroupPk getPk() {
		return pk;
	}

	public void setPk(BeanAcCostcentergroupPk pk) {
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
	 * @campo CostCenterMajorGroup
	*/
	public String getCostcentermajorgroup() {
		return costcentermajorgroup;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterMajorGroup
	*/
	public void setCostcentermajorgroup(String costcentermajorgroup) {
		this.costcentermajorgroup = costcentermajorgroup;
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

}
