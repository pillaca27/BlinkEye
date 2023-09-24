package net.royal.spring.core.dominio;

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
 * Maestro miscelaneos detalle
 * 
 * @tabla MA_MISCELANEOSDETALLE
*/
public class BeanMaMiscelaneosdetallePk implements java.io.Serializable{



	@Size(min = 0, max = 2)
	@NotNull
	@NotEmpty
	@Column(name = "APLICACIONCODIGO", length = 2, nullable = false)
	private String aplicacioncodigo;

	@Size(min = 0, max = 10)
	@NotNull
	@NotEmpty
	@Column(name = "CODIGOTABLA", length = 10, nullable = false)
	private String codigotabla;

	@Size(min = 0, max = 6)
	@NotNull
	@NotEmpty
	@Column(name = "COMPANIA", length = 6, nullable = false)
	private String compania;

	@Size(min = 0, max = 10)
	@NotNull
	@NotEmpty
	@Column(name = "CODIGOELEMENTO", length = 10, nullable = false)
	private String codigoelemento;


	public BeanMaMiscelaneosdetallePk() {
	}

	public BeanMaMiscelaneosdetallePk(String paplicacioncodigo,String pcodigotabla,String pcompania,String pcodigoelemento) {
this.aplicacioncodigo = paplicacioncodigo;
		this.codigotabla = pcodigotabla;
		this.compania = pcompania;
		this.codigoelemento = pcodigoelemento;
	}

	/**
	 * Aplicacion del codigo 
	 * 
	 * @campo APLICACIONCODIGO
	*/
	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	/**
	 * Aplicacion del codigo 
	 * 
	 * @campo APLICACIONCODIGO
	*/
	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	/**
	 * Codigo de tabla 
	 * 
	 * @campo CODIGOTABLA
	*/
	public String getCodigotabla() {
		return codigotabla;
	}

	/**
	 * Codigo de tabla 
	 * 
	 * @campo CODIGOTABLA
	*/
	public void setCodigotabla(String codigotabla) {
		this.codigotabla = codigotabla;
	}
	/**
	 * Compa?ia 
	 * 
	 * @campo COMPANIA
	*/
	public String getCompania() {
		return compania;
	}

	/**
	 * Compa?ia 
	 * 
	 * @campo COMPANIA
	*/
	public void setCompania(String compania) {
		this.compania = compania;
	}
	/**
	 * Codigo de elemento 
	 * 
	 * @campo CODIGOELEMENTO
	*/
	public String getCodigoelemento() {
		return codigoelemento;
	}

	/**
	 * Codigo de elemento 
	 * 
	 * @campo CODIGOELEMENTO
	*/
	public void setCodigoelemento(String codigoelemento) {
		this.codigoelemento = codigoelemento;
	}

}
