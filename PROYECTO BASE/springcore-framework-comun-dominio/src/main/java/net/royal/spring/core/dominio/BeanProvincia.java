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
 * @tabla dbo.Provincia
*/
@Entity
@Table(name = "PROVINCIA")
public class BeanProvincia extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanProvinciaPk pk;


	@Size(min = 0, max = 20)
	@Column(name = "DESCRIPCIONCORTA", length = 20, nullable = true)
	private String descripcioncorta;

	@Size(min = 0, max = 50)
	@Column(name = "DESCRIPCIONLARGA", length = 50, nullable = true)
	private String descripcionlarga;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 20)
	@Column(name = "ULTIMOUSUARIO", length = 20, nullable = true)
	private String ultimousuario;
	
	@Size(min = 0, max = 4)
	@Column(name = "PAIS", length = 4, nullable = true)
	private String pais;
	
	

	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}


	public BeanProvincia() {
		pk = new BeanProvinciaPk();
	}


	public BeanProvincia(BeanProvinciaPk pk) {
		this.pk = pk;
	}

	public BeanProvinciaPk getPk() {
		return pk;
	}

	public void setPk(BeanProvinciaPk pk) {
		this.pk = pk;
	}
	/**
	 * 
	 * 
	 * @campo Departamento
	*/
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

}
