package net.royal.spring.framework.util;

public class UClase {
	public static Class obtenerClase(String parametro) {

		Class clase = null;

		switch (parametro) {
		case "INTEGER":
			clase = Integer.class;
			break;
		case "STRING":
			clase = String.class;
			break;
		default:
			break;
		}

		return clase;
	}
}
