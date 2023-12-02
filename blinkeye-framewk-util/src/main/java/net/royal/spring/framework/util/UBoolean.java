package net.royal.spring.framework.util;

public class UBoolean {
	public static Boolean validarEstado(String condicion) {
		Boolean condicionBoolean = false;
		if (condicion != null) {
			if (("A").equals(condicion)) {
				condicionBoolean = true;
			}
		}
		return condicionBoolean;
	}

	public static String obtenerNombreValorIngles(String flg) {
		if (flg == null)
			return "";
		if (flg.trim().length() == 0)
			return "";
		if (flg.equals("S")) {
			return "true";
		}
		return "false";
	}
	
	public static String obtenerValorNombreIngles(String flg) {
		if (flg == null)
			return null;
		if (flg.trim().length() == 0)
			return null;
		if (flg.equals("true")) {
			return "S";
		}
		return "N";
	}

	public static Boolean validarObjectFlag(Object condicion) {
		Boolean condicionBoolean = false;
		if (condicion != null) {
			if (condicion.equals("S")) {
				condicionBoolean = true;
			}
		}
		return condicionBoolean;
	}

	public static Boolean validarFlag(String condicion) {
		Boolean condicionBoolean = false;
		if (condicion != null) {
			if (condicion.equals("S")) {
				condicionBoolean = true;
			}
		}
		return condicionBoolean;
	}

	public static Boolean validarFlagLogicoNulo(Boolean condicion) {
		if (condicion == null) {
			return Boolean.FALSE;
		}
		return condicion;
	}

	public static Boolean validarFlagLogicoNulo(Boolean condicion, Boolean porDefecto) {
		if (condicion == null) {
			return porDefecto;
		}
		return condicion;
	}

	public static String validarFlagLogico(Boolean condicion) {
		if (condicion == null)
			return "N";
		if (condicion)
			return "S";
		else
			return "N";
	}

	public static Integer validarFlagToBit(String condicion) {
		if (condicion != null) {
			if (condicion.equals("S")) {
				return 1;
			}
		}
		return 0;
	}
}
