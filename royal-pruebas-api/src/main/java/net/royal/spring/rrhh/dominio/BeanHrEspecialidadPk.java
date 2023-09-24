package net.royal.spring.rrhh.dominio;

import java.io.Serializable;

import javax.persistence.Column;

public class BeanHrEspecialidadPk implements Serializable{
	
	@Column(name = "Especialidad")
	private int especialidad;
	
	public BeanHrEspecialidadPk() {
		super();
	}
	
	public BeanHrEspecialidadPk(int especialidad) {
		super();
		this.especialidad = especialidad;
	}

	public int getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(int especialidad) {
		this.especialidad = especialidad;
	}
	
}