package net.royal.spring.workflow.dominio;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla SPRING.WF_MACRO_PROCESO_DETALLE
*/
@Entity
@Table(name = "WF_MACRO_PROCESO_DETALLE", schema = "sgworkflowsys")
public class WfMacroProcesoDetalle extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private WfMacroProcesoDetallePk pk;

	@Column(name = "ORDEN", nullable = true)
	private Integer orden;

	@Size(min = 0, max = 25)
	@Column(name = "CREACION_USUARIO", length = 25, nullable = true)
	private String creacionUsuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "CREACION_FECHA", nullable = true)
	private java.util.Date creacionFecha;

	@Size(min = 0, max = 25)
	@Column(name = "MODIFICACION_USUARIO", length = 25, nullable = true)
	private String modificacionUsuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "MODIFICACION_FECHA", nullable = true)
	private java.util.Date modificacionFecha;


	public WfMacroProcesoDetalle() {
		pk = new WfMacroProcesoDetallePk();
	}


	public WfMacroProcesoDetalle(WfMacroProcesoDetallePk pk) {
		this.pk = pk;
	}

	public WfMacroProcesoDetallePk getPk() {
		return pk;
	}

	public void setPk(WfMacroProcesoDetallePk pk) {
		this.pk = pk;
	}
	/**
	 *  
	 * 
	 * @campo ORDEN
	*/
	public Integer getOrden() {
		return orden;
	}

	/**
	 *  
	 * 
	 * @campo ORDEN
	*/
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	/**
	 *  
	 * 
	 * @campo CREACION_USUARIO
	*/
	public String getCreacionUsuario() {
		return creacionUsuario;
	}

	/**
	 *  
	 * 
	 * @campo CREACION_USUARIO
	*/
	public void setCreacionUsuario(String creacionUsuario) {
		this.creacionUsuario = creacionUsuario;
	}
	/**
	 *  
	 * 
	 * @campo CREACION_FECHA
	*/
	public java.util.Date getCreacionFecha() {
		return creacionFecha;
	}

	/**
	 *  
	 * 
	 * @campo CREACION_FECHA
	*/
	public void setCreacionFecha(java.util.Date creacionFecha) {
		this.creacionFecha = creacionFecha;
	}
	/**
	 *  
	 * 
	 * @campo MODIFICACION_USUARIO
	*/
	public String getModificacionUsuario() {
		return modificacionUsuario;
	}

	/**
	 *  
	 * 
	 * @campo MODIFICACION_USUARIO
	*/
	public void setModificacionUsuario(String modificacionUsuario) {
		this.modificacionUsuario = modificacionUsuario;
	}
	/**
	 *  
	 * 
	 * @campo MODIFICACION_FECHA
	*/
	public java.util.Date getModificacionFecha() {
		return modificacionFecha;
	}

	/**
	 *  
	 * 
	 * @campo MODIFICACION_FECHA
	*/
	public void setModificacionFecha(java.util.Date modificacionFecha) {
		this.modificacionFecha = modificacionFecha;
	}

}
