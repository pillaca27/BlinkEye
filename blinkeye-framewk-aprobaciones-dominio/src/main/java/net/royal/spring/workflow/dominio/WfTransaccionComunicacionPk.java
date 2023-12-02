package net.royal.spring.workflow.dominio;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGCORESYS.WF_TRANSACCION
 */
public class WfTransaccionComunicacionPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "TRANSACCION_ID", nullable = false)
	private Integer transaccionId;

	@NotNull
	@Column(name = "COMUNICACION_ID", nullable = false)
	private Integer comunicacionId;

	public WfTransaccionComunicacionPk() {
	}

	public WfTransaccionComunicacionPk(Integer transaccionId, Integer comunicacionId) {
		super();
		this.transaccionId = transaccionId;
		this.comunicacionId = comunicacionId;
	}

	public Integer getTransaccionId() {
		return transaccionId;
	}

	public void setTransaccionId(Integer transaccionId) {
		this.transaccionId = transaccionId;
	}

	public Integer getComunicacionId() {
		return comunicacionId;
	}

	public void setComunicacionId(Integer comunicacionId) {
		this.comunicacionId = comunicacionId;
	}

}
