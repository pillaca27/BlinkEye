package net.royal.spring.core.dominio.dto;

import net.royal.spring.core.dominio.BeanMaMiscelaneosheader;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
 * Maestro miscelaneos header
 * 
 * @tabla MA_MISCELANEOSHEADER
*/
public class DtoComunMaMiscelaneosheader extends DominioTransaccion implements java.io.Serializable{


	private String aplicacioncodigo;
	private String codigotabla;
	private String compania;
	private String descripcionlocal;
	private String descripcionextranjera;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	private List<DtoComunMaMiscelaneosdetalle> detalle;
	
	private String nombreAplicacion;
	private String nombreCompania;
	

	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	public String getCodigotabla() {
		return codigotabla;
	}

	public void setCodigotabla(String codigotabla) {
		this.codigotabla = codigotabla;
	}
	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}
	public String getDescripcionlocal() {
		return descripcionlocal;
	}

	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}
	public String getDescripcionextranjera() {
		return descripcionextranjera;
	}

	public void setDescripcionextranjera(String descripcionextranjera) {
		this.descripcionextranjera = descripcionextranjera;
	}
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUltimousuario() {
		return ultimousuario;
	}

	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	

	public BeanMaMiscelaneosheader obtenerBean() {
		BeanMaMiscelaneosheader bean = new BeanMaMiscelaneosheader();
		return obtenerBean(bean);
	}

	public BeanMaMiscelaneosheader obtenerBean(BeanMaMiscelaneosheader bean) {
		if (bean == null)
			bean = new BeanMaMiscelaneosheader();

		bean.getPk().setAplicacioncodigo(aplicacioncodigo);
		bean.getPk().setCodigotabla(codigotabla);
		bean.getPk().setCompania(compania);
		bean.setDescripcionlocal(descripcionlocal);
		bean.setDescripcionextranjera(descripcionextranjera);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}


	public String getNombreAplicacion() {
		return nombreAplicacion;
	}

	public void setNombreAplicacion(String nombreAplicacion) {
		this.nombreAplicacion = nombreAplicacion;
	}

	public String getNombreCompania() {
		return nombreCompania;
	}

	public void setNombreCompania(String nombreCompania) {
		this.nombreCompania = nombreCompania;
	}

	public List<DtoComunMaMiscelaneosdetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DtoComunMaMiscelaneosdetalle> detalle) {
		this.detalle = detalle;
	}

}
