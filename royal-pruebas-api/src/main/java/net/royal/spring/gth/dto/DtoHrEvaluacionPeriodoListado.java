package net.royal.spring.gth.dto;

public class DtoHrEvaluacionPeriodoListado {

	private Integer secuenciaEval;
	private String companyowner;
	private String companiaNombre;
	private String descripcion;
	private String fechaVigencia;

	public Integer getSecuenciaEval() {
		return secuenciaEval;
	}

	public void setSecuenciaEval(Integer secuenciaEval) {
		this.secuenciaEval = secuenciaEval;
	}

	public String getCompanyowner() {
		return companyowner;
	}

	public void setCompanyowner(String companyowner) {
		this.companyowner = companyowner;
	}

	public String getCompaniaNombre() {
		return companiaNombre;
	}

	public void setCompaniaNombre(String companiaNombre) {
		this.companiaNombre = companiaNombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFechaVigencia() {
		return fechaVigencia;
	}

	public void setFechaVigencia(String fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

}
