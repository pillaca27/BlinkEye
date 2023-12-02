package net.royal.spring.planilla.dominio.dto;

import java.math.BigDecimal;

public class DtoComunPrPrestamoEmpleado {
	private String tipoprestamo;
	private String tipoprestamo_desc;
	private Integer nroprestamo;
	private Integer estado;
	private String monedadocumento;
	private BigDecimal montoprestamo;
	private BigDecimal montopagado;
	private BigDecimal saldodeuda;
	private String companiasocio;
	public DtoComunPrPrestamoEmpleado() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DtoComunPrPrestamoEmpleado(String tipoprestamo, String tipoprestamo_desc, Integer nroprestamo, Integer estado,
			String monedadocumento, BigDecimal montoprestamo, BigDecimal montopagado, BigDecimal saldodeuda,
			String companiasocio) {
		super();
		this.tipoprestamo = tipoprestamo;
		this.tipoprestamo_desc = tipoprestamo_desc;
		this.nroprestamo = nroprestamo;
		this.estado = estado;
		this.monedadocumento = monedadocumento;
		this.montoprestamo = montoprestamo;
		this.montopagado = montopagado;
		this.saldodeuda = saldodeuda;
		this.companiasocio = companiasocio;
	}
	public String getTipoprestamo() {
		return tipoprestamo;
	}
	public void setTipoprestamo(String tipoprestamo) {
		this.tipoprestamo = tipoprestamo;
	}
	public String getTipoprestamo_desc() {
		return tipoprestamo_desc;
	}
	public void setTipoprestamo_desc(String tipoprestamo_desc) {
		this.tipoprestamo_desc = tipoprestamo_desc;
	}
	public Integer getNroprestamo() {
		return nroprestamo;
	}
	public void setNroprestamo(Integer nroprestamo) {
		this.nroprestamo = nroprestamo;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public String getMonedadocumento() {
		return monedadocumento;
	}
	public void setMonedadocumento(String monedadocumento) {
		this.monedadocumento = monedadocumento;
	}
	public BigDecimal getMontoprestamo() {
		return montoprestamo;
	}
	public void setMontoprestamo(BigDecimal montoprestamo) {
		this.montoprestamo = montoprestamo;
	}
	public BigDecimal getMontopagado() {
		return montopagado;
	}
	public void setMontopagado(BigDecimal montopagado) {
		this.montopagado = montopagado;
	}
	public BigDecimal getSaldodeuda() {
		return saldodeuda;
	}
	public void setSaldodeuda(BigDecimal saldodeuda) {
		this.saldodeuda = saldodeuda;
	}
	public String getCompaniasocio() {
		return companiasocio;
	}
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}

}
