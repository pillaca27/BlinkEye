package net.royal.spring.workflow.dominio.dto;

import java.math.BigDecimal;

public class DtoWfFlujoNivelAccion {

	private String proceso;
	private Integer flujo;
	private Integer nivel;
	private Integer accion;
	private String nombre;
	private String estado;
	private String accionwf;
	private String subaccion;
	private Integer nivelDestinoId;

	public Integer getFlujo() {
		return flujo;
	}

	public void setFlujo(Integer flujo) {
		this.flujo = flujo;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public Integer getAccion() {
		return accion;
	}

	public void setAccion(Integer accion) {
		this.accion = accion;
	}

	public String getAccionwf() {
		return accionwf;
	}

	public void setAccionwf(String accionwf) {
		this.accionwf = accionwf;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getSubaccion() {
		return subaccion;
	}

	public void setSubaccion(String subaccion) {
		this.subaccion = subaccion;
	}

	public Integer getNivelDestinoId() {
		return nivelDestinoId;
	}

	public void setNivelDestinoId(Integer nivelDestinoId) {
		this.nivelDestinoId = nivelDestinoId;
	}

}
