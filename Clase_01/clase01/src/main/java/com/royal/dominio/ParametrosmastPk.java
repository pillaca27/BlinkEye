package com.royal.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ParametrosmastPk implements Serializable {

	public ParametrosmastPk() {

	}

	public ParametrosmastPk(String companiacodigo, String aplicacioncodigo, String parametroclave) {
		this.companiacodigo = companiacodigo;
		this.aplicacioncodigo = aplicacioncodigo;
		this.parametroclave = parametroclave;
	}

	@NotEmpty
	@Size(min = 0, max = 10)
	@Column(name = "COMPANIACODIGO", length = 10, nullable = true)
	private String companiacodigo;

	@NotEmpty
	@Size(min = 0, max = 2)
	@Column(name = "APLICACIONCODIGO", length = 2, nullable = true)
	private String aplicacioncodigo;
	

	@NotEmpty
	@Size(min = 0, max = 10)
	@Column(name = "PARAMETROCLAVE", length = 10, nullable = true)
	private String parametroclave;

	public String getCompaniacodigo() {
		return companiacodigo;
	}

	public void setCompaniacodigo(String companiacodigo) {
		this.companiacodigo = companiacodigo;
	}

	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}

	public String getParametroclave() {
		return parametroclave;
	}

	public void setParametroclave(String parametroclave) {
		this.parametroclave = parametroclave;
	}

}
