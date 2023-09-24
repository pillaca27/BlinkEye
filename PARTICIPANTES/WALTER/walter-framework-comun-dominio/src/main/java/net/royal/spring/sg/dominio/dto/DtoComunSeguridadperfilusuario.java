package net.royal.spring.sg.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunSeguridadperfilusuario extends DominioTransaccion{
	private String perfil;
	private String nombre;
	private String usuario;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String nombreperfil;
	
	public BigDecimal ROWNUM_ ;
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public String getNombreperfil() {
		return nombreperfil;
	}
	public void setNombreperfil(String nombreperfil) {
		this.nombreperfil = nombreperfil;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
