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
 * @tabla dbo.MA_PersonaCuentaBancaria
*/
public class BeanMaPersonacuentabancariaPk implements java.io.Serializable{



	@NotNull
	@Column(name = "PERSONA", nullable = false)
	private Integer persona;

	@NotNull
	@Column(name = "SECUENCIA", nullable = false)
	private Integer secuencia;


	public BeanMaPersonacuentabancariaPk() {
	}

	public BeanMaPersonacuentabancariaPk(Integer ppersona,Integer psecuencia) {
this.persona = ppersona;
		this.secuencia = psecuencia;
	}

	/**
	 * 
	 * 
	 * @campo Persona
	*/
	public Integer getPersona() {
		return persona;
	}

	/**
	 * 
	 * 
	 * @campo Persona
	*/
	public void setPersona(Integer persona) {
		this.persona = persona;
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

}
