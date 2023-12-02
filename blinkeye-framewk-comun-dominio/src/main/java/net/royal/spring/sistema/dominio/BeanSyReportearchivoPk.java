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
 * SY_ReporteArchivo
 * 
 * @tabla SY_REPORTEARCHIVO
*/
public class BeanSyReportearchivoPk implements java.io.Serializable{



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

	@Size(min = 0, max = 8)
	@NotNull
	@NotEmpty
	@Column(name = "COMPANIASOCIO", length = 8, nullable = false)
	private String companiasocio;

	@Size(min = 0, max = 6)
	@NotNull
	@NotEmpty
	@Column(name = "PERIODO", length = 6, nullable = false)
	private String periodo;

	@Size(min = 0, max = 6)
	@NotNull
	@NotEmpty
	@Column(name = "VERSION", length = 6, nullable = false)
	private String version;


	public BeanSyReportearchivoPk() {
	}

	public BeanSyReportearchivoPk(String paplicacioncodigo,String preportecodigo,String pcompaniasocio,String pperiodo,String pversion) {
this.aplicacioncodigo = paplicacioncodigo;
		this.reportecodigo = preportecodigo;
		this.companiasocio = pcompaniasocio;
		this.periodo = pperiodo;
		this.version = pversion;
	}

	/**
	 * C\u00F3digo de la aplicaci\u00F3n
	 * 
	 * @campo APLICACIONCODIGO
	*/
	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	/**
	 * C\u00F3digo de la aplicaci\u00F3n
	 * 
	 * @campo APLICACIONCODIGO
	*/
	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	/**
	 * C\u00F3digo de reporte
	 * 
	 * @campo REPORTECODIGO
	*/
	public String getReportecodigo() {
		return reportecodigo;
	}

	/**
	 * C\u00F3digo de reporte
	 * 
	 * @campo REPORTECODIGO
	*/
	public void setReportecodigo(String reportecodigo) {
		this.reportecodigo = reportecodigo;
	}
	/**
	 * C\u00F3digo de la compa\u00F1\u00EDa
	 * 
	 * @campo COMPANIASOCIO
	*/
	public String getCompaniasocio() {
		return companiasocio;
	}

	/**
	 * C\u00F3digo de la compa\u00F1\u00EDa
	 * 
	 * @campo COMPANIASOCIO
	*/
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}
	/**
	 * N\u00FAmero del periodo
	 * 
	 * @campo PERIODO
	*/
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * N\u00FAmero del periodo
	 * 
	 * @campo PERIODO
	*/
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	/**
	 * Versi\u00F3n de formato
	 * 
	 * @campo VERSION
	*/
	public String getVersion() {
		return version;
	}

	/**
	 * Versi\u00F3n de formato
	 * 
	 * @campo VERSION
	*/
	public void setVersion(String version) {
		this.version = version;
	}

}
