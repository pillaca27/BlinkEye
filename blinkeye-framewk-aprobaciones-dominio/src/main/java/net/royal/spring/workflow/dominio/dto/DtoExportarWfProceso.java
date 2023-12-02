package net.royal.spring.workflow.dominio.dto;

public class DtoExportarWfProceso {

	private String procesoId;
	private String nombre;
	private String aplicacionId;

	public String getProcesoId() {
		return procesoId;
	}

	public void setProcesoId(String procesoId) {
		this.procesoId = procesoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAplicacionId() {
		return aplicacionId;
	}

	public void setAplicacionId(String aplicacionId) {
		this.aplicacionId = aplicacionId;
	}

}
