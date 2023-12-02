package net.royal.spring.workflow.dominio.dto;

public class DtoExportarWfFlujoConfiguracion {
	
	private String procesoId;
	private Integer versionId;
	private Integer flujoId;
	private Integer configuracionId;
	private String variableId;
	private String comparacion;
	private String valores;
	private Integer relacion;

	public Integer getFlujoId() {
		return flujoId;
	}

	public void setFlujoId(Integer flujoId) {
		this.flujoId = flujoId;
	}

	public Integer getConfiguracionId() {
		return configuracionId;
	}

	public void setConfiguracionId(Integer configuracionId) {
		this.configuracionId = configuracionId;
	}

	public String getVariableId() {
		return variableId;
	}

	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}

	public String getComparacion() {
		return comparacion;
	}

	public void setComparacion(String comparacion) {
		this.comparacion = comparacion;
	}

	public String getValores() {
		return valores;
	}

	public void setValores(String valores) {
		this.valores = valores;
	}

	public Integer getRelacion() {
		return relacion;
	}

	public void setRelacion(Integer relacion) {
		this.relacion = relacion;
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public String getProcesoId() {
		return procesoId;
	}

	public void setProcesoId(String procesoId) {
		this.procesoId = procesoId;
	}

}
