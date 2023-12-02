package net.royal.spring.rrhh.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunPostulanteSelector {

	private DominioPaginacion paginacion;
	private Integer postulante;
	private String nombre;

	public FiltroComunPostulanteSelector() {
		this.paginacion = new DominioPaginacion();
	}

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public Integer getPostulante() {
		return postulante;
	}

	public void setPostulante(Integer postulante) {
		this.postulante = postulante;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
