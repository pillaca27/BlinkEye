package net.royal.spring.hr.dominio;

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

import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.HR_ReqEtapa
*/
public class BeanHrReqetapaPk implements java.io.Serializable{


	@Size(min = 0, max = 8)
	@NotEmpty
	@Column(name = "COMPANYOWNER", length = 8, nullable = false)
	private String companyowner;

	@NotNull
	@Column(name = "ETAPA", nullable = false)
	private Integer etapa;

	public BeanHrReqetapaPk() {
	}

	public BeanHrReqetapaPk(String pcompanyowner,Integer petapa) {
this.companyowner = pcompanyowner;
		this.etapa = petapa;
	}

	/**
	 * 
	 * 
	 * @campo CompanyOwner
	*/
	public String getCompanyowner() {
		return companyowner;
	}

	/**
	 * 
	 * 
	 * @campo CompanyOwner
	*/
	public void setCompanyowner(String companyowner) {
		this.companyowner = companyowner;
	}
	/**
	 * 
	 * 
	 * @campo Etapa
	*/
	public Integer getEtapa() {
		return etapa;
	}

	/**
	 * 
	 * 
	 * @campo Etapa
	*/
	public void setEtapa(Integer etapa) {
		this.etapa = etapa;
	}


}
