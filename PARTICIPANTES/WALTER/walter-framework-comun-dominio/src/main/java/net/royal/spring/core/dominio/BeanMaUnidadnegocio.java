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
 * @tabla dbo.MA_UnidadNegocio
*/
@Entity
@Table(name = "MA_UNIDADNEGOCIO")
public class BeanMaUnidadnegocio extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanMaUnidadnegocioPk pk;

	@Size(min = 0, max = 4)
	@Column(name = "ZONA", length = 4, nullable = true)
	private String zona;

	@Size(min = 0, max = 30)
	@Column(name = "DESCRIPCIONLOCAL", length = 30, nullable = true)
	private String descripcionlocal;

	@Size(min = 0, max = 30)
	@Column(name = "DESCRIPCIONINGLES", length = 30, nullable = true)
	private String descripcioningles;

	@Size(min = 0, max = 1000)
	@Column(name = "DIRECCION", length = 1000, nullable = true)
	private String direccion;

	@Size(min = 0, max = 50)
	@Column(name = "TELEFONOS", length = 50, nullable = true)
	private String telefonos;

	@Size(min = 0, max = 20)
	@Column(name = "REGISTROPATRONALPLANILLA", length = 20, nullable = true)
	private String registropatronalplanilla;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@Size(min = 0, max = 10)
	@Column(name = "ULTIMOUSUARIO", length = 10, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 8)
	@Column(name = "COMPANIASOCIO", length = 8, nullable = true)
	private String companiasocio;

	@Size(min = 0, max = 2)
	@Column(name = "LEDGER", length = 2, nullable = true)
	private String ledger;

	@Size(min = 0, max = 20)
	@Column(name = "REPRESENTANTEDOCUMENTO", length = 20, nullable = true)
	private String representantedocumento;

	@Size(min = 0, max = 60)
	@Column(name = "REPRESENTANTE", length = 60, nullable = true)
	private String representante;

	@Column(name = "PERSONA", nullable = true)
	private Integer persona;


	public BeanMaUnidadnegocio() {
		pk = new BeanMaUnidadnegocioPk();
	}


	public BeanMaUnidadnegocio(BeanMaUnidadnegocioPk pk) {
		this.pk = pk;
	}

	public BeanMaUnidadnegocioPk getPk() {
		return pk;
	}

	public void setPk(BeanMaUnidadnegocioPk pk) {
		this.pk = pk;
	}
	/**
	 * 
	 * 
	 * @campo Zona
	*/
	public String getZona() {
		return zona;
	}

	/**
	 * 
	 * 
	 * @campo Zona
	*/
	public void setZona(String zona) {
		this.zona = zona;
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
	 * @campo Direccion
	*/
	public String getDireccion() {
		return direccion;
	}

	/**
	 * 
	 * 
	 * @campo Direccion
	*/
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * 
	 * 
	 * @campo Telefonos
	*/
	public String getTelefonos() {
		return telefonos;
	}

	/**
	 * 
	 * 
	 * @campo Telefonos
	*/
	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}
	/**
	 * 
	 * 
	 * @campo RegistroPatronalPlanilla
	*/
	public String getRegistropatronalplanilla() {
		return registropatronalplanilla;
	}

	/**
	 * 
	 * 
	 * @campo RegistroPatronalPlanilla
	*/
	public void setRegistropatronalplanilla(String registropatronalplanilla) {
		this.registropatronalplanilla = registropatronalplanilla;
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
	 * @campo Ledger
	*/
	public String getLedger() {
		return ledger;
	}

	/**
	 * 
	 * 
	 * @campo Ledger
	*/
	public void setLedger(String ledger) {
		this.ledger = ledger;
	}
	/**
	 * 
	 * 
	 * @campo RepresentanteDocumento
	*/
	public String getRepresentantedocumento() {
		return representantedocumento;
	}

	/**
	 * 
	 * 
	 * @campo RepresentanteDocumento
	*/
	public void setRepresentantedocumento(String representantedocumento) {
		this.representantedocumento = representantedocumento;
	}
	/**
	 * 
	 * 
	 * @campo Representante
	*/
	public String getRepresentante() {
		return representante;
	}

	/**
	 * 
	 * 
	 * @campo Representante
	*/
	public void setRepresentante(String representante) {
		this.representante = representante;
	}
	/**
	 * 
	 * 
	 * @campo Persona
	*/
	public Integer getPersona() {
		return persona;
	}

	/**
	 * 
	 * 
	 * @campo Persona
	*/
	public void setPersona(Integer persona) {
		this.persona = persona;
	}

}
