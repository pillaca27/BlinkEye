package net.royal.spring.core.dominio;

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
 * @tabla dbo.RTPS_EmpleadoEstablecimientoEx
*/
public class BeanRtpsEmpleadoEstablecimientoExternoPk implements java.io.Serializable{


	@NotNull
	@Column(name = "CODIGOAUTOMATICO", nullable = false)
	private Integer codigoautomatico;

	@NotNull
	@Column(name = "empleado", length = 4, nullable = false)
	private Integer empleado;

	@Size(min = 0, max = 8)
	@Column(name = "companiasocio", length = 8, nullable = true)
	private String companiasocio;

	public BeanRtpsEmpleadoEstablecimientoExternoPk() {
	}

	public BeanRtpsEmpleadoEstablecimientoExternoPk(Integer pcodigoautomatico) {
		this.codigoautomatico = pcodigoautomatico;
	}

	/**
	 * 
	 * 
	 * @campo CodigoAutomatico
	*/
	public Integer getCodigoautomatico() {
		return codigoautomatico;
	}

	/**
	 * 
	 * 
	 * @campo CodigoAutomatico
	*/
	public void setCodigoautomatico(Integer codigoautomatico) {
		this.codigoautomatico = codigoautomatico;
	}
	/**
	 * 
	 * 
	 * @campo Empleado
	*/
	public Integer getEmpleado() {
		return empleado;
	}
	/**
	 * 
	 * 
	 * @campo Empleado
	*/
	public void setEmpleado(Integer empleado) {
		this.empleado = empleado;
	}
	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	*/
	public String getCompaniasocio() {
		return companiasocio;
	}
	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	*/
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}

}
