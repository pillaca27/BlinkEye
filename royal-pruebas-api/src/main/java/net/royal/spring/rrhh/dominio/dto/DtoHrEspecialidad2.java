package net.royal.spring.rrhh.dominio.dto;

import java.io.Serializable;
import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.rrhh.dominio.BeanHrEspecialidad2;

public class DtoHrEspecialidad2 extends DominioTransaccion{

	// PK
	private Integer especialidad;

	// CAMPOS
	private String estado;
	private String descripcion;
	private String ultimoUsuario;
	private Date ultimaFechaModif;
	private String unidadReplicacion;
	private Integer empleadoSolicitante;

	// EXTRAS
	private String auxSolicitanteNombre;

	public Integer getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Integer especialidad) {
		this.especialidad = especialidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUltimoUsuario() {
		return ultimoUsuario;
	}

	public void setUltimoUsuario(String ultimoUsuario) {
		this.ultimoUsuario = ultimoUsuario;
	}

	public Date getUltimaFechaModif() {
		return ultimaFechaModif;
	}

	public void setUltimaFechaModif(Date ultimaFechaModif) {
		this.ultimaFechaModif = ultimaFechaModif;
	}

	public String getUnidadReplicacion() {
		return unidadReplicacion;
	}

	public void setUnidadReplicacion(String unidadReplicacion) {
		this.unidadReplicacion = unidadReplicacion;
	}

	public Integer getEmpleadoSolicitante() {
		return empleadoSolicitante;
	}

	public void setEmpleadoSolicitante(Integer empleadoSolicitante) {
		this.empleadoSolicitante = empleadoSolicitante;
	}
	
	public String getAuxSolicitanteNombre() {
		return auxSolicitanteNombre;
	}

	public void setAuxSolicitanteNombre(String auxSolicitanteNombre) {
		this.auxSolicitanteNombre = auxSolicitanteNombre;
	}
	
//	public BeanHrEspecialidad2 obtenerBean() {
//		BeanHrEspecialidad2 bean = new BeanHrEspecialidad2();
//		return obtenerBean(bean);
//	}

	public BeanHrEspecialidad2 obtenerBean() {

		BeanHrEspecialidad2 bean = new BeanHrEspecialidad2();

		bean.getPk().setEspecialidad(especialidad);
		bean.setDescripcion(descripcion);
		bean.setEstado(estado);
		bean.setUltimaFechaModif(ultimaFechaModif);
		bean.setUltimoUsuario(ultimoUsuario);
		bean.setUnidadReplicacion(unidadReplicacion);
		bean.setEmpleadoSolicitante(empleadoSolicitante);
		
		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgPreparado());
		
		return bean;
	}
	
	

}
