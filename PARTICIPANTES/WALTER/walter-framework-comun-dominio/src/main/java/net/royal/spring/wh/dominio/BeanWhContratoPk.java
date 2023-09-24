package net.royal.spring.wh.dominio;

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
 * @tabla dbo.WH_Contrato
*/
public class BeanWhContratoPk implements java.io.Serializable{



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
	
	public BeanWhContratoPk() {
	}

	public BeanWhContratoPk(String pcompaniasocio,String pnumerocontrato ) {
			this.companiasocio = pcompaniasocio;
			this.numerocontrato=pnumerocontrato;
	}
	


	public String getCompaniasocio() {
		return companiasocio;
	}

	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}

	public String getNumerocontrato() {
		return numerocontrato;
	}

	public void setNumerocontrato(String numerocontrato) {
		this.numerocontrato = numerocontrato;
	}

	

}
