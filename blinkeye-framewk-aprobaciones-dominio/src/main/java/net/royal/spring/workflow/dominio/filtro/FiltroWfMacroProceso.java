package net.royal.spring.workflow.dominio.filtro;

import net.royal.spring.workflow.dominio.WfMacroProceso;

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
 * @tabla sgworkflowsys.WF_MACRO_PROCESO
*/
public class FiltroWfMacroProceso extends DominioTransaccion implements java.io.Serializable{

	private String macroProcesoId;
	private String nombre;
	private String estado;
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
	 * @campo NOMBRE
	*/
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * 
	 * @campo NOMBRE
	*/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * 
	 * 
	 * @campo ESTADO
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * 
	 * 
	 * @campo ESTADO
	*/
	public void setEstado(String estado) {
		this.estado = estado;
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
