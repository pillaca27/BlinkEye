package net.royal.spring.framework.core;

import java.util.ArrayList;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;

public class UExceptionSessionFinished extends Exception {
	public UExceptionSessionFinished(String mensaje) {
		super(mensaje);
	}

	public List<DominioMensajeUsuario> getErrors() {
		List<DominioMensajeUsuario> lista = new ArrayList<DominioMensajeUsuario>();
		lista.add(new DominioMensajeUsuario(tipo_mensaje.INFORMACION, getMessage()));
		return lista;
	}
}
