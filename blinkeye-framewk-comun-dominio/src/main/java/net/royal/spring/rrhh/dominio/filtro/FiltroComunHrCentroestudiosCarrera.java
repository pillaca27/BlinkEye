package net.royal.spring.rrhh.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunHrCentroestudiosCarrera {
	
	private Integer centro;
	private String descripcion;
	private String regimen;
	private String tipoinstitucion;
	
	private DominioPaginacion paginacion;

	public FiltroComunHrCentroestudiosCarrera() {
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

	public String getRegimen() {
		return regimen;
	}

	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}

	public String getTipoinstitucion() {
		return tipoinstitucion;
	}

	public void setTipoinstitucion(String tipoinstitucion) {
		this.tipoinstitucion = tipoinstitucion;
	}

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

}
