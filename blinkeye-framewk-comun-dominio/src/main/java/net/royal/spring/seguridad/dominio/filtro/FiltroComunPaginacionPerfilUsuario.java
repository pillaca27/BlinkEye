package net.royal.spring.seguridad.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunPaginacionPerfilUsuario {
	private String usuario;
	private String labeelbutton;
	private String tipoexportar;
		 
	public String getTipoexportar() {
		return tipoexportar;
	}
	public void setTipoexportar(String tipoexportar) {
		this.tipoexportar = tipoexportar;
	}
	public String getLabeelbutton() {
		return labeelbutton;
	}
	public void setLabeelbutton(String labeelbutton) {
		this.labeelbutton = labeelbutton;
	}
	public DominioPaginacion paginacion;
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public DominioPaginacion getPaginacion() {
		return paginacion;
	}
	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}
	
	
}
