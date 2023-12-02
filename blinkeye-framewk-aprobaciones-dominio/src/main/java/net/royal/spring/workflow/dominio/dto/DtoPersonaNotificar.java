package net.royal.spring.workflow.dominio.dto;

public class DtoPersonaNotificar {
	
	private Integer transaccionId;
	private Integer personaId;
	private String referenciaDescripcion;
	
	private String auxlink;

	public Integer getTransaccionId() {
		return transaccionId;
	}

	public void setTransaccionId(Integer transaccionId) {
		this.transaccionId = transaccionId;
	}

	public Integer getPersonaId() {
		return personaId;
	}

	public void setPersonaId(Integer personaId) {
		this.personaId = personaId;
	}

	public String getAuxlink() {
		return auxlink;
	}

	public void setAuxlink(String auxlink) {
		this.auxlink = auxlink;
	}

	public String getReferenciaDescripcion() {
		return referenciaDescripcion;
	}

	public void setReferenciaDescripcion(String referenciaDescripcion) {
		this.referenciaDescripcion = referenciaDescripcion;
	}
}
