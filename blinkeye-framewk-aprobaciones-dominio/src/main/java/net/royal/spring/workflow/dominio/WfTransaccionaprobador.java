package net.royal.spring.workflow.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "WF_TRANSACCION_APROBADOR", schema = "sgworkflowsys")
public class WfTransaccionaprobador implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WfTransaccionaprobadorPk pk;

	@Column(name = "PERSONA_ID", nullable = true)
	private BigDecimal personaid;

	@Column(name = "CREACION_FECHA", nullable = true)
	private java.util.Date creacionfecha;

	@Size(min = 0, max = 100)
	@Column(name = "CREACION_USUARIO", length = 100, nullable = true)
	private String creacionusuario;

	@Column(name = "MODIFICACION_FECHA", nullable = true)
	private java.util.Date modificacionfecha;

	@Size(min = 0, max = 100)
	@Column(name = "MODIFICACION_USUARIO", length = 100, nullable = true)
	private String modificacionusuario;

	public WfTransaccionaprobador() {
		pk = new WfTransaccionaprobadorPk();
	}

	public WfTransaccionaprobador(WfTransaccionaprobadorPk pk) {
		this.pk = pk;
	}

	public WfTransaccionaprobadorPk getPk() {
		return pk;
	}

	public void setPk(WfTransaccionaprobadorPk pk) {
		this.pk = pk;
	}

	public BigDecimal getPersonaid() {
		return personaid;
	}

	public void setPersonaid(BigDecimal personaid) {
		this.personaid = personaid;
	}

	public java.util.Date getCreacionfecha() {
		return creacionfecha;
	}

	public void setCreacionfecha(java.util.Date creacionfecha) {
		this.creacionfecha = creacionfecha;
	}

	public String getCreacionusuario() {
		return creacionusuario;
	}

	public void setCreacionusuario(String creacionusuario) {
		this.creacionusuario = creacionusuario;
	}

	public java.util.Date getModificacionfecha() {
		return modificacionfecha;
	}

	public void setModificacionfecha(java.util.Date modificacionfecha) {
		this.modificacionfecha = modificacionfecha;
	}

	public String getModificacionusuario() {
		return modificacionusuario;
	}

	public void setModificacionusuario(String modificacionusuario) {
		this.modificacionusuario = modificacionusuario;
	}

}
