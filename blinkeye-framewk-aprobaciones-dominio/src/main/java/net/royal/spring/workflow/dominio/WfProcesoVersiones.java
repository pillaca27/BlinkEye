package net.royal.spring.workflow.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "WF_PROCESO_VERSION", schema = "sgworkflowsys")
public class WfProcesoVersiones implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WfProcesoVersionesPk pk;

	@Column(name = "NOMBRE", nullable = true)
	private String nombre;

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

	@Column(name = "WEB_COMPONENTE", nullable = true)
	private String webcomponente;

	@Column(name = "API", nullable = true)
	private String api;
	
	@Column(name = "SP_VER", nullable = true)
	private String spver;
	
	@Column(name = "ORIGEN_DATOS_ID", nullable = true)
	private Integer origendatosid;
	
	@Column(name = "SEGMENTO_CODIGOTABLA", nullable = true)
	private String segmentocodigotabla;
	
	@Column(name = "FLG_PLANIFICACION_GENERAR", nullable = true)
	private String flgplanificaciongenerar;
	
	@Column(name = "ADMINISTRADOR_ID", nullable = true)
	private Integer administradorId;
	
	@Column(name = "NIVEL_ESTADO_ID_INICIAL", nullable = true)
	private String nivelestadoidinicial;
	
	@Column(name = "COMUNICACION_FLG_ALERTA", nullable = true)
	private String comunicacionFlgAlerta;
	
	@Column(name = "FLG_CORREO_NIVELES", nullable = true)
	private String flgCorreoNiveles;
	
	@Column(name = "UUID", nullable = true)
	private String uuid;
	
	@Column(name = "FECHA_INICIO")
	private Date fechaInicio;
	
	@Column(name = "FECHA_FIN")
	private Date fechaFin;
	
	public WfProcesoVersiones() {
		pk = new WfProcesoVersionesPk();
	}

	public WfProcesoVersiones(WfProcesoVersionesPk pk) {
		this.pk = pk;
	}

	public WfProcesoVersionesPk getPk() {
		return pk;
	}

	public void setPk(WfProcesoVersionesPk pk) {
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

	public String getWebcomponente() {
		return webcomponente;
	}

	public void setWebcomponente(String webcomponente) {
		this.webcomponente = webcomponente;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getSpver() {
		return spver;
	}

	public void setSpver(String spver) {
		this.spver = spver;
	}

	public Integer getOrigendatosid() {
		return origendatosid;
	}

	public void setOrigendatosid(Integer origendatosid) {
		this.origendatosid = origendatosid;
	}

	public String getSegmentocodigotabla() {
		return segmentocodigotabla;
	}

	public void setSegmentocodigotabla(String segmentocodigotabla) {
		this.segmentocodigotabla = segmentocodigotabla;
	}

	public String getFlgplanificaciongenerar() {
		return flgplanificaciongenerar;
	}

	public void setFlgplanificaciongenerar(String flgplanificaciongenerar) {
		this.flgplanificaciongenerar = flgplanificaciongenerar;
	}

	public Integer getAdministradorId() {
		return administradorId;
	}

	public void setAdministradorId(Integer administradorId) {
		this.administradorId = administradorId;
	}

	public String getNivelestadoidinicial() {
		return nivelestadoidinicial;
	}

	public void setNivelestadoidinicial(String nivelestadoidinicial) {
		this.nivelestadoidinicial = nivelestadoidinicial;
	}

	public String getComunicacionFlgAlerta() {
		return comunicacionFlgAlerta;
	}

	public void setComunicacionFlgAlerta(String comunicacionFlgAlerta) {
		this.comunicacionFlgAlerta = comunicacionFlgAlerta;
	}

	public String getFlgCorreoNiveles() {
		return flgCorreoNiveles;
	}

	public void setFlgCorreoNiveles(String flgCorreoNiveles) {
		this.flgCorreoNiveles = flgCorreoNiveles;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
}
