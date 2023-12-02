package net.royal.spring.seguridad.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunAplicacionesmast  extends DominioTransaccion{
	private String aplicacioncodigo;
	private String descripcioncorta;
	private String descripcionlarga;
	private String ultimoperiodocontable;
	private String sistemafuente;
	private String estadisponible;
	private String departamentorevisor;
	private String ultimoperiodoprocesado;
	private String aplicacionusuario;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String aplicacionusuario02;
	private String aplicacionusuario03;
	private String aplicacionusuario04;
	private BigDecimal orden;
	
	public DtoComunAplicacionesmast() {}
	public DtoComunAplicacionesmast(String aplicacioncodigo) {
		this.aplicacioncodigo=aplicacioncodigo;
	}
	
	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}
	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	public String getDescripcioncorta() {
		return descripcioncorta;
	}
	public void setDescripcioncorta(String descripcioncorta) {
		this.descripcioncorta = descripcioncorta;
	}
	public String getDescripcionlarga() {
		return descripcionlarga;
	}
	public void setDescripcionlarga(String descripcionlarga) {
		this.descripcionlarga = descripcionlarga;
	}
	public String getUltimoperiodocontable() {
		return ultimoperiodocontable;
	}
	public void setUltimoperiodocontable(String ultimoperiodocontable) {
		this.ultimoperiodocontable = ultimoperiodocontable;
	}
	public String getSistemafuente() {
		return sistemafuente;
	}
	public void setSistemafuente(String sistemafuente) {
		this.sistemafuente = sistemafuente;
	}
	public String getEstadisponible() {
		return estadisponible;
	}
	public void setEstadisponible(String estadisponible) {
		this.estadisponible = estadisponible;
	}
	public String getDepartamentorevisor() {
		return departamentorevisor;
	}
	public void setDepartamentorevisor(String departamentorevisor) {
		this.departamentorevisor = departamentorevisor;
	}
	public String getUltimoperiodoprocesado() {
		return ultimoperiodoprocesado;
	}
	public void setUltimoperiodoprocesado(String ultimoperiodoprocesado) {
		this.ultimoperiodoprocesado = ultimoperiodoprocesado;
	}
	public String getAplicacionusuario() {
		return aplicacionusuario;
	}
	public void setAplicacionusuario(String aplicacionusuario) {
		this.aplicacionusuario = aplicacionusuario;
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
	public String getAplicacionusuario02() {
		return aplicacionusuario02;
	}
	public void setAplicacionusuario02(String aplicacionusuario02) {
		this.aplicacionusuario02 = aplicacionusuario02;
	}
	public String getAplicacionusuario03() {
		return aplicacionusuario03;
	}
	public void setAplicacionusuario03(String aplicacionusuario03) {
		this.aplicacionusuario03 = aplicacionusuario03;
	}
	public String getAplicacionusuario04() {
		return aplicacionusuario04;
	}
	public void setAplicacionusuario04(String aplicacionusuario04) {
		this.aplicacionusuario04 = aplicacionusuario04;
	}
	public BigDecimal getOrden() {
		return orden;
	}
	public void setOrden(BigDecimal orden) {
		this.orden = orden;
	}
	
	
}
