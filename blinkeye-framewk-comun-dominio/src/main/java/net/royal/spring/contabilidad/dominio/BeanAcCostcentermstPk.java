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
 * @tabla dbo.AC_CostCenterMst
*/
public class BeanAcCostcentermstPk implements java.io.Serializable{



	@Size(min = 0, max = 10)
	@NotNull
	@NotEmpty
	@Column(name = "COSTCENTER", length = 10, nullable = false)
	private String costcenter;


	public BeanAcCostcentermstPk() {
	}

	public BeanAcCostcentermstPk(String pcostcenter) {
this.costcenter = pcostcenter;
	}

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

}
