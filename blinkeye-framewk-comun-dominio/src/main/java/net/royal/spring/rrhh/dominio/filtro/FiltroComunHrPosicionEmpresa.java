package net.royal.spring.rrhh.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunHrPosicionEmpresa {
	private String companyowner;
	private Integer codigoposicion;
	private Integer codigotipo;
	private String descripcion;
	private String estado;
	
	private DominioPaginacion paginacion;

	public FiltroComunHrPosicionEmpresa() {
		this.paginacion = new DominioPaginacion();
	}
	
	public String getCompanyowner() {
		return companyowner;
	}

	public void setCompanyowner(String companyowner) {
		this.companyowner = companyowner;
	}

	public Integer getCodigoposicion() {
		return codigoposicion;
	}

	public void setCodigoposicion(Integer codigoposicion) {
		this.codigoposicion = codigoposicion;
	}

	public Integer getCodigotipo() {
		return codigotipo;
	}

	public void setCodigotipo(Integer codigotipo) {
		this.codigotipo = codigotipo;
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
