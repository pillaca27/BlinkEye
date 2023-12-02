package net.royal.spring.dt.dominio.filtro;

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


/**
 * 
 * 
 * @tabla dbo.Clases
*/
public class FiltroClases extends DominioTransaccion implements java.io.Serializable{

	private Integer idClase;
	private String nombre;
	private DominioPaginacion paginacion;

	/**
	 * 
	 * 
	 * @campo ID_Clase
	*/
	public Integer getIdClase() {
		return idClase;
	}

	/**
	 * 
	 * 
	 * @campo ID_Clase
	*/
	public void setIdClase(Integer idClase) {
		this.idClase = idClase;
	}
	/**
	 * 
	 * 
	 * @campo Nombre
	*/
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * 
	 * @campo Nombre
	*/
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
