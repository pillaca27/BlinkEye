package net.royal.spring.logistica.dominio.filtro;

import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class FiltroComunWhCotizacion extends DominioTransaccion {
	private String companiasocio;
	private String requisicionnumero;
	private String numeroinvitacion;
	private Date fechaDesde;
    private Date fechaHasta;
    private String unidadnegocio;
    private String estado;
    private Integer proveedor;
    private String accion;
    private String evento;
    private String status;
    private String cotizacionnumero;
    private Integer comprador;
    private String estadoProceso;
    
    private String modoInput;
    
    private String tipoexportar;
    
    private String modalidad;
    
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

	private DominioPaginacion paginacion;

	public DominioPaginacion getPaginacion() {
		if (paginacion == null)
			paginacion = new DominioPaginacion();
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public String getRequisicionnumero() {
		return requisicionnumero;
	}

	public void setRequisicionnumero(String requisicionnumero) {
		this.requisicionnumero = requisicionnumero;
	}

	public String getNumeroinvitacion() {
		return numeroinvitacion;
	}

	public void setNumeroinvitacion(String numeroinvitacion) {
		this.numeroinvitacion = numeroinvitacion;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getUnidadnegocio() {
		return unidadnegocio;
	}

	public void setUnidadnegocio(String unidadnegocio) {
		this.unidadnegocio = unidadnegocio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getProveedor() {
		return proveedor;
	}

	public void setProveedor(Integer proveedor) {
		this.proveedor = proveedor;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getEvento() {
		if (evento==null)
			evento="";
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCotizacionnumero() {
		return cotizacionnumero;
	}

	public void setCotizacionnumero(String cotizacionnumero) {
		this.cotizacionnumero = cotizacionnumero;
	}

	public Integer getComprador() {
		return comprador;
	}

	public void setComprador(Integer comprador) {
		this.comprador = comprador;
	}

	public String getEstadoProceso() {
		return estadoProceso;
	}

	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	public String getModoInput() {
		if (modoInput==null)
			modoInput="";
		return modoInput;
	}

	public void setModoInput(String modoInput) {
		this.modoInput = modoInput;
	}

	public String getTipoexportar() {
		return tipoexportar;
	}

	public void setTipoexportar(String tipoexportar) {
		this.tipoexportar = tipoexportar;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	
}
