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
 * @tabla dbo.Pais
*/
@Entity
@Table(name = "PAIS")
public class BeanPais extends DominioTransaccion implements java.io.Serializable{
	@EmbeddedId
	private BeanPaisPk pk;


	@Size(min = 0, max = 20)
	@Column(name = "DESCRIPCIONCORTA", length = 20, nullable = true)
	private String descripcioncorta;

	@Size(min = 0, max = 40)
	@Column(name = "DESCRIPCIONLARGA", length = 40, nullable = true)
	private String descripcionlarga;

	@Size(min = 0, max = 40)
	@Column(name = "DESCRIPCIONEXTRANJERA", length = 40, nullable = true)
	private String descripcionextranjera;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@Size(min = 0, max = 10)
	@Column(name = "ULTIMOUSUARIO", length = 10, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 40)
	@Column(name = "NOMBRE", length = 40, nullable = true)
	private String nombre;

	@Size(min = 0, max = 30)
	@Column(name = "NACIONALIDAD", length = 30, nullable = true)
	private String nacionalidad;

	@Size(min = 0, max = 4)
	@Column(name = "CODIGORTPS", length = 4, nullable = true)
	private String codigortps;

	public BeanPais() {
		pk = new BeanPaisPk();
	}


	public BeanPais(BeanPaisPk pk) {
		this.pk = pk;
	}

	public BeanPaisPk getPk() {
		return pk;
	}

	public void setPk(BeanPaisPk pk) {
		this.pk = pk;
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
	 * @campo Nombre
	*/
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * 
	 * @campo Nombre
	*/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * 
	 * 
	 * @campo Nacionalidad
	*/
	public String getNacionalidad() {
		return nacionalidad;
	}

	/**
	 * 
	 * 
	 * @campo Nacionalidad
	*/
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	/**
	 * 
	 * 
	 * @campo codigortps
	*/
	public String getCodigortps() {
		return codigortps;
	}

	/**
	 * 
	 * 
	 * @campo codigortps
	*/
	public void setCodigortps(String codigortps) {
		this.codigortps = codigortps;
	}


}
