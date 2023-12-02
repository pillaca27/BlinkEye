package net.royal.spring.logistica.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.royal.spring.framework.modelo.WorkFlowAdjunto;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunWhQuotationVendor extends DominioTransaccion implements java.io.Serializable{
	private String companiasocio;
	private Integer cotizacionsecuencia;
	private String proveedorcotizacion;
	private String formapago;
	private Integer plazoentrega;
	private Integer validezoferta;
	private String monedacodigo;
	private BigDecimal cantidad;
	private BigDecimal preciounitario;
	private BigDecimal preciounitacioconigv;
	private BigDecimal preciounitarioinicial;
	private BigDecimal preciounitarioinicialconigv;
	private BigDecimal montototal;
	private BigDecimal montototalconigv;
	private BigDecimal descuento;
	private String asignadoflag;
	private String ultimousuario;
	private Date ultimafechamodif;
	private Date fechadocumento;
	private Date fechaapertura;
	private Date fecharecepcion;
	private Date fechalimiterecepcion;
	private String igvexoneradoflag;
	private String impresionflag;
	private String observaciones;
	private String unidadnegocio;
	private Integer garantia;
	private String numeroorden;
	private String proveedor;
	private String proveedornombre;
	private String presupuestopendienteflag;
	private String observacionadicional;
	private BigDecimal preciounitarioinicialigv1;
	private BigDecimal preciounitarioinicialigv2;
	private BigDecimal preciounitarioinicialigv3;
	private String requisionnumero;
	private Integer secuencia;
	private String item;
	private String commodity;
	private String unidadcodigo;
	private String descripcion;
	private BigDecimal cantidadlimite;
	private BigDecimal cantidadoriginal;
	private BigDecimal preciounitario2;
	private String controlpresupuestalflag;
	private String redefinidoflag;
	private String mejor;
	private BigDecimal montocambio;
	private String partidapresupuestal;
	private String generarcontratoflag;
	private String licitacionasignadaflag;
	private String tipoadj;
	
	private String auxpar_name;
	private String auxpar_exoneradoflag;
	
	private Integer transaccionid;
	
	private BigDecimal ROWNUM_;
	private Date fechainvitacion;
	
	private Date fechaEntregaDocumentos;
	
	private String auxComentarioVal;
	
	private String compradirectaflag;	
		
	private List<WorkFlowAdjunto> lstAdjuntos;
	private String ventanaAccion;
	
	private BigDecimal totalCot;
	
	
	private String modalidad;
	
	private String auxVentana;

	public Date getFechainvitacion() {
		return fechainvitacion;
	}

	public void setFechainvitacion(Date fechainvitacion) {
		this.fechainvitacion = fechainvitacion;
	}

	public String getCompaniasocio() {
		return companiasocio;
	}

	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}

	public Integer getCotizacionsecuencia() {
		return cotizacionsecuencia;
	}

	public void setCotizacionsecuencia(Integer cotizacionsecuencia) {
		this.cotizacionsecuencia = cotizacionsecuencia;
	}

	public String getProveedorcotizacion() {
		return proveedorcotizacion;
	}

	public void setProveedorcotizacion(String proveedorcotizacion) {
		this.proveedorcotizacion = proveedorcotizacion;
	}

	public String getFormapago() {
		return formapago;
	}

	public void setFormapago(String formapago) {
		this.formapago = formapago;
	}

	public Integer getPlazoentrega() {
		return plazoentrega;
	}

	public void setPlazoentrega(Integer plazoentrega) {
		this.plazoentrega = plazoentrega;
	}

	public Integer getValidezoferta() {
		return validezoferta;
	}

	public void setValidezoferta(Integer validezoferta) {
		this.validezoferta = validezoferta;
	}

	public String getMonedacodigo() {
		return monedacodigo;
	}

	public void setMonedacodigo(String monedacodigo) {
		this.monedacodigo = monedacodigo;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPreciounitario() {
		return preciounitario;
	}

	public void setPreciounitario(BigDecimal preciounitario) {
		this.preciounitario = preciounitario;
	}

	public BigDecimal getPreciounitacioconigv() {
		return preciounitacioconigv;
	}

	public void setPreciounitacioconigv(BigDecimal preciounitacioconigv) {
		this.preciounitacioconigv = preciounitacioconigv;
	}

	public BigDecimal getPreciounitarioinicial() {
		return preciounitarioinicial;
	}

	public void setPreciounitarioinicial(BigDecimal preciounitarioinicial) {
		this.preciounitarioinicial = preciounitarioinicial;
	}

	public BigDecimal getPreciounitarioinicialconigv() {
		return preciounitarioinicialconigv;
	}

	public void setPreciounitarioinicialconigv(BigDecimal preciounitarioinicialconigv) {
		this.preciounitarioinicialconigv = preciounitarioinicialconigv;
	}

	public BigDecimal getMontototal() {
		return montototal;
	}

	public void setMontototal(BigDecimal montototal) {
		this.montototal = montototal;
	}

	public BigDecimal getMontototalconigv() {
		return montototalconigv;
	}

	public void setMontototalconigv(BigDecimal montototalconigv) {
		this.montototalconigv = montototalconigv;
	}

	public BigDecimal getDescuento() {
		if (descuento==null)
			descuento=new BigDecimal(0);
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public String getAsignadoflag() {
		return asignadoflag;
	}

	public void setAsignadoflag(String asignadoflag) {
		this.asignadoflag = asignadoflag;
	}

	public String getUltimousuario() {
		return ultimousuario;
	}

	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}

	public Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	public void setUltimafechamodif(Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}

	public Date getFechadocumento() {
		return fechadocumento;
	}

	public void setFechadocumento(Date fechadocumento) {
		this.fechadocumento = fechadocumento;
	}

	public Date getFechaapertura() {
		return fechaapertura;
	}

	public void setFechaapertura(Date fechaapertura) {
		this.fechaapertura = fechaapertura;
	}

	public Date getFecharecepcion() {
		return fecharecepcion;
	}

	public void setFecharecepcion(Date fecharecepcion) {
		this.fecharecepcion = fecharecepcion;
	}

	public Date getFechalimiterecepcion() {
		return fechalimiterecepcion;
	}

	public void setFechalimiterecepcion(Date fechalimiterecepcion) {
		this.fechalimiterecepcion = fechalimiterecepcion;
	}

	public String getIgvexoneradoflag() {
		return igvexoneradoflag;
	}

	public void setIgvexoneradoflag(String igvexoneradoflag) {
		this.igvexoneradoflag = igvexoneradoflag;
	}

	public String getImpresionflag() {
		return impresionflag;
	}

	public void setImpresionflag(String impresionflag) {
		this.impresionflag = impresionflag;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getUnidadnegocio() {
		return unidadnegocio;
	}

	public void setUnidadnegocio(String unidadnegocio) {
		this.unidadnegocio = unidadnegocio;
	}

	public Integer getGarantia() {
		return garantia;
	}

	public void setGarantia(Integer garantia) {
		this.garantia = garantia;
	}

	public String getNumeroorden() {
		return numeroorden;
	}

	public void setNumeroorden(String numeroorden) {
		this.numeroorden = numeroorden;
	}

	public String getProveedornombre() {
		return proveedornombre;
	}

	public void setProveedornombre(String proveedornombre) {
		this.proveedornombre = proveedornombre;
	}

	public String getPresupuestopendienteflag() {
		return presupuestopendienteflag;
	}

	public void setPresupuestopendienteflag(String presupuestopendienteflag) {
		this.presupuestopendienteflag = presupuestopendienteflag;
	}

	public String getObservacionadicional() {
		return observacionadicional;
	}

	public void setObservacionadicional(String observacionadicional) {
		this.observacionadicional = observacionadicional;
	}

	public BigDecimal getPreciounitarioinicialigv1() {
		return preciounitarioinicialigv1;
	}

	public void setPreciounitarioinicialigv1(BigDecimal preciounitarioinicialigv1) {
		this.preciounitarioinicialigv1 = preciounitarioinicialigv1;
	}

	public BigDecimal getPreciounitarioinicialigv2() {
		return preciounitarioinicialigv2;
	}

	public void setPreciounitarioinicialigv2(BigDecimal preciounitarioinicialigv2) {
		this.preciounitarioinicialigv2 = preciounitarioinicialigv2;
	}

	public BigDecimal getPreciounitarioinicialigv3() {
		return preciounitarioinicialigv3;
	}

	public void setPreciounitarioinicialigv3(BigDecimal preciounitarioinicialigv3) {
		this.preciounitarioinicialigv3 = preciounitarioinicialigv3;
	}

	public String getRequisionnumero() {
		return requisionnumero;
	}

	public void setRequisionnumero(String requisionnumero) {
		this.requisionnumero = requisionnumero;
	}

	public Integer getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public String getUnidadcodigo() {
		return unidadcodigo;
	}

	public void setUnidadcodigo(String unidadcodigo) {
		this.unidadcodigo = unidadcodigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getCantidadlimite() {
		return cantidadlimite;
	}

	public void setCantidadlimite(BigDecimal cantidadlimite) {
		this.cantidadlimite = cantidadlimite;
	}

	public BigDecimal getCantidadoriginal() {
		return cantidadoriginal;
	}

	public void setCantidadoriginal(BigDecimal cantidadoriginal) {
		this.cantidadoriginal = cantidadoriginal;
	}

	public BigDecimal getPreciounitario2() {
		return preciounitario2;
	}

	public void setPreciounitario2(BigDecimal preciounitario2) {
		this.preciounitario2 = preciounitario2;
	}

	public String getControlpresupuestalflag() {
		return controlpresupuestalflag;
	}

	public void setControlpresupuestalflag(String controlpresupuestalflag) {
		this.controlpresupuestalflag = controlpresupuestalflag;
	}

	public String getRedefinidoflag() {
		return redefinidoflag;
	}

	public void setRedefinidoflag(String redefinidoflag) {
		this.redefinidoflag = redefinidoflag;
	}

	public String getMejor() {
		return mejor;
	}

	public void setMejor(String mejor) {
		this.mejor = mejor;
	}

	public BigDecimal getMontocambio() {
		return montocambio;
	}

	public void setMontocambio(BigDecimal montocambio) {
		this.montocambio = montocambio;
	}

	public String getPartidapresupuestal() {
		return partidapresupuestal;
	}

	public void setPartidapresupuestal(String partidapresupuestal) {
		this.partidapresupuestal = partidapresupuestal;
	}

	public String getGenerarcontratoflag() {
		return generarcontratoflag;
	}

	public void setGenerarcontratoflag(String generarcontratoflag) {
		this.generarcontratoflag = generarcontratoflag;
	}

	public String getLicitacionasignadaflag() {
		return licitacionasignadaflag;
	}

	public void setLicitacionasignadaflag(String licitacionasignadaflag) {
		this.licitacionasignadaflag = licitacionasignadaflag;
	}

	public String getTipoadj() {
		return tipoadj;
	}

	public void setTipoadj(String tipoadj) {
		this.tipoadj = tipoadj;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getAuxpar_name() {
		return auxpar_name;
	}

	public void setAuxpar_name(String auxpar_name) {
		this.auxpar_name = auxpar_name;
	}

	public String getAuxpar_exoneradoflag() {
		return auxpar_exoneradoflag;
	}

	public void setAuxpar_exoneradoflag(String auxpar_exoneradoflag) {
		this.auxpar_exoneradoflag = auxpar_exoneradoflag;
	}

	public Integer getTransaccionid() {
		return transaccionid;
	}

	public void setTransaccionid(Integer transaccionid) {
		this.transaccionid = transaccionid;
	}

	public List<WorkFlowAdjunto> getLstAdjuntos() {
		return lstAdjuntos;
	}

	public void setLstAdjuntos(List<WorkFlowAdjunto> lstAdjuntos) {
		this.lstAdjuntos = lstAdjuntos;
	}

	public String getAuxComentarioVal() {
		return auxComentarioVal;
	}

	public void setAuxComentarioVal(String auxComentarioVal) {
		this.auxComentarioVal = auxComentarioVal;
	}

	public String getCompradirectaflag() {
		return compradirectaflag;
	}

	public String getVentanaAccion() {
		return ventanaAccion;
	}

	public void setVentanaAccion(String ventanaAccion) {
		this.ventanaAccion = ventanaAccion;
	}

	public void setCompradirectaflag(String compradirectaflag) {
		this.compradirectaflag = compradirectaflag;
	}

	public BigDecimal getTotalCot() {
		return totalCot;
	}

	public void setTotalCot(BigDecimal totalCot) {
		this.totalCot = totalCot;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public String getAuxVentana() {
		return auxVentana;
	}

	public void setAuxVentana(String auxVentana) {
		this.auxVentana = auxVentana;
	}

	public Date getFechaEntregaDocumentos() {
		return fechaEntregaDocumentos;
	}

	public void setFechaEntregaDocumentos(Date fechaEntregaDocumentos) {
		this.fechaEntregaDocumentos = fechaEntregaDocumentos;
	}

}
