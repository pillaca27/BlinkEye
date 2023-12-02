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
public class BeanTipocambiomastPk implements java.io.Serializable{



	@Size(min = 0, max = 2)
	@NotNull
	@NotEmpty
	@Column(name = "MONEDACODIGO", length = 2, nullable = false)
	private String monedacodigo;

	@Size(min = 0, max = 2)
	@NotNull
	@NotEmpty
	@Column(name = "MONEDACAMBIOCODIGO", length = 2, nullable = false)
	private String monedacambiocodigo;

	@NotNull
	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHACAMBIO", nullable = false)
	private java.util.Date fechacambio;


	public BeanTipocambiomastPk() {
	}

	public BeanTipocambiomastPk(String pmonedacodigo,String pmonedacambiocodigo,java.util.Date pfechacambio) {
this.monedacodigo = pmonedacodigo;
		this.monedacambiocodigo = pmonedacambiocodigo;
		this.fechacambio = pfechacambio;
	}

	/**
	 * TIPOCAMBIOMAST
	 * 
	 * @campo MONEDACODIGO
	*/
	public String getMonedacodigo() {
		return monedacodigo;
	}

	/**
	 * TIPOCAMBIOMAST
	 * 
	 * @campo MONEDACODIGO
	*/
	public void setMonedacodigo(String monedacodigo) {
		this.monedacodigo = monedacodigo;
	}
	/**
	 * Codigo Moneda de Cambio
	 * 
	 * @campo MONEDACAMBIOCODIGO
	*/
	public String getMonedacambiocodigo() {
		return monedacambiocodigo;
	}

	/**
	 * Codigo Moneda de Cambio
	 * 
	 * @campo MONEDACAMBIOCODIGO
	*/
	public void setMonedacambiocodigo(String monedacambiocodigo) {
		this.monedacambiocodigo = monedacambiocodigo;
	}
	/**
	 * Fecha de Cambio (Sin Hora <00:00:00>)
	 * 
	 * @campo FECHACAMBIO
	*/
	public java.util.Date getFechacambio() {
		return fechacambio;
	}

	/**
	 * Fecha de Cambio (Sin Hora <00:00:00>)
	 * 
	 * @campo FECHACAMBIO
	*/
	public void setFechacambio(java.util.Date fechacambio) {
		this.fechacambio = fechacambio;
	}

}
