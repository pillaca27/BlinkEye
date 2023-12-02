package net.royal.spring.logistica.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.logistica.dominio.BeanWhCommoditysub;

public class DtoComunWhCommoditysub extends DominioTransaccion{
	private String commodity01;
	private String commodity02;
	private String commodity;
	private String descripcionlocal;
	private String descripcioningles;
	private String unidadpordefecto;
	private String cuentacontablegasto;
	private String elementogasto;
	private String partidapresupuestal;
	private String flujodecaja;
	private String clasificacionactivo;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private BigDecimal montoreferencial;
	private String montoreferencialmoneda;
	private String commodityfamilia;
	private String commoditylinea;
	private String commoditysubfamilia;
	private String detraccioncodigo;
	private String partidaarancelaria;
	private String referenciafiscal02;
	private String revisionflujocodigo;
	private String cuentacontableextranjera;
	private String codigointerno;
	private String igvexoneradoflag;
	private String generarprovisionflag;
	private String cuentaprovision;
	private String referenciafiscal01;
	private String categoriaactivo;
	private String descripcioneditableflag;
	private String commoditygrupolinea;
	private BigDecimal correlativo;
	private BigDecimal correlativo02;
	private String flujocaja;
	private String itemBien;
	private String numeroOsce;
	private String estadodescripcion;
	private String xdescripcionlocal;
	private String elementoinversion;
	private  String accion;
	
	public String getEstadodescripcion() {
		return estadodescripcion;
	}
	public void setEstadodescripcion(String estadodescripcion) {
		this.estadodescripcion = estadodescripcion;
	}
	
	public DtoComunWhCommoditysub() {}
	public DtoComunWhCommoditysub(String commodity01,String commodity02) {
		this.commodity01=commodity01;
		this.commodity02=commodity02;
	}
	public DtoComunWhCommoditysub(String commodity01,String commodity02,String commodity) {
		this.commodity01=commodity01;
		this.commodity02=commodity02;
		this.commodity=commodity;
	}
	
	
	public String getCommodity01() {
		return commodity01;
	}
	public void setCommodity01(String commodity01) {
		this.commodity01 = commodity01;
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
	public String getUnidadpordefecto() {
		return unidadpordefecto;
	}
	public void setUnidadpordefecto(String unidadpordefecto) {
		this.unidadpordefecto = unidadpordefecto;
	}
	public String getCuentacontablegasto() {
		return cuentacontablegasto;
	}
	public void setCuentacontablegasto(String cuentacontablegasto) {
		this.cuentacontablegasto = cuentacontablegasto;
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
	public String getClasificacionactivo() {
		return clasificacionactivo;
	}
	public void setClasificacionactivo(String clasificacionactivo) {
		this.clasificacionactivo = clasificacionactivo;
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
	public BigDecimal getMontoreferencial() {
		return montoreferencial;
	}
	public void setMontoreferencial(BigDecimal montoreferencial) {
		this.montoreferencial = montoreferencial;
	}
	public String getMontoreferencialmoneda() {
		return montoreferencialmoneda;
	}
	public void setMontoreferencialmoneda(String montoreferencialmoneda) {
		this.montoreferencialmoneda = montoreferencialmoneda;
	}
	public String getCommodityfamilia() {
		return commodityfamilia;
	}
	public void setCommodityfamilia(String commodityfamilia) {
		this.commodityfamilia = commodityfamilia;
	}
	public String getCommoditylinea() {
		return commoditylinea;
	}
	public void setCommoditylinea(String commoditylinea) {
		this.commoditylinea = commoditylinea;
	}
	public String getCommoditysubfamilia() {
		return commoditysubfamilia;
	}
	public void setCommoditysubfamilia(String commoditysubfamilia) {
		this.commoditysubfamilia = commoditysubfamilia;
	}
	public String getDetraccioncodigo() {
		return detraccioncodigo;
	}
	public void setDetraccioncodigo(String detraccioncodigo) {
		this.detraccioncodigo = detraccioncodigo;
	}
	public String getPartidaarancelaria() {
		return partidaarancelaria;
	}
	public void setPartidaarancelaria(String partidaarancelaria) {
		this.partidaarancelaria = partidaarancelaria;
	}
	public String getReferenciafiscal02() {
		return referenciafiscal02;
	}
	public void setReferenciafiscal02(String referenciafiscal02) {
		this.referenciafiscal02 = referenciafiscal02;
	}
	public String getRevisionflujocodigo() {
		return revisionflujocodigo;
	}
	public void setRevisionflujocodigo(String revisionflujocodigo) {
		this.revisionflujocodigo = revisionflujocodigo;
	}
	public String getCuentacontableextranjera() {
		return cuentacontableextranjera;
	}
	public void setCuentacontableextranjera(String cuentacontableextranjera) {
		this.cuentacontableextranjera = cuentacontableextranjera;
	}
	public String getCodigointerno() {
		return codigointerno;
	}
	public void setCodigointerno(String codigointerno) {
		this.codigointerno = codigointerno;
	}
	public String getIgvexoneradoflag() {
		return igvexoneradoflag;
	}
	public void setIgvexoneradoflag(String igvexoneradoflag) {
		this.igvexoneradoflag = igvexoneradoflag;
	}
	public String getGenerarprovisionflag() {
		return generarprovisionflag;
	}
	public void setGenerarprovisionflag(String generarprovisionflag) {
		this.generarprovisionflag = generarprovisionflag;
	}
	public String getCuentaprovision() {
		return cuentaprovision;
	}
	public void setCuentaprovision(String cuentaprovision) {
		this.cuentaprovision = cuentaprovision;
	}
	public String getReferenciafiscal01() {
		return referenciafiscal01;
	}
	public void setReferenciafiscal01(String referenciafiscal01) {
		this.referenciafiscal01 = referenciafiscal01;
	}
	public String getCategoriaactivo() {
		return categoriaactivo;
	}
	public void setCategoriaactivo(String categoriaactivo) {
		this.categoriaactivo = categoriaactivo;
	}
	public String getDescripcioneditableflag() {
		return descripcioneditableflag;
	}
	public void setDescripcioneditableflag(String descripcioneditableflag) {
		this.descripcioneditableflag = descripcioneditableflag;
	}
	public String getCommoditygrupolinea() {
		return commoditygrupolinea;
	}
	public void setCommoditygrupolinea(String commoditygrupolinea) {
		this.commoditygrupolinea = commoditygrupolinea;
	}
	public BigDecimal getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(BigDecimal correlativo) {
		this.correlativo = correlativo;
	}
	public BigDecimal getCorrelativo02() {
		return correlativo02;
	}
	public void setCorrelativo02(BigDecimal correlativo02) {
		this.correlativo02 = correlativo02;
	}
	public String getFlujocaja() {
		return flujocaja;
	}
	public void setFlujocaja(String flujocaja) {
		this.flujocaja = flujocaja;
	}
	public String getItemBien() {
		return itemBien;
	}
	public void setItemBien(String itemBien) {
		this.itemBien = itemBien;
	}
	public String getNumeroOsce() {
		return numeroOsce;
	}
	public void setNumeroOsce(String numeroOsce) {
		this.numeroOsce = numeroOsce;
	}
	public String getXdescripcionlocal() {
		return xdescripcionlocal;
	}
	public void setXdescripcionlocal(String xdescripcionlocal) {
		this.xdescripcionlocal = xdescripcionlocal;
	}
	public String getElementoinversion() {
		return elementoinversion;
	}
	public void setElementoinversion(String elementoinversion) {
		this.elementoinversion = elementoinversion;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	public BeanWhCommoditysub obtenerBean() {
		BeanWhCommoditysub bean = new BeanWhCommoditysub();
		return obtenerBean(bean);
	}

	public BeanWhCommoditysub obtenerBean(BeanWhCommoditysub bean) {
		if (bean == null)
			bean = new BeanWhCommoditysub();

		bean.getPk().setCommodity01(commodity01);
		bean.getPk().setCommodity02(commodity02);
		bean.setCommodity(commodity);
		bean.setDescripcionlocal(descripcionlocal);
		bean.setDescripcioningles(descripcioningles);
		bean.setUnidadpordefecto(unidadpordefecto);
		bean.setCuentacontablegasto(cuentacontablegasto);
		bean.setElementogasto(elementogasto);
		bean.setPartidapresupuestal(partidapresupuestal);
		bean.setFlujodecaja(flujodecaja);
		bean.setClasificacionactivo(clasificacionactivo);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);
		bean.setMontoreferencial(montoreferencial);
		bean.setMontoreferencialmoneda(montoreferencialmoneda);
		bean.setRevisionflujocodigo(revisionflujocodigo);
		bean.setPartidaarancelaria(partidaarancelaria);
		bean.setXdescripcionlocal(xdescripcionlocal);
		bean.setReferenciafiscal02(referenciafiscal02);
		bean.setCommoditylinea(commoditylinea);
		bean.setCommodityfamilia(commodityfamilia);
		bean.setCommoditysubfamilia(commoditysubfamilia);
		bean.setDetraccioncodigo(detraccioncodigo);
		bean.setCommoditygrupolinea(commoditygrupolinea);
		bean.setElementoinversion(elementoinversion);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}
}
