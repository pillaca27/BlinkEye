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
import net.royal.spring.dt.dominio.Clases;

/**
 * 
 * 
 * @tabla dbo.Clases
*/
public class DtoClases extends DominioTransaccion implements java.io.Serializable{

	private Integer idClase;
	private String nombre;
//	private java.util.Date fechaHoraInicio;
//	private java.util.Date fechaHoraFin;
	private Integer idDocente;

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
	 * @campo Nombre
	*/
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * 
	 * @campo Nombre
	*/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
//	/**
//	 * 
//	 * 
//	 * @campo Fecha_Hora_Inicio
//	*/
//	public java.util.Date getFechaHoraInicio() {
//		return fechaHoraInicio;
//	}
//
//	/**
//	 * 
//	 * 
//	 * @campo Fecha_Hora_Inicio
//	*/
//	public void setFechaHoraInicio(java.util.Date fechaHoraInicio) {
//		this.fechaHoraInicio = fechaHoraInicio;
//	}
//	/**
//	 * 
//	 * 
//	 * @campo Fecha_Hora_Fin
//	*/
//	public java.util.Date getFechaHoraFin() {
//		return fechaHoraFin;
//	}
//
//	/**
//	 * 
//	 * 
//	 * @campo Fecha_Hora_Fin
//	*/
//	public void setFechaHoraFin(java.util.Date fechaHoraFin) {
//		this.fechaHoraFin = fechaHoraFin;
//	}
	/**
	 * 
	 * 
	 * @campo ID_Docente
	*/
	public Integer getIdDocente() {
		return idDocente;
	}

	/**
	 * 
	 * 
	 * @campo ID_Docente
	*/
	public void setIdDocente(Integer idDocente) {
		this.idDocente = idDocente;
	}


	public Clases obtenerBean() {
		return obtenerBeanCore(new Clases(),ConstantePantallaAccion.NINGUNO);
	}

	public Clases obtenerBeanRegistrar() {
		return obtenerBeanCore(new Clases(),ConstantePantallaAccion.INSERTAR);
	}

	public Clases obtenerBeanActualizar(Clases bean) {
		return obtenerBeanCore(bean,ConstantePantallaAccion.ACTUALIZAR);
	}

	private Clases obtenerBeanCore(Clases bean,String tipo) {
		if (UString.esNuloVacio(tipo))
			tipo=ConstantePantallaAccion.NINGUNO;

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		switch (tipo) {
		case ConstantePantallaAccion.INSERTAR,ConstantePantallaAccion.NINGUNO:
			bean.getPk().setIdClase(idClase);
			bean.setNombre(nombre);
//			bean.setFechaHoraInicio(fechaHoraInicio);
//			bean.setFechaHoraFin(fechaHoraFin);
			bean.setIdDocente(idDocente);

			break;
		case ConstantePantallaAccion.ACTUALIZAR:

			break;
		}

		return bean;
	}

}
