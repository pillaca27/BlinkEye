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
public class WfTransaccionplanificacionPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "TRANSACCION_ID", nullable = false)
	private Integer transaccionId;

	@NotNull
	@Column(name = "PLANIFICACION_ID", nullable = false)
	private Integer planificacionId;

	public WfTransaccionplanificacionPk() {
	}

	public WfTransaccionplanificacionPk(Integer transaccionId, Integer planificacionId) {
		this.transaccionId = transaccionId;
		this.planificacionId = planificacionId;
	}

	public Integer getTransaccionId() {
		return transaccionId;
	}

	public void setTransaccionId(Integer transaccionId) {
		this.transaccionId = transaccionId;
	}

	public Integer getPlanificacionId() {
		return planificacionId;
	}

	public void setPlanificacionId(Integer planificacionId) {
		this.planificacionId = planificacionId;
	}

}
