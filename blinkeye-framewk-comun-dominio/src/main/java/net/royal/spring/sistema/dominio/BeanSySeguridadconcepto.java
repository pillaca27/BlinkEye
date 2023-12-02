package net.royal.spring.sistema.dominio;

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
@Entity
@Table(name = "SY_SEGURIDADCONCEPTO")
public class BeanSySeguridadconcepto extends DominioTransaccion implements java.io.Serializable{
	@EmbeddedId
	private BeanSySeguridadconceptoPk pk;


	@Column(name = "ORDENPRESENTACION", nullable = true)
	private Integer ordenpresentacion;

	@Size(min = 0, max = 50)
	@Column(name = "DESCRIPCIONLOCAL", length = 50, nullable = true)
	private String descripcionlocal;

	@Size(min = 0, max = 50)
	@Column(name = "DESCRIPCIONINGLES", length = 50, nullable = true)
	private String descripcioningles;

	@Size(min = 0, max = 10)
	@Column(name = "CODIGOINTERNO", length = 10, nullable = true)
	private String codigointerno;

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

	public BeanSySeguridadconcepto() {
		pk = new BeanSySeguridadconceptoPk();
	}


	public BeanSySeguridadconcepto(BeanSySeguridadconceptoPk pk) {
		this.pk = pk;
	}

	public BeanSySeguridadconceptoPk getPk() {
		return pk;
	}

	public void setPk(BeanSySeguridadconceptoPk pk) {
		this.pk = pk;
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


}
