package net.royal.spring.sistema.dominio.dto;

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

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;
import net.royal.spring.sistema.dominio.BeanSyTipodocumento;

/**
 * 
 * 
 * @tabla dbo.SY_TipoDocumento
 */
public class DtoComunSyTipodocumento extends DominioTransaccion implements java.io.Serializable {

	private String tipodocumentoid;
	private String descripcionlocal;
	private String descripcioningles;
	private String comentarios;
	private String estado;
	private String ultimousuario;
	private String link;
	private java.util.Date ultimafechamodif;
	private List<DtoComunSyTipodocumentoproceso>detalle;

	public String getTipodocumentoid() {
		return tipodocumentoid;
	}

	public void setTipodocumentoid(String tipodocumentoid) {
		this.tipodocumentoid = tipodocumentoid;
	}

	public String getDescripcionlocal() {
		return descripcionlocal;
	}

	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}

	public String getDescripcioningles() {
		return descripcioningles;
	}

	public void setDescripcioningles(String descripcioningles) {
		this.descripcioningles = descripcioningles;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
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

	public BeanSyTipodocumento obtenerBean() {
		BeanSyTipodocumento bean = new BeanSyTipodocumento();
		return obtenerBean(bean);
	}

	public BeanSyTipodocumento obtenerBean(BeanSyTipodocumento bean) {
		if (bean == null)
			bean = new BeanSyTipodocumento();
		bean.getPk().setTipodocumentoid(tipodocumentoid);
		bean.setDescripcionlocal(descripcionlocal);
		bean.setDescripcioningles(descripcioningles);
		bean.setComentarios(comentarios);
		bean.setEstado(estado);
		bean.setLink(link);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);

		return bean;
	}

	public List<DtoComunSyTipodocumentoproceso> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DtoComunSyTipodocumentoproceso> detalle) {
		this.detalle = detalle;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
