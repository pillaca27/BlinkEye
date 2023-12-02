package net.royal.spring.core.dominio.dto;

import net.royal.spring.core.dominio.BeanRtpsEmpleadoEstablecimiento;
import net.royal.spring.core.dominio.BeanRtpsEmpleadoEstablecimientoExterno;
import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexterno;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

/**
 * 
 * 
 * @tabla dbo.RTPS_EMPLEADOESTABLECIMIENTO
*/
public class DtoComunRtpsEmpleadoEstablecimiento extends DominioTransaccion implements java.io.Serializable{
	
	private Integer empleado;
	private String companiasocio;
	private Integer codigoautomatico;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String accion = "UPDATE";

	public Integer getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Integer empleado) {
		this.empleado = empleado;
	}

	public String getCompaniasocio() {
		return companiasocio;
	}

	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}

	public Integer getCodigoautomatico() {
		return codigoautomatico;
	}

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
	
	public BeanRtpsEmpleadoEstablecimiento obtenerBean() {
		BeanRtpsEmpleadoEstablecimiento bean = new BeanRtpsEmpleadoEstablecimiento();
		return obtenerBean(bean);
	}

	public BeanRtpsEmpleadoEstablecimiento obtenerBean(BeanRtpsEmpleadoEstablecimiento bean) {
		if (bean == null)
			bean = new BeanRtpsEmpleadoEstablecimiento();

		bean.getPk().setCodigoautomatico(codigoautomatico);
		bean.getPk().setEmpleado(empleado);
		bean.getPk().setCompaniasocio(companiasocio);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);

		//bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		//bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
