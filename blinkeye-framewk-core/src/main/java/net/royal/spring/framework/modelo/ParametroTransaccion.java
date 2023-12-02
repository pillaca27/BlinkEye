package net.royal.spring.framework.modelo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class ParametroTransaccion extends DominioTransaccion{
	private String companiacodigo;
	private String aplicacioncodigo;
	private String parametroclave;
	
	private String descripcionparametro;
	private String tipodedatoflag;
	private String explicacion;
	private String texto;
	private String textoadicional;
	private BigDecimal numero;
	private java.util.Date fecha;
	private String proceso;

	public ParametroTransaccion() {}
	public ParametroTransaccion(String companiacodigo,String aplicacioncodigo,String parametroclave) {
		this.companiacodigo=companiacodigo;
		this.aplicacioncodigo=aplicacioncodigo;
		this.parametroclave=parametroclave;
	}
	
	public String getTextoadicional() {
		return textoadicional;
	}
	public void setTextoadicional(String textoadicional) {
		this.textoadicional = textoadicional;
	}
	public String getProceso() {
		return proceso;
	}
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
	public String getCompaniacodigo() {
		return companiacodigo;
	}
	public void setCompaniacodigo(String companiacodigo) {
		this.companiacodigo = companiacodigo;
	}
	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}
	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	public String getParametroclave() {
		return parametroclave;
	}
	public void setParametroclave(String parametroclave) {
		this.parametroclave = parametroclave;
	}
	public String getExplicacion() {
		return explicacion;
	}
	public void setExplicacion(String explicacion) {
		this.explicacion = explicacion;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public BigDecimal getNumero() {
		return numero;
	}
	public void setNumero(BigDecimal numero) {
		this.numero = numero;
	}
	public java.util.Date getFecha() {
		return fecha;
	}
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}
	public String getDescripcionparametro() {
		return descripcionparametro;
	}
	public void setDescripcionparametro(String descripcionparametro) {
		this.descripcionparametro = descripcionparametro;
	}
	
	
	public String getTipodedatoflag() {
		return tipodedatoflag;
	}
	public void setTipodedatoflag(String tipodedatoflag) {
		this.tipodedatoflag = tipodedatoflag;
	}

}
