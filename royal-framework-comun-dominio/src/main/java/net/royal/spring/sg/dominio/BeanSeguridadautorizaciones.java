package net.royal.spring.sg.dominio;

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
 * Registro de seguridad de autorizaciones
 * 
 * @tabla SPRING.SEGURIDADAUTORIZACIONES
*/
@Entity
@Table(name = "SEGURIDADAUTORIZACIONES")
public class BeanSeguridadautorizaciones extends DominioTransaccion implements java.io.Serializable{
	@EmbeddedId
	private BeanSeguridadautorizacionesPk pk;

	@Size(min = 0, max = 1)
	@Column(name = "OPCIONAGREGARFLAG", length = 1, nullable = true)
	private String opcionagregarflag;

	@Size(min = 0, max = 1)
	@Column(name = "OPCIONMODIFICARFLAG", length = 1, nullable = true)
	private String opcionmodificarflag;

	@Size(min = 0, max = 1)
	@Column(name = "OPCIONBORRARFLAG", length = 1, nullable = true)
	private String opcionborrarflag;

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
	@Column(name = "OPCIONAPROBARFLAG", length = 1, nullable = true)
	private String opcionaprobarflag;


	public BeanSeguridadautorizaciones() {
		pk = new BeanSeguridadautorizacionesPk();
	}


	public BeanSeguridadautorizaciones(BeanSeguridadautorizacionesPk pk) {
		this.pk = pk;
	}

	public BeanSeguridadautorizacionesPk getPk() {
		return pk;
	}

	public void setPk(BeanSeguridadautorizacionesPk pk) {
		this.pk = pk;
	}
	/**
	 * Opcion agregar 
	 * 
	 * @campo OPCIONAGREGARFLAG
	*/
	public String getOpcionagregarflag() {
		return opcionagregarflag;
	}

	/**
	 * Opcion agregar 
	 * 
	 * @campo OPCIONAGREGARFLAG
	*/
	public void setOpcionagregarflag(String opcionagregarflag) {
		this.opcionagregarflag = opcionagregarflag;
	}
	/**
	 * Opcion modificar 
	 * 
	 * @campo OPCIONMODIFICARFLAG
	*/
	public String getOpcionmodificarflag() {
		return opcionmodificarflag;
	}

	/**
	 * Opcion modificar 
	 * 
	 * @campo OPCIONMODIFICARFLAG
	*/
	public void setOpcionmodificarflag(String opcionmodificarflag) {
		this.opcionmodificarflag = opcionmodificarflag;
	}
	/**
	 * Opcion borrar 
	 * 
	 * @campo OPCIONBORRARFLAG
	*/
	public String getOpcionborrarflag() {
		return opcionborrarflag;
	}

	/**
	 * Opcion borrar 
	 * 
	 * @campo OPCIONBORRARFLAG
	*/
	public void setOpcionborrarflag(String opcionborrarflag) {
		this.opcionborrarflag = opcionborrarflag;
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
	/**
	 * Opcion aprobar 
	 * 
	 * @campo OPCIONAPROBARFLAG
	*/
	public String getOpcionaprobarflag() {
		return opcionaprobarflag;
	}

	/**
	 * Opcion aprobar 
	 * 
	 * @campo OPCIONAPROBARFLAG
	*/
	public void setOpcionaprobarflag(String opcionaprobarflag) {
		this.opcionaprobarflag = opcionaprobarflag;
	}
}
