package net.royal.spring.core.dominio.lista;

import net.royal.spring.core.dominio.BeanTiposervicio;

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
 * @tabla dbo.TipoServicio
*/
public class DtlComunTiposervicio extends DominioTransaccion implements java.io.Serializable{


	private String tiposervicio;
	private String descripcion;
	private String regimenfiscal;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private byte[] timestamp;
	private String clasificacionfiscal;
	private String estado;
	private String descripcioningles;
	private String estadodescripcion;
	
	public String getEstadodescripcion() {
		return estadodescripcion;
	}
	public void setEstadodescripcion(String estadodescripcion) {
		this.estadodescripcion = estadodescripcion;
	}

	/**
	 * 
	 * 
	 * @campo TipoServicio
	*/
	public String getTiposervicio() {
		return tiposervicio;
	}

	/**
	 * 
	 * 
	 * @campo TipoServicio
	*/
	public void setTiposervicio(String tiposervicio) {
		this.tiposervicio = tiposervicio;
	}
	/**
	 * 
	 * 
	 * @campo Descripcion
	*/
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * 
	 * 
	 * @campo Descripcion
	*/
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * 
	 * 
	 * @campo RegimenFiscal
	*/
	public String getRegimenfiscal() {
		return regimenfiscal;
	}

	/**
	 * 
	 * 
	 * @campo RegimenFiscal
	*/
	public void setRegimenfiscal(String regimenfiscal) {
		this.regimenfiscal = regimenfiscal;
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
	 * @campo Timestamp
	*/
	public byte[] getTimestamp() {
		return timestamp;
	}

	/**
	 * 
	 * 
	 * @campo Timestamp
	*/
	public void setTimestamp(byte[] timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * 
	 * 
	 * @campo ClasificacionFiscal
	*/
	public String getClasificacionfiscal() {
		return clasificacionfiscal;
	}

	/**
	 * 
	 * 
	 * @campo ClasificacionFiscal
	*/
	public void setClasificacionfiscal(String clasificacionfiscal) {
		this.clasificacionfiscal = clasificacionfiscal;
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
	 * @campo DescripcionIngles
	*/
	public String getDescripcioningles() {
		return descripcioningles;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionIngles
	*/
	public void setDescripcioningles(String descripcioningles) {
		this.descripcioningles = descripcioningles;
	}

}
