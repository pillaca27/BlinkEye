package net.royal.spring.legal.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DtoComunJuicio {	
	
	private String juicio;
	private String tipo;
	private Date fechainicio;  
	private Date fechacierre;   
	private String descripcion;
	private String naturaleza;
	private String tipoproceso;
	private String monedademanda;
	private BigDecimal montodemanda;           
	private BigDecimal montofinal;
	private BigDecimal ROWNUM_;
	
	public String getJuicio() {
		return juicio;
	}
	public void setJuicio(String juicio) {
		this.juicio = juicio;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}
	public Date getFechacierre() {
		return fechacierre;
	}
	public void setFechacierre(Date fechacierre) {
		this.fechacierre = fechacierre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNaturaleza() {
		return naturaleza;
	}
	public void setNaturaleza(String naturaleza) {
		this.naturaleza = naturaleza;
	}
	public String getTipoproceso() {
		return tipoproceso;
	}
	public void setTipoproceso(String tipoproceso) {
		this.tipoproceso = tipoproceso;
	}
	public String getMonedademanda() {
		return monedademanda;
	}
	public void setMonedademanda(String monedademanda) {
		this.monedademanda = monedademanda;
	}
	public BigDecimal getMontodemanda() {
		return montodemanda;
	}
	public void setMontodemanda(BigDecimal montodemanda) {
		this.montodemanda = montodemanda;
	}
	public BigDecimal getMontofinal() {
		return montofinal;
	}
	public void setMontofinal(BigDecimal montofinal) {
		this.montofinal = montofinal;
	}
	
	
	
}
