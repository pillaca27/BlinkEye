package net.royal.spring.core.dominio.dto;

import net.royal.spring.core.dominio.BeanMaPersonapersonagrupo;

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
public class DtoComunMaPersonapersonagrupo extends DominioTransaccion implements java.io.Serializable{


	private Integer persona;
	private String personagrupo;
	private String placavehiculo;
	private Integer personarelacionada;
	private String acciones;
	
	

	public String getAcciones() {
		return acciones;
	}

	public void setAcciones(String acciones) {
		this.acciones = acciones;
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
	 * @campo PersonaGrupo
	*/
	public String getPersonagrupo() {
		return personagrupo;
	}

	/**
	 * 
	 * 
	 * @campo PersonaGrupo
	*/
	public void setPersonagrupo(String personagrupo) {
		this.personagrupo = personagrupo;
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
	public BeanMaPersonapersonagrupo obtenerBean() {
		BeanMaPersonapersonagrupo bean = new BeanMaPersonapersonagrupo();
		return obtenerBean(bean);
	}

	public BeanMaPersonapersonagrupo obtenerBean(BeanMaPersonapersonagrupo bean) {
		if (bean == null)
			bean = new BeanMaPersonapersonagrupo();

		bean.getPk().setPersona(persona);
		bean.getPk().setPersonagrupo(personagrupo);
		bean.setPlacavehiculo(placavehiculo);
		bean.setPersonarelacionada(personarelacionada);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
