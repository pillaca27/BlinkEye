package net.royal.spring.logistica.dominio.dto;

import java.math.BigDecimal;

public class DtoComunWhImportacionSelectBl implements java.io.Serializable {
	private String companiasocio;
	private String blnumero;
	private String seleccion;
	private String transportedocumento;
	private String exportadordocumento;
	private String clasestilo;
	private BigDecimal monto01;
	private BigDecimal monto02;
	private BigDecimal montototal;
	private BigDecimal montoflete;
	private BigDecimal sumatotal;
	private BigDecimal rowkey;

	public String getCompaniasocio() {
		return companiasocio;
	}

	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}

	public String getBlnumero() {
		return blnumero;
	}

	public void setBlnumero(String blnumero) {
		this.blnumero = blnumero;
	}

	public String getTransportedocumento() {
		return transportedocumento;
	}

	public void setTransportedocumento(String transportedocumento) {
		this.transportedocumento = transportedocumento;
	}

	public String getExportadordocumento() {
		return exportadordocumento;
	}

	public void setExportadordocumento(String exportadordocumento) {
		this.exportadordocumento = exportadordocumento;
	}

	public BigDecimal getMonto01() {
		return monto01;
	}

	public void setMonto01(BigDecimal monto01) {
		this.monto01 = monto01;
	}

	public BigDecimal getMonto02() {
		return monto02;
	}

	public void setMonto02(BigDecimal monto02) {
		this.monto02 = monto02;
	}

	public BigDecimal getMontototal() {
		return montototal;
	}

	public void setMontototal(BigDecimal montototal) {
		this.montototal = montototal;
	}

	public BigDecimal getMontoflete() {
		return montoflete;
	}

	public void setMontoflete(BigDecimal montoflete) {
		this.montoflete = montoflete;
	}

	public BigDecimal getRowkey() {
		return rowkey;
	}

	public void setRowkey(BigDecimal rowkey) {
		this.rowkey = rowkey;
	}

	public BigDecimal getSumatotal() {
		return sumatotal;
	}

	public void setSumatotal(BigDecimal sumatotal) {
		this.sumatotal = sumatotal;
	}

	public String getClasestilo() {
		return clasestilo;
	}

	public void setClasestilo(String clasestilo) {
		this.clasestilo = clasestilo;
	}

	public String getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(String seleccion) {
		this.seleccion = seleccion;
	}


}
