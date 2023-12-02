package net.royal.spring.seguridad.dominio.dto;

import java.util.ArrayList;
import java.util.List;

import net.royal.spring.seguridad.dominio.BeanSeguridadautorizaciones;
import net.royal.spring.seguridad.dominio.BeanSeguridadautorizacionreporte;
import net.royal.spring.seguridad.dominio.BeanSySeguridadautorizaciones;

public class DtoComunAutorizacion {
	private String usuario;
	private String aplicacioncodigo;
	
	private List<DtoComunAutorizacionFuncion> listaDtoFunciones;
	private List<DtoComunAutorizacionReporte> listaDtoReportes;
	private List<DtoComunAutorizacionConcepto> listaDtoConceptos;
	
	/**
	 * solo se usara en Backend
	 */
	private List<BeanSeguridadautorizaciones> lstFunciones;
	private List<BeanSySeguridadautorizaciones> lstConceptos;
	private List<BeanSeguridadautorizacionreporte> lstReportes;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}
	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	public List<DtoComunAutorizacionFuncion> getListaDtoFunciones() {
		if (listaDtoFunciones==null)
			listaDtoFunciones=new ArrayList<>();
		return listaDtoFunciones;
	}
	public void setListaDtoFunciones(List<DtoComunAutorizacionFuncion> listaDtoFunciones) {
		this.listaDtoFunciones = listaDtoFunciones;
	}
	public List<DtoComunAutorizacionReporte> getListaDtoReportes() {
		if (listaDtoReportes==null)
			listaDtoReportes=new ArrayList<>();
		return listaDtoReportes;
	}
	public void setListaDtoReportes(List<DtoComunAutorizacionReporte> listaDtoReportes) {
		this.listaDtoReportes = listaDtoReportes;
	}
	public List<DtoComunAutorizacionConcepto> getListaDtoConceptos() {
		if (listaDtoConceptos==null)
			listaDtoConceptos=new ArrayList<>();
		return listaDtoConceptos;
	}
	public void setListaDtoConceptos(List<DtoComunAutorizacionConcepto> listaDtoConceptos) {
		this.listaDtoConceptos = listaDtoConceptos;
	}
	public List<BeanSeguridadautorizaciones> getLstFunciones() {
		if (lstFunciones==null)
			lstFunciones=new ArrayList<>();
		return lstFunciones;
	}
	public void setLstFunciones(List<BeanSeguridadautorizaciones> lstFunciones) {
		this.lstFunciones = lstFunciones;
	}
	public List<BeanSySeguridadautorizaciones> getLstConceptos() {
		if (lstConceptos==null)
			lstConceptos=new ArrayList<>();
		return lstConceptos;
	}
	public void setLstConceptos(List<BeanSySeguridadautorizaciones> lstConceptos) {
		this.lstConceptos = lstConceptos;
	}
	public List<BeanSeguridadautorizacionreporte> getLstReportes() {
		if (lstReportes==null)
			lstReportes=new ArrayList<>();
		return lstReportes;
	}
	public void setLstReportes(List<BeanSeguridadautorizacionreporte> lstReportes) {
		this.lstReportes = lstReportes;
	}	
}
