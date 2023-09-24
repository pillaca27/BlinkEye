package net.royal.spring.gth.dto;

public class DtoHrRequerimientoListado {
	
	private String companyowner;
	private String requerimiento;
	private String solicitanteNombre;
	private String companiaNombre;
	private String puestoNombre;
	private String fechaSolicitud;

	public String getCompanyowner() {
		return companyowner;
	}

	public void setCompanyowner(String companyowner) {
		this.companyowner = companyowner;
	}

	public String getRequerimiento() {
		return requerimiento;
	}

	public void setRequerimiento(String requerimiento) {
		this.requerimiento = requerimiento;
	}

	public String getSolicitanteNombre() {
		return solicitanteNombre;
	}

	public void setSolicitanteNombre(String solicitanteNombre) {
		this.solicitanteNombre = solicitanteNombre;
	}

	public String getCompaniaNombre() {
		return companiaNombre;
	}

	public void setCompaniaNombre(String companiaNombre) {
		this.companiaNombre = companiaNombre;
	}

	public String getPuestoNombre() {
		return puestoNombre;
	}

	public void setPuestoNombre(String puestoNombre) {
		this.puestoNombre = puestoNombre;
	}

	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

}
