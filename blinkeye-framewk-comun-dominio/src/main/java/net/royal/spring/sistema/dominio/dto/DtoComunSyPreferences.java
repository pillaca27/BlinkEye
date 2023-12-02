package net.royal.spring.sistema.dominio.dto;

import net.royal.spring.sistema.dominio.BeanSyPreferences;
import net.royal.spring.sistema.dominio.BeanSyReporte;

import java.math.BigDecimal;
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
 * Tabla de tipo de sistema reporte
 * 
 * @tabla SY_REPORTE
*/
public class DtoComunSyPreferences extends DominioTransaccion implements java.io.Serializable{

	private String usuario;
	private String preference;
	private String aplicacioncodigo;
	private String tipovalor;
	private String valorstring;
	private BigDecimal valornumero;
	private java.util.Date valorfecha;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	private String descripcioncorta;
	private String tipovalornombre;
				
	public DtoComunSyPreferences() {}
	public DtoComunSyPreferences(String usuario,String preference) {
		this.usuario=usuario;
		this.preference=preference;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPreference() {
		return preference;
	}
	public void setPreference(String preference) {
		this.preference = preference;
	}
	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}
	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	public String getTipovalor() {
		return tipovalor;
	}
	public void setTipovalor(String tipovalor) {
		this.tipovalor = tipovalor;
	}
	public String getValorstring() {
		return valorstring;
	}
	public void setValorstring(String valorstring) {
		this.valorstring = valorstring;
	}
	public BigDecimal getValornumero() {
		return valornumero;
	}
	public void setValornumero(BigDecimal valornumero) {
		this.valornumero = valornumero;
	}
	public java.util.Date getValorfecha() {
		return valorfecha;
	}
	public void setValorfecha(java.util.Date valorfecha) {
		this.valorfecha = valorfecha;
	}
	public String getUltimousuario() {
		return ultimousuario;
	}
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	public String getDescripcioncorta() {
		return descripcioncorta;
	}
	public void setDescripcioncorta(String descripcioncorta) {
		this.descripcioncorta = descripcioncorta;
	}
	public String getTipovalornombre() {
		return tipovalornombre;
	}
	public void setTipovalornombre(String tipovalornombre) {
		this.tipovalornombre = tipovalornombre;
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
