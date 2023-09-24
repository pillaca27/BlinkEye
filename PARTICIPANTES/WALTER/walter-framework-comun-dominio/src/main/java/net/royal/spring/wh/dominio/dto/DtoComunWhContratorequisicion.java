package net.royal.spring.wh.dominio.dto;

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
import net.royal.spring.wh.dominio.BeanWhContratorequisicion;

/**
 * 
 * 
 * @tabla dbo.WH_ContratoRequisicion
*/
public class DtoComunWhContratorequisicion extends DominioTransaccion implements java.io.Serializable{


	private String companiasocio;
	private String numerocontrato;
	private Integer secuencia;
	private String requisicionnumero;
	private Integer requisicionsecuencia;
	private java.math.BigDecimal cantidad;
	
	private String item;
	private String commodity;
	private String descripcion;
	private java.math.BigDecimal cantidadpedida;
	private String licitacionnumeroproceso;
	private java.math.BigDecimal preciounitario;
	private java.math.BigDecimal precioxcantidad;
	private java.math.BigDecimal pulicitacion;
	private String procesonumero;
	private java.math.BigDecimal montototal;
	
	
	public java.math.BigDecimal getMontototal() {
		return montototal;
	}

	public void setMontototal(java.math.BigDecimal montototal) {
		this.montototal = montototal;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public java.math.BigDecimal getCantidadpedida() {
		return cantidadpedida;
	}

	public void setCantidadpedida(java.math.BigDecimal cantidadpedida) {
		this.cantidadpedida = cantidadpedida;
	}

	public String getLicitacionnumeroproceso() {
		return licitacionnumeroproceso;
	}

	public void setLicitacionnumeroproceso(String licitacionnumeroproceso) {
		this.licitacionnumeroproceso = licitacionnumeroproceso;
	}

	public java.math.BigDecimal getPreciounitario() {
		return preciounitario;
	}

	public void setPreciounitario(java.math.BigDecimal preciounitario) {
		this.preciounitario = preciounitario;
	}

	public java.math.BigDecimal getPrecioxcantidad() {
		return precioxcantidad;
	}

	public void setPrecioxcantidad(java.math.BigDecimal precioxcantidad) {
		this.precioxcantidad = precioxcantidad;
	}

	public java.math.BigDecimal getPulicitacion() {
		return pulicitacion;
	}

	public void setPulicitacion(java.math.BigDecimal pulicitacion) {
		this.pulicitacion = pulicitacion;
	}

	public String getProcesonumero() {
		return procesonumero;
	}

	public void setProcesonumero(String procesonumero) {
		this.procesonumero = procesonumero;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	*/
	public String getCompaniasocio() {
		return companiasocio;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	*/
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}
	/**
	 * 
	 * 
	 * @campo NumeroContrato
	*/
	public String getNumerocontrato() {
		return numerocontrato;
	}

	/**
	 * 
	 * 
	 * @campo NumeroContrato
	*/
	public void setNumerocontrato(String numerocontrato) {
		this.numerocontrato = numerocontrato;
	}
	/**
	 * 
	 * 
	 * @campo Secuencia
	*/
	public Integer getSecuencia() {
		return secuencia;
	}

	/**
	 * 
	 * 
	 * @campo Secuencia
	*/
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}
	/**
	 * 
	 * 
	 * @campo RequisicionNumero
	*/
	public String getRequisicionnumero() {
		return requisicionnumero;
	}

	/**
	 * 
	 * 
	 * @campo RequisicionNumero
	*/
	public void setRequisicionnumero(String requisicionnumero) {
		this.requisicionnumero = requisicionnumero;
	}
	/**
	 * 
	 * 
	 * @campo RequisicionSecuencia
	*/
	public Integer getRequisicionsecuencia() {
		return requisicionsecuencia;
	}

	/**
	 * 
	 * 
	 * @campo RequisicionSecuencia
	*/
	public void setRequisicionsecuencia(Integer requisicionsecuencia) {
		this.requisicionsecuencia = requisicionsecuencia;
	}
	/**
	 * 
	 * 
	 * @campo Cantidad
	*/
	public java.math.BigDecimal getCantidad() {
		return cantidad;
	}

	/**
	 * 
	 * 
	 * @campo Cantidad
	*/
	public void setCantidad(java.math.BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	public BeanWhContratorequisicion obtenerBean() {
		BeanWhContratorequisicion bean = new BeanWhContratorequisicion();
		return obtenerBean(bean);
	}

	public BeanWhContratorequisicion obtenerBean(BeanWhContratorequisicion bean) {
		if (bean == null)
			bean = new BeanWhContratorequisicion();

		bean.getPk().setCompaniasocio(companiasocio);
		bean.getPk().setNumerocontrato(numerocontrato);
		bean.getPk().setSecuencia(secuencia);
		bean.getPk().setRequisicionnumero(requisicionnumero);
		bean.getPk().setRequisicionsecuencia(requisicionsecuencia);
		bean.setCantidad(cantidad);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
