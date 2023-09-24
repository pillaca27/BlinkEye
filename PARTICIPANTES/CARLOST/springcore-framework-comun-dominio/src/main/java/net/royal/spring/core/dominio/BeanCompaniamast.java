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
 * @tabla dbo.CompaniaMast
*/
@Entity
@Table(name = "COMPANIAMAST")
public class BeanCompaniamast extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanCompaniamastPk pk;

	@Size(min = 0, max = 40)
	@Column(name = "DESCRIPCIONCORTA", length = 40, nullable = true)
	private String descripcioncorta;

	@Size(min = 0, max = 60)
	@Column(name = "DESCRIPCIONLARGA", length = 60, nullable = true)
	private String descripcionlarga;

	@Size(min = 0, max = 80)
	@Column(name = "DIRECCIONCOMUN", length = 80, nullable = true)
	private String direccioncomun;

	@Size(min = 0, max = 80)
	@Column(name = "DIRECCIONADICIONAL", length = 80, nullable = true)
	private String direccionadicional;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHAFUNDACION", nullable = true)
	private java.util.Date fechafundacion;

	@Size(min = 0, max = 15)
	@Column(name = "TELEFONO1", length = 15, nullable = true)
	private String telefono1;

	@Size(min = 0, max = 15)
	@Column(name = "TELEFONO2", length = 15, nullable = true)
	private String telefono2;

	@Size(min = 0, max = 15)
	@Column(name = "TELEFONO3", length = 15, nullable = true)
	private String telefono3;

	@Size(min = 0, max = 15)
	@Column(name = "FAX1", length = 15, nullable = true)
	private String fax1;

	@Size(min = 0, max = 15)
	@Column(name = "FAX2", length = 15, nullable = true)
	private String fax2;

	@Size(min = 0, max = 20)
	@Column(name = "DOCUMENTOFISCAL", length = 20, nullable = true)
	private String documentofiscal;

	@Size(min = 0, max = 2)
	@Column(name = "PROPIETARIOPORDEFECTO", length = 2, nullable = true)
	private String propietariopordefecto;

	@Size(min = 0, max = 60)
	@Column(name = "DESCRIPCIONEXTRANJERA", length = 60, nullable = true)
	private String descripcionextranjera;

	@Size(min = 0, max = 2)
	@Column(name = "MONEDAPORDEFECTO", length = 2, nullable = true)
	private String monedapordefecto;

	@Size(min = 0, max = 1)
	@Column(name = "TIPOCOMPANIA", length = 1, nullable = true)
	private String tipocompania;

	@Size(min = 0, max = 1)
	@Column(name = "FACTORRVALIDACION", length = 1, nullable = true)
	private String factorrvalidacion;

	@Size(min = 0, max = 1)
	@Column(name = "AFECTOIGVFLAG", length = 1, nullable = true)
	private String afectoigvflag;

	@Size(min = 0, max = 1)
	@Column(name = "CREDITOFISCALFACTORFLAG", length = 1, nullable = true)
	private String creditofiscalfactorflag;

	@Size(min = 0, max = 1)
	@Column(name = "CUENTAPROVISIONSBSFLAG", length = 1, nullable = true)
	private String cuentaprovisionsbsflag;

	@Size(min = 0, max = 20)
	@Column(name = "LOGOFILE", length = 20, nullable = true)
	private String logofile;

	@Column(name = "PERSONA", nullable = true)
	private Integer persona;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@Size(min = 0, max = 10)
	@Column(name = "ULTIMOUSUARIO", length = 10, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 30)
	@Column(name = "REPRESENTANTELEGAL", length = 30, nullable = true)
	private String representantelegal;

	@Size(min = 0, max = 100)
	@Column(name = "PAGINAWEB", length = 100, nullable = true)
	private String paginaweb;

	@Size(min = 0, max = 1)
	@Column(name = "AFECTORETENCIONIGVFLAG", length = 1, nullable = true)
	private String afectoretencionigvflag;

	@Size(min = 0, max = 20)
	@Column(name = "CERTIFICADOINSCRIPCION", length = 20, nullable = true)
	private String certificadoinscripcion;

	@Size(min = 0, max = 20)
	@Column(name = "DETRACCIONCUENTABANCARIA", length = 20, nullable = true)
	private String detraccioncuentabancaria;

	@Size(min = 0, max = 2)
	@Column(name = "REGIMENLABORALRTPS", length = 2, nullable = true)
	private String regimenlaboralrtps;

	@Column(name = "CODIGOREPRESENTANTELEGAL", nullable = true)
	private Integer codigorepresentantelegal;

	@Size(min = 0, max = 20)
	@Column(name = "REPRESENTANTELEGALDOCUMENTO", length = 20, nullable = true)
	private String representantelegaldocumento;

	@Size(min = 0, max = 4)
	@Column(name = "CODESTABLESUNAT", length = 4, nullable = true)
	private String codestablesunat;


	public BeanCompaniamast() {
		pk = new BeanCompaniamastPk();
	}


	public BeanCompaniamast(BeanCompaniamastPk pk) {
		this.pk = pk;
	}

	public BeanCompaniamastPk getPk() {
		return pk;
	}

	public void setPk(BeanCompaniamastPk pk) {
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
	 * @campo DireccionComun
	*/
	public String getDireccioncomun() {
		return direccioncomun;
	}

	/**
	 * 
	 * 
	 * @campo DireccionComun
	*/
	public void setDireccioncomun(String direccioncomun) {
		this.direccioncomun = direccioncomun;
	}
	/**
	 * 
	 * 
	 * @campo DireccionAdicional
	*/
	public String getDireccionadicional() {
		return direccionadicional;
	}

	/**
	 * 
	 * 
	 * @campo DireccionAdicional
	*/
	public void setDireccionadicional(String direccionadicional) {
		this.direccionadicional = direccionadicional;
	}
	/**
	 * 
	 * 
	 * @campo FechaFundacion
	*/
	public java.util.Date getFechafundacion() {
		return fechafundacion;
	}

	/**
	 * 
	 * 
	 * @campo FechaFundacion
	*/
	public void setFechafundacion(java.util.Date fechafundacion) {
		this.fechafundacion = fechafundacion;
	}
	/**
	 * 
	 * 
	 * @campo Telefono1
	*/
	public String getTelefono1() {
		return telefono1;
	}

	/**
	 * 
	 * 
	 * @campo Telefono1
	*/
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}
	/**
	 * 
	 * 
	 * @campo Telefono2
	*/
	public String getTelefono2() {
		return telefono2;
	}

	/**
	 * 
	 * 
	 * @campo Telefono2
	*/
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	/**
	 * 
	 * 
	 * @campo Telefono3
	*/
	public String getTelefono3() {
		return telefono3;
	}

	/**
	 * 
	 * 
	 * @campo Telefono3
	*/
	public void setTelefono3(String telefono3) {
		this.telefono3 = telefono3;
	}
	/**
	 * 
	 * 
	 * @campo Fax1
	*/
	public String getFax1() {
		return fax1;
	}

	/**
	 * 
	 * 
	 * @campo Fax1
	*/
	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}
	/**
	 * 
	 * 
	 * @campo Fax2
	*/
	public String getFax2() {
		return fax2;
	}

	/**
	 * 
	 * 
	 * @campo Fax2
	*/
	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}
	/**
	 * 
	 * 
	 * @campo DocumentoFiscal
	*/
	public String getDocumentofiscal() {
		return documentofiscal;
	}

	/**
	 * 
	 * 
	 * @campo DocumentoFiscal
	*/
	public void setDocumentofiscal(String documentofiscal) {
		this.documentofiscal = documentofiscal;
	}
	/**
	 * 
	 * 
	 * @campo PropietarioPorDefecto
	*/
	public String getPropietariopordefecto() {
		return propietariopordefecto;
	}

	/**
	 * 
	 * 
	 * @campo PropietarioPorDefecto
	*/
	public void setPropietariopordefecto(String propietariopordefecto) {
		this.propietariopordefecto = propietariopordefecto;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionExtranjera
	*/
	public String getDescripcionextranjera() {
		return descripcionextranjera;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionExtranjera
	*/
	public void setDescripcionextranjera(String descripcionextranjera) {
		this.descripcionextranjera = descripcionextranjera;
	}
	/**
	 * 
	 * 
	 * @campo MonedaPorDefecto
	*/
	public String getMonedapordefecto() {
		return monedapordefecto;
	}

	/**
	 * 
	 * 
	 * @campo MonedaPorDefecto
	*/
	public void setMonedapordefecto(String monedapordefecto) {
		this.monedapordefecto = monedapordefecto;
	}
	/**
	 * 
	 * 
	 * @campo TipoCompania
	*/
	public String getTipocompania() {
		return tipocompania;
	}

	/**
	 * 
	 * 
	 * @campo TipoCompania
	*/
	public void setTipocompania(String tipocompania) {
		this.tipocompania = tipocompania;
	}
	/**
	 * 
	 * 
	 * @campo FactorRValidacion
	*/
	public String getFactorrvalidacion() {
		return factorrvalidacion;
	}

	/**
	 * 
	 * 
	 * @campo FactorRValidacion
	*/
	public void setFactorrvalidacion(String factorrvalidacion) {
		this.factorrvalidacion = factorrvalidacion;
	}
	/**
	 * 
	 * 
	 * @campo AfectoIGVFlag
	*/
	public String getAfectoigvflag() {
		return afectoigvflag;
	}

	/**
	 * 
	 * 
	 * @campo AfectoIGVFlag
	*/
	public void setAfectoigvflag(String afectoigvflag) {
		this.afectoigvflag = afectoigvflag;
	}
	/**
	 * 
	 * 
	 * @campo CreditoFiscalFactorFlag
	*/
	public String getCreditofiscalfactorflag() {
		return creditofiscalfactorflag;
	}

	/**
	 * 
	 * 
	 * @campo CreditoFiscalFactorFlag
	*/
	public void setCreditofiscalfactorflag(String creditofiscalfactorflag) {
		this.creditofiscalfactorflag = creditofiscalfactorflag;
	}
	/**
	 * 
	 * 
	 * @campo CuentaProvisionSBSFlag
	*/
	public String getCuentaprovisionsbsflag() {
		return cuentaprovisionsbsflag;
	}

	/**
	 * 
	 * 
	 * @campo CuentaProvisionSBSFlag
	*/
	public void setCuentaprovisionsbsflag(String cuentaprovisionsbsflag) {
		this.cuentaprovisionsbsflag = cuentaprovisionsbsflag;
	}
	/**
	 * 
	 * 
	 * @campo LogoFile
	*/
	public String getLogofile() {
		return logofile;
	}

	/**
	 * 
	 * 
	 * @campo LogoFile
	*/
	public void setLogofile(String logofile) {
		this.logofile = logofile;
	}
	/**
	 * 
	 * 
	 * @campo Persona
	*/
	public Integer getPersona() {
		return persona;
	}

	/**
	 * 
	 * 
	 * @campo Persona
	*/
	public void setPersona(Integer persona) {
		this.persona = persona;
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
	 * @campo RepresentanteLegal
	*/
	public String getRepresentantelegal() {
		return representantelegal;
	}

	/**
	 * 
	 * 
	 * @campo RepresentanteLegal
	*/
	public void setRepresentantelegal(String representantelegal) {
		this.representantelegal = representantelegal;
	}
	/**
	 * 
	 * 
	 * @campo PaginaWeb
	*/
	public String getPaginaweb() {
		return paginaweb;
	}

	/**
	 * 
	 * 
	 * @campo PaginaWeb
	*/
	public void setPaginaweb(String paginaweb) {
		this.paginaweb = paginaweb;
	}
	/**
	 * 
	 * 
	 * @campo AfectoRetencionIGVFlag
	*/
	public String getAfectoretencionigvflag() {
		return afectoretencionigvflag;
	}

	/**
	 * 
	 * 
	 * @campo AfectoRetencionIGVFlag
	*/
	public void setAfectoretencionigvflag(String afectoretencionigvflag) {
		this.afectoretencionigvflag = afectoretencionigvflag;
	}
	/**
	 * 
	 * 
	 * @campo CertificadoInscripcion
	*/
	public String getCertificadoinscripcion() {
		return certificadoinscripcion;
	}

	/**
	 * 
	 * 
	 * @campo CertificadoInscripcion
	*/
	public void setCertificadoinscripcion(String certificadoinscripcion) {
		this.certificadoinscripcion = certificadoinscripcion;
	}
	/**
	 * 
	 * 
	 * @campo DetraccionCuentaBancaria
	*/
	public String getDetraccioncuentabancaria() {
		return detraccioncuentabancaria;
	}

	/**
	 * 
	 * 
	 * @campo DetraccionCuentaBancaria
	*/
	public void setDetraccioncuentabancaria(String detraccioncuentabancaria) {
		this.detraccioncuentabancaria = detraccioncuentabancaria;
	}
	/**
	 * 
	 * 
	 * @campo RegimenLaboralRTPS
	*/
	public String getRegimenlaboralrtps() {
		return regimenlaboralrtps;
	}

	/**
	 * 
	 * 
	 * @campo RegimenLaboralRTPS
	*/
	public void setRegimenlaboralrtps(String regimenlaboralrtps) {
		this.regimenlaboralrtps = regimenlaboralrtps;
	}
	/**
	 * 
	 * 
	 * @campo CODIGOREPRESENTANTELEGAL
	*/
	public Integer getCodigorepresentantelegal() {
		return codigorepresentantelegal;
	}

	/**
	 * 
	 * 
	 * @campo CODIGOREPRESENTANTELEGAL
	*/
	public void setCodigorepresentantelegal(Integer codigorepresentantelegal) {
		this.codigorepresentantelegal = codigorepresentantelegal;
	}
	/**
	 * 
	 * 
	 * @campo RepresentanteLegalDocumento
	*/
	public String getRepresentantelegaldocumento() {
		return representantelegaldocumento;
	}

	/**
	 * 
	 * 
	 * @campo RepresentanteLegalDocumento
	*/
	public void setRepresentantelegaldocumento(String representantelegaldocumento) {
		this.representantelegaldocumento = representantelegaldocumento;
	}
	/**
	 * 
	 * 
	 * @campo CodEstableSunat
	*/
	public String getCodestablesunat() {
		return codestablesunat;
	}

	/**
	 * 
	 * 
	 * @campo CodEstableSunat
	*/
	public void setCodestablesunat(String codestablesunat) {
		this.codestablesunat = codestablesunat;
	}

}
