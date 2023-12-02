package net.royal.spring.workflow.dominio;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class WfProcesoFlujoNivelAprobadoresPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "PROCESO_ID", nullable = false)
	private String procesoid;

	@NotNull
	@Column(name = "VERSION_ID", nullable = false)
	private Integer versionid;

	@NotNull
	@Column(name = "FLUJO_ID", nullable = false)
	private Integer flujoid;

	@NotNull
	@Column(name = "NIVEL_ID", nullable = false)
	private Integer nivelid;

	@NotNull
	@Column(name = "PERSONA_ID", nullable = false)
	private Integer personaid;

	public WfProcesoFlujoNivelAprobadoresPk() {
	}

	public WfProcesoFlujoNivelAprobadoresPk(String procesoid, Integer versionid, Integer flujoid, Integer nivelid,
			Integer personaid) {
		this.procesoid = procesoid;
		this.versionid = versionid;
		this.flujoid = flujoid;
		this.nivelid = nivelid;
		this.personaid = personaid;
	}

	public String getProcesoid() {
		return procesoid;
	}

	public void setProcesoid(String procesoid) {
		this.procesoid = procesoid;
	}

	public Integer getVersionid() {
		return versionid;
	}

	public void setVersionid(Integer versionid) {
		this.versionid = versionid;
	}

	public Integer getFlujoid() {
		return flujoid;
	}

	public void setFlujoid(Integer flujoid) {
		this.flujoid = flujoid;
	}

	public Integer getNivelid() {
		return nivelid;
	}

	public void setNivelid(Integer nivelid) {
		this.nivelid = nivelid;
	}

	public Integer getPersonaid() {
		return personaid;
	}

	public void setPersonaid(Integer personaid) {
		this.personaid = personaid;
	}

}
