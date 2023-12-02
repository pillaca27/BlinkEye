package net.royal.spring.contabilidad.dominio.filtro;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class FiltroComunAcCashflow{
	
	private String tiporegistro;   
	private String tipooperacion;   
	private String grupoflujo;
	private String descripcion;
	private String codigo;
	 
	private DominioPaginacion paginacion=new DominioPaginacion();

	public String getTiporegistro() {
		return tiporegistro;
	}

	public void setTiporegistro(String tiporegistro) {
		this.tiporegistro = tiporegistro;
	}

	public String getTipooperacion() {
		return tipooperacion;
	}

	public void setTipooperacion(String tipooperacion) {
		this.tipooperacion = tipooperacion;
	}

	public String getGrupoflujo() {
		return grupoflujo;
	}

	public void setGrupoflujo(String grupoflujo) {
		this.grupoflujo = grupoflujo;
	}


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}
	
	
	
}
