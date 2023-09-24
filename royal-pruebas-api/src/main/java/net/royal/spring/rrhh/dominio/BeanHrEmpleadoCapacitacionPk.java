package net.royal.spring.rrhh.dominio;

import java.io.Serializable;

import javax.persistence.Column;

public class BeanHrEmpleadoCapacitacionPk implements Serializable {

	@Column(name = "Capacitacion")
	private String capacitacion;

	@Column(name = "Empleado")
	private Integer empleado;

	@Column(name = "CompanyOwner")
	private String companyOwner;

	public BeanHrEmpleadoCapacitacionPk() {
	
	}

	public BeanHrEmpleadoCapacitacionPk(String capacitacion, Integer empleado, String companyOwner) {
		super();
		this.capacitacion = capacitacion;
		this.empleado = empleado;
		this.companyOwner = companyOwner;
	}

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

}
