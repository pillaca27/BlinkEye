package net.royal.spring.logistica.dominio.filtro;

import net.royal.spring.logistica.dominio.BeanWhClasesubfamilia;

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
 * @tabla dbo.WH_ClaseSubFamilia
*/
public class FiltroComunWhClasesubfamilia extends DominioTransaccion implements java.io.Serializable{


	private String linea;
	private String familia;
	private String subfamilia;

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
	/**
	 * 
	 * 
	 * @campo SubFamilia
	*/
	public String getSubfamilia() {
		return subfamilia;
	}

	/**
	 * 
	 * 
	 * @campo SubFamilia
	*/
	public void setSubfamilia(String subfamilia) {
		this.subfamilia = subfamilia;
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
