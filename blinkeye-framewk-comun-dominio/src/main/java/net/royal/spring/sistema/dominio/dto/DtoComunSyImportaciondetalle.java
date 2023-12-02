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
public class DtoComunSyImportaciondetalle extends DominioTransaccion implements java.io.Serializable{
	private String aplicacioncodigo;
	private String tablacodigo;
	private BigDecimal linea;
	private String campo;
	private String descripcioncampo;
	private String tipodato;
	private BigDecimal longitud;
	private BigDecimal longituddecimales;
	private String formato;
	private String obligatorioflag;
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
	public BigDecimal getLinea() {
		return linea;
	}
	public void setLinea(BigDecimal linea) {
		this.linea = linea;
	}
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public String getDescripcioncampo() {
		return descripcioncampo;
	}
	public void setDescripcioncampo(String descripcioncampo) {
		this.descripcioncampo = descripcioncampo;
	}
	public String getTipodato() {
		return tipodato;
	}
	public void setTipodato(String tipodato) {
		this.tipodato = tipodato;
	}
	public BigDecimal getLongitud() {
		return longitud;
	}
	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}
	public BigDecimal getLongituddecimales() {
		return longituddecimales;
	}
	public void setLongituddecimales(BigDecimal longituddecimales) {
		this.longituddecimales = longituddecimales;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public String getObligatorioflag() {
		return obligatorioflag;
	}
	public void setObligatorioflag(String obligatorioflag) {
		this.obligatorioflag = obligatorioflag;
	}

	
}
