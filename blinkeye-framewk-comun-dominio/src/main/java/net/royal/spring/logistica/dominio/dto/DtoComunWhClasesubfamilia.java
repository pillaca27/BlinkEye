package net.royal.spring.logistica.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.logistica.dominio.BeanWhClasesubfamilia;

public class DtoComunWhClasesubfamilia extends DominioTransaccion{
	private String linea;
	private String familia;
	private String subfamilia;	
	private String descripcionlocal;
	private String descripcioningles;
	private String descripcioncompleta;
	private String itemtipo;
	private String transaccionoperacion;
	private String estado;
	private java.util.Date ultimafechamodif;
	private String ultimousuario;
	private String cuenta13;
	private String cuenta15;
	private String cuenta91;
	private String referenciafiscal02;
	
	private String accion;
	
	public DtoComunWhClasesubfamilia() {}
	public DtoComunWhClasesubfamilia(String linea,String familia,String subfamilia) {
		this.linea=linea;
		this.familia=familia;
		this.subfamilia=subfamilia;
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
	public String getSubfamilia() {
		return subfamilia;
	}
	public void setSubfamilia(String subfamilia) {
		this.subfamilia = subfamilia;
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
	public String getItemtipo() {
		return itemtipo;
	}
	public void setItemtipo(String itemtipo) {
		this.itemtipo = itemtipo;
	}
	public String getTransaccionoperacion() {
		return transaccionoperacion;
	}
	public void setTransaccionoperacion(String transaccionoperacion) {
		this.transaccionoperacion = transaccionoperacion;
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
	public String getCuenta13() {
		return cuenta13;
	}
	public void setCuenta13(String cuenta13) {
		this.cuenta13 = cuenta13;
	}
	public String getCuenta15() {
		return cuenta15;
	}
	public void setCuenta15(String cuenta15) {
		this.cuenta15 = cuenta15;
	}
	public String getCuenta91() {
		return cuenta91;
	}
	public void setCuenta91(String cuenta91) {
		this.cuenta91 = cuenta91;
	}
	public String getReferenciafiscal02() {
		return referenciafiscal02;
	}
	public void setReferenciafiscal02(String referenciafiscal02) {
		this.referenciafiscal02 = referenciafiscal02;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	public BeanWhClasesubfamilia obtenerBean() {
		BeanWhClasesubfamilia bean = new BeanWhClasesubfamilia();
		return obtenerBean(bean);
	}

	public BeanWhClasesubfamilia obtenerBean(BeanWhClasesubfamilia bean) {
		if (bean == null)
			bean = new BeanWhClasesubfamilia();

		bean.getPk().setLinea(linea);
		bean.getPk().setFamilia(familia);
		bean.getPk().setSubfamilia(subfamilia);
		bean.setDescripcionlocal(descripcionlocal);
		bean.setDescripcioningles(descripcioningles);
		bean.setDescripcioncompleta(descripcioncompleta);
		bean.setItemtipo(itemtipo);
		bean.setTransaccionoperacion(transaccionoperacion);
		bean.setEstado(estado);
		bean.setUltimafechamodif(ultimafechamodif);
		bean.setUltimousuario(ultimousuario);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}
}
