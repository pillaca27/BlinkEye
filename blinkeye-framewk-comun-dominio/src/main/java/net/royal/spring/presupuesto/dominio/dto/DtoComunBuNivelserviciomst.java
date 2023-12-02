package net.royal.spring.presupuesto.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunBuNivelserviciomst extends DominioTransaccion{
	
	private String tiposervicio;
	
	
	public String getTiposervicio() {
		return tiposervicio;
	}
	public void setTiposervicio(String tiposervicio) {
		this.tiposervicio = tiposervicio;
	}	
}
