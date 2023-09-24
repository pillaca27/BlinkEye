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
public class BeanReportingcompanyPk implements java.io.Serializable{



	@Size(min = 0, max = 6)
	@NotNull
	@NotEmpty
	@Column(name = "COMPANIACODIGO", length = 6, nullable = false)
	private String companiacodigo;

	@Size(min = 0, max = 8)
	@NotNull
	@NotEmpty
	@Column(name = "COMPANYOWNER", length = 8, nullable = false)
	private String companyowner;


	public BeanReportingcompanyPk() {
	}

	public BeanReportingcompanyPk(String pcompaniacodigo,String pcompanyowner) {
this.companiacodigo = pcompaniacodigo;
		this.companyowner = pcompanyowner;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaCodigo
	*/
	public String getCompaniacodigo() {
		return companiacodigo;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaCodigo
	*/
	public void setCompaniacodigo(String companiacodigo) {
		this.companiacodigo = companiacodigo;
	}
	/**
	 * 
	 * 
	 * @campo companyowner
	*/
	public String getCompanyowner() {
		return companyowner;
	}

	/**
	 * 
	 * 
	 * @campo companyowner
	*/
	public void setCompanyowner(String companyowner) {
		this.companyowner = companyowner;
	}

}
