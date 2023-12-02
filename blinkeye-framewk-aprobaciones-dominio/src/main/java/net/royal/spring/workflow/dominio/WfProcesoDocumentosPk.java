package net.royal.spring.workflow.dominio;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class WfProcesoDocumentosPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "PROCESO_ID", nullable = false)
	private String procesoid;

	@NotNull
	@Column(name = "TIPO_DOCUMENTO_ID", nullable = false)
	private String tipodocumentoid;

	public WfProcesoDocumentosPk() {
	}

	public WfProcesoDocumentosPk(String procesoid, String tipodocumentoid) {
		this.procesoid = procesoid;
		this.tipodocumentoid = tipodocumentoid;
	}

	public String getProcesoid() {
		return procesoid;
	}

	public void setProcesoid(String procesoid) {
		this.procesoid = procesoid;
	}

	public String getTipodocumentoid() {
		return tipodocumentoid;
	}

	public void setTipodocumentoid(String tipodocumentoid) {
		this.tipodocumentoid = tipodocumentoid;
	}

}
