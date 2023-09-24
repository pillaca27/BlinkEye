package net.royal.spring.rrhh.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

@Entity
@Table(name = "Hr_Especialidad_2")
public class BeanHrEspecialidad2 extends DominioTransaccion implements Serializable{
	
	@EmbeddedId
	private BeanHrEspecialidad2Pk pk;

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

	@Column(name = "EmpleadoSolicitante")
	private Integer empleadoSolicitante;
	
	public BeanHrEspecialidad2() {
		pk = new BeanHrEspecialidad2Pk();
	}

	public BeanHrEspecialidad2Pk getPk() {
		return pk;
	}

	public void setPk(BeanHrEspecialidad2Pk pk) {
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

	public Integer getEmpleadoSolicitante() {
		return empleadoSolicitante;
	}

	public void setEmpleadoSolicitante(Integer empleadoSolicitante) {
		this.empleadoSolicitante = empleadoSolicitante;
	}
	

}
