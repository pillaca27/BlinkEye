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
 * @tabla dbo.Microsuenos
*/
public class MicrosuenosPk implements java.io.Serializable{


	@NotNull
	@Column(name = "ID_MICROSUENO", nullable = false)
	private Integer idMicrosueno;

	public MicrosuenosPk() {
	}

	public MicrosuenosPk(Integer pidMicrosueno) {
this.idMicrosueno = pidMicrosueno;
	}

	/**
	 * 
	 * 
	 * @campo ID_Microsueno
	*/
	public Integer getIdMicrosueno() {
		return idMicrosueno;
	}

	/**
	 * 
	 * 
	 * @campo ID_Microsueno
	*/
	public void setIdMicrosueno(Integer idMicrosueno) {
		this.idMicrosueno = idMicrosueno;
	}


}
