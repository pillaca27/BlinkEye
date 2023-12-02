package net.royal.spring.framework.modelo;

import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class DtoWhExportar {

	private DominioPaginacion paginacion;
	private String tipoExpotar;
	private String titulo;
	private String[] arrCabecera;
	private String[] arrColumnas;
	private List lista;
	
	public DominioPaginacion getPaginacion() {
		return paginacion;
	}
	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}
	public String getTipoExpotar() {
		return tipoExpotar;
	}
	public void setTipoExpotar(String tipoExpotar) {
		this.tipoExpotar = tipoExpotar;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String[] getArrCabecera() {
		return arrCabecera;
	}
	public void setArrCabecera(String[] arrCabecera) {
		this.arrCabecera = arrCabecera;
	}
	public String[] getArrColumnas() {
		return arrColumnas;
	}
	public void setArrColumnas(String[] arrColumnas) {
		this.arrColumnas = arrColumnas;
	}
	public List getLista() {
		return lista;
	}
	public void setLista(List lista) {
		this.lista = lista;
	}
		
}
