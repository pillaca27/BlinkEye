package net.royal.spring.workflow.dominio.filtro;

import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroSolicitudes {
	private Integer Tipo;
	private String CompaniaSocio;
	private String Aplicacion;
	private String Proceso;
	private Integer NumeroProceso;
	private Integer ProcesoInterno;
	private String UnidadReplicacion;
	private String Estado;
	private String Documento;
	private Date FechaDesde;
	private Date FechaHasta;
	private Integer IdPersonaSolicitante;
	private Integer idPersonaSolicitante2;

	private Integer IdPersonaActual;
	private Integer Empleado;
	private Integer Nivel;
	private Integer Plan;
	private DominioPaginacion paginacion;
	private Date FechaSolicitud;
	private String Concepto;
	private String Aprobar1;
	private String Aprobar2;
	private String referencia;
	private String area;
	private String estadoProceso;

	private Double montohasta;
	private Double montodesde;

	public FiltroSolicitudes() {
		paginacion = new DominioPaginacion();
	}

	public Integer getTipo() {
		return Tipo;
	}

	public void setTipo(Integer tipo) {
		Tipo = tipo;
	}

	public String getCompaniaSocio() {
		return CompaniaSocio;
	}

	public void setCompaniaSocio(String companiaSocio) {
		CompaniaSocio = companiaSocio;
	}

	public String getAplicacion() {
		return Aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		Aplicacion = aplicacion;
	}

	public String getProceso() {
		return Proceso;
	}

	public void setProceso(String proceso) {
		Proceso = proceso;
	}

	public Integer getNumeroProceso() {
		return NumeroProceso;
	}

	public void setNumeroProceso(Integer numeroProceso) {
		NumeroProceso = numeroProceso;
	}

	public Integer getProcesoInterno() {
		return ProcesoInterno;
	}

	public void setProcesoInterno(Integer procesoInterno) {
		ProcesoInterno = procesoInterno;
	}

	public String getUnidadReplicacion() {
		return UnidadReplicacion;
	}

	public void setUnidadReplicacion(String unidadReplicacion) {
		UnidadReplicacion = unidadReplicacion;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public String getDocumento() {
		return Documento;
	}

	public void setDocumento(String documento) {
		Documento = documento;
	}

	public Date getFechaDesde() {
		return FechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		FechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return FechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		FechaHasta = fechaHasta;
	}

	public Integer getIdPersonaSolicitante() {
		return IdPersonaSolicitante;
	}

	public void setIdPersonaSolicitante(Integer idPersonaSolicitante) {
		IdPersonaSolicitante = idPersonaSolicitante;
	}

	public Integer getIdPersonaActual() {
		return IdPersonaActual;
	}

	public void setIdPersonaActual(Integer idPersonaActual) {
		IdPersonaActual = idPersonaActual;
	}

	public Integer getEmpleado() {
		return Empleado;
	}

	public void setEmpleado(Integer empleado) {
		Empleado = empleado;
	}

	public Integer getNivel() {
		return Nivel;
	}

	public void setNivel(Integer nivel) {
		Nivel = nivel;
	}

	public Integer getPlan() {
		return Plan;
	}

	public void setPlan(Integer plan) {
		Plan = plan;
	}

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public Date getFechaSolicitud() {
		return FechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		FechaSolicitud = fechaSolicitud;
	}

	public String getConcepto() {
		return Concepto;
	}

	public void setConcepto(String concepto) {
		Concepto = concepto;
	}

	public String getAprobar1() {
		return Aprobar1;
	}

	public void setAprobar1(String aprobar1) {
		Aprobar1 = aprobar1;
	}

	public String getAprobar2() {
		return Aprobar2;
	}

	public void setAprobar2(String aprobar2) {
		Aprobar2 = aprobar2;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Double getMontohasta() {
		return montohasta;
	}

	public void setMontohasta(Double montohasta) {
		this.montohasta = montohasta;
	}

	public Double getMontodesde() {
		return montodesde;
	}

	public void setMontodesde(Double montodesde) {
		this.montodesde = montodesde;
	}

	public Integer getIdPersonaSolicitante2() {
		return idPersonaSolicitante2;
	}

	public void setIdPersonaSolicitante2(Integer idPersonaSolicitante2) {
		this.idPersonaSolicitante2 = idPersonaSolicitante2;
	}

	public String getEstadoProceso() {
		return estadoProceso;
	}

	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

}
