package net.royal.spring.hr.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

/**
 * QQUECHOD VALIDADO
 * @author QquechoD
 *
 */
public class DtoComunHrCursodescripcion extends DominioTransaccion{	
	private Integer curso;
	private String descripcion;
	private String tipo;	
	private String area;	
	private String estado;
	private String unidadnegocio;
	private String nivelacademico;
	private String carrera;
	private BigDecimal costoestimado;
	private String areaNombre;
	private String tiponombre;
	
	public DtoComunHrCursodescripcion() {}
	public DtoComunHrCursodescripcion(Integer curso) {
		this.curso=curso;
	}
		
	/* solo sirve para la paginacion */
	@JsonIgnore
	private BigDecimal ROWNUM_;
	
	
	public String getTiponombre() {
		return tiponombre;
	}
	public void setTiponombre(String tiponombre) {
		this.tiponombre = tiponombre;
	}
	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUnidadnegocio() {
		return unidadnegocio;
	}

	public void setUnidadnegocio(String unidadnegocio) {
		this.unidadnegocio = unidadnegocio;
	}

	public String getNivelacademico() {
		return nivelacademico;
	}

	public void setNivelacademico(String nivelacademico) {
		this.nivelacademico = nivelacademico;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public BigDecimal getCostoestimado() {
		return costoestimado;
	}

	public void setCostoestimado(BigDecimal costoestimado) {
		this.costoestimado = costoestimado;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

	public String getAreaNombre() {
		return areaNombre;
	}

	public void setAreaNombre(String areaNombre) {
		this.areaNombre = areaNombre;
	}
	
}