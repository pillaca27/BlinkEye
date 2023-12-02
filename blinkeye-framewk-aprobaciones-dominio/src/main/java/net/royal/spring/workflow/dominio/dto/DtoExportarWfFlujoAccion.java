package net.royal.spring.workflow.dominio.dto;

public class DtoExportarWfFlujoAccion {

	private String procesoId;
	private Integer versionId;
	private Integer flujoId;
	private Integer nivelId;
	private Integer accionId;
	private String nombre;
	private String accionWf;
	private String estadoSubAccion;
	private Integer nivelDestinoId;

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

	public Integer getAccionId() {
		return accionId;
	}

	public void setAccionId(Integer accionId) {
		this.accionId = accionId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAccionWf() {
		return accionWf;
	}

	public void setAccionWf(String accionWf) {
		this.accionWf = accionWf;
	}

	public String getEstadoSubAccion() {
		return estadoSubAccion;
	}

	public void setEstadoSubAccion(String estadoSubAccion) {
		this.estadoSubAccion = estadoSubAccion;
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

	public Integer getNivelDestinoId() {
		return nivelDestinoId;
	}

	public void setNivelDestinoId(Integer nivelDestinoId) {
		this.nivelDestinoId = nivelDestinoId;
	}

}
