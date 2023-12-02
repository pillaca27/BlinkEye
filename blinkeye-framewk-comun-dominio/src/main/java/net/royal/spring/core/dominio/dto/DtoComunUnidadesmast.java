package net.royal.spring.core.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunUnidadesmast  extends DominioTransaccion{
	private String unidadcodigo;
	private String descripcioncorta;
	private String descripcionextranjera;
	private String unidadtipo;
	private Integer numerodecimales;
	private String codigofiscal;
	private String estado;
	private java.util.Date ultimafechamodif;
	private String ultimousuario;
	
	public DtoComunUnidadesmast() {}
	public DtoComunUnidadesmast(String unidadcodigo) {
		this.unidadcodigo=unidadcodigo;
	}
	
	public String getUnidadcodigo() {
		return unidadcodigo;
	}
	public void setUnidadcodigo(String unidadcodigo) {
		this.unidadcodigo = unidadcodigo;
	}
	public String getDescripcioncorta() {
		return descripcioncorta;
	}
	public void setDescripcioncorta(String descripcioncorta) {
		this.descripcioncorta = descripcioncorta;
	}
	public String getDescripcionextranjera() {
		return descripcionextranjera;
	}
	public void setDescripcionextranjera(String descripcionextranjera) {
		this.descripcionextranjera = descripcionextranjera;
	}
	public String getUnidadtipo() {
		return unidadtipo;
	}
	public void setUnidadtipo(String unidadtipo) {
		this.unidadtipo = unidadtipo;
	}
	public Integer getNumerodecimales() {
		return numerodecimales;
	}
	public void setNumerodecimales(Integer numerodecimales) {
		this.numerodecimales = numerodecimales;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	public String getUltimousuario() {
		return ultimousuario;
	}
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	public String getCodigofiscal() {
		return codigofiscal;
	}
	public void setCodigofiscal(String codigofiscal) {
		this.codigofiscal = codigofiscal;
	}	
}
