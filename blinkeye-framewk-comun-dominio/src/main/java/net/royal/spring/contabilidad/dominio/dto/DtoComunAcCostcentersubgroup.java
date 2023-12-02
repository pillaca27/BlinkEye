package net.royal.spring.contabilidad.dominio.dto;

import net.royal.spring.contabilidad.dominio.BeanAcCostcentersubgroup;

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
 * @tabla dbo.AC_CostCenterSubGroup
*/
public class DtoComunAcCostcentersubgroup extends DominioTransaccion implements java.io.Serializable{


	private String costcentergroup;
	private String costcentersubgroup;
	private String localname;
	private String englishname;
	private String status;
	private String lastuser;
	private java.util.Date lastdate;
	private String accion;
	
	

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterGroup
	*/
	public String getCostcentergroup() {
		return costcentergroup;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterGroup
	*/
	public void setCostcentergroup(String costcentergroup) {
		this.costcentergroup = costcentergroup;
	}
	/**
	 * 
	 * 
	 * @campo CostCenterSubGroup
	*/
	public String getCostcentersubgroup() {
		return costcentersubgroup;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterSubGroup
	*/
	public void setCostcentersubgroup(String costcentersubgroup) {
		this.costcentersubgroup = costcentersubgroup;
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
	public BeanAcCostcentersubgroup obtenerBean() {
		BeanAcCostcentersubgroup bean = new BeanAcCostcentersubgroup();
		return obtenerBean(bean);
	}

	public BeanAcCostcentersubgroup obtenerBean(BeanAcCostcentersubgroup bean) {
		if (bean == null)
			bean = new BeanAcCostcentersubgroup();

		bean.getPk().setCostcentergroup(costcentergroup);
		bean.getPk().setCostcentersubgroup(costcentersubgroup);
		bean.setLocalname(localname);
		bean.setEnglishname(englishname);
		bean.setStatus(status);
		bean.setLastuser(lastuser);
		bean.setLastdate(lastdate);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
