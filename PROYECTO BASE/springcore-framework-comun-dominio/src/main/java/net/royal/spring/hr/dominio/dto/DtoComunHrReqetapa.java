package net.royal.spring.hr.dominio.dto;

import net.royal.spring.hr.dominio.BeanHrReqetapa;

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

import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.HR_ReqEtapa
*/
public class DtoComunHrReqetapa extends DominioTransaccion implements java.io.Serializable{

	private String companyowner;
	private Integer etapa;
	private String descripcion;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	private String companyownernombre;
	
	

	public String getCompanyownernombre() {
		return companyownernombre;
	}

	public void setCompanyownernombre(String companyownernombre) {
		this.companyownernombre = companyownernombre;
	}

	/**
	 * 
	 * 
	 * @campo CompanyOwner
	*/
	public String getCompanyowner() {
		return companyowner;
	}

	/**
	 * 
	 * 
	 * @campo CompanyOwner
	*/
	public void setCompanyowner(String companyowner) {
		this.companyowner = companyowner;
	}
	/**
	 * 
	 * 
	 * @campo Etapa
	*/
	public Integer getEtapa() {
		return etapa;
	}

	/**
	 * 
	 * 
	 * @campo Etapa
	*/
	public void setEtapa(Integer etapa) {
		this.etapa = etapa;
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


	public BeanHrReqetapa obtenerBean() {
		return obtenerBeanCore(new BeanHrReqetapa(),ConstantePantallaAccion.NINGUNO);
	}

	public BeanHrReqetapa obtenerBeanRegistrar() {
		return obtenerBeanCore(new BeanHrReqetapa(),ConstantePantallaAccion.INSERTAR);
	}

	public BeanHrReqetapa obtenerBeanActualizar(BeanHrReqetapa bean) {
		return obtenerBeanCore(bean,ConstantePantallaAccion.ACTUALIZAR);
	}

	private BeanHrReqetapa obtenerBeanCore(BeanHrReqetapa bean,String tipo) {
		if (UString.esNuloVacio(tipo))
			tipo=ConstantePantallaAccion.NINGUNO;

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		switch (tipo) {
		case ConstantePantallaAccion.INSERTAR,ConstantePantallaAccion.NINGUNO:
			bean.getPk().setCompanyowner(companyowner);
			bean.getPk().setEtapa(etapa);
			bean.setDescripcion(descripcion);
			bean.setEstado(estado);
			bean.setUltimousuario(ultimousuario);
			bean.setUltimafechamodif(ultimafechamodif);

			break;
		case ConstantePantallaAccion.ACTUALIZAR:

			break;
		}

		return bean;
	}

}
