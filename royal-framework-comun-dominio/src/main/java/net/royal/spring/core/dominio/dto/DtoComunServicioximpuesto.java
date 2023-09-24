package net.royal.spring.core.dominio.dto;

import net.royal.spring.core.dominio.BeanServicioximpuesto;

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
 * @tabla dbo.ServicioXImpuesto
*/
public class DtoComunServicioximpuesto extends DominioTransaccion implements java.io.Serializable{


	private String tiposervicio;
	private String impuesto;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private byte[] timestamp;
	private String tipoimpuesto;
	private String accion;
	
	
	
	

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getTipoimpuesto() {
		return tipoimpuesto;
	}

	public void setTipoimpuesto(String tipoimpuesto) {
		this.tipoimpuesto = tipoimpuesto;
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
	 * @campo Impuesto
	*/
	public String getImpuesto() {
		return impuesto;
	}

	/**
	 * 
	 * 
	 * @campo Impuesto
	*/
	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
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
	public BeanServicioximpuesto obtenerBean() {
		BeanServicioximpuesto bean = new BeanServicioximpuesto();
		return obtenerBean(bean);
	}

	public BeanServicioximpuesto obtenerBean(BeanServicioximpuesto bean) {
		if (bean == null)
			bean = new BeanServicioximpuesto();

		bean.getPk().setTiposervicio(tiposervicio);
		bean.getPk().setImpuesto(impuesto);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
