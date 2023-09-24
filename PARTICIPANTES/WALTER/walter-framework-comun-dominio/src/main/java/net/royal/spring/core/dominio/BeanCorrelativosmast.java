package net.royal.spring.core.dominio;

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
@Entity
@Table(name = "CORRELATIVOSMAST")
public class BeanCorrelativosmast extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanCorrelativosmastPk pk;

	@Size(min = 0, max = 30)
	@Column(name = "DESCRIPCION", length = 30, nullable = true)
	private String descripcion;

	@Column(name = "CORRELATIVONUMERO", nullable = true)
	private BigDecimal correlativonumero;

	@Column(name = "CORRELATIVODESDE", nullable = true)
	private BigDecimal correlativodesde;

	@Column(name = "CORRELATIVOHASTA", nullable = true)
	private BigDecimal correlativohasta;

	@Size(min = 0, max = 4)
	@Column(name = "ALMACENCODIGO", length = 4, nullable = true)
	private String almacencodigo;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@Size(min = 0, max = 20)
	@Column(name = "ULTIMOUSUARIO", length = 20, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 1)
	@Column(name = "FEFLAG", length = 1, nullable = true)
	private String feflag;

	@Size(min = 0, max = 2)
	@Column(name = "FETIPOCOMPROBANTEREF", length = 2, nullable = true)
	private String fetipocomprobanteref;

	@Size(min = 0, max = 1)
	@Column(name = "FEENVIO", length = 1, nullable = true)
	private String feenvio;
	
	
	

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


	public BeanCorrelativosmast() {
		pk = new BeanCorrelativosmastPk();
	}


	public BeanCorrelativosmast(BeanCorrelativosmastPk pk) {
		this.pk = pk;
	}

	public BeanCorrelativosmastPk getPk() {
		return pk;
	}

	public void setPk(BeanCorrelativosmastPk pk) {
		this.pk = pk;
	}
	/**
	 * Descripcion Local
	 * 
	 * @campo DESCRIPCION
	*/
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Descripcion Local
	 * 
	 * @campo DESCRIPCION
	*/
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * Numero del Correlativo
	 * 
	 * @campo CORRELATIVONUMERO
	*/
	public BigDecimal getCorrelativonumero() {
		return correlativonumero;
	}

	/**
	 * Numero del Correlativo
	 * 
	 * @campo CORRELATIVONUMERO
	*/
	public void setCorrelativonumero(BigDecimal correlativonumero) {
		this.correlativonumero = correlativonumero;
	}
	/**
	 * Rango de Inicio del Correlativo
	 * 
	 * @campo CORRELATIVODESDE
	*/
	public BigDecimal getCorrelativodesde() {
		return correlativodesde;
	}

	/**
	 * Rango de Inicio del Correlativo
	 * 
	 * @campo CORRELATIVODESDE
	*/
	public void setCorrelativodesde(BigDecimal correlativodesde) {
		this.correlativodesde = correlativodesde;
	}
	/**
	 * Rango Final del Correlativo
	 * 
	 * @campo CORRELATIVOHASTA
	*/
	public BigDecimal getCorrelativohasta() {
		return correlativohasta;
	}

	/**
	 * Rango Final del Correlativo
	 * 
	 * @campo CORRELATIVOHASTA
	*/
	public void setCorrelativohasta(BigDecimal correlativohasta) {
		this.correlativohasta = correlativohasta;
	}
	/**
	 * Es el codigo del almacen al cual esta relacionada la informacion contenida en este registro
	 * 
	 * @campo ALMACENCODIGO
	*/
	public String getAlmacencodigo() {
		return almacencodigo;
	}

	/**
	 * Es el codigo del almacen al cual esta relacionada la informacion contenida en este registro
	 * 
	 * @campo ALMACENCODIGO
	*/
	public void setAlmacencodigo(String almacencodigo) {
		this.almacencodigo = almacencodigo;
	}
	/**
	 * A = Activo , I = Inactivo
	 * 
	 * @campo ESTADO
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * A = Activo , I = Inactivo
	 * 
	 * @campo ESTADO
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * Ultimo Usuario de Modificacion
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * Ultimo Usuario de Modificacion
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	/**
	 * Ultima Fecha de Modificacion
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * Ultima Fecha de Modificacion
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	

}
