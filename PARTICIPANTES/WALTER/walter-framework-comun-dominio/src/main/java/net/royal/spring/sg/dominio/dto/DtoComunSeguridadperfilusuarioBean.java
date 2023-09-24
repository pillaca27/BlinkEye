package net.royal.spring.sg.dominio.dto;

 
 
public class DtoComunSeguridadperfilusuarioBean {

	
	private String estado; 
	private String ultimousuario; 
	private java.util.Date ultimafechamodif; 
	private Boolean auxFlgPreparado=Boolean.FALSE; 
	private String labeelbutton ;
	private DtoComunSeguridadperfilusuarioPkBean pk;
	
	
	public DtoComunSeguridadperfilusuarioPkBean getPk() {
		return pk;
	}
	public void setPk(DtoComunSeguridadperfilusuarioPkBean pk) {
		this.pk = pk;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUltimousuario() {
		return ultimousuario;
	}
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	public Boolean getAuxFlgPreparado() {
		return auxFlgPreparado;
	}
	public void setAuxFlgPreparado(Boolean auxFlgPreparado) {
		this.auxFlgPreparado = auxFlgPreparado;
	}
	public String getLabeelbutton() {
		return labeelbutton;
	}
	public void setLabeelbutton(String labeelbutton) {
		this.labeelbutton = labeelbutton;
	}
	
	
	
}
