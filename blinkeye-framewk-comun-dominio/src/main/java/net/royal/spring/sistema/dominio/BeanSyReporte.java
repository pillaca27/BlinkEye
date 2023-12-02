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
@Entity
@Table(name = "SY_REPORTE")
public class BeanSyReporte extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanSyReportePk pk;

	@Size(min = 0, max = 50)
	@Column(name = "DESCRIPCIONLOCAL", length = 50, nullable = true)
	private String descripcionlocal;

	@Size(min = 0, max = 50)
	@Column(name = "DESCRIPCIONINGLES", length = 50, nullable = true)
	private String descripcioningles;

	@Size(min = 0, max = 3)
	@Column(name = "TOPICO", length = 3, nullable = true)
	private String topico;

	@Size(min = 0, max = 6)
	@Column(name = "PERIODOIMPLEMENTACION", length = 6, nullable = true)
	private String periodoimplementacion;

	@Size(min = 0, max = 40)
	@Column(name = "VENTANAOBJETO", length = 40, nullable = true)
	private String ventanaobjeto;

	@Size(min = 0, max = 1)
	@Column(name = "PARAMETROSFLAG", length = 1, nullable = true)
	private String parametrosflag;

	@Size(min = 0, max = 1)
	@Column(name = "FORMATODEFAULTFLAG", length = 1, nullable = true)
	private String formatodefaultflag;

	@Size(min = 0, max = 255)
	@Column(name = "DESCRIPCIONDATA", length = 255, nullable = true)
	private String descripciondata;

	@Size(min = 0, max = 255)
	@Column(name = "COMENTARIOS", length = 255, nullable = true)
	private String comentarios;

	@Size(min = 0, max = 255)
	@Column(name = "RESTRICCIONES", length = 255, nullable = true)
	private String restricciones;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@Size(min = 0, max = 20)
	@Column(name = "ULTIMOUSUARIO", length = 20, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 1)
	@Column(name = "REPORTESTANDARDFLAG", length = 1, nullable = true)
	private String reportestandardflag;

	@Size(min = 0, max = 500)
	@Column(name = "ASUNTO", length = 500, nullable = true)
	private String asunto;

	@Size(min = 0, max = 2)
	@Column(name = "PADRE_ID_APLICACION", length = 2, nullable = true)
	private String padreIdAplicacion;

	@Size(min = 0, max = 3)
	@Column(name = "PADRE_ID_REPORTE", length = 3, nullable = true)
	private String padreIdReporte;

	@Size(min = 0, max = 5)
	@Column(name = "TIPOREPORTE", length = 5, nullable = true)
	private String tiporeporte;

//	@Size(min = 0, max = 100)
//	@Column(name = "CARPETATEMPORAL", length = 100, nullable = true)
//	private String carpetatemporal;

//	@Size(min = 0, max = 100)
//	@Column(name = "HOJAID", length = 100, nullable = true)
//	private String hojaid;

//	private byte[] sentencia;

//	@Size(min = 0, max = 500)
//	@Column(name = "URLINFORMEGOOGLE", length = 500, nullable = true)
//	private String urlinformegoogle;

//	@JsonSerialize(using = USerializers.DateSerializer.class)
//	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
//	@Column(name = "FECHAREPORTEACTUALIZACION", nullable = true)
//	private java.util.Date fechareporteactualizacion;
	
	@Size(min = 0, max = 36)
	@Column(name = "UUID", length = 36, nullable = true)
	private String uuid;


	public BeanSyReporte() {
		pk = new BeanSyReportePk();
	}


	public BeanSyReporte(BeanSyReportePk pk) {
		this.pk = pk;
	}

	public BeanSyReportePk getPk() {
		return pk;
	}

	public void setPk(BeanSyReportePk pk) {
		this.pk = pk;
	}
	/**
	 * Descripcion Local
	 * 
	 * @campo DESCRIPCIONLOCAL
	*/
	public String getDescripcionlocal() {
		return descripcionlocal;
	}

	/**
	 * Descripcion Local
	 * 
	 * @campo DESCRIPCIONLOCAL
	*/
	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}
	/**
	 * Descripcion en Ingles
	 * 
	 * @campo DESCRIPCIONINGLES
	*/
	public String getDescripcioningles() {
		return descripcioningles;
	}

	/**
	 * Descripcion en Ingles
	 * 
	 * @campo DESCRIPCIONINGLES
	*/
	public void setDescripcioningles(String descripcioningles) {
		this.descripcioningles = descripcioningles;
	}
	/**
	 * Topico
	 * 
	 * @campo TOPICO
	*/
	public String getTopico() {
		return topico;
	}

	/**
	 * Topico
	 * 
	 * @campo TOPICO
	*/
	public void setTopico(String topico) {
		this.topico = topico;
	}
	/**
	 * Periodo de Implementacion
	 * 
	 * @campo PERIODOIMPLEMENTACION
	*/
	public String getPeriodoimplementacion() {
		return periodoimplementacion;
	}

	/**
	 * Periodo de Implementacion
	 * 
	 * @campo PERIODOIMPLEMENTACION
	*/
	public void setPeriodoimplementacion(String periodoimplementacion) {
		this.periodoimplementacion = periodoimplementacion;
	}
	/**
	 * Nombre de la ventana objeto
	 * 
	 * @campo VENTANAOBJETO
	*/
	public String getVentanaobjeto() {
		return ventanaobjeto;
	}

	/**
	 * Nombre de la ventana objeto
	 * 
	 * @campo VENTANAOBJETO
	*/
	public void setVentanaobjeto(String ventanaobjeto) {
		this.ventanaobjeto = ventanaobjeto;
	}
	/**
	 * Flag que indica si el reporte requiere parametros
	 * 
	 * @campo PARAMETROSFLAG
	*/
	public String getParametrosflag() {
		return parametrosflag;
	}

	/**
	 * Flag que indica si el reporte requiere parametros
	 * 
	 * @campo PARAMETROSFLAG
	*/
	public void setParametrosflag(String parametrosflag) {
		this.parametrosflag = parametrosflag;
	}
	/**
	 * Flag que indica si el reporte tiene formato por defecto
	 * 
	 * @campo FORMATODEFAULTFLAG
	*/
	public String getFormatodefaultflag() {
		return formatodefaultflag;
	}

	/**
	 * Flag que indica si el reporte tiene formato por defecto
	 * 
	 * @campo FORMATODEFAULTFLAG
	*/
	public void setFormatodefaultflag(String formatodefaultflag) {
		this.formatodefaultflag = formatodefaultflag;
	}
	/**
	 * Descripcion de la Data
	 * 
	 * @campo DESCRIPCIONDATA
	*/
	public String getDescripciondata() {
		return descripciondata;
	}

	/**
	 * Descripcion de la Data
	 * 
	 * @campo DESCRIPCIONDATA
	*/
	public void setDescripciondata(String descripciondata) {
		this.descripciondata = descripciondata;
	}
	/**
	 * Cualquier informacion adicional relacionada con el presente registro
	 * 
	 * @campo COMENTARIOS
	*/
	public String getComentarios() {
		return comentarios;
	}

	/**
	 * Cualquier informacion adicional relacionada con el presente registro
	 * 
	 * @campo COMENTARIOS
	*/
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	/**
	 * Restricciones
	 * 
	 * @campo RESTRICCIONES
	*/
	public String getRestricciones() {
		return restricciones;
	}

	/**
	 * Restricciones
	 * 
	 * @campo RESTRICCIONES
	*/
	public void setRestricciones(String restricciones) {
		this.restricciones = restricciones;
	}
	/**
	 * Flag que indica si el reporte es standard
	 * 
	 * @campo ESTADO
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * Flag que indica si el reporte es standard
	 * 
	 * @campo ESTADO
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * A = Activo, I = Inactivo
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * A = Activo, I = Inactivo
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	/**
	 * Es el Codigo del ultimo usuario que realizo modificaciones a este registro
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * Es el Codigo del ultimo usuario que realizo modificaciones a este registro
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	/**
	 * Es la fecha de la ultima modificacion realizada a este registro
	 * 
	 * @campo REPORTESTANDARDFLAG
	*/
	public String getReportestandardflag() {
		return reportestandardflag;
	}

	/**
	 * Es la fecha de la ultima modificacion realizada a este registro
	 * 
	 * @campo REPORTESTANDARDFLAG
	*/
	public void setReportestandardflag(String reportestandardflag) {
		this.reportestandardflag = reportestandardflag;
	}
	/**
	 * ASUNTO
	 * 
	 * @campo ASUNTO
	*/
	public String getAsunto() {
		return asunto;
	}

	/**
	 * ASUNTO
	 * 
	 * @campo ASUNTO
	*/
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	/**
	 * ID PADRE DE APLICACI\u00D3N 
	 * 
	 * @campo PADRE_ID_APLICACION
	*/
//	public String getPadreIdAplicacion() {
//		return padreIdAplicacion;
//	}

	/**
	 * ID PADRE DE APLICACI\u00D3N 
	 * 
	 * @campo PADRE_ID_APLICACION
	*/
//	public void setPadreIdAplicacion(String padreIdAplicacion) {
//		this.padreIdAplicacion = padreIdAplicacion;
//	}
	/**
	 * ID PADRE DE REPORTE
	 * 
	 * @campo PADRE_ID_REPORTE
	*/
//	public String getPadreIdReporte() {
//		return padreIdReporte;
//	}

	/**
	 * ID PADRE DE REPORTE
	 * 
	 * @campo PADRE_ID_REPORTE
	*/
//	public void setPadreIdReporte(String padreIdReporte) {
//		this.padreIdReporte = padreIdReporte;
//	}
	/**
	 * TIPO REPORTE
	 * 
	 * @campo TIPOREPORTE
	*/
	public String getTiporeporte() {
		return tiporeporte;
	}

	/**
	 * TIPO REPORTE
	 * 
	 * @campo TIPOREPORTE
	*/
	public void setTiporeporte(String tiporeporte) {
		this.tiporeporte = tiporeporte;
	}


	public String getPadreIdAplicacion() {
		return padreIdAplicacion;
	}


	public void setPadreIdAplicacion(String padreIdAplicacion) {
		this.padreIdAplicacion = padreIdAplicacion;
	}


	public String getPadreIdReporte() {
		return padreIdReporte;
	}


	public void setPadreIdReporte(String padreIdReporte) {
		this.padreIdReporte = padreIdReporte;
	}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	/*public String getPadreIdAplicacion() {
		return padreIdAplicacion;
	}


	public void setPadreIdAplicacion(String padreIdAplicacion) {
		this.padreIdAplicacion = padreIdAplicacion;
	}


	public String getPadreIdReporte() {
		return padreIdReporte;
	}


	public void setPadreIdReporte(String padreIdReporte) {
		this.padreIdReporte = padreIdReporte;
	}*/
	

}
