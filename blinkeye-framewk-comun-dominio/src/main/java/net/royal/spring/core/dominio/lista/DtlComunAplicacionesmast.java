package net.royal.spring.core.dominio.lista;

import net.royal.spring.core.dominio.BeanAplicacionesmast;

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
public class DtlComunAplicacionesmast extends DominioTransaccion implements java.io.Serializable{


	private String aplicacioncodigo;
	private String descripcioncorta;
	private String descripcionlarga;
	private String ultimoperiodocontable;
	private String sistemafuente;
	private String estadisponible;
	private String departamentorevisor;
	private String ultimoperiodoprocesado;
	private String aplicacionusuario;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String aplicacionusuario02;
	private String aplicacionusuario03;
	private String aplicacionusuario04;
	private Integer orden;
	private String estadodescripcion;
	private String uuid;	
	

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getEstadodescripcion() {
		return estadodescripcion;
	}

	public void setEstadodescripcion(String estadodescripcion) {
		this.estadodescripcion = estadodescripcion;
	}

	/**
	 * 
	 * 
	 * @campo AplicacionCodigo
	*/
	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	/**
	 * 
	 * 
	 * @campo AplicacionCodigo
	*/
	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
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

}
