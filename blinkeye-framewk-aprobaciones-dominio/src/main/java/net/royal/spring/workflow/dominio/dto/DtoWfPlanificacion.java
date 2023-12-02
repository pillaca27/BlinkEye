package net.royal.spring.workflow.dominio.dto;

import java.util.ArrayList;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoWfPlanificacion extends DominioTransaccion {
	private Integer transaccion;
	private List<DtoWfPlanificacionEtapa> lstEtapas;
	private DtoWfPlanificacionConfiguracion config;

	public DtoWfPlanificacion() {
		this.lstEtapas = new ArrayList<DtoWfPlanificacionEtapa>();
		this.config = new DtoWfPlanificacionConfiguracion();
	}

	public List<DtoWfPlanificacionEtapa> getLstEtapas() {
		return lstEtapas;
	}

	public void setLstEtapas(List<DtoWfPlanificacionEtapa> lstEtapas) {
		this.lstEtapas = lstEtapas;
	}

	public DtoWfPlanificacionConfiguracion getConfig() {
		return config;
	}

	public void setConfig(DtoWfPlanificacionConfiguracion config) {
		this.config = config;
	}

	public Integer getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Integer transaccion) {
		this.transaccion = transaccion;
	}

}
