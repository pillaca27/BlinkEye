package net.royal.spring.workflow.dominio;

import javax.persistence.Column;

public class SyDocumentoAnexosPk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CompaniaSocio")
	private String companiaSocio;

	@Column(name = "Modulo")
	private String modulo;

	@Column(name = "TipoDocumento")
	private String tipoDocumento;

	@Column(name = "NumeroDocumento")
	private String numeroDocumento;

	@Column(name = "Linea")
	private Integer linea;

	@Column(name = "Secuencia")
	private Integer secuencia;

	public SyDocumentoAnexosPk() {

	}

	public String getCompaniaSocio() {
		return companiaSocio;
	}

	public void setCompaniaSocio(String companiaSocio) {
		this.companiaSocio = companiaSocio;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Integer getLinea() {
		return linea;
	}

	public void setLinea(Integer linea) {
		this.linea = linea;
	}

	public Integer getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}

}
