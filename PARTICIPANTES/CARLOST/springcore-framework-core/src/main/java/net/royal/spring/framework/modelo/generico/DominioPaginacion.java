package net.royal.spring.framework.modelo.generico;

import java.util.List;

import net.royal.spring.framework.constante.ConstanteDatos.SORT_ORDER;

public class DominioPaginacion implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	
	private Integer paginacionRegistroInicio;
	private List paginacionListaResultado;
	private Integer paginacionRegistrosEncontrados;
	private Integer paginacionRegistrosPorPagina;
	
	private SORT_ORDER paginacionOrdenDireccion;
	private String paginacionOrdenAtributo;
			
	public SORT_ORDER getPaginacionOrdenDireccion() {
		return paginacionOrdenDireccion;
	}
	public void setPaginacionOrdenDireccion(SORT_ORDER paginacionOrdenDireccion) {
		this.paginacionOrdenDireccion = paginacionOrdenDireccion;
	}	
	public String getPaginacionOrdenAtributo() {
		return paginacionOrdenAtributo;
	}
	public void setPaginacionOrdenAtributo(String paginacionOrdenAtributo) {
		this.paginacionOrdenAtributo = paginacionOrdenAtributo;
	}
	public Integer getPaginacionRegistroInicio() {
		return paginacionRegistroInicio;
	}
	public void setPaginacionRegistroInicio(Integer paginacionRegistroInicio) {
		this.paginacionRegistroInicio = paginacionRegistroInicio;
	}
	public Integer getPaginacionRegistrosPorPagina() {
		return paginacionRegistrosPorPagina;
	}
	public void setPaginacionRegistrosPorPagina(Integer paginacionRegistrosPorPagina) {
		this.paginacionRegistrosPorPagina = paginacionRegistrosPorPagina;
	}
	public Integer getPaginacionRegistrosEncontrados() {
		return paginacionRegistrosEncontrados;
	}
	public void setPaginacionRegistrosEncontrados(Integer paginacionRegistrosEncontrados) {
		this.paginacionRegistrosEncontrados = paginacionRegistrosEncontrados;
	}
	public List getPaginacionListaResultado() {
		return paginacionListaResultado;
	}
	public void setPaginacionListaResultado(List paginacionListaResultado) {
		this.paginacionListaResultado = paginacionListaResultado;
	}

}
