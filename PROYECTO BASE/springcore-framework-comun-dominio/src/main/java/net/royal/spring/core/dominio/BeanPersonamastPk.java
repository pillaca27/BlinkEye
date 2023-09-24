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
 * @tabla dbo.PersonaMast
*/
public class BeanPersonamastPk implements java.io.Serializable{



	@NotNull
	@Column(name = "PERSONA", nullable = false)
	private Integer persona;


	public BeanPersonamastPk() {
	}

	public BeanPersonamastPk(Integer ppersona) {
this.persona = ppersona;
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

}
