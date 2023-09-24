package net.royal.spring.core.dominio.lista;

import net.royal.spring.core.dominio.BeanZonapostal;

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
 * @tabla dbo.ZonaPostal
*/
public class DtlComunZonapostal extends DominioTransaccion implements java.io.Serializable{


	private String departamento;
	private String provincia;
	private String codigopostal;
	private String descripcioncorta;
	private String descripcionlarga;
	private Integer cobrador;
	private String estado;
	private java.util.Date ultimafechamodif;
	private String ultimousuario;

	/**
	 * 
	 * 
	 * @campo Departamento
	*/
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * 
	 * 
	 * @campo Departamento
	*/
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	/**
	 * 
	 * 
	 * @campo Provincia
	*/
	public String getProvincia() {
		return provincia;
	}

	/**
	 * 
	 * 
	 * @campo Provincia
	*/
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	/**
	 * 
	 * 
	 * @campo CodigoPostal
	*/
	public String getCodigopostal() {
		return codigopostal;
	}

	/**
	 * 
	 * 
	 * @campo CodigoPostal
	*/
	public void setCodigopostal(String codigopostal) {
		this.codigopostal = codigopostal;
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
	 * @campo Cobrador
	*/
	public Integer getCobrador() {
		return cobrador;
	}

	/**
	 * 
	 * 
	 * @campo Cobrador
	*/
	public void setCobrador(Integer cobrador) {
		this.cobrador = cobrador;
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
