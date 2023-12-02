package net.royal.spring.workflow.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "WF_PROCESO_DOCUMENTO", schema = "sgworkflowsys")
public class WfProcesoDocumentos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WfProcesoDocumentosPk pk;

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
	
	@Column(name = "grupo", nullable = true)
	private String grupo;
	
	@Column(name = "rutaplantilla", nullable = true)
	private String rutaplantilla;

	public WfProcesoDocumentos() {
		pk = new WfProcesoDocumentosPk();
	}

	public WfProcesoDocumentos(WfProcesoDocumentosPk pk) {
		this.pk = pk;
	}

	public WfProcesoDocumentosPk getPk() {
		return pk;
	}

	public void setPk(WfProcesoDocumentosPk pk) {
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

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getRutaplantilla() {
		return rutaplantilla;
	}

	public void setRutaplantilla(String rutaplantilla) {
		this.rutaplantilla = rutaplantilla;
	}

}
