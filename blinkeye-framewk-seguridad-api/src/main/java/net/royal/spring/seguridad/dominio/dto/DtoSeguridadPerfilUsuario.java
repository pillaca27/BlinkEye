package net.royal.spring.seguridad.dominio.dto;

import java.math.BigDecimal;

public class DtoSeguridadPerfilUsuario {
	private String perfil;
	private String nombre;
	private String estado;
	private String usuario;
	private String nombreperfil;
	
	public BigDecimal ROWNUM_ ;
	
	
	
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
