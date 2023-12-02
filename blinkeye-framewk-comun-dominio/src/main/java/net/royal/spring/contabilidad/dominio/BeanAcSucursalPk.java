package net.royal.spring.contabilidad.dominio;

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
 * @tabla dbo.AC_Sucursal
*/
public class BeanAcSucursalPk implements java.io.Serializable{



	@Size(min = 0, max = 4)
	@NotNull
	@NotEmpty
	@Column(name = "SUCURSAL", length = 4, nullable = false)
	private String sucursal;


	public BeanAcSucursalPk() {
	}

	public BeanAcSucursalPk(String psucursal) {
this.sucursal = psucursal;
	}

	/**
	 * 
	 * 
	 * @campo Sucursal
	*/
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * 
	 * 
	 * @campo Sucursal
	*/
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

}
