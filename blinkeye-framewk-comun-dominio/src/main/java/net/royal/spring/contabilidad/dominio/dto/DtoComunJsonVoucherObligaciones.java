package net.royal.spring.contabilidad.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;

public class DtoComunJsonVoucherObligaciones extends DominioMensajeUsuario implements java.io.Serializable {

	private String keytexto1;
	private String keytexto2;
	private Integer keynumero1;
	private String voucherasignado;

	public String getVoucherasignado() {
		return voucherasignado;
	}

	public void setVoucherasignado(String voucherasignado) {
		this.voucherasignado = voucherasignado;
	}

	public String getKeytexto1() {
		return keytexto1;
	}

	public void setKeytexto1(String keytexto1) {
		this.keytexto1 = keytexto1;
	}

	public String getKeytexto2() {
		return keytexto2;
	}

	public void setKeytexto2(String keytexto2) {
		this.keytexto2 = keytexto2;
	}

	public Integer getKeynumero1() {
		return keynumero1;
	}

	public void setKeynumero1(Integer keynumero1) {
		this.keynumero1 = keynumero1;
	}

}
