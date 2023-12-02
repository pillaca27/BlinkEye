package net.royal.spring.core.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

import net.royal.spring.core.dominio.BeanServicioximpuesto;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunServicioximpuesto  extends DominioTransaccion{	
	private String tiposervicio;
	private String impuesto;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	private BigDecimal factorporcentaje;  
	private String  signo;
	
 
	private byte[] timestamp;
	private String tipoimpuesto;
	private String accion;
	
	
	
	public byte[] getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(byte[] timestamp) {
		this.timestamp = timestamp;
	}
	public String getTipoimpuesto() {
		return tipoimpuesto;
	}
	public void setTipoimpuesto(String tipoimpuesto) {
		this.tipoimpuesto = tipoimpuesto;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getTiposervicio() {
		return tiposervicio;
	}
	public void setTiposervicio(String tiposervicio) {
		this.tiposervicio = tiposervicio;
	}
	public String getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
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
	public BigDecimal getFactorporcentaje() {
		return factorporcentaje;
	}
	public void setFactorporcentaje(BigDecimal factorporcentaje) {
		this.factorporcentaje = factorporcentaje;
	}
	public String getSigno() {
		return signo;
	}
	public void setSigno(String signo) {
		this.signo = signo;
	}
	
	public BeanServicioximpuesto obtenerBean() {
		BeanServicioximpuesto bean = new BeanServicioximpuesto();
		return obtenerBean(bean);
	}

	public BeanServicioximpuesto obtenerBean(BeanServicioximpuesto bean) {
		if (bean == null)
			bean = new BeanServicioximpuesto();

		bean.getPk().setTiposervicio(tiposervicio);
		bean.getPk().setImpuesto(impuesto);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}
}
