package net.royal.spring.sistema.dominio;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.SY_DocumentoAnexos
*/
@Entity
@Table(name = "SY_DOCUMENTOANEXOS")
public class BeanSyDocumentoanexos extends DominioTransaccion implements java.io.Serializable{
	@EmbeddedId
	private BeanSyDocumentoanexosPk pk;


	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHA", nullable = true)
	private java.util.Date fecha;

	@Size(min = 0, max = 255)
	@Column(name = "DESCRIPCION", length = 255, nullable = true)
	private String descripcion;

	@Size(min = 0, max = 300)
	@Column(name = "COMENTARIO", length = 300, nullable = true)
	private String comentario;

	@Size(min = 0, max = 300)
	@Column(name = "RUTAARCHIVO", length = 300, nullable = true)
	private String rutaarchivo;

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

	@Size(min = 0, max = 500)
	@Column(name = "ARCHIVO", length = 500, nullable = true)
	private String archivo;
	
	@Size(min = 0, max = 500)
	@Column(name = "PROCESOTIPODOCUMENTOID", length = 500, nullable = true)
	private String procesoTipoDocumentoId;
	
	@Size(min = 0, max = 1)
	@Column(name = "CONCEPTO", length = 1, nullable = true)
	private String concepto;

	public BeanSyDocumentoanexos() {
		pk = new BeanSyDocumentoanexosPk();
	}


	public BeanSyDocumentoanexos(BeanSyDocumentoanexosPk pk) {
		this.pk = pk;
	}

	public BeanSyDocumentoanexosPk getPk() {
		return pk;
	}

	public void setPk(BeanSyDocumentoanexosPk pk) {
		this.pk = pk;
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


	public String getProcesoTipoDocumentoId() {
		return procesoTipoDocumentoId;
	}


	public void setProcesoTipoDocumentoId(String procesoTipoDocumentoId) {
		this.procesoTipoDocumentoId = procesoTipoDocumentoId;
	}


	public String getConcepto() {
		return concepto;
	}


	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	
}
