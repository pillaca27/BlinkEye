package net.royal.spring.sy.dominio.dto;

import net.royal.spring.sy.dominio.BeanSyUnidadreplicacion;

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
 * @tabla dbo.SY_UnidadReplicacion
*/
public class DtoComunSyUnidadreplicacion extends DominioTransaccion implements java.io.Serializable{


	private String unidadreplicacion;
	private String descripcionlocal;
	private String descripcionextranjera;
	private Integer rangopersonainicio;
	private Integer rangopersonafin;
	private Integer personaactual;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;

	/**
	 * 
	 * 
	 * @campo UnidadReplicacion
	*/
	public String getUnidadreplicacion() {
		return unidadreplicacion;
	}

	/**
	 * 
	 * 
	 * @campo UnidadReplicacion
	*/
	public void setUnidadreplicacion(String unidadreplicacion) {
		this.unidadreplicacion = unidadreplicacion;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionLocal
	*/
	public String getDescripcionlocal() {
		return descripcionlocal;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionLocal
	*/
	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionExtranjera
	*/
	public String getDescripcionextranjera() {
		return descripcionextranjera;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionExtranjera
	*/
	public void setDescripcionextranjera(String descripcionextranjera) {
		this.descripcionextranjera = descripcionextranjera;
	}
	/**
	 * 
	 * 
	 * @campo RangoPersonaInicio
	*/
	public Integer getRangopersonainicio() {
		return rangopersonainicio;
	}

	/**
	 * 
	 * 
	 * @campo RangoPersonaInicio
	*/
	public void setRangopersonainicio(Integer rangopersonainicio) {
		this.rangopersonainicio = rangopersonainicio;
	}
	/**
	 * 
	 * 
	 * @campo RangoPersonaFin
	*/
	public Integer getRangopersonafin() {
		return rangopersonafin;
	}

	/**
	 * 
	 * 
	 * @campo RangoPersonaFin
	*/
	public void setRangopersonafin(Integer rangopersonafin) {
		this.rangopersonafin = rangopersonafin;
	}
	/**
	 * 
	 * 
	 * @campo PersonaActual
	*/
	public Integer getPersonaactual() {
		return personaactual;
	}

	/**
	 * 
	 * 
	 * @campo PersonaActual
	*/
	public void setPersonaactual(Integer personaactual) {
		this.personaactual = personaactual;
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
	public BeanSyUnidadreplicacion obtenerBean() {
		BeanSyUnidadreplicacion bean = new BeanSyUnidadreplicacion();
		return obtenerBean(bean);
	}

	public BeanSyUnidadreplicacion obtenerBean(BeanSyUnidadreplicacion bean) {
		if (bean == null)
			bean = new BeanSyUnidadreplicacion();

		bean.getPk().setUnidadreplicacion(unidadreplicacion);
		bean.setDescripcionlocal(descripcionlocal);
		bean.setDescripcionextranjera(descripcionextranjera);
		bean.setRangopersonainicio(rangopersonainicio);
		bean.setRangopersonafin(rangopersonafin);
		bean.setPersonaactual(personaactual);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
