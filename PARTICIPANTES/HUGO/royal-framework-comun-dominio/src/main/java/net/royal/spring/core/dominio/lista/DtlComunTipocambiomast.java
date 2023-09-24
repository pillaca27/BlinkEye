package net.royal.spring.core.dominio.lista;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

/**
 * 
 * 
 * @tabla dbo.TipoCambioMast
*/
public class DtlComunTipocambiomast extends DominioTransaccion implements java.io.Serializable{


	private String monedacodigo;
	private String monedacambiocodigo;
	private java.util.Date fechacambio;
	private String fechacambiostring;
	private java.math.BigDecimal factor;
	private java.math.BigDecimal factorcompra;
	private java.math.BigDecimal factorventa;
	private java.math.BigDecimal factorpromedio;
	private java.math.BigDecimal factorcompraafp;
	private java.math.BigDecimal factorventaafp;
	private java.math.BigDecimal factorcomprasbs;
	private java.math.BigDecimal factorventasbs;
	private java.math.BigDecimal valorcuota;
	private String estado;
	private java.util.Date ultimafechamodif;
	private String ultimousuario;
	private java.math.BigDecimal tasatamex;
	private java.math.BigDecimal tasatamn;
	private java.math.BigDecimal tasaanualtamex;
	private java.math.BigDecimal tasaanualtamn;

	private String factorcompra2;
	private String factorventa2;
	private String factorpromedio2;
	private String factorcomprasbs2;
	private String factorventasbs2;
	private String fechacambiodescripcion;
	private String estadodescripcion;
	
	public String getEstadodescripcion() {
		return estadodescripcion;
	}
	public void setEstadodescripcion(String estadodescripcion) {
		this.estadodescripcion = estadodescripcion;
	}
	
	public String getFechacambiodescripcion() {
		return fechacambiodescripcion;
	}
	public void setFechacambiodescripcion(String fechacambiodescripcion) {
		this.fechacambiodescripcion = fechacambiodescripcion;
	}
	public String getFactorcompra2() {
		return factorcompra2;
	}

	public void setFactorcompra2(String factorcompra2) {
		this.factorcompra2 = factorcompra2;
	}

	public String getFactorventa2() {
		return factorventa2;
	}

	public void setFactorventa2(String factorventa2) {
		this.factorventa2 = factorventa2;
	}

	public String getFactorpromedio2() {
		return factorpromedio2;
	}

	public void setFactorpromedio2(String factorpromedio2) {
		this.factorpromedio2 = factorpromedio2;
	}

	public String getFactorcomprasbs2() {
		return factorcomprasbs2;
	}

	public void setFactorcomprasbs2(String factorcomprasbs2) {
		this.factorcomprasbs2 = factorcomprasbs2;
	}

	public String getFactorventasbs2() {
		return factorventasbs2;
	}

	public void setFactorventasbs2(String factorventasbs2) {
		this.factorventasbs2 = factorventasbs2;
	}
	/**
	 * 
	 * 
	 * @campo MonedaCodigo
	*/
	public String getMonedacodigo() {
		return monedacodigo;
	}

	/**
	 * 
	 * 
	 * @campo MonedaCodigo
	*/
	public void setMonedacodigo(String monedacodigo) {
		this.monedacodigo = monedacodigo;
	}
	/**
	 * 
	 * 
	 * @campo MonedaCambioCodigo
	*/
	public String getMonedacambiocodigo() {
		return monedacambiocodigo;
	}

	/**
	 * 
	 * 
	 * @campo MonedaCambioCodigo
	*/
	public void setMonedacambiocodigo(String monedacambiocodigo) {
		this.monedacambiocodigo = monedacambiocodigo;
	}
	/**
	 * 
	 * 
	 * @campo FechaCambio
	*/
	public java.util.Date getFechacambio() {
		return fechacambio;
	}

	/**
	 * 
	 * 
	 * @campo FechaCambio
	*/
	public void setFechacambio(java.util.Date fechacambio) {
		this.fechacambio = fechacambio;
	}
	/**
	 * 
	 * 
	 * @campo FechaCambioString
	*/
	public String getFechacambiostring() {
		return fechacambiostring;
	}

	/**
	 * 
	 * 
	 * @campo FechaCambioString
	*/
	public void setFechacambiostring(String fechacambiostring) {
		this.fechacambiostring = fechacambiostring;
	}
	/**
	 * 
	 * 
	 * @campo Factor
	*/
	public java.math.BigDecimal getFactor() {
		return factor;
	}

	/**
	 * 
	 * 
	 * @campo Factor
	*/
	public void setFactor(java.math.BigDecimal factor) {
		this.factor = factor;
	}
	/**
	 * 
	 * 
	 * @campo FactorCompra
	*/
	public java.math.BigDecimal getFactorcompra() {
		return factorcompra;
	}

	/**
	 * 
	 * 
	 * @campo FactorCompra
	*/
	public void setFactorcompra(java.math.BigDecimal factorcompra) {
		this.factorcompra = factorcompra;
	}
	/**
	 * 
	 * 
	 * @campo FactorVenta
	*/
	public java.math.BigDecimal getFactorventa() {
		return factorventa;
	}

	/**
	 * 
	 * 
	 * @campo FactorVenta
	*/
	public void setFactorventa(java.math.BigDecimal factorventa) {
		this.factorventa = factorventa;
	}
	/**
	 * 
	 * 
	 * @campo FactorPromedio
	*/
	public java.math.BigDecimal getFactorpromedio() {
		return factorpromedio;
	}

	/**
	 * 
	 * 
	 * @campo FactorPromedio
	*/
	public void setFactorpromedio(java.math.BigDecimal factorpromedio) {
		this.factorpromedio = factorpromedio;
	}
	/**
	 * 
	 * 
	 * @campo FactorCompraAfp
	*/
	public java.math.BigDecimal getFactorcompraafp() {
		return factorcompraafp;
	}

	/**
	 * 
	 * 
	 * @campo FactorCompraAfp
	*/
	public void setFactorcompraafp(java.math.BigDecimal factorcompraafp) {
		this.factorcompraafp = factorcompraafp;
	}
	/**
	 * 
	 * 
	 * @campo FactorVentaAfp
	*/
	public java.math.BigDecimal getFactorventaafp() {
		return factorventaafp;
	}

	/**
	 * 
	 * 
	 * @campo FactorVentaAfp
	*/
	public void setFactorventaafp(java.math.BigDecimal factorventaafp) {
		this.factorventaafp = factorventaafp;
	}
	/**
	 * 
	 * 
	 * @campo FactorCompraSBS
	*/
	public java.math.BigDecimal getFactorcomprasbs() {
		return factorcomprasbs;
	}

	/**
	 * 
	 * 
	 * @campo FactorCompraSBS
	*/
	public void setFactorcomprasbs(java.math.BigDecimal factorcomprasbs) {
		this.factorcomprasbs = factorcomprasbs;
	}
	/**
	 * 
	 * 
	 * @campo FactorVentaSBS
	*/
	public java.math.BigDecimal getFactorventasbs() {
		return factorventasbs;
	}

	/**
	 * 
	 * 
	 * @campo FactorVentaSBS
	*/
	public void setFactorventasbs(java.math.BigDecimal factorventasbs) {
		this.factorventasbs = factorventasbs;
	}
	/**
	 * 
	 * 
	 * @campo ValorCuota
	*/
	public java.math.BigDecimal getValorcuota() {
		return valorcuota;
	}

	/**
	 * 
	 * 
	 * @campo ValorCuota
	*/
	public void setValorcuota(java.math.BigDecimal valorcuota) {
		this.valorcuota = valorcuota;
	}
	/**
	 * 
	 * 
	 * @campo Estado
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * 
	 * 
	 * @campo Estado
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * 
	 * 
	 * @campo UltimaFechaModif
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * 
	 * 
	 * @campo UltimaFechaModif
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	/**
	 * 
	 * 
	 * @campo UltimoUsuario
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * 
	 * 
	 * @campo UltimoUsuario
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	/**
	 * 
	 * 
	 * @campo TasaTamex
	*/
	public java.math.BigDecimal getTasatamex() {
		return tasatamex;
	}

	/**
	 * 
	 * 
	 * @campo TasaTamex
	*/
	public void setTasatamex(java.math.BigDecimal tasatamex) {
		this.tasatamex = tasatamex;
	}
	/**
	 * 
	 * 
	 * @campo TasaTamn
	*/
	public java.math.BigDecimal getTasatamn() {
		return tasatamn;
	}

	/**
	 * 
	 * 
	 * @campo TasaTamn
	*/
	public void setTasatamn(java.math.BigDecimal tasatamn) {
		this.tasatamn = tasatamn;
	}
	/**
	 * 
	 * 
	 * @campo TasaAnualTAMEX
	*/
	public java.math.BigDecimal getTasaanualtamex() {
		return tasaanualtamex;
	}

	/**
	 * 
	 * 
	 * @campo TasaAnualTAMEX
	*/
	public void setTasaanualtamex(java.math.BigDecimal tasaanualtamex) {
		this.tasaanualtamex = tasaanualtamex;
	}
	/**
	 * 
	 * 
	 * @campo TasaAnualTAMN
	*/
	public java.math.BigDecimal getTasaanualtamn() {
		return tasaanualtamn;
	}

	/**
	 * 
	 * 
	 * @campo TasaAnualTAMN
	*/
	public void setTasaanualtamn(java.math.BigDecimal tasaanualtamn) {
		this.tasaanualtamn = tasaanualtamn;
	}

}
