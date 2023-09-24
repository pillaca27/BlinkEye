package com.royal.util;

public class UString {
	
	public static Boolean existeEnLista(String[] lista, String elementoBuscar) {
		if (estaVacio(elementoBuscar))
			return Boolean.FALSE;
		for (int i = 0; i < lista.length; i++) {
			String elemento = lista[i];
			if (elemento.equals(elementoBuscar))
				return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static boolean estaVacio(String valor) {
		if (valor != null) {
			if (!valor.trim().equals("")) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean esNuloVacio(Object varObject) {
		if (varObject == null) {
			return true;
		}
		String variable = (String) varObject;

		if (variable == null || variable.length() == 0) {
			return true;
		}
		return false;
	}

	public static boolean esNuloVacio(String variable) {
		if (variable == null || variable.length() == 0) {
			return true;
		}
		return false;
	}


}
