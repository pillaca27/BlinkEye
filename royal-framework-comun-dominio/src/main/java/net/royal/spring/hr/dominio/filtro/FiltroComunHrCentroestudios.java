package net.royal.spring.hr.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunHrCentroestudios {
	
	private Integer centro;
	private String descripcion;
	private String estado;
	
	private DominioPaginacion paginacion;

	public FiltroComunHrCentroestudios() {
		this.paginacion = new DominioPaginacion();
	}

	public Integer getCentro() {
		return centro;
	}

	public void setCentro(Integer centro) {
		this.centro = centro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

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
