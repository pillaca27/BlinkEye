package net.royal.spring.sistema.dominio;

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
 * @tabla dbo.SY_SeguridadConcepto
*/
public class BeanSySeguridadconceptoPk implements java.io.Serializable{


	@Size(min = 0, max = 2)
	@NotEmpty
	@Column(name = "APLICACIONCODIGO", length = 2, nullable = false)
	private String aplicacioncodigo;

	@Size(min = 0, max = 20)
	@NotEmpty
	@Column(name = "GRUPO", length = 20, nullable = false)
	private String grupo;

	@Size(min = 0, max = 20)
	@NotEmpty
	@Column(name = "CONCEPTO", length = 20, nullable = false)
	private String concepto;

	public BeanSySeguridadconceptoPk() {
	}

	public BeanSySeguridadconceptoPk(String paplicacioncodigo,String pgrupo,String pconcepto) {
this.aplicacioncodigo = paplicacioncodigo;
		this.grupo = pgrupo;
		this.concepto = pconcepto;
	}

	/**
	 * 
	 * 
	 * @campo AplicacionCodigo
	*/
	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	/**
	 * 
	 * 
	 * @campo AplicacionCodigo
	*/
	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	/**
	 * 
	 * 
	 * @campo Grupo
	*/
	public String getGrupo() {
		return grupo;
	}

	/**
	 * 
	 * 
	 * @campo Grupo
	*/
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	/**
	 * 
	 * 
	 * @campo Concepto
	*/
	public String getConcepto() {
		return concepto;
	}

	/**
	 * 
	 * 
	 * @campo Concepto
	*/
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}


}
