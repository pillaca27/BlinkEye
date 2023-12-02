package net.royal.spring.logistica.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class FiltroComunWhQuotationVendor extends DominioTransaccion {
	
	private String compania;
	private String cotizacionNumero;
	private String estadoan;
	private String estadoce;
	
	private String modoInput;
	
	private DominioPaginacion paginacion;

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public String getCotizacionNumero() {
		return cotizacionNumero;
	}

	public void setCotizacionNumero(String cotizacionNumero) {
		this.cotizacionNumero = cotizacionNumero;
	}

	public String getEstadoan() {
		return estadoan;
	}

	public void setEstadoan(String estadoan) {
		this.estadoan = estadoan;
	}

	public String getEstadoce() {
		return estadoce;
	}

	public void setEstadoce(String estadoce) {
		this.estadoce = estadoce;
	}

	public String getModoInput() {
		return modoInput;
	}

	public void setModoInput(String modoInput) {
		this.modoInput = modoInput;
	}
	
	
	
	
}
