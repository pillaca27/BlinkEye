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
 * 
 * 
 * @tabla dbo.MA_PersonaCuentaBancaria
*/
@Entity
@Table(name = "MA_PERSONACUENTABANCARIA")
public class BeanMaPersonacuentabancaria extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanMaPersonacuentabancariaPk pk;

	@Size(min = 0, max = 2)
	@Column(name = "MONEDACODIGO", length = 2, nullable = true)
	private String monedacodigo;

	@Size(min = 0, max = 1)
	@Column(name = "TIPOCUENTA", length = 1, nullable = true)
	private String tipocuenta;

	@Size(min = 0, max = 3)
	@Column(name = "BANCOCODIGO", length = 3, nullable = true)
	private String bancocodigo;

	@Size(min = 0, max = 20)
	@Column(name = "CUENTABANCARIANUMERO", length = 20, nullable = true)
	private String cuentabancarianumero;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@Size(min = 0, max = 20)
	@Column(name = "ULTIMOUSUARIO", length = 20, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 1)
	@Column(name = "DIFERIDOMODALIDAD", length = 1, nullable = true)
	private String diferidomodalidad;


	public BeanMaPersonacuentabancaria() {
		pk = new BeanMaPersonacuentabancariaPk();
	}


	public BeanMaPersonacuentabancaria(BeanMaPersonacuentabancariaPk pk) {
		this.pk = pk;
	}

	public BeanMaPersonacuentabancariaPk getPk() {
		return pk;
	}

	public void setPk(BeanMaPersonacuentabancariaPk pk) {
		this.pk = pk;
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
