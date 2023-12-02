package net.royal.spring.workflow.dominio.dto;

import java.util.ArrayList;
import java.util.List;

public class DtoExportarWf {

	private List<DtoExportarWfFlujoAccion> wfFlujoAcciones;
	private List<DtoExportarWfFlujoAprobador> wfFlujoAprobadores;
	private List<DtoExportarWfFlujoConfiguracion> wfFlujoConfiguraciones;
	private List<DtoExportarWfFlujoNivel> wfFlujoNiveles;
	private List<DtoExportarWfFlujoNivelDocumento> wfFlujoNivelDocumentos;
	private List<DtoExportarWfProceso> wfProcesos;
	private List<DtoExportarWfProcesoDocumento> wfProcesoDocumentos;
	private List<DtoExportarWfProcesoEstado> wfProcesoEstados;
	private List<DtoExportarWfProcesoFlujo> wfProcesoFlujos;
	private List<DtoExportarWfProcesoVariable> wfProcesoVariables;
	private List<DtoExportarWfProcesoVersion> wfProcesoVersiones;
	private List<DtoExportarWfProcesoRol> wfProcesoRoles;

	public DtoExportarWf() {
		this.wfFlujoAcciones = new ArrayList<DtoExportarWfFlujoAccion>();
		this.wfFlujoAprobadores = new ArrayList<DtoExportarWfFlujoAprobador>();
		this.wfFlujoConfiguraciones = new ArrayList<DtoExportarWfFlujoConfiguracion>();
		this.wfFlujoNiveles = new ArrayList<DtoExportarWfFlujoNivel>();
		this.wfFlujoNivelDocumentos = new ArrayList<DtoExportarWfFlujoNivelDocumento>();
		this.wfProcesos = new ArrayList<DtoExportarWfProceso>();
		this.wfProcesoDocumentos = new ArrayList<DtoExportarWfProcesoDocumento>();
		this.wfProcesoEstados = new ArrayList<DtoExportarWfProcesoEstado>();
		this.wfProcesoFlujos = new ArrayList<DtoExportarWfProcesoFlujo>();
		this.wfProcesoVariables = new ArrayList<DtoExportarWfProcesoVariable>();
		this.wfProcesoVersiones = new ArrayList<DtoExportarWfProcesoVersion>();
		this.wfProcesoRoles = new ArrayList<DtoExportarWfProcesoRol>();
	}

	public List<DtoExportarWfFlujoAccion> getWfFlujoAcciones() {
		return wfFlujoAcciones;
	}

	public void setWfFlujoAcciones(List<DtoExportarWfFlujoAccion> wfFlujoAcciones) {
		this.wfFlujoAcciones = wfFlujoAcciones;
	}

	public List<DtoExportarWfFlujoAprobador> getWfFlujoAprobadores() {
		return wfFlujoAprobadores;
	}

	public void setWfFlujoAprobadores(List<DtoExportarWfFlujoAprobador> wfFlujoAprobadores) {
		this.wfFlujoAprobadores = wfFlujoAprobadores;
	}

	public List<DtoExportarWfFlujoConfiguracion> getWfFlujoConfiguraciones() {
		return wfFlujoConfiguraciones;
	}

	public void setWfFlujoConfiguraciones(List<DtoExportarWfFlujoConfiguracion> wfFlujoConfiguraciones) {
		this.wfFlujoConfiguraciones = wfFlujoConfiguraciones;
	}

	public List<DtoExportarWfFlujoNivel> getWfFlujoNiveles() {
		return wfFlujoNiveles;
	}

	public void setWfFlujoNiveles(List<DtoExportarWfFlujoNivel> wfFlujoNiveles) {
		this.wfFlujoNiveles = wfFlujoNiveles;
	}

	public List<DtoExportarWfFlujoNivelDocumento> getWfFlujoNivelDocumentos() {
		return wfFlujoNivelDocumentos;
	}

	public void setWfFlujoNivelDocumentos(List<DtoExportarWfFlujoNivelDocumento> wfFlujoNivelDocumentos) {
		this.wfFlujoNivelDocumentos = wfFlujoNivelDocumentos;
	}

	public List<DtoExportarWfProceso> getWfProcesos() {
		return wfProcesos;
	}

	public void setWfProcesos(List<DtoExportarWfProceso> wfProcesos) {
		this.wfProcesos = wfProcesos;
	}

	public List<DtoExportarWfProcesoDocumento> getWfProcesoDocumentos() {
		return wfProcesoDocumentos;
	}

	public void setWfProcesoDocumentos(List<DtoExportarWfProcesoDocumento> wfProcesoDocumentos) {
		this.wfProcesoDocumentos = wfProcesoDocumentos;
	}

	public List<DtoExportarWfProcesoEstado> getWfProcesoEstados() {
		return wfProcesoEstados;
	}

	public void setWfProcesoEstados(List<DtoExportarWfProcesoEstado> wfProcesoEstados) {
		this.wfProcesoEstados = wfProcesoEstados;
	}

	public List<DtoExportarWfProcesoFlujo> getWfProcesoFlujos() {
		return wfProcesoFlujos;
	}

	public void setWfProcesoFlujos(List<DtoExportarWfProcesoFlujo> wfProcesoFlujos) {
		this.wfProcesoFlujos = wfProcesoFlujos;
	}

	public List<DtoExportarWfProcesoVariable> getWfProcesoVariables() {
		return wfProcesoVariables;
	}

	public void setWfProcesoVariables(List<DtoExportarWfProcesoVariable> wfProcesoVariables) {
		this.wfProcesoVariables = wfProcesoVariables;
	}

	public List<DtoExportarWfProcesoVersion> getWfProcesoVersiones() {
		return wfProcesoVersiones;
	}

	public void setWfProcesoVersiones(List<DtoExportarWfProcesoVersion> wfProcesoVersiones) {
		this.wfProcesoVersiones = wfProcesoVersiones;
	}

	public List<DtoExportarWfProcesoRol> getWfProcesoRoles() {
		return wfProcesoRoles;
	}

	public void setWfProcesoRoles(List<DtoExportarWfProcesoRol> wfProcesoRoles) {
		this.wfProcesoRoles = wfProcesoRoles;
	}

}
