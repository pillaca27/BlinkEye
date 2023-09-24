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
 * @tabla dbo.departmentmst
*/
@Entity
@Table(name = "DEPARTMENTMST")
public class BeanDepartmentmst extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanDepartmentmstPk pk;

	@Size(min = 0, max = 80)
	@NotNull
	@NotEmpty
	@Column(name = "DESCRIPTION", length = 80, nullable = false)
	private String description;

	@Size(min = 0, max = 1)
	@Column(name = "ACCOUNTAVAILABLEFLAG", length = 1, nullable = true)
	private String accountavailableflag;

	@Size(min = 0, max = 1)
	@Column(name = "WAREHOUSEAVAILABLEFLAG", length = 1, nullable = true)
	private String warehouseavailableflag;

	@Size(min = 0, max = 2)
	@Column(name = "APPLICATION", length = 2, nullable = true)
	private String application;

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


	public BeanDepartmentmst() {
		pk = new BeanDepartmentmstPk();
	}


	public BeanDepartmentmst(BeanDepartmentmstPk pk) {
		this.pk = pk;
	}

	public BeanDepartmentmstPk getPk() {
		return pk;
	}

	public void setPk(BeanDepartmentmstPk pk) {
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
	 * @campo AccountAvailableFlag
	*/
	public String getAccountavailableflag() {
		return accountavailableflag;
	}

	/**
	 * 
	 * 
	 * @campo AccountAvailableFlag
	*/
	public void setAccountavailableflag(String accountavailableflag) {
		this.accountavailableflag = accountavailableflag;
	}
	/**
	 * 
	 * 
	 * @campo WarehouseAvailableFlag
	*/
	public String getWarehouseavailableflag() {
		return warehouseavailableflag;
	}

	/**
	 * 
	 * 
	 * @campo WarehouseAvailableFlag
	*/
	public void setWarehouseavailableflag(String warehouseavailableflag) {
		this.warehouseavailableflag = warehouseavailableflag;
	}
	/**
	 * 
	 * 
	 * @campo application
	*/
	public String getApplication() {
		return application;
	}

	/**
	 * 
	 * 
	 * @campo application
	*/
	public void setApplication(String application) {
		this.application = application;
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

}
