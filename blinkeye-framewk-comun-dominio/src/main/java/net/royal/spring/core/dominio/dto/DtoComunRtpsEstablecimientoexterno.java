package net.royal.spring.core.dominio.dto;

import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexterno;

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
 * @tabla dbo.RTPS_EstablecimientoExterno
*/
public class DtoComunRtpsEstablecimientoexterno extends DominioTransaccion implements java.io.Serializable{

	private Integer codigoautomatico;
	private Integer automaticoexterno;
	private String codigo;
	private String denominacion;
	private String indcentroriesgo;
	private java.math.BigDecimal tasa;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String accion = "UPDATE";
	
	

	
	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * 
	 * 
	 * @campo CodigoAutomatico
	*/
	public Integer getCodigoautomatico() {
		return codigoautomatico;
	}

	/**
	 * 
	 * 
	 * @campo CodigoAutomatico
	*/
	public void setCodigoautomatico(Integer codigoautomatico) {
		this.codigoautomatico = codigoautomatico;
	}
	/**
	 * 
	 * 
	 * @campo AutomaticoExterno
	*/
	public Integer getAutomaticoexterno() {
		return automaticoexterno;
	}

	/**
	 * 
	 * 
	 * @campo AutomaticoExterno
	*/
	public void setAutomaticoexterno(Integer automaticoexterno) {
		this.automaticoexterno = automaticoexterno;
	}
	/**
	 * 
	 * 
	 * @campo Codigo
	*/
	public String getCodigo() {
		return codigo;
	}

	/**
	 * 
	 * 
	 * @campo Codigo
	*/
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * 
	 * 
	 * @campo Denominacion
	*/
	public String getDenominacion() {
		return denominacion;
	}

	/**
	 * 
	 * 
	 * @campo Denominacion
	*/
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	/**
	 * 
	 * 
	 * @campo IndCentroRiesgo
	*/
	public String getIndcentroriesgo() {
		return indcentroriesgo;
	}

	/**
	 * 
	 * 
	 * @campo IndCentroRiesgo
	*/
	public void setIndcentroriesgo(String indcentroriesgo) {
		this.indcentroriesgo = indcentroriesgo;
	}
	/**
	 * 
	 * 
	 * @campo Tasa
	*/
	public java.math.BigDecimal getTasa() {
		return tasa;
	}

	/**
	 * 
	 * 
	 * @campo Tasa
	*/
	public void setTasa(java.math.BigDecimal tasa) {
		this.tasa = tasa;
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


	public BeanRtpsEstablecimientoexterno obtenerBean() {
		BeanRtpsEstablecimientoexterno bean = new BeanRtpsEstablecimientoexterno();
		return obtenerBean(bean);
	}

	public BeanRtpsEstablecimientoexterno obtenerBean(BeanRtpsEstablecimientoexterno bean) {
		if (bean == null)
			bean = new BeanRtpsEstablecimientoexterno();

		bean.getPk().setCodigoautomatico(codigoautomatico);
		bean.setAutomaticoexterno(automaticoexterno);
		bean.setCodigo(codigo);
		bean.setDenominacion(denominacion);
		bean.setIndcentroriesgo(indcentroriesgo);
		bean.setTasa(tasa);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
