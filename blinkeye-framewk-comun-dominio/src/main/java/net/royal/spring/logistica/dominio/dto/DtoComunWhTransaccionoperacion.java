package net.royal.spring.logistica.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunWhTransaccionoperacion extends DominioTransaccion{
	private String transaccionoperacion;
	private String descripcionlocal;
	private String descripcioningles;
	private String cuentacontable;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	public DtoComunWhTransaccionoperacion() {}
	public DtoComunWhTransaccionoperacion(String transaccionoperacion) {
		this.transaccionoperacion=transaccionoperacion;
	}
	public String getTransaccionoperacion() {
		return transaccionoperacion;
	}
	public void setTransaccionoperacion(String transaccionoperacion) {
		this.transaccionoperacion = transaccionoperacion;
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
	public String getCuentacontable() {
		return cuentacontable;
	}
	public void setCuentacontable(String cuentacontable) {
		this.cuentacontable = cuentacontable;
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
}
