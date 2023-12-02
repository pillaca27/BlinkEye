package net.royal.spring.workflow.dominio.dto;

public class DtoWfSaltoNivel {

	private Integer nivel;
	private String rol;
	private String accion;
	private String estado;
	private String nivelActual;

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNivelActual() {
		return nivelActual;
	}

	public void setNivelActual(String nivelActual) {
		this.nivelActual = nivelActual;
	}

}
