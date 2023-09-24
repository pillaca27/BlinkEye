package net.royal.spring.framework.modelo.seguridad;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class SeguridadUsuarioLogin extends DominioTransaccion {
	public static final String CONSTRAINTS_NOTNULL = "usuarioactual.constraints.notnull";

	public static final String TIPOUSUARIO_EMPLEADO = "EMPL";
	public static final String TIPOUSUARIO_PROVEEDOR = "PROV";
	public static final String TIPOUSUARIO_CLIENTE = "CLIE";
	public static final String TIPOUSUARIO_POSTULANTE = "POST";
	public static final String TIPOUSUARIO_EXTERNO = "EXTR";

	private String sid;
	private String tipoUsuarioId;
	private String aplicacionCodigo;
	private String aplicacionNombre;
	private String companiaCodigo;
	private String companiaNombre;
	private String usuario;
	private String clave;
	private String token;
	private String direccionIp;

	private String flgCambiarClave;
	private String flgClaveDefault;

	public String getFlgCambiarClave() {
		return flgCambiarClave;
	}

	public void setFlgCambiarClave(String flgCambiarClave) {
		this.flgCambiarClave = flgCambiarClave;
	}

	public String getFlgClaveDefault() {
		return flgClaveDefault;
	}

	public void setFlgClaveDefault(String flgClaveDefault) {
		this.flgClaveDefault = flgClaveDefault;
	}

	public SeguridadUsuarioLogin() {
		this.setTransaccionEstado(DominioTransaccion.ERROR);
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getTipoUsuarioId() {
		return tipoUsuarioId;
	}

	public void setTipoUsuarioId(String tipoUsuarioId) {
		this.tipoUsuarioId = tipoUsuarioId;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getAplicacionCodigo() {
		return aplicacionCodigo;
	}

	public void setAplicacionCodigo(String aplicacionCodigo) {
		this.aplicacionCodigo = aplicacionCodigo;
	}

	public String getAplicacionNombre() {
		return aplicacionNombre;
	}

	public void setAplicacionNombre(String aplicacionNombre) {
		this.aplicacionNombre = aplicacionNombre;
	}

	public String getCompaniaCodigo() {
		return companiaCodigo;
	}

	public void setCompaniaCodigo(String companiaCodigo) {
		this.companiaCodigo = companiaCodigo;
	}

	public String getCompaniaNombre() {
		return companiaNombre;
	}

	public void setCompaniaNombre(String companiaNombre) {
		this.companiaNombre = companiaNombre;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDireccionIp() {
		return direccionIp;
	}

	public void setDireccionIp(String direccionIp) {
		this.direccionIp = direccionIp;
	}

}
