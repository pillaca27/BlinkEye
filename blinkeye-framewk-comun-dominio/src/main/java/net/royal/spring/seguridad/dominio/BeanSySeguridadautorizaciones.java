package net.royal.spring.seguridad.dominio;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * Tabla de tipo de sistema seguridad por autorizaciones
 * 
 * @tabla SGAGROSYS.SY_SEGURIDADAUTORIZACIONES
*/
@Entity
@Table(name = "SY_SEGURIDADAUTORIZACIONES")
public class BeanSySeguridadautorizaciones implements java.io.Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BeanSySeguridadautorizacionesPk pk;

	@Size(min = 0, max = 1)
	@Column(name = "MASTERBROWSEFLAG", length = 1, nullable = true)
	private String masterbrowseflag;

	@Size(min = 0, max = 1)
	@Column(name = "MASTERUPDATEFLAG", length = 1, nullable = true)
	private String masterupdateflag;

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

	@Size(min = 0, max = 1)
	@Column(name = "MASTERAPPROVEFLAG", length = 1, nullable = true)
	private String masterapproveflag;

	@Transient
	private Boolean auxFlgPreparado=Boolean.FALSE;


	public BeanSySeguridadautorizaciones() {
		pk = new BeanSySeguridadautorizacionesPk();
	}


	public BeanSySeguridadautorizaciones(BeanSySeguridadautorizacionesPk pk) {
		this.pk = pk;
	}

	public BeanSySeguridadautorizacionesPk getPk() {
		return pk;
	}

	public void setPk(BeanSySeguridadautorizacionesPk pk) {
		this.pk = pk;
	}
	/**
	 * Flag de Mostrar Maestro
	 * 
	 * @campo MASTERBROWSEFLAG
	*/
	public String getMasterbrowseflag() {
		return masterbrowseflag;
	}

	/**
	 * Flag de Mostrar Maestro
	 * 
	 * @campo MASTERBROWSEFLAG
	*/
	public void setMasterbrowseflag(String masterbrowseflag) {
		this.masterbrowseflag = masterbrowseflag;
	}
	/**
	 * Flag de Actualizar Maestro
	 * 
	 * @campo MASTERUPDATEFLAG
	*/
	public String getMasterupdateflag() {
		return masterupdateflag;
	}

	/**
	 * Flag de Actualizar Maestro
	 * 
	 * @campo MASTERUPDATEFLAG
	*/
	public void setMasterupdateflag(String masterupdateflag) {
		this.masterupdateflag = masterupdateflag;
	}
	/**
	 * A = Activo, I = Inactivo
	 * 
	 * @campo ESTADO
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * A = Activo, I = Inactivo
	 * 
	 * @campo ESTADO
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * Es el Codigo del ultimo usuario que realizo modificaciones a este registro
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * Es el Codigo del ultimo usuario que realizo modificaciones a este registro
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	/**
	 * Es la fecha de la ultima modificacion realizada a este registro
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * Es la fecha de la ultima modificacion realizada a este registro
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	/**
	 * Flag de Aprobar Maestro
	 * 
	 * @campo MASTERAPPROVEFLAG
	*/
	public String getMasterapproveflag() {
		return masterapproveflag;
	}

	/**
	 * Flag de Aprobar Maestro
	 * 
	 * @campo MASTERAPPROVEFLAG
	*/
	public void setMasterapproveflag(String masterapproveflag) {
		this.masterapproveflag = masterapproveflag;
	}
	public Boolean getAuxFlgPreparado() {
		if (auxFlgPreparado==null)
			return Boolean.FALSE;
		return auxFlgPreparado;
	}

	public void setAuxFlgPreparado(Boolean auxFlgPreparado) {
		this.auxFlgPreparado = auxFlgPreparado;
	}

}
