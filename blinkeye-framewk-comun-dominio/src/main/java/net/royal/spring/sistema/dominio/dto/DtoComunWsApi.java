package net.royal.spring.sistema.dominio.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DtoComunWsApi {

	private BigDecimal idApi;
	private String host;
	private String nombre;
	private List<DtoComunWsApipath> paths;
	private List<DtoComunWsDefinicion> definiciones;

	public DtoComunWsApi() {
		this.paths = new ArrayList<DtoComunWsApipath>();
		this.definiciones = new ArrayList<DtoComunWsDefinicion>();
	}

	public BigDecimal getIdApi() {
		return idApi;
	}

	public void setIdApi(BigDecimal idApi) {
		this.idApi = idApi;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<DtoComunWsApipath> getPaths() {
		return paths;
	}

	public void setPaths(List<DtoComunWsApipath> paths) {
		this.paths = paths;
	}

	public List<DtoComunWsDefinicion> getDefiniciones() {
		return definiciones;
	}

	public void setDefiniciones(List<DtoComunWsDefinicion> definiciones) {
		this.definiciones = definiciones;
	}

}
