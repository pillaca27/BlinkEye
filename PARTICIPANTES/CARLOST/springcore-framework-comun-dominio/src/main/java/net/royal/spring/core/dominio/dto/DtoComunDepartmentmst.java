package net.royal.spring.core.dominio.dto;

import net.royal.spring.core.dominio.BeanDepartmentmst;

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
public class DtoComunDepartmentmst extends DominioTransaccion implements java.io.Serializable{


	private String department;
	private String description;
	private String accountavailableflag;
	private String warehouseavailableflag;
	private String application;
	private String status;
	private String lastuser;
	private java.util.Date lastdate;

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
	public BeanDepartmentmst obtenerBean() {
		BeanDepartmentmst bean = new BeanDepartmentmst();
		return obtenerBean(bean);
	}

	public BeanDepartmentmst obtenerBean(BeanDepartmentmst bean) {
		if (bean == null)
			bean = new BeanDepartmentmst();

		bean.getPk().setDepartment(department);
		bean.setDescription(description);
		bean.setAccountavailableflag(accountavailableflag);
		bean.setWarehouseavailableflag(warehouseavailableflag);
		bean.setApplication(application);
		bean.setStatus(status);
		bean.setLastuser(lastuser);
		bean.setLastdate(lastdate);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
