package net.royal.spring.workflow.dominio;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class FsDocumentoPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "DOCUMENTO_ID", nullable = false)
	private Integer documentoId;

	public FsDocumentoPk() {
	}

	public Integer getDocumentoId() {
		return documentoId;
	}

	public void setDocumentoId(Integer documentoId) {
		this.documentoId = documentoId;
	}

	public FsDocumentoPk(@NotNull Integer documentoId) {
		super();
		this.documentoId = documentoId;
	}

}
