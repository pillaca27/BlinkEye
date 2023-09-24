package net.royal.spring.framework.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioAdjunto;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class WorkFlowProceso extends DominioTransaccion {
	private String procesoId;
	private String nombre;
	private Integer administradorId;
	private String nivelEstadoIdInicial;
	
	public WorkFlowProceso() {}
	public WorkFlowProceso(String procesoId) {
		this.procesoId=procesoId;
	}
	
	public String getProcesoId() {
		return procesoId;
	}
	public void setProcesoId(String procesoId) {
		this.procesoId = procesoId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getAdministradorId() {
		return administradorId;
	}
	public void setAdministradorId(Integer administradorId) {
		this.administradorId = administradorId;
	}
	public String getNivelEstadoIdInicial() {
		return nivelEstadoIdInicial;
	}
	public void setNivelEstadoIdInicial(String nivelEstadoIdInicial) {
		this.nivelEstadoIdInicial = nivelEstadoIdInicial;
	}	
}
