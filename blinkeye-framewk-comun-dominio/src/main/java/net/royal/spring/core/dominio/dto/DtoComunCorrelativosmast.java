package net.royal.spring.core.dominio.dto;

import net.royal.spring.core.dominio.BeanCorrelativosmast;

import java.math.BigDecimal;
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
 * Maestro de correlativos
 * 
 * @tabla CORRELATIVOSMAST
*/
public class DtoComunCorrelativosmast extends DominioTransaccion implements java.io.Serializable{
	
	private String companiacodigo;
	private String tipocomprobante;
	private String serie;
	private String descripcion;
	private BigDecimal correlativonumero;
	private BigDecimal correlativodesde;
	private BigDecimal correlativohasta;
	private String almacencodigo;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;	
	private BigDecimal auxCerosIzquierda;
	private String anio;
	
	private String feflag;
	private String fetipocomprobanteref;
	private String feenvio;
	private String estadodescripcion;
	
	public DtoComunCorrelativosmast() {}
	public DtoComunCorrelativosmast(String companiacodigo,String tipocomprobante,String serie) {
		this.companiacodigo=companiacodigo;
		this.tipocomprobante=tipocomprobante;
		this.serie=serie;
	}
	public DtoComunCorrelativosmast(String companiacodigo,String tipocomprobante,String serie,BigDecimal auxCerosIzquierda) {
		this.companiacodigo=companiacodigo;
		this.tipocomprobante=tipocomprobante;
		this.serie=serie;
		this.auxCerosIzquierda=auxCerosIzquierda;
	}
	
	public String getCompaniacodigo() {
		return companiacodigo;
	}
	public void setCompaniacodigo(String companiacodigo) {
		this.companiacodigo = companiacodigo;
	}
	public String getTipocomprobante() {
		return tipocomprobante;
	}
	public void setTipocomprobante(String tipocomprobante) {
		this.tipocomprobante = tipocomprobante;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getCorrelativonumero() {
		return correlativonumero;
	}
	public void setCorrelativonumero(BigDecimal correlativonumero) {
		this.correlativonumero = correlativonumero;
	}
	public BigDecimal getCorrelativodesde() {
		return correlativodesde;
	}
	public void setCorrelativodesde(BigDecimal correlativodesde) {
		this.correlativodesde = correlativodesde;
	}
	public BigDecimal getCorrelativohasta() {
		return correlativohasta;
	}
	public void setCorrelativohasta(BigDecimal correlativohasta) {
		this.correlativohasta = correlativohasta;
	}
	public String getAlmacencodigo() {
		return almacencodigo;
	}
	public void setAlmacencodigo(String almacencodigo) {
		this.almacencodigo = almacencodigo;
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
	
	
	public BigDecimal getAuxCerosIzquierda() {
		return auxCerosIzquierda;
	}
	public void setAuxCerosIzquierda(BigDecimal auxCerosIzquierda) {
		this.auxCerosIzquierda = auxCerosIzquierda;
	}
	public BeanCorrelativosmast obtenerBean() {
		BeanCorrelativosmast bean = new BeanCorrelativosmast();
		return obtenerBean(bean);
	}
	
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	
	
	public String getFeflag() {
		return feflag;
	}
	public void setFeflag(String feflag) {
		this.feflag = feflag;
	}
	public String getFetipocomprobanteref() {
		return fetipocomprobanteref;
	}
	public void setFetipocomprobanteref(String fetipocomprobanteref) {
		this.fetipocomprobanteref = fetipocomprobanteref;
	}
	public String getFeenvio() {
		return feenvio;
	}
	public void setFeenvio(String feenvio) {
		this.feenvio = feenvio;
	}
	public String getEstadodescripcion() {
		return estadodescripcion;
	}
	public void setEstadodescripcion(String estadodescripcion) {
		this.estadodescripcion = estadodescripcion;
	}
	public BeanCorrelativosmast obtenerBean(BeanCorrelativosmast bean) {
		if (bean == null)
			bean = new BeanCorrelativosmast();

		bean.getPk().setCompaniacodigo(companiacodigo);
		bean.getPk().setTipocomprobante(tipocomprobante);
		bean.getPk().setSerie(serie);
		bean.setDescripcion(descripcion);
		bean.setCorrelativonumero(correlativonumero);
		bean.setCorrelativodesde(correlativodesde);
		bean.setCorrelativohasta(correlativohasta);
		bean.setAlmacencodigo(almacencodigo);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}
}
