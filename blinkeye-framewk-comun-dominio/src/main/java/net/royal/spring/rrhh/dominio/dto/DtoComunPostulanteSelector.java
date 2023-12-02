package net.royal.spring.rrhh.dominio.dto;

public class DtoComunPostulanteSelector {
	private Integer postulante;
	private String nombre;
	private String tipoDocumentoNombre;
	private String documento;
	private String estadoNombre;

	public Integer getPostulante() {
		return postulante;
	}

	public void setPostulante(Integer postulante) {
		this.postulante = postulante;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoDocumentoNombre() {
		return tipoDocumentoNombre;
	}

	public void setTipoDocumentoNombre(String tipoDocumentoNombre) {
		this.tipoDocumentoNombre = tipoDocumentoNombre;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEstadoNombre() {
		return estadoNombre;
	}

	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}

}
