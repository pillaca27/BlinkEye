package net.royal.spring.framework.modelo;

import java.math.BigDecimal;
import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class TipoCambioSunatTransaccion extends DominioTransaccion{
	private BigDecimal compra;
	private BigDecimal venta;
	
	public BigDecimal getCompra() {
		return compra;
	}
	public void setCompra(BigDecimal compra) {
		this.compra = compra;
	}
	public BigDecimal getVenta() {
		return venta;
	}
	public void setVenta(BigDecimal venta) {
		this.venta = venta;
	}	
}
