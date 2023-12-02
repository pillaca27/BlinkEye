package net.royal.spring.workflow.dominio.dto;

public class DtoWfPlanificacionConfiguracion {
	
	private String puedeVer;
	private String puedeEditar;
	private String puedeActualizar;

	public String getPuedeVer() {
		return puedeVer;
	}

	public void setPuedeVer(String puedeVer) {
		this.puedeVer = puedeVer;
	}

	public String getPuedeEditar() {
		return puedeEditar;
	}

	public void setPuedeEditar(String puedeEditar) {
		this.puedeEditar = puedeEditar;
	}

	public String getPuedeActualizar() {
		return puedeActualizar;
	}

	public void setPuedeActualizar(String puedeActualizar) {
		this.puedeActualizar = puedeActualizar;
	}

}
