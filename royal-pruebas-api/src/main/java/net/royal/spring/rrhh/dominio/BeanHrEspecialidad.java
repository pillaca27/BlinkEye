package net.royal.spring.rrhh.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Hr_Especialidad")
public class BeanHrEspecialidad implements Serializable{
	
	@EmbeddedId
	private BeanHrEspecialidadPk pk;
	
	@Column(name = "Estado")
	private String estado;
	
	@Column(name = "Descripcion")
	private String descripcion;
	
	@Column(name = "UltimoUsuario")
	private String ultimoUsuario;
	
	@Column(name = "UltimaFechaModif")
	private Date ultimaFechaModif;
	
	@Column(name = "UnidadReplicacion")
	private String unidadReplicacion;

	public BeanHrEspecialidad() {
		this.pk = new BeanHrEspecialidadPk();
	}

	public BeanHrEspecialidadPk getPk() {
		return pk;
	}

	public void setPk(BeanHrEspecialidadPk pk) {
		this.pk = pk;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUltimoUsuario() {
		return ultimoUsuario;
	}

	public void setUltimoUsuario(String ultimoUsuario) {
		this.ultimoUsuario = ultimoUsuario;
	}

	public Date getUltimaFechaModif() {
		return ultimaFechaModif;
	}

	public void setUltimaFechaModif(Date ultimaFechaModif) {
		this.ultimaFechaModif = ultimaFechaModif;
	}

	public String getUnidadReplicacion() {
		return unidadReplicacion;
	}

	public void setUnidadReplicacion(String unidadReplicacion) {
		this.unidadReplicacion = unidadReplicacion;
	}
	
}
