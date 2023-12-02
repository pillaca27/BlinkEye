package net.royal.spring.workflow.dominio.dto;

public class DtoJerarquiaMacroProceso {
	
	private String transaccionUUID;
	private Integer transaccion;
	private Integer padre;
	private String dato1;
	private String dato2;
	private String dato3;
	private String dato4;
	private String dato5;
	private String dato6;
	private String dato7;

	public Integer getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Integer transaccion) {
		this.transaccion = transaccion;
	}

	public Integer getPadre() {
		return padre;
	}

	public void setPadre(Integer padre) {
		this.padre = padre;
	}

	public String getDato1() {
		return dato1;
	}

	public void setDato1(String dato1) {
		this.dato1 = dato1;
	}

	public String getDato2() {
		return dato2;
	}

	public void setDato2(String dato2) {
		this.dato2 = dato2;
	}

	public String getDato3() {
		return dato3;
	}

	public void setDato3(String dato3) {
		this.dato3 = dato3;
	}

	public String getDato4() {
		return dato4;
	}

	public void setDato4(String dato4) {
		this.dato4 = dato4;
	}

	public String getDato5() {
		return dato5;
	}

	public void setDato5(String dato5) {
		this.dato5 = dato5;
	}

	public String getDato6() {
		return dato6;
	}

	public void setDato6(String dato6) {
		this.dato6 = dato6;
	}

	public String getDato7() {
		return dato7;
	}

	public void setDato7(String dato7) {
		this.dato7 = dato7;
	}

	public String getTransaccionUUID() {
		return transaccionUUID;
	}

	public void setTransaccionUUID(String transaccionUUID) {
		this.transaccionUUID = transaccionUUID;
	}

}
