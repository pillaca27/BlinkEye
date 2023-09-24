package net.royal.spring.framework.util;

public class UInteger {
	
	public static String obtenerValorCadenaRelleno(Integer numero,String relleno,Integer longitud) {
		String nro = numero.toString();
		Integer nrolen = nro.length() + relleno.length();
		
		nro = relleno + nro;
		
		Integer i = nrolen - longitud;
		nro = nro.substring(i);
		
		return nro;
	}
	
	public static Integer obtenerValorCadena(String numero) {
		if (UString.esNuloVacio(numero))
			return 0;
		return Integer.parseInt(numero);
	}

	public static Integer StringToInteger(String valorEntero) {
		if (valorEntero != null) {
			if (!valorEntero.trim().equals("")) {
				return null;
			}
		}
		return Integer.valueOf(valorEntero);
	}

	public static Integer obtenerValor(Integer numero) {
		if (numero == null) {
			return 0;
		}
		return numero;
	}

	public static Integer obtenerValorEnteroSinNulo(Integer variable) {
		if (variable == null)
			return 0;
		return variable;
	}

	public static boolean esCeroOrNulo(Integer numero) {
		if (numero == null) {
			return true;
		}
		if (numero == 0) {
			return true;
		}
		return false;
	}

	public static Integer sumar(Integer variable1, Integer variable2) {
		return obtenerValorEnteroSinNulo(variable1) + obtenerValorEnteroSinNulo(variable2);
	}
}
