package net.royal.spring.core.dominio.dto;

import net.royal.spring.core.dominio.BeanTipocambiomast;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UFechaHora;
import net.royal.spring.framework.util.UString;

/**
 * Tipo de cambio diario
 * 
 * @tabla TIPOCAMBIOMAST
*/
public class DtoComunTipocambiomast extends DominioTransaccion implements java.io.Serializable{
	// pk
	private String monedacodigo;
	private String monedacambiocodigo;
	private java.util.Date fechacambio;
	
	// columna
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
	private java.math.BigDecimal factorcobranzacompra;
	private java.math.BigDecimal factorcobranzaventa;
	private String uuid;	
	// auxiliares
	private String auxRateType;
 
	private String factorcompra2;
	private String factorventa2;
	private String factorpromedio2;
	private String factorcomprasbs2;
	private String factorventasbs2;
		
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
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public DtoComunTipocambiomast() {}
	public DtoComunTipocambiomast(String monedacodigo,String monedacambiocodigo,java.util.Date fechacambio) {
		this.monedacodigo=monedacodigo;
		this.monedacambiocodigo=monedacambiocodigo;
		this.fechacambio=fechacambio;
		if (fechacambio!=null)
			this.fechacambiostring=UFechaHora.formatearFecha(fechacambio, "yyyyMMdd");
	}

	public String getMonedacodigo() {
		return monedacodigo;
	}

	public void setMonedacodigo(String monedacodigo) {
		this.monedacodigo = monedacodigo;
	}
	public String getMonedacambiocodigo() {
		return monedacambiocodigo;
	}

	public void setMonedacambiocodigo(String monedacambiocodigo) {
		this.monedacambiocodigo = monedacambiocodigo;
	}
	public java.util.Date getFechacambio() {
		return fechacambio;
	}

	public void setFechacambio(java.util.Date fechacambio) {
		this.fechacambio = fechacambio;
	}
	public String getFechacambiostring() {
		return fechacambiostring;
	}

	public void setFechacambiostring(String fechacambiostring) {
		this.fechacambiostring = fechacambiostring;
	}
	public java.math.BigDecimal getFactor() {
		return factor;
	}

	public void setFactor(java.math.BigDecimal factor) {
		this.factor = factor;
	}
	public java.math.BigDecimal getFactorcompra() {
		return factorcompra;
	}

	public void setFactorcompra(java.math.BigDecimal factorcompra) {
		this.factorcompra = factorcompra;
	}
	public java.math.BigDecimal getFactorventa() {
		return factorventa;
	}

	public void setFactorventa(java.math.BigDecimal factorventa) {
		this.factorventa = factorventa;
	}
	public java.math.BigDecimal getFactorpromedio() {
		return factorpromedio;
	}

	public void setFactorpromedio(java.math.BigDecimal factorpromedio) {
		this.factorpromedio = factorpromedio;
	}
	public java.math.BigDecimal getFactorcompraafp() {
		return factorcompraafp;
	}

	public void setFactorcompraafp(java.math.BigDecimal factorcompraafp) {
		this.factorcompraafp = factorcompraafp;
	}
	public java.math.BigDecimal getFactorventaafp() {
		return factorventaafp;
	}

	public void setFactorventaafp(java.math.BigDecimal factorventaafp) {
		this.factorventaafp = factorventaafp;
	}
	public java.math.BigDecimal getFactorcomprasbs() {
		return factorcomprasbs;
	}

	public void setFactorcomprasbs(java.math.BigDecimal factorcomprasbs) {
		this.factorcomprasbs = factorcomprasbs;
	}
	public java.math.BigDecimal getFactorventasbs() {
		return factorventasbs;
	}

	public void setFactorventasbs(java.math.BigDecimal factorventasbs) {
		this.factorventasbs = factorventasbs;
	}
	public java.math.BigDecimal getValorcuota() {
		return valorcuota;
	}

	public void setValorcuota(java.math.BigDecimal valorcuota) {
		this.valorcuota = valorcuota;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	public String getUltimousuario() {
		return ultimousuario;
	}

	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	public java.math.BigDecimal getTasatamex() {
		return tasatamex;
	}

	public void setTasatamex(java.math.BigDecimal tasatamex) {
		this.tasatamex = tasatamex;
	}
	public java.math.BigDecimal getTasatamn() {
		return tasatamn;
	}

	public void setTasatamn(java.math.BigDecimal tasatamn) {
		this.tasatamn = tasatamn;
	}
	public java.math.BigDecimal getTasaanualtamex() {
		return tasaanualtamex;
	}

	public void setTasaanualtamex(java.math.BigDecimal tasaanualtamex) {
		this.tasaanualtamex = tasaanualtamex;
	}
	public java.math.BigDecimal getTasaanualtamn() {
		return tasaanualtamn;
	}

	public void setTasaanualtamn(java.math.BigDecimal tasaanualtamn) {
		this.tasaanualtamn = tasaanualtamn;
	}
	public java.math.BigDecimal getFactorcobranzacompra() {
		return factorcobranzacompra;
	}

	public void setFactorcobranzacompra(java.math.BigDecimal factorcobranzacompra) {
		this.factorcobranzacompra = factorcobranzacompra;
	}
	public java.math.BigDecimal getFactorcobranzaventa() {
		return factorcobranzaventa;
	}

	public void setFactorcobranzaventa(java.math.BigDecimal factorcobranzaventa) {
		this.factorcobranzaventa = factorcobranzaventa;
	}
	
	public String getAuxRateType() {
		return auxRateType;
	}
	public void setAuxRateType(String auxRateType) {
		this.auxRateType = auxRateType;
	}
 
 
	public BeanTipocambiomast obtenerBean() {
		return obtenerBeanCore(new BeanTipocambiomast(),ConstantePantallaAccion.NINGUNO);
	}

	public BeanTipocambiomast obtenerBeanRegistrar() {
		return obtenerBeanCore(new BeanTipocambiomast(),ConstantePantallaAccion.INSERTAR);
	}

	public BeanTipocambiomast obtenerBeanActualizar(BeanTipocambiomast bean) {
		return obtenerBeanCore(bean,ConstantePantallaAccion.ACTUALIZAR);
	}

	private BeanTipocambiomast obtenerBeanCore(BeanTipocambiomast bean,String tipo) {
		if (UString.esNuloVacio(tipo))
			tipo=ConstantePantallaAccion.NINGUNO;

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		switch (tipo) {
		case ConstantePantallaAccion.INSERTAR:
		case ConstantePantallaAccion.NINGUNO:
			bean.getPk().setMonedacodigo(monedacodigo);
			bean.getPk().setMonedacambiocodigo(monedacambiocodigo);
			bean.getPk().setFechacambio(fechacambio);
			bean.setFechacambiostring(fechacambiostring);
			bean.setFactor(factor);
			bean.setFactorcompra( factorcompra);
			bean.setFactorventa(factorventa);
			bean.setFactorpromedio(factorpromedio);
			bean.setFactorcompraafp(factorcompraafp);
			bean.setFactorventaafp(factorventaafp);
			bean.setFactorcomprasbs(factorcomprasbs);
			bean.setFactorventasbs(factorventasbs);
			bean.setValorcuota(valorcuota);
			bean.setEstado(estado);
			bean.setUltimafechamodif(ultimafechamodif);
			bean.setUltimousuario(ultimousuario);
			bean.setTasatamex(tasatamex);
			bean.setTasatamn(tasatamn);
			bean.setTasaanualtamex(tasaanualtamex);
			bean.setTasaanualtamn(tasaanualtamn);
 			bean.setUuid(uuid);
			break;
		case ConstantePantallaAccion.ACTUALIZAR:
			//bean.getPk().setMonedacodigo(monedacodigo);
			//bean.getPk().setMonedacambiocodigo(monedacambiocodigo);
			//bean.getPk().setFechacambio(fechacambio);
			bean.setFechacambiostring(fechacambiostring);
			bean.setFactor(factor);
			bean.setFactorcompra( factorcompra);
			bean.setFactorventa(factorventa);
			bean.setFactorpromedio(factorpromedio);
			bean.setFactorcompraafp(factorcompraafp);
			bean.setFactorventaafp(factorventaafp);
			bean.setFactorcomprasbs(factorcomprasbs);
			bean.setFactorventasbs(factorventasbs);
			bean.setValorcuota(valorcuota);
			bean.setEstado(estado);
			bean.setUltimafechamodif(ultimafechamodif);
			bean.setUltimousuario(ultimousuario);
			bean.setTasatamex(tasatamex);
			bean.setTasatamn(tasatamn);
			bean.setTasaanualtamex(tasaanualtamex);
			bean.setTasaanualtamn(tasaanualtamn);
 			//bean.setUuid(uuid);
			break;
		}

		return bean;
	}

}
