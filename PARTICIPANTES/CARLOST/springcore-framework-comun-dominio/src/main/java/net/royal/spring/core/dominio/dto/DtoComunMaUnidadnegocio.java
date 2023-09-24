package net.royal.spring.core.dominio.dto;

import net.royal.spring.core.dominio.BeanMaUnidadnegocio;

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
public class DtoComunMaUnidadnegocio extends DominioTransaccion implements java.io.Serializable{


	private String unidadnegocio;
	private String zona;
	private String descripcionlocal;
	private String descripcioningles;
	private String direccion;
	private String telefonos;
	private String registropatronalplanilla;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String companiasocio;
	private String ledger;
	private String representantedocumento;
	private String representante;
	private Integer persona;

	/**
	 * 
	 * 
	 * @campo UnidadNegocio
	*/
	public String getUnidadnegocio() {
		return unidadnegocio;
	}

	/**
	 * 
	 * 
	 * @campo UnidadNegocio
	*/
	public void setUnidadnegocio(String unidadnegocio) {
		this.unidadnegocio = unidadnegocio;
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
	public BeanMaUnidadnegocio obtenerBean() {
		BeanMaUnidadnegocio bean = new BeanMaUnidadnegocio();
		return obtenerBean(bean);
	}

	public BeanMaUnidadnegocio obtenerBean(BeanMaUnidadnegocio bean) {
		if (bean == null)
			bean = new BeanMaUnidadnegocio();

		bean.getPk().setUnidadnegocio(unidadnegocio);
		bean.setZona(zona);
		bean.setDescripcionlocal(descripcionlocal);
		bean.setDescripcioningles(descripcioningles);
		bean.setDireccion(direccion);
		bean.setTelefonos(telefonos);
		bean.setRegistropatronalplanilla(registropatronalplanilla);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);
		bean.setCompaniasocio(companiasocio);
		bean.setLedger(ledger);
		bean.setRepresentantedocumento(representantedocumento);
		bean.setRepresentante(representante);
		bean.setPersona(persona);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
