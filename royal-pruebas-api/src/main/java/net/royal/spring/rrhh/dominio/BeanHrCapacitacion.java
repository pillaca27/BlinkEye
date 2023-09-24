package net.royal.spring.rrhh.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Hr_Capacitacion")
public class BeanHrCapacitacion implements Serializable {

	@EmbeddedId
	private BeanHrCapacitacionPk pk;

	@Column(name = "Curso")
	private Integer curso;

	@Column(name = "Descripcion")
	private String descripcion;

	@Column(name = "Estado")
	private String estado;

	@Column(name = "FechaDesde")
	private Date fechaDesde;

	@Column(name = "FechaHasta")
	private Date fechaHasta;

	@Column(name = "FechaSolicitud")
	private Date fechaSolicitud;

	@Column(name = "EmpleadoSolicitante")
	private Integer empleadoSolicitante;

	@Column(name = "UltimoUsuario")
	private String ultimoUsuario;

	@Column(name = "UltimaFechaModif")
	private Date ultimaFechaModif;
	
	@Column(name = "ARCHIVO")
	private byte[] archivo;

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public BeanHrCapacitacion() {
		this.pk = new BeanHrCapacitacionPk();
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

	public BeanHrCapacitacionPk getPk() {
		return pk;
	}

	public void setPk(BeanHrCapacitacionPk pk) {
		this.pk = pk;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Integer getEmpleadoSolicitante() {
		return empleadoSolicitante;
	}

	public void setEmpleadoSolicitante(Integer empleadoSolicitante) {
		this.empleadoSolicitante = empleadoSolicitante;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

}
