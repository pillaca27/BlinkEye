package net.royal.spring.framework.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioAdjunto;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;

public class WorkFlowSeguimiento extends DominioTransaccion {
	
	private String accion;
	private String subaccion;
	private String observaciones;
	private Integer nivelActual;
	
	private SeguridadUsuarioActual usuarioActual;
	
	private Integer transaccionId;
	private List<WorkFlowAdjunto> adjuntos;
	private List<WorkFlowAprobadorTransaccion> listaAprobador;
	
	public WorkFlowSeguimiento() {
		listaAprobador = new ArrayList<WorkFlowAprobadorTransaccion>();
	}
	
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getSubaccion() {
		return subaccion;
	}
	public void setSubaccion(String subaccion) {
		this.subaccion = subaccion;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Integer getTransaccionId() {
		return transaccionId;
	}
	public void setTransaccionId(Integer transaccionId) {
		this.transaccionId = transaccionId;
	}
	public List<WorkFlowAdjunto> getAdjuntos() {
		if (adjuntos==null)
			adjuntos=new ArrayList<WorkFlowAdjunto>();
		return adjuntos;
	}
	public void setAdjuntos(List<WorkFlowAdjunto> adjuntos) {
		this.adjuntos = adjuntos;
	}
	public SeguridadUsuarioActual getUsuarioActual() {
		return usuarioActual;
	}
	public void setUsuarioActual(SeguridadUsuarioActual usuarioActual) {
		this.usuarioActual = usuarioActual;
	}

	public List<WorkFlowAprobadorTransaccion> getListaAprobador() {
		return listaAprobador;
	}

	public void setListaAprobador(List<WorkFlowAprobadorTransaccion> listaAprobador) {
		this.listaAprobador = listaAprobador;
	}

	public Integer getNivelActual() {
		return nivelActual;
	}

	public void setNivelActual(Integer nivelActual) {
		this.nivelActual = nivelActual;
	}
	
}
