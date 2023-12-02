package net.royal.spring.workflow.dominio.dto;

public class DtoSeguimientoVistaAvanzada {

	private Integer seguimientoId;
	private Integer nivelId;
	private String usuario;
	private String observacion;
	private String estadoId;

	public Integer getSeguimientoId() {
		return seguimientoId;
	}

	public void setSeguimientoId(Integer seguimientoId) {
		this.seguimientoId = seguimientoId;
	}

	public Integer getNivelId() {
		return nivelId;
	}

	public void setNivelId(Integer nivelId) {
		this.nivelId = nivelId;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

}
