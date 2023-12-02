package net.royal.spring.workflow.dominio.dto;

public class DtoAprobacionAccion {
	private String nombreBoton;
	private String subaccion;

	public DtoAprobacionAccion() {

	}

	public DtoAprobacionAccion(String nombreBoton, String subaccion) {
		super();
		this.nombreBoton = nombreBoton;
		this.subaccion = subaccion;
	}

	public String getNombreBoton() {
		return nombreBoton;
	}

	public void setNombreBoton(String nombreBoton) {
		this.nombreBoton = nombreBoton;
	}

	public String getSubaccion() {
		return subaccion;
	}

	public void setSubaccion(String subaccion) {
		this.subaccion = subaccion;
	}

}
