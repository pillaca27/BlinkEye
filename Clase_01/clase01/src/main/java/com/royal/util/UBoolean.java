package com.royal.util;

public class UBoolean {
	public static Boolean validarFlag(String condicion) {
		Boolean condicionBoolean = false;
		if (condicion != null) {
			if (condicion.equals("S")) {
				condicionBoolean = true;
			}
		}
		return condicionBoolean;
	}

}
