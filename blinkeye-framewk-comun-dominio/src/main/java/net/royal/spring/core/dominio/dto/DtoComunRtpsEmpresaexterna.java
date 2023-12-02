package net.royal.spring.core.dominio.dto;

import net.royal.spring.core.dominio.BeanRtpsEmpresaexterna;

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
 * 
 * 
 * @tabla dbo.RTPS_EmpresaExterna
*/
public class DtoComunRtpsEmpresaexterna extends DominioTransaccion implements java.io.Serializable{

	private Integer codigoautomatico;
	private String rucempresa;
	private String razonsocial;
	private String codigoactividad;
	private String tipoempresa;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	private List<DtoComunRtpsEstablecimientoexterno> lstdetestablecimiento;
	
	
	

	
	public List<DtoComunRtpsEstablecimientoexterno> getLstdetestablecimiento() {
		return lstdetestablecimiento;
	}

	public void setLstdetestablecimiento(List<DtoComunRtpsEstablecimientoexterno> lstdetestablecimiento) {
		this.lstdetestablecimiento = lstdetestablecimiento;
	}

	/**
	 * 
	 * 
	 * @campo CodigoAutomatico
	*/
	public Integer getCodigoautomatico() {
		return codigoautomatico;
	}

	/**
	 * 
	 * 
	 * @campo CodigoAutomatico
	*/
	public void setCodigoautomatico(Integer codigoautomatico) {
		this.codigoautomatico = codigoautomatico;
	}
	/**
	 * 
	 * 
	 * @campo RUCEmpresa
	*/
	public String getRucempresa() {
		return rucempresa;
	}

	/**
	 * 
	 * 
	 * @campo RUCEmpresa
	*/
	public void setRucempresa(String rucempresa) {
		this.rucempresa = rucempresa;
	}
	/**
	 * 
	 * 
	 * @campo RazonSocial
	*/
	public String getRazonsocial() {
		return razonsocial;
	}

	/**
	 * 
	 * 
	 * @campo RazonSocial
	*/
	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}
	/**
	 * 
	 * 
	 * @campo CodigoActividad
	*/
	public String getCodigoactividad() {
		return codigoactividad;
	}

	/**
	 * 
	 * 
	 * @campo CodigoActividad
	*/
	public void setCodigoactividad(String codigoactividad) {
		this.codigoactividad = codigoactividad;
	}
	/**
	 * 
	 * 
	 * @campo TipoEmpresa
	*/
	public String getTipoempresa() {
		return tipoempresa;
	}

	/**
	 * 
	 * 
	 * @campo TipoEmpresa
	*/
	public void setTipoempresa(String tipoempresa) {
		this.tipoempresa = tipoempresa;
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


	public BeanRtpsEmpresaexterna obtenerBean() {
		BeanRtpsEmpresaexterna bean = new BeanRtpsEmpresaexterna();
		return obtenerBean(bean);
	}

	public BeanRtpsEmpresaexterna obtenerBean(BeanRtpsEmpresaexterna bean) {
		if (bean == null)
			bean = new BeanRtpsEmpresaexterna();

		bean.getPk().setCodigoautomatico(codigoautomatico);
		bean.setRucempresa(rucempresa);
		bean.setRazonsocial(razonsocial);
		bean.setCodigoactividad(codigoactividad);
		bean.setTipoempresa(tipoempresa);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
