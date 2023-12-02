package net.royal.spring.core.dominio.filtro;

import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunEmpleado {

	private Integer empleado;
	private String nombreempleado;
	private String centrocostodescripcion;
	private String centrocosto;
	private Date desde;
	private Date hasta;
	private DominioPaginacion paginacion;
	private String companiasocio;
	
	

	public String getCompaniasocio() {
		return companiasocio;
	}

	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}

	public FiltroComunEmpleado() {
		this.paginacion = new DominioPaginacion();
	}

	public Integer getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Integer empleado) {
		this.empleado = empleado;
	}

	public String getNombreempleado() {
		return nombreempleado;
	}

	public void setNombreempleado(String nombreempleado) {
		this.nombreempleado = nombreempleado;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public String getCentrocostodescripcion() {
		return centrocostodescripcion;
	}

	public void setCentrocostodescripcion(String centrocostodescripcion) {
		this.centrocostodescripcion = centrocostodescripcion;
	}

	public String getCentrocosto() {
		return centrocosto;
	}

	public void setCentrocosto(String centrocosto) {
		this.centrocosto = centrocosto;
	}

}
