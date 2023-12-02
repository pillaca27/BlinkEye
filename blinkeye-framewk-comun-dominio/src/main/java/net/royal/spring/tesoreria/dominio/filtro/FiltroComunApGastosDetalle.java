package net.royal.spring.tesoreria.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunApGastosDetalle {

	private String companiasocio;
	private String tipogasto;
	
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

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}
	
	
}
