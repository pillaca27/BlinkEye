package net.royal.spring.seguridad.dominio;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * Registro de seguridad de autorizaciones
 * 
 * @tabla SPRING.SEGURIDADAUTORIZACIONES
*/
public class BeanSeguridadautorizacionesPk  implements java.io.Serializable {
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

	@Size(min = 0, max = 20)
	@NotNull
	@NotEmpty
	@Column(name = "CONCEPTO", length = 20, nullable = false)
	private String concepto;

	@Size(min = 0, max = 20)
	@NotNull
	@NotEmpty
	@Column(name = "USUARIO", length = 20, nullable = false)
	private String usuario;


	public BeanSeguridadautorizacionesPk() {
	}

	public BeanSeguridadautorizacionesPk(String paplicacioncodigo,String pgrupo,String pconcepto,String pusuario) {
this.aplicacioncodigo = paplicacioncodigo;
		this.grupo = pgrupo;
		this.concepto = pconcepto;
		this.usuario = pusuario;
	}

	/**
	 * codigo de aplicacion 
	 * 
	 * @campo APLICACIONCODIGO
	*/
	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	/**
	 * codigo de aplicacion 
	 * 
	 * @campo APLICACIONCODIGO
	*/
	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	/**
	 * Grupo del proceso 
	 * 
	 * @campo GRUPO
	*/
	public String getGrupo() {
		return grupo;
	}

	/**
	 * Grupo del proceso 
	 * 
	 * @campo GRUPO
	*/
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	/**
	 * Concepto del proceso 
	 * 
	 * @campo CONCEPTO
	*/
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Concepto del proceso 
	 * 
	 * @campo CONCEPTO
	*/
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	/**
	 * Codigo de usuario 
	 * 
	 * @campo USUARIO
	*/
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Codigo de usuario 
	 * 
	 * @campo USUARIO
	*/
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
