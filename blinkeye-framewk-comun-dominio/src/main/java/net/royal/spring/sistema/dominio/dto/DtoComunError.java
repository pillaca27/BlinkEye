package net.royal.spring.sistema.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class DtoComunError {
	private Integer idError;
	private String nombre;
	private Integer idReglaNegocio;
	private Integer idCorreo;
	private Integer idLogAlerta;
	private Integer idAlerta;
	private String proceso;
	private String descripcionError;
	private String estado;
	private Date creacionFecha;
	private String className;
	private String objetoBasedatos;
	private String dominioMensajeUsuario;

	private BigDecimal ROWNUM_;

	private Boolean flgInactivar = false;
	
	private List<DtoComunError> lstParaModificar;

	public Integer getIdError() {
		return idError;
	}

	public void setIdError(Integer idError) {
		this.idError = idError;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdReglaNegocio() {
		return idReglaNegocio;
	}

	public void setIdReglaNegocio(Integer idReglaNegocio) {
		this.idReglaNegocio = idReglaNegocio;
	}

	public Integer getIdCorreo() {
		return idCorreo;
	}

	public void setIdCorreo(Integer idCorreo) {
		this.idCorreo = idCorreo;
	}

	public Integer getIdLogAlerta() {
		return idLogAlerta;
	}

	public void setIdLogAlerta(Integer idLogAlerta) {
		this.idLogAlerta = idLogAlerta;
	}

	public Integer getIdAlerta() {
		return idAlerta;
	}

	public void setIdAlerta(Integer idAlerta) {
		this.idAlerta = idAlerta;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getDescripcionError() {
		return descripcionError;
	}

	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getCreacionFecha() {
		return creacionFecha;
	}

	public void setCreacionFecha(Date creacionFecha) {
		this.creacionFecha = creacionFecha;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getObjetoBasedatos() {
		return objetoBasedatos;
	}

	public void setObjetoBasedatos(String objetoBasedatos) {
		this.objetoBasedatos = objetoBasedatos;
	}

	public String getDominioMensajeUsuario() {
		return dominioMensajeUsuario;
	}

	public void setDominioMensajeUsuario(String dominioMensajeUsuario) {
		this.dominioMensajeUsuario = dominioMensajeUsuario;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

	public Boolean getFlgInactivar() {
		return flgInactivar;
	}

	public void setFlgInactivar(Boolean flgInactivar) {
		this.flgInactivar = flgInactivar;
	}

	public List<DtoComunError> getLstParaModificar() {
		return lstParaModificar;
	}

	public void setLstParaModificar(List<DtoComunError> lstParaModificar) {
		this.lstParaModificar = lstParaModificar;
	}
	
}
