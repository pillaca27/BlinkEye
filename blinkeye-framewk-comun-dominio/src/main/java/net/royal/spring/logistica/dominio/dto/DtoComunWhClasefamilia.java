package net.royal.spring.logistica.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.logistica.dominio.BeanWhClasefamilia;

public class DtoComunWhClasefamilia extends DominioTransaccion{
	private String linea;
	private String familia;
	private String descripcionlocal;
	private String descripcioningles;
	private String descripcioncompleta;
	private String cuentainventario;
	private String cuentagasto;
	private String elementogasto;
	private String partidapresupuestal;
	private String flujodecaja;
	private String lineafamilia;
	private String lotevalidacionflag;
	private String medicinagrupoaflag;
	private String medicinagrupohflag;
	private String medicinagrupoeflag;
	private String medicinagruporflag;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String caracteristica01;
	private String caracteristica02;
	private String caracteristica03;
	private String caracteristica04;
	private String caracteristica05;
	private String contactoemail;
	private String contactofax;
	private String contactonombre;
	private String contactotelefono;
	private String cuentatransito;
	private String cuentaventas;
	private String referenciafiscal02;
	private String referenciafical02;
	private String cuentacostoventas;
	private String cuentasalidaterceros;	
	private String accion;
	private String partidapresupuestalDescri;
	
	public DtoComunWhClasefamilia() {}
	public DtoComunWhClasefamilia(String linea,String familia) {
		this.linea=linea;
		this.familia=familia;
	}
	
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	public String getFamilia() {
		return familia;
	}
	public void setFamilia(String familia) {
		this.familia = familia;
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
	public String getDescripcioncompleta() {
		return descripcioncompleta;
	}
	public void setDescripcioncompleta(String descripcioncompleta) {
		this.descripcioncompleta = descripcioncompleta;
	}
	public String getCuentainventario() {
		return cuentainventario;
	}
	public void setCuentainventario(String cuentainventario) {
		this.cuentainventario = cuentainventario;
	}
	public String getCuentagasto() {
		return cuentagasto;
	}
	public void setCuentagasto(String cuentagasto) {
		this.cuentagasto = cuentagasto;
	}
	public String getElementogasto() {
		return elementogasto;
	}
	public void setElementogasto(String elementogasto) {
		this.elementogasto = elementogasto;
	}
	public String getPartidapresupuestal() {
		return partidapresupuestal;
	}
	public void setPartidapresupuestal(String partidapresupuestal) {
		this.partidapresupuestal = partidapresupuestal;
	}
	public String getFlujodecaja() {
		return flujodecaja;
	}
	public void setFlujodecaja(String flujodecaja) {
		this.flujodecaja = flujodecaja;
	}
	public String getLineafamilia() {
		return lineafamilia;
	}
	public void setLineafamilia(String lineafamilia) {
		this.lineafamilia = lineafamilia;
	}
	public String getLotevalidacionflag() {
		return lotevalidacionflag;
	}
	public void setLotevalidacionflag(String lotevalidacionflag) {
		this.lotevalidacionflag = lotevalidacionflag;
	}
	public String getMedicinagrupoaflag() {
		return medicinagrupoaflag;
	}
	public void setMedicinagrupoaflag(String medicinagrupoaflag) {
		this.medicinagrupoaflag = medicinagrupoaflag;
	}
	public String getMedicinagrupohflag() {
		return medicinagrupohflag;
	}
	public void setMedicinagrupohflag(String medicinagrupohflag) {
		this.medicinagrupohflag = medicinagrupohflag;
	}
	public String getMedicinagrupoeflag() {
		return medicinagrupoeflag;
	}
	public void setMedicinagrupoeflag(String medicinagrupoeflag) {
		this.medicinagrupoeflag = medicinagrupoeflag;
	}
	public String getMedicinagruporflag() {
		return medicinagruporflag;
	}
	public void setMedicinagruporflag(String medicinagruporflag) {
		this.medicinagruporflag = medicinagruporflag;
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
	public String getCaracteristica01() {
		return caracteristica01;
	}
	public void setCaracteristica01(String caracteristica01) {
		this.caracteristica01 = caracteristica01;
	}
	public String getCaracteristica02() {
		return caracteristica02;
	}
	public void setCaracteristica02(String caracteristica02) {
		this.caracteristica02 = caracteristica02;
	}
	public String getCaracteristica03() {
		return caracteristica03;
	}
	public void setCaracteristica03(String caracteristica03) {
		this.caracteristica03 = caracteristica03;
	}
	public String getCaracteristica04() {
		return caracteristica04;
	}
	public void setCaracteristica04(String caracteristica04) {
		this.caracteristica04 = caracteristica04;
	}
	public String getCaracteristica05() {
		return caracteristica05;
	}
	public void setCaracteristica05(String caracteristica05) {
		this.caracteristica05 = caracteristica05;
	}
	public String getContactoemail() {
		return contactoemail;
	}
	public void setContactoemail(String contactoemail) {
		this.contactoemail = contactoemail;
	}
	public String getContactofax() {
		return contactofax;
	}
	public void setContactofax(String contactofax) {
		this.contactofax = contactofax;
	}
	public String getContactonombre() {
		return contactonombre;
	}
	public void setContactonombre(String contactonombre) {
		this.contactonombre = contactonombre;
	}
	public String getContactotelefono() {
		return contactotelefono;
	}
	public void setContactotelefono(String contactotelefono) {
		this.contactotelefono = contactotelefono;
	}
	public String getCuentatransito() {
		return cuentatransito;
	}
	public void setCuentatransito(String cuentatransito) {
		this.cuentatransito = cuentatransito;
	}
	public String getCuentaventas() {
		return cuentaventas;
	}
	public void setCuentaventas(String cuentaventas) {
		this.cuentaventas = cuentaventas;
	}
	public String getReferenciafiscal02() {
		return referenciafiscal02;
	}
	public void setReferenciafiscal02(String referenciafiscal02) {
		this.referenciafiscal02 = referenciafiscal02;
	}
	public String getReferenciafical02() {
		return referenciafical02;
	}
	public void setReferenciafical02(String referenciafical02) {
		this.referenciafical02 = referenciafical02;
	}
	public String getCuentacostoventas() {
		return cuentacostoventas;
	}
	public void setCuentacostoventas(String cuentacostoventas) {
		this.cuentacostoventas = cuentacostoventas;
	}
	public String getCuentasalidaterceros() {
		return cuentasalidaterceros;
	}
	public void setCuentasalidaterceros(String cuentasalidaterceros) {
		this.cuentasalidaterceros = cuentasalidaterceros;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getPartidapresupuestalDescri() {
		return partidapresupuestalDescri;
	}
	public void setPartidapresupuestalDescri(String partidapresupuestalDescri) {
		this.partidapresupuestalDescri = partidapresupuestalDescri;
	}

	public BeanWhClasefamilia obtenerBean() {
		BeanWhClasefamilia bean = new BeanWhClasefamilia();
		return obtenerBean(bean);
	}

	public BeanWhClasefamilia obtenerBean(BeanWhClasefamilia bean) {
		if (bean == null)
			bean = new BeanWhClasefamilia();

		bean.getPk().setLinea(linea);
		bean.getPk().setFamilia(familia);
		bean.setDescripcionlocal(descripcionlocal);
		bean.setDescripcioningles(descripcioningles);
		bean.setDescripcioncompleta(descripcioncompleta);
		bean.setCuentainventario(cuentainventario);
		bean.setCuentagasto(cuentagasto);
		bean.setElementogasto(elementogasto);
		bean.setPartidapresupuestal(partidapresupuestal);
		bean.setFlujodecaja(flujodecaja);
		bean.setLineafamilia(lineafamilia);
		bean.setLotevalidacionflag(lotevalidacionflag);
		bean.setMedicinagrupoaflag(medicinagrupoaflag);
		bean.setMedicinagrupohflag(medicinagrupohflag);
		bean.setMedicinagrupoeflag(medicinagrupoeflag);
		bean.setMedicinagruporflag(medicinagruporflag);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);
		bean.setCuentaventas(cuentaventas);
		bean.setCuentatransito(cuentatransito);
		bean.setContactonombre(contactonombre);
		bean.setContactotelefono(contactotelefono);
		bean.setContactofax(contactofax);
		bean.setContactoemail(contactoemail);
		bean.setCaracteristica01(caracteristica01);
		bean.setCaracteristica02(caracteristica02);
		bean.setCaracteristica03(caracteristica03);
		bean.setCaracteristica04(caracteristica04);
		bean.setCaracteristica05(caracteristica05);
		bean.setReferenciafiscal02(referenciafiscal02);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}
}
