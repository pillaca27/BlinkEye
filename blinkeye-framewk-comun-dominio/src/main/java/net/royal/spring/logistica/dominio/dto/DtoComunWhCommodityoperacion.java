package net.royal.spring.logistica.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunWhCommodityoperacion extends DominioTransaccion{
	private String operacioncodigo;
	private String descripcion;
	private String tipotransaccion;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private java.util.Date ultimafechareplicacion;
	public String getOperacioncodigo() {
		return operacioncodigo;
	}
	public void setOperacioncodigo(String operacioncodigo) {
		this.operacioncodigo = operacioncodigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipotransaccion() {
		return tipotransaccion;
	}
	public void setTipotransaccion(String tipotransaccion) {
		this.tipotransaccion = tipotransaccion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUltimousuario() {
		return ultimousuario;
	}
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	public java.util.Date getUltimafechareplicacion() {
		return ultimafechareplicacion;
	}
	public void setUltimafechareplicacion(java.util.Date ultimafechareplicacion) {
		this.ultimafechareplicacion = ultimafechareplicacion;
	}
	
}
