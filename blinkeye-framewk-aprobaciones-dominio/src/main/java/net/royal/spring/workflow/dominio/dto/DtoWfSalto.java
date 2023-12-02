package net.royal.spring.workflow.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoWfSalto extends DominioTransaccion{

	private Integer nivel;
	private Integer transaccionId;

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Integer getTransaccionId() {
		return transaccionId;
	}

	public void setTransaccionId(Integer transaccionId) {
		this.transaccionId = transaccionId;
	}
}
