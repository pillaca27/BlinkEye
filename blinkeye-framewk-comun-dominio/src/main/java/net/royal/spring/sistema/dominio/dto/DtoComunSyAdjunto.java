package net.royal.spring.sistema.dominio.dto;

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
import net.royal.spring.sistema.dominio.BeanSyAdjunto;

/**
 * 
 * 
 * @tabla SPRING.SY_ADJUNTO
*/
public class DtoComunSyAdjunto implements java.io.Serializable{


	private String nombretabla;
	private String clavetabla;
	private Integer secuencia;
	private String comentario;
	private String archivo;
	private java.util.Date ultimafechamodif;
	private String ultimousuario;

	public String getNombretabla() {
		return nombretabla;
	}

	public void setNombretabla(String nombretabla) {
		this.nombretabla = nombretabla;
	}
	public String getClavetabla() {
		return clavetabla;
	}

	public void setClavetabla(String clavetabla) {
		this.clavetabla = clavetabla;
	}
	public Integer getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	public String getUltimousuario() {
		return ultimousuario;
	}

	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	public BeanSyAdjunto obtenerBean() {
		BeanSyAdjunto bean = new BeanSyAdjunto();
		return obtenerBean(bean);
	}

	public BeanSyAdjunto obtenerBean(BeanSyAdjunto bean) {
		if (bean == null)
			bean = new BeanSyAdjunto();

		bean.getPk().setNombretabla(nombretabla);
		bean.getPk().setClavetabla(clavetabla);
		bean.getPk().setSecuencia(secuencia);
		bean.setComentario(comentario);
		bean.setArchivo(archivo);
		bean.setUltimafechamodif(ultimafechamodif);
		bean.setUltimousuario(ultimousuario);


		return bean;
	}

}
