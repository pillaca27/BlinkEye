package net.royal.spring.seguridad.dominio.dto;

import java.math.BigDecimal;

public class DtoEmpleadoSeguridad implements java.io.Serializable {

	private static final long serialVersionUID = -7319192864398648944L;
	private Integer idEmpleado;
	private String idEstado;
	private String idEstadoEmpleado;
	private String codigoUsuario;
	private String idCompaniaSocio;
	private String work;
	private String usuarioClave;
	private String correoEmpleado;
	private String personaNombreCompleto;
	private String valuehsh;

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}

	public String getIdEstadoEmpleado() {
		return idEstadoEmpleado;
	}

	public void setIdEstadoEmpleado(String idEstadoEmpleado) {
		this.idEstadoEmpleado = idEstadoEmpleado;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getIdCompaniaSocio() {
		return idCompaniaSocio;
	}

	public void setIdCompaniaSocio(String idCompaniaSocio) {
		this.idCompaniaSocio = idCompaniaSocio;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getUsuarioClave() {
		return usuarioClave;
	}

	public void setUsuarioClave(String usuarioClave) {
		this.usuarioClave = usuarioClave;
	}

	public String getCorreoEmpleado() {
		return correoEmpleado;
	}

	public void setCorreoEmpleado(String correoEmpleado) {
		this.correoEmpleado = correoEmpleado;
	}

	public String getPersonaNombreCompleto() {
		return personaNombreCompleto;
	}

	public void setPersonaNombreCompleto(String personaNombreCompleto) {
		this.personaNombreCompleto = personaNombreCompleto;
	}

	public String getValuehsh() {
		return valuehsh;
	}

	public void setValuehsh(String valuehsh) {
		this.valuehsh = valuehsh;
	}

}
