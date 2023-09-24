package net.royal.spring.wh.dominio.dto;

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
import net.royal.spring.wh.dominio.BeanWhItemunidad;

/**
 * 
 * 
 * @tabla dbo.WH_ItemUnidad
*/
public class DtoComunWhItemunidad extends DominioTransaccion implements java.io.Serializable{


	private String item;
	private String unidadcodigo;
	private java.math.BigDecimal factorconversion;
	private String redondeoflag;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String accion;
	private String unidadcodigodescri;
	
	
	
	

	public String getUnidadcodigodescri() {
		return unidadcodigodescri;
	}

	public void setUnidadcodigodescri(String unidadcodigodescri) {
		this.unidadcodigodescri = unidadcodigodescri;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * 
	 * 
	 * @campo Item
	*/
	public String getItem() {
		return item;
	}

	/**
	 * 
	 * 
	 * @campo Item
	*/
	public void setItem(String item) {
		this.item = item;
	}
	/**
	 * 
	 * 
	 * @campo UnidadCodigo
	*/
	public String getUnidadcodigo() {
		return unidadcodigo;
	}

	/**
	 * 
	 * 
	 * @campo UnidadCodigo
	*/
	public void setUnidadcodigo(String unidadcodigo) {
		this.unidadcodigo = unidadcodigo;
	}
	/**
	 * 
	 * 
	 * @campo FactorConversion
	*/
	public java.math.BigDecimal getFactorconversion() {
		return factorconversion;
	}

	/**
	 * 
	 * 
	 * @campo FactorConversion
	*/
	public void setFactorconversion(java.math.BigDecimal factorconversion) {
		this.factorconversion = factorconversion;
	}
	/**
	 * 
	 * 
	 * @campo RedondeoFlag
	*/
	public String getRedondeoflag() {
		return redondeoflag;
	}

	/**
	 * 
	 * 
	 * @campo RedondeoFlag
	*/
	public void setRedondeoflag(String redondeoflag) {
		this.redondeoflag = redondeoflag;
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
	public BeanWhItemunidad obtenerBean() {
		BeanWhItemunidad bean = new BeanWhItemunidad();
		return obtenerBean(bean);
	}

	public BeanWhItemunidad obtenerBean(BeanWhItemunidad bean) {
		if (bean == null)
			bean = new BeanWhItemunidad();

		bean.getPk().setItem(item);
		bean.getPk().setUnidadcodigo(unidadcodigo);
		bean.setFactorconversion(factorconversion);
		bean.setRedondeoflag(redondeoflag);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
