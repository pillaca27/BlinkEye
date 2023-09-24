package net.royal.spring.rrhh.dominio;

import java.io.Serializable;

import javax.persistence.Column;

public class BeanHrEspecialidad2Pk implements Serializable{
	
	@Column(name = "Especialidad")
	private Integer especialidad;
	
	public BeanHrEspecialidad2Pk() {

	}
	
	public BeanHrEspecialidad2Pk(Integer especialidad) {
		this.especialidad = especialidad;
	}

	public Integer getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Integer especialidad) {
		this.especialidad = especialidad;
	}
	
}
