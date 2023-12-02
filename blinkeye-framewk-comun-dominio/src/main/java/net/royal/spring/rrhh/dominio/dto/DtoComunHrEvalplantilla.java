package net.royal.spring.rrhh.dominio.dto;

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
 * @tabla dbo.HR_EvalPlantilla
*/
public class DtoComunHrEvalplantilla extends DominioTransaccion implements java.io.Serializable{

	private String companyowner;
	private Integer evaluacion;
	private String plantilla;
	private String descripcion;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	private String auxMaestro;
	private String auxCompaniaNombre;
	private Date auxFechainicio;
	private Date auxFechafin;

	/**
	 * 
	 * 
	 * @campo CompanyOwner
	*/
	public String getCompanyowner() {
		return companyowner;
	}

	/**
	 * 
	 * 
	 * @campo CompanyOwner
	*/
	public void setCompanyowner(String companyowner) {
		this.companyowner = companyowner;
	}
	/**
	 * 
	 * 
	 * @campo Evaluacion
	*/
	public Integer getEvaluacion() {
		return evaluacion;
	}

	/**
	 * 
	 * 
	 * @campo Evaluacion
	*/
	public void setEvaluacion(Integer evaluacion) {
		this.evaluacion = evaluacion;
	}
	/**
	 * 
	 * 
	 * @campo Plantilla
	*/
	public String getPlantilla() {
		return plantilla;
	}

	/**
	 * 
	 * 
	 * @campo Plantilla
	*/
	public void setPlantilla(String plantilla) {
		this.plantilla = plantilla;
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

	public String getAuxMaestro() {
		return auxMaestro;
	}

	public void setAuxMaestro(String auxMaestro) {
		this.auxMaestro = auxMaestro;
	}

	public String getAuxCompaniaNombre() {
		return auxCompaniaNombre;
	}

	public void setAuxCompaniaNombre(String auxCompaniaNombre) {
		this.auxCompaniaNombre = auxCompaniaNombre;
	}

	public Date getAuxFechainicio() {
		return auxFechainicio;
	}

	public void setAuxFechainicio(Date auxFechainicio) {
		this.auxFechainicio = auxFechainicio;
	}

	public Date getAuxFechafin() {
		return auxFechafin;
	}

	public void setAuxFechafin(Date auxFechafin) {
		this.auxFechafin = auxFechafin;
	}

}
