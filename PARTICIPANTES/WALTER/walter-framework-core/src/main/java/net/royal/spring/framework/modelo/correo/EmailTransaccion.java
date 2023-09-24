package net.royal.spring.framework.modelo.correo;

import java.util.ArrayList;
import java.util.List;

import net.royal.spring.framework.core.UExportar;
import net.royal.spring.framework.modelo.generico.DominioAdjunto;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.ULista;


public class EmailTransaccion extends DominioTransaccion{
	
	/*
	 * se regisra en ALERTAS y al enviar correo por implementar 
	 */
	public static final String TIPO_TRANSACCION_ALERTA = "ALERTA";
	
	/*
	 * solo se manda correo, (Por Defecto)
	 */
	public static final String TIPO_TRANSACCION_MOTOR = "MOTOR";
	
	/*
	 * Registrar en alertas solo para ver
	 */
	public static final String TIPO_TRANSACCION_LOGGER = "LOGGER";
	
	private String tipoTransaccion;
	private String remitenteNombre;
	private String remitenteCorreo;
	private String asunto;
	private byte[] cuerpoCorreoBytes;
	private String cuerpoCorreoBase64;
	
	private List<EmailDestino> listaCorreoDestino;
	private List<DominioAdjunto> listaCorreoAdjunto;

	private Boolean flgCorreoPrueba = Boolean.FALSE;

	/**
	 * se usa para procesos o etapas de testeo
	 * caso : workflow de aprobaciones enviar transaccion_id para ver quien mandan el correo
	 */
	private String traceReferencia;
	
	/**
	 * codigo o id de referencia nro de requerimiento, numero de convocatoria
	 */
	private String referenciaId;
	
	/**
	 * codigo o id de referencia nro de requerimiento, numero de convocatoria
	 */
	private String referenciaPadreId;
	
	/**
	 * codigo de proceso, o codigo de WF_PROCESO
	 */
	private String procesoId;
	
	/**
	 * id de transaccion de workflow, o identificador unico de proceso
	 */
	private Integer transaccionId;
	
	/**
	 * id de seguimiento de workflow o de seguimiento
	 */
	private Integer seguimientoId;
	
	/**
	 * accion APROBAR, RECHAZAR, DEVOLVER, etc, un vervo de la accion
	 */
	private String accionId;
	
	/**
	 * sub accion APROBAR, RECHAZAR, DEVOLVER, etc, un vervo de la accion
	 */
	private String subAccionId;
	
	public EmailTransaccion() {
		tipoTransaccion=TIPO_TRANSACCION_ALERTA;
		listaCorreoDestino = new ArrayList<EmailDestino>();
		listaCorreoAdjunto = new ArrayList<DominioAdjunto>();
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}


	public byte[] getCuerpoCorreoBytes() {
		return cuerpoCorreoBytes;
	}

	public void setCuerpoCorreoBytes(byte[] cuerpoCorreoBytes) {
		this.cuerpoCorreoBytes = cuerpoCorreoBytes;
	}

	public List<EmailDestino> getListaCorreoDestino() {
		if (listaCorreoDestino == null)
			listaCorreoDestino = new ArrayList<EmailDestino>();
		return listaCorreoDestino;
	}

	public void setListaCorreoDestino(List<EmailDestino> listaCorreoDestino) {
		this.listaCorreoDestino = listaCorreoDestino;
	}

	public List<DominioAdjunto> getListaCorreoAdjunto() {
		if (ULista.esListaVacia(listaCorreoAdjunto)) {
			listaCorreoAdjunto = new ArrayList<DominioAdjunto>();
		}
		return listaCorreoAdjunto;
	}

	public void setListaCorreoAdjunto(List<DominioAdjunto> listaCorreoAdjunto) {
		this.listaCorreoAdjunto = listaCorreoAdjunto;
	}


	public String getRemitenteNombre() {
		return remitenteNombre;
	}

	public void setRemitenteNombre(String remitenteNombre) {
		this.remitenteNombre = remitenteNombre;
	}

	public Boolean getFlgCorreoPrueba() {
		return flgCorreoPrueba;
	}

	public void setFlgCorreoPrueba(Boolean flgCorreoPrueba) {
		this.flgCorreoPrueba = flgCorreoPrueba;
	}

	public String getRemitenteCorreo() {
		return remitenteCorreo;
	}

	public void setRemitenteCorreo(String remitenteCorreo) {
		this.remitenteCorreo = remitenteCorreo;
	}

	public String getCuerpoCorreoBase64() {
		return cuerpoCorreoBase64;
	}

	public void setCuerpoCorreoBase64(String cuerpoCorreoBase64) {
		this.cuerpoCorreoBase64 = cuerpoCorreoBase64;
	}

	public String getTraceReferencia() {
		return traceReferencia;
	}

	public void setTraceReferencia(String traceReferencia) {
		this.traceReferencia = traceReferencia;
	}

	/**
	 * codigo o id de referencia nro de requerimiento, numero de convocatoria
	 */
	public String getReferenciaId() {
		return referenciaId;
	}

	/**
	 * codigo o id de referencia nro de requerimiento, numero de convocatoria
	 */
	public void setReferenciaId(String referenciaId) {
		this.referenciaId = referenciaId;
	}

	/**
	 * codigo o id de referencia nro de requerimiento, numero de convocatoria
	 */
	public String getReferenciaPadreId() {
		return referenciaPadreId;
	}

	/**
	 * codigo o id de referencia nro de requerimiento, numero de convocatoria
	 */
	public void setReferenciaPadreId(String referenciaPadreId) {
		this.referenciaPadreId = referenciaPadreId;
	}

	/**
	 * codigo de proceso, o codigo de WF_PROCESO
	 */
	public String getProcesoId() {
		return procesoId;
	}

	/**
	 * codigo de proceso, o codigo de WF_PROCESO
	 */
	public void setProcesoId(String procesoId) {
		this.procesoId = procesoId;
	}

	/**
	 * id de transaccion de workflow, o identificador unico de proceso
	 */
	public Integer getTransaccionId() {
		return transaccionId;
	}

	/**
	 * id de transaccion de workflow, o identificador unico de proceso
	 */
	public void setTransaccionId(Integer transaccionId) {
		this.transaccionId = transaccionId;
	}

	/**
	 * id de seguimiento de workflow o de seguimiento
	 */
	public Integer getSeguimientoId() {
		return seguimientoId;
	}

	/**
	 * id de seguimiento de workflow o de seguimiento
	 */
	public void setSeguimientoId(Integer seguimientoId) {
		this.seguimientoId = seguimientoId;
	}

	/**
	 * accion APROBAR, RECHAZAR, DEVOLVER, etc, un vervo de la accion
	 */
	public String getAccionId() {
		return accionId;
	}

	/**
	 * accion APROBAR, RECHAZAR, DEVOLVER, etc, un vervo de la accion
	 */
	public void setAccionId(String accionId) {
		this.accionId = accionId;
	}

	/**
	 * sub accion APROBAR, RECHAZAR, DEVOLVER, etc, un vervo de la accion
	 */
	public String getSubAccionId() {
		return subAccionId;
	}

	/**
	 * sub accion APROBAR, RECHAZAR, DEVOLVER, etc, un vervo de la accion
	 */
	public void setSubAccionId(String subAccionId) {
		this.subAccionId = subAccionId;
	}

	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}	
	
	
}
