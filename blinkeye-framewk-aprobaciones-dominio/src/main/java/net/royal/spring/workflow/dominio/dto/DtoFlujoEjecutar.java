package net.royal.spring.workflow.dominio.dto;

import java.util.ArrayList;
import java.util.List;

public class DtoFlujoEjecutar {
	
	private String accion;
	private String subaccion;
	private List<DtoFlujoSolicitud> listaSolicitudes;

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public List<DtoFlujoSolicitud> getListaSolicitudes() {
		if (listaSolicitudes==null)
			listaSolicitudes=new ArrayList<DtoFlujoSolicitud>();
		return listaSolicitudes;
	}

	public void setListaSolicitudes(List<DtoFlujoSolicitud> listaSolicitudes) {
		this.listaSolicitudes = listaSolicitudes;
	}

	public String getSubaccion() {
		return subaccion;
	}

	public void setSubaccion(String subaccion) {
		this.subaccion = subaccion;
	}

}
