package net.royal.spring.workflow.dominio.dto;

public class DtoExportarWfFlujoAprobador {

	private String procesoId;
	private Integer versionId;
	private Integer flujoId;
	private Integer nivelId;
	private Integer personaId;
	private String segmentoAprobar;

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public Integer getFlujoId() {
		return flujoId;
	}

	public void setFlujoId(Integer flujoId) {
		this.flujoId = flujoId;
	}

	public Integer getNivelId() {
		return nivelId;
	}

	public void setNivelId(Integer nivelId) {
		this.nivelId = nivelId;
	}

	public Integer getPersonaId() {
		return personaId;
	}

	public void setPersonaId(Integer personaId) {
		this.personaId = personaId;
	}

	public String getSegmentoAprobar() {
		return segmentoAprobar;
	}

	public void setSegmentoAprobar(String segmentoAprobar) {
		this.segmentoAprobar = segmentoAprobar;
	}

	public String getProcesoId() {
		return procesoId;
	}

	public void setProcesoId(String procesoId) {
		this.procesoId = procesoId;
	}
}
