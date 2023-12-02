package net.royal.spring.comun.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunSyReportearchivoWf {

	private String aplicacioncodigo;
	private String reportecodigo;
	private String estado;

	private DominioPaginacion paginacion=new DominioPaginacion();

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}

	public String getReportecodigo() {
		return reportecodigo;
	}

	public void setReportecodigo(String reportecodigo) {
		this.reportecodigo = reportecodigo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
