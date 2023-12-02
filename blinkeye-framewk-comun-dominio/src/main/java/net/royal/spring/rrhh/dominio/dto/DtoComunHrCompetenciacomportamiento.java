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
 * @tabla dbo.HR_CompetenciaComportamiento
*/
public class DtoComunHrCompetenciacomportamiento extends DominioTransaccion implements java.io.Serializable{

	private Integer competencia;
	private Integer comportamiento;
	private String descripcion;
	private String explicacion;
	private Integer orden;
	private java.math.BigDecimal valorminimo;
	private java.math.BigDecimal valormaximo;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	private String auxMaestroPlantilla;
	private String auxFlagEvaluacion;
	private String auxFlagDesempeno;
	private String auxFlagSeleccion;

	/**
	 * 
	 * 
	 * @campo Competencia
	*/
	public Integer getCompetencia() {
		return competencia;
	}

	/**
	 * 
	 * 
	 * @campo Competencia
	*/
	public void setCompetencia(Integer competencia) {
		this.competencia = competencia;
	}
	/**
	 * 
	 * 
	 * @campo Comportamiento
	*/
	public Integer getComportamiento() {
		return comportamiento;
	}

	/**
	 * 
	 * 
	 * @campo Comportamiento
	*/
	public void setComportamiento(Integer comportamiento) {
		this.comportamiento = comportamiento;
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
	 * @campo Explicacion
	*/
	public String getExplicacion() {
		return explicacion;
	}

	/**
	 * 
	 * 
	 * @campo Explicacion
	*/
	public void setExplicacion(String explicacion) {
		this.explicacion = explicacion;
	}
	/**
	 * 
	 * 
	 * @campo Orden
	*/
	public Integer getOrden() {
		return orden;
	}

	/**
	 * 
	 * 
	 * @campo Orden
	*/
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	/**
	 * 
	 * 
	 * @campo ValorMinimo
	*/
	public java.math.BigDecimal getValorminimo() {
		return valorminimo;
	}

	/**
	 * 
	 * 
	 * @campo ValorMinimo
	*/
	public void setValorminimo(java.math.BigDecimal valorminimo) {
		this.valorminimo = valorminimo;
	}
	/**
	 * 
	 * 
	 * @campo ValorMaximo
	*/
	public java.math.BigDecimal getValormaximo() {
		return valormaximo;
	}

	/**
	 * 
	 * 
	 * @campo ValorMaximo
	*/
	public void setValormaximo(java.math.BigDecimal valormaximo) {
		this.valormaximo = valormaximo;
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

	public String getAuxMaestroPlantilla() {
		return auxMaestroPlantilla;
	}

	public void setAuxMaestroPlantilla(String auxMaestroPlantilla) {
		this.auxMaestroPlantilla = auxMaestroPlantilla;
	}

	public String getAuxFlagEvaluacion() {
		return auxFlagEvaluacion;
	}

	public void setAuxFlagEvaluacion(String auxFlagEvaluacion) {
		this.auxFlagEvaluacion = auxFlagEvaluacion;
	}

	public String getAuxFlagDesempeno() {
		return auxFlagDesempeno;
	}

	public void setAuxFlagDesempeno(String auxFlagDesempeno) {
		this.auxFlagDesempeno = auxFlagDesempeno;
	}

	public String getAuxFlagSeleccion() {
		return auxFlagSeleccion;
	}

	public void setAuxFlagSeleccion(String auxFlagSeleccion) {
		this.auxFlagSeleccion = auxFlagSeleccion;
	}

}
