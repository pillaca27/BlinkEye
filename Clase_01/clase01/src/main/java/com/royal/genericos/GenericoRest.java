package com.royal.genericos;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.royal.util.SeguridadUsuarioActual;

public class GenericoRest {

	@Autowired
	private HttpServletRequest request;

	public SeguridadUsuarioActual getUsuarioActual() {
		ObjectMapper mapper = new ObjectMapper();
		String ua = request.getHeader("usuarioactual");

		SeguridadUsuarioActual usu = new SeguridadUsuarioActual();
		usu.setCompaniaNombre("Royal");
		usu.setPersonaId(-1);
		usu.setUsuario("MISESF");
		return usu;
	}
}
