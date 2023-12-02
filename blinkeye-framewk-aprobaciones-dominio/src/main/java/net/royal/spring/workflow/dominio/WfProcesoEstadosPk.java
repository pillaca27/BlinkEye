package net.royal.spring.workflow.dominio;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class WfProcesoEstadosPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "PROCESO_ID", nullable = false)
	private String procesoid;

	@NotNull
	@Column(name = "ESTADO_ID", nullable = false)
	private String estadoid;

	public WfProcesoEstadosPk() {
	}

	public WfProcesoEstadosPk(String procesoid, String estadoid) {
		this.procesoid = procesoid;
		this.estadoid = estadoid;
	}

	public String getProcesoid() {
		return procesoid;
	}

	public void setProcesoid(String procesoid) {
		this.procesoid = procesoid;
	}

	public String getEstadoid() {
		return estadoid;
	}

	public void setEstadoid(String estadoid) {
		this.estadoid = estadoid;
	}

}
