package net.royal.spring.comun.dominio;

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
@Entity
@Table(name = "SY_REPORTEARCHIVO")
public class WfBeanSyReportearchivo extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private WfBeanSyReportearchivoPk pk;

	private byte[] reporte;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;
	
	@Column(name = "nombre", nullable = true)
	private String nombre;

	@Size(min = 0, max = 50)
	@Column(name = "ULTIMOUSUARIO", length = 50, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	//@Size(min = 0, max = 2000)
	//@Column(name = "RUTACOMPLETA", length = 2000, nullable = true)
	//private String rutacompleta;
	@Column(name = "ASUNTO", nullable = true)
	private String asunto;

	
	@Transient
	private String auxString;

	public String getAuxString() {
		return auxString;
	}

	public void setAuxString(String auxString) {
		this.auxString = auxString;
	}
	

	public WfBeanSyReportearchivo() {
		pk = new WfBeanSyReportearchivoPk();
	}


	public WfBeanSyReportearchivo(WfBeanSyReportearchivoPk pk) {
		this.pk = pk;
	}

	public WfBeanSyReportearchivoPk getPk() {
		return pk;
	}

	public void setPk(WfBeanSyReportearchivoPk pk) {
		this.pk = pk;
	}
	/**
	 * Archivo binario que contiene el formato
	 * 
	 * @campo REPORTE
	*/
	public byte[] getReporte() {
		return reporte;
	}

	/**
	 * Archivo binario que contiene el formato
	 * 
	 * @campo REPORTE
	*/
	public void setReporte(byte[] reporte) {
		this.reporte = reporte;
	}
	/**
	 * Estado del registro
	 * 
	 * @campo ESTADO
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * Estado del registro
	 * 
	 * @campo ESTADO
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * Ultimo usuario que ha realizado modificaciones
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * Ultimo usuario que ha realizado modificaciones
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	/**
	 * Fecha de la ultima moficaci\u00F3n
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * Fecha de la ultima moficaci\u00F3n
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	/**
	 * RUTA COMPLETA DEL ARCHIVO ADJUNTO
	 * 
	 * @campo RUTACOMPLETA
	*/
	//public String getRutacompleta() {
		//return rutacompleta;
	//}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * RUTA COMPLETA DEL ARCHIVO ADJUNTO
	 * 
	 * @campo RUTACOMPLETA
	*/
	//public void setRutacompleta(String rutacompleta) {
		//this.rutacompleta = rutacompleta;
	//}
	public String getAsunto() {
		return asunto;
	}














	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
		
}
