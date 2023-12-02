package net.royal.spring.core.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.core.dominio.BeanTipopago;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;

public class DtoComunTipopago extends DominioTransaccion{
	private String tipopago;
	private String descripcion;
	private String restringidoflag;
	private String cartaflag;
	private String generarnumeracionflag;
	private String entregableflag;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String estado;
	private String vouchersumarizadoflag;
	private String disponiblefeederflag;
	private String codigosiaf;
	private String descripcioningles;
	private String generarcomisionflag;
	private String cuentacomision;
	private BigDecimal montocomisionlocal;
	private BigDecimal montocomisionextranjera;
	private String generarinterfaseflag;
	private String tipopagortps;
	private String codigofiscal;
	private String uuid;
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public DtoComunTipopago() {}
	public DtoComunTipopago(String tipopago) {
		this.tipopago=tipopago;
	}
	
	public String getTipopago() {
		return tipopago;
	}
	public void setTipopago(String tipopago) {
		this.tipopago = tipopago;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getRestringidoflag() {
		return restringidoflag;
	}
	public void setRestringidoflag(String restringidoflag) {
		this.restringidoflag = restringidoflag;
	}
	public String getCartaflag() {
		return cartaflag;
	}
	public void setCartaflag(String cartaflag) {
		this.cartaflag = cartaflag;
	}
	public String getGenerarnumeracionflag() {
		return generarnumeracionflag;
	}
	public void setGenerarnumeracionflag(String generarnumeracionflag) {
		this.generarnumeracionflag = generarnumeracionflag;
	}
	public String getEntregableflag() {
		return entregableflag;
	}
	public void setEntregableflag(String entregableflag) {
		this.entregableflag = entregableflag;
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getVouchersumarizadoflag() {
		return vouchersumarizadoflag;
	}
	public void setVouchersumarizadoflag(String vouchersumarizadoflag) {
		this.vouchersumarizadoflag = vouchersumarizadoflag;
	}
	public String getDisponiblefeederflag() {
		return disponiblefeederflag;
	}
	public void setDisponiblefeederflag(String disponiblefeederflag) {
		this.disponiblefeederflag = disponiblefeederflag;
	}
	public String getCodigosiaf() {
		return codigosiaf;
	}
	public void setCodigosiaf(String codigosiaf) {
		this.codigosiaf = codigosiaf;
	}
	public String getDescripcioningles() {
		return descripcioningles;
	}
	public void setDescripcioningles(String descripcioningles) {
		this.descripcioningles = descripcioningles;
	}
	public String getGenerarcomisionflag() {
		return generarcomisionflag;
	}
	public void setGenerarcomisionflag(String generarcomisionflag) {
		this.generarcomisionflag = generarcomisionflag;
	}
	public String getCuentacomision() {
		return cuentacomision;
	}
	public void setCuentacomision(String cuentacomision) {
		this.cuentacomision = cuentacomision;
	}
	public BigDecimal getMontocomisionlocal() {
		return montocomisionlocal;
	}
	public void setMontocomisionlocal(BigDecimal montocomisionlocal) {
		this.montocomisionlocal = montocomisionlocal;
	}
	public BigDecimal getMontocomisionextranjera() {
		return montocomisionextranjera;
	}
	public void setMontocomisionextranjera(BigDecimal montocomisionextranjera) {
		this.montocomisionextranjera = montocomisionextranjera;
	}
	public String getGenerarinterfaseflag() {
		return generarinterfaseflag;
	}
	public void setGenerarinterfaseflag(String generarinterfaseflag) {
		this.generarinterfaseflag = generarinterfaseflag;
	}
	public String getTipopagortps() {
		return tipopagortps;
	}
	public void setTipopagortps(String tipopagortps) {
		this.tipopagortps = tipopagortps;
	}
	public String getCodigofiscal() {
		return codigofiscal;
	}
	public void setCodigofiscal(String codigofiscal) {
		this.codigofiscal = codigofiscal;
	}
 
	public BeanTipopago obtenerBean() {
		return obtenerBeanCore(new BeanTipopago(),ConstantePantallaAccion.NINGUNO);
	}

	public BeanTipopago obtenerBeanRegistrar() {
		return obtenerBeanCore(new BeanTipopago(),ConstantePantallaAccion.INSERTAR);
	}

	public BeanTipopago obtenerBeanActualizar(BeanTipopago bean) {
		return obtenerBeanCore(bean,ConstantePantallaAccion.ACTUALIZAR);
	}

	private BeanTipopago obtenerBeanCore(BeanTipopago bean,String tipo) {
		if (UString.esNuloVacio(tipo))
			tipo=ConstantePantallaAccion.NINGUNO;

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		switch (tipo) {
		case ConstantePantallaAccion.INSERTAR:
		case ConstantePantallaAccion.NINGUNO:
			bean.getPk().setTipopago(tipopago);
			bean.setDescripcion(descripcion);
			bean.setRestringidoflag(restringidoflag);
			bean.setCartaflag(cartaflag);
			bean.setGenerarnumeracionflag(generarnumeracionflag);
			bean.setEntregableflag(entregableflag);
			bean.setUltimousuario(ultimousuario);
			bean.setUltimafechamodif(ultimafechamodif);
			bean.setEstado(estado);
			bean.setVouchersumarizadoflag(vouchersumarizadoflag);
			bean.setDisponiblefeederflag(disponiblefeederflag);
			bean.setDescripcioningles(descripcioningles);
			bean.setCodigosiaf(codigosiaf);
			bean.setCodigofiscal(codigofiscal);
			bean.setTipopagortps(tipopagortps);
			bean.setUuid(uuid);
			break;
		case ConstantePantallaAccion.ACTUALIZAR:
			//bean.getPk().setTipopago(unidadnegocio);
			bean.setDescripcion(descripcion);
			bean.setRestringidoflag(restringidoflag);
			bean.setCartaflag(cartaflag);
			bean.setGenerarnumeracionflag(generarnumeracionflag);
			bean.setEntregableflag(entregableflag);
			bean.setUltimousuario(ultimousuario);
			bean.setUltimafechamodif(ultimafechamodif);
			bean.setEstado(estado);
			bean.setVouchersumarizadoflag(vouchersumarizadoflag);
			bean.setDisponiblefeederflag(disponiblefeederflag);
			bean.setDescripcioningles(descripcioningles);
			bean.setCodigosiaf(codigosiaf);
			bean.setCodigofiscal(codigofiscal);
			bean.setTipopagortps(tipopagortps);
			//bean.setUuid(uuid);
			break;
		}

		return bean;
	}
}
