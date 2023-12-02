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
 * @tabla dbo.Microsuenos
*/
public class FiltroMicrosuenos extends DominioTransaccion implements java.io.Serializable{

	private Integer idMicrosueno;
	private DominioPaginacion paginacion;

	/**
	 * 
	 * 
	 * @campo ID_Microsueno
	*/
	public Integer getIdMicrosueno() {
		return idMicrosueno;
	}

	/**
	 * 
	 * 
	 * @campo ID_Microsueno
	*/
	public void setIdMicrosueno(Integer idMicrosueno) {
		this.idMicrosueno = idMicrosueno;
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
