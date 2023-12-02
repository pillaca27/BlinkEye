package net.royal.spring.sistema.dominio;

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
@Entity
@Table(name = "SY_UNIDADREPLICACION")
public class BeanSyUnidadreplicacion extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanSyUnidadreplicacionPk pk;

	@Size(min = 0, max = 30)
	@Column(name = "DESCRIPCIONLOCAL", length = 30, nullable = true)
	private String descripcionlocal;

	@Size(min = 0, max = 30)
	@Column(name = "DESCRIPCIONEXTRANJERA", length = 30, nullable = true)
	private String descripcionextranjera;

	@Column(name = "RANGOPERSONAINICIO", nullable = true)
	private Integer rangopersonainicio;

	@Column(name = "RANGOPERSONAFIN", nullable = true)
	private Integer rangopersonafin;

	@Column(name = "PERSONAACTUAL", nullable = true)
	private Integer personaactual;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@Size(min = 0, max = 6)
	@Column(name = "ULTIMOUSUARIO", length = 6, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 36)
	@Column(name = "UUID", length = 36, nullable = true)
	private String uuid;
	
	
	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public BeanSyUnidadreplicacion() {
		pk = new BeanSyUnidadreplicacionPk();
	}


	public BeanSyUnidadreplicacion(BeanSyUnidadreplicacionPk pk) {
		this.pk = pk;
	}

	public BeanSyUnidadreplicacionPk getPk() {
		return pk;
	}

	public void setPk(BeanSyUnidadreplicacionPk pk) {
		this.pk = pk;
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

}
