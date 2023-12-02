package net.royal.spring.workflow.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * 
 * @tabla SGCORESYS.WF_TRANSACCION
 */
public class WfFirmaDocumentoPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "ID_FIRMA", nullable = false)
	private BigDecimal idFirma;

	@NotNull
	@Column(name = "ID_DOCUMENTO", nullable = false)
	private BigDecimal idDocumento;

	public WfFirmaDocumentoPk() {
	}

	public WfFirmaDocumentoPk(BigDecimal idFirma, BigDecimal idDocumento) {
		this.idFirma = idFirma;
		this.idDocumento = idDocumento;
	}

	public BigDecimal getIdFirma() {
		return idFirma;
	}

	public void setIdFirma(BigDecimal idFirma) {
		this.idFirma = idFirma;
	}

	public BigDecimal getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(BigDecimal idDocumento) {
		this.idDocumento = idDocumento;
	}

}
