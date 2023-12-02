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
import net.royal.spring.dt.dominio.Docentes;

/**
 * 
 * 
 * @tabla dbo.Docentes
*/
public class DtoDocentes extends DominioTransaccion implements java.io.Serializable{

	private Integer idDocente;
	private String nombre;
	private String apellido;
	private String email;

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
	/**
	 * 
	 * 
	 * @campo Apellido
	*/
	public String getApellido() {
		return apellido;
	}

	/**
	 * 
	 * 
	 * @campo Apellido
	*/
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * 
	 * 
	 * @campo Email
	*/
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * 
	 * @campo Email
	*/
	public void setEmail(String email) {
		this.email = email;
	}


	public Docentes obtenerBean() {
		return obtenerBeanCore(new Docentes(),ConstantePantallaAccion.NINGUNO);
	}

	public Docentes obtenerBeanRegistrar() {
		return obtenerBeanCore(new Docentes(),ConstantePantallaAccion.INSERTAR);
	}

	public Docentes obtenerBeanActualizar(Docentes bean) {
		return obtenerBeanCore(bean,ConstantePantallaAccion.ACTUALIZAR);
	}

	private Docentes obtenerBeanCore(Docentes bean,String tipo) {
		if (UString.esNuloVacio(tipo))
			tipo=ConstantePantallaAccion.NINGUNO;

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		switch (tipo) {
		case ConstantePantallaAccion.INSERTAR,ConstantePantallaAccion.NINGUNO:
			bean.getPk().setIdDocente(idDocente);
			bean.setNombre(nombre);
			bean.setApellido(apellido);
			bean.setEmail(email);

			break;
		case ConstantePantallaAccion.ACTUALIZAR:

			break;
		}

		return bean;
	}

}
