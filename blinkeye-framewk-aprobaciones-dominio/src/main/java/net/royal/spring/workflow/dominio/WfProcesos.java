package net.royal.spring.workflow.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "WF_PROCESO", schema = "sgworkflowsys")
public class WfProcesos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WfProcesosPk pk;

	@Column(name = "NOMBRE", nullable = true)
	private String nombre;

	@Column(name = "ESTADO", nullable = true)
	private String estado;

	@Column(name = "APLICACION_ID", nullable = true)
	private String aplicacionid;

	@Column(name = "CREACION_USUARIO", nullable = true)
	private String creacionusuario;

	@Column(name = "MODIFICACION_USUARIO", nullable = true)
	private String modificacionusuario;

	@Column(name = "CREACION_FECHA", nullable = true)
	private Date creacionfecha;

	@Column(name = "MODIFICACION_FECHA", nullable = true)
	private Date modificacionfecha;

	public WfProcesos() {
		pk = new WfProcesosPk();
	}

	public WfProcesos(WfProcesosPk pk) {
		this.pk = pk;
	}

	public WfProcesosPk getPk() {
		return pk;
	}

	public void setPk(WfProcesosPk pk) {
		this.pk = pk;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getAplicacionid() {
		return aplicacionid;
	}

	public void setAplicacionid(String aplicacionid) {
		this.aplicacionid = aplicacionid;
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

//	public String getWebcomponente() {
//		return webcomponente;
//	}
//
//	public void setWebcomponente(String webcomponente) {
//		this.webcomponente = webcomponente;
//	}

//	public String getApi() {
//		return api;
//	}
//
//	public void setApi(String api) {
//		this.api = api;
//	}
//
//	public String getSpver() {
//		return spver;
//	}
//
//	public void setSpver(String spver) {
//		this.spver = spver;
//	}
//
//	public Integer getOrigendatosid() {
//		return origendatosid;
//	}
//
//	public void setOrigendatosid(Integer origendatosid) {
//		this.origendatosid = origendatosid;
//	}
//
//	public String getSegmentocodigotabla() {
//		return segmentocodigotabla;
//	}
//
//	public void setSegmentocodigotabla(String segmentocodigotabla) {
//		this.segmentocodigotabla = segmentocodigotabla;
//	}
//
//	public String getFlgplanificaciongenerar() {
//		return flgplanificaciongenerar;
//	}
//
//	public void setFlgplanificaciongenerar(String flgplanificaciongenerar) {
//		this.flgplanificaciongenerar = flgplanificaciongenerar;
//	}
//
//	public Integer getAdministradorId() {
//		return administradorId;
//	}
//
//	public void setAdministradorId(Integer administradorId) {
//		this.administradorId = administradorId;
//	}
//
//	public String getNivelestadoidinicial() {
//		return nivelestadoidinicial;
//	}
//
//	public void setNivelestadoidinicial(String nivelestadoidinicial) {
//		this.nivelestadoidinicial = nivelestadoidinicial;
//	}
//
//	public String getComunicacionFlgAlerta() {
//		return comunicacionFlgAlerta;
//	}
//
//	public void setComunicacionFlgAlerta(String comunicacionFlgAlerta) {
//		this.comunicacionFlgAlerta = comunicacionFlgAlerta;
//	}

}
