package net.royal.spring.sg.dominio.dto;

public class DtoComunSeguridadTabsMenu {
	private String concepto;
	private String descripcion;
	private String flgAgregar = "N";
	private String flgModificar = "N";
	private String flgBorrar = "N";
	private String flgAprobar = "N";

	private String flgVerDocs = "N";
	private String flgEnviarCorreo = "N";
	private String flgOtros = "N";
	
	
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFlgAgregar() {
		return flgAgregar;
	}
	public void setFlgAgregar(String flgAgregar) {
		this.flgAgregar = flgAgregar;
	}
	public String getFlgModificar() {
		return flgModificar;
	}
	public void setFlgModificar(String flgModificar) {
		this.flgModificar = flgModificar;
	}
	public String getFlgBorrar() {
		return flgBorrar;
	}
	public void setFlgBorrar(String flgBorrar) {
		this.flgBorrar = flgBorrar;
	}
	public String getFlgAprobar() {
		return flgAprobar;
	}
	public void setFlgAprobar(String flgAprobar) {
		this.flgAprobar = flgAprobar;
	}
	public String getFlgVerDocs() {
		return flgVerDocs;
	}
	public void setFlgVerDocs(String flgVerDocs) {
		this.flgVerDocs = flgVerDocs;
	}
	public String getFlgEnviarCorreo() {
		return flgEnviarCorreo;
	}
	public void setFlgEnviarCorreo(String flgEnviarCorreo) {
		this.flgEnviarCorreo = flgEnviarCorreo;
	}
	public String getFlgOtros() {
		return flgOtros;
	}
	public void setFlgOtros(String flgOtros) {
		this.flgOtros = flgOtros;
	}
	
	
	
	
}
