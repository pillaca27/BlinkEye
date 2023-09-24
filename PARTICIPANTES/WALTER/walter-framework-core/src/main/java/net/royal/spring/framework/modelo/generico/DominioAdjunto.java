package net.royal.spring.framework.modelo.generico;

public class DominioAdjunto extends DominioArchivo {
	
	private String flagVer;
	private String tipoDocumento;

	public String getFlagVer() {
		return flagVer;
	}

	public void setFlagVer(String flagVer) {
		this.flagVer = flagVer;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

}
