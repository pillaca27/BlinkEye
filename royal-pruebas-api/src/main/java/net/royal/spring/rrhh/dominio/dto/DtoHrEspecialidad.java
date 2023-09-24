package net.royal.spring.rrhh.dominio.dto;

import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.rrhh.dominio.BeanHrEspecialidad;

public class DtoHrEspecialidad extends DominioTransaccion{
	
	private Integer especialidad;
	
	private String estado;
	private String descripcion;
	private String ultimoUsuario;
	private Date ultimaFechaModif;
	private String unidadReplicacion;
	
	public BeanHrEspecialidad obtenerBean() {
		BeanHrEspecialidad bean = new BeanHrEspecialidad(); 
		bean.getPk().setEspecialidad(especialidad);
		bean.setDescripcion(descripcion);
		bean.setEstado(estado);
		bean.setUltimaFechaModif(ultimaFechaModif);
		bean.setUltimoUsuario(ultimoUsuario);
		bean.setUnidadReplicacion(unidadReplicacion);
		return bean;
	}
	
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
	
}
