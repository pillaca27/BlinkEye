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

import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.SY_AplicacionReporteTopico
*/
public class BeanSyAplicacionreportetopicoPk implements java.io.Serializable{


	@Size(min = 0, max = 2)
	@NotEmpty
	@Column(name = "APLICACIONCODIGO", length = 2, nullable = false)
	private String aplicacioncodigo;

	@Size(min = 0, max = 3)
	@NotEmpty
	@Column(name = "TOPICO", length = 3, nullable = false)
	private String topico;

	public BeanSyAplicacionreportetopicoPk() {
	}

	public BeanSyAplicacionreportetopicoPk(String paplicacioncodigo,String ptopico) {
this.aplicacioncodigo = paplicacioncodigo;
		this.topico = ptopico;
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
	 * @campo Topico
	*/
	public String getTopico() {
		return topico;
	}

	/**
	 * 
	 * 
	 * @campo Topico
	*/
	public void setTopico(String topico) {
		this.topico = topico;
	}


}
