package net.royal.spring.workflow.dominio.dto;

import java.math.BigDecimal;

public class DtoFlujoConfiguracion {

	private String variable;
	private String valores;
	private String valor;
	private String comparacion;

	public DtoFlujoConfiguracion(String variable, String valor) {
		super();
		this.variable = variable;
		this.valor = valor;
	}

	public DtoFlujoConfiguracion() {
		super();
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public String getValores() {
		return valores;
	}

	public void setValores(String valores) {
		this.valores = valores;
	}

	public String getComparacion() {
		return comparacion;
	}

	public void setComparacion(String comparacion) {
		this.comparacion = comparacion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
