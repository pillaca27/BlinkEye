package net.royal.spring.workflow.dominio.dto;

public class DtoExportarWfFlujoNivelDocumento {

	private String procesoId;
	private Integer versionId;
	private Integer flujoId;
	private Integer nivelId;
	private String tipoDocumentoId;
	private String flgRequerido;
	private String flgEditable;
	private String flgFirmaElectronica;
	private String flgFirmaImagen;

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

	public String getTipoDocumentoId() {
		return tipoDocumentoId;
	}

	public void setTipoDocumentoId(String tipoDocumentoId) {
		this.tipoDocumentoId = tipoDocumentoId;
	}

	public String getFlgRequerido() {
		return flgRequerido;
	}

	public void setFlgRequerido(String flgRequerido) {
		this.flgRequerido = flgRequerido;
	}

	public String getFlgEditable() {
		return flgEditable;
	}

	public void setFlgEditable(String flgEditable) {
		this.flgEditable = flgEditable;
	}

	public String getFlgFirmaElectronica() {
		return flgFirmaElectronica;
	}

	public void setFlgFirmaElectronica(String flgFirmaElectronica) {
		this.flgFirmaElectronica = flgFirmaElectronica;
	}

	public String getFlgFirmaImagen() {
		return flgFirmaImagen;
	}

	public void setFlgFirmaImagen(String flgFirmaImagen) {
		this.flgFirmaImagen = flgFirmaImagen;
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
