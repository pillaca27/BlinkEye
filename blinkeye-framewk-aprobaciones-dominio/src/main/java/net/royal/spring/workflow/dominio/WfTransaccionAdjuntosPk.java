package net.royal.spring.workflow.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class WfTransaccionAdjuntosPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "TRANSACCION_ID", nullable = false)
	private BigDecimal transaccionid;
	
	@NotNull
	@Column(name = "ADJUNTO_ID", nullable = false)
	private BigDecimal adjuntoid;

	public WfTransaccionAdjuntosPk() {
	}

	public WfTransaccionAdjuntosPk(BigDecimal transaccionid, BigDecimal adjuntoid) {
		this.transaccionid = transaccionid;
		this.adjuntoid = adjuntoid;
	}

	public BigDecimal getAdjuntoid() {
		return adjuntoid;
	}

	public void setAdjuntoid(BigDecimal adjuntoid) {
		this.adjuntoid = adjuntoid;
	}

	public BigDecimal getTransaccionid() {
		return transaccionid;
	}

	public void setTransaccionid(BigDecimal transaccionid) {
		this.transaccionid = transaccionid;
	}

}
