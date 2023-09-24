package net.royal.spring.sy.dominio;

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
 * 
 * 
 * @tabla dbo.SY_Preferences
*/
public class BeanSyPreferencesPk implements java.io.Serializable{


	@Size(min = 0, max = 20)
	@NotEmpty
	@Column(name = "USUARIO", length = 20, nullable = false)
	private String usuario;

	@Size(min = 0, max = 10)
	@NotEmpty
	@Column(name = "PREFERENCE", length = 10, nullable = false)
	private String preference;

	public BeanSyPreferencesPk() {
	}

	public BeanSyPreferencesPk(String pusuario,String ppreference) {
this.usuario = pusuario;
		this.preference = ppreference;
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
	/**
	 * 
	 * 
	 * @campo Preference
	*/
	public String getPreference() {
		return preference;
	}

	/**
	 * 
	 * 
	 * @campo Preference
	*/
	public void setPreference(String preference) {
		this.preference = preference;
	}


}
