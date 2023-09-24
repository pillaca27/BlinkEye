package net.royal.spring.sy.dominio.dto;

import java.math.BigDecimal;
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
import net.royal.spring.sy.dominio.BeanSyReporte;

/**
 * Tabla de tipo de sistema reporte
 * 
 * @tabla SY_REPORTE
*/
public class DtoComunSyReporte extends DominioTransaccion implements java.io.Serializable{

	// pk
	private String aplicacioncodigo;
	private String reportecodigo;
	
	// columna
	private String descripcionlocal;
	private String descripcioningles;	
	private String topico;	
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	
	private String asunto;
	private String padreIdAplicacion;
	private String padreIdReporte;
	private String tiporeporte;

	// AUXILIARES
	private String aplicacionDescripcion;	
	//private BigDecimal ROWNUM_;
	
	//private String periodoimplementacion;
	//private String ventanaobjeto;
	//private String parametrosflag;
	//private String formatodefaultflag;
	//private String descripciondata;
	//private String comentarios;
	//private String restricciones;	
	//private String reportestandardflag;	
	

	

	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	public String getReportecodigo() {
		return reportecodigo;
	}

	public void setReportecodigo(String reportecodigo) {
		this.reportecodigo = reportecodigo;
	}
	public String getDescripcionlocal() {
		return descripcionlocal;
	}

	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}
	public String getDescripcioningles() {
		return descripcioningles;
	}

	public void setDescripcioningles(String descripcioningles) {
		this.descripcioningles = descripcioningles;
	}
	public String getTopico() {
		return topico;
	}

	public void setTopico(String topico) {
		this.topico = topico;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUltimousuario() {
		return ultimousuario;
	}

	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	
	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
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
	public String getTiporeporte() {
		return tiporeporte;
	}

	public void setTiporeporte(String tiporeporte) {
		this.tiporeporte = tiporeporte;
	}
	
	
	public String getAplicacionDescripcion() {
		return aplicacionDescripcion;
	}

	public void setAplicacionDescripcion(String aplicacionDescripcion) {
		this.aplicacionDescripcion = aplicacionDescripcion;
	}


	public BeanSyReporte obtenerBean() {
		BeanSyReporte bean = new BeanSyReporte();
		return obtenerBean(bean);
	}

	public BeanSyReporte obtenerBean(BeanSyReporte bean) {
		if (bean == null)
			bean = new BeanSyReporte();

		bean.getPk().setAplicacioncodigo(aplicacioncodigo);
		bean.getPk().setReportecodigo(reportecodigo);
		bean.setDescripcionlocal(descripcionlocal);
		bean.setDescripcioningles(descripcioningles);
		bean.setTopico(topico);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);
		
		bean.setAsunto(asunto);		
		bean.setTiporeporte(tiporeporte);
		//bean.setPadreIdAplicacion(padreIdAplicacion);
		//bean.setPadreIdReporte(padreIdReporte);
		
		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());
		return bean;
	}	
	
	public void asignarBean(BeanSyReporte bean) {
		if (bean==null)
			return;
		aplicacioncodigo  = bean.getPk().getAplicacioncodigo();
		reportecodigo = bean.getPk().getReportecodigo();
		descripcionlocal = bean.getDescripcionlocal();
		descripcioningles = bean.getDescripcioningles();
		topico = bean.getTopico();
		estado = bean.getEstado();
		ultimousuario = bean.getUltimousuario();
		ultimafechamodif = bean.getUltimafechamodif();
		
		asunto = bean.getAsunto();		
		tiporeporte = bean.getTiporeporte();
		//padreIdAplicacion = bean.getPadreIdAplicacion();
		//padreIdReporte = bean.getPadreIdReporte();
		
	}	
}
