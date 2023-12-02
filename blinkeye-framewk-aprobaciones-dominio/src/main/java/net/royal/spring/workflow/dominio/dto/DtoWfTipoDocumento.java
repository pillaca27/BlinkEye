package net.royal.spring.workflow.dominio.dto;

public class DtoWfTipoDocumento {
	private String proceso;
	private String tipodocumento;
	private String grupo;
	private String rutaplantilla;
	private String archivoString;
	private String tipodocumentoAux;

	public String getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getRutaplantilla() {
		return rutaplantilla;
	}

	public void setRutaplantilla(String rutaplantilla) {
		this.rutaplantilla = rutaplantilla;
	}

	public String getArchivoString() {
		return archivoString;
	}

	public void setArchivoString(String archivoString) {
		this.archivoString = archivoString;
	}

	public String getTipodocumentoAux() {
		return tipodocumentoAux;
	}

	public void setTipodocumentoAux(String tipodocumentoAux) {
		this.tipodocumentoAux = tipodocumentoAux;
	}

}
