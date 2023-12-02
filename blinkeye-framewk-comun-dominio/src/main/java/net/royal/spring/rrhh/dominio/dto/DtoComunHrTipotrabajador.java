package net.royal.spring.rrhh.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunHrTipotrabajador extends DominioTransaccion{
	private String tipotrabajador;
	private String descripcionlocal;
	private String descripcionextranjera;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String tratamientocontableauxiliar;
	private String estado;
	private String codigoseguro;
	private String categoriartps;
	private String tipotrabajadorrtps;
	private String codigoafp;
	
	public DtoComunHrTipotrabajador() {}
	public DtoComunHrTipotrabajador(String tipotrabajador) {
		this.tipotrabajador=tipotrabajador;
	}
	public String getTipotrabajador() {
		return tipotrabajador;
	}
	public void setTipotrabajador(String tipotrabajador) {
		this.tipotrabajador = tipotrabajador;
	}
	public String getDescripcionlocal() {
		return descripcionlocal;
	}
	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}
	public String getDescripcionextranjera() {
		return descripcionextranjera;
	}
	public void setDescripcionextranjera(String descripcionextranjera) {
		this.descripcionextranjera = descripcionextranjera;
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
	public String getTratamientocontableauxiliar() {
		return tratamientocontableauxiliar;
	}
	public void setTratamientocontableauxiliar(String tratamientocontableauxiliar) {
		this.tratamientocontableauxiliar = tratamientocontableauxiliar;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCodigoseguro() {
		return codigoseguro;
	}
	public void setCodigoseguro(String codigoseguro) {
		this.codigoseguro = codigoseguro;
	}
	public String getCategoriartps() {
		return categoriartps;
	}
	public void setCategoriartps(String categoriartps) {
		this.categoriartps = categoriartps;
	}
	public String getTipotrabajadorrtps() {
		return tipotrabajadorrtps;
	}
	public void setTipotrabajadorrtps(String tipotrabajadorrtps) {
		this.tipotrabajadorrtps = tipotrabajadorrtps;
	}
	public String getCodigoafp() {
		return codigoafp;
	}
	public void setCodigoafp(String codigoafp) {
		this.codigoafp = codigoafp;
	}
	
	
}
