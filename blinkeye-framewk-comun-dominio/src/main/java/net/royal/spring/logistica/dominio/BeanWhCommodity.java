package net.royal.spring.logistica.dominio;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

/**
 * 
 * 
 * @tabla dbo.WH_Commodity
*/
@Entity
@Table(name = "WH_COMMODITY")
public class BeanWhCommodity extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanWhCommodityPk pk;

	@Size(min = 0, max = 30)
	@Column(name = "DESCRIPCIONLOCAL", length = 30, nullable = true)
	private String descripcionlocal;

	@Size(min = 0, max = 30)
	@Column(name = "DESCRIPCIONINGLES", length = 30, nullable = true)
	private String descripcioningles;

	@Size(min = 0, max = 3)
	@Column(name = "CLASIFICACION", length = 3, nullable = true)
	private String clasificacion;

	@Size(min = 0, max = 1)
	@Column(name = "CODIGOBARRASFLAG", length = 1, nullable = true)
	private String codigobarrasflag;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@Size(min = 0, max = 10)
	@Column(name = "ULTIMOUSUARIO", length = 10, nullable = true)
	private String ultimousuario;

	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;


	@Size(min = 0, max = 30)
	@Column(name = "XDESCRIPCIONLOCAL", length = 30, nullable = true)
	private String xdescripcionlocal;


	public BeanWhCommodity() {
		pk = new BeanWhCommodityPk();
	}


	public BeanWhCommodity(BeanWhCommodityPk pk) {
		this.pk = pk;
	}

	public BeanWhCommodityPk getPk() {
		return pk;
	}

	public void setPk(BeanWhCommodityPk pk) {
		this.pk = pk;
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
	 * @campo Clasificacion
	*/
	public String getClasificacion() {
		return clasificacion;
	}

	/**
	 * 
	 * 
	 * @campo Clasificacion
	*/
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	/**
	 * 
	 * 
	 * @campo CodigoBarrasFlag
	*/
	public String getCodigobarrasflag() {
		return codigobarrasflag;
	}

	/**
	 * 
	 * 
	 * @campo CodigoBarrasFlag
	*/
	public void setCodigobarrasflag(String codigobarrasflag) {
		this.codigobarrasflag = codigobarrasflag;
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

	public String getXdescripcionlocal() {
		return xdescripcionlocal;
	}

	/**
	 * 
	 * 
	 * @campo xDescripcionLocal
	*/
	public void setXdescripcionlocal(String xdescripcionlocal) {
		this.xdescripcionlocal = xdescripcionlocal;
	}

}
