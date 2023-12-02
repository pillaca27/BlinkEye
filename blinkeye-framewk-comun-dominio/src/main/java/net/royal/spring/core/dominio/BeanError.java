package net.royal.spring.core.dominio;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla SGALERTASSYS.ERROR
*/
@Entity
@Table(name = "ERROR" )
public class BeanError extends DominioTransaccion  implements java.io.Serializable{

	//@EmbeddedId
	//private ErrorBeanPk pk;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "ID_ERROR", nullable = false)
	private Integer idError;
	
	

	@Column(name = "ID_REGLA_NEGOCIO", nullable = true)
	private Integer idReglaNegocio;

	@Column(name = "ID_CORREO", nullable = true)
	private Integer idCorreo;

	@Column(name = "ID_LOG_ALERTA", nullable = true)
	private Integer idLogAlerta;

	@Column(name = "ID_ALERTA", nullable = true)
	private Integer idAlerta;

	@Size(min = 0, max = 500)
	@Column(name = "PROCESO", length = 500, nullable = true)
	private String proceso;

	//@Size(min = 0, max = 4000)
	@Lob
	@Column(name = "DESCRIPCION_ERROR", nullable = true)
	private String descripcionError;

	@Size(min = 0, max = 2000)
	@Column(name = "MENSAJE_USUARIO", length = 2000, nullable = true)
	private String dominioMensajeUsuario;

	@Size(min = 0, max = 3)
	@Column(name = "ESTADO", length = 3, nullable = true)
	private String estado;

	@Size(min = 0, max = 50)
	@Column(name = "CREACION_USUARIO", length = 50, nullable = true)
	private String creacionUsuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "CREACION_FECHA", nullable = true)
	private java.util.Date creacionFecha;

	@Size(min = 0, max = 50)
	@Column(name = "CREACION_TERMINAL", length = 50, nullable = true)
	private String creacionTerminal;

	@Size(min = 0, max = 50)
	@Column(name = "MODIFICACION_USUARIO", length = 50, nullable = true)
	private String modificacionUsuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "MODIFICACION_FECHA", nullable = true)
	private java.util.Date modificacionFecha;

	@Size(min = 0, max = 50)
	@Column(name = "MODIFICACION_TERMINAL", length = 50, nullable = true)
	private String modificacionTerminal;

	@Size(min = 0, max = 500)
	@Column(name = "CLASS_NAME", length = 500, nullable = true)
	private String className;

	@Size(min = 0, max = 300)
	@Column(name = "OBJETO_BASEDATOS", length = 300, nullable = true)
	private String objetoBasedatos;

	@Transient
	private Boolean auxFlgPreparadoBoolean=Boolean.FALSE;


	public BeanError() {
		//pk = new ErrorBeanPk();
	}


	/*public ErrorBean(ErrorBeanPk pk) {
		this.pk = pk;
	}*/

	/*public ErrorBeanPk getPk() {
		return pk;
	}

	public void setPk(ErrorBeanPk pk) {
		this.pk = pk;
	}*/
	/**
	 *  
	 * 
	 * @campo ID_REGLA_NEGOCIO
	*/
	public Integer getIdReglaNegocio() {
		return idReglaNegocio;
	}

	/**
	 *  
	 * 
	 * @campo ID_REGLA_NEGOCIO
	*/
	public void setIdReglaNegocio(Integer idReglaNegocio) {
		this.idReglaNegocio = idReglaNegocio;
	}
	/**
	 *  
	 * 
	 * @campo ID_CORREO
	*/
	public Integer getIdCorreo() {
		return idCorreo;
	}

	/**
	 *  
	 * 
	 * @campo ID_CORREO
	*/
	public void setIdCorreo(Integer idCorreo) {
		this.idCorreo = idCorreo;
	}
	/**
	 *  
	 * 
	 * @campo ID_LOG_ALERTA
	*/
	public Integer getIdLogAlerta() {
		return idLogAlerta;
	}

	/**
	 *  
	 * 
	 * @campo ID_LOG_ALERTA
	*/
	public void setIdLogAlerta(Integer idLogAlerta) {
		this.idLogAlerta = idLogAlerta;
	}
	/**
	 *  
	 * 
	 * @campo ID_ALERTA
	*/
	public Integer getIdAlerta() {
		return idAlerta;
	}

	/**
	 *  
	 * 
	 * @campo ID_ALERTA
	*/
	public void setIdAlerta(Integer idAlerta) {
		this.idAlerta = idAlerta;
	}
	/**
	 *  
	 * 
	 * @campo PROCESO
	*/
	public String getProceso() {
		return proceso;
	}

	/**
	 *  
	 * 
	 * @campo PROCESO
	*/
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
	/**
	 *  
	 * 
	 * @campo DESCRIPCION_ERROR
	*/
	public String getDescripcionError() {
		return descripcionError;
	}

	/**
	 *  
	 * 
	 * @campo DESCRIPCION_ERROR
	*/
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}
	/**
	 *  
	 * 
	 * @campo MENSAJE_USUARIO
	*/
	public String getDominioMensajeUsuario() {
		return dominioMensajeUsuario;
	}

	/**
	 *  
	 * 
	 * @campo MENSAJE_USUARIO
	*/
	public void setDominioMensajeUsuario(String DominioMensajeUsuario) {
		this.dominioMensajeUsuario = DominioMensajeUsuario;
	}
	/**
	 *  
	 * 
	 * @campo ESTADO
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 *  
	 * 
	 * @campo ESTADO
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 *  
	 * 
	 * @campo CREACION_USUARIO
	*/
	public String getCreacionUsuario() {
		return creacionUsuario;
	}

	/**
	 *  
	 * 
	 * @campo CREACION_USUARIO
	*/
	public void setCreacionUsuario(String creacionUsuario) {
		this.creacionUsuario = creacionUsuario;
	}
	/**
	 *  
	 * 
	 * @campo CREACION_FECHA
	*/
	public java.util.Date getCreacionFecha() {
		return creacionFecha;
	}

	/**
	 *  
	 * 
	 * @campo CREACION_FECHA
	*/
	public void setCreacionFecha(java.util.Date creacionFecha) {
		this.creacionFecha = creacionFecha;
	}
	/**
	 *  
	 * 
	 * @campo CREACION_TERMINAL
	*/
	public String getCreacionTerminal() {
		return creacionTerminal;
	}

	/**
	 *  
	 * 
	 * @campo CREACION_TERMINAL
	*/
	public void setCreacionTerminal(String creacionTerminal) {
		this.creacionTerminal = creacionTerminal;
	}
	/**
	 *  
	 * 
	 * @campo MODIFICACION_USUARIO
	*/
	public String getModificacionUsuario() {
		return modificacionUsuario;
	}

	/**
	 *  
	 * 
	 * @campo MODIFICACION_USUARIO
	*/
	public void setModificacionUsuario(String modificacionUsuario) {
		this.modificacionUsuario = modificacionUsuario;
	}
	/**
	 *  
	 * 
	 * @campo MODIFICACION_FECHA
	*/
	public java.util.Date getModificacionFecha() {
		return modificacionFecha;
	}

	/**
	 *  
	 * 
	 * @campo MODIFICACION_FECHA
	*/
	public void setModificacionFecha(java.util.Date modificacionFecha) {
		this.modificacionFecha = modificacionFecha;
	}
	/**
	 *  
	 * 
	 * @campo MODIFICACION_TERMINAL
	*/
	public String getModificacionTerminal() {
		return modificacionTerminal;
	}

	/**
	 *  
	 * 
	 * @campo MODIFICACION_TERMINAL
	*/
	public void setModificacionTerminal(String modificacionTerminal) {
		this.modificacionTerminal = modificacionTerminal;
	}
	/**
	 *  
	 * 
	 * @campo CLASS_NAME
	*/
	public String getClassName() {
		return className;
	}

	/**
	 *  
	 * 
	 * @campo CLASS_NAME
	*/
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 *  
	 * 
	 * @campo OBJETO_BASEDATOS
	*/
	public String getObjetoBasedatos() {
		return objetoBasedatos;
	}

	/**
	 *  
	 * 
	 * @campo OBJETO_BASEDATOS
	*/
	public void setObjetoBasedatos(String objetoBasedatos) {
		this.objetoBasedatos = objetoBasedatos;
	}
	
	public Boolean getAuxFlgPreparadoBoolean() {
		return auxFlgPreparadoBoolean;
	}


	public void setAuxFlgPreparadoBoolean(Boolean auxFlgPreparadoBoolean) {
		this.auxFlgPreparadoBoolean = auxFlgPreparadoBoolean;
	}
	
}
