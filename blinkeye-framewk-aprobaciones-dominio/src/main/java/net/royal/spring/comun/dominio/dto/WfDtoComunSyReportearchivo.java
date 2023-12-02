package net.royal.spring.comun.dominio.dto;

import net.royal.spring.comun.dominio.WfBeanSyReportearchivo;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class WfDtoComunSyReportearchivo  extends DominioTransaccion implements java.io.Serializable{
	private String aplicacioncodigo;
	private String reportecodigo;
	private String companiasocio;
	private String periodo;
	private String version;
	private String nombre;
	private String nombreReporte;
	private byte[] reporte;
	private String rutacompleta;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	
	private String auxString;	
	private String aplicacionDescripcion;
	private String asunto;
	private String asuntoPrincipal;

	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	public String getReportecodigo() {
		return reportecodigo;
	}

	public void setReportecodigo(String reportecodigo) {
		this.reportecodigo = reportecodigo;
	}
	public String getCompaniasocio() {
		return companiasocio;
	}

	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
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
	public byte[] getReporte() {
		return reporte;
	}

	public void setReporte(byte[] reporte) {
		this.reporte = reporte;
	}
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUltimousuario() {
		return ultimousuario;
	}

	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	public String getRutacompleta() {
		return rutacompleta;
	}

	public void setRutacompleta(String rutacompleta) {
		this.rutacompleta = rutacompleta;
	}
	private DominioPaginacion paginacion;

	public DominioPaginacion getPaginacion() {
		if (paginacion == null)
			paginacion = new DominioPaginacion();
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public WfBeanSyReportearchivo obtenerBean() {
		WfBeanSyReportearchivo bean = new WfBeanSyReportearchivo();
		return obtenerBean(bean);
	}

	public WfBeanSyReportearchivo obtenerBean(WfBeanSyReportearchivo bean) {
		if (bean == null)
			bean = new WfBeanSyReportearchivo();

		bean.getPk().setAplicacioncodigo(aplicacioncodigo);
		bean.getPk().setReportecodigo(reportecodigo);
		bean.getPk().setCompaniasocio(companiasocio);
		bean.getPk().setPeriodo(periodo);
		bean.getPk().setVersion(version);
		bean.setReporte(reporte);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);
		bean.setNombre(nombre);
		//bean.setRutacompleta(rutacompleta);	
		bean.setAsunto(asunto);
		bean.setAuxString(auxString);			
		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

	public String getAplicacionDescripcion() {
		return aplicacionDescripcion;
	}

	public void setAplicacionDescripcion(String aplicacionDescripcion) {
		this.aplicacionDescripcion = aplicacionDescripcion;
	}

	public String getAuxString() {
		return auxString;
	}

	public void setAuxString(String auxString) {
		this.auxString = auxString;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getAsuntoPrincipal() {
		return asuntoPrincipal;
	}

	public void setAsuntoPrincipal(String asuntoPrincipal) {
		this.asuntoPrincipal = asuntoPrincipal;
	}

	public String getNombreReporte() {
		return nombreReporte;
	}

	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}
	
}
