package net.royal.spring.planilla.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunPrProcesoperiodo extends DominioTransaccion {
	// pk
	private String companiasocio;
	private String periodo;
	private String tipoplanilla;
	private String tipoproceso;
	
	// campos
	private String flagprocesado;
	private String flagperiodomensual;
	private BigDecimal generador;
	private java.util.Date fechadesde;
	private java.util.Date fechahasta;
	private java.util.Date fechaproceso;
	private java.util.Date fechapago;
	private BigDecimal diasdelmesanterior;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String flagaprobado;
	private java.util.Date fechaaprobacion;
	private BigDecimal aprobadopor;
	private String nrovoucher;
	private String flagpresupuesto;
	
	public DtoComunPrProcesoperiodo() {}

	public DtoComunPrProcesoperiodo(String pcompaniasocio, String pperiodo, String ptipoplanilla, String ptipoproceso) {
		this.companiasocio = pcompaniasocio;
		this.periodo = pperiodo;
		this.tipoplanilla = ptipoplanilla;
		this.tipoproceso = ptipoproceso;
	}

	public String getCompaniasocio() {
		return companiasocio;
	}

	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getTipoplanilla() {
		return tipoplanilla;
	}

	public void setTipoplanilla(String tipoplanilla) {
		this.tipoplanilla = tipoplanilla;
	}

	public String getTipoproceso() {
		return tipoproceso;
	}

	public void setTipoproceso(String tipoproceso) {
		this.tipoproceso = tipoproceso;
	}

	public String getFlagprocesado() {
		return flagprocesado;
	}

	public void setFlagprocesado(String flagprocesado) {
		this.flagprocesado = flagprocesado;
	}

	public String getFlagperiodomensual() {
		return flagperiodomensual;
	}

	public void setFlagperiodomensual(String flagperiodomensual) {
		this.flagperiodomensual = flagperiodomensual;
	}

	public BigDecimal getGenerador() {
		return generador;
	}

	public void setGenerador(BigDecimal generador) {
		this.generador = generador;
	}

	public java.util.Date getFechadesde() {
		return fechadesde;
	}

	public void setFechadesde(java.util.Date fechadesde) {
		this.fechadesde = fechadesde;
	}

	public java.util.Date getFechahasta() {
		return fechahasta;
	}

	public void setFechahasta(java.util.Date fechahasta) {
		this.fechahasta = fechahasta;
	}

	public java.util.Date getFechaproceso() {
		return fechaproceso;
	}

	public void setFechaproceso(java.util.Date fechaproceso) {
		this.fechaproceso = fechaproceso;
	}

	public java.util.Date getFechapago() {
		return fechapago;
	}

	public void setFechapago(java.util.Date fechapago) {
		this.fechapago = fechapago;
	}

	public BigDecimal getDiasdelmesanterior() {
		return diasdelmesanterior;
	}

	public void setDiasdelmesanterior(BigDecimal diasdelmesanterior) {
		this.diasdelmesanterior = diasdelmesanterior;
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

	public String getFlagaprobado() {
		return flagaprobado;
	}

	public void setFlagaprobado(String flagaprobado) {
		this.flagaprobado = flagaprobado;
	}

	public java.util.Date getFechaaprobacion() {
		return fechaaprobacion;
	}

	public void setFechaaprobacion(java.util.Date fechaaprobacion) {
		this.fechaaprobacion = fechaaprobacion;
	}

	public BigDecimal getAprobadopor() {
		return aprobadopor;
	}

	public void setAprobadopor(BigDecimal aprobadopor) {
		this.aprobadopor = aprobadopor;
	}

	public String getNrovoucher() {
		return nrovoucher;
	}

	public void setNrovoucher(String nrovoucher) {
		this.nrovoucher = nrovoucher;
	}

	public String getFlagpresupuesto() {
		return flagpresupuesto;
	}

	public void setFlagpresupuesto(String flagpresupuesto) {
		this.flagpresupuesto = flagpresupuesto;
	}

	
}
