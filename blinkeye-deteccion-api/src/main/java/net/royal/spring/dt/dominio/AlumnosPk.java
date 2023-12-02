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
 * @tabla dbo.Alumnos
*/
public class AlumnosPk implements java.io.Serializable{


	@NotNull
	@Column(name = "ID_ALUMNO", nullable = false)
	private Integer idAlumno;

	public AlumnosPk() {
	}

	public AlumnosPk(Integer pidAlumno) {
this.idAlumno = pidAlumno;
	}

	/**
	 * 
	 * 
	 * @campo ID_Alumno
	*/
	public Integer getIdAlumno() {
		return idAlumno;
	}

	/**
	 * 
	 * 
	 * @campo ID_Alumno
	*/
	public void setIdAlumno(Integer idAlumno) {
		this.idAlumno = idAlumno;
	}


}
