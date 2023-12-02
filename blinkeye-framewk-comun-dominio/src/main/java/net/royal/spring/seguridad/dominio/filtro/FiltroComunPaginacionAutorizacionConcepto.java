package net.royal.spring.seguridad.dominio.filtro;

 
import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunPaginacionAutorizacionConcepto {

	
	public String codigoAplicacion;
	public String usuario;
	public DominioPaginacion paginacion;
	public String grupo; 
	public String tipoexportar;
	
	
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getCodigoAplicacion() {
		return codigoAplicacion;
	}
	public void setCodigoAplicacion(String codigoAplicacion) {
		this.codigoAplicacion = codigoAplicacion;
	}
	 
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public DominioPaginacion getPaginacion() {
		return paginacion;
	}
	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}
	public String getTipoexportar() {
		return tipoexportar;
	}
	public void setTipoexportar(String tipoexportar) {
		this.tipoexportar = tipoexportar;
	}
	
	
	
}
