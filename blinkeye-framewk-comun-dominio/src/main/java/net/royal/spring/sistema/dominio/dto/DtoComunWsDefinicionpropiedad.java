package net.royal.spring.sistema.dominio.dto;

import java.math.BigDecimal;

public class DtoComunWsDefinicionpropiedad {

	private String nombre;
	private String tipodato;
	private String requerido;
	private Integer idDefinicion;
	private DtoComunWsDefinicionpropiedad definicion;

	public DtoComunWsDefinicionpropiedad() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipodato() {
		return tipodato;
	}

	public void setTipodato(String tipodato) {
		this.tipodato = tipodato;
	}

	public String getRequerido() {
		return requerido;
	}

	public void setRequerido(String requerido) {
		this.requerido = requerido;
	}

	public DtoComunWsDefinicionpropiedad getDefinicion() {
		return definicion;
	}

	public void setDefinicion(DtoComunWsDefinicionpropiedad definicion) {
		this.definicion = definicion;
	}

	public Integer getIdDefinicion() {
		return idDefinicion;
	}

	public void setIdDefinicion(Integer idDefinicion) {
		this.idDefinicion = idDefinicion;
	}

}
