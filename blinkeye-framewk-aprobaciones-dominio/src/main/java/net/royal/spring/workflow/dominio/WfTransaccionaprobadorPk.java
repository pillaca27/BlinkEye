package net.royal.spring.workflow.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGCORESYS.WF_TRANSACCION
 */
public class WfTransaccionaprobadorPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "TRANSACCION_ID", nullable = false)
	private BigDecimal transaccionid;

	@NotNull
	@Column(name = "NIVEL_ID", nullable = false)
	private BigDecimal nivelid;

	@NotNull
	@Column(name = "DETALLE_ID", nullable = false)
	private BigDecimal detalleid;

	public WfTransaccionaprobadorPk() {
	}

	public BigDecimal getTransaccionid() {
		return transaccionid;
	}

	public void setTransaccionid(BigDecimal transaccionid) {
		this.transaccionid = transaccionid;
	}

	public BigDecimal getNivelid() {
		return nivelid;
	}

	public void setNivelid(BigDecimal nivelid) {
		this.nivelid = nivelid;
	}

	public BigDecimal getDetalleid() {
		return detalleid;
	}

	public void setDetalleid(BigDecimal detalleid) {
		this.detalleid = detalleid;
	}

}
