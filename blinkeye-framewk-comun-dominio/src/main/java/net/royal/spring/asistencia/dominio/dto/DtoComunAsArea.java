package net.royal.spring.asistencia.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunAsArea extends DominioTransaccion {
	private BigDecimal area;
	private String nombre;
	private BigDecimal areapadre;
	private BigDecimal diarotacion;
	private java.util.Date ultimarotacion;
	private BigDecimal horario;
	private BigDecimal empleadoresponsable;
	private BigDecimal secretariaarea;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	public BigDecimal getArea() {
		return area;
	}
	public void setArea(BigDecimal area) {
		this.area = area;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public BigDecimal getAreapadre() {
		return areapadre;
	}
	public void setAreapadre(BigDecimal areapadre) {
		this.areapadre = areapadre;
	}
	public BigDecimal getDiarotacion() {
		return diarotacion;
	}
	public void setDiarotacion(BigDecimal diarotacion) {
		this.diarotacion = diarotacion;
	}
	public java.util.Date getUltimarotacion() {
		return ultimarotacion;
	}
	public void setUltimarotacion(java.util.Date ultimarotacion) {
		this.ultimarotacion = ultimarotacion;
	}
	public BigDecimal getHorario() {
		return horario;
	}
	public void setHorario(BigDecimal horario) {
		this.horario = horario;
	}
	public BigDecimal getEmpleadoresponsable() {
		return empleadoresponsable;
	}
	public void setEmpleadoresponsable(BigDecimal empleadoresponsable) {
		this.empleadoresponsable = empleadoresponsable;
	}
	public BigDecimal getSecretariaarea() {
		return secretariaarea;
	}
	public void setSecretariaarea(BigDecimal secretariaarea) {
		this.secretariaarea = secretariaarea;
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
