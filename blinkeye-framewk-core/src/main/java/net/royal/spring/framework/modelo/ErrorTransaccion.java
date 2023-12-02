package net.royal.spring.framework.modelo;

import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class ErrorTransaccion extends DominioTransaccion {
	
	private Integer idReglaNegocio;
	private Integer idCorreo;
	private Integer idLogAlerta;
	private Integer idAlerta;

	private String proceso;
	private String descripcionError;
	private String dominioMensajeUsuario;
	private String estado;
	private String className;
	private String objetoBasedatos;
	
	public ErrorTransaccion() {}
	public ErrorTransaccion(String proceso, String descripcionError,String className) {
		this.proceso=proceso;
		this.descripcionError=descripcionError;
		this.className=className;
	}
	public ErrorTransaccion(Exception e) {
		this.dominioMensajeUsuario=e.getMessage();
		this.descripcionError=UException.getStackTrace(e);
	}

	public Integer getIdReglaNegocio() {
		return idReglaNegocio;
	}

	public void setIdReglaNegocio(Integer idReglaNegocio) {
		this.idReglaNegocio = idReglaNegocio;
	}

	public Integer getIdCorreo() {
		return idCorreo;
	}

	public void setIdCorreo(Integer idCorreo) {
		this.idCorreo = idCorreo;
	}

	public Integer getIdLogAlerta() {
		return idLogAlerta;
	}

	public void setIdLogAlerta(Integer idLogAlerta) {
		this.idLogAlerta = idLogAlerta;
	}

	public Integer getIdAlerta() {
		return idAlerta;
	}

	public void setIdAlerta(Integer idAlerta) {
		this.idAlerta = idAlerta;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getDescripcionError() {
		return descripcionError;
	}

	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	public String getDominioMensajeUsuario() {
		return dominioMensajeUsuario;
	}

	public void setDominioMensajeUsuario(String dominioMensajeUsuario) {
		this.dominioMensajeUsuario = dominioMensajeUsuario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getObjetoBasedatos() {
		return objetoBasedatos;
	}

	public void setObjetoBasedatos(String objetoBasedatos) {
		this.objetoBasedatos = objetoBasedatos;
	}

}
