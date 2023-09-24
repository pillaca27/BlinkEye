package net.royal.spring.wh.dominio.dto;

import net.royal.spring.wh.dominio.BeanWhContrato;
import net.royal.spring.wh.dominio.lista.DtlComunWhRequisiciones;

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

import net.royal.spring.core.dominio.lista.DtlComunObligaciones;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;
import net.royal.spring.sy.dominio.dto.DtoComunSyDocumentoanexos;

/**
 * 
 * 
 * @tabla dbo.WH_Contrato
*/
public class DtoComunWhContrato extends DominioTransaccion implements java.io.Serializable{


	private String companiasocio;
	private String numerocontrato;
	private String documentointerno;
	private String unidadnegocio;
	private String numerocontratorelacionado;
	private Integer proveedor;
	private String descripcion;
	private String formadepago;
	private java.util.Date fechadocumento;
	private java.util.Date fechavalidezdesde;
	private java.util.Date fechavalidezhasta;
	private java.util.Date fechacierre;
	private java.util.Date fechapreparacion;
	private java.util.Date fechaaprobacion;
	private java.util.Date fechatopegeneracion;
	private java.util.Date fechacartafianza01;
	private java.util.Date fechacartafianza02;
	private java.util.Date fecharenovacion;
	private String flujodecaja;
	private String monedadocumento;
	private java.math.BigDecimal montoimponible;
	private java.math.BigDecimal montoimpuestos;
	private java.math.BigDecimal montototal;
	private java.math.BigDecimal montopagado;
	private java.math.BigDecimal montodisponible;
	private Integer preparadapor;
	private Integer aprobadapor;
	private String comentarios;
	private String razonrechazo;
	private String clasificacion;
	private String tipoprogramacion;
	private String condicionesperiodicidadflag;
	private Integer condicionesperiodicidadnumero;
	private String almacencodigo;
	private String contratoresponsable;
	private String contratotipo;
	private String proveedorpaginaweb;
	private String gastosadicionalesflag;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String proveedorcontacto;
	private String contratoventaflag;
	private String contratomontozeroflag;
	private String responsablecodigo;
	private Integer revisadapor;
	private java.util.Date fecharevision;
	private java.math.BigDecimal montonoafecto;
	private java.math.BigDecimal montototalcontratopadre;
	private String enviadoflag;
	private String reportarppflag;
	private String noreportableflag;
	private String vercontratovencidoflag;
	private String formatoadministrador;
	private String formatosupervisor;
	private String descripcionformapago;
	private String descripcionplazocontrato;
	private String antecedentes;
	private String documentoscontractuales;
	private String modalidadcontratacion;
	private String formatoresponsable;
	private String tipoproceso;
	private String tipoadj;
	private String numeroproceso;
	private java.math.BigDecimal montogarantia;
	private String montogarantiamoneda;
	private String estadointerno;
	private String busqueda;
	private String estado_descri;
	private String aprobada;
	private String revisada;
	private String preparada;
	private String tiposervicio;
	private String accion;
	private String w_po_mensaje;
	private String fecha_desde;
	private String fecha_hasta;
	private String requisicionNumero;
	private String correo;
	private String requisicioncomentario;
	private String requisicionmodalidadcontratacion;
	private String requisiciontipolicitacion;
	private Integer plazoentrega;
	private String tipoadenda_descri;
	private String correo_aprobador;


	private List<DtoComunWhContratodetalle>lstDetalleContratos = new ArrayList<DtoComunWhContratodetalle>();
	private List<DtoComunWhContratodetalle> dw_detail = new ArrayList<DtoComunWhContratodetalle>();
	private List<DtoComunWhContratorequisicion> lstContratoRequisicion = new ArrayList<DtoComunWhContratorequisicion>();
	private List<DtoComunWhContratovencimiento> lstContratoVencimientos= new ArrayList<DtoComunWhContratovencimiento>();
	private List<DtoComunSyDocumentoanexos> lstDocumentos=new ArrayList<DtoComunSyDocumentoanexos>();
		
		
	//documentos relacionados
	private List<DtoComunWhContrato>lstContratosRelacionados = new ArrayList<DtoComunWhContrato>();
	private List<DtoComunWhContratorequisicion>lstRequerimientosOrigen= new ArrayList<DtoComunWhContratorequisicion>();
	private List<DtlComunObligaciones>lstObligaciones= new ArrayList<DtlComunObligaciones>();
	private List<DtlComunWhRequisiciones>lstContratoMarco= new ArrayList<DtlComunWhRequisiciones>();
	private List<DtlComunWhRequisiciones>lstContratoMarcoEjecutado= new ArrayList<DtlComunWhRequisiciones>();
	
	//adendas
	private List<DtoComunWhContratopoliza>lstAdendas = new ArrayList<DtoComunWhContratopoliza>();
	private List<DtoComunWhContratomodificacion>lstContratoModificaciones=new ArrayList<DtoComunWhContratomodificacion>();
	
	//temps
	private BigDecimal montototal_adenda;
	private Date fechavalidezdesde_adenda;
	private Date fechavalidezhasta_adenda;
	private Boolean accionAdenda;
	
	

	public String getCorreo_aprobador() {
		return correo_aprobador;
	}

	public void setCorreo_aprobador(String correo_aprobador) {
		this.correo_aprobador = correo_aprobador;
	}

	public String getTipoadenda_descri() {
		return tipoadenda_descri;
	}

	public void setTipoadenda_descri(String tipoadenda_descri) {
		this.tipoadenda_descri = tipoadenda_descri;
	}

	public List<DtlComunWhRequisiciones> getLstContratoMarcoEjecutado() {
		return lstContratoMarcoEjecutado;
	}

	public void setLstContratoMarcoEjecutado(List<DtlComunWhRequisiciones> lstContratoMarcoEjecutado) {
		this.lstContratoMarcoEjecutado = lstContratoMarcoEjecutado;
	}

	public String getRequisicioncomentario() {
		return requisicioncomentario;
	}

	public void setRequisicioncomentario(String requisicioncomentario) {
		this.requisicioncomentario = requisicioncomentario;
	}

	public String getRequisicionmodalidadcontratacion() {
		return requisicionmodalidadcontratacion;
	}

	public void setRequisicionmodalidadcontratacion(String requisicionmodalidadcontratacion) {
		this.requisicionmodalidadcontratacion = requisicionmodalidadcontratacion;
	}

	public String getRequisiciontipolicitacion() {
		return requisiciontipolicitacion;
	}

	public void setRequisiciontipolicitacion(String requisiciontipolicitacion) {
		this.requisiciontipolicitacion = requisiciontipolicitacion;
	}

	public Boolean getAccionAdenda() {
		return accionAdenda;
	}

	public void setAccionAdenda(Boolean accionAdenda) {
		this.accionAdenda = accionAdenda;
	}

	public BigDecimal getMontototal_adenda() {
		return montototal_adenda;
	}

	public void setMontototal_adenda(BigDecimal montototal_adenda) {
		this.montototal_adenda = montototal_adenda;
	}

	public Date getFechavalidezdesde_adenda() {
		return fechavalidezdesde_adenda;
	}

	public void setFechavalidezdesde_adenda(Date fechavalidezdesde_adenda) {
		this.fechavalidezdesde_adenda = fechavalidezdesde_adenda;
	}

	public Date getFechavalidezhasta_adenda() {
		return fechavalidezhasta_adenda;
	}

	public void setFechavalidezhasta_adenda(Date fechavalidezhasta_adenda) {
		this.fechavalidezhasta_adenda = fechavalidezhasta_adenda;
	}

	public List<DtoComunWhContratopoliza> getLstAdendas() {
		return lstAdendas;
	}

	public void setLstAdendas(List<DtoComunWhContratopoliza> lstAdendas) {
		this.lstAdendas = lstAdendas;
	}

	public List<DtoComunWhContratomodificacion> getLstContratoModificaciones() {
		return lstContratoModificaciones;
	}

	public void setLstContratoModificaciones(List<DtoComunWhContratomodificacion> lstContratoModificaciones) {
		this.lstContratoModificaciones = lstContratoModificaciones;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public List<DtoComunWhContrato> getLstContratosRelacionados() {
		return lstContratosRelacionados;
	}

	public void setLstContratosRelacionados(List<DtoComunWhContrato> lstContratosRelacionados) {
		this.lstContratosRelacionados = lstContratosRelacionados;
	}

	public List<DtoComunWhContratorequisicion> getLstRequerimientosOrigen() {
		return lstRequerimientosOrigen;
	}

	public void setLstRequerimientosOrigen(List<DtoComunWhContratorequisicion> lstRequerimientosOrigen) {
		this.lstRequerimientosOrigen = lstRequerimientosOrigen;
	}

	public List<DtlComunObligaciones> getLstObligaciones() {
		return lstObligaciones;
	}

	public void setLstObligaciones(List<DtlComunObligaciones> lstObligaciones) {
		this.lstObligaciones = lstObligaciones;
	}

	public List<DtlComunWhRequisiciones> getLstContratoMarco() {
		return lstContratoMarco;
	}

	public void setLstContratoMarco(List<DtlComunWhRequisiciones> lstContratoMarco) {
		this.lstContratoMarco = lstContratoMarco;
	}

	public List<DtoComunSyDocumentoanexos> getLstDocumentos() {
		return lstDocumentos;
	}

	public void setLstDocumentos(List<DtoComunSyDocumentoanexos> lstDocumentos) {
		this.lstDocumentos = lstDocumentos;
	}

	public String getRequisicionNumero() {
		return requisicionNumero;
	}

	public void setRequisicionNumero(String requisicionNumero) {
		this.requisicionNumero = requisicionNumero;
	}

	public String getFecha_desde() {
		return fecha_desde;
	}

	public void setFecha_desde(String fecha_desde) {
		this.fecha_desde = fecha_desde;
	}

	public String getFecha_hasta() {
		return fecha_hasta;
	}

	public void setFecha_hasta(String fecha_hasta) {
		this.fecha_hasta = fecha_hasta;
	}

	public List<DtoComunWhContratovencimiento> getLstContratoVencimientos() {
		return lstContratoVencimientos;
	}

	public void setLstContratoVencimientos(List<DtoComunWhContratovencimiento> lstContratoVencimientos) {
		this.lstContratoVencimientos = lstContratoVencimientos;
	}

	public String getW_po_mensaje() {
		return w_po_mensaje;
	}

	public void setW_po_mensaje(String w_po_mensaje) {
		this.w_po_mensaje = w_po_mensaje;
	}

	public List<DtoComunWhContratorequisicion> getLstContratoRequisicion() {
		return lstContratoRequisicion;
	}

	public void setLstContratoRequisicion(List<DtoComunWhContratorequisicion> lstContratoRequisicion) {
		this.lstContratoRequisicion = lstContratoRequisicion;
	}

	public List<DtoComunWhContratodetalle> getDw_detail() {
		return dw_detail;
	}

	public void setDw_detail(List<DtoComunWhContratodetalle> dw_detail) {
		this.dw_detail = dw_detail;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getTiposervicio() {
		return tiposervicio;
	}

	public void setTiposervicio(String tiposervicio) {
		this.tiposervicio = tiposervicio;
	}

	public java.math.BigDecimal getMontodisponible() {
		return montodisponible;
	}

	public void setMontodisponible(java.math.BigDecimal montodisponible) {
		this.montodisponible = montodisponible;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public String getEstado_descri() {
		return estado_descri;
	}

	public void setEstado_descri(String estado_descri) {
		this.estado_descri = estado_descri;
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

	public List<DtoComunWhContratodetalle> getLstDetalleContratos() {
		return lstDetalleContratos;
	}

	public void setLstDetalleContratos(List<DtoComunWhContratodetalle> lstDetalleContratos) {
		this.lstDetalleContratos = lstDetalleContratos;
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
	 * @campo DocumentoInterno
	*/
	public String getDocumentointerno() {
		return documentointerno;
	}

	/**
	 * 
	 * 
	 * @campo DocumentoInterno
	*/
	public void setDocumentointerno(String documentointerno) {
		this.documentointerno = documentointerno;
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
	 * @campo NumeroContratoRelacionado
	*/
	public String getNumerocontratorelacionado() {
		return numerocontratorelacionado;
	}

	/**
	 * 
	 * 
	 * @campo NumeroContratoRelacionado
	*/
	public void setNumerocontratorelacionado(String numerocontratorelacionado) {
		this.numerocontratorelacionado = numerocontratorelacionado;
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
	 * @campo FormadePago
	*/
	public String getFormadepago() {
		return formadepago;
	}

	/**
	 * 
	 * 
	 * @campo FormadePago
	*/
	public void setFormadepago(String formadepago) {
		this.formadepago = formadepago;
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
	 * @campo FechaValidezDesde
	*/
	public java.util.Date getFechavalidezdesde() {
		return fechavalidezdesde;
	}

	/**
	 * 
	 * 
	 * @campo FechaValidezDesde
	*/
	public void setFechavalidezdesde(java.util.Date fechavalidezdesde) {
		this.fechavalidezdesde = fechavalidezdesde;
	}
	/**
	 * 
	 * 
	 * @campo FechaValidezHasta
	*/
	public java.util.Date getFechavalidezhasta() {
		return fechavalidezhasta;
	}

	/**
	 * 
	 * 
	 * @campo FechaValidezHasta
	*/
	public void setFechavalidezhasta(java.util.Date fechavalidezhasta) {
		this.fechavalidezhasta = fechavalidezhasta;
	}
	/**
	 * 
	 * 
	 * @campo FechaCierre
	*/
	public java.util.Date getFechacierre() {
		return fechacierre;
	}

	/**
	 * 
	 * 
	 * @campo FechaCierre
	*/
	public void setFechacierre(java.util.Date fechacierre) {
		this.fechacierre = fechacierre;
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
	 * @campo FechaTopeGeneracion
	*/
	public java.util.Date getFechatopegeneracion() {
		return fechatopegeneracion;
	}

	/**
	 * 
	 * 
	 * @campo FechaTopeGeneracion
	*/
	public void setFechatopegeneracion(java.util.Date fechatopegeneracion) {
		this.fechatopegeneracion = fechatopegeneracion;
	}
	/**
	 * 
	 * 
	 * @campo FechaCartaFianza01
	*/
	public java.util.Date getFechacartafianza01() {
		return fechacartafianza01;
	}

	/**
	 * 
	 * 
	 * @campo FechaCartaFianza01
	*/
	public void setFechacartafianza01(java.util.Date fechacartafianza01) {
		this.fechacartafianza01 = fechacartafianza01;
	}
	/**
	 * 
	 * 
	 * @campo FechaCartaFianza02
	*/
	public java.util.Date getFechacartafianza02() {
		return fechacartafianza02;
	}

	/**
	 * 
	 * 
	 * @campo FechaCartaFianza02
	*/
	public void setFechacartafianza02(java.util.Date fechacartafianza02) {
		this.fechacartafianza02 = fechacartafianza02;
	}
	/**
	 * 
	 * 
	 * @campo FechaRenovacion
	*/
	public java.util.Date getFecharenovacion() {
		return fecharenovacion;
	}

	/**
	 * 
	 * 
	 * @campo FechaRenovacion
	*/
	public void setFecharenovacion(java.util.Date fecharenovacion) {
		this.fecharenovacion = fecharenovacion;
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
	 * @campo MontoPagado
	*/
	public java.math.BigDecimal getMontopagado() {
		return montopagado;
	}

	/**
	 * 
	 * 
	 * @campo MontoPagado
	*/
	public void setMontopagado(java.math.BigDecimal montopagado) {
		this.montopagado = montopagado;
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
	 * @campo TipoProgramacion
	*/
	public String getTipoprogramacion() {
		return tipoprogramacion;
	}

	/**
	 * 
	 * 
	 * @campo TipoProgramacion
	*/
	public void setTipoprogramacion(String tipoprogramacion) {
		this.tipoprogramacion = tipoprogramacion;
	}
	/**
	 * 
	 * 
	 * @campo CondicionesPeriodicidadFlag
	*/
	public String getCondicionesperiodicidadflag() {
		return condicionesperiodicidadflag;
	}

	/**
	 * 
	 * 
	 * @campo CondicionesPeriodicidadFlag
	*/
	public void setCondicionesperiodicidadflag(String condicionesperiodicidadflag) {
		this.condicionesperiodicidadflag = condicionesperiodicidadflag;
	}
	/**
	 * 
	 * 
	 * @campo CondicionesPeriodicidadNumero
	*/
	public Integer getCondicionesperiodicidadnumero() {
		return condicionesperiodicidadnumero;
	}

	/**
	 * 
	 * 
	 * @campo CondicionesPeriodicidadNumero
	*/
	public void setCondicionesperiodicidadnumero(Integer condicionesperiodicidadnumero) {
		this.condicionesperiodicidadnumero = condicionesperiodicidadnumero;
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
	 * @campo ContratoResponsable
	*/
	public String getContratoresponsable() {
		return contratoresponsable;
	}

	/**
	 * 
	 * 
	 * @campo ContratoResponsable
	*/
	public void setContratoresponsable(String contratoresponsable) {
		this.contratoresponsable = contratoresponsable;
	}
	/**
	 * 
	 * 
	 * @campo ContratoTipo
	*/
	public String getContratotipo() {
		return contratotipo;
	}

	/**
	 * 
	 * 
	 * @campo ContratoTipo
	*/
	public void setContratotipo(String contratotipo) {
		this.contratotipo = contratotipo;
	}
	/**
	 * 
	 * 
	 * @campo ProveedorPaginaWeb
	*/
	public String getProveedorpaginaweb() {
		return proveedorpaginaweb;
	}

	/**
	 * 
	 * 
	 * @campo ProveedorPaginaWeb
	*/
	public void setProveedorpaginaweb(String proveedorpaginaweb) {
		this.proveedorpaginaweb = proveedorpaginaweb;
	}
	/**
	 * 
	 * 
	 * @campo GastosAdicionalesFlag
	*/
	public String getGastosadicionalesflag() {
		return gastosadicionalesflag;
	}

	/**
	 * 
	 * 
	 * @campo GastosAdicionalesFlag
	*/
	public void setGastosadicionalesflag(String gastosadicionalesflag) {
		this.gastosadicionalesflag = gastosadicionalesflag;
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
	 * @campo ProveedorContacto
	*/
	public String getProveedorcontacto() {
		return proveedorcontacto;
	}

	/**
	 * 
	 * 
	 * @campo ProveedorContacto
	*/
	public void setProveedorcontacto(String proveedorcontacto) {
		this.proveedorcontacto = proveedorcontacto;
	}
	/**
	 * 
	 * 
	 * @campo ContratoVentaFlag
	*/
	public String getContratoventaflag() {
		return contratoventaflag;
	}

	/**
	 * 
	 * 
	 * @campo ContratoVentaFlag
	*/
	public void setContratoventaflag(String contratoventaflag) {
		this.contratoventaflag = contratoventaflag;
	}
	/**
	 * 
	 * 
	 * @campo ContratoMontoZeroFlag
	*/
	public String getContratomontozeroflag() {
		return contratomontozeroflag;
	}

	/**
	 * 
	 * 
	 * @campo ContratoMontoZeroFlag
	*/
	public void setContratomontozeroflag(String contratomontozeroflag) {
		this.contratomontozeroflag = contratomontozeroflag;
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
	 * @campo MontoTotalContratoPadre
	*/
	public java.math.BigDecimal getMontototalcontratopadre() {
		return montototalcontratopadre;
	}

	/**
	 * 
	 * 
	 * @campo MontoTotalContratoPadre
	*/
	public void setMontototalcontratopadre(java.math.BigDecimal montototalcontratopadre) {
		this.montototalcontratopadre = montototalcontratopadre;
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
	 * @campo ReportarPPFlag
	*/
	public String getReportarppflag() {
		return reportarppflag;
	}

	/**
	 * 
	 * 
	 * @campo ReportarPPFlag
	*/
	public void setReportarppflag(String reportarppflag) {
		this.reportarppflag = reportarppflag;
	}
	/**
	 * 
	 * 
	 * @campo NoReportableFlag
	*/
	public String getNoreportableflag() {
		return noreportableflag;
	}

	/**
	 * 
	 * 
	 * @campo NoReportableFlag
	*/
	public void setNoreportableflag(String noreportableflag) {
		this.noreportableflag = noreportableflag;
	}
	/**
	 * 
	 * 
	 * @campo VerContratoVencidoFlag
	*/
	public String getVercontratovencidoflag() {
		return vercontratovencidoflag;
	}

	/**
	 * 
	 * 
	 * @campo VerContratoVencidoFlag
	*/
	public void setVercontratovencidoflag(String vercontratovencidoflag) {
		this.vercontratovencidoflag = vercontratovencidoflag;
	}
	/**
	 * 
	 * 
	 * @campo FormatoAdministrador
	*/
	public String getFormatoadministrador() {
		return formatoadministrador;
	}

	/**
	 * 
	 * 
	 * @campo FormatoAdministrador
	*/
	public void setFormatoadministrador(String formatoadministrador) {
		this.formatoadministrador = formatoadministrador;
	}
	/**
	 * 
	 * 
	 * @campo FormatoSupervisor
	*/
	public String getFormatosupervisor() {
		return formatosupervisor;
	}

	/**
	 * 
	 * 
	 * @campo FormatoSupervisor
	*/
	public void setFormatosupervisor(String formatosupervisor) {
		this.formatosupervisor = formatosupervisor;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionFormaPago
	*/
	public String getDescripcionformapago() {
		return descripcionformapago;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionFormaPago
	*/
	public void setDescripcionformapago(String descripcionformapago) {
		this.descripcionformapago = descripcionformapago;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionPlazoContrato
	*/
	public String getDescripcionplazocontrato() {
		return descripcionplazocontrato;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionPlazoContrato
	*/
	public void setDescripcionplazocontrato(String descripcionplazocontrato) {
		this.descripcionplazocontrato = descripcionplazocontrato;
	}
	/**
	 * 
	 * 
	 * @campo Antecedentes
	*/
	public String getAntecedentes() {
		return antecedentes;
	}

	/**
	 * 
	 * 
	 * @campo Antecedentes
	*/
	public void setAntecedentes(String antecedentes) {
		this.antecedentes = antecedentes;
	}
	/**
	 * 
	 * 
	 * @campo DocumentosContractuales
	*/
	public String getDocumentoscontractuales() {
		return documentoscontractuales;
	}

	/**
	 * 
	 * 
	 * @campo DocumentosContractuales
	*/
	public void setDocumentoscontractuales(String documentoscontractuales) {
		this.documentoscontractuales = documentoscontractuales;
	}
	/**
	 * 
	 * 
	 * @campo ModalidadContratacion
	*/
	public String getModalidadcontratacion() {
		return modalidadcontratacion;
	}

	/**
	 * 
	 * 
	 * @campo ModalidadContratacion
	*/
	public void setModalidadcontratacion(String modalidadcontratacion) {
		this.modalidadcontratacion = modalidadcontratacion;
	}
	/**
	 * 
	 * 
	 * @campo FormatoResponsable
	*/
	public String getFormatoresponsable() {
		return formatoresponsable;
	}

	/**
	 * 
	 * 
	 * @campo FormatoResponsable
	*/
	public void setFormatoresponsable(String formatoresponsable) {
		this.formatoresponsable = formatoresponsable;
	}
	/**
	 * 
	 * 
	 * @campo TipoProceso
	*/
	public String getTipoproceso() {
		return tipoproceso;
	}

	/**
	 * 
	 * 
	 * @campo TipoProceso
	*/
	public void setTipoproceso(String tipoproceso) {
		this.tipoproceso = tipoproceso;
	}
	/**
	 * 
	 * 
	 * @campo TipoAdj
	*/
	public String getTipoadj() {
		return tipoadj;
	}

	/**
	 * 
	 * 
	 * @campo TipoAdj
	*/
	public void setTipoadj(String tipoadj) {
		this.tipoadj = tipoadj;
	}
	/**
	 * 
	 * 
	 * @campo NumeroProceso
	*/
	public String getNumeroproceso() {
		return numeroproceso;
	}

	/**
	 * 
	 * 
	 * @campo NumeroProceso
	*/
	public void setNumeroproceso(String numeroproceso) {
		this.numeroproceso = numeroproceso;
	}
	/**
	 * 
	 * 
	 * @campo MontoGarantia
	*/
	public java.math.BigDecimal getMontogarantia() {
		return montogarantia;
	}

	/**
	 * 
	 * 
	 * @campo MontoGarantia
	*/
	public void setMontogarantia(java.math.BigDecimal montogarantia) {
		this.montogarantia = montogarantia;
	}
	/**
	 * 
	 * 
	 * @campo MontoGarantiaMoneda
	*/
	public String getMontogarantiamoneda() {
		return montogarantiamoneda;
	}

	/**
	 * 
	 * 
	 * @campo MontoGarantiaMoneda
	*/
	public void setMontogarantiamoneda(String montogarantiamoneda) {
		this.montogarantiamoneda = montogarantiamoneda;
	}
	/**
	 * 
	 * 
	 * @campo EstadoInterno
	*/
	public String getEstadointerno() {
		return estadointerno;
	}

	/**
	 * 
	 * 
	 * @campo EstadoInterno
	*/
	public void setEstadointerno(String estadointerno) {
		this.estadointerno = estadointerno;
	}
	public BeanWhContrato obtenerBean() {
		BeanWhContrato bean = new BeanWhContrato();
		return obtenerBean(bean);
	}

	
	public Integer getPlazoentrega() {
		return plazoentrega;
	}

	public void setPlazoentrega(Integer plazoentrega) {
		this.plazoentrega = plazoentrega;
	}

	public BeanWhContrato obtenerBean(BeanWhContrato bean) {
		if (bean == null)
			bean = new BeanWhContrato();

		bean.getPk().setCompaniasocio(companiasocio);
		bean.getPk().setNumerocontrato(numerocontrato);
		bean.setDocumentointerno(documentointerno);
		bean.setUnidadnegocio(unidadnegocio);
		bean.setNumerocontratorelacionado(numerocontratorelacionado);
		bean.setProveedor(proveedor);
		bean.setDescripcion(descripcion);
		bean.setFormadepago(formadepago);
		bean.setFechadocumento(fechadocumento);
		bean.setFechavalidezdesde(fechavalidezdesde);
		bean.setFechavalidezhasta(fechavalidezhasta);
		bean.setFechacierre(fechacierre);
		bean.setFechapreparacion(fechapreparacion);
		bean.setFechaaprobacion(fechaaprobacion);
		bean.setFechatopegeneracion(fechatopegeneracion);
		bean.setFechacartafianza01(fechacartafianza01);
		bean.setFechacartafianza02(fechacartafianza02);
		bean.setFecharenovacion(fecharenovacion);
		bean.setFlujodecaja(flujodecaja);
		bean.setMonedadocumento(monedadocumento);
		bean.setMontoimponible(montoimponible);
		bean.setMontoimpuestos(montoimpuestos);
		bean.setMontototal(montototal);
		bean.setMontopagado(montopagado);
		bean.setPreparadapor(preparadapor);
		bean.setAprobadapor(aprobadapor);
		bean.setComentarios(comentarios);
		bean.setRazonrechazo(razonrechazo);
		bean.setClasificacion(clasificacion);
		bean.setTipoprogramacion(tipoprogramacion);
		bean.setCondicionesperiodicidadflag(condicionesperiodicidadflag);
		bean.setCondicionesperiodicidadnumero(condicionesperiodicidadnumero);
		bean.setAlmacencodigo(almacencodigo);
		bean.setContratoresponsable(contratoresponsable);
		bean.setContratotipo(contratotipo);
		bean.setProveedorpaginaweb(proveedorpaginaweb);
		bean.setGastosadicionalesflag(gastosadicionalesflag);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);
		bean.setProveedorcontacto(proveedorcontacto);
		bean.setContratoventaflag(contratoventaflag);
		bean.setContratomontozeroflag(contratomontozeroflag);
		bean.setResponsablecodigo(responsablecodigo);
		bean.setRevisadapor(revisadapor);
		bean.setFecharevision(fecharevision);
		bean.setMontonoafecto(montonoafecto);
		bean.setMontototalcontratopadre(montototalcontratopadre);
		bean.setEnviadoflag(enviadoflag);
		bean.setReportarppflag(reportarppflag);
		bean.setNoreportableflag(noreportableflag);
		bean.setVercontratovencidoflag(vercontratovencidoflag);
		bean.setFormatoadministrador(formatoadministrador);
		bean.setFormatosupervisor(formatosupervisor);
		bean.setDescripcionformapago(descripcionformapago);
		bean.setDescripcionplazocontrato(descripcionplazocontrato);
		bean.setAntecedentes(antecedentes);
		bean.setDocumentoscontractuales(documentoscontractuales);
		bean.setModalidadcontratacion(modalidadcontratacion);
		bean.setFormatoresponsable(formatoresponsable);
		bean.setTipoproceso(tipoproceso);
		bean.setTipoadj(tipoadj);
		bean.setNumeroproceso(numeroproceso);
		bean.setMontogarantia(montogarantia);
		bean.setMontogarantiamoneda(montogarantiamoneda);
		bean.setEstadointerno(estadointerno);
		bean.setRequisicioncomentario(requisicioncomentario);
		bean.setRequisicionmodalidadcontratacion(requisicionmodalidadcontratacion);
		bean.setRequisiciontipolicitacion(requisiciontipolicitacion);
		bean.setPlazoentrega(plazoentrega);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
