package net.royal.spring.rrhh.dominio.filtro;

import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunHrProfesion {
	private String profesion;
	private String area;
	private String descripcion;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String unidadreplicacion;
	private String abreviatura;
	
	private DominioPaginacion paginacion;

	public FiltroComunHrProfesion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FiltroComunHrProfesion(String profesion, String area, String descripcion, String estado,
			String ultimousuario, Date ultimafechamodif, String unidadreplicacion, String abreviatura,
			DominioPaginacion paginacion) {
		super();
		this.profesion = profesion;
		this.area = area;
		this.descripcion = descripcion;
		this.estado = estado;
		this.ultimousuario = ultimousuario;
		this.ultimafechamodif = ultimafechamodif;
		this.unidadreplicacion = unidadreplicacion;
		this.abreviatura = abreviatura;
		this.paginacion = paginacion;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	public String getUltimousuario() {
		return ultimousuario;
	}

	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}

	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}

	public String getUnidadreplicacion() {
		return unidadreplicacion;
	}

	public void setUnidadreplicacion(String unidadreplicacion) {
		this.unidadreplicacion = unidadreplicacion;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}
	
	
}
