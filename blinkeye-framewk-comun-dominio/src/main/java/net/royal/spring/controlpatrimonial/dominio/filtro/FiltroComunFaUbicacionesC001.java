package net.royal.spring.controlpatrimonial.dominio.filtro;

import java.math.BigDecimal;

public class FiltroComunFaUbicacionesC001 {
	private BigDecimal numerodigitos;
	private BigDecimal numerodigitosHijos;
	private String estado;
	
	private String ubicacion;
	private BigDecimal numerodigitosAnterior;
	
	public BigDecimal getNumerodigitos() {
		return numerodigitos;
	}
	public void setNumerodigitos(BigDecimal numerodigitos) {
		this.numerodigitos = numerodigitos;
	}
	public BigDecimal getNumerodigitosHijos() {
		return numerodigitosHijos;
	}
	public void setNumerodigitosHijos(BigDecimal numerodigitosHijos) {
		this.numerodigitosHijos = numerodigitosHijos;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public BigDecimal getNumerodigitosAnterior() {
		return numerodigitosAnterior;
	}
	public void setNumerodigitosAnterior(BigDecimal numerodigitosAnterior) {
		this.numerodigitosAnterior = numerodigitosAnterior;
	}	
	
}
