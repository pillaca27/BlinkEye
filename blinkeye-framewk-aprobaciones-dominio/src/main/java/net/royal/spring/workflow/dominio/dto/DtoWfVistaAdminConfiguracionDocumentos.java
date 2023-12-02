package net.royal.spring.workflow.dominio.dto;

public class DtoWfVistaAdminConfiguracionDocumentos {
	
	private Integer nivel;
	private String tipoAprobador;
	private String rolNombre;
	private String tipoDocumento;
	private String tipoDocumentoDescripcion;
	private String estado;
	private String flgRequerido;
	private String flgEditable;

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getTipoAprobador() {
		return tipoAprobador;
	}

	public void setTipoAprobador(String tipoAprobador) {
		this.tipoAprobador = tipoAprobador;
	}

	public String getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getTipoDocumentoDescripcion() {
		return tipoDocumentoDescripcion;
	}

	public void setTipoDocumentoDescripcion(String tipoDocumentoDescripcion) {
		this.tipoDocumentoDescripcion = tipoDocumentoDescripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFlgRequerido() {
		return flgRequerido;
	}

	public void setFlgRequerido(String flgRequerido) {
		this.flgRequerido = flgRequerido;
	}

	public String getFlgEditable() {
		return flgEditable;
	}

	public void setFlgEditable(String flgEditable) {
		this.flgEditable = flgEditable;
	}

}
