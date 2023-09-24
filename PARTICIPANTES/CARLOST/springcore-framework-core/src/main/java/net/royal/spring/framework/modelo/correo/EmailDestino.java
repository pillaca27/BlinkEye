package net.royal.spring.framework.modelo.correo;


public class EmailDestino {

	public enum tipo_destino {
		TO, CC, BCC
	}
	
	private tipo_destino destino;
	private String correoDestino;

	public EmailDestino() {
	}

	public EmailDestino(String correoDestino) {
		this.correoDestino = correoDestino;
	}
	
	public EmailDestino(String correoDestino, tipo_destino destino) {
		this.correoDestino = correoDestino;
		this.destino = destino;
	}

	public String getCorreoDestino() {
		return correoDestino;
	}

	public void setCorreoDestino(String correoDestino) {
		this.correoDestino = correoDestino;
	}

	public tipo_destino getDestino() {
		return destino;
	}

	public void setDestino(tipo_destino destino) {
		this.destino = destino;
	}
}
