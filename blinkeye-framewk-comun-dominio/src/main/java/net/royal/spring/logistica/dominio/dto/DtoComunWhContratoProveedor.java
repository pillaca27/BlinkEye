package net.royal.spring.logistica.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunWhContratoProveedor extends DominioTransaccion implements java.io.Serializable {

	private String compania;
	private String numerocontrato;
	private String numeroInterno;
	private String monedaDescripcion;
	private String moneda;
	private BigDecimal montoTotal;
	private BigDecimal montoPagado;
	private Integer proveedor;
	private String contratoVencido;
	private String descripcion;
	private Date fechadesde;
	private Date fechahasta;
	private String estado;
	private String clasificacion;
	private String proveedornombre;
	private String companianombre;
	private String tiponombre;
	private String documento;
	private Integer evtevatablaid;
	private BigDecimal evapuntajefinal;
	private String evacalificacionfinaldesc;
	private String nivelclasificacion;
	private Date fechaevaluacion;
	private String evausuariocreacion;
	private String evtdescripcionlocal;

	private String requisicioncomentario;
	private String requisicionmodalidadcontratacion;
	private String requisiciontipolicitacion;
	private String centrocosto;
	private Integer plazoentrega;

	public Integer getPlazoentrega() {
		return plazoentrega;
	}

	public void setPlazoentrega(Integer plazoentrega) {
		this.plazoentrega = plazoentrega;
	}

	private BigDecimal ROWNUM_;

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

	public Date getFechaevaluacion() {
		return fechaevaluacion;
	}

	public void setFechaevaluacion(Date fechaevaluacion) {
		this.fechaevaluacion = fechaevaluacion;
	}

	public String getEvausuariocreacion() {
		return evausuariocreacion;
	}

	public void setEvausuariocreacion(String evausuariocreacion) {
		this.evausuariocreacion = evausuariocreacion;
	}

	public String getEvtdescripcionlocal() {
		return evtdescripcionlocal;
	}

	public void setEvtdescripcionlocal(String evtdescripcionlocal) {
		this.evtdescripcionlocal = evtdescripcionlocal;
	}

	public String getNivelclasificacion() {
		return nivelclasificacion;
	}

	public void setNivelclasificacion(String nivelclasificacion) {
		this.nivelclasificacion = nivelclasificacion;
	}

	public Integer getEvtevatablaid() {
		return evtevatablaid;
	}

	public void setEvtevatablaid(Integer evtevatablaid) {
		this.evtevatablaid = evtevatablaid;
	}

	public BigDecimal getEvapuntajefinal() {
		return evapuntajefinal;
	}

	public void setEvapuntajefinal(BigDecimal evapuntajefinal) {
		this.evapuntajefinal = evapuntajefinal;
	}

	public String getEvacalificacionfinaldesc() {
		return evacalificacionfinaldesc;
	}

	public void setEvacalificacionfinaldesc(String evacalificacionfinaldesc) {
		this.evacalificacionfinaldesc = evacalificacionfinaldesc;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public String getNumerocontrato() {
		return numerocontrato;
	}

	public void setNumerocontrato(String numerocontrato) {
		this.numerocontrato = numerocontrato;
	}

	public Integer getProveedor() {
		return proveedor;
	}

	public void setProveedor(Integer proveedor) {
		this.proveedor = proveedor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechadesde() {
		return fechadesde;
	}

	public void setFechadesde(Date fechadesde) {
		this.fechadesde = fechadesde;
	}

	public Date getFechahasta() {
		return fechahasta;
	}

	public void setFechahasta(Date fechahasta) {
		this.fechahasta = fechahasta;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public String getTiponombre() {
		return tiponombre;
	}

	public void setTiponombre(String tiponombre) {
		this.tiponombre = tiponombre;
	}

	public String getNumeroInterno() {
		return numeroInterno;
	}

	public void setNumeroInterno(String numeroInterno) {
		this.numeroInterno = numeroInterno;
	}

	public String getMonedaDescripcion() {
		return monedaDescripcion;
	}

	public void setMonedaDescripcion(String monedaDescripcion) {
		this.monedaDescripcion = monedaDescripcion;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	public BigDecimal getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(BigDecimal montoPagado) {
		this.montoPagado = montoPagado;
	}

	public String getContratoVencido() {
		return contratoVencido;
	}

	public void setContratoVencido(String contratoVencido) {
		this.contratoVencido = contratoVencido;
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

	public String getCentrocosto() {
		return centrocosto;
	}

	public void setCentrocosto(String centrocosto) {
		this.centrocosto = centrocosto;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

}
