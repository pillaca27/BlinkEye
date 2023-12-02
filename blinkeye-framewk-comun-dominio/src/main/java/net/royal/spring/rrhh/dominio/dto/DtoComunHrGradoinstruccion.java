package net.royal.spring.rrhh.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunHrGradoinstruccion extends DominioTransaccion{
	private String gradoinstruccion;
	private String descripcion;
	private String flagcentroestudios;
	private String estado;
	private String ultimousuario;
	private java.util.Date uitimafechamodif;	
	private String flagtienecarrera;
	private BigDecimal jerarquia;	
	private String flagmuestraduracion;
	private String gradocuantitativo;
	private BigDecimal puntosgrado;
	private BigDecimal porcentajegrado;
	private String niveleducativortps;
	private String flagtermino;
	private String tipomaestro;
	
	@JsonIgnore
	private BigDecimal ROWNUM_;
	
	public DtoComunHrGradoinstruccion() {}
	public DtoComunHrGradoinstruccion(String gradoinstruccion) {
		this.gradoinstruccion=gradoinstruccion;
	}
	
	public String getGradoinstruccion() {
		return gradoinstruccion;
	}
	public void setGradoinstruccion(String gradoinstruccion) {
		this.gradoinstruccion = gradoinstruccion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFlagcentroestudios() {
		return flagcentroestudios;
	}
	public void setFlagcentroestudios(String flagcentroestudios) {
		this.flagcentroestudios = flagcentroestudios;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUltimousuario() {
		return ultimousuario;
	}
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	public java.util.Date getUitimafechamodif() {
		return uitimafechamodif;
	}
	public void setUitimafechamodif(java.util.Date uitimafechamodif) {
		this.uitimafechamodif = uitimafechamodif;
	}
	public String getFlagtienecarrera() {
		return flagtienecarrera;
	}
	public void setFlagtienecarrera(String flagtienecarrera) {
		this.flagtienecarrera = flagtienecarrera;
	}
	public BigDecimal getJerarquia() {
		return jerarquia;
	}
	public void setJerarquia(BigDecimal jerarquia) {
		this.jerarquia = jerarquia;
	}
	public String getFlagmuestraduracion() {
		return flagmuestraduracion;
	}
	public void setFlagmuestraduracion(String flagmuestraduracion) {
		this.flagmuestraduracion = flagmuestraduracion;
	}
	public String getGradocuantitativo() {
		return gradocuantitativo;
	}
	public void setGradocuantitativo(String gradocuantitativo) {
		this.gradocuantitativo = gradocuantitativo;
	}
	public BigDecimal getPuntosgrado() {
		return puntosgrado;
	}
	public void setPuntosgrado(BigDecimal puntosgrado) {
		this.puntosgrado = puntosgrado;
	}
	public BigDecimal getPorcentajegrado() {
		return porcentajegrado;
	}
	public void setPorcentajegrado(BigDecimal porcentajegrado) {
		this.porcentajegrado = porcentajegrado;
	}
	public String getNiveleducativortps() {
		return niveleducativortps;
	}
	public void setNiveleducativortps(String niveleducativortps) {
		this.niveleducativortps = niveleducativortps;
	}
	public String getFlagtermino() {
		return flagtermino;
	}
	public void setFlagtermino(String flagtermino) {
		this.flagtermino = flagtermino;
	}
	public String getTipomaestro() {
		return tipomaestro;
	}
	public void setTipomaestro(String tipomaestro) {
		this.tipomaestro = tipomaestro;
	}
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}	
	
}
