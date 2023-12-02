package net.royal.spring.framework.modelo;

import java.util.ArrayList;
import java.util.List;

import net.royal.spring.framework.modelo.correo.EmailDestino;
import net.royal.spring.framework.modelo.generico.DominioAdjunto;

public class WorkFlowEnvioCorreoDetalle {	
	private Integer transaccionId;
	private List<EmailDestino> listaCorreoDestino;
	private List<DominioAdjunto> listaCorreoAdjunto;
	
	public Integer getTransaccionId() {
		return transaccionId;
	}
	public void setTransaccionId(Integer transaccionId) {
		this.transaccionId = transaccionId;
	}
	public List<EmailDestino> getListaCorreoDestino() {
		if (listaCorreoDestino==null)
			listaCorreoDestino=new ArrayList<EmailDestino>();
		return listaCorreoDestino;
	}
	public void setListaCorreoDestino(List<EmailDestino> listaCorreoDestino) {
		this.listaCorreoDestino = listaCorreoDestino;
	}
	public List<DominioAdjunto> getListaCorreoAdjunto() {
		if (listaCorreoAdjunto==null)
			listaCorreoAdjunto=new ArrayList<DominioAdjunto>();
		return listaCorreoAdjunto;
	}
	public void setListaCorreoAdjunto(List<DominioAdjunto> listaCorreoAdjunto) {
		this.listaCorreoAdjunto = listaCorreoAdjunto;
	}	
}	
