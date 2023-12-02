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
 * @tabla dbo.HR_EvalEvaluacion
*/
public class DtoComunHrEvalevaluacion extends DominioTransaccion {

	private String companyowner;
	private Integer evaluacion;
	private String unidadnegocio;
	private String periodo;
	private String solicitantecompania;
	private Integer solicitante;
	private String descripcion;
	private String plantillacriterio;
	private String flagevaluacioncompetencia;
	private String flagresultado;
	private java.util.Date fechainicio;
	private java.util.Date fechafin;
	private java.util.Date fechaplaninicio;
	private java.util.Date fechaplanfin;
	private java.util.Date fecharealinicio;
	private java.util.Date fecharealfin;
	private String revcompromisocompanyowner;
	private Integer revcompromisoevaluacion;
	private String correoasunto;
	private String correodetalle;
	private String correoadjunto;
	private String correocopia;
	private String correoincluirusuario;
	private String codigoproceso;
	private Integer numeroproceso;
	private Integer nivelaprobacion;
	private String motivoAnulado;
	private String motivoSuspendido;
	private String motivoNosuspendido;
	private String estadoSuspendido;
	private String motivoRechazo;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private Integer enproceso;
	
	private String compania_desc;
	private String unidadnegocio_desc;
	private String periodo_desc;
	private String planillacriterio_Desc;
	private String evaluacioncompetencia_desc;
	private String resultado_desc;
	private String estado_desc;

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
	 * @campo Periodo
	*/
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * 
	 * 
	 * @campo Periodo
	*/
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	/**
	 * 
	 * 
	 * @campo SolicitanteCompania
	*/
	public String getSolicitantecompania() {
		return solicitantecompania;
	}

	/**
	 * 
	 * 
	 * @campo SolicitanteCompania
	*/
	public void setSolicitantecompania(String solicitantecompania) {
		this.solicitantecompania = solicitantecompania;
	}
	/**
	 * 
	 * 
	 * @campo Solicitante
	*/
	public Integer getSolicitante() {
		return solicitante;
	}

	/**
	 * 
	 * 
	 * @campo Solicitante
	*/
	public void setSolicitante(Integer solicitante) {
		this.solicitante = solicitante;
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
	 * @campo PlantillaCriterio
	*/
	public String getPlantillacriterio() {
		return plantillacriterio;
	}

	/**
	 * 
	 * 
	 * @campo PlantillaCriterio
	*/
	public void setPlantillacriterio(String plantillacriterio) {
		this.plantillacriterio = plantillacriterio;
	}
	/**
	 * 
	 * 
	 * @campo FlagEvaluacionCompetencia
	*/
	public String getFlagevaluacioncompetencia() {
		return flagevaluacioncompetencia;
	}

	/**
	 * 
	 * 
	 * @campo FlagEvaluacionCompetencia
	*/
	public void setFlagevaluacioncompetencia(String flagevaluacioncompetencia) {
		this.flagevaluacioncompetencia = flagevaluacioncompetencia;
	}
	/**
	 * 
	 * 
	 * @campo FlagResultado
	*/
	public String getFlagresultado() {
		return flagresultado;
	}

	/**
	 * 
	 * 
	 * @campo FlagResultado
	*/
	public void setFlagresultado(String flagresultado) {
		this.flagresultado = flagresultado;
	}
	/**
	 * 
	 * 
	 * @campo FechaInicio
	*/
	public java.util.Date getFechainicio() {
		return fechainicio;
	}

	/**
	 * 
	 * 
	 * @campo FechaInicio
	*/
	public void setFechainicio(java.util.Date fechainicio) {
		this.fechainicio = fechainicio;
	}
	/**
	 * 
	 * 
	 * @campo FechaFin
	*/
	public java.util.Date getFechafin() {
		return fechafin;
	}

	/**
	 * 
	 * 
	 * @campo FechaFin
	*/
	public void setFechafin(java.util.Date fechafin) {
		this.fechafin = fechafin;
	}
	/**
	 * 
	 * 
	 * @campo FechaPlanInicio
	*/
	public java.util.Date getFechaplaninicio() {
		return fechaplaninicio;
	}

	/**
	 * 
	 * 
	 * @campo FechaPlanInicio
	*/
	public void setFechaplaninicio(java.util.Date fechaplaninicio) {
		this.fechaplaninicio = fechaplaninicio;
	}
	/**
	 * 
	 * 
	 * @campo FechaPlanFin
	*/
	public java.util.Date getFechaplanfin() {
		return fechaplanfin;
	}

	/**
	 * 
	 * 
	 * @campo FechaPlanFin
	*/
	public void setFechaplanfin(java.util.Date fechaplanfin) {
		this.fechaplanfin = fechaplanfin;
	}
	/**
	 * 
	 * 
	 * @campo FechaRealInicio
	*/
	public java.util.Date getFecharealinicio() {
		return fecharealinicio;
	}

	/**
	 * 
	 * 
	 * @campo FechaRealInicio
	*/
	public void setFecharealinicio(java.util.Date fecharealinicio) {
		this.fecharealinicio = fecharealinicio;
	}
	/**
	 * 
	 * 
	 * @campo FechaRealFin
	*/
	public java.util.Date getFecharealfin() {
		return fecharealfin;
	}

	/**
	 * 
	 * 
	 * @campo FechaRealFin
	*/
	public void setFecharealfin(java.util.Date fecharealfin) {
		this.fecharealfin = fecharealfin;
	}
	/**
	 * 
	 * 
	 * @campo RevCompromisoCompanyOwner
	*/
	public String getRevcompromisocompanyowner() {
		return revcompromisocompanyowner;
	}

	/**
	 * 
	 * 
	 * @campo RevCompromisoCompanyOwner
	*/
	public void setRevcompromisocompanyowner(String revcompromisocompanyowner) {
		this.revcompromisocompanyowner = revcompromisocompanyowner;
	}
	/**
	 * 
	 * 
	 * @campo RevCompromisoEvaluacion
	*/
	public Integer getRevcompromisoevaluacion() {
		return revcompromisoevaluacion;
	}

	/**
	 * 
	 * 
	 * @campo RevCompromisoEvaluacion
	*/
	public void setRevcompromisoevaluacion(Integer revcompromisoevaluacion) {
		this.revcompromisoevaluacion = revcompromisoevaluacion;
	}
	/**
	 * 
	 * 
	 * @campo CorreoAsunto
	*/
	public String getCorreoasunto() {
		return correoasunto;
	}

	/**
	 * 
	 * 
	 * @campo CorreoAsunto
	*/
	public void setCorreoasunto(String correoasunto) {
		this.correoasunto = correoasunto;
	}
	/**
	 * 
	 * 
	 * @campo CorreoDetalle
	*/
	public String getCorreodetalle() {
		return correodetalle;
	}

	/**
	 * 
	 * 
	 * @campo CorreoDetalle
	*/
	public void setCorreodetalle(String correodetalle) {
		this.correodetalle = correodetalle;
	}
	/**
	 * 
	 * 
	 * @campo CorreoAdjunto
	*/
	public String getCorreoadjunto() {
		return correoadjunto;
	}

	/**
	 * 
	 * 
	 * @campo CorreoAdjunto
	*/
	public void setCorreoadjunto(String correoadjunto) {
		this.correoadjunto = correoadjunto;
	}
	/**
	 * 
	 * 
	 * @campo CorreoCopia
	*/
	public String getCorreocopia() {
		return correocopia;
	}

	/**
	 * 
	 * 
	 * @campo CorreoCopia
	*/
	public void setCorreocopia(String correocopia) {
		this.correocopia = correocopia;
	}
	/**
	 * 
	 * 
	 * @campo CorreoIncluirUsuario
	*/
	public String getCorreoincluirusuario() {
		return correoincluirusuario;
	}

	/**
	 * 
	 * 
	 * @campo CorreoIncluirUsuario
	*/
	public void setCorreoincluirusuario(String correoincluirusuario) {
		this.correoincluirusuario = correoincluirusuario;
	}
	/**
	 * 
	 * 
	 * @campo CodigoProceso
	*/
	public String getCodigoproceso() {
		return codigoproceso;
	}

	/**
	 * 
	 * 
	 * @campo CodigoProceso
	*/
	public void setCodigoproceso(String codigoproceso) {
		this.codigoproceso = codigoproceso;
	}
	/**
	 * 
	 * 
	 * @campo NumeroProceso
	*/
	public Integer getNumeroproceso() {
		return numeroproceso;
	}

	/**
	 * 
	 * 
	 * @campo NumeroProceso
	*/
	public void setNumeroproceso(Integer numeroproceso) {
		this.numeroproceso = numeroproceso;
	}
	/**
	 * 
	 * 
	 * @campo NivelAprobacion
	*/
	public Integer getNivelaprobacion() {
		return nivelaprobacion;
	}

	/**
	 * 
	 * 
	 * @campo NivelAprobacion
	*/
	public void setNivelaprobacion(Integer nivelaprobacion) {
		this.nivelaprobacion = nivelaprobacion;
	}
	/**
	 * 
	 * 
	 * @campo Motivo_Anulado
	*/
	public String getMotivoAnulado() {
		return motivoAnulado;
	}

	/**
	 * 
	 * 
	 * @campo Motivo_Anulado
	*/
	public void setMotivoAnulado(String motivoAnulado) {
		this.motivoAnulado = motivoAnulado;
	}
	/**
	 * 
	 * 
	 * @campo Motivo_Suspendido
	*/
	public String getMotivoSuspendido() {
		return motivoSuspendido;
	}

	/**
	 * 
	 * 
	 * @campo Motivo_Suspendido
	*/
	public void setMotivoSuspendido(String motivoSuspendido) {
		this.motivoSuspendido = motivoSuspendido;
	}
	/**
	 * 
	 * 
	 * @campo Motivo_NoSuspendido
	*/
	public String getMotivoNosuspendido() {
		return motivoNosuspendido;
	}

	/**
	 * 
	 * 
	 * @campo Motivo_NoSuspendido
	*/
	public void setMotivoNosuspendido(String motivoNosuspendido) {
		this.motivoNosuspendido = motivoNosuspendido;
	}
	/**
	 * 
	 * 
	 * @campo Estado_Suspendido
	*/
	public String getEstadoSuspendido() {
		return estadoSuspendido;
	}

	/**
	 * 
	 * 
	 * @campo Estado_Suspendido
	*/
	public void setEstadoSuspendido(String estadoSuspendido) {
		this.estadoSuspendido = estadoSuspendido;
	}
	/**
	 * 
	 * 
	 * @campo Motivo_Rechazo
	*/
	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	/**
	 * 
	 * 
	 * @campo Motivo_Rechazo
	*/
	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
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

	public Integer getEnproceso() {
		return enproceso;
	}

	public void setEnproceso(Integer enproceso) {
		this.enproceso = enproceso;
	}

	public String getCompania_desc() {
		return compania_desc;
	}

	public void setCompania_desc(String compania_desc) {
		this.compania_desc = compania_desc;
	}

	public String getUnidadnegocio_desc() {
		return unidadnegocio_desc;
	}

	public void setUnidadnegocio_desc(String unidadnegocio_desc) {
		this.unidadnegocio_desc = unidadnegocio_desc;
	}

	public String getPeriodo_desc() {
		return periodo_desc;
	}

	public void setPeriodo_desc(String periodo_desc) {
		this.periodo_desc = periodo_desc;
	}

	public String getPlanillacriterio_Desc() {
		return planillacriterio_Desc;
	}

	public void setPlanillacriterio_Desc(String planillacriterio_Desc) {
		this.planillacriterio_Desc = planillacriterio_Desc;
	}

	public String getEvaluacioncompetencia_desc() {
		return evaluacioncompetencia_desc;
	}

	public void setEvaluacioncompetencia_desc(String evaluacioncompetencia_desc) {
		this.evaluacioncompetencia_desc = evaluacioncompetencia_desc;
	}

	public String getResultado_desc() {
		return resultado_desc;
	}

	public void setResultado_desc(String resultado_desc) {
		this.resultado_desc = resultado_desc;
	}

	public String getEstado_desc() {
		return estado_desc;
	}

	public void setEstado_desc(String estado_desc) {
		this.estado_desc = estado_desc;
	}

}
