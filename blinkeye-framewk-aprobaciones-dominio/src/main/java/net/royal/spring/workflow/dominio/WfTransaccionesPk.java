package net.royal.spring.workflow.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

public class WfTransaccionesPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "TRANSACCION_ID", nullable = false)
	private Integer transaccionid;

	@Transient
	private String auxiliar;

	public WfTransaccionesPk() {
	}

	public WfTransaccionesPk(Integer transaccionid) {
		this.transaccionid = transaccionid;
	}

	public Integer getTransaccionid() {
		return transaccionid;
	}

	public void setTransaccionid(Integer transaccionid) {
		this.transaccionid = transaccionid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
