package net.royal.spring.workflow.dominio;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla SGCORESYS.WF_REEMPLAZO
 */
@Entity
@Table(name = "WF_REEMPLAZO", schema = "sgworkflowsys")
public class WfReemplazo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WfReemplazoPk pk;

	@Size(min = 0, max = 1)
	@Column(name = "TIPO", length = 1, nullable = true)
	private String tipo;

	@Column(name = "EMPLEADO_ANTIGUO", nullable = true)
	private Integer empleadoAntiguo;

	@Column(name = "EMPLEADO_NUEVO", nullable = true)
	private Integer empleadoNuevo;

	@Column(name = "DESDE", nullable = true)
	private java.util.Date desde;

	@Column(name = "HASTA", nullable = true)
	private java.util.Date hasta;

	@Size(min = 0, max = 3000)
	@Column(name = "OBSERVACION", length = 3000, nullable = true)
	private String observacion;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@Size(min = 0, max = 50)
	@Column(name = "CREACION_USUARIO", length = 50, nullable = true)
	private String preparadopor;

	@Column(name = "CREACION_FECHA", nullable = true)
	private java.util.Date fechapreparacion;

	@Size(min = 0, max = 50)
	@Column(name = "MODIFICACION_USUARIO", length = 50, nullable = true)
	private String modificadopor;

	@Column(name = "MODIFICACION_FECHA", nullable = true)
	private java.util.Date fechamodificacion;

	@Transient
	private Boolean auxFlgPreparado = Boolean.FALSE;

	@Transient
	private String nuevoNombre;

	@Transient
	private String antiguoNombre;

	public String getNuevoNombre() {
		return nuevoNombre;
	}

	public void setNuevoNombre(String nuevoNombre) {
		this.nuevoNombre = nuevoNombre;
	}

	public String getAntiguoNombre() {
		return antiguoNombre;
	}

	public void setAntiguoNombre(String antiguoNombre) {
		this.antiguoNombre = antiguoNombre;
	}

	public WfReemplazo() {
		pk = new WfReemplazoPk();
	}

	public WfReemplazo(WfReemplazoPk pk) {
		this.pk = pk;
	}

	public WfReemplazoPk getPk() {
		return pk;
	}

	public void setPk(WfReemplazoPk pk) {
		this.pk = pk;
	}

	/**
	 * 
	 * 
	 * @campo TIPO
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * 
	 * 
	 * @campo TIPO
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * 
	 * 
	 * @campo EMPLEADO_ANTIGUO
	 */
	public Integer getEmpleadoAntiguo() {
		return empleadoAntiguo;
	}

	/**
	 * 
	 * 
	 * @campo EMPLEADO_ANTIGUO
	 */
	public void setEmpleadoAntiguo(Integer empleadoAntiguo) {
		this.empleadoAntiguo = empleadoAntiguo;
	}

	/**
	 * 
	 * 
	 * @campo EMPLEADO_NUEVO
	 */
	public Integer getEmpleadoNuevo() {
		return empleadoNuevo;
	}

	/**
	 * 
	 * 
	 * @campo EMPLEADO_NUEVO
	 */
	public void setEmpleadoNuevo(Integer empleadoNuevo) {
		this.empleadoNuevo = empleadoNuevo;
	}

	/**
	 * 
	 * 
	 * @campo DESDE
	 */
	public java.util.Date getDesde() {
		return desde;
	}

	/**
	 * 
	 * 
	 * @campo DESDE
	 */
	public void setDesde(java.util.Date desde) {
		this.desde = desde;
	}

	/**
	 * 
	 * 
	 * @campo HASTA
	 */
	public java.util.Date getHasta() {
		return hasta;
	}

	/**
	 * 
	 * 
	 * @campo HASTA
	 */
	public void setHasta(java.util.Date hasta) {
		this.hasta = hasta;
	}

	/**
	 * 
	 * 
	 * @campo OBSERVACION
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * 
	 * 
	 * @campo OBSERVACION
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * 
	 * 
	 * @campo ESTADO
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * 
	 * 
	 * @campo ESTADO
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * 
	 * 
	 * @campo PREPARADOPOR
	 */
	public String getPreparadopor() {
		return preparadopor;
	}

	/**
	 * 
	 * 
	 * @campo PREPARADOPOR
	 */
	public void setPreparadopor(String preparadopor) {
		this.preparadopor = preparadopor;
	}

	/**
	 * 
	 * 
	 * @campo FECHAPREPARACION
	 */
	public java.util.Date getFechapreparacion() {
		return fechapreparacion;
	}

	/**
	 * 
	 * 
	 * @campo FECHAPREPARACION
	 */
	public void setFechapreparacion(java.util.Date fechapreparacion) {
		this.fechapreparacion = fechapreparacion;
	}

	/**
	 * 
	 * 
	 * @campo MODIFICADOPOR
	 */
	public String getModificadopor() {
		return modificadopor;
	}

	/**
	 * 
	 * 
	 * @campo MODIFICADOPOR
	 */
	public void setModificadopor(String modificadopor) {
		this.modificadopor = modificadopor;
	}

	/**
	 * 
	 * 
	 * @campo FECHAMODIFICACION
	 */
	public java.util.Date getFechamodificacion() {
		return fechamodificacion;
	}

	/**
	 * 
	 * 
	 * @campo FECHAMODIFICACION
	 */
	public void setFechamodificacion(java.util.Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	public Boolean getAuxFlgPreparado() {
		if (auxFlgPreparado == null)
			return Boolean.FALSE;
		return auxFlgPreparado;
	}

	public void setAuxFlgPreparado(Boolean auxFlgPreparado) {
		this.auxFlgPreparado = auxFlgPreparado;
	}

}
