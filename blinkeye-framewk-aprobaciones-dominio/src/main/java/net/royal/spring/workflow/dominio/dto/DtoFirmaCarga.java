package net.royal.spring.workflow.dominio.dto;

public class DtoFirmaCarga {
	private Integer idFirma;
	private String token;
	private String urlConsulta;
	private String urlConfirmacion;

	public DtoFirmaCarga() {
		super();
	}

	public DtoFirmaCarga(Integer idFirma, String token, String urlConsulta, String urlConfirmacion) {
		super();
		this.idFirma = idFirma;
		this.token = token;
		this.urlConsulta = urlConsulta;
		this.urlConfirmacion = urlConfirmacion;
	}

	public String getUrlConsulta() {
		return urlConsulta;
	}

	public void setUrlConsulta(String urlConsulta) {
		this.urlConsulta = urlConsulta;
	}

	public String getUrlConfirmacion() {
		return urlConfirmacion;
	}

	public void setUrlConfirmacion(String urlConfirmacion) {
		this.urlConfirmacion = urlConfirmacion;
	}

	public Integer getIdFirma() {
		return idFirma;
	}

	public void setIdFirma(Integer idFirma) {
		this.idFirma = idFirma;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
