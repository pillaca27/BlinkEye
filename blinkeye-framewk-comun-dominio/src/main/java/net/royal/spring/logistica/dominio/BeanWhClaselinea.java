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
 * @tabla dbo.WH_ClaseLinea
*/
@Entity
@Table(name = "WH_CLASELINEA")
public class BeanWhClaselinea extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanWhClaselineaPk pk;

	@Size(min = 0, max = 30)
	@Column(name = "DESCRIPCIONLOCAL", length = 30, nullable = true)
	private String descripcionlocal;

	@Size(min = 0, max = 30)
	@Column(name = "DESCRIPCIONINGLES", length = 30, nullable = true)
	private String descripcioningles;

	@Size(min = 0, max = 2)
	@Column(name = "GRUPOLINEA", length = 2, nullable = true)
	private String grupolinea;

	@Size(min = 0, max = 1)
	@Column(name = "MANEJOCOLORSERIEFLAG", length = 1, nullable = true)
	private String manejocolorserieflag;

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


	public BeanWhClaselinea() {
		pk = new BeanWhClaselineaPk();
	}


	public BeanWhClaselinea(BeanWhClaselineaPk pk) {
		this.pk = pk;
	}

	public BeanWhClaselineaPk getPk() {
		return pk;
	}

	public void setPk(BeanWhClaselineaPk pk) {
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
	 * @campo GrupoLinea
	*/
	public String getGrupolinea() {
		return grupolinea;
	}

	/**
	 * 
	 * 
	 * @campo GrupoLinea
	*/
	public void setGrupolinea(String grupolinea) {
		this.grupolinea = grupolinea;
	}
	/**
	 * 
	 * 
	 * @campo ManejoColorSerieFlag
	*/
	public String getManejocolorserieflag() {
		return manejocolorserieflag;
	}

	/**
	 * 
	 * 
	 * @campo ManejoColorSerieFlag
	*/
	public void setManejocolorserieflag(String manejocolorserieflag) {
		this.manejocolorserieflag = manejocolorserieflag;
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
