package net.royal.spring.planilla.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunPrPlanillaEmpleado extends DominioTransaccion {
	private String tipoproceso;
	private String companiasocio;
	private String tipoplanilla;
	private String sucursal;
	public String getTipoproceso() {
		return tipoproceso;
	}
	public void setTipoproceso(String tipoproceso) {
		this.tipoproceso = tipoproceso;
	}
	public String getCompaniasocio() {
		return companiasocio;
	}
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}
	public String getTipoplanilla() {
		return tipoplanilla;
	}
	public void setTipoplanilla(String tipoplanilla) {
		this.tipoplanilla = tipoplanilla;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
		
}
