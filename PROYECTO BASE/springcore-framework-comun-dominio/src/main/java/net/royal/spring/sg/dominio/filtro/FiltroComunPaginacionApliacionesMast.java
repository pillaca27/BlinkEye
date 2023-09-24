package net.royal.spring.sg.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunPaginacionApliacionesMast {
	private String estado;
	public DominioPaginacion paginacion;
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public DominioPaginacion getPaginacion() {
		return paginacion;
	}
	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}
	
	
}
