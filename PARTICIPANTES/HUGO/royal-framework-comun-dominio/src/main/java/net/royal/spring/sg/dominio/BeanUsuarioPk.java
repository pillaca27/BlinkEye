package net.royal.spring.sg.dominio;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BeanUsuarioPk  implements java.io.Serializable{
	@Size(min = 0, max = 20)
	@NotNull
	@NotEmpty
	@Column(name = "USUARIO", length = 20, nullable = false)
	private String usuario;

	public BeanUsuarioPk() {
	}

	public BeanUsuarioPk(String pusuario) {
		this.usuario = pusuario;
	}

	/**
	 * Codigo del Usuario
	 * 
	 * @campo USUARIO
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Codigo del Usuario
	 * 
	 * @campo USUARIO
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
