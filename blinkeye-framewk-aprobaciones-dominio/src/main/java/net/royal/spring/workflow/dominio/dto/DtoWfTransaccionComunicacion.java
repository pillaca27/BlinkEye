package net.royal.spring.workflow.dominio.dto;

public class DtoWfTransaccionComunicacion {
	
	private Integer comunicacionId;
	private Integer comunicacionPadreId;
	private Integer emisorId;
	private String emisorNombre;
	private String mensaje;
	private String tipoMensajeId;

	public Integer getComunicacionId() {
		return comunicacionId;
	}

	public void setComunicacionId(Integer comunicacionId) {
		this.comunicacionId = comunicacionId;
	}

	public Integer getComunicacionPadreId() {
		return comunicacionPadreId;
	}

	public void setComunicacionPadreId(Integer comunicacionPadreId) {
		this.comunicacionPadreId = comunicacionPadreId;
	}

	public Integer getEmisorId() {
		return emisorId;
	}

	public void setEmisorId(Integer emisorId) {
		this.emisorId = emisorId;
	}

	public String getEmisorNombre() {
		return emisorNombre;
	}

	public void setEmisorNombre(String emisorNombre) {
		this.emisorNombre = emisorNombre;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getTipoMensajeId() {
		return tipoMensajeId;
	}

	public void setTipoMensajeId(String tipoMensajeId) {
		this.tipoMensajeId = tipoMensajeId;
	}

}
