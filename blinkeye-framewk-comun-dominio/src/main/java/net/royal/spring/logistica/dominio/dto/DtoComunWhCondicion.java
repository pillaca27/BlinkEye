package net.royal.spring.logistica.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunWhCondicion extends DominioTransaccion{
	private String condicion;
	private String descripcionlocal;
	private String descripcioningles;
	private java.math.BigDecimal porcentaje;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String cuentainventario;
	private String nocontrolableflag;
	public String getCondicion() {
		return condicion;
	}
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	public String getDescripcionlocal() {
		return descripcionlocal;
	}
	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}
	public String getDescripcioningles() {
		return descripcioningles;
	}
	public void setDescripcioningles(String descripcioningles) {
		this.descripcioningles = descripcioningles;
	}
	public java.math.BigDecimal getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(java.math.BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
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
	public String getCuentainventario() {
		return cuentainventario;
	}
	public void setCuentainventario(String cuentainventario) {
		this.cuentainventario = cuentainventario;
	}
	public String getNocontrolableflag() {
		return nocontrolableflag;
	}
	public void setNocontrolableflag(String nocontrolableflag) {
		this.nocontrolableflag = nocontrolableflag;
	}
	
	
}
