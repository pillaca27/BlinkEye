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
public class WfFirmaPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "ID_FIRMA", nullable = false)
	private BigDecimal idFirma;

	public WfFirmaPk() {
	}

	public WfFirmaPk(BigDecimal pnumeroproceso) {
		this.idFirma = pnumeroproceso;
	}

	public BigDecimal getIdFirma() {
		return idFirma;
	}

	public void setIdFirma(BigDecimal idFirma) {
		this.idFirma = idFirma;
	}

}
