package net.royal.spring.logistica.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunWhItemalmacenlote extends DominioTransaccion{
	private String item;
	private String condicion;
	private String almacencodigo;
	private String lote;
	private BigDecimal donante;
	private java.util.Date fechaingreso;
	private java.util.Date fechavencimiento;
	private BigDecimal stockingreso;
	private BigDecimal stockactual;
	private BigDecimal stockcomprometido;
	private BigDecimal stockactualdoble;
	private BigDecimal preciounitario;
	private BigDecimal prioridad;
	private BigDecimal preciopromediolocal;
	private BigDecimal preciopromediodolares;
	private BigDecimal preciopromediocantidad;
	private String documentoreferencia;
	private String loteequivalente;
	private BigDecimal ingresadopor;
	private BigDecimal preciounitariodolares;
	
	@JsonIgnore
	private BigDecimal ROWNUM_;

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public String getAlmacencodigo() {
		return almacencodigo;
	}

	public void setAlmacencodigo(String almacencodigo) {
		this.almacencodigo = almacencodigo;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public BigDecimal getDonante() {
		return donante;
	}

	public void setDonante(BigDecimal donante) {
		this.donante = donante;
	}

	public java.util.Date getFechaingreso() {
		return fechaingreso;
	}

	public void setFechaingreso(java.util.Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}

	public java.util.Date getFechavencimiento() {
		return fechavencimiento;
	}

	public void setFechavencimiento(java.util.Date fechavencimiento) {
		this.fechavencimiento = fechavencimiento;
	}

	public BigDecimal getStockingreso() {
		return stockingreso;
	}

	public void setStockingreso(BigDecimal stockingreso) {
		this.stockingreso = stockingreso;
	}

	public BigDecimal getStockactual() {
		return stockactual;
	}

	public void setStockactual(BigDecimal stockactual) {
		this.stockactual = stockactual;
	}

	public BigDecimal getStockcomprometido() {
		return stockcomprometido;
	}

	public void setStockcomprometido(BigDecimal stockcomprometido) {
		this.stockcomprometido = stockcomprometido;
	}

	public BigDecimal getStockactualdoble() {
		return stockactualdoble;
	}

	public void setStockactualdoble(BigDecimal stockactualdoble) {
		this.stockactualdoble = stockactualdoble;
	}

	public BigDecimal getPreciounitario() {
		return preciounitario;
	}

	public void setPreciounitario(BigDecimal preciounitario) {
		this.preciounitario = preciounitario;
	}

	public BigDecimal getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(BigDecimal prioridad) {
		this.prioridad = prioridad;
	}

	public BigDecimal getPreciopromediolocal() {
		return preciopromediolocal;
	}

	public void setPreciopromediolocal(BigDecimal preciopromediolocal) {
		this.preciopromediolocal = preciopromediolocal;
	}

	public BigDecimal getPreciopromediodolares() {
		return preciopromediodolares;
	}

	public void setPreciopromediodolares(BigDecimal preciopromediodolares) {
		this.preciopromediodolares = preciopromediodolares;
	}

	public BigDecimal getPreciopromediocantidad() {
		return preciopromediocantidad;
	}

	public void setPreciopromediocantidad(BigDecimal preciopromediocantidad) {
		this.preciopromediocantidad = preciopromediocantidad;
	}

	public String getDocumentoreferencia() {
		return documentoreferencia;
	}

	public void setDocumentoreferencia(String documentoreferencia) {
		this.documentoreferencia = documentoreferencia;
	}

	public String getLoteequivalente() {
		return loteequivalente;
	}

	public void setLoteequivalente(String loteequivalente) {
		this.loteequivalente = loteequivalente;
	}

	public BigDecimal getIngresadopor() {
		return ingresadopor;
	}

	public void setIngresadopor(BigDecimal ingresadopor) {
		this.ingresadopor = ingresadopor;
	}

	public BigDecimal getPreciounitariodolares() {
		return preciounitariodolares;
	}

	public void setPreciounitariodolares(BigDecimal preciounitariodolares) {
		this.preciounitariodolares = preciounitariodolares;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	
	
}
