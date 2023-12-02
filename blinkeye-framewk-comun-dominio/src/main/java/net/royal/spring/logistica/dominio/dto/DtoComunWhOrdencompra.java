package net.royal.spring.logistica.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunWhOrdencompra extends DominioTransaccion implements java.io.Serializable  {
	
	private String companiasocio;
	private String compania;
	private String tiponombre;
	private String descripcion;
	private String numeroorden;
	private String clasificacion;
	private String unidadnegocio;
	private Integer proveedor;
	private String monedacodigo;
	private String almacencodigo;
	private String localforeignflag;
	private java.util.Date fechaprometida;
	private java.util.Date fechapreparacion;
	private java.util.Date fecharevision;
	private java.util.Date fechaaprobacion;
	private Integer preparadapor;
	private Integer revisadapor;
	private Integer aprobadapor;
	private String tiposervicio;
	private java.math.BigDecimal montobruto;
	private java.math.BigDecimal montoigv;
	private java.math.BigDecimal montootros;
	private java.math.BigDecimal montoflete;
	private java.math.BigDecimal montototal;
	private java.math.BigDecimal montopendientedepago;
	private java.math.BigDecimal tipodecambio;
	private java.math.BigDecimal factorotros;
	private String formadepago;
	private Integer impresionnumero;
	private String carpetanumero;
	private String numerocontrato;
	private String numerocartafianza;
	private String viatransporte;
	private String responsablecodigo;
	private String racionflag;
	private String pagarconadelantoflag;
	private String requisicionnumero;
	private String observaciones;
	private String razonrechazo;
	private String numerointerno;
	private String estado;
	private java.util.Date ultimafechamodif;
	private String ultimousuario;
	private String observacionesentrega;
	private java.util.Date fechacontrato;
	private String numeroboleta;
	private java.util.Date fechaboleta;
	private java.util.Date fechausodesde;
	private java.util.Date fechausohasta;
	private String almacencodigoingreso;
	private Integer plazoentrega;
	private String comentariodetallado;
	private java.math.BigDecimal montonoafecto;
	private String transferenciaexternaflag;
	private String prestamonumero;
	private String prestamobanco;
	private String monedapago;
	private String entregadireccion1;
	private String entregadireccion2;
	private String proveedorcontacto;
	private String proveedorfax;
	private String transportistachofercodigo;
	private String transportistaplacavehiculo;
	private Integer representantecodigo;
	private java.util.Date fechaenvioproveedor;
	private String comitenumero;
	private String comiteflag;
	private String comitecomentario;
	private java.util.Date comitefechaaprobacion;
	private String cartafianzanumero;
	private java.math.BigDecimal cartafianzamonto;
	private java.util.Date cartafianzafecha;
	private String enviadoflag;
	private String busqueda;
	private String montobruto2;
	private String montoigv2;
	private String montootros2;
	private String montoflete2;
	private String montototal2;
	private String montopendientedepago2;
	private String tipodecambio2;
	private String montonoafecto2;
	private String localforeingflag;
	private String aprobada;
	private String revisada;
	private String preparada;
	private String accion;
	private String parametro;
	private String estadoAnterior;
	private String estadoActual;
	private String tipocambiodefault;
	private String parametroItemCuenta;
	private String parametroCommoditiesCuenta;
	private Integer parametroDigitos;
	private String tablaNombre;
	private String tablaClave;
	private String correo;
	private String totalletras;
	private String monedasigno;
	
	//aux contratos
	private String companiaContrato;
	private Integer secuenciaContrato;
	private Integer lineaContrato;
	
	 private String proveedornombre;
	 private String companianombre;
	
	private BigDecimal ROWNUM_;
	
	private java.util.Date fechadesde;
	private java.util.Date fechahasta;
	
	

	public java.util.Date getFechadesde() {
		return fechadesde;
	}

	public void setFechadesde(java.util.Date fechadesde) {
		this.fechadesde = fechadesde;
	}

	public java.util.Date getFechahasta() {
		return fechahasta;
	}

	public void setFechahasta(java.util.Date fechahasta) {
		this.fechahasta = fechahasta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTiponombre() {
		return tiponombre;
	}

	public void setTiponombre(String tiponombre) {
		this.tiponombre = tiponombre;
	}

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public String getCompaniasocio() {
		return companiasocio;
	}

	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}

	public String getNumeroorden() {
		return numeroorden;
	}

	public void setNumeroorden(String numeroorden) {
		this.numeroorden = numeroorden;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getUnidadnegocio() {
		return unidadnegocio;
	}

	public void setUnidadnegocio(String unidadnegocio) {
		this.unidadnegocio = unidadnegocio;
	}

	public Integer getProveedor() {
		return proveedor;
	}

	public void setProveedor(Integer proveedor) {
		this.proveedor = proveedor;
	}

	public String getMonedacodigo() {
		return monedacodigo;
	}

	public void setMonedacodigo(String monedacodigo) {
		this.monedacodigo = monedacodigo;
	}

	public String getAlmacencodigo() {
		return almacencodigo;
	}

	public void setAlmacencodigo(String almacencodigo) {
		this.almacencodigo = almacencodigo;
	}

	public String getLocalforeignflag() {
		return localforeignflag;
	}

	public void setLocalforeignflag(String localforeignflag) {
		this.localforeignflag = localforeignflag;
	}

	public java.util.Date getFechaprometida() {
		return fechaprometida;
	}

	public void setFechaprometida(java.util.Date fechaprometida) {
		this.fechaprometida = fechaprometida;
	}

	public java.util.Date getFechapreparacion() {
		return fechapreparacion;
	}

	public void setFechapreparacion(java.util.Date fechapreparacion) {
		this.fechapreparacion = fechapreparacion;
	}

	public java.util.Date getFecharevision() {
		return fecharevision;
	}

	public void setFecharevision(java.util.Date fecharevision) {
		this.fecharevision = fecharevision;
	}

	public java.util.Date getFechaaprobacion() {
		return fechaaprobacion;
	}

	public void setFechaaprobacion(java.util.Date fechaaprobacion) {
		this.fechaaprobacion = fechaaprobacion;
	}

	public Integer getPreparadapor() {
		return preparadapor;
	}

	public void setPreparadapor(Integer preparadapor) {
		this.preparadapor = preparadapor;
	}

	public Integer getRevisadapor() {
		return revisadapor;
	}

	public void setRevisadapor(Integer revisadapor) {
		this.revisadapor = revisadapor;
	}

	public Integer getAprobadapor() {
		return aprobadapor;
	}

	public void setAprobadapor(Integer aprobadapor) {
		this.aprobadapor = aprobadapor;
	}

	public String getTiposervicio() {
		return tiposervicio;
	}

	public void setTiposervicio(String tiposervicio) {
		this.tiposervicio = tiposervicio;
	}

	public java.math.BigDecimal getMontobruto() {
		return montobruto;
	}

	public void setMontobruto(java.math.BigDecimal montobruto) {
		this.montobruto = montobruto;
	}

	public java.math.BigDecimal getMontoigv() {
		return montoigv;
	}

	public void setMontoigv(java.math.BigDecimal montoigv) {
		this.montoigv = montoigv;
	}

	public java.math.BigDecimal getMontootros() {
		return montootros;
	}

	public void setMontootros(java.math.BigDecimal montootros) {
		this.montootros = montootros;
	}

	public java.math.BigDecimal getMontoflete() {
		return montoflete;
	}

	public void setMontoflete(java.math.BigDecimal montoflete) {
		this.montoflete = montoflete;
	}

	public java.math.BigDecimal getMontototal() {
		return montototal;
	}

	public void setMontototal(java.math.BigDecimal montototal) {
		this.montototal = montototal;
	}

	public java.math.BigDecimal getMontopendientedepago() {
		return montopendientedepago;
	}

	public void setMontopendientedepago(java.math.BigDecimal montopendientedepago) {
		this.montopendientedepago = montopendientedepago;
	}

	public java.math.BigDecimal getTipodecambio() {
		return tipodecambio;
	}

	public void setTipodecambio(java.math.BigDecimal tipodecambio) {
		this.tipodecambio = tipodecambio;
	}

	public java.math.BigDecimal getFactorotros() {
		return factorotros;
	}

	public void setFactorotros(java.math.BigDecimal factorotros) {
		this.factorotros = factorotros;
	}

	public String getFormadepago() {
		return formadepago;
	}

	public void setFormadepago(String formadepago) {
		this.formadepago = formadepago;
	}

	public Integer getImpresionnumero() {
		return impresionnumero;
	}

	public void setImpresionnumero(Integer impresionnumero) {
		this.impresionnumero = impresionnumero;
	}

	public String getCarpetanumero() {
		return carpetanumero;
	}

	public void setCarpetanumero(String carpetanumero) {
		this.carpetanumero = carpetanumero;
	}

	public String getNumerocontrato() {
		return numerocontrato;
	}

	public void setNumerocontrato(String numerocontrato) {
		this.numerocontrato = numerocontrato;
	}

	public String getNumerocartafianza() {
		return numerocartafianza;
	}

	public void setNumerocartafianza(String numerocartafianza) {
		this.numerocartafianza = numerocartafianza;
	}

	public String getViatransporte() {
		return viatransporte;
	}

	public void setViatransporte(String viatransporte) {
		this.viatransporte = viatransporte;
	}

	public String getResponsablecodigo() {
		return responsablecodigo;
	}

	public void setResponsablecodigo(String responsablecodigo) {
		this.responsablecodigo = responsablecodigo;
	}

	public String getRacionflag() {
		return racionflag;
	}

	public void setRacionflag(String racionflag) {
		this.racionflag = racionflag;
	}

	public String getPagarconadelantoflag() {
		return pagarconadelantoflag;
	}

	public void setPagarconadelantoflag(String pagarconadelantoflag) {
		this.pagarconadelantoflag = pagarconadelantoflag;
	}

	public String getRequisicionnumero() {
		return requisicionnumero;
	}

	public void setRequisicionnumero(String requisicionnumero) {
		this.requisicionnumero = requisicionnumero;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getRazonrechazo() {
		return razonrechazo;
	}

	public void setRazonrechazo(String razonrechazo) {
		this.razonrechazo = razonrechazo;
	}

	public String getNumerointerno() {
		return numerointerno;
	}

	public void setNumerointerno(String numerointerno) {
		this.numerointerno = numerointerno;
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

	public String getObservacionesentrega() {
		return observacionesentrega;
	}

	public void setObservacionesentrega(String observacionesentrega) {
		this.observacionesentrega = observacionesentrega;
	}

	public java.util.Date getFechacontrato() {
		return fechacontrato;
	}

	public void setFechacontrato(java.util.Date fechacontrato) {
		this.fechacontrato = fechacontrato;
	}

	public String getNumeroboleta() {
		return numeroboleta;
	}

	public void setNumeroboleta(String numeroboleta) {
		this.numeroboleta = numeroboleta;
	}

	public java.util.Date getFechaboleta() {
		return fechaboleta;
	}

	public void setFechaboleta(java.util.Date fechaboleta) {
		this.fechaboleta = fechaboleta;
	}

	public java.util.Date getFechausodesde() {
		return fechausodesde;
	}

	public void setFechausodesde(java.util.Date fechausodesde) {
		this.fechausodesde = fechausodesde;
	}

	public java.util.Date getFechausohasta() {
		return fechausohasta;
	}

	public void setFechausohasta(java.util.Date fechausohasta) {
		this.fechausohasta = fechausohasta;
	}

	public String getAlmacencodigoingreso() {
		return almacencodigoingreso;
	}

	public void setAlmacencodigoingreso(String almacencodigoingreso) {
		this.almacencodigoingreso = almacencodigoingreso;
	}

	public Integer getPlazoentrega() {
		return plazoentrega;
	}

	public void setPlazoentrega(Integer plazoentrega) {
		this.plazoentrega = plazoentrega;
	}

	public String getComentariodetallado() {
		return comentariodetallado;
	}

	public void setComentariodetallado(String comentariodetallado) {
		this.comentariodetallado = comentariodetallado;
	}

	public java.math.BigDecimal getMontonoafecto() {
		return montonoafecto;
	}

	public void setMontonoafecto(java.math.BigDecimal montonoafecto) {
		this.montonoafecto = montonoafecto;
	}

	public String getTransferenciaexternaflag() {
		return transferenciaexternaflag;
	}

	public void setTransferenciaexternaflag(String transferenciaexternaflag) {
		this.transferenciaexternaflag = transferenciaexternaflag;
	}

	public String getPrestamonumero() {
		return prestamonumero;
	}

	public void setPrestamonumero(String prestamonumero) {
		this.prestamonumero = prestamonumero;
	}

	public String getPrestamobanco() {
		return prestamobanco;
	}

	public void setPrestamobanco(String prestamobanco) {
		this.prestamobanco = prestamobanco;
	}

	public String getMonedapago() {
		return monedapago;
	}

	public void setMonedapago(String monedapago) {
		this.monedapago = monedapago;
	}

	public String getEntregadireccion1() {
		return entregadireccion1;
	}

	public void setEntregadireccion1(String entregadireccion1) {
		this.entregadireccion1 = entregadireccion1;
	}

	public String getEntregadireccion2() {
		return entregadireccion2;
	}

	public void setEntregadireccion2(String entregadireccion2) {
		this.entregadireccion2 = entregadireccion2;
	}

	public String getProveedorcontacto() {
		return proveedorcontacto;
	}

	public void setProveedorcontacto(String proveedorcontacto) {
		this.proveedorcontacto = proveedorcontacto;
	}

	public String getProveedorfax() {
		return proveedorfax;
	}

	public void setProveedorfax(String proveedorfax) {
		this.proveedorfax = proveedorfax;
	}

	public String getTransportistachofercodigo() {
		return transportistachofercodigo;
	}

	public void setTransportistachofercodigo(String transportistachofercodigo) {
		this.transportistachofercodigo = transportistachofercodigo;
	}

	public String getTransportistaplacavehiculo() {
		return transportistaplacavehiculo;
	}

	public void setTransportistaplacavehiculo(String transportistaplacavehiculo) {
		this.transportistaplacavehiculo = transportistaplacavehiculo;
	}

	public Integer getRepresentantecodigo() {
		return representantecodigo;
	}

	public void setRepresentantecodigo(Integer representantecodigo) {
		this.representantecodigo = representantecodigo;
	}

	public java.util.Date getFechaenvioproveedor() {
		return fechaenvioproveedor;
	}

	public void setFechaenvioproveedor(java.util.Date fechaenvioproveedor) {
		this.fechaenvioproveedor = fechaenvioproveedor;
	}

	public String getComitenumero() {
		return comitenumero;
	}

	public void setComitenumero(String comitenumero) {
		this.comitenumero = comitenumero;
	}

	public String getComiteflag() {
		return comiteflag;
	}

	public void setComiteflag(String comiteflag) {
		this.comiteflag = comiteflag;
	}

	public String getComitecomentario() {
		return comitecomentario;
	}

	public void setComitecomentario(String comitecomentario) {
		this.comitecomentario = comitecomentario;
	}

	public java.util.Date getComitefechaaprobacion() {
		return comitefechaaprobacion;
	}

	public void setComitefechaaprobacion(java.util.Date comitefechaaprobacion) {
		this.comitefechaaprobacion = comitefechaaprobacion;
	}

	public String getCartafianzanumero() {
		return cartafianzanumero;
	}

	public void setCartafianzanumero(String cartafianzanumero) {
		this.cartafianzanumero = cartafianzanumero;
	}

	public java.math.BigDecimal getCartafianzamonto() {
		return cartafianzamonto;
	}

	public void setCartafianzamonto(java.math.BigDecimal cartafianzamonto) {
		this.cartafianzamonto = cartafianzamonto;
	}

	public java.util.Date getCartafianzafecha() {
		return cartafianzafecha;
	}

	public void setCartafianzafecha(java.util.Date cartafianzafecha) {
		this.cartafianzafecha = cartafianzafecha;
	}

	public String getEnviadoflag() {
		return enviadoflag;
	}

	public void setEnviadoflag(String enviadoflag) {
		this.enviadoflag = enviadoflag;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public String getMontobruto2() {
		return montobruto2;
	}

	public void setMontobruto2(String montobruto2) {
		this.montobruto2 = montobruto2;
	}

	public String getMontoigv2() {
		return montoigv2;
	}

	public void setMontoigv2(String montoigv2) {
		this.montoigv2 = montoigv2;
	}

	public String getMontootros2() {
		return montootros2;
	}

	public void setMontootros2(String montootros2) {
		this.montootros2 = montootros2;
	}

	public String getMontoflete2() {
		return montoflete2;
	}

	public void setMontoflete2(String montoflete2) {
		this.montoflete2 = montoflete2;
	}

	public String getMontototal2() {
		return montototal2;
	}

	public void setMontototal2(String montototal2) {
		this.montototal2 = montototal2;
	}

	public String getMontopendientedepago2() {
		return montopendientedepago2;
	}

	public void setMontopendientedepago2(String montopendientedepago2) {
		this.montopendientedepago2 = montopendientedepago2;
	}

	public String getTipodecambio2() {
		return tipodecambio2;
	}

	public void setTipodecambio2(String tipodecambio2) {
		this.tipodecambio2 = tipodecambio2;
	}

	public String getMontonoafecto2() {
		return montonoafecto2;
	}

	public void setMontonoafecto2(String montonoafecto2) {
		this.montonoafecto2 = montonoafecto2;
	}

	public String getLocalforeingflag() {
		return localforeingflag;
	}

	public void setLocalforeingflag(String localforeingflag) {
		this.localforeingflag = localforeingflag;
	}

	public String getAprobada() {
		return aprobada;
	}

	public void setAprobada(String aprobada) {
		this.aprobada = aprobada;
	}

	public String getRevisada() {
		return revisada;
	}

	public void setRevisada(String revisada) {
		this.revisada = revisada;
	}

	public String getPreparada() {
		return preparada;
	}

	public void setPreparada(String preparada) {
		this.preparada = preparada;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getEstadoAnterior() {
		return estadoAnterior;
	}

	public void setEstadoAnterior(String estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}

	public String getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(String estadoActual) {
		this.estadoActual = estadoActual;
	}

	public String getTipocambiodefault() {
		return tipocambiodefault;
	}

	public void setTipocambiodefault(String tipocambiodefault) {
		this.tipocambiodefault = tipocambiodefault;
	}

	public String getParametroItemCuenta() {
		return parametroItemCuenta;
	}

	public void setParametroItemCuenta(String parametroItemCuenta) {
		this.parametroItemCuenta = parametroItemCuenta;
	}

	public String getParametroCommoditiesCuenta() {
		return parametroCommoditiesCuenta;
	}

	public void setParametroCommoditiesCuenta(String parametroCommoditiesCuenta) {
		this.parametroCommoditiesCuenta = parametroCommoditiesCuenta;
	}

	public Integer getParametroDigitos() {
		return parametroDigitos;
	}

	public void setParametroDigitos(Integer parametroDigitos) {
		this.parametroDigitos = parametroDigitos;
	}

	public String getTablaNombre() {
		return tablaNombre;
	}

	public void setTablaNombre(String tablaNombre) {
		this.tablaNombre = tablaNombre;
	}

	public String getTablaClave() {
		return tablaClave;
	}

	public void setTablaClave(String tablaClave) {
		this.tablaClave = tablaClave;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTotalletras() {
		return totalletras;
	}

	public void setTotalletras(String totalletras) {
		this.totalletras = totalletras;
	}

	public String getMonedasigno() {
		return monedasigno;
	}

	public void setMonedasigno(String monedasigno) {
		this.monedasigno = monedasigno;
	}

	public String getCompaniaContrato() {
		return companiaContrato;
	}

	public void setCompaniaContrato(String companiaContrato) {
		this.companiaContrato = companiaContrato;
	}

	public Integer getSecuenciaContrato() {
		return secuenciaContrato;
	}

	public void setSecuenciaContrato(Integer secuenciaContrato) {
		this.secuenciaContrato = secuenciaContrato;
	}

	public Integer getLineaContrato() {
		return lineaContrato;
	}

	public void setLineaContrato(Integer lineaContrato) {
		this.lineaContrato = lineaContrato;
	}

	public String getProveedornombre() {
		return proveedornombre;
	}

	public void setProveedornombre(String proveedornombre) {
		this.proveedornombre = proveedornombre;
	}

	public String getCompanianombre() {
		return companianombre;
	}

	public void setCompanianombre(String companianombre) {
		this.companianombre = companianombre;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	
	
	
	

}
