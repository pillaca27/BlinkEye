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
 * Seguridad de perfil de usuario
 * 
 * @tabla SGAGROSYS.SEGURIDADPERFILUSUARIO
 */
@Entity
@Table(name = "SEGURIDADPERFILUSUARIO")
public class BeanSeguridadperfilusuario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2994001963134115269L;

	@EmbeddedId
	private BeanSeguridadperfilusuarioPk pk;

	@Size(min = 0, max = 2)
	@Column(name = "ESTADO", length = 2, nullable = true)
	private String estado;

	@Size(min = 0, max = 20)
	@Column(name = "ULTIMOUSUARIO", length = 20, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Transient
	private Boolean auxFlgPreparado = Boolean.FALSE;

	public BeanSeguridadperfilusuario() {
		pk = new BeanSeguridadperfilusuarioPk();
	}

	public BeanSeguridadperfilusuario(BeanSeguridadperfilusuarioPk pk) {
		this.pk = pk;
	}

	public BeanSeguridadperfilusuarioPk getPk() {
		return pk;
	}

	public void setPk(BeanSeguridadperfilusuarioPk pk) {
		this.pk = pk;
	}

	/**
	 * Estado del proceso
	 * 
	 * @campo ESTADO
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Estado del proceso
	 * 
	 * @campo ESTADO
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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

	public Boolean getAuxFlgPreparado() {
		if (auxFlgPreparado == null)
			return Boolean.FALSE;
		return auxFlgPreparado;
	}

	public void setAuxFlgPreparado(Boolean auxFlgPreparado) {
		this.auxFlgPreparado = auxFlgPreparado;
	}

}
