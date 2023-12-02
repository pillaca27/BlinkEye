package net.royal.spring.core.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.core.dominio.BeanMaUnidadnegocio;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;

public class DtoComunMaUnidadnegocio extends DominioTransaccion{
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
	private String representante;
	private String representantedocumento;
	private String representantecargo;
	private String representante2;
	private String representantedocumento2;
	private String representantecargo2;
	private String representante3;
	private String representantedocumento3;
	private String representantecargo3;
	private String companiasocio;
	
	@JsonIgnore
	private BigDecimal ROWNUM_;
	
	private String ledger;
	private Integer persona;
	private String uuid;	
	
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRepresentantecargo() {
		return representantecargo;
	}

	public void setRepresentantecargo(String representantecargo) {
		this.representantecargo = representantecargo;
	}

	public String getRepresentante2() {
		return representante2;
	}

	public void setRepresentante2(String representante2) {
		this.representante2 = representante2;
	}

	public String getRepresentantedocumento2() {
		return representantedocumento2;
	}

	public void setRepresentantedocumento2(String representantedocumento2) {
		this.representantedocumento2 = representantedocumento2;
	}

	public String getRepresentantecargo2() {
		return representantecargo2;
	}

	public void setRepresentantecargo2(String representantecargo2) {
		this.representantecargo2 = representantecargo2;
	}

	public String getRepresentante3() {
		return representante3;
	}

	public void setRepresentante3(String representante3) {
		this.representante3 = representante3;
	}

	public String getRepresentantedocumento3() {
		return representantedocumento3;
	}

	public void setRepresentantedocumento3(String representantedocumento3) {
		this.representantedocumento3 = representantedocumento3;
	}

	public String getRepresentantecargo3() {
		return representantecargo3;
	}

	public void setRepresentantecargo3(String representantecargo3) {
		this.representantecargo3 = representantecargo3;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

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
		return obtenerBeanCore(new BeanMaUnidadnegocio(),ConstantePantallaAccion.NINGUNO);
	}

	public BeanMaUnidadnegocio obtenerBeanRegistrar() {
		return obtenerBeanCore(new BeanMaUnidadnegocio(),ConstantePantallaAccion.INSERTAR);
	}

	public BeanMaUnidadnegocio obtenerBeanActualizar(BeanMaUnidadnegocio bean) {
		return obtenerBeanCore(bean,ConstantePantallaAccion.ACTUALIZAR);
	}

	private BeanMaUnidadnegocio obtenerBeanCore(BeanMaUnidadnegocio bean,String tipo) {
		if (UString.esNuloVacio(tipo))
			tipo=ConstantePantallaAccion.NINGUNO;

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		switch (tipo) {
		case ConstantePantallaAccion.INSERTAR:
		case ConstantePantallaAccion.NINGUNO:
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
			bean.setUuid(uuid);
			bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
			bean.setAuxFlgValidado(this.getAuxFlgValidado());
			break;
		case ConstantePantallaAccion.ACTUALIZAR:
			//bean.getPk().setUnidadnegocio(unidadnegocio);
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
			//bean.setUuid(uuid);
			//bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
			//bean.setAuxFlgValidado(this.getAuxFlgValidado());
			break;
		}

		return bean;
	}
}
