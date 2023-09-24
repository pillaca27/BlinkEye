package net.royal.spring.core.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;


/**
 * 
 * 
 * @tabla dbo.CorrelativosMast
*/
public class FiltroComunCorrelativosmast extends DominioTransaccion implements java.io.Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String companiacodigo;
	private String tipocomprobante;
	private String serie;
	private String tipoexportar;
	
	

	public String getTipoexportar() {
		return tipoexportar;
	}

	public void setTipoexportar(String tipoexportar) {
		this.tipoexportar = tipoexportar;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaCodigo
	*/
	public String getCompaniacodigo() {
		return companiacodigo;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaCodigo
	*/
	public void setCompaniacodigo(String companiacodigo) {
		this.companiacodigo = companiacodigo;
	}
	/**
	 * 
	 * 
	 * @campo TipoComprobante
	*/
	public String getTipocomprobante() {
		return tipocomprobante;
	}

	/**
	 * 
	 * 
	 * @campo TipoComprobante
	*/
	public void setTipocomprobante(String tipocomprobante) {
		this.tipocomprobante = tipocomprobante;
	}
	/**
	 * 
	 * 
	 * @campo Serie
	*/
	public String getSerie() {
		return serie;
	}

	/**
	 * 
	 * 
	 * @campo Serie
	*/
	public void setSerie(String serie) {
		this.serie = serie;
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
