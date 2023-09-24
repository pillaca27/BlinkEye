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
import net.royal.spring.wh.dominio.BeanWhContratovencimiento;

/**
 * 
 * 
 * @tabla dbo.WH_ContratoVencimiento
*/
public class DtoComunWhContratovencimiento extends DominioTransaccion implements java.io.Serializable{


	private String companiasocio;
	private String numerocontrato;
	private Integer secuencia;
	private Integer linea;
	private java.util.Date fechavencimiento;
	private java.math.BigDecimal cantidad;
	private java.math.BigDecimal cantidadreal;
	private java.math.BigDecimal preciounitario;
	private java.math.BigDecimal montoimponible;
	private java.math.BigDecimal montoimpuestos;
	private java.math.BigDecimal montototal;
	private String referenciatipodocumento;
	private String referencianumerodocumento;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String centrocosto;
	private String proyecto;
	private String camporeferencia;
	private Integer referenciasecuencia;
	private String documentoreferencia;
	private java.math.BigDecimal montototalheader;
	private java.math.BigDecimal montoimponibleheader;
	private java.math.BigDecimal montocimpuestos;
	private String accion;
	
	
	
	
	
	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public java.math.BigDecimal getMontototalheader() {
		return montototalheader;
	}

	public void setMontototalheader(java.math.BigDecimal montototalheader) {
		this.montototalheader = montototalheader;
	}

	public java.math.BigDecimal getMontoimponibleheader() {
		return montoimponibleheader;
	}

	public void setMontoimponibleheader(java.math.BigDecimal montoimponibleheader) {
		this.montoimponibleheader = montoimponibleheader;
	}

	public java.math.BigDecimal getMontocimpuestos() {
		return montocimpuestos;
	}

	public void setMontocimpuestos(java.math.BigDecimal montocimpuestos) {
		this.montocimpuestos = montocimpuestos;
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
	 * @campo Linea
	*/
	public Integer getLinea() {
		return linea;
	}

	/**
	 * 
	 * 
	 * @campo Linea
	*/
	public void setLinea(Integer linea) {
		this.linea = linea;
	}
	/**
	 * 
	 * 
	 * @campo FechaVencimiento
	*/
	public java.util.Date getFechavencimiento() {
		return fechavencimiento;
	}

	/**
	 * 
	 * 
	 * @campo FechaVencimiento
	*/
	public void setFechavencimiento(java.util.Date fechavencimiento) {
		this.fechavencimiento = fechavencimiento;
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
	/**
	 * 
	 * 
	 * @campo CantidadReal
	*/
	public java.math.BigDecimal getCantidadreal() {
		return cantidadreal;
	}

	/**
	 * 
	 * 
	 * @campo CantidadReal
	*/
	public void setCantidadreal(java.math.BigDecimal cantidadreal) {
		this.cantidadreal = cantidadreal;
	}
	/**
	 * 
	 * 
	 * @campo PrecioUnitario
	*/
	public java.math.BigDecimal getPreciounitario() {
		return preciounitario;
	}

	/**
	 * 
	 * 
	 * @campo PrecioUnitario
	*/
	public void setPreciounitario(java.math.BigDecimal preciounitario) {
		this.preciounitario = preciounitario;
	}
	/**
	 * 
	 * 
	 * @campo MontoImponible
	*/
	public java.math.BigDecimal getMontoimponible() {
		return montoimponible;
	}

	/**
	 * 
	 * 
	 * @campo MontoImponible
	*/
	public void setMontoimponible(java.math.BigDecimal montoimponible) {
		this.montoimponible = montoimponible;
	}
	/**
	 * 
	 * 
	 * @campo MontoImpuestos
	*/
	public java.math.BigDecimal getMontoimpuestos() {
		return montoimpuestos;
	}

	/**
	 * 
	 * 
	 * @campo MontoImpuestos
	*/
	public void setMontoimpuestos(java.math.BigDecimal montoimpuestos) {
		this.montoimpuestos = montoimpuestos;
	}
	/**
	 * 
	 * 
	 * @campo MontoTotal
	*/
	public java.math.BigDecimal getMontototal() {
		return montototal;
	}

	/**
	 * 
	 * 
	 * @campo MontoTotal
	*/
	public void setMontototal(java.math.BigDecimal montototal) {
		this.montototal = montototal;
	}
	/**
	 * 
	 * 
	 * @campo ReferenciaTipoDocumento
	*/
	public String getReferenciatipodocumento() {
		return referenciatipodocumento;
	}

	/**
	 * 
	 * 
	 * @campo ReferenciaTipoDocumento
	*/
	public void setReferenciatipodocumento(String referenciatipodocumento) {
		this.referenciatipodocumento = referenciatipodocumento;
	}
	/**
	 * 
	 * 
	 * @campo ReferenciaNumeroDocumento
	*/
	public String getReferencianumerodocumento() {
		return referencianumerodocumento;
	}

	/**
	 * 
	 * 
	 * @campo ReferenciaNumeroDocumento
	*/
	public void setReferencianumerodocumento(String referencianumerodocumento) {
		this.referencianumerodocumento = referencianumerodocumento;
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
	/**
	 * 
	 * 
	 * @campo CentroCosto
	*/
	public String getCentrocosto() {
		return centrocosto;
	}

	/**
	 * 
	 * 
	 * @campo CentroCosto
	*/
	public void setCentrocosto(String centrocosto) {
		this.centrocosto = centrocosto;
	}
	/**
	 * 
	 * 
	 * @campo Proyecto
	*/
	public String getProyecto() {
		return proyecto;
	}

	/**
	 * 
	 * 
	 * @campo Proyecto
	*/
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
	/**
	 * 
	 * 
	 * @campo CampoReferencia
	*/
	public String getCamporeferencia() {
		return camporeferencia;
	}

	/**
	 * 
	 * 
	 * @campo CampoReferencia
	*/
	public void setCamporeferencia(String camporeferencia) {
		this.camporeferencia = camporeferencia;
	}
	/**
	 * 
	 * 
	 * @campo ReferenciaSecuencia
	*/
	public Integer getReferenciasecuencia() {
		return referenciasecuencia;
	}

	/**
	 * 
	 * 
	 * @campo ReferenciaSecuencia
	*/
	public void setReferenciasecuencia(Integer referenciasecuencia) {
		this.referenciasecuencia = referenciasecuencia;
	}
	/**
	 * 
	 * 
	 * @campo DocumentoReferencia
	*/
	public String getDocumentoreferencia() {
		return documentoreferencia;
	}

	/**
	 * 
	 * 
	 * @campo DocumentoReferencia
	*/
	public void setDocumentoreferencia(String documentoreferencia) {
		this.documentoreferencia = documentoreferencia;
	}
	public BeanWhContratovencimiento obtenerBean() {
		BeanWhContratovencimiento bean = new BeanWhContratovencimiento();
		return obtenerBean(bean);
	}

	public BeanWhContratovencimiento obtenerBean(BeanWhContratovencimiento bean) {
		if (bean == null)
			bean = new BeanWhContratovencimiento();

		bean.getPk().setCompaniasocio(companiasocio);
		bean.getPk().setNumerocontrato(numerocontrato);
		bean.getPk().setSecuencia(secuencia);
		bean.getPk().setLinea(linea);
		bean.setFechavencimiento(fechavencimiento);
		bean.setCantidad(cantidad);
		bean.setCantidadreal(cantidadreal);
		bean.setPreciounitario(preciounitario);
		bean.setMontoimponible(montoimponible);
		bean.setMontoimpuestos(montoimpuestos);
		bean.setMontototal(montototal);
		bean.setReferenciatipodocumento(referenciatipodocumento);
		bean.setReferencianumerodocumento(referencianumerodocumento);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);
		bean.setCentrocosto(centrocosto);
		bean.setProyecto(proyecto);
		bean.setCamporeferencia(camporeferencia);
		bean.setReferenciasecuencia(referenciasecuencia);
		bean.setDocumentoreferencia(documentoreferencia);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
