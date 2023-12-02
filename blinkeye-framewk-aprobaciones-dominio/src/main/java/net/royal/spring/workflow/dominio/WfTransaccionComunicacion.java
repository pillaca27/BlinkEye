package net.royal.spring.workflow.dominio;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

@Entity
@Table(name = "wf_transaccion_comunicacion", schema = "sgworkflowsys")
public class WfTransaccionComunicacion extends DominioTransaccion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WfTransaccionComunicacionPk pk;

	@Column(name = "COMUNICACION_PADRE_ID", nullable = true)
	private Integer comunicacionPadreId;

	@Column(name = "EMISOR_ID", nullable = true)
	private Integer emisorId;

	@Column(name = "RECEPTOR_ID", nullable = true)
	private Integer receptorId;

	@Column(name = "MENSAJE", nullable = true)
	private String mensaje;

	@Column(name = "TIPO_MENSAJE_ID", nullable = true)
	private String tipoMensajeid;

	@Column(name = "URL", nullable = true)
	private String url;

	@Column(name = "ESTADO", nullable = true)
	private String estado;

	@Column(name = "CREACION_FECHA", nullable = true)
	private java.util.Date creacionFecha;

	@Column(name = "CREACION_USUARIO", length = 100, nullable = true)
	private String creacionUsuario;

	@Column(name = "MODIFICACION_FECHA", nullable = true)
	private java.util.Date modificacionFecha;

	@Column(name = "MODIFICACION_USUARIO", length = 100, nullable = true)
	private String modificacionUsuario;

	@Transient
	private String auxContenido;
	
	@Transient
	private String origen = "";

	public WfTransaccionComunicacion() {
		pk = new WfTransaccionComunicacionPk();
	}

	public WfTransaccionComunicacion(WfTransaccionComunicacionPk pk) {
		this.pk = pk;
	}

	public WfTransaccionComunicacionPk getPk() {
		return pk;
	}

	public void setPk(WfTransaccionComunicacionPk pk) {
		this.pk = pk;
	}

	public Integer getComunicacionPadreId() {
		return comunicacionPadreId;
	}

	public void setComunicacionPadreId(Integer comunicacionPadreId) {
		this.comunicacionPadreId = comunicacionPadreId;
	}

	public Integer getEmisorId() {
		return emisorId;
	}

	public void setEmisorId(Integer emisorId) {
		this.emisorId = emisorId;
	}

	public Integer getReceptorId() {
		return receptorId;
	}

	public void setReceptorId(Integer receptorId) {
		this.receptorId = receptorId;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getTipoMensajeid() {
		return tipoMensajeid;
	}

	public void setTipoMensajeid(String tipoMensajeid) {
		this.tipoMensajeid = tipoMensajeid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public java.util.Date getCreacionFecha() {
		return creacionFecha;
	}

	public void setCreacionFecha(java.util.Date creacionFecha) {
		this.creacionFecha = creacionFecha;
	}

	public String getCreacionUsuario() {
		return creacionUsuario;
	}

	public void setCreacionUsuario(String creacionUsuario) {
		this.creacionUsuario = creacionUsuario;
	}

	public java.util.Date getModificacionFecha() {
		return modificacionFecha;
	}

	public void setModificacionFecha(java.util.Date modificacionFecha) {
		this.modificacionFecha = modificacionFecha;
	}

	public String getModificacionUsuario() {
		return modificacionUsuario;
	}

	public void setModificacionUsuario(String modificacionUsuario) {
		this.modificacionUsuario = modificacionUsuario;
	}

	public String getAuxContenido() {
		return auxContenido;
	}

	public void setAuxContenido(String auxContenido) {
		this.auxContenido = auxContenido;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

}
