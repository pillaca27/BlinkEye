package net.royal.spring.core.dominio.filtro;

import java.math.BigDecimal;
import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class FiltroComunUbigeo  extends DominioTransaccion{
	
	private DominioPaginacion paginacion;
	
	private String pais;
	private String departamento;
	private String provincia;
	private String zonapostal;
	
	public DominioPaginacion getPaginacion() {
		return paginacion;
	}
	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public String getZonapostal() {
		return zonapostal;
	}
	public void setZonapostal(String zonapostal) {
		this.zonapostal = zonapostal;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}	
}
