package net.royal.spring.workflow.dominio;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "WF_FLUJO_NIVEL", schema = "sgworkflowsys")
public class WfProcesoFlujosNiveles implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WfProcesoFlujosNivelesPk pk;

	@Column(name = "ESTADO", nullable = true)
	private String estado;

	@Column(name = "ESTADO_ID", nullable = true)
	private String estadoid;

	@Column(name = "FLG_CORREOJEFE", nullable = true)
	private String flgcorreojefe;

	@Column(name = "FLG_CORREOSOLICITANTE", nullable = true)
	private String flgcorreosolicitante;

	@Column(name = "CORREOOTROS", nullable = true)
	private String correootros;

	@Column(name = "SP_VALIDAR", nullable = true)
	private String spvalidar;

	@Column(name = "SP_APROBAR", nullable = true)
	private String spaprobar;

	@Column(name = "SP_RECHAZAR", nullable = true)
	private String sprechazar;

	@Column(name = "SP_DEVOLVER", nullable = true)
	private String spdevolver;

	@Column(name = "API", nullable = true)
	private String api;

	@Column(name = "WEB_COMPONENTE", nullable = true)
	private String webcomponente;

	@Column(name = "CREACION_USUARIO", nullable = true)
	private String creacionusuario;

	@Column(name = "MODIFICACION_USUARIO", nullable = true)
	private String modificacionusuario;

	@Column(name = "CREACION_FECHA", nullable = true)
	private Date creacionfecha;

	@Column(name = "MODIFICACION_FECHA", nullable = true)
	private Date modificacionfecha;

	@Column(name = "ORIGEN_DATOS_ID", nullable = true)
	private Integer origendatosid;

	@Column(name = "TIPO_APROBADOR_ID", nullable = true)
	private String tipoaprobadorid;

	@Column(name = "CONDICION_APROBACION_ID", nullable = true)
	private String condicionaprobacionid;

	@Column(name = "FLG_NOTIFICAR", nullable = true)
	private String flgnotificar;

	@Column(name = "FLG_BOTON_APROBAR", nullable = true)
	private String flgbotonaprobar;

	@Column(name = "FLG_BOTON_RECHAZAR", nullable = true)
	private String flgbotonrechazar;

	@Column(name = "FLG_BOTON_DEVOLVER", nullable = true)
	private String flgbotondevolver;

	@Column(name = "FLG_PLANIFICACION_VER", nullable = true)
	private String flgplanificacionver;

	@Column(name = "FLG_PLANIFICACION_EDITABLE", nullable = true)
	private String flgplanificacioneditable;

	@Column(name = "NOMBRE", nullable = true)
	private String nombre;

	@Column(name = "DURACION_TIPO", nullable = true)
	private String duraciontipo;

	@Column(name = "DURACION_CANTIDAD", nullable = true)
	private Integer duracioncantidad;

	/****/
	@Column(name = "FLG_PLANIFICACION_VALIDAR", nullable = true)
	private String flgPlanificacionValidar;

	@Column(name = "PLANIFICACION_TAG", nullable = true)
	private String PlanificacionTag;

	@Column(name = "DOCUMENTO_FLG_COLUMNA_GRUPO", nullable = true)
	private String documentoFlgColumnaGrupo;

	@Column(name = "DOCUMENTO_FLG_BOTON_NUEVO", nullable = true)
	private String documentoFlgBotonNuevo;

	@Column(name = "FLG_APROBAR_COMENTARIO_DETALLADO", nullable = true)
	private String flgAprobarComentarioDetallado;
	
	@Column(name = "FLG_RECHAZAR_COMENTARIO_DETALLADO", nullable = true)
	private String flgRechazarComentarioDetallado;
	
	@Column(name = "FLG_DEVOLVER_COMENTARIO_DETALLADO", nullable = true)
	private String flgDevolverComentarioDetallado;
	
	@Column(name = "FLG_CORREO_PERSONA_REFERENCIA", nullable = true)
	private String flgCorreoPersonaReferencia;
	
	@Column(name = "FLG_CORREO_TRANSACCION", nullable = true)
	private String flgCorreoTransaccion;
	
	@Column(name = "FLG_CORREO_PERSONA", nullable = true)
	private String flgCorreoPersona;
	
	@Column(name = "FLG_APROBAR_COMENTARIO", nullable = true)
	private String flgAprobarComentario;
	
	@Column(name = "FLG_CORREO_ADJUNTAR_DOCUMENTOS", nullable = true)
	private String flgCorreoAdjuntarDocumentos;

	@Column(name = "FLG_BOTON_SALTAR", nullable = true)
	private String flgBotonSaltar;

	public WfProcesoFlujosNiveles() {
		pk = new WfProcesoFlujosNivelesPk();
	}

	public WfProcesoFlujosNiveles(WfProcesoFlujosNivelesPk pk) {
		this.pk = pk;
	}

	public WfProcesoFlujosNivelesPk getPk() {
		return pk;
	}

	public void setPk(WfProcesoFlujosNivelesPk pk) {
		this.pk = pk;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstadoid() {
		return estadoid;
	}

	public void setEstadoid(String estadoid) {
		this.estadoid = estadoid;
	}

	public String getFlgcorreojefe() {
		return flgcorreojefe;
	}

	public void setFlgcorreojefe(String flgcorreojefe) {
		this.flgcorreojefe = flgcorreojefe;
	}

	public String getFlgcorreosolicitante() {
		return flgcorreosolicitante;
	}

	public void setFlgcorreosolicitante(String flgcorreosolicitante) {
		this.flgcorreosolicitante = flgcorreosolicitante;
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

	public String getCorreootros() {
		return correootros;
	}

	public void setCorreootros(String correootros) {
		this.correootros = correootros;
	}

	public String getSpvalidar() {
		return spvalidar;
	}

	public void setSpvalidar(String spvalidar) {
		this.spvalidar = spvalidar;
	}

	public String getSpaprobar() {
		return spaprobar;
	}

	public void setSpaprobar(String spaprobar) {
		this.spaprobar = spaprobar;
	}

	public String getSprechazar() {
		return sprechazar;
	}

	public void setSprechazar(String sprechazar) {
		this.sprechazar = sprechazar;
	}

	public String getSpdevolver() {
		return spdevolver;
	}

	public void setSpdevolver(String spdevolver) {
		this.spdevolver = spdevolver;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getWebcomponente() {
		return webcomponente;
	}

	public void setWebcomponente(String webcomponente) {
		this.webcomponente = webcomponente;
	}

	public Integer getOrigendatosid() {
		return origendatosid;
	}

	public void setOrigendatosid(Integer origendatosid) {
		this.origendatosid = origendatosid;
	}

	public String getTipoaprobadorid() {
		return tipoaprobadorid;
	}

	public void setTipoaprobadorid(String tipoaprobadorid) {
		this.tipoaprobadorid = tipoaprobadorid;
	}

	public String getCondicionaprobacionid() {
		return condicionaprobacionid;
	}

	public void setCondicionaprobacionid(String condicionaprobacionid) {
		this.condicionaprobacionid = condicionaprobacionid;
	}

	public String getFlgnotificar() {
		return flgnotificar;
	}

	public void setFlgnotificar(String flgnotificar) {
		this.flgnotificar = flgnotificar;
	}

	public String getFlgbotonaprobar() {
		return flgbotonaprobar;
	}

	public void setFlgbotonaprobar(String flgbotonaprobar) {
		this.flgbotonaprobar = flgbotonaprobar;
	}

	public String getFlgbotonrechazar() {
		return flgbotonrechazar;
	}

	public void setFlgbotonrechazar(String flgbotonrechazar) {
		this.flgbotonrechazar = flgbotonrechazar;
	}

	public String getFlgbotondevolver() {
		return flgbotondevolver;
	}

	public void setFlgbotondevolver(String flgbotondevolver) {
		this.flgbotondevolver = flgbotondevolver;
	}

	public String getFlgplanificacionver() {
		return flgplanificacionver;
	}

	public void setFlgplanificacionver(String flgplanificacionver) {
		this.flgplanificacionver = flgplanificacionver;
	}

	public String getFlgplanificacioneditable() {
		return flgplanificacioneditable;
	}

	public void setFlgplanificacioneditable(String flgplanificacioneditable) {
		this.flgplanificacioneditable = flgplanificacioneditable;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDuraciontipo() {
		return duraciontipo;
	}

	public void setDuraciontipo(String duraciontipo) {
		this.duraciontipo = duraciontipo;
	}

	public Integer getDuracioncantidad() {
		return duracioncantidad;
	}

	public void setDuracioncantidad(Integer duracioncantidad) {
		this.duracioncantidad = duracioncantidad;
	}

	public String getFlgPlanificacionValidar() {
		return flgPlanificacionValidar;
	}

	public void setFlgPlanificacionValidar(String flgPlanificacionValidar) {
		this.flgPlanificacionValidar = flgPlanificacionValidar;
	}

	public String getPlanificacionTag() {
		return PlanificacionTag;
	}

	public void setPlanificacionTag(String planificacionTag) {
		PlanificacionTag = planificacionTag;
	}

	public String getDocumentoFlgColumnaGrupo() {
		return documentoFlgColumnaGrupo;
	}

	public void setDocumentoFlgColumnaGrupo(String documentoFlgColumnaGrupo) {
		this.documentoFlgColumnaGrupo = documentoFlgColumnaGrupo;
	}

	public String getDocumentoFlgBotonNuevo() {
		return documentoFlgBotonNuevo;
	}

	public void setDocumentoFlgBotonNuevo(String documentoFlgBotonNuevo) {
		this.documentoFlgBotonNuevo = documentoFlgBotonNuevo;
	}

	public String getFlgAprobarComentarioDetallado() {
		return flgAprobarComentarioDetallado;
	}

	public void setFlgAprobarComentarioDetallado(String flgAprobarComentarioDetallado) {
		this.flgAprobarComentarioDetallado = flgAprobarComentarioDetallado;
	}

	public String getFlgRechazarComentarioDetallado() {
		return flgRechazarComentarioDetallado;
	}

	public void setFlgRechazarComentarioDetallado(String flgRechazarComentarioDetallado) {
		this.flgRechazarComentarioDetallado = flgRechazarComentarioDetallado;
	}

	public String getFlgDevolverComentarioDetallado() {
		return flgDevolverComentarioDetallado;
	}

	public void setFlgDevolverComentarioDetallado(String flgDevolverComentarioDetallado) {
		this.flgDevolverComentarioDetallado = flgDevolverComentarioDetallado;
	}

	public String getFlgCorreoPersonaReferencia() {
		return flgCorreoPersonaReferencia;
	}

	public void setFlgCorreoPersonaReferencia(String flgCorreoPersonaReferencia) {
		this.flgCorreoPersonaReferencia = flgCorreoPersonaReferencia;
	}

	public String getFlgCorreoTransaccion() {
		return flgCorreoTransaccion;
	}

	public void setFlgCorreoTransaccion(String flgCorreoTransaccion) {
		this.flgCorreoTransaccion = flgCorreoTransaccion;
	}

	public String getFlgCorreoPersona() {
		return flgCorreoPersona;
	}

	public void setFlgCorreoPersona(String flgCorreoPersona) {
		this.flgCorreoPersona = flgCorreoPersona;
	}

	public String getFlgAprobarComentario() {
		return flgAprobarComentario;
	}

	public void setFlgAprobarComentario(String flgAprobarComentario) {
		this.flgAprobarComentario = flgAprobarComentario;
	}

	public String getFlgCorreoAdjuntarDocumentos() {
		return flgCorreoAdjuntarDocumentos;
	}

	public void setFlgCorreoAdjuntarDocumentos(String flgCorreoAdjuntarDocumentos) {
		this.flgCorreoAdjuntarDocumentos = flgCorreoAdjuntarDocumentos;
	}

	public String getFlgBotonSaltar() {
		return flgBotonSaltar;
	}

	public void setFlgBotonSaltar(String flgBotonSaltar) {
		this.flgBotonSaltar = flgBotonSaltar;
	}

}
