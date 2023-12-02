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

/**
 * 
 * 
 * @tabla dbo.Docentes
*/
@Entity
@Table(name = "DOCENTES")
public class Docentes extends DominioTransaccion implements java.io.Serializable{
	@EmbeddedId
	private DocentesPk pk;


	@Size(min = 0, max = 50)
	@Column(name = "NOMBRE", length = 50, nullable = true)
	private String nombre;

	@Size(min = 0, max = 50)
	@Column(name = "APELLIDO", length = 50, nullable = true)
	private String apellido;

	@Size(min = 0, max = 100)
	@Column(name = "EMAIL", length = 100, nullable = true)
	private String email;

	public Docentes() {
		pk = new DocentesPk();
	}


	public Docentes(DocentesPk pk) {
		this.pk = pk;
	}

	public DocentesPk getPk() {
		return pk;
	}

	public void setPk(DocentesPk pk) {
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
	/**
	 * 
	 * 
	 * @campo Apellido
	*/
	public String getApellido() {
		return apellido;
	}

	/**
	 * 
	 * 
	 * @campo Apellido
	*/
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * 
	 * 
	 * @campo Email
	*/
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * 
	 * @campo Email
	*/
	public void setEmail(String email) {
		this.email = email;
	}


}
