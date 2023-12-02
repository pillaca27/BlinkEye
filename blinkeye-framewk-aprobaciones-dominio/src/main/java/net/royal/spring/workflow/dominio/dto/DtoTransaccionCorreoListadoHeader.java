package net.royal.spring.workflow.dominio.dto;

import java.util.ArrayList;
import java.util.List;

public class DtoTransaccionCorreoListadoHeader {

	public DtoTransaccionCorreoListadoHeader() {
		this.lst = new ArrayList<DtoTransaccionCorreoListado>();
	}

	private String reportePorNiveles;
	private String aplicacion;
	private List<DtoTransaccionCorreoListado> lst;

	public String getReportePorNiveles() {
		return reportePorNiveles;
	}

	public void setReportePorNiveles(String reportePorNiveles) {
		this.reportePorNiveles = reportePorNiveles;
	}

	public List<DtoTransaccionCorreoListado> getLst() {
		return lst;
	}

	public void setLst(List<DtoTransaccionCorreoListado> lst) {
		this.lst = lst;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

}
