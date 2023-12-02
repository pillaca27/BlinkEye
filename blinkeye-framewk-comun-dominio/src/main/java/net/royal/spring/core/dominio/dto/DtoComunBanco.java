package net.royal.spring.core.dominio.dto;

import net.royal.spring.core.dominio.BeanBanco;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;

public class DtoComunBanco  extends DominioTransaccion{	
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
	private String ultimousuario;
	private String conciliacionformatoflag;
	private String codigointerfaseafp;
	private String disponibleedflag;
	private String codigofiscal;
	private String codigortps;
	private Double tasaefectivaanual;
	private Double tasaefectivaanualex;
	private String persona;
 
	private String estado_desc;
	private String banconumero_desc;
	private String codigortps_desc;
	private String uuid;
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public DtoComunBanco() {}
	public DtoComunBanco(String banco) {
		this.banco=banco;
	}
	
	
	public String getPersona() {
		return persona;
	}
	public void setPersona(String persona) {
		this.persona = persona;
	}
	public String getEstado_desc() {
		return estado_desc;
	}
	public void setEstado_desc(String estado_desc) {
		this.estado_desc = estado_desc;
	}
	public String getBanconumero_desc() {
		return banconumero_desc;
	}
	public void setBanconumero_desc(String banconumero_desc) {
		this.banconumero_desc = banconumero_desc;
	}
	public String getCodigortps_desc() {
		return codigortps_desc;
	}
	public void setCodigortps_desc(String codigortps_desc) {
		this.codigortps_desc = codigortps_desc;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
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
	public Integer getBanconumero() {
		return banconumero;
	}
	public void setBanconumero(Integer banconumero) {
		this.banconumero = banconumero;
	}
	public String getPersonacontacto() {
		return personacontacto;
	}
	public void setPersonacontacto(String personacontacto) {
		this.personacontacto = personacontacto;
	}
	public String getConciliacionautomaticaflag() {
		return conciliacionautomaticaflag;
	}
	public void setConciliacionautomaticaflag(String conciliacionautomaticaflag) {
		this.conciliacionautomaticaflag = conciliacionautomaticaflag;
	}
	public String getFormatopropioflag() {
		return formatopropioflag;
	}
	public void setFormatopropioflag(String formatopropioflag) {
		this.formatopropioflag = formatopropioflag;
	}
	public String getFormatodatawindow() {
		return formatodatawindow;
	}
	public void setFormatodatawindow(String formatodatawindow) {
		this.formatodatawindow = formatodatawindow;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	public String getUltimousuario() {
		return ultimousuario;
	}
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	public String getConciliacionformatoflag() {
		return conciliacionformatoflag;
	}
	public void setConciliacionformatoflag(String conciliacionformatoflag) {
		this.conciliacionformatoflag = conciliacionformatoflag;
	}
	public String getCodigointerfaseafp() {
		return codigointerfaseafp;
	}
	public void setCodigointerfaseafp(String codigointerfaseafp) {
		this.codigointerfaseafp = codigointerfaseafp;
	}
	public String getDisponibleedflag() {
		return disponibleedflag;
	}
	public void setDisponibleedflag(String disponibleedflag) {
		this.disponibleedflag = disponibleedflag;
	}
	public String getCodigofiscal() {
		return codigofiscal;
	}
	public void setCodigofiscal(String codigofiscal) {
		this.codigofiscal = codigofiscal;
	}
	public String getCodigortps() {
		return codigortps;
	}
	public void setCodigortps(String codigortps) {
		this.codigortps = codigortps;
	}
	public Double getTasaefectivaanual() {
		return tasaefectivaanual;
	}
	public void setTasaefectivaanual(Double tasaefectivaanual) {
		this.tasaefectivaanual = tasaefectivaanual;
	}
	public Double getTasaefectivaanualex() {
		return tasaefectivaanualex;
	}
	public void setTasaefectivaanualex(Double tasaefectivaanualex) {
		this.tasaefectivaanualex = tasaefectivaanualex;
	}
	
	public BeanBanco obtenerBean() {
		return obtenerBeanCore(new BeanBanco(),ConstantePantallaAccion.NINGUNO);
	}

	public BeanBanco obtenerBeanRegistrar() {
		return obtenerBeanCore(new BeanBanco(),ConstantePantallaAccion.INSERTAR);
	}

	public BeanBanco obtenerBeanActualizar(BeanBanco bean) {
		return obtenerBeanCore(bean,ConstantePantallaAccion.ACTUALIZAR);
	}

	private BeanBanco obtenerBeanCore(BeanBanco bean,String tipo) {
		if (UString.esNuloVacio(tipo))
			tipo=ConstantePantallaAccion.NINGUNO;

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		switch (tipo) {
		case ConstantePantallaAccion.INSERTAR:
		case ConstantePantallaAccion.NINGUNO:
			bean.getPk().setBanco(banco);
			bean.setDescripcioncorta(descripcioncorta);
			bean.setDescripcionlarga(descripcionlarga);
			bean.setBanconumero(banconumero);
			bean.setPersonacontacto(personacontacto);
			bean.setConciliacionautomaticaflag(conciliacionautomaticaflag);
			bean.setFormatopropioflag(formatopropioflag);
			bean.setFormatodatawindow(formatodatawindow);
			bean.setEstado(estado);
			bean.setUltimafechamodif(ultimafechamodif);
			bean.setUltimousuario(ultimousuario);
			bean.setConciliacionformatoflag(conciliacionformatoflag);
			bean.setCodigointerfaseafp(codigointerfaseafp);
			bean.setDisponibleedflag(disponibleedflag);
			bean.setTasaefectivaanual(tasaefectivaanual);
			bean.setTasaefectivaanualex(tasaefectivaanualex);
			bean.setCodigofiscal(codigofiscal);
			bean.setCodigortps(codigortps);
 			bean.setUuid(uuid);
			break;
		case ConstantePantallaAccion.ACTUALIZAR:
			//bean.getPk().setBanco(banco);
			bean.setDescripcioncorta(descripcioncorta);
			bean.setDescripcionlarga(descripcionlarga);
			bean.setBanconumero(banconumero);
			bean.setPersonacontacto(personacontacto);
			bean.setConciliacionautomaticaflag(conciliacionautomaticaflag);
			bean.setFormatopropioflag(formatopropioflag);
			bean.setFormatodatawindow(formatodatawindow);
			bean.setEstado(estado);
			bean.setUltimafechamodif(ultimafechamodif);
			bean.setUltimousuario(ultimousuario);
			bean.setConciliacionformatoflag(conciliacionformatoflag);
			bean.setCodigointerfaseafp(codigointerfaseafp);
			bean.setDisponibleedflag(disponibleedflag);
			bean.setTasaefectivaanual(tasaefectivaanual);
			bean.setTasaefectivaanualex(tasaefectivaanualex);
			bean.setCodigofiscal(codigofiscal);
			bean.setCodigortps(codigortps);
 			//bean.setUuid(uuid);
			break;
		}

		return bean;
	}
}
