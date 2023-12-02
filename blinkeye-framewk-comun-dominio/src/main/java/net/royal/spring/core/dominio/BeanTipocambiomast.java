package net.royal.spring.core.dominio;

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
@Entity
@Table(name = "TIPOCAMBIOMAST")
public class BeanTipocambiomast extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanTipocambiomastPk pk;

	@Size(min = 0, max = 8)
	@Column(name = "FECHACAMBIOSTRING", length = 8, nullable = true)
	private String fechacambiostring;

	@Column(name = "FACTOR", precision = 63,scale =0, nullable = true)
	private java.math.BigDecimal factor;

	@Column(name = "FACTORCOMPRA", nullable = true)
	private java.math.BigDecimal factorcompra;

	@Column(name = "FACTORVENTA", nullable = true)
	private java.math.BigDecimal factorventa;

	@Column(name = "FACTORPROMEDIO", nullable = true)
	private java.math.BigDecimal factorpromedio;

	@Column(name = "FACTORCOMPRAAFP", nullable = true)
	private java.math.BigDecimal factorcompraafp;

	@Column(name = "FACTORVENTAAFP", nullable = true)
	private java.math.BigDecimal factorventaafp;

	@Column(name = "FACTORCOMPRASBS", nullable = true)
	private java.math.BigDecimal factorcomprasbs;

	@Column(name = "FACTORVENTASBS", nullable = true)
	private java.math.BigDecimal factorventasbs;

	@Column(name = "VALORCUOTA", nullable = true)
	private java.math.BigDecimal valorcuota;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 20)
	@Column(name = "ULTIMOUSUARIO", length = 20, nullable = true)
	private String ultimousuario;

	@Column(name = "TASATAMEX", nullable = true)
	private java.math.BigDecimal tasatamex;

	@Column(name = "TASATAMN", nullable = true)
	private java.math.BigDecimal tasatamn;

	@Column(name = "TASAANUALTAMEX", nullable = true)
	private java.math.BigDecimal tasaanualtamex;

	@Column(name = "TASAANUALTAMN", nullable = true)
	private java.math.BigDecimal tasaanualtamn;

	@Size(min = 0, max = 36)
	@Column(name = "UUID", length = 36, nullable = true)
	private String uuid;	

	
	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public BeanTipocambiomast() {
		pk = new BeanTipocambiomastPk();
	}


	public BeanTipocambiomast(BeanTipocambiomastPk pk) {
		this.pk = pk;
	}

	public BeanTipocambiomastPk getPk() {
		return pk;
	}

	public void setPk(BeanTipocambiomastPk pk) {
		this.pk = pk;
	}
	/**
	 * Fecha de Cambio String, se llena automaticamente con la fecha de Cambio en formato texto. (AAAAMMDD)
	 * 
	 * @campo FECHACAMBIOSTRING
	*/
	public String getFechacambiostring() {
		return fechacambiostring;
	}

	/**
	 * Fecha de Cambio String, se llena automaticamente con la fecha de Cambio en formato texto. (AAAAMMDD)
	 * 
	 * @campo FECHACAMBIOSTRING
	*/
	public void setFechacambiostring(String fechacambiostring) {
		this.fechacambiostring = fechacambiostring;
	}
	/**
	 * Parametrizacion otras Compa?ias.
	 * 
	 * @campo FACTOR
	*/
	public java.math.BigDecimal getFactor() {
		return factor;
	}

	/**
	 * Parametrizacion otras Compa?ias.
	 * 
	 * @campo FACTOR
	*/
	public void setFactor(java.math.BigDecimal factor) {
		this.factor = factor;
	}
	/**
	 * Factor de Compra (Precio de Compra)
	 * 
	 * @campo FACTORCOMPRA
	*/
	public java.math.BigDecimal getFactorcompra() {
		return factorcompra;
	}

	/**
	 * Factor de Compra (Precio de Compra)
	 * 
	 * @campo FACTORCOMPRA
	*/
	public void setFactorcompra(java.math.BigDecimal factorcompra) {
		this.factorcompra = factorcompra;
	}
	/**
	 * Factor de Venta (Precio de Venta)
	 * 
	 * @campo FACTORVENTA
	*/
	public java.math.BigDecimal getFactorventa() {
		return factorventa;
	}

	/**
	 * Factor de Venta (Precio de Venta)
	 * 
	 * @campo FACTORVENTA
	*/
	public void setFactorventa(java.math.BigDecimal factorventa) {
		this.factorventa = factorventa;
	}
	/**
	 * Factor Promedio se  calcula automaticamente: (FactorCompra + FactorVenta)/2
	 * 
	 * @campo FACTORPROMEDIO
	*/
	public java.math.BigDecimal getFactorpromedio() {
		return factorpromedio;
	}

	/**
	 * Factor Promedio se  calcula automaticamente: (FactorCompra + FactorVenta)/2
	 * 
	 * @campo FACTORPROMEDIO
	*/
	public void setFactorpromedio(java.math.BigDecimal factorpromedio) {
		this.factorpromedio = factorpromedio;
	}
	/**
	 * Parametrizacion otras Compa?ias
	 * 
	 * @campo FACTORCOMPRAAFP
	*/
	public java.math.BigDecimal getFactorcompraafp() {
		return factorcompraafp;
	}

	/**
	 * Parametrizacion otras Compa?ias
	 * 
	 * @campo FACTORCOMPRAAFP
	*/
	public void setFactorcompraafp(java.math.BigDecimal factorcompraafp) {
		this.factorcompraafp = factorcompraafp;
	}
	/**
	 * Parametrizacion otras Compa?ias
	 * 
	 * @campo FACTORVENTAAFP
	*/
	public java.math.BigDecimal getFactorventaafp() {
		return factorventaafp;
	}

	/**
	 * Parametrizacion otras Compa?ias
	 * 
	 * @campo FACTORVENTAAFP
	*/
	public void setFactorventaafp(java.math.BigDecimal factorventaafp) {
		this.factorventaafp = factorventaafp;
	}
	/**
	 * Parametrizacion otras Compa?ias
	 * 
	 * @campo FACTORCOMPRASBS
	*/
	public java.math.BigDecimal getFactorcomprasbs() {
		return factorcomprasbs;
	}

	/**
	 * Parametrizacion otras Compa?ias
	 * 
	 * @campo FACTORCOMPRASBS
	*/
	public void setFactorcomprasbs(java.math.BigDecimal factorcomprasbs) {
		this.factorcomprasbs = factorcomprasbs;
	}
	/**
	 * Parametrizacion otras Compa?ias
	 * 
	 * @campo FACTORVENTASBS
	*/
	public java.math.BigDecimal getFactorventasbs() {
		return factorventasbs;
	}

	/**
	 * Parametrizacion otras Compa?ias
	 * 
	 * @campo FACTORVENTASBS
	*/
	public void setFactorventasbs(java.math.BigDecimal factorventasbs) {
		this.factorventasbs = factorventasbs;
	}
	/**
	 * Parametrizacion otras Compa?ias
	 * 
	 * @campo VALORCUOTA
	*/
	public java.math.BigDecimal getValorcuota() {
		return valorcuota;
	}

	/**
	 * Parametrizacion otras Compa?ias
	 * 
	 * @campo VALORCUOTA
	*/
	public void setValorcuota(java.math.BigDecimal valorcuota) {
		this.valorcuota = valorcuota;
	}
	/**
	 * A = Activo, I = Inactivo
	 * 
	 * @campo ESTADO
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * A = Activo, I = Inactivo
	 * 
	 * @campo ESTADO
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * Es la fecha de la ultima modificacion realizada a este registro
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * Es la fecha de la ultima modificacion realizada a este registro
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	/**
	 * Es el Codigo del ultimo usuario que realizo modificaciones a este registro
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * Es el Codigo del ultimo usuario que realizo modificaciones a este registro
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	/**
	 * Parametrizacion otras Compa?ias
	 * 
	 * @campo TASATAMEX
	*/
	public java.math.BigDecimal getTasatamex() {
		return tasatamex;
	}

	/**
	 * Parametrizacion otras Compa?ias
	 * 
	 * @campo TASATAMEX
	*/
	public void setTasatamex(java.math.BigDecimal tasatamex) {
		this.tasatamex = tasatamex;
	}
	/**
	 * Parametrizacion otras Compa?ias
	 * 
	 * @campo TASATAMN
	*/
	public java.math.BigDecimal getTasatamn() {
		return tasatamn;
	}

	/**
	 * Parametrizacion otras Compa?ias
	 * 
	 * @campo TASATAMN
	*/
	public void setTasatamn(java.math.BigDecimal tasatamn) {
		this.tasatamn = tasatamn;
	}
	/**
	 * Parametrizacion otras Compa?ias
	 * 
	 * @campo TASAANUALTAMEX
	*/
	public java.math.BigDecimal getTasaanualtamex() {
		return tasaanualtamex;
	}

	/**
	 * Parametrizacion otras Compa?ias
	 * 
	 * @campo TASAANUALTAMEX
	*/
	public void setTasaanualtamex(java.math.BigDecimal tasaanualtamex) {
		this.tasaanualtamex = tasaanualtamex;
	}
	/**
	 * Parametrizacion otras Compa?ias
	 * 
	 * @campo TASAANUALTAMN
	*/
	public java.math.BigDecimal getTasaanualtamn() {
		return tasaanualtamn;
	}

	/**
	 * Parametrizacion otras Compa?ias
	 * 
	 * @campo TASAANUALTAMN
	*/
	public void setTasaanualtamn(java.math.BigDecimal tasaanualtamn) {
		this.tasaanualtamn = tasaanualtamn;
	}


}
