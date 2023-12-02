package net.royal.spring.workflow.dominio;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "WF_FIRMA", schema = "sgworkflowsys")
public class WfFirma implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WfFirmaPk pk;

	@Column(name = "ESTADO", length = 4, nullable = true)
	private String estado;

	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 100)
	@Column(name = "ULTIMOUSUARIO", length = 100, nullable = true)
	private String ultimousuario;

	public WfFirma() {
		pk = new WfFirmaPk();
	}

	public WfFirma(WfFirmaPk pk) {
		this.pk = pk;
	}

	public WfFirmaPk getPk() {
		return pk;
	}

	public void setPk(WfFirmaPk pk) {
		this.pk = pk;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}

	public String getUltimousuario() {
		return ultimousuario;
	}

	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}

}
