package net.royal.spring.workflow.dominio;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class WfProcesosPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "PROCESO_ID", nullable = false)
	private String procesoid;

	public WfProcesosPk() {
	}

	public WfProcesosPk(String procesoid) {
		this.procesoid = procesoid;
	}

	public String getProcesoid() {
		return procesoid;
	}

	public void setProcesoid(String procesoid) {
		this.procesoid = procesoid;
	}

}
