package net.royal.spring.core.dominio;

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
 * Tabla agrupadora de los parametros del sistema SIA
 * 
 * @tabla PARAMETROSMAST
*/
@Entity
@Table(name = "PARAMETROSMAST")
public class BeanParametrosmast extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanParametrosmastPk pk;

	@Size(min = 0, max = 50)
	@Column(name = "DESCRIPCIONPARAMETRO", length = 50, nullable = true)
	private String descripcionparametro;

	//MODIFICAR TABLA PARA QUE LLEGUE A 1000
	@Size(min = 0, max = 1000)
	@Column(name = "EXPLICACION", length = 1000, nullable = true)
	private String explicacion;

	@Size(min = 0, max = 1)
	@Column(name = "TIPODEDATOFLAG", length = 1, nullable = true)
	private String tipodedatoflag;

	@Size(min = 0, max = 1000)
	@Column(name = "TEXTO", length = 1000, nullable = true)
	private String texto;

	@Column(name = "NUMERO", nullable = true)
	private Integer numero;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHA", nullable = true)
	private java.util.Date fecha;

	@Size(min = 0, max = 1)
	@Column(name = "FINANCECOMUNFLAG", length = 1, nullable = true)
	private String financecomunflag;

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

	@Size(min = 0, max = 4000)
	@Column(name = "EXPLICACIONADICIONAL", length = 4000, nullable = true)
	private String explicacionadicional;

	@Size(min = 0, max = 36)
	@Column(name = "UUID", length = 36, nullable = true)
	private String uuid;
	

	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public BeanParametrosmast() {
		pk = new BeanParametrosmastPk();
	}


	public BeanParametrosmast(BeanParametrosmastPk pk) {
		this.pk = pk;
	}

	public BeanParametrosmastPk getPk() {
		return pk;
	}

	public void setPk(BeanParametrosmastPk pk) {
		this.pk = pk;
	}
	/**
	 * Descripcion del parametro
	 * 
	 * @campo DESCRIPCIONPARAMETRO
	*/
	public String getDescripcionparametro() {
		return descripcionparametro;
	}

	/**
	 * Descripcion del parametro
	 * 
	 * @campo DESCRIPCIONPARAMETRO
	*/
	public void setDescripcionparametro(String descripcionparametro) {
		this.descripcionparametro = descripcionparametro;
	}
	/**
	 * Explicacion detallada
	 * 
	 * @campo EXPLICACION
	*/
	public String getExplicacion() {
		return explicacion;
	}

	/**
	 * Explicacion detallada
	 * 
	 * @campo EXPLICACION
	*/
	public void setExplicacion(String explicacion) {
		this.explicacion = explicacion;
	}
	/**
	 * T = Texto, N = Numerico, F = Fecha
	 * 
	 * @campo TIPODEDATOFLAG
	*/
	public String getTipodedatoflag() {
		return tipodedatoflag;
	}

	/**
	 * T = Texto, N = Numerico, F = Fecha
	 * 
	 * @campo TIPODEDATOFLAG
	*/
	public void setTipodedatoflag(String tipodedatoflag) {
		this.tipodedatoflag = tipodedatoflag;
	}
	/**
	 * Valiable de texto del parametro
	 * 
	 * @campo TEXTO
	*/
	public String getTexto() {
		return texto;
	}

	/**
	 * Valiable de texto del parametro
	 * 
	 * @campo TEXTO
	*/
	public void setTexto(String texto) {
		this.texto = texto;
	}
	/**
	 * Numero
	 * 
	 * @campo NUMERO
	*/
	public Integer getNumero() {
		return numero;
	}

	/**
	 * Numero
	 * 
	 * @campo NUMERO
	*/
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	/**
	 * Fecha
	 * 
	 * @campo FECHA
	*/
	public java.util.Date getFecha() {
		return fecha;
	}

	/**
	 * Fecha
	 * 
	 * @campo FECHA
	*/
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * S = Parametro comun para finanzas, N = No
	 * 
	 * @campo FINANCECOMUNFLAG
	*/
	public String getFinancecomunflag() {
		return financecomunflag;
	}

	/**
	 * S = Parametro comun para finanzas, N = No
	 * 
	 * @campo FINANCECOMUNFLAG
	*/
	public void setFinancecomunflag(String financecomunflag) {
		this.financecomunflag = financecomunflag;
	}
	/**
	 * A = Activo. I = Inactivo
	 * 
	 * @campo ESTADO
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * A = Activo. I = Inactivo
	 * 
	 * @campo ESTADO
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * Ultimo Usuario de Modificacion
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * Ultimo Usuario de Modificacion
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	/**
	 * Es la fecha de la ultima modificacion realizada a este registro
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * Es la fecha de la ultima modificacion realizada a este registro
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	/**
	 * Explicacion Adicional
	 * 
	 * @campo EXPLICACIONADICIONAL
	*/
	public String getExplicacionadicional() {
		return explicacionadicional;
	}

	/**
	 * Explicacion Adicional
	 * 
	 * @campo EXPLICACIONADICIONAL
	*/
	public void setExplicacionadicional(String explicacionadicional) {
		this.explicacionadicional = explicacionadicional;
	}

}
