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

import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * Maestro usuario del sistema
 * 
 * @tabla SGAGROSYS.USUARIO
*/
public class UsuarioPk implements java.io.Serializable{



	@Size(min = 0, max = 20)
	@NotNull
	@NotEmpty
	@Column(name = "USUARIO", length = 20, nullable = false)
	private String usuario;


	public UsuarioPk() {
	}

	public UsuarioPk(String pusuario) {
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
