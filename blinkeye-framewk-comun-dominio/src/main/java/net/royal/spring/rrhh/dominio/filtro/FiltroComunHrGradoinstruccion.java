package net.royal.spring.rrhh.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

/**
 * MALCAN VALIDADO
 * @author QquechoD
 *
 */
public class FiltroComunHrGradoinstruccion {
	
	private String gradoinstruccion;
	private String descripcion;	
	private String estado;

	private DominioPaginacion paginacion;

	
	public FiltroComunHrGradoinstruccion() {
		this.paginacion = new DominioPaginacion();
	}
	
	public String getGradoinstruccion() {
		return gradoinstruccion;
	}

	public void setGradoinstruccion(String gradoinstruccion) {
		this.gradoinstruccion = gradoinstruccion;
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