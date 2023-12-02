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
 * Maestro miscelaneos header
 * 
 * @tabla MA_MISCELANEOSHEADER
*/
public class BeanMaMiscelaneosheaderPk implements java.io.Serializable{



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


	public BeanMaMiscelaneosheaderPk() {
	}

	public BeanMaMiscelaneosheaderPk(String paplicacioncodigo,String pcodigotabla,String pcompania) {
this.aplicacioncodigo = paplicacioncodigo;
		this.codigotabla = pcodigotabla;
		this.compania = pcompania;
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
	 * Reservado
	 * 
	 * @campo CODIGOTABLA
	*/
	public String getCodigotabla() {
		return codigotabla;
	}

	/**
	 * Reservado
	 * 
	 * @campo CODIGOTABLA
	*/
	public void setCodigotabla(String codigotabla) {
		this.codigotabla = codigotabla;
	}
	/**
	 * Codigo de la Compa?ia a la cual esta relacionada la presente transaccion
	 * 
	 * @campo COMPANIA
	*/
	public String getCompania() {
		return compania;
	}

	/**
	 * Codigo de la Compa?ia a la cual esta relacionada la presente transaccion
	 * 
	 * @campo COMPANIA
	*/
	public void setCompania(String compania) {
		this.compania = compania;
	}

}
