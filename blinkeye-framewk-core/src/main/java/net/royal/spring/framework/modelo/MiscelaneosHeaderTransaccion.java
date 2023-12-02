package net.royal.spring.framework.modelo;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class MiscelaneosHeaderTransaccion extends DominioTransaccion{
	private String aplicacioncodigo = "99";
	private String codigotabla;
	private String compania = "999999";
	
	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}
	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	public String getCodigotabla() {
		return codigotabla;
	}
	public void setCodigotabla(String codigotabla) {
		this.codigotabla = codigotabla;
	}
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}	
}
