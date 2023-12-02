package net.royal.spring.workflow.dominio.filtro;

public class FiltroReemplazo {
	private Integer id;
	private String nombre;
	private String descripcion;
	private String estado;
	private Integer reemplazo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getReemplazo() {
		return reemplazo;
	}

	public void setReemplazo(Integer reemplazo) {
		this.reemplazo = reemplazo;
	}
}
