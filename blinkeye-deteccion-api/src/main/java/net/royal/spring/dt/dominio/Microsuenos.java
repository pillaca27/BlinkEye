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

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.Microsuenos
*/
@Entity
@Table(name = "MICROSUENOS")
public class Microsuenos extends DominioTransaccion implements java.io.Serializable{
	@EmbeddedId
	private MicrosuenosPk pk;


	@Column(name = "ID_ALUMNO", nullable = true)
	private Integer idAlumno;

	@Column(name = "ID_SESION", nullable = true)
	private Integer idSesion;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHA_HORA", nullable = true)
	private java.util.Date fechaHora;

	@Column(name = "DURACIÓN", nullable = true)
	private Integer duracion;

	public Microsuenos() {
		pk = new MicrosuenosPk();
	}


	public Microsuenos(MicrosuenosPk pk) {
		this.pk = pk;
	}

	public MicrosuenosPk getPk() {
		return pk;
	}

	public void setPk(MicrosuenosPk pk) {
		this.pk = pk;
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
	/**
	 * 
	 * 
	 * @campo ID_SESION
	*/
	public Integer getIdSesion() {
		return idSesion;
	}

	/**
	 * 
	 * 
	 * @campo ID_SESION
	*/
	public void setIdSesion(Integer idSesion) {
		this.idSesion = idSesion;
	}
	/**
	 * 
	 * 
	 * @campo Fecha_Hora
	*/
	public java.util.Date getFechaHora() {
		return fechaHora;
	}

	/**
	 * 
	 * 
	 * @campo Fecha_Hora
	*/
	public void setFechaHora(java.util.Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	/**
	 * 
	 * 
	 * @campo Duración
	*/
	public Integer getDuracion() {
		return duracion;
	}

	/**
	 * 
	 * 
	 * @campo Duración
	*/
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}


}
