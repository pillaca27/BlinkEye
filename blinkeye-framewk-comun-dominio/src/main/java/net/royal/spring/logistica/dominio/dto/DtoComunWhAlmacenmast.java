package net.royal.spring.logistica.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunWhAlmacenmast  extends DominioTransaccion{
	private String almacencodigo;
	private String multicompaniaflag;
	private String companiasocio;
	private String descripcionlocal;
	private String descripcioningles;
	private String direccion;
	private String unidadnegocio;
	private String tipoalmacen;
	private String almacentransitoprincipal;
	private String almacenventaflag;
	private String almacenproduccionflag;
	private String subalmacenflag;
	private String cuentainventario;
	private Integer numeromesesstock;
	private Integer encargado;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String cuentagasto;
	private String almacenregion;
	private String almacenconsignacionflag;
	private String disponibleplanproduccionflag;
	private String almacencommodityflag;
	private String almacenintermedioflag;
	private Integer comercialcajero;
	private Integer comercialcobrador;
	private String sucursal;
	private String establecimientofiscal;
	private String almacengastoflag;
	
	public DtoComunWhAlmacenmast() {}
	public DtoComunWhAlmacenmast(String almacencodigo) {
		this.almacencodigo=almacencodigo;
	}
	public DtoComunWhAlmacenmast(String almacencodigo,String companiasocio) {
		this.almacencodigo=almacencodigo;
		this.companiasocio=companiasocio;
	}
	
	public String getAlmacencodigo() {
		return almacencodigo;
	}
	public void setAlmacencodigo(String almacencodigo) {
		this.almacencodigo = almacencodigo;
	}
	public String getMulticompaniaflag() {
		return multicompaniaflag;
	}
	public void setMulticompaniaflag(String multicompaniaflag) {
		this.multicompaniaflag = multicompaniaflag;
	}
	public String getCompaniasocio() {
		return companiasocio;
	}
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
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
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getUnidadnegocio() {
		return unidadnegocio;
	}
	public void setUnidadnegocio(String unidadnegocio) {
		this.unidadnegocio = unidadnegocio;
	}
	public String getTipoalmacen() {
		return tipoalmacen;
	}
	public void setTipoalmacen(String tipoalmacen) {
		this.tipoalmacen = tipoalmacen;
	}
	public String getAlmacentransitoprincipal() {
		return almacentransitoprincipal;
	}
	public void setAlmacentransitoprincipal(String almacentransitoprincipal) {
		this.almacentransitoprincipal = almacentransitoprincipal;
	}
	public String getAlmacenventaflag() {
		return almacenventaflag;
	}
	public void setAlmacenventaflag(String almacenventaflag) {
		this.almacenventaflag = almacenventaflag;
	}
	public String getAlmacenproduccionflag() {
		return almacenproduccionflag;
	}
	public void setAlmacenproduccionflag(String almacenproduccionflag) {
		this.almacenproduccionflag = almacenproduccionflag;
	}
	public String getSubalmacenflag() {
		return subalmacenflag;
	}
	public void setSubalmacenflag(String subalmacenflag) {
		this.subalmacenflag = subalmacenflag;
	}
	public String getCuentainventario() {
		return cuentainventario;
	}
	public void setCuentainventario(String cuentainventario) {
		this.cuentainventario = cuentainventario;
	}
	public Integer getNumeromesesstock() {
		return numeromesesstock;
	}
	public void setNumeromesesstock(Integer numeromesesstock) {
		this.numeromesesstock = numeromesesstock;
	}
	public Integer getEncargado() {
		return encargado;
	}
	public void setEncargado(Integer encargado) {
		this.encargado = encargado;
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
	public String getCuentagasto() {
		return cuentagasto;
	}
	public void setCuentagasto(String cuentagasto) {
		this.cuentagasto = cuentagasto;
	}
	public String getAlmacenregion() {
		return almacenregion;
	}
	public void setAlmacenregion(String almacenregion) {
		this.almacenregion = almacenregion;
	}
	public String getAlmacenconsignacionflag() {
		return almacenconsignacionflag;
	}
	public void setAlmacenconsignacionflag(String almacenconsignacionflag) {
		this.almacenconsignacionflag = almacenconsignacionflag;
	}
	public String getDisponibleplanproduccionflag() {
		return disponibleplanproduccionflag;
	}
	public void setDisponibleplanproduccionflag(String disponibleplanproduccionflag) {
		this.disponibleplanproduccionflag = disponibleplanproduccionflag;
	}
	public String getAlmacencommodityflag() {
		return almacencommodityflag;
	}
	public void setAlmacencommodityflag(String almacencommodityflag) {
		this.almacencommodityflag = almacencommodityflag;
	}
	public String getAlmacenintermedioflag() {
		return almacenintermedioflag;
	}
	public void setAlmacenintermedioflag(String almacenintermedioflag) {
		this.almacenintermedioflag = almacenintermedioflag;
	}
	public Integer getComercialcajero() {
		return comercialcajero;
	}
	public void setComercialcajero(Integer comercialcajero) {
		this.comercialcajero = comercialcajero;
	}
	public Integer getComercialcobrador() {
		return comercialcobrador;
	}
	public void setComercialcobrador(Integer comercialcobrador) {
		this.comercialcobrador = comercialcobrador;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getEstablecimientofiscal() {
		return establecimientofiscal;
	}
	public void setEstablecimientofiscal(String establecimientofiscal) {
		this.establecimientofiscal = establecimientofiscal;
	}
	public String getAlmacengastoflag() {
		return almacengastoflag;
	}
	public void setAlmacengastoflag(String almacengastoflag) {
		this.almacengastoflag = almacengastoflag;
	}
	
	
}
