package net.royal.spring.rrhh.dominio.dto;

import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.rrhh.dominio.BeanHrEmpleadoCapacitacion;

public class DtoHrEmpleadoCapacitacion extends DominioTransaccion{

	private String capacitacion;
	private Integer empleado;
	private String companyOwner;
	private String ultimoUsuario;
	private Date ultimaFechaModif;

	private String auxEmpleadoNombre;

	public String getCapacitacion() {
		return capacitacion;
	}

	public void setCapacitacion(String capacitacion) {
		this.capacitacion = capacitacion;
	}

	public Integer getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Integer empleado) {
		this.empleado = empleado;
	}

	public String getCompanyOwner() {
		return companyOwner;
	}

	public void setCompanyOwner(String companyOwner) {
		this.companyOwner = companyOwner;
	}

	public String getUltimoUsuario() {
		return ultimoUsuario;
	}

	public void setUltimoUsuario(String ultimoUsuario) {
		this.ultimoUsuario = ultimoUsuario;
	}

	public Date getUltimaFechaModif() {
		return ultimaFechaModif;
	}

	public void setUltimaFechaModif(Date ultimaFechaModif) {
		this.ultimaFechaModif = ultimaFechaModif;
	}

	public String getAuxEmpleadoNombre() {
		return auxEmpleadoNombre;
	}

	public void setAuxEmpleadoNombre(String auxEmpleadoNombre) {
		this.auxEmpleadoNombre = auxEmpleadoNombre;
	}

	public BeanHrEmpleadoCapacitacion obtenerBean() {
		BeanHrEmpleadoCapacitacion bean = new BeanHrEmpleadoCapacitacion();
		bean.getPk().setCapacitacion(capacitacion);
		bean.getPk().setCompanyOwner(companyOwner);
		bean.getPk().setEmpleado(empleado);
		bean.setUltimaFechaModif(ultimaFechaModif);
		bean.setUltimoUsuario(ultimoUsuario);
		return bean;
	}

}
