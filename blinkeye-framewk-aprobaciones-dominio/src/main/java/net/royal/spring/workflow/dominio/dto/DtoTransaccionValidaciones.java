package net.royal.spring.workflow.dominio.dto;

import java.math.BigDecimal;

public class DtoTransaccionValidaciones {

	private String spvalidar;
	private String spaprobar;
	private String sprechazar;
	private String spdevolver;
	private String api;

	private BigDecimal origenDatos;
	private String cadenaconexion;
	private String dialecto;
	private String driver;

	public String getSpvalidar() {
		return spvalidar;
	}

	public void setSpvalidar(String spvalidar) {
		this.spvalidar = spvalidar;
	}

	public String getSpaprobar() {
		return spaprobar;
	}

	public void setSpaprobar(String spaprobar) {
		this.spaprobar = spaprobar;
	}

	public String getSprechazar() {
		return sprechazar;
	}

	public void setSprechazar(String sprechazar) {
		this.sprechazar = sprechazar;
	}

	public String getSpdevolver() {
		return spdevolver;
	}

	public void setSpdevolver(String spdevolver) {
		this.spdevolver = spdevolver;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

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
