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
 * @tabla dbo.Clases
*/
@Entity
@Table(name = "CLASES")
public class Clases extends DominioTransaccion implements java.io.Serializable{
	@EmbeddedId
	private ClasesPk pk;


	@Size(min = 0, max = 50)
	@Column(name = "NOMBRE", length = 50, nullable = true)
	private String nombre;

//	@JsonSerialize(using = USerializers.DateSerializer.class)
//	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
//	@Column(name = "FECHA_HORA_INICIO", nullable = true)
//	private java.util.Date fechaHoraInicio;
//
//	@JsonSerialize(using = USerializers.DateSerializer.class)
//	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
//	@Column(name = "FECHA_HORA_FIN", nullable = true)
//	private java.util.Date fechaHoraFin;

	@Column(name = "ID_DOCENTE", nullable = true)
	private Integer idDocente;

	public Clases() {
		pk = new ClasesPk();
	}


	public Clases(ClasesPk pk) {
		this.pk = pk;
	}

	public ClasesPk getPk() {
		return pk;
	}

	public void setPk(ClasesPk pk) {
		this.pk = pk;
	}

	/**
	 * 
	 * 
	 * @campo Nombre
	*/
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * 
	 * @campo Nombre
	*/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
//	/**
//	 * 
//	 * 
//	 * @campo Fecha_Hora_Inicio
//	*/
//	public java.util.Date getFechaHoraInicio() {
//		return fechaHoraInicio;
//	}
//
//	/**
//	 * 
//	 * 
//	 * @campo Fecha_Hora_Inicio
//	*/
//	public void setFechaHoraInicio(java.util.Date fechaHoraInicio) {
//		this.fechaHoraInicio = fechaHoraInicio;
//	}
//	/**
//	 * 
//	 * 
//	 * @campo Fecha_Hora_Fin
//	*/
//	public java.util.Date getFechaHoraFin() {
//		return fechaHoraFin;
//	}
//
//	/**
//	 * 
//	 * 
//	 * @campo Fecha_Hora_Fin
//	*/
//	public void setFechaHoraFin(java.util.Date fechaHoraFin) {
//		this.fechaHoraFin = fechaHoraFin;
//	}
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
