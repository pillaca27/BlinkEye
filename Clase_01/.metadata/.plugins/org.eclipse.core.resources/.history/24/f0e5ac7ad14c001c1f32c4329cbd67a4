package net.royal.spring.framework.core;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.util.UString;

public class UException extends Exception {

	private List<DominioMensajeUsuario> errors;
	private DominioMensajeUsuario.tipo_mensaje tipomensaje;

	public List<DominioMensajeUsuario> getErrors() {

		List<DominioMensajeUsuario> lista = errors;

		if (lista == null) {
			lista = new ArrayList<DominioMensajeUsuario>();
		}

		if (!UString.estaVacio(this.getMessage())) {
			DominioMensajeUsuario mm = new DominioMensajeUsuario();
			mm.setMensaje(this.getMessage());
			mm.setTipoMensaje(this.tipomensaje);

			boolean esta = false;

			for (DominioMensajeUsuario mensajeUsuario : lista) {
				if (mensajeUsuario.getMensaje().equalsIgnoreCase(this.getMessage())) {
					esta = true;
				}
			}

			if (!esta) {
				lista.add(mm);
			}
		}

		return lista;
	}

	public void setErrors(List<DominioMensajeUsuario> errors) {
		this.errors = errors;
	}

	public UException(List<DominioMensajeUsuario> listaMensajeUsuario) {
		super(DominioMensajeUsuario.concatenarArregloValidator(listaMensajeUsuario));
		this.errors = listaMensajeUsuario;
	}

	public UException(final String message) {
		super(message);
		this.tipomensaje = DominioMensajeUsuario.tipo_mensaje.INFORMACION;
	}

	public UException(final String message, tipo_mensaje tipomensaje) {
		super(message);
		this.tipomensaje = tipomensaje;
	}

	public UException() {
		super();
	}

	public UException(DominioMensajeUsuario e) {
		super(e.getMensaje());
	}

	public static String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		t.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

	public static String getClassName(Exception ex) {
		try {
			StackTraceElement[] trace = ex.getStackTrace();
			if (trace != null) {
				if (trace.length > 0) {
					return trace[0].getClassName();
				}
			}
			return "";
		} catch (Exception e) {
		}
		return "";
	}
}
