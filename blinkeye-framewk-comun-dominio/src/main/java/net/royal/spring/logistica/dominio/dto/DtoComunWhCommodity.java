package net.royal.spring.logistica.dominio.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.logistica.dominio.BeanWhCommodity;

public class DtoComunWhCommodity extends DominioTransaccion{
	private String commodity01;
	private String descripcionlocal;
	private String descripcioningles;
	private String clasificacion;
	private String codigobarrasflag;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private byte[] timestamp;
	private String xdescripcionlocal;
	private List<DtoComunWhCommoditysub> lstCommodityDetalle = new ArrayList<>();
	private String flagValida;

	
	private String commodity02;
	private String commodity;
	private String descripcionlocalsub;
	private String accion;
	
	/**
	 * 
	 * 
	 * @campo Commodity01
	*/
	
	
	public List<DtoComunWhCommoditysub> getLstCommodityDetalle() {
		return lstCommodityDetalle;
	}



	public String getFlagValida() {
		return flagValida;
	}



	public void setFlagValida(String flagValida) {
		this.flagValida = flagValida;
	}



	public void setLstCommodityDetalle(List<DtoComunWhCommoditysub> lstCommodityDetalle) {
		this.lstCommodityDetalle = lstCommodityDetalle;
	}
	
	public String getCommodity01() {
		return commodity01;
	}


	/**
	 * 
	 * 
	 * @campo Commodity01
	*/
	public void setCommodity01(String commodity01) {
		this.commodity01 = commodity01;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionLocal
	*/
	public String getDescripcionlocal() {
		return descripcionlocal;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionLocal
	*/
	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionIngles
	*/
	public String getDescripcioningles() {
		return descripcioningles;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionIngles
	*/
	public void setDescripcioningles(String descripcioningles) {
		this.descripcioningles = descripcioningles;
	}
	/**
	 * 
	 * 
	 * @campo Clasificacion
	*/
	public String getClasificacion() {
		return clasificacion;
	}

	/**
	 * 
	 * 
	 * @campo Clasificacion
	*/
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	/**
	 * 
	 * 
	 * @campo CodigoBarrasFlag
	*/
	public String getCodigobarrasflag() {
		return codigobarrasflag;
	}

	/**
	 * 
	 * 
	 * @campo CodigoBarrasFlag
	*/
	public void setCodigobarrasflag(String codigobarrasflag) {
		this.codigobarrasflag = codigobarrasflag;
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
	 * @campo xDescripcionLocal
	*/
	public String getXdescripcionlocal() {
		return xdescripcionlocal;
	}

	/**
	 * 
	 * 
	 * @campo xDescripcionLocal
	*/
	public void setXdescripcionlocal(String xdescripcionlocal) {
		this.xdescripcionlocal = xdescripcionlocal;
	}
	public BeanWhCommodity obtenerBean() {
		BeanWhCommodity bean = new BeanWhCommodity();
		return obtenerBean(bean);
	}

	public BeanWhCommodity obtenerBean(BeanWhCommodity bean) {
		if (bean == null)
			bean = new BeanWhCommodity();

		bean.getPk().setCommodity01(commodity01);
		bean.setDescripcionlocal(descripcionlocal);
		bean.setDescripcioningles(descripcioningles);
		bean.setClasificacion(clasificacion);
		bean.setCodigobarrasflag(codigobarrasflag);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);
		//bean.setTimestamp(timestamp);
		bean.setXdescripcionlocal(xdescripcionlocal);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}



	public String getCommodity02() {
		return commodity02;
	}



	public void setCommodity02(String commodity02) {
		this.commodity02 = commodity02;
	}



	public String getCommodity() {
		return commodity;
	}



	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}



	public String getDescripcionlocalsub() {
		return descripcionlocalsub;
	}



	public void setDescripcionlocalsub(String descripcionlocalsub) {
		this.descripcionlocalsub = descripcionlocalsub;
	}



	public String getAccion() {
		return accion;
	}



	public void setAccion(String accion) {
		this.accion = accion;
	}

	
}
