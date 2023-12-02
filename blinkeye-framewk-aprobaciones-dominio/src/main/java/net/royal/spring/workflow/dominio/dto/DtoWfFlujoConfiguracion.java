package net.royal.spring.workflow.dominio.dto;

import java.util.ArrayList;
import java.util.List;

public class DtoWfFlujoConfiguracion {

	private String proceso;
	private Integer flujo;
	private Integer configuracion;
	private String variable;
	private String comparacion;
	private String valorDescripcion;
	private List<String> valores;
	private Integer relacion;

	public DtoWfFlujoConfiguracion() {
		this.valores = new ArrayList<String>();
	}

	public Integer getFlujo() {
		return flujo;
	}

	public void setFlujo(Integer flujo) {
		this.flujo = flujo;
	}

	public Integer getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(Integer configuracion) {
		this.configuracion = configuracion;
	}

	public String getComparacion() {
		return comparacion;
	}

	public void setComparacion(String comparacion) {
		this.comparacion = comparacion;
	}

	public String getValorDescripcion() {
		return valorDescripcion;
	}

	public void setValorDescripcion(String valorDescripcion) {
		this.valorDescripcion = valorDescripcion;
	}

	public List<String> getValores() {
		return valores;
	}

	public void setValores(List<String> valores) {
		this.valores = valores;
	}

	public Integer getRelacion() {
		return relacion;
	}

	public void setRelacion(Integer relacion) {
		this.relacion = relacion;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

}
