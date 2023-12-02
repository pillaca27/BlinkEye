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
 * @tabla dbo.RTPS_EmpleadoEstablecimiento
*/
public class BeanRtpsEmpleadoEstablecimientoPk implements java.io.Serializable{

	@NotNull
	@Column(name = "EMPLEADO", nullable = false)
	private Integer empleado;

	@Size(min = 0, max = 8)
	@NotEmpty
	@Column(name = "COMPANIASOCIO", length = 8, nullable = false)
	private String companiasocio;

	@NotNull
	@Column(name = "CODIGOAUTOMATICO", nullable = false)
	private Integer codigoautomatico;

	public BeanRtpsEmpleadoEstablecimientoPk() {
	}

	public BeanRtpsEmpleadoEstablecimientoPk(Integer pempleado,String pcompaniasocio,Integer pcodigoautomatico) {
		this.empleado = pempleado;
		this.companiasocio = pcompaniasocio;
		this.codigoautomatico = pcodigoautomatico;
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


}
