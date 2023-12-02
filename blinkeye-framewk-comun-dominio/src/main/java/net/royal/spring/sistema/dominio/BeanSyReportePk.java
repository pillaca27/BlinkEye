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
 * Tabla de tipo de sistema reporte
 * 
 * @tabla SY_REPORTE
*/
public class BeanSyReportePk implements java.io.Serializable{



	@Size(min = 0, max = 2)
	@NotNull
	@NotEmpty
	@Column(name = "APLICACIONCODIGO", length = 2, nullable = false)
	private String aplicacioncodigo;

	@Size(min = 0, max = 3)
	@NotNull
	@NotEmpty
	@Column(name = "REPORTECODIGO", length = 3, nullable = false)
	private String reportecodigo;


	public BeanSyReportePk() {
	}

	public BeanSyReportePk(String paplicacioncodigo,String preportecodigo) {
this.aplicacioncodigo = paplicacioncodigo;
		this.reportecodigo = preportecodigo;
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
	 * Codigo del Reporte
	 * 
	 * @campo REPORTECODIGO
	*/
	public String getReportecodigo() {
		return reportecodigo;
	}

	/**
	 * Codigo del Reporte
	 * 
	 * @campo REPORTECODIGO
	*/
	public void setReportecodigo(String reportecodigo) {
		this.reportecodigo = reportecodigo;
	}

}
