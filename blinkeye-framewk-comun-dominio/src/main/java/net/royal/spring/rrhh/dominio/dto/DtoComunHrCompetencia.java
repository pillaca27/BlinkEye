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
 * @tabla dbo.HR_Competencia
*/
public class DtoComunHrCompetencia extends DominioTransaccion implements java.io.Serializable{

	private Integer competencia;
	private String descripcion;
	private String descripcioningles;
	private String explicacion;
	private String area;
	private String flagdesempeno;
	private String flagseleccion;
	private String tipocalificacion;
	private String flagevaluacion;
	private java.math.BigDecimal valorminimo;
	private java.math.BigDecimal valormaximo;
	private java.math.BigDecimal valorrequerido;
	private String companyowner;
	private String maestro;
	private String maestrocompanyowner;
	private String maestronumero;
	private String maestroplantilla;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String flagcontrato;
	
	private String auxAreaNombre;
	private String auxTipoCalifNombre;

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
	 * @campo Area
	*/
	public String getArea() {
		return area;
	}

	/**
	 * 
	 * 
	 * @campo Area
	*/
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * 
	 * 
	 * @campo FlagDesempeno
	*/
	public String getFlagdesempeno() {
		return flagdesempeno;
	}

	/**
	 * 
	 * 
	 * @campo FlagDesempeno
	*/
	public void setFlagdesempeno(String flagdesempeno) {
		this.flagdesempeno = flagdesempeno;
	}
	/**
	 * 
	 * 
	 * @campo FlagSeleccion
	*/
	public String getFlagseleccion() {
		return flagseleccion;
	}

	/**
	 * 
	 * 
	 * @campo FlagSeleccion
	*/
	public void setFlagseleccion(String flagseleccion) {
		this.flagseleccion = flagseleccion;
	}
	/**
	 * 
	 * 
	 * @campo TipoCalificacion
	*/
	public String getTipocalificacion() {
		return tipocalificacion;
	}

	/**
	 * 
	 * 
	 * @campo TipoCalificacion
	*/
	public void setTipocalificacion(String tipocalificacion) {
		this.tipocalificacion = tipocalificacion;
	}
	/**
	 * 
	 * 
	 * @campo FlagEvaluacion
	*/
	public String getFlagevaluacion() {
		return flagevaluacion;
	}

	/**
	 * 
	 * 
	 * @campo FlagEvaluacion
	*/
	public void setFlagevaluacion(String flagevaluacion) {
		this.flagevaluacion = flagevaluacion;
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
	 * @campo ValorRequerido
	*/
	public java.math.BigDecimal getValorrequerido() {
		return valorrequerido;
	}

	/**
	 * 
	 * 
	 * @campo ValorRequerido
	*/
	public void setValorrequerido(java.math.BigDecimal valorrequerido) {
		this.valorrequerido = valorrequerido;
	}
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
	 * @campo Maestro
	*/
	public String getMaestro() {
		return maestro;
	}

	/**
	 * 
	 * 
	 * @campo Maestro
	*/
	public void setMaestro(String maestro) {
		this.maestro = maestro;
	}
	/**
	 * 
	 * 
	 * @campo MaestroCompanyOwner
	*/
	public String getMaestrocompanyowner() {
		return maestrocompanyowner;
	}

	/**
	 * 
	 * 
	 * @campo MaestroCompanyOwner
	*/
	public void setMaestrocompanyowner(String maestrocompanyowner) {
		this.maestrocompanyowner = maestrocompanyowner;
	}
	/**
	 * 
	 * 
	 * @campo MaestroNumero
	*/
	public String getMaestronumero() {
		return maestronumero;
	}

	/**
	 * 
	 * 
	 * @campo MaestroNumero
	*/
	public void setMaestronumero(String maestronumero) {
		this.maestronumero = maestronumero;
	}
	/**
	 * 
	 * 
	 * @campo MaestroPlantilla
	*/
	public String getMaestroplantilla() {
		return maestroplantilla;
	}

	/**
	 * 
	 * 
	 * @campo MaestroPlantilla
	*/
	public void setMaestroplantilla(String maestroplantilla) {
		this.maestroplantilla = maestroplantilla;
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
	 * @campo flagcontrato
	*/
	public String getFlagcontrato() {
		return flagcontrato;
	}

	/**
	 * 
	 * 
	 * @campo flagcontrato
	*/
	public void setFlagcontrato(String flagcontrato) {
		this.flagcontrato = flagcontrato;
	}

	public String getAuxAreaNombre() {
		return auxAreaNombre;
	}

	public void setAuxAreaNombre(String auxAreaNombre) {
		this.auxAreaNombre = auxAreaNombre;
	}

	public String getAuxTipoCalifNombre() {
		return auxTipoCalifNombre;
	}

	public void setAuxTipoCalifNombre(String auxTipoCalifNombre) {
		this.auxTipoCalifNombre = auxTipoCalifNombre;
	}

}
