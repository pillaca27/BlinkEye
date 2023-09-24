package net.royal.spring.framework.modelo;

import net.royal.spring.framework.constante.ConstanteDatos;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class CompanyownerrecursoTransaccion  extends DominioTransaccion {

	
	private String compania; 
	private ConstanteDatos.TIPO_IMAGEN tipoImagen; 
	private String periodo; 
	private String tipoReporte;
	
	
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	public ConstanteDatos.TIPO_IMAGEN getTipoImagen() {
		return tipoImagen;
	}
	public void setTipoImagen(ConstanteDatos.TIPO_IMAGEN tipoImagen) {
		this.tipoImagen = tipoImagen;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getTipoReporte() {
		return tipoReporte;
	}
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	
}
