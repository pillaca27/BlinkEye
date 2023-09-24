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
 * Tabla agrupadora de los parametros del sistema SIA
 * 
 * @tabla PARAMETROSMAST
*/
public class BeanParametrosmastPk implements java.io.Serializable{



	@Size(min = 0, max = 10)
	@NotNull
	@NotEmpty
	@Column(name = "COMPANIACODIGO", length = 10, nullable = false)
	private String companiacodigo;

	@Size(min = 0, max = 2)
	@NotNull
	@NotEmpty
	@Column(name = "APLICACIONCODIGO", length = 2, nullable = false)
	private String aplicacioncodigo;

	@Size(min = 0, max = 10)
	@NotNull
	@NotEmpty
	@Column(name = "PARAMETROCLAVE", length = 10, nullable = false)
	private String parametroclave;


	public BeanParametrosmastPk() {
	}

	public BeanParametrosmastPk(String pcompaniacodigo,String paplicacioncodigo,String pparametroclave) {
this.companiacodigo = pcompaniacodigo;
		this.aplicacioncodigo = paplicacioncodigo;
		this.parametroclave = pparametroclave;
	}

	/**
	 * Codigo de la Compa?ia a la cual esta relacionada la presente transaccion
	 * 
	 * @campo COMPANIACODIGO
	*/
	public String getCompaniacodigo() {
		return companiacodigo;
	}

	/**
	 * Codigo de la Compa?ia a la cual esta relacionada la presente transaccion
	 * 
	 * @campo COMPANIACODIGO
	*/
	public void setCompaniacodigo(String companiacodigo) {
		this.companiacodigo = companiacodigo;
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
	 * Parametro clave
	 * 
	 * @campo PARAMETROCLAVE
	*/
	public String getParametroclave() {
		return parametroclave;
	}

	/**
	 * Parametro clave
	 * 
	 * @campo PARAMETROCLAVE
	*/
	public void setParametroclave(String parametroclave) {
		this.parametroclave = parametroclave;
	}

}
