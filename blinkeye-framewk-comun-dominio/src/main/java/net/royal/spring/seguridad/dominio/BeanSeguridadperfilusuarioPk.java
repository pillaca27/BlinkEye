package net.royal.spring.seguridad.dominio;


import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Seguridad de perfil de usuario
 * 
 * @tabla SGAGROSYS.SEGURIDADPERFILUSUARIO
*/
public class BeanSeguridadperfilusuarioPk implements java.io.Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 4459197707133132033L;

	@Size(min = 0, max = 20)
	@NotNull
	@NotEmpty
	@Column(name = "PERFIL", length = 20, nullable = false)
	private String perfil;

	@Size(min = 0, max = 20)
	@NotNull
	@NotEmpty
	@Column(name = "USUARIO", length = 20, nullable = false)
	private String usuario;


	public BeanSeguridadperfilusuarioPk() {
	}

	public BeanSeguridadperfilusuarioPk(String pperfil,String pusuario) {
this.perfil = pperfil;
		this.usuario = pusuario;
	}

	/**
	 * Perfil del usuario 
	 * 
	 * @campo PERFIL
	*/
	public String getPerfil() {
		return perfil;
	}

	/**
	 * Perfil del usuario 
	 * 
	 * @campo PERFIL
	*/
	public void setPerfil(String perfil) {
		this.perfil = perfil;
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
