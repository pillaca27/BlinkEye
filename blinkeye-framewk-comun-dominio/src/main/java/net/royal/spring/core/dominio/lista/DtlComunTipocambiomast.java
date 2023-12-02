package net.royal.spring.core.dominio.lista;

import net.royal.spring.core.dominio.BeanTipocambiomast;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * Tipo de cambio diario
 * 
 * @tabla TIPOCAMBIOMAST
*/
public class DtlComunTipocambiomast implements java.io.Serializable{

	private String monedacodigo;
	private String monedacambiocodigo;
	private java.util.Date fechacambio;
	private java.math.BigDecimal factor;
	private java.math.BigDecimal factorcompra;
	private java.math.BigDecimal factorventa;	
	private String estado;	
	private java.math.BigDecimal tasatamex;
	private java.math.BigDecimal tasatamn;	
	
	private String accion;
	private String uuid;
	
	private BigDecimal ROWNUM_;
	
	
 
	private String fechacambiostring;
	private java.math.BigDecimal factorpromedio;
	private java.math.BigDecimal factorcompraafp;
	private java.math.BigDecimal factorventaafp;
	private java.math.BigDecimal factorcomprasbs;
	private java.math.BigDecimal factorventasbs;
	private java.math.BigDecimal valorcuota;
	private java.util.Date ultimafechamodif;
	private String ultimousuario;
	private java.math.BigDecimal tasaanualtamex;
	private java.math.BigDecimal tasaanualtamn;
	private String factorcompra2;
	private String factorventa2;
	private String factorpromedio2;
	private String factorcomprasbs2;
	private String factorventasbs2;
	private String fechacambiodescripcion;
	private String estadodescripcion;

	
	
	public String getFechacambiostring() {
		return fechacambiostring;
	}

	public void setFechacambiostring(String fechacambiostring) {
		this.fechacambiostring = fechacambiostring;
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

	public String getFechacambiodescripcion() {
		return fechacambiodescripcion;
	}

	public void setFechacambiodescripcion(String fechacambiodescripcion) {
		this.fechacambiodescripcion = fechacambiodescripcion;
	}

	public String getEstadodescripcion() {
		return estadodescripcion;
	}

	public void setEstadodescripcion(String estadodescripcion) {
		this.estadodescripcion = estadodescripcion;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public java.util.Date getFechacambio() {
		return fechacambio;
	}

	public void setFechacambio(java.util.Date fechacambio) {
		this.fechacambio = fechacambio;
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
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
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

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

}
