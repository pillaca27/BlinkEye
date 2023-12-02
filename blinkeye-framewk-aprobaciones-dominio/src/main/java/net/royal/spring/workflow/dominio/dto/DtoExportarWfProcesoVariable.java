package net.royal.spring.workflow.dominio.dto;

public class DtoExportarWfProcesoVariable {

	private String procesoId;
	private String variableId;

	public String getVariableId() {
		return variableId;
	}

	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}

	public String getProcesoId() {
		return procesoId;
	}

	public void setProcesoId(String procesoId) {
		this.procesoId = procesoId;
	}

}
