package net.royal.spring.workflow.dominio.dto;

import java.util.ArrayList;
import java.util.List;

public class DtoWfMacroProcesoAdjuntos {
	private Integer transaccionId;
	private String procesoNombre;
	private String referencia;
	private String tareaNombre;
	private String fecha;
	private String referenciaPadre;
	private String estadoNombre;
	private String componenteWeb;
	private String estadoColor;

	private List<DtoFlujoAdjunto> adjuntos;

	public DtoWfMacroProcesoAdjuntos() {
		this.adjuntos = new ArrayList<DtoFlujoAdjunto>();
	}

	public Integer getTransaccionId() {
		return transaccionId;
	}

	public void setTransaccionId(Integer transaccionId) {
		this.transaccionId = transaccionId;
	}

	public String getProcesoNombre() {
		return procesoNombre;
	}

	public void setProcesoNombre(String procesoNombre) {
		this.procesoNombre = procesoNombre;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getReferenciaPadre() {
		return referenciaPadre;
	}

	public void setReferenciaPadre(String referenciaPadre) {
		this.referenciaPadre = referenciaPadre;
	}

	public List<DtoFlujoAdjunto> getAdjuntos() {
		return adjuntos;
	}

	public void setAdjuntos(List<DtoFlujoAdjunto> adjuntos) {
		this.adjuntos = adjuntos;
	}

	public String getTareaNombre() {
		return tareaNombre;
	}

	public void setTareaNombre(String tareaNombre) {
		this.tareaNombre = tareaNombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEstadoNombre() {
		return estadoNombre;
	}

	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}

	public String getComponenteWeb() {
		return componenteWeb;
	}

	public void setComponenteWeb(String componenteWeb) {
		this.componenteWeb = componenteWeb;
	}

	public String getEstadoColor() {
		return estadoColor;
	}

	public void setEstadoColor(String estadoColor) {
		this.estadoColor = estadoColor;
	}
	
	
}
