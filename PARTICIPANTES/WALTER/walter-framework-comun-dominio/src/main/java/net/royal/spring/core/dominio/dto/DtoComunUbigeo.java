package net.royal.spring.core.dominio.dto;

public class DtoComunUbigeo {
	private String pais;
	private String departamento;
	private String provincia;
	private String zonapostal;
	
	private String descripcion;
	private String codigoelemento;
	
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getZonapostal() {
		return zonapostal;
	}
	public void setZonapostal(String zonapostal) {
		this.zonapostal = zonapostal;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigoelemento() {
		return codigoelemento;
	}
	public void setCodigoelemento(String codigoelemento) {
		this.codigoelemento = codigoelemento;
	}	
}
