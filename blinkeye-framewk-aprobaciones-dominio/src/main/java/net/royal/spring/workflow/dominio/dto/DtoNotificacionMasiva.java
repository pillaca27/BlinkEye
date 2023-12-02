package net.royal.spring.workflow.dominio.dto;

import java.util.ArrayList;
import java.util.List;

public class DtoNotificacionMasiva {

	private List<DtoPersonaNotificar> transacciones;
	private List<DtoAdjuntoNotificar> adjuntos;
	private String mensaje;

	public DtoNotificacionMasiva() {
		this.transacciones = new ArrayList<DtoPersonaNotificar>();
		this.adjuntos = new ArrayList<DtoAdjuntoNotificar>();
	}

	public List<DtoPersonaNotificar> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(List<DtoPersonaNotificar> transacciones) {
		this.transacciones = transacciones;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<DtoAdjuntoNotificar> getAdjuntos() {
		return adjuntos;
	}

	public void setAdjuntos(List<DtoAdjuntoNotificar> adjuntos) {
		this.adjuntos = adjuntos;
	}

}
