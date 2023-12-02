package net.royal.spring.workflow.dominio.dto;

import java.util.ArrayList;
import java.util.List;

public class DtoWfFlujo {

	private String proceso;
	private Integer flujo;
	private String descripcion;
	private List<DtoWfFlujoConfiguracion> configuraciones;
	private List<DtoWfFlujoNivel> niveles;

	public DtoWfFlujo() {
		this.configuraciones = new ArrayList<DtoWfFlujoConfiguracion>();
		this.niveles = new ArrayList<DtoWfFlujoNivel>();
	}

	public Integer getFlujo() {
		return flujo;
	}

	public void setFlujo(Integer flujo) {
		this.flujo = flujo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<DtoWfFlujoConfiguracion> getConfiguraciones() {
		return configuraciones;
	}

	public void setConfiguraciones(List<DtoWfFlujoConfiguracion> configuraciones) {
		this.configuraciones = configuraciones;
	}

	public List<DtoWfFlujoNivel> getNiveles() {
		return niveles;
	}

	public void setNiveles(List<DtoWfFlujoNivel> niveles) {
		this.niveles = niveles;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

}
