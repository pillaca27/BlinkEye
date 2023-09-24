package net.royal.spring.framework.modelo;

import java.util.ArrayList;
import java.util.List;

import net.royal.spring.framework.modelo.correo.EmailDestino;
import net.royal.spring.framework.modelo.generico.DominioAdjunto;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class WorkFlowEnvioCorreo extends DominioTransaccion {
	
	/*
	 * PARAMETROS PARA EL REPORTE
	 * */
	private String aplicacionCodigo;
	private String reporteCodigo;
	private String companiaSocio;
	private String periodo;
	private String version;
	
	private List<WorkFlowEnvioCorreoDetalle> listaCorreoDetalle;
	private List<EmailDestino> listaCorreoDestino;
	private List<DominioAdjunto> listaCorreoAdjunto;
	
	
	public String getAplicacionCodigo() {
		return aplicacionCodigo;
	}
	public void setAplicacionCodigo(String aplicacionCodigo) {
		this.aplicacionCodigo = aplicacionCodigo;
	}
	public String getReporteCodigo() {
		return reporteCodigo;
	}
	public void setReporteCodigo(String reporteCodigo) {
		this.reporteCodigo = reporteCodigo;
	}
	public String getCompaniaSocio() {
		return companiaSocio;
	}
	public void setCompaniaSocio(String companiaSocio) {
		this.companiaSocio = companiaSocio;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public List<WorkFlowEnvioCorreoDetalle> getListaCorreoDetalle() {
		if (listaCorreoDetalle==null)
			listaCorreoDetalle=new ArrayList<WorkFlowEnvioCorreoDetalle>();
		return listaCorreoDetalle;
	}
	public void setListaCorreoDetalle(List<WorkFlowEnvioCorreoDetalle> listaCorreoDetalle) {
		this.listaCorreoDetalle = listaCorreoDetalle;
	}
	public List<DominioAdjunto> getListaCorreoAdjunto() {
		if (listaCorreoAdjunto==null)
			listaCorreoAdjunto=new ArrayList<DominioAdjunto>();
		return listaCorreoAdjunto;
	}
	public void setListaCorreoAdjunto(List<DominioAdjunto> listaCorreoAdjunto) {
		this.listaCorreoAdjunto = listaCorreoAdjunto;
	}
	public List<EmailDestino> getListaCorreoDestino() {
		if (listaCorreoDestino==null)
			listaCorreoDestino=new ArrayList<EmailDestino>();
		return listaCorreoDestino;
	}
	public void setListaCorreoDestino(List<EmailDestino> listaCorreoDestino) {
		this.listaCorreoDestino = listaCorreoDestino;
	}
	
	
}
