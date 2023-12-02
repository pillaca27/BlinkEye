package net.royal.spring.workflow.dominio.dto;

public class DtoExportarWfProcesoRol {

	private String procesoId;
	private Integer versionId;
	private String tipoAprobadorId;
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getTipoAprobadorId() {
		return tipoAprobadorId;
	}

	public void setTipoAprobadorId(String tipoAprobadorId) {
		this.tipoAprobadorId = tipoAprobadorId;
	}

}
