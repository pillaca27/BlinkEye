package net.royal.spring.framework.modelo.generico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.util.UBoolean;
import net.royal.spring.framework.util.UString;

public class DominioTransaccion {
	public static final String VALIDACION = "VA";
	public static final String PROCESS = "PRO";
	public static final String ERROR = "ER";
	public static final String OK = "OK";
	public static final String NO_ENCONTRADO = "NF";
	
	public enum EstadoRegistro
	{
	    Ninguno,	//*** existe registro 
	    Registrar, 	// es nuevo
	    Actualizado, // se actualizo
	    Eliminado, 	 // fue eliminado
	    Temporal;	// es solo temporal no se debe considerar
	}
	
	@Transient
	private String transaccionEstado = "OK";

	@Transient
	private List<DominioMensajeUsuario> transaccionListaMensajes = new ArrayList<>();

	@Transient
	private String auxFlgPreparado = "N";

	@Transient
	private String auxFlgValidado = "N";

	/*accion sobre el registro*/
	@Transient
	private String auxAccion = "";
	
	/*cuando el registro es nuevo (detalle)*/
	@Transient
	private String auxFlgNuevo;
	
	/*cuando el registro es modificado (detalle)*/
	@Transient
	private String auxFlgEditar;
	
	/*cuando el registro es eliminado (detalle)*/
	@Transient
	private String auxFlgEliminar;
	
	@Transient
	private Date transaccionFechaHora;
	
	@Transient
	private EstadoRegistro auxEstadoRegistro = EstadoRegistro.Ninguno;
	
	public DominioTransaccion() {
		transaccionListaMensajes = new ArrayList();
		this.auxFlgNuevo = "N";
		this.auxFlgEditar = "N";
		this.auxFlgEliminar = "N";
	}
	
	public DominioTransaccion(String estado) {
		transaccionListaMensajes = new ArrayList();
		transaccionEstado=estado;
		this.auxFlgNuevo = "N";
		this.auxFlgEditar = "N";
		this.auxFlgEliminar = "N";
	}

	public String getTransaccionEstado() {
		return transaccionEstado;
	}

	public void setTransaccionEstado(String transaccionEstado) {
		this.transaccionEstado = transaccionEstado;
	}

	public List<DominioMensajeUsuario> getTransaccionListaMensajes() {
		if (transaccionListaMensajes == null)
			transaccionListaMensajes = new ArrayList();
		return transaccionListaMensajes;
	}

	public void setTransaccionListaMensajes(List<DominioMensajeUsuario> transaccionListaMensajes) {
		this.transaccionListaMensajes = transaccionListaMensajes;
	}

	public Boolean getAuxFlgPreparadoBoolean() {
		return UBoolean.validarFlag(auxFlgPreparado);
	}

	public void setAuxFlgPreparadoBoolean(Boolean flgPreparado) {
		if (flgPreparado)
			auxFlgPreparado = "S";
		else
			auxFlgPreparado = "N";
	}

	public String getAuxFlgPreparado() {
		if (UString.esNuloVacio(auxFlgPreparado))
			auxFlgPreparado = "N";
		return auxFlgPreparado;
	}

	public void setAuxFlgPreparado(String auxFlgPreparado) {
		this.auxFlgPreparado = auxFlgPreparado;
	}

	/****/
	public Boolean getAuxFlgValidadoBoolean() {
		return UBoolean.validarFlag(auxFlgValidado);
	}

	public void setAuxFlgValidadoBoolean(Boolean flgValidado) {
		if (flgValidado)
			auxFlgValidado = "S";
		else
			auxFlgValidado = "N";
	}

	public String getAuxFlgValidado() {
		if (UString.esNuloVacio(auxFlgValidado))
			auxFlgValidado = "N";
		return auxFlgValidado;
	}

	public void setAuxFlgValidado(String auxFlgValidado) {
		this.auxFlgValidado = auxFlgValidado;
	}

	public String getAuxAccion() {
		return auxAccion;
	}

	public void setAuxAccion(String auxAccion) {
		this.auxAccion = auxAccion;
	}

	public String getAuxFlgNuevo() {
		if (UString.esNuloVacio(auxFlgNuevo))
			auxFlgNuevo = "N";
		return auxFlgNuevo;
	}

	public void setAuxFlgNuevo(String auxFlgNuevo) {
		this.auxFlgNuevo = auxFlgNuevo;
	}

	public String getAuxFlgEditar() {
		if (UString.esNuloVacio(auxFlgEditar))
			auxFlgEditar = "N";
		return auxFlgEditar;
	}

	public void setAuxFlgEditar(String auxFlgEditar) {
		this.auxFlgEditar = auxFlgEditar;
	}

	public String getAuxFlgEliminar() {
		if (UString.esNuloVacio(auxFlgEliminar))
			auxFlgEliminar = "N";
		return auxFlgEliminar;
	}

	public void setAuxFlgEliminar(String auxFlgEliminar) {
		this.auxFlgEliminar = auxFlgEliminar;
	}

	
	
	public EstadoRegistro getAuxEstadoRegistro() {
		return auxEstadoRegistro;
	}

	public void setAuxEstadoRegistro(EstadoRegistro auxEstadoRegistro) {
		this.auxEstadoRegistro = auxEstadoRegistro;
	}

	public String getTransaccionMensajesCadena() {
		String ret="";
		if (transaccionListaMensajes == null)
			transaccionListaMensajes = new ArrayList();
		for (DominioMensajeUsuario d : transaccionListaMensajes) {
			ret = ret + d.getMensaje();
		}
		return ret;
	}
	
	public void setTransaccionMensajesCadena(String cadena) {
	
	}
	public void evaluarEstadoRegistro() {
		this.auxEstadoRegistro=auxEstadoRegistro.Ninguno;
		if (UString.esNuloVacio(auxFlgEliminar))
			auxFlgEliminar = "N";
		if (UString.esNuloVacio(auxFlgNuevo))
			auxFlgNuevo = "N";
		if (UString.esNuloVacio(auxFlgEditar))
			auxFlgEditar = "N";
		
		if (auxFlgNuevo.equals("S") && auxFlgEliminar.equals("N") ) {
			this.auxEstadoRegistro=auxEstadoRegistro.Registrar;
		}else if(auxFlgEditar.equals("S") && auxFlgEliminar.equals("N") && auxFlgNuevo.equals("N") ) {
			this.auxEstadoRegistro=auxEstadoRegistro.Actualizado;
		}else if(auxFlgEliminar.equals("S")) {
			this.auxEstadoRegistro=auxEstadoRegistro.Eliminado;
		}
		
	}

	public Date getTransaccionFechaHora() {
		return transaccionFechaHora;
	}

	public void setTransaccionFechaHora(Date transaccionFechaHora) {
		this.transaccionFechaHora = transaccionFechaHora;
	}
	
	public void asignarTransaccionMensajesCadena(String cadena) {
		if (transaccionListaMensajes == null)
			transaccionListaMensajes = new ArrayList();
		transaccionListaMensajes.clear();
		transaccionListaMensajes.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, cadena));
	}
}
