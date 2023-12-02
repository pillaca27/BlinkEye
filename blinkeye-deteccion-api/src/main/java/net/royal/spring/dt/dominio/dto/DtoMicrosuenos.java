package net.royal.spring.dt.dominio.dto;

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
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;
import net.royal.spring.dt.dominio.Microsuenos;


/**
 * 
 * 
 * @tabla dbo.Microsuenos
*/
public class DtoMicrosuenos extends DominioTransaccion implements java.io.Serializable{

	private Integer idMicrosueno;
	private Integer idAlumno;
	private Integer idSesion;
	private java.util.Date fechaHora;
	private Integer duracion;
	
	private String auxNombreClase;
	private String auxNombreDocente;
	private String auxApellidoDocente;
	private String auxNombreAlumno;
	private String auxApellidoAlumo;
	private String auxEmailAlumno;
	private java.util.Date fechahoraInicio;
	private java.util.Date fechahoraFin;
	private Integer totalMicrosuenos;
	private Integer DuraciontotalMicrosuenos;
	

	/**
	 * 
	 * 
	 * @campo ID_Microsueno
	*/
	public Integer getIdMicrosueno() {
		return idMicrosueno;
	}

	/**
	 * 
	 * 
	 * @campo ID_Microsueno
	*/
	public void setIdMicrosueno(Integer idMicrosueno) {
		this.idMicrosueno = idMicrosueno;
	}
	/**
	 * 
	 * 
	 * @campo ID_Alumno
	*/
	public Integer getIdAlumno() {
		return idAlumno;
	}

	/**
	 * 
	 * 
	 * @campo ID_Alumno
	*/
	public void setIdAlumno(Integer idAlumno) {
		this.idAlumno = idAlumno;
	}
	/**
	 * 
	 * 
	 * @campo ID_Clase
	*/
	public Integer getIdSesion() {
		return idSesion;
	}

	/**
	 * 
	 * 
	 * @campo ID_Clase
	*/
	public void setIdSesion(Integer idSesion) {
		this.idSesion = idSesion;
	}
	/**
	 * 
	 * 
	 * @campo Fecha_Hora
	*/
	public java.util.Date getFechaHora() {
		return fechaHora;
	}

	/**
	 * 
	 * 
	 * @campo Fecha_Hora
	*/
	public void setFechaHora(java.util.Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	/**
	 * 
	 * 
	 * @campo Duración
	*/
	public Integer getDuracion() {
		return duracion;
	}

	/**
	 * 
	 * 
	 * @campo Duración
	*/
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}


	public Microsuenos obtenerBean() {
		return obtenerBeanCore(new Microsuenos(),ConstantePantallaAccion.NINGUNO);
	}

	public Microsuenos obtenerBeanRegistrar() {
		return obtenerBeanCore(new Microsuenos(),ConstantePantallaAccion.INSERTAR);
	}

	public Microsuenos obtenerBeanActualizar(Microsuenos bean) {
		return obtenerBeanCore(bean,ConstantePantallaAccion.ACTUALIZAR);
	}

	private Microsuenos obtenerBeanCore(Microsuenos bean,String tipo) {
		if (UString.esNuloVacio(tipo))
			tipo=ConstantePantallaAccion.NINGUNO;

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		switch (tipo) {
		case ConstantePantallaAccion.INSERTAR,ConstantePantallaAccion.NINGUNO:
			bean.getPk().setIdMicrosueno(idMicrosueno);
			bean.setIdAlumno(idAlumno);
			bean.setIdSesion(idSesion);
			bean.setFechaHora(fechaHora);
			bean.setDuracion(duracion);

			break;
		case ConstantePantallaAccion.ACTUALIZAR:

			break;
		}

		return bean;
	}

	public String getAuxNombreClase() {
		return auxNombreClase;
	}

	public void setAuxNombreClase(String auxNombreClase) {
		this.auxNombreClase = auxNombreClase;
	}

	public String getAuxNombreDocente() {
		return auxNombreDocente;
	}

	public void setAuxNombreDocente(String auxNombreDocente) {
		this.auxNombreDocente = auxNombreDocente;
	}

	public String getAuxApellidoDocente() {
		return auxApellidoDocente;
	}

	public void setAuxApellidoDocente(String auxApellidoDocente) {
		this.auxApellidoDocente = auxApellidoDocente;
	}

	public String getAuxNombreAlumno() {
		return auxNombreAlumno;
	}

	public void setAuxNombreAlumno(String auxNombreAlumno) {
		this.auxNombreAlumno = auxNombreAlumno;
	}

	public String getAuxApellidoAlumo() {
		return auxApellidoAlumo;
	}

	public void setAuxApellidoAlumo(String auxApellidoAlumo) {
		this.auxApellidoAlumo = auxApellidoAlumo;
	}

	public String getAuxEmailAlumno() {
		return auxEmailAlumno;
	}

	public void setAuxEmailAlumno(String auxEmailAlumno) {
		this.auxEmailAlumno = auxEmailAlumno;
	}

	public java.util.Date getFechahoraInicio() {
		return fechahoraInicio;
	}

	public void setFechahoraInicio(java.util.Date fechahoraInicio) {
		this.fechahoraInicio = fechahoraInicio;
	}

	public java.util.Date getFechahoraFin() {
		return fechahoraFin;
	}

	public void setFechahoraFin(java.util.Date fechahoraFin) {
		this.fechahoraFin = fechahoraFin;
	}

	public Integer getTotalMicrosuenos() {
		return totalMicrosuenos;
	}

	public void setTotalMicrosuenos(Integer totalMicrosuenos) {
		this.totalMicrosuenos = totalMicrosuenos;
	}

	public Integer getDuraciontotalMicrosuenos() {
		return DuraciontotalMicrosuenos;
	}

	public void setDuraciontotalMicrosuenos(Integer duraciontotalMicrosuenos) {
		DuraciontotalMicrosuenos = duraciontotalMicrosuenos;
	}

}
