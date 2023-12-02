package net.royal.spring.asistencia.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunAsTipodiamovimiento extends DominioTransaccion {
	private BigDecimal tipodia;
	private String intervaloacceso;
	private BigDecimal secuencia;
	
	private java.util.Date horainicio;
	private java.util.Date horafin;
	private String estado;
	private String flagrestriccion;
	private String conceptoaccesorestringido;
	private String conceptoaccesocomplemento;
	private java.util.Date tiemporestringido;
	private String flagdiaant;
	
	public BigDecimal getTipodia() {
		return tipodia;
	}
	public void setTipodia(BigDecimal tipodia) {
		this.tipodia = tipodia;
	}
	public String getIntervaloacceso() {
		return intervaloacceso;
	}
	public void setIntervaloacceso(String intervaloacceso) {
		this.intervaloacceso = intervaloacceso;
	}
	public BigDecimal getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(BigDecimal secuencia) {
		this.secuencia = secuencia;
	}
	public java.util.Date getHorainicio() {
		return horainicio;
	}
	public void setHorainicio(java.util.Date horainicio) {
		this.horainicio = horainicio;
	}
	public java.util.Date getHorafin() {
		return horafin;
	}
	public void setHorafin(java.util.Date horafin) {
		this.horafin = horafin;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFlagrestriccion() {
		return flagrestriccion;
	}
	public void setFlagrestriccion(String flagrestriccion) {
		this.flagrestriccion = flagrestriccion;
	}
	public String getConceptoaccesorestringido() {
		return conceptoaccesorestringido;
	}
	public void setConceptoaccesorestringido(String conceptoaccesorestringido) {
		this.conceptoaccesorestringido = conceptoaccesorestringido;
	}
	public String getConceptoaccesocomplemento() {
		return conceptoaccesocomplemento;
	}
	public void setConceptoaccesocomplemento(String conceptoaccesocomplemento) {
		this.conceptoaccesocomplemento = conceptoaccesocomplemento;
	}
	public java.util.Date getTiemporestringido() {
		return tiemporestringido;
	}
	public void setTiemporestringido(java.util.Date tiemporestringido) {
		this.tiemporestringido = tiemporestringido;
	}
	public String getFlagdiaant() {
		return flagdiaant;
	}
	public void setFlagdiaant(String flagdiaant) {
		this.flagdiaant = flagdiaant;
	}
	
	
}
