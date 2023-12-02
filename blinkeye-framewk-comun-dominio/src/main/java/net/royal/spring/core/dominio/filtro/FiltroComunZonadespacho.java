package net.royal.spring.core.dominio.filtro;

import net.royal.spring.core.dominio.BeanZonadespacho;

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
 * Maestro de zona de despacho
 * 
 * @tabla SPRING.ZONADESPACHO
*/
public class FiltroComunZonadespacho extends DominioTransaccion implements java.io.Serializable{


	private String zonadespacho;

	/**
	 * codigo de zona despacho
	 * 
	 * @campo ZONADESPACHO
	*/
	public String getZonadespacho() {
		return zonadespacho;
	}

	/**
	 * codigo de zona despacho
	 * 
	 * @campo ZONADESPACHO
	*/
	public void setZonadespacho(String zonadespacho) {
		this.zonadespacho = zonadespacho;
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
