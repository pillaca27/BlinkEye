package net.royal.spring.core.dominio;

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
 * @tabla dbo.MA_PersonaGrupo
*/
@Entity
@Table(name = "MA_PERSONAGRUPO")
public class BeanMaPersonagrupo extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanMaPersonagrupoPk pk;

	@Size(min = 0, max = 50)
	@Column(name = "DESCRIPCIONLOCAL", length = 50, nullable = true)
	private String descripcionlocal;

	@Size(min = 0, max = 50)
	@Column(name = "DESCRIPCIONINGLES", length = 50, nullable = true)
	private String descripcioningles;

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

	@Size(min = 0, max = 15)
	@Column(name = "CODIGOINTERNO", length = 15, nullable = true)
	private String codigointerno;

	@Size(min = 0, max = 1)
	@Column(name = "GENERARNUMERACIONFLAG", length = 1, nullable = true)
	private String generarnumeracionflag;


	public BeanMaPersonagrupo() {
		pk = new BeanMaPersonagrupoPk();
	}


	public BeanMaPersonagrupo(BeanMaPersonagrupoPk pk) {
		this.pk = pk;
	}

	public BeanMaPersonagrupoPk getPk() {
		return pk;
	}

	public void setPk(BeanMaPersonagrupoPk pk) {
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
	 * @campo GenerarNumeracionFlag
	*/
	public String getGenerarnumeracionflag() {
		return generarnumeracionflag;
	}

	/**
	 * 
	 * 
	 * @campo GenerarNumeracionFlag
	*/
	public void setGenerarnumeracionflag(String generarnumeracionflag) {
		this.generarnumeracionflag = generarnumeracionflag;
	}

}
