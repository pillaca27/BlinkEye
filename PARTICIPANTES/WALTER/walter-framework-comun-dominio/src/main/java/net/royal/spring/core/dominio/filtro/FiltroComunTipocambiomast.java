package net.royal.spring.core.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

/**
 * 
 * 
 * @tabla dbo.TipoCambioMast
*/
public class FiltroComunTipocambiomast extends DominioTransaccion implements java.io.Serializable{


	private String monedacodigo;
	private String monedacambiocodigo;
	private String fechacambiostring;
	private java.util.Date fechacambio;
	private String tipoexportar;
	
	public String getTipoexportar() {
		return tipoexportar;
	}
	public void setTipoexportar(String tipoexportar) {
		this.tipoexportar = tipoexportar;
	}
	
	public String getFechacambiostring() {
		return fechacambiostring;
	}

	public void setFechacambiostring(String fechacambiostring) {
		this.fechacambiostring = fechacambiostring;
	}

	/**
	 * 
	 * 
	 * @campo MonedaCodigo
	*/
	public String getMonedacodigo() {
		return monedacodigo;
	}

	/**
	 * 
	 * 
	 * @campo MonedaCodigo
	*/
	public void setMonedacodigo(String monedacodigo) {
		this.monedacodigo = monedacodigo;
	}
	/**
	 * 
	 * 
	 * @campo MonedaCambioCodigo
	*/
	public String getMonedacambiocodigo() {
		return monedacambiocodigo;
	}

	/**
	 * 
	 * 
	 * @campo MonedaCambioCodigo
	*/
	public void setMonedacambiocodigo(String monedacambiocodigo) {
		this.monedacambiocodigo = monedacambiocodigo;
	}
	/**
	 * 
	 * 
	 * @campo FechaCambio
	*/
	public java.util.Date getFechacambio() {
		return fechacambio;
	}

	/**
	 * 
	 * 
	 * @campo FechaCambio
	*/
	public void setFechacambio(java.util.Date fechacambio) {
		this.fechacambio = fechacambio;
	}

	private DominioPaginacion paginacion;

	public DominioPaginacion getPaginacion() {
		if (paginacion == null)
			paginacion = new DominioPaginacion();
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}


}
