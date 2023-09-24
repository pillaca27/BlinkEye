package net.royal.spring.rrhh.dominio;

import java.io.Serializable;

import javax.persistence.Column;

public class BeanHrCapacitacionPk implements Serializable {

	@Column(name = "Capacitacion")
	private String capacitacion;

	@Column(name = "CompanyOwner")
	private String companyOwner;

	public BeanHrCapacitacionPk() {
		super();
	}

	public BeanHrCapacitacionPk(String companyOwner, String capacitacion) {
		super();
		this.capacitacion = capacitacion;
		this.companyOwner = companyOwner;
	}

	public String getCapacitacion() {
		return capacitacion;
	}

	public void setCapacitacion(String capacitacion) {
		this.capacitacion = capacitacion;
	}

	public String getCompanyOwner() {
		return companyOwner;
	}

	public void setCompanyOwner(String companyOwner) {
		this.companyOwner = companyOwner;
	}

}
