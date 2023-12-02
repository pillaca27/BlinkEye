package net.royal.spring.asistencia.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunAsConceptoacceso extends DominioTransaccion {
	private String conceptoacceso;
	private String descripcionlocal;
	private String descripcionextranjera;
	private String conceptoaccesosistema;
	private String tipoconceptoacceso;
	private String conceptoplanilla;
	private String expresadoen;
	private String flagparasustitucion;
	private String mandatorio;
	private String pagarrefrigerio;
	private String flagcompensa;
	private String flagotrospermisos;
	private String flagweb;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	public DtoComunAsConceptoacceso() {}
	public DtoComunAsConceptoacceso(String conceptoacceso) {
		this.conceptoacceso=conceptoacceso;
	}
	
	public String getConceptoacceso() {
		return conceptoacceso;
	}
	public void setConceptoacceso(String conceptoacceso) {
		this.conceptoacceso = conceptoacceso;
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
	public String getConceptoaccesosistema() {
		return conceptoaccesosistema;
	}
	public void setConceptoaccesosistema(String conceptoaccesosistema) {
		this.conceptoaccesosistema = conceptoaccesosistema;
	}
	public String getTipoconceptoacceso() {
		return tipoconceptoacceso;
	}
	public void setTipoconceptoacceso(String tipoconceptoacceso) {
		this.tipoconceptoacceso = tipoconceptoacceso;
	}
	public String getConceptoplanilla() {
		return conceptoplanilla;
	}
	public void setConceptoplanilla(String conceptoplanilla) {
		this.conceptoplanilla = conceptoplanilla;
	}
	public String getExpresadoen() {
		return expresadoen;
	}
	public void setExpresadoen(String expresadoen) {
		this.expresadoen = expresadoen;
	}
	public String getFlagparasustitucion() {
		return flagparasustitucion;
	}
	public void setFlagparasustitucion(String flagparasustitucion) {
		this.flagparasustitucion = flagparasustitucion;
	}
	public String getMandatorio() {
		return mandatorio;
	}
	public void setMandatorio(String mandatorio) {
		this.mandatorio = mandatorio;
	}
	public String getPagarrefrigerio() {
		return pagarrefrigerio;
	}
	public void setPagarrefrigerio(String pagarrefrigerio) {
		this.pagarrefrigerio = pagarrefrigerio;
	}
	public String getFlagcompensa() {
		return flagcompensa;
	}
	public void setFlagcompensa(String flagcompensa) {
		this.flagcompensa = flagcompensa;
	}
	public String getFlagotrospermisos() {
		return flagotrospermisos;
	}
	public void setFlagotrospermisos(String flagotrospermisos) {
		this.flagotrospermisos = flagotrospermisos;
	}
	public String getFlagweb() {
		return flagweb;
	}
	public void setFlagweb(String flagweb) {
		this.flagweb = flagweb;
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
