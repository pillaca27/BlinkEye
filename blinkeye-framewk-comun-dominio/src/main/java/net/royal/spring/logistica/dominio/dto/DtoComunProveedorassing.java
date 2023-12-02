package net.royal.spring.logistica.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DtoComunProveedorassing {

	private Integer persona;
	private String busqueda;
	private String linea;
	private String familia;
	private String proveedorcompradorflag;
	private String estado;
	private Date ultimafechamodif;
	private String ultimousuario;
	
	public Integer getPersona() {
		return persona;
	}
	public void setPersona(Integer persona) {
		this.persona = persona;
	}
	public String getBusqueda() {
		return busqueda;
	}
	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	public String getFamilia() {
		return familia;
	}
	public void setFamilia(String familia) {
		this.familia = familia;
	}
	public String getProveedorcompradorflag() {
		return proveedorcompradorflag;
	}
	public void setProveedorcompradorflag(String proveedorcompradorflag) {
		this.proveedorcompradorflag = proveedorcompradorflag;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getUltimafechamodif() {
		return ultimafechamodif;
	}
	public void setUltimafechamodif(Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	public String getUltimousuario() {
		return ultimousuario;
	}
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}	
	
}
