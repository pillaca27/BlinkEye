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
 * @tabla dbo.SY_ProcesoMst
*/
public class BeanSyProcesomstPk implements java.io.Serializable{


	@Size(min = 0, max = 2)
	@NotEmpty
	@Column(name = "APLICACIONCODIGO", length = 2, nullable = false)
	private String aplicacioncodigo;

	@Size(min = 0, max = 10)
	@NotEmpty
	@Column(name = "PROCESOCODIGO", length = 10, nullable = false)
	private String procesocodigo;

	public BeanSyProcesomstPk() {
	}

	public BeanSyProcesomstPk(String paplicacioncodigo,String pprocesocodigo) {
this.aplicacioncodigo = paplicacioncodigo;
		this.procesocodigo = pprocesocodigo;
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
	 * @campo ProcesoCodigo
	*/
	public String getProcesocodigo() {
		return procesocodigo;
	}

	/**
	 * 
	 * 
	 * @campo ProcesoCodigo
	*/
	public void setProcesocodigo(String procesocodigo) {
		this.procesocodigo = procesocodigo;
	}


}
