package net.royal.spring.rrhh.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunHrPosicionempresa extends DominioTransaccion{
	private String companyowner;
	private Integer codigoposicion;
	private Integer codigotipo;
	private String descripcion;
	private String descripcioningles;
	private String flagstaff;
	private String centrocostos;
	private String comentarios;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String unidadreplicacion;
	private String unidadoperativa;
	private Integer orden;
	private String responsibleRrhh;
	private String department;
	private Integer codigoposicionsuperior;
	private String companianombre;
	
	public String getCompanyowner() {
		return companyowner;
	}
	public void setCompanyowner(String companyowner) {
		this.companyowner = companyowner;
	}
	public Integer getCodigoposicion() {
		return codigoposicion;
	}
	public void setCodigoposicion(Integer codigoposicion) {
		this.codigoposicion = codigoposicion;
	}
	public Integer getCodigotipo() {
		return codigotipo;
	}
	public void setCodigotipo(Integer codigotipo) {
		this.codigotipo = codigotipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescripcioningles() {
		return descripcioningles;
	}
	public void setDescripcioningles(String descripcioningles) {
		this.descripcioningles = descripcioningles;
	}
	public String getFlagstaff() {
		return flagstaff;
	}
	public void setFlagstaff(String flagstaff) {
		this.flagstaff = flagstaff;
	}
	public String getCentrocostos() {
		return centrocostos;
	}
	public void setCentrocostos(String centrocostos) {
		this.centrocostos = centrocostos;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
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
	public String getUnidadreplicacion() {
		return unidadreplicacion;
	}
	public void setUnidadreplicacion(String unidadreplicacion) {
		this.unidadreplicacion = unidadreplicacion;
	}
	public String getUnidadoperativa() {
		return unidadoperativa;
	}
	public void setUnidadoperativa(String unidadoperativa) {
		this.unidadoperativa = unidadoperativa;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public String getResponsibleRrhh() {
		return responsibleRrhh;
	}
	public void setResponsibleRrhh(String responsibleRrhh) {
		this.responsibleRrhh = responsibleRrhh;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Integer getCodigoposicionsuperior() {
		return codigoposicionsuperior;
	}
	public void setCodigoposicionsuperior(Integer codigoposicionsuperior) {
		this.codigoposicionsuperior = codigoposicionsuperior;
	}
	public String getCompanianombre() {
		return companianombre;
	}
	public void setCompanianombre(String companianombre) {
		this.companianombre = companianombre;
	}
	
}
