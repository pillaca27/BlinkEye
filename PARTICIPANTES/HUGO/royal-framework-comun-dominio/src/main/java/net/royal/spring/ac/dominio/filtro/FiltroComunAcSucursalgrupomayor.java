package net.royal.spring.ac.dominio.filtro;

import net.royal.spring.ac.dominio.BeanAcSucursalgrupomayor;

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
 * @tabla dbo.AC_SucursalGrupoMayor
*/
public class FiltroComunAcSucursalgrupomayor extends DominioTransaccion implements java.io.Serializable{

	private String sucursalgrupomayor;
	private String descripcionlocal;
	private String estado;
	private DominioPaginacion paginacion;

	/**
	 * 
	 * 
	 * @campo SucursalGrupoMayor
	*/
	public String getSucursalgrupomayor() {
		return sucursalgrupomayor;
	}

	/**
	 * 
	 * 
	 * @campo SucursalGrupoMayor
	*/
	public void setSucursalgrupomayor(String sucursalgrupomayor) {
		this.sucursalgrupomayor = sucursalgrupomayor;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionLocal
	*/
	public String getDescripcionlocal() {
		return descripcionlocal;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionLocal
	*/
	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}
	/**
	 * 
	 * 
	 * @campo Estado
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * 
	 * 
	 * @campo Estado
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public DominioPaginacion getPaginacion() {
		if (paginacion == null)
			paginacion = new DominioPaginacion();
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}


}
