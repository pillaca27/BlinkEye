package net.royal.spring.workflow.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "WF_FLUJO_ACCION", schema = "sgworkflowsys")
public class WfProcesoFlujoNivelAcciones implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WfProcesoFlujoNivelAccionesPk pk;

	@Column(name = "NOMBRE", nullable = true)
	private String nombre;

	@Column(name = "ACCION_WF", nullable = true)
	private String accionwf;
	
	@Column(name = "ESTADO_SUBACCION", nullable = true)
	private String estadosubaccion;
	
	@Column(name = "NIVEL_DESTINO_ID", nullable = true)
	private Integer nivelDestinoId;

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

	public WfProcesoFlujoNivelAcciones() {
		pk = new WfProcesoFlujoNivelAccionesPk();
	}

	public WfProcesoFlujoNivelAcciones(WfProcesoFlujoNivelAccionesPk pk) {
		this.pk = pk;
	}

	public WfProcesoFlujoNivelAccionesPk getPk() {
		return pk;
	}

	public void setPk(WfProcesoFlujoNivelAccionesPk pk) {
		this.pk = pk;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAccionwf() {
		return accionwf;
	}

	public void setAccionwf(String accionwf) {
		this.accionwf = accionwf;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public String getEstadosubaccion() {
		return estadosubaccion;
	}

	public void setEstadosubaccion(String estadosubaccion) {
		this.estadosubaccion = estadosubaccion;
	}

	public Integer getNivelDestinoId() {
		return nivelDestinoId;
	}

	public void setNivelDestinoId(Integer nivelDestinoId) {
		this.nivelDestinoId = nivelDestinoId;
	}

}
