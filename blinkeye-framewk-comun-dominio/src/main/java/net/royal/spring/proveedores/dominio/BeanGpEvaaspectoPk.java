package net.royal.spring.proveedores.dominio;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla dbo.GP_EVAASPECTO
*/
public class BeanGpEvaaspectoPk implements java.io.Serializable{



	@NotNull
	@Column(name = "EASEVAASPECTOID", nullable = false)
	private Integer easevaaspectoid;


	public BeanGpEvaaspectoPk() {
	}

	public BeanGpEvaaspectoPk(Integer peasevaaspectoid) {
this.easevaaspectoid = peasevaaspectoid;
	}

	/**
	 * 
	 * 
	 * @campo EASEVAASPECTOID
	*/
	public Integer getEasevaaspectoid() {
		return easevaaspectoid;
	}

	/**
	 * 
	 * 
	 * @campo EASEVAASPECTOID
	*/
	public void setEasevaaspectoid(Integer easevaaspectoid) {
		this.easevaaspectoid = easevaaspectoid;
	}

}
