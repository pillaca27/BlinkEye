package net.royal.spring.core.dominio.dto;

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
public class DtoComunMaPersonacuentabancaria extends DominioTransaccion implements java.io.Serializable{


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
	private String acciones;
	
	
	

	public String getAcciones() {
		return acciones;
	}

	public void setAcciones(String acciones) {
		this.acciones = acciones;
	}

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
	public BeanMaPersonacuentabancaria obtenerBean() {
		BeanMaPersonacuentabancaria bean = new BeanMaPersonacuentabancaria();
		return obtenerBean(bean);
	}

	public BeanMaPersonacuentabancaria obtenerBean(BeanMaPersonacuentabancaria bean) {
		if (bean == null)
			bean = new BeanMaPersonacuentabancaria();

		bean.getPk().setPersona(persona);
		bean.getPk().setSecuencia(secuencia);
		bean.setMonedacodigo(monedacodigo);
		bean.setTipocuenta(tipocuenta);
		bean.setBancocodigo(bancocodigo);
		bean.setCuentabancarianumero(cuentabancarianumero);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);
		bean.setDiferidomodalidad(diferidomodalidad);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
