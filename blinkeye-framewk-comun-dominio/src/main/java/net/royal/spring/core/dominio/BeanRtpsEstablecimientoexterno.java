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
 * @tabla dbo.RTPS_EstablecimientoExterno
*/
@Entity
@Table(name = "RTPS_ESTABLECIMIENTOEXTERNO")
public class BeanRtpsEstablecimientoexterno extends DominioTransaccion implements java.io.Serializable{
	@EmbeddedId
	private BeanRtpsEstablecimientoexternoPk pk;


	@Column(name = "AUTOMATICOEXTERNO", nullable = true)
	private Integer automaticoexterno;

	@Size(min = 0, max = 4)
	@Column(name = "CODIGO", length = 4, nullable = true)
	private String codigo;

	@Size(min = 0, max = 60)
	@Column(name = "DENOMINACION", length = 60, nullable = true)
	private String denominacion;

	@Size(min = 0, max = 1)
	@Column(name = "INDCENTRORIESGO", length = 1, nullable = true)
	private String indcentroriesgo;

	@Column(name = "TASA", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal tasa;

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

	public BeanRtpsEstablecimientoexterno() {
		pk = new BeanRtpsEstablecimientoexternoPk();
	}


	public BeanRtpsEstablecimientoexterno(BeanRtpsEstablecimientoexternoPk pk) {
		this.pk = pk;
	}

	public BeanRtpsEstablecimientoexternoPk getPk() {
		return pk;
	}

	public void setPk(BeanRtpsEstablecimientoexternoPk pk) {
		this.pk = pk;
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


}
