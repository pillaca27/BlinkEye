package net.royal.spring.dt.dominio;

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

/**
 * 
 * 
 * @tabla dbo.Docentes
*/
public class DocentesPk implements java.io.Serializable{


	@NotNull
	@Column(name = "ID_DOCENTE", nullable = false)
	private Integer idDocente;

	public DocentesPk() {
	}

	public DocentesPk(Integer pidDocente) {
this.idDocente = pidDocente;
	}

	/**
	 * 
	 * 
	 * @campo ID_Docente
	*/
	public Integer getIdDocente() {
		return idDocente;
	}

	/**
	 * 
	 * 
	 * @campo ID_Docente
	*/
	public void setIdDocente(Integer idDocente) {
		this.idDocente = idDocente;
	}


}
