package net.royal.spring.workflow.dominio.dto;

import java.util.ArrayList;
import java.util.List;

public class DtoAprobacionAcciones {

	private Integer nivelActual;
	private String puedeAprobar;
	private String puedeRechazar;
	private String puedeDevolver;
	private String puedeSaltar;

	private List<DtoAprobacionAccion> botonesaprobar;
	private List<DtoAprobacionAccion> botonesrechazar;
	private List<DtoAprobacionAccion> botonesdevolver;
	private List<DtoWfSaltoNivel> lstSaltos;

	public DtoAprobacionAcciones() {
		botonesaprobar = new ArrayList<DtoAprobacionAccion>();
		botonesrechazar = new ArrayList<DtoAprobacionAccion>();
		botonesdevolver = new ArrayList<DtoAprobacionAccion>();
		lstSaltos= new ArrayList<DtoWfSaltoNivel>();
	}

	public String getPuedeAprobar() {
		return puedeAprobar;
	}

	public void setPuedeAprobar(String puedeAprobar) {
		this.puedeAprobar = puedeAprobar;
	}

	public String getPuedeRechazar() {
		return puedeRechazar;
	}

	public void setPuedeRechazar(String puedeRechazar) {
		this.puedeRechazar = puedeRechazar;
	}

	public String getPuedeDevolver() {
		return puedeDevolver;
	}

	public void setPuedeDevolver(String puedeDevolver) {
		this.puedeDevolver = puedeDevolver;
	}

	public List<DtoAprobacionAccion> getBotonesaprobar() {
		return botonesaprobar;
	}

	public void setBotonesaprobar(List<DtoAprobacionAccion> botonesaprobar) {
		this.botonesaprobar = botonesaprobar;
	}

	public List<DtoAprobacionAccion> getBotonesrechazar() {
		return botonesrechazar;
	}

	public void setBotonesrechazar(List<DtoAprobacionAccion> botonesrechazar) {
		this.botonesrechazar = botonesrechazar;
	}

	public List<DtoAprobacionAccion> getBotonesdevolver() {
		return botonesdevolver;
	}

	public void setBotonesdevolver(List<DtoAprobacionAccion> botonesdevolver) {
		this.botonesdevolver = botonesdevolver;
	}

	public Integer getNivelActual() {
		return nivelActual;
	}

	public void setNivelActual(Integer nivelActual) {
		this.nivelActual = nivelActual;
	}

	public String getPuedeSaltar() {
		return puedeSaltar;
	}

	public void setPuedeSaltar(String puedeSaltar) {
		this.puedeSaltar = puedeSaltar;
	}

	public List<DtoWfSaltoNivel> getLstSaltos() {
		return lstSaltos;
	}

	public void setLstSaltos(List<DtoWfSaltoNivel> lstSaltos) {
		this.lstSaltos = lstSaltos;
	}

}
