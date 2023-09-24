package net.royal.spring.rrhh.dominio.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.rrhh.dominio.BeanHrCapacitacion;

public class DtoHrCapacitacion extends DominioTransaccion{

	private String capacitacion;
	private String companyOwner;
	private Integer curso;
	private String descripcion;
	private String estado;
	private Date fechaSolicitud;
	private Date fechaDesde;
	private Date fechaHasta;
	private Integer empleadoSolicitante;
	private String ultimoUsuario;
	private Date ultimaFechaModif;

	private String auxSolicitanteNombre;
	private String auxCursoNombre;
	private String auxEstadoNombre;
	private Integer auxCantidadParticipantes;
	private String archivo;
	private String archivodatastring;

	public String getArchivodatastring() {
		return archivodatastring;
	}

	public void setArchivodatastring(String archivodatastring) {
		this.archivodatastring = archivodatastring;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	private List<DtoHrEmpleadoCapacitacion> lstParticipantes;

	public DtoHrCapacitacion() {
		this.lstParticipantes = new ArrayList<DtoHrEmpleadoCapacitacion>();
	}

	public String getCapacitacion() {
		return capacitacion;
	}

	public void setCapacitacion(String capacitacion) {
		this.capacitacion = capacitacion;
	}

	public String getCompanyOwner() {
		return companyOwner;
	}

	public void setCompanyOwner(String companyOwner) {
		this.companyOwner = companyOwner;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Integer getEmpleadoSolicitante() {
		return empleadoSolicitante;
	}

	public void setEmpleadoSolicitante(Integer empleadoSolicitante) {
		this.empleadoSolicitante = empleadoSolicitante;
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

	public String getAuxSolicitanteNombre() {
		return auxSolicitanteNombre;
	}

	public void setAuxSolicitanteNombre(String auxSolicitanteNombre) {
		this.auxSolicitanteNombre = auxSolicitanteNombre;
	}

	public String getAuxCursoNombre() {
		return auxCursoNombre;
	}

	public void setAuxCursoNombre(String auxCursoNombre) {
		this.auxCursoNombre = auxCursoNombre;
	}

	public String getAuxEstadoNombre() {
		return auxEstadoNombre;
	}

	public void setAuxEstadoNombre(String auxEstadoNombre) {
		this.auxEstadoNombre = auxEstadoNombre;
	}

	public Integer getAuxCantidadParticipantes() {
		return auxCantidadParticipantes;
	}

	public void setAuxCantidadParticipantes(Integer auxCantidadParticipantes) {
		this.auxCantidadParticipantes = auxCantidadParticipantes;
	}

	public List<DtoHrEmpleadoCapacitacion> getLstParticipantes() {
		return lstParticipantes;
	}

	public void setLstParticipantes(List<DtoHrEmpleadoCapacitacion> lstParticipantes) {
		this.lstParticipantes = lstParticipantes;
	}

	public BeanHrCapacitacion obtenerBean() {
		BeanHrCapacitacion bean = new BeanHrCapacitacion(); 
		bean.getPk().setCapacitacion(capacitacion);
		bean.getPk().setCompanyOwner(companyOwner);
		bean.setCurso(curso);
		bean.setDescripcion(descripcion);
		bean.setEmpleadoSolicitante(empleadoSolicitante);
		bean.setEstado(estado);
		bean.setFechaSolicitud(fechaSolicitud);
		bean.setFechaDesde(fechaDesde);
		bean.setFechaHasta(fechaHasta);
		bean.setUltimaFechaModif(ultimaFechaModif);
		bean.setUltimoUsuario(ultimoUsuario);
		return bean;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
}
