package net.royal.spring.logistica.dominio;

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
 * @tabla dbo.WH_ItemUnidad
*/
@Entity
@Table(name = "WH_ITEMUNIDAD")
public class BeanWhItemunidad extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanWhItemunidadPk pk;

	@Column(name = "FACTORCONVERSION", precision = 16,scale =6, nullable = true)
	private java.math.BigDecimal factorconversion;

	@Size(min = 0, max = 1)
	@Column(name = "REDONDEOFLAG", length = 1, nullable = true)
	private String redondeoflag;

	@Size(min = 0, max = 20)
	@Column(name = "ULTIMOUSUARIO", length = 20, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;


	public BeanWhItemunidad() {
		pk = new BeanWhItemunidadPk();
	}


	public BeanWhItemunidad(BeanWhItemunidadPk pk) {
		this.pk = pk;
	}

	public BeanWhItemunidadPk getPk() {
		return pk;
	}

	public void setPk(BeanWhItemunidadPk pk) {
		this.pk = pk;
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

}
