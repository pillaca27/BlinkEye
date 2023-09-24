package net.royal.spring.sg.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class FiltroComunUsuario   extends DominioTransaccion{
	
	private DominioPaginacion paginacion;
	
	private String tipo;
	private String usuario;
	private String nombre;
	private String estado;
	
	public DominioPaginacion getPaginacion() {
		return paginacion;
	}
	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

}