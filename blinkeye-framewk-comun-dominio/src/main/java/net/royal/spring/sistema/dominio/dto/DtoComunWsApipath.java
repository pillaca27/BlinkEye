package net.royal.spring.sistema.dominio.dto;

import java.math.BigDecimal;

public class DtoComunWsApipath {

	private Integer idApi;
	private Integer idPath;
	private String nombre;
	private String nombre2;
	private String metodo;
	private String notas;
	private String vb;

	private String tipodatoRequest;
	private Integer idDefinicionRequest;
	private DtoComunWsDefinicion definicionRequest;

	private String tipodatoResponse;
	private Integer idDefinicionResponse;
	private DtoComunWsDefinicion definicionResponse;

	private Integer ROWNUM_;

	public Integer getIdApi() {
		return idApi;
	}

	public void setIdApi(Integer idApi) {
		this.idApi = idApi;
	}

	public Integer getIdPath() {
		return idPath;
	}

	public void setIdPath(Integer idPath) {
		this.idPath = idPath;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public String getTipodatoRequest() {
		return tipodatoRequest;
	}

	public void setTipodatoRequest(String tipodatoRequest) {
		this.tipodatoRequest = tipodatoRequest;
	}

	public DtoComunWsDefinicion getDefinicionRequest() {
		return definicionRequest;
	}

	public void setDefinicionRequest(DtoComunWsDefinicion definicionRequest) {
		this.definicionRequest = definicionRequest;
	}

	public String getTipodatoResponse() {
		return tipodatoResponse;
	}

	public void setTipodatoResponse(String tipodatoResponse) {
		this.tipodatoResponse = tipodatoResponse;
	}

	public DtoComunWsDefinicion getDefinicionResponse() {
		return definicionResponse;
	}

	public void setDefinicionResponse(DtoComunWsDefinicion definicionResponse) {
		this.definicionResponse = definicionResponse;
	}

	public Integer getIdDefinicionRequest() {
		return idDefinicionRequest;
	}

	public void setIdDefinicionRequest(Integer idDefinicionRequest) {
		this.idDefinicionRequest = idDefinicionRequest;
	}

	public Integer getIdDefinicionResponse() {
		return idDefinicionResponse;
	}

	public void setIdDefinicionResponse(Integer idDefinicionResponse) {
		this.idDefinicionResponse = idDefinicionResponse;
	}

	public Integer getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(Integer rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

	public String getVb() {
		return vb;
	}

	public void setVb(String vb) {
		this.vb = vb;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

}
