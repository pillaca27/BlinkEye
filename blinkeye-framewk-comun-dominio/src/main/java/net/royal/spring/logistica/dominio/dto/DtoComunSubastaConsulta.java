package net.royal.spring.logistica.dominio.dto;

import java.math.BigDecimal;

public class DtoComunSubastaConsulta {
	private String numeroOrden;
	private Integer proveedor;
	
	private String subastaFlag;
	private BigDecimal subastaMontoTotal;
	private String subastaFlagGanador;
	
	public String getNumeroOrden() {
		return numeroOrden;
	}
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}
	public Integer getProveedor() {
		return proveedor;
	}
	public void setProveedor(Integer proveedor) {
		this.proveedor = proveedor;
	}
	public String getSubastaFlag() {
		return subastaFlag;
	}
	public void setSubastaFlag(String subastaFlag) {
		this.subastaFlag = subastaFlag;
	}
	public BigDecimal getSubastaMontoTotal() {
		return subastaMontoTotal;
	}
	public void setSubastaMontoTotal(BigDecimal subastaMontoTotal) {
		this.subastaMontoTotal = subastaMontoTotal;
	}
	public String getSubastaFlagGanador() {
		return subastaFlagGanador;
	}
	public void setSubastaFlagGanador(String subastaFlagGanador) {
		this.subastaFlagGanador = subastaFlagGanador;
	}
	
	
}
