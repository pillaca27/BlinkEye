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
	private Integer idClase;
	private java.util.Date fechaHora;
	private Integer duracion;

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
	public Integer getIdClase() {
		return idClase;
	}

	/**
	 * 
	 * 
	 * @campo ID_Clase
	*/
	public void setIdClase(Integer idClase) {
		this.idClase = idClase;
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
			bean.setIdClase(idClase);
			bean.setFechaHora(fechaHora);
			bean.setDuracion(duracion);

			break;
		case ConstantePantallaAccion.ACTUALIZAR:

			break;
		}

		return bean;
	}

}
