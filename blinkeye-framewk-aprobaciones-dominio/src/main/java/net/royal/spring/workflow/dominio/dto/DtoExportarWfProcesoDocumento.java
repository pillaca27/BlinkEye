package net.royal.spring.workflow.dominio.dto;

public class DtoExportarWfProcesoDocumento {

	private String procesoId;
	private String tipoDocumentoId;
	private String grupo;
	private String rutaPlantilla;

	public String getTipoDocumentoId() {
		return tipoDocumentoId;
	}

	public void setTipoDocumentoId(String tipoDocumentoId) {
		this.tipoDocumentoId = tipoDocumentoId;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getRutaPlantilla() {
		return rutaPlantilla;
	}

	public void setRutaPlantilla(String rutaPlantilla) {
		this.rutaPlantilla = rutaPlantilla;
	}

	public String getProcesoId() {
		return procesoId;
	}

	public void setProcesoId(String procesoId) {
		this.procesoId = procesoId;
	}

}
