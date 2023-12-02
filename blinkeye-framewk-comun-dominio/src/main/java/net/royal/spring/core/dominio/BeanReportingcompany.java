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
 * @tabla dbo.reportingcompany
*/
@Entity
@Table(name = "REPORTINGCOMPANY")
public class BeanReportingcompany extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanReportingcompanyPk pk;

	@Column(name = "PERCENTAGE", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal percentage;


	public BeanReportingcompany() {
		pk = new BeanReportingcompanyPk();
	}


	public BeanReportingcompany(BeanReportingcompanyPk pk) {
		this.pk = pk;
	}

	public BeanReportingcompanyPk getPk() {
		return pk;
	}

	public void setPk(BeanReportingcompanyPk pk) {
		this.pk = pk;
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

}
