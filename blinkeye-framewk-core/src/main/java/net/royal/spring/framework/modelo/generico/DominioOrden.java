package net.royal.spring.framework.modelo.generico;

import java.io.Serializable;

import net.royal.spring.framework.constante.ConstanteDatos.SORT_ORDER;

public class DominioOrden implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -57870284546898794L;

	private String atributoOrdenar;
	private SORT_ORDER direccionOrdenAtributo;

	public String getAtributoOrdenar() {
		return atributoOrdenar;
	}

	public void setAtributoOrdenar(String atributoOrdenar) {
		this.atributoOrdenar = atributoOrdenar;
	}

	public SORT_ORDER getDireccionOrdenAtributo() {
		return direccionOrdenAtributo;
	}

	public void setDireccionOrdenAtributo(SORT_ORDER direccionOrdenAtributo) {
		this.direccionOrdenAtributo = direccionOrdenAtributo;
	}

}
