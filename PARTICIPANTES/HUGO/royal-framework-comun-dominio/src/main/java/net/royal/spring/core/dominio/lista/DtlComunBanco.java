package net.royal.spring.core.dominio.lista;

import net.royal.spring.core.dominio.BeanBanco;

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
 * @tabla dbo.Banco
*/
public class DtlComunBanco extends DominioTransaccion implements java.io.Serializable{


	private String banco;
	private String descripcioncorta;
	private String descripcionlarga;
	private Integer banconumero;
	private String personacontacto;
	private String conciliacionautomaticaflag;
	private String formatopropioflag;
	private String formatodatawindow;
	private String estado;
	private java.util.Date ultimafechamodif;
	private byte[] timestamp;
	private String ultimousuario;
	private String conciliacionformatoflag;
	private String codigointerfaseafp;
	private String disponibleedflag;
	private Float tasaefectivaanual;
	private String codigofiscal;
	private String estadodescripcion;
	
	public String getEstadodescripcion() {
		return estadodescripcion;
	}
	public void setEstadodescripcion(String estadodescripcion) {
		this.estadodescripcion = estadodescripcion;
	}
	
	/**
	 * 
	 * 
	 * @campo Banco
	*/
	public String getBanco() {
		return banco;
	}

	/**
	 * 
	 * 
	 * @campo Banco
	*/
	public void setBanco(String banco) {
		this.banco = banco;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionCorta
	*/
	public String getDescripcioncorta() {
		return descripcioncorta;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionCorta
	*/
	public void setDescripcioncorta(String descripcioncorta) {
		this.descripcioncorta = descripcioncorta;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionLarga
	*/
	public String getDescripcionlarga() {
		return descripcionlarga;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionLarga
	*/
	public void setDescripcionlarga(String descripcionlarga) {
		this.descripcionlarga = descripcionlarga;
	}
	/**
	 * 
	 * 
	 * @campo BancoNumero
	*/
	public Integer getBanconumero() {
		return banconumero;
	}

	/**
	 * 
	 * 
	 * @campo BancoNumero
	*/
	public void setBanconumero(Integer banconumero) {
		this.banconumero = banconumero;
	}
	/**
	 * 
	 * 
	 * @campo PersonaContacto
	*/
	public String getPersonacontacto() {
		return personacontacto;
	}

	/**
	 * 
	 * 
	 * @campo PersonaContacto
	*/
	public void setPersonacontacto(String personacontacto) {
		this.personacontacto = personacontacto;
	}
	/**
	 * 
	 * 
	 * @campo ConciliacionAutomaticaFlag
	*/
	public String getConciliacionautomaticaflag() {
		return conciliacionautomaticaflag;
	}

	/**
	 * 
	 * 
	 * @campo ConciliacionAutomaticaFlag
	*/
	public void setConciliacionautomaticaflag(String conciliacionautomaticaflag) {
		this.conciliacionautomaticaflag = conciliacionautomaticaflag;
	}
	/**
	 * 
	 * 
	 * @campo FormatoPropioFlag
	*/
	public String getFormatopropioflag() {
		return formatopropioflag;
	}

	/**
	 * 
	 * 
	 * @campo FormatoPropioFlag
	*/
	public void setFormatopropioflag(String formatopropioflag) {
		this.formatopropioflag = formatopropioflag;
	}
	/**
	 * 
	 * 
	 * @campo FormatoDatawindow
	*/
	public String getFormatodatawindow() {
		return formatodatawindow;
	}

	/**
	 * 
	 * 
	 * @campo FormatoDatawindow
	*/
	public void setFormatodatawindow(String formatodatawindow) {
		this.formatodatawindow = formatodatawindow;
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
	 * @campo Timestamp
	*/
	public byte[] getTimestamp() {
		return timestamp;
	}

	/**
	 * 
	 * 
	 * @campo Timestamp
	*/
	public void setTimestamp(byte[] timestamp) {
		this.timestamp = timestamp;
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
	 * @campo ConciliacionFormatoFlag
	*/
	public String getConciliacionformatoflag() {
		return conciliacionformatoflag;
	}

	/**
	 * 
	 * 
	 * @campo ConciliacionFormatoFlag
	*/
	public void setConciliacionformatoflag(String conciliacionformatoflag) {
		this.conciliacionformatoflag = conciliacionformatoflag;
	}
	/**
	 * 
	 * 
	 * @campo CODIGOINTERFASEAFP
	*/
	public String getCodigointerfaseafp() {
		return codigointerfaseafp;
	}

	/**
	 * 
	 * 
	 * @campo CODIGOINTERFASEAFP
	*/
	public void setCodigointerfaseafp(String codigointerfaseafp) {
		this.codigointerfaseafp = codigointerfaseafp;
	}
	/**
	 * 
	 * 
	 * @campo DisponibleEDflag
	*/
	public String getDisponibleedflag() {
		return disponibleedflag;
	}

	/**
	 * 
	 * 
	 * @campo DisponibleEDflag
	*/
	public void setDisponibleedflag(String disponibleedflag) {
		this.disponibleedflag = disponibleedflag;
	}
	/**
	 * 
	 * 
	 * @campo TasaEfectivaAnual
	*/
	public Float getTasaefectivaanual() {
		return tasaefectivaanual;
	}

	/**
	 * 
	 * 
	 * @campo TasaEfectivaAnual
	*/
	public void setTasaefectivaanual(Float tasaefectivaanual) {
		this.tasaefectivaanual = tasaefectivaanual;
	}
	/**
	 * 
	 * 
	 * @campo CodigoFiscal
	*/
	public String getCodigofiscal() {
		return codigofiscal;
	}

	/**
	 * 
	 * 
	 * @campo CodigoFiscal
	*/
	public void setCodigofiscal(String codigofiscal) {
		this.codigofiscal = codigofiscal;
	}

}
