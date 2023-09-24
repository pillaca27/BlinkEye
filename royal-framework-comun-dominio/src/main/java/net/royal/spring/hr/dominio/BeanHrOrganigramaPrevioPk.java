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
 * @tabla dbo.HR_Organigrama_Previo
*/
public class BeanHrOrganigramaPrevioPk implements java.io.Serializable{


	@Size(min = 0, max = 8)
	@NotEmpty
	@Column(name = "COMPANYOWNER", length = 8, nullable = false)
	private String companyowner;

	@NotNull
	@Column(name = "ANO", nullable = false)
	private Integer ano;

	@Size(min = 0, max = 8)
	@NotEmpty
	@Column(name = "UNIDADNEGOCIO", length = 8, nullable = false)
	private String unidadnegocio;

	@NotNull
	@Column(name = "SECUENCIA", nullable = false)
	private Integer secuencia;

	@Size(min = 0, max = 200)
	@NotEmpty
	@Column(name = "ORDEN", length = 200, nullable = false)
	private String orden;

	public BeanHrOrganigramaPrevioPk() {
	}

	public BeanHrOrganigramaPrevioPk(String pcompanyowner,Integer pano,String punidadnegocio,Integer psecuencia,String porden) {
this.companyowner = pcompanyowner;
		this.ano = pano;
		this.unidadnegocio = punidadnegocio;
		this.secuencia = psecuencia;
		this.orden = porden;
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
	 * @campo Ano
	*/
	public Integer getAno() {
		return ano;
	}

	/**
	 * 
	 * 
	 * @campo Ano
	*/
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	/**
	 * 
	 * 
	 * @campo UnidadNegocio
	*/
	public String getUnidadnegocio() {
		return unidadnegocio;
	}

	/**
	 * 
	 * 
	 * @campo UnidadNegocio
	*/
	public void setUnidadnegocio(String unidadnegocio) {
		this.unidadnegocio = unidadnegocio;
	}
	/**
	 * 
	 * 
	 * @campo Secuencia
	*/
	public Integer getSecuencia() {
		return secuencia;
	}

	/**
	 * 
	 * 
	 * @campo Secuencia
	*/
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}
	/**
	 * 
	 * 
	 * @campo Orden
	*/
	public String getOrden() {
		return orden;
	}

	/**
	 * 
	 * 
	 * @campo Orden
	*/
	public void setOrden(String orden) {
		this.orden = orden;
	}


}
