package net.royal.spring.core.dominio.filtro;

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
public class FiltroComunMaPersonapersonagrupo extends DominioTransaccion implements java.io.Serializable{


	private Integer persona;
	private String personagrupo;

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

	private DominioPaginacion paginacion;

	public DominioPaginacion getPaginacion() {
		if (paginacion == null)
			paginacion = new DominioPaginacion();
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}


}
