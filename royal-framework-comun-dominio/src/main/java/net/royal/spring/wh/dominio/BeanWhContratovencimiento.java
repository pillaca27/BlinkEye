package net.royal.spring.wh.dominio;

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
 * @tabla dbo.WH_ContratoVencimiento
*/
@Entity
@Table(name = "WH_CONTRATOVENCIMIENTO")
public class BeanWhContratovencimiento extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanWhContratovencimientoPk pk;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHAVENCIMIENTO", nullable = true)
	private java.util.Date fechavencimiento;

	@Column(name = "CANTIDAD", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal cantidad;

	@Column(name = "CANTIDADREAL", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal cantidadreal;

	@Column(name = "PRECIOUNITARIO", precision = 16,scale =6, nullable = true)
	private java.math.BigDecimal preciounitario;

	@Column(name = "MONTOIMPONIBLE", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal montoimponible;

	@Column(name = "MONTOIMPUESTOS", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal montoimpuestos;

	@Column(name = "MONTOTOTAL", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal montototal;

	@Size(min = 0, max = 2)
	@Column(name = "REFERENCIATIPODOCUMENTO", length = 2, nullable = true)
	private String referenciatipodocumento;

	@Size(min = 0, max = 10)
	@Column(name = "REFERENCIANUMERODOCUMENTO", length = 10, nullable = true)
	private String referencianumerodocumento;

	@Size(min = 0, max = 2)
	@Column(name = "ESTADO", length = 2, nullable = true)
	private String estado;

	@Size(min = 0, max = 20)
	@Column(name = "ULTIMOUSUARIO", length = 20, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 10)
	@Column(name = "CENTROCOSTO", length = 10, nullable = true)
	private String centrocosto;

	@Size(min = 0, max = 15)
	@Column(name = "PROYECTO", length = 15, nullable = true)
	private String proyecto;

	@Size(min = 0, max = 12)
	@Column(name = "CAMPOREFERENCIA", length = 12, nullable = true)
	private String camporeferencia;

	@Column(name = "REFERENCIASECUENCIA", nullable = true)
	private Integer referenciasecuencia;

	@Size(min = 0, max = 20)
	@Column(name = "DOCUMENTOREFERENCIA", length = 20, nullable = true)
	private String documentoreferencia;


	public BeanWhContratovencimiento() {
		pk = new BeanWhContratovencimientoPk();
	}


	public BeanWhContratovencimiento(BeanWhContratovencimientoPk pk) {
		this.pk = pk;
	}

	public BeanWhContratovencimientoPk getPk() {
		return pk;
	}

	public void setPk(BeanWhContratovencimientoPk pk) {
		this.pk = pk;
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

}
