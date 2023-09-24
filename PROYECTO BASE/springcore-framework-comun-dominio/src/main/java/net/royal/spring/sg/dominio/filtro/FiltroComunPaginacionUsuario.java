package net.royal.spring.sg.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunPaginacionUsuario {
	public String nombre;
	public String codigoUsuario;
	public String usuarioPerfil;
	public String estado;
	
	public String tipoexportar;
	
	public DominioPaginacion paginacion;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public String getUsuarioPerfil() {
		return usuarioPerfil;
	}
	public void setUsuarioPerfil(String usuarioPerfil) {
		this.usuarioPerfil = usuarioPerfil;
	}
	public DominioPaginacion getPaginacion() {
		return paginacion;
	}
	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTipoexportar() {
		return tipoexportar;
	}
	public void setTipoexportar(String tipoexportar) {
		this.tipoexportar = tipoexportar;
	}
	
}

