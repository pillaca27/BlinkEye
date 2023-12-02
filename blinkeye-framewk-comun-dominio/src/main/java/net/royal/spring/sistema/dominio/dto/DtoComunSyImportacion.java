package net.royal.spring.sistema.dominio.dto;

import net.royal.spring.sistema.dominio.BeanSyReporte;

import java.math.BigDecimal;
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
 * Tabla de tipo de sistema reporte
 * 
 * @tabla SY_REPORTE
*/
public class DtoComunSyImportacion extends DominioTransaccion implements java.io.Serializable{
	private String aplicacioncodigo;
	private String tablacodigo;
	private String descripcionlocal;
	private String descripcioningles;
	private String tipoformatoflag;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}
	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	public String getTablacodigo() {
		return tablacodigo;
	}
	public void setTablacodigo(String tablacodigo) {
		this.tablacodigo = tablacodigo;
	}
	public String getDescripcionlocal() {
		return descripcionlocal;
	}
	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}
	public String getDescripcioningles() {
		return descripcioningles;
	}
	public void setDescripcioningles(String descripcioningles) {
		this.descripcioningles = descripcioningles;
	}
	public String getTipoformatoflag() {
		return tipoformatoflag;
	}
	public void setTipoformatoflag(String tipoformatoflag) {
		this.tipoformatoflag = tipoformatoflag;
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
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	
	
}
