package net.royal.spring.sistema.dominio.dto;

import net.royal.spring.sistema.dominio.BeanSySeguridadconcepto;

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
 * @tabla dbo.SY_SeguridadConcepto
*/
public class DtoComunSySeguridadconcepto extends DominioTransaccion implements java.io.Serializable{

	private String aplicacioncodigo;
	private String grupo;
	private String concepto;
	private Integer ordenpresentacion;
	private String descripcionlocal;
	private String descripcioningles;
	private String codigointerno;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	private String flgactualizar;
	
	
	public String getFlgactualizar() {
		return flgactualizar;
	}

	public void setFlgactualizar(String flgactualizar) {
		this.flgactualizar = flgactualizar;
	}

	/**
	 * 
	 * 
	 * @campo AplicacionCodigo
	*/
	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	/**
	 * 
	 * 
	 * @campo AplicacionCodigo
	*/
	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	/**
	 * 
	 * 
	 * @campo Grupo
	*/
	public String getGrupo() {
		return grupo;
	}

	/**
	 * 
	 * 
	 * @campo Grupo
	*/
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	/**
	 * 
	 * 
	 * @campo Concepto
	*/
	public String getConcepto() {
		return concepto;
	}

	/**
	 * 
	 * 
	 * @campo Concepto
	*/
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	/**
	 * 
	 * 
	 * @campo OrdenPresentacion
	*/
	public Integer getOrdenpresentacion() {
		return ordenpresentacion;
	}

	/**
	 * 
	 * 
	 * @campo OrdenPresentacion
	*/
	public void setOrdenpresentacion(Integer ordenpresentacion) {
		this.ordenpresentacion = ordenpresentacion;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionLocal
	*/
	public String getDescripcionlocal() {
		return descripcionlocal;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionLocal
	*/
	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
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
	/**
	 * 
	 * 
	 * @campo CodigoInterno
	*/
	public String getCodigointerno() {
		return codigointerno;
	}

	/**
	 * 
	 * 
	 * @campo CodigoInterno
	*/
	public void setCodigointerno(String codigointerno) {
		this.codigointerno = codigointerno;
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


	public BeanSySeguridadconcepto obtenerBean() {
		BeanSySeguridadconcepto bean = new BeanSySeguridadconcepto();
		return obtenerBean(bean);
	}

	public BeanSySeguridadconcepto obtenerBean(BeanSySeguridadconcepto bean) {
		if (bean == null)
			bean = new BeanSySeguridadconcepto();

		bean.getPk().setAplicacioncodigo(aplicacioncodigo);
		bean.getPk().setGrupo(grupo);
		bean.getPk().setConcepto(concepto);
		bean.setOrdenpresentacion(ordenpresentacion);
		bean.setDescripcionlocal(descripcionlocal);
		bean.setDescripcioningles(descripcioningles);
		bean.setCodigointerno(codigointerno);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
