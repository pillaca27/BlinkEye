package net.royal.spring.logistica.dominio.dto;

import java.math.BigDecimal;

public class DtoComunWhImportacionSelect implements java.io.Serializable {
	private String carpetanumero;
	private String polizaimportacion;
	private String busqueda;
	private String carpetanumero_1;
	private String seleccion;
	private BigDecimal proveedor;
	private BigDecimal montototal;
	private BigDecimal monto01;
	private BigDecimal rowkey;

	public String getCarpetanumero() {
		return carpetanumero;
	}

	public void setCarpetanumero(String carpetanumero) {
		this.carpetanumero = carpetanumero;
	}

	public String getPolizaimportacion() {
		return polizaimportacion;
	}

	public void setPolizaimportacion(String polizaimportacion) {
		this.polizaimportacion = polizaimportacion;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public String getCarpetanumero_1() {
		return carpetanumero_1;
	}

	public void setCarpetanumero_1(String carpetanumero_1) {
		this.carpetanumero_1 = carpetanumero_1;
	}

	public BigDecimal getProveedor() {
		return proveedor;
	}

	public void setProveedor(BigDecimal proveedor) {
		this.proveedor = proveedor;
	}

	public BigDecimal getMontototal() {
		return montototal;
	}

	public void setMontototal(BigDecimal montototal) {
		this.montototal = montototal;
	}

	public BigDecimal getMonto01() {
		return monto01;
	}

	public void setMonto01(BigDecimal monto01) {
		this.monto01 = monto01;
	}

	public BigDecimal getRowkey() {
		return rowkey;
	}

	public void setRowkey(BigDecimal rowkey) {
		this.rowkey = rowkey;
	}

	public String getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(String seleccion) {
		this.seleccion = seleccion;
	}

}
