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
 * @tabla dbo.Clases
*/
public class ClasesPk implements java.io.Serializable{


	@NotNull
	@Column(name = "ID_CLASE", nullable = false)
	private Integer idClase;

	public ClasesPk() {
	}

	public ClasesPk(Integer pidClase) {
this.idClase = pidClase;
	}

	/**
	 * 
	 * 
	 * @campo ID_Clase
	*/
	public Integer getIdClase() {
		return idClase;
	}

	/**
	 * 
	 * 
	 * @campo ID_Clase
	*/
	public void setIdClase(Integer idClase) {
		this.idClase = idClase;
	}


}
