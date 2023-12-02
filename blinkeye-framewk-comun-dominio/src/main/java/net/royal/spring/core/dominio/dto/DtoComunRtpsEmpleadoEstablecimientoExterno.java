package net.royal.spring.core.dominio.dto;

import net.royal.spring.core.dominio.BeanRtpsEmpleadoEstablecimientoExterno;
import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexterno;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

/**
 * 
 * 
 * @tabla dbo.RTPS_EmpleadoEstablecimientoEx
*/
public class DtoComunRtpsEmpleadoEstablecimientoExterno extends DominioTransaccion implements java.io.Serializable{
	
	private Integer empleado;
	private String companiasocio;
	private Integer codigoautomatico;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String accion = "UPDATE";

	/**
	 * 
	 * 
	 * @campo Empleado
	*/
	public Integer getEmpleado() {
		return empleado;
	}
	/**
	 * 
	 * 
	 * @campo Empleado
	*/
	public void setEmpleado(Integer empleado) {
		this.empleado = empleado;
	}
	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	*/
	public String getCompaniasocio() {
		return companiasocio;
	}
	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	*/
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}
	/**
	 * 
	 * 
	 * @campo CodigoAutomatico
	*/
	public Integer getCodigoautomatico() {
		return codigoautomatico;
	}
	/**
	 * 
	 * 
	 * @campo CodigoAutomatico
	*/
	public void setCodigoautomatico(Integer codigoautomatico) {
		this.codigoautomatico = codigoautomatico;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUltimousuario() {
		return ultimousuario;
	}

	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}

	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	public BeanRtpsEmpleadoEstablecimientoExterno obtenerBean() {
		BeanRtpsEmpleadoEstablecimientoExterno bean = new BeanRtpsEmpleadoEstablecimientoExterno();
		return obtenerBean(bean);
	}

	public BeanRtpsEmpleadoEstablecimientoExterno obtenerBean(BeanRtpsEmpleadoEstablecimientoExterno bean) {
		if (bean == null)
			bean = new BeanRtpsEmpleadoEstablecimientoExterno();

		bean.getPk().setCodigoautomatico(codigoautomatico);
		bean.getPk().setCompaniasocio(companiasocio);
		bean.getPk().setEmpleado(empleado);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);

		//bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		//bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
