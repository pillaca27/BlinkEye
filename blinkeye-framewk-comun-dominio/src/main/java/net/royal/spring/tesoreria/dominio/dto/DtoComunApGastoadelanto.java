package net.royal.spring.tesoreria.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

/**
 * 
 * 
 * @tabla dbo.AP_GastoAdelanto
 */
public class DtoComunApGastoadelanto extends DominioTransaccion implements java.io.Serializable {

	private String unidadreplicacion;
	private String tipoadelanto;
	private Integer numeroadelanto;
	private String companiasocio;
	private String responsable;
	private String clasificacion;
	private String compromisotipo;
	private String compromisonumero;
	private java.util.Date fechadocumento;
	private java.util.Date fechaesperadapago;
	private java.util.Date fechapartida;
	private java.util.Date fechallegada;
	private String lugardestino;
	private String tipopago;
	private Integer persona;
	private Integer preparadopor;
	private java.util.Date fechapreparacion;
	private Integer aprobadopor;
	private java.util.Date fechaaprobacion;
	private String monedadocumento;
	private java.math.BigDecimal montototal;
	private java.math.BigDecimal saldoadelanto;
	private String obligaciontipodocumento;
	private String obligacionnumerodocumento;
	private String descripcion;
	private String observaciones;
	private String numerodocumentointerno;
	private String centrocostos;
	private String partidapresupuestal;
	private String proyecto;
	private String criterio1;
	private String criterio2;
	private String criterio3;
	private String criterio4;
	private String criterio5;
	private String counterflag;
	private String flujodecaja;
	private Integer personapagara;
	private String conceptogasto;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private byte[] timestamp;
	private String unidadnegocio;
	private String flagproceso;
	private Integer personaresponsable;
	private String comentario;
	private String ubigeo;
	private String camporeferencia;
	private String centrocostodestino;
	private String detraccioncodigo;
	private String detraccioncodigoflag;
	private Integer empleadoautorizado;
	private java.math.BigDecimal montoreferencial;
	private String numeroorden;
	private String siafExpediente;
	private Integer revisadopor;
	private java.util.Date fecharevision;
	private String enviadoflag;

	private String personapagaranombre;
	private String personanombre;
	private String preparadopornombre;
	private String aprobadopornombre;
	private String revisadorpornombre;
	private String centrocostos_name;
	private String proyecto_name;
	private String flujodecaja_name;
	private String camporeferencia_name;
	private String numeroadelanto_name;
	private Integer index;
	private Boolean form1;
	private Boolean form2;
	private String busqueda;
	private String origen;
	private String periodo;
	private String aplicacion;
	private String tipodocumento;
	private String numerdocumento;
	private String voucher;
	private String indicadorvoucher;

	public String getIndicadorvoucher() {
		return indicadorvoucher;
	}

	public void setIndicadorvoucher(String indicadorvoucher) {
		this.indicadorvoucher = indicadorvoucher;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	public String getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public String getNumerdocumento() {
		return numerdocumento;
	}

	public void setNumerdocumento(String numerdocumento) {
		this.numerdocumento = numerdocumento;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public Boolean getForm2() {
		if (form2 == null)
			return false;
		return form2;
	}

	public void setForm2(Boolean form2) {
		this.form2 = form2;
	}

	public Boolean getForm1() {
		if (form1 == null)
			return false;
		return form1;
	}

	public void setForm1(Boolean form1) {
		this.form1 = form1;
	}

	public Integer getIndex() {
		if (index == null)
			return 0;
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getNumeroadelanto_name() {
		return numeroadelanto_name;
	}

	public void setNumeroadelanto_name(String numeroadelanto_name) {
		this.numeroadelanto_name = numeroadelanto_name;
	}

	public String getCamporeferencia_name() {
		return camporeferencia_name;
	}

	public void setCamporeferencia_name(String camporeferencia_name) {
		this.camporeferencia_name = camporeferencia_name;
	}

	public String getPersonapagaranombre() {
		return personapagaranombre;
	}

	public void setPersonapagaranombre(String personapagaranombre) {
		this.personapagaranombre = personapagaranombre;
	}

	public String getPersonanombre() {
		return personanombre;
	}

	public void setPersonanombre(String personanombre) {
		this.personanombre = personanombre;
	}

	public String getPreparadopornombre() {
		return preparadopornombre;
	}

	public void setPreparadopornombre(String preparadopornombre) {
		this.preparadopornombre = preparadopornombre;
	}

	public String getAprobadopornombre() {
		return aprobadopornombre;
	}

	public void setAprobadopornombre(String aprobadopornombre) {
		this.aprobadopornombre = aprobadopornombre;
	}

	public String getRevisadorpornombre() {
		return revisadorpornombre;
	}

	public void setRevisadorpornombre(String revisadorpornombre) {
		this.revisadorpornombre = revisadorpornombre;
	}

	public String getCentrocostos_name() {
		return centrocostos_name;
	}

	public void setCentrocostos_name(String centrocostos_name) {
		this.centrocostos_name = centrocostos_name;
	}

	public String getProyecto_name() {
		return proyecto_name;
	}

	public void setProyecto_name(String proyecto_name) {
		this.proyecto_name = proyecto_name;
	}

	public String getFlujodecaja_name() {
		return flujodecaja_name;
	}

	public void setFlujodecaja_name(String flujodecaja_name) {
		this.flujodecaja_name = flujodecaja_name;
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
	 * @campo TipoAdelanto
	 */
	public String getTipoadelanto() {
		return tipoadelanto;
	}

	/**
	 * 
	 * 
	 * @campo TipoAdelanto
	 */
	public void setTipoadelanto(String tipoadelanto) {
		this.tipoadelanto = tipoadelanto;
	}

	/**
	 * 
	 * 
	 * @campo NumeroAdelanto
	 */
	public Integer getNumeroadelanto() {
		return numeroadelanto;
	}

	/**
	 * 
	 * 
	 * @campo NumeroAdelanto
	 */
	public void setNumeroadelanto(Integer numeroadelanto) {
		this.numeroadelanto = numeroadelanto;
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
	 * @campo CompromisoTipo
	 */
	public String getCompromisotipo() {
		return compromisotipo;
	}

	/**
	 * 
	 * 
	 * @campo CompromisoTipo
	 */
	public void setCompromisotipo(String compromisotipo) {
		this.compromisotipo = compromisotipo;
	}

	/**
	 * 
	 * 
	 * @campo CompromisoNumero
	 */
	public String getCompromisonumero() {
		return compromisonumero;
	}

	/**
	 * 
	 * 
	 * @campo CompromisoNumero
	 */
	public void setCompromisonumero(String compromisonumero) {
		this.compromisonumero = compromisonumero;
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
	 * @campo FechaEsperadaPago
	 */
	public java.util.Date getFechaesperadapago() {
		return fechaesperadapago;
	}

	/**
	 * 
	 * 
	 * @campo FechaEsperadaPago
	 */
	public void setFechaesperadapago(java.util.Date fechaesperadapago) {
		this.fechaesperadapago = fechaesperadapago;
	}

	/**
	 * 
	 * 
	 * @campo FechaPartida
	 */
	public java.util.Date getFechapartida() {
		return fechapartida;
	}

	/**
	 * 
	 * 
	 * @campo FechaPartida
	 */
	public void setFechapartida(java.util.Date fechapartida) {
		this.fechapartida = fechapartida;
	}

	/**
	 * 
	 * 
	 * @campo FechaLlegada
	 */
	public java.util.Date getFechallegada() {
		return fechallegada;
	}

	/**
	 * 
	 * 
	 * @campo FechaLlegada
	 */
	public void setFechallegada(java.util.Date fechallegada) {
		this.fechallegada = fechallegada;
	}

	/**
	 * 
	 * 
	 * @campo LugarDestino
	 */
	public String getLugardestino() {
		return lugardestino;
	}

	/**
	 * 
	 * 
	 * @campo LugarDestino
	 */
	public void setLugardestino(String lugardestino) {
		this.lugardestino = lugardestino;
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
	 * @campo Persona
	 */
	public Integer getPersona() {
		return persona;
	}

	/**
	 * 
	 * 
	 * @campo Persona
	 */
	public void setPersona(Integer persona) {
		this.persona = persona;
	}

	/**
	 * 
	 * 
	 * @campo PreparadoPor
	 */
	public Integer getPreparadopor() {
		return preparadopor;
	}

	/**
	 * 
	 * 
	 * @campo PreparadoPor
	 */
	public void setPreparadopor(Integer preparadopor) {
		this.preparadopor = preparadopor;
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
	 * @campo SaldoAdelanto
	 */
	public java.math.BigDecimal getSaldoadelanto() {
		return saldoadelanto;
	}

	/**
	 * 
	 * 
	 * @campo SaldoAdelanto
	 */
	public void setSaldoadelanto(java.math.BigDecimal saldoadelanto) {
		this.saldoadelanto = saldoadelanto;
	}

	/**
	 * 
	 * 
	 * @campo ObligacionTipoDocumento
	 */
	public String getObligaciontipodocumento() {
		return obligaciontipodocumento;
	}

	/**
	 * 
	 * 
	 * @campo ObligacionTipoDocumento
	 */
	public void setObligaciontipodocumento(String obligaciontipodocumento) {
		this.obligaciontipodocumento = obligaciontipodocumento;
	}

	/**
	 * 
	 * 
	 * @campo ObligacionNumeroDocumento
	 */
	public String getObligacionnumerodocumento() {
		return obligacionnumerodocumento;
	}

	/**
	 * 
	 * 
	 * @campo ObligacionNumeroDocumento
	 */
	public void setObligacionnumerodocumento(String obligacionnumerodocumento) {
		this.obligacionnumerodocumento = obligacionnumerodocumento;
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
	 * @campo Observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * 
	 * 
	 * @campo Observaciones
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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
	 * @campo CentroCostos
	 */
	public String getCentrocostos() {
		return centrocostos;
	}

	/**
	 * 
	 * 
	 * @campo CentroCostos
	 */
	public void setCentrocostos(String centrocostos) {
		this.centrocostos = centrocostos;
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
	 * @campo Criterio1
	 */
	public String getCriterio1() {
		return criterio1;
	}

	/**
	 * 
	 * 
	 * @campo Criterio1
	 */
	public void setCriterio1(String criterio1) {
		this.criterio1 = criterio1;
	}

	/**
	 * 
	 * 
	 * @campo Criterio2
	 */
	public String getCriterio2() {
		return criterio2;
	}

	/**
	 * 
	 * 
	 * @campo Criterio2
	 */
	public void setCriterio2(String criterio2) {
		this.criterio2 = criterio2;
	}

	/**
	 * 
	 * 
	 * @campo Criterio3
	 */
	public String getCriterio3() {
		return criterio3;
	}

	/**
	 * 
	 * 
	 * @campo Criterio3
	 */
	public void setCriterio3(String criterio3) {
		this.criterio3 = criterio3;
	}

	/**
	 * 
	 * 
	 * @campo Criterio4
	 */
	public String getCriterio4() {
		return criterio4;
	}

	/**
	 * 
	 * 
	 * @campo Criterio4
	 */
	public void setCriterio4(String criterio4) {
		this.criterio4 = criterio4;
	}

	/**
	 * 
	 * 
	 * @campo Criterio5
	 */
	public String getCriterio5() {
		return criterio5;
	}

	/**
	 * 
	 * 
	 * @campo Criterio5
	 */
	public void setCriterio5(String criterio5) {
		this.criterio5 = criterio5;
	}

	/**
	 * 
	 * 
	 * @campo CounterFlag
	 */
	public String getCounterflag() {
		return counterflag;
	}

	/**
	 * 
	 * 
	 * @campo CounterFlag
	 */
	public void setCounterflag(String counterflag) {
		this.counterflag = counterflag;
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
	 * @campo PersonaPagara
	 */
	public Integer getPersonapagara() {
		return personapagara;
	}

	/**
	 * 
	 * 
	 * @campo PersonaPagara
	 */
	public void setPersonapagara(Integer personapagara) {
		this.personapagara = personapagara;
	}

	/**
	 * 
	 * 
	 * @campo ConceptoGasto
	 */
	public String getConceptogasto() {
		return conceptogasto;
	}

	/**
	 * 
	 * 
	 * @campo ConceptoGasto
	 */
	public void setConceptogasto(String conceptogasto) {
		this.conceptogasto = conceptogasto;
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
	 * @campo timestamp
	 */
	public byte[] getTimestamp() {
		return timestamp;
	}

	/**
	 * 
	 * 
	 * @campo timestamp
	 */
	public void setTimestamp(byte[] timestamp) {
		this.timestamp = timestamp;
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
	 * @campo FlagProceso
	 */
	public String getFlagproceso() {
		return flagproceso;
	}

	/**
	 * 
	 * 
	 * @campo FlagProceso
	 */
	public void setFlagproceso(String flagproceso) {
		this.flagproceso = flagproceso;
	}

	/**
	 * 
	 * 
	 * @campo PersonaResponsable
	 */
	public Integer getPersonaresponsable() {
		return personaresponsable;
	}

	/**
	 * 
	 * 
	 * @campo PersonaResponsable
	 */
	public void setPersonaresponsable(Integer personaresponsable) {
		this.personaresponsable = personaresponsable;
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
	 * @campo Ubigeo
	 */
	public String getUbigeo() {
		return ubigeo;
	}

	/**
	 * 
	 * 
	 * @campo Ubigeo
	 */
	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
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
	 * @campo EmpleadoAutorizado
	 */
	public Integer getEmpleadoautorizado() {
		return empleadoautorizado;
	}

	/**
	 * 
	 * 
	 * @campo EmpleadoAutorizado
	 */
	public void setEmpleadoautorizado(Integer empleadoautorizado) {
		this.empleadoautorizado = empleadoautorizado;
	}

	/**
	 * 
	 * 
	 * @campo MontoReferencial
	 */
	public java.math.BigDecimal getMontoreferencial() {
		return montoreferencial;
	}

	/**
	 * 
	 * 
	 * @campo MontoReferencial
	 */
	public void setMontoreferencial(java.math.BigDecimal montoreferencial) {
		this.montoreferencial = montoreferencial;
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
	 * @campo enviadoflag
	 */
	public String getEnviadoflag() {
		return enviadoflag;
	}

	/**
	 * 
	 * 
	 * @campo enviadoflag
	 */
	public void setEnviadoflag(String enviadoflag) {
		this.enviadoflag = enviadoflag;
	}

}
