package net.royal.spring.tesoreria.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunAdelantoHeader {

	private String companiasocio;
	private String tipogasto;
	private String clasificacion;
	private String estado;  
	
	private DominioPaginacion paginacion=new DominioPaginacion();

	public String getCompaniasocio() {
		return companiasocio;
	}

	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}

	public String getTipogasto() {
		return tipogasto;
	}

	public void setTipogasto(String tipogasto) {
		this.tipogasto = tipogasto;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
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
