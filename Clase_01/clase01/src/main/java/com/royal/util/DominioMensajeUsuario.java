package com.royal.util;

import java.io.Serializable;
import java.util.List;


public class DominioMensajeUsuario implements Serializable {
	/**
	 * 
	 */
	public enum tipo_mensaje {
	ERROR, ADVERTENCIA, EXITO, INFORMACION,PREGUNTA
	};

	private static final long serialVersionUID = -2801857163477327081L;
	private tipo_mensaje tipoMensaje;

	private String codigo;
	private String titulo;
	private String mensaje;
	private String fuente;
	private String solucion;

	public static String concatenarArregloValidator(List<DominioMensajeUsuario> listaMensajeUsuario) {
		String mensajeInterno = "";
		Integer contador = 0;
		if (listaMensajeUsuario == null)
			return "";

		for (DominioMensajeUsuario val : listaMensajeUsuario) {
			if (contador == 0) {
				mensajeInterno = val.getMensaje();
			} else {
				mensajeInterno = mensajeInterno + "\n" + val.getMensaje();
				contador++;
			}
		}
		return mensajeInterno;
	}
	
	public DominioMensajeUsuario(Exception e) {
		this.titulo=e.getMessage();
		this.mensaje=UException.getStackTrace(e);
	}

	public DominioMensajeUsuario(tipo_mensaje tipoMensaje, String mensaje) {
		this.tipoMensaje = tipoMensaje;
		this.mensaje = mensaje;
	}

	public DominioMensajeUsuario(tipo_mensaje tipoMensaje, String titulo, String mensaje) {
		this.tipoMensaje = tipoMensaje;
		this.titulo = titulo;
		this.mensaje = mensaje;
	}
	
	public DominioMensajeUsuario() {
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public String getSolucion() {
		return solucion;
	}

	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}

	public tipo_mensaje getTipoMensaje() {
		return tipoMensaje;
	}

	public void setTipoMensaje(tipo_mensaje tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}