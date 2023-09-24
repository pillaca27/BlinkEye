package net.royal.spring.sy.dominio.dto;

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
import net.royal.spring.sy.dominio.BeanSyPreferences;

/**
 * 
 * 
 * @tabla dbo.SY_Preferences
*/
public class DtoComunSyPreferences extends DominioTransaccion implements java.io.Serializable{

	private String usuario;
	private String preference;
	private String aplicacioncodigo;
	private String tipovalor;
	private String valorstring;
	private Integer valornumero;
	private java.util.Date valorfecha;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;

	private String descripcioncorta;
	
	
	public String getDescripcioncorta() {
		return descripcioncorta;
	}

	public void setDescripcioncorta(String descripcioncorta) {
		this.descripcioncorta = descripcioncorta;
	}

	/**
	 * 
	 * 
	 * @campo Usuario
	*/
	public String getUsuario() {
		return usuario;
	}

	/**
	 * 
	 * 
	 * @campo Usuario
	*/
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * 
	 * 
	 * @campo Preference
	*/
	public String getPreference() {
		return preference;
	}

	/**
	 * 
	 * 
	 * @campo Preference
	*/
	public void setPreference(String preference) {
		this.preference = preference;
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
	 * @campo TipoValor
	*/
	public String getTipovalor() {
		return tipovalor;
	}

	/**
	 * 
	 * 
	 * @campo TipoValor
	*/
	public void setTipovalor(String tipovalor) {
		this.tipovalor = tipovalor;
	}
	/**
	 * 
	 * 
	 * @campo ValorString
	*/
	public String getValorstring() {
		return valorstring;
	}

	/**
	 * 
	 * 
	 * @campo ValorString
	*/
	public void setValorstring(String valorstring) {
		this.valorstring = valorstring;
	}
	/**
	 * 
	 * 
	 * @campo ValorNumero
	*/
	public Integer getValornumero() {
		return valornumero;
	}

	/**
	 * 
	 * 
	 * @campo ValorNumero
	*/
	public void setValornumero(Integer valornumero) {
		this.valornumero = valornumero;
	}
	/**
	 * 
	 * 
	 * @campo ValorFecha
	*/
	public java.util.Date getValorfecha() {
		return valorfecha;
	}

	/**
	 * 
	 * 
	 * @campo ValorFecha
	*/
	public void setValorfecha(java.util.Date valorfecha) {
		this.valorfecha = valorfecha;
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


	public BeanSyPreferences obtenerBean() {
		BeanSyPreferences bean = new BeanSyPreferences();
		return obtenerBean(bean);
	}

	public BeanSyPreferences obtenerBean(BeanSyPreferences bean) {
		if (bean == null)
			bean = new BeanSyPreferences();

		bean.getPk().setUsuario(usuario);
		bean.getPk().setPreference(preference);
		bean.setAplicacioncodigo(aplicacioncodigo);
		bean.setTipovalor(tipovalor);
		bean.setValorstring(valorstring);
		bean.setValornumero(valornumero);
		bean.setValorfecha(valorfecha);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
