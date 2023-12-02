package net.royal.spring.core.dominio.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.royal.spring.core.dominio.BeanCompaniamast;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;

public class DtoComunCompaniamast  extends DominioTransaccion{	
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
	private String grupo;
	private String certificadoinscripcion;
	private String detraccioncuentabancaria;
	private String representantelegaldocumento;
	private String regimenlaboralrtps;
	private BigDecimal representantelegalcontrato;
	private String numeroresolucion;
	private String numerodesignacion;
	private Integer codigorepresentantelegal;
	private String codigoanterior;
	private String plancontable;
	private String pliego;
	private java.util.Date fechavigenciapolizafin;
	private java.util.Date fechavigenciapolizainicio;
	private java.util.Date fechasuscripcionpoliza;
	private String numeropoliza;
	private String unidadejecutora;
	private String servwebusuario;
	private String servwebclave;
	private String email;
	private String uuid;	

	private String codestablesunat;
	private String personaDescri;

	private List<DtoComunCompanyowner> detalle1= new ArrayList<DtoComunCompanyowner>();
	private List<DtoComunReportingcompany> detalle2= new ArrayList<DtoComunReportingcompany>();

	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCodestablesunat() {
		return codestablesunat;
	}
	public void setCodestablesunat(String codestablesunat) {
		this.codestablesunat = codestablesunat;
	}
	public String getPersonaDescri() {
		return personaDescri;
	}
	public void setPersonaDescri(String personaDescri) {
		this.personaDescri = personaDescri;
	}
	public List<DtoComunCompanyowner> getDetalle1() {
		return detalle1;
	}
	public void setDetalle1(List<DtoComunCompanyowner> detalle1) {
		this.detalle1 = detalle1;
	}
	public List<DtoComunReportingcompany> getDetalle2() {
		return detalle2;
	}
	public void setDetalle2(List<DtoComunReportingcompany> detalle2) {
		this.detalle2 = detalle2;
	}
	public DtoComunCompaniamast() {}
	public DtoComunCompaniamast(String companiacodigo) {
		this.companiacodigo=companiacodigo;
	}
	
	public String getCompaniacodigo() {
		return companiacodigo;
	}
	public void setCompaniacodigo(String companiacodigo) {
		this.companiacodigo = companiacodigo;
	}
	public String getDescripcioncorta() {
		return descripcioncorta;
	}
	public void setDescripcioncorta(String descripcioncorta) {
		this.descripcioncorta = descripcioncorta;
	}
	public String getDescripcionlarga() {
		return descripcionlarga;
	}
	public void setDescripcionlarga(String descripcionlarga) {
		this.descripcionlarga = descripcionlarga;
	}
	public String getDireccioncomun() {
		return direccioncomun;
	}
	public void setDireccioncomun(String direccioncomun) {
		this.direccioncomun = direccioncomun;
	}
	public String getDireccionadicional() {
		return direccionadicional;
	}
	public void setDireccionadicional(String direccionadicional) {
		this.direccionadicional = direccionadicional;
	}
	public java.util.Date getFechafundacion() {
		return fechafundacion;
	}
	public void setFechafundacion(java.util.Date fechafundacion) {
		this.fechafundacion = fechafundacion;
	}
	public String getTelefono1() {
		return telefono1;
	}
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public String getTelefono3() {
		return telefono3;
	}
	public void setTelefono3(String telefono3) {
		this.telefono3 = telefono3;
	}
	public String getFax1() {
		return fax1;
	}
	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}
	public String getFax2() {
		return fax2;
	}
	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}
	public String getDocumentofiscal() {
		return documentofiscal;
	}
	public void setDocumentofiscal(String documentofiscal) {
		this.documentofiscal = documentofiscal;
	}
	public String getPropietariopordefecto() {
		return propietariopordefecto;
	}
	public void setPropietariopordefecto(String propietariopordefecto) {
		this.propietariopordefecto = propietariopordefecto;
	}
	public String getDescripcionextranjera() {
		return descripcionextranjera;
	}
	public void setDescripcionextranjera(String descripcionextranjera) {
		this.descripcionextranjera = descripcionextranjera;
	}
	public String getMonedapordefecto() {
		return monedapordefecto;
	}
	public void setMonedapordefecto(String monedapordefecto) {
		this.monedapordefecto = monedapordefecto;
	}
	public String getTipocompania() {
		return tipocompania;
	}
	public void setTipocompania(String tipocompania) {
		this.tipocompania = tipocompania;
	}
	public String getFactorrvalidacion() {
		return factorrvalidacion;
	}
	public void setFactorrvalidacion(String factorrvalidacion) {
		this.factorrvalidacion = factorrvalidacion;
	}
	public String getAfectoigvflag() {
		return afectoigvflag;
	}
	public void setAfectoigvflag(String afectoigvflag) {
		this.afectoigvflag = afectoigvflag;
	}
	public String getCreditofiscalfactorflag() {
		return creditofiscalfactorflag;
	}
	public void setCreditofiscalfactorflag(String creditofiscalfactorflag) {
		this.creditofiscalfactorflag = creditofiscalfactorflag;
	}
	public String getCuentaprovisionsbsflag() {
		return cuentaprovisionsbsflag;
	}
	public void setCuentaprovisionsbsflag(String cuentaprovisionsbsflag) {
		this.cuentaprovisionsbsflag = cuentaprovisionsbsflag;
	}
	public String getLogofile() {
		return logofile;
	}
	public void setLogofile(String logofile) {
		this.logofile = logofile;
	}
	public Integer getPersona() {
		return persona;
	}
	public void setPersona(Integer persona) {
		this.persona = persona;
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
	public String getRepresentantelegal() {
		return representantelegal;
	}
	public void setRepresentantelegal(String representantelegal) {
		this.representantelegal = representantelegal;
	}
	public String getPaginaweb() {
		return paginaweb;
	}
	public void setPaginaweb(String paginaweb) {
		this.paginaweb = paginaweb;
	}
	public String getAfectoretencionigvflag() {
		return afectoretencionigvflag;
	}
	public void setAfectoretencionigvflag(String afectoretencionigvflag) {
		this.afectoretencionigvflag = afectoretencionigvflag;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getCertificadoinscripcion() {
		return certificadoinscripcion;
	}
	public void setCertificadoinscripcion(String certificadoinscripcion) {
		this.certificadoinscripcion = certificadoinscripcion;
	}
	public String getDetraccioncuentabancaria() {
		return detraccioncuentabancaria;
	}
	public void setDetraccioncuentabancaria(String detraccioncuentabancaria) {
		this.detraccioncuentabancaria = detraccioncuentabancaria;
	}
	public String getRepresentantelegaldocumento() {
		return representantelegaldocumento;
	}
	public void setRepresentantelegaldocumento(String representantelegaldocumento) {
		this.representantelegaldocumento = representantelegaldocumento;
	}
	public String getRegimenlaboralrtps() {
		return regimenlaboralrtps;
	}
	public void setRegimenlaboralrtps(String regimenlaboralrtps) {
		this.regimenlaboralrtps = regimenlaboralrtps;
	}
	public BigDecimal getRepresentantelegalcontrato() {
		return representantelegalcontrato;
	}
	public void setRepresentantelegalcontrato(BigDecimal representantelegalcontrato) {
		this.representantelegalcontrato = representantelegalcontrato;
	}
	public String getNumeroresolucion() {
		return numeroresolucion;
	}
	public void setNumeroresolucion(String numeroresolucion) {
		this.numeroresolucion = numeroresolucion;
	}
	public String getNumerodesignacion() {
		return numerodesignacion;
	}
	public void setNumerodesignacion(String numerodesignacion) {
		this.numerodesignacion = numerodesignacion;
	}
	public Integer getCodigorepresentantelegal() {
		return codigorepresentantelegal;
	}
	public void setCodigorepresentantelegal(Integer codigorepresentantelegal) {
		this.codigorepresentantelegal = codigorepresentantelegal;
	}
	public String getCodigoanterior() {
		return codigoanterior;
	}
	public void setCodigoanterior(String codigoanterior) {
		this.codigoanterior = codigoanterior;
	}
	public String getPlancontable() {
		return plancontable;
	}
	public void setPlancontable(String plancontable) {
		this.plancontable = plancontable;
	}
	public String getPliego() {
		return pliego;
	}
	public void setPliego(String pliego) {
		this.pliego = pliego;
	}
	public java.util.Date getFechavigenciapolizafin() {
		return fechavigenciapolizafin;
	}
	public void setFechavigenciapolizafin(java.util.Date fechavigenciapolizafin) {
		this.fechavigenciapolizafin = fechavigenciapolizafin;
	}
	public java.util.Date getFechavigenciapolizainicio() {
		return fechavigenciapolizainicio;
	}
	public void setFechavigenciapolizainicio(java.util.Date fechavigenciapolizainicio) {
		this.fechavigenciapolizainicio = fechavigenciapolizainicio;
	}
	public java.util.Date getFechasuscripcionpoliza() {
		return fechasuscripcionpoliza;
	}
	public void setFechasuscripcionpoliza(java.util.Date fechasuscripcionpoliza) {
		this.fechasuscripcionpoliza = fechasuscripcionpoliza;
	}
	public String getNumeropoliza() {
		return numeropoliza;
	}
	public void setNumeropoliza(String numeropoliza) {
		this.numeropoliza = numeropoliza;
	}
	public String getUnidadejecutora() {
		return unidadejecutora;
	}
	public void setUnidadejecutora(String unidadejecutora) {
		this.unidadejecutora = unidadejecutora;
	}
	public String getServwebusuario() {
		return servwebusuario;
	}
	public void setServwebusuario(String servwebusuario) {
		this.servwebusuario = servwebusuario;
	}
	public String getServwebclave() {
		return servwebclave;
	}
	public void setServwebclave(String servwebclave) {
		this.servwebclave = servwebclave;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public BeanCompaniamast obtenerBean() {
		return obtenerBeanCore(new BeanCompaniamast(),ConstantePantallaAccion.NINGUNO);
	}

	public BeanCompaniamast obtenerBeanRegistrar() {
		return obtenerBeanCore(new BeanCompaniamast(),ConstantePantallaAccion.INSERTAR);
	}

	public BeanCompaniamast obtenerBeanActualizar(BeanCompaniamast bean) {
		return obtenerBeanCore(bean,ConstantePantallaAccion.ACTUALIZAR);
	}

	private BeanCompaniamast obtenerBeanCore(BeanCompaniamast bean,String tipo) {
		if (UString.esNuloVacio(tipo))
			tipo=ConstantePantallaAccion.NINGUNO;

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		switch (tipo) {
		case ConstantePantallaAccion.INSERTAR:
		case ConstantePantallaAccion.NINGUNO:
			bean.getPk().setCompaniacodigo(companiacodigo);
			bean.setDescripcioncorta(descripcioncorta);
			bean.setDescripcionlarga(descripcionlarga);
			bean.setDireccioncomun(direccioncomun);
			bean.setDireccionadicional(direccionadicional);
			bean.setFechafundacion(fechafundacion);
			bean.setTelefono1(telefono1);
			bean.setTelefono2(telefono2);
			bean.setTelefono3(telefono3);
			bean.setFax1(fax1);
			bean.setFax2(fax2);
			bean.setDocumentofiscal(documentofiscal);
			bean.setPropietariopordefecto(propietariopordefecto);
			bean.setDescripcionextranjera(descripcionextranjera);
			bean.setMonedapordefecto(monedapordefecto);
			bean.setTipocompania(tipocompania);
			bean.setFactorrvalidacion(factorrvalidacion);
			bean.setAfectoigvflag(afectoigvflag);
			bean.setCreditofiscalfactorflag(creditofiscalfactorflag);
			bean.setCuentaprovisionsbsflag(cuentaprovisionsbsflag);
			bean.setLogofile(logofile);
			bean.setPersona(persona);
			bean.setEstado(estado);
			bean.setUltimousuario(ultimousuario);
			bean.setUltimafechamodif(ultimafechamodif);
			bean.setRepresentantelegal(representantelegal);
			bean.setPaginaweb(paginaweb);
			bean.setAfectoretencionigvflag(afectoretencionigvflag);
			bean.setCertificadoinscripcion(certificadoinscripcion);
			bean.setDetraccioncuentabancaria(detraccioncuentabancaria);
			bean.setRegimenlaboralrtps(regimenlaboralrtps);
			bean.setCodigorepresentantelegal(codigorepresentantelegal);
			bean.setRepresentantelegaldocumento(representantelegaldocumento);
			bean.setCodestablesunat(codestablesunat);
 			bean.setUuid(uuid);
			break;
		case ConstantePantallaAccion.ACTUALIZAR:
			//bean.getPk().setCompaniacodigo(companiacodigo);
			bean.setDescripcioncorta(descripcioncorta);
			bean.setDescripcionlarga(descripcionlarga);
			bean.setDireccioncomun(direccioncomun);
			bean.setDireccionadicional(direccionadicional);
			bean.setFechafundacion(fechafundacion);
			bean.setTelefono1(telefono1);
			bean.setTelefono2(telefono2);
			bean.setTelefono3(telefono3);
			bean.setFax1(fax1);
			bean.setFax2(fax2);
			bean.setDocumentofiscal(documentofiscal);
			bean.setPropietariopordefecto(propietariopordefecto);
			bean.setDescripcionextranjera(descripcionextranjera);
			bean.setMonedapordefecto(monedapordefecto);
			bean.setTipocompania(tipocompania);
			bean.setFactorrvalidacion(factorrvalidacion);
			bean.setAfectoigvflag(afectoigvflag);
			bean.setCreditofiscalfactorflag(creditofiscalfactorflag);
			bean.setCuentaprovisionsbsflag(cuentaprovisionsbsflag);
			bean.setLogofile(logofile);
			bean.setPersona(persona);
			bean.setEstado(estado);
			bean.setUltimousuario(ultimousuario);
			bean.setUltimafechamodif(ultimafechamodif);
			bean.setRepresentantelegal(representantelegal);
			bean.setPaginaweb(paginaweb);
			bean.setAfectoretencionigvflag(afectoretencionigvflag);
			bean.setCertificadoinscripcion(certificadoinscripcion);
			bean.setDetraccioncuentabancaria(detraccioncuentabancaria);
			bean.setRegimenlaboralrtps(regimenlaboralrtps);
			bean.setCodigorepresentantelegal(codigorepresentantelegal);
			bean.setRepresentantelegaldocumento(representantelegaldocumento);
			bean.setCodestablesunat(codestablesunat);
 			//bean.setUuid(uuid);
			break;
		}

		return bean;
	}
}
