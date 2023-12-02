package net.royal.spring.logistica.dominio.filtro;

import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class FiltroComunWhLicitacionrequisicion extends DominioTransaccion {


	private String companiasocio;
	private String numeroproceso;
	private String tipoproceso;
	private String tipoadj;
	private String clasificacion;
	private Integer empleadoId;
	private String empleadoNombre;
	private String centroCostoId;
	private String centroCostoNombre;
	private String estado;
	private String comprasAlmacen;
	private Date fechaDesde;
	private Date fechaHasta;
	private String requisicionNumero;
	private String unidadNegocio;
	private String proceso;
	
	private String wfProcesoComponente;
	private Integer comprador;
	
	private String estadoId;
	
	private String tipoexportar;
	
	private String modalidad;

	public Integer getComprador() {
		return comprador;
	}

	public void setComprador(Integer comprador) {
		this.comprador = comprador;
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

	private DominioPaginacion paginacion;

	public DominioPaginacion getPaginacion() {
		if (paginacion == null)
			paginacion = new DominioPaginacion();
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public Integer getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(Integer empleadoId) {
		this.empleadoId = empleadoId;
	}

	public String getEmpleadoNombre() {
		return empleadoNombre;
	}

	public void setEmpleadoNombre(String empleadoNombre) {
		this.empleadoNombre = empleadoNombre;
	}

	public String getCentroCostoId() {
		return centroCostoId;
	}

	public void setCentroCostoId(String centroCostoId) {
		this.centroCostoId = centroCostoId;
	}

	public String getCentroCostoNombre() {
		return centroCostoNombre;
	}

	public void setCentroCostoNombre(String centroCostoNombre) {
		this.centroCostoNombre = centroCostoNombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getComprasAlmacen() {
		return comprasAlmacen;
	}

	public void setComprasAlmacen(String comprasAlmacen) {
		this.comprasAlmacen = comprasAlmacen;
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

	public String getRequisicionNumero() {
		return requisicionNumero;
	}

	public void setRequisicionNumero(String requisicionNumero) {
		this.requisicionNumero = requisicionNumero;
	}

	public String getUnidadNegocio() {
		return unidadNegocio;
	}

	public void setUnidadNegocio(String unidadNegocio) {
		this.unidadNegocio = unidadNegocio;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getWfProcesoComponente() {
		return wfProcesoComponente;
	}

	public void setWfProcesoComponente(String wfProcesoComponente) {
		this.wfProcesoComponente = wfProcesoComponente;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
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
