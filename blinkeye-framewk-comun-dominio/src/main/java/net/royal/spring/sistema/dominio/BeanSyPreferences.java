package net.royal.spring.sistema.dominio;

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
 * 
 * 
 * @tabla dbo.SY_Preferences
*/
@Entity
@Table(name = "SY_PREFERENCES")
public class BeanSyPreferences extends DominioTransaccion implements java.io.Serializable{
	@EmbeddedId
	private BeanSyPreferencesPk pk;


	@Size(min = 0, max = 2)
	@Column(name = "APLICACIONCODIGO", length = 2, nullable = true)
	private String aplicacioncodigo;

	@Size(min = 0, max = 1)
	@Column(name = "TIPOVALOR", length = 1, nullable = true)
	private String tipovalor;

	@Size(min = 0, max = 15)
	@Column(name = "VALORSTRING", length = 15, nullable = true)
	private String valorstring;

	@Column(name = "VALORNUMERO", nullable = true)
	private BigDecimal valornumero;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "VALORFECHA", nullable = true)
	private java.util.Date valorfecha;

	@Size(min = 0, max = 20)
	@Column(name = "ULTIMOUSUARIO", length = 20, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	public BeanSyPreferences() {
		pk = new BeanSyPreferencesPk();
	}


	public BeanSyPreferences(BeanSyPreferencesPk pk) {
		this.pk = pk;
	}

	public BeanSyPreferencesPk getPk() {
		return pk;
	}

	public void setPk(BeanSyPreferencesPk pk) {
		this.pk = pk;
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
	public BigDecimal getValornumero() {
		return valornumero;
	}

	/**
	 * 
	 * 
	 * @campo ValorNumero
	*/
	public void setValornumero(BigDecimal valornumero) {
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


}
