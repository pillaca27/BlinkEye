package net.royal.spring.workflow.dominio.dto;

import java.util.Date;

public class DtoWfPlanificacionEtapa {
	private Integer planificacionid;
	private Integer nivel;
	private String nombre;
	private Date inicio;
	private Date fin;

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public Integer getPlanificacionid() {
		return planificacionid;
	}

	public void setPlanificacionid(Integer planificacionid) {
		this.planificacionid = planificacionid;
	}

}
