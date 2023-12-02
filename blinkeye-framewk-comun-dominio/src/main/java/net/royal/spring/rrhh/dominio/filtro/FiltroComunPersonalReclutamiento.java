package net.royal.spring.rrhh.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunPersonalReclutamiento {

	private DominioPaginacion paginacion;
	private Integer empleado;
	private String nombre;
	private String compania;
	private String tipoPlanilla;
	private String auxCompaniaNombre;
	private String auxTipoPlanillaNombre;

	public FiltroComunPersonalReclutamiento() {
		this.paginacion = new DominioPaginacion();
	}

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public Integer getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Integer empleado) {
		this.empleado = empleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public String getTipoPlanilla() {
		return tipoPlanilla;
	}

	public void setTipoPlanilla(String tipoPlanilla) {
		this.tipoPlanilla = tipoPlanilla;
	}

	public String getAuxCompaniaNombre() {
		return auxCompaniaNombre;
	}

	public void setAuxCompaniaNombre(String auxCompaniaNombre) {
		this.auxCompaniaNombre = auxCompaniaNombre;
	}

	public String getAuxTipoPlanillaNombre() {
		return auxTipoPlanillaNombre;
	}

	public void setAuxTipoPlanillaNombre(String auxTipoPlanillaNombre) {
		this.auxTipoPlanillaNombre = auxTipoPlanillaNombre;
	}

}
