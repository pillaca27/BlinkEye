package net.royal.spring.sistema.dominio.lista;

import java.util.ArrayList;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.sistema.dominio.dto.DtoComunSySeguridadconcepto;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSySeguridadconcepto;

public class DtlComunSeguridadConcepto extends DominioTransaccion implements java.io.Serializable{

	private FiltroComunSySeguridadconcepto filtro;
	private List<DtoComunSySeguridadconcepto> listado;
	
	public FiltroComunSySeguridadconcepto getFiltro() {
		return filtro;
	}
	public void setFiltro(FiltroComunSySeguridadconcepto filtro) {
		this.filtro = filtro;
	}
	public List<DtoComunSySeguridadconcepto> getListado() {
		return listado;
	}
	public void setListado(List<DtoComunSySeguridadconcepto> listado) {
		this.listado = listado;
	}
	
}
