package net.royal.spring.sg.dominio;


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
 * 
 * 
 * @tabla SGAGROSYS.SEGURIDADAUTORIZACIONREPORTE
*/
@Entity
@Table(name = "SEGURIDADAUTORIZACIONREPORTE")
public class BeanSeguridadautorizacionreporte implements java.io.Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BeanSeguridadautorizacionreportePk pk;

	@Size(min = 0, max = 1)
	@Column(name = "DISPONIBLE", length = 1, nullable = true)
	private String disponible;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 20)
	@Column(name = "ULTIMOUSUARIO", length = 20, nullable = true)
	private String ultimousuario;

	@Size(min = 0, max = 50)
	@Column(name = "CAMPODATA", length = 50, nullable = true)
	private String campodata;

	@Transient
	private Boolean auxFlgPreparado=Boolean.FALSE;


	public BeanSeguridadautorizacionreporte() {
		pk = new BeanSeguridadautorizacionreportePk();
	}


	public BeanSeguridadautorizacionreporte(BeanSeguridadautorizacionreportePk pk) {
		this.pk = pk;
	}

	public BeanSeguridadautorizacionreportePk getPk() {
		return pk;
	}

	public void setPk(BeanSeguridadautorizacionreportePk pk) {
		this.pk = pk;
	}
	/**
	 * Reporte disponible  
	 * 
	 * @campo DISPONIBLE
	*/
	public String getDisponible() {
		return disponible;
	}

	/**
	 * Reporte disponible  
	 * 
	 * @campo DISPONIBLE
	*/
	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}
	/**
	 * Ultima Fecha de Modificacion
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * Ultima Fecha de Modificacion
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	/**
	 * Ultimo Usuario de Modificacion
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * Ultimo Usuario de Modificacion
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	/**
	 * Campo de informacion 
	 * 
	 * @campo CAMPODATA
	*/
	public String getCampodata() {
		return campodata;
	}

	/**
	 * Campo de informacion 
	 * 
	 * @campo CAMPODATA
	*/
	public void setCampodata(String campodata) {
		this.campodata = campodata;
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
