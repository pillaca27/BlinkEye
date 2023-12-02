package net.royal.spring.framework.util;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UString {

	public static String f_convierte_fechaatexto(Date fecha) {
		String fechaString = "";
		if (fecha == null) {
			return fechaString;
		}
		fechaString = String.format("%02d", UFechaHora.obtenerDia(fecha)) + " de "
				+ UFechaHora.obtenerMesEnCadena(UFechaHora.obtenerMes(fecha)) + " de " + UFechaHora.obtenerAnio(fecha);
		return fechaString;
	}

	public static final int[] ASCII_DEC = { 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51,
			52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78,
			79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104,
			105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125,
			126, 
			//No van
			/*128, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 142, 145, 146, 147, 148, 149, 150, 151,
			152, 153, 154, 155, 156, 158, 159, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 174, 175,
			176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196,
			197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217,
			218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238,
			239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255*/ };

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

	/**
	 * https://lefunes.wordpress.com/2007/11/14/caracteres-especiales-en-javascript/
	 * 
	 * @param string
	 * @return
	 */
	public static String cambiarCaracteresEspecialesUnicode(String string) {
		if (estaVacio(string))
			return string;
		string = string.replace(Character.toString((char) 225), "\u00E1");
		string = string.replace(Character.toString((char) 233), "\u00E9");
		string = string.replace(Character.toString((char) 237), "\u00ED");
		string = string.replace(Character.toString((char) 243), "\u00F3");
		string = string.replace(Character.toString((char) 250), "\u00FA");

		string = string.replace(Character.toString((char) 241), "\u00F1");

		string = string.replace(Character.toString((char) 193), "\u00C1");
		string = string.replace(Character.toString((char) 201), "\u00C9");
		string = string.replace(Character.toString((char) 205), "\u00CD");
		string = string.replace(Character.toString((char) 211), "\u00D3");
		string = string.replace(Character.toString((char) 218), "\u00DA");

		string = string.replace(Character.toString((char) 209), "\u00D1");
		return string;
	}

	public static String cambiarCaracteresEspecialesHTML(String string) {
		if (estaVacio(string))
			return string;
		string = string.replace(Character.toString((char)225), "&aacute;");
		string = string.replace(Character.toString((char)233), "&eacute;");
		string = string.replace(Character.toString((char)237), "&iacute;");
		string = string.replace(Character.toString((char)243), "&oacute;");
		string = string.replace(Character.toString((char)250), "&uacute;");

		string = string.replace(Character.toString((char)241), "&ntilde;");

		string = string.replace(Character.toString((char)193), "&Aacute;");
		string = string.replace(Character.toString((char)201), "&Eacute;");
		string = string.replace(Character.toString((char)205), "&Iacute;");
		string = string.replace(Character.toString((char)211), "&Oacute;");
		string = string.replace(Character.toString((char)218), "&Uacute;");
		
		string = string.replace(Character.toString((char)209), "&Ntilde;");
		return string;
	}

	public static String cambiarCaracteresEspecialesUsuarioLogin(String string) {
		if (estaVacio(string))
			return string;
		string = string.replace(Character.toString((char)225), "a");
		string = string.replace(Character.toString((char)233), "e");
		string = string.replace(Character.toString((char)237), "i");
		string = string.replace(Character.toString((char)243), "o");
		string = string.replace(Character.toString((char)250), "u");
		
		string = string.replace(Character.toString((char)241), "n");
		
		string = string.replace(Character.toString((char)193), "A");
		string = string.replace(Character.toString((char)201), "E");
		string = string.replace(Character.toString((char)205), "I");
		string = string.replace(Character.toString((char)211), "O");
		string = string.replace(Character.toString((char)218), "U");
		
		string = string.replace(Character.toString((char)209), "N");
		
		string = string.replace("-", " ");
		string = string.replace("_", " ");
		return string;
	}

	/**
	 * Convierte un ASCII a char
	 *
	 * @param ascii
	 *            , tipo Integer
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
	 * @param caracter
	 *            , tipo char
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
		if (valorCadena==null)
			return null;
		return valorCadena.toString();
	}

	/**
	 * Retorna FALSE cuando el texto es diferente de NULL y diferente de vacio. Caso
	 * contrario retorna TRUE;
	 * 
	 * @param valor
	 *            texto que se evaluara, tipo String
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

	public static String trimSinNuloUpper(String variable) {
		if (variable == null)
			return "";
		return variable.trim().toUpperCase();
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
	 * @param variable
	 *            ej. "aaa"
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

	/**
	 * se debe considerar los saltos de linea /n /r como contenido caso formaldor de
	 * planillas
	 * 
	 * @param variable
	 * @return
	 */
	public static boolean esNuloVacioTrim(String variable) {
		if (variable == null || variable.length() == 0) {
			return true;
		} else {
			if (variable.length() == 0)
				return true;
		}
		return false;
	}

	public static String esPeriodoValido(String variable) {
		if (variable == null || variable.length() == 0) {
			return null;
		} else {
			variable = variable.trim();
			variable = variable.replace("-", "");
		}
		return variable;
	}

	public static String limpiarSql(String variable) {
		if (variable == null)
			variable = "";
		variable = variable.replace("'", "");
		variable = variable.replace(":", "");
		variable = variable.replace(";", "");
		return variable;
	}

	public static String f_replace(String w_documento, String w_1, String w_2) {
		String w_result = "";
		w_result = w_documento.replace(w_1, w_2);

		return w_result;
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

	public static String obtener_mes(String month) {
		String resultado = "";

		switch (month) {
		case "01":
			resultado = "Enero";
			break;

		case "02":
			resultado = "Febrero";
			break;

		case "03":
			resultado = "Marzo";
			break;

		case "04":
			resultado = "Abril";
			break;

		case "05":
			resultado = "Mayo";
			break;

		case "06":
			resultado = "Junio";
			break;

		case "07":
			resultado = "Julio";
			break;

		case "08":
			resultado = "Agosto";
			break;

		case "09":
			resultado = "Septiembre";
			break;

		case "10":
			resultado = "Octubre";
			break;

		case "11":
			resultado = "Noviembre";
			break;

		case "12":
			resultado = "Diciembre";
			break;
		}

		return resultado;
	}

	public static String booleanAflag(Boolean w_flag) {
		if (w_flag == null)
			return "N";

		if (w_flag)
			return "S";
		else
			return "N";
	}

	public static String booleanAflagInvese(Boolean w_flag) {
		if (w_flag == null)
			return "S";

		if (w_flag)
			return "N";
		else
			return "S";
	}

	public static String obtenerMimeType(String filename) {
		if (filename != null)
			return URLConnection.guessContentTypeFromName(filename);
		return "application/vnd.ms-excel;charset=UTF-8";
	}

	public static String correlativoIzquieda(Integer codigo) {
		String salida = String.format("%08d", codigo);
		return salida;
	}

	public static String correlativoIzquieda06_default(Integer codigo) {
		String salida = String.format("%06d", codigo);
		return salida;
	}

	public static String correlativoIzquieda06(Integer codigo) {
		String salida = String.format("%06d", codigo);
		return "Nueva Obligaci\u00F3n Registrada , Registro N\u00FAmero " + salida;
	}

	public static String correlativoIzquieda06_update(Integer codigo) {
		String salida = String.format("%06d", codigo);
		return "Se \u00E1 el Registro N\u00FAmero " + salida;
	}

	public static String correlativoIzquiedaxDigitos(Integer codigo, Integer digitos) {
		String f_format = "%0" + digitos + "d";
		String salida = String.format(f_format, codigo);
		return salida;
	}

	public static String title_error(String tipo, String aux) {
		String title = "";
		if (tipo.equals("DW")) {
			title = "Error";
		} else if (tipo.equals("DW_DETAIL")) {
			title = "Error Linea " + aux + " ";
		} else {
			title = tipo;
		}

		return title;
	}

	public static String f_advance_descripcion(String par_action, String par_string) {
		String w_work;
		// "123456789012345678901234567"
		// "(-) Adelanto FP-001-0000001"
		if (par_action.equals("Descripcion")) {
			w_work = "(-) Adelanto " + par_string;
		} else {
			w_work = par_string.trim().substring(13, par_string.trim().length());
		}

		return w_work;
	}

	public static String nvo_cx_of_html_table(String a_accion, String a_valor) {
		return nvo_cx_of_html_table(a_accion, a_valor, "");
	}

	private static String nvo_cx_of_html_table(String a_accion, String a_valor, String a_alineamiento) {

		String ls_return = "";

		if (UString.estaVacio(a_valor)) {
			a_valor = "";
		}
		if (a_valor.length() == 0) {
			a_valor = " ";
		}

		if (UString.estaVacio(a_alineamiento)) {
			a_alineamiento = "";
		}
		if (a_alineamiento.length() == 0) {
			a_alineamiento = "I";
		}

		if (a_alineamiento.equals("I")) {
			a_alineamiento = " style= \"text-align:left;\"";
		} else {
			a_alineamiento = " style= \"text-align:right;\"";
		}

		switch (a_accion) {
		case "beg-table":
			ls_return = "<table>";
			break;
		case "beg-row":
			ls_return = "<tr>";
			break;
		case "heading":
			ls_return = "<th" + a_alineamiento + '>' + a_valor + "</th>";
			break;
		case "data":
			ls_return = "<td" + a_alineamiento + '>' + a_valor + "</td>";
			break;
		case "end-row":
			ls_return = "</tr>";
			break;
		case "end-table":
			ls_return = "</table>";
			break;
		}

		return ls_return;
	}

	public static String spring_MID(String string, int start, int length) {
		if (length < 0)
			return "";
		if (length >= string.length())
			return string.substring(start - 1);
		return string.substring(start - 1, start + length - 1);
	}

	public static String spring_RIGHT(String string, int length) {
		int start = string.length() - length;
		return string.substring(start);
	}

	public static String prepararFiltroMultiple(String string) {
		if (UString.estaVacio(string))
			return null;
		else
			return "," + string + ",";
	}
	
	public static Integer spring_POS(String string,String string2) {
		 Integer result = 0;
		 
		 result = string.indexOf(string2);
		 if(result < 1) {
			 return 0;
		 }
		 System.out.println(result);
		 return result;
	}
}
