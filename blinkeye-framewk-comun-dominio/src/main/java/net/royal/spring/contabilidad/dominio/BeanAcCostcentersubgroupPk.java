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
 * @tabla dbo.AC_CostCenterSubGroup
*/
public class BeanAcCostcentersubgroupPk implements java.io.Serializable{



	@Size(min = 0, max = 10)
	@NotNull
	@NotEmpty
	@Column(name = "COSTCENTERGROUP", length = 10, nullable = false)
	private String costcentergroup;

	@Size(min = 0, max = 10)
	@NotNull
	@NotEmpty
	@Column(name = "COSTCENTERSUBGROUP", length = 10, nullable = false)
	private String costcentersubgroup;


	public BeanAcCostcentersubgroupPk() {
	}

	public BeanAcCostcentersubgroupPk(String pcostcentergroup,String pcostcentersubgroup) {
this.costcentergroup = pcostcentergroup;
		this.costcentersubgroup = pcostcentersubgroup;
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

}
