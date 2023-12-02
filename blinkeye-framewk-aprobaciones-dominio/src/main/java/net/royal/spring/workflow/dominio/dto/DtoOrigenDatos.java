package net.royal.spring.workflow.dominio.dto;

import java.math.BigDecimal;

public class DtoOrigenDatos {

	private BigDecimal origenDatos;
	private String cadenaconexion;
	private String dialecto;
	private String driver;

	public BigDecimal getOrigenDatos() {
		return origenDatos;
	}

	public void setOrigenDatos(BigDecimal origenDatos) {
		this.origenDatos = origenDatos;
	}

	public String getCadenaconexion() {
		return cadenaconexion;
	}

	public void setCadenaconexion(String cadenaconexion) {
		this.cadenaconexion = cadenaconexion;
	}

	public String getDialecto() {
		return dialecto;
	}

	public void setDialecto(String dialecto) {
		this.dialecto = dialecto;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

}
