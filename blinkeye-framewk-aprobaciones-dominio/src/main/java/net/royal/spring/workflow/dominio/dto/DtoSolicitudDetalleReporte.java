package net.royal.spring.workflow.dominio.dto;

import java.util.ArrayList;
import java.util.List;

public class DtoSolicitudDetalleReporte {

	private String clave;
	private List<DtoWfFormularioDatos> metadatos;

	public DtoSolicitudDetalleReporte() {
		metadatos = new ArrayList<DtoWfFormularioDatos>();
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public List<DtoWfFormularioDatos> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(List<DtoWfFormularioDatos> metadatos) {
		this.metadatos = metadatos;
	}

}
