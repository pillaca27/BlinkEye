package net.royal.spring.logistica.dominio.dto;

import java.util.ArrayList;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.logistica.dominio.BeanWhClaselinea;

public class DtoComunWhClaselinea extends DominioTransaccion{
	private String linea;
	private String descripcionlocal;
	private String descripcioningles;
	private String grupolinea;
	private String manejocolorserieflag;
	private String estado;
	private java.util.Date ultimafechamodif;
	private String ultimousuario;
	private String centrocostovalida;
		
	private List<DtoComunWhClasefamilia>lstDetalle=new ArrayList<>();
	private List<DtoComunWhClasesubfamilia>lstDetalleSubFamilia=new ArrayList<>();
	private String flagValida;
	private String familia;
	private Boolean flagFamilia;
	private String estadodescripcion;
	
	public DtoComunWhClaselinea() {}
	public DtoComunWhClaselinea(String linea) {
		this.linea=linea;
	}
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
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
	public String getGrupolinea() {
		return grupolinea;
	}
	public void setGrupolinea(String grupolinea) {
		this.grupolinea = grupolinea;
	}
	public String getManejocolorserieflag() {
		return manejocolorserieflag;
	}
	public void setManejocolorserieflag(String manejocolorserieflag) {
		this.manejocolorserieflag = manejocolorserieflag;
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
	public String getCentrocostovalida() {
		return centrocostovalida;
	}
	public void setCentrocostovalida(String centrocostovalida) {
		this.centrocostovalida = centrocostovalida;
	}
	public List<DtoComunWhClasefamilia> getLstDetalle() {
		return lstDetalle;
	}
	public void setLstDetalle(List<DtoComunWhClasefamilia> lstDetalle) {
		this.lstDetalle = lstDetalle;
	}
	public List<DtoComunWhClasesubfamilia> getLstDetalleSubFamilia() {
		return lstDetalleSubFamilia;
	}
	public void setLstDetalleSubFamilia(List<DtoComunWhClasesubfamilia> lstDetalleSubFamilia) {
		this.lstDetalleSubFamilia = lstDetalleSubFamilia;
	}
	public String getFlagValida() {
		return flagValida;
	}
	public void setFlagValida(String flagValida) {
		this.flagValida = flagValida;
	}
	public String getFamilia() {
		return familia;
	}
	public void setFamilia(String familia) {
		this.familia = familia;
	}
	public Boolean getFlagFamilia() {
		return flagFamilia;
	}
	public void setFlagFamilia(Boolean flagFamilia) {
		this.flagFamilia = flagFamilia;
	}
	public String getEstadodescripcion() {
		return estadodescripcion;
	}
	public void setEstadodescripcion(String estadodescripcion) {
		this.estadodescripcion = estadodescripcion;
	}
	
	public BeanWhClaselinea obtenerBean() {
		BeanWhClaselinea bean = new BeanWhClaselinea();
		return obtenerBean(bean);
	}

	public BeanWhClaselinea obtenerBean(BeanWhClaselinea bean) {
		if (bean == null)
			bean = new BeanWhClaselinea();

		bean.getPk().setLinea(linea);
		bean.setDescripcionlocal(descripcionlocal);
		bean.setDescripcioningles(descripcioningles);
		bean.setGrupolinea(grupolinea);
		bean.setManejocolorserieflag(manejocolorserieflag);
		bean.setEstado(estado);
		bean.setUltimafechamodif(ultimafechamodif);
		bean.setUltimousuario(ultimousuario);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}
}
