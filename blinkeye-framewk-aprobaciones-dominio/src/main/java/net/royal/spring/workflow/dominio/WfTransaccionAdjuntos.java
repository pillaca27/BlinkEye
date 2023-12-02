package net.royal.spring.workflow.dominio;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "WF_TRANSACCION_ADJUNTO", schema = "sgworkflowsys")
public class WfTransaccionAdjuntos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WfTransaccionAdjuntosPk pk;

	@Column(name = "PROCESO_ID")
	private String procesoid;

	@Column(name = "VERSION_ID")
	private BigDecimal versionid;

	@Column(name = "FLUJO_ID")
	private BigDecimal flujoid;

	@Column(name = "TIPO_DOCUMENTO_ID")
	private String tipodocumentoid;

	@Column(name = "RUTAADJUNTO")
	private String rutaAdjunto;

	@Column(name = "FLAGVER")
	private String flagver;

	@Column(name = "CANTIDAD_FIRMAS")
	private BigDecimal cantidadfirmas;

	@Column(name = "CREACION_USUARIO", nullable = true)
	private String creacionusuario;

	@Column(name = "MODIFICACION_USUARIO", nullable = true)
	private String modificacionusuario;

	@Column(name = "CREACION_FECHA", nullable = true)
	private Date creacionfecha;

	@Column(name = "MODIFICACION_FECHA", nullable = true)
	private Date modificacionfecha;

	public WfTransaccionAdjuntos() {
		pk = new WfTransaccionAdjuntosPk();
	}

	public WfTransaccionAdjuntos(WfTransaccionAdjuntosPk pk) {
		this.pk = pk;
	}

	public WfTransaccionAdjuntosPk getPk() {
		return pk;
	}

	public void setPk(WfTransaccionAdjuntosPk pk) {
		this.pk = pk;
	}

	public String getProcesoid() {
		return procesoid;
	}

	public void setProcesoid(String procesoid) {
		this.procesoid = procesoid;
	}

	public BigDecimal getVersionid() {
		return versionid;
	}

	public void setVersionid(BigDecimal versionid) {
		this.versionid = versionid;
	}

	public BigDecimal getFlujoid() {
		return flujoid;
	}

	public void setFlujoid(BigDecimal flujoid) {
		this.flujoid = flujoid;
	}

	public String getTipodocumentoid() {
		return tipodocumentoid;
	}

	public void setTipodocumentoid(String tipodocumentoid) {
		this.tipodocumentoid = tipodocumentoid;
	}

	public String getRutaAdjunto() {
		return rutaAdjunto;
	}

	public void setRutaAdjunto(String rutaAdjunto) {
		this.rutaAdjunto = rutaAdjunto;
	}

	public String getFlagver() {
		return flagver;
	}

	public void setFlagver(String flagver) {
		this.flagver = flagver;
	}

	public BigDecimal getCantidadfirmas() {
		return cantidadfirmas;
	}

	public void setCantidadfirmas(BigDecimal cantidadfirmas) {
		this.cantidadfirmas = cantidadfirmas;
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

}
