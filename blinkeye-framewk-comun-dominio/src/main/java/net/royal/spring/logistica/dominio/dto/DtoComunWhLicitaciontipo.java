package net.royal.spring.logistica.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunWhLicitaciontipo  extends DominioTransaccion{
	private String tipolicitacion;
	private String descripcionlocal;
	private String descripcionextranjera;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private BigDecimal factormaximo;
	private BigDecimal factorminimo;
	private String excluirflag;
	private String codigoseace;
	private String formatoevaluacion;
	private String formatonumero;
	private String formatotitulo;
	private String formatoobjeto;
	private String formatobuenapro;
	private String buenapronumero;
	private String buenaprotitulo;
	private String buenaproobjeto;
	public String getTipolicitacion() {
		return tipolicitacion;
	}
	public void setTipolicitacion(String tipolicitacion) {
		this.tipolicitacion = tipolicitacion;
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
	public BigDecimal getFactormaximo() {
		return factormaximo;
	}
	public void setFactormaximo(BigDecimal factormaximo) {
		this.factormaximo = factormaximo;
	}
	public BigDecimal getFactorminimo() {
		return factorminimo;
	}
	public void setFactorminimo(BigDecimal factorminimo) {
		this.factorminimo = factorminimo;
	}
	public String getExcluirflag() {
		return excluirflag;
	}
	public void setExcluirflag(String excluirflag) {
		this.excluirflag = excluirflag;
	}
	public String getCodigoseace() {
		return codigoseace;
	}
	public void setCodigoseace(String codigoseace) {
		this.codigoseace = codigoseace;
	}
	public String getFormatoevaluacion() {
		return formatoevaluacion;
	}
	public void setFormatoevaluacion(String formatoevaluacion) {
		this.formatoevaluacion = formatoevaluacion;
	}
	public String getFormatonumero() {
		return formatonumero;
	}
	public void setFormatonumero(String formatonumero) {
		this.formatonumero = formatonumero;
	}
	public String getFormatotitulo() {
		return formatotitulo;
	}
	public void setFormatotitulo(String formatotitulo) {
		this.formatotitulo = formatotitulo;
	}
	public String getFormatoobjeto() {
		return formatoobjeto;
	}
	public void setFormatoobjeto(String formatoobjeto) {
		this.formatoobjeto = formatoobjeto;
	}
	public String getFormatobuenapro() {
		return formatobuenapro;
	}
	public void setFormatobuenapro(String formatobuenapro) {
		this.formatobuenapro = formatobuenapro;
	}
	public String getBuenapronumero() {
		return buenapronumero;
	}
	public void setBuenapronumero(String buenapronumero) {
		this.buenapronumero = buenapronumero;
	}
	public String getBuenaprotitulo() {
		return buenaprotitulo;
	}
	public void setBuenaprotitulo(String buenaprotitulo) {
		this.buenaprotitulo = buenaprotitulo;
	}
	public String getBuenaproobjeto() {
		return buenaproobjeto;
	}
	public void setBuenaproobjeto(String buenaproobjeto) {
		this.buenaproobjeto = buenaproobjeto;
	}
	
	
}
