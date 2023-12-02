package net.royal.spring.workflow.dominio.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.royal.spring.framework.modelo.correo.EmailTransaccion;

public class DtoFlujoSolicitud {

	private String proceso;
	private Integer version;
	private Integer flujo;
	private Integer transaccion;
	private String solicitante;
	private String referencia;
	private Date fechaRegistro;
	private String procesoFlujoDescripcion;
	private Integer nivelAprobar;
	private BigDecimal ROWNUM_;
	private String usuarioAccion;
	private String usuariosubaccion;
	private String estado;
	private String estadoDescripcion;
	private Integer nivelActual;
	private String criterios;
	private Integer idSolicitante;
	private String componenteweb;
	private String areaNombre;
	private BigDecimal monto;
	private String segmento;
	private String observaciones;
	private List<DtoFlujoAdjunto> adjuntos;
	private List<DtoFlujoAdjuntoValidar> tiposadjuntovalidar;
	private String nombrepersonareferencia;
	private EmailTransaccion email;
	private String conEmailPreparado;
	private String uuid;
	private Integer nivelSaltoAuxiliar;
	private Integer nivelSaltoAuxiliar2;
	private Integer nivelADondeAprobariaPeroHaDevuelto;
	
	public DtoFlujoSolicitud() {
		this.adjuntos = new ArrayList<DtoFlujoAdjunto>();
		this.tiposadjuntovalidar = new ArrayList<DtoFlujoAdjuntoValidar>();
		this.email = new EmailTransaccion();
	}

	public Integer getFlujo() {
		return flujo;
	}

	public void setFlujo(Integer flujo) {
		this.flujo = flujo;
	}

	public Integer getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Integer transaccion) {
		this.transaccion = transaccion;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getProcesoFlujoDescripcion() {
		return procesoFlujoDescripcion;
	}

	public void setProcesoFlujoDescripcion(String procesoFlujoDescripcion) {
		this.procesoFlujoDescripcion = procesoFlujoDescripcion;
	}

	public Integer getNivelAprobar() {
		return nivelAprobar;
	}

	public void setNivelAprobar(Integer nivelAprobar) {
		this.nivelAprobar = nivelAprobar;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

	public String getUsuarioAccion() {
		return usuarioAccion;
	}

	public void setUsuarioAccion(String usuarioAccion) {
		this.usuarioAccion = usuarioAccion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getNivelActual() {
		return nivelActual;
	}

	public void setNivelActual(Integer nivelActual) {
		this.nivelActual = nivelActual;
	}

	public String getCriterios() {
		return criterios;
	}

	public void setCriterios(String criterios) {
		this.criterios = criterios;
	}

	public Integer getIdSolicitante() {
		return idSolicitante;
	}

	public void setIdSolicitante(Integer idSolicitante) {
		this.idSolicitante = idSolicitante;
	}

	public List<DtoFlujoAdjunto> getAdjuntos() {
		return adjuntos;
	}

	public void setAdjuntos(List<DtoFlujoAdjunto> adjuntos) {
		this.adjuntos = adjuntos;
	}

	public List<DtoFlujoAdjuntoValidar> getTiposadjuntovalidar() {
		return tiposadjuntovalidar;
	}

	public void setTiposadjuntovalidar(List<DtoFlujoAdjuntoValidar> tiposadjuntovalidar) {
		this.tiposadjuntovalidar = tiposadjuntovalidar;
	}

	public String getEstadoDescripcion() {
		return estadoDescripcion;
	}

	public void setEstadoDescripcion(String estadoDescripcion) {
		this.estadoDescripcion = estadoDescripcion;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getUsuariosubaccion() {
		return usuariosubaccion;
	}

	public void setUsuariosubaccion(String usuariosubaccion) {
		this.usuariosubaccion = usuariosubaccion;
	}

	public String getComponenteweb() {
		return componenteweb;
	}

	public void setComponenteweb(String componenteweb) {
		this.componenteweb = componenteweb;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getAreaNombre() {
		return areaNombre;
	}

	public void setAreaNombre(String areaNombre) {
		this.areaNombre = areaNombre;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getNombrepersonareferencia() {
		return nombrepersonareferencia;
	}

	public void setNombrepersonareferencia(String nombrepersonareferencia) {
		this.nombrepersonareferencia = nombrepersonareferencia;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public EmailTransaccion getEmail() {
		return email;
	}

	public void setEmail(EmailTransaccion email) {
		this.email = email;
	}

	public String getConEmailPreparado() {
		return conEmailPreparado;
	}

	public void setConEmailPreparado(String conEmailPreparado) {
		this.conEmailPreparado = conEmailPreparado;
	}

	public Integer getNivelSaltoAuxiliar() {
		return nivelSaltoAuxiliar;
	}

	public void setNivelSaltoAuxiliar(Integer nivelSaltoAuxiliar) {
		this.nivelSaltoAuxiliar = nivelSaltoAuxiliar;
	}

	public Integer getNivelSaltoAuxiliar2() {
		return nivelSaltoAuxiliar2;
	}

	public void setNivelSaltoAuxiliar2(Integer nivelSaltoAuxiliar2) {
		this.nivelSaltoAuxiliar2 = nivelSaltoAuxiliar2;
	}

	public Integer getNivelADondeAprobariaPeroHaDevuelto() {
		return nivelADondeAprobariaPeroHaDevuelto;
	}

	public void setNivelADondeAprobariaPeroHaDevuelto(Integer nivelADondeAprobariaPeroHaDevuelto) {
		this.nivelADondeAprobariaPeroHaDevuelto = nivelADondeAprobariaPeroHaDevuelto;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
