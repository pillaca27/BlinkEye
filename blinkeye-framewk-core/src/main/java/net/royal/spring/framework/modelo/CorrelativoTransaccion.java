package net.royal.spring.framework.modelo;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class CorrelativoTransaccion extends DominioTransaccion{
	public static String TIPO_CORRELATIVO_SPRING="SPRING";
	public static String TIPO_CORRELATIVO_ANIO="ANIO";
	
	private String tipoCorrelativo=CorrelativoTransaccion.TIPO_CORRELATIVO_SPRING;
	private String companiacodigo;
	private String tipocomprobante;
	private String serie;
	private Integer cerosIzquierda;
	private String anio;
	
	public CorrelativoTransaccion() {}
	public CorrelativoTransaccion(String companiacodigo,String tipocomprobante,String serie) {
		this.companiacodigo=companiacodigo;
		this.tipocomprobante=tipocomprobante;
		this.serie=serie;
	}
	
	public CorrelativoTransaccion(String companiacodigo,String tipocomprobante,String serie, Integer cerosIzquierda) {
		this.companiacodigo=companiacodigo;
		this.tipocomprobante=tipocomprobante;
		this.serie=serie;
		this.cerosIzquierda=cerosIzquierda;
		
	}
	
	private String numeroGeneradoCadena;
	private BigDecimal numeroGenerado;
	
	public String getCompaniacodigo() {
		return companiacodigo;
	}
	public void setCompaniacodigo(String companiacodigo) {
		this.companiacodigo = companiacodigo;
	}
	public String getTipocomprobante() {
		return tipocomprobante;
	}
	public void setTipocomprobante(String tipocomprobante) {
		this.tipocomprobante = tipocomprobante;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getNumeroGeneradoCadena() {
		return numeroGeneradoCadena;
	}
	public void setNumeroGeneradoCadena(String numeroGeneradoCadena) {
		this.numeroGeneradoCadena = numeroGeneradoCadena;
	}
	public BigDecimal getNumeroGenerado() {
		return numeroGenerado;
	}
	public void setNumeroGenerado(BigDecimal numeroGenerado) {
		this.numeroGenerado = numeroGenerado;
	}
	public Integer getCerosIzquierda() {
		return cerosIzquierda;
	}
	public void setCerosIzquierda(Integer cerosIzquierda) {
		this.cerosIzquierda = cerosIzquierda;
	}
	public String getTipoCorrelativo() {
		return tipoCorrelativo;
	}
	public void setTipoCorrelativo(String tipoCorrelativo) {
		this.tipoCorrelativo = tipoCorrelativo;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	
}
