package com.royal.util;

public class SeguridadUsuarioActual {
	public static final String CONSTRAINTS_NOTNULL = "Usuario actual Nulo";
	private String companiaNombre;
	private String usuario;
	private Integer personaId;
	
	public String getCompaniaNombre() {
		return companiaNombre;
	}
	public void setCompaniaNombre(String companiaNombre) {
		this.companiaNombre = companiaNombre;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Integer getPersonaId() {
		return personaId;
	}
	public void setPersonaId(Integer personaId) {
		this.personaId = personaId;
	}
}