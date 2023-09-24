package net.royal.spring.wh.dominio.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;
import net.royal.spring.wh.dominio.BeanWhContratodetalle;

/**
 * 
 * 
 * @tabla dbo.WH_ContratoDetalle
*/
public class DtoComunWhContratodetalle extends DominioTransaccion implements java.io.Serializable{


	private String companiasocio;
	private String numerocontrato;
	private Integer secuencia;
	private String descripcion;
	private java.math.BigDecimal cantidad;
	private java.math.BigDecimal preciounitario;
	private java.math.BigDecimal montototal;
	private String item;
	private String commodity;
	private String unidadcodigo;
	private String cuentacontable;
	private String centrocosto;
	private String centrocostodestino;
	private String proyecto;
	private String sucursal;
	private String camporeferencia;
	private String destinocompaniasocio;
	private String comentario;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String igvexoneradoflag;
	private String referenciafiscal01;
	private String referenciafiscal02;
	private String referenciafiscal03;
	private String addfuturoflag;
	private String yecAfe;
	private Integer adendanumero;
	private String adendaflag;
	private String adendaestado;
	private String adendarazonrechazo;
	private String adendarequisicion;
	private String futuroflag;
	private String accion;
	private String ano;
	private String centrocosto_descri;
	private String proyecto_descri;
	private Boolean igvexoneradoflag_aux;
	private String itemmst;
	private BigDecimal cantidadTotal;
	private BigDecimal cantidadTotalGe;
	private String unidadcodigo_detail;
	private String tipoitem;
	private String controlcalidadflag;
	
	private List<DtoComunWhContratovencimiento> lstVencimientos = new ArrayList<DtoComunWhContratovencimiento>();
		
		
		
	
	
	
	public String getControlcalidadflag() {
		return controlcalidadflag;
	}

	public void setControlcalidadflag(String controlcalidadflag) {
		this.controlcalidadflag = controlcalidadflag;
	}

	public String getTipoitem() {
		return tipoitem;
	}

	public void setTipoitem(String tipoitem) {
		this.tipoitem = tipoitem;
	}

	public String getUnidadcodigo_detail() {
		return unidadcodigo_detail;
	}

	public void setUnidadcodigo_detail(String unidadcodigo_detail) {
		this.unidadcodigo_detail = unidadcodigo_detail;
	}

	public BigDecimal getCantidadTotalGe() {
		return cantidadTotalGe;
	}

	public void setCantidadTotalGe(BigDecimal cantidadTotalGe) {
		this.cantidadTotalGe = cantidadTotalGe;
	}

	public BigDecimal getCantidadTotal() {
		return cantidadTotal;
	}

	public void setCantidadTotal(BigDecimal cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	public List<DtoComunWhContratovencimiento> getLstVencimientos() {
		return lstVencimientos;
	}

	public void setLstVencimientos(List<DtoComunWhContratovencimiento> lstVencimientos) {
		this.lstVencimientos = lstVencimientos;
	}

	public String getItemmst() {
		return itemmst;
	}

	public void setItemmst(String itemmst) {
		this.itemmst = itemmst;
	}

	public Boolean getIgvexoneradoflag_aux() {
		return igvexoneradoflag_aux;
	}

	public void setIgvexoneradoflag_aux(Boolean igvexoneradoflag_aux) {
		this.igvexoneradoflag_aux = igvexoneradoflag_aux;
	}

	public String getCentrocosto_descri() {
		return centrocosto_descri;
	}

	public void setCentrocosto_descri(String centrocosto_descri) {
		this.centrocosto_descri = centrocosto_descri;
	}

	public String getProyecto_descri() {
		return proyecto_descri;
	}

	public void setProyecto_descri(String proyecto_descri) {
		this.proyecto_descri = proyecto_descri;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	*/
	public String getCompaniasocio() {
		return companiasocio;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	*/
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}
	/**
	 * 
	 * 
	 * @campo NumeroContrato
	*/
	public String getNumerocontrato() {
		return numerocontrato;
	}

	/**
	 * 
	 * 
	 * @campo NumeroContrato
	*/
	public void setNumerocontrato(String numerocontrato) {
		this.numerocontrato = numerocontrato;
	}
	/**
	 * 
	 * 
	 * @campo Secuencia
	*/
	public Integer getSecuencia() {
		return secuencia;
	}

	/**
	 * 
	 * 
	 * @campo Secuencia
	*/
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}
	/**
	 * 
	 * 
	 * @campo Descripcion
	*/
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * 
	 * 
	 * @campo Descripcion
	*/
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * 
	 * 
	 * @campo Cantidad
	*/
	public java.math.BigDecimal getCantidad() {
		return cantidad;
	}

	/**
	 * 
	 * 
	 * @campo Cantidad
	*/
	public void setCantidad(java.math.BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * 
	 * 
	 * @campo PrecioUnitario
	*/
	public java.math.BigDecimal getPreciounitario() {
		return preciounitario;
	}

	/**
	 * 
	 * 
	 * @campo PrecioUnitario
	*/
	public void setPreciounitario(java.math.BigDecimal preciounitario) {
		this.preciounitario = preciounitario;
	}
	/**
	 * 
	 * 
	 * @campo MontoTotal
	*/
	public java.math.BigDecimal getMontototal() {
		return montototal;
	}

	/**
	 * 
	 * 
	 * @campo MontoTotal
	*/
	public void setMontototal(java.math.BigDecimal montototal) {
		this.montototal = montototal;
	}
	/**
	 * 
	 * 
	 * @campo Item
	*/
	public String getItem() {
		return item;
	}

	/**
	 * 
	 * 
	 * @campo Item
	*/
	public void setItem(String item) {
		this.item = item;
	}
	/**
	 * 
	 * 
	 * @campo Commodity
	*/
	public String getCommodity() {
		return commodity;
	}

	/**
	 * 
	 * 
	 * @campo Commodity
	*/
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}
	/**
	 * 
	 * 
	 * @campo UnidadCodigo
	*/
	public String getUnidadcodigo() {
		return unidadcodigo;
	}

	/**
	 * 
	 * 
	 * @campo UnidadCodigo
	*/
	public void setUnidadcodigo(String unidadcodigo) {
		this.unidadcodigo = unidadcodigo;
	}
	/**
	 * 
	 * 
	 * @campo CuentaContable
	*/
	public String getCuentacontable() {
		return cuentacontable;
	}

	/**
	 * 
	 * 
	 * @campo CuentaContable
	*/
	public void setCuentacontable(String cuentacontable) {
		this.cuentacontable = cuentacontable;
	}
	/**
	 * 
	 * 
	 * @campo CentroCosto
	*/
	public String getCentrocosto() {
		return centrocosto;
	}

	/**
	 * 
	 * 
	 * @campo CentroCosto
	*/
	public void setCentrocosto(String centrocosto) {
		this.centrocosto = centrocosto;
	}
	/**
	 * 
	 * 
	 * @campo CentroCostoDestino
	*/
	public String getCentrocostodestino() {
		return centrocostodestino;
	}

	/**
	 * 
	 * 
	 * @campo CentroCostoDestino
	*/
	public void setCentrocostodestino(String centrocostodestino) {
		this.centrocostodestino = centrocostodestino;
	}
	/**
	 * 
	 * 
	 * @campo Proyecto
	*/
	public String getProyecto() {
		return proyecto;
	}

	/**
	 * 
	 * 
	 * @campo Proyecto
	*/
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
	/**
	 * 
	 * 
	 * @campo Sucursal
	*/
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * 
	 * 
	 * @campo Sucursal
	*/
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	/**
	 * 
	 * 
	 * @campo CampoReferencia
	*/
	public String getCamporeferencia() {
		return camporeferencia;
	}

	/**
	 * 
	 * 
	 * @campo CampoReferencia
	*/
	public void setCamporeferencia(String camporeferencia) {
		this.camporeferencia = camporeferencia;
	}
	/**
	 * 
	 * 
	 * @campo DestinoCompaniaSocio
	*/
	public String getDestinocompaniasocio() {
		return destinocompaniasocio;
	}

	/**
	 * 
	 * 
	 * @campo DestinoCompaniaSocio
	*/
	public void setDestinocompaniasocio(String destinocompaniasocio) {
		this.destinocompaniasocio = destinocompaniasocio;
	}
	/**
	 * 
	 * 
	 * @campo Comentario
	*/
	public String getComentario() {
		return comentario;
	}

	/**
	 * 
	 * 
	 * @campo Comentario
	*/
	public void setComentario(String comentario) {
		this.comentario = comentario;
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
	 * @campo IGVExoneradoFlag
	*/
	public String getIgvexoneradoflag() {
		return igvexoneradoflag;
	}

	/**
	 * 
	 * 
	 * @campo IGVExoneradoFlag
	*/
	public void setIgvexoneradoflag(String igvexoneradoflag) {
		this.igvexoneradoflag = igvexoneradoflag;
	}
	/**
	 * 
	 * 
	 * @campo ReferenciaFiscal01
	*/
	public String getReferenciafiscal01() {
		return referenciafiscal01;
	}

	/**
	 * 
	 * 
	 * @campo ReferenciaFiscal01
	*/
	public void setReferenciafiscal01(String referenciafiscal01) {
		this.referenciafiscal01 = referenciafiscal01;
	}
	/**
	 * 
	 * 
	 * @campo ReferenciaFiscal02
	*/
	public String getReferenciafiscal02() {
		return referenciafiscal02;
	}

	/**
	 * 
	 * 
	 * @campo ReferenciaFiscal02
	*/
	public void setReferenciafiscal02(String referenciafiscal02) {
		this.referenciafiscal02 = referenciafiscal02;
	}
	/**
	 * 
	 * 
	 * @campo ReferenciaFiscal03
	*/
	public String getReferenciafiscal03() {
		return referenciafiscal03;
	}

	/**
	 * 
	 * 
	 * @campo ReferenciaFiscal03
	*/
	public void setReferenciafiscal03(String referenciafiscal03) {
		this.referenciafiscal03 = referenciafiscal03;
	}
	/**
	 * 
	 * 
	 * @campo ADDFUTUROFLAG
	*/
	public String getAddfuturoflag() {
		return addfuturoflag;
	}

	/**
	 * 
	 * 
	 * @campo ADDFUTUROFLAG
	*/
	public void setAddfuturoflag(String addfuturoflag) {
		this.addfuturoflag = addfuturoflag;
	}
	/**
	 * 
	 * 
	 * @campo YEC_Afe
	*/
	public String getYecAfe() {
		return yecAfe;
	}

	/**
	 * 
	 * 
	 * @campo YEC_Afe
	*/
	public void setYecAfe(String yecAfe) {
		this.yecAfe = yecAfe;
	}
	/**
	 * 
	 * 
	 * @campo AdendaNumero
	*/
	public Integer getAdendanumero() {
		return adendanumero;
	}

	/**
	 * 
	 * 
	 * @campo AdendaNumero
	*/
	public void setAdendanumero(Integer adendanumero) {
		this.adendanumero = adendanumero;
	}
	/**
	 * 
	 * 
	 * @campo AdendaFlag
	*/
	public String getAdendaflag() {
		return adendaflag;
	}

	/**
	 * 
	 * 
	 * @campo AdendaFlag
	*/
	public void setAdendaflag(String adendaflag) {
		this.adendaflag = adendaflag;
	}
	/**
	 * 
	 * 
	 * @campo ADENDAESTADO
	*/
	public String getAdendaestado() {
		return adendaestado;
	}

	/**
	 * 
	 * 
	 * @campo ADENDAESTADO
	*/
	public void setAdendaestado(String adendaestado) {
		this.adendaestado = adendaestado;
	}
	/**
	 * 
	 * 
	 * @campo ADENDARAZONRECHAZO
	*/
	public String getAdendarazonrechazo() {
		return adendarazonrechazo;
	}

	/**
	 * 
	 * 
	 * @campo ADENDARAZONRECHAZO
	*/
	public void setAdendarazonrechazo(String adendarazonrechazo) {
		this.adendarazonrechazo = adendarazonrechazo;
	}
	/**
	 * 
	 * 
	 * @campo ADENDAREQUISICION
	*/
	public String getAdendarequisicion() {
		return adendarequisicion;
	}

	/**
	 * 
	 * 
	 * @campo ADENDAREQUISICION
	*/
	public void setAdendarequisicion(String adendarequisicion) {
		this.adendarequisicion = adendarequisicion;
	}
	/**
	 * 
	 * 
	 * @campo FUTUROFLAG
	*/
	public String getFuturoflag() {
		return futuroflag;
	}

	/**
	 * 
	 * 
	 * @campo FUTUROFLAG
	*/
	public void setFuturoflag(String futuroflag) {
		this.futuroflag = futuroflag;
	}
	public BeanWhContratodetalle obtenerBean() {
		BeanWhContratodetalle bean = new BeanWhContratodetalle();
		return obtenerBean(bean);
	}

	public BeanWhContratodetalle obtenerBean(BeanWhContratodetalle bean) {
		if (bean == null)
			bean = new BeanWhContratodetalle();

		bean.getPk().setCompaniasocio(companiasocio);
		bean.getPk().setNumerocontrato(numerocontrato);
		bean.getPk().setSecuencia(secuencia);
		bean.setDescripcion(descripcion);
		bean.setCantidad(cantidad);
		bean.setPreciounitario(preciounitario);
		bean.setMontototal(montototal);
		bean.setItem(item);
		bean.setCommodity(commodity);
		bean.setUnidadcodigo(unidadcodigo);
		bean.setCuentacontable(cuentacontable);
		bean.setCentrocosto(centrocosto);
		bean.setCentrocostodestino(centrocostodestino);
		bean.setProyecto(proyecto);
		bean.setSucursal(sucursal);
		bean.setCamporeferencia(camporeferencia);
		bean.setDestinocompaniasocio(destinocompaniasocio);
		bean.setComentario(comentario);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);
		bean.setIgvexoneradoflag(igvexoneradoflag);
		bean.setReferenciafiscal01(referenciafiscal01);
		bean.setReferenciafiscal02(referenciafiscal02);
		bean.setReferenciafiscal03(referenciafiscal03);
		bean.setAddfuturoflag(addfuturoflag);
		bean.setYecAfe(yecAfe);
		bean.setAdendanumero(adendanumero);
		bean.setAdendaflag(adendaflag);
		bean.setAdendaestado(adendaestado);
		bean.setAdendarazonrechazo(adendarazonrechazo);
		bean.setAdendarequisicion(adendarequisicion);
		bean.setFuturoflag(futuroflag);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
