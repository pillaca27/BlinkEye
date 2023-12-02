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

import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.SeguridadPerfilUsuario
*/
public class SeguridadperfilusuarioPk implements java.io.Serializable{


	@Size(min = 0, max = 20)
	@NotEmpty
	@Column(name = "PERFIL", length = 20, nullable = false)
	private String perfil;

	@Size(min = 0, max = 20)
	@NotEmpty
	@Column(name = "USUARIO", length = 20, nullable = false)
	private String usuario;

	public SeguridadperfilusuarioPk() {
	}

	public SeguridadperfilusuarioPk(String pperfil,String pusuario) {
this.perfil = pperfil;
		this.usuario = pusuario;
	}

	/**
	 * 
	 * 
	 * @campo Perfil
	*/
	public String getPerfil() {
		return perfil;
	}

	/**
	 * 
	 * 
	 * @campo Perfil
	*/
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	/**
	 * 
	 * 
	 * @campo Usuario
	*/
	public String getUsuario() {
		return usuario;
	}

	/**
	 * 
	 * 
	 * @campo Usuario
	*/
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


}
