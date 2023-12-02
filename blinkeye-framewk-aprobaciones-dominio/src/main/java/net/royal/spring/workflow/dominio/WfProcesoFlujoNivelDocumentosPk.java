package net.royal.spring.workflow.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class WfProcesoFlujoNivelDocumentosPk implements java.io.Serializable {

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
	@Column(name = "TIPO_DOCUMENTO_ID", nullable = false)
	private String tipodocumentoid;

	public WfProcesoFlujoNivelDocumentosPk() {
	}

	public WfProcesoFlujoNivelDocumentosPk(String procesoid, Integer versionid, Integer flujoid,
			Integer nivelid, String tipodocumentoid) {
		this.procesoid = procesoid;
		this.versionid = versionid;
		this.flujoid = flujoid;
		this.nivelid = nivelid;
		this.tipodocumentoid = tipodocumentoid;
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

	public String getTipodocumentoid() {
		return tipodocumentoid;
	}

	public void setTipodocumentoid(String tipodocumentoid) {
		this.tipodocumentoid = tipodocumentoid;
	}

}
