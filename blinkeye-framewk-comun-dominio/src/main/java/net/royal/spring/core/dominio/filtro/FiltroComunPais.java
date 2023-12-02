package net.royal.spring.core.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

/**
 * 
 * 
 * @tabla dbo.Pais
*/
public class FiltroComunPais extends DominioTransaccion implements java.io.Serializable{

	private String pais;
	private String estado;
	private String nombre;
	private DominioPaginacion paginacion;

	/**
	 * 
	 * 
	 * @campo Pais
	*/
	public String getPais() {
		return pais;
	}

	/**
	 * 
	 * 
	 * @campo Pais
	*/
	public void setPais(String pais) {
		this.pais = pais;
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

	public DominioPaginacion getPaginacion() {
		if (paginacion == null)
			paginacion = new DominioPaginacion();
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}


}
