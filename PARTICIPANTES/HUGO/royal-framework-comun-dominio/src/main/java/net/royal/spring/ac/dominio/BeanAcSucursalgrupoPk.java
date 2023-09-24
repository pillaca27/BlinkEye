package net.royal.spring.ac.dominio;

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
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.AC_SucursalGrupo
*/
public class BeanAcSucursalgrupoPk implements java.io.Serializable{


	@Size(min = 0, max = 4)
	@NotEmpty
	@Column(name = "SUCURSALGRUPO", length = 4, nullable = false)
	private String sucursalgrupo;

	public BeanAcSucursalgrupoPk() {
	}

	public BeanAcSucursalgrupoPk(String psucursalgrupo) {
this.sucursalgrupo = psucursalgrupo;
	}

	/**
	 * 
	 * 
	 * @campo SucursalGrupo
	*/
	public String getSucursalgrupo() {
		return sucursalgrupo;
	}

	/**
	 * 
	 * 
	 * @campo SucursalGrupo
	*/
	public void setSucursalgrupo(String sucursalgrupo) {
		this.sucursalgrupo = sucursalgrupo;
	}


}
