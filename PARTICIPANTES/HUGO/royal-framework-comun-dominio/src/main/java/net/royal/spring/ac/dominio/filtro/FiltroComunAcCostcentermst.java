package net.royal.spring.ac.dominio.filtro;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;

public class FiltroComunAcCostcentermst {
	private String costcenter;
	private String localname;
	
	private String idCompania;
	private String idUsuario;
	private String idAplicacion;
	private Integer idPersona;
	private String valor1;
	private String status;
	
	private String principales = "N";
	
	private DominioPaginacion paginacion=new DominioPaginacion();
	
	
	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCostcenter() {
		return costcenter;
	}

	public void setCostcenter(String costcenter) {
		this.costcenter = costcenter;
	}

	public String getLocalname() {
		return localname;
	}

	public void setLocalname(String localname) {
		this.localname = localname;
	}

	public String getIdCompania() {
		return idCompania;
	}

	public void setIdCompania(String idCompania) {
		this.idCompania = idCompania;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdAplicacion() {
		return idAplicacion;
	}

	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getValor1() {
		return valor1;
	}

	public void setValor1(String valor1) {
		this.valor1 = valor1;
	}

	public String getPrincipales() {
		return principales;
	}

	public void setPrincipales(String principales) {
		this.principales = principales;
	}

}
