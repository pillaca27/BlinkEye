package net.royal.spring.gth.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroPersonaListado {

	private String nombre;
	private DominioPaginacion paginacion;
	
	public FiltroPersonaListado() {
		this.paginacion = new DominioPaginacion();
	}

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
