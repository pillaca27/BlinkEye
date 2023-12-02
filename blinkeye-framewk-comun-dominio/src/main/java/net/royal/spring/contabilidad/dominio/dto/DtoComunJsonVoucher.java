package net.royal.spring.contabilidad.dominio.dto;

import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;

public class DtoComunJsonVoucher extends DominioMensajeUsuario implements java.io.Serializable {

	private String voucherheader;
	private String voucherdetalle;

	public String getVoucherheader() {
		return voucherheader;
	}

	public void setVoucherheader(String voucherheader) {
		this.voucherheader = voucherheader;
	}

	public String getVoucherdetalle() {
		return voucherdetalle;
	}

	public void setVoucherdetalle(String voucherdetalle) {
		this.voucherdetalle = voucherdetalle;
	}

}
