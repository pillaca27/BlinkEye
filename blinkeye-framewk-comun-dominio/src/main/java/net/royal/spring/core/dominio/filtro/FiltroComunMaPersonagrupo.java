package net.royal.spring.core.dominio.filtro;

import net.royal.spring.core.dominio.BeanMaPersonagrupo;

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
 * @tabla dbo.MA_PersonaGrupo
*/
public class FiltroComunMaPersonagrupo extends DominioTransaccion implements java.io.Serializable{


	private String personagrupo;
	private String tipoexportar;
	private String descripcionlocal;
	private String estado;
	

	public String getDescripcionlocal() {
		return descripcionlocal;
	}

	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoexportar() {
		return tipoexportar;
	}

	public void setTipoexportar(String tipoexportar) {
		this.tipoexportar = tipoexportar;
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
