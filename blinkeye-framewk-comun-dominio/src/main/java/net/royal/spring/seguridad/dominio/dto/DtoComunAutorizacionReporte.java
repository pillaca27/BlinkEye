package net.royal.spring.seguridad.dominio.dto;

import java.util.Date;

import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.seguridad.dominio.BeanSeguridadautorizacionreporte;

public class DtoComunAutorizacionReporte {
	private String seleccionar;
	private String reportecodigo;

	public String getSeleccionar() {
		return seleccionar;
	}

	public void setSeleccionar(String seleccionar) {
		this.seleccionar = seleccionar;
	}

	public String getReportecodigo() {
		return reportecodigo;
	}

	public void setReportecodigo(String reportecodigo) {
		this.reportecodigo = reportecodigo;
	}

	public BeanSeguridadautorizacionreporte obtenerBean(SeguridadUsuarioActual usuarioActual, DtoComunAutorizacion dtoAutorizacion) {
		BeanSeguridadautorizacionreporte bean = new BeanSeguridadautorizacionreporte();

		bean.getPk().setAplicacioncodigo(dtoAutorizacion.getAplicacioncodigo());
		bean.getPk().setUsuario(dtoAutorizacion.getUsuario());

		bean.getPk().setReportecodigo(reportecodigo);
		bean.setDisponible(seleccionar);

		bean.setUltimousuario(usuarioActual.getUsuario());
		bean.setUltimafechamodif(new Date());

		return bean;
	}
}
