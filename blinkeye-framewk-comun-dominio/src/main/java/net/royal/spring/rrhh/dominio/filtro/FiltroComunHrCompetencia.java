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
 * @tabla dbo.HR_Competencia
*/
public class FiltroComunHrCompetencia extends DominioTransaccion implements java.io.Serializable{

	private Integer competencia;
	private String descripcion;
	private String estado;
	private String flagevaluacion;
	private String maestro;
	private Integer comportamiento;
	private String comportadesc;
	private Integer idcompeten;
	
	private DominioPaginacion paginacion;

	/**
	 * 
	 * 
	 * @campo Competencia
	*/
	public Integer getCompetencia() {
		return competencia;
	}

	/**
	 * 
	 * 
	 * @campo Competencia
	*/
	public void setCompetencia(Integer competencia) {
		this.competencia = competencia;
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
	/**
	 * 
	 * 
	 * @campo Estado
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * 
	 * 
	 * @campo Estado
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public DominioPaginacion getPaginacion() {
		if (paginacion == null)
			paginacion = new DominioPaginacion();
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public Integer getComportamiento() {
		return comportamiento;
	}

	public void setComportamiento(Integer comportamiento) {
		this.comportamiento = comportamiento;
	}

	public String getFlagevaluacion() {
		return flagevaluacion;
	}

	public void setFlagevaluacion(String flagevaluacion) {
		this.flagevaluacion = flagevaluacion;
	}

	public String getMaestro() {
		return maestro;
	}

	public void setMaestro(String maestro) {
		this.maestro = maestro;
	}

	public String getComportadesc() {
		return comportadesc;
	}

	public void setComportadesc(String comportadesc) {
		this.comportadesc = comportadesc;
	}

	public Integer getIdcompeten() {
		return idcompeten;
	}

	public void setIdcompeten(Integer idcompeten) {
		this.idcompeten = idcompeten;
	}

}
