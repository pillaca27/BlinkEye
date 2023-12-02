package net.royal.spring.core.dominio.lista;

import net.royal.spring.core.dominio.BeanZonadespacho;

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
 * Maestro de zona de despacho
 * 
 * @tabla SPRING.ZONADESPACHO
*/
public class DtlComunZonadespacho extends DominioTransaccion implements java.io.Serializable{


	private String zonadespacho;
	private java.math.BigDecimal transportista;
	private String descripcion;
	private String estado;
	private java.util.Date ultimafechamodif;
	private String ultimousuario;

	/**
	 * codigo de zona despacho
	 * 
	 * @campo ZONADESPACHO
	*/
	public String getZonadespacho() {
		return zonadespacho;
	}

	/**
	 * codigo de zona despacho
	 * 
	 * @campo ZONADESPACHO
	*/
	public void setZonadespacho(String zonadespacho) {
		this.zonadespacho = zonadespacho;
	}
	/**
	 * codigo transportista
	 * 
	 * @campo TRANSPORTISTA
	*/
	public java.math.BigDecimal getTransportista() {
		return transportista;
	}

	/**
	 * codigo transportista
	 * 
	 * @campo TRANSPORTISTA
	*/
	public void setTransportista(java.math.BigDecimal transportista) {
		this.transportista = transportista;
	}
	/**
	 * descriptcion de transportista
	 * 
	 * @campo DESCRIPCION
	*/
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * descriptcion de transportista
	 * 
	 * @campo DESCRIPCION
	*/
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * reservado
	 * 
	 * @campo ESTADO
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * reservado
	 * 
	 * @campo ESTADO
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * ultima fecha de modificacion
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * ultima fecha de modificacion
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	/**
	 * ultimo usuario de modificacion
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * ultimo usuario de modificacion
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}

}
