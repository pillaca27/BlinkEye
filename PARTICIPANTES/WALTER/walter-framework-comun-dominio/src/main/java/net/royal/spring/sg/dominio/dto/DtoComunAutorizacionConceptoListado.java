package net.royal.spring.sg.dominio.dto;

import java.util.Date;
import java.util.List;

import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
 

public class DtoComunAutorizacionConceptoListado {
	
	private String seleccionar;	
	private List<DtoComunAutorizacionConcepto> listado;
	
	public String getSeleccionar() {
		return seleccionar;
	}
	public void setSeleccionar(String seleccionar) {
		this.seleccionar = seleccionar;
	}
	public List<DtoComunAutorizacionConcepto> getListado() {
		return listado;
	}
	public void setListado(List<DtoComunAutorizacionConcepto> listado) {
		this.listado = listado;
	}
	

	
}
