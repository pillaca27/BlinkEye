package net.royal.spring.core.dominio;

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

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.departmentmst
*/
public class BeanDepartmentmstPk implements java.io.Serializable{



	@Size(min = 0, max = 3)
	@NotNull
	@NotEmpty
	@Column(name = "DEPARTMENT", length = 3, nullable = false)
	private String department;


	public BeanDepartmentmstPk() {
	}

	public BeanDepartmentmstPk(String pdepartment) {
this.department = pdepartment;
	}

	/**
	 * 
	 * 
	 * @campo department
	*/
	public String getDepartment() {
		return department;
	}

	/**
	 * 
	 * 
	 * @campo department
	*/
	public void setDepartment(String department) {
		this.department = department;
	}

}
