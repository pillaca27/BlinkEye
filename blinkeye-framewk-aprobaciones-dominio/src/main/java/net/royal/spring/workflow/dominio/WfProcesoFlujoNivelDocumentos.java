package net.royal.spring.workflow.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "WF_FLUJO_NIVEL_DOCUMENTO", schema = "sgworkflowsys")
public class WfProcesoFlujoNivelDocumentos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WfProcesoFlujoNivelDocumentosPk pk;

	@Column(name = "FLG_REQUERIDO", nullable = true)
	private String flgrequerido;

	@Column(name = "FLG_FIRMAELECTRONICA", nullable = true)
	private String flgfirmaelectronica;

	@Column(name = "FLG_FIRMAIMAGEN", nullable = true)
	private String flgfirmaimagen;
	
	@Column(name = "FLG_EDITABLE", nullable = true)
	private String flgeditable;

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

	public WfProcesoFlujoNivelDocumentos() {
		pk = new WfProcesoFlujoNivelDocumentosPk();
	}

	public WfProcesoFlujoNivelDocumentos(WfProcesoFlujoNivelDocumentosPk pk) {
		this.pk = pk;
	}

	public WfProcesoFlujoNivelDocumentosPk getPk() {
		return pk;
	}

	public void setPk(WfProcesoFlujoNivelDocumentosPk pk) {
		this.pk = pk;
	}

	public String getFlgrequerido() {
		return flgrequerido;
	}

	public void setFlgrequerido(String flgrequerido) {
		this.flgrequerido = flgrequerido;
	}

	public String getFlgfirmaelectronica() {
		return flgfirmaelectronica;
	}

	public void setFlgfirmaelectronica(String flgfirmaelectronica) {
		this.flgfirmaelectronica = flgfirmaelectronica;
	}

	public String getFlgfirmaimagen() {
		return flgfirmaimagen;
	}

	public void setFlgfirmaimagen(String flgfirmaimagen) {
		this.flgfirmaimagen = flgfirmaimagen;
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

	public String getFlgeditable() {
		return flgeditable;
	}

	public void setFlgeditable(String flgeditable) {
		this.flgeditable = flgeditable;
	}

}
