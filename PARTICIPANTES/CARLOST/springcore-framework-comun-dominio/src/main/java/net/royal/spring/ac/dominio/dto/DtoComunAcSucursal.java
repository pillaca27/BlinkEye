package net.royal.spring.ac.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunAcSucursal extends DominioTransaccion{
	private String sucursal;
	private String descripcionlocal;
	private String descripcioningles;
	private String sucursalgrupo;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String cuentacontabledefecto;
	private String companiasocio;
	
	public DtoComunAcSucursal() {}
	public DtoComunAcSucursal(String sucursal) {
		this.sucursal=sucursal;
	}
	public DtoComunAcSucursal(String sucursal,String sucursalgrupo) {
		this.sucursal=sucursal;
		this.sucursalgrupo=sucursalgrupo;
	}
	
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
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
	public String getSucursalgrupo() {
		return sucursalgrupo;
	}
	public void setSucursalgrupo(String sucursalgrupo) {
		this.sucursalgrupo = sucursalgrupo;
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
	public String getCuentacontabledefecto() {
		return cuentacontabledefecto;
	}
	public void setCuentacontabledefecto(String cuentacontabledefecto) {
		this.cuentacontabledefecto = cuentacontabledefecto;
	}
	public String getCompaniasocio() {
		return companiasocio;
	}
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}
	
	
}
