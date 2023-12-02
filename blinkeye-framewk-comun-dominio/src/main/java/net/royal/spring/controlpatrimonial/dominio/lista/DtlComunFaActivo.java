package net.royal.spring.controlpatrimonial.dominio.lista;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.controlpatrimonial.dominio.dto.DtoComunFaActivo;

public class DtlComunFaActivo extends DtoComunFaActivo {
	
	private String ubicacionNombre;
	private String empleadoacargoNombre;
	private String centrocostosNombre;
	private String companiasocioNombre;
	private String sucursalNombre;
	private String unidadnegocioNombre;
	
	public String getUbicacionNombre() {
		return ubicacionNombre;
	}
	public void setUbicacionNombre(String ubicacionNombre) {
		this.ubicacionNombre = ubicacionNombre;
	}
	public String getEmpleadoacargoNombre() {
		return empleadoacargoNombre;
	}
	public void setEmpleadoacargoNombre(String empleadoacargoNombre) {
		this.empleadoacargoNombre = empleadoacargoNombre;
	}
	public String getCentrocostosNombre() {
		return centrocostosNombre;
	}
	public void setCentrocostosNombre(String centrocostosNombre) {
		this.centrocostosNombre = centrocostosNombre;
	}
	public String getCompaniasocioNombre() {
		return companiasocioNombre;
	}
	public void setCompaniasocioNombre(String companiasocioNombre) {
		this.companiasocioNombre = companiasocioNombre;
	}
	public String getSucursalNombre() {
		return sucursalNombre;
	}
	public void setSucursalNombre(String sucursalNombre) {
		this.sucursalNombre = sucursalNombre;
	}
	public String getUnidadnegocioNombre() {
		return unidadnegocioNombre;
	}
	public void setUnidadnegocioNombre(String unidadnegocioNombre) {
		this.unidadnegocioNombre = unidadnegocioNombre;
	}
}
