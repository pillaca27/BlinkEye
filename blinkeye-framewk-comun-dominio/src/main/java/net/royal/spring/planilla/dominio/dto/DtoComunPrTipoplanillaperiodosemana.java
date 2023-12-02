package net.royal.spring.planilla.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunPrTipoplanillaperiodosemana extends DominioTransaccion {
	// pk
	private String tipoplanilla;
	private String periodosemanal;
	private String periodo;
	private String tipoproceso;
	private BigDecimal secuencia;
	
	// columnas
	private java.util.Date fechainicio;
	private java.util.Date fechafin;
	private String flagdescuento;

	private String auxPeriodosemanalCorto;
	
	public DtoComunPrTipoplanillaperiodosemana() {
	}

	public DtoComunPrTipoplanillaperiodosemana(String ptipoplanilla, String pperiodosemanal, String pperiodo,
			String ptipoproceso, BigDecimal psecuencia) {
		this.tipoplanilla = ptipoplanilla;
		this.periodosemanal = pperiodosemanal;
		this.periodo = pperiodo;
		this.tipoproceso = ptipoproceso;
		this.secuencia = psecuencia;
	}

	public String getTipoplanilla() {
		return tipoplanilla;
	}

	public void setTipoplanilla(String tipoplanilla) {
		this.tipoplanilla = tipoplanilla;
	}

	public String getPeriodosemanal() {
		return periodosemanal;
	}

	public void setPeriodosemanal(String periodosemanal) {
		this.periodosemanal = periodosemanal;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getTipoproceso() {
		return tipoproceso;
	}

	public void setTipoproceso(String tipoproceso) {
		this.tipoproceso = tipoproceso;
	}

	public BigDecimal getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(BigDecimal secuencia) {
		this.secuencia = secuencia;
	}

	public java.util.Date getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(java.util.Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	public java.util.Date getFechafin() {
		return fechafin;
	}

	public void setFechafin(java.util.Date fechafin) {
		this.fechafin = fechafin;
	}

	public String getFlagdescuento() {
		return flagdescuento;
	}

	public void setFlagdescuento(String flagdescuento) {
		this.flagdescuento = flagdescuento;
	}

	public String getAuxPeriodosemanalCorto() {
		return auxPeriodosemanalCorto;
	}

	public void setAuxPeriodosemanalCorto(String auxPeriodosemanalCorto) {
		this.auxPeriodosemanalCorto = auxPeriodosemanalCorto;
	}
	
	
}
