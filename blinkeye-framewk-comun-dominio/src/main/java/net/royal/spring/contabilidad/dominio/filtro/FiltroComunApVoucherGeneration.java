package net.royal.spring.contabilidad.dominio.filtro;


import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;


/**
 * 
 * 
 * @tabla dbo.OrdenPago
*/
public class FiltroComunApVoucherGeneration extends DominioTransaccion implements java.io.Serializable{

	private String companiasocio;
	private String periodo;
	private String tipogeneracion;

	
	public String getCompaniasocio() {
		return companiasocio;
	}

	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getTipogeneracion() {
		return tipogeneracion;
	}

	public void setTipogeneracion(String tipogeneracion) {
		this.tipogeneracion = tipogeneracion;
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
