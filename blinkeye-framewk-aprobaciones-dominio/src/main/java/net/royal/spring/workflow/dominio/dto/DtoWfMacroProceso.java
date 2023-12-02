package net.royal.spring.workflow.dominio.dto;

import net.royal.spring.workflow.dominio.WfMacroProceso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
 * @tabla SPRING.WF_MACRO_PROCESO
*/
public class DtoWfMacroProceso extends DominioTransaccion implements java.io.Serializable{


	private String macroProcesoId;
	private String nombre;
	private String estado;
	private String creacionUsuario;
	private java.util.Date creacionFecha;
	private String modificacionUsuario;
	private java.util.Date modificacionFecha;

	private List<DtoWfMacroProcesoDetalle> detalle;
	
	public DtoWfMacroProceso() {
		this.detalle = new ArrayList<DtoWfMacroProcesoDetalle>();
	}
	
	
	public List<DtoWfMacroProcesoDetalle> getDetalle() {
		return detalle;
	}


	public void setDetalle(List<DtoWfMacroProcesoDetalle> detalle) {
		this.detalle = detalle;
	}


	public String getMacroProcesoId() {
		return macroProcesoId;
	}

	public void setMacroProcesoId(String macroProcesoId) {
		this.macroProcesoId = macroProcesoId;
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
	public WfMacroProceso obtenerBean() {
		WfMacroProceso bean = new WfMacroProceso();
		return obtenerBean(bean);
	}

	public WfMacroProceso obtenerBean(WfMacroProceso bean) {
		if (bean == null)
			bean = new WfMacroProceso();

		bean.getPk().setMacroProcesoId(macroProcesoId);
		bean.setNombre(nombre);
		bean.setEstado(estado);
		bean.setCreacionUsuario(creacionUsuario);
		bean.setCreacionFecha(creacionFecha);
		bean.setModificacionUsuario(modificacionUsuario);
		bean.setModificacionFecha(modificacionFecha);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
