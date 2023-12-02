package net.royal.spring.workflow.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "WF_FLUJO_APROBADOR", schema = "sgworkflowsys")
public class WfProcesoFlujoNivelAprobadores implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WfProcesoFlujoNivelAprobadoresPk pk;

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
	
	@Column(name = "SEGMENTO_APROBAR", nullable = true)
	private String segmentoaprobar;

	public WfProcesoFlujoNivelAprobadores() {
		pk = new WfProcesoFlujoNivelAprobadoresPk();
	}

	public WfProcesoFlujoNivelAprobadores(WfProcesoFlujoNivelAprobadoresPk pk) {
		this.pk = pk;
	}

	public WfProcesoFlujoNivelAprobadoresPk getPk() {
		return pk;
	}

	public void setPk(WfProcesoFlujoNivelAprobadoresPk pk) {
		this.pk = pk;
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

	public Date getCreacionfecha() {
		return creacionfecha;
	}

	public void setCreacionfecha(Date creacionfecha) {
		this.creacionfecha = creacionfecha;
	}

	public String getModificacionusuario() {
		return modificacionusuario;
	}

	public void setModificacionusuario(String modificacionusuario) {
		this.modificacionusuario = modificacionusuario;
	}

	public Date getModificacionfecha() {
		return modificacionfecha;
	}

	public void setModificacionfecha(Date modificacionfecha) {
		this.modificacionfecha = modificacionfecha;
	}

	public String getSegmentoaprobar() {
		return segmentoaprobar;
	}

	public void setSegmentoaprobar(String segmentoaprobar) {
		this.segmentoaprobar = segmentoaprobar;
	}

}
