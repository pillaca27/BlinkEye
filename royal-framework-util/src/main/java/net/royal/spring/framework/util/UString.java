package net.royal.spring.framework.util;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class UString {

	public static final int[] ASCII_DEC = { 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51,
			52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78,
			79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104,
			105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125,
			126,
			// No van
			/*
			 * 128, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 142, 145, 146,
			 * 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 158, 159, 161, 162, 163,
			 * 164, 165, 166, 167, 168, 169, 170, 171, 172, 174, 175, 176, 177, 178, 179,
			 * 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194,
			 * 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209,
			 * 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224,
			 * 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239,
			 * 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254,
			 * 255
			 */ };

	public static final char[] ASCII_CHAR = { '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.',
			'/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C',
			'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
			'Y', 'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~'

	};

	public static String concatenarArreglo(String[] lista) {
		String mensajeInterno = "";
		Integer contador = 0;
		if (lista == null)
			return "";

		for (String s : lista) {
			if (contador == 0)
				mensajeInterno = s;
			else
				mensajeInterno = mensajeInterno + "\n" + s;
			contador++;
		}
		return mensajeInterno;
	}

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

	public static String[] split(String text, String delemeter) {
		java.util.List<String> parts = new java.util.ArrayList<String>();

		if (estaVacio(text))
			return parts.toArray(new String[0]);

		text += delemeter;
		for (int i = text.indexOf(delemeter), j = 0; i != -1;) {
			String temp = text.substring(j, i);
			if (temp.trim().length() != 0) {
				parts.add(temp);
			}
			j = i + delemeter.length();
			i = text.indexOf(delemeter, j);
		}
		return parts.toArray(new String[0]);
	}

	public static String split(String text, String delemeter, Integer indice) {
		java.util.List<String> parts = new java.util.ArrayList<String>();
		String retorno;

		if (text == null)
			return null;

		text += delemeter;

		for (int i = text.indexOf(delemeter), j = 0; i != -1;) {
			String temp = text.substring(j, i);
			if (temp.trim().length() != 0) {
				parts.add(temp);
			}
			j = i + delemeter.length();
			i = text.indexOf(delemeter, j);
		}

		if (text.equals(delemeter))
			return null;

		if (indice > parts.size())
			return null;

		retorno = parts.get(indice);

		return retorno;
	}

	public static String upper(String text) {
		return estaVacio(text) ? "" : text.toUpperCase();
	}

	public static String cambiarCaracteresEspecialesHTML(String string) {
		if (estaVacio(string))
			return string;
		string = string.replace(Character.toString((char) 225), "&aacute;");
		string = string.replace(Character.toString((char) 233), "&eacute;");
		string = string.replace(Character.toString((char) 237), "&iacute;");
		string = string.replace(Character.toString((char) 243), "&oacute;");
		string = string.replace(Character.toString((char) 250), "&uacute;");

		string = string.replace(Character.toString((char) 241), "&ntilde;");

		string = string.replace(Character.toString((char) 193), "&Aacute;");
		string = string.replace(Character.toString((char) 201), "&Eacute;");
		string = string.replace(Character.toString((char) 205), "&Iacute;");
		string = string.replace(Character.toString((char) 211), "&Oacute;");
		string = string.replace(Character.toString((char) 218), "&Uacute;");

		string = string.replace(Character.toString((char) 209), "&Ntilde;");
		return string;
	}

	public static String cambiarCaracteresEspecialesUsuarioLogin(String string) {
		if (estaVacio(string))
			return string;
		string = string.replace(Character.toString((char) 225), "a");
		string = string.replace(Character.toString((char) 233), "e");
		string = string.replace(Character.toString((char) 237), "i");
		string = string.replace(Character.toString((char) 243), "o");
		string = string.replace(Character.toString((char) 250), "u");

		string = string.replace(Character.toString((char) 241), "n");

		string = string.replace(Character.toString((char) 193), "A");
		string = string.replace(Character.toString((char) 201), "E");
		string = string.replace(Character.toString((char) 205), "I");
		string = string.replace(Character.toString((char) 211), "O");
		string = string.replace(Character.toString((char) 218), "U");

		string = string.replace(Character.toString((char) 209), "N");

		string = string.replace("-", " ");
		string = string.replace("_", " ");
		return string;
	}

	/**
	 * Convierte un ASCII a char
	 *
	 * @param ascii , tipo Integer
	 * @return el valor del ASCII en char, tipo char
	 */
	public static char convertAscii2Char(int ascii) {
		for (int i = 0; i < ASCII_DEC.length; i++) {
			if (ASCII_DEC[i] == ascii) {
				return ASCII_CHAR[i];
			}
		}
		return (char) ascii;
	}

	/**
	 * Convierte una char a ASCII
	 *
	 * @param caracter , tipo char
	 * @return el valor en ASCII del char, tipo Integer
	 */
	public static int convertChar2Ascii(char caracter) {
		for (int i = 0; i < ASCII_CHAR.length; i++) {
			if (ASCII_CHAR[i] == caracter) {
				return ASCII_DEC[i];
			}
		}

		return (int) caracter;
	}

	public static String IntegerToString(Integer valorCadena) {
		if (valorCadena == null)
			return null;
		return valorCadena.toString();
	}

	/**
	 * Retorna FALSE cuando el texto es diferente de NULL y diferente de vacio. Caso
	 * contrario retorna TRUE;
	 * 
	 * @param valor texto que se evaluara, tipo String
	 * @return TRUE o FALSE dependiendo de la condicion cumplida
	 */
	public static boolean estaVacio(String valor) {
		if (valor != null) {
			if (!valor.trim().equals("")) {
				return false;
			}
		}
		return true;
	}

	public static String obtenerValorCadenaSinNulo(String variable) {
		if (variable == null || variable.length() == 0)
			return "";
		return variable;
	}

	public static String trimSinNulo(String variable) {
		if (variable == null)
			return "";
		return variable.trim();
	}

	public static String trimConNulo(String variable) {
		if (variable == null)
			return null;
		return variable.trim();
	}

	public static List<String> arregloCadenaToList(String[] arregloCadena) {
		List<String> lst = new ArrayList<String>();

		if (arregloCadena == null)
			return lst;

		for (int i = 0; i < arregloCadena.length; i++) {
			lst.add(arregloCadena[i]);
		}

		return lst;
	}

	public static List<String> listaLimpiarDuplicadosNulos(List<String> listaCorreos) {
		List<String> lst = new ArrayList<String>();

		for (String elemento : listaCorreos) {
			if (!estaVacio(elemento))
				lst.add(elemento);
		}
		listaCorreos = lst;

		Boolean flgEncontrado = Boolean.FALSE;
		for (String elemento : listaCorreos) {
			for (String correo : lst) {
				if (elemento.toUpperCase().equals(correo.toUpperCase())) {
					flgEncontrado = Boolean.TRUE;
					break;
				}
			}
			if (!flgEncontrado)
				lst.add(elemento);
			flgEncontrado = Boolean.FALSE;
		}

		return lst;
	}

	/**
	 * regresa la cadena especificada en tipo Date, null si no es posible
	 * 
	 * @param variable ej. "aaa"
	 * @return boolean
	 */
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

	public static String limpiarSql(String variable) {
		if (variable == null)
			variable = "";
		variable = variable.replace("'", "");
		variable = variable.replace(":", "");
		variable = variable.replace(";", "");
		return variable;
	}

	public static String extraer(String val, Integer inicio, Integer fin) {
		if (val == null)
			return "";
		if (fin == null)
			fin = 0;
		try {
			if (fin == 0)
				return val.substring(inicio);
			else
				return val.substring(inicio, fin);
		} catch (Exception e) {
			System.err.println("error en el extraer");
		}
		return "";
	}

	public static String obtenerSinNulo(String variable) {
		if (variable == null || variable.length() == 0)
			return "";
		return variable;
	}

	/**
	 * 
	 * @param numero
	 * @param formato ="%07d"
	 * @return
	 */
	public static String obtenerCodigoFormat(Integer numero, String formato) {
		String codigoCliente;
		if (numero == null)
			return numero + "";

		codigoCliente = String.format(formato, numero);

		return codigoCliente;
	}

	public static String obtenerMimeType(String filename) {
		if (filename != null)
			return URLConnection.guessContentTypeFromName(filename);
		return "application/vnd.ms-excel;charset=UTF-8";
	}

	public static String spring_MID(String string, int start, int length) {
		return string.substring(start - 1, start + length - 1);
	}
}
