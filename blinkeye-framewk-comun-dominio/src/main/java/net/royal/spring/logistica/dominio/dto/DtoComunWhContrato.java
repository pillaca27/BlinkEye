package net.royal.spring.logistica.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunWhContrato implements java.io.Serializable {
	private String numerocontrato;
	private String carpetanumero;
	private String descripcion;
	private String monedadocumento;
	private java.math.BigDecimal montototal;
	private java.math.BigDecimal proveedor;	
	private java.util.Date fechaaprobacion;
	private String contratoresponsable;		
	private java.math.BigDecimal ROWNUM_;
	public String getNumerocontrato() {
		return numerocontrato;
	}
	public void setNumerocontrato(String numerocontrato) {
		this.numerocontrato = numerocontrato;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getMonedadocumento() {
		return monedadocumento;
	}
	public void setMonedadocumento(String monedadocumento) {
		this.monedadocumento = monedadocumento;
	}
	public java.math.BigDecimal getMontototal() {
		return montototal;
	}
	public void setMontototal(java.math.BigDecimal montototal) {
		this.montototal = montototal;
	}
	public java.math.BigDecimal getProveedor() {
		return proveedor;
	}
	public void setProveedor(java.math.BigDecimal proveedor) {
		this.proveedor = proveedor;
	}
	public java.util.Date getFechaaprobacion() {
		return fechaaprobacion;
	}
	public void setFechaaprobacion(java.util.Date fechaaprobacion) {
		this.fechaaprobacion = fechaaprobacion;
	}
	public String getContratoresponsable() {
		return contratoresponsable;
	}
	public void setContratoresponsable(String contratoresponsable) {
		this.contratoresponsable = contratoresponsable;
	}
	public String getCarpetanumero() {
		return carpetanumero;
	}
	public void setCarpetanumero(String carpetanumero) {
		this.carpetanumero = carpetanumero;
	}
	public java.math.BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(java.math.BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	
	
	

}
