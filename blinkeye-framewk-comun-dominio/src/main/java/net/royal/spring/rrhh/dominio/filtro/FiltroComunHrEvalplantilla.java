package net.royal.spring.rrhh.dominio.filtro;

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

import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.HR_EvalPlantilla
*/
public class FiltroComunHrEvalplantilla extends DominioTransaccion implements java.io.Serializable{

	private String companyowner;
	private Integer evaluacion;
	private String plantilla;
	private String descripcion;
	private DominioPaginacion paginacion;

	/**
	 * 
	 * 
	 * @campo CompanyOwner
	*/
	public String getCompanyowner() {
		return companyowner;
	}

	/**
	 * 
	 * 
	 * @campo CompanyOwner
	*/
	public void setCompanyowner(String companyowner) {
		this.companyowner = companyowner;
	}
	/**
	 * 
	 * 
	 * @campo Evaluacion
	*/
	public Integer getEvaluacion() {
		return evaluacion;
	}

	/**
	 * 
	 * 
	 * @campo Evaluacion
	*/
	public void setEvaluacion(Integer evaluacion) {
		this.evaluacion = evaluacion;
	}
	/**
	 * 
	 * 
	 * @campo Plantilla
	*/
	public String getPlantilla() {
		return plantilla;
	}

	/**
	 * 
	 * 
	 * @campo Plantilla
	*/
	public void setPlantilla(String plantilla) {
		this.plantilla = plantilla;
	}
	/**
	 * 
	 * 
	 * @campo Descripcion
	*/
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * 
	 * 
	 * @campo Descripcion
	*/
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
