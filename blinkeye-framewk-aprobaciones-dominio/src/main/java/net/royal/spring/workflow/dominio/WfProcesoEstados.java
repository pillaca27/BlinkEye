package net.royal.spring.workflow.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "WF_PROCESO_ESTADO", schema = "sgworkflowsys")
public class WfProcesoEstados implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WfProcesoEstadosPk pk;

	@Column(name = "NOMBRE", nullable = true)
	private String nombre;

	/**
	 * INIC : Estado inicial
	 * RECH : Rechazado/Anulado/Cancelado
	 */
	@Column(name = "TIPO_ID", nullable = true)
	private String tipoId;
	
	@Column(name = "ESTADO", nullable = true)
	private String estado;

	@Column(name = "CREACION_USUARIO", nullable = true)
	private String creacionusuario;

	@Column(name = "MODIFICACION_USUARIO", nullable = true)
	private String modificacionusuario;

	@Column(name = "CREACION_FECHA", nullable = true)
	private Date creacionfecha;

	@Column(name = "MODIFICACION_FECHA", nullable = true)
	private Date modificacionfecha;

	@Column(name = "COLOR", nullable = true)
	private String color;

	public WfProcesoEstados() {
		pk = new WfProcesoEstadosPk();
	}

	public WfProcesoEstados(WfProcesoEstadosPk pk) {
		this.pk = pk;
	}

	public WfProcesoEstadosPk getPk() {
		return pk;
	}

	public void setPk(WfProcesoEstadosPk pk) {
		this.pk = pk;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCreacionusuario() {
		return creacionusuario;
	}

	public void setCreacionusuario(String creacionusuario) {
		this.creacionusuario = creacionusuario;
	}

	public String getModificacionusuario() {
		return modificacionusuario;
	}

	public void setModificacionusuario(String modificacionusuario) {
		this.modificacionusuario = modificacionusuario;
	}

	public Date getCreacionfecha() {
		return creacionfecha;
	}

	public void setCreacionfecha(Date creacionfecha) {
		this.creacionfecha = creacionfecha;
	}

	public Date getModificacionfecha() {
		return modificacionfecha;
	}

	public void setModificacionfecha(Date modificacionfecha) {
		this.modificacionfecha = modificacionfecha;
	}

	public String getTipoId() {
		return tipoId;
	}

	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
