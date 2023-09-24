package net.royal.spring.hr.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

/**
 * QQUECHOD VALIDADO
 * @author QquechoD
 *
 */
public class FiltroComunHrCursodescripcion {
	
	private Integer curso;
	private String descripcion;	
	private String area;
	private String tipo;
	private String estado;
	private String tipomaestro;
	
	
	private DominioPaginacion paginacion;

	
	public String getTipomaestro() {
		return tipomaestro;
	}

	public void setTipomaestro(String tipomaestro) {
		this.tipomaestro = tipomaestro;
	}

	public FiltroComunHrCursodescripcion() {
		this.paginacion = new DominioPaginacion();
	}
	
	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}