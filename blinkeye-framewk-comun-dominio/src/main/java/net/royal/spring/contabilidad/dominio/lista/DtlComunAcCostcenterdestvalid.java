package net.royal.spring.contabilidad.dominio.lista;

import net.royal.spring.contabilidad.dominio.BeanAcCostcenterdestvalid;

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
 * @tabla dbo.AC_CostCenterDestValid
*/
public class DtlComunAcCostcenterdestvalid extends DominioTransaccion implements java.io.Serializable{


	private String costcenter;
	private String costcenterdestination;
	private String status;
	private String lastuser;
	private java.util.Date lastdate;

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
	 * @campo CostCenterDestination
	*/
	public String getCostcenterdestination() {
		return costcenterdestination;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterDestination
	*/
	public void setCostcenterdestination(String costcenterdestination) {
		this.costcenterdestination = costcenterdestination;
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
	 * @campo LastDate
	*/
	public java.util.Date getLastdate() {
		return lastdate;
	}

	/**
	 * 
	 * 
	 * @campo LastDate
	*/
	public void setLastdate(java.util.Date lastdate) {
		this.lastdate = lastdate;
	}

}
