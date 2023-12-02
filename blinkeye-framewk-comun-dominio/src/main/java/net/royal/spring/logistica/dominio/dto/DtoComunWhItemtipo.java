package net.royal.spring.logistica.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunWhItemtipo extends DominioTransaccion{
	private String itemtipo;
	private String descripcionlocal;
	private String descripcioningles;
	private String cuentamayor;
	private String ingenieriaflag;
	private String transacciondelsistemaflag;
	private String materiaprimaflag;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String codigofiscal;
	
	public DtoComunWhItemtipo() {}
	public DtoComunWhItemtipo(String itemtipo) {
		this.itemtipo=itemtipo;
	}
	
	public String getItemtipo() {
		return itemtipo;
	}
	public void setItemtipo(String itemtipo) {
		this.itemtipo = itemtipo;
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
	public String getCuentamayor() {
		return cuentamayor;
	}
	public void setCuentamayor(String cuentamayor) {
		this.cuentamayor = cuentamayor;
	}
	public String getIngenieriaflag() {
		return ingenieriaflag;
	}
	public void setIngenieriaflag(String ingenieriaflag) {
		this.ingenieriaflag = ingenieriaflag;
	}
	public String getTransacciondelsistemaflag() {
		return transacciondelsistemaflag;
	}
	public void setTransacciondelsistemaflag(String transacciondelsistemaflag) {
		this.transacciondelsistemaflag = transacciondelsistemaflag;
	}
	public String getMateriaprimaflag() {
		return materiaprimaflag;
	}
	public void setMateriaprimaflag(String materiaprimaflag) {
		this.materiaprimaflag = materiaprimaflag;
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
	public String getCodigofiscal() {
		return codigofiscal;
	}
	public void setCodigofiscal(String codigofiscal) {
		this.codigofiscal = codigofiscal;
	}

	
}
