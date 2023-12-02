package net.royal.spring.framework.modelo;

import java.math.BigDecimal;

public class WorkFlowAprobadorTransaccion {
	private BigDecimal nivelId;
	private BigDecimal personaId;
	
	
	public WorkFlowAprobadorTransaccion() {
		
	}
	
	public WorkFlowAprobadorTransaccion(BigDecimal nivelIda, BigDecimal personaIda) {
		
		this.nivelId = nivelIda;
		this.personaId = personaIda;
		
	}
	
	
	public BigDecimal getNivelId() {
		return nivelId;
	}
	public void setNivelId(BigDecimal nivelId) {
		this.nivelId = nivelId;
	}
	public BigDecimal getPersonaId() {
		return personaId;
	}
	public void setPersonaId(BigDecimal personaId) {
		this.personaId = personaId;
	}	
}