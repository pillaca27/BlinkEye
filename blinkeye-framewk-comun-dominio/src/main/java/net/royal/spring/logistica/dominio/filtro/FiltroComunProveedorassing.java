package net.royal.spring.logistica.dominio.filtro;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunProveedorassing {

	private DominioPaginacion paginacion;
	
	private String item;
	private String commodity;
	private String linea;
	private String familia;
	private String proveedorcompradorflag;

	private String codigo;
	private String busqueda;
	
	
	public DominioPaginacion getPaginacion() {
		return paginacion;
	}
	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getCommodity() {
		return commodity;
	}
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	public String getFamilia() {
		return familia;
	}
	public void setFamilia(String familia) {
		this.familia = familia;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getBusqueda() {
		return busqueda;
	}
	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}
	public String getProveedorcompradorflag() {
		return proveedorcompradorflag;
	}
	public void setProveedorcompradorflag(String proveedorcompradorflag) {
		this.proveedorcompradorflag = proveedorcompradorflag;
	}
	
	
}
