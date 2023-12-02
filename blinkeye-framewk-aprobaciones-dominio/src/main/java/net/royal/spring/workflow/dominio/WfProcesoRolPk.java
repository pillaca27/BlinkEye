package net.royal.spring.workflow.dominio;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class WfProcesoRolPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "PROCESO_ID", nullable = false)
	private String procesoid;

	@NotNull
	@Column(name = "VERSION_ID", nullable = false)
	private Integer versionid;

	@NotNull
	@Column(name = "TIPO_APROBADOR_ID", nullable = false)
	private String tipoAprobadorId;

	public WfProcesoRolPk() {
	}

	public WfProcesoRolPk(String procesoid, Integer versionid, String tipoAprobadorId) {
		this.procesoid = procesoid;
		this.versionid = versionid;
		this.tipoAprobadorId = tipoAprobadorId;
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

	public String getTipoAprobadorId() {
		return tipoAprobadorId;
	}

	public void setTipoAprobadorId(String tipoAprobadorId) {
		this.tipoAprobadorId = tipoAprobadorId;
	}

	

}
