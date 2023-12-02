package net.royal.spring.rrhh.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunHrTipocontrato extends DominioTransaccion{
	private String tipocontrato;
	private String descripcion;
	private String flagplanillas;
	private String flagfechavencimiento;
	private String flagservice;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String flagpracticas;
	private String tiempovencimiento;
	private String carta;
	private String tipocontratortps;
	private String tieneperiodoprueba;
	private String flagindeterminado;
	private String codigoauxiliar;
	private String flagtiempoparcial;
	private String cartanombre;
	
	public DtoComunHrTipocontrato() {}
	public DtoComunHrTipocontrato(String tipocontrato) {
		this.tipocontrato=tipocontrato;
	}
	
	public String getTipocontrato() {
		return tipocontrato;
	}
	public void setTipocontrato(String tipocontrato) {
		this.tipocontrato = tipocontrato;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFlagplanillas() {
		return flagplanillas;
	}
	public void setFlagplanillas(String flagplanillas) {
		this.flagplanillas = flagplanillas;
	}
	public String getFlagfechavencimiento() {
		return flagfechavencimiento;
	}
	public void setFlagfechavencimiento(String flagfechavencimiento) {
		this.flagfechavencimiento = flagfechavencimiento;
	}
	public String getFlagservice() {
		return flagservice;
	}
	public void setFlagservice(String flagservice) {
		this.flagservice = flagservice;
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
	public String getFlagpracticas() {
		return flagpracticas;
	}
	public void setFlagpracticas(String flagpracticas) {
		this.flagpracticas = flagpracticas;
	}
	public String getTiempovencimiento() {
		return tiempovencimiento;
	}
	public void setTiempovencimiento(String tiempovencimiento) {
		this.tiempovencimiento = tiempovencimiento;
	}
	public String getCarta() {
		return carta;
	}
	public void setCarta(String carta) {
		this.carta = carta;
	}
	public String getTipocontratortps() {
		return tipocontratortps;
	}
	public void setTipocontratortps(String tipocontratortps) {
		this.tipocontratortps = tipocontratortps;
	}
	public String getTieneperiodoprueba() {
		return tieneperiodoprueba;
	}
	public void setTieneperiodoprueba(String tieneperiodoprueba) {
		this.tieneperiodoprueba = tieneperiodoprueba;
	}
	public String getFlagindeterminado() {
		return flagindeterminado;
	}
	public void setFlagindeterminado(String flagindeterminado) {
		this.flagindeterminado = flagindeterminado;
	}
	public String getCodigoauxiliar() {
		return codigoauxiliar;
	}
	public void setCodigoauxiliar(String codigoauxiliar) {
		this.codigoauxiliar = codigoauxiliar;
	}
	public String getFlagtiempoparcial() {
		return flagtiempoparcial;
	}
	public void setFlagtiempoparcial(String flagtiempoparcial) {
		this.flagtiempoparcial = flagtiempoparcial;
	}
	public String getCartanombre() {
		return cartanombre;
	}
	public void setCartanombre(String cartanombre) {
		this.cartanombre = cartanombre;
	}
	
}
