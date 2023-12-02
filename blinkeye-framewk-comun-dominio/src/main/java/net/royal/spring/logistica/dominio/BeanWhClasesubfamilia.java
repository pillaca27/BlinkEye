package net.royal.spring.logistica.dominio;

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
 * @tabla dbo.WH_ClaseSubFamilia
*/
@Entity
@Table(name = "WH_CLASESUBFAMILIA")
public class BeanWhClasesubfamilia extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanWhClasesubfamiliaPk pk;

	@Size(min = 0, max = 50)
	@Column(name = "DESCRIPCIONLOCAL", length = 50, nullable = true)
	private String descripcionlocal;

	@Size(min = 0, max = 50)
	@Column(name = "DESCRIPCIONINGLES", length = 50, nullable = true)
	private String descripcioningles;

	@Size(min = 0, max = 250)
	@Column(name = "DESCRIPCIONCOMPLETA", length = 250, nullable = true)
	private String descripcioncompleta;

	@Size(min = 0, max = 1)
	@Column(name = "ITEMTIPO", length = 1, nullable = true)
	private String itemtipo;

	@Size(min = 0, max = 3)
	@Column(name = "TRANSACCIONOPERACION", length = 3, nullable = true)
	private String transaccionoperacion;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 10)
	@Column(name = "ULTIMOUSUARIO", length = 10, nullable = true)
	private String ultimousuario;


	public BeanWhClasesubfamilia() {
		pk = new BeanWhClasesubfamiliaPk();
	}


	public BeanWhClasesubfamilia(BeanWhClasesubfamiliaPk pk) {
		this.pk = pk;
	}

	public BeanWhClasesubfamiliaPk getPk() {
		return pk;
	}

	public void setPk(BeanWhClasesubfamiliaPk pk) {
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
	 * @campo DescripcionCompleta
	*/
	public String getDescripcioncompleta() {
		return descripcioncompleta;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionCompleta
	*/
	public void setDescripcioncompleta(String descripcioncompleta) {
		this.descripcioncompleta = descripcioncompleta;
	}
	/**
	 * 
	 * 
	 * @campo ItemTipo
	*/
	public String getItemtipo() {
		return itemtipo;
	}

	/**
	 * 
	 * 
	 * @campo ItemTipo
	*/
	public void setItemtipo(String itemtipo) {
		this.itemtipo = itemtipo;
	}
	/**
	 * 
	 * 
	 * @campo TransaccionOperacion
	*/
	public String getTransaccionoperacion() {
		return transaccionoperacion;
	}

	/**
	 * 
	 * 
	 * @campo TransaccionOperacion
	*/
	public void setTransaccionoperacion(String transaccionoperacion) {
		this.transaccionoperacion = transaccionoperacion;
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

}
