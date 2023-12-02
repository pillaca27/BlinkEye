package net.royal.spring.framework.modelo;

import java.math.BigDecimal;
import java.util.Date;

public class WorkFlowResultado {
	public static String ERROR = "ERROR";
	public static String OK = "OK";

	private String estado;
	private String mensaje;

	private String codigoproceso;
	private String companiasocio;
	private Integer numeroproceso;
	private Integer nivel;
	private Integer transaccionid;
	private String accion;
	private String subaccion;
	private String observaciones;
	private String segmentosAprobados;

	private String referencia;
	private Integer solicitanteId;
	private Date fechaRegistro;
	
	private Integer flujoId;

	public WorkFlowResultado() {
		estado = ERROR;
	}
	public WorkFlowResultado(Integer transaccionid) {
		this.transaccionid=transaccionid;
		estado = ERROR;
	}
	
	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Integer getTransaccionid() {
		return transaccionid;
	}

	public void setTransaccionid(Integer transaccionid) {
		this.transaccionid = transaccionid;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getSubaccion() {
		return subaccion;
	}

	public void setSubaccion(String subaccion) {
		this.subaccion = subaccion;
	}

	

	public WorkFlowResultado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getCodigoproceso() {
		return codigoproceso;
	}

	public void setCodigoproceso(String codigoproceso) {
		this.codigoproceso = codigoproceso;
	}

	public String getCompaniasocio() {
		return companiasocio;
	}

	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}

	public Integer getNumeroproceso() {
		return numeroproceso;
	}

	public void setNumeroproceso(Integer numeroproceso) {
		this.numeroproceso = numeroproceso;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getSegmentosAprobados() {
		return segmentosAprobados;
	}

	public void setSegmentosAprobados(String segmentosAprobados) {
		this.segmentosAprobados = segmentosAprobados;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Integer getSolicitanteId() {
		return solicitanteId;
	}

	public void setSolicitanteId(Integer solicitanteId) {
		this.solicitanteId = solicitanteId;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Integer getFlujoId() {
		return flujoId;
	}
	public void setFlujoId(Integer flujoId) {
		this.flujoId = flujoId;
	}
	
}
