package net.royal.spring.workflow.dominio.dto;

import java.util.List;

public class DtoFirmaData {

	private Integer idFirma;

	public Integer getIdFirma() {
		return idFirma;
	}

	public void setIdFirma(Integer idFirma) {
		this.idFirma = idFirma;
	}

	private List<DtoFirmaDocumento> documentos;
	private List<DtoFirmaParametros> parametros;

	public List<DtoFirmaDocumento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DtoFirmaDocumento> documentos) {
		this.documentos = documentos;
	}

	public List<DtoFirmaParametros> getParametros() {
		return parametros;
	}

	public void setParametros(List<DtoFirmaParametros> parametros) {
		this.parametros = parametros;
	}

}
