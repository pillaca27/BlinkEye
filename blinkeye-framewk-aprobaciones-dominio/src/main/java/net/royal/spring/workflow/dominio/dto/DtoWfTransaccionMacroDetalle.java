package net.royal.spring.workflow.dominio.dto;

import net.royal.spring.workflow.dominio.WfTransaccionMacroDetalle;

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
 * @tabla SPRING.WF_TRANSACCION_MACRO_DETALLE
*/
public class DtoWfTransaccionMacroDetalle extends DominioTransaccion implements java.io.Serializable{


	private Integer transaccionMacroId;
	private Integer transaccionMacroDetalleId;
	private Integer transaccionOrigenId;
	private Integer transaccionDestinoId;
	private String creacionUsuario;
	private java.util.Date creacionFecha;
	private String modificacionUsuario;
	private java.util.Date modificacionFecha;

	public Integer getTransaccionMacroId() {
		return transaccionMacroId;
	}

	public void setTransaccionMacroId(Integer transaccionMacroId) {
		this.transaccionMacroId = transaccionMacroId;
	}
	public Integer getTransaccionMacroDetalleId() {
		return transaccionMacroDetalleId;
	}

	public void setTransaccionMacroDetalleId(Integer transaccionMacroDetalleId) {
		this.transaccionMacroDetalleId = transaccionMacroDetalleId;
	}
	public Integer getTransaccionOrigenId() {
		return transaccionOrigenId;
	}

	public void setTransaccionOrigenId(Integer transaccionOrigenId) {
		this.transaccionOrigenId = transaccionOrigenId;
	}
	public Integer getTransaccionDestinoId() {
		return transaccionDestinoId;
	}

	public void setTransaccionDestinoId(Integer transaccionDestinoId) {
		this.transaccionDestinoId = transaccionDestinoId;
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
	public WfTransaccionMacroDetalle obtenerBean() {
		WfTransaccionMacroDetalle bean = new WfTransaccionMacroDetalle();
		return obtenerBean(bean);
	}

	public WfTransaccionMacroDetalle obtenerBean(WfTransaccionMacroDetalle bean) {
		if (bean == null)
			bean = new WfTransaccionMacroDetalle();

		bean.getPk().setTransaccionMacroId(transaccionMacroId);
		bean.getPk().setTransaccionMacroDetalleId(transaccionMacroDetalleId);
		bean.setTransaccionOrigenId(transaccionOrigenId);
		bean.setTransaccionDestinoId(transaccionDestinoId);
		bean.setCreacionUsuario(creacionUsuario);
		bean.setCreacionFecha(creacionFecha);
		bean.setModificacionUsuario(modificacionUsuario);
		bean.setModificacionFecha(modificacionFecha);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
