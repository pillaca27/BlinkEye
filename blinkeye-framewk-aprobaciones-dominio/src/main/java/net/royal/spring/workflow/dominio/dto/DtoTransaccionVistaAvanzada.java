package net.royal.spring.workflow.dominio.dto;

public class DtoTransaccionVistaAvanzada {
	
	private String transaccionUUID;
	private Integer transaccionId;
	private Integer flujoId;
	private Integer nivelId;
	private String criterios;
	private Integer solicitanteId;
	private String solicitanteNombre;
	private Integer personaReferenciaId;
	private String personaReferenciaNombre;
	private String referencia;
	private String estado;
	private String nivelEstadoId;
	private String nivelEstadoNombre;
	
	private String nivelEstadoSiguienteId;
	private String nivelEstadoSiguienteNombre;
	
	private String procesoId;
	private String procesoUUID;

	public Integer getTransaccionId() {
		return transaccionId;
	}

	public void setTransaccionId(Integer transaccionId) {
		this.transaccionId = transaccionId;
	}

	public Integer getFlujoId() {
		return flujoId;
	}

	public void setFlujoId(Integer flujoId) {
		this.flujoId = flujoId;
	}

	public Integer getNivelId() {
		return nivelId;
	}

	public void setNivelId(Integer nivelId) {
		this.nivelId = nivelId;
	}

	public String getCriterios() {
		return criterios;
	}

	public void setCriterios(String criterios) {
		this.criterios = criterios;
	}

	public Integer getSolicitanteId() {
		return solicitanteId;
	}

	public void setSolicitanteId(Integer solicitanteId) {
		this.solicitanteId = solicitanteId;
	}

	public String getSolicitanteNombre() {
		return solicitanteNombre;
	}

	public void setSolicitanteNombre(String solicitanteNombre) {
		this.solicitanteNombre = solicitanteNombre;
	}

	public Integer getPersonaReferenciaId() {
		return personaReferenciaId;
	}

	public void setPersonaReferenciaId(Integer personaReferenciaId) {
		this.personaReferenciaId = personaReferenciaId;
	}

	public String getPersonaReferenciaNombre() {
		return personaReferenciaNombre;
	}

	public void setPersonaReferenciaNombre(String personaReferenciaNombre) {
		this.personaReferenciaNombre = personaReferenciaNombre;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNivelEstadoId() {
		return nivelEstadoId;
	}

	public void setNivelEstadoId(String nivelEstadoId) {
		this.nivelEstadoId = nivelEstadoId;
	}

	public String getNivelEstadoNombre() {
		return nivelEstadoNombre;
	}

	public void setNivelEstadoNombre(String nivelEstadoNombre) {
		this.nivelEstadoNombre = nivelEstadoNombre;
	}

	public String getNivelEstadoSiguienteId() {
		return nivelEstadoSiguienteId;
	}

	public void setNivelEstadoSiguienteId(String nivelEstadoSiguienteId) {
		this.nivelEstadoSiguienteId = nivelEstadoSiguienteId;
	}

	public String getNivelEstadoSiguienteNombre() {
		return nivelEstadoSiguienteNombre;
	}

	public void setNivelEstadoSiguienteNombre(String nivelEstadoSiguienteNombre) {
		this.nivelEstadoSiguienteNombre = nivelEstadoSiguienteNombre;
	}

	public String getProcesoId() {
		return procesoId;
	}

	public void setProcesoId(String procesoId) {
		this.procesoId = procesoId;
	}

	public String getTransaccionUUID() {
		return transaccionUUID;
	}

	public void setTransaccionUUID(String transaccionUUID) {
		this.transaccionUUID = transaccionUUID;
	}

	public String getProcesoUUID() {
		return procesoUUID;
	}

	public void setProcesoUUID(String procesoUUID) {
		this.procesoUUID = procesoUUID;
	}

}
