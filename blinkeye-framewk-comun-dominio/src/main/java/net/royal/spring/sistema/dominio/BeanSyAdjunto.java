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
 * @tabla SPRING.SY_ADJUNTO
*/
@Entity
@Table(name = "SY_ADJUNTO")
public class BeanSyAdjunto implements java.io.Serializable{


	@EmbeddedId
	private BeanSyAdjuntoPk pk;

	@Size(min = 0, max = 100)
	@Column(name = "COMENTARIO", length = 100, nullable = true)
	private String comentario;

	@Size(min = 0, max = 200)
	@NotNull
	@NotEmpty
	@Column(name = "ARCHIVO", length = 200, nullable = false)
	private String archivo;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 20)
	@Column(name = "ULTIMOUSUARIO", length = 20, nullable = true)
	private String ultimousuario;
	
	@Column(name = "ARCHIVODATA")
	private byte[] archivodata;

	@Transient
	private Boolean auxFlgPreparado=Boolean.FALSE;
	
	@Transient
	private String ruta;
	
	@Transient
	private byte[] archivoadjunto;
	
	@Transient
	private String archivoadjuntostring;	


	public BeanSyAdjunto() {
		pk = new BeanSyAdjuntoPk();
	}


	public BeanSyAdjunto(BeanSyAdjuntoPk pk) {
		this.pk = pk;
	}

	public BeanSyAdjuntoPk getPk() {
		return pk;
	}

	public void setPk(BeanSyAdjuntoPk pk) {
		this.pk = pk;
	}
	/**
	 *  
	 * 
	 * @campo COMENTARIO
	*/
	public String getComentario() {
		return comentario;
	}

	/**
	 *  
	 * 
	 * @campo COMENTARIO
	*/
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	/**
	 *  
	 * 
	 * @campo ARCHIVO
	*/
	public String getArchivo() {
		return archivo;
	}

	/**
	 *  
	 * 
	 * @campo ARCHIVO
	*/
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	/**
	 *  
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 *  
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	/**
	 *  
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 *  
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	public Boolean getAuxFlgPreparado() {
		if (auxFlgPreparado==null)
			return Boolean.FALSE;
		return auxFlgPreparado;
	}

	public void setAuxFlgPreparado(Boolean auxFlgPreparado) {
		this.auxFlgPreparado = auxFlgPreparado;
	}


	public String getRuta() {
		return ruta;
	}


	public void setRuta(String ruta) {
		this.ruta = ruta;
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


	public byte[] getArchivodata() {
		return archivodata;
	}


	public void setArchivodata(byte[] archivodata) {
		this.archivodata = archivodata;
	}

}
