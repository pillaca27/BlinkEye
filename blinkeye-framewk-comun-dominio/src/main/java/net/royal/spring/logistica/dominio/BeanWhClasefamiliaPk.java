package net.royal.spring.logistica.dominio;

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
 * @tabla dbo.WH_ClaseFamilia
*/
public class BeanWhClasefamiliaPk implements java.io.Serializable{



	@Size(min = 0, max = 6)
	@NotNull
	@NotEmpty
	@Column(name = "LINEA", length = 6, nullable = false)
	private String linea;

	@Size(min = 0, max = 6)
	@NotNull
	@NotEmpty
	@Column(name = "FAMILIA", length = 6, nullable = false)
	private String familia;


	public BeanWhClasefamiliaPk() {
	}

	public BeanWhClasefamiliaPk(String plinea,String pfamilia) {
this.linea = plinea;
		this.familia = pfamilia;
	}

	/**
	 * 
	 * 
	 * @campo Linea
	*/
	public String getLinea() {
		return linea;
	}

	/**
	 * 
	 * 
	 * @campo Linea
	*/
	public void setLinea(String linea) {
		this.linea = linea;
	}
	/**
	 * 
	 * 
	 * @campo Familia
	*/
	public String getFamilia() {
		return familia;
	}

	/**
	 * 
	 * 
	 * @campo Familia
	*/
	public void setFamilia(String familia) {
		this.familia = familia;
	}

}
