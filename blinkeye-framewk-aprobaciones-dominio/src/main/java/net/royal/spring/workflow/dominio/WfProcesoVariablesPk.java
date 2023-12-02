package net.royal.spring.workflow.dominio;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class WfProcesoVariablesPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "PROCESO_ID", nullable = false)
	private String procesoid;

	@NotNull
	@Column(name = "VARIABLE_ID", nullable = false)
	private String variableid;

	public WfProcesoVariablesPk() {
	}

	public WfProcesoVariablesPk(String procesoid, String variableid) {
		this.procesoid = procesoid;
		this.variableid = variableid;
	}

	public String getProcesoid() {
		return procesoid;
	}

	public void setProcesoid(String procesoid) {
		this.procesoid = procesoid;
	}

	public String getVariableid() {
		return variableid;
	}

	public void setVariableid(String variableid) {
		this.variableid = variableid;
	}

}
