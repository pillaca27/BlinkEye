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
 * @tabla dbo.AplicacionesMast
*/
@Entity
@Table(name = "APLICACIONESMAST")
public class BeanAplicacionesmast extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanAplicacionesmastPk pk;

	@Size(min = 0, max = 20)
	@Column(name = "DESCRIPCIONCORTA", length = 20, nullable = true)
	private String descripcioncorta;

	@Size(min = 0, max = 40)
	@Column(name = "DESCRIPCIONLARGA", length = 40, nullable = true)
	private String descripcionlarga;

	@Size(min = 0, max = 6)
	@Column(name = "ULTIMOPERIODOCONTABLE", length = 6, nullable = true)
	private String ultimoperiodocontable;

	@Size(min = 0, max = 8)
	@Column(name = "SISTEMAFUENTE", length = 8, nullable = true)
	private String sistemafuente;

	@Size(min = 0, max = 10)
	@Column(name = "ESTADISPONIBLE", length = 10, nullable = true)
	private String estadisponible;

	@Size(min = 0, max = 3)
	@Column(name = "DEPARTAMENTOREVISOR", length = 3, nullable = true)
	private String departamentorevisor;

	@Size(min = 0, max = 6)
	@Column(name = "ULTIMOPERIODOPROCESADO", length = 6, nullable = true)
	private String ultimoperiodoprocesado;

	@Size(min = 0, max = 2)
	@Column(name = "APLICACIONUSUARIO", length = 2, nullable = true)
	private String aplicacionusuario;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@Size(min = 0, max = 10)
	@Column(name = "ULTIMOUSUARIO", length = 10, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 2)
	@Column(name = "APLICACIONUSUARIO02", length = 2, nullable = true)
	private String aplicacionusuario02;

	@Size(min = 0, max = 2)
	@Column(name = "APLICACIONUSUARIO03", length = 2, nullable = true)
	private String aplicacionusuario03;

	@Size(min = 0, max = 2)
	@Column(name = "APLICACIONUSUARIO04", length = 2, nullable = true)
	private String aplicacionusuario04;

	@Column(name = "ORDEN", nullable = true)
	private Integer orden;


	@Transient
	private String codigocontablevalid;
	
	public BeanAplicacionesmast() {
		pk = new BeanAplicacionesmastPk();
	}


	public BeanAplicacionesmast(BeanAplicacionesmastPk pk) {
		this.pk = pk;
	}

	public BeanAplicacionesmastPk getPk() {
		return pk;
	}

	public void setPk(BeanAplicacionesmastPk pk) {
		this.pk = pk;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionCorta
	*/
	public String getDescripcioncorta() {
		return descripcioncorta;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionCorta
	*/
	public void setDescripcioncorta(String descripcioncorta) {
		this.descripcioncorta = descripcioncorta;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionLarga
	*/
	public String getDescripcionlarga() {
		return descripcionlarga;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionLarga
	*/
	public void setDescripcionlarga(String descripcionlarga) {
		this.descripcionlarga = descripcionlarga;
	}
	/**
	 * 
	 * 
	 * @campo UltimoPeriodoContable
	*/
	public String getUltimoperiodocontable() {
		return ultimoperiodocontable;
	}

	/**
	 * 
	 * 
	 * @campo UltimoPeriodoContable
	*/
	public void setUltimoperiodocontable(String ultimoperiodocontable) {
		this.ultimoperiodocontable = ultimoperiodocontable;
	}
	/**
	 * 
	 * 
	 * @campo SistemaFuente
	*/
	public String getSistemafuente() {
		return sistemafuente;
	}

	/**
	 * 
	 * 
	 * @campo SistemaFuente
	*/
	public void setSistemafuente(String sistemafuente) {
		this.sistemafuente = sistemafuente;
	}
	/**
	 * 
	 * 
	 * @campo EstaDisponible
	*/
	public String getEstadisponible() {
		return estadisponible;
	}

	/**
	 * 
	 * 
	 * @campo EstaDisponible
	*/
	public void setEstadisponible(String estadisponible) {
		this.estadisponible = estadisponible;
	}
	/**
	 * 
	 * 
	 * @campo DepartamentoRevisor
	*/
	public String getDepartamentorevisor() {
		return departamentorevisor;
	}

	/**
	 * 
	 * 
	 * @campo DepartamentoRevisor
	*/
	public void setDepartamentorevisor(String departamentorevisor) {
		this.departamentorevisor = departamentorevisor;
	}
	/**
	 * 
	 * 
	 * @campo UltimoPeriodoProcesado
	*/
	public String getUltimoperiodoprocesado() {
		return ultimoperiodoprocesado;
	}

	/**
	 * 
	 * 
	 * @campo UltimoPeriodoProcesado
	*/
	public void setUltimoperiodoprocesado(String ultimoperiodoprocesado) {
		this.ultimoperiodoprocesado = ultimoperiodoprocesado;
	}
	/**
	 * 
	 * 
	 * @campo AplicacionUsuario
	*/
	public String getAplicacionusuario() {
		return aplicacionusuario;
	}

	/**
	 * 
	 * 
	 * @campo AplicacionUsuario
	*/
	public void setAplicacionusuario(String aplicacionusuario) {
		this.aplicacionusuario = aplicacionusuario;
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
	 * @campo AplicacionUsuario02
	*/
	public String getAplicacionusuario02() {
		return aplicacionusuario02;
	}

	/**
	 * 
	 * 
	 * @campo AplicacionUsuario02
	*/
	public void setAplicacionusuario02(String aplicacionusuario02) {
		this.aplicacionusuario02 = aplicacionusuario02;
	}
	/**
	 * 
	 * 
	 * @campo AplicacionUsuario03
	*/
	public String getAplicacionusuario03() {
		return aplicacionusuario03;
	}

	/**
	 * 
	 * 
	 * @campo AplicacionUsuario03
	*/
	public void setAplicacionusuario03(String aplicacionusuario03) {
		this.aplicacionusuario03 = aplicacionusuario03;
	}
	/**
	 * 
	 * 
	 * @campo AplicacionUsuario04
	*/
	public String getAplicacionusuario04() {
		return aplicacionusuario04;
	}

	/**
	 * 
	 * 
	 * @campo AplicacionUsuario04
	*/
	public void setAplicacionusuario04(String aplicacionusuario04) {
		this.aplicacionusuario04 = aplicacionusuario04;
	}
	/**
	 * 
	 * 
	 * @campo ORDEN
	*/
	public Integer getOrden() {
		return orden;
	}

	/**
	 * 
	 * 
	 * @campo ORDEN
	*/
	public void setOrden(Integer orden) {
		this.orden = orden;
	}


	public String getCodigocontablevalid() {
		return codigocontablevalid;
	}


	public void setCodigocontablevalid(String codigocontablevalid) {
		this.codigocontablevalid = codigocontablevalid;
	}

	
}
