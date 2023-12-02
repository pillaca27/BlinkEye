package net.royal.spring.sistema.dominio.dto;

import net.royal.spring.sistema.dominio.BeanSyReporte;
import net.royal.spring.sistema.dominio.BeanSyReportearchivo;

public class DtoComunReporteConfiguracionResultado {
	private BeanSyReporte syReporte;
	private Boolean flgOk = Boolean.FALSE;
	private String rutaCompletaReporte;
	private byte[] archivo;
	private BeanSyReportearchivo syReportearchivo;

	public DtoComunReporteConfiguracionResultado() {
		flgOk = Boolean.FALSE;
	}


	public Boolean getFlgOk() {
		return flgOk;
	}

	public void setFlgOk(Boolean flgOk) {
		this.flgOk = flgOk;
	}

	public String getRutaCompletaReporte() {
		return rutaCompletaReporte;
	}

	public void setRutaCompletaReporte(String rutaCompletaReporte) {
		this.rutaCompletaReporte = rutaCompletaReporte;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}


	public BeanSyReporte getSyReporte() {
		return syReporte;
	}


	public void setSyReporte(BeanSyReporte syReporte) {
		this.syReporte = syReporte;
	}


	public BeanSyReportearchivo getSyReportearchivo() {
		return syReportearchivo;
	}


	public void setSyReportearchivo(BeanSyReportearchivo syReportearchivo) {
		this.syReportearchivo = syReportearchivo;
	}
}
