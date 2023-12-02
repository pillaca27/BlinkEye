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
 * @tabla dbo.MA_PersonaPersonaGrupo
*/
@Entity
@Table(name = "MA_PERSONAPERSONAGRUPO")
public class BeanMaPersonapersonagrupo extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanMaPersonapersonagrupoPk pk;

	@Size(min = 0, max = 6)
	@Column(name = "PLACAVEHICULO", length = 6, nullable = true)
	private String placavehiculo;

	@Column(name = "PERSONARELACIONADA", nullable = true)
	private Integer personarelacionada;


	public BeanMaPersonapersonagrupo() {
		pk = new BeanMaPersonapersonagrupoPk();
	}


	public BeanMaPersonapersonagrupo(BeanMaPersonapersonagrupoPk pk) {
		this.pk = pk;
	}

	public BeanMaPersonapersonagrupoPk getPk() {
		return pk;
	}

	public void setPk(BeanMaPersonapersonagrupoPk pk) {
		this.pk = pk;
	}
	/**
	 * 
	 * 
	 * @campo PlacaVehiculo
	*/
	public String getPlacavehiculo() {
		return placavehiculo;
	}

	/**
	 * 
	 * 
	 * @campo PlacaVehiculo
	*/
	public void setPlacavehiculo(String placavehiculo) {
		this.placavehiculo = placavehiculo;
	}
	/**
	 * 
	 * 
	 * @campo PersonaRelacionada
	*/
	public Integer getPersonarelacionada() {
		return personarelacionada;
	}

	/**
	 * 
	 * 
	 * @campo PersonaRelacionada
	*/
	public void setPersonarelacionada(Integer personarelacionada) {
		this.personarelacionada = personarelacionada;
	}

}
