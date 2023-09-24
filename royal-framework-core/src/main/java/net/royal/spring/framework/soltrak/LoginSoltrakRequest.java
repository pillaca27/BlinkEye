package net.royal.spring.framework.soltrak;

public class LoginSoltrakRequest {
	private String aplicacionCodigo;
	private String companiaCodigo;
	private String usuarioLogin;
	private String usuarioClave;

	public String getAplicacionCodigo() {
		return aplicacionCodigo;
	}

	public void setAplicacionCodigo(String aplicacionCodigo) {
		this.aplicacionCodigo = aplicacionCodigo;
	}

	public String getCompaniaCodigo() {
		return companiaCodigo;
	}

	public void setCompaniaCodigo(String companiaCodigo) {
		this.companiaCodigo = companiaCodigo;
	}

	public String getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public String getUsuarioClave() {
		return usuarioClave;
	}

	public void setUsuarioClave(String usuarioClave) {
		this.usuarioClave = usuarioClave;
	}
}
