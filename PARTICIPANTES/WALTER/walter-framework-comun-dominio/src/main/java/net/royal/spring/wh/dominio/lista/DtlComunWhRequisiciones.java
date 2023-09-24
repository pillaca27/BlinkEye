package net.royal.spring.wh.dominio.lista;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;


/**
 * 
 * 
 * @tabla dbo.WH_Requisiciones
*/
public class DtlComunWhRequisiciones extends DominioTransaccion implements java.io.Serializable{


	private String companiasocio;
	private String requisicionnumero;
	private String clasificacion;
	private String comprasalmacenflag;
	private String almacencodigo;
	private String monedacodigo;
	private String monedadocumento;
	private java.util.Date fecharequerida;
	private java.util.Date fechapreparacion;
	private java.util.Date fecharevision;
	private java.util.Date fechaaprobacion;
	private Integer preparadapor;
	private Integer revisadapor;
	private Integer aprobadapor;
	private String responsable;
	private String departamento;
	private java.math.BigDecimal preciototal;
	private String prioridadcodigo;
	private String defaultprime;
	private String defaultafe;
	private String cuantiamonetariapendienteflag;
	private String ordentrabajo;
	private String unidadnegocio;
	private String unidadreplicacion;
	private String localforeignflag;
	private String direcciondestino;
	private String comentarios;
	private String razonrechazo;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private Integer ultimousuarionumero;
	private String transaccionoperacion;
	private String licitacionasignadaflag;
	private String revisionflujocodigo;
	private String revisiontecnicapendienteflag;
	private String defaultcamporeferencia;
	private java.util.Date fecharevisiontecnica;
	private Integer revisiontecnicapor;
	private String numeroorden;
	private Integer cliente;
	private String clientenumeropedido;
	private Integer proveedor;
	private String proveedordocumentoreferencia;
	private String viatransporte;
	private String origengeneracionflag;
	private String clasificacioncompras;
	private String observacionesentrega;
	private String contratoadenda;
	private String ano;
	private Integer paac;
	private String modalidad;
	private String sistemacontratacion;
	private String modalidadcontractual;
	private String referencia01data;
	private java.util.Date referencia01fecha;
	private String referencia02data;
	private String referencia03data;
	private String cotizadoflag;
	private String certificacionnumero;
	private String certificacionestado;
	private Integer nivelservicio;
	private String valorizacionflag;
	private String almacentraslado;
	private String unidadnegociocompra;
	private String sucursal;
	private Integer revisionflujonivel;
	private String enviadoflag;
	private String tipolicitacion;
	private Integer comprador;
	private String gestorasignadoflag;
	private java.math.BigDecimal preciolocal;
	private java.math.BigDecimal preciodolar;
	
	
	
	public String getMonedadocumento() {
		return monedadocumento;
	}

	public void setMonedadocumento(String monedadocumento) {
		this.monedadocumento = monedadocumento;
	}

	public java.math.BigDecimal getPreciolocal() {
		return preciolocal;
	}

	public void setPreciolocal(java.math.BigDecimal preciolocal) {
		this.preciolocal = preciolocal;
	}

	public java.math.BigDecimal getPreciodolar() {
		return preciodolar;
	}

	public void setPreciodolar(java.math.BigDecimal preciodolar) {
		this.preciodolar = preciodolar;
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
	 * @campo RequisicionNumero
	*/
	public String getRequisicionnumero() {
		return requisicionnumero;
	}

	/**
	 * 
	 * 
	 * @campo RequisicionNumero
	*/
	public void setRequisicionnumero(String requisicionnumero) {
		this.requisicionnumero = requisicionnumero;
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
	 * @campo ComprasAlmacenFlag
	*/
	public String getComprasalmacenflag() {
		return comprasalmacenflag;
	}

	/**
	 * 
	 * 
	 * @campo ComprasAlmacenFlag
	*/
	public void setComprasalmacenflag(String comprasalmacenflag) {
		this.comprasalmacenflag = comprasalmacenflag;
	}
	/**
	 * 
	 * 
	 * @campo AlmacenCodigo
	*/
	public String getAlmacencodigo() {
		return almacencodigo;
	}

	/**
	 * 
	 * 
	 * @campo AlmacenCodigo
	*/
	public void setAlmacencodigo(String almacencodigo) {
		this.almacencodigo = almacencodigo;
	}
	/**
	 * 
	 * 
	 * @campo MonedaCodigo
	*/
	public String getMonedacodigo() {
		return monedacodigo;
	}

	/**
	 * 
	 * 
	 * @campo MonedaCodigo
	*/
	public void setMonedacodigo(String monedacodigo) {
		this.monedacodigo = monedacodigo;
	}
	/**
	 * 
	 * 
	 * @campo FechaRequerida
	*/
	public java.util.Date getFecharequerida() {
		return fecharequerida;
	}

	/**
	 * 
	 * 
	 * @campo FechaRequerida
	*/
	public void setFecharequerida(java.util.Date fecharequerida) {
		this.fecharequerida = fecharequerida;
	}
	/**
	 * 
	 * 
	 * @campo FechaPreparacion
	*/
	public java.util.Date getFechapreparacion() {
		return fechapreparacion;
	}

	/**
	 * 
	 * 
	 * @campo FechaPreparacion
	*/
	public void setFechapreparacion(java.util.Date fechapreparacion) {
		this.fechapreparacion = fechapreparacion;
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
	 * @campo PreparadaPor
	*/
	public Integer getPreparadapor() {
		return preparadapor;
	}

	/**
	 * 
	 * 
	 * @campo PreparadaPor
	*/
	public void setPreparadapor(Integer preparadapor) {
		this.preparadapor = preparadapor;
	}
	/**
	 * 
	 * 
	 * @campo RevisadaPor
	*/
	public Integer getRevisadapor() {
		return revisadapor;
	}

	/**
	 * 
	 * 
	 * @campo RevisadaPor
	*/
	public void setRevisadapor(Integer revisadapor) {
		this.revisadapor = revisadapor;
	}
	/**
	 * 
	 * 
	 * @campo AprobadaPor
	*/
	public Integer getAprobadapor() {
		return aprobadapor;
	}

	/**
	 * 
	 * 
	 * @campo AprobadaPor
	*/
	public void setAprobadapor(Integer aprobadapor) {
		this.aprobadapor = aprobadapor;
	}
	/**
	 * 
	 * 
	 * @campo Responsable
	*/
	public String getResponsable() {
		return responsable;
	}

	/**
	 * 
	 * 
	 * @campo Responsable
	*/
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	/**
	 * 
	 * 
	 * @campo Departamento
	*/
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * 
	 * 
	 * @campo Departamento
	*/
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	/**
	 * 
	 * 
	 * @campo PrecioTotal
	*/
	public java.math.BigDecimal getPreciototal() {
		return preciototal;
	}

	/**
	 * 
	 * 
	 * @campo PrecioTotal
	*/
	public void setPreciototal(java.math.BigDecimal preciototal) {
		this.preciototal = preciototal;
	}
	/**
	 * 
	 * 
	 * @campo PrioridadCodigo
	*/
	public String getPrioridadcodigo() {
		return prioridadcodigo;
	}

	/**
	 * 
	 * 
	 * @campo PrioridadCodigo
	*/
	public void setPrioridadcodigo(String prioridadcodigo) {
		this.prioridadcodigo = prioridadcodigo;
	}
	/**
	 * 
	 * 
	 * @campo DefaultPrime
	*/
	public String getDefaultprime() {
		return defaultprime;
	}

	/**
	 * 
	 * 
	 * @campo DefaultPrime
	*/
	public void setDefaultprime(String defaultprime) {
		this.defaultprime = defaultprime;
	}
	/**
	 * 
	 * 
	 * @campo DefaultAfe
	*/
	public String getDefaultafe() {
		return defaultafe;
	}

	/**
	 * 
	 * 
	 * @campo DefaultAfe
	*/
	public void setDefaultafe(String defaultafe) {
		this.defaultafe = defaultafe;
	}
	/**
	 * 
	 * 
	 * @campo CuantiaMonetariaPendienteFlag
	*/
	public String getCuantiamonetariapendienteflag() {
		return cuantiamonetariapendienteflag;
	}

	/**
	 * 
	 * 
	 * @campo CuantiaMonetariaPendienteFlag
	*/
	public void setCuantiamonetariapendienteflag(String cuantiamonetariapendienteflag) {
		this.cuantiamonetariapendienteflag = cuantiamonetariapendienteflag;
	}
	/**
	 * 
	 * 
	 * @campo OrdenTrabajo
	*/
	public String getOrdentrabajo() {
		return ordentrabajo;
	}

	/**
	 * 
	 * 
	 * @campo OrdenTrabajo
	*/
	public void setOrdentrabajo(String ordentrabajo) {
		this.ordentrabajo = ordentrabajo;
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
	 * @campo LocalForeignFlag
	*/
	public String getLocalforeignflag() {
		return localforeignflag;
	}

	/**
	 * 
	 * 
	 * @campo LocalForeignFlag
	*/
	public void setLocalforeignflag(String localforeignflag) {
		this.localforeignflag = localforeignflag;
	}
	/**
	 * 
	 * 
	 * @campo DireccionDestino
	*/
	public String getDirecciondestino() {
		return direcciondestino;
	}

	/**
	 * 
	 * 
	 * @campo DireccionDestino
	*/
	public void setDirecciondestino(String direcciondestino) {
		this.direcciondestino = direcciondestino;
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
	 * @campo UltimoUsuarioNumero
	*/
	public Integer getUltimousuarionumero() {
		return ultimousuarionumero;
	}

	/**
	 * 
	 * 
	 * @campo UltimoUsuarioNumero
	*/
	public void setUltimousuarionumero(Integer ultimousuarionumero) {
		this.ultimousuarionumero = ultimousuarionumero;
	}
	/**
	 * 
	 * 
	 * @campo TransaccionOperacion
	*/
	public String getTransaccionoperacion() {
		return transaccionoperacion;
	}

	/**
	 * 
	 * 
	 * @campo TransaccionOperacion
	*/
	public void setTransaccionoperacion(String transaccionoperacion) {
		this.transaccionoperacion = transaccionoperacion;
	}
	/**
	 * 
	 * 
	 * @campo LicitacionAsignadaFlag
	*/
	public String getLicitacionasignadaflag() {
		return licitacionasignadaflag;
	}

	/**
	 * 
	 * 
	 * @campo LicitacionAsignadaFlag
	*/
	public void setLicitacionasignadaflag(String licitacionasignadaflag) {
		this.licitacionasignadaflag = licitacionasignadaflag;
	}
	/**
	 * 
	 * 
	 * @campo RevisionFlujoCodigo
	*/
	public String getRevisionflujocodigo() {
		return revisionflujocodigo;
	}

	/**
	 * 
	 * 
	 * @campo RevisionFlujoCodigo
	*/
	public void setRevisionflujocodigo(String revisionflujocodigo) {
		this.revisionflujocodigo = revisionflujocodigo;
	}
	/**
	 * 
	 * 
	 * @campo RevisionTecnicaPendienteFlag
	*/
	public String getRevisiontecnicapendienteflag() {
		return revisiontecnicapendienteflag;
	}

	/**
	 * 
	 * 
	 * @campo RevisionTecnicaPendienteFlag
	*/
	public void setRevisiontecnicapendienteflag(String revisiontecnicapendienteflag) {
		this.revisiontecnicapendienteflag = revisiontecnicapendienteflag;
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
	 * @campo FechaRevisionTecnica
	*/
	public java.util.Date getFecharevisiontecnica() {
		return fecharevisiontecnica;
	}

	/**
	 * 
	 * 
	 * @campo FechaRevisionTecnica
	*/
	public void setFecharevisiontecnica(java.util.Date fecharevisiontecnica) {
		this.fecharevisiontecnica = fecharevisiontecnica;
	}
	/**
	 * 
	 * 
	 * @campo RevisionTecnicaPor
	*/
	public Integer getRevisiontecnicapor() {
		return revisiontecnicapor;
	}

	/**
	 * 
	 * 
	 * @campo RevisionTecnicaPor
	*/
	public void setRevisiontecnicapor(Integer revisiontecnicapor) {
		this.revisiontecnicapor = revisiontecnicapor;
	}
	/**
	 * 
	 * 
	 * @campo NumeroOrden
	*/
	public String getNumeroorden() {
		return numeroorden;
	}

	/**
	 * 
	 * 
	 * @campo NumeroOrden
	*/
	public void setNumeroorden(String numeroorden) {
		this.numeroorden = numeroorden;
	}
	/**
	 * 
	 * 
	 * @campo Cliente
	*/
	public Integer getCliente() {
		return cliente;
	}

	/**
	 * 
	 * 
	 * @campo Cliente
	*/
	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	/**
	 * 
	 * 
	 * @campo ClienteNumeroPedido
	*/
	public String getClientenumeropedido() {
		return clientenumeropedido;
	}

	/**
	 * 
	 * 
	 * @campo ClienteNumeroPedido
	*/
	public void setClientenumeropedido(String clientenumeropedido) {
		this.clientenumeropedido = clientenumeropedido;
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
	 * @campo ProveedorDocumentoReferencia
	*/
	public String getProveedordocumentoreferencia() {
		return proveedordocumentoreferencia;
	}

	/**
	 * 
	 * 
	 * @campo ProveedorDocumentoReferencia
	*/
	public void setProveedordocumentoreferencia(String proveedordocumentoreferencia) {
		this.proveedordocumentoreferencia = proveedordocumentoreferencia;
	}
	/**
	 * 
	 * 
	 * @campo ViaTransporte
	*/
	public String getViatransporte() {
		return viatransporte;
	}

	/**
	 * 
	 * 
	 * @campo ViaTransporte
	*/
	public void setViatransporte(String viatransporte) {
		this.viatransporte = viatransporte;
	}
	/**
	 * 
	 * 
	 * @campo OrigenGeneracionFlag
	*/
	public String getOrigengeneracionflag() {
		return origengeneracionflag;
	}

	/**
	 * 
	 * 
	 * @campo OrigenGeneracionFlag
	*/
	public void setOrigengeneracionflag(String origengeneracionflag) {
		this.origengeneracionflag = origengeneracionflag;
	}
	/**
	 * 
	 * 
	 * @campo Clasificacioncompras
	*/
	public String getClasificacioncompras() {
		return clasificacioncompras;
	}

	/**
	 * 
	 * 
	 * @campo Clasificacioncompras
	*/
	public void setClasificacioncompras(String clasificacioncompras) {
		this.clasificacioncompras = clasificacioncompras;
	}
	/**
	 * 
	 * 
	 * @campo ObservacionesEntrega
	*/
	public String getObservacionesentrega() {
		return observacionesentrega;
	}

	/**
	 * 
	 * 
	 * @campo ObservacionesEntrega
	*/
	public void setObservacionesentrega(String observacionesentrega) {
		this.observacionesentrega = observacionesentrega;
	}
	/**
	 * 
	 * 
	 * @campo ContratoAdenda
	*/
	public String getContratoadenda() {
		return contratoadenda;
	}

	/**
	 * 
	 * 
	 * @campo ContratoAdenda
	*/
	public void setContratoadenda(String contratoadenda) {
		this.contratoadenda = contratoadenda;
	}
	/**
	 * 
	 * 
	 * @campo Ano
	*/
	public String getAno() {
		return ano;
	}

	/**
	 * 
	 * 
	 * @campo Ano
	*/
	public void setAno(String ano) {
		this.ano = ano;
	}
	/**
	 * 
	 * 
	 * @campo PAAC
	*/
	public Integer getPaac() {
		return paac;
	}

	/**
	 * 
	 * 
	 * @campo PAAC
	*/
	public void setPaac(Integer paac) {
		this.paac = paac;
	}
	/**
	 * 
	 * 
	 * @campo Modalidad
	*/
	public String getModalidad() {
		return modalidad;
	}

	/**
	 * 
	 * 
	 * @campo Modalidad
	*/
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	/**
	 * 
	 * 
	 * @campo SistemaContratacion
	*/
	public String getSistemacontratacion() {
		return sistemacontratacion;
	}

	/**
	 * 
	 * 
	 * @campo SistemaContratacion
	*/
	public void setSistemacontratacion(String sistemacontratacion) {
		this.sistemacontratacion = sistemacontratacion;
	}
	/**
	 * 
	 * 
	 * @campo ModalidadContrActual
	*/
	public String getModalidadcontractual() {
		return modalidadcontractual;
	}

	/**
	 * 
	 * 
	 * @campo ModalidadContrActual
	*/
	public void setModalidadcontractual(String modalidadcontractual) {
		this.modalidadcontractual = modalidadcontractual;
	}
	/**
	 * 
	 * 
	 * @campo Referencia01Data
	*/
	public String getReferencia01data() {
		return referencia01data;
	}

	/**
	 * 
	 * 
	 * @campo Referencia01Data
	*/
	public void setReferencia01data(String referencia01data) {
		this.referencia01data = referencia01data;
	}
	/**
	 * 
	 * 
	 * @campo Referencia01Fecha
	*/
	public java.util.Date getReferencia01fecha() {
		return referencia01fecha;
	}

	/**
	 * 
	 * 
	 * @campo Referencia01Fecha
	*/
	public void setReferencia01fecha(java.util.Date referencia01fecha) {
		this.referencia01fecha = referencia01fecha;
	}
	/**
	 * 
	 * 
	 * @campo Referencia02Data
	*/
	public String getReferencia02data() {
		return referencia02data;
	}

	/**
	 * 
	 * 
	 * @campo Referencia02Data
	*/
	public void setReferencia02data(String referencia02data) {
		this.referencia02data = referencia02data;
	}
	/**
	 * 
	 * 
	 * @campo Referencia03Data
	*/
	public String getReferencia03data() {
		return referencia03data;
	}

	/**
	 * 
	 * 
	 * @campo Referencia03Data
	*/
	public void setReferencia03data(String referencia03data) {
		this.referencia03data = referencia03data;
	}
	/**
	 * 
	 * 
	 * @campo CotizadoFlag
	*/
	public String getCotizadoflag() {
		return cotizadoflag;
	}

	/**
	 * 
	 * 
	 * @campo CotizadoFlag
	*/
	public void setCotizadoflag(String cotizadoflag) {
		this.cotizadoflag = cotizadoflag;
	}
	/**
	 * 
	 * 
	 * @campo CertificacionNumero
	*/
	public String getCertificacionnumero() {
		return certificacionnumero;
	}

	/**
	 * 
	 * 
	 * @campo CertificacionNumero
	*/
	public void setCertificacionnumero(String certificacionnumero) {
		this.certificacionnumero = certificacionnumero;
	}
	/**
	 * 
	 * 
	 * @campo CertificacionEstado
	*/
	public String getCertificacionestado() {
		return certificacionestado;
	}

	/**
	 * 
	 * 
	 * @campo CertificacionEstado
	*/
	public void setCertificacionestado(String certificacionestado) {
		this.certificacionestado = certificacionestado;
	}
	/**
	 * 
	 * 
	 * @campo NivelServicio
	*/
	public Integer getNivelservicio() {
		return nivelservicio;
	}

	/**
	 * 
	 * 
	 * @campo NivelServicio
	*/
	public void setNivelservicio(Integer nivelservicio) {
		this.nivelservicio = nivelservicio;
	}
	/**
	 * 
	 * 
	 * @campo ValorizacionFlag
	*/
	public String getValorizacionflag() {
		return valorizacionflag;
	}

	/**
	 * 
	 * 
	 * @campo ValorizacionFlag
	*/
	public void setValorizacionflag(String valorizacionflag) {
		this.valorizacionflag = valorizacionflag;
	}
	/**
	 * 
	 * 
	 * @campo AlmacenTraslado
	*/
	public String getAlmacentraslado() {
		return almacentraslado;
	}

	/**
	 * 
	 * 
	 * @campo AlmacenTraslado
	*/
	public void setAlmacentraslado(String almacentraslado) {
		this.almacentraslado = almacentraslado;
	}
	/**
	 * 
	 * 
	 * @campo UnidadNegocioCompra
	*/
	public String getUnidadnegociocompra() {
		return unidadnegociocompra;
	}

	/**
	 * 
	 * 
	 * @campo UnidadNegocioCompra
	*/
	public void setUnidadnegociocompra(String unidadnegociocompra) {
		this.unidadnegociocompra = unidadnegociocompra;
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
	 * @campo RevisionFlujoNivel
	*/
	public Integer getRevisionflujonivel() {
		return revisionflujonivel;
	}

	/**
	 * 
	 * 
	 * @campo RevisionFlujoNivel
	*/
	public void setRevisionflujonivel(Integer revisionflujonivel) {
		this.revisionflujonivel = revisionflujonivel;
	}
	/**
	 * 
	 * 
	 * @campo EnviadoFlag
	*/
	public String getEnviadoflag() {
		return enviadoflag;
	}

	/**
	 * 
	 * 
	 * @campo EnviadoFlag
	*/
	public void setEnviadoflag(String enviadoflag) {
		this.enviadoflag = enviadoflag;
	}
	/**
	 * 
	 * 
	 * @campo TipoLicitacion
	*/
	public String getTipolicitacion() {
		return tipolicitacion;
	}

	/**
	 * 
	 * 
	 * @campo TipoLicitacion
	*/
	public void setTipolicitacion(String tipolicitacion) {
		this.tipolicitacion = tipolicitacion;
	}
	/**
	 * 
	 * 
	 * @campo Comprador
	*/
	public Integer getComprador() {
		return comprador;
	}

	/**
	 * 
	 * 
	 * @campo Comprador
	*/
	public void setComprador(Integer comprador) {
		this.comprador = comprador;
	}
	/**
	 * 
	 * 
	 * @campo GestorAsignadoFlag
	*/
	public String getGestorasignadoflag() {
		return gestorasignadoflag;
	}

	/**
	 * 
	 * 
	 * @campo GestorAsignadoFlag
	*/
	public void setGestorasignadoflag(String gestorasignadoflag) {
		this.gestorasignadoflag = gestorasignadoflag;
	}

}
