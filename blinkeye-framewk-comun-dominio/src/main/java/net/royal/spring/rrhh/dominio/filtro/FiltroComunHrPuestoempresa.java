package net.royal.spring.rrhh.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunHrPuestoempresa {

	private DominioPaginacion paginacion;
	private Integer codigopuesto;
	private String descripcion;
	private String tipopuesto;
	private String estado;
	private String flginactivo;
	private String puestolinea;
	private Integer puestodef;
	
	public FiltroComunHrPuestoempresa() {
		paginacion=new DominioPaginacion();
	}

	public Integer getCodigopuesto() {
		return codigopuesto;
	}

	public void setCodigopuesto(Integer codigopuesto) {
		this.codigopuesto = codigopuesto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipopuesto() {
		return tipopuesto;
	}

	public void setTipopuesto(String tipopuesto) {
		this.tipopuesto = tipopuesto;
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

	public String getFlginactivo() {
		return flginactivo;
	}

	public void setFlginactivo(String flginactivo) {
		this.flginactivo = flginactivo;
	}

	public String getPuestolinea() {
		return puestolinea;
	}

	public void setPuestolinea(String puestolinea) {
		this.puestolinea = puestolinea;
	}

	public Integer getPuestodef() {
		return puestodef;
	}

	public void setPuestodef(Integer puestodef) {
		this.puestodef = puestodef;
	}
	
}
