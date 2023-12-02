package net.royal.spring.core.dominio.filtro;

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
 * @tabla dbo.RTPS_EMPLEADOESTABLECIMIENTO
*/
public class FiltroComunRtpsEmpleadoEstablecimiento extends DominioTransaccion implements java.io.Serializable{

	private Integer empleado;
	private String companiasocio;
	private DominioPaginacion paginacion;

	/**
	 * 
	 * 
	 * @campo CodigoAutomatico
	*/
	public Integer getEmpleado() {
		return empleado;
	}

	/**
	 * 
	 * 
	 * @campo CodigoAutomatico
	*/
	public void setEmpleado(Integer empleado) {
		this.empleado = empleado;
	}
	/**
	 * 
	 * 
	 * @campo Estado
	*/
	public String getCompaniasocio() {
		return companiasocio;
	}

	/**
	 * 
	 * 
	 * @campo Estado
	*/
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
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
