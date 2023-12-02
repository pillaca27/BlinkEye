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
 * @tabla dbo.ServicioXImpuesto
*/
public class BeanServicioximpuestoPk implements java.io.Serializable{



	@Size(min = 0, max = 6)
	@NotNull
	@NotEmpty
	@Column(name = "TIPOSERVICIO", length = 6, nullable = false)
	private String tiposervicio;

	@Size(min = 0, max = 3)
	@NotNull
	@NotEmpty
	@Column(name = "IMPUESTO", length = 3, nullable = false)
	private String impuesto;


	public BeanServicioximpuestoPk() {
	}

	public BeanServicioximpuestoPk(String ptiposervicio,String pimpuesto) {
this.tiposervicio = ptiposervicio;
		this.impuesto = pimpuesto;
	}

	/**
	 * 
	 * 
	 * @campo TipoServicio
	*/
	public String getTiposervicio() {
		return tiposervicio;
	}

	/**
	 * 
	 * 
	 * @campo TipoServicio
	*/
	public void setTiposervicio(String tiposervicio) {
		this.tiposervicio = tiposervicio;
	}
	/**
	 * 
	 * 
	 * @campo Impuesto
	*/
	public String getImpuesto() {
		return impuesto;
	}

	/**
	 * 
	 * 
	 * @campo Impuesto
	*/
	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}

}
