package net.royal.spring.workflow.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "WF_TRANSACCION_PLANIFICACION", schema = "sgworkflowsys")
public class WfTransaccionplanificacion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WfTransaccionplanificacionPk pk;

	@Column(name = "FLUJO_ID", nullable = true)
	private Integer flujoId;

	@Column(name = "NIVEL_ID", nullable = true)
	private Integer nivelId;

	@Column(name = "DURACION_TIPO", nullable = true)
	private String duracionTipo;

	@Column(name = "DURACION_CANTIDAD", nullable = true)
	private Integer duracionCantidad;

	@Column(name = "FECHA_HORA_INICIO_CALCULADO", nullable = true)
	private Date fechahoraInicioCalculado;

	@Column(name = "FECHA_HORA_FIN_CALCULADO", nullable = true)
	private Date fechahoraFinCalculado;

	@Column(name = "FECHA_HORA_INICIO", nullable = true)
	private Date fechahoraInicio;

	@Column(name = "FECHA_HORA_FIN", nullable = true)
	private Date fechahoraFin;

	@Column(name = "TIPO_GENERACION_ID", nullable = true)
	private String tipoGeneracionId;

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

	public WfTransaccionplanificacion() {
		pk = new WfTransaccionplanificacionPk();
	}

	public WfTransaccionplanificacion(WfTransaccionplanificacionPk pk) {
		this.pk = pk;
	}

	public WfTransaccionplanificacionPk getPk() {
		return pk;
	}

	public void setPk(WfTransaccionplanificacionPk pk) {
		this.pk = pk;
	}

	public Integer getFlujoId() {
		return flujoId;
	}

	public void setFlujoId(Integer flujoId) {
		this.flujoId = flujoId;
	}

	public Integer getNivelId() {
		return nivelId;
	}

	public void setNivelId(Integer nivelId) {
		this.nivelId = nivelId;
	}

	public String getDuracionTipo() {
		return duracionTipo;
	}

	public void setDuracionTipo(String duracionTipo) {
		this.duracionTipo = duracionTipo;
	}

	public Integer getDuracionCantidad() {
		return duracionCantidad;
	}

	public void setDuracionCantidad(Integer duracionCantidad) {
		this.duracionCantidad = duracionCantidad;
	}

	public Date getFechahoraInicioCalculado() {
		return fechahoraInicioCalculado;
	}

	public void setFechahoraInicioCalculado(Date fechahoraInicioCalculado) {
		this.fechahoraInicioCalculado = fechahoraInicioCalculado;
	}

	public Date getFechahoraFinCalculado() {
		return fechahoraFinCalculado;
	}

	public void setFechahoraFinCalculado(Date fechahoraFinCalculado) {
		this.fechahoraFinCalculado = fechahoraFinCalculado;
	}

	public Date getFechahoraInicio() {
		return fechahoraInicio;
	}

	public void setFechahoraInicio(Date fechahoraInicio) {
		this.fechahoraInicio = fechahoraInicio;
	}

	public Date getFechahoraFin() {
		return fechahoraFin;
	}

	public void setFechahoraFin(Date fechahoraFin) {
		this.fechahoraFin = fechahoraFin;
	}

	public String getTipoGeneracionId() {
		return tipoGeneracionId;
	}

	public void setTipoGeneracionId(String tipoGeneracionId) {
		this.tipoGeneracionId = tipoGeneracionId;
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

}
