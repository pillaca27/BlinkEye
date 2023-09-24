package net.royal.spring.sy.dominio.filtro;

import net.royal.spring.sy.dominio.BeanSyUnidadreplicacion;

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
 * @tabla dbo.SY_UnidadReplicacion
*/
public class FiltroComunSyUnidadreplicacion extends DominioTransaccion implements java.io.Serializable{


	private String unidadreplicacion;
	private String tipoexportar;
	
	public String getTipoexportar() {
		return tipoexportar;
	}
	public void setTipoexportar(String tipoexportar) {
		this.tipoexportar = tipoexportar;
	}
	/**
	 * 
	 * 
	 * @campo UnidadReplicacion
	*/
	public String getUnidadreplicacion() {
		return unidadreplicacion;
	}

	/**
	 * 
	 * 
	 * @campo UnidadReplicacion
	*/
	public void setUnidadreplicacion(String unidadreplicacion) {
		this.unidadreplicacion = unidadreplicacion;
	}

	private DominioPaginacion paginacion;

	public DominioPaginacion getPaginacion() {
		if (paginacion == null)
			paginacion = new DominioPaginacion();
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}


}
