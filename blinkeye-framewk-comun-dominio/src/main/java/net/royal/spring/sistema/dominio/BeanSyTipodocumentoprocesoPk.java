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

import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.SY_TipoDocumentoProceso
*/
public class BeanSyTipodocumentoprocesoPk implements java.io.Serializable{



	@Size(min = 0, max = 4)
	@NotNull
	@NotEmpty
	@Column(name = "TIPODOCUMENTOID", length = 4, nullable = false)
	private String tipodocumentoid;

	@Size(min = 0, max = 2)
	@NotNull
	@NotEmpty
	@Column(name = "APLICACIONCODIGO", length = 2, nullable = false)
	private String aplicacioncodigo;

	@Size(min = 0, max = 10)
	@NotNull
	@NotEmpty
	@Column(name = "PROCESOCODIGO", length = 10, nullable = false)
	private String procesocodigo;


	public BeanSyTipodocumentoprocesoPk() {
	}

	public BeanSyTipodocumentoprocesoPk(String ptipodocumentoid,String paplicacioncodigo,String pprocesocodigo) {
this.tipodocumentoid = ptipodocumentoid;
		this.aplicacioncodigo = paplicacioncodigo;
		this.procesocodigo = pprocesocodigo;
	}

	/**
	 * 
	 * 
	 * @campo TipoDocumentoId
	*/
	public String getTipodocumentoid() {
		return tipodocumentoid;
	}

	/**
	 * 
	 * 
	 * @campo TipoDocumentoId
	*/
	public void setTipodocumentoid(String tipodocumentoid) {
		this.tipodocumentoid = tipodocumentoid;
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
