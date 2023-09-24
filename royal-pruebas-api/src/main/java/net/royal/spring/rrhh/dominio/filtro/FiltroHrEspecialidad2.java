package net.royal.spring.rrhh.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroHrEspecialidad2 {
	
	private Integer especialidad;
	private String descripcion;
	private Integer empleadoSolicitante;
	private String estado;
	
	private DominioPaginacion dominioPaginacion;
	
	public FiltroHrEspecialidad2() {
		this.dominioPaginacion = new DominioPaginacion();
	}

	public Integer getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Integer especialidad) {
		this.especialidad = especialidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEmpleadoSolicitante() {
		return empleadoSolicitante;
	}

	public void setEmpleadoSolicitante(Integer empleadoSolicitante) {
		this.empleadoSolicitante = empleadoSolicitante;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public DominioPaginacion getDominioPaginacion() {
		return dominioPaginacion;
	}

	public void setDominioPaginacion(DominioPaginacion dominioPaginacion) {
		this.dominioPaginacion = dominioPaginacion;
	}
	
	
	
}
