package net.royal.spring.core.dominio.lista;

import net.royal.spring.core.dominio.BeanMaPersonacuentabancaria;

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
 * 
 * 
 * @tabla dbo.MA_PersonaCuentaBancaria
*/
public class DtlComunMaPersonacuentabancaria extends DominioTransaccion implements java.io.Serializable{


	private Integer persona;
	private Integer secuencia;
	private String monedacodigo;
	private String tipocuenta;
	private String bancocodigo;
	private String cuentabancarianumero;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String diferidomodalidad;

	/**
	 * 
	 * 
	 * @campo Persona
	*/
	public Integer getPersona() {
		return persona;
	}

	/**
	 * 
	 * 
	 * @campo Persona
	*/
	public void setPersona(Integer persona) {
		this.persona = persona;
	}
	/**
	 * 
	 * 
	 * @campo Secuencia
	*/
	public Integer getSecuencia() {
		return secuencia;
	}

	/**
	 * 
	 * 
	 * @campo Secuencia
	*/
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
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
	 * @campo TipoCuenta
	*/
	public String getTipocuenta() {
		return tipocuenta;
	}

	/**
	 * 
	 * 
	 * @campo TipoCuenta
	*/
	public void setTipocuenta(String tipocuenta) {
		this.tipocuenta = tipocuenta;
	}
	/**
	 * 
	 * 
	 * @campo BancoCodigo
	*/
	public String getBancocodigo() {
		return bancocodigo;
	}

	/**
	 * 
	 * 
	 * @campo BancoCodigo
	*/
	public void setBancocodigo(String bancocodigo) {
		this.bancocodigo = bancocodigo;
	}
	/**
	 * 
	 * 
	 * @campo CuentaBancariaNumero
	*/
	public String getCuentabancarianumero() {
		return cuentabancarianumero;
	}

	/**
	 * 
	 * 
	 * @campo CuentaBancariaNumero
	*/
	public void setCuentabancarianumero(String cuentabancarianumero) {
		this.cuentabancarianumero = cuentabancarianumero;
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
	 * @campo DiferidoModalidad
	*/
	public String getDiferidomodalidad() {
		return diferidomodalidad;
	}

	/**
	 * 
	 * 
	 * @campo DiferidoModalidad
	*/
	public void setDiferidomodalidad(String diferidomodalidad) {
		this.diferidomodalidad = diferidomodalidad;
	}

}
