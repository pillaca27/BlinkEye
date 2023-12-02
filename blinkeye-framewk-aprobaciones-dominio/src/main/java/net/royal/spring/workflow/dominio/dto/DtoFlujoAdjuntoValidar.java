package net.royal.spring.workflow.dominio.dto;

public class DtoFlujoAdjuntoValidar {
	
	private String tipodocumento;
	private String tipodocumentodescripcion;
	private String requerido;
	private String firmaelectronica;
	private String firmaimagen;
	private String grupo;
	private String plantilla;

	public String getRequerido() {
		return requerido;
	}

	public void setRequerido(String requerido) {
		this.requerido = requerido;
	}

	public String getFirmaelectronica() {
		return firmaelectronica;
	}

	public void setFirmaelectronica(String firmaelectronica) {
		this.firmaelectronica = firmaelectronica;
	}

	public String getFirmaimagen() {
		return firmaimagen;
	}

	public void setFirmaimagen(String firmaimagen) {
		this.firmaimagen = firmaimagen;
	}

	public String getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public String getTipodocumentodescripcion() {
		return tipodocumentodescripcion;
	}

	public void setTipodocumentodescripcion(String tipodocumentodescripcion) {
		this.tipodocumentodescripcion = tipodocumentodescripcion;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(String plantilla) {
		this.plantilla = plantilla;
	}

}
