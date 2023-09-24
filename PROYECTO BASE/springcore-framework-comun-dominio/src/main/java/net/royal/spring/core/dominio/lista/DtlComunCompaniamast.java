package net.royal.spring.core.dominio.lista;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

/**
 * 
 * 
 * @tabla dbo.CompaniaMast
*/
public class DtlComunCompaniamast extends DominioTransaccion implements java.io.Serializable{


	private String companiacodigo;
	private String descripcioncorta;
	private String descripcionlarga;
	private String direccioncomun;
	private String direccionadicional;
	private java.util.Date fechafundacion;
	private String telefono1;
	private String telefono2;
	private String telefono3;
	private String fax1;
	private String fax2;
	private String documentofiscal;
	private String propietariopordefecto;
	private String descripcionextranjera;
	private String monedapordefecto;
	private String tipocompania;
	private String factorrvalidacion;
	private String afectoigvflag;
	private String creditofiscalfactorflag;
	private String cuentaprovisionsbsflag;
	private String logofile;
	private Integer persona;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String representantelegal;
	private String paginaweb;
	private String afectoretencionigvflag;
	private String certificadoinscripcion;
	private String detraccioncuentabancaria;
	private String regimenlaboralrtps;
	private Integer codigorepresentantelegal;
	private String representantelegaldocumento;
	private String codestablesunat;
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
	 * @campo CompaniaCodigo
	*/
	public String getCompaniacodigo() {
		return companiacodigo;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaCodigo
	*/
	public void setCompaniacodigo(String companiacodigo) {
		this.companiacodigo = companiacodigo;
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
