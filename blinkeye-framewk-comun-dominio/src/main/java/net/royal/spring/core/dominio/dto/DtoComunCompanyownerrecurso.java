package net.royal.spring.core.dominio.dto;

import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunCompanyownerrecurso extends DominioTransaccion {

	public String nombrerecurso;
	public String companyowner;
	public String tiporecurso;
	public String periodo;
	public String nombreCompania;
	public String estado;
	public String AuxCompanyowner;


	public String ultimousuario;
	public Date ultimafechamodif;
	public String auxString;
	
	
	public String getNombrerecurso() {
		return nombrerecurso;
	}
	public void setNombrerecurso(String nombrerecurso) {
		this.nombrerecurso = nombrerecurso;
	}
	public String getCompanyowner() {
		return companyowner;
	}
	public void setCompanyowner(String companyowner) {
		this.companyowner = companyowner;
	}
	public String getTiporecurso() {
		return tiporecurso;
	}
	public void setTiporecurso(String tiporecurso) {
		this.tiporecurso = tiporecurso;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getNombreCompania() {
		return nombreCompania;
	}
	public void setNombreCompania(String nombreCompania) {
		this.nombreCompania = nombreCompania;
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
	public Date getUltimafechamodif() {
		return ultimafechamodif;
	}
	public void setUltimafechamodif(Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	public String getAuxString() {
		return auxString;
	}
	public void setAuxString(String auxString) {
		this.auxString = auxString;
	}
	public String getAuxCompanyowner() {
		return AuxCompanyowner;
	}
	public void setAuxCompanyowner(String auxCompanyowner) {
		AuxCompanyowner = auxCompanyowner;
	} 	
	
	
}
