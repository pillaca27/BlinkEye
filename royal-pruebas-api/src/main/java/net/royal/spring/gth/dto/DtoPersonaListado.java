package net.royal.spring.gth.dto;

public class DtoPersonaListado {
	private Integer personaCodigo;
	private String personaNombreCompleto;
	private String datoAdicional;

	public String getDatoAdicional() {
		return datoAdicional;
	}

	public void setDatoAdicional(String datoAdicional) {
		this.datoAdicional = datoAdicional;
	}

	public Integer getPersonaCodigo() {
		return personaCodigo;
	}

	public void setPersonaCodigo(Integer personaCodigo) {
		this.personaCodigo = personaCodigo;
	}

	public String getPersonaNombreCompleto() {
		return personaNombreCompleto;
	}

	public void setPersonaNombreCompleto(String personaNombreCompleto) {
		this.personaNombreCompleto = personaNombreCompleto;
	}

}
