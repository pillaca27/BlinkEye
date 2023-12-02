package net.royal.spring.sistema.dominio.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DtoComunWsDefinicion {

	private Integer idDefinicion;
	private Integer idApi;
	private String nombre;

	private List<DtoComunWsDefinicionpropiedad> propiedades;

	public DtoComunWsDefinicion() {
		this.propiedades = new ArrayList<DtoComunWsDefinicionpropiedad>();
	}

	public Integer getIdDefinicion() {
		return idDefinicion;
	}

	public void setIdDefinicion(Integer idDefinicion) {
		this.idDefinicion = idDefinicion;
	}

	public Integer getIdApi() {
		return idApi;
	}

	public void setIdApi(Integer idApi) {
		this.idApi = idApi;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<DtoComunWsDefinicionpropiedad> getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(List<DtoComunWsDefinicionpropiedad> propiedades) {
		this.propiedades = propiedades;
	}

}
