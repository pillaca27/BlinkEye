package net.royal.spring.sg.dominio;


import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Tabla de tipo de sistema seguridad por autorizaciones
 * 
 * @tabla SGAGROSYS.SY_SEGURIDADAUTORIZACIONES
*/
public class BeanSySeguridadautorizacionesPk implements java.io.Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Size(min = 0, max = 2)
	@NotNull
	@NotEmpty
	@Column(name = "APLICACIONCODIGO", length = 2, nullable = false)
	private String aplicacioncodigo;

	@Size(min = 0, max = 20)
	@NotNull
	@NotEmpty
	@Column(name = "GRUPO", length = 20, nullable = false)
	private String grupo;

	@Size(min = 0, max = 25)
	@NotNull
	@NotEmpty
	@Column(name = "CONCEPTO", length = 25, nullable = false)
	private String concepto;

	@Size(min = 0, max = 20)
	@NotNull
	@NotEmpty
	@Column(name = "USUARIO", length = 20, nullable = false)
	private String usuario;


	public BeanSySeguridadautorizacionesPk() {
	}

	public BeanSySeguridadautorizacionesPk(String paplicacioncodigo,String pgrupo,String pconcepto,String pusuario) {
this.aplicacioncodigo = paplicacioncodigo;
		this.grupo = pgrupo;
		this.concepto = pconcepto;
		this.usuario = pusuario;
	}

	/**
	 * Codigo del Aplicativo
	 * 
	 * @campo APLICACIONCODIGO
	*/
	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	/**
	 * Codigo del Aplicativo
	 * 
	 * @campo APLICACIONCODIGO
	*/
	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	/**
	 * Grupo de Seguridad
	 * 
	 * @campo GRUPO
	*/
	public String getGrupo() {
		return grupo;
	}

	/**
	 * Grupo de Seguridad
	 * 
	 * @campo GRUPO
	*/
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	/**
	 * Concepto de Seguridad
	 * 
	 * @campo CONCEPTO
	*/
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Concepto de Seguridad
	 * 
	 * @campo CONCEPTO
	*/
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	/**
	 * Codigo de Usuario a autorizar
	 * 
	 * @campo USUARIO
	*/
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Codigo de Usuario a autorizar
	 * 
	 * @campo USUARIO
	*/
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
