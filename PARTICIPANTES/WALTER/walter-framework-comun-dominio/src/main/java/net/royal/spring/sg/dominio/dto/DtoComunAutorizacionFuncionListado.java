package net.royal.spring.sg.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.sg.dominio.BeanSeguridadautorizaciones;

public class DtoComunAutorizacionFuncionListado {
	
	
	private String seleccionar;
	private List<DtoComunAutorizacionFuncion> listado;
	
	public String getSeleccionar() {
		return seleccionar;
	}
	public void setSeleccionar(String seleccionar) {
		this.seleccionar = seleccionar;
	}
	public List<DtoComunAutorizacionFuncion> getListado() {
		return listado;
	}
	public void setListado(List<DtoComunAutorizacionFuncion> listado) {
		this.listado = listado;
	}
	
	
}
