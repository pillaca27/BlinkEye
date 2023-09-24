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
 * Maestro de correlativos
 * 
 * @tabla CORRELATIVOSMAST
*/
public class BeanCorrelativosmastPk implements java.io.Serializable{



	@Size(min = 0, max = 6)
	@NotNull
	@NotEmpty
	@Column(name = "COMPANIACODIGO", length = 6, nullable = false)
	private String companiacodigo;

	@Size(min = 0, max = 2)
	@NotNull
	@NotEmpty
	@Column(name = "TIPOCOMPROBANTE", length = 2, nullable = false)
	private String tipocomprobante;

	@Size(min = 0, max = 4)
	@NotNull
	@NotEmpty
	@Column(name = "SERIE", length = 4, nullable = false)
	private String serie;


	public BeanCorrelativosmastPk() {
	}

	public BeanCorrelativosmastPk(String pcompaniacodigo,String ptipocomprobante,String pserie) {
this.companiacodigo = pcompaniacodigo;
		this.tipocomprobante = ptipocomprobante;
		this.serie = pserie;
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
	 * Tipo Comprobante
	 * 
	 * @campo TIPOCOMPROBANTE
	*/
	public String getTipocomprobante() {
		return tipocomprobante;
	}

	/**
	 * Tipo Comprobante
	 * 
	 * @campo TIPOCOMPROBANTE
	*/
	public void setTipocomprobante(String tipocomprobante) {
		this.tipocomprobante = tipocomprobante;
	}
	/**
	 * Serie
	 * 
	 * @campo SERIE
	*/
	public String getSerie() {
		return serie;
	}

	/**
	 * Serie
	 * 
	 * @campo SERIE
	*/
	public void setSerie(String serie) {
		this.serie = serie;
	}

}
