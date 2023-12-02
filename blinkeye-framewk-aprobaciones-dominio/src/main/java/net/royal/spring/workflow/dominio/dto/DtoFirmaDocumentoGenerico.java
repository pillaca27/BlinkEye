package net.royal.spring.workflow.dominio.dto;

public class DtoFirmaDocumentoGenerico {
	
	private String rutaAdjunto;
	private String pkAdjuntoString;
	private Integer nroFirma;
	private String tabla;
	private String tablaPk;

	public String getRutaAdjunto() {
		return rutaAdjunto;
	}

	public void setRutaAdjunto(String rutaAdjunto) {
		this.rutaAdjunto = rutaAdjunto;
	}

	public String getPkAdjuntoString() {
		return pkAdjuntoString;
	}

	public void setPkAdjuntoString(String pkAdjuntoString) {
		this.pkAdjuntoString = pkAdjuntoString;
	}

	public Integer getNroFirma() {
		return nroFirma;
	}

	public void setNroFirma(Integer nroFirma) {
		this.nroFirma = nroFirma;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public String getTablaPk() {
		return tablaPk;
	}

	public void setTablaPk(String tablaPk) {
		this.tablaPk = tablaPk;
	}

}
