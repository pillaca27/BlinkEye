package net.royal.spring.seguridad.dominio;

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

import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.SeguridadGrupo
*/
@Entity
@Table(name = "SEGURIDADGRUPO")
public class Seguridadgrupo extends DominioTransaccion implements java.io.Serializable{
	@EmbeddedId
	private SeguridadgrupoPk pk;


	@Size(min = 0, max = 30)
	@Column(name = "DESCRIPCION", length = 30, nullable = true)
	private String descripcion;

	@Size(min = 0, max = 10)
	@Column(name = "ULTIMOUSUARIO", length = 10, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Column(name = "ORDEN", nullable = true)
	private Integer orden;

	@Size(min = 0, max = 200)
	@Column(name = "IMAGEN", length = 200, nullable = true)
	private String imagen;

	@Size(min = 0, max = 10)
	@Column(name = "WEBGRUPO", length = 10, nullable = true)
	private String webgrupo;

	@Size(min = 0, max = 10)
	@Column(name = "WEBGRUPOSECUENCIA", length = 10, nullable = true)
	private String webgruposecuencia;

	@Size(min = 0, max = 6)
	@Column(name = "GRUPOPADRE", length = 6, nullable = true)
	private String grupopadre;

	public Seguridadgrupo() {
		pk = new SeguridadgrupoPk();
	}


	public Seguridadgrupo(SeguridadgrupoPk pk) {
		this.pk = pk;
	}

	public SeguridadgrupoPk getPk() {
		return pk;
	}

	public void setPk(SeguridadgrupoPk pk) {
		this.pk = pk;
	}

	/**
	 * 
	 * 
	 * @campo Descripcion
	*/
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * 
	 * 
	 * @campo Descripcion
	*/
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	 * @campo ORDEN
	*/
	public Integer getOrden() {
		return orden;
	}

	/**
	 * 
	 * 
	 * @campo ORDEN
	*/
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	/**
	 * 
	 * 
	 * @campo imagen
	*/
	public String getImagen() {
		return imagen;
	}

	/**
	 * 
	 * 
	 * @campo imagen
	*/
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	/**
	 * 
	 * 
	 * @campo webgrupo
	*/
	public String getWebgrupo() {
		return webgrupo;
	}

	/**
	 * 
	 * 
	 * @campo webgrupo
	*/
	public void setWebgrupo(String webgrupo) {
		this.webgrupo = webgrupo;
	}
	/**
	 * 
	 * 
	 * @campo WebGrupoSecuencia
	*/
	public String getWebgruposecuencia() {
		return webgruposecuencia;
	}

	/**
	 * 
	 * 
	 * @campo WebGrupoSecuencia
	*/
	public void setWebgruposecuencia(String webgruposecuencia) {
		this.webgruposecuencia = webgruposecuencia;
	}
	/**
	 * 
	 * 
	 * @campo GrupoPadre
	*/
	public String getGrupopadre() {
		return grupopadre;
	}

	/**
	 * 
	 * 
	 * @campo GrupoPadre
	*/
	public void setGrupopadre(String grupopadre) {
		this.grupopadre = grupopadre;
	}


}
