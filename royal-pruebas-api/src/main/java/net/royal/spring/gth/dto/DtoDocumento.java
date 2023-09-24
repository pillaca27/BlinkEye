package net.royal.spring.gth.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoDocumento extends DominioTransaccion {

	private String documentoNombre;
	private String documentoContenidoBase64;

	public String getDocumentoNombre() {
		return documentoNombre;
	}

	public void setDocumentoNombre(String documentoNombre) {
		this.documentoNombre = documentoNombre;
	}

	public String getDocumentoContenidoBase64() {
		return documentoContenidoBase64;
	}

	public void setDocumentoContenidoBase64(String documentoContenidoBase64) {
		this.documentoContenidoBase64 = documentoContenidoBase64;
	}

}
