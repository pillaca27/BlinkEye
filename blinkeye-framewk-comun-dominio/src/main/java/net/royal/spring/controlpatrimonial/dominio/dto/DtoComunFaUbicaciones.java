package net.royal.spring.controlpatrimonial.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunFaUbicaciones extends DominioTransaccion{
	private String ubicacion;
	private String grupo01;
	private String descripcionlocal;
	private String descripcioningles;
	private BigDecimal numerodigitos;
	private String centrocostos;
	private String situacionactivo;
	private String financiadoflag;	
	private String unidadnegocio;
	private String departamento;
	private String provincia;
	private String codigopostal;
	private String ubigeo;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	private BigDecimal auxNumerodigitosHijos;
	private BigDecimal auxCantidadHijos;
	@JsonIgnore
	private BigDecimal ROWNUM_;

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getGrupo01() {
		return grupo01;
	}

	public void setGrupo01(String grupo01) {
		this.grupo01 = grupo01;
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

	public BigDecimal getNumerodigitos() {
		return numerodigitos;
	}

	public void setNumerodigitos(BigDecimal numerodigitos) {
		this.numerodigitos = numerodigitos;
	}

	public String getCentrocostos() {
		return centrocostos;
	}

	public void setCentrocostos(String centrocostos) {
		this.centrocostos = centrocostos;
	}

	public String getSituacionactivo() {
		return situacionactivo;
	}

	public void setSituacionactivo(String situacionactivo) {
		this.situacionactivo = situacionactivo;
	}

	public String getFinanciadoflag() {
		return financiadoflag;
	}

	public void setFinanciadoflag(String financiadoflag) {
		this.financiadoflag = financiadoflag;
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

	public String getUnidadnegocio() {
		return unidadnegocio;
	}

	public void setUnidadnegocio(String unidadnegocio) {
		this.unidadnegocio = unidadnegocio;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCodigopostal() {
		return codigopostal;
	}

	public void setCodigopostal(String codigopostal) {
		this.codigopostal = codigopostal;
	}

	public String getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

	public BigDecimal getAuxNumerodigitosHijos() {
		return auxNumerodigitosHijos;
	}

	public void setAuxNumerodigitosHijos(BigDecimal auxNumerodigitosHijos) {
		this.auxNumerodigitosHijos = auxNumerodigitosHijos;
	}

	public BigDecimal getAuxCantidadHijos() {
		return auxCantidadHijos;
	}

	public void setAuxCantidadHijos(BigDecimal auxCantidadHijos) {
		this.auxCantidadHijos = auxCantidadHijos;
	}
	
	
}
