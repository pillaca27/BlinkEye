package net.royal.spring.core.dominio;

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
@Entity
@Table(name = "BANCO")
public class BeanBanco extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanBancoPk pk;

	@Size(min = 0, max = 20)
	@Column(name = "DESCRIPCIONCORTA", length = 20, nullable = true)
	private String descripcioncorta;

	@Size(min = 0, max = 40)
	@Column(name = "DESCRIPCIONLARGA", length = 40, nullable = true)
	private String descripcionlarga;

	@Column(name = "BANCONUMERO", nullable = true)
	private Integer banconumero;

	@Size(min = 0, max = 30)
	@Column(name = "PERSONACONTACTO", length = 30, nullable = true)
	private String personacontacto;

	@Size(min = 0, max = 1)
	@Column(name = "CONCILIACIONAUTOMATICAFLAG", length = 1, nullable = true)
	private String conciliacionautomaticaflag;

	@Size(min = 0, max = 1)
	@Column(name = "FORMATOPROPIOFLAG", length = 1, nullable = true)
	private String formatopropioflag;

	@Size(min = 0, max = 50)
	@Column(name = "FORMATODATAWINDOW", length = 50, nullable = true)
	private String formatodatawindow;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;


	@Size(min = 0, max = 10)
	@Column(name = "ULTIMOUSUARIO", length = 10, nullable = true)
	private String ultimousuario;

	@Size(min = 0, max = 1)
	@Column(name = "CONCILIACIONFORMATOFLAG", length = 1, nullable = true)
	private String conciliacionformatoflag;

	@Size(min = 0, max = 3)
	@Column(name = "CODIGOINTERFASEAFP", length = 3, nullable = true)
	private String codigointerfaseafp;

	@Size(min = 0, max = 1)
	@Column(name = "DISPONIBLEEDFLAG", length = 1, nullable = true)
	private String disponibleedflag;

	@Column(name = "TASAEFECTIVAANUAL", nullable = true)
	private Float tasaefectivaanual;

	@Size(min = 0, max = 2)
	@Column(name = "CODIGOFISCAL", length = 2, nullable = true)
	private String codigofiscal;


	public BeanBanco() {
		pk = new BeanBancoPk();
	}


	public BeanBanco(BeanBancoPk pk) {
		this.pk = pk;
	}

	public BeanBancoPk getPk() {
		return pk;
	}

	public void setPk(BeanBancoPk pk) {
		this.pk = pk;
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
