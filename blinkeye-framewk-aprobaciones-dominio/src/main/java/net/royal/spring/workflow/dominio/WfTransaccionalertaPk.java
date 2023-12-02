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
public class WfTransaccionalertaPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "ALERTA_ID", nullable = false)
	private Integer alertaId;

	public WfTransaccionalertaPk() {
	}

	public WfTransaccionalertaPk(Integer alertaId) {
		this.alertaId = alertaId;
	}

	public Integer getAlertaId() {
		return alertaId;
	}

	public void setAlertaId(Integer alertaId) {
		this.alertaId = alertaId;
	}

}
