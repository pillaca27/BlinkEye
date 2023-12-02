package net.royal.spring.workflow.dominio.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DtoWfFlujoNivelAprobador {

	private String proceso;
	private Integer flujo;
	private Integer nivel;
	private Integer empleado;
	private String nombreEmpleado;
	private String valorDescripcion;
	private List<String> valores;

	public DtoWfFlujoNivelAprobador() {
		this.valores = new ArrayList<String>();
	}

	public Integer getFlujo() {
		return flujo;
	}

	public void setFlujo(Integer flujo) {
		this.flujo = flujo;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Integer getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Integer empleado) {
		this.empleado = empleado;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
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

}
