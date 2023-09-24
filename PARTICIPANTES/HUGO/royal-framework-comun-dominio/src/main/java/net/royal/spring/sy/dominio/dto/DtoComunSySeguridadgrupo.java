package net.royal.spring.sy.dominio.dto;

import java.util.ArrayList;
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
import net.royal.spring.sy.dominio.BeanSySeguridadgrupo;

/**
 * 
 * 
 * @tabla dbo.SY_SeguridadGrupo
*/
public class DtoComunSySeguridadgrupo extends DominioTransaccion implements java.io.Serializable{

	private String aplicacioncodigo;
	private String grupo;
	private String descripcionlocal;
	private String descripcioningles;
	private String tipodetalle;
	private Integer ordenpresentacion;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;

	private List<DtoComunSySeguridadconcepto> seguridadconcepto;
	
	public DtoComunSySeguridadgrupo() {
		seguridadconcepto = new ArrayList<DtoComunSySeguridadconcepto>();
	}
	public List<DtoComunSySeguridadconcepto> getSeguridadconcepto() {
		return seguridadconcepto;
	}

	public void setSeguridadconcepto(List<DtoComunSySeguridadconcepto> seguridadconcepto) {
		this.seguridadconcepto = seguridadconcepto;
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
	 * @campo TipoDetalle
	*/
	public String getTipodetalle() {
		return tipodetalle;
	}

	/**
	 * 
	 * 
	 * @campo TipoDetalle
	*/
	public void setTipodetalle(String tipodetalle) {
		this.tipodetalle = tipodetalle;
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


	public BeanSySeguridadgrupo obtenerBean() {
		BeanSySeguridadgrupo bean = new BeanSySeguridadgrupo();
		return obtenerBean(bean);
	}

	public BeanSySeguridadgrupo obtenerBean(BeanSySeguridadgrupo bean) {
		if (bean == null)
			bean = new BeanSySeguridadgrupo();

		bean.getPk().setAplicacioncodigo(aplicacioncodigo);
		bean.getPk().setGrupo(grupo);
		bean.setDescripcionlocal(descripcionlocal);
		bean.setDescripcioningles(descripcioningles);
		bean.setTipodetalle(tipodetalle);
		bean.setOrdenpresentacion(ordenpresentacion);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
