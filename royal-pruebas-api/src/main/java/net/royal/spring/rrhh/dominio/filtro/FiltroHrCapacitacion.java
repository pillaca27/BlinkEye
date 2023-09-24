package net.royal.spring.rrhh.dominio.filtro;

import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroHrCapacitacion {

	private String capacitacion;
	private String companyOwner;
	private Date fechaInicio;
	private Date fechaFin;
	private String estado;

	private DominioPaginacion dominioPaginacion;

	public FiltroHrCapacitacion() {
		this.dominioPaginacion = new DominioPaginacion();
	}

	public String getCapacitacion() {
		return capacitacion;
	}

	public void setCapacitacion(String capacitacion) {
		this.capacitacion = capacitacion;
	}

	public String getCompanyOwner() {
		return companyOwner;
	}

	public void setCompanyOwner(String companyOwner) {
		this.companyOwner = companyOwner;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public DominioPaginacion getDominioPaginacion() {
		return dominioPaginacion;
	}

	public void setDominioPaginacion(DominioPaginacion dominioPaginacion) {
		this.dominioPaginacion = dominioPaginacion;
	}

}
