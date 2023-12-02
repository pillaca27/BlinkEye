package net.royal.spring.workflow.dominio;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "WF_FLUJO_CONFIGURACION", schema = "sgworkflowsys")
public class WfProcesoFlujoConfiguraciones implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WfProcesoFlujoConfiguracionesPk pk;

	@Column(name = "VARIABLE_ID", nullable = true)
	private String variableid;

	@Column(name = "COMPARACION", nullable = true)
	private String comparacion;

	@Column(name = "VALORES", nullable = true)
	private String valores;

	@Column(name = "ESTADO", nullable = true)
	private String estado;

	@Column(name = "RELACION", nullable = true)
	private Integer relacion;

	@Column(name = "CREACION_USUARIO", nullable = true)
	private String creacionusuario;

	@Column(name = "MODIFICACION_USUARIO", nullable = true)
	private String modificacionusuario;

	@Column(name = "CREACION_FECHA", nullable = true)
	private Date creacionfecha;

	@Column(name = "MODIFICACION_FECHA", nullable = true)
	private Date modificacionfecha;

	public WfProcesoFlujoConfiguraciones() {
		pk = new WfProcesoFlujoConfiguracionesPk();
	}

	public WfProcesoFlujoConfiguraciones(WfProcesoFlujoConfiguracionesPk pk) {
		this.pk = pk;
	}

	public WfProcesoFlujoConfiguracionesPk getPk() {
		return pk;
	}

	public void setPk(WfProcesoFlujoConfiguracionesPk pk) {
		this.pk = pk;
	}

	public String getVariableid() {
		return variableid;
	}

	public void setVariableid(String variableid) {
		this.variableid = variableid;
	}

	public String getComparacion() {
		return comparacion;
	}

	public void setComparacion(String comparacion) {
		this.comparacion = comparacion;
	}

	public String getValores() {
		return valores;
	}

	public void setValores(String valores) {
		this.valores = valores;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getRelacion() {
		return relacion;
	}

	public void setRelacion(Integer relacion) {
		this.relacion = relacion;
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
