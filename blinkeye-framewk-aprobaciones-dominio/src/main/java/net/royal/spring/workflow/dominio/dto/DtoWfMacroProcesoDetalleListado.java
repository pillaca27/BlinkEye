package net.royal.spring.workflow.dominio.dto;

import net.royal.spring.workflow.dominio.WfMacroProcesoDetalle;

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
public class DtoWfMacroProcesoDetalleListado extends DominioTransaccion implements java.io.Serializable{


	private String macroProcesoId;
	private String procesoOrigenId;
	private String procesoDestinoId;
	private Integer orden;
	private String creacionUsuario;
	private java.util.Date creacionFecha;
	private String modificacionUsuario;
	private java.util.Date modificacionFecha;

	public String getMacroProcesoId() {
		return macroProcesoId;
	}

	public void setMacroProcesoId(String macroProcesoId) {
		this.macroProcesoId = macroProcesoId;
	}
	public String getProcesoOrigenId() {
		return procesoOrigenId;
	}

	public void setProcesoOrigenId(String procesoOrigenId) {
		this.procesoOrigenId = procesoOrigenId;
	}
	public String getProcesoDestinoId() {
		return procesoDestinoId;
	}

	public void setProcesoDestinoId(String procesoDestinoId) {
		this.procesoDestinoId = procesoDestinoId;
	}
	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public String getCreacionUsuario() {
		return creacionUsuario;
	}

	public void setCreacionUsuario(String creacionUsuario) {
		this.creacionUsuario = creacionUsuario;
	}
	public java.util.Date getCreacionFecha() {
		return creacionFecha;
	}

	public void setCreacionFecha(java.util.Date creacionFecha) {
		this.creacionFecha = creacionFecha;
	}
	public String getModificacionUsuario() {
		return modificacionUsuario;
	}

	public void setModificacionUsuario(String modificacionUsuario) {
		this.modificacionUsuario = modificacionUsuario;
	}
	public java.util.Date getModificacionFecha() {
		return modificacionFecha;
	}

	public void setModificacionFecha(java.util.Date modificacionFecha) {
		this.modificacionFecha = modificacionFecha;
	}

}
