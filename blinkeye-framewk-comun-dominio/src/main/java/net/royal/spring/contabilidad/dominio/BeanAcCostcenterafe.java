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
 * @tabla dbo.AC_CostCenterAfe
*/
@Entity
@Table(name = "AC_COSTCENTERAFE")
public class BeanAcCostcenterafe extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanAcCostcenterafePk pk;

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


	public BeanAcCostcenterafe() {
		pk = new BeanAcCostcenterafePk();
	}


	public BeanAcCostcenterafe(BeanAcCostcenterafePk pk) {
		this.pk = pk;
	}

	public BeanAcCostcenterafePk getPk() {
		return pk;
	}

	public void setPk(BeanAcCostcenterafePk pk) {
		this.pk = pk;
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
