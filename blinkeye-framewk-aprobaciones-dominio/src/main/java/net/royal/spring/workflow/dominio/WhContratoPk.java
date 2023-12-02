package net.royal.spring.workflow.dominio;

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

import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.WH_Contrato
 */
public class WhContratoPk implements java.io.Serializable {

	@Size(min = 0, max = 8)
	@NotNull
	@NotEmpty
	@Column(name = "COMPANIASOCIO", length = 8, nullable = false)
	private String companiasocio;

	@Size(min = 0, max = 20)
	@NotNull
	@NotEmpty
	@Column(name = "NUMEROCONTRATO", length = 20, nullable = false)
	private String numerocontrato;

	public WhContratoPk() {
	}

	public WhContratoPk(String pcompaniasocio, String pnumerocontrato) {
		this.companiasocio = pcompaniasocio;
		this.numerocontrato = pnumerocontrato;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	 */
	public String getCompaniasocio() {
		return companiasocio;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	 */
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}

	/**
	 * 
	 * 
	 * @campo NumeroContrato
	 */
	public String getNumerocontrato() {
		return numerocontrato;
	}

	/**
	 * 
	 * 
	 * @campo NumeroContrato
	 */
	public void setNumerocontrato(String numerocontrato) {
		this.numerocontrato = numerocontrato;
	}

}
