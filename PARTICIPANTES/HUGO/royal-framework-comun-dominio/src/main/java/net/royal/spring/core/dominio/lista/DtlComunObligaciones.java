package net.royal.spring.core.dominio.lista;


import net.royal.spring.framework.modelo.generico.DominioTransaccion;

/**
 * 
 * 
 * @tabla dbo.Obligaciones
*/
public class DtlComunObligaciones extends DominioTransaccion implements java.io.Serializable{


	private Integer proveedor;
	private String tipodocumento;
	private String numerodocumento;
	private String cuentabancaria;
	private String companiacodigo;
	private String unidadnegocio;
	private String responsablecodigo;
	private String tipopago;
	private java.util.Date fechadocumento;
	private java.util.Date fecharegistro;
	private java.util.Date fechavencimiento;
	private java.util.Date fechavencimientooriginal;
	private java.util.Date fecharecepcion;
	private java.util.Date fechapago;
	private String generarpago;
	private String tiposervicio;
	private String monedadocumento;
	private String conversionrequerida;
	private String monedapago;
	private String referenciatipodocumento;
	private String referencianumerodocumento;
	private String referenciacodigointerno;
	private String obligacionrelacionadatipo;
	private String obligacionrelacionadanumero;
	private java.math.BigDecimal montoobligacion;
	private java.math.BigDecimal montoimpuestoventas;
	private java.math.BigDecimal montonoafecto;
	private java.math.BigDecimal montoimponible;
	private java.math.BigDecimal montoadelantos;
	private java.math.BigDecimal montoimpuestos;
	private java.math.BigDecimal netomonedalocal;
	private java.math.BigDecimal netomonedaextranjera;
	private java.math.BigDecimal montopagoparcial;
	private java.math.BigDecimal tipodecambio;
	private Integer aprobadopor;
	private Integer aprobadocp1;
	private Integer aprobadocp2;
	private Integer ingresadopor;
	private Integer revisadopor;
	private Integer retenidopor;
	private String estadodocumento;
	private String contabilizacionpendiente;
	private String facturaafectasplitflag;
	private String chequeindividual;
	private String voucher;
	private String voucheranulacion;
	private java.util.Date fechavoucher;
	private Integer numeropago;
	private Integer numeroproceso;
	private Integer procesosecuencia;
	private Integer registronumero;
	private Integer canjeregistronumero;
	private String factorrvalidacion;
	private String comentarios;
	private String comentariosadicional;
	private String razonrechazo;
	private String unidadreplicacion;
	private Integer proveedorpagara;
	private String partidapresupuestal;
	private String flujodecaja;
	private String centrocosto;
	private String centrocostocp;
	private java.util.Date fechaaprobacion;
	private java.util.Date fecharevision;
	private String controlpresupuestalflag;
	private String numerodocumentointerno;
	private String cargoflag;
	private java.math.BigDecimal montocreditofiscal;
	private java.math.BigDecimal tipodecambioprovision;
	private String afectoigvflag;
	private String pagaranombre;
	private String diferidoflag;
	private String adelantoflag;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private java.math.BigDecimal montoretenidolocal;
	private java.math.BigDecimal montoretenidodolares;
	private String pagocajachicaflag;
	private String transferenciaexcluidaflag;
	private byte[] timestamp;
	private String pagodiferidoflag;
	private String detracciondocumento;
	private java.util.Date detraccionfecha;
	private String archivoadjunto;
	private String detraccioncodigo;
	private String detraccioncodigoflag;
	private String defaultcamporeferencia;
	private String siafExpediente;
	private String detraccionpercepcionflag;
	private java.util.Date fechaautorizacionfiscal;
	private String codigoexoneracion;
	private String referenciafiscal02;
	private String referenciafiscal01;
	private java.util.Date fechavalor;
	private String terceramonedacodigo;
	private java.math.BigDecimal terceramonedatc;
	private String semtprocesoflag;
	private String semtlote;
	private java.util.Date fechaaprobacionlv;
	private java.math.BigDecimal montoicbp;
	private java.math.BigDecimal montototal;
	
	

	public java.math.BigDecimal getMontototal() {
		return montototal;
	}

	public void setMontototal(java.math.BigDecimal montototal) {
		this.montototal = montototal;
	}

	/**
	 * 
	 * 
	 * @campo Proveedor
	*/
	public Integer getProveedor() {
		return proveedor;
	}

	/**
	 * 
	 * 
	 * @campo Proveedor
	*/
	public void setProveedor(Integer proveedor) {
		this.proveedor = proveedor;
	}
	/**
	 * 
	 * 
	 * @campo TipoDocumento
	*/
	public String getTipodocumento() {
		return tipodocumento;
	}

	/**
	 * 
	 * 
	 * @campo TipoDocumento
	*/
	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	/**
	 * 
	 * 
	 * @campo NumeroDocumento
	*/
	public String getNumerodocumento() {
		return numerodocumento;
	}

	/**
	 * 
	 * 
	 * @campo NumeroDocumento
	*/
	public void setNumerodocumento(String numerodocumento) {
		this.numerodocumento = numerodocumento;
	}
	/**
	 * 
	 * 
	 * @campo CuentaBancaria
	*/
	public String getCuentabancaria() {
		return cuentabancaria;
	}

	/**
	 * 
	 * 
	 * @campo CuentaBancaria
	*/
	public void setCuentabancaria(String cuentabancaria) {
		this.cuentabancaria = cuentabancaria;
	}
	/**
	 * 
	 * 
	 * @campo CompaniaCodigo
	*/
	public String getCompaniacodigo() {
		return companiacodigo;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaCodigo
	*/
	public void setCompaniacodigo(String companiacodigo) {
		this.companiacodigo = companiacodigo;
	}
	/**
	 * 
	 * 
	 * @campo UnidadNegocio
	*/
	public String getUnidadnegocio() {
		return unidadnegocio;
	}

	/**
	 * 
	 * 
	 * @campo UnidadNegocio
	*/
	public void setUnidadnegocio(String unidadnegocio) {
		this.unidadnegocio = unidadnegocio;
	}
	/**
	 * 
	 * 
	 * @campo ResponsableCodigo
	*/
	public String getResponsablecodigo() {
		return responsablecodigo;
	}

	/**
	 * 
	 * 
	 * @campo ResponsableCodigo
	*/
	public void setResponsablecodigo(String responsablecodigo) {
		this.responsablecodigo = responsablecodigo;
	}
	/**
	 * 
	 * 
	 * @campo TipoPago
	*/
	public String getTipopago() {
		return tipopago;
	}

	/**
	 * 
	 * 
	 * @campo TipoPago
	*/
	public void setTipopago(String tipopago) {
		this.tipopago = tipopago;
	}
	/**
	 * 
	 * 
	 * @campo FechaDocumento
	*/
	public java.util.Date getFechadocumento() {
		return fechadocumento;
	}

	/**
	 * 
	 * 
	 * @campo FechaDocumento
	*/
	public void setFechadocumento(java.util.Date fechadocumento) {
		this.fechadocumento = fechadocumento;
	}
	/**
	 * 
	 * 
	 * @campo FechaRegistro
	*/
	public java.util.Date getFecharegistro() {
		return fecharegistro;
	}

	/**
	 * 
	 * 
	 * @campo FechaRegistro
	*/
	public void setFecharegistro(java.util.Date fecharegistro) {
		this.fecharegistro = fecharegistro;
	}
	/**
	 * 
	 * 
	 * @campo FechaVencimiento
	*/
	public java.util.Date getFechavencimiento() {
		return fechavencimiento;
	}

	/**
	 * 
	 * 
	 * @campo FechaVencimiento
	*/
	public void setFechavencimiento(java.util.Date fechavencimiento) {
		this.fechavencimiento = fechavencimiento;
	}
	/**
	 * 
	 * 
	 * @campo FechaVencimientoOriginal
	*/
	public java.util.Date getFechavencimientooriginal() {
		return fechavencimientooriginal;
	}

	/**
	 * 
	 * 
	 * @campo FechaVencimientoOriginal
	*/
	public void setFechavencimientooriginal(java.util.Date fechavencimientooriginal) {
		this.fechavencimientooriginal = fechavencimientooriginal;
	}
	/**
	 * 
	 * 
	 * @campo FechaRecepcion
	*/
	public java.util.Date getFecharecepcion() {
		return fecharecepcion;
	}

	/**
	 * 
	 * 
	 * @campo FechaRecepcion
	*/
	public void setFecharecepcion(java.util.Date fecharecepcion) {
		this.fecharecepcion = fecharecepcion;
	}
	/**
	 * 
	 * 
	 * @campo FechaPago
	*/
	public java.util.Date getFechapago() {
		return fechapago;
	}

	/**
	 * 
	 * 
	 * @campo FechaPago
	*/
	public void setFechapago(java.util.Date fechapago) {
		this.fechapago = fechapago;
	}
	/**
	 * 
	 * 
	 * @campo GenerarPago
	*/
	public String getGenerarpago() {
		return generarpago;
	}

	/**
	 * 
	 * 
	 * @campo GenerarPago
	*/
	public void setGenerarpago(String generarpago) {
		this.generarpago = generarpago;
	}
	/**
	 * 
	 * 
	 * @campo TipoServicio
	*/
	public String getTiposervicio() {
		return tiposervicio;
	}

	/**
	 * 
	 * 
	 * @campo TipoServicio
	*/
	public void setTiposervicio(String tiposervicio) {
		this.tiposervicio = tiposervicio;
	}
	/**
	 * 
	 * 
	 * @campo MonedaDocumento
	*/
	public String getMonedadocumento() {
		return monedadocumento;
	}

	/**
	 * 
	 * 
	 * @campo MonedaDocumento
	*/
	public void setMonedadocumento(String monedadocumento) {
		this.monedadocumento = monedadocumento;
	}
	/**
	 * 
	 * 
	 * @campo ConversionRequerida
	*/
	public String getConversionrequerida() {
		return conversionrequerida;
	}

	/**
	 * 
	 * 
	 * @campo ConversionRequerida
	*/
	public void setConversionrequerida(String conversionrequerida) {
		this.conversionrequerida = conversionrequerida;
	}
	/**
	 * 
	 * 
	 * @campo MonedaPago
	*/
	public String getMonedapago() {
		return monedapago;
	}

	/**
	 * 
	 * 
	 * @campo MonedaPago
	*/
	public void setMonedapago(String monedapago) {
		this.monedapago = monedapago;
	}
	/**
	 * 
	 * 
	 * @campo ReferenciaTipoDocumento
	*/
	public String getReferenciatipodocumento() {
		return referenciatipodocumento;
	}

	/**
	 * 
	 * 
	 * @campo ReferenciaTipoDocumento
	*/
	public void setReferenciatipodocumento(String referenciatipodocumento) {
		this.referenciatipodocumento = referenciatipodocumento;
	}
	/**
	 * 
	 * 
	 * @campo ReferenciaNumeroDocumento
	*/
	public String getReferencianumerodocumento() {
		return referencianumerodocumento;
	}

	/**
	 * 
	 * 
	 * @campo ReferenciaNumeroDocumento
	*/
	public void setReferencianumerodocumento(String referencianumerodocumento) {
		this.referencianumerodocumento = referencianumerodocumento;
	}
	/**
	 * 
	 * 
	 * @campo ReferenciaCodigoInterno
	*/
	public String getReferenciacodigointerno() {
		return referenciacodigointerno;
	}

	/**
	 * 
	 * 
	 * @campo ReferenciaCodigoInterno
	*/
	public void setReferenciacodigointerno(String referenciacodigointerno) {
		this.referenciacodigointerno = referenciacodigointerno;
	}
	/**
	 * 
	 * 
	 * @campo ObligacionRelacionadaTipo
	*/
	public String getObligacionrelacionadatipo() {
		return obligacionrelacionadatipo;
	}

	/**
	 * 
	 * 
	 * @campo ObligacionRelacionadaTipo
	*/
	public void setObligacionrelacionadatipo(String obligacionrelacionadatipo) {
		this.obligacionrelacionadatipo = obligacionrelacionadatipo;
	}
	/**
	 * 
	 * 
	 * @campo ObligacionRelacionadaNumero
	*/
	public String getObligacionrelacionadanumero() {
		return obligacionrelacionadanumero;
	}

	/**
	 * 
	 * 
	 * @campo ObligacionRelacionadaNumero
	*/
	public void setObligacionrelacionadanumero(String obligacionrelacionadanumero) {
		this.obligacionrelacionadanumero = obligacionrelacionadanumero;
	}
	/**
	 * 
	 * 
	 * @campo MontoObligacion
	*/
	public java.math.BigDecimal getMontoobligacion() {
		return montoobligacion;
	}

	/**
	 * 
	 * 
	 * @campo MontoObligacion
	*/
	public void setMontoobligacion(java.math.BigDecimal montoobligacion) {
		this.montoobligacion = montoobligacion;
	}
	/**
	 * 
	 * 
	 * @campo MontoImpuestoVentas
	*/
	public java.math.BigDecimal getMontoimpuestoventas() {
		return montoimpuestoventas;
	}

	/**
	 * 
	 * 
	 * @campo MontoImpuestoVentas
	*/
	public void setMontoimpuestoventas(java.math.BigDecimal montoimpuestoventas) {
		this.montoimpuestoventas = montoimpuestoventas;
	}
	/**
	 * 
	 * 
	 * @campo MontoNoAfecto
	*/
	public java.math.BigDecimal getMontonoafecto() {
		return montonoafecto;
	}

	/**
	 * 
	 * 
	 * @campo MontoNoAfecto
	*/
	public void setMontonoafecto(java.math.BigDecimal montonoafecto) {
		this.montonoafecto = montonoafecto;
	}
	/**
	 * 
	 * 
	 * @campo MontoImponible
	*/
	public java.math.BigDecimal getMontoimponible() {
		return montoimponible;
	}

	/**
	 * 
	 * 
	 * @campo MontoImponible
	*/
	public void setMontoimponible(java.math.BigDecimal montoimponible) {
		this.montoimponible = montoimponible;
	}
	/**
	 * 
	 * 
	 * @campo MontoAdelantos
	*/
	public java.math.BigDecimal getMontoadelantos() {
		return montoadelantos;
	}

	/**
	 * 
	 * 
	 * @campo MontoAdelantos
	*/
	public void setMontoadelantos(java.math.BigDecimal montoadelantos) {
		this.montoadelantos = montoadelantos;
	}
	/**
	 * 
	 * 
	 * @campo MontoImpuestos
	*/
	public java.math.BigDecimal getMontoimpuestos() {
		return montoimpuestos;
	}

	/**
	 * 
	 * 
	 * @campo MontoImpuestos
	*/
	public void setMontoimpuestos(java.math.BigDecimal montoimpuestos) {
		this.montoimpuestos = montoimpuestos;
	}
	/**
	 * 
	 * 
	 * @campo NetoMonedaLocal
	*/
	public java.math.BigDecimal getNetomonedalocal() {
		return netomonedalocal;
	}

	/**
	 * 
	 * 
	 * @campo NetoMonedaLocal
	*/
	public void setNetomonedalocal(java.math.BigDecimal netomonedalocal) {
		this.netomonedalocal = netomonedalocal;
	}
	/**
	 * 
	 * 
	 * @campo NetoMonedaExtranjera
	*/
	public java.math.BigDecimal getNetomonedaextranjera() {
		return netomonedaextranjera;
	}

	/**
	 * 
	 * 
	 * @campo NetoMonedaExtranjera
	*/
	public void setNetomonedaextranjera(java.math.BigDecimal netomonedaextranjera) {
		this.netomonedaextranjera = netomonedaextranjera;
	}
	/**
	 * 
	 * 
	 * @campo MontoPagoParcial
	*/
	public java.math.BigDecimal getMontopagoparcial() {
		return montopagoparcial;
	}

	/**
	 * 
	 * 
	 * @campo MontoPagoParcial
	*/
	public void setMontopagoparcial(java.math.BigDecimal montopagoparcial) {
		this.montopagoparcial = montopagoparcial;
	}
	/**
	 * 
	 * 
	 * @campo TipoDeCambio
	*/
	public java.math.BigDecimal getTipodecambio() {
		return tipodecambio;
	}

	/**
	 * 
	 * 
	 * @campo TipoDeCambio
	*/
	public void setTipodecambio(java.math.BigDecimal tipodecambio) {
		this.tipodecambio = tipodecambio;
	}
	/**
	 * 
	 * 
	 * @campo AprobadoPor
	*/
	public Integer getAprobadopor() {
		return aprobadopor;
	}

	/**
	 * 
	 * 
	 * @campo AprobadoPor
	*/
	public void setAprobadopor(Integer aprobadopor) {
		this.aprobadopor = aprobadopor;
	}
	/**
	 * 
	 * 
	 * @campo AprobadoCP1
	*/
	public Integer getAprobadocp1() {
		return aprobadocp1;
	}

	/**
	 * 
	 * 
	 * @campo AprobadoCP1
	*/
	public void setAprobadocp1(Integer aprobadocp1) {
		this.aprobadocp1 = aprobadocp1;
	}
	/**
	 * 
	 * 
	 * @campo AprobadoCP2
	*/
	public Integer getAprobadocp2() {
		return aprobadocp2;
	}

	/**
	 * 
	 * 
	 * @campo AprobadoCP2
	*/
	public void setAprobadocp2(Integer aprobadocp2) {
		this.aprobadocp2 = aprobadocp2;
	}
	/**
	 * 
	 * 
	 * @campo IngresadoPor
	*/
	public Integer getIngresadopor() {
		return ingresadopor;
	}

	/**
	 * 
	 * 
	 * @campo IngresadoPor
	*/
	public void setIngresadopor(Integer ingresadopor) {
		this.ingresadopor = ingresadopor;
	}
	/**
	 * 
	 * 
	 * @campo RevisadoPor
	*/
	public Integer getRevisadopor() {
		return revisadopor;
	}

	/**
	 * 
	 * 
	 * @campo RevisadoPor
	*/
	public void setRevisadopor(Integer revisadopor) {
		this.revisadopor = revisadopor;
	}
	/**
	 * 
	 * 
	 * @campo RetenidoPor
	*/
	public Integer getRetenidopor() {
		return retenidopor;
	}

	/**
	 * 
	 * 
	 * @campo RetenidoPor
	*/
	public void setRetenidopor(Integer retenidopor) {
		this.retenidopor = retenidopor;
	}
	/**
	 * 
	 * 
	 * @campo EstadoDocumento
	*/
	public String getEstadodocumento() {
		return estadodocumento;
	}

	/**
	 * 
	 * 
	 * @campo EstadoDocumento
	*/
	public void setEstadodocumento(String estadodocumento) {
		this.estadodocumento = estadodocumento;
	}
	/**
	 * 
	 * 
	 * @campo ContabilizacionPendiente
	*/
	public String getContabilizacionpendiente() {
		return contabilizacionpendiente;
	}

	/**
	 * 
	 * 
	 * @campo ContabilizacionPendiente
	*/
	public void setContabilizacionpendiente(String contabilizacionpendiente) {
		this.contabilizacionpendiente = contabilizacionpendiente;
	}
	/**
	 * 
	 * 
	 * @campo FacturaAfectaSplitFlag
	*/
	public String getFacturaafectasplitflag() {
		return facturaafectasplitflag;
	}

	/**
	 * 
	 * 
	 * @campo FacturaAfectaSplitFlag
	*/
	public void setFacturaafectasplitflag(String facturaafectasplitflag) {
		this.facturaafectasplitflag = facturaafectasplitflag;
	}
	/**
	 * 
	 * 
	 * @campo ChequeIndividual
	*/
	public String getChequeindividual() {
		return chequeindividual;
	}

	/**
	 * 
	 * 
	 * @campo ChequeIndividual
	*/
	public void setChequeindividual(String chequeindividual) {
		this.chequeindividual = chequeindividual;
	}
	/**
	 * 
	 * 
	 * @campo Voucher
	*/
	public String getVoucher() {
		return voucher;
	}

	/**
	 * 
	 * 
	 * @campo Voucher
	*/
	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}
	/**
	 * 
	 * 
	 * @campo VoucherAnulacion
	*/
	public String getVoucheranulacion() {
		return voucheranulacion;
	}

	/**
	 * 
	 * 
	 * @campo VoucherAnulacion
	*/
	public void setVoucheranulacion(String voucheranulacion) {
		this.voucheranulacion = voucheranulacion;
	}
	/**
	 * 
	 * 
	 * @campo FechaVoucher
	*/
	public java.util.Date getFechavoucher() {
		return fechavoucher;
	}

	/**
	 * 
	 * 
	 * @campo FechaVoucher
	*/
	public void setFechavoucher(java.util.Date fechavoucher) {
		this.fechavoucher = fechavoucher;
	}
	/**
	 * 
	 * 
	 * @campo NumeroPago
	*/
	public Integer getNumeropago() {
		return numeropago;
	}

	/**
	 * 
	 * 
	 * @campo NumeroPago
	*/
	public void setNumeropago(Integer numeropago) {
		this.numeropago = numeropago;
	}
	/**
	 * 
	 * 
	 * @campo NumeroProceso
	*/
	public Integer getNumeroproceso() {
		return numeroproceso;
	}

	/**
	 * 
	 * 
	 * @campo NumeroProceso
	*/
	public void setNumeroproceso(Integer numeroproceso) {
		this.numeroproceso = numeroproceso;
	}
	/**
	 * 
	 * 
	 * @campo ProcesoSecuencia
	*/
	public Integer getProcesosecuencia() {
		return procesosecuencia;
	}

	/**
	 * 
	 * 
	 * @campo ProcesoSecuencia
	*/
	public void setProcesosecuencia(Integer procesosecuencia) {
		this.procesosecuencia = procesosecuencia;
	}
	/**
	 * 
	 * 
	 * @campo RegistroNumero
	*/
	public Integer getRegistronumero() {
		return registronumero;
	}

	/**
	 * 
	 * 
	 * @campo RegistroNumero
	*/
	public void setRegistronumero(Integer registronumero) {
		this.registronumero = registronumero;
	}
	/**
	 * 
	 * 
	 * @campo CanjeRegistroNumero
	*/
	public Integer getCanjeregistronumero() {
		return canjeregistronumero;
	}

	/**
	 * 
	 * 
	 * @campo CanjeRegistroNumero
	*/
	public void setCanjeregistronumero(Integer canjeregistronumero) {
		this.canjeregistronumero = canjeregistronumero;
	}
	/**
	 * 
	 * 
	 * @campo FactorRValidacion
	*/
	public String getFactorrvalidacion() {
		return factorrvalidacion;
	}

	/**
	 * 
	 * 
	 * @campo FactorRValidacion
	*/
	public void setFactorrvalidacion(String factorrvalidacion) {
		this.factorrvalidacion = factorrvalidacion;
	}
	/**
	 * 
	 * 
	 * @campo Comentarios
	*/
	public String getComentarios() {
		return comentarios;
	}

	/**
	 * 
	 * 
	 * @campo Comentarios
	*/
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	/**
	 * 
	 * 
	 * @campo ComentariosAdicional
	*/
	public String getComentariosadicional() {
		return comentariosadicional;
	}

	/**
	 * 
	 * 
	 * @campo ComentariosAdicional
	*/
	public void setComentariosadicional(String comentariosadicional) {
		this.comentariosadicional = comentariosadicional;
	}
	/**
	 * 
	 * 
	 * @campo RazonRechazo
	*/
	public String getRazonrechazo() {
		return razonrechazo;
	}

	/**
	 * 
	 * 
	 * @campo RazonRechazo
	*/
	public void setRazonrechazo(String razonrechazo) {
		this.razonrechazo = razonrechazo;
	}
	/**
	 * 
	 * 
	 * @campo UnidadReplicacion
	*/
	public String getUnidadreplicacion() {
		return unidadreplicacion;
	}

	/**
	 * 
	 * 
	 * @campo UnidadReplicacion
	*/
	public void setUnidadreplicacion(String unidadreplicacion) {
		this.unidadreplicacion = unidadreplicacion;
	}
	/**
	 * 
	 * 
	 * @campo ProveedorPagarA
	*/
	public Integer getProveedorpagara() {
		return proveedorpagara;
	}

	/**
	 * 
	 * 
	 * @campo ProveedorPagarA
	*/
	public void setProveedorpagara(Integer proveedorpagara) {
		this.proveedorpagara = proveedorpagara;
	}
	/**
	 * 
	 * 
	 * @campo PartidaPresupuestal
	*/
	public String getPartidapresupuestal() {
		return partidapresupuestal;
	}

	/**
	 * 
	 * 
	 * @campo PartidaPresupuestal
	*/
	public void setPartidapresupuestal(String partidapresupuestal) {
		this.partidapresupuestal = partidapresupuestal;
	}
	/**
	 * 
	 * 
	 * @campo FlujodeCaja
	*/
	public String getFlujodecaja() {
		return flujodecaja;
	}

	/**
	 * 
	 * 
	 * @campo FlujodeCaja
	*/
	public void setFlujodecaja(String flujodecaja) {
		this.flujodecaja = flujodecaja;
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
	 * @campo CentroCostoCP
	*/
	public String getCentrocostocp() {
		return centrocostocp;
	}

	/**
	 * 
	 * 
	 * @campo CentroCostoCP
	*/
	public void setCentrocostocp(String centrocostocp) {
		this.centrocostocp = centrocostocp;
	}
	/**
	 * 
	 * 
	 * @campo FechaAprobacion
	*/
	public java.util.Date getFechaaprobacion() {
		return fechaaprobacion;
	}

	/**
	 * 
	 * 
	 * @campo FechaAprobacion
	*/
	public void setFechaaprobacion(java.util.Date fechaaprobacion) {
		this.fechaaprobacion = fechaaprobacion;
	}
	/**
	 * 
	 * 
	 * @campo FechaRevision
	*/
	public java.util.Date getFecharevision() {
		return fecharevision;
	}

	/**
	 * 
	 * 
	 * @campo FechaRevision
	*/
	public void setFecharevision(java.util.Date fecharevision) {
		this.fecharevision = fecharevision;
	}
	/**
	 * 
	 * 
	 * @campo ControlPresupuestalFlag
	*/
	public String getControlpresupuestalflag() {
		return controlpresupuestalflag;
	}

	/**
	 * 
	 * 
	 * @campo ControlPresupuestalFlag
	*/
	public void setControlpresupuestalflag(String controlpresupuestalflag) {
		this.controlpresupuestalflag = controlpresupuestalflag;
	}
	/**
	 * 
	 * 
	 * @campo NumeroDocumentoInterno
	*/
	public String getNumerodocumentointerno() {
		return numerodocumentointerno;
	}

	/**
	 * 
	 * 
	 * @campo NumeroDocumentoInterno
	*/
	public void setNumerodocumentointerno(String numerodocumentointerno) {
		this.numerodocumentointerno = numerodocumentointerno;
	}
	/**
	 * 
	 * 
	 * @campo CargoFlag
	*/
	public String getCargoflag() {
		return cargoflag;
	}

	/**
	 * 
	 * 
	 * @campo CargoFlag
	*/
	public void setCargoflag(String cargoflag) {
		this.cargoflag = cargoflag;
	}
	/**
	 * 
	 * 
	 * @campo MontoCreditoFiscal
	*/
	public java.math.BigDecimal getMontocreditofiscal() {
		return montocreditofiscal;
	}

	/**
	 * 
	 * 
	 * @campo MontoCreditoFiscal
	*/
	public void setMontocreditofiscal(java.math.BigDecimal montocreditofiscal) {
		this.montocreditofiscal = montocreditofiscal;
	}
	/**
	 * 
	 * 
	 * @campo TipodeCambioProvision
	*/
	public java.math.BigDecimal getTipodecambioprovision() {
		return tipodecambioprovision;
	}

	/**
	 * 
	 * 
	 * @campo TipodeCambioProvision
	*/
	public void setTipodecambioprovision(java.math.BigDecimal tipodecambioprovision) {
		this.tipodecambioprovision = tipodecambioprovision;
	}
	/**
	 * 
	 * 
	 * @campo AfectoIGVFlag
	*/
	public String getAfectoigvflag() {
		return afectoigvflag;
	}

	/**
	 * 
	 * 
	 * @campo AfectoIGVFlag
	*/
	public void setAfectoigvflag(String afectoigvflag) {
		this.afectoigvflag = afectoigvflag;
	}
	/**
	 * 
	 * 
	 * @campo PagaraNombre
	*/
	public String getPagaranombre() {
		return pagaranombre;
	}

	/**
	 * 
	 * 
	 * @campo PagaraNombre
	*/
	public void setPagaranombre(String pagaranombre) {
		this.pagaranombre = pagaranombre;
	}
	/**
	 * 
	 * 
	 * @campo DiferidoFlag
	*/
	public String getDiferidoflag() {
		return diferidoflag;
	}

	/**
	 * 
	 * 
	 * @campo DiferidoFlag
	*/
	public void setDiferidoflag(String diferidoflag) {
		this.diferidoflag = diferidoflag;
	}
	/**
	 * 
	 * 
	 * @campo AdelantoFlag
	*/
	public String getAdelantoflag() {
		return adelantoflag;
	}

	/**
	 * 
	 * 
	 * @campo AdelantoFlag
	*/
	public void setAdelantoflag(String adelantoflag) {
		this.adelantoflag = adelantoflag;
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
	 * @campo MontoRetenidoLocal
	*/
	public java.math.BigDecimal getMontoretenidolocal() {
		return montoretenidolocal;
	}

	/**
	 * 
	 * 
	 * @campo MontoRetenidoLocal
	*/
	public void setMontoretenidolocal(java.math.BigDecimal montoretenidolocal) {
		this.montoretenidolocal = montoretenidolocal;
	}
	/**
	 * 
	 * 
	 * @campo MontoRetenidoDolares
	*/
	public java.math.BigDecimal getMontoretenidodolares() {
		return montoretenidodolares;
	}

	/**
	 * 
	 * 
	 * @campo MontoRetenidoDolares
	*/
	public void setMontoretenidodolares(java.math.BigDecimal montoretenidodolares) {
		this.montoretenidodolares = montoretenidodolares;
	}
	/**
	 * 
	 * 
	 * @campo PagoCajaChicaFlag
	*/
	public String getPagocajachicaflag() {
		return pagocajachicaflag;
	}

	/**
	 * 
	 * 
	 * @campo PagoCajaChicaFlag
	*/
	public void setPagocajachicaflag(String pagocajachicaflag) {
		this.pagocajachicaflag = pagocajachicaflag;
	}
	/**
	 * 
	 * 
	 * @campo TransferenciaExcluidaFlag
	*/
	public String getTransferenciaexcluidaflag() {
		return transferenciaexcluidaflag;
	}

	/**
	 * 
	 * 
	 * @campo TransferenciaExcluidaFlag
	*/
	public void setTransferenciaexcluidaflag(String transferenciaexcluidaflag) {
		this.transferenciaexcluidaflag = transferenciaexcluidaflag;
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
	 * @campo PagoDiferidoFlag
	*/
	public String getPagodiferidoflag() {
		return pagodiferidoflag;
	}

	/**
	 * 
	 * 
	 * @campo PagoDiferidoFlag
	*/
	public void setPagodiferidoflag(String pagodiferidoflag) {
		this.pagodiferidoflag = pagodiferidoflag;
	}
	/**
	 * 
	 * 
	 * @campo DetraccionDocumento
	*/
	public String getDetracciondocumento() {
		return detracciondocumento;
	}

	/**
	 * 
	 * 
	 * @campo DetraccionDocumento
	*/
	public void setDetracciondocumento(String detracciondocumento) {
		this.detracciondocumento = detracciondocumento;
	}
	/**
	 * 
	 * 
	 * @campo DetraccionFecha
	*/
	public java.util.Date getDetraccionfecha() {
		return detraccionfecha;
	}

	/**
	 * 
	 * 
	 * @campo DetraccionFecha
	*/
	public void setDetraccionfecha(java.util.Date detraccionfecha) {
		this.detraccionfecha = detraccionfecha;
	}
	/**
	 * 
	 * 
	 * @campo ArchivoAdjunto
	*/
	public String getArchivoadjunto() {
		return archivoadjunto;
	}

	/**
	 * 
	 * 
	 * @campo ArchivoAdjunto
	*/
	public void setArchivoadjunto(String archivoadjunto) {
		this.archivoadjunto = archivoadjunto;
	}
	/**
	 * 
	 * 
	 * @campo DetraccionCodigo
	*/
	public String getDetraccioncodigo() {
		return detraccioncodigo;
	}

	/**
	 * 
	 * 
	 * @campo DetraccionCodigo
	*/
	public void setDetraccioncodigo(String detraccioncodigo) {
		this.detraccioncodigo = detraccioncodigo;
	}
	/**
	 * 
	 * 
	 * @campo DetraccionCodigoFlag
	*/
	public String getDetraccioncodigoflag() {
		return detraccioncodigoflag;
	}

	/**
	 * 
	 * 
	 * @campo DetraccionCodigoFlag
	*/
	public void setDetraccioncodigoflag(String detraccioncodigoflag) {
		this.detraccioncodigoflag = detraccioncodigoflag;
	}
	/**
	 * 
	 * 
	 * @campo DefaultCampoReferencia
	*/
	public String getDefaultcamporeferencia() {
		return defaultcamporeferencia;
	}

	/**
	 * 
	 * 
	 * @campo DefaultCampoReferencia
	*/
	public void setDefaultcamporeferencia(String defaultcamporeferencia) {
		this.defaultcamporeferencia = defaultcamporeferencia;
	}
	/**
	 * 
	 * 
	 * @campo SIAF_Expediente
	*/
	public String getSiafExpediente() {
		return siafExpediente;
	}

	/**
	 * 
	 * 
	 * @campo SIAF_Expediente
	*/
	public void setSiafExpediente(String siafExpediente) {
		this.siafExpediente = siafExpediente;
	}
	/**
	 * 
	 * 
	 * @campo DetraccionPercepcionFlag
	*/
	public String getDetraccionpercepcionflag() {
		return detraccionpercepcionflag;
	}

	/**
	 * 
	 * 
	 * @campo DetraccionPercepcionFlag
	*/
	public void setDetraccionpercepcionflag(String detraccionpercepcionflag) {
		this.detraccionpercepcionflag = detraccionpercepcionflag;
	}
	/**
	 * 
	 * 
	 * @campo FechaAutorizacionFiscal
	*/
	public java.util.Date getFechaautorizacionfiscal() {
		return fechaautorizacionfiscal;
	}

	/**
	 * 
	 * 
	 * @campo FechaAutorizacionFiscal
	*/
	public void setFechaautorizacionfiscal(java.util.Date fechaautorizacionfiscal) {
		this.fechaautorizacionfiscal = fechaautorizacionfiscal;
	}
	/**
	 * 
	 * 
	 * @campo CodigoExoneracion
	*/
	public String getCodigoexoneracion() {
		return codigoexoneracion;
	}

	/**
	 * 
	 * 
	 * @campo CodigoExoneracion
	*/
	public void setCodigoexoneracion(String codigoexoneracion) {
		this.codigoexoneracion = codigoexoneracion;
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
	 * @campo FechaValor
	*/
	public java.util.Date getFechavalor() {
		return fechavalor;
	}

	/**
	 * 
	 * 
	 * @campo FechaValor
	*/
	public void setFechavalor(java.util.Date fechavalor) {
		this.fechavalor = fechavalor;
	}
	/**
	 * 
	 * 
	 * @campo TerceraMonedaCodigo
	*/
	public String getTerceramonedacodigo() {
		return terceramonedacodigo;
	}

	/**
	 * 
	 * 
	 * @campo TerceraMonedaCodigo
	*/
	public void setTerceramonedacodigo(String terceramonedacodigo) {
		this.terceramonedacodigo = terceramonedacodigo;
	}
	/**
	 * 
	 * 
	 * @campo TerceraMonedaTC
	*/
	public java.math.BigDecimal getTerceramonedatc() {
		return terceramonedatc;
	}

	/**
	 * 
	 * 
	 * @campo TerceraMonedaTC
	*/
	public void setTerceramonedatc(java.math.BigDecimal terceramonedatc) {
		this.terceramonedatc = terceramonedatc;
	}
	/**
	 * 
	 * 
	 * @campo SEMTPRocesoFlag
	*/
	public String getSemtprocesoflag() {
		return semtprocesoflag;
	}

	/**
	 * 
	 * 
	 * @campo SEMTPRocesoFlag
	*/
	public void setSemtprocesoflag(String semtprocesoflag) {
		this.semtprocesoflag = semtprocesoflag;
	}
	/**
	 * 
	 * 
	 * @campo SEMTLote
	*/
	public String getSemtlote() {
		return semtlote;
	}

	/**
	 * 
	 * 
	 * @campo SEMTLote
	*/
	public void setSemtlote(String semtlote) {
		this.semtlote = semtlote;
	}
	/**
	 * 
	 * 
	 * @campo FechaAprobacionLV
	*/
	public java.util.Date getFechaaprobacionlv() {
		return fechaaprobacionlv;
	}

	/**
	 * 
	 * 
	 * @campo FechaAprobacionLV
	*/
	public void setFechaaprobacionlv(java.util.Date fechaaprobacionlv) {
		this.fechaaprobacionlv = fechaaprobacionlv;
	}
	/**
	 * 
	 * 
	 * @campo MontoICBP
	*/
	public java.math.BigDecimal getMontoicbp() {
		return montoicbp;
	}

	/**
	 * 
	 * 
	 * @campo MontoICBP
	*/
	public void setMontoicbp(java.math.BigDecimal montoicbp) {
		this.montoicbp = montoicbp;
	}

}
