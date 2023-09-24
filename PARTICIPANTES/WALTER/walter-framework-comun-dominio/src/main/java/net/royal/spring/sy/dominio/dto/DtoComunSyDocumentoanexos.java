package net.royal.spring.sy.dominio.dto;

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
import net.royal.spring.sy.dominio.BeanSyDocumentoanexos;

/**
 * 
 * 
 * @tabla dbo.SY_DocumentoAnexos
*/
public class DtoComunSyDocumentoanexos extends DominioTransaccion implements java.io.Serializable{


	private String companiasocio;
	private String modulo;
	private String tipodocumento;
	private String numerodocumento;
	private Integer linea;
	private Integer secuencia;
	private java.util.Date fecha;
	private String descripcion;
	private String comentario;
	private String rutaarchivo;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String archivo;
	private Integer secuenciawh;
	private byte[] archivoadjunto;

	private String archivoadjuntostring;	
	private byte[] archivodata;
	private String accion;
	private String clavetabla;
	
	private String nombreanteerior;

	
	
	
	
	
	public String getNombreanteerior() {
		return nombreanteerior;
	}

	public void setNombreanteerior(String nombreanteerior) {
		this.nombreanteerior = nombreanteerior;
	}

	public String getClavetabla() {
		return clavetabla;
	}

	public void setClavetabla(String clavetabla) {
		this.clavetabla = clavetabla;
	}

	public Integer getSecuenciawh() {
		return secuenciawh;
	}

	public void setSecuenciawh(Integer secuenciawh) {
		this.secuenciawh = secuenciawh;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public byte[] getArchivodata() {
		return archivodata;
	}

	public void setArchivodata(byte[] archivodata) {
		this.archivodata = archivodata;
	}

	public byte[] getArchivoadjunto() {
		return archivoadjunto;
	}

	public void setArchivoadjunto(byte[] archivoadjunto) {
		this.archivoadjunto = archivoadjunto;
	}

	public String getArchivoadjuntostring() {
		return archivoadjuntostring;
	}

	public void setArchivoadjuntostring(String archivoadjuntostring) {
		this.archivoadjuntostring = archivoadjuntostring;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	*/
	public String getCompaniasocio() {
		return companiasocio;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	*/
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}
	/**
	 * 
	 * 
	 * @campo Modulo
	*/
	public String getModulo() {
		return modulo;
	}

	/**
	 * 
	 * 
	 * @campo Modulo
	*/
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	/**
	 * 
	 * 
	 * @campo TipoDocumento
	*/
	public String getTipodocumento() {
		return tipodocumento;
	}

	/**
	 * 
	 * 
	 * @campo TipoDocumento
	*/
	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	/**
	 * 
	 * 
	 * @campo NumeroDocumento
	*/
	public String getNumerodocumento() {
		return numerodocumento;
	}

	/**
	 * 
	 * 
	 * @campo NumeroDocumento
	*/
	public void setNumerodocumento(String numerodocumento) {
		this.numerodocumento = numerodocumento;
	}
	/**
	 * 
	 * 
	 * @campo Linea
	*/
	public Integer getLinea() {
		return linea;
	}

	/**
	 * 
	 * 
	 * @campo Linea
	*/
	public void setLinea(Integer linea) {
		this.linea = linea;
	}
	/**
	 * 
	 * 
	 * @campo Secuencia
	*/
	public Integer getSecuencia() {
		return secuencia;
	}

	/**
	 * 
	 * 
	 * @campo Secuencia
	*/
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}
	/**
	 * 
	 * 
	 * @campo Fecha
	*/
	public java.util.Date getFecha() {
		return fecha;
	}

	/**
	 * 
	 * 
	 * @campo Fecha
	*/
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * 
	 * 
	 * @campo Descripcion
	*/
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * 
	 * 
	 * @campo Descripcion
	*/
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * 
	 * 
	 * @campo Comentario
	*/
	public String getComentario() {
		return comentario;
	}

	/**
	 * 
	 * 
	 * @campo Comentario
	*/
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	/**
	 * 
	 * 
	 * @campo RutaArchivo
	*/
	public String getRutaarchivo() {
		return rutaarchivo;
	}

	/**
	 * 
	 * 
	 * @campo RutaArchivo
	*/
	public void setRutaarchivo(String rutaarchivo) {
		this.rutaarchivo = rutaarchivo;
	}
	/**
	 * 
	 * 
	 * @campo Estado
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * 
	 * 
	 * @campo Estado
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * 
	 * 
	 * @campo UltimoUsuario
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * 
	 * 
	 * @campo UltimoUsuario
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	/**
	 * 
	 * 
	 * @campo UltimaFechaModif
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * 
	 * 
	 * @campo UltimaFechaModif
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	/**
	 * 
	 * 
	 * @campo Archivo
	*/
	public String getArchivo() {
		return archivo;
	}

	/**
	 * 
	 * 
	 * @campo Archivo
	*/
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public BeanSyDocumentoanexos obtenerBean() {
		BeanSyDocumentoanexos bean = new BeanSyDocumentoanexos();
		return obtenerBean(bean);
	}

	public BeanSyDocumentoanexos obtenerBean(BeanSyDocumentoanexos bean) {
		if (bean == null)
			bean = new BeanSyDocumentoanexos();

		bean.getPk().setCompaniasocio(companiasocio);
		bean.getPk().setModulo(modulo);
		bean.getPk().setTipodocumento(tipodocumento);
		bean.getPk().setNumerodocumento(numerodocumento);
		bean.getPk().setLinea(linea);
		bean.getPk().setSecuencia(secuencia);
		bean.setFecha(fecha);
		bean.setDescripcion(descripcion);
		bean.setComentario(comentario);
		bean.setRutaarchivo(rutaarchivo);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);
		bean.setArchivo(archivo);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
