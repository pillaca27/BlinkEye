package net.royal.spring.workflow.dominio.filtro;

import net.royal.spring.workflow.dominio.WfMacroProcesoDetalle;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla sgworkflowsys.WF_MACRO_PROCESO_DETALLE
*/
public class FiltroWfMacroProcesoDetalle extends DominioTransaccion implements java.io.Serializable{

	private String macroProcesoId;
	private String procesoOrigenId;
	private String procesoDestinoId;
	private DominioPaginacion paginacion;

	/**
	 * 
	 * 
	 * @campo MACRO_PROCESO_ID
	*/
	public String getMacroProcesoId() {
		return macroProcesoId;
	}

	/**
	 * 
	 * 
	 * @campo MACRO_PROCESO_ID
	*/
	public void setMacroProcesoId(String macroProcesoId) {
		this.macroProcesoId = macroProcesoId;
	}
	/**
	 * 
	 * 
	 * @campo PROCESO_ORIGEN_ID
	*/
	public String getProcesoOrigenId() {
		return procesoOrigenId;
	}

	/**
	 * 
	 * 
	 * @campo PROCESO_ORIGEN_ID
	*/
	public void setProcesoOrigenId(String procesoOrigenId) {
		this.procesoOrigenId = procesoOrigenId;
	}
	/**
	 * 
	 * 
	 * @campo PROCESO_DESTINO_ID
	*/
	public String getProcesoDestinoId() {
		return procesoDestinoId;
	}

	/**
	 * 
	 * 
	 * @campo PROCESO_DESTINO_ID
	*/
	public void setProcesoDestinoId(String procesoDestinoId) {
		this.procesoDestinoId = procesoDestinoId;
	}

	public DominioPaginacion getPaginacion() {
		if (paginacion == null)
			paginacion = new DominioPaginacion();
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}


}
