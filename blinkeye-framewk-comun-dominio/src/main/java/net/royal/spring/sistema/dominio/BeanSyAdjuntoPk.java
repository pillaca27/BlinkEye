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
public class BeanSyAdjuntoPk implements java.io.Serializable{



	@Size(min = 0, max = 40)
	@NotNull
	@NotEmpty
	@Column(name = "NOMBRETABLA", length = 40, nullable = false)
	private String nombretabla;

	@Size(min = 0, max = 100)
	@NotNull
	@NotEmpty
	@Column(name = "CLAVETABLA", length = 100, nullable = false)
	private String clavetabla;

	@NotNull
	@Column(name = "SECUENCIA", nullable = false)
	private Integer secuencia;


	public BeanSyAdjuntoPk() {
	}

	public BeanSyAdjuntoPk(String pnombretabla,String pclavetabla,Integer psecuencia) {
this.nombretabla = pnombretabla;
		this.clavetabla = pclavetabla;
		this.secuencia = psecuencia;
	}

	/**
	 *  
	 * 
	 * @campo NOMBRETABLA
	*/
	public String getNombretabla() {
		return nombretabla;
	}

	/**
	 *  
	 * 
	 * @campo NOMBRETABLA
	*/
	public void setNombretabla(String nombretabla) {
		this.nombretabla = nombretabla;
	}
	/**
	 *  
	 * 
	 * @campo CLAVETABLA
	*/
	public String getClavetabla() {
		return clavetabla;
	}

	/**
	 *  
	 * 
	 * @campo CLAVETABLA
	*/
	public void setClavetabla(String clavetabla) {
		this.clavetabla = clavetabla;
	}
	/**
	 *  
	 * 
	 * @campo SECUENCIA
	*/
	public Integer getSecuencia() {
		return secuencia;
	}

	/**
	 *  
	 * 
	 * @campo SECUENCIA
	*/
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}

}
