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
 * @tabla dbo.WH_ContratoVencimiento
*/
public class BeanWhContratovencimientoPk implements java.io.Serializable{



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

	@NotNull
	@Column(name = "SECUENCIA", nullable = false)
	private Integer secuencia;

	@NotNull
	@Column(name = "LINEA", nullable = false)
	private Integer linea;


	public BeanWhContratovencimientoPk() {
	}

	public BeanWhContratovencimientoPk(String pcompaniasocio,String pnumerocontrato,Integer psecuencia,Integer plinea) {
this.companiasocio = pcompaniasocio;
		this.numerocontrato = pnumerocontrato;
		this.secuencia = psecuencia;
		this.linea = plinea;
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
	 * @campo Linea
	*/
	public Integer getLinea() {
		return linea;
	}

	/**
	 * 
	 * 
	 * @campo Linea
	*/
	public void setLinea(Integer linea) {
		this.linea = linea;
	}

}