package net.royal.spring.workflow.dominio;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "WF_TRANSACCION_ALERTA", schema = "sgworkflowsys")
public class WfTransaccionalerta implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "ALERTA_ID", nullable = false)
	private Integer alertaId;

	@Column(name = "NOMBRE", nullable = true)
	private String nombre;

	@Column(name = "LINK", nullable = true)
	private String link;

	@Column(name = "PERSONA_ID", nullable = true)
	private Integer personaId;

	@Column(name = "PROCESO_ID", nullable = true)
	private String procesoId;

	@Column(name = "TRANSACCION_ID", nullable = true)
	private Integer transaccionId;

	@Column(name = "ACCION", nullable = true)
	private String accion;

	@Column(name = "SUBACCION", nullable = true)
	private String subaccion;

	@Column(name = "REFERENCIA", nullable = true)
	private String referencia;

	@Column(name = "ESTADO", nullable = true)
	private String estado;

	@Column(name = "CREACION_USUARIO", nullable = true)
	private String creacionUsuario;

	@Column(name = "CREACION_FECHA", nullable = true)
	private java.util.Date creacionFecha;

	@Column(name = "MODIFICACION_USUARIO", nullable = true)
	private String modificacionUsuario;

	@Column(name = "MODIFICACION_FECHA", nullable = true)
	private java.util.Date modificacionFecha;

	@Column(name = "ReferenciaDescripcion", nullable = true)
	private String referenciaDescripcion;

	public WfTransaccionalerta() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getPersonaId() {
		return personaId;
	}

	public void setPersonaId(Integer personaId) {
		this.personaId = personaId;
	}

	public String getProcesoId() {
		return procesoId;
	}

	public void setProcesoId(String procesoId) {
		this.procesoId = procesoId;
	}

	public Integer getTransaccionId() {
		return transaccionId;
	}

	public void setTransaccionId(Integer transaccionId) {
		this.transaccionId = transaccionId;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getSubaccion() {
		return subaccion;
	}

	public void setSubaccion(String subaccion) {
		this.subaccion = subaccion;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCreacionUsuario() {
		return creacionUsuario;
	}

	public void setCreacionUsuario(String creacionUsuario) {
		this.creacionUsuario = creacionUsuario;
	}

	public java.util.Date getCreacionFecha() {
		return creacionFecha;
	}

	public void setCreacionFecha(java.util.Date creacionFecha) {
		this.creacionFecha = creacionFecha;
	}

	public String getModificacionUsuario() {
		return modificacionUsuario;
	}

	public void setModificacionUsuario(String modificacionUsuario) {
		this.modificacionUsuario = modificacionUsuario;
	}

	public java.util.Date getModificacionFecha() {
		return modificacionFecha;
	}

	public void setModificacionFecha(java.util.Date modificacionFecha) {
		this.modificacionFecha = modificacionFecha;
	}

	public Integer getAlertaId() {
		return alertaId;
	}

	public void setAlertaId(Integer alertaId) {
		this.alertaId = alertaId;
	}

	public String getReferenciaDescripcion() {
		return referenciaDescripcion;
	}

	public void setReferenciaDescripcion(String referenciaDescripcion) {
		this.referenciaDescripcion = referenciaDescripcion;
	}

}
