package net.royal.spring.sg.dominio.lista;

import java.math.BigDecimal;

public class DtlComunUsuario {
	
	private String usuario; 
	private String nombre;
	private String usuarioperfil;
	private String estado;
	
	private BigDecimal ROWNUM_;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuarioperfil() {
		return usuarioperfil;
	}

	public void setUsuarioperfil(String usuarioperfil) {
		this.usuarioperfil = usuarioperfil;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	
}
