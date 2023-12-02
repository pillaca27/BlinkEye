package net.royal.spring.workflow.dominio.dto;

import java.util.ArrayList;
import java.util.List;

import net.royal.spring.framework.modelo.generico.dto.DtoTabla;

public class DtoNotificacionMensajeDetalle {

	private Integer transaccionId;
	private String referencia;
	private String mensaje;
	private List<DtoTabla> adjuntos;

	public DtoNotificacionMensajeDetalle() {
		this.adjuntos = new ArrayList<DtoTabla>();
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<DtoTabla> getAdjuntos() {
		return adjuntos;
	}

	public void setAdjuntos(List<DtoTabla> adjuntos) {
		this.adjuntos = adjuntos;
	}

	public Integer getTransaccionId() {
		return transaccionId;
	}

	public void setTransaccionId(Integer transaccionId) {
		this.transaccionId = transaccionId;
	}
}
