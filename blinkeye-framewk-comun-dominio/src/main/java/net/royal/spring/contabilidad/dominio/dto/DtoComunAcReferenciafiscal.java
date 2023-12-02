package net.royal.spring.contabilidad.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunAcReferenciafiscal extends DominioTransaccion{
	// pk
	private String ano;
	private String tiporeferenciafiscal;
	private String nivel;
	private String referenciafiscal;
	
	// campos
	private String descripcionlocal;
	private String descripcioningles;
	private String referenciafiscalrelacionada;	
	private String referenciafiscalanterior;
	private String unidadmedida;
	private String tipogasto;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	public DtoComunAcReferenciafiscal() {}
	public DtoComunAcReferenciafiscal(String ano,String tiporeferenciafiscal,String nivel,String referenciafiscal) {
		this.ano=ano;
		this.tiporeferenciafiscal=tiporeferenciafiscal;
		this.nivel=nivel;
		this.referenciafiscal=referenciafiscal;
	}
		
	public String getTiporeferenciafiscal() {
		return tiporeferenciafiscal;
	}
	public void setTiporeferenciafiscal(String tiporeferenciafiscal) {
		this.tiporeferenciafiscal = tiporeferenciafiscal;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getReferenciafiscal() {
		return referenciafiscal;
	}
	public void setReferenciafiscal(String referenciafiscal) {
		this.referenciafiscal = referenciafiscal;
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
	public String getReferenciafiscalrelacionada() {
		return referenciafiscalrelacionada;
	}
	public void setReferenciafiscalrelacionada(String referenciafiscalrelacionada) {
		this.referenciafiscalrelacionada = referenciafiscalrelacionada;
	}
	public String getReferenciafiscalanterior() {
		return referenciafiscalanterior;
	}
	public void setReferenciafiscalanterior(String referenciafiscalanterior) {
		this.referenciafiscalanterior = referenciafiscalanterior;
	}
	public String getUnidadmedida() {
		return unidadmedida;
	}
	public void setUnidadmedida(String unidadmedida) {
		this.unidadmedida = unidadmedida;
	}
	public String getTipogasto() {
		return tipogasto;
	}
	public void setTipogasto(String tipogasto) {
		this.tipogasto = tipogasto;
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
